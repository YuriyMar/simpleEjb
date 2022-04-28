declare 
sumbyact numeric;

BEGIN
select (select round(cast((materials+transport+zarp) as decimal),2) + overall as summa_akt from
(select
            /*Загальновиробничі витрати для актов на объекты на сторону на ТО
    и ремонты */
    (select case when calc_type = 1 then (round(sum(payswork) * (case when isActDateAfter01052014 +
           isServicesDateAfter01052014 = 2 then 0.6
     	 else 0.7 end), 2)) else round(cast(sum(generalexpenses) as numeric), 2) end as overall from
(Select
	p2h.tabnumber,
	p2h.fio,
	cast(p2h.salary as numeric),
	cast(p2h.timemonth as numeric),
	cast(p2h.salaryhours as numeric) as salaryhours,
	cast(sum(p2h.timework) as numeric) as timework,
	cast(sum(p2h.paysworkbonus) as numeric) as payswork ,
	cast(sum(p2h.generalexpenses) as numeric) as generalexpenses ,
    (select fww.chargepercent from finworker fww
         where fww.code in (select case when p2h.humenkindrefcode  = 1 /*если електротехнич персонал*/
                          then (
                           select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = hum.planrefcode

                              and hum.finworkercode = fw.code )
                          else (
                           select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = aut.planrefcode

                              and aut.finworkercode = fw.code ) end
                           )
        ) as chargepercent  ,
        (select fww.chargerefcode from finworker fww
         where fww.code in (select case when p2h.humenkindrefcode = 1 /*если електротехнич персонал*/
                          then (
                           select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = hum.planrefcode

                              and hum.finworkercode = fw.code )
                          else (
                           select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = aut.planrefcode

                              and aut.finworkercode = fw.code ) end
                           )
        ) as chargerefcode,
	coalesce((select case when contractdateservices >= to_date('01.05.2014','dd.MM.yyyy') then 1
	else 0 end from enservicesobject
	where enservicesobject.elementcode = (select elementcode from enact where code = ac_super.code)),1)
 	as isServicesDateAfter01052014,
    coalesce((select case when dategen >= to_date('01.05.2014','dd.MM.yyyy') then 1
  	else 0 end from enact where code = ac_super.code),1)  as isActDateAfter01052014,
    coalesce((select calctyperefcode from enservicesobject
	where enservicesobject.elementcode = (select elementcode from enact where code = ac_super.code)),1)
 	as calc_type

from
	enact2enplanwork a2p,
	enplanwork pw,
	enplanworkitem pwi,
	enplanworkitem2humen p2h,
	tktechcard as tc,
    tkclassificationtype as tkcls
where  a2p.actrefcode = ac_super.code
   and a2p.plancode = pw.code
   and pw.code = pwi.planrefcode
   and pwi.countgen <> 0
   and pwi.code = p2h.plaitemrefcode
   and tc.code = pwi.kartarefcode
   and (tkcls.costworkscontractor is null
   or tkcls.costworkscontractor = 0)
   and tkcls.code = tc.classificationtypecode
   Group by     a2p.actrefcode , p2h.bonus , p2h.tabnumber , p2h.fio  , p2h.salary , p2h.timemonth , p2h.salaryhours , p2h.humenkindrefcode
   Order by p2h.tabnumber) as query group by isActDateAfter01052014, isServicesDateAfter01052014, calc_type) as overall,
    (

       /*Запрос материалы  */
    
   select coalesce(sum(stoimost),0) into sumbyact from
        (
        Select                       mat_name ,mu_name , sum(countgen) as countgen  ,
                      cost ,                       sum(stoimost) as stoimost ,
                      bal_sch  , nn , kindrefcode         From        (
            Select                         Case When estimate.kindrefcode = 2 Then finmat.mat_name||('(ПММ)')                   Else finmat.mat_name end as mat_name                , finmat.mu_name
               , finmat.quantity  as countgen
               , finmat.calc_price  as cost                     , coalesce(finmat.cost,0) as stoimost
               , finmat.bal_sch
               , finmat.nn
               , estimate.kindrefcode
             From                 enestimateitem estimate                   , finmaterials finmat
                  , enact2enplanwork ena2             where                 ena2.actrefcode = ac_super.code
                 and ena2.plancode = estimate.planrefcode
                 and finmat.estimateitemrefcode = estimate.code
                 and finmat.statusrefcode = 1
                 and estimate.kindrefcode in (1,2)
                 and finmat.cost<> 0) sel1            group by            sel1.mat_name ,              sel1.mu_name ,
           sel1.bal_sch ,            sel1.nn,             sel1.cost,
           kindrefcode                 order by  nn , bal_sch , mat_name , mu_name , cost)  as mat_query
    ) as materials,
      /*Транспорт*/
      (select coalesce(sum(payswork),0) from (SELECT  en2tr.code,
 en2tr.invnumber,
 en2tr.name as nameavto ,
 en2tr.expense as countfact ,
 en2tr.depreciationmonth,
 en2tr.depreciationhours,
 en2tr.timework as countworkfact ,
 en2tr.payswork,
 en2tr.modify_time,
 en2tr.actrefcode,
 (select tr.code from tktransportreal tr where tr.invnumber = en2tr.invnumber) as transportcode
FROM  net.enact2transport en2tr  where en2tr.actrefcode = ac_super.code
Order by en2tr.name) as transport_query) as transport,

/*Зарплата персонала*/

(select (sum(payswork) + sum(round(cast(payswork*chargepercent/100 as decimal),2))) as total_zarplata from
(Select
	p2h.tabnumber,
	p2h.fio,
	cast(p2h.salary as numeric),
	cast(p2h.timemonth as numeric),
	cast(p2h.salaryhours as numeric) as salaryhours,
	cast(sum(p2h.timework) as numeric) as timework,
	cast(sum(p2h.paysworkbonus) as numeric) as payswork ,
    (select fww.chargepercent from finworker fww
         where fww.code in (select case when p2h.humenkindrefcode  = 1 /*если електротехнич персонал*/
                          then (
                           select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = hum.planrefcode

                              and hum.finworkercode = fw.code )
                          else (
                           select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = aut.planrefcode

                              and aut.finworkercode = fw.code ) end
                           )
        ) as chargepercent  ,
        (select fww.chargerefcode from finworker fww
         where fww.code in (select case when p2h.humenkindrefcode = 1 /*если електротехнич персонал*/
                          then (
                           select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = hum.planrefcode

                              and hum.finworkercode = fw.code )
                          else (
                           select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw
                            where a2p1.actrefcode = a2p.actrefcode
                              and cast(fw.tabnumber as varchar) =  p2h.tabnumber
                              and a2p1.plancode = aut.planrefcode

                              and aut.finworkercode = fw.code ) end
                           )
        ) as chargerefcode

from
	enact2enplanwork a2p,
	enplanwork pw,
	enplanworkitem pwi,
	enplanworkitem2humen p2h,
	tktechcard as tc,
    tkclassificationtype as tkcls
where  a2p.actrefcode = ac_super.code
   and a2p.plancode = pw.code
   and pw.code = pwi.planrefcode
   and pwi.countgen <> 0
   and pwi.code = p2h.plaitemrefcode
   and tc.code = pwi.kartarefcode
   and (tkcls.costworkscontractor is null
   or tkcls.costworkscontractor = 0)
   and tkcls.code = tc.classificationtypecode
   Group by     a2p.actrefcode , p2h.bonus , p2h.tabnumber , p2h.fio  , p2h.salary , p2h.timemonth , p2h.salaryhours , p2h.humenkindrefcode
   Order by p2h.tabnumber) as query) as zarp
from
enact as ac_super
where
ac_super.code = ac.code) as act_super
) as summa_akt
from
enact as ac
where
ac.code = p_actcode ;
return   sumbyact; 
   
END;
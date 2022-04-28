CREATE OR REPLACE FUNCTION net.get_sum_by_act_only_zp(p_actcode double precision)
 RETURNS numeric
 LANGUAGE plpgsql
AS $function$
declare 
sumbyact numeric;

BEGIN
select (select round(cast((elteh_zarp+voditel_zarp) as decimal),2) + overall as summa_akt from
(select
            /*Загальновиробничі витрати для актов на объекты на сторону на ТО
    и ремонты */
    coalesce((
select coalesce(round(cast(sum(paysworkbonus) * 
   /*(case when isActDateAfter01052014 +
              isServicesDateAfter01052014 + 
              isOperativeDateAfter01052014 + 
              isTechCondDateAfter01052014 = 4 then 0.6 else 0.7 end)
              переделал выборку процента общепроизводственных затрат на функцию 16.05.2017 */
         ((select * from get_productionexpencespercent(ac_super.code::numeric)) /100 ) as decimal),2),0) from
(select distinct achu1.*,
	coalesce((select case when contractdateservices >= to_date('01.05.2014','dd.MM.yyyy') then 1
	else 0 end from enservicesobject
	where enservicesobject.elementcode = (select elementcode from enact where code = ac_super.code)),1)
 	as isServicesDateAfter01052014,
    coalesce((select case when dategen >= to_date('01.05.2014','dd.MM.yyyy') then 1
  	else 0 end from enact where code = ac_super.code),1)  as isActDateAfter01052014,
	coalesce((select case when contractdate >= to_date('01.05.2014','dd.MM.yyyy') then 1
	else 0 end from enoperativeobject
	where enoperativeobject.elementcode = (select elementcode from enact where code = ac_super.code)),1)
	 as isOperativeDateAfter01052014,
     coalesce((select case when tcs.contractdate  >= to_date('01.05.2014','dd.MM.yyyy') then 1
else 0 end
from ENTECHCOND2PLANWORK tc2pl  , ENTECHCONDITIONSSERVCS tcs
where tc2pl.planrefcode in (
select a2p.plancode from enact2enplanwork a2p
where a2p.actrefcode = ac_super.code)
and tcs.code = tc2pl.techconservicesrefcode
limit 1
),1) as isTechCondDateAfter01052014
    from enact as ac1 inner join enact2humen as achu1 on ac1.code = achu1.actrefcode
	inner join enelement as el1 on el1.code = ac1.elementcode
	inner join enact2enplanwork as acpw1 on ac1.code = acpw1.actrefcode
	inner join enplanwork as pw1 on acpw1.plancode = pw1.code

	 where 
    ac1.code = ac_super.code
	and
	(
		el1.typerefcode = 22
		or (pw1.staterefcode = 4 and pw1.typerefcode = 7)
		or (pw1.staterefcode = 2 and pw1.typerefcode in (7, 23))
		or (pw1.staterefcode = 29)
		
	)
	and ac1.dategen >= to_date('01.05.2012','dd.mm.yyyy')) as query group by isServicesDateAfter01052014, isActDateAfter01052014, isOperativeDateAfter01052014, isTechCondDateAfter01052014), 0
) as overall,


/*Зарплата электротехнического персонала*/

(select
coalesce(cast((sum(paysworkbonus)+sum(esv)) as decimal),0) as total_zarplata
from
(select
achu1.paysworkbonus,
round(cast(achu1.paysworkbonus * (select round(cast(finworker.chargepercent/100 as numeric),4) from finworker where code = (select
max(fi.code)
from     enact2humen as achu
    inner join
   (enact2enplanwork as acpw
       inner join
       (enhumenitem as hu
       inner join
       finworker as fi
       on
       fi.code = hu.finworkercode
       )
       on
       hu.planrefcode = acpw.plancode
   )
   on
   achu.actrefcode = acpw.actrefcode
where
cast(fi.tabnumber as text) = cast( achu1.tabnumber as text)
and achu.actrefcode = achu1.actrefcode)) as numeric),10) as esv
from
enact2humen as achu1
where
achu1.actrefcode = ac_super.code
and achu1.humenkindrefcode = 1) as elteh_query) as elteh_zarp,

/*Зарплата водителей*/

(select
coalesce(cast((sum(paysworkbonus)+sum(esv)) as decimal),0) as total_zarplata
from
(select
achu1.paysworkbonus,
round(cast(achu1.paysworkbonus * (select round(cast(finworker.chargepercent/100 as numeric),4) from finworker where code = (select
max(fi.code)
from     enact2humen as achu
    inner join
   (enact2enplanwork as acpw
       inner join
       (entransportitem as tr
       inner join
       finworker as fi
       on
       fi.code = tr.finworkercode
       )
       on
       tr.planrefcode = acpw.plancode
   )
   on
   achu.actrefcode = acpw.actrefcode
where
cast(fi.tabnumber as text) = cast(achu1.tabnumber as text)
and achu.actrefcode = achu1.actrefcode)) as numeric),10) as esv
from
enact2humen as achu1
where
achu1.actrefcode = ac_super.code
and achu1.humenkindrefcode = 2) as voditel_query) as voditel_zarp
from
enact as ac_super
where
ac_super.code = ac.code) as act_super
) as summa_akt into sumbyact 
from
enact as ac
where
ac.code = p_actcode ;
return   sumbyact; 
   
END;
$function$

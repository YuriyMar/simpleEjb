CREATE OR REPLACE FUNCTION net.getsumbyactservicesout(p_actcode double precision)
 RETURNS numeric
 LANGUAGE plpgsql
AS $function$ 
      declare sumbyact numeric      
      ;      
      BEGIN      
            select      
      (      
     select      
     round      
     (      
        cast((materials+transport+zarp) as decimal),2      
     )      
     + overall + costrecovery + countercost as summa_akt      
     from      
		     (      
		    select      
		    /*Відшкодування витрат сторонніх організацій*/ coalesce(( select sum(a2r.summa) from enact2costrecovery a2r where a2r.actrefcode = p_actcode ),0) as costrecovery ,      
		    /* Стоимость счетчика для усуг на сторону */ 
		    /* +++mar+++ 30.07.2018 если метод расчета старый или новый сучетом материалов или это присоеденен то материалы учитываем , иначе нет  */
		           coalesce((
				    select case when a.acttyperefcode = 7 
				                and coalesce((select count(engivecounter.code)  from engivecounter where engivecounter.serialnumber = s.buildnumber),0) = 0
				                and ( 
				                    ((select * from getcalckindrefcodebyactcode(ac_super.code   ) ) in (1,3) or coalesce((select * from getENconnectionKindByActCode(ac_super.code) ),'') <> '')
				                    or 
				                    ((select * from getcalckindrefcodebyactcode(ac_super.code   ) ) in (2) 
				                       /* и если замена счетчика и новый вид расчета */
				                       and ( select count( distinct pi.planrefcode) from enact2enplanwork a2p , enplanworkitem pi , tktechcard tkd ,  tkclassificationtype t  
											     where a2p.actrefcode = ac_super.code
											     and a2p.plancode = pi.planrefcode 
											     and pi.kartarefcode = tkd.code 
											     and tkd.classificationtypecode = t.code 
											     and t.replacecounterkindcode = 1 
										    ) > 0 
				                      )
		                            ) 
				           then coalesce(sum(s.costold), 0)
				           else 0 end as cost  
	 from sccounter s, enestimateitem es, enact2enplanwork ena2, enact a
				    where a.code = ena2.actrefcode 
				    and s.estimateitemrefcode = es.code 
				    and es.countfact <> 0 
				    and es.kindrefcode = 1 
				    and s.account <> '1534' 
				    and ena2.plancode = es.planrefcode 
				    and ena2.actrefcode = p_actcode 
				    group by a.acttyperefcode , s.buildnumber ), 0) 
				   as countercost,      
		     /*Загальновиробничі витрати */ 
		    (select     
		     round(cast(coalesce(sum(q.sum_production_costs), 0) as decimal), 2)     
		     from     
		     (select     
		        case when cls.replacecounterkindcode = 2 and so.isnopay = 1 then 0 else 
				        case when so.calctyperefcode = 1 then     
							        (   
										           select     
										           distinct coalesce(plcl1.productionexpenssprcnt, 0) / 100     
										           from enplanwork2classfctntp as plcl1     
										           inner join enplanwork as pw1 on plcl1.planrefcode = pw1.code     
										           where plcl1.classificationtyperfcd = cls.code     
										           and pw1.kindcode = 5     
										           and pw1.elementrefcode = a.elementcode  
									           
							        )     
							        *  
							        /* SUPP-74363 если метод расчета новый то загальновиробничи берем с ЗП без водителей и без проезда   */
			                case when (select * from getcalckindrefcodebyactcode(ac_super.code )) not in (2,3) 
			                             then		                               
						        ( coalesce(sum(pihu.paysworkbonus) ,0)     )
						        else 
						         /*+++mar+++30.07.2018 если присоединение то общепроизводственніе считаем на ЗП всех в акте  */
						         case when coalesce((select * from getENconnectionKindByActCode(acpw.actrefcode) ),'') = '' then   
									( case when pihu.humenkindrefcode <> 2 then 
									sum((coalesce(pihu.paysworkbonuswithotdlv,0) + coalesce(pihu.paysworksurchrgwthtdlv,0) + coalesce(pihu.paysworkpremiumwthtdlv,0) + coalesce(pihu.paysworkadditnlwthtdlv,0)))::numeric(15,8) 
									else 0 end 
									)
								 else
									( coalesce( sum(( coalesce(pihu.paysworkbonus ,0) + coalesce(pihu.paysworksurcharge,0 ) + coalesce(pihu.paysworkpremium,0) + coalesce(pihu.paysworkadditional,0)))::numeric(15,8) )   )			 
								 end 
							end 
		              when so.calctyperefcode = 2 then coalesce     
				        (     
				           cast(sum(pihu.generalexpenses) as numeric(15,2))     
				        )     
			          else 0 
			          end  
			    end as sum_production_costs 
		        from enact2enplanwork as acpw     
		        inner join enplanworkitem as pi on pi.planrefcode = acpw.plancode     
		        inner join enplanworkitem2humen as pihu on pi.code = pihu.plaitemrefcode     
		        inner join enact as a on a.code = acpw.actrefcode     
		        inner join enservicesobject as so on a.elementcode = so.elementcode     
		        inner join tktechcard as kart on pi.kartarefcode = kart.code     
		        inner join tkclassificationtype as cls on kart.classificationtypecode = cls.code     
		        where acpw.actrefcode = ac_super.code     
		        and pi.countgen <> 0   
		        group by  a.code, cls.code, so.calctyperefcode, pihu.chargepercent,  pihu.chargerefcode, pihu.bonus, pihu.tabnumber, pihu.fio, pihu.salary, pihu.timemonth, pihu.salaryhours, pihu.humenkindrefcode, acpw.actrefcode,
		        cls.replacecounterkindcode, so.isnopay     
--------------Загальновиробничі 
                )     
		     as q     
		     ) as overall,     
		  /*Запрос материалы */ 
		     
		    (    
		     select     
		      /* SUPP-74363 если метод расчета новый и єто не присоединение то материалы не учитываем   */
		       case when (select * from getcalckindrefcodebyactcode(ac_super.code ) )  not in (2)
		                 or coalesce((select * from getENconnectionKindByActCode(ac_super.code) ),'') <> '' 
		            then coalesce(sum(stoimost),0) else 0 end  into sumbyact
		     from     
		     (     
		        Select     
		        mat_name ,     
		        mu_name ,     
		        sum(countgen) as countgen ,     
		        cost ,     
		        sum(stoimost) as stoimost ,     
		        bal_sch ,     
		        nn ,     
		        kindrefcode     
		        From     
		        (     
		           Select     
		           Case When estimate.kindrefcode = 2 Then finmat.mat_name||('(ПММ)') Else finmat.mat_name end as mat_name ,     
		           finmat.mu_name ,     
		           finmat.quantity as countgen ,     
		           finmat.calc_price as cost ,     
		           coalesce(finmat.cost,0) as stoimost ,     
		           finmat.bal_sch ,     
		           finmat.nn ,     
		           estimate.kindrefcode     
		           From enestimateitem estimate , finmaterials finmat , enact2enplanwork ena2     
		           where ena2.actrefcode = ac_super.code     
		           and ena2.plancode = estimate.planrefcode     
		           and finmat.estimateitemrefcode = estimate.code     
		           and finmat.statusrefcode = 1     
		           and estimate.kindrefcode in (1,2)     
		           and finmat.cost<> 0     
		          
		     union all     
		     /*Пломбы*/     
		     Select distinct     
		                    ss.name as mat_name     
		             , 'ШТ' as mu_name     
		             , 1  as countgen     
		             , ss.cost  as cost     
		             , ss.cost as stoimost     
		             , ss.account as bal_sch     
		             , ss.buildnumber as nn     
		             , estimate.kindrefcode     
		       From     
		              enestimateitem estimate     
		            , scseal ss     
		            , enact2enplanwork ena2     
		            , enplanworkitem pwi     
		            , tktechcard tkd     
		            , tkclassificationtype tkcls     
		     where     
		     ena2.actrefcode = ac_super.code     
		       and ena2.plancode = estimate.planrefcode     
		       and ss.estimateitemrefcode = estimate.code     
		       and ss.statusrefcode in (1,3)     
		       and estimate.kindrefcode = 1     
		       and pwi.planrefcode = ena2.plancode     
		       and pwi.kartarefcode = tkd.code     
		       and pwi.code = estimate.planitemrefcode     
		       /*Если costworkcontractor не пустая, значит работа сторонняя - не включаем в расходный акт*/     
		       and tkcls.code = tkd.classificationtypecode     
		       and (tkcls.costworkscontractor is null     
		       or tkcls.costworkscontractor = 0)     
		        )     
		        sel1     
		        group by sel1.mat_name ,     
		        sel1.mu_name ,     
		        sel1.bal_sch ,     
		        sel1.nn,     
		        sel1.cost,     
		        kindrefcode     
		        order by nn , bal_sch , mat_name , mu_name , cost     
		     )     
		     as mat_query     
		     )  as materials,      
		    /*Транспорт*/             
		   (select 
		    /* SUPP-74363 если метод расчета новый то транспорт не учитываем   */
		       case when (select * from getcalckindrefcodebyactcode(ac_super.code ))  not in (2,3)
		            then
		   sum(payswork) else 0 end  
		   from ( select coalesce(sum(payswork),0) as payswork from (SELECT en2tr.code, en2tr.invnumber, en2tr.name as nameavto , en2tr.expense as countfact , en2tr.depreciationmonth, en2tr.depreciationhours, en2tr.timework as countworkfact , en2tr.payswork, en2tr.actrefcode, (select tr.code from tktransportreal tr where tr.invnumber = en2tr.invnumber) as transportcode FROM net.enact2transport en2tr, enact act
		   where en2tr.actrefcode = ac_super.code
		   and en2tr.actrefcode = act.code and act.dateact >= '01.08.2012' Order by en2tr.name) as transport_query union all (select coalesce(sum(act_pay.payswork) , 0) as payswork from ( SELECT DISTINCT tkcls.code as calculationcode ,
		   getPaysTransportByAktCalcul( ac_super.code::integer , cast(tkcls.code as Integer)) as payswork FROM enact2enplanwork a2p, enact act, enplanwork pw, enplanworkitem pwi, tktechcard tkd, tkclassificationtype tkcls, enplanworkitem2humen p2h
		   WHERE a2p.actrefcode = ac_super.code and a2p.plancode = pw.code and act.dateact < '01.08.2012' and a2p.actrefcode = act.code and pw.code = pwi.planrefcode and pwi.countgen <> 0 and pwi.kartarefcode = tkd.code and tkd.classificationtypecode = tkcls.code and pwi.code = p2h.plaitemrefcode and p2h.classificationtyperfcd = tkcls.code and (tkcls.costworkscontractor = 0 or tkcls.costworkscontractor is null) ) as act_pay) ) 
		   as foo ) 
		            as transport,      
		    /*Зарплата электротехнического персонала и водителей */      
		  ( select sum(total_zarplata) from ( 
			select     /* SUPP-74363 если метод расчета новый то берем ЗП без водителей и без проезда   */
			           case when (select * from getcalckindrefcodebyactcode(ac_super.code ) ) not in (2,3)
				                then (sum(payswork)::numeric(15,2)  + sum(round(cast((payswork)*chargepercent/100 as decimal),8)))::numeric(15,2) 
				                else 
					                /*+++mar+++30.07.2018 ЕСЛИ ПРИСОЕДИНЕНИЯ ИЛИ МЕТОД РАСЧЕТА = 3 то зарплата считаем на ЗП всех в акте иначе без доставки и водителей  */
									case when coalesce((select * from getENconnectionKindByActCode(ac_super.code ) ),'') <> ''
									       or (select * from getcalckindrefcodebyactcode(ac_super.code ) ) = 3 
									then (sum(paysworkWithAddit)::numeric(15,2) + sum(CHARGESUM)::numeric(15,2) )::numeric(15,2) 
									else
										 (sum(paysWorkBonusWithoutDelivery)::numeric(15,2) + sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) )::numeric(15,2)							 
									end 
				                end 
		                as total_zarplata 
		from    
			    (
						Select
						  p2h.tabnumber
						, p2h.fio
						, cast(p2h.salary as numeric) as salary
						, cast(p2h.timemonth as numeric) as timemonth
						, cast(p2h.salaryhours as numeric) as salaryhours
						, cast(p2h.timework as numeric) as timework
						, (coalesce(p2h.paysworkbonus,0)  ) as payswork
						, (coalesce(p2h.paysworkbonus,0) + coalesce(p2h.paysworksurcharge,0) + coalesce(p2h.paysworkpremium,0) + coalesce(p2h.paysworkadditional,0) ) as paysworkWithAddit
						, (coalesce(p2h.paysworkbonuswithotdlv,0) + coalesce(p2h.paysworksurchrgwthtdlv,0) + coalesce(p2h.paysworkpremiumwthtdlv,0) + coalesce(p2h.paysworkadditnlwthtdlv,0)) as paysWorkBonusWithoutDelivery
						, p2h.generalexpenses
						, p2h.chargepercent
						, p2h.chargerefcode
						, (select distinct achu1.balans||'; '||substring(achu1.podrcod,0,4)
							from enact2humen as achu1
							where achu1.actrefcode = a2p.actrefcode and achu1.tabnumber = p2h.tabnumber) as balanskod
						, p2h.bonus
						, coalesce((coalesce(p2h.percentsurcharge,0) + coalesce(p2h.percentpremium,0)  + coalesce(p2h.percentadditional,0) ),0) as bonusdditional
						, tkcls.replacecounterkindcode
					    , coalesce((p2h.paysworksurcharge + p2h.paysworkpremium + p2h.paysworkadditional),0)::numeric(15,8) as paysworkAdditional
					    , so.isnopay , a2p.actrefcode , p2h.humenkindrefcode
					    , p2h.timeDelivery
					    , p2h.CHARGESUM
					    , p2h.CHARGESUMWITHOUTDELIV
						from
						enact2enplanwork as a2p
						inner join enplanwork as pw on a2p.plancode = pw.code
						inner join enplanworkitem as pwi on pw.code = pwi.planrefcode
						inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode
						inner join tktechcard as tc on pwi.kartarefcode = tc.code
						inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code
						inner join enservicesobject as so on pw.elementrefcode = so.elementcode
						where  a2p.actrefcode = ac_super.code
					 	and pwi.countgen <> 0
						and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0)
					) as q1
					group by chargepercent
					, chargerefcode
					, actrefcode
					, tabnumber
					, fio
					, salary
					, timemonth
					, salaryhours
					, humenkindrefcode
					, replacecounterkindcode
					, isnopay
					, bonus
					, bonusdditional /*доп ЗП сумарно*/  
		    ) as totalzp1
		 ) as zarp      
		            from enact as ac_super      
		            where ac_super.code = ac.code      
		        )     
         as act_super      
      )      
      as summa_akt      
      from enact as ac      
      where ac.code = p_actcode    
     
    
      ;      
      return sumbyact      
      ;      
      END      
      ; 
 $function$

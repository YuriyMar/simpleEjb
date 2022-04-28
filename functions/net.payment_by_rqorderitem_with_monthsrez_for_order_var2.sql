CREATE OR REPLACE FUNCTION net.payment_by_rqorderitem_with_monthsrez_for_order_var2 (
  p_startdate varchar,
  p_budgcode varchar,
  p_pkindrefcode integer,
  p_strgroupmaterials integer,
  p_yeargen integer,
  p_rqordertypecode integer
)
RETURNS varchar AS
$body$
DECLARE
  l record;
  fact_pay record;
  orderitem_for_fact_pay record;
  countsel integer;
  
  v_summa1 numeric(15,2);
  v_date1 date;
  v_summa2 numeric(15,2);
  v_date2 date;
  
  v_count_inserting_row integer;
  
  v_rqorderitemcode integer;
  v_typepayrefcode numeric;
  v_paymentvalue Integer;
  v_planneddatedelivery date;
  v_planneddatepays date;
  v_order_period date;
  v_sum_by_rqorderitem numeric;
  v_pay_by_rqorderitem_in_prev_period numeric;
BEGIN

select count(*) into countsel from information_schema.tables where table_name = 'payment_by_rqorderitem_for_order';

 if countsel = 0 then
   create TEMPORARY table payment_by_rqorderitem_for_order(rqorderitemcode numeric NOT NULL,
    period DATE,
    summa numeric,
    typepay INTEGER
   ) ;
  CREATE INDEX payment_by_rqorderitem_for_order_idx ON payment_by_rqorderitem_for_order
  USING btree (rqorderitemcode);

  CREATE INDEX payment_by_rqorderitem_for_order_idx1 ON payment_by_rqorderitem_for_order
  USING btree (period);

  CREATE INDEX payment_by_rqorderitem_for_order_idx2 ON payment_by_rqorderitem_for_order
  USING btree (typepay);


 ELSE truncate table payment_by_rqorderitem_for_order;

 End if;

v_count_inserting_row := 0;
for l in
 select ddscode,
project::text as project,
numberdoc,
orderdate,
mname,
meas,
contract,
orgname,
countfact,
countgen,
p,
pricewithnds,
summa,
estimateitemcode,
estimateitemcode_for_bill,
oicode,
budgetrefname,
deliverytime,
statussymbol,
planneddatepays,
pr_period,
paymenttypename,
paymentvalue,
case when first_day(planneddatepays) <= first_day(to_date(p_startdate,'dd.mm.yyyy') )
             then  plansumpay else 0 end as plansumpay,
oplatata_date,
postavka_date
from (
select ddscode ,  'тмц' as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname ,
/* расчет кол-во плановое
 - если кол-во план больше кол-ва факт тогда разницу между план и фактом выводим
 - если  разница нормативное кол-во минус оплачено в пред периоде равно или меньше 0
     но сумма по счету больше чем оплачено  (т.е не все оплатили ) тогда нормативное кол -во
 */
 (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact ,
 countgen , p::numeric(15,3) ,
 pricewithnds::numeric(15,3) as pricewithnds,
/* расчет сумма плановая
 - если кол-во план больше кол-ва факт тогда разницу между план и фактом умноженное на цену выводим
 - если  разница нормативное кол-во минус оплачено в пред периоде равно или меньше 0
     но сумма по счету больше чем оплачено  (т.е не все оплатили ) тогда нормативное кол -во
 */
   (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa ,
 oplatata_date , postavka_date  ,
 estimateitemcode ,
 case when estimateitemcode_for_bill = '0' then estimateitemcode else estimateitemcode_for_bill end  as estimateitemcode_for_bill,
 oicode ,
 budgetrefname ,
 deliverytime  ,
 statussymbol ,
            --==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            oplatata_date
                            else
                              case when first_day(oplatata_date) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                                 oplatata_date
                              else
                                 first_day(to_date(p_startdate,'dd.mm.yyyy') )
                              end
                            end

                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (case when  first_day(postavka_date+7) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                              	postavka_date+7
                                else
                                first_day(to_date(p_startdate,'dd.mm.yyyy') )
                           end)
                          else (
                          case when  first_day(oplatata_date) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (  case when coalesce((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end  * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату
                                     case when first_day(oplatata_date) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                                          oplatata_date
                                          else
                                          first_day(to_date(p_startdate,'dd.mm.yyyy') )
                                     end
                                  else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней
                                     postavka_date+7
                             end
                           ) else --план дата оплаты больше чем дата формирования ТД
                           (
                             oplatata_date
                           )
                          end
                          )

                     end
                   )

             end  as planneddatepays ,
            -----============================

 -1 as pr_period ,
 --==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode)
                            else
                             'Кінцевий розрахунок'
                            end
                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           ('Кінцевий розрахунок')
                          else (
                          (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode)
                          )

                     end
                   )

             end  as paymenttypename ,
            -----============================

  --==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode)
                            else
                             100
                            end
                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (100)
                          else (
                          (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode)
                          )

                     end
                   )

             end  as paymentvalue ,
            -----============================
  /* SUPP-9591 для заявок предыдущего периода если есть счет то сумму с ндс по заявке тянем уже относительно счета а не заявки иначе сумма берется со строки заявки */
  --==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            0
                            else
                               ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end from rqorderitem oii where oii.code = oicode )
                                 -
                                 p_sum)::numeric(15,2)
                            end

                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                          ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end from rqorderitem oii where oii.code = oicode )
                                 -
                                 p_sum)::numeric(15,2)
                          else (
                          case when  first_day(oplatata_date) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (  case when coalesce((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end  * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату
                                     ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end *oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )
                                      -
                                      p_sum)::numeric(15,2)
                                  else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней
                                     0
                             end
                           ) else --план дата оплаты больше чем дата формирования ТД
                           (
                             0
                           )
                          end
                          )

                     end
                   )

             end  as plansumpay
            -----============================

from (
/* все заявки */
select ddscode ,  'тмц' as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname ,
 sum(countgen) as countgen , sum(p)::numeric(15,3) as p  ,  pricewithnds::numeric(15,3) as pricewithnds,
 oplatata_date , postavka_date ,
   group_concat( estimateitemcode::varchar ,',') as estimateitemcode ,
   group_concat( distinct estimateitemcode_for_bill::varchar ,',') as estimateitemcode_for_bill ,
 oicode ,
 budgetrefname ,
 deliverytime ,
 statussymbol ,
 planneddatepays ,
 sum(payincurperiod) as payincurperiod
      /*сумма оплаты в пред периоде */
-- (select * from get_pay_02prior_period2(oicode , group_concat( estimateitemcode::varchar ,',') , /*startDate*/ '01.12.2013')) as paysumoldperiod ,
--
, coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/
    / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = oicode
         and oi.billitemrefcode = bi.code ),0)
       * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = oicode
         and oi.billitemrefcode = bi.code ),0)
         From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc
         			where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and rqpaydoc.dategen <= to_date(p_startdate,'dd.mm.yyyy')
                      and rqbillitem2orderitem.orderitemrefcode = oicode
                      and rqbillitem2orderitem.billitemrefcode = rqbillitem.code
         ),0)::numeric(15,2) as paysumoldperiod
 -- sum(billsum) as billsum
-- , (select * from get_bill_sum_by_estimate(group_concat( estimateitemcode::text ,',') )) as billsum
--------
, coalesce((select sum((bi.sumgen / bi.countfact) * bi2oi.countfact) ::numeric(15, 2)
 from rqbillitem bi,
      rqbillitem2orderitem bi2oi
 where bi.code = bi2oi.billitemrefcode and
       bi2oi.orderitemrefcode = oicode ),0) as billsum
--------
 , typepayrefcode
 , coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/
    / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = oicode
         and oi.billitemrefcode = bi.code ),0)
       * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = oicode
         and oi.billitemrefcode = bi.code ),0)
         From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc
         			where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and rqpaydoc.dategen <  first_day(to_date(p_startdate,'dd.mm.yyyy') ) 
                      and rqbillitem2orderitem.orderitemrefcode = oicode
                      and rqbillitem2orderitem.billitemrefcode = rqbillitem.code


         ),0) as p_sum

  , ( select coalesce(sum(summma_s_nds_in_bill),0) from (
select b.countfact *  (bi.sumgen/bi.countfact) as summma_s_nds_in_bill
from rqbillitem2orderitem b , rqbillitem bi
where b.orderitemrefcode = oicode
and b.billitemrefcode = bi.code ) as aa ) as SUMMMA_S_NDS_IN_BILL
 , paymentvalue
 From (
Select r.numberdoc ,
       to_char(r.orderperiod,'MM.yyyy') as orderdate ,
       ri.materialnamegen ,
       ri2e.countgen ,
       ri2e.estimateitemcode ,
       /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */
       
        coalesce(( Select sum(rqbillitem2enestimattm.countgen)   From  rqbillitem2enestimattm ,
                                                                      rqbillitem  
         			where rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode
                      and rqbillitem2enestimattm.billitemcode = rqbillitem.code                     
                      and rqbillitem.code in ( 
                        select rqpaydocitem2billitem.billitemcode from  rqpaydocitem2billitem , rqpaydocitem , rqpaydoc 
                           where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                           and rqpaydoc.code = rqpaydocitem.paydocrefcode
                           and rqpaydoc.dategen < first_day(to_date(p_startdate,'dd.mm.yyyy') )
                                            ) 

         ),0) as p
         /*естимейты из заявок пред периода отнимем естимейты по которым были оплаты
          останутся только естимейты по которым небыло оплаты */
       , coalesce((select ri2e.estimateitemcode
        EXCEPT
        Select rqbillitem2enestimattm.estimateitemcode     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc
         			where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode
                      and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode
                      and rqbillitem2enestimattm.billitemcode = rqbillitem.code
                      and rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and rqpaydoc.dategen < first_day(to_date(p_startdate,'dd.mm.yyyy') )
        ),0) as  estimateitemcode_for_bill
         ,
         (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode

          ) as mname
         ,
         (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode and mm.measurementcode = ms.code

          ) as meas
          ,
          rdds.txtcode as ddscode,
          ri.contractnumber || 'от ' || ri.contractdate as contract ,
          (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname ,
          ri.pricewithnds ,
          ri.plannedDatePays as oplatata_date,
          ri.plannedDateDelivery as postavka_date ,
          ri.code as oicode ,
          case when dbb.name is null then db.name else dbb.name end as budgetrefname ,
           COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime ,
          case when r.statusrefcode = 2 then 'З'
               when r.statusrefcode = 3 then 'П' else '' end as statussymbol
          , ri.planneddatepays
          ,  /*признак = или больше 1 если оплаты были в периоде формирования отчета т.е с первого числа месяца по указаное число при формировании отчета */
        coalesce(( Select  count(rqpaydoc.code)    From rqpaydocitem2billitem , rqbillitem2enestimattm ,
                                                                 rqbillitem  , rqpaydocitem , rqpaydoc
         			where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode
                      and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode
                      and rqbillitem2enestimattm.billitemcode = rqbillitem.code
                      and rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and rqpaydoc.dategen between first_day(to_date(p_startdate,'dd.mm.yyyy') ) and to_date(p_startdate,'dd.mm.yyyy')


         ),0) as payincurperiod

, ri.typepayrefcode
, ri.paymentvalue

  From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code)
								   left join rqddscodes rdds on (ri.ddscodescode = rdds.code)
                                   left join rqorg on (ri.orgcode = rqorg.code )
      , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i , tkmaterials sm
Where r.code = ri.orderrefcode
  and ri.code = ri2e.orderitemcode
  and ri.statusrefcode <> 2 /*которые не утратили необходимость*/
  and ri2e.statusrefcode = 1 /*действительный*/
  and i.code = ri2e.estimateitemcode
  and i.planrefcode = p.code
  and p.budgetrefcode = db.code
  and p.kindcode = 2
  and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/
        /*или план завершений но была оплата в текущ периоде */
       or ( p.statuscode = 8
           and
           (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm ,
                                                                 rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem
         			where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode
                      and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode
                      and rqbillitem2enestimattm.billitemcode = rqbillitem.code
                      and rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and enplanwork.code = enestimateitem.planrefcode
                      and enestimateitem.code = ri2e.estimateitemcode
                      and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode
                      and enplanwork.statuscode = 8
                     -- 18.02.2014 строка ниже оплаты нада учитывать за весь месяц что  бы  показывать строку в ТД
                     -- and rqpaydoc.dategen between first_day(to_date({startDate},'dd.mm.yyyy') ) and to_date({startDate},'dd.mm.yyyy')
                      and rqpaydoc.dategen between first_day(to_date(p_startdate,'dd.mm.yyyy') ) and last_day( to_date(p_startdate,'dd.mm.yyyy') )
                      limit 1
            ) = 8
           )
              /*add yura 17.02.2014 или есть строка счета по строке заявки которая неоплачена еще */
       or (
          (Select  coalesce(bi.staterefcode,0) from rqbillitem2orderitem b2o, rqbillitem bi
             where b2o.orderitemrefcode = ri.code
             and b2o.billitemrefcode = bi.code
             order by bi.staterefcode
             limit 1
            ) = 0
           )
       )
  and r.orderperiod < first_day(to_date(p_startdate,'dd.mm.yyyy') )
  and r.orderperiod >= '01.01.2013' /* SUPP-9591 не берем старые заявки так как в связке rqbillitem2orderitem нету количества*/
  /* бюджетодержатель */
--  and db.code = ANY ( p_budgcode )
  And db.code::text = any(string_to_array(p_budgcode, ','))
  /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */
  and  (r.kindrefcode = p_pkindrefcode or ( p_pkindrefcode = 0 and r.kindrefcode in (4,5) )   )
  and i.materialrefcode = sm.code
  
  and r.statusrefcode = 2
 /* группа материалов */
  and ( sm.rootgrouprefcode = p_strgroupmaterials or  p_strgroupmaterials = 0  )
 /*budg or ip */
  and ( r.rqordertypecode = p_rqordertypecode or p_rqordertypecode = 0 ) 


/* отсекаем планы 2010 2011 */
and p.code not in (select code  from aaa_plan_code )
 
--/*test*/ and ri.code = 500015396

 ) s1

   Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname
          , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname , deliverytime , statussymbol , planneddatepays
          , typepayrefcode , paymentvalue
 ) s2
   Where  (  /*показать строки заявки которые не оплачены в предыдущ периоде */
            -- если план кол-во больше оплоченых и сумма счета больше оплаты допустим на 2 грн
            ((s2.countgen > s2.p) and ((billsum - paysumoldperiod) > 2 )  )
            -- если кол-во план <=0 и разница оплаты и счета больше 2 )
            or (
                 ( (s2.countgen - s2.p ) <= 0 )
                 and
                  ( (billsum - paysumoldperiod) > 2  )
               )
            -- или были оплаты в текущ периоде
            or
             payincurperiod > 0
            -- если небыло счета
            or
             COALESCE(billsum,0) = 0
            -- если есть счет  но нет оплат ни в текущ периоде ни в прошлых
            or
             ( COALESCE(billsum,0) > 0 and  COALESCE(paysumoldperiod,0) = 0 and s2.p = 0 )
            or /* add yura 18.02.2014 если неоплаченый счет (не попадали копейки недоплаченые по счету )*/
             ( COALESCE(billsum,0) >  COALESCE(paysumoldperiod,0) )
            -- временно для тд октября - не попадал  бензин 80 и дт
            or oicode in (500139368 , 500139371)
          )
   /* уберем строки с нулевыми плановыми суммами*/
   and case when s2.countgen > s2.p then  (countgen - p) * pricewithnds ::numeric(15,3)
      when ( ( (s2.countgen - s2.p ) <= 0 )  and (billsum > paysumoldperiod ) )  then billsum-paysumoldperiod::numeric(15,3)
      /*NET-4066 Если была оплата в текущем периоде, то необходимо показывать эту строку завки*/
      when payincurperiod > 0 then payincurperiod
      end
      is not null


 ) as sel




UNION ALL
/*КЛЕИМ ЗАЯВКИ ТЕКУЩЕГО ПЕРИОДА */
/* все заявки */

select ddscode ,
project,
numberdoc  ,
orderdate ,
mname ,
meas ,
contract ,
orgname ,
countfact ,
countgen ,
p ,
pricewithnds ,
summa  ,
estimateitemcode ,
estimateitemcode_for_bill ,
oicode ,
budgetrefname ,
deliverytime ,
statussymbol  ,
--==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            oplatata_date
                            else
                              case when first_day(oplatata_date) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                                 oplatata_date
                              else
                                 first_day(to_date(p_startdate,'dd.mm.yyyy') )
                              end
                            end

                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (case when  first_day(postavka_date+7) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                              	postavka_date+7
                                else
                                first_day(to_date(p_startdate,'dd.mm.yyyy') )
                           end)
                          else (
                          case when  first_day(oplatata_date) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату
                                     case when first_day(oplatata_date) = first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                                          oplatata_date
                                          else
                                          first_day(to_date(p_startdate,'dd.mm.yyyy') )
                                     end
                                  else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней
                                     postavka_date+7
                             end
                           ) else --план дата оплаты больше чем дата формирования ТД
                           (
                             oplatata_date
                           )
                          end
                          )

                     end
                   )

             end  as planneddatepays ,
-----============================
 1 as pr_period ,
--==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode)
                            else
                             'Кінцевий розрахунок'
                            end
                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           ('Кінцевий розрахунок')
                          else (
                          (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode)
                          )

                     end
                   )

             end  as paymenttypename ,
            -----============================

  --==========================
             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode)
                            else
                             100
                            end
                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (100)
                          else (
                          (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode)
                          )

                     end
                   )

             end  as paymentvalue ,

             case when (    typepayrefcode = 2
                        or (typepayrefcode = 1 and paymentvalue = 100 )
                        or  typepayrefcode is null ) then (
                        	case when first_day(oplatata_date) > first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                            0
                            else
                               ((select oii.sumgen from rqorderitem oii where oii.code = oicode )
                                 -
                                 p_sum)::numeric(15,2)
                            end

                        )
                   when (typepayrefcode = 1 and paymentvalue <> 100 ) then
                   ( case when first_day(postavka_date+7) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                          ((select oii.sumgen from rqorderitem oii where oii.code = oicode )
                                 -
                                 p_sum)::numeric(15,2)
                          else (
                          case when  first_day(oplatata_date) <= first_day(to_date(p_startdate,'dd.mm.yyyy') ) then
                           (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату
                                     ((select oii.sumgen  *oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )
                                      -
                                      p_sum)::numeric(15,2)
                                  else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней
                                     0
                             end
                           ) else --план дата оплаты больше чем дата формирования ТД
                           (
                             0
                           )
                          end
                          )

                     end
                   )

             end  as plansumpay ,
            -----============================
oplatata_date,
postavka_date
From
(
    select ddscode ,  'тмц'::text as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname ,
       (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact ,
      0 as countgen , 0 as p ,
     pricewithnds ,
     (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa  ,
     oplatata_date , postavka_date ,
      group_concat( estimateitemcode::varchar ,',') as estimateitemcode ,
      group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill ,
     oicode ,
     budgetrefname ,
     deliverytime ,
     statussymbol  ,
     1 as pr_period

        , coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/
        / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
             where oi.orderitemrefcode = oicode
             and oi.billitemrefcode = bi.code ),0)
           * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
             where oi.orderitemrefcode = oicode
             and oi.billitemrefcode = bi.code ),0)
             From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc
                        where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                          and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                          and rqpaydoc.code = rqpaydocitem.paydocrefcode
                          and rqpaydoc.dategen < first_day(to_date(p_startdate,'dd.mm.yyyy') ) 
                          and rqbillitem2orderitem.orderitemrefcode = oicode
                          and rqbillitem2orderitem.billitemrefcode = rqbillitem.code


             ),0) as p_sum   ,
          typepayrefcode ,
          paymentvalue
     from (
    Select r.numberdoc ,
           to_char(r.orderperiod,'MM.yyyy') as orderdate ,
           ri.materialnamegen ,
           ri2e.countgen ,
           ri2e.estimateitemcode ,
             (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode

              ) as mname
             ,
             (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode and mm.measurementcode = ms.code

              ) as meas
              ,
              rdds.txtcode as ddscode,
              ri.contractnumber || 'от ' || ri.contractdate as contract ,
              (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname ,
              ri.pricewithnds ,
              ri.plannedDatePays as oplatata_date,
              ri.plannedDateDelivery as postavka_date ,
              ri.code as oicode ,
              case when dbb.name is null then db.name else dbb.name end as budgetrefname ,
              COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime ,
              case when r.statusrefcode = 2 then 'З'
                   when r.statusrefcode = 3 then 'П' else '' end as statussymbol ,
              ri.planneddatepays
              , ri.typepayrefcode
              , ri.paymentvalue
      From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code)
                                       left join rqddscodes rdds on (ri.ddscodescode = rdds.code)
                                       left join rqorg on (ri.orgcode = rqorg.code )
          , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm
    Where r.code = ri.orderrefcode
      and ri.code = ri2e.orderitemcode
      and ri.statusrefcode <> 2 /*которые не утратили необходимость*/
      and ri2e.statusrefcode = 1 /*действительный*/
      and i.code = ri2e.estimateitemcode
      and i.planrefcode = p.code
      and p.kindcode = 2
      and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/
            /*или план завершений но была оплата в текущ периоде */
           or ( p.statuscode = 8
               and
               (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm ,
                                                                     rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem
                        where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode
                          and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode
                          and rqbillitem2enestimattm.billitemcode = rqbillitem.code
                          and rqpaydocitem2billitem.billitemcode = rqbillitem.code
                          and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                          and rqpaydoc.code = rqpaydocitem.paydocrefcode
                          and enplanwork.code = enestimateitem.planrefcode
                          and enestimateitem.code = ri2e.estimateitemcode
                          and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode
                          and enplanwork.statuscode = 8
                          and rqpaydoc.dategen between first_day(to_date(p_startdate,'dd.mm.yyyy') ) and to_date(p_startdate,'dd.mm.yyyy')
                limit 1
                 ) = 8 )
           )
      and p.budgetrefcode = db.code
      and ( r.orderperiod between first_day( to_date(p_startdate,'dd.mm.yyyy') )
                              and last_day( to_date(p_startdate,'dd.mm.yyyy') )    and 1  = 1  )
     
      /* бюджетодержатель */
      -- and db.code = ANY ( p_budgcode )
      And db.code::text = any(string_to_array(p_budgcode, ','))
      /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */
      and  (r.kindrefcode = p_pkindrefcode or ( p_pkindrefcode = 0 and r.kindrefcode in (4,5) )   )
      and i.materialrefcode = sm.code
      
      /*статус заЯвки*/
      and  r.statusrefcode = 2
      /* группа материалов */
      and ( sm.rootgrouprefcode = p_strgroupmaterials or  p_strgroupmaterials = 0  )
       /*budg or ip */
      and ( r.rqordertypecode = p_rqordertypecode or p_rqordertypecode = 0 )

      /* отсекаем планы 2010 2011 */
      and p.code not in (select code  from aaa_plan_code )

 --/*test*/ and ri.code = 500015396

      ) s1

    Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname
    , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime  ,
    statussymbol , planneddatepays , typepayrefcode  , paymentvalue
) s2

loop 
v_pay_by_rqorderitem_in_prev_period := 0;   
v_rqorderitemcode = l.oicode;
 select oi.typepayrefcode into v_typepayrefcode from rqorderitem oi where oi.code = v_rqorderitemcode; 
 select oi.paymentvalue into v_paymentvalue from rqorderitem oi where oi.code = v_rqorderitemcode; 
 select oi.planneddatepays into v_planneddatepays from rqorderitem oi where oi.code = v_rqorderitemcode; 
 select oi.planneddatedelivery into v_planneddatedelivery from rqorderitem oi where oi.code = v_rqorderitemcode;
 select first_day(o.orderperiod) into v_order_period from rqorderitem oi , rqorder o where o.code = oi.orderrefcode and oi.code = v_rqorderitemcode;
 
 if v_order_period < to_date(p_startdate,'dd.mm.yyyy') then 
   --сумма со счета c ндс 
        select coalesce(sum(summma_s_nds_in_bill),0) into v_sum_by_rqorderitem from (
        select b.countfact *  (bi.sumgen/bi.countfact) as summma_s_nds_in_bill
        from rqbillitem2orderitem b , rqbillitem bi
        where b.orderitemrefcode = v_rqorderitemcode
        and b.billitemrefcode = bi.code ) as qqq;
        
        if coalesce(v_sum_by_rqorderitem,0) = 0  then 
          select oi.sumgen into v_sum_by_rqorderitem from rqorderitem oi where oi.code = v_rqorderitemcode;      
        end if;
    else 
    select oi.sumgen into v_sum_by_rqorderitem from rqorderitem oi where oi.code = v_rqorderitemcode;      
 end if;
 
  
 
 select sum(qqqq) from(
 Select rqpaydocitem.summagen   --пропорционально вычисляем оплаты строки заявки
    / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = v_rqorderitemcode
         and oi.billitemrefcode = bi.code ),0)
       * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
         where oi.orderitemrefcode = v_rqorderitemcode
         and oi.billitemrefcode = bi.code ),0) as qqqq 
          INTO  v_pay_by_rqorderitem_in_prev_period
         From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc , rqbill b
         			where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                      and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                      and rqpaydoc.code = rqpaydocitem.paydocrefcode
                      and rqpaydoc.dategen <  first_day(to_date(p_startdate,'dd.mm.yyyy') ) 
                      and rqbillitem2orderitem.orderitemrefcode = v_rqorderitemcode
                      and rqbillitem2orderitem.billitemrefcode = rqbillitem.code
                      and b.code = rqbillitem.billrefcode
  
) as www;


 
 select summa1 , date1 , summa2 , date2 from get_payment_by_rqorderitem(
      v_typepayrefcode::integer ,
      v_paymentvalue::integer ,
      v_planneddatepays ,
      v_planneddatedelivery,
      to_date(p_startdate,'dd.mm.yyyy') ,
      coalesce(v_sum_by_rqorderitem ,0),
      coalesce(v_pay_by_rqorderitem_in_prev_period,0)
      ) into v_summa1 , v_date1 , v_summa2 , v_date2;
      
      

-- заполним данными по плановым оплатам вр табл
  if ((v_summa1 > 0) and (to_char(v_date1,'yyyy')::numeric = p_yeargen::numeric)) then -- первая оплата в текущ периоде
   insert into payment_by_rqorderitem_for_order values (v_rqorderitemcode,v_date1,v_summa1,1);
   v_count_inserting_row := v_count_inserting_row+1;
  end if;
  if ((v_summa2 > 0) and (to_char(v_date2,'yyyy')::numeric = p_yeargen::numeric)) then -- вторая / единств оплата в текущ периоде
   insert into payment_by_rqorderitem_for_order values (v_rqorderitemcode,v_date2,v_summa2,1);
   v_count_inserting_row := v_count_inserting_row+1;
  end if;


end loop;

--- выберем фактические оплаты по строке заявки 
for orderitem_for_fact_pay in 
  select distinct  oi.code  from rqorderitem oi , rqorder o , tkmaterials sm
  where oi.orderrefcode = o.code 
  and oi.materialcode = sm.code 
  and o.statusrefcode = 2
  and o.orderperiod >= '01.01.2013'
  and oi.statusrefcode <> 2 /*которые не утратили необходимость*/
--  and oi.budgetrefcode = ANY ( p_budgcode ) 
  And oi.budgetrefcode::text = any(string_to_array(p_budgcode, ','))
  and (o.kindrefcode = p_pkindrefcode  or ( p_pkindrefcode = 0 and o.kindrefcode in (4,5) )   ) 
  and ( sm.rootgrouprefcode = p_strgroupmaterials or  p_strgroupmaterials = 0  )
   /*budg or ip */
  and ( o.rqordertypecode = p_rqordertypecode or p_rqordertypecode = 0 )
  and exists ( select bi2oi.orderitemrefcode from rqbillitem2orderitem bi2oi , rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc p
               where bi2oi.orderitemrefcode = oi.code
               and bi2oi.billitemrefcode = pi2bi.billitemcode
               and pi2bi.paydocitemcode = pi.code 
               and pi.paydocrefcode = p.code
               and to_char(p.dategen,'yyyy')::numeric = p_yeargen
               and first_day(p.dategen) < first_day(to_date(p_startdate,'dd.mm.yyyy') )  
             )
 loop 
      -- фактические оплаты в предыдущем периоде выберем по строке заявки
       for fact_pay in
        Select rqpaydocitem.summagen  /*пропорционально вычисляем оплаты строки заявки*/
          / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
               where oi.orderitemrefcode = orderitem_for_fact_pay.code 
               and oi.billitemrefcode = bi.code ),0)
             * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi
               where oi.orderitemrefcode = orderitem_for_fact_pay.code 
               and oi.billitemrefcode = bi.code ),0) as summa_pay 
           , first_day(rqpaydoc.dategen) as dategen
               From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc , rqbill b
                          where rqpaydocitem2billitem.billitemcode = rqbillitem.code
                            and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode
                            and rqpaydoc.code = rqpaydocitem.paydocrefcode
                            and rqpaydoc.dategen <  first_day(to_date(p_startdate,'dd.mm.yyyy') ) 
                            and rqbillitem2orderitem.orderitemrefcode = orderitem_for_fact_pay.code 
                            and rqbillitem2orderitem.billitemrefcode = rqbillitem.code
                            and b.code = rqbillitem.billrefcode
          loop 
          
           
          -- вставим в темп таблицу фактические оплаты  typepay = 2 если год оплаты текущий 
          
           if ((fact_pay.summa_pay > 0) and (to_char(fact_pay.dategen,'yyyy')::numeric = p_yeargen::numeric)) then 
           insert into payment_by_rqorderitem_for_order values (v_rqorderitemcode,fact_pay.dategen,fact_pay.summa_pay,2);
           v_count_inserting_row := v_count_inserting_row+1;
           end if;
          
          
          end loop; 
 end loop;

return v_count_inserting_row::varchar;

END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;
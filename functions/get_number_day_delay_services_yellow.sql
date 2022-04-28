CREATE OR REPLACE FUNCTION net.get_number_day_delay_services_yellow (
  servicescode double precision
)
RETURNS integer AS
$body$
DECLARE
  count_day integer;
BEGIN
  count_day := 0;
select 
case when so.contractstatusrefcode = 10 
     or so.statusrefcode in (2,4) /* SUPP-33046 если провед в фин кол то не просроч*/ 
     or so.contractstatusrefcode = 11 /*SUPP-34177 - проведенным считаются договора также со енерджинетовским статусом "закритий"*/  
     then 0 
 else 
    case when
   /*is_yellow*/
     coalesce(limit_number_day + so.executeworkdate ,current_date) < current_date
        then
          case when coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p
                             where enact.elementcode = so.elementcode
                               and enact.elementcode = p.elementrefcode
                               and p.kindcode = 3
                               and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null
                               and enact.code = a2p.actrefcode
                          ),0) > 0
          then 0 else -- кол-во дней просрочки от намеченой даты выполнения для
                    current_date - (limit_number_day + so.executeworkdate)
         end
        else 0 end
         end /*условие на отмененные*/ 
        as is_yellow
    from enservicesobject so , 
     (
       select so.code as socode , so.contractnumberservices 
        , (select coalesce(p2c.isjobsbytime,0)  
             from enplanwork pw , enplanwork2classfctntp p2c
            where pw.elementrefcode = so.elementcode 
            and pw.kindcode = 5 
            and pw.code = p2c.planrefcode
            order by coalesce(p2c.isjobsbytime,0) desc 
            limit 1  ) as isjobsbytime
            , (select min(dategen) from enpayment2so
                        where enpayment2so.servicesobjectrefcode = so.code) as datepay 
           
          , coalesce(
                  case when coalesce(( select count(q.code) from tkclassificationtype q , enplanwork2classfctntp p2c , enplanwork p
                          where q.replacecounterkindcode =2 
                          and q.code = p2c.classificationtyperfcd
                          and p2c.planrefcode = p.code 
                          and p.kindcode = 5
                          and p.elementrefcode = so.elementcode 
                       ),0) > 0 then 14 + (select min(dategen) from enpayment2so
                            where enpayment2so.servicesobjectrefcode = so.code)
                  else 
                     (case coalesce(so.regionaltype,0) when 2
                     then 9 + (select min(dategen) from enpayment2so
                            where enpayment2so.servicesobjectrefcode = so.code)
                     else 5 + (select min(dategen) from enpayment2so
                            where enpayment2so.servicesobjectrefcode = so.code)
                     end)
                  end    
            ,so.executeworkdate) as limit_date_by_pay
        
                , coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p
                             where enact.elementcode = (select srv.elementcode  from enservicesobject srv where srv.code = so.code limit 1 )
                               and enact.elementcode = p.elementrefcode
                               and p.kindcode = 3
                               and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null
                               and enact.code = a2p.actrefcode
                          ),0) as act_vip_work /*акт вып работ по договору */             
            ,  ( case when coalesce(( select count(q.code) from tkclassificationtype q , enplanwork2classfctntp p2c , enplanwork p
                          where q.replacecounterkindcode =2 
                          and q.code = p2c.classificationtyperfcd
                          and p2c.planrefcode = p.code 
                          and p.kindcode = 5
                          and p.elementrefcode = so.elementcode 
                       ),0) > 0 then 14 
                  else 
                   case when coalesce(so.regionaltype,0) = 2 then 9 else 5 end  
             end           

            ) as limit_number_day  
            
         from enservicesobject so

     ) as table_isjobsbytime 
  Where  so.code = table_isjobsbytime.socode 
  and so.contractdateservices is not null
  and so.code = servicescode INTO count_day;
return count_day;
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;
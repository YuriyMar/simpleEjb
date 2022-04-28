CREATE OR REPLACE FUNCTION net.f_rep_state_lite_summary_byplfact_sub (
  pstartdate varchar,
  penddate varchar,
  pceh numeric,
  pelementtypecode numeric,
  pbudgcode double precision [],
  prencode numeric,
  pplancode numeric,
  pelementcode numeric,
  pformplancode numeric,
  pkomplekt numeric,
  pstrgroupmaterials double precision [],
  pgroup numeric,
  pstrestimateitems double precision [],
  ordercode double precision
)
RETURNS SETOF net.enrepstate_summary AS
$body$
DECLARE
  report_row net.enrepstate_summary%ROWTYPE;
  rr record;
  es numeric;
  states_row net.enstatematerial_tmp%ROWTYPE;
  p_kindcode numeric;
  p_estimate numeric;
  
  v_count_row_states numeric ;
 nn numeric;  
  
  onlyestimate CURSOR IS select p_estimate;
  
  states CURSOR IS 
      select 
        estimateitemcode  ,  countfact  , nomenclaturename , nomenclatureunitname  , partycode ,  
        kindcode  , statusname ,   modify_time , 
        ' '||moloutcode as moloutcode , moloutname ,  prizn 
 , 
         
  
         ' '  as objfactname
/*выбор фактического состояния материала */ 
     , 
      wwww.factstat  as factstatt
,
      
      ( select min(em.monthgen) || ' ' || min(em.yeargen) from enplanworkmovehistory em where em.planrefcode in (
         select ep.code from enestimateitem e , enplanwork ep   
           where e.planrefcode = ep.code 
             and e.code = estimateitemcode                 )
       ) as movedateplan                  
     from ( 

select  estimateitemcode  ,  sum(countfact) as countfact  , nomenclaturename , nomenclatureunitname  , partycode ,  
        kindcode  , statusname ,   max(modify_time) as modify_time , 
        moloutcode , moloutname 
      ,  f_analyse_motion(estimateitemcode  , sum(countfact) , 
                          nomenclaturename , nomenclatureunitname ,                          
                          partycode , kindcode , max(modify_time)  , p_kindcode ) as prizn , factstat 
       from (

 /* << два юниона  на выборку приходов если проведеный то с партиями если нет то с нулевой партией .*/
              Select   fi2ei.modify_time   , fi2ei.estimateitemcode  , fi2ei.countgen as countfact ,  rqfkor.moloutcode , 
                       rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
                       , rqfk2part.partycode ,  rqfkor.kindcode  ,rqfkorstatu.name as statusname , 
         case when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ' then ' '  
                          
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then rqfkor.moloutcode||' '||rqfkor.moloutname   
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname    
         else ' ' end  as factstat                    
                       
               From  rqfkorderitem2enstmttm fi2ei , rqfkorderitem rqfkit ,  rqfkorder rqfkor 
                   , rqfkorderdata2fkparty rqfk2part , rqfkorderstatus rqfkorstatu  
              where fi2ei.estimateitemcode =  p_estimate 
                and rqfkit.code = fi2ei.fkorderitemrefcode
                and rqfkor.code = rqfkit.fkorderrefcode
                and rqfkor.kindcode = 1 /*Приходный ордер*/
                and rqfk2part.fkorderitemrefcode = rqfkit.code
                and rqfk2part.estimateitemrefcode = fi2ei.estimateitemcode
                and rqfkorstatu.code =  rqfkor.statuscode
                and rqfkorstatu.code in (4,3) /*для провуеденых приходов */
                and fi2ei.countgen <> 0 /* не учитываем нулевые приходы так как по нему уже было дальше движение */
                 /* 22.04.2014 не учитываем приходы если по естимейту был документ по перемещению раньше */
              and not exists ( select * from rqfkorderitem2enstmttm fi2ei2 , rqfkorderitem fi2 , rqfkorder f2
                       where fi2ei2.estimateitemcode = p_estimate
                       and fi2ei2.fkorderitemrefcode = fi2.code 
                       and fi2.fkorderrefcode=f2.code
                       and f2.dategen <= rqfkor.dategen 
                       and f2.kindcode in (2,4,5,6,13 , 28 , 29)                         
             )
                UNION 
                Select   fi2ei.modify_time   , fi2ei.estimateitemcode  , fi2ei.countgen as countfact ,  rqfkor.moloutcode , 
                       rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
                       , 0 as partycode ,  rqfkor.kindcode  ,rqfkorstatu.name as statusname , 
                       case when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ' then ' '  
                          
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then rqfkor.moloutcode||' '||rqfkor.moloutname   
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname    
         else ' ' end  as factstat
                      
                       
               From  rqfkorderitem2enstmttm fi2ei , rqfkorderitem rqfkit ,  rqfkorder rqfkor 
                   ,  rqfkorderstatus rqfkorstatu  
              where fi2ei.estimateitemcode = p_estimate
                and rqfkit.code = fi2ei.fkorderitemrefcode
                and rqfkor.code = rqfkit.fkorderrefcode
                and rqfkor.kindcode = 1 /*Приходный ордер*/                
                and rqfkorstatu.code =  rqfkor.statuscode
                and rqfkorstatu.code = 2  /*для  приходов  складеных */
                and fi2ei.countgen <> 0 /* не учитываем нулевые приходы так как по нему уже было дальше движение */
                 /* 22.04.2014 не учитываем приходы если по естимейту был документ по перемещению раньше */
              and not exists ( select * from rqfkorderitem2enstmttm fi2ei2 , rqfkorderitem fi2 , rqfkorder f2
                       where fi2ei2.estimateitemcode = p_estimate
                       and fi2ei2.fkorderitemrefcode = fi2.code 
                       and fi2.fkorderrefcode=f2.code
                       and f2.dategen <= rqfkor.dategen 
                       and f2.kindcode in (2,4,5,6,13 , 28 , 29)                         
             )
  
  UNION ALL
  
 Select  fi2ei.modify_time  , fi2ei.estimateitemcode  , 
          case when fin.statusrefcode  = 3 then /*если статус перенесен на другой план */ 
               (select  coalesce(sum(fin1.quantity), 0) 
                  from finmaterials fin1
                 where fin1.estimateitemrefcode = p_estimate
                   and fin1.parentrefcode in
                           (select fin.code from finmaterials fin where
                                   fin.estimateitemrefcode = p_estimate and fin.statusrefcode = 3)
                   and   fin1.modify_time = (select max(fin3.modify_time) 
                                               from finmaterials fin3
                                              where fin3.estimateitemrefcode = p_estimate
                                                and fin3.parentrefcode in
                                                  (select fin4.code from finmaterials fin4 
                                                    where fin4.estimateitemrefcode = p_estimate
                                                      and fin4.statusrefcode = 3)
                            )           
               )                     
                           else fin.quantity end as countfact ,  
          rqfkor.moloutcode , 
          rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
         , fin.party_id as partycode ,  rqfkor.kindcode ,rqfkorstatu.name as statusname , 
         
          /* для расходного ордера */
        case
         when kindcode = 2 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ'  
                          then  rqfkor.molincode||' '||rqfkor.molinname  
         when kindcode = 2 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname                          
         when kindcode = 2 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname   
         else ' ' end as factstat
      
    
 from       
   rqfkorderitem2enstmttm fi2ei, rqfkorderitem rqfkit ,  rqfkorder rqfkor 
       ,  finmaterials fin  , rqfkorderstatus  rqfkorstatu       
where fi2ei.estimateitemcode = p_estimate
  and rqfkit.code = fi2ei.fkorderitemrefcode
  and rqfkor.code = rqfkit.fkorderrefcode
  and rqfkor.kindcode in (2,4,5,6,13 , 28 , 29) /*Видатковий  ордер*/
  and fin.estimateitemrefcode = fi2ei.estimateitemcode
  and fin.code = fi2ei.finmaterialsrefcode
  and rqfkorstatu.code = rqfkor.statuscode
  and rqfkor.statuscode <> 1
  
  

 
  
  UNION ALL 
  /*акты списание по месячным естимейтам  */ 
  
  Select  fin.modify_time as  modify_time ,  (select p_estimate ) as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  ,
  case when act.statusrefcode = 3  then /* не показываем фактическое состояние материала если акт проведен */
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
, finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
  /*убрали привязку к партии прихода */
  and  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
   /*and  act.statusrefcode = 3 проведенный в финколекции */ 
  and actstatu.code = act.statusrefcode 
  

  
  
    
 and     enit2enit2.estimateiteminrefcode /*=*/ in  (
   Select enit2enit1.estimateitemoutrefcode from enestimateitem2nstmttm enit2enit1 /*наличие материала на плане*/
    Where enit2enit1.estimateiteminrefcode = p_estimate )
 and (SELECT p_kindcode) = 2 /*если признак месячній  план */
         
    UNION ALL    
    
      /*акты списание по естимейтем План  */ 
  
  Select  fin.modify_time as  modify_time , (select p_estimate ) as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname ,
  case when act.statusrefcode = 3  then  /*не показываем фактическое состояние материала если акт проведен*/ 
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
, finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
  /*убрали привязку к партии прихода */
  and  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
  /* and  act.statusrefcode = 3 проведенный в финколекции*/
  and actstatu.code = act.statusrefcode 
  
  and ( enit2enit2.estimateiteminrefcode = p_estimate )
  and (SELECT p_kindcode) = 3 /*если признак задание план  */
       
   UNION ALL    
    
      /*акты списание по естимейтем Факт  */ 
  
  Select  fin.modify_time as  modify_time ,  ( select p_estimate ) as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname , 
  case when act.statusrefcode = 3  then  /*не показываем фактическое состояние материала если акт проведен */
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from /*enestimateitem2nstmttm enit2enit2*/ /*наличие материала на факте*/
/*,*/ finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   /*fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        */
  /*убрали привязку к партии прихода */
  /*and*/  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
  /*and  act.statusrefcode = 3 проведенный в финколекции*/ 
  and actstatu.code = act.statusrefcode 
  
  /*and ( enit2enit2.estimateitemoutrefcode = p_estimate ) */
  and ene.code = p_estimate
  and (SELECT p_kindcode)  = 4 /*если признак задание   факт */   
         
         
  

      
  
  
  
  
  ) www 
  
 


  Group by  estimateitemcode  ,  /*countfact ,*/ nomenclaturename , nomenclatureunitname  , partycode ,  
           kindcode  ,statusname , moloutcode , moloutname , factstat 
           
 
  ) wwww 
  where ( prizn = 0 or kindcode = 10 )
  --- YYY 
  and  
        ( ( (pceh = 18 ) and   (moloutcode like ('18%') and kindcode <> 10  ) ) 
         or (pceh  = 0 )  
        )    


UNION  /*======================================================================================*/


select 
       f.estimateitemrefcode as estimateitemcode , f.quantity as countfact , 
       f.mat_name as nomenclaturename  , f.mu_name as nomenclatureunitname , 
       coalesce(f.party_id,0) as partycode ,  0 as kindcode  , ' '  as statusname ,    0 as modify_time , 
       ' ' as moloutcode , 'Зарезерв. під акти' as  moloutname , 0 as prizn , ' ' as objfactname
      
       , f.div_code ||' '|| f.div_name  as factstatt
       , ( select min(em.monthgen) || ' ' || min(em.yeargen) from enplanworkmovehistory em where em.planrefcode in (
           select ep.code from enestimateitem e , enplanwork ep   
            where e.planrefcode = ep.code 
              and e.code = estimateitemrefcode                 )
          ) as movedateplan

from finmaterials f 
where f.statusrefcode = 1 
and f.code in (select fi.code  from finmaterials  fi 
where fi.modify_time in (
select max(modify_time)   from finmaterials  f where f.statusrefcode = 1
and f.estimateitemrefcode in ( /*если с месячного задания то  развязаные материалы на плане и на факте */
 
select estimatecode from ( 
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode = p_estimate
     and  (SELECT p_kindcode)  = 2    
UNION
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode in ( Select en2en.estimateitemoutrefcode as estimatecode 
                                                from  enestimateitem2nstmttm en2en 
                                                where en2en.estimateiteminrefcode = p_estimate )
     and (SELECT p_kindcode) = 2  
) sel_if_month 
UNION 
/* если с задания План то развязаные материалы на плане и на факте */
select estimatecode from ( 
Select p_estimate as estimatecode 
where (SELECT p_kindcode) = 3   
UNION  
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode = p_estimate
     and  (SELECT p_kindcode) = 3    


) sel_if_plan  
UNION 
/* если с задания Факт то развязаные материалы на факте и на плане */
select estimatecode from ( 
Select p_estimate as estimatecode 
where  (SELECT p_kindcode)  = 4  
UNION  
Select en2en.estimateiteminrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateitemoutrefcode = p_estimate
     and  (SELECT p_kindcode) = 4    


) sel_if_plan  
    		    )
                
              group by   f.mat_id
                
                
       )  )


and ( /* ищем в ордерах */
    coalesce((    
           Select coalesce(sum(countgen),0) from (    
            Select  coalesce(sum(fi2ei.countgen),0) as  countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode = p_estimate
                and (SELECT p_kindcode) = 2 /*Мес*/
             UNION
              Select  coalesce(sum(fi2ei.countgen),0) as countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode in (select en2en.estimateiteminrefcode from enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode  = p_estimate ) 
                and (SELECT p_kindcode) = 3 /*план*/ 
              UNION
              Select  coalesce(sum(fi2ei.countgen),0) as countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode in (select en2en.estimateiteminrefcode from  enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode in 
                                                 (select estimateiteminrefcode from enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode  = p_estimate
                                                 ) 
                                              )
                and (SELECT p_kindcode) = 4 /*факт*/   
                ) selord
               
                 ),0) = 0 
   
 And  /* ищем в актах */
                            (   select coalesce(sum(countfact),0)  from (
                          /*акты списание по месячным естимейтам  */   
                          Select  fin.modify_time as  modify_time ,  (select p_estimate ) as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
                        , finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            
                           fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
                          and  fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          and  enit2enit2.estimateiteminrefcode /*=*/ in  (
                           Select enit2enit1.estimateitemoutrefcode from enestimateitem2nstmttm enit2enit1 /*наличие материала на плане*/
                            Where enit2enit1.estimateiteminrefcode = p_estimate )
                         and (SELECT p_kindcode) = 2 /*если признак месячній  план */
                                 
                            UNION     
                            
                              /*акты списание по естимейтем План  */ 
                          
                          Select  fin.modify_time as  modify_time , ( select p_estimate ) as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
                        , finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            
                           fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        

                          and  fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          
                          and ( enit2enit2.estimateiteminrefcode = p_estimate )
                          and (SELECT p_kindcode) = 3 /*если признак задание план  */
                               
                           UNION     
                            
                              /*акты списание по естимейтем Факт  */ 
                          
                          Select  fin.modify_time as  modify_time , ( select  p_estimate ) as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from /*enestimateitem2nstmttm enit2enit2*/ /*наличие материала на факте*/
                        /*,*/ finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            /*
                           fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
                          and*/  fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          
                          /*and ( enit2enit2.estimateitemoutrefcode = p_estimate ) */
                          and ene.code  = p_estimate
                          and (SELECT p_kindcode)  = 4 /*если признак задание   факт */   
                          

                          ) selact 

     ) = 0 
  
 )
  
 order by nomenclaturename , kindcode ;
  
 /*начало процедурі*/ 
BEGIN

  for rr in 
  Select distinct   staterefcode ,  objectcode ,   depname ,  budgname , 
  cast(eicode as numeric) as eicode , 
  cast(kindcode as numeric ) as kindcode
  , materialname
         ,countfact 
       ,  aaaa from (


Select    (  select ed.ename ||' Инв.№'|| ed.invnumber from enelementdata ed where ed.ecode = elementrefcode )||
                 ' / (' || (select enplanworkstate.shortname from enplanworkstate , enplanwork 
         where enplanworkstate.code =  staterefcode
          and  enplanwork.code = plancode
       )||')'  as object  
       , staterefcode ,  
         depname ,
         budgname , 
         objectcode ,
         prizn , 
         aaaa , 
         eicode ,
         kindcode , 
         materialname
         ,countfact 
      

       
 from ( 
select  enpl.kindcode    ,
        enit.code as eicode , 
        enit.countfact ,  
        enit.materialrefcode , 
        tkm.name as materialname , 
        enpi.code , 
        tk.name as workname , 
        tk.techkartnumber , 
        tkmesu.name as namemeasure, 
        enpl.elementrefcode,
        enpl.staterefcode , 
        enpl.code as plancode , 
        (select endepartment.name from endepartment  where endepartment.code = enpl.budgetrefcode ) as budgname, 
        (select endepartment.name from endepartment  where endepartment.code = enpl.departmentrefcode ) as depname , 
        enpl.elementrefcode  as objectcode
 
 
 , case when enpl.kindcode = 2 then 1
        when enpl.kindcode = 3 then 
        case 
              /*если есть в месячном то не  берем План */
             when   coalesce(( select case when sum(en2en.countgen) > 0 then 999999  
                                           when sum(en2en.countgen) is null then -1 else 0 end 
                               from enestimateitem2nstmttm en2en where en2en.estimateitemoutrefcode = enit.code
                                and en2en.typerefcode = 1  ),0) > 0    then 0 
             /* если нету в месячном  берем План  */
             when coalesce(( select case when sum(en2en.countgen) > 0 then 999999  
                                         when sum(en2en.countgen) is null then -1 else 0 end   
                               from enestimateitem2nstmttm en2en where en2en.estimateitemoutrefcode = enit.code
                                and en2en.typerefcode = 1  ),0) in ( -1 , 0 )   then 1                                    
                                end                                
                              
        when enpl.kindcode = 4 then
             /* если есть на месячном или на Плане то не берем */
        case  when ( coalesce(( select case when sum(en2en.countgen) > 0 then 999999  
                                            when sum(en2en.countgen) is null then -1 else 0 end 
                               from enestimateitem2nstmttm en2en where en2en.estimateitemoutrefcode = enit.code  
                                and en2en.typerefcode = 1  ),0) > 0 
                                
                                or 
                   coalesce(( select  case when sum(en2en.countgen) > 0 then 999999  
                                           when sum(en2en.countgen) is null then -1 else 0 end 
                               from enestimateitem2nstmttm en2en where en2en.estimateitemoutrefcode = (select en2en.estimateiteminrefcode from enestimateitem2nstmttm en2en where en2en.estimateitemoutrefcode = enit.code)
                                and en2en.typerefcode = 1  ),0) > 0  
                    )   then 0  else 1   
                                end                            
                               
    end  
       
      as prizn 
      
      ,
      /*шифруем по комплектации -----------------------------------------------*/ 
      /* количество из движений */
  
 ( select coalesce(sum(countfact),0) from (
select cast(enit.code||' | '|| enpl.kindcode as varchar) as slujfield , 
        estimateitemcode  ,  countfact  , nomenclaturename , nomenclatureunitname  , partycode ,  
        kindcode  , statusname ,   modify_time , 
        moloutcode , moloutname ,  prizn 
 ,        ' '  as objfactname
/*выбор фактического состояния материала */ 
     , 
      wwww.factstat  as factstatt
,
      
      ( select min(em.monthgen) || ' ' || min(em.yeargen) from enplanworkmovehistory em where em.planrefcode in (
         select ep.code from enestimateitem e , enplanwork ep   
           where e.planrefcode = ep.code 
             and e.code = estimateitemcode                 )
       ) as movedateplan                  
     from ( 

select  estimateitemcode  ,  sum(countfact) as countfact  , nomenclaturename , nomenclatureunitname  , partycode ,  
        kindcode  , statusname ,   max(modify_time) as modify_time , 
        moloutcode , moloutname 
      ,  f_analyse_motion(estimateitemcode  , sum(countfact) , 
                          nomenclaturename , nomenclatureunitname ,                          
                          partycode , kindcode , max(modify_time)  , kindcode ) as prizn , factstat 
       from (

 /* << два юниона  на выборку приходов если проведеный то с партиями если нет то с нулевой партией .*/
              Select   fi2ei.modify_time   , fi2ei.estimateitemcode  , fi2ei.countgen as countfact ,  rqfkor.moloutcode , 
                       rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
                       , rqfk2part.partycode ,  rqfkor.kindcode  ,rqfkorstatu.name as statusname , 
         case when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ' then ' '  
                          
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then rqfkor.moloutcode||' '||rqfkor.moloutname   
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname    
         else ' ' end  as factstat                    
                       
               From  rqfkorderitem2enstmttm fi2ei , rqfkorderitem rqfkit ,  rqfkorder rqfkor 
                   , rqfkorderdata2fkparty rqfk2part , rqfkorderstatus rqfkorstatu  
              where fi2ei.estimateitemcode =  enit.code 
                and rqfkit.code = fi2ei.fkorderitemrefcode
                and rqfkor.code = rqfkit.fkorderrefcode
                and rqfkor.kindcode = 1 /*Приходный ордер*/
                and rqfk2part.fkorderitemrefcode = rqfkit.code
                and rqfk2part.estimateitemrefcode = fi2ei.estimateitemcode
                and rqfkorstatu.code =  rqfkor.statuscode
                and rqfkorstatu.code in (4,3) /*для провуеденых приходов */
                and fi2ei.countgen <> 0 /* не учитываем нулевые приходы так как по нему уже было дальше движение */
                 /* 22.04.2014 не учитываем приходы если по естимейту был документ по перемещению раньше */
              and not exists ( select * from rqfkorderitem2enstmttm fi2ei2 , rqfkorderitem fi2 , rqfkorder f2
                       where fi2ei2.estimateitemcode = enit.code
                       and fi2ei2.fkorderitemrefcode = fi2.code 
                       and fi2.fkorderrefcode=f2.code
                       and f2.dategen <= rqfkor.dategen 
                       and f2.kindcode in (2,4,5,6,13 , 28 , 29)                         
             )
                UNION 
                Select   fi2ei.modify_time   , fi2ei.estimateitemcode  , fi2ei.countgen as countfact ,  rqfkor.moloutcode , 
                       rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
                       , 0 as partycode ,  rqfkor.kindcode  ,rqfkorstatu.name as statusname , 
                       case when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ' then ' '  
                          
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then rqfkor.moloutcode||' '||rqfkor.moloutname   
         when rqfkor.kindcode = 1 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname    
         else ' ' end  as factstat
                      
                       
               From  rqfkorderitem2enstmttm fi2ei , rqfkorderitem rqfkit ,  rqfkorder rqfkor 
                   ,  rqfkorderstatus rqfkorstatu  
              where fi2ei.estimateitemcode = enit.code
                and rqfkit.code = fi2ei.fkorderitemrefcode
                and rqfkor.code = rqfkit.fkorderrefcode
                and rqfkor.kindcode = 1 /*Приходный ордер*/                
                and rqfkorstatu.code =  rqfkor.statuscode
                and rqfkorstatu.code = 2  /*для  приходов  складеных */
                and fi2ei.countgen <> 0 /* не учитываем нулевые приходы так как по нему уже было дальше движение */
                 /* 22.04.2014 не учитываем приходы если по естимейту был документ по перемещению раньше */
              and not exists ( select * from rqfkorderitem2enstmttm fi2ei2 , rqfkorderitem fi2 , rqfkorder f2
                       where fi2ei2.estimateitemcode = enit.code
                       and fi2ei2.fkorderitemrefcode = fi2.code 
                       and fi2.fkorderrefcode=f2.code
                       and f2.dategen <= rqfkor.dategen 
                       and f2.kindcode in (2,4,5,6,13 , 28 , 29)                         
             )
  
  UNION ALL
  
 Select  fi2ei.modify_time  , fi2ei.estimateitemcode  , 
          case when fin.statusrefcode  = 3 then /*если статус перенесен на другой план */ 
               (select  coalesce(sum(fin1.quantity), 0) 
                  from finmaterials fin1
                 where fin1.estimateitemrefcode = enit.code
                   and fin1.parentrefcode in
                           (select fin.code from finmaterials fin where
                                   fin.estimateitemrefcode = enit.code and fin.statusrefcode = 3)
                   and   fin1.modify_time = (select max(fin3.modify_time) 
                                               from finmaterials fin3
                                              where fin3.estimateitemrefcode = enit.code
                                                and fin3.parentrefcode in
                                                  (select fin4.code from finmaterials fin4 
                                                    where fin4.estimateitemrefcode = enit.code
                                                      and fin4.statusrefcode = 3)
                            )           
               )                     
                           else fin.quantity end as countfact ,  
          rqfkor.moloutcode , 
          rqfkor.moloutname  , rqfkit.nomenclaturename , rqfkit.nomenclatureunitname  
         , fin.party_id as partycode ,  rqfkor.kindcode ,rqfkorstatu.name as statusname , 
         
          /* для расходного ордера */
        case
         when kindcode = 2 and upper(rqfkorstatu.name) = 'СКЛАДЕНИЙ'  
                          then  rqfkor.molincode||' '||rqfkor.molinname  
         when kindcode = 2 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname                          
         when kindcode = 2 and upper(rqfkorstatu.name) = 'ПРОВЕДЕНИЙ ЯК ОЗ'
                          then  rqfkor.moloutcode||' '||rqfkor.moloutname   
         else ' ' end as factstat
      
    
 from       
   rqfkorderitem2enstmttm fi2ei, rqfkorderitem rqfkit ,  rqfkorder rqfkor 
       ,  finmaterials fin  , rqfkorderstatus  rqfkorstatu       
where fi2ei.estimateitemcode = enit.code
  and rqfkit.code = fi2ei.fkorderitemrefcode
  and rqfkor.code = rqfkit.fkorderrefcode
  and rqfkor.kindcode in (2,4,5,6,13 , 28 , 29) /*Видатковий  ордер*/
  and fin.estimateitemrefcode = fi2ei.estimateitemcode
  and fin.code = fi2ei.finmaterialsrefcode
  and rqfkorstatu.code = rqfkor.statuscode
  and rqfkor.statuscode <> 1
  
  

 
  
  UNION ALL 
  /*акты списание по месячным естимейтам  */ 
  
  Select  fin.modify_time as  modify_time ,  enit.code as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  ,
  case when act.statusrefcode = 3  then /* не показываем фактическое состояние материала если акт проведен */
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
, finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
  /*убрали привязку к партии прихода */
  and  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
   /*and  act.statusrefcode = 3 проведенный в финколекции */ 
  and actstatu.code = act.statusrefcode 
  

  
  
    
 and     enit2enit2.estimateiteminrefcode /*=*/ in  (
   Select enit2enit1.estimateitemoutrefcode from enestimateitem2nstmttm enit2enit1 /*наличие материала на плане*/
    Where enit2enit1.estimateiteminrefcode = enit.code )
 and (SELECT enpl.kindcode) = 2 /*если признак месячній  план */
         
    UNION ALL    
    
      /*акты списание по естимейтем План  */ 
  
  Select  fin.modify_time as  modify_time , enit.code as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname ,
  case when act.statusrefcode = 3  then  /*не показываем фактическое состояние материала если акт проведен*/ 
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
, finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
  /*убрали привязку к партии прихода */
  and  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
  /* and  act.statusrefcode = 3 проведенный в финколекции*/
  and actstatu.code = act.statusrefcode 
  
  and ( enit2enit2.estimateiteminrefcode = enit.code )
  and (SELECT enpl.kindcode) = 3 /*если признак задание план  */
       
   UNION ALL    
    
      /*акты списание по естимейтем Факт  */ 
  
  Select  fin.modify_time as  modify_time ,  enit.code  as estimateitemcode , fin.quantity as countfact  , 
  act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
  fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
  fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname , 
  case when act.statusrefcode = 3  then  /*не показываем фактическое состояние материала если акт проведен */
    ' '
     else  
     fin.div_code ||' '|| fin.div_name  end as factstat
    from /*enestimateitem2nstmttm enit2enit2*/ /*наличие материала на факте*/
/*,*/ finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
  enact act , enactstatus actstatu
 Where   
    
   /*fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        */
  /*убрали привязку к партии прихода */
  /*and*/  fin.estimateitemrefcode = ene.code
  and  ene.planrefcode = enp.code  
  and  act2wor.plancode  =  enp.code 
  and  act2wor.actrefcode = act.code
  and  fin.statusrefcode = 1 /*действительный*/
  /*and  act.statusrefcode = 3 проведенный в финколекции*/ 
  and actstatu.code = act.statusrefcode 
  and ene.code = enit.code
  /*and ( enit2enit2.estimateitemoutrefcode = enit.code ) */
  and (SELECT enpl.kindcode)  = 4 /*если признак задание   факт */   
         
         
  

      
  
  
  
  
  ) www 
  
 


  Group by  estimateitemcode  ,  /*countfact ,*/ nomenclaturename , nomenclatureunitname  , partycode ,  
           kindcode  ,statusname , moloutcode , moloutname , factstat 
           
 
  ) wwww 
  where ( prizn = 0 or kindcode = 10 )
  
UNION  /*======================================================================================*/


select cast(enit.code||' | '|| enpl.kindcode as varchar) as slujfield , 
       f.estimateitemrefcode as estimateitemcode , f.quantity as countfact , 
       f.mat_name as nomenclaturename  , f.mu_name as nomenclatureunitname , 
       coalesce(f.party_id,0) as partycode ,  0 as kindcode  , ' '  as statusname ,    0 as modify_time , 
       ' ' as moloutcode , 'Зарезерв. під акти' as  moloutname , 0 as prizn , ' ' as objfactname
      
       , f.div_code ||' '|| f.div_name  as factstatt
       , ( select min(em.monthgen) || ' ' || min(em.yeargen) from enplanworkmovehistory em where em.planrefcode in (
           select ep.code from enestimateitem e , enplanwork ep   
            where e.planrefcode = ep.code 
              and e.code = estimateitemrefcode                 )
          ) as movedateplan

from finmaterials f 
where f.statusrefcode = 1 
and f.code in (select fi.code  from finmaterials  fi 
where fi.modify_time in (
select max(modify_time)   from finmaterials  f where f.statusrefcode = 1
and f.estimateitemrefcode in ( /*если с месячного задания то  развязаные материалы на плане и на факте */
 
select estimatecode from ( 
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode = enit.code
     and  (SELECT enpl.kindcode)  = 2    
UNION
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode in ( Select en2en.estimateitemoutrefcode as estimatecode 
                                                from  enestimateitem2nstmttm en2en 
                                                where en2en.estimateiteminrefcode = enit.code )
     and (SELECT enpl.kindcode) = 2  
) sel_if_month 
UNION 
/* если с задания План то развязаные материалы на плане и на факте */
select estimatecode from ( 
Select enit.code as estimatecode 
where (SELECT enpl.kindcode) = 3   
UNION  
Select en2en.estimateitemoutrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateiteminrefcode = enit.code
     and  (SELECT enpl.kindcode) = 3    


) sel_if_plan  
UNION 
/* если с задания Факт то развязаные материалы на факте и на плане */
select estimatecode from ( 
Select enit.code as estimatecode 
where  (SELECT enpl.kindcode)  = 4  
UNION  
Select en2en.estimateiteminrefcode as estimatecode 
   from  enestimateitem2nstmttm en2en 
   where en2en.estimateitemoutrefcode = enit.code
     and  (SELECT enpl.kindcode) = 4    


) sel_if_plan  
    		    )
                
              group by   f.mat_id
                
                
       )  )


and ( /* ищем в ордерах */
    coalesce((    
           Select coalesce(sum(countgen),0) from (    
            Select  coalesce(sum(fi2ei.countgen),0) as  countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode = enit.code
                and (SELECT enpl.kindcode) = 2 /*Мес*/
             UNION
              Select  coalesce(sum(fi2ei.countgen),0) as countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode in (select en2en.estimateiteminrefcode from enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode  = enit.code ) 
                and (SELECT enpl.kindcode) = 3 /*план*/ 
              UNION
              Select  coalesce(sum(fi2ei.countgen),0) as countgen
               From  rqfkorderitem2enstmttm fi2ei  
              where fi2ei.estimateitemcode in (select en2en.estimateiteminrefcode from  enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode in 
                                                 (select estimateiteminrefcode from enestimateitem2nstmttm en2en 
                                               where en2en.estimateitemoutrefcode  = enit.code
                                                 ) 
                                              )
                and (SELECT enpl.kindcode) = 4 /*факт*/   
                ) selord
               
                 ),0) = 0 
   
 And  /* ищем в актах */
                            (   select coalesce(sum(countfact),0)  from (
                          /*акты списание по месячным естимейтам  */   
                          Select  fin.modify_time as  modify_time ,  enit.code as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
                        , finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            
                           fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
                          and  fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          and  enit2enit2.estimateiteminrefcode /*=*/ in  (
                           Select enit2enit1.estimateitemoutrefcode from enestimateitem2nstmttm enit2enit1 /*наличие материала на плане*/
                            Where enit2enit1.estimateiteminrefcode = enit.code )
                         and (SELECT enpl.kindcode) = 2 /*если признак месячній  план */
                                 
                            UNION     
                            
                              /*акты списание по естимейтем План  */ 
                          
                          Select  fin.modify_time as  modify_time , enit.code as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/
                        , finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            
                           fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        

                          and  fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          
                          and ( enit2enit2.estimateiteminrefcode = enit.code )
                          and (SELECT enpl.kindcode) = 3 /*если признак задание план  */
                               
                           UNION     
                            
                              /*акты списание по естимейтем Факт  */ 
                          
                          Select  fin.modify_time as  modify_time , enit.code  as estimateitemcode , coalesce(fin.quantity,0) as countfact  , 
                          act.numbergen as moloutcode , to_char(act.dategen ,'dd.mm.yyyy') as moloutname , 
                          fin.mat_name as nomenclaturename , fin.mu_name as nomenclatureunitname , 
                          fin.party_id as partycode , 10 as kindcode , actstatu.name as statusname  
                            from /*enestimateitem2nstmttm enit2enit2*/ /*наличие материала на факте*/
                        /*,*/ finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , 
                          enact act , enactstatus actstatu
                         Where   
                            
                           /*fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode                                        
                          and */ fin.estimateitemrefcode = ene.code
                          and  ene.planrefcode = enp.code  
                          and  act2wor.plancode  =  enp.code 
                          and  act2wor.actrefcode = act.code
                          and  fin.statusrefcode = 1 /*действительный*/
                          and actstatu.code = act.statusrefcode 
                          and ene.code = enit.code 
                          /*and ( enit2enit2.estimateitemoutrefcode = enit.code ) */
                          and (SELECT enpl.kindcode)  = 4 /*если признак задание   факт */   
                          

                          ) selact 

     ) = 0 
  
 )
) selcountfact

where    
        ( ( (pceh = 18 ) and   (moloutcode like ('18%') and kindcode <> 10  ) ) 
         or (pceh = 0 )  
         )

) 
   as aaaa
      
  
      
      
      

 from enestimateitem enit left join enplanworkitem enpi on (enit.planitemrefcode = enpi.code) 
                                  left join tktechcard tk on (enpi.kartarefcode = tk.code ) ,  
     enplanwork enpl ,
     tkmaterials tkm , 
     tkmeasurement tkmesu  ,

                  ( Select   enplanwork.code  from enelement , enplanwork
                     Where   enelement.code = enplanwork.elementrefcode
                       and   enplanwork.datestart between to_date(pstartdate,'dd.mm.yyyy') and  to_date(penddate,'dd.mm.yyyy')
                       and ( enelement.typerefcode = pelementtypecode or 0 = pelementtypecode )
                       
                        --   and ( enplanwork.budgetrefcode = pbudgcode  or 0 = pbudgcode )
                       and enplanwork.budgetrefcode = ANY ( pbudgcode )
                       and ( enplanwork.departmentrefcode = prencode or 0 = prencode )
                       and ( enplanwork.code = pplancode  or  0 = pplancode )
                       and ( enplanwork.elementrefcode = pelementcode or  0 = pelementcode )
                       and ( enplanwork.formrefcode = pformplancode or 0 = pformplancode )

                   ) codeplonelement 
    
 where codeplonelement.code = enpl.code 
   and enit.planrefcode = enpl.code
   and enit.kindrefcode = 1 /*только материалы*/
   and enit.statusrefcode in (1,2,3) 
   and enit.countfact <> 0
   and case when ordercode > -1 then enit.code in (select oies.estimateitemcode 
 					from rqorderitem2enestimttm as oies 
                    			inner join rqorderitem as oi
                    			on oies.orderitemcode = oi.code
                    			where oi.orderrefcode = ordercode)
	else 1 = 1 end 
   and enpl.kindcode = 2 /*не учитываем годовые планы */ /*сделаем так что бы выбирались только из месячных планов (согласовано с салыгиным)*/
   and tkm.code = enit.materialrefcode
   and tkmesu.code = tkm.measurementcode

  -- and enit.code = 500221378 
        /* группа материалов или конкретный материал */ 
   
    and  tkm.code in  
                  (select tm.code from tkmaterials tm 
                    Where ( 
                            ( tm.rootgrouprefcode  = ANY( pstrgroupmaterials  ) and pgroup = 1 ) -- если по групам
                    or  
                            ( tm.code = ANY( pstrgroupmaterials) and pgroup = 0 ) -- если по материалам
                           )
                   
                  ) 
   
/*     если с признаком только центральные склады то поле аааа проверим на не 0 */


 
  group by enit.code ,  enit.materialrefcode   , enpl.kindcode , tkm.name , enpi.code   , tk.name ,  enit.countfact ,
           tk.techkartnumber  , tkmesu.name ,  enpl.elementrefcode, enpl.staterefcode ,  enpl.code , tkm.name 
           , enpl.budgetrefcode , enpl.departmentrefcode 
  order by materialrefcode , enpl.kindcode
  
   ) selsprizn
  
   where prizn = 1 
   
   /*проверка недоукоплектованых или переукомплектованих материалов*/
  /*  and  
     ( 
          ( (select pkomplekt  ) = (select 0)  )
          or 
          ( ( (select pkomplekt  ) = (select 1) ) and (selsprizn.countfact > selsprizn.aaaa)  )
          or 
          ( (  (select pkomplekt ) = (select 2) ) and (selsprizn.countfact < selsprizn.aaaa)  )
          or 
          ( (  (select pkomplekt ) = (select 3) ) and (selsprizn.countfact = selsprizn.aaaa)  )
     ) */
     
     and   (( pceh = 18 and selsprizn.aaaa <> 0 ) or (pceh = 0 ) )
   ) 
   selnotdist
   where selnotdist.eicode = ANY (pstrestimateitems)
       
       order by selnotdist.depname , selnotdist.materialname ,  selnotdist.budgname 
       
       LOOP
       

        p_kindcode := rr.kindcode;
        p_estimate := rr.eicode;
     --     p_estimate := 30194275::numeric;
        OPEN onlyestimate;
      
        
       OPEN states;
        v_count_row_states := 0 ;
      LOOP
     

          FETCH states INTO  report_row;            
     IF  found = true THEN     
      --    EXIT when not found; 
           
          RETURN NEXT report_row;
    v_count_row_states:= v_count_row_states + 1 ; 
    ELSE
       
      if v_count_row_states = 0 then    
          FETCH onlyestimate INTO report_row ;
         end if;   
         RETURN NEXT  report_row;
    
          EXIT;
     END IF;   
      
--               RETURN NEXT states_row;
      
  /*      IF not found  THEN
          FETCH onlyestimate INTO report_row ;
           RETURN NEXT  report_row;
          EXIT; 
         end if;  */ 
   
           

      END LOOP;
     
       CLOSE states;
       CLOSE onlyestimate;
        
       END LOOP ;
       
    





  
  
  
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100 ROWS 1000;
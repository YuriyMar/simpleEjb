
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2HumenDAOGen;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Humen;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2HumenShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2HumenShortList;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;

  /**
  *  DAO Object for ENPlanWorkItem2Humen;
  *
  */

public class ENPlanWorkItem2HumenDAO extends ENPlanWorkItem2HumenDAOGen {

//  public ENPlanWorkItem2HumenDAO() {super();}
//  public ENPlanWorkItem2HumenDAO(Connection aConnection) {super(aConnection);}
//  public ENPlanWorkItem2HumenDAO(UserProfile anUserProfile) {super(anUserProfile);}
  public ENPlanWorkItem2HumenDAO(UserProfile anUserProfile,Connection aConnection) {super(anUserProfile,aConnection);}
  public ENPlanWorkItem2HumenDAO(Connection aConnection,UserProfile anUserProfile) {super(aConnection,anUserProfile);}



  public ENPlanWorkItem2HumenShortList getDataList(int actCode) throws PersistenceException
   {
    ENPlanWorkItem2HumenShortList result = new ENPlanWorkItem2HumenShortList();
    ENPlanWorkItem2HumenShort anObject;
    result.list = new Vector<ENPlanWorkItem2HumenShort>();

    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;


    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");


    /* selectStr =   " select picode, ctype, tabnumber, fio, salary, timemonth, daysmonth, salaryhours, \n" +
    " workfact, workgen, pitimegen, humenkind  \n" +
    " from  \n" +
    " ( \n" +
    " select  \n" +
    " pi.code as picode, \n" +
    "  tc.classificationtypecode as ctype, \n" +
    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours, \n" +
    " sum(p2h.timeworkfact) as workfact, sum(p2h.timeworkgen) as workgen, sum(h.countfact +  \n" +
    " coalesce(dt.deliverytimefact,0)) as pitimegen \n" +
    " , 1 as humenkind \n" +
    " from \n" +
    " enact2enplanwork a2p, enplan2humen p2h, \n" +
    " enact2humen a2h \n" +
    " , enplanworkitem pi, tktechcard tc, \n" +
    " enhumenitem h , finworker f, endeliverytime dt \n" +
    " where \n" +
    " a2p.actrefcode = ?  \n" +
    " and p2h.humenkindrefcode = 1 \n" +
    " and a2h.humenkindrefcode = 1 \n" +
    " and a2p.plancode = p2h.planrefcode \n" +
    " and a2h.actrefcode = a2p.actrefcode \n" +
    " and pi.planrefcode = a2p.plancode \n" +
    " and pi.countgen > 0 and pi.kartarefcode = tc.code \n" +
    " and h.planitemrefcode = pi.code \n" +
    " and h.planrefcode = a2p.plancode \n" +
    " and f.code = h.finworkercode \n" +
    " and p2h.tabnumber = f.tabnumber \n" +
    " and a2h.tabnumber = f.tabnumber \n" +
    " and dt.humenitemrefcode = h.code \n" +
    " group by pi.code, tc.classificationtypecode, \n" +
    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours \n" +
    "  \n" +
    " union all \n" +
    "  \n" +
    " select  \n" +
    " pi.code as picode, \n" +
    "  tc.classificationtypecode as ctype, \n" +
    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours, \n" +
    " sum(p2h.timeworkfact) as workfact, \n" +
    " sum(case when coalesce(p2h.timeworkgen, 0) = 0 then dtp.deliverytimefact else p2h.timeworkgen end) as workgen, \n" +
    " ( 0 закоментарить) sum(p2h.timeworkfact)  as pitimegen \n" + //!!! 22.09.11 sum(p2h.timeworkfact) as pitimegen - типа время для водителя
    " , 2 as humenkind \n" +
    " from \n" +
    " enact2enplanwork a2p left join endeliverytimeplan dtp on a2p.plancode = dtp.planworkrefcode, enplan2humen p2h, \n" +
    " enact2humen a2h \n" +
    " , enplanworkitem pi, tktechcard tc, \n" +
    " entransportitem t , finworker f \n" +
    " where \n" +
    " a2p.actrefcode = ?  \n" +
    " and p2h.humenkindrefcode = 2 \n" +
    " and a2h.humenkindrefcode = 2 \n" +
    " and a2p.plancode = p2h.planrefcode \n" +
    " and a2h.actrefcode = a2p.actrefcode \n" +
    " and pi.planrefcode = a2p.plancode \n" +
    " and pi.countgen > 0 and pi.kartarefcode = tc.code \n" +
    " and t.planitemrefcode = pi.code \n" +
    " and t.planrefcode = a2p.plancode \n" +
    " and f.code = t.finworkercode \n" +
    " and p2h.tabnumber = f.tabnumber \n" +
    " and a2h.tabnumber = f.tabnumber \n" +
    " and t.countworkfact <> 0 \n" +
    " group by pi.code, tc.classificationtypecode, \n" +
    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours \n" +
    " ) dat \n" +
    " order by dat.picode, dat.tabnumber, dat.humenkind \n" ;
*/

// " ";
    // 2012.06.27 - задваивалось отработанное время для водителей исправил второй юнион
	    selectStr =   " select picode, ctype, tabnumber, fio, salary, timemonth, daysmonth, salaryhours, \n" +
	    " workfact, workgen, pitimegen, humenkind, \n" +
	    ///// 13.05.13 NET-4235 Отдельно вытянем время и стоимость доставки
	    " timedelivery, (salaryhours * timedelivery) as costdelivery \n" + 
	    "  , coalesce(clSalaryPercentBonus,0) as clSalaryPercentBonus /*Премія*/ \n" +
	    "  , coalesce(clSalaryPercentSurcharge,0) as clSalaryPercentSurcharge /*Доплата*/  \n" +
	    "  , coalesce(clSalaryPercentPremium,0) as clSalaryPercentPremium  /*Надбавка*/  \n" +
	    "  , coalesce(clSalaryPercentAdditional,0) as clSalaryPercentAdditional /*Інші витрати*/ " + 
	    "  , positiongencode " + 
	    "  , transportcode " + 
	    /////
	    " from  \n" +
	    " ( \n" +
	    " select  \n" +
	    " h.positiongencode , " + 
	    " pi.code as picode, \n" +
	    "  tc.classificationtypecode as ctype, \n" +
	    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours, \n" +
	    " sum(p2h.timeworkfact) as workfact, sum(p2h.timeworkgen) as workgen, \n " +
	    // " sum(h.countfact + coalesce(dt.deliverytimefact,0)) as pitimegen \n" +
	    // Исправление того что попадала доставка по работнику если его поставили єлктромонтером но он біл водителем на плане
	   " case when \n " +
	   " ( /*выбрать то этому табельному и в одном плане человека в транспортитеме если найдем значит доставку не учитываем */ \n " +
	   " select count(*) from entransportitem ti  , finworker fw \n " +
	   " where fw.tabnumber = a2h.tabnumber \n " +
	   " and fw.code = ti.finworkercode \n " +
	   " and ti.planrefcode = h.planrefcode \n " +
	   "  ) > 0 then  sum(h.countfact) else \n " +
	   //"  sum(h.countfact) + sum(coalesce(dt.deliverytimefact, 0)) \n" +
	   "  sum(h.countfact) + coalesce((select sum(coalesce(dt.deliverytimefact,0)) from endeliverytime dt where dt.humenitemrefcode = h.code), 0) \n " +
	   "  end \n " +
	   " as pitimegen \n " +
	    " , 1 as humenkind, \n" +

	    ///// 13.05.13 NET-4235 Отдельно вытянем время доставки
	    // " , sum(coalesce(dt.deliverytimefact, 0)) as timedelivery \n " + // 22.05.13 Неправильно работает при наличии водителей-совместителей
	    " case when  \n" +
	    " ( /*выбрать то этому табельному и в одном плане человека в транспортитеме если найдем значит доставку не учитываем */  \n" +
	    " select count(*) from entransportitem ti  , finworker fw  \n" +
	    " where fw.tabnumber = a2h.tabnumber  \n" +
	    " and fw.code = ti.finworkercode  \n" +
	    " and ti.planrefcode = h.planrefcode  \n" +
	    "  ) > 0 then 0 else  \n" +
	    //"  sum(coalesce(dt.deliverytimefact, 0)) \n" +
	    " coalesce((select sum(coalesce(dt.deliverytimefact,0)) from endeliverytime dt where dt.humenitemrefcode = h.code),0) " +
	    "  end  \n" +
	    " as timedelivery   \n" +
	    "  \n" +
	    ///// 
	    "  , coalesce((select chs.percentbonus from enplancorrecthistory hhh  , enplanwork2classfctntp p2c , encalchumensalary chs  \n" +
	    " where p2c.code = chs.plan2ctyperefcode \n" +
	    " and hhh.planoldrefcode = p2c.planrefcode \n" +
	    " and chs.positionrefcode = h.positiongencode \n" +
	    " and hhh.reasoncode = 13  \n" +
	    " and  hhh.plannewrefcode in ( \n" +
	    "     select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    "     where hh.reasoncode = 4  \n" +
	    "       and hh.plannewrefcode in (  \n" +
	    " 	    select hs.planoldrefcode from enplancorrecthistory hs \n" +
	    " 	    where hs.reasoncode = 5  \n" +
	    " 	      and  hs.plannewrefcode = h.planrefcode \n" +
	    "         )  \n" +
	    " ) \n" +
	    " limit 1),0) as clSalaryPercentBonus /*Премія*/ \n" + 
	    "  , coalesce((select chs.percentsurcharge from enplancorrecthistory hhh  , enplanwork2classfctntp p2c , encalchumensalary chs   \n" +
	    " where p2c.code = chs.plan2ctyperefcode \n" +
	    " and hhh.planoldrefcode = p2c.planrefcode \n" +
	    " and chs.positionrefcode = h.positiongencode \n" +
	    " and hhh.reasoncode = 13  \n" +
	    " and  hhh.plannewrefcode in ( \n" +
	    "     select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    "     where hh.reasoncode = 4  \n" +
	    "       and hh.plannewrefcode in (  \n" +
	    " 	    select hs.planoldrefcode from enplancorrecthistory hs \n" +
	    " 	    where hs.reasoncode = 5  \n" +
	    " 	      and  hs.plannewrefcode = h.planrefcode \n" +
	    "         )  \n" +
	    " ) \n" +
	    " limit 1),0) as clSalaryPercentSurcharge  /*Доплата*/  \n" +
	    "  , coalesce((select chs.percentpremium from enplancorrecthistory hhh  , enplanwork2classfctntp p2c , encalchumensalary chs  \n" + 
	    " where p2c.code = chs.plan2ctyperefcode \n" +
	    " and hhh.planoldrefcode = p2c.planrefcode \n" +
	    " and chs.positionrefcode = h.positiongencode \n" +
	    " and hhh.reasoncode = 13  \n" +
	    " and  hhh.plannewrefcode in ( \n" +
	    "     select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    "    where hh.reasoncode = 4  \n" +
	    "      and hh.plannewrefcode in (  \n" +
	    " 	    select hs.planoldrefcode from enplancorrecthistory hs \n" +
	    " 	    where hs.reasoncode = 5  \n" +
	    " 	      and  hs.plannewrefcode = h.planrefcode \n" +
	    "         )  \n" +
	    " ) \n" +
	    "  limit 1 ),0) as clSalaryPercentPremium  /*Надбавка*/  \n" +
	    "  , coalesce((select chs.percentadditional from enplancorrecthistory hhh  , enplanwork2classfctntp p2c , encalchumensalary chs   \n" +
	    "  where p2c.code = chs.plan2ctyperefcode \n" +
	    "   and hhh.planoldrefcode = p2c.planrefcode \n" +
	    "   and chs.positionrefcode = h.positiongencode \n" +
	    "   and hhh.reasoncode = 13  \n" +
	    "   and  hhh.plannewrefcode in ( \n" +
	    " 	    select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    " 	    where hh.reasoncode = 4  \n" +
	    " 	      and hh.plannewrefcode in (  \n" +
	    " 	    select hs.planoldrefcode from enplancorrecthistory hs \n" +
			    " 		    where hs.reasoncode = 5  \n" +
			    " 	      and  hs.plannewrefcode = h.planrefcode \n" +
			    "         )  \n" +
			    "   ) \n" +
			    "   limit 1 ),0) as clSalaryPercentAdditional  /*Інші витрати*/ " +
	    " , null as transportcode " + 
	    " from \n" +
	    " enact2enplanwork a2p, enplan2humen p2h, \n" +
	    " enact2humen a2h \n" +
	    " , enplanworkitem pi, tktechcard tc, \n" +
	    " enhumenitem h , finworker f /*, endeliverytime dt*/ \n" +
	    " where \n" +
	    " a2p.actrefcode = ?  \n" +
	    " and p2h.humenkindrefcode = 1 \n" +
	    " and a2h.humenkindrefcode = 1 \n" +
	    " and a2p.plancode = p2h.planrefcode \n" +
	    " and a2h.actrefcode = a2p.actrefcode \n" +
	    " and pi.planrefcode = a2p.plancode \n" +
	    " and pi.countgen > 0 and pi.kartarefcode = tc.code \n" +
	    " and h.planitemrefcode = pi.code \n" +
	    " and h.planrefcode = a2p.plancode \n" +
	    " and f.code = h.finworkercode \n" +
	    " and p2h.tabnumber = f.tabnumber \n" +
	    " and a2h.tabnumber = f.tabnumber \n" +

	    // SUPP-28502... 15.01.2015 смена окладов
		" and f.pricegen = a2h.salary " +

		// " and dt.humenitemrefcode = h.code \n" +
	    " group by pi.code, tc.classificationtypecode, \n" +
	    " a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours , h.planrefcode, h.code /*NET-4570*/, a2h.actrefcode  , h.positiongencode \n" +

	    "  \n" +
	    " union all \n" +
	    "  \n" +
	    " select \n" +
	    "  null as  positiongencode , " + 
	    " picode, \n" +
	    "   ctype, \n" +
	    "    tabnumber, fio, salary, timemonth, daysmonth, salaryhours, \n" +
	    "    sum(workfact) as workfact, \n" +
	    "    sum(workgen) as workgen , \n" +
	    "    pitimegen , \n" +
	    "    humenkind \n" +

	    ///// 13.05.13 NET-4235 Отдельно вытянем время доставки
	    " , 0 as timedelivery \n " +
	    /////
	    " , clSalaryPercentBonus /*Премія*/ \n" + 
	    " , clSalaryPercentSurcharge  /*Доплата*/ \n" +  
	    " , clSalaryPercentPremium  /*Надбавка*/   \n" +
	    " , clSalaryPercentAdditional  /*Інші витрати*/ \n" +  
	    " , transportcode " + 
	    "    from ( \n" +
	    " select  \n" +
	    "  pi.code as picode, \n" +
	    "   tc.classificationtypecode as ctype, \n" +
	    "    a2h.tabnumber, a2h.fio, a2h.salary, a2h.timemonth, a2h.daysmonth, a2h.salaryhours, \n" +
	    "    case when t.tktransporttypecode = 1 then coalesce(dtp.deliverytimefact,0) else t.countworkfact end as workfact , \n" +
	    "    case when t.tktransporttypecode = 1 then coalesce(dtp.deliverytimeplan,0) else t.countworkgen end as workgen , \n" +
	    "    t.countworkfact as pitimegen , \n" +
	    "    2 as humenkind \n" +
	    "  , /*проценты доплаты берутся по истории на дату договора */ \n" +
	    " ( select thist.bonus from tktransporthistory thist  \n" +
	    "   where thist.tktransportcode  = t.transportcode \n" +
	    "  and (     select  pw.datestart  from enplancorrecthistory hhh  , enplanwork pw \n" +
	    " 		    where hhh.planoldrefcode = pw.code \n" +
	    " 		      and hhh.reasoncode = 13  \n" +
	    " 		      and  hhh.plannewrefcode in ( \n" +
	    " 				    select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    " 				    where hh.reasoncode = 4  \n" +
	    " 				      and hh.plannewrefcode in (  \n" +
	    " 					    select h.planoldrefcode from enplancorrecthistory h \n" +
	    " 					    where h.reasoncode = 5  \n" +
	    " 					      and  h.plannewrefcode = a2p.plancode \n" +
	    " 				        )  \n" +
	    "         ) \n" +
	    " 	 ) \n" + 
	    "    between thist.datefrom and coalesce(thist.dateto,'31.12.9999') \n" +
	    " order by thist.datefrom desc  \n" +
	    "     ) as clSalaryPercentBonus /*Премія*/ \n" +
	    " , ( select thist.salarySurcharge from tktransporthistory thist  \n" +
	    "   where thist.tktransportcode  = t.transportcode  \n" +
	    "  and (     select  pw.datestart  from enplancorrecthistory hhh  , enplanwork pw \n" +
	    " 		    where hhh.planoldrefcode = pw.code \n" +
	    " 		      and hhh.reasoncode = 13  \n" +
	    " 		      and  hhh.plannewrefcode in ( \n" +
	    " 				    select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    " 				    where hh.reasoncode = 4  \n" +
	    " 				      and hh.plannewrefcode in (  \n" +
	    " 					    select h.planoldrefcode from enplancorrecthistory h \n" +
	    " 					    where h.reasoncode = 5  \n" +
	    " 					      and  h.plannewrefcode = a2p.plancode \n" +
	    " 				        )  \n" +
	    "         ) \n" +
	    " 	 ) \n" + 
	    "    between thist.datefrom and coalesce(thist.dateto,'31.12.9999') \n" +
	    " order by thist.datefrom desc \n" +
	    "     ) as clsalaryPercentSurcharge /*Доплата*/ \n" +    
	    " , ( select thist.salaryPremium from tktransporthistory thist \n" +
	    "   where thist.tktransportcode  = t.transportcode \n" +
	    "  and (     select  pw.datestart  from enplancorrecthistory hhh  , enplanwork pw \n" +
	    " 		    where hhh.planoldrefcode = pw.code \n" +
	    " 		      and hhh.reasoncode = 13  \n" +
	    " 		      and  hhh.plannewrefcode in ( \n" +
	    " 				    select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    " 				    where hh.reasoncode = 4  \n" +
	    " 				      and hh.plannewrefcode in (  \n" +
	    " 					    select h.planoldrefcode from enplancorrecthistory h \n" +
	    " 					    where h.reasoncode = 5  \n" +
	    " 					      and  h.plannewrefcode = a2p.plancode \n" +
	    " 				        )  \n" +
	    "         ) \n" +
	    " 	 ) \n" + 
	    "     between thist.datefrom and coalesce(thist.dateto,'31.12.9999') \n" +
	    " order by thist.datefrom desc \n" +
	    "     ) as clSalaryPercentPremium /*Надбавка*/ \n" +
	    " , ( select thist.salaryAdditional from tktransporthistory thist  \n" +
	    "   where thist.tktransportcode  = t.transportcode  \n" +
	    "  and (     select  pw.datestart  from enplancorrecthistory hhh  , enplanwork pw \n" +
	    " 		    where hhh.planoldrefcode = pw.code \n" +
	    " 		      and hhh.reasoncode = 13  \n" +
	    " 		      and  hhh.plannewrefcode in ( \n" +
	    " 				    select hh.planoldrefcode from enplancorrecthistory hh \n" +
	    " 				    where hh.reasoncode = 4  \n" +
	    " 				      and hh.plannewrefcode in (  \n" +
	    " 					    select h.planoldrefcode from enplancorrecthistory h \n" +
	    " 					    where h.reasoncode = 5  \n" +
	    " 					      and  h.plannewrefcode = a2p.plancode \n" +
	    " 				        )  \n" +
	    "         ) \n" +
	    " 	 ) \n" + 
	    "     between thist.datefrom and coalesce(thist.dateto,'31.12.9999') \n" +
	    " order by thist.datefrom desc  \n" +
	    "     ) as clSalaryPercentAdditional /*Інші витрати*/ \n" +
	    " , t.transportcode as transportcode " + 
	    "    from \n" +
	    "    enact2enplanwork a2p left join endeliverytimeplan dtp on a2p.plancode = dtp.planworkrefcode, enplan2humen p2h, \n" +
	    "    enact2humen a2h  , \n" +
	    "    enplanworkitem pi, tktechcard tc, \n" +
	    "    entransportitem t , finworker f \n" +
	    "    where \n" +
	    "    a2p.actrefcode = ? \n" +
	    "    and p2h.humenkindrefcode = 2 \n" +
	    "    and a2h.humenkindrefcode = 2 \n" +
	    "    and a2p.plancode = p2h.planrefcode \n" +
	    "    and a2h.actrefcode = a2p.actrefcode \n" +
	    "    and pi.planrefcode = a2p.plancode \n" +
	    "    and pi.countgen > 0 and pi.kartarefcode = tc.code \n" +
	    "    and t.planitemrefcode = pi.code \n" +
	    "    and t.planrefcode = a2p.plancode \n" +
	    "    and f.code = t.finworkercode \n" +

	    // SUPP-28502... 15.01.2015 смена окладов
		"    and f.pricegen = a2h.salary " +

	    "    and p2h.tabnumber = f.tabnumber \n" +
	    "    and a2h.tabnumber = f.tabnumber \n" +
	    "     ) sel1 \n" +
	    "    group by \n" +
	    "    picode, \n" +
	    "    ctype, \n" +
	    "    tabnumber, fio, salary, timemonth, daysmonth, salaryhours, \n" +
	    "    pitimegen , \n" +
	    "    humenkind \n" +
	    "    , clSalaryPercentBonus /*Премія*/ \n" +
	    "    , clSalaryPercentSurcharge  /*Доплата*/   \n" +
	    "    , clSalaryPercentPremium  /*Надбавка*/   \n" +
	    "    , clSalaryPercentAdditional  /*Інші витрати*/   \n" +
	    " , transportcode " + 
	    " ) dat \n" + 
	    " order by dat.tabnumber , dat.picode, dat.humenkind \n" ;


    try
     {
       statement = connection.prepareStatement(selectStr);
      statement.setInt(1, actCode );
      statement.setInt(2, actCode);


      set = statement.executeQuery();
      int i;
      for(i = 0;set.next();i++)
       {

        anObject = new ENPlanWorkItem2HumenShort();

        anObject.plaItemRefCode = set.getInt(1);
		if(set.wasNull())
		   anObject.plaItemRefCode = Integer.MIN_VALUE;

        anObject.classificationTypeRefCode = set.getInt(2);
		if(set.wasNull())
		   anObject.classificationTypeRefCode = Integer.MIN_VALUE;

        anObject.tabNumber = set.getString(3);
        anObject.fio = set.getString(4);
        anObject.salary = set.getBigDecimal(5);
        if(anObject.salary != null)
            anObject.salary = anObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.timeMonth = set.getBigDecimal(6);
        if(anObject.timeMonth != null)
            anObject.timeMonth = anObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.daysMonth = set.getBigDecimal(7);
        if(anObject.daysMonth != null)
            anObject.daysMonth = anObject.daysMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);


        anObject.salaryHours = set.getBigDecimal(8);
        if(anObject.salaryHours != null)
            anObject.salaryHours = anObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);


        anObject.timeWorkFact = set.getBigDecimal(9);
        if(anObject.timeWorkFact != null)
            anObject.timeWorkFact = anObject.timeWorkFact.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.timeWork = set.getBigDecimal(10);
        if(anObject.timeWork != null)
            anObject.timeWork = anObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);


        anObject.timeObjectWork = set.getBigDecimal(11);
        if(anObject.timeObjectWork != null)
            anObject.timeObjectWork = anObject.timeObjectWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.humenKindRefCode = set.getInt(12);
		if(set.wasNull())
		   anObject.humenKindRefCode = Integer.MIN_VALUE;

		///// 13.05.13 NET-4235 Отдельно вытянем время и стоимость доставки
        anObject.timeDelivery = set.getBigDecimal(13);
        if(anObject.timeDelivery != null)
            anObject.timeDelivery = anObject.timeDelivery.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.costDelivery = set.getBigDecimal(14);
        if(anObject.costDelivery != null)
            anObject.costDelivery = anObject.costDelivery.setScale(6,java.math.BigDecimal.ROUND_HALF_UP); // 6 знаков для точности!!!
        /////
        
        anObject.bonus = set.getBigDecimal(15);
        if(anObject.bonus != null)
            anObject.bonus = anObject.bonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        
        anObject.percentSurcharge = set.getBigDecimal(16);
        if(anObject.percentSurcharge != null)
            anObject.percentSurcharge = anObject.percentSurcharge.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        
        anObject.percentPremium = set.getBigDecimal(17);
        if(anObject.percentPremium != null)
            anObject.percentPremium = anObject.percentPremium.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        
        anObject.percentAdditional = set.getBigDecimal(18);
        if(anObject.percentAdditional != null)
            anObject.percentAdditional = anObject.percentAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        
        
        anObject.positionRefCode = set.getInt(19);
		if(set.wasNull())
		   anObject.positionRefCode = Integer.MIN_VALUE;
		
		anObject.transportCode = set.getInt(20);
		if(set.wasNull())
		   anObject.transportCode = Integer.MIN_VALUE;

         result.list.add(anObject);
       }

      result.setTotalCount(i);
      return result;
     }
    catch(SQLException e)
     {
      System.out.println(e.getMessage()+"\nstatement - "+selectStr);
      EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
      return null;
     }
    finally
     {
      try {if (set != null) set.close();}             catch (SQLException e) {}
      try {if (statement != null) statement.close();} catch (SQLException e) {}
      if(connection != super.getConnection())
       {
        try{connection.close();} catch(SQLException e){}
       }
     }
   }

  @Override
public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2Humen aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector<? extends Object> aBindObjects) throws PersistenceException
  {
   ENPlanWorkItem2HumenShortList result = new ENPlanWorkItem2HumenShortList();
   ENPlanWorkItem2HumenShort anObject;
   result.list = new Vector<ENPlanWorkItem2HumenShort>();

   String     selectStr;
   Connection connection = getConnection();
   PreparedStatement statement = null;
   ResultSet  set = null;
   String     whereStr = "";
   String     condition = processCondition(anCondition);
   String     orderBy = processCondition(anOrderBy);

   if(orderBy.length() == 0)
    orderBy = "ENPLANWORKITEM2HUMEN.CODE";

   if(quantity < 0)
    quantity = Integer.MAX_VALUE/2;

   if(getUserProfile() == null)
    throw new PersistenceException("Internal Error (User Profile Is Undefined)");

   selectStr = "SELECT "+
    "ENPLANWORKITEM2HUMEN.CODE"+
    ",ENPLANWORKITEM2HUMEN.OREDERNUM"+
    ",ENPLANWORKITEM2HUMEN.TABNUMBER"+
    ",ENPLANWORKITEM2HUMEN.FIO"+
    ",ENPLANWORKITEM2HUMEN.SALARY"+
    ",ENPLANWORKITEM2HUMEN.TIMEMONTH"+
    ",ENPLANWORKITEM2HUMEN.DAYSMONTH"+
    ",ENPLANWORKITEM2HUMEN.SALARYHOURS"+
    ",ENPLANWORKITEM2HUMEN.TIMEWORK"+
    ",ENPLANWORKITEM2HUMEN.TIMEOBJECTWORK"+ // 10 
    ",ENPLANWORKITEM2HUMEN.TIMEWORKFACT"+
    ",ENPLANWORKITEM2HUMEN.TIMEDELIVERY"+
    ",ENPLANWORKITEM2HUMEN.PAYSWORK"+
    ",ENPLANWORKITEM2HUMEN.BONUS"+
    ",ENPLANWORKITEM2HUMEN.SALARYHOURSBONUS"+
    ",ENPLANWORKITEM2HUMEN.PAYSWORKBONUS"+
    ",ENPLANWORKITEM2HUMEN.CHARGEPERCENT"+
    ",ENPLANWORKITEM2HUMEN.CHARGESUM"+
    ",ENPLANWORKITEM2HUMEN.BALANS"+
    ",ENPLANWORKITEM2HUMEN.PODRCOD"+ // 20 
    ",ENPLANWORKITEM2HUMEN.COSTDELIVERY"+
    ",ENPLANWORKITEM2HUMEN.GENERALEXPENSES"+ 

     ", ENHUMENITEMKIND.CODE " +
     ", ENHUMENITEMKIND.NAME " +
     ", ENPLANWORKITEM.CODE " +
     ", ENPLANWORKITEM.COUNTGEN " +
     ", ENPLANWORKITEM.TIMEGEN " +
     ", ENPLANWORKITEM.COSTGEN " +
     ", ENPLANWORKITEM.USERGEN " +
     ", ENPLANWORKITEM.DATEEDIT " + // 30 
     ", TKCLASSIFICATIONTYPE.CODE " +
     ", TKCLASSIFICATIONTYPE.NAME " +
     ", TKCLASSIFICATIONTYPE.KOD " +

     ", ENPLANWORKITEM2HUMEN.CHARGEREFCODE " +
     ", (select FINCHARGETYPE.NAME from FINCHARGETYPE where FINCHARGETYPE.CODE = ENPLANWORKITEM2HUMEN.CHARGEREFCODE) " +

     ",ENPLANWORKITEM2HUMEN.CEHID"+ // 36  
     
		",ENPLANWORKITEM2HUMEN.PERCENTSURCHARGE"+
		",ENPLANWORKITEM2HUMEN.SALARYHOURSSURCHARGE"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKSURCHARGE"+
		",ENPLANWORKITEM2HUMEN.PERCENTPREMIUM"+
		",ENPLANWORKITEM2HUMEN.SALARYHOURSPREMIUM"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUM"+
		",ENPLANWORKITEM2HUMEN.PERCENTADDITIONAL"+
		",ENPLANWORKITEM2HUMEN.SALARYHOURSADDITIONAL"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKADDITIONAL"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKWITHALLSURCHRG"+ // 46
		",ENPLANWORKITEM2HUMEN.POSITIONREFCODE"+  // 47 
		",ENPLANWORKITEM2HUMEN.transportcode"+    // 48
		

		",ENPLANWORKITEM2HUMEN.PSWRKWTHLLSRCHRGWTHTDL"+ 
		",ENPLANWORKITEM2HUMEN.PAYSWORKBONUSWITHOTDLV"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKSURCHRGWTHTDLV"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUMWTHTDLV"+
		",ENPLANWORKITEM2HUMEN.PAYSWORKADDITNLWTHTDLV"+
		",ENPLANWORKITEM2HUMEN.CHARGESUMWITHOUTDELIV" +
		",ENPLANWORKITEM2HUMEN.ADMINISTRATIVEEXPENSES " +
		
    " FROM ENPLANWORKITEM2HUMEN " +
    ", ENHUMENITEMKIND " +
    ", ENPLANWORKITEM " +
    ", TKCLASSIFICATIONTYPE " +
    //", FINCHARGETYPE " +
    //" WHERE "
 "";
    whereStr = " ENHUMENITEMKIND.CODE = ENPLANWORKITEM2HUMEN.HUMENKINDREFCODE" ; //+
     whereStr = whereStr +" AND ENPLANWORKITEM.CODE = ENPLANWORKITEM2HUMEN.PLAITEMREFCODE" ; //+
     whereStr = whereStr +" AND TKCLASSIFICATIONTYPE.CODE = ENPLANWORKITEM2HUMEN.CLASSIFICATIONTYPERFCD" ; //+
     //whereStr = whereStr +" AND FINCHARGETYPE.CODE = ENPLANWORKITEM2HUMEN.CHARGEREFCODE" ; //+
   //selectStr = selectStr + " ${s} ENPLANWORKITEM2HUMEN.CODE IN ( SELECT ENPLANWORKITEM2HUMEN.CODE FROM ENPLANWORKITEM2HUMEN ";

//" ";
     if(aFilterObject != null)
     {
       if(aFilterObject.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.CODE = ?";
       }
       if(aFilterObject.orederNum != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.OREDERNUM = ?";
       }
        if (aFilterObject.tabNumber != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
            if (aFilterObject.tabNumber.indexOf('*',0) < 0 && aFilterObject.tabNumber.indexOf('?',0) < 0)
                whereStr = whereStr + "  UPPER(ENPLANWORKITEM2HUMEN.TABNUMBER) = UPPER(?)";
            else
                whereStr = whereStr + " UPPER(ENPLANWORKITEM2HUMEN.TABNUMBER) LIKE UPPER(?)";
        }
        if (aFilterObject.fio != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
            if (aFilterObject.fio.indexOf('*',0) < 0 && aFilterObject.fio.indexOf('?',0) < 0)
                whereStr = whereStr + "  UPPER(ENPLANWORKITEM2HUMEN.FIO) = UPPER(?)";
            else
                whereStr = whereStr + " UPPER(ENPLANWORKITEM2HUMEN.FIO) LIKE UPPER(?)";
        }
       if(aFilterObject.salary != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.SALARY = ?";
       }
       if(aFilterObject.timeMonth != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.TIMEMONTH = ?";
       }
       if(aFilterObject.daysMonth != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.DAYSMONTH = ?";
       }
       if(aFilterObject.salaryHours != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.SALARYHOURS = ?";
       }
       if(aFilterObject.timeWork != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.TIMEWORK = ?";
       }
       if(aFilterObject.timeObjectWork != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.TIMEOBJECTWORK = ?";
       }
       if(aFilterObject.timeWorkFact != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.TIMEWORKFACT = ?";
       }
       if(aFilterObject.timeDelivery != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.TIMEDELIVERY = ?";
       }
       if(aFilterObject.paysWork != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.PAYSWORK = ?";
       }
       if(aFilterObject.bonus != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.BONUS = ?";
       }
       if(aFilterObject.salaryHoursBonus != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.SALARYHOURSBONUS = ?";
       }
       if(aFilterObject.paysWorkBonus != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.PAYSWORKBONUS = ?";
       }
       if(aFilterObject.chargePercent != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.CHARGEPERCENT = ?";
       }
       if(aFilterObject.chargeSum != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.CHARGESUM = ?";
       }
        if (aFilterObject.balans != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
            if (aFilterObject.balans.indexOf('*',0) < 0 && aFilterObject.balans.indexOf('?',0) < 0)
                whereStr = whereStr + "  UPPER(ENPLANWORKITEM2HUMEN.BALANS) = UPPER(?)";
            else
                whereStr = whereStr + " UPPER(ENPLANWORKITEM2HUMEN.BALANS) LIKE UPPER(?)";
        }
        if (aFilterObject.podrcod != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
            if (aFilterObject.podrcod.indexOf('*',0) < 0 && aFilterObject.podrcod.indexOf('?',0) < 0)
                whereStr = whereStr + "  UPPER(ENPLANWORKITEM2HUMEN.PODRCOD) = UPPER(?)";
            else
                whereStr = whereStr + " UPPER(ENPLANWORKITEM2HUMEN.PODRCOD) LIKE UPPER(?)";
        }
       if(aFilterObject.costDelivery != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.COSTDELIVERY = ?";
       }
       if(aFilterObject.generalExpenses != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.GENERALEXPENSES = ?";
       }
       if(aFilterObject.modify_time != Long.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM2HUMEN.MODIFY_TIME = ?";
       }
       if(aFilterObject.humenKindRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM2HUMEN.HUMENKINDREFCODE = ? ";
       }
       if(aFilterObject.plaItemRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM2HUMEN.PLAITEMREFCODE = ? ";
       }
       if(aFilterObject.classificationTypeRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM2HUMEN.CLASSIFICATIONTYPERFCD = ? ";
       }
       if(aFilterObject.chargeRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM2HUMEN.CHARGEREFCODE = ? ";
       }

     }



     if(condition.length() != 0)
     {
        if(whereStr.length() != 0)
           whereStr = whereStr + " AND ";

        whereStr = whereStr + " (" + condition + ")";
     }
//+ " WHERE" +  сделано выше ????
    if(whereStr.length() != 0)
        selectStr = selectStr + " WHERE " + whereStr;

   // selectStr = selectStr + ") ";

   selectStr = selectStr + " ORDER BY " + orderBy;

   selectStr = selectStr + " OFFSET " + fromPosition;
   if (quantity>-1) selectStr = selectStr + " LIMIT " + quantity;

   try
    {
     statement = connection.prepareStatement(selectStr);
     int number = 0;
     if(aFilterObject != null){
        if(aFilterObject.code != Integer.MIN_VALUE){
            number++;
            statement.setInt(number,aFilterObject.code);
        }
        if(aFilterObject.orederNum != Integer.MIN_VALUE){
            number++;
            statement.setInt(number,aFilterObject.orederNum);
        }

          if(aFilterObject.tabNumber != null){
              number++;
              StringBuffer likeStr = new StringBuffer();
              likeStr.append(aFilterObject.tabNumber);
              for(int i = 0;i < likeStr.length();i++){
                   if(likeStr.charAt(i) == '*')
                        likeStr.setCharAt(i,'%');
                   if(likeStr.charAt(i) == '?')
                        likeStr.setCharAt(i,'_');
              }
              statement.setString(number,likeStr.toString());
          }

          if(aFilterObject.fio != null){
              number++;
              StringBuffer likeStr = new StringBuffer();
              likeStr.append(aFilterObject.fio);
              for(int i = 0;i < likeStr.length();i++){
                   if(likeStr.charAt(i) == '*')
                        likeStr.setCharAt(i,'%');
                   if(likeStr.charAt(i) == '?')
                        likeStr.setCharAt(i,'_');
              }
              statement.setString(number,likeStr.toString());
          }
       if(aFilterObject.salary != null){
           number++;
           aFilterObject.salary = aFilterObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.salary);
       }
       if(aFilterObject.timeMonth != null){
           number++;
           aFilterObject.timeMonth = aFilterObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.timeMonth);
       }
       if(aFilterObject.daysMonth != null){
           number++;
           aFilterObject.daysMonth = aFilterObject.daysMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.daysMonth);
       }
       if(aFilterObject.salaryHours != null){
           number++;
           aFilterObject.salaryHours = aFilterObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.salaryHours);
       }
       if(aFilterObject.timeWork != null){
           number++;
           aFilterObject.timeWork = aFilterObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.timeWork);
       }
       if(aFilterObject.timeObjectWork != null){
           number++;
           aFilterObject.timeObjectWork = aFilterObject.timeObjectWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.timeObjectWork);
       }
       if(aFilterObject.timeWorkFact != null){
           number++;
           aFilterObject.timeWorkFact = aFilterObject.timeWorkFact.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.timeWorkFact);
       }
       if(aFilterObject.timeDelivery != null){
           number++;
           aFilterObject.timeDelivery = aFilterObject.timeDelivery.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.timeDelivery);
       }
       if(aFilterObject.paysWork != null){
           number++;
           aFilterObject.paysWork = aFilterObject.paysWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.paysWork);
       }
       if(aFilterObject.bonus != null){
           number++;
           aFilterObject.bonus = aFilterObject.bonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.bonus);
       }
       if(aFilterObject.salaryHoursBonus != null){
           number++;
           aFilterObject.salaryHoursBonus = aFilterObject.salaryHoursBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.salaryHoursBonus);
       }
       if(aFilterObject.paysWorkBonus != null){
           number++;
           aFilterObject.paysWorkBonus = aFilterObject.paysWorkBonus.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.paysWorkBonus);
       }
       if(aFilterObject.chargePercent != null){
           number++;
           aFilterObject.chargePercent = aFilterObject.chargePercent.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.chargePercent);
       }
       if(aFilterObject.chargeSum != null){
           number++;
           aFilterObject.chargeSum = aFilterObject.chargeSum.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.chargeSum);
       }

          if(aFilterObject.balans != null){
              number++;
              StringBuffer likeStr = new StringBuffer();
              likeStr.append(aFilterObject.balans);
              for(int i = 0;i < likeStr.length();i++){
                   if(likeStr.charAt(i) == '*')
                        likeStr.setCharAt(i,'%');
                   if(likeStr.charAt(i) == '?')
                        likeStr.setCharAt(i,'_');
              }
              statement.setString(number,likeStr.toString());
          }

          if(aFilterObject.podrcod != null){
              number++;
              StringBuffer likeStr = new StringBuffer();
              likeStr.append(aFilterObject.podrcod);
              for(int i = 0;i < likeStr.length();i++){
                   if(likeStr.charAt(i) == '*')
                        likeStr.setCharAt(i,'%');
                   if(likeStr.charAt(i) == '?')
                        likeStr.setCharAt(i,'_');
              }
              statement.setString(number,likeStr.toString());
          }
       if(aFilterObject.costDelivery != null){
           number++;
           aFilterObject.costDelivery = aFilterObject.costDelivery.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.costDelivery);
       }
       if(aFilterObject.generalExpenses != null){
           number++;
           aFilterObject.generalExpenses = aFilterObject.generalExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
           statement.setBigDecimal(number,aFilterObject.generalExpenses);
       }
       if(aFilterObject.modify_time != Long.MIN_VALUE){
           number++;
           if(aFilterObject.modify_time == Long.MIN_VALUE)
               statement.setBigDecimal(number,null);
           else
               statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
       }
      if(aFilterObject.humenKindRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.humenKindRef.code);
      }
      if(aFilterObject.plaItemRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.plaItemRef.code);
      }
      if(aFilterObject.classificationTypeRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.classificationTypeRef.code);
      }
      if(aFilterObject.chargeRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.chargeRef.code);
      }
     }

     if(condition.length() > 0 && aBindObjects != null)
      _bindObjectsToPreparedStatement(statement,aBindObjects,number);

     set = statement.executeQuery();
     int i;
     for (i = 0; set.next(); i++) {
       /*
       if (i < fromPosition)
         continue;
       else if (i >= fromPosition + quantity) {
         i++;
         break;
       } */

       anObject = new ENPlanWorkItem2HumenShort();

       anObject.code = set.getInt(1);
       if ( set.wasNull() )
           anObject.code = Integer.MIN_VALUE;
       anObject.orederNum = set.getInt(2);
       if ( set.wasNull() )
           anObject.orederNum = Integer.MIN_VALUE;
       anObject.tabNumber = set.getString(3);
       anObject.fio = set.getString(4);
       anObject.salary = set.getBigDecimal(5);
       if(anObject.salary != null)
           anObject.salary = anObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.timeMonth = set.getBigDecimal(6);
       if(anObject.timeMonth != null)
           anObject.timeMonth = anObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.daysMonth = set.getBigDecimal(7);
       if(anObject.daysMonth != null)
           anObject.daysMonth = anObject.daysMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.salaryHours = set.getBigDecimal(8);
       if(anObject.salaryHours != null)
           anObject.salaryHours = anObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.timeWork = set.getBigDecimal(9);
       if(anObject.timeWork != null)
           anObject.timeWork = anObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.timeObjectWork = set.getBigDecimal(10);
       if(anObject.timeObjectWork != null)
           anObject.timeObjectWork = anObject.timeObjectWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.timeWorkFact = set.getBigDecimal(11);
       if(anObject.timeWorkFact != null)
           anObject.timeWorkFact = anObject.timeWorkFact.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.timeDelivery = set.getBigDecimal(12);
       if(anObject.timeDelivery != null)
           anObject.timeDelivery = anObject.timeDelivery.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.paysWork = set.getBigDecimal(13);
       if(anObject.paysWork != null)
           anObject.paysWork = anObject.paysWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.bonus = set.getBigDecimal(14);
       if(anObject.bonus != null)
           anObject.bonus = anObject.bonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.salaryHoursBonus = set.getBigDecimal(15);
       if(anObject.salaryHoursBonus != null)
           anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.paysWorkBonus = set.getBigDecimal(16);
       if(anObject.paysWorkBonus != null)
           anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.chargePercent = set.getBigDecimal(17);
       if(anObject.chargePercent != null)
           anObject.chargePercent = anObject.chargePercent.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.chargeSum = set.getBigDecimal(18);
       if(anObject.chargeSum != null)
           anObject.chargeSum = anObject.chargeSum.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.balans = set.getString(19);
       anObject.podrcod = set.getString(20);
       anObject.costDelivery = set.getBigDecimal(21);
       if(anObject.costDelivery != null)
           anObject.costDelivery = anObject.costDelivery.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.generalExpenses = set.getBigDecimal(22);
       if(anObject.generalExpenses != null)
           anObject.generalExpenses = anObject.generalExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);

       anObject.humenKindRefCode = set.getInt(23);
   if(set.wasNull())
     anObject.humenKindRefCode = Integer.MIN_VALUE;
       anObject.humenKindRefName = set.getString(24);
       anObject.plaItemRefCode = set.getInt(25);
   if(set.wasNull())
     anObject.plaItemRefCode = Integer.MIN_VALUE;
       anObject.plaItemRefCountGen = set.getBigDecimal(26);
       if(anObject.plaItemRefCountGen != null)
         anObject.plaItemRefCountGen = anObject.plaItemRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.plaItemRefTimeGen = set.getBigDecimal(27);
       if(anObject.plaItemRefTimeGen != null)
         anObject.plaItemRefTimeGen = anObject.plaItemRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.plaItemRefCostGen = set.getBigDecimal(28);
       if(anObject.plaItemRefCostGen != null)
         anObject.plaItemRefCostGen = anObject.plaItemRefCostGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
       anObject.plaItemRefUserGen = set.getString(29);
       anObject.plaItemRefDateEdit = set.getDate(30);
       anObject.classificationTypeRefCode = set.getInt(31);
   if(set.wasNull())
     anObject.classificationTypeRefCode = Integer.MIN_VALUE;
       anObject.classificationTypeRefName = set.getString(32);
       anObject.classificationTypeRefKod = set.getString(33);
       anObject.chargeRefCode = set.getInt(34);
   if(set.wasNull())
     anObject.chargeRefCode = Integer.MIN_VALUE;
       anObject.chargeRefName = set.getString(35);
       
       anObject.cehId = set.getString(36); 
       
       anObject.percentSurcharge = set.getBigDecimal(37);
		if(anObject.percentSurcharge != null) {
			anObject.percentSurcharge = anObject.percentSurcharge.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.salaryHoursSurcharge = set.getBigDecimal(38);
		if(anObject.salaryHoursSurcharge != null) {
			anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkSurcharge = set.getBigDecimal(39);
		if(anObject.paysWorkSurcharge != null) {
			anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.percentPremium = set.getBigDecimal(40);
		if(anObject.percentPremium != null) {
			anObject.percentPremium = anObject.percentPremium.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.salaryHoursPremium = set.getBigDecimal(41);
		if(anObject.salaryHoursPremium != null) {
			anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkPremium = set.getBigDecimal(42);
		if(anObject.paysWorkPremium != null) {
			anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.percentAdditional = set.getBigDecimal(43);
		if(anObject.percentAdditional != null) {
			anObject.percentAdditional = anObject.percentAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.salaryHoursAdditional = set.getBigDecimal(44);
		if(anObject.salaryHoursAdditional != null) {
			anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkAdditional = set.getBigDecimal(45);
		if(anObject.paysWorkAdditional != null) {
			anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkWithAllSurcharge = set.getBigDecimal(46);
		if(anObject.paysWorkWithAllSurcharge != null) {
			anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		
		anObject.positionRefCode = set.getInt(47);
		if(set.wasNull()) {
			anObject.positionRefCode = Integer.MIN_VALUE;
		}
		
		anObject.transportCode = set.getInt(48);
		if(set.wasNull()) {
			anObject.transportCode = Integer.MIN_VALUE;
		}
		
		
		anObject.paysWorkWithAllSurchargeWithoutDeliv = set.getBigDecimal(49);
		if(anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
			anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkBonusWithoutDeliv = set.getBigDecimal(50);
		if(anObject.paysWorkBonusWithoutDeliv != null) {
			anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkSurchargeWithoutDeliv = set.getBigDecimal(51);
		if(anObject.paysWorkSurchargeWithoutDeliv != null) {
			anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkPremiumWithoutDeliv = set.getBigDecimal(52);
		if(anObject.paysWorkPremiumWithoutDeliv != null) {
			anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.paysWorkAdditionalWithoutDeliv = set.getBigDecimal(53);
		if(anObject.paysWorkAdditionalWithoutDeliv != null) {
			anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.chargeSumWithoutDeliv = set.getBigDecimal(54);
		if(anObject.chargeSumWithoutDeliv != null) {
			anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		anObject.administrativeExpenses = set.getBigDecimal(55);
		if(anObject.administrativeExpenses != null) {
			anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
		}
		
		
       

        result.list.add(anObject);
      }

     result.setTotalCount(i);
     return result;
    }
   catch(SQLException e)
    {
     System.out.println(e.getMessage()+"\nstatement - "+selectStr);
     throw new SystemException(e.getMessage(), e);
     //return null;
    }
   finally
    {
     try {if (set != null) set.close();}             catch (SQLException e) {}
     try {if (statement != null) statement.close();} catch (SQLException e) {}
     if(connection != super.getConnection())
      {
       try{connection.close();} catch(SQLException e){}
      }
    }
  }

  public ENPlanWorkItem2HumenShortList getSalaryChargesList(int actCode) throws PersistenceException
  {
	   ENPlanWorkItem2HumenShortList result = new ENPlanWorkItem2HumenShortList();
	   ENPlanWorkItem2HumenShort anObject;
	   result.list = new Vector<ENPlanWorkItem2HumenShort>();

	   String     selectStr;
	   Connection connection = getConnection();
	   PreparedStatement statement = null;
	   ResultSet  set = null;


	   if(getUserProfile() == null)
		   throw new PersistenceException("Internal Error (User Profile Is Undefined)");


	   selectStr =
		   "  select " +
		   "      p2h.code, " +
		   "      p2h.tabnumber, " +
		   "      p2h.fio, " +
		   "      cast(p2h.salary as numeric), " +
		   "      cast(p2h.timemonth as numeric), " +
		   "      cast(p2h.salaryhours as numeric) as salaryhours, " +
		   "      cast(p2h.timework as numeric) as timework, " +
		   "      cast(p2h.paysworkbonus as numeric) as payswork, " +

		   "      cast(p2h.generalexpenses as numeric(15,2)) as generalexpenses, " +

		   "      (select fww.chargepercent from finworker fww " +
		   "           where fww.code in (select case when p2h.humenkindrefcode  = 1 /*если електротехнич персонал*/ " +
		   "                            then ( " +
		   "                             select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw " +
		   "                              where a2p1.actrefcode = a2p.actrefcode " +
		   "                                and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
		   "                                and a2p1.plancode = hum.planrefcode " +
		   "                                and hum.countfact <> 0 " +
		   "                                and hum.finworkercode = fw.code ) " +
		   "                            else ( " +
		   "                             select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw " +
		   "                              where a2p1.actrefcode = a2p.actrefcode " +
		   "                                and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
		   "                                and a2p1.plancode = aut.planrefcode " +
		   "                                 " +
		   "                                and aut.finworkercode = fw.code ) end " +
		   "                             ) " +
		   "          ) as chargepercent, " +
		   "          (select fww.chargerefcode from finworker fww " +
		   "           where fww.code in (select case when p2h.humenkindrefcode = 1 /*если електротехнич персонал*/ " +
		   "                            then ( " +
		   "                             select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw " +
		   "                              where a2p1.actrefcode = a2p.actrefcode " +
		   "                                and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
		   "                                and a2p1.plancode = hum.planrefcode " +
		   "                                and hum.countfact <> 0 " +
		   "                                and hum.finworkercode = fw.code ) " +
		   "                            else ( " +
		   "                             select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw " +
		   "                              where a2p1.actrefcode = a2p.actrefcode " +
		   "                                and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
		   "                                and a2p1.plancode = aut.planrefcode " +
		   "   " +
		   "                                and aut.finworkercode = fw.code ) end " +
		   "                             ) " +
		   "          ) as chargerefcode, " +
		   "      p2h.humenkindrefcode, " +

		   " (select fww.departmentcode from finworker fww " +
		   " where fww.code in (select case when p2h.humenkindrefcode  = 1 /*если електротехнич персонал*/ " +
           "              then (  " +
           "               select max(fw.code) from enact2enplanwork a2p1 , enhumenitem hum , finworker fw " +
           "                where a2p1.actrefcode = a2p.actrefcode  " +
           "                  and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
           "                  and a2p1.plancode = hum.planrefcode  " +
           "                  and hum.countfact <> 0  " +
           "                  and hum.finworkercode = fw.code ) " +
           "               else (  " +
           "               select max(fw.code) from enact2enplanwork a2p1 , entransportitem aut , finworker fw " +
           "                where a2p1.actrefcode = a2p.actrefcode  " +
           "                  and cast(fw.tabnumber as varchar) =  p2h.tabnumber " +
           "                  and a2p1.plancode = aut.planrefcode  " +
           "                  and aut.finworkercode = fw.code ) end  " +
           "               )  " +
           " ) as podrcode " +
           "    , cast(p2h.paysworksurcharge as numeric) as paysworksurcharge \n" +
           "    , cast(p2h.paysworkpremium as numeric) as paysworkpremium \n" +
           "    , cast(p2h.paysworkadditional as numeric) as paysworkadditional \n" +
           "    , cast(paysworkwithallsurchrg  as numeric) as paysworkwithallsurchrg   \n" +
           "    , cast(pswrkwthllsrchrgwthtdl  as numeric) as pswrkwthllsrchrgwthtdl \n" +
           "    , cast(paysworkbonuswithotdlv  as numeric) as  paysworkbonuswithotdlv \n" +
           "    , cast(paysworksurchrgwthtdlv  as numeric) as  paysworksurchrgwthtdlv \n" +
           "    , cast(paysworkpremiumwthtdlv  as numeric) as  paysworkpremiumwthtdlv \n" +
           "    , cast(paysworkadditnlwthtdlv  as numeric) as  paysworkadditnlwthtdlv \n" +
           "    , cast(chargesumwithoutdeliv as numeric) as chargesumwithoutdeliv \n" +
           "	, cast(p2h.administrativeexpenses as numeric(15,2)) as administrativeexpenses \n" +
		   "  from " +
		   "      enact2enplanwork a2p, " +
		   "      enplanwork pw, " +
		   "      enplanworkitem pwi, " +
		   "      enplanworkitem2humen p2h, " +
		   "      tktechcard as tc, " +
		   "      tkclassificationtype as tkcls " +
		   "  where  a2p.actrefcode = " + actCode +
		   "     and a2p.plancode = pw.code " +
		   "     and pw.code = pwi.planrefcode " +
		   "     and pwi.countgen <> 0 " +
		   "     and pwi.code = p2h.plaitemrefcode " +
		   "     and tc.code = pwi.kartarefcode " +
		   "     and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) " +
		   "     and tkcls.code = tc.classificationtypecode " +
		   "  order by p2h.code ";


	   try
	   {
		   statement = connection.prepareStatement(selectStr);

	       set = statement.executeQuery();

	       int i;

	       for (i = 0; set.next(); i++)
	       {
	    	   anObject = new ENPlanWorkItem2HumenShort();

		       anObject.code = set.getInt(1);
		       if(set.wasNull())
				   anObject.code = Integer.MIN_VALUE;

		       anObject.tabNumber = set.getString(2);
		       anObject.fio = set.getString(3);

		       anObject.salary = set.getBigDecimal(4);
		       if(anObject.salary != null)
		           anObject.salary = anObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

		       anObject.timeMonth = set.getBigDecimal(5);
		       if(anObject.timeMonth != null)
		           anObject.timeMonth = anObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

		       anObject.salaryHours = set.getBigDecimal(6);
		       if(anObject.salaryHours != null)
		           anObject.salaryHours = anObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

		       anObject.timeWork = set.getBigDecimal(7);
		       if(anObject.timeWork != null)
		           anObject.timeWork = anObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

		       anObject.paysWork = set.getBigDecimal(8);
		       if(anObject.paysWork != null)
		           anObject.paysWork = anObject.paysWork.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);

		       anObject.generalExpenses = set.getBigDecimal(9);
		       if(anObject.generalExpenses != null)
		           anObject.generalExpenses = anObject.generalExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);

			   /*
		       anObject.plaItemRefCode = set.getInt(1);
				if(set.wasNull())
				   anObject.plaItemRefCode = Integer.MIN_VALUE;

		       anObject.classificationTypeRefCode = set.getInt(2);
				if(set.wasNull())
				   anObject.classificationTypeRefCode = Integer.MIN_VALUE;
			   */

			   anObject.chargePercent = set.getBigDecimal(10);
			   if(anObject.chargePercent != null)
			       anObject.chargePercent = anObject.chargePercent.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

			   /*
			   anObject.chargeSum = set.getBigDecimal(18);
			   if(anObject.chargeSum != null)
			       anObject.chargeSum = anObject.chargeSum.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);

			   anObject.balans = set.getString(19);
			   anObject.podrcod = set.getString(20);
			   */

			   anObject.chargeRefCode = set.getInt(11);
			   if(set.wasNull())
				   anObject.chargeRefCode = Integer.MIN_VALUE;

		       anObject.humenKindRefCode = set.getInt(12);
			   if(set.wasNull())
				   anObject.humenKindRefCode = Integer.MIN_VALUE;

			   anObject.podrcod = set.getString(13);
			   
			   anObject.paysWorkSurcharge = set.getBigDecimal(14);
				if(anObject.paysWorkSurcharge != null) {
					anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				
				anObject.paysWorkPremium = set.getBigDecimal(15);
				if(anObject.paysWorkPremium != null) {
					anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				anObject.paysWorkAdditional = set.getBigDecimal(16);
				if(anObject.paysWorkAdditional != null) {
					anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				anObject.paysWorkWithAllSurcharge = set.getBigDecimal(17);
				if(anObject.paysWorkWithAllSurcharge != null) {
					anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				anObject.paysWorkWithAllSurchargeWithoutDeliv = set.getBigDecimal(18);
				if(anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
					anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				anObject.paysWorkBonusWithoutDeliv = set.getBigDecimal(19);
				if(anObject.paysWorkBonusWithoutDeliv != null) {
					anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkSurchargeWithoutDeliv = set.getBigDecimal(20);
				if(anObject.paysWorkSurchargeWithoutDeliv != null) {
					anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkPremiumWithoutDeliv = set.getBigDecimal(21);
				if(anObject.paysWorkPremiumWithoutDeliv != null) {
					anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkAdditionalWithoutDeliv = set.getBigDecimal(22);
				if(anObject.paysWorkAdditionalWithoutDeliv != null) {
					anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargeSumWithoutDeliv = set.getBigDecimal(23);
				if(anObject.chargeSumWithoutDeliv != null) {
					anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.administrativeExpenses = set.getBigDecimal(24);
				if(anObject.administrativeExpenses != null) {
					anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}

		       result.list.add(anObject);
	      }

	      result.setTotalCount(i);
	      return result;
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e.getMessage()+"\nstatement - "+selectStr);
		   EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
		   return null;
	   }
	   finally
	   {
		   try {if (set != null) set.close();}             catch (SQLException e) {}
	       try {if (statement != null) statement.close();} catch (SQLException e) {}
	       if(connection != super.getConnection())
	       {
	    	   try{connection.close();} catch(SQLException e){}
	       }
	   }
  }




} // end of ENPlanWorkItem2HumenDAO



//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2GraphDAOGen;
import com.ksoe.energynet.logic.mDaxLogic;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2GraphShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2GraphShortList;

/**
 * DAO Object for ENPlanWorkItem2Graph;
 *
 */

public class ENPlanWorkItem2GraphDAO extends ENPlanWorkItem2GraphDAOGen {

    public ENPlanWorkItem2GraphDAO(UserProfile anUserProfile,
            Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkItem2GraphDAO(Connection aConnection,
            UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }
    
 public ENPlanWorkItem2GraphShortList getScrollableFilteredListGraph(ENPlanWorkItem2Graph aFilterObject) 
				 throws PersistenceException {
		ENPlanWorkItem2GraphShortList result = new ENPlanWorkItem2GraphShortList();
		ENPlanWorkItem2GraphShort anObject;
		result.list = new Vector<ENPlanWorkItem2GraphShort>();
		
		
		mDaxLogic mdLogic = new mDaxLogic(getConnection(), getUserProfile());

		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet  set = null;
		String whereStr = "";
		String condition = "";
		String orderBy = "";

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORKITEM2GRAPH.CODE";
		}


		selectStr = "                                      with selParam as ( \n" +
				"        \n" +
				"       select "+ aFilterObject.month + " as monthGr , " + aFilterObject.year  + " as yearGr , last_day( to_date('01."+aFilterObject.month+"."+aFilterObject.year+"' , 'dd.mm.yyyy' ) ) as lastdayinmonth   \n" +
				"       )   \n" +
				"  select \n" +
				"      ENPLANWORKITEMCODE, \n" +
				"      techkartnumber, \n" +
				"      TKTECHCARDNAME, \n" +
				"      TKMEASUREMENTNAME, \n" +
				"      ENPLANWORKITEMCOUNTGEN,        \n" +
				"      TKTECHCARDNORMOFTIME, \n" +
				"      ENPLANWORKITEMTIMEGEN ,  \n" +
				"      coalesce( (select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) limit 1  ) , 0)::varchar  as d1 ,  \n" +
				"      coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 1 limit 1 ) , 0)::varchar  as d2, \n" +
				"      coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 2 limit 1 ) , 0)::varchar  as d3, \n" +
				"      coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 3 limit 1 ) , 0)::varchar  as d4, \n" +
				"      coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 4 limit 1 ) , 0)::varchar  as d5, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 5 limit 1 ) , 0)::varchar  as d6, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 6 limit 1 ) , 0)::varchar  as d7, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 7 limit 1 ) , 0)::varchar  as d8, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 8 limit 1 ) , 0)::varchar  as d9, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 9 limit 1 ) , 0)::varchar  as d10, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 10 limit 1 ) , 0)::varchar  as d11, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 11 limit 1 ) , 0)::varchar  as d12, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 12 limit 1 ) , 0)::varchar  as d13, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 13 limit 1 ) , 0)::varchar  as d14, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 14 limit 1 ) , 0)::varchar  as d15, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 15 limit 1 ) , 0)::varchar as d16, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 16 limit 1 ) , 0)::varchar  as d17, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 17 limit 1 ) , 0)::varchar  as d18, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 18 limit 1 ) , 0)::varchar  as d19, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 19 limit 1 ) , 0)::varchar  as d20, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 20 limit 1 ) , 0)::varchar  as d21, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 21 limit 1 ) , 0)::varchar  as d22, \n" +
				"      coalesce(( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 22 limit 1 ) , 0)::varchar  as d23, \n" +
				"    coalesce(  ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 23 limit 1 ) , 0)::varchar  as d24, \n" +
				"    coalesce(  ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 24 limit 1 ) , 0)::varchar  as d25, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 25 limit 1 ) , 0)::varchar  as d26, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 26 limit 1 ) , 0)::varchar as d27, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 27 limit 1 ) , 0)::varchar  as d28, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 28 limit 1 ) , 0)::varchar  as d29, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 29 limit 1 ) , 0)::varchar  as d30, \n" +
				"     coalesce( ( select sum(countgen)::numeric(15,3) from enplanworkitem2graph i2g \n" +
				"         where i2g.planworkitemrefcode = ENPLANWORKITEMCODE  \n" +
				"           and i2g.daywork =  to_date('01.'||( select monthGr from selParam )||'.'||( select yearGr from selParam ) , 'dd.mm.yyyy' ) + 30 limit 1 ) , 0)::varchar  as d31,  \n" ; 
		String selectStr2 = 
				"      ( select to_char(lastdayinmonth,'dd')::numeric from selParam limit 1 ) as lastdayinmonth ,  " + 
				" ( select ' Період виконання робіт по плану згідно графіка з ' || to_char( min(daywork), 'dd.mm.yyyy' ) ||' по '||  to_char( max(daywork), 'dd.mm.yyyy' ) " +   
				" 	       from enplanworkitem2graph ggg where ggg.planworkrefcode = ENPLANWORKCODE ) as periodenplanwork " +
				"   , possiblecountgen \n " +
				"  from (     \n" +
				"          select \n" +
				"              ENPLANWORKITEM.CODE as ENPLANWORKITEMCODE , \n" +
				"              TKTECHCARD.techkartnumber as techkartnumber , \n" +
				"              TKTECHCARD.name as TKTECHCARDNAME , \n" +
				"              TKMEASUREMENT.NAME as TKMEASUREMENTNAME , \n" +
				"              ENPLANWORKITEM.COUNTGEN as ENPLANWORKITEMCOUNTGEN , \n" +
				"              TKTECHCARD.NORMOFTIME as TKTECHCARDNORMOFTIME, \n" +
				"              ENPLANWORKITEM.TIMEGEN as ENPLANWORKITEMTIMEGEN ,  \n" +
				"              TKTECHCARD.CODE as enplanworkitemkartarefcode, \n" +
	            "              ENPLANWORKITEM.planrefcode as ENPLANWORKCODE \n" +
				"          from \n" +
				"              ENPLANWORKITEM, \n" +
				"              ENPLANWORK, \n" +
				"              TKTECHCARD, \n" +
				"              TKMEASUREMENT \n" +
				"          where \n" +
				"              ENPLANWORK.CODE = ENPLANWORKITEM.PLANREFCODE \n" +
				"              and TKTECHCARD.MEASUREMENTCODE = TKMEASUREMENT.CODE \n" +
				"              and TKTECHCARD.CODE = ENPLANWORKITEM.KARTAREFCODE \n" +
				"              and ENPLANWORKITEM.PLANREFCODE = "+ aFilterObject.planWorkRef.code +"  \n" +
				"              and ENPLANWORKITEM.countgen > 0  \n" +
				"  ) as selworks     \n" +
				"    \n" +
				"                                    , \n" +
				"    ( \n" +
				"          select kartarefcode ,  \n" +
				"          case when (monthPlan - factcountgen - npzcountgen) <= 0 then 0    else (monthPlan - factcountgen - npzcountgen) end as possiblecountgen  \n" +
				"          from ( select pi.kartarefcode, pi.countgen as monthPlan,   \n" +
				"          (select coalesce(sum(pi2.countgen), 0)  \n" +
				"          from enplanworkitem pi2, enplanwork pl  where pl.code = pi2.planrefcode  and pl.kindcode = 4 and pl.code in  \n" +
				"          (  select plannewrefcode from enplancorrecthistory where planrefcode = "+ aFilterObject.planWorkRef.code +" \n" +
				"          union select "+ aFilterObject.planWorkRef.code +" as planrefcode  \n" +
				"          union select planrefcode from enplancorrecthistory \n" +
				"          where plannewrefcode = "+ aFilterObject.planWorkRef.code +"  \n" +
				"          union select plannewrefcode from enplancorrecthistory \n" +
				"          where planrefcode in (  select planrefcode from enplancorrecthistory where plannewrefcode = "+ aFilterObject.planWorkRef.code +" ) )     \n" +
				"          and pi2.kartarefcode = pi.kartarefcode) as factcountgen,   \n" +
				"          (select coalesce(sum(pi3.countgen), 0) from enplanworkitem pi3, enplanwork pl   \n" +
				"          where pl.code = pi3.planrefcode  and pl.kindcode = 3 and pl.statuscode = 1 and pl.code in  \n" +
				"          (  select plannewrefcode from enplancorrecthistory where planrefcode = "+ aFilterObject.planWorkRef.code +" \n" +
				"          union select "+ aFilterObject.planWorkRef.code +" as planrefcode   \n" +
				"          union select planrefcode from enplancorrecthistory \n" +
				"          where plannewrefcode = "+ aFilterObject.planWorkRef.code +"  \n" +
				"          union select plannewrefcode from enplancorrecthistory  \n" +
				"          where planrefcode in (  select planrefcode from enplancorrecthistory where plannewrefcode = "+ aFilterObject.planWorkRef.code +" ) )   \n" +
				"          and pi3.kartarefcode = pi.kartarefcode ) as npzcountgen  from enplanworkitem pi where pi.planrefcode = "+ aFilterObject.planWorkRef.code +" ) m \n" +
				"  ) as m2 where selworks.enplanworkitemkartarefcode = m2.kartarefcode  \n "+      
				"  order by enplanworkitemkartarefcode ";
	
		
		int number = 0;
		try {
			statement = connection.prepareStatement(selectStr + selectStr2);
			//number = setParameters(aFilterObject, statement);

			int work1d = Integer.MIN_VALUE;  int work2d = Integer.MIN_VALUE;  int work3d = Integer.MIN_VALUE; 	int work4d = Integer.MIN_VALUE;  int work5d = Integer.MIN_VALUE;  int work6d = Integer.MIN_VALUE;
			int work7d = Integer.MIN_VALUE;  int work8d = Integer.MIN_VALUE;  int work9d = Integer.MIN_VALUE;   int work10d = Integer.MIN_VALUE; int work11d = Integer.MIN_VALUE; int work12d = Integer.MIN_VALUE;
			int work13d = Integer.MIN_VALUE;  int work14d = Integer.MIN_VALUE;  int work15d = Integer.MIN_VALUE;   int work16d = Integer.MIN_VALUE;  int work17d = Integer.MIN_VALUE;  int work18d = Integer.MIN_VALUE;
			int work19d = Integer.MIN_VALUE;  int work20d = Integer.MIN_VALUE;  int work21d = Integer.MIN_VALUE;   int work22d = Integer.MIN_VALUE;  int work23d = Integer.MIN_VALUE;  int work24d = Integer.MIN_VALUE;
			int work25d = Integer.MIN_VALUE;  int work26d = Integer.MIN_VALUE;  int work27d = Integer.MIN_VALUE;   int work28d = Integer.MIN_VALUE;  int work29d = Integer.MIN_VALUE;  int work30d = Integer.MIN_VALUE;
			int work31d = Integer.MIN_VALUE;


			set = statement.executeQuery();
			int i;
			for (i = 0; set.next(); i++) {
				anObject = new ENPlanWorkItem2GraphShort();
				
				anObject.planWorkItemRefCode = set.getInt(1);
				if(set.wasNull()) {
					anObject.planWorkItemRefCode = Integer.MIN_VALUE;
				}
			
				anObject.techkartNumber = set.getString(2);
				
				anObject.tkTechcardName = set.getString(3);
				
				anObject.tkMeasurementName = set.getString(4);
				
				anObject.planWorkItemRefCountGen = set.getBigDecimal(5);
				if(anObject.planWorkItemRefCountGen != null) {
					anObject.planWorkItemRefCountGen = anObject.planWorkItemRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				
				anObject.tktechcardnormoftime = set.getBigDecimal(6);
				if(anObject.tktechcardnormoftime != null) {
					anObject.tktechcardnormoftime = anObject.tktechcardnormoftime.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				
				anObject.planWorkItemRefTimeGen = set.getBigDecimal(7);
				if(anObject.planWorkItemRefTimeGen != null) {
					anObject.planWorkItemRefTimeGen = anObject.planWorkItemRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				
				
				
				if (i==0){
					work1d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 1) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 1));
					work2d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 2) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 2));
					work3d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 3) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 3));
					work4d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 4) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 4));
					work5d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 5) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 5));
					work6d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 6) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 6));
					work7d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 7) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 7));
					work8d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 8) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 8));
					work9d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 9) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 9));
					work10d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 10) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 10));
					work11d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 11) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 11));
					work12d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 12) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 12));
					work13d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 13) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 13));
					work14d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 14) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 14));
					work15d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 15) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 15));
					work16d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 16) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 16));
					work17d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 17) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 17));
					work18d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 18) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 18));
					work19d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 19) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 19));
					work20d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 20) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 20));
					work21d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 21) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 21));
					work22d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 22) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 22));
					work23d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 23) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 23));
					work24d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 24) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 24));
					work25d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 25) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 25));
					work26d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 26) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 26));
					work27d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 27) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 27));
					work28d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 28) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 28));
					
					if ( set.getInt(39) >= 29 ){
						work29d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 29) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 29));
					}
					
					if ( set.getInt(39) >= 30 ){
						work30d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 30) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 30));
					}
					
					if ( set.getInt(39) == 31 ){
						work31d = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(aFilterObject.year, aFilterObject.month, 31) , Tools.encodeDate(aFilterObject.year, aFilterObject.month, 31));
					}
					
				}
				
			/////////////////////////////////////////////////////
				anObject.d1 = set.getString(8);  
				// вых или праздник
				if(work1d==0){
					anObject.d1 = anObject.d1 + "X";
				}				 
				
				anObject.d2 = set.getString(9);				
				if(work2d==0){
					anObject.d2 = anObject.d2 + "X";
				}
				
				anObject.d3 = set.getString(10);
				if(work3d==0){
					anObject.d3 = anObject.d3 + "X";
				}
				
				anObject.d4 = set.getString(11);
				if(work4d==0){
					anObject.d4 = anObject.d4 + "X";
				}
				
				anObject.d5 = set.getString(12);
				if(work5d==0){
					anObject.d5 = anObject.d5 + "X";
				}
				
				anObject.d6 = set.getString(13);
				if(work6d==0){
					anObject.d6 = anObject.d6 + "X";
				}
				
				anObject.d7 = set.getString(14);
				if(work7d==0){
					anObject.d7 = anObject.d7 + "X";
				}
				
				anObject.d8 = set.getString(15);
				if(work8d==0){
					anObject.d8 = anObject.d8 + "X";
				}
				
				anObject.d9 = set.getString(16);
				if(work9d==0){
					anObject.d9 = anObject.d9 + "X";
				}
				
				anObject.d10 = set.getString(17);
				if(work10d==0){
					anObject.d10 = anObject.d10 + "X";
				}
				
				anObject.d11 = set.getString(18);
				if(work11d==0){
					anObject.d11 = anObject.d11 + "X";
				}
				
				anObject.d12 = set.getString(19);
				if(work12d==0){
					anObject.d12 = anObject.d12 + "X";
				}
				
				anObject.d13 = set.getString(20);
				if(work13d==0){
					anObject.d13 = anObject.d13 + "X";
				}
				
				anObject.d14 = set.getString(21);
				if(work14d==0){
					anObject.d14 = anObject.d14 + "X";
				}
				
				anObject.d15 = set.getString(22);
				if(work15d==0){
					anObject.d15 = anObject.d15 + "X";
				}
				
				anObject.d16 = set.getString(23);
				if(work16d==0){
					anObject.d16 = anObject.d16 + "X";
				}
				
				anObject.d17 = set.getString(24);
				if(work17d==0){
					anObject.d17 = anObject.d17 + "X";
				}
				
				anObject.d18 = set.getString(25);
				if(work18d==0){
					anObject.d18 = anObject.d18 + "X";
				}
				
				anObject.d19 = set.getString(26);
				if(work19d==0){
					anObject.d19 = anObject.d19 + "X";
				}
				
				anObject.d20 = set.getString(27);
				if(work20d==0){
					anObject.d20 = anObject.d20 + "X";
				}
				
				anObject.d21 = set.getString(28);
				if(work21d==0){
					anObject.d21 = anObject.d21 + "X";
				}
				
				anObject.d22 = set.getString(29);
				if(work22d==0){
					anObject.d22 = anObject.d22 + "X";
				}
				
				anObject.d23 = set.getString(30);
				if(work23d==0){
					anObject.d23 = anObject.d23 + "X";
				}
				
				anObject.d24 = set.getString(31);
				if(work24d==0){
					anObject.d24 = anObject.d24 + "X";
				}
				
				anObject.d25 = set.getString(32);
				if(work25d==0){
					anObject.d25 = anObject.d25 + "X";
				}
				
				anObject.d26 = set.getString(33);
				if(work26d==0){
					anObject.d26 = anObject.d26 + "X";
				}
				
				anObject.d27 = set.getString(34);
				if(work27d==0){
					anObject.d27 = anObject.d27 + "X";
				}
				
				anObject.d28 = set.getString(35);
				if(work28d==0){
					anObject.d28 = anObject.d28 + "X";
				}
				
				anObject.d29 = set.getString(36);
				if(work29d==0){
					anObject.d29 = anObject.d29 + "X";
				}
				
				anObject.d30 = set.getString(37);
				if(work30d==0){
					anObject.d30 = anObject.d30 + "X";
				}
				
				anObject.d31 = set.getString(38);
				if(work31d==0){
					anObject.d31 = anObject.d31 + "X";
				}
				
				
				anObject.lastdayinmonth = set.getInt(39);
				if(set.wasNull()) {
					anObject.lastdayinmonth = Integer.MIN_VALUE;
				}
				
				anObject.periodenplanwork = set.getString(40);
				
				anObject.possiblecountgen = set.getBigDecimal(41);
				if(anObject.possiblecountgen != null) {
					anObject.possiblecountgen = anObject.possiblecountgen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}

				result.list.add(anObject);
			}

			result.setTotalCount(i);
			return result;
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();}             catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}
 
 public void removeGrafikByPlanworkAndMonth( ENPlanWorkItem2GraphShort[] pi2grList  ) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		
		selectStr = " DELETE FROM  ENPLANWORKITEM2GRAPH WHERE planworkrefcode = "  + pi2grList[0].planWorkRefCode + 
		 " and daywork between first_day('" + new SimpleDateFormat("dd.MM.yyyy").format(pi2grList[0].dayWork) + "') and last_day('" + new SimpleDateFormat("dd.MM.yyyy").format(pi2grList[0].dayWork) + "' ) ";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}



		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(selectStr);

			statement.execute();
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}
 
 
 public ENPlanWorkItem2GraphShortList getGroupListGraphByDays( int planWorkRefCode ) 
		 throws PersistenceException {
ENPlanWorkItem2GraphShortList result = new ENPlanWorkItem2GraphShortList();
ENPlanWorkItem2GraphShort anObject;
result.list = new Vector<ENPlanWorkItem2GraphShort>();


mDaxLogic mdLogic = new mDaxLogic(getConnection(), getUserProfile());

String selectStr;
Connection connection = getConnection();
PreparedStatement statement = null;
ResultSet  set = null;
String whereStr = "";
String condition = "";
String orderBy = "";

if(orderBy.length() == 0) {
	orderBy = "ENPLANWORKITEM2GRAPH.CODE";
}


selectStr = " select distinct  enplanworkitem2graph.daywork  from enplanworkitem2graph " + 
			"	where enplanworkitem2graph.planworkrefcode = " + planWorkRefCode + 
			"	order by enplanworkitem2graph.daywork   ";


int number = 0;
try {
	statement = connection.prepareStatement(selectStr );
	
	set = statement.executeQuery();
	int i;
	for (i = 0; set.next(); i++) {
		anObject = new ENPlanWorkItem2GraphShort();
		
		anObject.dayWork = set.getTimestamp(1);

		result.list.add(anObject);
	}

	result.setTotalCount(i);
	return result;
} catch(SQLException e) {
	System.out.println(e.getMessage()+"\nstatement - "+selectStr);
	throw new SystemException(e.getMessage(), e);
} finally {
	try {if (set != null) set.close();}             catch (SQLException e) {}
	try {if (statement != null) statement.close();} catch (SQLException e) {}
	if(connection != super.getConnection()) {
		try{connection.close();} catch(SQLException e){}
	}
}
}

} // end of ENPlanWorkItem2GraphDAO

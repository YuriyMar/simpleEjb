package com.ksoe.energynet.reports.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.logic.mDaxLogic;
import com.ksoe.energynet.reports.NPZ_with_otpusk_pl_fact.brigadeDS;
import com.ksoe.energynet.reports.PercentPremiums.Tech.axPremiumsWorkerDS;
import com.ksoe.energynet.reports.RepOrder.reestrPaymentDS.ReestrPaymentDS;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.brief.workerListFromKadryShortAX;
import com.ksoe.energynet.valueobject.filter.FINWorkerFilter;
import com.ksoe.energynet.valueobject.lists.ENNormVolumeAVZItemShortList;
import com.ksoe.energynet.valueobject.lists.FINWorkerShortList;
import com.ksoe.energynet.valueobject.lists.workerListFromKadryShortListAX;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.report.exception.ReportSystemException;
import com.ksoe.rqorder.logic.OrderLogic;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;

public class mDaxScriptlet extends JRDefaultScriptlet{

    private transient java.sql.Connection finConnection = null;
    private transient java.sql.Connection connection = null;
    private transient java.sql.Connection netConnection = null;
    private transient java.sql.Connection mDaxConnection = null;



    private Date getFirstDayInMonth(Date d){
        Date out = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE,1);

        out = calendar.getTime();

        return out;
    }

	protected java.sql.Connection getmDaxConnection()
			throws DatasourceConnectException {
		try {
			if (mDaxConnection != null && !mDaxConnection.isClosed()) {
				return mDaxConnection;
			}

			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext
					.lookup(AuthorizationJNDINames.MDAX_DATASOURCE);
			mDaxConnection = dataSource.getConnection();
			return mDaxConnection;
		} catch (NamingException e) {
			throw new DatasourceConnectException(AuthorizationJNDINames.MDAX_DATASOURCE, e);
		} catch (SQLException e) {
			throw new DatasourceConnectException(AuthorizationJNDINames.MDAX_DATASOURCE, e);
		}
	}

    protected java.sql.Connection getConnection(String dataSourceName)
            throws DatasourceConnectException {
        try {

            if (finConnection != null && !finConnection.isClosed()) {
                //System.out.print("get OLD Connection to FK");
                return finConnection;
            }

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext
                    .lookup(dataSourceName);
            finConnection = dataSource.getConnection();
            //System.out.print("get NEW Connection to FK");
            return finConnection;
        } catch (NamingException e) {
            System.out.print("\n error NamingException \n ");
            e.printStackTrace();
            throw new DatasourceConnectException(dataSourceName, e);
        } catch (SQLException e) {
            System.out.print("\n error SQLException \n ");
            throw new DatasourceConnectException(dataSourceName, e);
        }
    }

    protected java.sql.Connection getNetConnection(String dataSourceName)
            throws DatasourceConnectException {
        try {

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(dataSourceName);
            connection = dataSource.getConnection();
            return connection;

        } catch (NamingException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        } catch (SQLException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        }
    }


    @Override
	public void beforeReportInit() throws JRScriptletException
    {
        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        finConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("finConnection");
    }

    public BigDecimal getCountEltehpersonalFromKadry(String pdate_srez , Integer ppodrcod , String type_pers  ) throws PersistenceException
    {
    	BigDecimal out = new BigDecimal(0);
    	Date dateIn = com.ksoe.energynet.util.Tools.convertStringToDate(pdate_srez);

    	FINWorkerShortList finworkerList = new FINWorkerShortList();
    	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");

    	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
    	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");


    	try{

			mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile);

			FINWorkerFilter finworkerFilter = new FINWorkerFilter();


			finworkerList = mdLogic.getFINWorkerList(finworkerFilter, 0,-1, dateIn, true);

			for (int i = 0; i < finworkerList.totalCount; i++) {

//				if (finworkerList.get(i).nvz.toUpperCase().contains(type_pers)
//						&& finworkerList.get(i).tabNumber != Integer.MIN_VALUE) {
//					out = out.add(new BigDecimal(1));
//				}

			}


			return out;
    	 } catch (Exception e) {
             System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadry");
             return out;
         }
    }





/**
 * выборка количества рабочих часов в месяце для работника по плану
 * @param pdateStart
 * @param pdateFinal
 * @param tabn
 * @return
 * @throws PersistenceException
 */
public BigDecimal getCountWorkHoursByWorker(String pdateStart ,  String pdateFinal , String tabn  ) throws PersistenceException
{


  BigDecimal out = new BigDecimal(0);
  BigDecimal sumHours = new BigDecimal(0);

  PreparedStatement statement = null;
  ResultSet  resultSet = null;


  boolean isDebug = false;

  boolean isNewConn = false;


  try {

	  String sql = " SELECT sum(cast(workhours as numeric(15,2))) from [dbo].[ENERGYNET_FN_WORKING_TIME_PLAN]( \n" +
 " /* DATAAREAID */ 'KSOE', \n" +
 " /* FROMDATE */ '"+pdateStart+"', \n" +
 " /* TODATE */ '"+pdateFinal+"', \n" +
 " /* ORGID */ '', \n" +
 " /* EMPLID */ '"+tabn+"', \n" +
 " /* PAYCALENDARID */ '', \n" +
 " /* TIMEGROUP_FACT */ 'ENET_FACT');" ;

  if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
	  mDaxConnection = getmDaxConnection();
      isNewConn = true;
  }

  statement = mDaxConnection.prepareStatement(sql);
  resultSet = statement.executeQuery();

  if    (resultSet.next())
  {

	  sumHours = resultSet.getBigDecimal(1);

      return  sumHours ;
  }

  } catch (Exception e) {
      System.out.println(    e.getMessage() );
      throw new SystemException(e);
  } finally {

      if (isDebug)
          System.out.println("finally getCountWorkHoursByWorker:" + new Date());

      try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
      try {if (statement != null) statement.close();} catch (SQLException e) {}



          try {
              if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
            	  mDaxConnection.close();
            	  mDaxConnection = null;}
          } catch (SQLException e) {
          }
  }
return sumHours;

}

/**
 * выборка рабочих часов за месяц - только для основного графика
 * @param pdate_srez
 * @return
 * @throws PersistenceException
 */
public BigDecimal getCountWorkHours(String pdate_srez ) throws PersistenceException
{
	BigDecimal hours = new BigDecimal(0);

	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");

	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

	mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );

	BigDecimal[] wDaysHoursArr = { new BigDecimal(0), new BigDecimal(0) };
	wDaysHoursArr = mdLogic.getWorkingTimePlan( Tools.getFirstDayOfMonth( Tools.convertStringToDate(pdate_srez) ) ,
			Tools.getLastDayOfMonth( Tools.convertStringToDate(pdate_srez) ) , "Смена 8.2 часа Пн-Пт" );

	hours = wDaysHoursArr[1];
	return hours;
}


public Integer getWorkDaysBetweenDates(Date dateStart, Date dateEnd) {
	Integer result = null;	
	try {
		JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
		netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
		mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile);
		result = mdLogic.getWorkDaysBetweenDates(dateStart, dateEnd, true);		
	} catch(Exception exc) {
		exc.printStackTrace();
	}
	return result;
}


/**
 * выборка рабочих дней за месяц
 * @param pdate_srez
 * @return
 * @throws PersistenceException
 */
public BigDecimal getCountWorkDay(String pdate_srez ) throws PersistenceException
{
BigDecimal days = new BigDecimal(0);

	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");

	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

	mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );

	BigDecimal[] wDaysHoursArr = { new BigDecimal(0), new BigDecimal(0) };
	wDaysHoursArr = mdLogic.getWorkingTimePlan( Tools.getFirstDayOfMonth( Tools.convertStringToDate(pdate_srez) ) ,
			Tools.getLastDayOfMonth( Tools.convertStringToDate(pdate_srez) ) , "Смена 8.2 часа Пн-Пт" );

	days = wDaysHoursArr[0];
	return days;

}


/**
 *
 * @param pdate_srez
 * @param hrmorganizationid
 * @param orgchild -- учитывать вложенные (1-да , 0-нет )
 * @param nvz_type
 * @return
 * @throws PersistenceException
 */
public BigDecimal getCountPersonalInPodrAx(String pdate_srez , String hrmorganizationid , int orgchild , String nvz_type ) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start getCountPersonalInPodrAx:" + new Date());


    BigDecimal count = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    boolean isNewConn = false;

    try {

    String	sql = " select \n" +
    " count(DESCRIPTION) \n" +
    " from dbo.ENERGYNET_FN_STAFFLIST ( \n" +
    "                   /*@DATAAREAID =*/ 'KSOE' \n" +
	"			, /*@ACTUALITYDATE =*/ '"+pdate_srez+"' \n" +
	"			, /*@ORGANIZATIONLIST =*/ "+ hrmorganizationid +" \n" +
	"			, /*@ORGCHILD = */ "+ orgchild +" \n" +
	"			, /*@EMPLOYMENTLIST =*/ '' \n" +
	"			, /*@EMPLOYMENTNAMELIST =*/ '' \n" +
	"			, /*@STAFFPOSITIONLIST =*/ '' \n" +
	"           , '' " +
    " ) as r_in \n" +
    " where rate LIKE ( '%"+nvz_type+"%'  ) ";

    if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
  	  mDaxConnection = getmDaxConnection();
        isNewConn = true;
    }

        statement = mDaxConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();
        int i;
     if (resultSet.next())
     {
    	 count = resultSet.getBigDecimal(1) ;
     }


        return  count ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "  getCountPersonalInPodrAx");
        return  count ;
    } finally {

    	try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
                	mDaxConnection.close();
                	mDaxConnection = null;}
            } catch (SQLException e) {
            }
    }

}

/**
 * Метод возвращает коды подразделений(строка с текстовыми кодами подразделений) от парента до самого дочернего вниз по цепочке  через запятую ,
 *       учитывает только те подразделения в которых сидит работник по like НВЗ
 *       за период
 * @param startDate
 * @param endDate
 * @param energyNetPodrCode
 * @return
 * @throws PersistenceException
 */

public String GetAxOrganizationIdDownStringByEnergyNETPodrCodeWithTypeNVZ(String startDate , String endDate , int energyNetPodrCode , String typeNVZ ) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start GetAxOrganizationIdDownStringByEnergyNETPodrCodeWithTypeNVZ:" + new Date());


    String orgIdStr = "";

    PreparedStatement statement_net = null;
    ResultSet  resultSet_net = null;

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    boolean isNewConn = false;

    try {

    String sql_net = " select dep.hrmorganizationid from endepartment dep where dep.code =  " + energyNetPodrCode ;

    if ((netConnection == null) || (netConnection.isClosed()) ){
    	netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
      }

    statement_net = netConnection.prepareStatement(sql_net);
    resultSet_net = statement_net.executeQuery();

		 if (resultSet_net.next())
		 {
			 orgIdStr = resultSet_net.getString(1) ;
		 }

    String	sql = " SELECT   STUFF(( SELECT ',' + ''''+ a.HRMORGANIZATIONID  + '''' \n"+
       " from  dbo.ENERGYNET_FN_EMPL_HISTORY \n"+
       " ('KSOE' \n"+
	   "		,'"+ startDate +"' \n"+
	   "		,'"+ endDate +"' \n"+
	   "        , '"+ orgIdStr +"' \n"+
	   "		,1  \n"+
	   "		,''  \n"+
	   "        ,''  \n"+
	   "        ,''  \n"+
	   "        ,'"+ typeNVZ +"'  \n"+
       "        ,0)  AS a \n"+
//	   " where a.rate like ('%"+typeNVZ+"%') \n"+
	  " group BY a.HRMORGANIZATIONID \n"+
      " ORDER BY a.HRMORGANIZATIONID \n"+
      "  FOR XML PATH('') ) \n"+
      "  , 1,1,'') AS HRMORGANIZATIONID  ";

    if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
  	  mDaxConnection = getmDaxConnection();
        isNewConn = true;
    }

        statement = mDaxConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

     if (resultSet.next())
     {
    	 orgIdStr = resultSet.getString(1) ;
     }


        return  orgIdStr ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "  GetAxOrganizationIdDownStringByEnergyNETPodrCodeWithTypeNVZ");
        return  orgIdStr ;
    } finally {

    	try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
        try {if (resultSet_net != null) resultSet_net.close();} catch (SQLException e) {}
        try {if (statement_net != null) statement_net.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
                	mDaxConnection.close();
                	mDaxConnection = null;}
            } catch (SQLException e) {
            }
    }

}


/**
 * Метод возвращает коды подразделений от парента до самого дочернего вниз по цепочке  через запятую ,
 *
 * @param pdate_srez
 * @param energyNetPodrCode
 * @return
 * @throws PersistenceException
 */

public  String GetAxOrganizationIdDownStringByEnergyNETPodrCode(String startDate , String endDate ,  int energyNetPodrCode ) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start GetAxOrganizationIdDownStringByEnergyNETPodrCode:" + new Date());


    String orgIdStr = "";

    PreparedStatement statement_net = null;
    ResultSet  resultSet_net = null;

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    boolean isNewConn = false;

    try {

    String sql_net = " select dep.hrmorganizationid from endepartment dep where dep.code =  " + energyNetPodrCode ;

    if ((netConnection == null) || (netConnection.isClosed()) ){
    	netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
      }

    statement_net = netConnection.prepareStatement(sql_net);
    resultSet_net = statement_net.executeQuery();

		 if (resultSet_net.next())
		 {
			 orgIdStr = resultSet_net.getString(1) ;
		 }

		 String	sql = " SELECT   STUFF(( SELECT ',' + ''''+ a.HRMORGANIZATIONID  + '''' \n"+
			       " from  dbo.ENERGYNET_FN_EMPL_HISTORY \n"+
			       " ('KSOE' \n"+
				   "		,'"+ startDate +"' \n"+
				   "		,'"+ endDate +"' \n"+
				   "        , '"+ orgIdStr +"' \n"+
				   "		,1  \n"+
				   "		,''  \n"+
				   "        ,''  \n"+
				   "        ,''  \n"+
				   "        ,''  \n"+
			       "        ,0)  AS a \n"+
				  " group BY a.HRMORGANIZATIONID \n"+
			      " ORDER BY a.HRMORGANIZATIONID \n"+
			      "  FOR XML PATH('') ) \n"+
			      "  , 1,1,'') AS HRMORGANIZATIONID  ";

    if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
  	  mDaxConnection = getmDaxConnection();
        isNewConn = true;
    }

        statement = mDaxConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

     if (resultSet.next())
     {
    	 if(resultSet.getString(1).equals("") ) {
    		 orgIdStr = orgIdStr;
    	 } else {
    	 orgIdStr = "'"+orgIdStr+"'" + "," + resultSet.getString(1) ;
    	 }
     }


        return  orgIdStr ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "  GetAxOrganizationIdDownStringByEnergyNETPodrCode");
        return  orgIdStr ;
    } finally {

    	try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
        try {if (resultSet_net != null) resultSet_net.close();} catch (SQLException e) {}
        try {if (statement_net != null) statement_net.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
                	mDaxConnection.close();
                	mDaxConnection = null;}
            } catch (SQLException e) {
            }
    }

}


/**
 * Метод возвращает коды подразделений от парента до самого дочернего вниз по цепочке  через запятую ,
 *
 * @param startDate
 * @param endDate
 * @param axOrgId
 * @return
 * @throws PersistenceException
 */

public  String GetAxOrganizationIdDownString(String startDate , String endDate ,  String axOrgId ) throws PersistenceException
{
    String orgIdStr = axOrgId ;

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    boolean isNewConn = false;

    try {


		 String	sql = " SELECT   STUFF(( SELECT ',' + ''''+ a.HRMORGANIZATIONID  + '''' \n"+
			       " from  dbo.ENERGYNET_FN_EMPL_HISTORY \n"+
			       " ('KSOE' \n"+
				   "		,'"+ startDate +"' \n"+
				   "		,'"+ endDate +"' \n"+
				   "        , '"+ orgIdStr +"' \n"+
				   "		,1  \n"+
				   "		,''  \n"+
				   "        ,''  \n"+
				   "        ,''  \n"+
				   "        ,''  \n"+
			       "        ,0)  AS a \n"+
				  " group BY a.HRMORGANIZATIONID \n"+
			      " ORDER BY a.HRMORGANIZATIONID \n"+
			      "  FOR XML PATH('') ) \n"+
			      "  , 1,1,'') AS HRMORGANIZATIONID  ";

    if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
  	  mDaxConnection = getmDaxConnection();
        isNewConn = true;
    }

        statement = mDaxConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

     if (resultSet.next())
     {
    	 orgIdStr = resultSet.getString(1) ;
     }


        return  orgIdStr ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "  GetAxOrganizationIdDownStringByEnergyNETPodrCode");
        return  orgIdStr ;
    } finally {

    	try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
            try {
                if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
                	mDaxConnection.close();
                	mDaxConnection = null;}
            } catch (SQLException e) {
            }
    }

}


/**
 * Метод возвращает коды подразделений от парента до самого дочернего вниз по цепочке  через запятую c учетом типа значений НВЗ
 *
 * @param startDate
 * @param endDate
 * @param axOrgId
 * @param typeNVZ
 * @return
 * @throws PersistenceException
 */

public  String GetAxOrganizationIdDownStringWithTypeNVZ(String startDate , String endDate ,  String axPodrId, String typeNVZ ) throws PersistenceException
{              
    String orgIdStr = axPodrId ;

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    boolean isNewConn = false;

    try {


		 String	sql = " SELECT   STUFF(( SELECT ',' + ''''+ a.HRMORGANIZATIONID  + '''' \n"+
			       " from  dbo.ENERGYNET_FN_EMPL_HISTORY \n"+
			       " ('KSOE' \n"+
				   "		,'"+ startDate +"' \n"+
				   "		,'"+ endDate +"' \n"+
				   "        , '"+ orgIdStr +"' \n"+
				   "		,1  \n"+
				   "		,''  \n"+
				   "        ,''  \n"+
				   "        ,''  \n"+
				   "        ,'"+ typeNVZ +"'  \n"+
			       "        ,0)  AS a \n"+
				  " group BY a.HRMORGANIZATIONID \n"+
			      " ORDER BY a.HRMORGANIZATIONID \n"+
			      "  FOR XML PATH('') ) \n"+
			      "  , 1,1,'') AS HRMORGANIZATIONID  ";

    if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
  	  mDaxConnection = getmDaxConnection();
        isNewConn = true;
    }

        statement = mDaxConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

     if (resultSet.next())
     {
    	 orgIdStr = resultSet.getString(1) ;
    	 System.out.println(    orgIdStr );
     }


        return  orgIdStr ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "  GetAxOrganizationIdDownStringWithTypeNVZ");
        return  orgIdStr ;
    } finally {

    	try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
            try {
                if  ((isNewConn ) && (mDaxConnection != null && !mDaxConnection.isClosed())){
                	mDaxConnection.close();
                	mDaxConnection = null;}
            } catch (SQLException e) {
            }
    }

}


public JRDataSource getBrigadeDS(String rencode , String pdate1,  String pdate2 , BigDecimal persentLoadFilter , int withZadaniePlan )
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

PreparedStatement statement = null;
ResultSet  resultSet = null;

PreparedStatement statementNet = null;
ResultSet  resultSetNet = null;
boolean isNewConn = false;

System.out.print("Start getBrigadeDS  ");

try {
	// по подразделению выберем бригады с нпз

	ArrayList rows  = new ArrayList();

	String querySelBrigade = "";
	String querySelWorkHours = "";
	String querySelWorkHours1 = "";
	String querySelWorkHours2 = "";
	String querySelWorkHours3 = "";
	String querySelWorkHours4 = "";
	String querySelWorkHours5 = "";
	String querySelWorkHours6 = "";
	String querySelWorkHours7 = "";
	String querySelWorkHours8 = "";
	String querySelWorkHours9 = "";
	String querySelWorkHours10 = "";
	String querySelWorkHours11 = "";
	String querySelWorkHours12 = "";
	String querySelWorkHours13 = "";
	String querySelWorkHours14 = "";
	String querySelWorkHours15 = "";
	String querySelWorkHours16 = "";
	String querySelWorkHours17 = "";
	String querySelWorkHours18 = "";
	String querySelWorkHours19 = "";



					querySelBrigade = "  select \n" +
				" DESCRIPTION as podr_nazv , \n" +
				" HRMORGANIZATIONID as podr_id , \n" +
				" PARENTORGANIZATIONID as main_podr_id , \n" +
				" PARENTDESCRIPTION as podr_nazv_main , \n" +
				" case when rate like '%RZA%' then 'RZA' \n" +
				"      when rate like '%DTU%' then 'DTU' \n" +
				"      when rate like '%IZ%'  then 'IZ' \n" +
				"      else 'SHTAT' end as shortname , \n" +
				" count(name) as countpersonalinpodr \n" +
				"  \n" +
				" ,STREMPLID  --'string_agg табельных' \n" +
				"  \n" +
				" from ( \n" +
				"     select \n" +
				"     DESCRIPTION , \n" +
				"     HRMORGANIZATIONID , \n" +
				"     PARENTORGANIZATIONID , \n" +
				"     PARENTDESCRIPTION , \n" +
				"     NAME, \n" +
				"     case when rate like '%RZA%' then 'RZA' \n" +
				"          when rate like '%DTU%' then 'DTU' \n" +
				"          when rate like '%IZ%'  then 'IZ' \n" +
				"          else 'SHTAT' end as rate \n" +
				"  \n" +
				"      from dbo.ENERGYNET_FN_STAFFLIST ( \n" +
				"                       /*@DATAAREAID =*/ 'KSOE' \n" +
				"                     , /*@ACTUALITYDATE =*/ '"+pdate2+"' \n" +
				"                     , /*@ORGANIZATIONLIST =*/ '"+ rencode + "' \n" +
				"                     , /*@ORGCHILD = */ 1 \n" +
				"                     , /*@EMPLOYMENTLIST =*/ '' \n" +
				"                     , /*@EMPLOYMENTNAMELIST =*/ '' \n" +
				"                     , /*@STAFFPOSITIONLIST =*/ '' \n" +
				"                     , '' " +
				"     ) as r_in \n" +
				" where rate LIKE ( 'НВЗ%Д3%'  ) \n" +
				"  ) as r1 \n" +
				" OUTER APPLY \n" +
				"     (SELECT STUFF(( SELECT ',' + a.EMPLID \n" +
				"        FROM dbo.ENERGYNET_FN_STAFFLIST ( \n" +
				"                       /*@DATAAREAID =*/ 'KSOE' \n" +
				"                     , /*@ACTUALITYDATE =*/ '"+pdate2+"' \n" +
				"                     , /*@ORGANIZATIONLIST =*/ '"+ rencode + "' \n" +
				"                     , /*@ORGCHILD = */ 1 \n" +
				"                     , /*@EMPLOYMENTLIST =*/ '' \n" +
				"                     , /*@EMPLOYMENTNAMELIST =*/ '' \n" +
				"                     , /*@STAFFPOSITIONLIST =*/ '' \n" +
				"                     , ' ' " +
				" ) AS a \n" +
				"       WHERE a.HRMORGANIZATIONID = r1.HRMORGANIZATIONID \n" +
				"       ORDER BY a.EMPLID \n" +
				"         FOR XML PATH('') ), 1,1,'') AS STREMPLID ) AS f \n" +
				"  \n" +
				" group by DESCRIPTION, \n" +
				"  HRMORGANIZATIONID, \n" +
				"  PARENTORGANIZATIONID, \n" +
				"  PARENTDESCRIPTION , \n" +
				"  RATE, \n" +
				"  STREMPLID \n" +
				"  \n" +
				"  order by HRMORGANIZATIONID \n" +
				"  \n" ;


	if (mDaxConnection == null || mDaxConnection.isClosed()){

		mDaxConnection = getmDaxConnection();

    }

    statement = mDaxConnection.prepareStatement(querySelBrigade);
    resultSet = statement.executeQuery();


    BigDecimal vv_count_personal = new BigDecimal(0);
    // часы по норме в месяце
    BigDecimal v_countworkhours = getCountWorkHours(pdate2);
    while(resultSet.next())  /// цикл по бригадам
    {




        String Ppodrkod = resultSet.getString(2);
        String shortname = resultSet.getString(5);

        vv_count_personal= new BigDecimal( resultSet.getInt(6));

        // если нет персонала пропускаем
        if (vv_count_personal.doubleValue() <= 0) {
        	continue;
        }

        // часы отпуска по подразделению
        BigDecimal v_counthoursotpusk = getCountHoursOtpuskByEmplString(pdate1 , pdate2, resultSet.getString(7) );
        //кількість годин , які повинні відпрацювати електромонтери з урахуванням відпустки (ст.4*ст.1-ст.5)
        BigDecimal var_col6 = (v_countworkhours.multiply(vv_count_personal)).subtract(v_counthoursotpusk).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);

        // условие бюджетодержателя
        String budgcode ="";
        if (shortname.equals("RZA") ) {
        	budgcode = "75000012";
        } else
        if (shortname.equals("DTU") ) {
        	budgcode = "75000014";
        } else
            if (shortname.equals("IZ") ) {
            	budgcode = "75000023,75000001";
            }
            else budgcode = "select d.code from endepartment d where d.parentrefcode = 1002";

        int plankind = ENPlanWorkKind.CURRENT;

        //>============
        if (withZadaniePlan == 0){
        querySelWorkHours = " select  case when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '01' then 'Січень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '02' then 'Лютий' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '03' then 'Березень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '04' then 'Квітень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '05' then 'Травень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '06' then 'Червень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '07' then 'Липень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '08' then 'Серпень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '09' then 'Вересень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '10' then 'Жовтень' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '11' then 'Листопад' \n" +
                "               when to_char(to_date('"+pdate2+"','dd.mm.yyyy'),'mm') = '12' then 'Грудень' \n" +
                "             end  as period  , \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  --- выборка отработаных часов по работникам строго по своему ПОДРАЗДЕЛЕНИЮ ( по штатному ) \n" +
                "   coalesce((  select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork  , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены */ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.typerefcode = 5 /* инвест программа */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  \n" +
                "  ),0) as realizac_invest_progr \n" +
                "             , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor , finexecutor2plan \n" +
                "                    Where enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.typerefcode = 7 /*присоединение*/ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as vikonan_work_priconection \n" +
                "             , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.typerefcode in (6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /*енергозбыт*/ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as work_in_energozbut \n" +
                "  \n" +
                "             , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.typerefcode = 3 /*роботи за приписом */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as work_on_pripis \n" +
                "  \n" +
                "  ----- ТП -10/0,4 кВ \n" +
                "  , coalesce((   select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_14 \n" +
                "   , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_14_vneplan \n" +
                "  , coalesce((   select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убралиРаботы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_15 \n" +
                "  , coalesce((  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_16 \n" +
                "   , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from \n" +
                "                                                         enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_17 \n" +
                "  ------------ ПЛ - 10,/0,4 кВ -------------------------------------------------- \n" +
                "   , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_18 \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_18_vneplan \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"', 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_19 \n" +
                "  , coalesce((    select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement ,  finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_20 \n" +
                "   , coalesce((     select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_21 \n" +
                "  , \n" +
                "  ---------------- КЛ -0,4/10/35 кВ --------------------------------------------- \n" +
                "  coalesce(( select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_22 \n" +
                " ,  coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( /*1 , */ 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_22_vneplan \n" +
                "  \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
                "                      and  enplanwork.typerefcode in ( 2 , 9 )  /* или вкл аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_23 \n" +
                "  , coalesce((select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  \n" +
                "  ),0) as col_24 \n" +
                "   , coalesce(( select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode in ( 2 , 9 ) /* или вкл или аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_25 \n" +
                "  \n" +
                "  -----------------ПС -150/35 кВ ------------------------------------------------ \n" +
                "   , coalesce((  select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні ,  річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
                "                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_26 \n" +
                "   , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні ,  річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
                "                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( /*1 ,*/  10 )  /*плановые работы + внеплановые  */ \n" +
                " 					 and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_26_vneplan \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
                "                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_27 \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
                "                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_28 \n" +
                "   , coalesce((  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні ,  річні   */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
                "                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_29 \n" +
                "  \n" +
                "  ----------------- ВЛ -35 /150 кВ ---------------------------------------------- \n" +
                "  \n" +
                "    , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_30 \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                    and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
                "                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
                "                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_30_vneplan \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor  , finexecutor2plan \n" +
                "                    Where enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_31 \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план ,  поточні , річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_32 \n" +
                "   , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні  */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
                "                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as col_33 \n" +
                "  , \n" +
                "  \n" +
                " coalesce((select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
                "  sum(endeliverytime.deliverytimeplan) end from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
                " Select enhumenitem.code as enplanitemcode \n" +
                "   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
                "                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
                "                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
                "                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
                "                      and  enplanwork.datestart between to_date('"+pdate1+"', 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+"  /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanworkitem.countgen <> 0 \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "                      )) ,0) as deliveritime \n" +
                " , \n" +
                " coalesce((select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
                "  sum(endeliverytime.deliverytimeplan) end from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
                " Select enhumenitem.code as enplanitemcode \n" +
                "   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
                "                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
                "                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
                "                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
                "                      and  enplanwork.datestart between to_date('"+pdate1+"', 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+"  /* задание факт , план ,  поточні , річні  */ \n" +
                "                      and  enplanworkitem.countgen <> 0 \n" +
                "                     and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "                      and ( enplanwork.typerefcode in (5 , 7 ) -- инв прогр и приедн \n" +
                "                           /*или*/ \n" +
                "                                    /*по кр */ \n" +
                "                            or ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (1 /*, 10*/ )) ) \n" +
                "                          ) \n" +
                "  \n" +
                "                      )) ,0) as deliveritime_kr_inv_pricon \n" +
                " , \n" +
                " coalesce((select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
                "  sum(endeliverytime.deliverytimeplan) end from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
                " Select enhumenitem.code as enplanitemcode \n" +
                "   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
                "                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
                "                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
                "                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
                "                      and  enplanwork.datestart between to_date('"+pdate1+"', 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+"  /* задание факт , план ,  поточні , річні  */ \n" +
                "                      and  enplanworkitem.countgen <> 0 \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "                      and ( \n" +
                "                              ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (/*1 ,*/ 10 )) ) \n" +
                "                             ) \n" +
                "  \n" +
                "                      )) ,0) as deliveritime_kr_vneplan \n" +
                " , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
                "                      and  ( enelement.typerefcode NOT IN (21 ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
                "                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
                "                              or \n" +
                "                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
                "                              or -- не попадала реконстукция модернизация \n" +
                "                             (enplanwork.staterefcode = 2 )  ) ) \n" +
                "                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
                " присоединению , инвест программе*/ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid = '"+Ppodrkod+"' \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as other_avr \n" +
                "  \n" +
                "  , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні ,  річні */ \n" +
                "                      and  enplanwork.elementrefcode = enelement.code \n" +
                "                      and  enplanwork.typerefcode <> 2 /*аварийно востановительные работы */ \n" +
                "                      and  ( enelement.typerefcode NOT IN (21  ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
                "                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
                "                              or \n" +
                "                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
                "                              or -- не попадала реконстукция модернизация \n" +
                "                             (enplanwork.staterefcode = 2 )    ) ) \n" +
                "                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
                " присоединению , инвест программе*/ \n" +
                "  \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.axorgid =  '"+Ppodrkod+"'  \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as other \n"  ;
        }

        // с учетом заданий планов в периоде по которым месячные в прошлом периоде
        if (withZadaniePlan == 1){
        	querySelWorkHours = " select  case when to_char(pdate2,'mm') = '01' then 'Січень' \n" +
        			"               when to_char(pdate2,'mm') = '02' then 'Лютий' \n" +
        			"               when to_char(pdate2,'mm') = '03' then 'Березень' \n" +
        			"               when to_char(pdate2,'mm') = '04' then 'Квітень' \n" +
        			"               when to_char(pdate2,'mm') = '05' then 'Травень' \n" +
        			"               when to_char(pdate2,'mm') = '06' then 'Червень' \n" +
        			"               when to_char(pdate2,'mm') = '07' then 'Липень' \n" +
        			"               when to_char(pdate2,'mm') = '08' then 'Серпень' \n" +
        			"               when to_char(pdate2,'mm') = '09' then 'Вересень' \n" +
        			"               when to_char(pdate2,'mm') = '10' then 'Жовтень' \n" +
        			"               when to_char(pdate2,'mm') = '11' then 'Листопад' \n" +
        			"               when to_char(pdate2,'mm') = '12' then 'Грудень' \n" +
        			"             end  as period  , \n" +
        			"  \n" +
        			"  \n" +
        			"  \n" +
        			"  /* выборка отработаных часов по работникам строго по своему ПОДРАЗДЕЛЕНИЮ ( по штатному )*/ \n" +
        			"   coalesce((  select sum(countfact) from ( \n" +
        			"               select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork  , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.typerefcode = 5 /* инвест программа */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                union all \n" +
        			"                select coalesce(sum(hi.countfact),0) as countfact  from enplanwork  , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode = 5 /* инвест программа */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy') \n" +
        			"                          < to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                 ) \n" +
        			"              ) s_realiz \n" +
        			"  \n" +
        			"  ),0) as realizac_invest_progr \n" +
        			"  \n" +
        			" , coalesce((   select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor , finexecutor2plan \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні */ \n" +
        			"                      and  enplanwork.typerefcode = 7 /*присоединение*/ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                  union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , finexecutor , enhumenitem hi \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode = 7 /*присоединение*/ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy') \n" +
        			"                          < to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                 ) \n" +
        			"                   ) s_vikon \n" +
        			"  ),0) as vikonan_work_priconection \n" +
        			"  \n" +
        			"  \n" +
        			" , coalesce(( select sum(countfact) from ( \n" +
        			"                   select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні */ \n" +
        			"                      and  enplanwork.typerefcode in (6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /*енергозбыт*/ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode in (6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /*енергозбыт*/ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy') \n" +
        			"                          < to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                 ) \n" +
        			"                     ) s_enezb \n" +
        			"  ),0) as work_in_energozbut \n" +
        			"  \n" +
        			"  \n" ;
        		 querySelWorkHours1 =	" , coalesce(( select sum(countfact) from ( \n" +
        			"               select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.typerefcode = 3 /*роботи за приписом */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode = 3 /*роботи за приписом */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy') \n" +
        			"                          < to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                 ) \n" +
        			"                     ) s_pripi \n" +
        			"  \n" +
        			"  ),0) as work_on_pripis \n" +
        			"  \n" +
        			" /* ТП -10/0,4 кВ*/ \n" +
        			"  , coalesce((  select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                   union all \n" +
        			"                   select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy') \n" +
        			"                          < to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                 ) \n" +
        			"                  ) s14 \n" +
        			"  ),0) as col_14 \n" +
        			"   , coalesce((  select sum(countfact) from ( \n" +
        			"                  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                   select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (21 ,  3 ) /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s14vnpl \n" +
        			"  ),0) as col_14_vneplan \n" ;
        			querySelWorkHours2 = "  , coalesce((  select sum(countfact) from ( \n" +
        			"                 select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select  coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor ,enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s15 \n" +
        			"  ),0) as col_15 \n" +
        			"  , coalesce(( \n" +
        			"             select sum(countfact) from ( \n" +
        			"               select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor  ,  enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                  ) s16 \n" +
        			"  ),0) as col_16 \n" +
        			"   , coalesce(( select sum(countfact) from ( \n" +
        			"                  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                    union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor  , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 3 /*ТП 0.4 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s17 \n" +
        			"  \n" +
        			"  ),0) as col_17 \n" +
        			"  /*------------ ПЛ - 10,/0,4 кВ --------------------------------------------------*/ \n" ;
        			 querySelWorkHours3 = "   , coalesce(( select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor ,enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s18 \n" +
        			"  ),0) as col_18 \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 )  /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                    ) s18_vnpl \n" +
        			"  ),0) as col_18_vneplan \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" ;
        			querySelWorkHours4 = " select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor ,  enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s19 \n" +
        			"  ),0) as col_19 \n" +
        			"  , coalesce((   select sum(countfact) from ( \n" +
        			"                   select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement ,  finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                   select coalesce(sum(hi.countfact),0)  as countfact  from  enplanwork , enelement ,  finexecutor  , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4 - 10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                       and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                 ) s20 \n" +
        			"  \n" +
        			"  ),0) as col_20 \n" ;
        			 querySelWorkHours5 = "   , coalesce((  select sum(countfact) from ( \n" +
        			"                   select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select  coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s21 \n" +
        			"  ),0) as col_21 \n" +
        			"  , \n" +
        			"  /*---------------- КЛ -0,4/10/35 кВ ---------------------------------------------*/ \n" +
        			"  coalesce(( select sum(countfact) from ( \n" +
        			"               select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select  coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s22 \n" ;
        			querySelWorkHours6 = "  ),0) as col_22 \n" +
        			" ,  coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 , */ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                    union all \n" +
        			"                    select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 , */ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s22vnpl \n" ;
        			querySelWorkHours7 = "  ),0) as col_22_vneplan \n" +
        			"  \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode in ( 2 , 9 )  /* или вкл аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode in ( 2 , 9 )  /* или вкл аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s23 \n" +
        			"  ),0) as col_23 \n" ;
        			 querySelWorkHours8 = "  , coalesce((   select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                   select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor  , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                     )s24 \n" +
        			"  \n" +
        			"  ),0) as col_24 \n" +
        			"   , coalesce(( select sum(countfact) from ( \n" +
        			"                 select  sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 2 , 9 ) /* или вкл или аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n";
        			querySelWorkHours9 = " select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 2 , 9 ) /* или вкл или аварийно востановительные работы */ \n" +
        			"                       and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s25 \n" +
        			"  ),0) as col_25 \n" +
        			"  \n" +
        			"  /*-----------------ПС -150/35 кВ ------------------------------------------------*/ \n" +
        			"   , coalesce(( \n" +
        			"                select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні ,  річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                     union all \n" +
        			"  \n" +
        			"                     select coalesce(sum(hi.countfact),0)  as countfact  from enplanwork ,  enelement , finexecutor ,enhumenitem hi \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s26 \n" +
        			"  ),0) as col_26 \n" ;
        			querySelWorkHours10 = "   , coalesce(( select sum(countfact) from ( \n" +
        			"                  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні ,  річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/  10 )  /*плановые работы + внеплановые  */ \n" +
        			" 					 and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor ,enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/  10 )  /*плановые работы + внеплановые  */ \n" +
        			" 					 and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s26vnpl \n" +
        			"  ),0) as col_26_vneplan \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s27 \n" +
        			"  ),0) as col_27 \n" ;
        			querySelWorkHours11 = "  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor ,enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s28 \n" +
        			"  \n" +
        			"  ),0) as col_28 \n" ;
        			querySelWorkHours12 ="   , coalesce((  select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні ,  річні   */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in ( 6 , 9 ) /*Подстанция 35-150 кВ(6)  + объекты РЗА(9)  */ \n" +
        			"                      and  enplanwork.staterefcode = 5 /*Текущий ремонт  */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                    ) s29 \n" +
        			"  ),0) as col_29 \n" +
        			"  \n" +
        			"  /*----------------- ВЛ -35 /150 кВ ----------------------------------------------*/ \n" +
        			"  \n" ;
        			querySelWorkHours13 = "    , coalesce(( select sum(countfact) from ( \n" +
        			"                  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                   select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 /*, 10*/ )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                   ) s30 \n" +
        			"  ),0) as col_30 \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork ,  enelement , finexecutor ,finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                    and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                    and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in( 1 , 8)  /*Кап ремонт или Доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 ,*/ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                    ) s30vnpl \n" +
        			"  ),0) as col_30_vneplan \n" ;
        			querySelWorkHours14 = "  , coalesce(( select sum(countfact) from ( \n" +
        			"                 select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor  , finexecutor2plan \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                 select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor  ,  enhumenitem hi \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                   ) s31 \n" +
        			"  ),0) as col_31 \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
        			"                select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план ,  поточні , річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor  , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 1 , 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                    ) s32 \n" ;
        			querySelWorkHours15 = "  ),0) as col_32 \n" +
        			"   , coalesce(( select sum(countfact) from ( \n" +
        			"                  select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork ,  enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 5 /*Повітряна лінія 35-150 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"  \n" +
        			"                      ) s33 \n" +
        			"  ),0) as col_33 \n" +
        			"  , \n" +
        			"  \n" ;
        			querySelWorkHours16= " coalesce(( \n" +
        			"  select sum(delivery) from ( \n" +
        			"  select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"  sum(endeliverytime.deliverytimeplan) end as delivery from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			" Select enhumenitem.code as enplanitemcode \n" +
        			"   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                      and  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind  /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanworkitem.countgen <> 0 \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"               ) \n" +
        			"   union all \n" +
        			"  \n" +
        			"   select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"  sum(endeliverytime.deliverytimeplan) end as delivery from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			" Select enhumenitem.code as enplanitemcode \n" +
        			"   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor \n" +
        			"                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                      and  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanworkitem.countgen <> 0 \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = enhumenitem.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					           ) \n" +
        			"               ) \n" +
        			"           ) sdeli \n" +
        			"  ) ,0) as deliveritime \n" +
        			" , \n" +
        			" coalesce(( \n" +
        			"          select sum(delivery) from ( \n" +
        			"           select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"  sum(endeliverytime.deliverytimeplan) end as delivery from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			" Select enhumenitem.code as enplanitemcode \n" +
        			"   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
        			"                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                      and  enplanwork.datestart between  pdate1 and pdate2 \n" +
        			"                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind  /* задание факт , план ,  поточні , річні  */ \n" +
        			"                      and  enplanworkitem.countgen <> 0 \n" +
        			"                     and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and ( enplanwork.typerefcode in (5 , 7 ) /*-- инв прогр и приедн*/ \n" +
        			"                           /*или*/ \n" +
        			"                                    /*по кр */ \n" +
        			"                            or ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (1 /*, 10*/ )) ) \n" +
        			"                          ) \n" +
        			"  \n" +
        			"                      ) \n" +
        			"              union all \n" +
        			"  \n" ;
        			querySelWorkHours17 = "              select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"  sum(endeliverytime.deliverytimeplan)  end as delivery  from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			" Select enhumenitem.code as enplanitemcode \n" +
        			"   from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor \n" +
        			"                    Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                      and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                      and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                      and  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanworkitem.countgen <> 0 \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = enhumenitem.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"                      and ( enplanwork.typerefcode in (5 , 7 ) /*-- инв прогр и приедн*/ \n" +
        			"                           /*или*/ \n" +
        			"                                    /*по кр */ \n" +
        			"                            or ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (1 /*, 10*/ )) ) \n" +
        			"                          ) \n" +
        			"  \n" +
        			"                      ) \n" +
        			"              ) sdelpricon \n" +
        			"  \n" +
        			"  \n" +
        			"   ) ,0) as deliveritime_kr_inv_pricon \n" +
        			" , \n" +
        			" coalesce(( \n" +
        			"  select sum(delivery) from ( \n" +
        			"         select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"        sum(endeliverytime.deliverytimeplan) end as delivery from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			"       Select enhumenitem.code as enplanitemcode \n" +
        			"         from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor  , finexecutor2plan \n" +
        			"                          Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                            and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                            and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                            and  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                             and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                            and  enplanwork.kindcode = plankind  /* задание факт , план ,  поточні , річні  */ \n" +
        			"                            and  enplanworkitem.countgen <> 0 \n" +
        			"                            and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                            and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                            and  finexecutor.axorgid = Ppodrkod \n" +
        			"                            And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                            and ( \n" +
        			"                                    ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (/*1 ,*/ 10 )) ) \n" +
        			"                                   ) \n" +
        			"  \n" +
        			"                            ) \n" +
        			"                       union all \n" +
        			"  \n" ;
        			querySelWorkHours18 ="         select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
        			"        sum(endeliverytime.deliverytimeplan) end as delivery from endeliverytime where  endeliverytime.humenitemrefcode in ( \n" +
        			"       Select enhumenitem.code as enplanitemcode \n" +
        			"         from enhumenitem  ,  enplanworkitem , enplanwork , finexecutor \n" +
        			"                          Where  enhumenitem.planitemrefcode = enplanworkitem.code \n" +
        			"                            and  enplanworkitem.planrefcode = enplanwork.code \n" +
        			"                            and  coalesce(enhumenitem.countfact  , 0 ) <> 0 \n" +
        			"                            and  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                             and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                            and  enplanwork.kindcode = 3 \n" +
        			"                            and  enplanworkitem.countgen <> 0 \n" +
        			"                            and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                            and  finexecutor.axorgid = Ppodrkod \n" +
        			"                            And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                            and enplanwork.code = enhumenitem.planrefcode \n" +
        			"                            and exists (select \n" +
        			"                             pp.code \n" +
        			"                             from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                             where pm.code = ph.planoldrefcode \n" +
        			"                             and ph.reasoncode = 4 \n" +
        			"                             and ph.plannewrefcode = pp.code \n" +
        			"                             and pp.code = enplanwork.code \n" +
        			"                             and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			"                                       ) \n" +
        			"                            and ( \n" +
        			"                                    ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (/*1 ,*/ 10 )) ) \n" +
        			"                                   ) \n" +
        			"  \n" +
        			"                            ) \n" +
        			"              ) sdelivkrvnepl \n" +
        			"       ) ,0) as deliveritime_kr_vneplan \n" +
        			"  \n" +
        			"  \n" +
        			" , coalesce(( \n" +
        			"   select sum(countfact) from ( \n" +
        			"     select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  ( enelement.typerefcode NOT IN (21 ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
        			"                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or \n" +
        			"                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or /*-- не попадала реконстукция модернизация*/ \n" +
        			"                             (enplanwork.staterefcode = 2 )  ) ) \n" +
        			"                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
        			" присоединению , инвест программе*/ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"     union all \n" +
        			"  \n" +
        			"     select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  ( enelement.typerefcode NOT IN (21 ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
        			"                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or \n" +
        			"                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or /*-- не попадала реконстукция модернизация*/ \n" +
        			"                             (enplanwork.staterefcode = 2 )  ) ) \n" +
        			"                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
        			" присоединению , инвест программе*/ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"          ) sotheravr \n" +
        			"  \n" +
        			"  ),0) as other_avr \n" +
        			"  \n" ;
        			querySelWorkHours19 = "  , coalesce(( \n" +
        			"    select sum(countfact) from ( \n" +
        			"      select sum(finexecutor2plan.totaltimemanhours) as countfact  from  enplanwork , enelement , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні ,  річні */ \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enplanwork.typerefcode <> 2 /*аварийно востановительные работы */ \n" +
        			"                      and  ( enelement.typerefcode NOT IN (21  ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
        			"                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or \n" +
        			"                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or /*-- не попадала реконстукция модернизация*/ \n" +
        			"                             (enplanwork.staterefcode = 2 )    ) ) \n" +
        			"                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
        			" присоединению , инвест программе*/ \n" +
        			"  \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"  union all \n" +
        			"  \n" +
        			"  select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                       and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enplanwork.typerefcode <> 2 /*аварийно востановительные работы */ \n" +
        			"                      and  ( enelement.typerefcode NOT IN (21  ,  3 , 1 , 2 , 11 , 6 , 9 , 5 ) \n" +
        			"                        or ( ( enelement.typerefcode = 9 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or \n" +
        			"                             ( enelement.typerefcode = 6 and enplanwork.staterefcode = 3 ) \n" +
        			"                              or /*-- не попадала реконстукция модернизация*/ \n" +
        			"                             (enplanwork.staterefcode = 2 )    ) ) \n" +
        			"                      and  enplanwork.typerefcode NOT IN (5, 7, 6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /* исключая работы по энергосбыту ,  \n" +
        			" присоединению , инвест программе*/ \n" +
        			"  \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.axorgid = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and enplanwork.code = hi.planrefcode \n" +
        			"                      and exists (select \n" +
        			"                       pp.code \n" +
        			"                       from enplanwork pm , enplancorrecthistory ph ,  enplanwork pp \n" +
        			"                       where pm.code = ph.planoldrefcode \n" +
        			"                       and ph.reasoncode = 4 \n" +
        			"                       and ph.plannewrefcode = pp.code \n" +
        			"                       and pp.code = enplanwork.code \n" +
        			"                       and  to_date('01'||'.'||pm.monthgen||'.'||pm.yeargen,'dd.mm.yyyy')< to_date('01'||'.'||pp.monthgen||'.'||pp.yeargen,'dd.mm.yyyy') \n" +
        			" 					            ) \n" +
        			"           ) sother \n" +
        			"  \n" +
        			"  ),0) as other \n" +
        			"  \n" +
        			"  from ( \n" +
        			"    select to_date('" +pdate1 + "' , 'dd.mm.yyyy')  as pdate1 \n" +
        			"  , ( select to_date('" +pdate2 + "' , 'dd.mm.yyyy'))  as pdate2 \n" +
        			"  , ( "+plankind+" ) as plankind \n" +
        			"  , ( '"+Ppodrkod+"'::text ) as Ppodrkod \n" +
        			"  ) sel_param \n" +
        			"  \n" ;

        }

        //<============

//        1  period
//        2  realizac_invest_progr
//        3  vikonan_work_priconection
//        4  work_in_energozbut
//        5  work_on_pripis
//        6  col_14
//        7  col_14_vneplan
//        8  col_15
//        9  col_16
//        10  col_17
//        11  col_18
//        12  col_18_vneplan
//        13  col_19
//        14  col_20
//        15  col_21
//        16  col_22
//        17  col_22_vneplan
//        18  col_23
//        19  col_24
//        20  col_25
//        21  col_26
//        22  col_26_vneplan
//        23  col_27
//        24  col_28
//        25  col_29
//        26  col_30
//        27  col_30_vneplan
//        28  col_31
//        29  col_32
//        30  col_33
//        31  deliveritime
//        32  deliveritime_kr_inv_pricon
//        33  deliveritime_kr_vneplan
//        34  other_avr
//        35  other


        if (netConnection == null || netConnection.isClosed() ){
        	netConnection =  getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
        }

        statementNet = netConnection.prepareStatement(querySelWorkHours+
        		querySelWorkHours1+
        		querySelWorkHours2+
        		querySelWorkHours3+
        		querySelWorkHours4+
        		querySelWorkHours5+
        		querySelWorkHours6+
        		querySelWorkHours7+
        		querySelWorkHours8+
        		querySelWorkHours9+
        		querySelWorkHours10+
        		querySelWorkHours11+
        		querySelWorkHours12+
        		querySelWorkHours13+
        		querySelWorkHours14+
        		querySelWorkHours15+
        		querySelWorkHours16+
        		querySelWorkHours17+
        		querySelWorkHours18+
        		querySelWorkHours19

        		);

        /*String reportPath = "C:/Users/yura/test.txt";
        String fileName =  querySelWorkHours+
        		querySelWorkHours1+
        		querySelWorkHours2+
        		querySelWorkHours3+
        		querySelWorkHours4+
        		querySelWorkHours5+
        		querySelWorkHours6+
        		querySelWorkHours7+
        		querySelWorkHours8+
        		querySelWorkHours9+
        		querySelWorkHours10+
        		querySelWorkHours11+
        		querySelWorkHours12+
        		querySelWorkHours13+
        		querySelWorkHours14+
        		querySelWorkHours15+
        		querySelWorkHours16+
        		querySelWorkHours17+
        		querySelWorkHours18+
        		querySelWorkHours19 ;

        FileOutputStream fop;
        try {
            fop = new FileOutputStream(reportPath);

            // get the content in bytes
            byte[] contentInBytes = fileName.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        resultSetNet = statementNet.executeQuery();


        while(resultSetNet.next())  /// данные для процента загрузки
        {

        BigDecimal var_col10 = new BigDecimal(0);


        BigDecimal col_15 = new BigDecimal(0);
        BigDecimal col_17 = new BigDecimal(0);
        BigDecimal col_19 = new BigDecimal(0);
        BigDecimal col_21 = new BigDecimal(0);
        BigDecimal col_23 = new BigDecimal(0);
        BigDecimal col_25 = new BigDecimal(0);
        BigDecimal col_27 = new BigDecimal(0);
        BigDecimal col_29 = new BigDecimal(0);
        BigDecimal col_31 = new BigDecimal(0);
        BigDecimal col_33 = new BigDecimal(0);
        BigDecimal other_avr = new BigDecimal(0);

         var_col10 = var_col6.multiply(new BigDecimal(0.05)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);


         col_15 = resultSetNet.getBigDecimal(8);
         col_17 = resultSetNet.getBigDecimal(10);
         col_19 = resultSetNet.getBigDecimal(13);
         col_21 = resultSetNet.getBigDecimal(15);
         col_23 = resultSetNet.getBigDecimal(18);
         col_25 = resultSetNet.getBigDecimal(20);
         col_27 = resultSetNet.getBigDecimal(23);
         col_29 = resultSetNet.getBigDecimal(25);
         col_31 = resultSetNet.getBigDecimal(28);
         col_33 = resultSetNet.getBigDecimal(30);
         other_avr = resultSetNet.getBigDecimal(34);

         BigDecimal var_col11 = new BigDecimal(0);
         var_col11 =  col_15.add(col_17).add(col_19).add(col_21).add(col_23).add(col_25).add(col_27).add(col_29).add(col_31).add(col_33).add(other_avr);

         BigDecimal realizac_invest_progr = new BigDecimal(0);
         BigDecimal vikonan_work_priconection = new BigDecimal(0);
         BigDecimal work_in_energozbut = new BigDecimal(0);
         BigDecimal deliveritime = new BigDecimal(0);
         BigDecimal col_14 = new BigDecimal(0);
         BigDecimal col_14_vneplan = new BigDecimal(0);
         BigDecimal col_16 = new BigDecimal(0);
         BigDecimal col_18 = new BigDecimal(0);
         BigDecimal col_18_vneplan = new BigDecimal(0);
         BigDecimal col_20 = new BigDecimal(0);
         BigDecimal col_22 = new BigDecimal(0);
         BigDecimal col_22_vneplan = new BigDecimal(0);
         BigDecimal col_24 = new BigDecimal(0);
         BigDecimal col_26 = new BigDecimal(0);
         BigDecimal col_26_vneplan = new BigDecimal(0);
         BigDecimal col_28 = new BigDecimal(0);
         BigDecimal col_30 = new BigDecimal(0);
         BigDecimal col_30_vneplan = new BigDecimal(0);
         BigDecimal col_32 = new BigDecimal(0);
         BigDecimal other = new BigDecimal(0);
         BigDecimal work_on_pripis = new BigDecimal(0);


         realizac_invest_progr = resultSetNet.getBigDecimal(2);
         vikonan_work_priconection = resultSetNet.getBigDecimal(3);
         work_in_energozbut = resultSetNet.getBigDecimal(4);
         deliveritime = resultSetNet.getBigDecimal(31);
         col_14 = resultSetNet.getBigDecimal(6);
         col_14_vneplan = resultSetNet.getBigDecimal(7);
         col_16 = resultSetNet.getBigDecimal(9);
         col_18 = resultSetNet.getBigDecimal(11);
         col_18_vneplan = resultSetNet.getBigDecimal(12);
         col_20 = resultSetNet.getBigDecimal(14);
         col_22 = resultSetNet.getBigDecimal(16);
         col_22_vneplan = resultSetNet.getBigDecimal(17);
         col_24 = resultSetNet.getBigDecimal(19);
         col_26 = resultSetNet.getBigDecimal(21);
         col_26_vneplan = resultSetNet.getBigDecimal(22);
         col_28 = resultSetNet.getBigDecimal(24);
         col_30 = resultSetNet.getBigDecimal(26);
         col_30_vneplan = resultSetNet.getBigDecimal(27);
         col_32 = resultSetNet.getBigDecimal(29);
         other = resultSetNet.getBigDecimal(35);
         work_on_pripis = resultSetNet.getBigDecimal(5);



         BigDecimal var_col34 = new BigDecimal(0);

         var_col34 = realizac_invest_progr.add(
                 vikonan_work_priconection).add(
                         work_in_energozbut).add(
                         var_col10).add(
                         var_col11).add(
                         deliveritime).add(
                         col_14).add(
                         col_14_vneplan).add(
                         col_16).add(
                         col_18).add(
                         col_18_vneplan).add(
                         col_20).add(
                         col_22).add(
                         col_22_vneplan).add(
                         col_24).add(
                         col_26).add(
                         col_26_vneplan).add(
                         col_28).add(
                         col_30).add(
                         col_30_vneplan).add(
                         col_32).add(
                         other).add(
                         work_on_pripis);

         BigDecimal loadPercent = new BigDecimal(0);


         if (var_col6.doubleValue() > 0 ) {
         loadPercent = var_col34.divide(var_col6, 8 , 2).multiply( new BigDecimal(100)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);;
         }
         if (loadPercent.doubleValue() > persentLoadFilter.doubleValue()  ){
        	 continue;
         }





        	HashMap hashMap = new HashMap();

        	hashMap.put(brigadeDS.PODR_NAME, resultSet.getString(4) + " " +  resultSet.getString(1));
        	hashMap.put(brigadeDS.PODR_CODE, resultSet.getString(2));
            hashMap.put(brigadeDS.LOADPERCENT, loadPercent.toString() );

            rows.add(hashMap);


        };






    }

    if (rows.isEmpty()) {
    	return null;
    }
    else {
        return new ReestrPaymentDS(rows.toArray());
    }


    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (DatasourceConnectException e) {
    	throw new ReportSystemException(e);
	} catch (PersistenceException e) {
		throw new ReportSystemException(e);
	} finally {
        try {
            if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
        }
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
        }

        try {
            if (resultSetNet != null) resultSetNet.close();
            } catch (SQLException e) {
        }
        try {
            if (statementNet != null) statementNet.close();
        } catch (SQLException e) {
        }

    }
}

/**
 * //  выборка количества  часов отпуска по сотрудникам (отпуска заведенные по приказам , т.е не фактические часы отпуска которые отгулял сотрудник )
 * @param datestart начальная дата периода
 * @param dateend конечная дата периода
 * @param stringEmplid строка с табельными сотрудников
 * @return уол-во часов отпуска
 * @throws PersistenceException
 */
public BigDecimal getCountHoursOtpuskByEmplString(String datestart,	String dateend , String	stringEmplid) throws PersistenceException
{
	BigDecimal hoursOrptusk = new BigDecimal(0);

	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");

	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

	mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );


	hoursOrptusk = mdLogic.getCountHoursOtpuskByEmplString( datestart , dateend , stringEmplid );


	return hoursOrptusk;
}

public BigDecimal getCountActualWorkHoursByEmployee(String tabNumber, Date dateStart, Date dateFinal) {
	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
	mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );
	return mdLogic.getCountActualWorkHoursByEmployee(tabNumber, dateStart, dateFinal);
}

public String getPodrName(String id, Date date) {
	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
	mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );
	return mdLogic.getPodrName(id, date);
}


public JRDataSource getWorkerAx(String hrmorganizationid,  int rencode, String pdate1, String pdate2, int kindWorker, boolean forPremija) {
	return this.getWorkerAx(hrmorganizationid, rencode, pdate1, pdate2, kindWorker, forPremija, null);
}

Map<String, BigDecimal> hashForRegionalManagement = new HashMap<String, BigDecimal>();
/***
 *
 * @param hrmorganizationid -- код подразделения AX
 * @param rencode  -- код подразделения Net
 * @param pdate1   -- дата с
 * @param pdate2   -- дата по
 * @param kindWorker -- тип персонала (1-Загальнотехнічний , 2-Енергозбутовий , 12-Загальнотехнічний и Енергозбутовий вместе  )
 * @param forPremija -- признак  выборки сотрудников для премии или для додатков
 * @return
 */
public JRDataSource getWorkerAx(String hrmorganizationid,  int rencode, String pdate1, String pdate2, int kindWorker, boolean forPremija, Collection<String> personnelNumbers) {

	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	PreparedStatement statement = null;
	ResultSet  resultSet = null;

	PreparedStatement statementNet = null;
	ResultSet  resultSetNet = null;
	boolean isNewConn = false;

	System.out.print("Start getWorkerAx  String hrmorganizationid = " + hrmorganizationid + " , int rencode = " + rencode + ", String pdate1 = " + pdate1 + ", String pdate2 " + pdate2 + " int 			kindWorker = " + kindWorker + ", boolean forPremija = " + forPremija );
	
	List<String> regionalManagements = Arrays.asList("1030501", "1030502", "1030503", "1030504", "1030505", "1030506");
	List<String> regionalManagementPersonnelNumbers = new Vector<String>();

	try {

		ArrayList rows  = new ArrayList();
		String querySelWorkHours = "";
		System.out.println("Начинаю выборку");
		workerListFromKadryShortListAX workerList = this.getWorkerListFromAX(pdate1, pdate2 , hrmorganizationid , kindWorker, forPremija, personnelNumbers);
		System.out.println("Заканчиваю выборку");
		ArrayList<WorkerListFromKadryShortCompare> arraylistSort = new ArrayList<WorkerListFromKadryShortCompare>();
		//for (int f=0; f < workerList.totalCount; f++) {
		for(workerListFromKadryShortAX item : workerList.list) {
		
			boolean podrContained = false;
			for(String regionalManagement : regionalManagements) if(item.podr_id.startsWith(regionalManagement)) {
				podrContained = true; break;
			}
			
			if(podrContained && hrmorganizationid.equals("000")) {
				regionalManagementPersonnelNumbers.add(item.tabn);
				continue;
			}
		
			boolean ixistTabnFromSortList = false;
			for (int i = 0; i < arraylistSort.size(); i++) {
				String finWorkerList = item.tabn;
				String finWorkerListSort =  arraylistSort.get(i).tabn;
				if (finWorkerList.equals(finWorkerListSort))  {
					ixistTabnFromSortList = true;
					arraylistSort.set(i, new WorkerListFromKadryShortCompare(
						item.tabn
						, item.podr_nazv
						, item.podr_id
						, item.fio
						, item.post_fin
						, item.norma_vrem_hours.add(arraylistSort.get(i).norma_vrem_hours)
						, item.sumhoursotpusk.add(arraylistSort.get(i).sumhoursotpusk)
						, item.main_podr_id
						, item.podr_nazv_main
						, item.fact_workdays.add(arraylistSort.get(i).fact_workdays)
						, item.rate
						, item.fact_workhours.add(arraylistSort.get(i).fact_workhours)
						, item.fact_workhours_overall.add(arraylistSort.get(i).fact_workhours_overall)));
				}
			}
			
			if (!ixistTabnFromSortList) {
				arraylistSort.add(new WorkerListFromKadryShortCompare(
					item.tabn
					, item.podr_nazv
					, item.podr_id
					, item.fio
					, item.post_fin
					, item.norma_vrem_hours
					, item.sumhoursotpusk
					, item.main_podr_id
					, item.podr_nazv_main
					, item.fact_workdays
					, item.rate
					, item.fact_workhours
					, item.fact_workhours_overall));
			}
		}
		
		ArrayList<WorkerListFromKadryShortCompare> arraylistSortWithoutRegionalManagements = new ArrayList<WorkerListFromKadryShortCompare>();
		for(WorkerListFromKadryShortCompare item : arraylistSort) {
			if(hrmorganizationid.equals("000") && regionalManagementPersonnelNumbers.contains(item.tabn)) {
				continue;	
			}
			arraylistSortWithoutRegionalManagements.add(item);
		}
		arraylistSort = arraylistSortWithoutRegionalManagements;

		Collections.sort(arraylistSort);
		
		if(arraylistSort.size() > 0 && regionalManagements.contains(hrmorganizationid)) {
			Map<String, BigDecimal> overallWorkingTimes = null;
			if(hashForRegionalManagement.size() > 0) {
				System.out.println("Можно взять табельное рабочее время из хэш");
				overallWorkingTimes = hashForRegionalManagement;
			} else {
				System.out.println("Начинаю забирать общее время");
			  	String strKindWorker = new String( (kindWorker == 1 && forPremija == true ) ? "'НВЗ_ПРЕМ;НВЗ_РЗА_ПРЕМ;НВЗ_ДТУ_ПРЕМ;НВЗ_ІЗ_ПРЕМ' " :
							                 ((kindWorker == 2 || kindWorker == -1 || kindWorker == -2 ) && forPremija == true ) ? "'НВЗ_Е_ПРЕМ'" :
							                 (kindWorker == 1 && forPremija == false ) ? "'НВЗ_Д3;НВЗ_РЗА_Д3;НВЗ_ДТУ_Д3;НВЗ_ІЗ_Д3' " :
							                 (kindWorker == 2 && forPremija == false ) ? "'НВЗ_Е_Д3'" :
							                 (kindWorker == 4 && forPremija == false ) ? "'НВЗ_ПРОЕКТ'" :
							                 (kindWorker == 12 && forPremija == true ) ? "'НВЗ_ПРЕМ;НВЗ_РЗА_ПРЕМ;НВЗ_ДТУ_ПРЕМ;НВЗ_ІЗ_ПРЕМ;НВЗ_Е_ПРЕМ' " :
							                 (kindWorker == 12 && forPremija == false ) ? "'НВЗ_Д3;НВЗ_РЗА_Д3;НВЗ_ДТУ_Д3;НВЗ_ІЗ_Д3;НВЗ_Е_Д3' " :
							                // 02,06,2016 - добавим условие по контрелерам , инспекторам, нач - что бы брались тоже только те которые со ставкой НВЗ_Е_ПРЕМ - михайлова ТВ присылала письмо что они не должны попадать
							              //   ((kindWorker == -1 || kindWorker == -2 || kindWorker == -3 ) && forPremija == true)	 ? "НВЗ_Е_ПРЕМ" :
							                		        	                	 "''" );
			 	netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
				mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );
				String code_time_vihod = mdLogic.getPayCalendarTimeCodes("ТабФакт");
				String code_time_vihod2 = mdLogic.getPayCalendarTimeCodes("ТабКоманд");
				String code_time_vihod3 = mdLogic.getPayCalendarTimeCodes("ТабСверхур");
				if (!code_time_vihod2.equals("")){
					code_time_vihod = code_time_vihod+";"+code_time_vihod2;
				}
				if (!code_time_vihod3.equals("")){
					code_time_vihod = code_time_vihod+";"+code_time_vihod3;
				}
				overallWorkingTimes = getOverallWorkTime("000"
					, pdate1, pdate2, code_time_vihod, strKindWorker, personnelNumbers);			
			}
			
			for(WorkerListFromKadryShortCompare item : arraylistSort) {
				if(overallWorkingTimes.containsKey(item.tabn)) {
					item.fact_workhours_overall = overallWorkingTimes.get(item.tabn);
				}
			}
			System.out.println("Заканчиваю забирать общее время");
		}

		for (WorkerListFromKadryShortCompare item : arraylistSort) {
		
			if(hrmorganizationid.equals("000") && regionalManagementPersonnelNumbers.contains(item.tabn)) {
				continue;	
			}
			HashMap<String, Object> hashMap = new HashMap<>();

			hashMap.put(axPremiumsWorkerDS.TABN, item.tabn);
			hashMap.put(axPremiumsWorkerDS.PODR_NAZV, item.podr_nazv);
			hashMap.put(axPremiumsWorkerDS.PODR_ID, item.podr_id);
			hashMap.put(axPremiumsWorkerDS.FIO, item.fio);
			hashMap.put(axPremiumsWorkerDS.POST_FIN, item.post_fin);
			hashMap.put(axPremiumsWorkerDS.NORMA_VREM_HOURS, item.norma_vrem_hours);
			hashMap.put(axPremiumsWorkerDS.SUMHOURSOTPUSK, item.sumhoursotpusk);
			hashMap.put(axPremiumsWorkerDS.MAIN_PODR_ID, item.main_podr_id);
			hashMap.put(axPremiumsWorkerDS.PODR_NAZV_MAIN, item.podr_nazv_main);
			hashMap.put(axPremiumsWorkerDS.FACT_WORKDAYS, item.fact_workdays);
			hashMap.put(axPremiumsWorkerDS.RATE, item.rate);
			hashMap.put(axPremiumsWorkerDS.FACT_WORKHOURS, item.fact_workhours);
			hashMap.put(axPremiumsWorkerDS.FACT_WORKHOURS_OVERALL, item.fact_workhours_overall);

			rows.add(hashMap);
		}


		if (rows.isEmpty()) {
			return null;
		} else {
			return new axPremiumsWorkerDS(rows.toArray());
		}

	} catch (SQLException e) {
		throw new ReportSystemException(e);
	} catch (DatasourceConnectException e) {
		throw new ReportSystemException(e);
	} catch (PersistenceException e) {
		throw new ReportSystemException(e);
	} finally {
	}
}

	public Map<String, BigDecimal> getOverallWorkTime(String renCode, String pdate1, String pdate2, String code_time_vihod, String strKindWorker, Collection<String> personnelNumbers) throws DatasourceConnectException  {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		String concatenatedPersonnelNumbers = (personnelNumbers != null) ? personnelNumbers.stream().map(Object::toString).collect(Collectors.joining(";")) : "";
		try {
			if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
				mDaxConnection = getmDaxConnection();
			}
		} catch(SQLException e) {}
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
                       String sql =  "SELECT emplid, sum(fact_workhours) AS fact_workhours FROM \n " +
					 "(SELECT emplid, coalesce((  SELECT sum(ROUND(totalhours,2))  FROM dbo.[ENERGYNET_FN_WORKING_TIME]('KSOE' \n " +
					 "	,CASE WHEN STARTDATE < '" + pdate1 + "' THEN '" + pdate1 + "' ELSE STARTDATE END   \n " +
					 "     ,CASE WHEN ENDDATE = '01.01.1900' OR ENDDATE > '" + pdate2 + "' THEN '" + pdate2 + "' ELSE ENDDATE END \n " +
					 "     ,hrmorganizationid  \n " +
					 "     ,1    \n " +
					 "     , emplid  \n " +
					 "     ,''    \n " +
					 "     ,''   \n " +
					 "     ,'" + code_time_vihod + "'  \n " +
					 "     ," + strKindWorker + "    \n " +
					 ")  ),0) AS fact_workhours   \n " +
					 "      FROM dbo.ENERGYNET_FN_EMPL_HISTORY('KSOE'  \n " +
					 "                     ,  '" + pdate1 + "'  \n " +
					 "                     ,  '" + pdate2 + "'  \n " +
					 "                     , '" + renCode +"'  \n " +
					 "                     , 1  \n " +
					 "                     ,  '" + concatenatedPersonnelNumbers + "'  \n " +
					 "                     , ''  \n " +
					 "                     , '' \n " +
					 "                     , " + strKindWorker + "  \n " +
					 "                     , 0) \n " +
					 ") AS sel GROUP BY emplid \n " +
					 "                          \n ";
			stmt = mDaxConnection.prepareStatement(sql);
			set = stmt.executeQuery();
			while(set.next()) {
				String emplid = set.getString(1);
				BigDecimal hours = set.getBigDecimal(2);
				if(hours != null) {
					hours = hours.setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				result.put(emplid, hours);
			}
			if(hashForRegionalManagement.size() == 0) {
				hashForRegionalManagement.putAll(result);
			}
			return result;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {if (set != null) set.close();}             catch (SQLException e) {}
			try {if (stmt != null) stmt.close();} catch (SQLException e) {}

			try {
				if  ((mDaxConnection != null && !mDaxConnection.isClosed())){
					mDaxConnection.close();
				}
			} catch (SQLException e) {}			
		}
	}


public workerListFromKadryShortListAX getWorkerListFromAX(String pdate1 , String pdate2 , String rencode , int kindWorker , boolean forPremija)
	throws PersistenceException, SQLException, DatasourceConnectException {
	return this.getWorkerListFromAX(pdate1, pdate2, rencode, kindWorker, forPremija, null);
}

/****
 * @param forPremija - если для премии то не показывать бригады по списку
 * @throws DatasourceConnectException
 * @throws SQLException
 *
 * */
public workerListFromKadryShortListAX getWorkerListFromAX(String pdate1 , String pdate2 , String rencode , int kindWorker , boolean forPremija, Collection<String> personnelNumbers) throws PersistenceException, SQLException, DatasourceConnectException
{

     Calendar calendar = Calendar.getInstance();

     Date dateIn_1 = com.ksoe.energynet.util.Tools.clearTimeOfDate(calendar.getTime());
     
     String concatenatedPersonnelNumbers = (personnelNumbers != null) ? personnelNumbers.stream().map(Object::toString).collect(Collectors.joining(";")) : "";




	 workerListFromKadryShortListAX result = new workerListFromKadryShortListAX();
	 workerListFromKadryShortAX anObject;
	 result.list = new Vector();


 String  selectStr;

 if ((mDaxConnection == null) || (mDaxConnection.isClosed()) ){
	  mDaxConnection = getmDaxConnection();

 }
 PreparedStatement statement = null;
 ResultSet  set = null;
 JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
 UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
 
 netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

  String code_time_nevihod = "ДержОбовяз;КурсиСредн;ВідпСпорт;Медкомісія;ВідБ/ОплОб;ВідгВихідн;ВідпБ/Опл;ВідпБ/оплА;ВідпБОгод;ВідпВагПол;ВідпДКтаК;ВідпДог6р;ВідпДогДит;ВідпНавчБО;ВідпНавчОп;ВідпОсновн;ДодОснВідп;ВідпСоцДПД;ВідпСоціал;ВідпУБД;ВідпЧАЕС;ДО(Вибори);ДО(военк);Донор;ЗниклБезв;Лікарняний;Медкомісія;МедСпрБ/Оп;МобВійськС;НаркПсихол;НеявНезПри;ПідСлідств;Прогул;Суд(ОБВИН)";
  mDaxLogic mdLogic = new mDaxLogic(netConnection, userProfile );
  
  String strKindWorker = new String( (kindWorker == 1 && forPremija == true ) ? "'НВЗ_ПРЕМ;НВЗ_РЗА_ПРЕМ;НВЗ_ДТУ_ПРЕМ;НВЗ_ІЗ_ПРЕМ' " :
				        	                 ((kindWorker == 2 || kindWorker == -1 || kindWorker == -2 ) && forPremija == true ) ? "'НВЗ_Е_ПРЕМ'" :
				        	                 (kindWorker == 1 && forPremija == false ) ? "'НВЗ_Д3;НВЗ_РЗА_Д3;НВЗ_ДТУ_Д3;НВЗ_ІЗ_Д3' " :
				        	                 (kindWorker == 2 && forPremija == false ) ? "'НВЗ_Е_Д3'" :
				        	                 (kindWorker == 4 && forPremija == false ) ? "'НВЗ_ПРОЕКТ'" :
				        	                 (kindWorker == 12 && forPremija == true ) ? "'НВЗ_ПРЕМ;НВЗ_РЗА_ПРЕМ;НВЗ_ДТУ_ПРЕМ;НВЗ_ІЗ_ПРЕМ;НВЗ_Е_ПРЕМ' " :
				        	                 (kindWorker == 12 && forPremija == false ) ? "'НВЗ_Д3;НВЗ_РЗА_Д3;НВЗ_ДТУ_Д3;НВЗ_ІЗ_Д3;НВЗ_Е_Д3' " :
				        	                // 02,06,2016 - добавим условие по контрелерам , инспекторам, нач - что бы брались тоже только те которые со ставкой НВЗ_Е_ПРЕМ - михайлова ТВ присылала письмо что они не должны попадать
				        	              //   ((kindWorker == -1 || kindWorker == -2 || kindWorker == -3 ) && forPremija == true)	 ? "НВЗ_Е_ПРЕМ" :
				        	                	 "''" );
  
    String code_time_vihod = mdLogic.getPayCalendarTimeCodes("ТабФакт");
	String code_time_vihod2 = mdLogic.getPayCalendarTimeCodes("ТабКоманд");
	String code_time_vihod3 = mdLogic.getPayCalendarTimeCodes("ТабСверхур");
	
	  if (!code_time_vihod2.equals("")){
		  code_time_vihod = code_time_vihod+";"+code_time_vihod2;
	  }
	  if (!code_time_vihod3.equals("")){
		  code_time_vihod = code_time_vihod+";"+code_time_vihod3;
	  }
	  
  selectStr =
		  " select  \n" +
				  "   tabn ,	 \n" +  //1
				  "   podr_id, \n" +   //2
				  "   ( SELECT TOP(1) mainorg.DESCRIPTION \n" +
				  " 	FROM dbo.RPAYHRMORGANIZATION mainorg, dbo.RPAYHRMORGANIZATION parentorg \n" + 
				  " 	WHERE mainorg.HRMORGANIZATIONID = sel3.podr_id  \n" + 
				  " 	AND parentorg.HRMORGANIZATIONID = mainorg.PARENTORGANIZATIONID \n" + 
				  " 	AND mainorg.AZT_STARTDATE <= '"+ pdate2 +"' \n" + 
				  " 	ORDER BY mainorg.AZT_STARTDATE desc \n" +
	              "  ) as podr_nazv	, \n" +  //3
				  "   fio, \n" +  //4
				  "   post_fin	, \n" +  //5
				  "   ( SELECT TOP(1)  parentorg.HRMORGANIZATIONID as parent_orgid \n" +
				  " 	FROM dbo.RPAYHRMORGANIZATION mainorg, dbo.RPAYHRMORGANIZATION parentorg \n" + 
				  " 	WHERE mainorg.HRMORGANIZATIONID = sel3.podr_id  \n" + 
				  " 	AND parentorg.HRMORGANIZATIONID = mainorg.PARENTORGANIZATIONID \n" + 
				  " 	AND parentorg.AZT_STARTDATE <= '"+ pdate2 +"' \n" + 
				  " 	ORDER BY parentorg.AZT_STARTDATE desc \n" +
	              "  ) as main_podr_id	, \n" + //6
	              "   ( SELECT TOP(1)  parentorg.DESCRIPTION AS parent_description \n" +
				  " 	FROM dbo.RPAYHRMORGANIZATION mainorg, dbo.RPAYHRMORGANIZATION parentorg \n" + 
				  " 	WHERE mainorg.HRMORGANIZATIONID = sel3.podr_id  \n" + 
				  " 	AND parentorg.HRMORGANIZATIONID = mainorg.PARENTORGANIZATIONID \n" + 
				  " 	AND parentorg.AZT_STARTDATE <= '"+ pdate2 +"' \n" + 
				  " 	ORDER BY parentorg.AZT_STARTDATE desc \n" +
	              "  ) as podr_nazv_main	, \n" + //7
				  "   enddate , \n" + //8
				  "   sum(norma_vrem_hours) as norma_vrem_hours, \n" +  //9
				  "   sum(fact_workdays) as fact_workdays,	 \n" +  //10
				  "   sum(sumhoursotpusk) as sumhoursotpusk  \n" +  //11
				  "     , tradecategoryid \n " +          // 12 - разряд/категория
				  "   , startdate \n " + // 13- дата старта периода из табеля
				  "   , positionid \n " + // 14 -- айдишник посади 
				  " , rate \n"+ 
				  " ,  sum(fact_workhours) as fact_workhours	 \n" +  //16
				  " ,  sum(fact_workhours_overall) as fact_workhours_overall	 \n" +  //17
				  " from  \n" +
				  "     ( \n" +
				  "     select \n" +
				  "      emplid as tabn \n" +
				  "     ,hrmorganizationid as podr_id \n" +
				  "     ,description +  case when lower(rate) like '%рза%' then ' РЗйА' \n" +
				  "                          when lower(rate) like '%із%' then ' Ізоляція' \n" +
				  "                          when lower(rate) like '%дту%' then ' СДТУ' \n" +
				  "                          else '' end as podr_nazv \n" +
				  "     ,name as fio  \n" +
				  "     ,title as post_fin    \n" +
				  "     , positionid \n" +
				  "     , tradecategoryid \n " +
				  "     ,parentorganizationid as main_podr_id \n" +
				  "     ,parentdescription as podr_nazv_main \n" +
				  "     ,azt_paycalendarid \n" +
				  "     ,startdate \n" +
				  "     ,enddate \n" +
				  "     /*фонд рабочего времени за период ,без привязки к табелям по графику рабочего времени */ \n" +
				  "      , norma_vrem_hours = coalesce((SELECT round(sum(WORKHOURS),2) FROM VSE_FN_WORKING_TIME_PLAN \n" +
				  "                         ( \n" +
				  "                         'KSOE' \n" +
				  "                         ,sel2.startdate \n" +
				  "                         ,sel2.enddate \n" +
				  "                         ,sel2.azt_paycalendarid \n" +
				  "                         ,'ENET_FACT' \n" +
				  "                         ) ),0)   \n" +
				  "     /*рабочие дни за период по работнику ,с привязкой к табелям т.к формируем для факт загрузки    */ \n" +
				  "      , fact_workdays = coalesce((  SELECT sum(ROUND(TOTALDAYS,2))  FROM dbo.[ENERGYNET_FN_WORKING_TIME]  \n" +
				  "                                      ('KSOE'   \n" +
				  "                                     ,sel2.startdate \n" +
				  "                                     ,sel2.enddate \n" +
				  "                                     ,sel2.HRMORGANIZATIONID   \n" +
				  "                                     ,0  \n" +
				  "                                     ,sel2.emplid  \n" +
				  "                                     ,''  \n" +
				  "                                     ,'' \n" +
				  "                                     ,'ФАКТ;Відряджен;Пересменка;Факт' /*коды времен выхода*/ \n" +
				  "                                     ,''  \n" +
				  "                                      ) /*where lower(RPAYCALENDARTIMECODE) like '%факт%'*/ ),0)                                      \n" +
				  "     /* отпуск по сотруднику с привязкой к табелю - т.к формируем для факт загрузки  \n" +
				  "        !!!!!! добавить айдишники всяких невыходов   */ \n" +
				  "     , sumhoursotpusk =  \n" +
				  "      coalesce((  SELECT sum(ROUND(TOTALHOURS,2))  FROM dbo.[ENERGYNET_FN_WORKING_TIME]  \n" +
				  "                                      ('KSOE'   \n" +
				  "                                     ,sel2.startdate \n" +
				  "                                     ,sel2.enddate \n" +
				  "                                     ,sel2.HRMORGANIZATIONID   \n" +
				  "                                     ,0  \n" +
				  "                                     ,sel2.emplid  \n" +
				  "                                     ,''  \n" +
				  "                                     ,'' \n" +
				  "                                     ,'"+code_time_nevihod+"' \n" +
				  "                                     ,'' \n" +
				  "                                      ) /*where lower(RPAYCALENDARTIMECODE) like '%відп%'*/ ),0)                     \n" +
				  "   ,rate" + 
				  /*рабочие часы  по табелю     */ 
				  " , fact_workhours = coalesce((  SELECT sum(ROUND(totalhours,2))  FROM dbo.[ENERGYNET_FN_WORKING_TIME] \n" +  
				  "                                     ('KSOE'    \n" +
				  "                                   ,sel2.startdate  \n" +
				  "                                   ,sel2.enddate  \n" +
				  "                                   ,sel2.hrmorganizationid \n" + 
				  "                                   ,0   \n" +
				  "                                   ,sel2.emplid \n" +  
				  "                                   ,''   \n" +
				  "                                   ,''  \n" +
				  "                                   ,'"+code_time_vihod+"' \n" + 
				  "                                   ," + strKindWorker + "   \n" +
				  "                                    )  ),0) \n" + 
				  " , fact_workhours_overall = 0 \n" +
				   " from ( \n" +
				  "      SELECT  \n" +
				  "          NAME  \n" +
				  "         ,EMPLID  \n" +
				  "         ,GENDER  \n" +
				  "         ,BIRTHDATE  \n" +
				  "         ,PASSPORT_SERIES  \n" +
				  "         ,PASSPORT_NUMBER  \n" +
				  "         ,PASSPORT_DATE  \n" +
				  "         ,PASSPORT_ISSUEDBY  \n" +
				  "         ,PAYEMPLOYMENTDATE_RU  \n" +
				  "         ,PAYRESIGNEDDATE_RU  \n" +
				  "         ,PAYINN_RU  \n" +
				  "         ,ADDRESS  \n" +
				  "         ,AZT_PAYCALENDARID  \n" +
				  "         ,GROUPDISABILITY  \n" +
				  "         ,POSITIONID  \n" +
				  "         ,TITLE  \n" +
				  "         ,AZT_FIXEDSALARY  \n" +
				  "         ,RPAYTITLEPOSTING  \n" +
				  "         ,TRADECATEGORYID  \n" +
				  "         ,PERSONNELCATEGORYID  \n" +
				  "         ,PERSONNELCATEGORY  \n" +
				  "         ,RATE  \n" +
				  "         ,HRMORGANIZATIONID  \n" +
				  "         ,DESCRIPTION  \n" +
				  "         ,PARENTORGANIZATIONID  \n" +
				  "         ,PARENTDESCRIPTION  \n" +
				  "         ,OPERATIONTYPE  \n" +
				  "         ,case when STARTDATE < '"+pdate1+"' then '"+pdate1+"' else STARTDATE end as    STARTDATE \n" +
				  "         ,case when ENDDATE = '01.01.1900' or ENDDATE > '"+pdate2+"' then  '"+pdate2+"' else ENDDATE END as  ENDDATE \n" +
				  "         ,TRANSFERWITHOUTTERMCHANGES 	 \n" +
				  "      FROM dbo.ENERGYNET_FN_EMPL_HISTORY \n" +
				  "                                     ( \n" +
				  "                      /*@DATAAREAID*/ 'KSOE' \n" +
				  "                     ,/*@FROMDATE*/ /*$P{pdate1}*/ '"+pdate1+"' \n" +
				  "                     ,/*@TODATE*/ /*$P{pdate2}*/ '"+pdate2+"' \n" +
				  "                     ,/*@ORGANIZATIONLIST*/ '"+rencode+"' \n" +
				  "                     ,/*@CHILDORG*/ 1 \n" +
				  "                     ,/*@EMPLOYMENTLIST*/  '" + concatenatedPersonnelNumbers + "' \n" +
				  "                     ,/*@EMPLOYMENTNAMELIST*/ '' \n" +
				  "                     ,/*@STAFFPOSITIONLIST*/ " +
				  				 // Для премий из колл-центра/биллинга
				  				 new String( (kindWorker == -1 ) ? "'%контролер%;%доглядач%'" :
					    	                 (kindWorker == -2 ) ? "'%інспектор%'" :
					    	                 (kindWorker == -3 ) ? "'%нач%абон%;%нач%відділ%'" :
						                		 "''" )   + "\n"+
				  "                     ,/*@RATELIST*/ " + strKindWorker  + "\n"+
				  "                     ,/*@ONLYLAST*/ 0 \n" +
				  "                                     ) \n" +
				 // 29.05.2019 не попадали работники которые увольнялись посреди периода  "           where ( PAYRESIGNEDDATE_RU = '01.01.1900'  or  PAYRESIGNEDDATE_RU > '"+pdate2+"' ) -- не уволенные 
				 " where ( PAYRESIGNEDDATE_RU = '01.01.1900'  or  PAYRESIGNEDDATE_RU > '"+pdate2+"' ) -- не уволенные до периода выборки  \n" +
				///*fortezzzt*/ " and EMPLID = '070783' " +  
				  // премия по енергосбыту тут формируется только для електромонтеров
				  new String ( (kindWorker == 2 && forPremija == true) ? " and title like '%монтер%з%експлуатац%лічильн%' " : " " ) +
				  "  \n" +
				  "                         \n" +
				  "      ) as sel2                     \n" +
				  "  ) as sel3 \n" +
				  new String( (kindWorker == 12 && forPremija == false ) ? " where  ( lower( podr_nazv ) not like '%оперативно%' and  lower( podr_nazv ) not like '%відділ перевірок%' and podr_nazv not like '%диспетчерська%' and podr_nazv_main not like '%диспетчерська%' and podr_nazv not like '%механізації%' and lower(podr_nazv) not like lower('%Енергоінспекція%')  ) " : " " )  + 
				  "  group by tabn , podr_id,	podr_nazv	, fio,	post_fin, positionid , tradecategoryid	,main_podr_id	,podr_nazv_main	, enddate  , startdate ,rate \n" +
				  "  order by  tabn , enddate \n" +
				  "  \n" ;

 try {
   statement = mDaxConnection.prepareStatement(selectStr);


   set = statement.executeQuery();
   int i;
   for(i = 0;set.next();i++)    {
     anObject = new workerListFromKadryShortAX();

     anObject.tabn = set.getString(1);
     anObject.podr_nazv = set.getString(2) + ", " +set.getString(3);
     anObject.podr_id = set.getString(2);
     anObject.fio = set.getString(4);
     anObject.post_fin = set.getString(5);
     anObject.main_podr_id = set.getString(6);
     anObject.podr_nazv_main = set.getString(7);

     anObject.norma_vrem_hours = set.getBigDecimal(9);
     if(anObject.norma_vrem_hours != null)
         anObject.norma_vrem_hours = anObject.norma_vrem_hours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

     anObject.fact_workdays = set.getBigDecimal(10);
     if(anObject.fact_workdays != null)
         anObject.fact_workdays = anObject.fact_workdays.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

     anObject.sumhoursotpusk = set.getBigDecimal(11);
     if(anObject.sumhoursotpusk != null)
         anObject.sumhoursotpusk = anObject.sumhoursotpusk.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

     anObject.tradecategoryid = set.getString(12);

	 anObject.datestart = set.getDate(13);
	 anObject.datefinal = set.getDate(8);
	 anObject.positionid = set.getString(14);
	 anObject.rate = set.getString(15);
	 
	 anObject.fact_workhours = set.getBigDecimal(16);
     if(anObject.fact_workhours != null)
         anObject.fact_workhours = anObject.fact_workhours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
         
	 anObject.fact_workhours_overall = anObject.fact_workhours;
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

   try {
       if  ((mDaxConnection != null && !mDaxConnection.isClosed())){
    	   mDaxConnection.close();
       }
   } catch (SQLException e) {
   }
  }
}



public class WorkerListFromKadryShortCompare implements Comparable  {

    public String tabn ;
    public String podr_nazv;
    public String podr_id ;
    public String fio;
    public String post_fin;
    public BigDecimal norma_vrem_hours;
    public BigDecimal sumhoursotpusk;
    public String main_podr_id ;
    public String podr_nazv_main;
    public BigDecimal fact_workdays;
    public String rate;
    public BigDecimal fact_workhours;
    public BigDecimal fact_workhours_overall;


    public WorkerListFromKadryShortCompare(String tabn, String podr_nazv, String podr_id , String fio ,
    		String post_fin , BigDecimal norma_vrem_hours ,
    		BigDecimal sumhoursotpusk  , String main_podr_id , String podr_nazv_main ,BigDecimal fact_workdays, String rate , BigDecimal fact_workhours, BigDecimal fact_workhours_overall  ) {
        this.tabn = tabn;
        this.podr_nazv = podr_nazv;
        this.podr_id = podr_id;
        this.fio= fio;
        this.post_fin = post_fin;
        this.norma_vrem_hours = norma_vrem_hours ;
        this.sumhoursotpusk = sumhoursotpusk ;
        this.main_podr_id = main_podr_id;
        this.podr_nazv_main = podr_nazv_main ;
        this.fact_workdays = fact_workdays;
        this.rate = rate;
        this.fact_workhours= fact_workhours;
        this.fact_workhours_overall = fact_workhours_overall;

    }

    public void setTabn(String aValue){
       tabn = aValue;
    }

    public String getTabn(){
       return tabn;
    }
    public void setPodr_nazv(String aValue){
       podr_nazv = aValue;
    }

    public String getPodr_nazv(){
       return podr_nazv;
    }
    public void setPodr_id(String aValue){
       podr_id = aValue;
    }

    public String getPodr_id(){
       return podr_id;
    }
    public void setFio(String aValue){
       fio = aValue;
    }

    public String getFio(){
       return fio;
    }
    public void setPost_fin(String aValue){
       post_fin = aValue;
    }

    public String getPost_fin(){
       return post_fin;
    }

    public void setNorma_vrem_hours(BigDecimal aValue){
       norma_vrem_hours = aValue;
    }

    public BigDecimal getNorma_vrem_hours(){
       return norma_vrem_hours;
    }

    public void setSumhoursotpusk(BigDecimal aValue){
       sumhoursotpusk = aValue;
    }

    public BigDecimal getSumhoursotpusk(){
       return sumhoursotpusk;
    }
    public void setMain_podr_id(String aValue){
       main_podr_id = aValue;
    }

    public String getMain_podr_id(){
       return main_podr_id;
    }
    public void setPodr_nazv_main(String aValue){
       podr_nazv_main = aValue;
    }

    public String getPodr_nazv_main(){
       return podr_nazv_main;
    }

    public void setFact_workdays(BigDecimal aValue){
    	fact_workdays = aValue;
     }

     public BigDecimal getFact_workdays(){
        return fact_workdays;
     }
     
     public void setRate(String aValue){
         rate = aValue;
      }

      public String getRate(){
         return rate;
      }
      
      
      public void setFact_workhours(BigDecimal aValue){
      	fact_workhours = aValue;
       }

       public BigDecimal getFact_workhours(){
          return fact_workhours;
       }
       
       public void setFact_workhours_overall(BigDecimal aValue){
      	fact_workhours_overall = aValue;
       }

       public BigDecimal getFact_workhours_overall(){
          return fact_workhours_overall;
       }
     
     

    @Override
    public int compareTo(Object compareWork) {

		//String compareTabn=((WorkerListFromKadryShortCompare)compareWork).getTabn();
    	String comparePodrId=((WorkerListFromKadryShortCompare)compareWork).getPodr_id()+((WorkerListFromKadryShortCompare)compareWork).getPodr_nazv();
        /* For Ascending order*/
        return (this.podr_id+this.podr_nazv).compareTo(comparePodrId) ;

	}

	@Override
	public String toString() {
        return "[ tabn=" + tabn+ ", podr_nazv =" + podr_nazv +
        ",podr_id =" +podr_id +
        ",fio=" + fio +
        ",post_fin =" + post_fin+
        ",norma_vrem_hours =" + norma_vrem_hours +
        ",sumhoursotpusk =" + sumhoursotpusk +
        ",main_podr_id =" + main_podr_id+
        ",podr_nazv_main =" + podr_nazv_main +
        ",fact_workdays =" + fact_workdays +
        ",fact_workhours =" + fact_workhours + "]";
    }




}


/* заполнение временной таблицы в ростгресе из аксапты  c подразделениями из аксапты с конкатенацией
 * в сттроке табельных которые работали в подразделении за период  в разрезе подразделения   */
public BigDecimal getPodrWithConcatinateTabn(String date1 , String date2 , String mainpodrid) throws PersistenceException{
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    BigDecimal out = new BigDecimal(10);
    String sql;
    try {
    	if (connection == null){
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }
    	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    	String chktbl = "select count(*)  from information_schema.tables where table_name = 'temptablePodrAxWithTabnString'";
    	prepStatement = connection.prepareStatement(chktbl);
    	resultSet = prepStatement.executeQuery();

    	boolean temptblexist = false;
    	if (resultSet.next()){
    		if (resultSet.getInt(1) > 0  ) {
    			temptblexist = true;
    		}
    	}

    	if (temptblexist ==false ) {
    		  sql = " create temporary table temptablePodrAxWithTabnString( "
      		  		+"  podrname varchar "
      		  		+" ,podrid varchar "
      		  		+" ,mainpodrid varchar "
      		  		+" ,painpodrname "
      		  		+" ,emplidstr )";

    	} else
    		 sql = " truncate table temptablePodrAxWithTabnString; ";

    	statement = connection.createStatement();
    	statement.execute(sql);

    	OrderLogic ordlogic = new OrderLogic(connection, userProfile);
    	ENNormVolumeAVZItemShortList normAVZList = ordlogic.generateAVZListForOrder(true);

    	for (int i=0; i<normAVZList.totalCount; i++){

    	String   strNomenclatures =  normAVZList.get(i).strNomenclatures.replace( '\'', ' ' );

    	String sql2 =	" insert into temptableavz (\"countgen\" " +
									" , \"countrequired\" " +
									" , \"materialrefcode\" " +
									" , \"materialrefname\" " +
									" , \"measurementcode\" " +
									" , \"measurementname\" " +
									" , \"nomenclaturnumber\" " +
									" , \"budgetrefcode\" " +
									" , \"departmentrefcode\" " +
									" , \"strnomenclatures\" " +
									" , \"orderedcountwithoutinvoices\" " +
									" , \"countinfk\" " +
									" , \"counttoorder\" " +
									" , \"counttoordercalculated\" " +
									" , \"sumtoorder\" ) VALUES ( " +
									normAVZList.get(i).countGen +
									", " + normAVZList.get(i).countRequired +
									", " + normAVZList.get(i).materialRefCode +
									", " + normAVZList.get(i).materialRefName +
									", " + normAVZList.get(i).measurementCode +
									", " + (normAVZList.get(i).measurementName == null ? null : "'"+normAVZList.get(i).measurementName+"'") +
									", " + normAVZList.get(i).nomenclaturNumber +
									", " + normAVZList.get(i).budgetRefCode +
									", " + normAVZList.get(i).departmentRefCode +
									", " + "'"+strNomenclatures  +"'"+
									", " + normAVZList.get(i).orderedCountWithoutInvoices +
									", " + normAVZList.get(i).countInFK +
									", " + normAVZList.get(i).countToOrder +
									", " + normAVZList.get(i).countToOrderCalculated + // countToOrderCalculated поле спец для  репорта оно показывает минусовые кол-ва необходимые
									", " + normAVZList.get(i).sumToOrder + " ); ";


									;

		System.out.print(sql2);
    	statement = connection.createStatement();
        statement.execute(sql2);
    	}
        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " error  - fillingTempTableAVZ");

    } finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
        /*try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }*/
    }
    return out;

}









}




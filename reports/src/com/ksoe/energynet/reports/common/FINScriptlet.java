package com.ksoe.energynet.reports.common;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.AXOrgId2FKOrgIdDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENServicesObject2RQFKOrderDAO;
import com.ksoe.energynet.dataminer.ENServicesObjectDAO;
import com.ksoe.energynet.dataminer.ENSpravMolDAO;
import com.ksoe.energynet.logic.AuthLogic;
import com.ksoe.energynet.logic.FINLogic;
import com.ksoe.energynet.logic.FinMaterialsLogic;
import com.ksoe.energynet.logic.ManningTableLogic;
import com.ksoe.energynet.logic.SCLogic;
import com.ksoe.energynet.logic.TransportLogic;
import com.ksoe.energynet.logic.mDaxLogic;
import com.ksoe.energynet.reports.AverageAccountingPersonalNPZ.dsAccounting;
import com.ksoe.energynet.reports.NPZ_with_otpusk_pl_fact.brigadeDS;
import com.ksoe.energynet.reports.PercentPremiums.Tech.PremiumsWorkerDS;
import com.ksoe.energynet.reports.RepOrder.reestrPaymentDS.ReestrPaymentDS;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.CNSubsystemType;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.FINMaterialsStatus;
import com.ksoe.energynet.valueobject.brief.ENServicesObject2RQFKOrderShort;
import com.ksoe.energynet.valueobject.brief.FINContractsShort;
import com.ksoe.energynet.valueobject.brief.workerListFromKadryShort;
import com.ksoe.energynet.valueobject.filter.AXOrgId2FKOrgIdFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectFilter;
import com.ksoe.energynet.valueobject.filter.ENSpravMolFilter;
import com.ksoe.energynet.valueobject.filter.FINMaterialsFilter;
import com.ksoe.energynet.valueobject.filter.FINWorkerFilter;
import com.ksoe.energynet.valueobject.lists.AXOrgId2FKOrgIdShortList;
import com.ksoe.energynet.valueobject.lists.ENNormVolumeAVZItemShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesObject2RQFKOrderShortList;
import com.ksoe.energynet.valueobject.lists.ENSpravMolShortList;
import com.ksoe.energynet.valueobject.lists.FINMaterialsList;
import com.ksoe.energynet.valueobject.lists.FINWorkerShortList;
import com.ksoe.energynet.valueobject.lists.workerListFromKadryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.fin.logic.FKConsts;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.report.exception.ReportSystemException;
import com.ksoe.rqorder.dataminer.RQContactDAO;
import com.ksoe.rqorder.dataminer.RQFKOrderDAO;
import com.ksoe.rqorder.logic.OrderLogic;
import com.ksoe.rqorder.logic.RQConsts;
import com.ksoe.rqorder.valueobject.RQFKOrder;
import com.ksoe.rqorder.valueobject.RQFKOrderStatus;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;

public class FINScriptlet extends JRDefaultScriptlet{

    private transient java.sql.Connection finConnection = null;
    private transient java.sql.Connection connection = null;
    private transient java.sql.Connection netConnection = null;
    private transient java.sql.Connection cnConnection = null;

    private  class existProductObj
    {
    private String nn;
    private BigDecimal quantity;
    private Date doc_date;
    }

    private transient Vector existProduct = null;

    private Date getFirstDayInMonth(Date d){
        Date out = new Date();
        Calendar calendar = Calendar.getInstance();
        //calendar.ge
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE,1);

        out = calendar.getTime();
      //  System.out.println(out);
       // String s = "01" + "." + calendar.get(Calendar.MONTH)+1 + "." + calendar.get(Calendar.YEAR);
      //  System.out.println(s);
        return out;
    }

    protected java.sql.Connection getConnection(String dataSourceName)
            throws DatasourceConnectException {
        //System.out.println("getting connection ...");
        try {

            //finConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("finConnection");

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

        	 if (netConnection != null && !netConnection.isClosed()) {
                 return netConnection;
             }

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(dataSourceName);
            netConnection = dataSource.getConnection();
            return netConnection;

        } catch (NamingException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        } catch (SQLException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        }
    }

    protected java.sql.Connection getCNConnection(String dataSourceName)
            throws DatasourceConnectException {
        try {

        	if (cnConnection != null && !cnConnection.isClosed()) {
                return cnConnection;
            }

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(dataSourceName);
            cnConnection = dataSource.getConnection();
            return cnConnection;

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
        //finConnection = (Connection) ((JRFillParameter) this.parametersMap.get("finConnection")).getValue();
        finConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("finConnection");
        //userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
        //logic = new TransportLogic(connection, userProfile);

        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");

        //System.out.println("FINScriptlet beforeReportInit, " + new Date());
/*
        if (finConnection == null){
        try {
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        } catch (DatasourceConnectException e) {
            finConnection = null;
        }
        }
*/
    }

	public boolean isCustomerCounterAccount(String account) {
		try {
			JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
			Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
			UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

			 if (finConnection == null || finConnection.isClosed()) {

	            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
	            }



			com.ksoe.energynet.logic.SCLogic scLogic = new com.ksoe.energynet.logic.SCLogic(connection, finConnection, userProfile);
			if (account == null) {return false;}
			return scLogic.isCustomerCounterAccount(account);
		} catch(Exception e) {
			throw new SystemException(e);
		}
	}

    public java.util.AbstractMap.SimpleImmutableEntry<Boolean, String> getInvNumberBySerialNumber(String serialNumber, int servicesObjectCode) throws PersistenceException {
    	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

		try {
	        ENServicesObject2RQFKOrderDAO dao = new ENServicesObject2RQFKOrderDAO(connection, userProfile);
		ENServicesObjectDAO soDao = new ENServicesObjectDAO(connection, userProfile);
	        RQFKOrderDAO fkOrderDao = new RQFKOrderDAO(connection, userProfile);
			ENServicesObject so = soDao.getObjectNoSegr(servicesObjectCode);
			 SCLogic logic = new SCLogic(connection,
					getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE), userProfile);

		        ENServicesObjectFilter filter = new ENServicesObjectFilter();
		        filter.code = servicesObjectCode;
		        ENServicesObject2RQFKOrderShortList list = dao.getENServicesObject2RQFKOrderShortListByServicesObject(filter);

		        if(list.totalCount == 0) {
		        	throw new EnergyproSystemException(String.format("Не знайдено прибуткового ордеру передачі лічильника від абонента для договору № %s", so.contractNumberServices));
		        } else {
		        	Vector<String> invNumbers = new Vector<String>();
		        	for(ENServicesObject2RQFKOrderShort shortObj : list.list) {
		        		RQFKOrder fkOrder = fkOrderDao.getObject(shortObj.fkOrderRefCode);
		        		fkOrderDao.checkFKOrderInStatuses(fkOrder, true, RQFKOrderStatus.COUNTER_IN_SC);
		        		String invNumber = logic.getInvNumberBySerialNumber(serialNumber, fkOrder.numberDoc, false);
		        		if(invNumber != null) {
		        			invNumbers.add(invNumber);
		        			break;
		        		} else {
		        			continue;
		        		}
		        	}
		        	if(invNumbers.size() == 0) {
		        		throw new EnergyproSystemException(String.format("Для договору № %s не знайдено інвентарний номер лічильника, що передається абонентом", so.contractNumberServices));
		        	}
		        	return new java.util.AbstractMap.SimpleImmutableEntry<Boolean, String>(false, invNumbers.get(0));
		        }

		} catch (Exception e) {
			return new java.util.AbstractMap.SimpleImmutableEntry<Boolean, String>(true, e.getMessage());
		}
    }

    public BigDecimal getWorkTimeInMonth(java.sql.Date d) throws PersistenceException
    {

        boolean isDebug = true;
        if (isDebug)
        System.out.println("start getWorkTimeInMonth:" + new Date());

        Date firstDay = getFirstDayInMonth(d);

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        boolean usesMDAXDataReport = false;
        AuthLogic netAuth = new AuthLogic(connection, userProfile);
    	usesMDAXDataReport = netAuth.checkUsesMDAXDataForReport();
    	mDaxScriptlet mScr = new mDaxScriptlet();


    	if (usesMDAXDataReport == true) {
    		mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );
    		//return mScr.getCountWorkHours(new SimpleDateFormat("dd.MM.yyyy").format(firstDay) );
    		BigDecimal[] wDaysHoursArr = { new BigDecimal(0), new BigDecimal(0) };
    		wDaysHoursArr = mdLogic.getWorkingTimePlan( Tools.getFirstDayOfMonth(firstDay ) ,Tools.getLastDayOfMonth( firstDay ) , "Смена 8.2 часа Пн-Пт" );
    		BigDecimal hours = wDaysHoursArr[1];
    		return hours;
    	}
    	/** nv.grafik_id = 1 -- график рабочего времени (типа общий) */
        String sql = " select a_hours from trud.norma_vrem nv where nv.grafik_id = 1 and nv.date_rasch = ? ";

        if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + firstDay.toString());
        }

        PreparedStatement statement = null;
        ResultSet  resultSet = null;
        boolean isNewConn = false;
        BigDecimal out = new BigDecimal(0);
        try {

            if (finConnection == null){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            statement = finConnection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(firstDay.getTime()));

            resultSet = statement.executeQuery();
            if (resultSet.next())
            out = resultSet.getBigDecimal(1);


            return out;
        } catch (Exception e) {
            System.out.println(    e.getMessage() + " error 20 - getWorktimeinMonth");
            return out;
        } finally {
            if (isDebug)
            System.out.println("finally getWorkTimeInMonth:" + new Date());


            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                }
            } catch (SQLException e) {
            }
        }

    }


    public Boolean isNalogPay(String partner) throws PersistenceException
    {

        String sql = " SELECT nvl((case when s.id_nalogform in (6,20,43,44,45) then 0 else 1 end),1) as nf "+
        " FROM sprav.org s "+
        " where s.code='"+partner+"'";

        PreparedStatement statement = null;
        ResultSet  resultSet = null;
        boolean isNewConn = false;
        Boolean out = new Boolean(true);
        int outI=1;

        try {

            if (finConnection == null){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            statement = finConnection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            if (resultSet.next())
                outI = resultSet.getInt(1);

            if (outI==0)
            {
            out = new Boolean(false);
            }

            out = new Boolean(true); //Все продаем с НДС - пока
            return out;
        } catch (Exception e) {
            System.out.println(    e.getMessage());
            return out;
        } finally {
            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                }
            } catch (SQLException e) {
            }
        }

    }



    public String ebana(Integer  PcodeAkt)
    //throws PersistenceException
    {
        String out = new String("" +
                "---");
        return out;
        /*
        try {


        String sql = " Select  min(enworkorder.dategen ) as datestart , max(enworkorder.dategen ) as datefinal from " +
                     " enworkorder , enworkorder2enplanwork , enplanwork " +
                     " where enworkorder.code = enworkorder2enplanwork.workordercode " +
                     " and enplanwork.code = enworkorder2enplanwork.plancode " +
                     " and enplanwork.code in ( " +
                     " Select  enp.code " +
                     " from enact ena , enact2enplanwork ena2 , enplanwork enp " +
                     " where ena.code = " + PcodeAkt +
                     " and ena.code = ena2.actrefcode " +
                     " and ena2.plancode = enp.code ) " ;

        System.out.println("sql : " + sql);
        System.out.println("param : " + PcodeAkt);


        //try {

            PreparedStatement statement = connection.prepareStatement(sql);

            //statement.setInt(1, PcodeAkt.intValue());

            ResultSet resultSet = statement.executeQuery();
            //resultSet.next();
        //    if (!resultSet.next()) {
        //        throw new SystemException("Count work time not found for date " + d);
        //    }



            while(resultSet.next())
            {

                //   out = "Виконано робіт з " + resultSet.getString(1) + " по " + resultSet.getString(2);
                out = " mm" ;
            }



            return out;
        } catch (Exception e) {
            System.out.println(    e.getMessage());
        //    return out;
        } finally {
//            try {
                //if (connection != null && !connection.isClosed())
//                    connection.close();
            //} catch (SQLException e) {
            //}

            return out;
        }

        */
    }

    public String getRoadNumbers(Integer  PcodeAkt) throws PersistenceException
    {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("running getRoadNumbers");

        String out = new String("");



        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {
        String sql = " select  " +
        " distinct ti.numberlist " +
        " from enact2enplanwork a2p, enplanworkitem  pi, entransportitem ti " +
        " where a2p.actrefcode = ?" + // + PcodeAkt +
        " and a2p.plancode = pi.planrefcode and pi.code = ti.planitemrefcode " +
        " and pi.countgen > 0 " +
        " and ti.numberlist is not null " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        statement = connection.prepareStatement(sql);
        statement.setInt(1, PcodeAkt.intValue());

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                if (out.length() > 0)
                {
                out = out + ", " + resultSet.getString(1);
                }
                else
                {
                out = resultSet.getString(1);
                }
            }
            return out;
        }
        //catch (SQLException e) {
        //    System.out.println(    e.getMessage());
        //    throw new SystemException(e);
        //}
        catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e) ;
        }
        finally {
            try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}
        }


    }


    public String getContactValue(int departmentCode, int contactTypeCode) {
         return this.getContactValue(departmentCode, contactTypeCode, new Date());
    }

    public String getContactValue(int departmentCode, int contactTypeCode, Date date) {

        try {
            if (connection == null){
	        connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	    }

            JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
            UserProfile userProfile = (UserProfile) ((HashMap<?,?>) jrParameterMap.getValue()).get("userProfile");

            RQContactDAO contactDao = new RQContactDAO(connection, userProfile);

            return contactDao.getContactValue(departmentCode, contactTypeCode, date);
        } catch (PersistenceException e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e);
		} finally {

        }
    }



	public String getSettings(String key) {
		return this.getSettings(key, new Date(), false);
	}

	public String getSettings(String key, Date date) {
		return this.getSettings(key, date, false);
	}

	public String getSettings(String key, Date date, boolean capitalizeFirstLetter) {

		if (date == null)
			date = new Date();

		String out = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String sql = "select sv.value as value from ensettings s "
			+ "inner join ensettingsvalues sv on s.code = sv.settingsrefcode where s.key = ? "
			+ "and ? between sv.datestart and coalesce(sv.datefinal, ?) ";

		try {
			if (netConnection == null || netConnection.isClosed()) {
				netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
			}
			statement = netConnection.prepareStatement(sql);
			statement.setString(1, key);
			statement.setDate(2, new java.sql.Date(date.getTime()));

			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			c.set(Calendar.YEAR, 9999);
			c.set(Calendar.DATE, 31);
			Date date9999 = Tools.clearTimeOfDate(c.getTime());

			statement.setDate(3, new java.sql.Date(date9999.getTime()));

			resultSet = statement.executeQuery();

			byte count = 0;
			while(resultSet.next()) {
				count++;
				if(count > 1) {
					throw new RuntimeException(String.format("Помилка у кількості знайдених налаштувань для ключа %s на дату %s", key, new SimpleDateFormat("dd.MM.yyyy").format(date)));
				}
				out = resultSet.getString("value");
			}

			if(count == 0) {
				throw new RuntimeException(String.format("Помилка у кількості знайдених налаштувань для ключа %s на дату %s", key, new SimpleDateFormat("dd.MM.yyyy").format(date)));
			}

			if(capitalizeFirstLetter) {
				out = out.substring(0, 1).toUpperCase() + out.substring(1);
			}

			return out;

		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			throw new SystemException(e);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}

			try {
				if (netConnection != null && !netConnection.isClosed())
					netConnection.close();
			} catch (SQLException e) {
			}

		}
	}


    public String getRoadNumbers(Integer  PcodeAkt, Integer transportCode) throws PersistenceException
    {
        boolean isDebug = false;

        if (isDebug)
            System.out.println("running getRoadNumbers (2)");

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {
        String sql = " select  " +
        " distinct ti.numberlist " +
        " from enact2enplanwork a2p, enplanworkitem  pi, entransportitem ti " +
        " where a2p.actrefcode = ? " + // + PcodeAkt +
        " and ti.transportrealcode = ? " +
        " and a2p.plancode = pi.planrefcode and pi.code = ti.planitemrefcode " +
        " and pi.countgen > 0 " +
        " and ti.numberlist is not null " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        statement = connection.prepareStatement(sql);
        statement.setInt(1, PcodeAkt.intValue());
        statement.setInt(2, transportCode.intValue());

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                if (out.length() > 0)
                {
                out = out + ", " + resultSet.getString(1);
                }
                else
                {
                out = resultSet.getString(1);
                }
            }
            return out;
        }
        //catch (SQLException e) {
        //    System.out.println(    e.getMessage());
        //    throw new SystemException(e);
        //}
        catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e);
        }
        finally {
            try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}
        }


    }

        public String getRoadNumbersIncome(Integer  PcodeAkt, Integer transportCode) throws PersistenceException
        /*Ф-ция вытаскивает путевые листы для доходного акта услуг по договорам и ремонтам по коду приходного
        акта и коду машины*/
    {
        boolean isDebug = false;

        if (isDebug)
            System.out.println("running getRoadNumbers (2)");

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {
        String sql = " select  " +
        " distinct ti.numberlist " +
        " from enact2enplanwork a2p, enplanworkitem  pi, entransportitem ti, enactincome2enact as aiac  " +
        " where aiac.actincomerefcode = ? " + // + PcodeAkt +
        " and a2p.actrefcode = aiac.actrefcode " +
        " and ti.transportrealcode = ? " +
        " and a2p.plancode = pi.planrefcode and pi.code = ti.planitemrefcode " +
        " and pi.countgen > 0 " +
        " and ti.numberlist is not null " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        statement = connection.prepareStatement(sql);
        statement.setInt(1, PcodeAkt.intValue());
        statement.setInt(2, transportCode.intValue());

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                if (out.length() > 0)
                {
                out = out + ", " + resultSet.getString(1);
                }
                else
                {
                out = resultSet.getString(1);
                }
            }
            return out;
        }
        //catch (SQLException e) {
        //    System.out.println(    e.getMessage());
        //    throw new SystemException(e);
        //}
        catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e);
        }
        finally {
            try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}
        }


    }


    public String getnaryadi(Integer  PcodeAkt) throws PersistenceException
    {
        boolean isDebug = false;

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {


        String sql = " Select enworkorder.workordernumber ||' (' ||to_char( enworkorder.dategen ,'dd.mm.yyyy') ||')' as naryad from " +
                     " enworkorder , enworkorder2enplanwork , enplanwork " +
                     " where enworkorder.code = enworkorder2enplanwork.workordercode " +
                     " and enplanwork.code = enworkorder2enplanwork.plancode " +
                     " and enplanwork.code in ( " +
                     " Select  enp.code " +
                     " from enact ena , enact2enplanwork ena2 , enplanwork enp " +
                     " where ena.code = " + PcodeAkt +
                     " and ena.code = ena2.actrefcode " +
                     " and ena2.plancode = enp.code ) " ;
        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        //try {

            statement = connection.prepareStatement(sql);

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();
            //resultSet.next();
        //    if (!resultSet.next()) {
        //        throw new SystemException("Count work time not found for date " + d);
        //    }



            while(resultSet.next())
            {


                if (out.length() > 0)
                {
                out = out + ", " + resultSet.getString(1);


                }
                else
                {
                out = resultSet.getString(1);
                }
            }
                        return out;
        } catch (Exception e) {
            System.out.println(    "error in getnaryadi , actCode = PcodeAkt, " + e.getMessage());
            //throw new SystemException(e);
            return out;
        } finally {

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

//            try {
                //if (connection != null && !connection.isClosed())
//                    connection.close();
            //} catch (SQLException e) {
            //}

            //return out;
        }


    }

    public String getcommentzku(Integer  PcodeAkt) throws PersistenceException
    {
        boolean isDebug = false;

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {


        String sql =  " Select  enp.commentgen " +
        " from enact ena , enact2enplanwork ena2 , enplanwork enp "+
        " where ena.code =  " + PcodeAkt  +
        " and ena.code = ena2.actrefcode  "+
        " and ena2.plancode = enp.code  ";

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
          int i;
            i=0;
          String personalacc;


            while(resultSet.next())
            {
              i++;
              personalacc = resultSet.getString(1);
              if ((resultSet.wasNull())||(personalacc==null))
                {personalacc="";}

                if (out.length() > 0)
                {
                out = out + ", " + personalacc;
                if (i==5)
                {
                    out = out + "\n";
                    i=0;
                }
                }
                else
                {
                out = resultSet.getString(1);
                }
            }
                        return out;
        } catch (Exception e) {
            System.out.println(    "error in getcommentzku , actCode = PcodeAkt, " + e.getMessage());
            return out;
        } finally {

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

        }


    }





public String getincomenaryadi(Integer  PcodeAkt) throws PersistenceException
/* Выбирает наряды для доходного акта услуг по договорам на ТО и ремонты */
    {
        boolean isDebug = false;

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {


        String sql = " select " +
                        " actrefcode " +
                    " from " +
                        " enactincome2enact " +
                    " where " +
                    " actincomerefcode = " + PcodeAkt;
            ;
        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        //try {

            statement = connection.prepareStatement(sql);

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();
            //resultSet.next();
        //    if (!resultSet.next()) {
        //        throw new SystemException("Count work time not found for date " + d);
        //    }



            while(resultSet.next())
            {


                if (out.length() > 0)
                {
                out = out + ", " + getnaryadi(new Integer(resultSet.getInt(1)));


                }
                else
                {
                out = getnaryadi(new Integer(resultSet.getInt(1)));
                }
            }


            return out;
        } catch (Exception e) {
            System.out.println(    "error in getincomenaryadi , actCode = PcodeAkt, " + e.getMessage());
            //throw new SystemException(e);
            return out;
        } finally {

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

//            try {
                //if (connection != null && !connection.isClosed())
//                    connection.close();
            //} catch (SQLException e) {
            //}

            //return out;
        }


    }

    public String getincomeobjects(Integer  PcodeAkt) throws PersistenceException
    {
        boolean isDebug = false;

        String out = new String("");

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {


        String sql = " select distinct " +
                        " el.ename " +
                    " from " +
                    "    enelementdata as el, " +
                    "    enactincome2enact as aiac, " +
                    "    enact2enplanwork as acpw, " +
                    "    enplanwork as pw " +
                    " where " +
                    "    aiac.actincomerefcode =  " + PcodeAkt +
                    "    and el.ecode = pw.elementrefcode " +
                    "    and pw.code = acpw.plancode " +
                    "    and acpw.actrefcode = aiac.actrefcode " ;
        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodeAkt);
        }

        if (connection == null){
            //System.out.println("connection=null");
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        //try {

            statement = connection.prepareStatement(sql);

            //statement.setInt(1, PcodeAkt.intValue());

            resultSet = statement.executeQuery();
            //resultSet.next();
        //    if (!resultSet.next()) {
        //        throw new SystemException("Count work time not found for date " + d);
        //    }



            while(resultSet.next())
            {


                if (out.length() > 0)
                {
                out = out + ", " + resultSet.getString(1);


                }
                else
                {
                out = resultSet.getString(1);
                }
            }



            return out;
        } catch (Exception e) {
            System.out.println(    "error in getincomeobjects , actCode = PcodeAkt, " + e.getMessage());
            //throw new SystemException(e);
            return out;
        } finally {

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

//            try {
                //if (connection != null && !connection.isClosed())
//                    connection.close();
            //} catch (SQLException e) {
            //}

            //return out;
        }


    }

    public BigDecimal getESV(Integer  PcodeAkt, Integer PIsElteh) throws PersistenceException
/* Выбирает посчитанный поактно и просумированный ЄСВ (Єдиний соціальний внесок) - 37.06%
        (из-за расбежностей сумм в акте приходном и составляющих его расходных актов*/
    {
        boolean isDebug = false;

        BigDecimal out = new BigDecimal(0);

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {


            String sql = " select " +
                        " coalesce(sum(round(cast(payswork* 0.3706 as decimal),2)),0) as ESV " +
                    " from " +
                        " (select " +
                        "     actrefcode, " +
                        "    sum(payswork) as payswork " +
                        " from " +
                        " (select distinct " +
                        "     achu.code, " +
                        "     achu.actrefcode, " +
                        "     achu.paysworkbonus as payswork " +
                        " from " +
                        "     enactincome2enact as aiac " +
                        "     left join " +
                        "    enact2humen as achu " +
                        "    on " +
                        "    aiac.actrefcode = achu.actrefcode " +
                        " where " +
                        "     aiac.actincomerefcode = " + PcodeAkt +
                        "    and " +
                        "    achu.humenkindrefcode = " + PIsElteh + ") as query1 " +
                        " group by " +
                        "    actrefcode) as query2 ";

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("PcodeAkt : " + PcodeAkt);
            System.out.println("PIsElteh : " + PIsElteh);
        }

        if (connection == null){
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                out = resultSet.getBigDecimal(1);
            }


            return out;
        } catch (Exception e) {
            System.out.println(    "error in getESV , actCode = PcodeAkt, " + e.getMessage());
            //throw new SystemException(e);
            return out;
        } finally {

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

        }


    }

    /**
    *
    * @param PcodeAkt код акта
    *
    * Выбирает сумму доработанных материалов по коду акта
    *
    */
    public BigDecimal getSumRefined(Integer  PcodeAkt) throws PersistenceException
        {
            boolean isDebug = false;

            BigDecimal out = new BigDecimal(0);

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            try {


                String sql = "select ma.cost from " +
                            " enplanwork as pw " +
                            " inner join enact2enplanwork as acpw on acpw.plancode = pw.code " +
                            " inner join enestimateitem as es on pw.code = es.planrefcode " +
                            " inner join finmaterials as ma on ma.estimateitemrefcode = es.code " +
                            " where " +
                            " es.kindrefcode = " + ENEstimateItemKind.REFINEMENT +
                            " and acpw.actrefcode = " + PcodeAkt;

            if (isDebug){
                System.out.println("sql : " + sql);
                System.out.println("PcodeAkt : " + PcodeAkt);
            }

            if (connection == null){
                connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
            }

                statement = connection.prepareStatement(sql);

                resultSet = statement.executeQuery();

                while(resultSet.next())
                {
                    out = resultSet.getBigDecimal(1);
                }


                return out;
            } catch (Exception e) {
                System.out.println(    "error in getSumRefined , actCode = PcodeAkt, " + e.getMessage());
                //throw new SystemException(e);
                return out;
            } finally {

                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

            }


        }

    /**
    *
    * @param PcodeAkt код акта
    *
    * Выбирает сумму материалов до доработки по коду акта
    *
    */
    public BigDecimal getSumBeforeRefined(Integer  PcodeAkt) throws PersistenceException
        {
            boolean isDebug = false;

            BigDecimal out = new BigDecimal(0);

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            try {


                String sql = "    select " +
                            "        ma_beforerefined.cost " +
                            " from " +
                            "     enestimateitem as es_beforerefined inner join enestimateitem as es_refined " +
                            "     on (es_beforerefined.planrefcode = es_refined.planrefcode " +
                            "     and es_beforerefined.materialrefcode = es_refined.materialrefcode " +
                            "     and (es_beforerefined.planitemrefcode = es_refined.planitemrefcode " +
                            "     or es_refined.planitemrefcode is null)) " +
                            "     inner join finmaterials as ma_beforerefined on ma_beforerefined.estimateitemrefcode = es_beforerefined.code " +
                            "     inner join enact2enplanwork as acpw on acpw.plancode = es_refined.planrefcode " +
                            " where es_refined.kindrefcode = " + ENEstimateItemKind.REFINEMENT +
                            "     and es_beforerefined.kindrefcode = " + ENEstimateItemKind.MATERIALS +
                            "	and ma_beforerefined.statusrefcode = " + FINMaterialsStatus.GOOD +
                            "     and acpw.actrefcode = " + PcodeAkt;

            if (isDebug){
                System.out.println("sql : " + sql);
                System.out.println("PcodeAkt : " + PcodeAkt);
            }

            if (connection == null){
                connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
            }

                statement = connection.prepareStatement(sql);

                resultSet = statement.executeQuery();

                while(resultSet.next())
                {
                    out = resultSet.getBigDecimal(1);
                }


                return out;
            } catch (Exception e) {
                System.out.println(    "error in getSumBeforeRefined , actCode = PcodeAkt, " + e.getMessage());
                //throw new SystemException(e);
                return out;
            } finally {

                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

            }


        }

    public BigDecimal getDepreciation_NO_USING_(String inv_num , Date dd) throws PersistenceException
    {


        boolean isDebug = false;

        if (isDebug)
            System.out.println("start getDepreciation:" + new Date());

        Date firstDay = getFirstDayInMonth(dd);


String sql =
    " /* выбираем суммарный износ по бухучету на указанный месяц */" +
    " SELECT a.num_un, sum(a.sum_izn_b) sum_izn_b " +
    "  FROM os_t.izn_nachisl a , os_t.ostable oo " +
    " where a.num_un = oo.num_un " +
    "   and oo.kod_inv = ? " +
    "   and a.dt_nachisl = ? " +
    "  group by  a.num_un " +
    "  " +
    " union " +
    "  " +
    " /*-- выбираем суммарный износ по бухучету на текущий месяц, */" +
    " /*--если месяц выборки больше бухгалтерского месяца */" +
    " SELECT a.num_un, sum(a.sum_izn_b) sum_izn_b " +
    "   FROM os_t.izn_nachisl a , os_t.ostable oo " +
    "  where a.num_un = oo.num_un " +
    "    and oo.kod_inv = ? " +
    "    and a.dt_nachisl = (select dt_current from os_t.osdate) " +
    "    and (select dt_current from os_t.osdate) < ? " +
    "  group by  a.num_un " ;

// опачки ... перестало работать .. Андрюха кидал почтой ...

//        String sql =  //"    /* селект амортизации из данных за последний квартал*/ " +
//     " Select cast(sum_izn_b as numeric(15,2)) as qq From( " +
//                  " SELECT max(a.num_un) as num_un, a.sum_izn_b " +
//                  "  FROM os_t.izn_nachisl a , os_t.ostable oo " +
//                  " Where a.num_un = oo.num_un " +
//                  "   And oo.kod_inv = ? " +
//                  "   And a.dt_nachisl = ? " +
//                  "   Group by  a.sum_izn_b ) " +
//        " UNION " +
//
//        " Select sum_izn_b From ( " +
//                  " SELECT max(rb.num_un) as num_un, rb.sum_izn_b " +
//                  "  FROM os_t.izn_nachisl_rb rb , os_t.ostable oo " +
//                  " Where rb.num_un = oo.num_un " +
//                  "   And oo.kod_inv = ? " +
//                  "    And rb.dt_nachisl = ? " +
//                  "   Group by  rb.sum_izn_b ) " +
//        " UNION " +
//              //  " /* селект амортизации (если нет амортизации за тек квартал и за предыдущие ) если выбирается амортизац за дату большую чем в бух учете  */ " +
//        " Select sum_izn_b From ( " +
//                  " SELECT max(inc.num_un) as num_un, inc.sum_izn_b " +
//                  "  FROM os_t.izn_nachisl inc , os_t.ostable oo " +
//                  " Where inc.num_un = oo.num_un " +
//                  "   And oo.kod_inv = ? " +
//                  "   And inc.dt_nachisl = Trunc((Select dt_current from os_t.osdate),'mm') " +
//                  "   and Trunc((Select dt_current from os_t.osdate),'mm') < ? " +
//                  "   Group by  inc.sum_izn_b ) " ;

        // sql = "select 1 from dual " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + firstDay.toString());
            System.out.println("param : " + inv_num);
        }


        PreparedStatement statement = null;
        ResultSet  resultSet = null;
        boolean isNewConn = false;

        BigDecimal out = new BigDecimal(0);
        try {
            //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
            //System.out.println("try");
            //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            if ( finConnection.isClosed() ){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }


            statement = finConnection.prepareStatement(sql);
            //System.out.println("prepare");
            statement.setString(1, inv_num);
            statement.setDate(2, new java.sql.Date(firstDay.getTime()));
            statement.setString(3, inv_num);
            statement.setDate(4,  new java.sql.Date(firstDay.getTime()));
            //statement.setString(5, inv_num);
            //statement.setDate(6,  new java.sql.Date(firstDay.getTime()));

        //    System.out.println(dd);
        //    System.out.println(new java.sql.Date(firstDay.getTime()));
        //    System.out.println(inv_num);

            resultSet = statement.executeQuery();
            //System.out.println("execSQL");
            if (resultSet.next()){
            out = resultSet.getBigDecimal(2);
            //System.out.println("Deprication :" + out );
            }
            else{
                System.out.println("NOT resultSet.next() for deprication" );
                throw new SystemException("Avto not found :"+inv_num);
            }

        //    out = new BigDecimal(1);

            if (isDebug)
                System.out.println("return :" + out);

            return out;
        }

            catch (SQLException e) {
                //out = new BigDecimal(2);
                System.out.println(    e.getMessage()+ " erorrr 21 - getdepreciation_no_using");
                //finConnection.
                //return out;
                throw new PersistenceException(e.getMessage());


        }
            catch (Exception e) {
                //out = new BigDecimal(2);
                System.out.println(    e.getMessage()+ " error 22 - getdepreciation_no_using");
                //throw new Exception(e.getMessage());
                throw new PersistenceException("Error in method getDepreciation()",e);
                //finConnection.
                //return out;


        }
            finally {

                //System.out.println("finally getDepreciation:" + new Date());

            //System.out.pr
            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {
                if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                    finConnection.close();
                //System.out.println("Close connn in getDeeprication");
            } catch (SQLException e) {
            }
        }

    }



    public BigDecimal getDepreciation(String inv_num , Date dd) throws PersistenceException
    {


        boolean isDebug = false;

        if (isDebug)
            System.out.println("start getDepreciation:" + new Date());

        Date firstDay = getFirstDayInMonth(dd);


        if (isDebug){
            System.out.println("param : " + firstDay.toString());
            System.out.println("param : " + inv_num);
        }


        boolean isNewConn = false;

        BigDecimal out = new BigDecimal(0);
        try {

            if (finConnection == null){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            if ( finConnection.isClosed() ){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
            UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

            FINLogic fLogic = new FINLogic(finConnection, userProfile);

            out = fLogic.getDepreciation(inv_num, firstDay);


            if (isDebug)
                System.out.println("return :" + out);

            return out;
        }

            catch (SQLException e) {
                System.out.println(    e.getMessage() + "error23 - getdepreciation");
                throw new PersistenceException(e.getMessage());
        }
            catch (Exception e) {
                System.out.println(e.getMessage() + "error24 - getdepreciation");
                throw new PersistenceException("Error in method getDepreciation()",e);
        }
            finally {

            try {
                if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                    finConnection.close();

            } catch (SQLException e) {
            }
        }

    }



    public BigDecimal getCountEltehpersonalFromKadry(String pdate_srez , Integer ppodrcod , String type_pers  ) throws PersistenceException
    {

        boolean isDebug = false;

        BigDecimal out = new BigDecimal(0);

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        if (isDebug)
            System.out.println("start getCountEltehpersonalFromKadry" + new Date());

        String sql = " SELECT  count(*) as count  from ( "+
        //" SELECT /*+ RULE */ "
        " SELECT "
        + "/*-- Назначение на Должность*/ "

    + " n.Karta_Id, "
    + " kr.TabN, "
    + " fio.F||' '||fio.I||' '||fio.O FIO "

    + "  FROM "
    + " kadry.Prom            prom, "
    + " kadry.Kategory        k, "
    + " (SELECT gp2.Grafik_Id, gp2.Norma "
    + "  FROM  kadry.Grafik_Period  gp2 "
    + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
    + "     AND rownum<=1) g, "
    + " kadry.Doljnost      d, "
    + " kadry.Doljnost_Day  dd, "
    + " (SELECT p.Id             Podr_Id, "
    + "        p.Date_Start     Podr_Date_Start, "
    + "        p.Date_Finish    Podr_Date_Finish, "
    + "        pd.ROWID         Podr_Day_Row_Id, "
    + "        pd.Podr_Id       Podr_Day_Podr_Id, "
    + "        pd.Date_Start    Podr_Day_Date_Start, "
    + "        pd.Main_Podr_Id  Main_Podr_Id, "
    + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
    + "        pd.Nazv          Podr_Nazv, "
    + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
    + "        pd.Kod           Podr_Kod, "
    + "        pd.Prom_Id       Podr_Prom_Id , "
    + "        pd.Pos           Podr_Pos, "
    + "        pd.Vne_Shtata    Vne_Shtata, "
    + "        ceh.ROWID        Ceh_Row_Id, "
    + "        ceh.Podr_Id      Ceh_Id, "
    + "        ceh.Date_Start   Ceh_Date_Start, "
    + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
    + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
    + "        ceh.Nazv         Ceh_Nazv, "
    + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
    + "        ceh.Kod          Ceh_Kod, "
    + "        ceh.Prom_Id      Ceh_Prom_Id, "
    + "        ceh.Pos          Ceh_Pos "
    + "   FROM kadry.Podr_Day  ceh, "
    + "        kadry.Podr_Day  pd, "
    + "        kadry.Podr      p "
    + "  WHERE pd.Podr_Id(+) = p.Id "
    + "    AND (pd.Podr_Id IS NULL "
    + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day pd1 "
    + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
    + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "        ) "
    + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day ceh1 "
    + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
    + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND p.Id = " + ppodrcod
    + "  ) pd, "
    + " kadry.v_Post       ps, "
    + " kadry.v_Post       ps1, "

    + " /*-- предыдущее назначение*/ "
    + " kadry.Perevod_Vid  pv, "
    + " kadry.Osnova       os, "
    + " kadry.Nazn         n2, "
    + " kadry.Doljnost     d2, "
    + " kadry.Post         ps2, "
    + " kadry.Razr         rz2, "
    + " kadry.Prof         pf2, "
    + " /*-- следующее назначение*/ "
    + " kadry.Perevod_Vid  pv3, "
    + " kadry.Osnova       os3, "
    + " kadry.Nazn         n3, "
    + " kadry.Doljnost     d3, "
    + " kadry.Post         ps3, "
    + " kadry.Razr         rz3, "
    + " kadry.Prof         pf3, "

    + " kadry.FIO          fio, "
    + " kadry.Karta        kr, "
    + " kadry.Nazn_Vid     nv, "
    + " kadry.Pricaz       pr1, "
    + " kadry.Pricaz       pr2, "
    + " kadry.Pricaz       pr3, "
    + " kadry.Nazn_Day     nd, "
    + " kadry.Nazn         n "

    + " WHERE (nd.Nazn_Id = n.Id "

    + " /*-- Выбор параметров на дату среза*/ "
    + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + " AND NOT EXISTS "
    + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
    + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
    + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND nd2.Date_Start > nd.Date_Start) "
    + " AND n.Start_Pricaz_Id  = pr1.Id "
    + " AND n.Finish_Pricaz_Id = pr2.Id "
    + " AND nd.Pricaz_Id       = pr3.Id "
    + " AND n.Doljnost_Id = d.Id "
    + " AND pd.Podr_Id = d.Podr_Id "
    + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
    + " AND ps1.Post_Id = d.Post_Id "
    + " AND k.Id = d.Kategory_Id "
    + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
    + " AND dd.Doljnost_Id(+) = d.Id "
    + " /*-- Выбор параметров должности на дату*/ "
    + " AND (dd.Doljnost_Id IS NULL "
    + "   OR (dd.Date_Start <= n.Date_Finish  "
    + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
    + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
    + "                        AND dd2.Date_Start <= n.Date_Finish  "
    + "                            AND dd2.Date_Start > dd.Date_Start))) "
    + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
      + "    /*-- предыдущее назначение*/ "
      + "      AND pv.Id(+) = n.Perevod_Vid_Id "
    + "      AND os.Id(+) = n.Perevod_Osnova_Id "
    + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
      + "      AND n2.Doljnost_Id = d2.Id(+) "
      + "      AND ps2.Id(+) = d2.Post_Id "
    + "      AND pf2.Id(+) = ps2.Prof_Id "
    + "      AND rz2.Id(+) = ps2.Razr_Id   "
    + "    /*-- сдедующее назначение*/ "
    + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
   + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
   + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
   + " AND d3.Id(+)  = n3.Doljnost_Id "
   + " AND ps3.Id(+) = d3.Post_Id "
   + " AND pf3.Id(+) = ps3.Prof_Id "
   + " AND rz3.Id(+) = ps3.Razr_Id   "
   + " AND n.Karta_Id     = kr.Id "
   + " AND  upper(kr.prim) like ('%"+type_pers+"%')   "
   + " AND fio.Id         = kr.FIO_Id "
   + " AND nd.Nazn_Vid_Id = nv.Id(+) "
   + "    /*-- график назначения*/ "
    + " ) AND  "
    + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
    + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
//    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
    + " ORDER BY kr.TabN , n.Date_Start desc  " +
        " ) " ;


    //    sql = "select count(*) count from kadry.Doljnost dd  Where dd.date_start >= ? " ;

        if (isDebug){
            System.out.println(" sql : " + sql);
            System.out.println("parampodrcod : " + ppodrcod.toString());
            System.out.println("paramdate : " + pdate_srez.toString());
            System.out.println("paramdate2 : " + pdate_srez);
        }

        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //finConnection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();



        boolean isNewConn = false;

        try {


            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            statement = finConnection.prepareStatement(sql);

        /*    statement.setDate(1, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(2, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(3, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(4, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(5, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(6, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(7, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(8, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(9, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(10, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(11, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(12, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(13, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(14, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(15, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(16, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(17, new java.sql.Date(pdate_srez.getTime()));
            statement.setDate(18, new java.sql.Date(pdate_srez.getTime()));
            statement.setInt(19, ppodrcod.intValue()); */


            resultSet = statement.executeQuery();
            //resultSet.next();
        //    if (!resultSet.next()) {
        //        throw new SystemException("Count work time not found for date " + d);
        //    }

            if (resultSet.next())
            out = resultSet.getBigDecimal(1);
            else
              System.out.println("resultSet clear getCountEltehpersonalFromKadry");

            //System.out.println(); //new java.sql.Date(firstDay.getTime()));

            //System.out.println("paramdate2 : " + out);
            return out;
        } catch (Exception e) {
            System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadry");
            return out;
        } finally {

            //System.out.println("finally getCountEltehpersonalFromKadry" + new Date());

            try {
                if (resultSet != null)
                    resultSet.close();
                }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (statement != null)
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                        finConnection.close();
                        finConnection = null;
                    }
                        //System.out.println("Close connect");
                } catch (SQLException e) {
                    System.out.println(e.getMessage() + "erorrr2 getCountEltehpersonalFromKadry");
                }
        }

    }


    public String addYear(String d, int y) throws ParseException
    {
        String out = new Date().toString();
        //System.out.println("addYear : In Date" + d);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date d1 = formatter.parse(d);
        Calendar calendar = Calendar.getInstance();
        //calendar.ge
        calendar.setTime(d1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.YEAR, y);
        Date d2 = calendar.getTime();
        out = formatter.format(d2);
        //System.out.println("addYear : Out Date" + out);
        return out;
    }

    // выборка количества рабочих дней в месяце
    public Integer getCountWorkDay(String pdate_srez ) throws PersistenceException, ParseException
    {
        boolean isDebug = false;

        // Date firstDay = getFirstDayInMonth(pdate_srez);
        int out = 0;

        if (isDebug)
            System.out.println("start getCountWorkDay:" + new Date());

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        boolean usesMDAXDataReport = false;
        AuthLogic netAuth = new AuthLogic(connection, userProfile);
    	usesMDAXDataReport = netAuth.checkUsesMDAXDataForReport();
    	mDaxScriptlet mScr = new mDaxScriptlet();


    	if (usesMDAXDataReport == true) {
    		mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );
    		//return mScr.getCountWorkHours(new SimpleDateFormat("dd.MM.yyyy").format(firstDay) );
    		BigDecimal[] wDaysHoursArr = { new BigDecimal(0), new BigDecimal(0) };
    		Date firstDay = this.getFirstDayInMonth(new SimpleDateFormat("dd.MM.yyyy").parse(pdate_srez));
    		wDaysHoursArr = mdLogic.getWorkingTimePlan( Tools.getFirstDayOfMonth(firstDay ) ,Tools.getLastDayOfMonth( firstDay ) , "Смена 8.2 часа Пн-Пт" );
    		BigDecimal days = wDaysHoursArr[0];
    		return days.intValue();
    	}

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        PreparedStatement statement2 = null;
        ResultSet resultSet2 =  null;

        boolean isNewConn = false;
        try {

            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

        String sql = " alter session "
                + " set   NLS_TERRITORY=AMERICA "
                + " NLS_LANGUAGE=AMERICAN "
                + " OPTIMIZER_MODE=RULE " ;
        statement = finConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

        sql = "";
        sql = " SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10, "
        + " rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
        + " rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 "
          + " FROM   zarpherson.grafik_work_view "
        + " WHERE  date_rasch =  trunc(to_date('" + pdate_srez + " ','dd.mm.yyyy'),'mm')"
        + " and  work_time_id = 1 " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("paramworkday : " + pdate_srez.toString());
        }



            // PreparedStatement statement2 = finConnection.prepareStatement(sql);
            // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));

            statement2 = finConnection.prepareStatement(sql);
            resultSet2 = statement2.executeQuery();


            int i;
            if (isDebug)
                System.out.println(" дергаем строку  " );
        if    (resultSet2.next())
        {
            if (isDebug) System.out.println(" начинается цикл  " );
            for(i = 1; i < 32 ;i++)
            {
                if (isDebug) System.out.println("колонка  : " + i );
            if(resultSet2.getDouble(i) > 0 )
                {
                out = out + 1 ;
                if (isDebug) System.out.println("значение  : " + resultSet2.getDouble(i));
                }

            }
        }


            return new Integer( out );
        } catch (Exception e) {
            System.out.println(    e.getMessage() + "erorr3 - getCountWorkDay");
            return new Integer( out );
        } finally {

            if (isDebug) System.out.println("finally getCountWorkDay:" + new Date());

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
            try {if (statement2 != null) statement2.close();} catch (SQLException e) {}


                try {
                    if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                        finConnection.close();
                    finConnection = null;}
                } catch (SQLException e) {
                }
        }

    }

    // выборка количества рабочих часов в месяце
    public BigDecimal getCountWorkHours(String pdate_srez ) throws PersistenceException
    {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("start getCountWorkHours:" + new Date());

        // Date firstDay = getFirstDayInMonth(pdate_srez);
        BigDecimal out = new BigDecimal(0);
        BigDecimal sumHours = new BigDecimal(0);

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        PreparedStatement statement2 = null;
        ResultSet resultSet2 = null;

    // BigDecimal qq =    new BigDecimal( Math.ceil(  (new BigDecimal(new BigDecimal(1).divide(new BigDecimal(1))) ).doubleValue()   ) );


    // new BigDecimal( Math.ceil( ( new BigDecimal($V{var_hh_month1}.divide($P{count_work_hours_month1}))  ).doubleValue() ) )
        boolean isNewConn = false;

        try {
        String sql = " alter session "
                + " set   NLS_TERRITORY=AMERICA "
                + " NLS_LANGUAGE=AMERICAN "
                + " OPTIMIZER_MODE=RULE " ;

        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

        sql = "";
        sql = " SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10, "
        + " rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
        + " rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 "
          + " FROM   zarpherson.grafik_work_view "
        + " WHERE  date_rasch =  trunc(to_date('" + pdate_srez+ " ','dd.mm.yyyy'),'mm')"
        + " and  work_time_id = 1 " ;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("paramworkday : " + pdate_srez.toString());
        }



            // PreparedStatement statement2 = finConnection.prepareStatement(sql);
            // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
        if (finConnection == null)
            return new BigDecimal(Integer.MIN_VALUE);

            statement2 = finConnection.prepareStatement(sql);
            resultSet2 = statement2.executeQuery();


            int i;
            if (isDebug)
                System.out.println(" дергаем строку  " );

        if    (resultSet2.next())
        {
            if (isDebug)
                System.out.println(" начинается цикл  " );
            for(i = 1; i < 32 ;i++)
            {
                if (isDebug)
                    System.out.println("колонка  : " + i );

            if(resultSet2.getDouble(i) > 0 )
                {
                // sumHours = sumHours + resultSet2.getBigDecimal(i) ;
                sumHours = sumHours.add(resultSet2.getBigDecimal(i)) ;
                if (isDebug){
                    System.out.println("значение  : " + resultSet2.getDouble(i));
                    System.out.println("значение переменной  : " + sumHours);
                }
                }

            }
        }


            return  sumHours ;
        } catch (Exception e) {
            System.out.println(    e.getMessage() + "error4 - getcountworkhours");
            return  sumHours ;
        } finally {
            if (isDebug)
            System.out.println("finally getCountWorkHours:" + new Date());

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
            try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

                try {
                    if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                        finConnection.close();
                    finConnection = null;}
                } catch (SQLException e) {
                }
        }

    }


  //     выборка количества  часов в месяце для отпускников
    public BigDecimal getCountHoursOtpusk(String pdate_srez , Integer ppodrcod ) throws PersistenceException
    {
        boolean isDebug = false;

        if (isDebug)
            System.out.println("start getCountHoursOtpusk:" + new Date());

        // Date firstDay = getFirstDayInMonth(pdate_srez);
        BigDecimal out = new BigDecimal(0);
        BigDecimal sumHours = new BigDecimal(0);

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        PreparedStatement statementHours = null;
        ResultSet resultSetHours = null;

            PreparedStatement statement2 = null;
            ResultSet resultSet2 =  null;

            PreparedStatement statementnastroyka = null;
            ResultSet resultSetnastroyka = null;

        boolean isNewConn = false;

        try {
        String sql = " alter session "
                + " set   NLS_TERRITORY=AMERICA "
                + " NLS_LANGUAGE=AMERICAN "
                + " OPTIMIZER_MODE=RULE " ;

        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();



        sql = "";
        // текст запроса назначеных людей
        sql = //" SELECT /*+ RULE */ "
                " SELECT "
            + "/*-- Назначение на Должность*/ "

        + " n.Karta_Id, "
        + " kr.TabN, "
        + " fio.F||' '||fio.I||' '||fio.O FIO "

        + "  FROM "
        + " kadry.Prom            prom, "
        + " kadry.Kategory        k, "
        + " (SELECT gp2.Grafik_Id, gp2.Norma "
        + "  FROM  kadry.Grafik_Period  gp2 "
        + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
        + "     AND rownum<=1) g, "
        + " kadry.Doljnost      d, "
        + " kadry.Doljnost_Day  dd, "
        + " (SELECT p.Id             Podr_Id, "
        + "        p.Date_Start     Podr_Date_Start, "
        + "        p.Date_Finish    Podr_Date_Finish, "
        + "        pd.ROWID         Podr_Day_Row_Id, "
        + "        pd.Podr_Id       Podr_Day_Podr_Id, "
        + "        pd.Date_Start    Podr_Day_Date_Start, "
        + "        pd.Main_Podr_Id  Main_Podr_Id, "
        + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
        + "        pd.Nazv          Podr_Nazv, "
        + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
        + "        pd.Kod           Podr_Kod, "
        + "        pd.Prom_Id       Podr_Prom_Id , "
        + "        pd.Pos           Podr_Pos, "
        + "        pd.Vne_Shtata    Vne_Shtata, "
        + "        ceh.ROWID        Ceh_Row_Id, "
        + "        ceh.Podr_Id      Ceh_Id, "
        + "        ceh.Date_Start   Ceh_Date_Start, "
        + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
        + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
        + "        ceh.Nazv         Ceh_Nazv, "
        + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
        + "        ceh.Kod          Ceh_Kod, "
        + "        ceh.Prom_Id      Ceh_Prom_Id, "
        + "        ceh.Pos          Ceh_Pos "
        + "   FROM kadry.Podr_Day  ceh, "
        + "        kadry.Podr_Day  pd, "
        + "        kadry.Podr      p "
        + "  WHERE pd.Podr_Id(+) = p.Id "
        + "    AND (pd.Podr_Id IS NULL "
        + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
        + "                        FROM kadry.Podr_Day pd1 "
        + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
        + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "        ) "
        + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
        + "                        FROM kadry.Podr_Day ceh1 "
        + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
        + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND p.Id = " + ppodrcod
        + "  ) pd, "
        + " kadry.v_Post       ps, "
        + " kadry.v_Post       ps1, "

        + " /*-- предыдущее назначение*/ "
        + " kadry.Perevod_Vid  pv, "
        + " kadry.Osnova       os, "
        + " kadry.Nazn         n2, "
        + " kadry.Doljnost     d2, "
        + " kadry.Post         ps2, "
        + " kadry.Razr         rz2, "
        + " kadry.Prof         pf2, "
        + " /*-- следующее назначение*/ "
        + " kadry.Perevod_Vid  pv3, "
        + " kadry.Osnova       os3, "
        + " kadry.Nazn         n3, "
        + " kadry.Doljnost     d3, "
        + " kadry.Post         ps3, "
        + " kadry.Razr         rz3, "
        + " kadry.Prof         pf3, "

        + " kadry.FIO          fio, "
        + " kadry.Karta        kr, "
        + " kadry.Nazn_Vid     nv, "
        + " kadry.Pricaz       pr1, "
        + " kadry.Pricaz       pr2, "
        + " kadry.Pricaz       pr3, "
        + " kadry.Nazn_Day     nd, "
        + " kadry.Nazn         n "

        + " WHERE (nd.Nazn_Id = n.Id "

        + " /*-- Выбор параметров на дату среза*/ "
        + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + " AND NOT EXISTS "
        + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
        + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
        + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND nd2.Date_Start > nd.Date_Start) "
        + " AND n.Start_Pricaz_Id  = pr1.Id "
        + " AND n.Finish_Pricaz_Id = pr2.Id "
        + " AND nd.Pricaz_Id       = pr3.Id "
        + " AND n.Doljnost_Id = d.Id "
        + " AND pd.Podr_Id = d.Podr_Id "
        + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
        + " AND ps1.Post_Id = d.Post_Id "
        + " AND k.Id = d.Kategory_Id "
        + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
        + " AND dd.Doljnost_Id(+) = d.Id "
        + " /*-- Выбор параметров должности на дату*/ "
        + " AND (dd.Doljnost_Id IS NULL "
        + "   OR (dd.Date_Start <= n.Date_Finish  "
        + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
        + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
        + "                        AND dd2.Date_Start <= n.Date_Finish  "
        + "                            AND dd2.Date_Start > dd.Date_Start))) "
        + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
        + "    /*-- предыдущее назначение*/ "
        + "      AND pv.Id(+) = n.Perevod_Vid_Id "
        + "      AND os.Id(+) = n.Perevod_Osnova_Id "
        + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
        + "      AND n2.Doljnost_Id = d2.Id(+) "
        + "      AND ps2.Id(+) = d2.Post_Id "
        + "      AND pf2.Id(+) = ps2.Prof_Id "
        + "      AND rz2.Id(+) = ps2.Razr_Id   "
        + "    /*-- сдедующее назначение*/ "
        + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
    + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
    + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
    + " AND d3.Id(+)  = n3.Doljnost_Id "
    + " AND ps3.Id(+) = d3.Post_Id "
    + " AND pf3.Id(+) = ps3.Prof_Id "
    + " AND rz3.Id(+) = ps3.Razr_Id   "
    + " AND n.Karta_Id     = kr.Id "
    + " AND upper(kr.prim) like ('%НВЗ%') "
    + " AND fio.Id         = kr.FIO_Id "
    + " AND nd.Nazn_Vid_Id = nv.Id(+) "
    + "    /*-- график назначения*/ "
        + " ) AND  "
        + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
        + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
        //    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
        + " ORDER BY kr.TabN, n.Date_Start desc  " ;

        if (isDebug){
            System.out.println("sql выборка людей : " + sql);
            System.out.println("paramworkday : " + pdate_srez.toString());
        }




            // PreparedStatement statement2 = finConnection.prepareStatement(sql);
            // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
            statement2 = finConnection.prepareStatement(sql);
            resultSet2 = statement2.executeQuery();

          // получили список сотрудников
            int i;

        while (resultSet2.next())
        {
            // по каждому сотруднику выбирам количество часов в отпуске
            String sqlnastroyka = " alter session "
                + " set   NLS_TERRITORY=AMERICA "
                + " NLS_LANGUAGE=AMERICAN "
                + " OPTIMIZER_MODE=RULE " ;

        statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
        resultSetnastroyka = statement.executeQuery();
       // if (isDebug)
            System.out.println(" Табельный " + resultSet2.getString(2) );

            String sqlHours = " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "  ) "
                    + "     Then graf.rday1 else 0 end  "
                + " +  "
                + " case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                + "  ) "
                + "         Then graf.rday2 else 0 end "
                +" +    "
                +"  case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday3 else 0 end   "
                +"   +  "
                +"  case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"  ) "
                +"          Then graf.rday4 else 0 end    "
                +" +  "
                +"  case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday5 else 0 end "
                +" + "
                +"  case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday6 else 0 end     "
                +" + "
                +"  case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday7 else 0 end     "
                +" +  "
                +"  case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday8 else 0 end    "
                +" +  "
                +"  case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday9 else 0 end    "
                +" +  "
                +"  case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday10 else 0 end  "
                +" +  "
                +"  case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday11 else 0 end    "
                +" +  "
                +"  case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday12 else 0 end     "
                +" + "
                +"  case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday13 else 0 end   "
                +" +  "
                +"  case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday14 else 0 end   "
                +" + "
                +"  case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday15 else 0 end  "
                +" +  "
                +"  case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"           Then graf.rday16 else 0 end  "
                +" +  "
                +"  case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday17 else 0 end  "
                +" +  "
                +"  case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday18 else 0 end    "
                +" +  "
                +"  case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday19 else 0 end    "
                +" +  "
                +"  case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"  ) "
                +"          Then graf.rday20 else 0 end      "
                +" +  "
                +"  case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday21 else 0 end     "
                +" +  "
                +"  case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday22 else 0 end      "
                +" +  "
                +"  case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday23 else 0 end  "
                +" + "
                +"  case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                +"  ) "
                +"          Then graf.rday24 else 0 end  "
                +" +  "
                +"  case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"  ) "
                +"          Then graf.rday25 else 0 end   "
                +" +  "
                +"  case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday26 else 0 end "
                +" + "
                +"  case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday27 else 0 end       "
                +" +  "
                +"  case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday28 else 0 end "
                +" +  "
                +"  case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then "
                +"  case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday29 else 0 end  "
                +" else 0  end      "
                +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
                +"  case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday30 else 0 end  "
                +" else 0  end      "
                +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
                +"  case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                +"   ) "
                +"          Then graf.rday31 else 0 end "
                +" else 0  end   "
                +"          sumhours "
                +" from  (  "
                +" SELECT "
                +"  DECODE(odi.S,  TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.S) S, "
                +"  DECODE(odi.Po, TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.PO) PO "
                +" FROM "
                +"  kadry.vOtp    vo, "
                +"  kadry.Karta   k, "
                +"  kadry.Otp o, "

                +"  kadry.Otp_Razdel r, "
                +"  (select odi1.Id "
                +"        , odi1.Otp_Id "
                +"        , odi1.S "
                +"        , odi1.Po "
                +"        , odi1.Kol "
                +"        , odi1.data_vvoda "
                +"        , odi1.Kompens "
                +"        , odi1.Mat_Pom "
                +"        , odi1.Otp_Razdel_Id "
                +"        , odi1.Osnova_Id "
            //  +"        , odi1.Prichina "
                +"        , odi1.Raspr "
                +"        , odi1.Pricaz_Id "
                +"       , p1.data "
                +"        , p1.nomer "
                +"        , p1.project "
                +"     from kadry.Pricaz p1, "
                +"          kadry.Otp_Dni_Ispolz odi1 "
                +"    where odi1.Pricaz_Id = p1.Id(+) "
                +"     ) odi "
                +" WHERE ((vo.Id = o.vOtp_Id "
                +" AND o.Karta_Id = k.Id "
                +" AND odi.Otp_Razdel_Id = r.Id(+) "
                +" AND o.Id = odi.Otp_Id "
                +" ) AND o.Karta_Id = o.Karta_Id  "
                +"  and k.tabn = " + resultSet2.getString(2)+ " ) AND o.Karta_Id = o.Karta_Id "
                +"                      and k.tabn = " + resultSet2.getString(2)
                +"  and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
                +" ) dat ,     "
                + "( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
                +"   rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
                +"   rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
                +" Where gwv.date_rasch = trunc(to_date('"+pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
                +" and  gwv.work_time_id = 1 ) graf "   ;

        // выполняем скрипт
            if (isDebug)
                System.out.println(    sqlHours);

            statementHours = finConnection.prepareStatement(sqlHours);
            resultSetHours = statementHours.executeQuery();

            //if    (resultSetHours.next())  NET-4418 - нашел баг, несколько отпусков выбиралось . но НЕ сумировалось
            while(resultSetHours.next())

            {
                sumHours = sumHours.add(resultSetHours.getBigDecimal(1)) ;
                if (isDebug)
                System.out.println(resultSetHours.getBigDecimal(1));
            }

                try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
                try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

                try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
                try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

        }


            return  sumHours ;
        } catch (Exception e) {
            System.out.println(    e.getMessage() + "error5 - getcounthoursotpusk");

            return  sumHours ;
        } finally {
            if (isDebug)
                System.out.println("finally getCountHoursOtpusk:" + new Date());

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
            try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

            try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
            try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

            try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
            try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


                try {
                    if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                        finConnection.close();
                        finConnection = null;}
                } catch (SQLException e) {
                }
        }

    }

    public BigDecimal getTimeByPlan(int planCode) throws PersistenceException{

        //System.out.println("start getTimeByPlan:" + new Date());

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
        //System.out.println("final getTimeByPlan:" + new Date());
        return new TransportLogic(connection, userProfile).calculateTimeForPlanByDistance(planCode, 0);
    }

    // выборка часов затраченых бригадой на проезд суммой из всех планов
    public BigDecimal getProezdBrigadi(String pdate1  , String pdate2  , Integer ppodrcod ) throws PersistenceException
    {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("start getProezdBrigadi:" + new Date());

        // Date firstDay = getFirstDayInMonth(pdate_srez);
        BigDecimal out = new BigDecimal(0);
        BigDecimal sumHours = new BigDecimal(0);

        PreparedStatement statement = null;
        ResultSet  resultSet = null;

        try {
        // выбрать все планы которые попадают за дату и по бригаде     выбирается код плана и количество персонала в плане
        String sql = "   select enp.code "
                    + "    /* количество персонала выполнение  */ "
                    + " ,(select count(tabnumber) from (     Select distinct(finworker.tabnumber) as tabnumber  from ENTRANSPORTITEM , "
                    + "                               enplanworkitem , "
                    + "                               enplanwork ,  "
                    + "                               tktransporttype , "
                    + "                                finworker  "
                    + " where ENTRANSPORTITEM.planitemrefcode = enplanworkitem.code "
                    + " and tktransporttype.code = ENTRANSPORTITEM.tktransporttypecode  "
                    + " and enplanwork.code = enplanworkitem.planrefcode "
                    + " and ENTRANSPORTITEM.tktransporttypecode <> 1 "
                    + " and coalesce(ENTRANSPORTITEM.countworkgen ,0) <> 0 "
                    + " and enplanwork.code  = enp.code "
                    + " and finworker.code =  ENTRANSPORTITEM.finworkercode "
                    + "         UNION    "
                    + " select distinct(fw.tabnumber) as tabnumber  from enhumenitem hum , "
                    + "                                  finworker fw "
                    + " where hum.planrefcode =enp.code "
                    + " and hum.finworkercode = fw.code )as q) as count_personal_fact   From enplanwork enp , "
                    + " endepartment d , "
                    + " enelement en , "
                    + " enelementtype ent , "
                    + " enplanworktype enwt , "
                    + " finexecutor "
                    + " Where enp.departmentrefcode = d.code "
                    + " and enp.elementrefcode = en.code "
                    + " and en.typerefcode = ent.code "
                    + " and enp.typerefcode = enwt.code "
                    + " and  enp.finexecutorcode =  finexecutor.code "
                    + " and  finexecutor.fincode =  " + ppodrcod  + " /* 273   птаховская*/ "
                    + "  and ( Select count(enplanworkitem.code) from enplanworkitem "
                    + " Where enplanworkitem.planrefcode = enp.code "
                    + " and  enplanworkitem.countgen <> 0 "
                    + " ) > 0 "
                    + "  and enp.statuscode NOT IN (2,6) "
                    + " and  enp.kindcode = 4 /* задание факт */ "
                    + " and  enp.datestart between to_date('"+pdate1.toString()+"' , 'dd.mm.yyyy') and to_date('"+pdate2.toString()+"','dd.mm.yyyy') "   ;
        if (isDebug)
            System.out.println(" запрос доставка" + sql );

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();


        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery();


        int i;

        if    (resultSet.next())
        {
            if (isDebug)
                System.out.println(" начинается цикл  " );

            while (resultSet.next())
            {

            if(resultSet.getDouble(1) > 0 )
                {
                if (isDebug)
                    System.out.println("перед значением  : " + resultSet.getDouble(1));
                if ( resultSet.getDouble(2) > 0 )
                {
                sumHours = sumHours.add( getTimeByPlan(resultSet.getInt(1)).multiply(resultSet.getBigDecimal(2))) ;
                }
                if (isDebug){
                    System.out.println(" значение  : " + resultSet.getDouble(1));
                    System.out.println(" значение переменной  : " + sumHours);
                }
                }

            }
        }


            return  sumHours ;
        } catch (Exception e) {
            System.out.println(    e.getMessage() + "error 6 - getproezdbrigadi");
            return  sumHours ;
        } finally {

            if (isDebug)
                System.out.println("finally getProezdBrigadi:" + new Date());

            try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
            try {if (statement != null) statement.close();} catch (SQLException e) {}

        }

    }

    /* процедура расчета сумы рабочего времени за два предыдущих месяца*/
    public BigDecimal getWorkTimeTwoPreviousMonth(Date d) throws PersistenceException
    {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("start getWorkTimeTwoPreviousMonth:" + new Date());

           BigDecimal out = new BigDecimal(Integer.MIN_VALUE);
            //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
            //Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
            //UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
            //System.out.println("userProfile" + userProfile);

            boolean isNewConn = false;



            try{
            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

                isNewConn = true;
            }

            if ( ! (finConnection != null && !finConnection.isClosed())) {
                if (isDebug)
                    System.out.println(" getMiddlePayTwoPreviousMonth error on connect");
                finConnection = null;
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                if ( ! (finConnection != null && !finConnection.isClosed())) {
                    if (isDebug)
                    System.out.println("EEEEEEEEEEEEe");
                }
                isNewConn = true;
            }
            }
            catch (DatasourceConnectException e){
                System.out.println(e.getStackTrace());
                throw new PersistenceException("Error to connect FK",e);
            }
            catch (SQLException e){
                System.out.println(e.getStackTrace());
                throw new PersistenceException("Error to exec SQL",e);

            }

        //  BigDecimal qq= new FINZPLogic(finConnection, userProfile).getWorkTimeTwoPreviousMonth(d);
        //  System.out.println(" qq = " + qq );
    //}

        Date firstDay = getFirstDayInMonth(d);
        /* запрос выбора суммы рабочих часов за два предыдущих месяца. */
        /** nv.grafik_id = 1 -- график рабочего времени (типа общий) */
        String sql = " select sum(a_hours) from ( "
                        + " select a_hours from trud.norma_vrem nv where nv.grafik_id = 1 and nv.date_rasch = ADD_MONTHS( ? , -1) "
                        + " UNION ALL"
                        + " select a_hours from trud.norma_vrem nv where nv.grafik_id = 1 and nv.date_rasch = ADD_MONTHS( ? , -2) "
                        + " ) " ;

                /*  String sql = " select sum(a_hours) from ( "
                        + " select a_hours from trud.norma_vrem nv where nv.date_rasch = ADD_MONTHS( to_date('01.03.2010','dd.mm.yyyy') , -1) "
                        + " UNION ALL"
                        + " select a_hours from trud.norma_vrem nv where nv.date_rasch = ADD_MONTHS( to_date('01.03.2010','dd.mm.yyyy') , -2) "
                        + " ) " ; */
        if (isDebug){
                System.out.println("sql : " + sql);
                System.out.println("param : " + firstDay.toString());
        }


                PreparedStatement statement = null;
                ResultSet  resultSet = null;


        try
        {

            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }
            if ( ! (finConnection != null && !finConnection.isClosed())) {
                //System.out.println(" getWorkTimeTwoPreviousMonth error on connect");
                finConnection = null;
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                if ( ! (finConnection != null && !finConnection.isClosed())) {
                    System.out.println("EEEEEEEEEEEEe");
                }
                isNewConn = true;
            }

            statement = finConnection.prepareStatement(sql);

            statement.setDate(1, new java.sql.Date(firstDay.getTime()));
            statement.setDate(2, new java.sql.Date(firstDay.getTime()));

                resultSet = statement.executeQuery();

                if (resultSet.next())
                out = resultSet.getBigDecimal(1);
                else
                    if (isDebug)
                    System.out.println("resultSet clear getWorkTimeTwoPreviousMonth");

                if (isDebug)
                    System.out.println("end getWorkTimeTwoPreviousMonth " + out);

            return out;
        } catch (Exception e) {
            System.out.println(    e.getMessage());
            System.out.println("eRRRRRRRRRor end getWorkTimeTwoPreviousMonth");
            return out;
        } finally {

            if (isDebug)
                System.out.println("finally getWorkTimeTwoPreviousMonth:" + new Date());

                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                    try {
                        if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed()))
                            finConnection.close();
                    } catch (SQLException e) {
                    }
        }
            }


    /* процедура расчета начислений по работнику  за два предыдущих месяца (шифр 500 из ЗП)*/
    public BigDecimal getMiddlePayTwoPreviousMonth(Date d , String tabnum) throws PersistenceException, Exception
    {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("start getMiddlePayTwoPreviousMonth:" + new Date());

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
            //Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
            UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

            boolean isNewConn = false;

            try{
            if (finConnection == null){
                if (isDebug)
                    System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

                isNewConn = true;
            }

            if ( ! (finConnection != null && !finConnection.isClosed())) {
                //System.out.println(" getMiddlePayTwoPreviousMonth error on connect");
                finConnection = null;
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                if ( ! (finConnection != null && !finConnection.isClosed())) {
                    System.out.println("EEEEEEEEEEEEe");
                }
                isNewConn = true;
            }
            }
            catch (DatasourceConnectException e){
                System.out.println(e.getStackTrace());
                throw new PersistenceException("Error to connect FK",e);
            }
            catch (SQLException e){
                System.out.println(e.getStackTrace());
                throw new PersistenceException("Error to exec SQL",e);

            }

        //BigDecimal qq= new FINZPLogic(finConnection, userProfile).getMiddlePayTwoPreviousMonth(d , tabnum);
        //System.out.println(" tabn = " + qq );


                Date firstDay = getFirstDayInMonth(d);
    /* запрос выбора суммы рабочих часов за два предыдущих месяца. */
                String sql = " select sum(summa) from ( "
                    + " select al.summa from zarpherson.arhiv_list al, zarpherson.kadry_day_view kdv "
                    + " where al.idkadry = kdv.idkadry "
                    + "   and kdv.tabn = ? "
                    + "   and al.shifr = '500' "
                    + "   and al.date_rasch = ADD_MONTHS( ? , -1) "
                    + " UNION ALL "
                    + " select al.summa from zarpherson.arhiv_list al, zarpherson.kadry_day_view kdv "
                    + " where al.idkadry = kdv.idkadry "
                    + " and kdv.tabn = ? "
                    + " and al.shifr = '500' "
                    + " and al.date_rasch = ADD_MONTHS( ? , -2) "
                    + " ) ";


                /* String sql = " select sum(summa) from ( "
                        + " Select al.summa from zarpherson.arhiv_list al, zarpherson.kadry_day_view kdv "
                        + " Where al.idkadry = kdv.idkadry "
                        + "   And kdv.tabn = '67317' "
                        + "   And al.shifr = '500' "
                        + "   And al.date_rasch = ADD_MONTHS( to_date('01.03.2010','dd.mm.yyyy') , -1) "
                        + " UNION ALL "
                        + " Select al.summa from zarpherson.arhiv_list al, zarpherson.kadry_day_view kdv "
                        + " Where al.idkadry = kdv.idkadry "
                        + " And kdv.tabn = '67317' "
                        + " And al.shifr = '500' "
                        + " And al.date_rasch = ADD_MONTHS( to_date('01.03.2010','dd.mm.yyyy') , -2) "
                        + " ) ";*/

                if (isDebug){
                    System.out.println(" sql : " + sql);
                    System.out.println(" param : " + firstDay.toString());
                }

                BigDecimal out = new BigDecimal(Integer.MIN_VALUE);

                PreparedStatement statement = null;
                ResultSet  resultSet = null;


        try {

            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

                isNewConn = true;
            }

            if ( ! (finConnection != null && !finConnection.isClosed())) {
                //System.out.println(" getMiddlePayTwoPreviousMonth error on connect");
                finConnection = null;
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                if ( ! (finConnection != null && !finConnection.isClosed())) {
                    System.out.println("EEEEEEEEEEEEe");
                }
                isNewConn = true;
            }

                statement = finConnection.prepareStatement(sql);

                statement.setString(1, tabnum);
                statement.setDate(2, new java.sql.Date(firstDay.getTime()));
                statement.setString(3, tabnum);
                statement.setDate(4, new java.sql.Date(firstDay.getTime()));


                resultSet = statement.executeQuery();

                if (resultSet.next())
                out = resultSet.getBigDecimal(1);
                else
                System.out.println("resultSet clear getMiddlePayTwoPreviousMonth");

                if (isDebug)
                    System.out.println("end getMiddlePayTwoPreviousMonth" + out);

            return out;
        } catch (Exception e) {
            throw new Exception(e);
            //System.out.println(    e.getMessage());
            //return out;
        } finally {

            if (isDebug)
                System.out.println("finally getMiddlePayTwoPreviousMonth:" + new Date());

                try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                    try {
                        if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed()))
                            finConnection.close();
                    } catch (SQLException e) {
                    }
        }
        }

    /// проверка есть ли в подразделении работник с указаным табельным

    public BigDecimal getPresentEltehpersonalFromKadry(String pdate_srez , Integer ppodrcod , String ptabnum ) throws PersistenceException
        {
        boolean isDebug = false;
        if (isDebug)
            System.out.println("start getPresentEltehpersonalFromKadry:" + new Date());

            BigDecimal out = new BigDecimal(0);

            PreparedStatement statement = null;
            ResultSet  resultSet = null;
            String var_ptabnum = new String("0");

            if (ptabnum != null)
               {
                var_ptabnum = ptabnum ;
               }



            String sql = " SELECT  count(*) as count  from ( "+
            //" SELECT /*+ RULE */ "
            " SELECT "
            + "/*-- Назначение на Должность*/ "

        + " n.Karta_Id, "
        + " kr.TabN, "
        + " fio.F||' '||fio.I||' '||fio.O FIO "

        + "  FROM "
        + " kadry.Prom            prom, "
        + " kadry.Kategory        k, "
        + " (SELECT gp2.Grafik_Id, gp2.Norma "
        + "  FROM  kadry.Grafik_Period  gp2 "
        + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
        + "     AND rownum<=1) g, "
        + " kadry.Doljnost      d, "
        + " kadry.Doljnost_Day  dd, "
        + " (SELECT p.Id             Podr_Id, "
        + "        p.Date_Start     Podr_Date_Start, "
        + "        p.Date_Finish    Podr_Date_Finish, "
        + "        pd.ROWID         Podr_Day_Row_Id, "
        + "        pd.Podr_Id       Podr_Day_Podr_Id, "
        + "        pd.Date_Start    Podr_Day_Date_Start, "
        + "        pd.Main_Podr_Id  Main_Podr_Id, "
        + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
        + "        pd.Nazv          Podr_Nazv, "
        + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
        + "        pd.Kod           Podr_Kod, "
        + "        pd.Prom_Id       Podr_Prom_Id , "
        + "        pd.Pos           Podr_Pos, "
        + "        pd.Vne_Shtata    Vne_Shtata, "
        + "        ceh.ROWID        Ceh_Row_Id, "
        + "        ceh.Podr_Id      Ceh_Id, "
        + "        ceh.Date_Start   Ceh_Date_Start, "
        + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
        + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
        + "        ceh.Nazv         Ceh_Nazv, "
        + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
        + "        ceh.Kod          Ceh_Kod, "
        + "        ceh.Prom_Id      Ceh_Prom_Id, "
        + "        ceh.Pos          Ceh_Pos "
        + "   FROM kadry.Podr_Day  ceh, "
        + "        kadry.Podr_Day  pd, "
        + "        kadry.Podr      p "
        + "  WHERE pd.Podr_Id(+) = p.Id "
        + "    AND (pd.Podr_Id IS NULL "
        + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
        + "                        FROM kadry.Podr_Day pd1 "
        + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
        + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "        ) "
        + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
        + "                        FROM kadry.Podr_Day ceh1 "
        + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
        + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND p.Id = " + ppodrcod
        + "  ) pd, "
        + " kadry.v_Post       ps, "
        + " kadry.v_Post       ps1, "

        + " /*-- предыдущее назначение*/ "
        + " kadry.Perevod_Vid  pv, "
        + " kadry.Osnova       os, "
        + " kadry.Nazn         n2, "
        + " kadry.Doljnost     d2, "
        + " kadry.Post         ps2, "
        + " kadry.Razr         rz2, "
        + " kadry.Prof         pf2, "
        + " /*-- следующее назначение*/ "
        + " kadry.Perevod_Vid  pv3, "
        + " kadry.Osnova       os3, "
        + " kadry.Nazn         n3, "
        + " kadry.Doljnost     d3, "
        + " kadry.Post         ps3, "
        + " kadry.Razr         rz3, "
        + " kadry.Prof         pf3, "

        + " kadry.FIO          fio, "
        + " kadry.Karta        kr, "
        + " kadry.Nazn_Vid     nv, "
        + " kadry.Pricaz       pr1, "
        + " kadry.Pricaz       pr2, "
        + " kadry.Pricaz       pr3, "
        + " kadry.Nazn_Day     nd, "
        + " kadry.Nazn         n "

        + " WHERE (nd.Nazn_Id = n.Id "

        + " /*-- Выбор параметров на дату среза*/ "
        + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + " AND NOT EXISTS "
        + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
        + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
        + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
        + "    AND nd2.Date_Start > nd.Date_Start) "
        + " AND n.Start_Pricaz_Id  = pr1.Id "
        + " AND n.Finish_Pricaz_Id = pr2.Id "
        + " AND nd.Pricaz_Id       = pr3.Id "
        + " AND n.Doljnost_Id = d.Id "
        + " AND pd.Podr_Id = d.Podr_Id "
        + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
        + " AND ps1.Post_Id = d.Post_Id "
        + " AND k.Id = d.Kategory_Id "
        + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
        + " AND dd.Doljnost_Id(+) = d.Id "
        + " /*-- Выбор параметров должности на дату*/ "
        + " AND (dd.Doljnost_Id IS NULL "
        + "   OR (dd.Date_Start <= n.Date_Finish  "
        + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
        + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
        + "                        AND dd2.Date_Start <= n.Date_Finish  "
        + "                            AND dd2.Date_Start > dd.Date_Start))) "
        + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
        + "    /*-- предыдущее назначение*/ "
        + "      AND pv.Id(+) = n.Perevod_Vid_Id "
        + "      AND os.Id(+) = n.Perevod_Osnova_Id "
        + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
        + "      AND n2.Doljnost_Id = d2.Id(+) "
        + "      AND ps2.Id(+) = d2.Post_Id "
        + "      AND pf2.Id(+) = ps2.Prof_Id "
        + "      AND rz2.Id(+) = ps2.Razr_Id   "
        + "    /*-- сдедующее назначение*/ "
        + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
    + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
    + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
    + " AND d3.Id(+)  = n3.Doljnost_Id "
    + " AND ps3.Id(+) = d3.Post_Id "
    + " AND pf3.Id(+) = ps3.Prof_Id "
    + " AND rz3.Id(+) = ps3.Razr_Id   "
    + " AND n.Karta_Id     = kr.Id "
    + " AND upper(kr.prim) like ('%НВЗ%') "
    + " AND fio.Id         = kr.FIO_Id "
    + " AND nd.Nazn_Vid_Id = nv.Id(+) "
    + "    /*-- график назначения*/ "
        + " ) AND  "
        + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
        + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
        // + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
        + " and kr.TabN =  " + "'"+ var_ptabnum + "'"
        + " ORDER BY kr.TabN , n.Date_Start desc  " +
            " ) " ;


        //    sql = "select count(*) count from kadry.Doljnost dd  Where dd.date_start >= ? " ;
            if (isDebug){
                System.out.println(" sql : " + sql);
                System.out.println(" parampodrcod : " + ppodrcod.toString());
            }

            boolean isNewConn = false;

            try {

                if ((finConnection == null) || (finConnection.isClosed()  )){
                    //System.out.println("finConnection is null");
                    //return new BigDecimal(Integer.MIN_VALUE);
                    finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                    isNewConn = true;
                }
                altersession();
                statement = finConnection.prepareStatement(sql);

            /*    statement.setDate(1, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(2, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(3, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(4, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(5, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(6, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(7, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(8, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(9, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(10, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(11, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(12, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(13, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(14, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(15, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(16, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(17, new java.sql.Date(pdate_srez.getTime()));
                statement.setDate(18, new java.sql.Date(pdate_srez.getTime()));
                statement.setInt(19, ppodrcod.intValue()); */


                resultSet = statement.executeQuery();
                //resultSet.next();
            //    if (!resultSet.next()) {
            //        throw new SystemException("Count work time not found for date " + d);
            //    }

                if (resultSet.next())
                out = resultSet.getBigDecimal(1);
                else
                System.out.println("resultSet clear getCountEltehpersonalFromKadry");

                //System.out.println(); //new java.sql.Date(firstDay.getTime()));

                if (isDebug)
                    System.out.println("paramdate2 : " + out);

                return out;
            } catch (Exception e) {
                System.out.println(    e.getMessage()+ "erorrr7 - getpresenteltehpersonalfromkadry");
                System.out.println(sql);
                return out;
            } finally {

                if (isDebug)
                    System.out.println("finally getPresentEltehpersonalFromKadry:" + new Date());

                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                    try {
                        if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                            finConnection.close();

                        finConnection = null;}

                    } catch (SQLException e) {
                    }
            }

        }

//         выборка количества  часов в месяце отпуска по человеку
        public BigDecimal getCountHoursOtpuskByHuman(String pdate_srez , Integer ppodrcod , String tabnumber ) throws PersistenceException
        {
            boolean isDebug = false;

            // Date firstDay = getFirstDayInMonth(pdate_srez);
            BigDecimal out = new BigDecimal(0);
            BigDecimal sumHours = new BigDecimal(0);

        //    PreparedStatement statement = null;
        //    ResultSet  resultSet = null;

            PreparedStatement statementHours = null;
            ResultSet resultSetHours = null;

        //        PreparedStatement statement2 = null;
        //        ResultSet resultSet2 =  null;

                PreparedStatement statementnastroyka = null;
                ResultSet resultSetnastroyka = null;

            boolean isNewConn = false;

            try {

            	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
                Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
                UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

            	boolean usesMDAXDataReport = false;
                AuthLogic netAuth = new AuthLogic(connection, userProfile);
            	usesMDAXDataReport = netAuth.checkUsesMDAXDataForReport();
            	mDaxScriptlet mScr = new mDaxScriptlet();

            	SimpleDateFormat dfyyyy = new SimpleDateFormat("yyyy");
            	SimpleDateFormat dfMM = new SimpleDateFormat("MM");

            	Date d_date_srez = Tools.convertStringToDate(pdate_srez);
            	String datestart = "01." + dfMM.format(d_date_srez) +"."+ dfyyyy.format(d_date_srez);
            	String dateend = "01." + dfMM.format(d_date_srez) +"."+   dfyyyy.format(d_date_srez);
            	Date d_datestart = Tools.getFirstDayOfMonth(Tools.convertStringToDate(datestart));
            	Date d_dateend = Tools.getLastDayOfMonth(Tools.convertStringToDate(dateend));

            	 SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
            	 datestart = formattedDate.format(d_datestart);
              	 dateend =  formattedDate.format(d_dateend);

            	if (usesMDAXDataReport == true) {
            		mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );
            		BigDecimal[] wDaysHoursArr = { new BigDecimal(0), new BigDecimal(0) };
            		// wDaysHoursArr = mdLogic.getWorkingTimePlan( Tools.getFirstDayOfMonth(pdate_srez ) ,Tools.getLastDayOfMonth( pdate_srez ) , "Смена 8.2 часа Пн-Пт" );
            		sumHours = mdLogic.getCountHoursOtpuskByEmplString( datestart , dateend , tabnumber );

            		return sumHours;
            	}

            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }



                // по каждому сотруднику выбирам количество часов в отпуске
                String sqlnastroyka = " alter session "
                    + " set   NLS_TERRITORY=AMERICA "
                    + " NLS_LANGUAGE=AMERICAN "
                    + " OPTIMIZER_MODE=RULE " ;

            statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
            resultSetnastroyka = statementnastroyka.executeQuery();
            // YYY  resultSetnastroyka = statement.executeQuery();

            if (isDebug)
                System.out.println(" Отпуск -  Табельный " + tabnumber );

                String sqlHoursOld = " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "  ) "
                    + "     Then graf.rday1 else 0 end  "
                    + " +  "
                    + " case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                    + "  ) "
                    + "         Then graf.rday2 else 0 end "
                    +" +    "
                    +"  case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday3 else 0 end   "
                    +"   +  "
                    +"  case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday4 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday5 else 0 end "
                    +" + "
                    +"  case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday6 else 0 end     "
                    +" + "
                    +"  case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday7 else 0 end     "
                    +" +  "
                    +"  case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday8 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday9 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday10 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday11 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday12 else 0 end     "
                    +" + "
                    +"  case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday13 else 0 end   "
                    +" +  "
                    +"  case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday14 else 0 end   "
                    +" + "
                    +"  case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday15 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"           Then graf.rday16 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday17 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday18 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday19 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday20 else 0 end      "
                    +" +  "
                    +"  case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday21 else 0 end     "
                    +" +  "
                    +"  case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday22 else 0 end      "
                    +" +  "
                    +"  case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday23 else 0 end  "
                    +" + "
                    +"  case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                    +"  ) "
                    +"          Then graf.rday24 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday25 else 0 end   "
                    +" +  "
                    +"  case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday26 else 0 end "
                    +" + "
                    +"  case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday27 else 0 end       "
                    +" +  "
                    +"  case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday28 else 0 end "
                    +" +  "
                    +"  case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then "
                    +"  case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday29 else 0 end  "
                    +" else 0  end      "
                    +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
                    +"  case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday30 else 0 end  "
                    +" else 0  end      "
                    +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
                    +"  case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday31 else 0 end "
                    +" else 0  end   "
                    +"          sumhours "
                    +" from  (  "
                    +" SELECT "
                    +"  DECODE(odi.S,  TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.S) S, "
                    +"  DECODE(odi.Po, TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.PO) PO "
                    +" FROM "
                    +"  kadry.vOtp    vo, "
                    +"  kadry.Karta   k, "
                    +"  kadry.Otp o, "

                    +"  kadry.Otp_Razdel r, "
                    +"  (select odi1.Id "
                    +"        , odi1.Otp_Id "
                    +"        , odi1.S "
                    +"        , odi1.Po "
                    +"        , odi1.Kol "
                    +"        , odi1.data_vvoda "
                    +"        , odi1.Kompens "
                    +"        , odi1.Mat_Pom "
                    +"        , odi1.Otp_Razdel_Id "
                    +"        , odi1.Osnova_Id "
                //    +"        , odi1.Prichina "
                    +"        , odi1.Raspr "
                    +"        , odi1.Pricaz_Id "
                    +"       , p1.data "
                    +"        , p1.nomer "
                    +"        , p1.project "
                    +"     from kadry.Pricaz p1, "
                    +"          kadry.Otp_Dni_Ispolz odi1 "
                    +"    where odi1.Pricaz_Id = p1.Id(+) "
                    +"     ) odi "
                    +" WHERE ((vo.Id = o.vOtp_Id "
                    +" AND o.Karta_Id = k.Id "
                    +" AND odi.Otp_Razdel_Id = r.Id(+) "
                    +" AND o.Id = odi.Otp_Id "
                    +" ) AND o.Karta_Id = o.Karta_Id  "
                    +"  and k.tabn = " + "'"+tabnumber+"'" + " ) AND o.Karta_Id = o.Karta_Id "
                    +"                      and k.tabn = " + "'" + tabnumber + "'"
                    +"  and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
                    +" ) dat ,     "
                    + "( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
                    +"   rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
                    +"   rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
                    +" Where gwv.date_rasch = trunc(to_date('"+pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
                    +" and  gwv.work_time_id = 1 ) graf "   ;



                String sqlHours = " select sum(dat2.sumhours) sumhours from ( "
                    + " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                          Then graf.rday1 else 0 end   "
                    + "                     +  "
                    + "                      case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                              Then graf.rday2 else 0 end "
                    + "                      +    "
                    + "                       case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday3 else 0 end   "
                    + "                        +  "
                    + "                       case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                               Then graf.rday4 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday5 else 0 end "
                    + "                       + "
                    + "                       case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday6 else 0 end    "
                    + "                      + "
                    + "                       case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday7 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday8 else 0 end    "
                    + "                     +  "
                    + "                       case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday9 else 0 end    "
                    + "                      +  "
                    + "                       case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday10 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday11 else 0 end   "
                    + "                      +  "
                    + "                       case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday12 else 0 end  "
                    + "                      + "
                    + "                       case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday13 else 0 end   "
                    + "                      +  "
                    + "                       case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday14 else 0 end  "
                    + "                      + "
                    + "                       case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday15 else 0 end "
                    + "                       +  "
                    + "                       case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                                Then graf.rday16 else 0 end "
                    + "                      +  "
                    + "                       case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday17 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday18 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday19 else 0 end   "
                    + "                      +  "
                    + "                       case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                               Then graf.rday20 else 0 end "
                    + "                      +  "
                    + "                       case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday21 else 0 end "
                    + "                      +  "
                    + "                       case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday22 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday23 else 0 end  "
                    + "                      + "
                    + "                       case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                               Then graf.rday24 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                       ) "
                    + "                               Then graf.rday25 else 0 end   "
                    + "                      +  "
                    + "                       case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                              Then graf.rday26 else 0 end "
                    + "                      + "
                    + "                       case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday27 else 0 end  "
                    + "                      +  "
                    + "                       case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday28 else 0 end "
                    + "                      +  "
                    + "                       case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then  "
                    + "                       case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )  "
                    + "                        ) "
                    + "                               Then graf.rday29 else 0 end  "
                    + "                      else 0  end   "
                    + "                      + "
                    + "                    case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
                    + "                       case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday30 else 0 end "
                    + "                      else 0  end      "
                    + "                    +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
                    + "                       case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    + "                        ) "
                    + "                               Then graf.rday31 else 0 end "
                    + "                      else 0  end   "
                    + "                               sumhours "
                    + "                      from ( "
                    + " select decode(dr.s,null,odi.s, dr.s) s, decode(dr.po,null,odi.po, dr.po) po "
                    + "     from kadry.dniotp_raspr dr , "
                    + "          kadry.karta k , "
                        + "         kadry.otp o, "
                        + "        kadry.Otp_Dni_Ispolz odi "
                    + " where k.tabn = " + "'"+tabnumber+"'"
                    + " and k.id = o.karta_id "
                    + " and o.id = dr.otp_id(+) "
                    + " and odi.otp_id = o.id "
                    + " and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
                    + " ) dat , "
                    + " ( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
                        + "                 rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
                    + "                     rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
                    + "                     Where gwv.date_rasch = trunc(to_date('"+pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
                    + "                     and  gwv.work_time_id = 1 ) graf "
                    + "  Where trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(dat.s,'mm') and last_day(dat.po)  "
                    + "  ) "
                    + "  dat2 "  ;





            // выполняем скрипт
                if (isDebug)
                System.out.println("sqlHours = " +     sqlHours);

                statementHours = finConnection.prepareStatement(sqlHours);

                resultSetHours = statementHours.executeQuery();

                if    (resultSetHours.next())
                {
                    // sumHours = sumHours.add(resultSetHours.getBigDecimal(1)) ;
                    sumHours = resultSetHours.getBigDecimal(1) ;

                    if (isDebug)
                        System.out.println(sumHours);
                }
                else
                {
                    if (isDebug)
                        System.out.println(" count hours otpusk null ");
                }
                    try {if (resultSetHours != null) resultSetHours.close();}      catch (SQLException e) {}
                    try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

                    try {if (resultSetnastroyka != null) resultSetnastroyka.close();}  catch (SQLException e) {}
                    try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}




                //return  sumHours ;
            } catch (Exception e) {
                System.out.println(tabnumber + "отпуск не выбран" +    e.getMessage());

                return  new BigDecimal(0) ; // sumHours ;
            } finally {


        //         try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        //         try {if (statement != null) statement.close();} catch (SQLException e) {}

        //         try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        //         try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

                try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
                try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

                try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
                try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


                    try {
                        if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                            finConnection.close();
                            finConnection = null;
                        }
                    } catch (SQLException e) {
                    }
            }

            if (sumHours == null) {
                return new BigDecimal(0);

            } else
            {
            return sumHours;
            }

        }
        public BigDecimal altersession() throws PersistenceException
        {

            boolean isDebug = false;

            if (isDebug)
                System.out.println("start altersession:" + new Date());


            String sql = " alter session "
               + " set   NLS_TERRITORY=AMERICA "
               + " NLS_LANGUAGE=AMERICAN "
               + " OPTIMIZER_MODE=RULE " ;

            if (isDebug)
                System.out.println("sql altersession : " + sql);


            PreparedStatement statement = null;
            ResultSet  resultSet = null;
            boolean isNewConn = false;

            BigDecimal out = new BigDecimal(0);
            try {
                //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
                if (isDebug) System.out.println("try");
                //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
                if (finConnection == null){
                    //System.out.println("finConnection is null");
                    //return new BigDecimal(Integer.MIN_VALUE);
                    finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                    isNewConn = true;
                }

                if ( finConnection.isClosed() ){
                    finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                    isNewConn = true;
                }


                statement = finConnection.prepareStatement(sql);
                if (isDebug) System.out.println("prepare");



                resultSet = statement.executeQuery();
                if (isDebug) System.out.println("execSQL");
                // if (resultSet.next()){
                // out = new BigDecimal(1);
                // System.out.println("altersession :" + out );
                //}
                //else{
                //    System.out.println("NOT resultSet.next() for altersession" );
                //    throw new SystemException("alter session failed");
                //}

            //    out = new BigDecimal(1);

                if (isDebug) System.out.println("return :" + out);
                return out;
            }

                catch (SQLException e) {
                    //out = new BigDecimal(2);
                    //System.out.println(    e.getMessage());
                    //finConnection.
                    //return out;
                    throw new PersistenceException(e.getMessage());


            }
                catch (Exception e) {
                    //out = new BigDecimal(2);
                    //System.out.println(    e.getMessage());
                    //throw new Exception(e.getMessage());
                    throw new PersistenceException("Error in method altersession()",e);
                    //finConnection.
                    //return out;


            }
                finally {

                    //System.out.println("finally altersession:" + new Date());

                //System.out.pr
                try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                try {
                    if ((isNewConn)&&(finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;
                    }
                    //System.out.println(" Close connn in altersession ");
                } catch (SQLException e) {
                }
            }

        }
    // отпуск + больничные по подразделению
public BigDecimal getCountHoursOtpuskBoln(String pdate_srez , Integer ppodrcod ) throws PersistenceException
        {

    boolean isDebug = false;

        if (isDebug) System.out.println("start getCountHoursOtpusk:" + new Date());

            // Date firstDay = getFirstDayInMonth(pdate_srez);
            BigDecimal out = new BigDecimal(0);
            BigDecimal sumHours = new BigDecimal(0);

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            PreparedStatement statementHours = null;
            ResultSet resultSetHours = null;

                PreparedStatement statement2 = null;
                ResultSet resultSet2 =  null;

                PreparedStatement statementnastroyka = null;
                ResultSet resultSetnastroyka = null;

            boolean isNewConn = false;

            try {
            String sql = " alter session "
                    + " set   NLS_TERRITORY=AMERICA "
                    + " NLS_LANGUAGE=AMERICAN "
                    + " OPTIMIZER_MODE=RULE " ;

            if (finConnection == null){
                //System.out.println("finConnection is null");
                //return new BigDecimal(Integer.MIN_VALUE);
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            statement = finConnection.prepareStatement(sql);
            resultSet = statement.executeQuery();



            sql = "";
            // текст запроса назначеных людей
            sql = " select kr.TabN " +
                " from kadry.nazn n , kadry.Doljnost d , kadry.Podr_Day  pd, " +
                  " kadry.Podr      p , kadry.Karta  kr , kadry.fio fio  " +
               " WHERE pd.Podr_Id(+) = p.Id " +
               " AND pd.Podr_Id = d.Podr_Id " +
               " AND n.Doljnost_Id = d.Id " +
               " AND n.Karta_Id     = kr.Id " +
               " AND fio.Id         = kr.FIO_Id " +
               " AND p.Id = " + ppodrcod +
               " AND n.Date_Finish >=  " + "to_date( '" + pdate_srez.toString()+ "','dd.mm.yyyy')" +
               " AND upper(kr.prim) like ('%НВЗ%')" ;


            if (isDebug){
                System.out.println("sql выборка людей : " + sql);
                System.out.println("paramworkday : " + pdate_srez.toString());
            }



                // PreparedStatement statement2 = finConnection.prepareStatement(sql);
                // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
                statement2 = finConnection.prepareStatement(sql);
                resultSet2 = statement2.executeQuery();

            // получили список сотрудников
                int i;

            while (resultSet2.next())
            {
                // по каждому сотруднику выбирам количество часов в отпуске
                String sqlnastroyka = " alter session "
                    + " set   NLS_TERRITORY=AMERICA "
                    + " NLS_LANGUAGE=AMERICAN "
                    + " OPTIMIZER_MODE=RULE " ;

            statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
            resultSetnastroyka = statement.executeQuery();

            if (isDebug) System.out.println(" Табельный " + resultSet2.getString(2) );
                String sqlHours = " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                        + "  ) "
                        + "     Then graf.rday1 else 0 end  "
                    + " +  "
                    + " case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                    + "  ) "
                    + "         Then graf.rday2 else 0 end "
                    +" +    "
                    +"  case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday3 else 0 end   "
                    +"   +  "
                    +"  case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday4 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday5 else 0 end "
                    +" + "
                    +"  case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday6 else 0 end     "
                    +" + "
                    +"  case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday7 else 0 end     "
                    +" +  "
                    +"  case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday8 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday9 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday10 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday11 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday12 else 0 end     "
                    +" + "
                    +"  case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday13 else 0 end   "
                    +" +  "
                    +"  case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday14 else 0 end   "
                    +" + "
                    +"  case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday15 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"           Then graf.rday16 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday17 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday18 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday19 else 0 end    "
                    +" +  "
                    +"  case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday20 else 0 end      "
                    +" +  "
                    +"  case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday21 else 0 end     "
                    +" +  "
                    +"  case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday22 else 0 end      "
                    +" +  "
                    +"  case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday23 else 0 end  "
                    +" + "
                    +"  case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
                    +"  ) "
                    +"          Then graf.rday24 else 0 end  "
                    +" +  "
                    +"  case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"  ) "
                    +"          Then graf.rday25 else 0 end   "
                    +" +  "
                    +"  case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday26 else 0 end "
                    +" + "
                    +"  case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday27 else 0 end       "
                    +" +  "
                    +"  case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday28 else 0 end "
                    +" +  "
                    +"  case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then "
                    +"  case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday29 else 0 end  "
                    +" else 0  end      "
                    +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
                    +"  case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday30 else 0 end  "
                    +" else 0  end      "
                    +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
                    +"  case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                    +"   ) "
                    +"          Then graf.rday31 else 0 end "
                    +" else 0  end   "
                    +"          sumhours "
                    +" from  (  "
                    +" SELECT "
                    +"  DECODE(odi.S,  TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.S) S, "
                    +"  DECODE(odi.Po, TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.PO) PO "
                    +" FROM "
                    +"  kadry.vOtp    vo, "
                    +"  kadry.Karta   k, "
                    +"  kadry.Otp o, "

                    +"  kadry.Otp_Razdel r, "
                    +"  (select odi1.Id "
                    +"        , odi1.Otp_Id "
                    +"        , odi1.S "
                    +"        , odi1.Po "
                    +"        , odi1.Kol "
                    +"        , odi1.data_vvoda "
                    +"        , odi1.Kompens "
                    +"        , odi1.Mat_Pom "
                    +"        , odi1.Otp_Razdel_Id "
                    +"        , odi1.Osnova_Id "
                //  +"        , odi1.Prichina "
                    +"        , odi1.Raspr "
                    +"        , odi1.Pricaz_Id "
                    +"       , p1.data "
                    +"        , p1.nomer "
                    +"        , p1.project "
                    +"     from kadry.Pricaz p1, "
                    +"          kadry.Otp_Dni_Ispolz odi1 "
                    +"    where odi1.Pricaz_Id = p1.Id(+) "
                    +"     ) odi "
                    +" WHERE ((vo.Id = o.vOtp_Id "
                    +" AND o.Karta_Id = k.Id "
                    +" AND odi.Otp_Razdel_Id = r.Id(+) "
                    +" AND o.Id = odi.Otp_Id "
                    +" ) AND o.Karta_Id = o.Karta_Id  "
                    +"  and k.tabn = " + resultSet2.getString(2)+ " ) AND o.Karta_Id = o.Karta_Id "
                    +"                      and k.tabn = " + resultSet2.getString(2)
                    +"  and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
                    +" ) dat ,     "
                    + "( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
                    +"   rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
                    +"   rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
                    +" Where gwv.date_rasch = trunc(to_date('"+ pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
                    +" and  gwv.work_time_id = 1 ) graf "   ;

            // выполняем скрипт
                //System.out.println(    sqlHours);
                statementHours = finConnection.prepareStatement(sqlHours);
                resultSetHours = statementHours.executeQuery();

                if    (resultSetHours.next())
                {
                    sumHours = sumHours.add(resultSetHours.getBigDecimal(1)) ;
                    if (isDebug) System.out.println(resultSetHours.getBigDecimal(1));
                }

                    try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
                    try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

                    try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
                    try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

            }


                return  sumHours ;
            } catch (Exception e) {
                System.out.println(    e.getMessage()+ "errorr9 - getcounthoursotpuskboln");

                return  sumHours ;
            } finally {

                //System.out.println("finally getCountHoursOtpusk:" + new Date());

                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
                try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

                try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
                try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

                try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
                try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


                    try {
                        if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                            finConnection.close();
                            finConnection = null;}
                    } catch (SQLException e) {
                    }
            }

        }
 ///// Выборка табельных в указаном подразделении
public String getAllTabnInBrigada(String pdate_srez , Integer  PcodePodr ) throws PersistenceException
{
    //System.out.println("running getAllTabnInBrigada");

    String out = new String("");

    boolean isDebug = false;
    boolean isNewConn = false;
    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    try {
        String sql = " SELECT  tabn    from ( "+
        //" SELECT /*+ RULE */ "
        " SELECT "
        + "/*-- Назначение на Должность*/ "
    + " n.Karta_Id, "
    + " kr.TabN, "
    + " fio.F||' '||fio.I||' '||fio.O FIO "
    + "  FROM "
    + " kadry.Prom            prom, "
    + " kadry.Kategory        k, "
    + " (SELECT gp2.Grafik_Id, gp2.Norma "
    + "  FROM  kadry.Grafik_Period  gp2 "
    + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
    + "     AND rownum<=1) g, "
    + " kadry.Doljnost      d, "
    + " kadry.Doljnost_Day  dd, "
    + " (SELECT p.Id             Podr_Id, "
    + "        p.Date_Start     Podr_Date_Start, "
    + "        p.Date_Finish    Podr_Date_Finish, "
    + "        pd.ROWID         Podr_Day_Row_Id, "
    + "        pd.Podr_Id       Podr_Day_Podr_Id, "
    + "        pd.Date_Start    Podr_Day_Date_Start, "
    + "        pd.Main_Podr_Id  Main_Podr_Id, "
    + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
    + "        pd.Nazv          Podr_Nazv, "
    + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
    + "        pd.Kod           Podr_Kod, "
    + "        pd.Prom_Id       Podr_Prom_Id , "
    + "        pd.Pos           Podr_Pos, "
    + "        pd.Vne_Shtata    Vne_Shtata, "
    + "        ceh.ROWID        Ceh_Row_Id, "
    + "        ceh.Podr_Id      Ceh_Id, "
    + "        ceh.Date_Start   Ceh_Date_Start, "
    + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
    + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
    + "        ceh.Nazv         Ceh_Nazv, "
    + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
    + "        ceh.Kod          Ceh_Kod, "
    + "        ceh.Prom_Id      Ceh_Prom_Id, "
    + "        ceh.Pos          Ceh_Pos "
    + "   FROM kadry.Podr_Day  ceh, "
    + "        kadry.Podr_Day  pd, "
    + "        kadry.Podr      p "
    + "  WHERE pd.Podr_Id(+) = p.Id "
    + "    AND (pd.Podr_Id IS NULL "
    + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day pd1 "
    + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
    + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "        ) "
    + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day ceh1 "
    + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
    + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND p.Id = " + PcodePodr
    + "  ) pd, "
    + " kadry.v_Post       ps, "
    + " kadry.v_Post       ps1, "
    + " /*-- предыдущее назначение*/ "
    + " kadry.Perevod_Vid  pv, "
    + " kadry.Osnova       os, "
    + " kadry.Nazn         n2, "
    + " kadry.Doljnost     d2, "
    + " kadry.Post         ps2, "
    + " kadry.Razr         rz2, "
    + " kadry.Prof         pf2, "
    + " /*-- следующее назначение*/ "
    + " kadry.Perevod_Vid  pv3, "
    + " kadry.Osnova       os3, "
    + " kadry.Nazn         n3, "
    + " kadry.Doljnost     d3, "
    + " kadry.Post         ps3, "
    + " kadry.Razr         rz3, "
    + " kadry.Prof         pf3, "
    + " kadry.FIO          fio, "
    + " kadry.Karta        kr, "
    + " kadry.Nazn_Vid     nv, "
    + " kadry.Pricaz       pr1, "
    + " kadry.Pricaz       pr2, "
    + " kadry.Pricaz       pr3, "
    + " kadry.Nazn_Day     nd, "
    + " kadry.Nazn         n "
    + " WHERE (nd.Nazn_Id = n.Id "
    + " /*-- Выбор параметров на дату среза*/ "
    + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + " AND NOT EXISTS "
    + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
    + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
    + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND nd2.Date_Start > nd.Date_Start) "
    + " AND n.Start_Pricaz_Id  = pr1.Id "
    + " AND n.Finish_Pricaz_Id = pr2.Id "
    + " AND nd.Pricaz_Id       = pr3.Id "
    + " AND n.Doljnost_Id = d.Id "
    + " AND pd.Podr_Id = d.Podr_Id "
    + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
    + " AND ps1.Post_Id = d.Post_Id "
    + " AND k.Id = d.Kategory_Id "
    + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
    + " AND dd.Doljnost_Id(+) = d.Id "
    + " /*-- Выбор параметров должности на дату*/ "
    + " AND (dd.Doljnost_Id IS NULL "
    + "   OR (dd.Date_Start <= n.Date_Finish  "
    + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
    + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
    + "                        AND dd2.Date_Start <= n.Date_Finish  "
    + "                            AND dd2.Date_Start > dd.Date_Start))) "
    + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
      + "    /*-- предыдущее назначение*/ "
      + "      AND pv.Id(+) = n.Perevod_Vid_Id "
    + "      AND os.Id(+) = n.Perevod_Osnova_Id "
    + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
      + "      AND n2.Doljnost_Id = d2.Id(+) "
      + "      AND ps2.Id(+) = d2.Post_Id "
    + "      AND pf2.Id(+) = ps2.Prof_Id "
    + "      AND rz2.Id(+) = ps2.Razr_Id   "
    + "    /*-- сдедующее назначение*/ "
    + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
   + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
   + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
   + " AND d3.Id(+)  = n3.Doljnost_Id "
   + " AND ps3.Id(+) = d3.Post_Id "
   + " AND pf3.Id(+) = ps3.Prof_Id "
   + " AND rz3.Id(+) = ps3.Razr_Id   "
   + " AND n.Karta_Id     = kr.Id "
   + " AND upper(kr.prim) like ('%НВЗ%') "
   + " AND fio.Id         = kr.FIO_Id "
   + " AND nd.Nazn_Vid_Id = nv.Id(+) "
   + "    /*-- график назначения*/ "
    + " ) AND  "
    + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
    + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
    //    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
    + " ORDER BY kr.TabN , n.Date_Start desc  " +
        " ) " ;


        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param : " + PcodePodr);
        }


    if (finConnection == null){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        isNewConn = true;
    }


    statement = finConnection.prepareStatement(sql);
//    statement.setInt(1, PcodePodr.intValue());



        resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            if (out.length() > 0)
            {
            out = out + " , " + resultSet.getString(1);
            }
            else
            {
            out = resultSet.getString(1);
            }
        }
        if (out.length() == 0)
        {return new String("-1");
            }
        else
            {
        return "("+ out + ")" ;
            }
    }
    //catch (SQLException e) {
    //    System.out.println(    e.getMessage());
    //    throw new SystemException(e);
    //}
    catch (Exception e) {
        System.out.println("Error :" + e.getMessage());
        throw new SystemException(e);
    }
    finally {
        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;}
            } catch (SQLException e) {
            }
    }


}

//выборка количества рабочих часов в месяце для работника
public BigDecimal getCountWorkHoursByWorker(String pdate_srez , String tabn  ) throws PersistenceException
{

    //System.out.println("start getCountWorkHoursByWorker:" + new Date());

    // Date firstDay = getFirstDayInMonth(pdate_srez);



    BigDecimal out = new BigDecimal(0);
    BigDecimal sumHours = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement statement2 = null;
    ResultSet resultSet2 = null;

    boolean isDebug = false;

    boolean isNewConn = false;

    //PreparedStatement statementnastroyka = null;
    //ResultSet resultSetnastroyka = null;


    if (isDebug) {
    System.out.println(" param date" + pdate_srez);
    System.out.println(" param tabn"+ tabn);
    }

    try {

        // altersession();

        //statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
        //resultSetnastroyka = statementnastroyka.executeQuery();

    String sql = " SELECT " +
                 " DECODE(s.S, TO_DATE('01.01.1900','dd.mm.yyyy'), TO_DATE(NULL), TO_DATE('01.01.3000','dd.mm.yyyy'), TO_DATE(NULL), s.S) S " +
                 " FROM " +
                 " kadry.v_Karta   s " +
                 " WHERE s.TabN = " +"'" + Integer.parseInt(tabn) + "'" +
                 " And to_char(s.s,'mm.yyyy') = to_char(to_date('" + pdate_srez+ " ','dd.mm.yyyy'),'mm.yyyy')" +
                 " ORDER BY s.TabN " ;

    if ((finConnection == null) || (finConnection.isClosed()) ){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        isNewConn = true;
    }

    if (isDebug)
        System.out.println(" скрипт выбор даты с "+ sql);

    statement = finConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();

    if    (resultSet.next())
    {
        if (isDebug)
            System.out.println(" Если человек пришел в текущ месяце " );

        String sql_fhours = " Select "  +
            " case when '01' < to_char(s1.s,'dd') then 0 else s2.rday1 end  +  " +
            " case when '02' < to_char(s1.s,'dd') then 0 else s2.rday2 end  +  " +
            " case when '03' < to_char(s1.s,'dd') then 0 else s2.rday3 end  +  " +
            " case when '04' < to_char(s1.s,'dd') then 0 else s2.rday4 end  +  " +
            " case when '05' < to_char(s1.s,'dd') then 0 else s2.rday5 end  +  " +
            " case when '06' < to_char(s1.s,'dd') then 0 else s2.rday6 end  +  " +
            " case when '07' < to_char(s1.s,'dd') then 0 else s2.rday7 end  +  " +
            " case when '08' < to_char(s1.s,'dd') then 0 else s2.rday8 end  +  " +
            " case when '09' < to_char(s1.s,'dd') then 0 else s2.rday9 end  +  " +
            " case when '10' < to_char(s1.s,'dd') then 0 else s2.rday10 end  +  " +
            " case when '11' < to_char(s1.s,'dd') then 0 else s2.rday11 end  +  " +
            " case when '12' < to_char(s1.s,'dd') then 0 else s2.rday12 end  +  " +
            " case when '13' < to_char(s1.s,'dd') then 0 else s2.rday13 end  +  " +
            " case when '14' < to_char(s1.s,'dd') then 0 else s2.rday14 end  +  " +
            " case when '15' < to_char(s1.s,'dd') then 0 else s2.rday15 end  +  " +
            " case when '16' < to_char(s1.s,'dd') then 0 else s2.rday16 end  +  " +
            " case when '17' < to_char(s1.s,'dd') then 0 else s2.rday17 end  +  " +
            " case when '18' < to_char(s1.s,'dd') then 0 else s2.rday18 end  +   " +
            " case when '19' < to_char(s1.s,'dd') then 0 else s2.rday19 end  +  " +
            " case when '20' < to_char(s1.s,'dd') then 0 else s2.rday20 end  +  " +
            " case when '21' < to_char(s1.s,'dd') then 0 else s2.rday21 end  +  " +
            " case when '22' < to_char(s1.s,'dd') then 0 else s2.rday22 end  +  " +
            " case when '23' < to_char(s1.s,'dd') then 0 else s2.rday23 end  +  " +
            " case when '24' < to_char(s1.s,'dd') then 0 else s2.rday24 end  +  " +
            " case when '25' < to_char(s1.s,'dd') then 0 else s2.rday25 end  +  " +
            " case when '26' < to_char(s1.s,'dd') then 0 else s2.rday26 end  +  " +
            " case when '27' < to_char(s1.s,'dd') then 0 else s2.rday27 end  +  " +
            " case when '28' < to_char(s1.s,'dd') then 0 else s2.rday28 end  +  " +
            " case when '29' < to_char(s1.s,'dd') then 0 else s2.rday29 end  +  " +
            " case when '30' < to_char(s1.s,'dd') then 0 else s2.rday30 end  +  " +
            " case when '31' < to_char(s1.s,'dd') then 0 else s2.rday31 end  hours  "  +
            " from  " +
            " (  " +
            " SELECT  " +
            "   DECODE(s.S, TO_DATE('01.01.1900','dd.mm.yyyy'), TO_DATE(NULL), TO_DATE('01.01.3000','dd.mm.yyyy'), TO_DATE(NULL), s.S) S  " +
            " FROM " +
            "   kadry.v_Karta   s  " +
            " WHERE s.TabN = '"+ tabn + "'" +
            " ORDER BY s.TabN ) s1 ,  " +
            " ( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10, "  +
            " rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "  +
            " rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 "  +
            " FROM   zarpherson.grafik_work_view "  +
            " WHERE  date_rasch =  trunc(to_date('" + pdate_srez + "','dd.mm.yyyy'),'mm') "  +
            " And  work_time_id = 1 ) s2 ";

        if (isDebug){
        System.out.println(" sql : " + sql_fhours);
        System.out.println(" значение по табельному  " + tabn + " "  );
        }

        if (finConnection == null)
                return new BigDecimal(Integer.MIN_VALUE);

        statement2 = finConnection.prepareStatement(sql_fhours);
        resultSet2 = statement2.executeQuery();
        if    (resultSet2.next())
        {
        sumHours = resultSet2.getBigDecimal(1) ;
        }
        else
        {
        sumHours = new BigDecimal(2000);
        }

        //try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        //try {if (statement != null) statement.close();} catch (SQLException e) {}

        //try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        //try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

        if (isDebug)
            System.out.println("время если человек пришел в этом месяце : " + sumHours);

        return  sumHours ;
    }
    else
    {
        sql = "";
        sql = " SELECT  nvl(rday1,0) + nvl(rday2,0) + nvl(rday3,0) + nvl(rday4,0) + nvl(rday5,0) + nvl(rday6,0) + nvl(rday7,0) + nvl(rday8,0) + nvl(rday9,0) + nvl(rday10,0) + "
        + " nvl(rday11,0) + nvl(rday12,0) + nvl(rday13,0) + nvl(rday14,0) + nvl(rday15,0) + nvl(rday16,0) + nvl(rday17,0) + nvl(rday18,0) + nvl(rday19,0) + nvl(rday20,0) + "
        + " nvl(rday21,0) + nvl(rday22,0) + nvl(rday23,0) + nvl(rday24,0) + nvl(rday25,0) + nvl(rday26,0) + nvl(rday27,0) + nvl(rday28,0) + nvl(rday29,0) + nvl(rday30,0) + nvl(rday31,0) as hours"
        + " FROM   zarpherson.grafik_work_view "
        + " WHERE  date_rasch =  trunc(to_date('" + pdate_srez+ " ','dd.mm.yyyy'),'mm')"
        + " and  work_time_id = 1 " ;

        //System.out.println("sql : " + sql);
        //System.out.println("paramworkday : " + pdate_srez.toString());
        if (isDebug)
            System.out.println(" sql on  tabn zag fond time  " + tabn + " " + sql  );

        if (finConnection == null)
            return new BigDecimal(Integer.MIN_VALUE);

            statement2 = finConnection.prepareStatement(sql);
            resultSet2 = statement2.executeQuery();

        if    (resultSet2.next())
            {
            sumHours = resultSet2.getBigDecimal(1) ;
            if (isDebug)
                System.out.println(" result  on  tabn zag fond time  " + tabn + " " + sumHours  );
            }
        else
            {
            sumHours = new BigDecimal(2000);
            }

        //try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        //try {if (statement != null) statement.close();} catch (SQLException e) {}

        //try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        //try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

        if (isDebug)
            System.out.println("время если человек работал раньше : " + sumHours);

        return  sumHours ;

    }





    } catch (Exception e) {
        System.out.println(    e.getMessage() );
        //return  sumHours ;
        throw new SystemException(e);
    } finally {

        if (isDebug)
            System.out.println("finally getCountWorkHoursByWorker:" + new Date());

        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                finConnection = null;}
            } catch (SQLException e) {
            }
    }

}

/**
 *
 * Возвращает счет на котором будет счетчик после проведения внутреннего перемещения
 *
 * @param currentAccount текущий счет счетчика
 * @param molIn МОЛ-отправить
 * @param molOut МОЛ-получатель
 * @return будущий счет счетчика
 */
public String getNextAccountForVnPerCounters(String currentAccount, String molIn, String molOut) {
	try {
		Vector<String> bindedParams = new Vector<String>();
		bindedParams.add(currentAccount);
		bindedParams.add("0" + molIn.substring(0, 2));
		bindedParams.add("0" + molOut.substring(0,2));

		boolean isNewConn = false;
	    if ((finConnection == null) || (finConnection.isClosed()) ){
	        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
	        isNewConn = true;
	    }
		return executeScalar("select countersread.pkg_doc.vn_per_next_account(?,?,?) from dual", finConnection, bindedParams, isNewConn);
	} catch(PersistenceException e) {
		throw new RuntimeException(e);
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} catch (DatasourceConnectException e) {
		throw new RuntimeException(e);
	} finally {

	}

}

@SuppressWarnings("unchecked")
private <E> E executeScalar(String sql, Connection pConnection, Vector<? extends Object> bindedObjects, boolean isCloseConnection) throws PersistenceException {
	PreparedStatement statement = null;
	ResultSet set = null;
	E out = null;
	Object obj = null;
	try {
		statement = pConnection.prepareStatement(sql);
		if(bindedObjects != null) {
			int index = 1;
			for(Object param : bindedObjects) {
				if(param.getClass() == java.lang.Integer.class) {
					statement.setInt(index++, (Integer)param);
				} else if(param.getClass() == java.lang.Boolean.class) {
					statement.setBoolean(index++, (Boolean)param);
				} else if(param.getClass() == java.lang.Long.class) {
					statement.setLong(index++, (Long)param);
				} else if(param.getClass() == java.lang.Character.class) {
					statement.setString(index++, (String)param);
				} else if(param.getClass() == java.lang.Double.class) {
					statement.setDouble(index++, (Double)param);
				} else if(param.getClass() == java.lang.Byte.class) {
					statement.setByte(index++, (Byte)param);
				} else if(param.getClass() == java.lang.Short.class) {
					statement.setShort(index++, (Short)param);
				} else if(param.getClass() == java.lang.String.class) {
					statement.setString(index++, (String)param);
				} else if(param.getClass() == java.math.BigDecimal.class) {
					statement.setBigDecimal(index++, (BigDecimal)param);
				} else if(param.getClass() == java.util.Date.class) {
					statement.setDate(index++, new java.sql.Date(((Date)param).getTime()));
				}
			}
		}
		set = statement.executeQuery();
		if(set.next()) {
			obj = set.getObject(1);
		}
		out = (E)obj;
		return out;
	} catch(SQLException e) {
		System.out.println(e.getMessage()+"\nstatement - "+sql);
		throw new PersistenceException(e.getMessage(), e);
	} finally {
		try {if (set != null) set.close();} catch (SQLException e) {}
		try {if (statement != null) statement.close();} catch (SQLException e) {}
        try {
			if  ((isCloseConnection ) && (pConnection != null && !pConnection.isClosed())){
				pConnection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
public BigDecimal getCountEltehpersonalFromKadryRegular(String pdate_srez , Integer ppodrcod , String type_pers , String regular ) throws PersistenceException
{

    boolean isDebug = false;

    BigDecimal out = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    if (isDebug)
        System.out.println("start getCountEltehpersonalFromKadryRegular" + new Date());

    String sql = " SELECT  count(*) as count  from ( "+
    //" SELECT /*+ RULE */ "
    " SELECT "
    + "/*-- Назначение на Должность*/ "

  + " n.Karta_Id, "
  + " kr.TabN, "
  + " fio.F||' '||fio.I||' '||fio.O FIO "

  + "  FROM "
  + " kadry.Prom            prom, "
  + " kadry.Kategory        k, "
  + " (SELECT gp2.Grafik_Id, gp2.Norma "
  + "  FROM  kadry.Grafik_Period  gp2 "
  + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
  + "     AND rownum<=1) g, "
  + " kadry.Doljnost      d, "
  + " kadry.Doljnost_Day  dd, "
  + " (SELECT p.Id             Podr_Id, "
  + "        p.Date_Start     Podr_Date_Start, "
  + "        p.Date_Finish    Podr_Date_Finish, "
  + "        pd.ROWID         Podr_Day_Row_Id, "
  + "        pd.Podr_Id       Podr_Day_Podr_Id, "
  + "        pd.Date_Start    Podr_Day_Date_Start, "
  + "        pd.Main_Podr_Id  Main_Podr_Id, "
  + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
  + "        pd.Nazv          Podr_Nazv, "
  + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
  + "        pd.Kod           Podr_Kod, "
  + "        pd.Prom_Id       Podr_Prom_Id , "
  + "        pd.Pos           Podr_Pos, "
  + "        pd.Vne_Shtata    Vne_Shtata, "
  + "        ceh.ROWID        Ceh_Row_Id, "
  + "        ceh.Podr_Id      Ceh_Id, "
  + "        ceh.Date_Start   Ceh_Date_Start, "
  + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
  + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
  + "        ceh.Nazv         Ceh_Nazv, "
  + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
  + "        ceh.Kod          Ceh_Kod, "
  + "        ceh.Prom_Id      Ceh_Prom_Id, "
  + "        ceh.Pos          Ceh_Pos "
  + "   FROM kadry.Podr_Day  ceh, "
  + "        kadry.Podr_Day  pd, "
  + "        kadry.Podr      p "
  + "  WHERE pd.Podr_Id(+) = p.Id "
  + "    AND (pd.Podr_Id IS NULL "
  + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day pd1 "
  + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
  + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "        ) "
  + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day ceh1 "
  + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
  + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND p.Id = " + ppodrcod
  + "  ) pd, "
  + " kadry.v_Post       ps, "
  + " kadry.v_Post       ps1, "

  + " /*-- предыдущее назначение*/ "
  + " kadry.Perevod_Vid  pv, "
  + " kadry.Osnova       os, "
  + " kadry.Nazn         n2, "
  + " kadry.Doljnost     d2, "
  + " kadry.Post         ps2, "
  + " kadry.Razr         rz2, "
  + " kadry.Prof         pf2, "
  + " /*-- следующее назначение*/ "
  + " kadry.Perevod_Vid  pv3, "
  + " kadry.Osnova       os3, "
  + " kadry.Nazn         n3, "
  + " kadry.Doljnost     d3, "
  + " kadry.Post         ps3, "
  + " kadry.Razr         rz3, "
  + " kadry.Prof         pf3, "

  + " kadry.FIO          fio, "
  + " kadry.Karta        kr, "
  + " kadry.Nazn_Vid     nv, "
  + " kadry.Pricaz       pr1, "
  + " kadry.Pricaz       pr2, "
  + " kadry.Pricaz       pr3, "
  + " kadry.Nazn_Day     nd, "
  + " kadry.Nazn         n "

  + " WHERE (nd.Nazn_Id = n.Id "

  + " /*-- Выбор параметров на дату среза*/ "
  + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + " AND NOT EXISTS "
  + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
  + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
  + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND nd2.Date_Start > nd.Date_Start) "
  + " AND n.Start_Pricaz_Id  = pr1.Id "
  + " AND n.Finish_Pricaz_Id = pr2.Id "
  + " AND nd.Pricaz_Id       = pr3.Id "
  + " AND n.Doljnost_Id = d.Id "
  + " AND pd.Podr_Id = d.Podr_Id "
  + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
  + " AND ps1.Post_Id = d.Post_Id "
  + " AND k.Id = d.Kategory_Id "
  + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
  + " AND dd.Doljnost_Id(+) = d.Id "
  + " /*-- Выбор параметров должности на дату*/ "
  + " AND (dd.Doljnost_Id IS NULL "
  + "   OR (dd.Date_Start <= n.Date_Finish  "
  + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
  + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
  + "                        AND dd2.Date_Start <= n.Date_Finish  "
  + "                            AND dd2.Date_Start > dd.Date_Start))) "
  + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
  + "    /*-- предыдущее назначение*/ "
  + "      AND pv.Id(+) = n.Perevod_Vid_Id "
  + "      AND os.Id(+) = n.Perevod_Osnova_Id "
  + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
  + "      AND n2.Doljnost_Id = d2.Id(+) "
  + "      AND ps2.Id(+) = d2.Post_Id "
  + "      AND pf2.Id(+) = ps2.Prof_Id "
  + "      AND rz2.Id(+) = ps2.Razr_Id   "
  + "    /*-- сдедующее назначение*/ "
  + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
+ "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
+ " AND os3.Id(+) = n3.Perevod_Osnova_Id "
+ " AND d3.Id(+)  = n3.Doljnost_Id "
+ " AND ps3.Id(+) = d3.Post_Id "
+ " AND pf3.Id(+) = ps3.Prof_Id "
+ " AND rz3.Id(+) = ps3.Razr_Id   "
+ " AND n.Karta_Id     = kr.Id "

+ " AND  ( "
+ "        (    trim(UPPER(kr.prim)) LIKE ('%"+type_pers+"%') "
+ "            AND trim(UPPER(kr.prim)) LIKE ('%"+regular+"%') "
+ "            ) "
+ "     OR     (    trim(UPPER(kr.prim)) LIKE ('"+type_pers+"')  AND '"+regular+"' = 'SHTAT'  "// 06.07.2015 SUPP-36182 - учитывался работник с признаком просто НВЗ. // SUPP-29631 - выбирался лишний персонал
+ "              AND (  "
+ "                   trim(UPPER(kr.prim)) NOT LIKE ('%RZA%') "
+ "              and (trim(UPPER(kr.prim)) NOT LIKE ('%DTU%'))  "
+ "              and (trim(UPPER(kr.prim)) NOT LIKE ('%IZ%')) "
+ "                  ) "
+ "            ) "
+ "        )   "
+ " AND fio.Id         = kr.FIO_Id "
+ " AND nd.Nazn_Vid_Id = nv.Id(+) "
+ "    /*-- график назначения*/ "
  + " ) AND  "
+ "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
+ "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
//+ " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
+ " ORDER BY kr.TabN , n.Date_Start desc  " +
    " ) " ;


//    sql = "select count(*) count from kadry.Doljnost dd  Where dd.date_start >= ? " ;

    if (isDebug){
        System.out.println(" sql : " + sql);
        System.out.println("parampodrcod : " + ppodrcod.toString());
        System.out.println("paramdate : " + pdate_srez.toString());
        System.out.println("paramdate2 : " + pdate_srez);
    }

    //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
    //finConnection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();



    boolean isNewConn = false;

    try {


        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

    /*    statement.setDate(1, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(2, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(3, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(4, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(5, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(6, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(7, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(8, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(9, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(10, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(11, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(12, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(13, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(14, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(15, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(16, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(17, new java.sql.Date(pdate_srez.getTime()));
        statement.setDate(18, new java.sql.Date(pdate_srez.getTime()));
        statement.setInt(19, ppodrcod.intValue()); */


        resultSet = statement.executeQuery();
        //resultSet.next();
    //    if (!resultSet.next()) {
    //        throw new SystemException("Count work time not found for date " + d);
    //    }

        if (resultSet.next())
        out = resultSet.getBigDecimal(1);
        else
          System.out.println("resultSet clear getCountEltehpersonalFromKadry");

        //System.out.println(); //new java.sql.Date(firstDay.getTime()));

        //System.out.println("paramdate2 : " + out);
        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "error 25  - getcounteltehpersonalfromkadryregular");
        return out;
    } finally {

        //System.out.println("finally getCountEltehpersonalFromKadry" + new Date());

        try {
            if (resultSet != null)
                resultSet.close();
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;}
                    //System.out.println("Close connect");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

}


//     выборка количества  часов в месяце для отпускников с учетом разбивки технической части
//  на сдту и тд
public BigDecimal getCountHoursOtpuskRegular(String pdate_srez , Integer ppodrcod , String type_pers , String regular ) throws PersistenceException
{
    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCountHoursOtpusk:" + new Date());

    // Date firstDay = getFirstDayInMonth(pdate_srez);
    BigDecimal out = new BigDecimal(0);
    BigDecimal sumHours = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement statementHours = null;
    ResultSet resultSetHours = null;

        PreparedStatement statement2 = null;
        ResultSet resultSet2 =  null;

        PreparedStatement statementnastroyka = null;
        ResultSet resultSetnastroyka = null;

    boolean isNewConn = false;

    try {
    String sql = " alter session "
            + " set   NLS_TERRITORY=AMERICA "
            + " NLS_LANGUAGE=AMERICAN "
            + " OPTIMIZER_MODE=RULE " ;

    if (finConnection == null){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        isNewConn = true;
    }

    statement = finConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();



    sql = "";
    // текст запроса назначеных людей
    sql = //" SELECT /*+ RULE */ "
            " SELECT "
        + "/*-- Назначение на Должность*/ "

    + " n.Karta_Id, "
    + " kr.TabN, "
    + " fio.F||' '||fio.I||' '||fio.O FIO "

    + "  FROM "
    + " kadry.Prom            prom, "
    + " kadry.Kategory        k, "
    + " (SELECT gp2.Grafik_Id, gp2.Norma "
    + "  FROM  kadry.Grafik_Period  gp2 "
    + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
    + "     AND rownum<=1) g, "
    + " kadry.Doljnost      d, "
    + " kadry.Doljnost_Day  dd, "
    + " (SELECT p.Id             Podr_Id, "
    + "        p.Date_Start     Podr_Date_Start, "
    + "        p.Date_Finish    Podr_Date_Finish, "
    + "        pd.ROWID         Podr_Day_Row_Id, "
    + "        pd.Podr_Id       Podr_Day_Podr_Id, "
    + "        pd.Date_Start    Podr_Day_Date_Start, "
    + "        pd.Main_Podr_Id  Main_Podr_Id, "
    + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
    + "        pd.Nazv          Podr_Nazv, "
    + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
    + "        pd.Kod           Podr_Kod, "
    + "        pd.Prom_Id       Podr_Prom_Id , "
    + "        pd.Pos           Podr_Pos, "
    + "        pd.Vne_Shtata    Vne_Shtata, "
    + "        ceh.ROWID        Ceh_Row_Id, "
    + "        ceh.Podr_Id      Ceh_Id, "
    + "        ceh.Date_Start   Ceh_Date_Start, "
    + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
    + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
    + "        ceh.Nazv         Ceh_Nazv, "
    + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
    + "        ceh.Kod          Ceh_Kod, "
    + "        ceh.Prom_Id      Ceh_Prom_Id, "
    + "        ceh.Pos          Ceh_Pos "
    + "   FROM kadry.Podr_Day  ceh, "
    + "        kadry.Podr_Day  pd, "
    + "        kadry.Podr      p "
    + "  WHERE pd.Podr_Id(+) = p.Id "
    + "    AND (pd.Podr_Id IS NULL "
    + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day pd1 "
    + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
    + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "        ) "
    + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day ceh1 "
    + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
    + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND p.Id = " + ppodrcod
    + "  ) pd, "
    + " kadry.v_Post       ps, "
    + " kadry.v_Post       ps1, "

    + " /*-- предыдущее назначение*/ "
    + " kadry.Perevod_Vid  pv, "
    + " kadry.Osnova       os, "
    + " kadry.Nazn         n2, "
    + " kadry.Doljnost     d2, "
    + " kadry.Post         ps2, "
    + " kadry.Razr         rz2, "
    + " kadry.Prof         pf2, "
    + " /*-- следующее назначение*/ "
    + " kadry.Perevod_Vid  pv3, "
    + " kadry.Osnova       os3, "
    + " kadry.Nazn         n3, "
    + " kadry.Doljnost     d3, "
    + " kadry.Post         ps3, "
    + " kadry.Razr         rz3, "
    + " kadry.Prof         pf3, "

    + " kadry.FIO          fio, "
    + " kadry.Karta        kr, "
    + " kadry.Nazn_Vid     nv, "
    + " kadry.Pricaz       pr1, "
    + " kadry.Pricaz       pr2, "
    + " kadry.Pricaz       pr3, "
    + " kadry.Nazn_Day     nd, "
    + " kadry.Nazn         n "

    + " WHERE (nd.Nazn_Id = n.Id "

    + " /*-- Выбор параметров на дату среза*/ "
    + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + " AND NOT EXISTS "
    + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
    + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
    + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND nd2.Date_Start > nd.Date_Start) "
    + " AND n.Start_Pricaz_Id  = pr1.Id "
    + " AND n.Finish_Pricaz_Id = pr2.Id "
    + " AND nd.Pricaz_Id       = pr3.Id "
    + " AND n.Doljnost_Id = d.Id "
    + " AND pd.Podr_Id = d.Podr_Id "
    + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
    + " AND ps1.Post_Id = d.Post_Id "
    + " AND k.Id = d.Kategory_Id "
    + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
    + " AND dd.Doljnost_Id(+) = d.Id "
    + " /*-- Выбор параметров должности на дату*/ "
    + " AND (dd.Doljnost_Id IS NULL "
    + "   OR (dd.Date_Start <= n.Date_Finish  "
    + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
    + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
    + "                        AND dd2.Date_Start <= n.Date_Finish  "
    + "                            AND dd2.Date_Start > dd.Date_Start))) "
    + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
      + "    /*-- предыдущее назначение*/ "
      + "      AND pv.Id(+) = n.Perevod_Vid_Id "
    + "      AND os.Id(+) = n.Perevod_Osnova_Id "
    + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
      + "      AND n2.Doljnost_Id = d2.Id(+) "
      + "      AND ps2.Id(+) = d2.Post_Id "
    + "      AND pf2.Id(+) = ps2.Prof_Id "
    + "      AND rz2.Id(+) = ps2.Razr_Id   "
    + "    /*-- сдедующее назначение*/ "
    + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
   + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
   + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
   + " AND d3.Id(+)  = n3.Doljnost_Id "
   + " AND ps3.Id(+) = d3.Post_Id "
   + " AND pf3.Id(+) = ps3.Prof_Id "
   + " AND rz3.Id(+) = ps3.Razr_Id   "
   + " AND n.Karta_Id     = kr.Id "

   // + " AND upper(kr.prim) like ('%НВЗ%') "

   + " AND  ( "
   + "        (    UPPER (kr.prim) LIKE ('%"+type_pers+"%') "
   + "            AND UPPER (kr.prim) LIKE ('%"+regular+"%') "
   + "            ) "
   + "     OR     (    UPPER (kr.prim) LIKE ('"+type_pers+"') "// SUPP-29631 - выбирался лишний персонал
   + "              AND (  "
   + "                   UPPER (kr.prim) NOT LIKE ('%RZA%') "
   + "              and (UPPER (kr.prim) NOT LIKE ('%DTU%'))  "
   + "              and (UPPER (kr.prim) NOT LIKE ('%IZ%')) "
   + "                  ) "
   + "            ) "
   + "        ) "

   + " AND fio.Id         = kr.FIO_Id "
   + " AND nd.Nazn_Vid_Id = nv.Id(+) "
   + "    /*-- график назначения*/ "
    + " ) AND  "
    + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
    + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
    //    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
    + " ORDER BY kr.TabN, n.Date_Start desc  " ;

    if (isDebug){
        System.out.println("sql выборка людей : " + sql);
        System.out.println("paramworkday : " + pdate_srez.toString());
    }




        // PreparedStatement statement2 = finConnection.prepareStatement(sql);
        // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
        statement2 = finConnection.prepareStatement(sql);
        resultSet2 = statement2.executeQuery();

      // получили список сотрудников
        int i;

    while (resultSet2.next())
    {
        // по каждому сотруднику выбирам количество часов в отпуске
        String sqlnastroyka = " alter session "
            + " set   NLS_TERRITORY=AMERICA "
            + " NLS_LANGUAGE=AMERICAN "
            + " OPTIMIZER_MODE=RULE " ;

    statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
    resultSetnastroyka = statement.executeQuery();
    if (isDebug)
        System.out.println(" Табельный " + resultSet2.getString(2) );

        String sqlHours = " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                + "  ) "
                + "     Then graf.rday1 else 0 end  "
            + " +  "
            + " case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
            + "  ) "
            + "         Then graf.rday2 else 0 end "
            +" +    "
            +"  case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday3 else 0 end   "
            +"   +  "
            +"  case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday4 else 0 end    "
            +" +  "
            +"  case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday5 else 0 end "
            +" + "
            +"  case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday6 else 0 end     "
            +" + "
            +"  case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday7 else 0 end     "
            +" +  "
            +"  case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday8 else 0 end    "
            +" +  "
            +"  case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday9 else 0 end    "
            +" +  "
            +"  case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday10 else 0 end  "
            +" +  "
            +"  case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday11 else 0 end    "
            +" +  "
            +"  case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday12 else 0 end     "
            +" + "
            +"  case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday13 else 0 end   "
            +" +  "
            +"  case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday14 else 0 end   "
            +" + "
            +"  case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday15 else 0 end  "
            +" +  "
            +"  case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"           Then graf.rday16 else 0 end  "
            +" +  "
            +"  case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday17 else 0 end  "
            +" +  "
            +"  case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday18 else 0 end    "
            +" +  "
            +"  case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday19 else 0 end    "
            +" +  "
            +"  case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday20 else 0 end      "
            +" +  "
            +"  case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday21 else 0 end     "
            +" +  "
            +"  case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday22 else 0 end      "
            +" +  "
            +"  case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday23 else 0 end  "
            +" + "
            +"  case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
            +"  ) "
            +"          Then graf.rday24 else 0 end  "
            +" +  "
            +"  case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday25 else 0 end   "
            +" +  "
            +"  case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday26 else 0 end "
            +" + "
            +"  case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday27 else 0 end       "
            +" +  "
            +"  case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday28 else 0 end "
            +" +  "
            +"  case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then "
            +"  case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday29 else 0 end  "
            +" else 0  end      "
            +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
            +"  case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday30 else 0 end  "
            +" else 0  end      "
            +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
            +"  case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday31 else 0 end "
            +" else 0  end   "
            +"          sumhours "
            +" from  (  "
            +" SELECT "
            +"  DECODE(odi.S,  TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.S) S, "
            +"  DECODE(odi.Po, TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.PO) PO "
            +" FROM "
            +"  kadry.vOtp    vo, "
            +"  kadry.Karta   k, "
            +"  kadry.Otp o, "

            +"  kadry.Otp_Razdel r, "
            +"  (select odi1.Id "
            +"        , odi1.Otp_Id "
            +"        , odi1.S "
            +"        , odi1.Po "
            +"        , odi1.Kol "
            +"        , odi1.data_vvoda "
            +"        , odi1.Kompens "
            +"        , odi1.Mat_Pom "
            +"        , odi1.Otp_Razdel_Id "
            +"        , odi1.Osnova_Id "
        //  +"        , odi1.Prichina "
            +"        , odi1.Raspr "
            +"        , odi1.Pricaz_Id "
            +"       , p1.data "
            +"        , p1.nomer "
            +"        , p1.project "
            +"     from kadry.Pricaz p1, "
            +"          kadry.Otp_Dni_Ispolz odi1 "
            +"    where odi1.Pricaz_Id = p1.Id(+) "
            +"     ) odi "
            +" WHERE ((vo.Id = o.vOtp_Id "
            +" AND o.Karta_Id = k.Id "
            +" AND odi.Otp_Razdel_Id = r.Id(+) "
            +" AND o.Id = odi.Otp_Id "
            +" ) AND o.Karta_Id = o.Karta_Id  "
            +"  and k.tabn = " + resultSet2.getString(2)+ " ) AND o.Karta_Id = o.Karta_Id "
            +"                      and k.tabn = " + resultSet2.getString(2)
            +"  and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
            +" ) dat ,     "
            + "( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
            +"   rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
            +"   rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
            +" Where gwv.date_rasch = trunc(to_date('"+pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
            +" and  gwv.work_time_id = 1 ) graf "   ;

    // выполняем скрипт
        if (isDebug)
            System.out.println(sqlHours);

        statementHours = finConnection.prepareStatement(sqlHours);
        resultSetHours = statementHours.executeQuery();

        if    (resultSetHours.next())
        {  sumHours = sumHours.add(getCountHoursOtpuskByHuman(pdate_srez.toString(),ppodrcod , resultSet2.getString(2) )); // вызываем процедуру выбора отпуска по человеку
            // sumHours = sumHours.add(resultSetHours.getBigDecimal(1)) ; // Y закомитил
            // System.out.println(resultSetHours.getBigDecimal(1));
                System.out.println(sumHours);
        }

            try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
            try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

            try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
            try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

    }


        return  sumHours ;
    } catch (Exception e) {
        System.out.println(    e.getMessage()+ "error 26 - getcounthoursotpuskregular");

        return  sumHours ;
    } finally {
        if (isDebug)
            System.out.println("finally getCountHoursOtpusk:" + new Date());

        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

        try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
        try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

        try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
        try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;}
            } catch (SQLException e) {
            }
    }

}

//выборка количества  часов в месяце сначала выборка людей в подразд потом по человеку выбирается его часы   с учетом разбивки технической части
//на сдту и тд
public BigDecimal getCountHoursByPodrByWorker(String pdate_srez , Integer ppodrcod , String type_pers , String regular ) throws PersistenceException
{
boolean isDebug = false;

if (isDebug)
    System.out.println("start getCountHoursByPodrByWorker:" + new Date());

// Date firstDay = getFirstDayInMonth(pdate_srez);
BigDecimal out = new BigDecimal(0);
BigDecimal sumHours = new BigDecimal(0);

PreparedStatement statement = null;
ResultSet  resultSet = null;

  PreparedStatement statementHours = null;
  ResultSet resultSetHours = null;

    PreparedStatement statement2 = null;
    ResultSet resultSet2 =  null;

    PreparedStatement statementnastroyka = null;
    ResultSet resultSetnastroyka = null;

boolean isNewConn = false;

try {
String sql = " alter session "
        + " set   NLS_TERRITORY=AMERICA "
        + " NLS_LANGUAGE=AMERICAN "
        + " OPTIMIZER_MODE=RULE " ;

if (finConnection == null){
    //System.out.println("finConnection is null");
    //return new BigDecimal(Integer.MIN_VALUE);
    finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    isNewConn = true;
}

statement = finConnection.prepareStatement(sql);
resultSet = statement.executeQuery();



sql = "";
// текст запроса назначеных людейl
sql = //" SELECT /*+ RULE */ "
   " SELECT "
    + "/*-- Назначение на Должность*/ "

  + " n.Karta_Id, "
  + " kr.TabN, "
  + " fio.F||' '||fio.I||' '||fio.O FIO "

  + "  FROM "
  + " kadry.Prom            prom, "
  + " kadry.Kategory        k, "
  + " (SELECT gp2.Grafik_Id, gp2.Norma "
  + "  FROM  kadry.Grafik_Period  gp2 "
  + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
  + "     AND rownum<=1) g, "
  + " kadry.Doljnost      d, "
  + " kadry.Doljnost_Day  dd, "
  + " (SELECT p.Id             Podr_Id, "
  + "        p.Date_Start     Podr_Date_Start, "
  + "        p.Date_Finish    Podr_Date_Finish, "
  + "        pd.ROWID         Podr_Day_Row_Id, "
  + "        pd.Podr_Id       Podr_Day_Podr_Id, "
  + "        pd.Date_Start    Podr_Day_Date_Start, "
  + "        pd.Main_Podr_Id  Main_Podr_Id, "
  + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
  + "        pd.Nazv          Podr_Nazv, "
  + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
  + "        pd.Kod           Podr_Kod, "
  + "        pd.Prom_Id       Podr_Prom_Id , "
  + "        pd.Pos           Podr_Pos, "
  + "        pd.Vne_Shtata    Vne_Shtata, "
  + "        ceh.ROWID        Ceh_Row_Id, "
  + "        ceh.Podr_Id      Ceh_Id, "
  + "        ceh.Date_Start   Ceh_Date_Start, "
  + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
  + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
  + "        ceh.Nazv         Ceh_Nazv, "
  + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
  + "        ceh.Kod          Ceh_Kod, "
  + "        ceh.Prom_Id      Ceh_Prom_Id, "
  + "        ceh.Pos          Ceh_Pos "
  + "   FROM kadry.Podr_Day  ceh, "
  + "        kadry.Podr_Day  pd, "
  + "        kadry.Podr      p "
  + "  WHERE pd.Podr_Id(+) = p.Id "
  + "    AND (pd.Podr_Id IS NULL "
  + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day pd1 "
  + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
  + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "        ) "
  + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day ceh1 "
  + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
  + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND p.Id = " + ppodrcod
  + "  ) pd, "
  + " kadry.v_Post       ps, "
  + " kadry.v_Post       ps1, "

  + " /*-- предыдущее назначение*/ "
  + " kadry.Perevod_Vid  pv, "
  + " kadry.Osnova       os, "
  + " kadry.Nazn         n2, "
  + " kadry.Doljnost     d2, "
  + " kadry.Post         ps2, "
  + " kadry.Razr         rz2, "
  + " kadry.Prof         pf2, "
  + " /*-- следующее назначение*/ "
  + " kadry.Perevod_Vid  pv3, "
  + " kadry.Osnova       os3, "
  + " kadry.Nazn         n3, "
  + " kadry.Doljnost     d3, "
  + " kadry.Post         ps3, "
  + " kadry.Razr         rz3, "
  + " kadry.Prof         pf3, "

  + " kadry.FIO          fio, "
  + " kadry.Karta        kr, "
  + " kadry.Nazn_Vid     nv, "
  + " kadry.Pricaz       pr1, "
  + " kadry.Pricaz       pr2, "
  + " kadry.Pricaz       pr3, "
  + " kadry.Nazn_Day     nd, "
  + " kadry.Nazn         n "

  + " WHERE (nd.Nazn_Id = n.Id "

  + " /*-- Выбор параметров на дату среза*/ "
  + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + " AND NOT EXISTS "
  + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
  + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
  + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND nd2.Date_Start > nd.Date_Start) "
  + " AND n.Start_Pricaz_Id  = pr1.Id "
  + " AND n.Finish_Pricaz_Id = pr2.Id "
  + " AND nd.Pricaz_Id       = pr3.Id "
  + " AND n.Doljnost_Id = d.Id "
  + " AND pd.Podr_Id = d.Podr_Id "
  + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
  + " AND ps1.Post_Id = d.Post_Id "
  + " AND k.Id = d.Kategory_Id "
  + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
  + " AND dd.Doljnost_Id(+) = d.Id "
  + " /*-- Выбор параметров должности на дату*/ "
  + " AND (dd.Doljnost_Id IS NULL "
  + "   OR (dd.Date_Start <= n.Date_Finish  "
  + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
  + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
  + "                        AND dd2.Date_Start <= n.Date_Finish  "
  + "                            AND dd2.Date_Start > dd.Date_Start))) "
  + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
  + "    /*-- предыдущее назначение*/ "
  + "      AND pv.Id(+) = n.Perevod_Vid_Id "
  + "      AND os.Id(+) = n.Perevod_Osnova_Id "
  + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
  + "      AND n2.Doljnost_Id = d2.Id(+) "
  + "      AND ps2.Id(+) = d2.Post_Id "
  + "      AND pf2.Id(+) = ps2.Prof_Id "
  + "      AND rz2.Id(+) = ps2.Razr_Id   "
  + "    /*-- сдедующее назначение*/ "
  + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
+ "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
+ " AND os3.Id(+) = n3.Perevod_Osnova_Id "
+ " AND d3.Id(+)  = n3.Doljnost_Id "
+ " AND ps3.Id(+) = d3.Post_Id "
+ " AND pf3.Id(+) = ps3.Prof_Id "
+ " AND rz3.Id(+) = ps3.Razr_Id   "
+ " AND n.Karta_Id     = kr.Id "

// + " AND upper(kr.prim) like ('%НВЗ%') "

+ " AND  ( "
+ "        (    UPPER (kr.prim) LIKE ('%"+type_pers+"%') "
+ "            AND UPPER (kr.prim) LIKE ('%"+regular+"%') "
+ "            ) "
+ "     OR     (    UPPER (kr.prim) LIKE ('"+type_pers+"') " // SUPP-29631 - выбирался лишний персонал
+ "              AND (  "
+ "                   UPPER (kr.prim) NOT LIKE ('%RZA%') "
+ "              and (UPPER (kr.prim) NOT LIKE ('%DTU%'))  "
+ "              and (UPPER (kr.prim) NOT LIKE ('%IZ%')) "
+ "                  ) "
+ "            ) "
+ "        ) "

+ " AND fio.Id         = kr.FIO_Id "
+ " AND nd.Nazn_Vid_Id = nv.Id(+) "
+ "    /*-- график назначения*/ "
  + " ) AND  "
+ "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
+ "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
//    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
+ " ORDER BY kr.TabN, n.Date_Start desc  " ;

if (isDebug){
    System.out.println("sql выборка людей : " + sql);
    System.out.println("paramworkday : " + pdate_srez.toString());
}




    // PreparedStatement statement2 = finConnection.prepareStatement(sql);
    // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
    statement2 = finConnection.prepareStatement(sql);
    resultSet2 = statement2.executeQuery();

  // получили список сотрудников
    int i;

while (resultSet2.next())
{

if (isDebug)
    System.out.println(" Табельный " + resultSet2.getString(2) );






        sumHours = sumHours.add(getCountWorkHoursByWorker(pdate_srez.toString(),resultSet2.getString(2) )); // вызываем процедуру выбора отработаных часов по человеку
        System.out.println(sumHours);


        try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
        try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

        try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
        try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

}


    return  sumHours ;
} catch (Exception e) {
    System.out.println(    e.getMessage()+ "error27 - getcounthoursbypodrbyworker");

    return  sumHours ;
} finally {
    if (isDebug)
        System.out.println("finally getCountHoursByPodrByWorker:" + new Date());

     try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
     try {if (statement != null) statement.close();} catch (SQLException e) {}

     try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
     try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

     try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
     try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

     try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
     try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
                finConnection = null;}
        } catch (SQLException e) {
        }
}

}

public String getTypeNameOperFromFin(Integer findoccode, Integer Index) throws PersistenceException, SQLException
{

    boolean isDebug = false;

    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String out = new String();

    if (isDebug)
        System.out.println("start getTypeNameOperFromFin:" + new Date());


    String sql = " Select distinct(k.text) as text, ' '||t.doc_num "
            + " from umc_dba.thead t, umc_dba.toper_kind k "
            + " where t.id = " + findoccode
            + " and k.id = t.op_kind_id " ;

    if (isDebug)
        System.out.println("sql getTypeNameOperFromFin : " + sql);

    boolean isNewConn = false;

    try {


        if (finConnection == null){

            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();

        if (resultSet.next())
        {
        switch ((Index).intValue())
        {
            case 1 : out = resultSet.getString(1); if (resultSet.wasNull()) out = " "; break;
            case 2 : out = resultSet.getString(2); if (resultSet.wasNull()) out = " "; break;
        }
        }


    }

        catch (SQLException e) {

            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {

            throw new PersistenceException("Error in method getTypeNameOperFromFin()",e);



    }
        //finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed())){
            finConnection.close();
            finConnection = null;
            }

        } catch (SQLException e) {
        }
        return out;

    //}

}


// ------------------------------------------------------------------------------------------------------
public String getFactDataPostavkaByEstimateitem( String pstrcodeestimate ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getFactDataPostavkaByEstimateitem:" + new Date());
}


String sql =
    " select  to_char(dategen,'dd.mm.yyyy') "
    + " from ( "
    + " /*поставка приход с выбором партий */ "
    + " select  "
    + " iord.dategen "
    + " from rqfkorderitem ii  "
    + "      left join rqfkorderitem2enstmttm  "
    + "         on ( rqfkorderitem2enstmttm.fkorderitemrefcode = ii.code "
    + "              and coalesce(rqfkorderitem2enstmttm.countgen,0) <> 0 ) "
    + " , rqfkorder iord left join  findoc2fkorder ffkord on (iord.code = ffkord.fkorderrefcode)  , rqfkorderstatus "

    + " where iord.code = ii.fkorderrefcode "
    + " and iord.statuscode = rqfkorderstatus.code "
    + " and rqfkorderstatus.code <> 1 /*НЕ равно черновым сказано Салыгиным 23,11,2010*/ "
    + " and iord.kindcode = 1 "
    + " and rqfkorderitem2enstmttm.estimateitemcode in ( " + pstrcodeestimate
    + "                                                ) "
    + " ) w "

    + " Union "

    + "   select "
    + "     to_char(rqfkorder.dategen,'dd.mm.yyyy') "
    + "    from rqfkorderitemremainder , rqfkorderitem , rqfkorder , rqfkorderstatus "
    + "    where rqfkorderitemremainder.estimateitemrefcode in "
    + "                                              ( " + pstrcodeestimate
    + "                                              ) "
    + "      and rqfkorderitemremainder.typerefcode = 2 "
    + "      and rqfkorderitemremainder.fkorderitemrefcode = rqfkorderitem.code "
    + "      and rqfkorderitem.fkorderrefcode = rqfkorder.code "
    + "      and rqfkorder.kindcode = 1 "
    + "      and rqfkorder.statuscode <> 1 /*НЕ равно черновым сказано Салыгиным 23,11,2010*/ "
    + "      and rqfkorder.statuscode = rqfkorderstatus.code " ;

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();


while(resultSet.next())
{


if ( out.length() > 0)
{
out = out + " , " + resultSet.getString(1);
}
else
{
out = resultSet.getString(1);
}
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}

public String getFactDataBillByEstimateitem( String pstrcodeestimate ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getFactDataBillByEstimateitem:" + new Date());
}


String sql =
    " select distinct to_char(dategen,'dd.mm.yyyy') "
    + " from ( "
    + " select  "
    + "  b.dategen as dategen "
    + " from rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, "
    + "     rqbill b, rqbillitem bi, rqbillitem2orderitem bo "
    + " where be.estimateitemcode = oe.estimateitemcode "
    + " and be.billitemcode = bo.billitemrefcode "
    + " and bo.orderitemrefcode = oe.orderitemcode "
    + " and bi.code = bo.billitemrefcode "
    + " and b.code = bi.billrefcode "
    + " and be.estimateitemcode in ( " + pstrcodeestimate + ")"
    + " ) w " ;

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();


while(resultSet.next())
{


if ( out.length() > 0)
{
out = out + " , " + resultSet.getString(1);


}
else
{
out = resultSet.getString(1);
}
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}

public String getFactDataPayByEstimateitem( String pstrcodeestimate ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getFactDataPayByEstimateitem:" + new Date());
}


String sql =
    " select distinct to_char(p.dategen,'dd.mm.yyyy') from rqbillitem2enestimattm r2en , rqpaydocitem2billitem rq2bill ,  rqpaydocitem pi , rqpaydoc p "
    + "  where r2en.estimateitemcode in  ( " + pstrcodeestimate + ")"
    + "  and r2en.billitemcode = rq2bill.billitemcode "
    + " and rq2bill.paydocitemcode = pi.code "
    + " and p.code = pi.paydocrefcode " ;

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();


while(resultSet.next())
{


if ( out.length() > 0)
{
out = out + " , " + resultSet.getString(1);


}
else
{
out = resultSet.getString(1);
}
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}

public String getFactPriceBillByEstimateitem( String pstrcodeestimate ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getFactPriceBillByEstimateitem:" + new Date());
}


String sql =

    " select  "
    + "  distinct bi.pricewithoutnds::numeric(15,2) "
    + " from rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, "
    + "     rqbill b, rqbillitem bi, rqbillitem2orderitem bo "
    + " where be.estimateitemcode = oe.estimateitemcode "
    + " and be.billitemcode = bo.billitemrefcode "
    + " and bo.orderitemrefcode = oe.orderitemcode "
    + " and bi.code = bo.billitemrefcode "
    + " and b.code = bi.billrefcode "
    + " and be.estimateitemcode in ( " + pstrcodeestimate + ")" ;

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();


while(resultSet.next())
{


if ( out.length() > 0)
{
out = out + " ; " + resultSet.getString(1);


}
else
{
out = resultSet.getString(1);
}
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}


public BigDecimal getSumPayByEstimateitem( String pstrcodeestimate ) // вытаскивает сумму оплаты по строке эстимейтов

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getSumPayByEstimateitem:" + new Date());
}


String sql =

     // NET-4517 Отчет " Виконання заявок ТМЗ" добавить фильтр по подразделению,
     // делал фильтр для отчета проверил оплаты ... Какае то они корявинькие. Ниже другой запрос сделал
     /*   " select sum(distinct pi.summagen)    from rqbillitem2enestimattm r2en , rqpaydocitem2billitem rq2bill , "
        + " rqpaydocitem pi , rqpaydoc p "
        + " where r2en.estimateitemcode  in ( " + pstrcodeestimate + ") "
    + " and r2en.billitemcode = rq2bill.billitemcode "
    + " and rq2bill.paydocitemcode = pi.code "
    + " and p.code = pi.paydocrefcode " */

" select sum(coalesce(summagen,0))::numeric(15,2) as summagen from \n" +
" ( \n" +
" select \n" +
"    ( be.countgen *  (bii.pricewithoutnds*pdi.summagen/bii.sumwithoutnds  )) as summagen , \n" +
"    pd.dategen \n" +
" from rqbillitem2enestimattm be , rqbillitem bii , rqpaydocitem2billitem p2b , rqbill r, rqpaydocitem pdi, rqpaydoc pd \n" +
"  where  be.estimateitemcode in (" + pstrcodeestimate + ") \n" +
"    and be.billitemcode = bii.code \n" +
"    and p2b.billitemcode = bii.code \n" +
"    and r.code = bii.billrefcode \n" +
"    and pdi.code = p2b.paydocitemcode \n" +
"    and pdi.paydocrefcode = pd.code \n" +
" ) selpay \n" ;



if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

BigDecimal out = new BigDecimal(0);
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();


while(resultSet.next())
{
out =  resultSet.getBigDecimal(1);
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}
// ------------------------------------------------------------------------------------------------------



public String getDatePostavkaOplata(  Integer idagree , String pstrcodeestimate) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("running getDatePostavkaOplata");

    String out = new String("");
    Date plandate = null;

    String sqlBillOrPrih = "";


    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    PreparedStatement statementFormPay = null;
    ResultSet  resultSetFormPay = null;


    PreparedStatement statementBillOrPrih = null;
    ResultSet  resultSetBillOrPrih = null;



    PreparedStatement statement = null;
    ResultSet  resultSet = null;




    try {
//         FIN выборка по коду договора в заявке его формы оплаты из справочников договоров
    String sqlFormPay = " select id_payform from sprav.agree a where a.id = " + idagree ;

    if (isDebug){
        System.out.println("sqlFormPay : " + sqlFormPay);
        System.out.println("param idagree : " + idagree );
    }

    if (finConnection == null){
        //System.out.println("connection=null");
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

    statementFormPay = finConnection.prepareStatement(sqlFormPay);
    resultSetFormPay = statementFormPay.executeQuery();

    int FormPay =0;
    while(resultSetFormPay.next())
    {
        FormPay = resultSetFormPay.getInt(1);
    }

    if (isDebug){
        System.out.println("выбрали форму оплаты айди = "  + FormPay);
                }
// если форма оплаты по предоплате то выбираем по строке итемсов все даты счетов

    if (FormPay == 25)
                    {
        System.out.println("форма оплаты предоплата выбраем даты счетов  = " );
                    sqlBillOrPrih = " select distinct to_char(dategen ,'dd.mm.yyyy') "
                        + " from ( "
                        + " select  "
                        + "  b.dategen as dategen "
                        + " from rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, "
                        + "     rqbill b, rqbillitem bi, rqbillitem2orderitem bo "
                        + " where be.estimateitemcode = oe.estimateitemcode "
                        + " and be.billitemcode = bo.billitemrefcode "
                        + " and bo.orderitemrefcode = oe.orderitemcode "
                        + " and bi.code = bo.billitemrefcode "
                        + " and b.code = bi.billrefcode "
                        + " and be.estimateitemcode in ( " + pstrcodeestimate + ")"
                        + " ) w ";
                       }
// если форма оплаты по факту то выбираем по строке итемсов все даты приходов

                    if (FormPay == 26)
                                        {
                        System.out.println("форма оплаты по факту  выбраем даты приходов   = " );
                                       sqlBillOrPrih = " select  to_char(dategen,'dd.mm.yyyy') "
    + " from ( "
    + " /*поставка приход с выбором партий */ "
    + " select  "
    + " iord.dategen "
    + " from rqfkorderitem ii  "
    + "      left join rqfkorderitem2enstmttm  "
    + "         on ( rqfkorderitem2enstmttm.fkorderitemrefcode = ii.code "
    + "              and coalesce(rqfkorderitem2enstmttm.countgen,0) <> 0 ) "
    + " , rqfkorder iord left join  findoc2fkorder ffkord on (iord.code = ffkord.fkorderrefcode)  , rqfkorderstatus "

    + " where iord.code = ii.fkorderrefcode "
    + " and iord.statuscode = rqfkorderstatus.code "
    + " and rqfkorderstatus.code <> 1 /*НЕ равно черновым сказано Салыгиным 23,11,2010*/ "
    + " and iord.kindcode = 1 "
    + " and rqfkorderitem2enstmttm.estimateitemcode in ( " + pstrcodeestimate
    + "                                                ) "
    + " ) w "

    + " Union "

    + "   select "
    + "     to_char(rqfkorder.dategen,'dd.mm.yyyy') "
    + "    from rqfkorderitemremainder , rqfkorderitem , rqfkorder , rqfkorderstatus "
    + "    where rqfkorderitemremainder.estimateitemrefcode in "
    + "                                              ( " + pstrcodeestimate
    + "                                              ) "
    + "      and rqfkorderitemremainder.typerefcode = 2 "
    + "      and rqfkorderitemremainder.fkorderitemrefcode = rqfkorderitem.code "
    + "      and rqfkorderitem.fkorderrefcode = rqfkorder.code "
    + "      and rqfkorder.kindcode = 1 "
    + "      and rqfkorder.statuscode <> 1 /*НЕ равно черновым сказано Салыгиным 23,11,2010*/ "
    + "      and rqfkorder.statuscode = rqfkorderstatus.code " ;
                                          }


       if (FormPay != 25 && FormPay != 26)
       {
        out = "notfound";
        return out;
       }

    // дергаем скрипт выборки

       System.out.println(" дергаем скрипт для выборки дат счетов или приходов   = " );
       statementBillOrPrih = connection.prepareStatement(sqlBillOrPrih);
       resultSetBillOrPrih = statementBillOrPrih.executeQuery();

    // цикл по выбраным датам счетов или поставок

       while(resultSetBillOrPrih.next())
       {



//         выбираем по айди договора его реквизиты ( кол во дней оплаты )
    String sql = "  Select    id  /*айди договора*/ "
                 + " ,  id_payform   /* (ид по спр-ку PayForm Формы оплаты) (25 - ПЕРЕДПЛАТА , 26 - ЗА ФАКТОМ ПОСТАЧАННЯ) */ "
                 + " ,  PAY_PERIOD   /* Период оплаты(в днях) */ "
                 + " ,  a.pay_type "
                 + " ,  to_date('"+ resultSetBillOrPrih.getString(1) + " ','dd.mm.yyyy') DateAccount "
                 + "                                    /*дата счета*/ "
                 + " , to_char( case when ( a.pay_type = 1 and a.pay_period <> 0 ) /*если срок оплаты  по календарным дням то дату счета  + период */ "
                 + " then to_date('" + resultSetBillOrPrih.getString(1) + " ','dd.mm.yyyy') +  a.pay_period "
                 + "         when ( a.pay_type = 2 and a.pay_period <> 0 ) /*если срок оплаты по банковским дням то дату счета + перид перенося праздники и выходные на сл рабочие дни  */ "
                 + " then  to_date(( select energynet.add_working_days(a.pay_period /*срок оплаты */ "
                 + "                                                /*+ кол-во прадничных дней в периоде*/ "
                 + "                                                +  (  "
                 + "                                                     select count(*) from zarpherson.calendar c "
                 + "                                                      where c.day between to_date('" + resultSetBillOrPrih.getString(1) + " ','dd.mm.yyyy') "
                 + "                                                        and to_date(( select energynet.add_working_days(a.pay_period   , to_date('" + resultSetBillOrPrih.getString(1) + " ','dd.mm.yyyy'))  from dual ),'dd.mm.yyyy') "
                 + "                                                    ) "
                 + "                                                 , to_date('" + resultSetBillOrPrih.getString(1) + "','dd.mm.yyyy'))  from dual ),'dd.mm.yyyy') "
                 + "         when a.pay_period = 0 /*если срок оплаты = 0 то возвратим  дату параметра  */ "
                 + " then  to_date('"+ resultSetBillOrPrih.getString(1) + " ','dd.mm.yyyy') "
                 + "  end  , 'dd.mm.yyyy') dateplan              /*дата планирумой оплаты с учетом дней указаных в договоре */ "
                 + " from sprav.agree a  "
                 + " where a.PAY_TYPE in (1,2)  /*Способ расчета срока оплаты(Календарный день , Банковский День)*/ "
                 + " /* and a.id in (  3569 , 1937 , 4846 ) */  "
                 + " and a.id = " + idagree  ;

    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param idagree : " + idagree );
    }

    if (finConnection == null){
        //System.out.println("connection=null");
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

    statement = finConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();

    while(resultSet.next())
    {
    //    plandate = resultSet.getString(6);



    if ( out.length() > 0)
    {
    out = out + " ; " + resultSet.getString(6);
    }
    else
    {
    out = resultSet.getString(6);
    }

    }


    } // канец от цикла

       return out;
}  // try
    catch (Exception e) {
        System.out.println("Error :" + e.getMessage());
        throw new SystemException(e);
    }
    finally {
        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {if (resultSetFormPay != null) resultSetFormPay.close();} catch (SQLException e) {}
        try {if (statementFormPay != null) statementFormPay.close();} catch (SQLException e) {}

        try {if (resultSetBillOrPrih != null) resultSetBillOrPrih.close();} catch (SQLException e) {}
        try {if (statementBillOrPrih != null) statementBillOrPrih.close();} catch (SQLException e) {}
    }


}

public String getFormPayOr_SposobAndCountDay(Integer idagree , int priznResult) throws PersistenceException
{

    boolean isDebug = false;
    if (isDebug)
    System.out.println(" start getSposobAndCountDay:" + new Date());

    String sql = " Select pf.name || ' | ' || pt.name  || ' : дней ' || a.pay_period  as tt , id_payform  From sprav.agree a , sprav.pay_types pt  , sprav.payform pf "
                 + " where a.id = " + idagree
                 + " and a.pay_type = pt.pay_type "
                 + " and a.id_payform = pf.code(+) " ;
                // + " and a.id_payform in (25, 26) ";

    if (isDebug){
    System.out.println("sql : " + sql);

    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    String out = new String("");
    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if (resultSet.next())
        out = resultSet.getString(priznResult);




        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " error 55 - getSposobAndCountDay");
        return out;
    } finally {
        if (isDebug)
        System.out.println("finally getSposobAndCountDay:" + new Date());


        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }
    }

}

public String getDate(Date date)
{
    SimpleDateFormat dformat = new SimpleDateFormat("dd.MM.yyyy");
    return dformat.format(date);
}



public String returnDatebyTypeDay(String pdate , int prizntypeday  ) throws PersistenceException
{

    boolean isDebug = false;
    if (isDebug)
    System.out.println(" start returnDatebyTypeDay:" + new Date());

    String sql = " select retDate returndate from ( "
                + " select energynet.add_working_days(5 /*срок оплаты */ "
                + "                                                 /*+ кол-во прадничных дней в периоде*/ "
                + " +  (  "
                + " select count(*) from zarpherson.calendar c "
                + " where c.day between to_date('" + pdate + "','dd.mm.yyyy')  "
                + " and to_date(( select energynet.add_working_days(5  , to_date('"+pdate + "','dd.mm.yyyy'))  from dual ),'dd.mm.yyyy') "
                + "  ) "
                + " , to_date('"+ pdate + "','dd.mm.yyyy')) retDate  from dual "
                + " where "+prizntypeday+" = 1 /*возращаем дату по банковским дням*/ "
                + " union  "
                + " select to_date('" + pdate+ "','dd.mm.yyyy') + 5 retDate from dual"
                + " where "+prizntypeday+" = 2 /*возращаем дату по календарным дням*/ "
                + " ) " ;

    if (isDebug){
    System.out.println("sql : " + sql);

    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    String out = "";
    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if (resultSet.next())
        out = getDate(resultSet.getDate(1));


        if (isDebug)
            System.out.println(" out :" + out);

        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " error 77 - returnDatebyTypeDay");
        return out;
    } finally {
        if (isDebug)
        System.out.println("finally returnDatebyTypeDay:" + new Date());


        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }
    }

}


public String returnAccountForCounters(String accountin  , String explorunexpl  ) throws PersistenceException
{

    boolean isDebug = false;
    if (isDebug)
    System.out.println(" start returnAccountForCounters:" + new Date());
    String sql = "";
    // если ввод в эксплуатацию
    if (explorunexpl == "E" ) {
    sql = " SELECT kod_subsch, name_subsch " +
                 " FROM countersread.v_s_subsch_expl " +
                 " WHERE kod_subsch_before = " + accountin  ; }
    // если вывод в эксплуатации
    if (explorunexpl == "U" ) {
        sql = " SELECT kod_subsch, name_subsch " +
                    " FROM countersread.v_s_subsch_unexpl " +
                    " WHERE kod_subsch_before = " + accountin  ; }

    if (isDebug){
    System.out.println("sql : " + sql);

    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    String out = "";
    try {

        if (finConnection == null || finConnection.isClosed()) {
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if (resultSet.next())
        out = resultSet.getString(1);


        if (isDebug)
            System.out.println(" out :" + out);

        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " error 78 - returnAccountForCounters");
        return out;
    } finally {
        if (isDebug)
        System.out.println("finally returnAccountForCounters:" + new Date());


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }
    }

}


public String setMaterialsExistList(String balansNumberCondition, String molCode, Date date, int finDocCode) throws PersistenceException
{
      String result="";
    existProduct = new Vector();

    FINMaterialsFilter aFilterObject = new FINMaterialsFilter();

    aFilterObject.conditionSQL="isCN is null and  ( substr(dat.bal_sch,1,2) in ('20', '28') ) ";

        boolean isNewConn = false;


        try {

            if (finConnection == null){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }


            if ( finConnection.isClosed() ){
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                isNewConn = true;
            }

            JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
            UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

            String materialCondition="";

            FinMaterialsLogic finMaterialsLogic = new FinMaterialsLogic(finConnection, userProfile);
            FINMaterialsList finList = finMaterialsLogic.getMaterialsListND(aFilterObject, balansNumberCondition,  molCode,  materialCondition,  date, finDocCode);

            existProductObj eo;

            if (finList.list.size()>0)
            {
            for (int i = 0; i < finList.totalCount; i++) {
                if (finList.get(i).quantity!=null)
                {
                if (finList.get(i).quantity.doubleValue()>0)
                {
                    eo = new  existProductObj();
                    eo.nn=finList.get(i).nn;
                    eo.quantity=finList.get(i).quantity;
                    eo.doc_date=finList.get(i).doc_date;
                    existProduct.add(eo);
                }
                }
            }
            }

        }

            catch (SQLException e) {
                System.out.println(    e.getMessage());
                throw new PersistenceException(e.getMessage());
        }
            catch (Exception e) {
                System.out.println(e.getMessage());
                throw new PersistenceException("Error in method getQuantityMaterialsList",e);
        }
            finally {

            try {
                if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                    finConnection.close();

            } catch (SQLException e) {
            }
        }
   return result;

}


public Boolean isMaterialExists(String nn,Date date) throws PersistenceException
{

    Boolean result=new Boolean(false);
    String exists_nn;
    Date doc_date;

    if (existProduct.size()>0)
    {
    for ( int j = 0; j < existProduct.size(); j++ ){

        exists_nn=((existProductObj)existProduct.get(j)).nn;
        if (exists_nn.equals(nn))
        {

            Calendar calendar= Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            date = calendar.getTime();

            doc_date=((existProductObj)existProduct.get(j)).doc_date;
            calendar.clear();
            calendar.setTime(doc_date);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            doc_date = calendar.getTime();

        if (date.equals(doc_date))
        {
            result=new Boolean(true);
            break;
        }
        }
    }
    }

    return result;
}


public BigDecimal getMaterialQuantity(String nn,Date date) throws PersistenceException
{

    BigDecimal result=new BigDecimal(0);

    String exists_nn;
    Date doc_date;


    if (existProduct.size()>0)
    {
    for ( int j = 0; j < existProduct.size(); j++ ){

        exists_nn=((existProductObj)existProduct.get(j)).nn;
        if (exists_nn.equals(nn))
        {

            Calendar calendar= Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            date = calendar.getTime();

            doc_date=((existProductObj)existProduct.get(j)).doc_date;
            calendar.clear();
            calendar.setTime(doc_date);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            doc_date = calendar.getTime();

        if (date.equals(doc_date))
        {
           result=((existProductObj)existProduct.get(j)).quantity;
            break;
        }


        }
    }
    }

    return result;
}





public BigDecimal CheckPersonalInAct( String tabn , String datestart , String datefinal ) // проверка был ли работник засвечен в актах в выбранном периоде

throws PersistenceException
{
boolean isDebug = false ;

//JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
// Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
//UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
//connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
//finConnection = (Connection) ((JRFillParameter) this.parametersMap.get("finConnection")).getValue();
netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
//UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start CheckPersonalInAct:" + new Date());
}


/*String sql =

        " Select coalesce(count(a2h.code),0)  as cccc from enact2humen a2h , enact a " +
        "                       left join scusageinputitemoz2nct scu2act on ( scu2act.enactrefcode = a.code ) " +
        "                       left join scusageinputitemoz scoz on ( scoz.code = scu2act.usageinputitemozrefcod ) " +
        "      , enact2enplanwork a2p   , enplanwork enp " +
 " where a2h.actrefcode = a.code " +
 "  and a.statusrefcode <> 2 " +
 "  and a.dategen between to_date('" + datestart + "','dd.mm.yyyy')  " + " and  to_date('" + datefinal + "','dd.mm.yyyy')  " +
 "  and a.code = a2p.actrefcode  " +
 "  and a2p.plancode = enp.code " +
 "  and enp.kindcode = 4  " +
 "  and a2h.tabnumber =  " + "'"+tabn+"'" ;
 */

BigDecimal out = new BigDecimal(0);

if (tabn == null){
	return out;
}

String sql = " Select coalesce(sum(p2h.code),0)  as cccc from enplan2humen p2h , enact a " +
" left join scusageinputitemoz2nct scu2act on ( scu2act.enactrefcode = a.code ) " +
" left join scusageinputitemoz scoz on ( scoz.code = scu2act.usageinputitemozrefcod ) " +
" , enact2enplanwork a2p   , enplanwork enp                " +
" where a.statusrefcode <> 2 /*не равно отмененным*/ " +
"  and a.dategen between to_date('" + datestart + "','dd.mm.yyyy')  " + " and  to_date('" + datefinal + "','dd.mm.yyyy')  " +
" and a.code = a2p.actrefcode " +
" and a2p.plancode = enp.code " +
" and enp.kindcode = 4  " +
" and p2h.tabnumber::Integer = " + Integer.parseInt(tabn) +
" and p2h.planrefcode = enp.code " ;

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;



try {



statement = netConnection.prepareStatement(sql);


resultSet = statement.executeQuery();


while(resultSet.next())
{
out =  resultSet.getBigDecimal(1);
}




return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {




try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (netConnection != null && netConnection.isClosed())
    netConnection.close();
} catch (SQLException e) {
}
}
}
// ------------------------------------------------------------------------------------------------------


public String getKodZatrat(String inv_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getKodZatrat:" + new Date());

    if (inv_num.length() == 5) {
        inv_num = "0" + inv_num;
    }


String sql = " Select ooo.kod_zatr from os_t.ostable ooo "  +
             " Where ooo.kod_inv = '" + inv_num  + "'" +
             " And ooo.show_ = 'Y' ";
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + inv_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");
    try {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //System.out.println("try");
        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

    //    statement.setString(1, inv_num);



        resultSet = statement.executeQuery();
        //System.out.println("execSQL");
    //    if (resultSet.next()){
    //     out = resultSet.getString(1);
    //     System.out.println("Deprication :" + out );
    //    }
    //    else{
    //        System.out.println("NOT resultSet.next() for getKodZatrat " );
    //        throw new SystemException("Inv not found :"+inv_num);
    //    }

        while(resultSet.next())
        {
        out =  resultSet.getString(1);
        }

    //    out = new BigDecimal(1);

        if (isDebug)
            System.out.println("return :" + out);

        if(out.equals(""))
            out="          ";

        return out;
    }

        catch (SQLException e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " erorrr 101 - getKodZatrat");
            //finConnection.
            //return out;
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " error 101 - getKodZatrat");
            //throw new Exception(e.getMessage());
            throw new PersistenceException("Error in method getKodZatrat()",e);
            //finConnection.
            //return out;


    }
        finally {

            //System.out.println("finally getDepreciation:" + new Date());

        //System.out.pr
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if (/*(isNewConn)&&*/(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
            //System.out.println("Close connn in getDeeprication");
        } catch (SQLException e) {
        }
    }

}


public String getSignNalog(String inv_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getSignNalog:" + new Date());

    if (inv_num.length() == 5) {
        inv_num = "0" + inv_num;
    }


String sql = " Select CASE WHEN ooo.f_amort = 'Y' then 'налог.' else 'неналог.' end as signnalog  from os_t.ostable ooo "  +
             " Where ooo.kod_inv = '" + inv_num  + "'" +
             " And ooo.show_ = 'Y' ";
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + inv_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");
    try {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //System.out.println("try");
        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

    //    statement.setString(1, inv_num);



        resultSet = statement.executeQuery();
        //System.out.println("execSQL");
    //    if (resultSet.next()){
    //     out = resultSet.getString(1);
    //     System.out.println("Deprication :" + out );
    //    }
    //    else{
    //        System.out.println("NOT resultSet.next() for getKodZatrat " );
    //        throw new SystemException("Inv not found :"+inv_num);
    //    }

        while(resultSet.next())
        {
        // if     (resultSet.getString(1) == "Y")
        //     {
        //        out = "налог."; }
        // else { out = "неналог.";}
        out =  resultSet.getString(1);
        }

    //    out = new BigDecimal(1);

        if (isDebug)
            System.out.println("return :" + out);

        return out;
    }

        catch (SQLException e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " erorrr 102 - getSignNalog");
            //finConnection.
            //return out;
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " error 103 - getSignNalog");
            //throw new Exception(e.getMessage());
            throw new PersistenceException("Error in method getSignNalog()",e);
            //finConnection.
            //return out;


    }
        finally {

            //System.out.println("finally getDepreciation:" + new Date());

        //System.out.pr
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
            //System.out.println("Close connn in getDeeprication");
        } catch (SQLException e) {
        }
    }

}

/**
 *
 * Возвращает код источника прихода по инвентарному номеру ОС
 *
 * @param inv_num инвентарный номер ОС
 * @return код источника прихода
 * @throws PersistenceException
 */
public String getKodIst(String inv_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getKodIst:" + new Date());

    if (inv_num.length() == 5) {
        inv_num = "0" + inv_num;
    }


String sql = " select ooo.kod_ist from os_t.ostable ooo "  +
             " where ooo.kod_inv = '" + inv_num  + "'" +
             " and ooo.show_ = 'Y' ";
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + inv_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");
    try {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //System.out.println("try");
        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

    //    statement.setString(1, inv_num);



        resultSet = statement.executeQuery();
        //System.out.println("execSQL");
    //    if (resultSet.next()){
    //     out = resultSet.getString(1);
    //     System.out.println("Deprication :" + out );
    //    }
    //    else{
    //        System.out.println("NOT resultSet.next() for getKodZatrat " );
    //        throw new SystemException("Inv not found :"+inv_num);
    //    }

        while(resultSet.next())
        {
        // if     (resultSet.getString(1) == "Y")
        //     {
        //        out = "налог."; }
        // else { out = "неналог.";}
        out =  resultSet.getString(1);
        }

    //    out = new BigDecimal(1);

        if (isDebug)
            System.out.println("return :" + out);

        return out;
    }

        catch (SQLException e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " erorrr 102 - getKodIst");
            //finConnection.
            //return out;
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " error 103 - getKodIst");
            //throw new Exception(e.getMessage());
            throw new PersistenceException("Error in method getKodIst()",e);
            //finConnection.
            //return out;


    }
        finally {

            //System.out.println("finally getDepreciation:" + new Date());

        //System.out.pr
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
            //System.out.println("Close connn in getDeeprication");
        } catch (SQLException e) {
        }
    }

}

public String getFirstActiveNN(Integer id) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getFirstActiveNN:" + new Date());

/*String sql ="select " +
                " nn " +
            " from " +
            " (select " +
                " nn " +
            " from " +
                " umc_dba.tmatherial " +
            " where " +
                " ID in (select " +
                            " id " +
                        " from " +
                            " umc_dba.tmatherial " +
                        " where " +
                            " UMC_DBA.TMATHERIAL.status = 'A' " +
                        " and UMC_DBA.TMATHERIAL.bal_sch = '" + bal_sch + "'" +
                        " and upper(UMC_DBA.TMATHERIAL.name) = upper('" + mat_name +"')" +
                        " and UMC_DBA.TMATHERIAL.mesure_unit_id in " +
                        " (select " +
                                " umc_dba.tmesure_unit.id " +
                        " from " +
                            " umc_dba.tmesure_unit " +
                        " where " +
                            " umc_dba.tmesure_unit.status = 'A' " +
                        " and upper(umc_dba.tmesure_unit.text) = '" + mu_name +"'))" +
                " order by id) " +
            " where " +
            " rownum = 1";*/
        String sql = "select " +
                    " nn " +
                    " from " +
                    " umc_dba.tmatherial " +
                    " where id = " + id;
    if (isDebug){
        System.out.println("sql : " + sql);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");
    try {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //System.out.println("try");
        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

    //    statement.setString(1, inv_num);



        resultSet = statement.executeQuery();
        //System.out.println("execSQL");
    //    if (resultSet.next()){
    //     out = resultSet.getString(1);
    //     System.out.println("Deprication :" + out );
    //    }
    //    else{
    //        System.out.println("NOT resultSet.next() for getKodZatrat " );
    //        throw new SystemException("Inv not found :"+inv_num);
    //    }

        while(resultSet.next())
        {
        out = out + resultSet.getString(1);
        }

        /*Если номенклатура не найдена*/
        if(out.length() == 0)
            out = "Невідомо";
    //    out = new BigDecimal(1);

        if (isDebug)
            System.out.println("return :" + out+ " " + out.length());

        return out;
    }

        catch (SQLException e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " erorrr 102 - getSignNalog");
            //finConnection.
            //return out;
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " error 103 - getSignNalog");
            //throw new Exception(e.getMessage());
            throw new PersistenceException("Error in method getSignNalog()",e);
            //finConnection.
            //return out;


    }
        finally {

            //System.out.println("finally getDepreciation:" + new Date());

        //System.out.pr
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
            //System.out.println("Close connn in getDeeprication");
        } catch (SQLException e) {
        }
    }

}

public String getBalansKod(String tabnumber, Date date) throws PersistenceException
{


    boolean isDebug = false;
    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
    mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );

    boolean usesMDAXDataReport = false;
	AuthLogic netAuth = new AuthLogic(connection, userProfile);
	usesMDAXDataReport = netAuth.checkUsesMDAXDataForReport();

    if (isDebug)
        System.out.println("start getBalansKod:" + new Date());


String sql = " select b.balans , p.podrcod from zarpherson.kadry_day_view v, zarpherson.balans b, zarpherson.podr p"
        +" where v.balans_id = b.id "
        +" and v.podrid = p.podrid "
        +" and v.tabn =" + tabnumber;



    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + tabnumber);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");

    try {
       if (usesMDAXDataReport == false){
    	if (finConnection == null || finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }
        statement = finConnection.prepareStatement(sql);
        resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            // код ШПЗ + код подразделения
            out = resultSet.getString(1) + "; " + resultSet.getString(2);
        }
       } else {
    	   FINWorkerShortList finworkerList = new FINWorkerShortList();
    	   FINWorkerFilter finworkerFilter = new FINWorkerFilter();
    	   System.out.print(" get balans for tabn " + tabnumber);
    	   finworkerFilter.tabNumber = tabnumber;

    	   Date curDate = new Date();

    	   finworkerList = mdLogic.getFINWorkerList(finworkerFilter, 0,-1, date, true);
    	   if (finworkerList.totalCount > 0 ){
    		   if (!finworkerList.get(0).balans.equals("")){
    			  out = finworkerList.get(0).balans;
    		   }
    		   if (!finworkerList.get(0).departmentCode.equals("")){
     			  out = out + "; " + finworkerList.get(0).departmentCode.substring(0, 3);
     		   }
    	   } else
    	   {
    		   out = "000000000000000";
    		   //throw new EnergyproSystemException(String.format("Не знайдено особову картку співробітника  " + tabnumber ));
    	   }
       }


        return out;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ " erorrr 102 - getBalansKod");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ " error 103 - getBalansKod");
            throw new PersistenceException("Error in method getBalansKod()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}


public String getCodeFormNalogByContragentCodeString(String contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeFormNalogByContragentCodeString:" + new Date());


    String sql = "select nf.code_form from sprav.org a, sprav.nalogform nf " +
    " where a.code =  '" + contragentCode + "'" +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCodeString");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCodeString");
            throw new PersistenceException("Error in method getCodeFormNalogByContragentCodeString()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getCodeOKPOByContragentCodeString(String contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeOKPOByContragentCodeString:" + new Date());


    String sql = "select ' '||a.okpo from sprav.org a, sprav.nalogform nf " +
    " where a.code =  '" + contragentCode + "'" +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCodeString");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCodeString");
            throw new PersistenceException("Error in method getCodeOKPOByContragentCodeString()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getCodeOKPOByContragentCodeStringPDF(String contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeOKPOByContragentCodeString:" + new Date());


    String sql = "select a.okpo from sprav.org a, sprav.nalogform nf " +
    " where a.code =  '" + contragentCode + "'" +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCodeString");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCodeString");
            throw new PersistenceException("Error in method getCodeOKPOByContragentCodeString()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}
public String getNameContragentByContragentCodeString(String contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getNameContragentByContragentCodeString:" + new Date());


    String sql = "select a.name from sprav.org a, sprav.nalogform nf " +
    " where a.code =  '" + contragentCode + "'" +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCodeString");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCodeString");
            throw new PersistenceException("Error in method getNameContragentByContragentCodeString()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getCodeFormNalogByContragentCode(Integer contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeFormNalogByContragentCode:" + new Date());


    String sql = "select  nf.code_form from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCode");
            throw new PersistenceException("Error in method getCodeFormNalogByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getCodeOKPOByContragentCode(Integer contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeOKPOByContragentCode:" + new Date());


    String sql = "select  ' '||a.okpo from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCode");
            throw new PersistenceException("Error in method getCodeOKPOByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getNameContragentByContragentCode(Integer contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getNameContragentByContragentCode:" + new Date());


    String sql = "select  a.name from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;



    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCode");
            throw new PersistenceException("Error in method getNameContragentByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}


public String getCodeFormNalogByContragentCode(int contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeFormNalogByContragentCode:" + new Date());


    String sql = "select  nf.code_form from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeFormNalogByContragentCode");
            throw new PersistenceException("Error in method getCodeFormNalogByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getCodeOKPOByContragentCode(int contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCodeOKPOByContragentCode:" + new Date());


    String sql = "select  a.okpo from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getCodeOKPOByContragentCode");
            throw new PersistenceException("Error in method getCodeOKPOByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

public String getNameContragentByContragentCode(int contragentCode ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getNameContragentByContragentCode:" + new Date());


    String sql = "select  a.name from  sprav.org a , sprav.nalogform nf " +
    " where a.id =  " + contragentCode +
    " and a.id_nalogform = nf.id";

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;



    String outCode = "";

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if ( resultSet.next() )
         {
        outCode = resultSet.getString(1);
         }

        return outCode;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCode");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ "getNameContragentByContragentCode");
            throw new PersistenceException("Error in method getNameContragentByContragentCode()",e);

    }
        finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

/* Срез остатков */
public BigDecimal cutRemainsMaterials(String date) throws PersistenceException
{

    boolean isDebug = true;
    if (isDebug)
    System.out.println("start cutRemainsMaterials:" + new Date());

    String sql = "begin umc_dba.pkg_rest.init_dates_for_calc_rest(to_date(?,'dd.mm.yyyy'), 1); end;";


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    BigDecimal out = new BigDecimal(10);

    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        statement.setString(1, date);

        resultSet = statement.executeQuery();
        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " error  - cutRemainsMaterials");

    } finally {
        if (isDebug)
        System.out.println("finally cutRemainsMaterials:" + new Date());


        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }
    }
    return out;

}

public String getDepartmentCodesDown( int depCode) throws PersistenceException {
	return this.getDepartmentCodesDown( depCode, null);
}
public String getDepartmentCodesDown( int depCode, String codesExclude) throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getDepartmentCodesDown:" + new Date());
}

String out = "";
try
{
out = new ManningTableLogic(connection, userProfile).getDepartmentCodesDown(depCode, "", codesExclude);

// System.out.println(out);

return out;
} catch (Exception e) {
System.out.println(    e.getMessage());
return out;
} finally {

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}


/**
 *
 * Возвращает строку вида "Договір №<№ договора> від <дата договора>"
 *
 * @param int finDocID - код договора из ФК
 */
public String getFinContractDescription(int finDocID) throws PersistenceException
{
    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getFinContractDescription: " + new Date());


    String sql = "SELECT 'Договір №' || a.in_num || ' від ' || to_char(a.in_date, 'dd.MM.yyyy')" +
                "  FROM sprav.agree a " +
                " WHERE a.id = " + finDocID;

    if (isDebug){
        System.out.println("getFinContractDescription_sql : " + sql);
        System.out.println("getFinContractDescription_param (finDocID) : " + finDocID);
    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");

    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            out = resultSet.getString(1);
        }

        if (isDebug)
            System.out.println("return : " + out);

        return out;
    }

        catch (SQLException e) {
            // System.out.println(e.getMessage()+ "getFinContractDescription - SQLException");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //System.out.println(e.getMessage()+ "getFinContractDescription - Exception");
            throw new PersistenceException("Error in method getFinContractDescription()", e);

    }
        finally {


            if (isDebug)
                System.out.println("final getFinContractDescription: " + new Date());

        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}


/**
 *
 * Возвращает строку вида "договір № <№ договора 1> від <дата договора 1>,... № <№ договора n> від <дата договора n>"
 *
 * @param int <I>orderCode</I> - код ордера на внутреннее перемещение по договору подряда ("видаткового ордера для ДП")
 */
public String getFinContractsDescriptionByOrder(int orderCode) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start getFinContractsDescriptionByOrder: " + new Date());

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement finStatement = null;
    ResultSet  finResultSet = null;
    boolean isNewConn = false;

    String out = "";

    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


    //String sql = "select distinct p.servicesfsidefinid, case when el.etype <> 48 then 'Інвентарний № '||el.invnumber else '' end as invnumber " +
        String sql = "select distinct p.servicesfsidefinid, case when el.etype <> 48 then 'Інв. № '||el.invnumber||'  '||el.buhname else '' end as invnumber " +
                "from rqfkorder o, rqfkorderitem oi, rqfkorderitem2enstmttm ie, rqfkorder2fkorder oo, " +
                "       enestimateitem ei, enplanwork p, enelementdata as el " +
                "where oi.fkorderrefcode = oo.fkorderoutrefcode " +
                "  and oo.fkorderinrefcode = o.code " +
                "  and ie.fkorderitemrefcode = oi.code " +
                "  and ie.estimateitemcode = ei.code " +
                "  and ei.planrefcode = p.code " +
                "  and el.ecode = p.elementrefcode " +
                "  and o.code = " + orderCode +
                " and p.servicesfsidefinid is not null ";

    //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
    connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();


    statement = connection.prepareStatement(sql);
    resultSet = statement.executeQuery();


    int finDocID;

    while (resultSet.next()){
        finDocID = resultSet.getInt(1);

        String finOut = "";
        String finSql = "SELECT '№ ' || a.in_num || ' від ' || to_char(a.in_date, 'dd.MM.yyyy')" +
                        "  FROM sprav.agree a " +
                        " WHERE a.id = " + finDocID;

        finStatement = finConnection.prepareStatement(finSql);
        finResultSet = finStatement.executeQuery();
        if (finResultSet.next()) {
            finOut = finResultSet.getString(1);
        }

        String invNumber_str = resultSet.getString(2);

        if (! finOut.equals("")){

        	// SUPP-20574 Дописывание инвентарного номера в конец
        	finOut = finOut + " " + invNumber_str;

            if (out.equals("")) {
                out = finOut;
            }
            else {
                out = out + ", " + finOut;
            }
        }
    }

    if (! out.equals(""))
    {
        out = "договір " + out;
    }

        return  out ;
    } catch (Exception e) {
        System.out.println("Error in getFinContractsDescriptionByOrder: " +    e.getMessage());
        return out ;
    } finally {

        if (isDebug)
            System.out.println("final getFinContractsDescriptionByOrder: " + new Date());

        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}


        try {if (finResultSet != null) finResultSet.close();} catch (SQLException e) {}
        try {if (finStatement != null) finStatement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {}
    }

}

/* выбираем остатки из ТМЦ и записываем в темп таблицу на постресе */

public String selectRestFromUMCDBA(String date , String divcode , String nn , String storagename , String storagezonename ) throws PersistenceException
{
    boolean isDebug = true;
    if (isDebug)
        System.out.println("start selectRestFromUMCDBA: " + new Date());

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement netStatement = null;
    ResultSet  netResultSet = null;
    boolean isNewConn = false;

    String out = "";

    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


    String sql = "select " +
            " rest_purpose_type_id , " +
            " party_id , " +
            " party_date, " +
            " rest_purpose_id , " +
            // " ' '||div_code as div_code, " +
            " div_code as div_code, " +
            " div_name, " +
            //" ' '||nn as nn, " +
            " nn as nn, " +
            " mat_name, " +
            //" ' '||bal_sch as bal_sch, " +
            " bal_sch as bal_sch, " +
            " mu_name, " +
            " nameostatok, " +
            " aq, " +
            " sm " +
            " from ( " +
            " select /*l.code as codecfo,l.name as namecfo , */ t.rest_purpose_type_id , t.id as rest_purpose_id ,   v.party_date , v.party_id , v.div_code, v.div_name, v.nn, v.mat_name, v.bal_sch, v.mu_name, t.name as nameostatok, sum(quantity) aq, sum(cost) sm " +
            "  from umc_dba.v_calc_rest_detail v, sprav.budget_core b, sprav.frc_list l, umc_dba.rest_purpose t " +
            " where b.frc_list_id = l.id " +
            "   and b.id = v.budget_core_id " +
            "   and v.rest_purpose_id = t.id " +
        //    "   and ( v.div_code = " + divcode + " or " + divcode + " = '0'  ) " +
            "   and ( v.div_code = ? or ? = '0'  ) " +
        //    "   and ( v.nn = " + nn + " or " + nn + " = '0'  ) " +
            "   and ( v.nn = ? or ? = '0'  ) " +
        //    "   and ( upper(t.name) like ('%'||upper(" + storagename + ")||'%') or " + storagename + " = ' ' ) " +
        // temp    "   and ( upper(t.name) like ('%'||upper(?)||'%') or ? = ' ' ) " +
        //    "   and ( upper(t.name) like ('%'||upper(" + storagezonename + ")||'%') or " + storagezonename + " = ' ' ) " +
        // temp    "   and ( upper(t.name) like ('%'||upper(?)||'%') or ? = ' ' ) " +
            " group by /*l.code , l.name ,*/ t.rest_purpose_type_id , t.id , v.party_date , v.party_id ,   v.div_code, v.div_name, v.nn, v.mat_name, v.bal_sch, v.mu_name, t.name " +
            " ) " +
            " Where aq <> 0 " +
            " order by div_code, div_name, nn, mat_name, bal_sch, mu_name, nameostatok ";

    //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
    connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();


    statement = finConnection.prepareStatement(sql);
    statement.setString(1, divcode);
    statement.setString(2, divcode);
    statement.setString(3, nn);
    statement.setString(4, nn);
    // temp statement.setString(5, storagename);
    // temp statement.setString(6, storagename);
    // temp statement.setString(7, storagezonename);
    // temp statement.setString(8, storagezonename);

    resultSet = statement.executeQuery();


    int finDocID;
    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
  // идем в цикле по остаткам и записываем их в темп таблицу
    while (resultSet.next())
    {
            String netSql = "insert into net.aaa_umc_rest (rest_purpose_type_id, party_id ,  party_date , rest_purpose_id  , div_code  , div_name , nn , mat_name , bal_sch , mu_name , nameostatok , aq , sm ) values "+
            " (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try
            {

                netStatement = netConnection.prepareStatement(netSql);
                netStatement.setInt(1, resultSet.getInt(1));
                netStatement.setInt(2, resultSet.getInt(2));
                netStatement.setDate(3, resultSet.getDate(3));
                netStatement.setInt(4, resultSet.getInt(4));
                netStatement.setString(5, resultSet.getString(5));
                netStatement.setString(6, resultSet.getString(6));
                netStatement.setString(7, resultSet.getString(7));
                netStatement.setString(8, resultSet.getString(8));
                netStatement.setString(9, resultSet.getString(9));
                netStatement.setString(10, resultSet.getString(10));
                netStatement.setString(11, resultSet.getString(11));
                netStatement.setBigDecimal(12, resultSet.getBigDecimal(12));
                netStatement.setBigDecimal(13, resultSet.getBigDecimal(13));

                // netResultSet = netStatement.executeQuery();
                // netStatement.executeQuery();
                netStatement.execute();
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage()+"\netStatement - " + netSql);
                EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
            }
            finally
            {
            try {if (netResultSet != null) netResultSet.close();} catch (SQLException e) {}
            try {if (netStatement != null) netStatement.close();} catch (SQLException e) {}
            netStatement = null;
            }
    }



        return  "" ;
    } catch (Exception e) {
        System.out.println("Error in selectRestFromUMCDBA: " +    e.getMessage());
        return "" ;
    } finally {

        if (isDebug)
            System.out.println("final selectRestFromUMCDBA: " + new Date());

        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}


        try {if (netResultSet != null) netResultSet.close();} catch (SQLException e) {}
        try {if (netStatement != null) netStatement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {}
    }

}

/* выбор МОЛ ов с которых было массовое пермещение в ТМЦ под Необходимого МОЛ а */
public String getStringMolMassMove(String partyid , String cutDate , String molcode ) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start getStringMolMassMove: " + new Date());

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement finStatement = null;
    ResultSet  finResultSet = null;
    boolean isNewConn = false;

    String out = "";
    String vmolcode = molcode.trim();
    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


    String sqlUmc = " select distinct th.sender_id " +
                    " from umc_dba.thead th , umc_dba.tstring  ts , umc_dba.tmatherial tm " +
                    " where th.id = ts.head_id " +
                    " and ts.mat_id = tm.id " +
                    " and ts.party_id in (" + partyid + ") " +
                    " and th.doc_date <= to_date('"+ cutDate +"','dd.mm.yyyy') " +
                    " and th.op_kind_id = -4 " +
                    " and th.acceptor_id = '"+ vmolcode +"'";


    statement = finConnection.prepareStatement(sqlUmc);
    resultSet = statement.executeQuery();


    String finOut = "";
    while (resultSet.next())
    {

        finOut = resultSet.getString(1);


        if (! finOut.equals(""))
        {
            if (out.equals(""))
            {
                out = "'"+finOut+"'";
            }
            else
            {
                out = out + ", " + "'"+finOut+"'";
            }
        }
    }

        // приклеим строку с посылаемым МОЛ ом
    if (out.equals("")) {
        out = "'"+vmolcode+"'";
        }
    else {
        out = out+","+"'"+vmolcode+"'";
        }

        return  out ;
    } catch (Exception e) {
        System.out.println("Error in getStringMolMassMove: " +    e.getMessage());
        return out ;
    } finally {

        if (isDebug)
            System.out.println("final getStringMolMassMove: " + new Date());

        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}


        try {if (finResultSet != null) finResultSet.close();} catch (SQLException e) {}
        try {if (finStatement != null) finStatement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {}
    }

}



public String getStorageName( Integer restpurposeid )
throws PersistenceException
{
    boolean isDebug = false;



    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

    if (isDebug){
    System.out.println("start getStorageName:" + new Date());
    }


    String sql = " select s.shortname from rqstoragezone2restprps aaa  , rqstoragezone sz , rqstorage s " +
                " where aaa.rest_purpose_id = " +   restpurposeid +  // 5225
                " and aaa.zonerefcode = sz.code " +
                " and sz.storagecode = s.code  limit 1 "     ;

    if (isDebug){
    System.out.println("sql : " + sql);

    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    String out = new String("");
    try {

    statement = netConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();
    while(resultSet.next())
    {
    out = resultSet.getString(1);
    }

    return out;
    } catch (Exception e) {
    System.out.println(    e.getMessage());
    return out;
    } finally {

    try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
    try {if (statement != null) statement.close();} catch (SQLException e) {}

    try {
    if  (netConnection != null && netConnection.isClosed())
        netConnection.close();
    } catch (SQLException e) {
    }
    }
}


public String getStoragezoneName( Integer restpurposeid )
throws PersistenceException
{
    boolean isDebug = false;



    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");

    if (isDebug){
    System.out.println("start getStorageName:" + new Date());
    }


    String sql = " select sz.name from rqstoragezone2restprps aaa  , rqstoragezone sz " +
                " where aaa.rest_purpose_id =  " + restpurposeid +
                " and aaa.zonerefcode = sz.code " +
                " limit 1 " ;

    if (isDebug){
    System.out.println("sql : " + sql);

    }

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    String out = new String("");
    try {

    statement = netConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();
    while(resultSet.next())
    {
    out = resultSet.getString(1);
    }

    return out;
    } catch (Exception e) {
    System.out.println(    e.getMessage());
    return out;
    } finally {

    try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
    try {if (statement != null) statement.close();} catch (SQLException e) {}

    try {
    if  (netConnection != null && netConnection.isClosed())
        netConnection.close();
    } catch (SQLException e) {
    }
    }
}

/*
 *
 * хитрый метод вычисления количества из одного или нескольких подразделений которые передаются в фунцию
 * выбирается колличество людей из того подразделения которое позже заведено в кадрах
 * */
public BigDecimal getCountEltehpersonalFromKadryForHMVE(String pdate_srez , String ppodrcod , String type_pers  ) throws PersistenceException
{

    boolean isDebug = false;

    BigDecimal out = new BigDecimal(0);
    int returnOnePodr = 0;


    PreparedStatement statementPodr = null;
    ResultSet  resultSetPodr = null;

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    if (isDebug)
        System.out.println("start getCountEltehpersonalFromKadryForHMVE" + new Date());

    // для вычесление количества персонала в подразделении выберем только одно
    // подразделение которое открыто на дату и дата заведения подразделения старше

    String SelectOnePodr = " select pd.podr_id from kadry.podr_day pd " +
                        " where pd.podr_id in (" + ppodrcod + ")" +
                        " and pd.Date_Start <= to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +
                        " and pd.date_finish >=  to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +
                        " order by pd.date_start desc ";
    boolean isNewConn = false;

    try {


        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

//        statementPodr = finConnection.prepareStatement(SelectOnePodr);
//
//        resultSetPodr = statementPodr.executeQuery();
//
//
//        if (resultSetPodr.next())
//            returnOnePodr = resultSetPodr.getInt(1);
//        else
//          System.out.println("resultSet clear getCountEltehpersonalFromKadryForHMVE");



//    } catch (Exception e) {
    //    System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadryForHMVE opredelenie odnogo podrazdeleniya ");

    //}


    //  выбор количества работников по выбраному подразделению


    String sql = " SELECT  count(*) as count  from ( "+
    //" SELECT /*+ RULE */ "
    " SELECT "
    + "/*-- Назначение на Должность*/ "

  + " n.Karta_Id, "
  + " kr.TabN, "
  + " fio.F||' '||fio.I||' '||fio.O FIO "

  + "  FROM "
  + " kadry.Prom            prom, "
  + " kadry.Kategory        k, "
  + " (SELECT gp2.Grafik_Id, gp2.Norma "
  + "  FROM  kadry.Grafik_Period  gp2 "
  + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
  + "     AND rownum<=1) g, "
  + " kadry.Doljnost      d, "
  + " kadry.Doljnost_Day  dd, "
  + " (SELECT p.Id             Podr_Id, "
  + "        p.Date_Start     Podr_Date_Start, "
  + "        p.Date_Finish    Podr_Date_Finish, "
  + "        pd.ROWID         Podr_Day_Row_Id, "
  + "        pd.Podr_Id       Podr_Day_Podr_Id, "
  + "        pd.Date_Start    Podr_Day_Date_Start, "
  + "        pd.Main_Podr_Id  Main_Podr_Id, "
  + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
  + "        pd.Nazv          Podr_Nazv, "
  + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
  + "        pd.Kod           Podr_Kod, "
  + "        pd.Prom_Id       Podr_Prom_Id , "
  + "        pd.Pos           Podr_Pos, "
  + "        pd.Vne_Shtata    Vne_Shtata, "
  + "        ceh.ROWID        Ceh_Row_Id, "
  + "        ceh.Podr_Id      Ceh_Id, "
  + "        ceh.Date_Start   Ceh_Date_Start, "
  + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
  + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
  + "        ceh.Nazv         Ceh_Nazv, "
  + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
  + "        ceh.Kod          Ceh_Kod, "
  + "        ceh.Prom_Id      Ceh_Prom_Id, "
  + "        ceh.Pos          Ceh_Pos "
  + "   FROM kadry.Podr_Day  ceh, "
  + "        kadry.Podr_Day  pd, "
  + "        kadry.Podr      p "
  + "  WHERE pd.Podr_Id(+) = p.Id "
  + "    AND (pd.Podr_Id IS NULL "
  + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day pd1 "
  + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
  + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "        ) "
  + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
  + "                        FROM kadry.Podr_Day ceh1 "
  + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
  + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  // + "    AND p.Id = " + returnOnePodr
  + "    AND p.Id in (" + ppodrcod + ")"
  + "  ) pd, "
  + " kadry.v_Post       ps, "
  + " kadry.v_Post       ps1, "

  + " /*-- предыдущее назначение*/ "
  + " kadry.Perevod_Vid  pv, "
  + " kadry.Osnova       os, "
  + " kadry.Nazn         n2, "
  + " kadry.Doljnost     d2, "
  + " kadry.Post         ps2, "
  + " kadry.Razr         rz2, "
  + " kadry.Prof         pf2, "
  + " /*-- следующее назначение*/ "
  + " kadry.Perevod_Vid  pv3, "
  + " kadry.Osnova       os3, "
  + " kadry.Nazn         n3, "
  + " kadry.Doljnost     d3, "
  + " kadry.Post         ps3, "
  + " kadry.Razr         rz3, "
  + " kadry.Prof         pf3, "

  + " kadry.FIO          fio, "
  + " kadry.Karta        kr, "
  + " kadry.Nazn_Vid     nv, "
  + " kadry.Pricaz       pr1, "
  + " kadry.Pricaz       pr2, "
  + " kadry.Pricaz       pr3, "
  + " kadry.Nazn_Day     nd, "
  + " kadry.Nazn         n "

  + " WHERE (nd.Nazn_Id = n.Id "

  + " /*-- Выбор параметров на дату среза*/ "
  + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + " AND NOT EXISTS "
  + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
  + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
  + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
  + "    AND nd2.Date_Start > nd.Date_Start) "
  + " AND n.Start_Pricaz_Id  = pr1.Id "
  + " AND n.Finish_Pricaz_Id = pr2.Id "
  + " AND nd.Pricaz_Id       = pr3.Id "
  + " AND n.Doljnost_Id = d.Id "
  + " AND pd.Podr_Id = d.Podr_Id "
  + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
  + " AND ps1.Post_Id = d.Post_Id "
  + " AND k.Id = d.Kategory_Id "
  + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
  + " AND dd.Doljnost_Id(+) = d.Id "
  + " /*-- Выбор параметров должности на дату*/ "
  + " AND (dd.Doljnost_Id IS NULL "
  + "   OR (dd.Date_Start <= n.Date_Finish  "
  + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
  + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
  + "                        AND dd2.Date_Start <= n.Date_Finish  "
  + "                            AND dd2.Date_Start > dd.Date_Start))) "
  + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
  + "    /*-- предыдущее назначение*/ "
  + "      AND pv.Id(+) = n.Perevod_Vid_Id "
  + "      AND os.Id(+) = n.Perevod_Osnova_Id "
  + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
  + "      AND n2.Doljnost_Id = d2.Id(+) "
  + "      AND ps2.Id(+) = d2.Post_Id "
  + "      AND pf2.Id(+) = ps2.Prof_Id "
  + "      AND rz2.Id(+) = ps2.Razr_Id   "
  + "    /*-- сдедующее назначение*/ "
  + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
+ "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
+ " AND os3.Id(+) = n3.Perevod_Osnova_Id "
+ " AND d3.Id(+)  = n3.Doljnost_Id "
+ " AND ps3.Id(+) = d3.Post_Id "
+ " AND pf3.Id(+) = ps3.Prof_Id "
+ " AND rz3.Id(+) = ps3.Razr_Id   "
+ " AND n.Karta_Id     = kr.Id "
+ " AND  upper(kr.prim) like ('%"+type_pers+"%')   "
+ " AND fio.Id         = kr.FIO_Id "
+ " AND nd.Nazn_Vid_Id = nv.Id(+) "
+ "    /*-- график назначения*/ "
  + " ) AND  "
+ "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
+ "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
//+ " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
+ " ORDER BY kr.TabN , n.Date_Start desc  " +
    " ) " ;


//    sql = "select count(*) count from kadry.Doljnost dd  Where dd.date_start >= ? " ;

    if (isDebug){
        System.out.println(" sql : " + sql);
        System.out.println("parampodrcod : " + returnOnePodr);
        System.out.println("paramdate : " + pdate_srez.toString());
        System.out.println("paramdate2 : " + pdate_srez);
    }

    //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
    //finConnection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();





    // try {


        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();


        if (resultSet.next())
        out = resultSet.getBigDecimal(1);
        else
          System.out.println("resultSet clear getCountEltehpersonalFromKadry");


        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadry");
        return out;
    } finally {

        //System.out.println("finally getCountEltehpersonalFromKadry" + new Date());

        try {
            if (resultSet != null)
                resultSet.close();
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;
                }
                    //System.out.println("Close connect");
            } catch (SQLException e) {
                System.out.println(e.getMessage() + "erorrr2 getCountEltehpersonalFromKadry");
            }
    }

}



//     выборка количества  часов в месяце для отпускников
public BigDecimal getCountHoursOtpuskForHMVE(String pdate_srez , String ppodrcod ) throws PersistenceException
{
    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getCountHoursOtpusk:" + new Date());

    // Date firstDay = getFirstDayInMonth(pdate_srez);
    BigDecimal out = new BigDecimal(0);
    int returnOnePodr = 0;
    BigDecimal sumHours = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement statementHours = null;
    ResultSet resultSetHours = null;

    PreparedStatement statement2 = null;
    ResultSet resultSet2 =  null;

    PreparedStatement statementnastroyka = null;
    ResultSet resultSetnastroyka = null;

    PreparedStatement statementPodr = null;
    ResultSet  resultSetPodr = null;

    // для вычесление количества персонала в подразделении выберем только одно
    // подразделение которое открыто на дату и дата заведения подразделения старше

    String SelectOnePodr = " select pd.podr_id from kadry.podr_day pd " +
                        " where pd.podr_id in (" + ppodrcod + ")" +
                        " and pd.Date_Start <= to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +
                        " and pd.date_finish >=  to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +
                        " order by pd.date_start desc ";
    boolean isNewConn = false;

    try {


        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

//        statementPodr = finConnection.prepareStatement(SelectOnePodr);
//
//        resultSetPodr = statementPodr.executeQuery();
//
//
//        if (resultSetPodr.next())
//            returnOnePodr = resultSetPodr.getInt(1);
//        else
//          System.out.println("resultSet clear getCountHoursOtpuskForHMVE");



//    } catch (Exception e) {
//        System.out.println(    e.getMessage() + " errorrr - getCountHoursOtpuskForHMVE opredelenie odnogo podrazdeleniya ");

//    }



//    try {
    String sql = " alter session "
            + " set   NLS_TERRITORY=AMERICA "
            + " NLS_LANGUAGE=AMERICAN "
            + " OPTIMIZER_MODE=RULE " ;

    if (finConnection == null){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        isNewConn = true;
    }

    statement = finConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();



    sql = "";
    // текст запроса назначеных людей
    sql = //" SELECT /*+ RULE */ "
            " SELECT "
        + "/*-- Назначение на Должность*/ "

    + " n.Karta_Id, "
    + " kr.TabN, "
    + " fio.F||' '||fio.I||' '||fio.O FIO "

    + "  FROM "
    + " kadry.Prom            prom, "
    + " kadry.Kategory        k, "
    + " (SELECT gp2.Grafik_Id, gp2.Norma "
    + "  FROM  kadry.Grafik_Period  gp2 "
    + "   WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po "
    + "     AND rownum<=1) g, "
    + " kadry.Doljnost      d, "
    + " kadry.Doljnost_Day  dd, "
    + " (SELECT p.Id             Podr_Id, "
    + "        p.Date_Start     Podr_Date_Start, "
    + "        p.Date_Finish    Podr_Date_Finish, "
    + "        pd.ROWID         Podr_Day_Row_Id, "
    + "        pd.Podr_Id       Podr_Day_Podr_Id, "
    + "        pd.Date_Start    Podr_Day_Date_Start, "
    + "        pd.Main_Podr_Id  Main_Podr_Id, "
    + "        pd.Podr_Vid_Id   Podr_Vid_Id, "
    + "        pd.Nazv          Podr_Nazv, "
    + "        pd.Sokr_Nazv     Podr_Sokr_Nazv, "
    + "        pd.Kod           Podr_Kod, "
    + "        pd.Prom_Id       Podr_Prom_Id , "
    + "        pd.Pos           Podr_Pos, "
    + "        pd.Vne_Shtata    Vne_Shtata, "
    + "        ceh.ROWID        Ceh_Row_Id, "
    + "        ceh.Podr_Id      Ceh_Id, "
    + "        ceh.Date_Start   Ceh_Date_Start, "
    + "        ceh.Main_Podr_Id Ceh_Main_Podr_Id, "
    + "        ceh.Podr_Vid_Id  Ceh_Vid_Id, "
    + "        ceh.Nazv         Ceh_Nazv, "
    + "        ceh.Sokr_Nazv    Ceh_Sokr_Nazv, "
    + "        ceh.Kod          Ceh_Kod, "
    + "        ceh.Prom_Id      Ceh_Prom_Id, "
    + "        ceh.Pos          Ceh_Pos "
    + "   FROM kadry.Podr_Day  ceh, "
    + "        kadry.Podr_Day  pd, "
    + "        kadry.Podr      p "
    + "  WHERE pd.Podr_Id(+) = p.Id "
    + "    AND (pd.Podr_Id IS NULL "
    + "      OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day pd1 "
    + "                       WHERE pd1.Podr_Id=pd.Podr_Id "
    + "                         AND pd1.Date_Start<= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "        ) "
    + "    AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) "
    + "                        FROM kadry.Podr_Day ceh1 "
    + "                       WHERE ceh1.Podr_Id=ceh.Podr_Id "
    + "                         AND ceh1.Date_Start<=" + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    // + "    AND p.Id = " + returnOnePodr
    + "    AND p.Id in (" + ppodrcod + ")"
    + "  ) pd, "
    + " kadry.v_Post       ps, "
    + " kadry.v_Post       ps1, "

    + " /*-- предыдущее назначение*/ "
    + " kadry.Perevod_Vid  pv, "
    + " kadry.Osnova       os, "
    + " kadry.Nazn         n2, "
    + " kadry.Doljnost     d2, "
    + " kadry.Post         ps2, "
    + " kadry.Razr         rz2, "
    + " kadry.Prof         pf2, "
    + " /*-- следующее назначение*/ "
    + " kadry.Perevod_Vid  pv3, "
    + " kadry.Osnova       os3, "
    + " kadry.Nazn         n3, "
    + " kadry.Doljnost     d3, "
    + " kadry.Post         ps3, "
    + " kadry.Razr         rz3, "
    + " kadry.Prof         pf3, "

    + " kadry.FIO          fio, "
    + " kadry.Karta        kr, "
    + " kadry.Nazn_Vid     nv, "
    + " kadry.Pricaz       pr1, "
    + " kadry.Pricaz       pr2, "
    + " kadry.Pricaz       pr3, "
    + " kadry.Nazn_Day     nd, "
    + " kadry.Nazn         n "

    + " WHERE (nd.Nazn_Id = n.Id "

    + " /*-- Выбор параметров на дату среза*/ "
    + " AND nd.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + " AND NOT EXISTS "
    + " (SELECT NULL FROM kadry.s_Nazn_Day nd2 "
    + "     WHERE nd2.Nazn_Id = nd.Nazn_Id "
    + "        AND nd2.Date_Start <= GREATEST(n.Date_Start, " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + ") "
    + "    AND nd2.Date_Start > nd.Date_Start) "
    + " AND n.Start_Pricaz_Id  = pr1.Id "
    + " AND n.Finish_Pricaz_Id = pr2.Id "
    + " AND nd.Pricaz_Id       = pr3.Id "
    + " AND n.Doljnost_Id = d.Id "
    + " AND pd.Podr_Id = d.Podr_Id "
    + "  AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) "
    + " AND ps1.Post_Id = d.Post_Id "
    + " AND k.Id = d.Kategory_Id "
    + " AND prom.Id = DECODE(k.Prom_Id, 3,pd.Podr_Prom_Id, k.Prom_Id) "
    + " AND dd.Doljnost_Id(+) = d.Id "
    + " /*-- Выбор параметров должности на дату*/ "
    + " AND (dd.Doljnost_Id IS NULL "
    + "   OR (dd.Date_Start <= n.Date_Finish  "
    + " AND NOT EXISTS (SELECT NULL FROM kadry.s_Doljnost_Day dd2 "
    + "                    WHERE dd2.Doljnost_Id = dd.Doljnost_Id "
    + "                        AND dd2.Date_Start <= n.Date_Finish  "
    + "                            AND dd2.Date_Start > dd.Date_Start))) "
    + "      AND dd.Grafik_Id = g.Grafik_Id(+)  "
      + "    /*-- предыдущее назначение*/ "
      + "      AND pv.Id(+) = n.Perevod_Vid_Id "
    + "      AND os.Id(+) = n.Perevod_Osnova_Id "
    + "      AND n.Perevod_Nazn_Id = n2.Id(+)  "
      + "      AND n2.Doljnost_Id = d2.Id(+) "
      + "      AND ps2.Id(+) = d2.Post_Id "
    + "      AND pf2.Id(+) = ps2.Prof_Id "
    + "      AND rz2.Id(+) = ps2.Razr_Id   "
    + "    /*-- сдедующее назначение*/ "
    + "      AND n.Id = n3.Perevod_Nazn_Id(+) "
   + "      AND pv3.Id(+) = n3.Perevod_Vid_Id "
   + " AND os3.Id(+) = n3.Perevod_Osnova_Id "
   + " AND d3.Id(+)  = n3.Doljnost_Id "
   + " AND ps3.Id(+) = d3.Post_Id "
   + " AND pf3.Id(+) = ps3.Prof_Id "
   + " AND rz3.Id(+) = ps3.Razr_Id   "
   + " AND n.Karta_Id     = kr.Id "
   + " AND upper(kr.prim) like ('%НВЗ%') "
   + " AND fio.Id         = kr.FIO_Id "
   + " AND nd.Nazn_Vid_Id = nv.Id(+) "
   + "    /*-- график назначения*/ "
    + " ) AND  "
    + "      n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
    + "   AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* добавил условие т.к из финкол попадали лишние люди которые назначены выше даты среза */  "
    //    + " AND ps1.Prof_Id in (9086 , 7294 , 7241, 7242 , 7243 , 7244 , 7245 , 7246 , 7169, 7170 , 7173 , 7251 , 7252 , 7253 , 7254 , 7256 , 7269 , 7275 , 7295 , 7296 , 7334 , 8144 , 8455 , 8482 , 8496 , 8507 , 9049 ,8453)  "
    + " ORDER BY kr.TabN, n.Date_Start desc  " ;

    if (isDebug){
        System.out.println("sql выборка людей : " + sql);
        System.out.println("paramworkday : " + pdate_srez.toString());
    }




        // PreparedStatement statement2 = finConnection.prepareStatement(sql);
        // statement.setDate(1,  new java.sql.Date(firstDay.getTime()));
        statement2 = finConnection.prepareStatement(sql);
        resultSet2 = statement2.executeQuery();

      // получили список сотрудников
        int i;

    while (resultSet2.next())
    {
        // по каждому сотруднику выбирам количество часов в отпуске
        String sqlnastroyka = " alter session "
            + " set   NLS_TERRITORY=AMERICA "
            + " NLS_LANGUAGE=AMERICAN "
            + " OPTIMIZER_MODE=RULE " ;

    statementnastroyka = finConnection.prepareStatement(sqlnastroyka);
    resultSetnastroyka = statement.executeQuery();
    if (isDebug)
        System.out.println(" Табельный " + resultSet2.getString(2) );

        String sqlHours = " select case when ((to_date('01.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
                + "  ) "
                + "     Then graf.rday1 else 0 end  "
            + " +  "
            + " case when ((to_date('02.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
            + "  ) "
            + "         Then graf.rday2 else 0 end "
            +" +    "
            +"  case when ((to_date('03.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday3 else 0 end   "
            +"   +  "
            +"  case when ((to_date('04.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday4 else 0 end    "
            +" +  "
            +"  case when ((to_date('05.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday5 else 0 end "
            +" + "
            +"  case when ((to_date('06.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday6 else 0 end     "
            +" + "
            +"  case when ((to_date('07.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday7 else 0 end     "
            +" +  "
            +"  case when ((to_date('08.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday8 else 0 end    "
            +" +  "
            +"  case when ((to_date('09.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday9 else 0 end    "
            +" +  "
            +"  case when ((to_date('10.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday10 else 0 end  "
            +" +  "
            +"  case when ((to_date('11.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday11 else 0 end    "
            +" +  "
            +"  case when ((to_date('12.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday12 else 0 end     "
            +" + "
            +"  case when ((to_date('13.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday13 else 0 end   "
            +" +  "
            +"  case when ((to_date('14.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday14 else 0 end   "
            +" + "
            +"  case when ((to_date('15.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday15 else 0 end  "
            +" +  "
            +"  case when ((to_date('16.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"           Then graf.rday16 else 0 end  "
            +" +  "
            +"  case when ((to_date('17.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday17 else 0 end  "
            +" +  "
            +"  case when ((to_date('18.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday18 else 0 end    "
            +" +  "
            +"  case when ((to_date('19.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday19 else 0 end    "
            +" +  "
            +"  case when ((to_date('20.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday20 else 0 end      "
            +" +  "
            +"  case when ((to_date('21.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday21 else 0 end     "
            +" +  "
            +"  case when ((to_date('22.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday22 else 0 end      "
            +" +  "
            +"  case when ((to_date('23.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday23 else 0 end  "
            +" + "
            +"  case when ((to_date('24.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po )"
            +"  ) "
            +"          Then graf.rday24 else 0 end  "
            +" +  "
            +"  case when ((to_date('25.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"  ) "
            +"          Then graf.rday25 else 0 end   "
            +" +  "
            +"  case when ((to_date('26.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday26 else 0 end "
            +" + "
            +"  case when ((to_date('27.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday27 else 0 end       "
            +" +  "
            +"  case when ((to_date('28.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday28 else 0 end "
            +" +  "
            +"  case when (( mod(to_number(to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'yyyy')) , 4 ) = 0 ) and ( to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') = '02' ))  or to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') <> '02' then "
            +"  case when ((to_date('29.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday29 else 0 end  "
            +" else 0  end      "
            +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') not in ('02') then "
            +"  case when ((to_date('30.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday30 else 0 end  "
            +" else 0  end      "
            +" +  case when to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm')  in ( '01' , '03', '05', '07', '08' , '10' , '12'  )  then  "
            +"  case when ((to_date('31.'||to_char(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm.yyyy'),'dd.mm.yyyy') between dat.s and dat.po ) "
            +"   ) "
            +"          Then graf.rday31 else 0 end "
            +" else 0  end   "
            +"          sumhours "
            +" from  (  "
            +" SELECT "
            +"  DECODE(odi.S,  TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.S) S, "
            +"  DECODE(odi.Po, TO_DATE('01.01.3000','DD.MM.YYYY'), TO_DATE(NULL), odi.PO) PO "
            +" FROM "
            +"  kadry.vOtp    vo, "
            +"  kadry.Karta   k, "
            +"  kadry.Otp o, "

            +"  kadry.Otp_Razdel r, "
            +"  (select odi1.Id "
            +"        , odi1.Otp_Id "
            +"        , odi1.S "
            +"        , odi1.Po "
            +"        , odi1.Kol "
            +"        , odi1.data_vvoda "
            +"        , odi1.Kompens "
            +"        , odi1.Mat_Pom "
            +"        , odi1.Otp_Razdel_Id "
            +"        , odi1.Osnova_Id "
        //  +"        , odi1.Prichina "
            +"        , odi1.Raspr "
            +"        , odi1.Pricaz_Id "
            +"       , p1.data "
            +"        , p1.nomer "
            +"        , p1.project "
            +"     from kadry.Pricaz p1, "
            +"          kadry.Otp_Dni_Ispolz odi1 "
            +"    where odi1.Pricaz_Id = p1.Id(+) "
            +"     ) odi "
            +" WHERE ((vo.Id = o.vOtp_Id "
            +" AND o.Karta_Id = k.Id "
            +" AND odi.Otp_Razdel_Id = r.Id(+) "
            +" AND o.Id = odi.Otp_Id "
            +" ) AND o.Karta_Id = o.Karta_Id  "
            +"  and k.tabn = " + resultSet2.getString(2)+ " ) AND o.Karta_Id = o.Karta_Id "
            +"                      and k.tabn = " + resultSet2.getString(2)
            +"  and trunc(to_date('"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') between trunc(odi.s,'mm') and last_day(odi.po)   "
            +" ) dat ,     "
            + "( SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,   "
            +"   rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20, "
            +"   rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 from zarpherson.grafik_work_view gwv "
            +" Where gwv.date_rasch = trunc(to_date('"+pdate_srez.toString()+"' , 'dd.mm.yyyy'),'mm')    "
            +" and  gwv.work_time_id = 1 ) graf "   ;

    // выполняем скрипт
        if (isDebug)
            System.out.println(    sqlHours);

        statementHours = finConnection.prepareStatement(sqlHours);
        resultSetHours = statementHours.executeQuery();

        if    (resultSetHours.next())
        {
            sumHours = sumHours.add(resultSetHours.getBigDecimal(1)) ;
            if (isDebug)
            System.out.println(resultSetHours.getBigDecimal(1));
        }

            try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
            try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}

            try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
            try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

    }


        return  sumHours ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "error5 - getcounthoursotpusk");

        return  sumHours ;
    } finally {
        if (isDebug)
            System.out.println("finally getCountHoursOtpusk:" + new Date());

        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

        try {if (resultSetnastroyka != null) resultSetnastroyka.close();}             catch (SQLException e) {}
        try {if (statementnastroyka != null) statementnastroyka.close();} catch (SQLException e) {}

        try {if (resultSetHours != null) resultSetHours.close();}             catch (SQLException e) {}
        try {if (statementHours != null) statementHours.close();} catch (SQLException e) {}


            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;}
            } catch (SQLException e) {
            }
    }

}


public BigDecimal getCountEltehpersonalFromKadryLite(String pdate_srez , Integer ppodrcod , String type_pers  ) throws PersistenceException
{

    boolean isDebug = false;

    BigDecimal out = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    if (isDebug)
        System.out.println("start getCountEltehpersonalFromKadryLite" + new Date());

    String sql = " select count(distinct (k.tabn)) kolvo " +
                " from kadry.karta k, kadry.nazn n, kadry.doljnost d " +
                " where  upper(k.prim) like ('%"+type_pers+"%')  and  " +
                "     ( "  +
                        " (   (n.date_start  between trunc(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') and  last_day(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy'))  )" +
                    "    or  (n.date_finish  between trunc(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') and  last_day(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy'))  ) " +
                        " ) " +
                    " or  (n.date_start < trunc(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy'),'mm') and (n.date_finish is null or n.date_finish > last_day(to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy') ) ) ) " +
                    " ) " +
                " and n.karta_id = k.id " +
                " and n.doljnost_id = d.id " +
                " and d.podr_id = " + ppodrcod +
             // NET-4418- количество персонала бралось все и закрытые и открытые .
             " AND   n.Date_Finish >= " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " /* открытые */  "
           + " AND   n.Date_Start < " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')"  ;


    if (isDebug){
        System.out.println(" sql : " + sql);
        System.out.println("parampodrcod : " + ppodrcod.toString());
        System.out.println("paramdate : " + pdate_srez.toString());
        System.out.println("paramdate2 : " + pdate_srez);
    }

    boolean isNewConn = false;

    try {


        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);




        resultSet = statement.executeQuery();


        if (resultSet.next())
        out = resultSet.getBigDecimal(1);
        else
          System.out.println("resultSet clear getCountEltehpersonalFromKadryLite");

        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadryLite");
        return out;
    } finally {



        try {
            if (resultSet != null)
                resultSet.close();
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;
                }
                    //System.out.println("Close connect");
            } catch (SQLException e) {
                System.out.println(e.getMessage() + "erorrr2 getCountEltehpersonalFromKadry");
            }
    }

}

public String getListOfFreeZones(int storageCode, java.util.Date date) throws PersistenceException, DatasourceConnectException, SQLException
{
    boolean isNewConn = false;
    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    try
    {
        java.util.List zoneList = new ArrayList();
        List restsList = new ArrayList();
        String result = "";

        int totalCount = 0; // Всего записей по назначениям остатков из зон
        int totalCountOut = 0; // Всего записей по "занятым" назначениям остатков из Учета материалов
        int counter = 1; // счетчик количества итераций

        /*Инициализация подключенией*/
        if (connection == null){
            connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        }

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        /*Вытягивание всех назначений остатков для зон по коду склада*/
        String restPurposesSQL = "select distinct " +
                                    " zops.rest_purpose_id " +
                                " from " +
                                "    rqstoragezone as zo " +
                                "    inner join rqstoragezone2restprps as zops on zo.code = zops.zonerefcode " +
                                " where " +
                                "    zo.storagecode = " + storageCode;

        statement = connection.prepareStatement(restPurposesSQL);
        resultSet = statement.executeQuery();

        while(resultSet.next())
        {

            zoneList.add(resultSet.getString(1));

            totalCount = totalCount + 1;
            if(((totalCount / counter) - 1) == 1000)
                counter = counter + 1;


        }

        resultSet.close();

        /*Вытягивание всех МОЛов по коду склада*/
        String storageMolsSQL = "select mo.fincode From rqstorage2enmol as somo " +
                                " inner join enmol as mo on mo.code = somo.molrefcode " +
                                " where storagerefcode = " + storageCode;

        String storageMols = "";

        statement = connection.prepareStatement(storageMolsSQL);
        resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            if(storageMols.length() == 0)
                storageMols = "'" + resultSet.getString(1) + "'";
            else
                storageMols = storageMols + ", '" + resultSet.getString(1) + "'";
        }


        resultSet.close();

        if(storageMols.length() == 0)
            storageMols = "null";

        // Процедура для расчета остатков
        String initSQL = "begin umc_dba.pkg_rest.init_dates_for_calc_rest(?,1); end;";

        statement = finConnection.prepareCall(initSQL);
        statement.setDate(1,new java.sql.Date(date.getTime()));
        statement.execute();


        // Начало итераций. Берется по 1000 записей, т.к. в Oracle нельзя посылать в условии больше чем 1000 строк
        for(int i = 1; i <= counter; i++)
        {
            String restPurposes = "";

            int last = ((totalCount - 1000*i) <= 0) ? totalCount : 1000 * i;
            int first = 1000*(i - 1);
            for(int j = first; j < last; j++)
            {
                if(restPurposes.length() == 0)
                    restPurposes = (String)zoneList.get(j);
                else
                    restPurposes = restPurposes + "," + (String)zoneList.get(j);
            }

            if(restPurposes.length() == 0)
                restPurposes = "null";

            String restsSQL = "select distinct " +
            "     rest_purpose_id as restpurposeid " +
            " from (" +
            " select div_code, div_name, bal_sch, mat_id, nn, mu_id, party_id, rest_purpose_type_id, rest_purpose_id, sum(quantity) as avg_qty " +
            "     from umc_dba.v_calc_rest_detail v " +
            " where v.rest_purpose_id in (" + restPurposes + ") " +
            "    and v.div_code in (" + storageMols + ")" +
            " group by div_code, div_name, bal_sch, mat_id, nn, mu_id, party_id, rest_purpose_type_id, rest_purpose_id ) where avg_qty > 0";

            statement = finConnection.prepareStatement(restsSQL);
            resultSet = statement.executeQuery();


            while(resultSet.next())
            {
                restsList.add(resultSet.getString(1));
                totalCountOut = totalCountOut +1;

            }

            resultSet.close();

        }

        String rests = "";
        if(totalCountOut > 0)
            for(int i = 0; i < totalCountOut; i++)
            {
                if(rests.length() == 0)
                    rests = (String)restsList.get(i);
                else
                    rests = rests + ", " + (String)restsList.get(i);
            }

        if(rests.length() == 0)
            rests = "null";

        String zonesSQL = " select distinct code from rqstoragezone " +
        " where " +
        " code not in " +
        " ( " +
        "     select zonerefcode from rqstoragezone2restprps where rest_purpose_id in (" + rests + ") )";

        statement = connection.prepareStatement(zonesSQL);
        resultSet = statement.executeQuery();


        while(resultSet.next())
        {
            if(result.length() == 0)
                result = resultSet.getString(1);
            else
                result = result + ", " + resultSet.getString(1);
        }

        resultSet.close();

        if(result.length() == 0)
            result = "null";

        return result;
    }
    finally
    {

        try {
            if (resultSet != null)
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        try {
            if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
                finConnection = null;
            } } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

}

/**public JRDataSource getContractBYestimate(String group_enestimateitem)
{

JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
boolean isNewConn = false;
PreparedStatement statement = null;
ResultSet  resultSet = null;
try {

    String sqlEne = " select distinct   e2c.findocid, o.id \n" +
            " from  enestimateitem ei , tkmaterials tm , enestimateitem2contrct e2c left join rqorg o on e2c.orgcode = o.code   \n" +
            "  \n" +
            "       where e2c.estimateitemcode = ei.code \n" +
            "       and ei.materialrefcode = tm.code  \n" +
            "       and e2c.estimateitemcode in(  504432015,504431221,506137078,506136967,506137046 )  \n" +
            " Union  \n" +
            "  \n" +
            " select distinct  oi.findocid , o.id  \n" +
            "  from rqorderitem2enestimttm qqq , rqorderitem oi  , rqorg o \n" +
            " where qqq.estimateitemcode in( 504432015,504431221,506137078,506136967,506137046 )  \n" +
            " and qqq.orderitemcode = oi.code  \n" +
            " and oi.orgcode = o.code  \n" +
            "  \n" ;

    Statement tempSt;

    tempSt = connection.createStatement();
    ResultSet rs = tempSt.executeQuery(sqlEne);

    while(rs.next())  // цикл по договорам енерджинет внутривыберем суммы
    {

    }


    ArrayList rows  = new ArrayList();
    String query = "";




//    if(rs.next()){//
//        if (rs.getInt(1) > 0 ) {
//        isPrihodFromBillItem = true;
//        }
//    }

         while(rs.next())  /// нашли приходы
            {

                  HashMap hashMap = new HashMap();
                        hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, new BigDecimal(0));

                        rows.add(hashMap);



    rs.close();
    tempSt.close();
            }
    return new ReestrPaymentDS(rows.toArray());

} catch (SQLException e) {
    throw new ReportSystemException(e);
} catch (ParseException e) {

    throw new ReportSystemException(e);
} finally {

}
}
*/


/**
*
* Возвращает сумму по договору
*
* @param int findocid - код договора из ФК
* @param int orgid - код поставщика из ФК
*/
public BigDecimal getSummaByContract(int findocid , int orgid ) throws PersistenceException
{
   boolean isDebug = true;

   if (isDebug)
       System.out.println("start getSummaByContract: " + new Date());


   // в тыс грн.

   String sql = " select sum(a.summa/1000) as sumtisgrn from  sprav.agree a , sprav.agree_partner_link apl  ,  sprav.partner p \n" +
        " where a.id = apl.agree_id \n" +
        " and p.id = apl.partner_id \n" +
        " and a.id = " + findocid + " \n" +
        " and p.id = " + orgid ;


   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

   BigDecimal out = new BigDecimal(100);

   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }


       statement = finConnection.prepareStatement(sql);

       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getSummaByContract()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getSummaByContract: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}

/**
 * Возвращает остаток топлива
 * если стоит isVRTU, то берет чисто ВРТУшный остаток, иначе берет весь остаток, кроме ВРТУшного
 *
 * @param molCode
 * @param type
 * @param date
 * @param isVRTU
 * @return
 * @throws PersistenceException
 * @throws SQLException
 */
public BigDecimal getFuelRestByMOLAndType(String molCode , int type, String date, boolean isVRTU) throws PersistenceException, SQLException
{
   boolean isDebug = false;

   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

   if (isDebug)
       System.out.println("start getFuelRestByMOLAndType: " + new Date());


   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

// не совсем корректный запрос для выбора остатков
   String sql = " SELECT " +
"           nvl(sum(q_end),0) " +
" From ( " +
" SELECT код_поставщика  as div, " +
 "          код_материала  as  m_id, " +
 "          m.name as mat_name, " +
 "          sum(количество) as q_end, " +
 "          sum(сумма)      as s_end, " +
 "          max(дата_документа) last_date " +
 "    FROM umc_dba.v_revert_str, umc_dba.tmatherial m " +
 "    WHERE дата_документа <= Last_Day(TO_DATE('" + date + "', 'dd.mm.yyyy')) and " +
 "          код_поставщика in (" + molCode + ") and " +
 "          код_материала = m.id " +
 " 			and v_revert_str.rest_purpose_id <> 2075 /*Резерв*/ " +
 "          and v_revert_str.budget_core_id " + (isVRTU ? "=" : "<>") + FKConsts.BUDGET_CORE_ID_VRTU +
 "          and ( ( (m.name like ('БЕНЗИН%92%') or m.name like ('БЕНЗИН%80%')) and  "+ type + " = 1 ) " +
 "              or " +
 "                (m.name like ('ДИЗПАЛИВО%') and  " + type + " = 2 ) " +
 "              or " +
 "                (m.name like ('БЕНЗИН%95%') and  " + type + " = 3 ) " +
 "              ) " +
 "    GROUP BY код_поставщика, " +
 "             код_материала , " +
 "             m.name " +
 "             ) " +
"Where q_end <> 0";


       BigDecimal out = new BigDecimal(100);

       statement = finConnection.prepareStatement(sql);



       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getFuelRestByMOLAndType()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getFuelRestByMOLAndType: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}

public JRDataSource getBrigadeDS(int rencode , String pdate1,  String pdate2 , BigDecimal persentLoadFilter , int withZadaniePlan )
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


	querySelBrigade = " select PODR_NAZV , podr_id , MAIN_PODR_ID ,PODR_NAZV_MAIN , shortname from ( \n" +
			"    select 'ТЕХНІЧНА ЧАСТИНА РЗйА' PODR_NAZV , cast(p.podr_id as number) podr_id , cast(-1 as number) MAIN_PODR_ID , ' ' PODR_NAZV_MAIN , 'RZA'  \n" +
			" shortname \n" +
			"      from  kadry.v_Podr_All_Rab p where p.ceh_id = "+rencode+" and p.podr_nazv like ('ТЕХНІЧНА ЧАСТИНА') \n" +
			"   UNION ALL \n" +
			"   select 'ТЕХНІЧНА ЧАСТИНА СДТУ' PODR_NAZV , cast(p.podr_id as number) podr_id , cast(-1 as number) MAIN_PODR_ID , ' ' PODR_NAZV_MAIN , 'DTU'  \n" +
			" shortname \n" +
			"      from  kadry.v_Podr_All_Rab p where p.ceh_id = "+rencode+" and p.podr_nazv like ('ТЕХНІЧНА ЧАСТИНА') \n" +
			"   UNION ALL \n" +
			"   select 'ТЕХНІЧНА ЧАСТИНА Ізоляція' PODR_NAZV , cast(p.podr_id as number) podr_id , cast(-1 as number) MAIN_PODR_ID , ' ' PODR_NAZV_MAIN  , 'IZ'  \n" +
			" shortname \n" +
			"      from  kadry.v_Podr_All_Rab p where p.ceh_id = "+rencode+" and p.podr_nazv like ('ТЕХНІЧНА ЧАСТИНА') \n" +
			"   UNION ALL \n" +
			"  \n" +
			"  Select p.Nazv as PODR_NAZV,   p.Podr_Id,   p.Main_Podr_Id,   pdm.Nazv   PODR_NAZV_MAIN , 'SHTAT' shortname \n" +
			"  \n" +
			" FROM \n" +
			"  \n" +
			"   kadry.Podr_Day  pdm, \n" +
			"   kadry.Podr_Day  cehd,     kadry.Podr_Vid     pv,   kadry.Prom         prom,   kadry.Pricaz       pr1,   kadry.Pricaz       pr2, \n" +
			"   kadry.Pricaz       pr3, \n" +
			"   (SELECT p0.*, \n" +
			" --          pd0.ROWID, \n" +
			"           pd0.Podr_Id, \n" +
			"           pd0.Date_Start Day_Date_Start, \n" +
			"           pd0.Main_Podr_Id, \n" +
			"           pd0.Podr_Vid_Id, \n" +
			"           pd0.Nazv, \n" +
			"           pd0.Sokr_Nazv, \n" +
			"           pd0.Kod, \n" +
			"           pd0.Prom_Id, \n" +
			"           pd0.STRUCT_ED, \n" +
			"           pd0.POS, \n" +
			"           pd0.VNE_SHTATA, \n" +
			"           pd0.PEREVOD_VID_ID, \n" +
			"           pd0.PRICAZ_ID, \n" +
			"           pd0.DATE_FINISH Day_Date_Finish, \n" +
			"           pd0.Kved_Id, \n" +
			"           pd0.Prim, \n" +
			"           pd0.Kod_Edrpou, \n" +
			"           kadry.Pkg_Podr_Utils.Get_For_Podr(p0.Id, GREATEST(p0.Date_Start, to_date('"+pdate2+"','dd.mm.yyyy'))) For_Podr \n" +
			"      FROM kadry.Podr     p0, \n" +
			"           kadry.Podr_Day pd0/*, \n" +
			"           kadry.v_dostup   v*/ \n" +
			"      WHERE p0.Id > 0 \n" +
			"        AND p0.Id = pd0.PODR_ID \n" +
			"        -- AND (v.podr_list IS NULL OR INSTR(v.podr_list, '_'||p0.id||'_') <> 0) \n" +
			"        AND pd0.Date_Start <= GREATEST(p0.Date_Start, to_date('"+pdate2+"','dd.mm.yyyy')) \n" +
			"        AND NOT EXISTS \n" +
			"          (SELECT NULL FROM kadry.Podr_Day pd2 \n" +
			"             WHERE pd2.Podr_Id = pd0.Podr_Id \n" +
			"               AND pd2.Date_Start <= GREATEST(p0.Date_Start, to_date('"+pdate2+"','dd.mm.yyyy')) \n" +
			"               AND pd2.Date_Start > pd0.Date_Start) \n" +
			"               ) p \n" +
			" WHERE (((p.Start_Pricaz_Id = pr1.Id \n" +
			"   AND p.Finish_Pricaz_Id = pr2.Id \n" +
			"   AND p.Pricaz_Id = pr3.Id(+) \n" +
			"   AND p.Podr_Vid_Id = pv.Id(+) \n" +
			"   AND p.Prom_Id = prom.Id(+) \n" +
			"   -- Параметры главного подразделения на дату среза \n" +
			"   AND pdm.ROWID = p.For_Podr.Main_Podr_Day_Row_Id \n" +
			"   -- Параметры Цеха на дату среза \n" +
			"   AND cehd.ROWID = p.For_Podr.Ceh_Day_Row_Id \n" +
			"  \n" +
			" ) AND p.Date_Start <= to_date('"+pdate2+"','dd.mm.yyyy') AND p.Date_Finish >= to_date('"+pdate2+"','dd.mm.yyyy') /* открытые */ \n" +
			" ) AND INSTR(kadry.pkg_Podr_Utils.Get_Path_Id(p.Podr_Id, GREATEST(p.Date_Start, to_date('"+pdate2+"','dd.mm.yyyy'))), '_'|| "+rencode+" ||'_')<>0) \n" +
			"  \n" +
			"   AND p.PODR_VID_ID = 9 /*= НВЗ*/ \n" +
			"  \n" +
			"   ) \n" +
			"  \n" +
			// "  where podr_id  = 229 " +  // test
			"   ORDER BY kadry.pkg_Podr_Utils.Concat_Pos(Podr_Id , TO_DATE ('"+pdate2+"', 'dd.mm.yyyy')) , main_podr_id \n" +
			"  \n" ;

	if (finConnection == null || finConnection.isClosed()){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

    statement = finConnection.prepareStatement(querySelBrigade);
    resultSet = statement.executeQuery();


    BigDecimal vv_count_personal = new BigDecimal(0);
    // часы по норме в месяце
    BigDecimal v_countworkhours = getCountWorkHours(pdate2);
    while(resultSet.next())  /// цикл по бригадам
    {




        int Ppodrkod = resultSet.getInt(2);
        String shortname = resultSet.getString(5);

        vv_count_personal= getCountEltehpersonalFromKadryRegular(pdate2, Ppodrkod , new String("НВЗ"), shortname );

        // если нет персонала пропускаем
        if (vv_count_personal.doubleValue() <= 0) {
        	continue;
        }

        // часы отпуска по подразделению
        BigDecimal v_counthoursotpusk = getCountHoursOtpuskRegular(pdate2, Ppodrkod , new String("НВЗ"), shortname );
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
                "                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
                "  ),0) as vikonan_work_priconection \n" +
                "             , coalesce(( select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor  , finexecutor2plan \n" +
                "                    Where  enplanwork.datestart between to_date('"+pdate1+"' , 'dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') \n" +
                "                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
                "                      and  enplanwork.kindcode = "+plankind+" /* задание факт , план , поточні , річні */ \n" +
                "                      and  enplanwork.typerefcode in (6, 100, 101, 102, 103, 104, 105, 106, 107,111,112) /*енергозбыт*/ \n" +
                "                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
                "                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode = "+Ppodrkod+" \n" +
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
                "                      and  finexecutor.fincode =  " +  Ppodrkod + "  \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                union all \n" +
        			"                select coalesce(sum(hi.countfact),0) as countfact  from enplanwork  , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode = 5 /* инвест программа */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                  union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , finexecutor , enhumenitem hi \n" +
        			"                    Where enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.typerefcode = 7 /*присоединение*/ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  \n" +
        			" , coalesce(( select sum(countfact) from ( \n" +
        			"               select sum(finexecutor2plan.totaltimemanhours) as countfact  from enplanwork , finexecutor , finexecutor2plan \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                      and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = plankind /* задание факт , план , поточні , річні  */ \n" +
        			"                      and  enplanwork.typerefcode = 3 /*роботи за приписом */ \n" +
        			"                      and  enplanwork.code =  finexecutor2plan.planrefcode \n" +
        			"                      and  finexecutor.code = finexecutor2plan.finexecutorcode \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_14_vneplan \n" +
        			"  , coalesce((  select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  /*------------ ПЛ - 10,/0,4 кВ --------------------------------------------------*/ \n" +
        			"   , coalesce(( select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                      select coalesce(sum(hi.countfact),0) as countfact  from  enplanwork , enelement , finexecutor ,  enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode in (1, 2) /*Повітряна лінія 0.4-10 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 1 /*Кап ремонт*/ \n" +
        			"                      and  enplanwork.typerefcode = 2 /*аварийно востановительные работы */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_20 \n" +
        			"   , coalesce((  select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                   ) s22 \n" +
        			"  ),0) as col_22 \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                    union all \n" +
        			"  \n" +
        			"                    select coalesce(sum(hi.countfact),0) as countfact  from enplanwork ,  enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode in ( 1 , 8 ) /*Кап ремонт или доробка */ \n" +
        			"                      and  enplanwork.typerefcode in ( /*1 , */ 10 )  /*плановые работы + внеплановые  */ \n" +
        			"                      and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                    ) s22vnpl \n" +
        			"  ),0) as col_22_vneplan \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_23 \n" +
        			"  , coalesce((   select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"  \n" +
        			"                      union all \n" +
        			"  \n" +
        			"                  select coalesce(sum(hi.countfact),0) as countfact  from enplanwork , enelement , finexecutor , enhumenitem hi \n" +
        			"                    Where  enplanwork.datestart between pdate1 and pdate2 \n" +
        			"                     and  enplanwork.statuscode NOT IN (2,6,8) /*убрали Работы завершены*/ \n" +
        			"                      and  enplanwork.kindcode = 3 \n" +
        			"                      and  enplanwork.elementrefcode = enelement.code \n" +
        			"                      and  enelement.typerefcode = 11 /*КЛ - 0,4/10/35 кВ*/ \n" +
        			"                      and  enplanwork.staterefcode = 3 /*Техническое обслуживание */ \n" +
        			"                      and  enplanwork.typerefcode in ( 2 , 9 ) /* или вкл или аварийно востановительные работы */ \n" +
        			"                       and  enplanwork.finexecutorcode = finexecutor.code \n" +
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_26 \n" +
        			"   , coalesce(( select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_27 \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_28 \n" +
        			"   , coalesce((  select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  \n" +
        			"    , coalesce(( select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  ),0) as col_30_vneplan \n" +
        			"  , coalesce(( select sum(countfact) from ( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                    ) s32 \n" +
        			"  ),0) as col_32 \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  \n" +
        			" coalesce(( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
        			"                      And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                      and ( enplanwork.typerefcode in (5 , 7 ) /*-- инв прогр и приедн*/ \n" +
        			"                           /*или*/ \n" +
        			"                                    /*по кр */ \n" +
        			"                            or ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (1 /*, 10*/ )) ) \n" +
        			"                          ) \n" +
        			"  \n" +
        			"                      ) \n" +
        			"              union all \n" +
        			"  \n" +
        			"              select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                            and  finexecutor.fincode = Ppodrkod \n" +
        			"                            And enplanwork.budgetrefcode in ("+budgcode+") \n" +
        			"                            and ( \n" +
        			"                                    ((enplanwork.staterefcode in ( 1 , 8 )) AND (enplanwork.typerefcode in (/*1 ,*/ 10 )) ) \n" +
        			"                                   ) \n" +
        			"  \n" +
        			"                            ) \n" +
        			"                       union all \n" +
        			"  \n" +
        			"         select case when sum(endeliverytime.deliverytimefact) <> 0 then sum(endeliverytime.deliverytimefact) else \n" +
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
        			"                            and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  \n" +
        			"  , coalesce(( \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"                      and  finexecutor.fincode = Ppodrkod \n" +
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
        			"  , ( "+Ppodrkod+" ) as Ppodrkod \n" +
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
            // netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
        	netConnection =  getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
        }

        statementNet = netConnection.prepareStatement(querySelWorkHours);
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

  //       if (var_col34.doubleValue() == 0 || var_col6.doubleValue() == 0 ){
  //
  //      	 System.out.print(resultSet.getString(4) + " " +  resultSet.getString(1));

       //  }

         if (var_col6.doubleValue() > 0 ) {
         loadPercent = var_col34.divide(var_col6, 8 , 2).multiply( new BigDecimal(100)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);;
         }
         if (loadPercent.doubleValue() > persentLoadFilter.doubleValue()  ){
        	 continue;
         }





        	HashMap hashMap = new HashMap();

        	hashMap.put(brigadeDS.PODR_NAME, resultSet.getString(4) + " " +  resultSet.getString(1));
        	hashMap.put(brigadeDS.PODR_CODE, resultSet.getInt(2));
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
 *  Дата прихода основного из Учета основных средств
 * */
public Date getDT_DOC_OS(String inv_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getDT_DOC_OS:" + new Date());

    if (inv_num.length() == 5) {
        inv_num = "0" + inv_num;
    }


    String sql = " Select ooo.dt_doc  from os_t.ostable ooo "  +
             " Where ooo.kod_inv = '" + inv_num.trim()  + "'" +
             " and ooo.dt_doc is not null " +
             " and rownum = 1 ";
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + inv_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    Date out = null;
    try {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //System.out.println("try");
        //finConnection = (Connection) ((HashMap) jrParameterMap.getValue()).get("finConnection");
        if (finConnection == null){
            //System.out.println("finConnection is null");
            //return new BigDecimal(Integer.MIN_VALUE);
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

    //    statement.setString(1, inv_num);



        resultSet = statement.executeQuery();
        //System.out.println("execSQL");
    //    if (resultSet.next()){
    //     out = resultSet.getString(1);
    //     System.out.println("Deprication :" + out );
    //    }
    //    else{
    //        System.out.println("NOT resultSet.next() for getKodZatrat " );
    //        throw new SystemException("Inv not found :"+inv_num);
    //    }

        while(resultSet.next())
        {
        out =  resultSet.getDate(1);
        }

    //    out = new BigDecimal(1);

        if (isDebug)
            System.out.println("return :" + out);

        if(out.equals(""))
            out=null;

        return out;
    }

        catch (SQLException e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " erorrr 101 - getDT_DOC_OS");
            //finConnection.
            //return out;
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            //out = new BigDecimal(2);
            System.out.println(    e.getMessage()+ " error 101 - getKodZatrat");
            //throw new Exception(e.getMessage());
            throw new PersistenceException("Error in method getKodZatrat()",e);
            //finConnection.
            //return out;


    }
        finally {

            //System.out.println("finally getDepreciation:" + new Date());

        //System.out.pr
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if (/*(isNewConn)&&*/(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
            //System.out.println("Close connn in getDeeprication");
        } catch (SQLException e) {
        }
    }

}


/**
 *  Дата выпуска основного из Учета основных средств
 * */
public Date getDT_VYPUSK_OS(String inv_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getDT_VYPUSK_OS:" + new Date());

    if (inv_num.length() == 5) {
        inv_num = "0" + inv_num;
    }


    String sql = " Select ooo.dt_vypusk from os_t.ostable ooo "  +
             " Where ooo.kod_inv = '" + inv_num.trim()  + "'" +
             " and ooo.dt_vypusk is not null " +
             " and rownum = 1 ";
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + inv_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    Date out = null;
    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        while(resultSet.next())
        {
        out =  resultSet.getDate(1);
        }


        if (isDebug)
            System.out.println("return :" + out);

        if(out != null && out.equals("")) {
        	out = null;
        }

        return out;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ " erorrr 101 - getDT_VYPUSK_OS");
            throw new PersistenceException(e.getMessage());


    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ " error 101 - getDT_VYPUSK_OS");
            throw new PersistenceException("Error in method getDT_VYPUSK_OS()",e);

    }
        finally {
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}

/**
 * Возвращает остаток топлива по входящим МОЛам , на дату , с или без резерва , по типу топлива(1-бензин 2-дп)

 *
 * @param depCode
 * @param type
 * @param date
 * @return
 * @throws PersistenceException
 * @throws SQLException
 */
public BigDecimal getFuelRestByMOLAndTypeOnDate(String depCode , int type, String date, int withRezerv) throws PersistenceException, SQLException
{
   boolean isDebug = true;

   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
  	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
  	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

   ENSpravMolDAO sprMolDAO = new ENSpravMolDAO(connection, userProfile);
   ENSpravMolFilter sprMolFilter = new ENSpravMolFilter();
   sprMolFilter.conditionSQL = " ENSpravMol.departmentcode in ( " + depCode + " ) ";

   String strMOL;
   strMOL = "";

   ENSpravMolShortList sprMoList = sprMolDAO.getScrollableFilteredList(sprMolFilter, 0, -1);

   for (int i=0; i<sprMoList.totalCount; i++){
	   if (strMOL.length() == 0 ){
		   strMOL = sprMoList.get(i).molkod;
	   } else
	   {
		   strMOL = strMOL + ", " + sprMoList.get(i).molkod;
	   }
   }

   if (  strMOL.length() == 0 ) {
	   return new BigDecimal(0);
   }

   if (isDebug)
       System.out.println("start getFuelRestByMOLAndTypeOnDate: " + new Date());


   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

		// не совсем корректный запрос для выбора остатков
		   String sql = " SELECT \n" +
		"           nvl(sum(q_end),0) \n " +
		" From ( \n " +
		" SELECT код_поставщика  as div, \n " +
		 "          код_материала  as  m_id, \n" +
		 "          m.name as mat_name, \n" +
		 "          sum(количество) as q_end, \n" +
		 "          sum(сумма)      as s_end, \n" +
		 "          max(дата_документа) last_date \n" +
		 "    FROM umc_dba.v_revert_str, umc_dba.tmatherial m \n" +
		 "    WHERE дата_документа <= TO_DATE('" + date + "', 'dd.mm.yyyy') and \n" + // остатки брались постоянно на посл день месяца(это не правильно)
		 "          код_поставщика in (" + strMOL + ") and \n" +
		 "          код_материала = m.id \n" +
		(withRezerv == 1 ? " " : " 			and v_revert_str.rest_purpose_id <> 2075 /*Резерв*/ " ) +
		 " 			and v_revert_str.rest_purpose_id <> "+RQConsts.REST_PURPOSE_ID_FUELTANK +" /*БАКИ*/ \n "  + // при выдаче топлива на путевых оно переходит в назначение БАКИ. поэтому остатки фин берем все что не  резерв и не баки
		 "			and (  \n" +
		 "	( (m.name like ('БЕНЗИН%92%') ) and "+ type +" = 1 )  \n " +
		 "	or  \n" +
		 "	(m.name like ('БЕНЗИН%95%') and   "+ type +"  = 2 )  \n " +
	     "		or  \n" +
		 "	(m.name like ('ДИЗПАЛИВО%') and  "+ type +"   = 3 )  \n" +
		 "	) \n" +
		 "    GROUP BY код_поставщика, \n" +
		 "             код_материала , \n" +
		 "             m.name \n" +
		 "             ) \n" +
		"Where q_end <> 0 ";


       BigDecimal out = new BigDecimal(0);

       statement = finConnection.prepareStatement(sql);



       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getFuelRestByMOLAndTypeOnDate()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getFuelRestByMOLAndTypeOnDate: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}



/**
 * Возвращает остаток топлива по АВЗ по входящим МОЛам , на дату , по типу топлива(1-бензин 2-дп)

 *
 * @param depCode
 * @param type
 * @param date
 * @return
 * @throws PersistenceException
 * @throws SQLException
 */
public BigDecimal getFuelRestAVZByMOLAndTypeOnDate(String depCode , int type, String date) throws PersistenceException, SQLException
{
   boolean isDebug = true;

   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
  	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
  	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

   ENSpravMolDAO sprMolDAO = new ENSpravMolDAO(connection, userProfile);
   ENSpravMolFilter sprMolFilter = new ENSpravMolFilter();
   sprMolFilter.conditionSQL = " ENSpravMol.departmentcode in ( " + depCode + " ) ";

   String strMOL;
   strMOL = "";

   ENSpravMolShortList sprMoList = sprMolDAO.getScrollableFilteredList(sprMolFilter, 0, -1);

   for (int i=0; i<sprMoList.totalCount; i++){
	   if (strMOL.length() == 0 ){
		   strMOL = sprMoList.get(i).molkod;
	   } else
	   {
		   strMOL = strMOL + ", " + sprMoList.get(i).molkod;
	   }
   }

   if (  strMOL.length() == 0 ) {
	   return new BigDecimal(0);
   }

   if (isDebug)
       System.out.println("start getFuelRestAVZByMOLAndTypeOnDate: " + new Date());


   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

		// не совсем корректный запрос для выбора остатков
		   String sql = " SELECT \n" +
		"           nvl(sum(q_end),0) \n" +
		" From ( \n" +
		" SELECT код_поставщика  as div, \n" +
		 "          код_материала  as  m_id, \n" +
		 "          m.name as mat_name, \n" +
		 "          sum(количество) as q_end, \n" +
		 "          sum(сумма)      as s_end, \n" +
		 "          max(дата_документа) last_date \n" +
		 "    FROM umc_dba.v_revert_str, umc_dba.tmatherial m \n" +
		 "    WHERE дата_документа <= TO_DATE('" + date + "', 'dd.mm.yyyy') and \n" + // остатки брались постоянно на посл день месяца(это не правильно)
		 "          код_поставщика in (" + strMOL + ") and \n" +
		 "          код_материала = m.id \n" +
		 " 			and v_revert_str.rest_purpose_id = 1 /*Аварийный запас*/ \n"  +
		 "			and (  \n" +
		 "	( (m.name like ('БЕНЗИН%92%') ) and "+ type +" = 1 )  \n" +
		 "	or  \n" +
		 "	(m.name like ('БЕНЗИН%95%') and   "+ type +"  = 2 )  \n" +
	     "		or  \n" +
		 "	(m.name like ('ДИЗПАЛИВО%') and  "+ type +"   = 3 )  \n" +
		 "	) \n" +
		 "    GROUP BY код_поставщика, \n" +
		 "             код_материала , \n" +
		 "             m.name \n" +
		 "             ) \n " +
		"Where q_end <> 0 ";


       BigDecimal out = new BigDecimal(100);

       statement = finConnection.prepareStatement(sql);



       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getFuelRestByMOLAndTypeOnDate()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getFuelRestAVZByMOLAndTypeOnDate: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}



public BigDecimal getRestAVZ(String nn ) throws PersistenceException
{

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement netStatement = null;
    ResultSet  netResultSet = null;
    boolean isNewConn = false;

    BigDecimal out = new BigDecimal(0);

    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


    String sql  = " select sum(r.quantity) as quantity " +
    		  " from umc_dba.v_calc_rest_detail r " +
    		  " where r.rest_purpose_type_id = 1 " +
    		  // " -- and r.nn in ('538001391','538001445') " +
    		   " and   r.nn = substr(?, instr(?, r.nn, 1), 9) " +
    		   " having sum(r.quantity) <> 0 ";

    connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();


    statement = finConnection.prepareStatement(sql);
    statement.setString(1, nn);
    statement.setString(2, nn);
    resultSet = statement.executeQuery();


    int finDocID;
//    JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
 //   netConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("netConnection");
  // идем в цикле по остаткам и записываем их в темп таблицу
    while (resultSet.next())
    {
         out = resultSet.getBigDecimal(1);
    }



        return  out ;
    } catch (Exception e) {
        System.out.println("Error in getRestAVZ: " +    e.getMessage());
        throw new SystemException(e.getMessage() );
       // return out ;
    } finally {


        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}


        try {if (netResultSet != null) netResultSet.close();} catch (SQLException e) {}
        try {if (netStatement != null) netStatement.close();} catch (SQLException e) {}

        try {
            if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {}
    }

}



/* заполнение временной таблицы материалы АВЗ для закупки  */
public BigDecimal fillingTempTableAVZ(String date) throws PersistenceException
{

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


    	String chktbl = "select count(*)  from information_schema.tables where table_name = 'temptableavz'";
    	prepStatement = connection.prepareStatement(chktbl);
    	resultSet = prepStatement.executeQuery();

    	boolean temptblexist = false;
    	if (resultSet.next()){
    		if (resultSet.getInt(1) > 0  ) {
    			temptblexist = true;
    		}
    	}

    	if (temptblexist ==false ) {
    		  sql = " create /*temporary*/ table temptableavz( " +
      		  		"   countgen double precision " +
      		  		" , countrequired double precision  " +
      		  		" , materialrefcode double precision " +
      		  		" , materialrefname varchar " +
      		  		" , measurementcode double precision " +
      		  		" , measurementname varchar " +
      		  		" , nomenclaturnumber varchar " +
      		  		" , budgetrefcode double precision " +
      		  		" , departmentrefcode double precision " +
      		  		" , strnomenclatures varchar " +
      		  		" , orderedcountwithoutinvoices double precision " +
      		  		" , countinfk double precision" +
      		  		" , counttoorder double precision " +
      		  	    " , counttoordercalculated double precision " +
      		  		" , sumtoorder double precision ) ; ";
    	} else
    		 sql = " truncate table temptableavz; ";

    	statement = connection.createStatement();
    	statement.execute(sql);

        // kbc
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


public JRDataSource getDsAccountingPersonal(String dateStart , String dateFinal , int countmonth ,  int department, int onlyNPZ , String ax_podrcode)
	{


	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	PreparedStatement tempSt = null;
	ResultSet rs = null;

	try {

		ENPlanWorkDAO pDAO = new ENPlanWorkDAO(connection, userProfile);
		AXOrgId2FKOrgIdDAO ax2fkDAO = new AXOrgId2FKOrgIdDAO(connection, userProfile);

		finConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("finConnection");
		FINLogic finLogic = new FINLogic(finConnection, userProfile);


		String podr_str = "";




		if (department != Integer.MIN_VALUE && department != 0 )
		podr_str = finLogic.getDepartmentIdDownFromKadry(department);



		boolean usesMDAXDataReport = false;
    	AuthLogic netAuth = new AuthLogic(connection, userProfile);
    	usesMDAXDataReport = netAuth.checkUsesMDAXDataForReport();
    	mDaxScriptlet mScr = new mDaxScriptlet();

		// выберем аксаптовские коды подразделений если посылается аксаптовский код подразделения
		if (!ax_podrcode.equals("")){
			podr_str = mScr.GetAxOrganizationIdDownString(dateStart, dateFinal, ax_podrcode) ;
		}

		if ((department != Integer.MIN_VALUE && department != 0 ) || (!ax_podrcode.equals("")) ) {

			AXOrgId2FKOrgIdFilter ax2fkFilter = new AXOrgId2FKOrgIdFilter();

			// если по фк коду подразделения то ищем соответствия в аксаптовских кодах
			if (department != Integer.MIN_VALUE && department != 0 ){
				ax2fkFilter.conditionSQL = " AXORGID2FKORGID.FKORGID in ( " + podr_str + ")" ;
			} else
			{
				ax2fkFilter.conditionSQL = " AXORGID2FKORGID.AXORGID in ( " + podr_str + ")" ;
			}

			AXOrgId2FKOrgIdShortList ax2fkList = ax2fkDAO.getScrollableFilteredList(ax2fkFilter, 0, -1);

			for (int l = 0; l < ax2fkList.totalCount; l++){

				if (department != Integer.MIN_VALUE && department != 0 ) {
			    	podr_str = podr_str + "," + "'"+ ax2fkList.get(l).axOrgId +"'";
				} else
				{
					podr_str = podr_str + "," + "'"+ax2fkList.get(l).fkOrgId+"'";
				}
			}

		}




		 String selStr1 = " with plan as (  select p.code , p.typerefcode  from enplanwork p  \n" +
				" where p.kindcode = 4 \n" +
				" and p.datestart between '"+dateStart+"' and '"+dateFinal+"' \n" +
				" and p.statuscode = 3 \n" +
				"  \n" +
				" ) \n" +
				"  \n" +
				" select count(tabnumber) as countpers_by_day ,  \n" + // 1
				" categorid ,  \n" +  //2
				" categorname ,  \n" + //3
				" worktimeid ,  \n" + // 4
				" first_day(datestart) \n" + // 5
				" from ( \n" +
				"         Select fw.name , fw.tabnumber , fw.categorid , fw.categorname , fw.worktimeid , p.datestart \n" +
				"         , string_agg( hi.planrefcode::text , ',')  \n" +
				"          from enhumenitem hi , finworker fw , enplanwork p , enplanworkitem pi \n" +
				"                      where hi.planrefcode in (select code from plan )  \n" +
				"                      and hi.finworkercode = fw.code \n" +
				"                      and hi.planrefcode = p.code  \n" +
				"                      and p.typerefcode <> 11 -- перевезення нужно брать только водителей   \n" +
				"                      and hi.planitemrefcode = pi.code  \n" +
				"                      and pi.countgen > 0 \n" +
				"                      and pi.planrefcode = p.code  \n" +
				"                      and hi.countfact > 0  \n" +
				"                      and fw.categorid is not null  \n" +
				"                      and fw.worktimeid is not null \n" +
				new String( (department != 0 || !ax_podrcode.equals("") ) ? " and fw.departmentcode in (" + podr_str + " ) "  : " " ) + "\n" +
				// признак уичтывать всех работников с плана или только с признаками нвз и нвз_е
				new String( onlyNPZ == 1 ? " and coalesce(fw.kindrefcode,0) > 0   " : " " )  + " \n" +
				"                      group by fw.tabnumber , fw.categorid , fw.categorname , fw.worktimeid , p.datestart,  fw.name \n" +
				"           union all \n" +
				"           Select fw.name , fw.tabnumber , fw.categorid , fw.categorname , fw.worktimeid , p.datestart \n" +
				"         , string_agg( ti.planrefcode::text , ',')  \n" +
				"          from entransportitem ti , finworker fw , enplanwork p , enplanworkitem pi \n" +
				"                      where ti.planrefcode in (select code from plan )  \n" +
				"                      and ti.finworkercode = fw.code \n" +
				"                      and ti.planrefcode = p.code  \n" +
				"                      and ti.planitemrefcode = pi.code  \n" +
				"                      and pi.countgen > 0 \n" +
				"                      and pi.planrefcode = p.code  \n" +
				"                      and fw.categorid is not null  \n" +
				"                      and fw.worktimeid is not null \n" +
				new String( (department != 0 || !ax_podrcode.equals("") ) ? " and fw.departmentcode in (" + podr_str + " ) " : " " ) + "\n" +
				// признак уичтывать всех работников с плана или только с признаками нвз и нвз_е
				new String( onlyNPZ == 1 ? " and coalesce(fw.kindrefcode,0) > 0   " : " " )  + " \n" +
				"                      group by fw.tabnumber , fw.categorid , fw.categorname , fw.worktimeid , p.datestart,  fw.name \n" +
				"                      \n" +
				" ) sel_pers_by_day \n" +
				" group by categorid ,  \n" +
				" categorname ,  \n" +
				" worktimeid ,  \n" +
				" first_day(datestart)  \n" +
				" order by categorid , worktimeid \n" +
				"  \n" ;


		tempSt = connection.prepareStatement(selStr1 );
        System.out.println(selStr1 );
        rs = tempSt.executeQuery();

	    ArrayList rows  = new ArrayList();
	    Vector list = new Vector();
	    int count = 0;

	    class countPers {
	        public String categoryid;
	        public String categoryname;
	        public BigDecimal averagecountpersonal;

	        public countPers() {
	        }
	    }

	    Hashtable<Integer, countPers> hashcountP = new Hashtable<Integer, countPers>(); // Хэш для хранения информации по численности
	    while(rs.next())
      {
	    	count = count+1;

	    	countPers countP = new countPers();

	    	BigDecimal[] w_daysArr = { new BigDecimal(0), new BigDecimal(0) };
	    	BigDecimal w_days = new BigDecimal(0);

	    	// если график работ из аксапты то выберем рабочие дни из аксапты
	    	System.out.print("rs.getString(4) === " + rs.getString(4) );

           // if (rs.getString(4).toLowerCase().startsWith("ax") )
	    	 if (!rs.getString(4).startsWith("1")  && !rs.getString(4).startsWith("3") )
            {

            	mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );


               w_daysArr = 	mdLogic.getWorkingTimePlan(  rs.getDate(5) ,
            		   Tools.getLastDayOfMonth(rs.getDate(5)) ,
            		   rs.getString(4) );
               w_days= w_daysArr[0];

               if (w_days.compareTo(new BigDecimal(0)) == -1 || w_days.compareTo(new BigDecimal(0)) == 0  ) {
   	        	// исправим глюк что по финворкеру может быть неправельный график т.к изначально график не вставлялся в финворкера .
            	   // выберем пчасы дни по общему графику на дату
            	   w_daysArr = 	mdLogic.getWorkingTimePlan(  rs.getDate(5) ,
                		   Tools.getLastDayOfMonth(rs.getDate(5)) ,
                		   "Смена 8.2 часа Пн-Пт" );
                   w_days= w_daysArr[0]; // дни
   	           }

            } else {
	    	   w_daysArr = finLogic.getWorkTimeByGrafik(rs.getDate(5),rs.getInt(4)); // рабочие дни для графика на дату
	           w_days = w_daysArr[1];
            }

	        if (w_days.compareTo(new BigDecimal(0)) == -1 || w_days.compareTo(new BigDecimal(0)) == 0  ) {
	        	 throw new EnergyproSystemException(" Не знайдено кількість робочих днів!!!" + "  " );
	        }

	        BigDecimal average_count_by_category_worktime = new BigDecimal(0);
	        average_count_by_category_worktime = rs.getBigDecimal(1).divide(w_days,2, BigDecimal.ROUND_HALF_UP);



	    	if(hashcountP.containsKey(rs.getInt(2))) {
	    		countP = hashcountP.get(rs.getInt(2));
	    		countP.averagecountpersonal = countP.averagecountpersonal.add(average_count_by_category_worktime);
			  } else {
				    countP.categoryid = rs.getString(2);
			    	countP.categoryname = rs.getString(3);
			    	countP.averagecountpersonal = average_count_by_category_worktime;
			  }

	    	hashcountP.put(rs.getInt(2), countP);


      }

		 // строка всього
	    	BigDecimal countSum = new BigDecimal(0);
			  Enumeration<Integer> hashcountPCategory = hashcountP.keys();
			  while(hashcountPCategory.hasMoreElements()) {
				  int categoryCode = hashcountPCategory.nextElement();
				  countPers categor = hashcountP.get(categoryCode);
				  // c делением на кол во месяцев и статистическим округлением
				  countSum = countSum.add(categor.averagecountpersonal.divide(new BigDecimal(countmonth),0, BigDecimal.ROUND_HALF_EVEN));
			  }

	      HashMap hashMap = new HashMap();


	      hashMap.put(dsAccounting.CATEGORYID, 0);
	      hashMap.put(dsAccounting.CATEGORYNAME, "ВСЬОГО");
	      hashMap.put(dsAccounting.AVERAGECOUNTPERSONAL, countSum);


	      rows.add(hashMap);

	   // расшифровка всього по категориям

			  Enumeration<Integer> hashcountPCategoryDetail = hashcountP.keys();
			  while(hashcountPCategoryDetail.hasMoreElements()) {
				  int categoryCode = hashcountPCategoryDetail.nextElement();
				  countPers categor = hashcountP.get(categoryCode);


				// делим на кол-во месяцев что б получить среднее и округляем до целого числа
			  BigDecimal  countSumDetail = categor.averagecountpersonal.divide(new BigDecimal(countmonth),0, BigDecimal.ROUND_HALF_EVEN);

				  hashMap = new HashMap();
				  hashMap.put(dsAccounting.CATEGORYID, categor.categoryid);
			      hashMap.put(dsAccounting.CATEGORYNAME, categor.categoryname);
			      hashMap.put(dsAccounting.AVERAGECOUNTPERSONAL,countSumDetail );


			      rows.add(hashMap);
			  }









	    return new dsAccounting(rows.toArray()); //  dsFinansirOsvoenieBalans(rows.toArray());

	    } catch (PersistenceException e) {

	    	// System.out.println(    e.getMessage());
	    	throw new EnergyproSystemException(e.getMessage());
		} catch (SQLException e) {

			throw new EnergyproSystemException(e.getMessage());
		} finally {
	        try {
	            if (rs != null) rs.close();
	            } catch (SQLException e) {
	        }
	        try {
	            if (tempSt != null) tempSt.close();
	        } catch (SQLException e) {
	        }
	    }
	}


public String getDepartmentIdDownFromKadryIntString( int department) throws SQLException
{


JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


try {


	//finConnection = (Connection)((HashMap) jrParameterMap.getValue()).get("finConnection");


	 if ((finConnection == null) || (finConnection.isClosed()) )  {
	     try {
			finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
		} catch (DatasourceConnectException e) {
	    	throw new ReportSystemException(e);
		}
	 }
	 FINLogic finLogic = new FINLogic(finConnection, userProfile);

	String fk_podr_str = "";
	fk_podr_str = finLogic.getDepartmentIdDownFromKadry(department);


	fk_podr_str = fk_podr_str.replace( '\'', ' ' );


    return fk_podr_str;

    } catch (PersistenceException e) {

    	// System.out.println(    e.getMessage());
    	throw new EnergyproSystemException(e.getMessage());
	}
}


/**
 * Сумма до договору для отчета Виставлені рахунки по договорам (tender_materials)
 * */
public BigDecimal getSummaByAgreeForTender_materials(Integer findocid ) throws PersistenceException
{

    String sql = " select sum((a.summa * 5 / 6)) summa " +
                 "  from sprav.agree a " +
                 " where a.id = " + findocid ;


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;
    BigDecimal out = new BigDecimal(0);
    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        if (resultSet.next())
        out = resultSet.getBigDecimal(1);

        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() );
        return out;
    } finally {
        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                finConnection.close();
            }
        } catch (SQLException e) {
        }
    }

}
/**
 *
 *
 * @param rencode
 * @param pdate1
 * @param pdate2
 * @param param_kindWorker (1- технич  2-сбыт монтеры 3 сбыт инспекция )
 * @return
 */
public JRDataSource getPremiumsWorker(int rencode , String pdate1 , String pdate2 , int param_kindWorker  )
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

PreparedStatement statement = null;
ResultSet  resultSet = null;

PreparedStatement statementNet = null;
ResultSet  resultSetNet = null;
boolean isNewConn = false;

System.out.print("Start getPremiumsWorker  ");

try {
	// по подразделению выберем бригады с нпз

	//FINWorkerShortList.

	ArrayList rows  = new ArrayList();
	mDaxScriptlet mScr = new mDaxScriptlet();

	String querySelWorker = "";

	String querySelWorkHours = "";

	String depCodes  = this.getDepartmentIdDownFromKadryIntString(rencode);
	String depCodesAX = mScr.GetAxOrganizationIdDownStringByEnergyNETPodrCode(pdate1 , pdate2, rencode);

	if(depCodes.equals("")){
		  throw new EnergyproSystemException("Неопределенное подразделение!!!");
	}

	int kindWorker = 0;
	if (param_kindWorker == 1 ) {
		kindWorker = 1;
	}
	if (param_kindWorker == 2|| param_kindWorker == 3) {
		kindWorker = 2;
	}


	querySelWorker = "select /*distinct fw.tabnumber*/ string_agg(distinct ''''||fw.tabnumber||''''::varchar,',')  from finworker fw , enhumenitem hi , enplanwork p " +
					" where fw.code = hi.finworkercode  " +
					" and hi.planrefcode = p.code  " +
					" and p.kindcode = 4 " +
					" and p.datestart between to_date('"+pdate1+"','dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') " +
					" and fw.kindrefcode =  "  + ( param_kindWorker == 1 ? 1 : param_kindWorker == 2 ? 2 : param_kindWorker == 3 ? 2 : 0 )  +
					" and coalesce(hi.countfact,0) > 0  " +
					" and ( fw.departmentcode::double precision in ("+depCodes+")  or fw.departmentcode::double precision in ( "+depCodesAX+") ) \n"
					+ new String(param_kindWorker == 2 ? " and upper(fw.positionname) like upper('%монтер%лічильників%') " :

						         param_kindWorker == 3 ? " and upper(fw.positionname) like upper('%інспектор%інспекції%') " :

						" and ( (upper(fw.positionname) like upper('%релейного%захисту%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%монтер%ремонт%апаратури%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%повітр%ліні%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%монтер%кабельн%ліній%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%слюсар%устаткув%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%монтер%обмоток%ізол%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%монтер%експлуатації%р%м%')) \n" +
						"                           or  \n" +
						"                           (upper(fw.positionname) like upper('%монтер%ремонту%передачі%')) \n" +
						"                           or   \n" +
						" 						  (upper(fw.positionname) like upper('%зварник%'))  \n" +
						"                           or   \n" +
						" 						  (upper(fw.positionname) like upper('%ел.монтер з рем.апаратури РЗйА%'))  \n" +
						"                           or   \n" +
						" 						  (upper(fw.positionname) like upper('%ел.монтер з ремонту обмоток та ізол ел.устаткування%')) \n" +
						"                           or   \n" +
						" 						  (upper(fw.positionname) like upper('%ел.монтер з ремонту та монтажу КЛ%')) \n" +
						"                          ) \n" +
						"  \n"

                                		)
					//+ /*tezzzt */ " and fw.tabnumber = 50861 "
					;

	if (finConnection == null || finConnection.isClosed()){

        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

	 if (netConnection == null || netConnection.isClosed() ){
     	netConnection =  getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
     }

	 statement = netConnection.prepareStatement(querySelWorker);
	 resultSet = statement.executeQuery();
	 String   tabnStr = "";
	 if (resultSet.next())
       tabnStr = resultSet.getString(1);


	 workerListFromKadryShortList workerList = this.getWorkerListFromKadry(pdate1, pdate2 , rencode ,tabnStr , kindWorker, true);

	 ArrayList<WorkerListFromKadryShortCompare> arraylistSort = new ArrayList<WorkerListFromKadryShortCompare>();

	 for (int f=0; f < workerList.totalCount; f++){

		 boolean ixistTabnFromSortList = false;
		 for (int i = 0; i < arraylistSort.size(); i++) {

			   int finWorkerList = workerList.get(f).tabn;
			   int finWorkerListSort =  arraylistSort.get(i).tabn;

			   if (finWorkerList == finWorkerListSort)  {
				   ixistTabnFromSortList = true;
				   arraylistSort.set(i, new WorkerListFromKadryShortCompare(
							 workerList.get(f).tabn ,
							 workerList.get(f).podr_nazv ,
							 workerList.get(f).podr_id ,
							 workerList.get(f).fio,
							 workerList.get(f).post_fin,
							 workerList.get(f).norma_vrem_days + arraylistSort.get(i).norma_vrem_days,
							 workerList.get(f).norma_vrem_hours.add(arraylistSort.get(i).norma_vrem_hours),
							 workerList.get(f).norma_vrem_hours_without_hours.add(arraylistSort.get(i).norma_vrem_hours_without_hours),
							 workerList.get(f).sumhoursotpusk.add(arraylistSort.get(i).sumhoursotpusk) ,
							 workerList.get(f).main_podr_id ,
							 workerList.get(f).podr_nazv_main ,
							 workerList.get(f).shortname ));
			   }

			}

		    if (!ixistTabnFromSortList) {
				 arraylistSort.add(new WorkerListFromKadryShortCompare(
				 workerList.get(f).tabn ,
				 workerList.get(f).podr_nazv ,
				 workerList.get(f).podr_id ,
				 workerList.get(f).fio,
				 workerList.get(f).post_fin,
				 workerList.get(f).norma_vrem_days,
				 workerList.get(f).norma_vrem_hours,
				 workerList.get(f).norma_vrem_hours_without_hours,
				 workerList.get(f).sumhoursotpusk ,
				 workerList.get(f).main_podr_id ,
				 workerList.get(f).podr_nazv_main ,
				 workerList.get(f).shortname));
		    }
	 }

	 Collections.sort(arraylistSort);

	 for(WorkerListFromKadryShortCompare str: arraylistSort){
			System.out.println(str);
	   }

	 for (int s = 0; s < arraylistSort.size(); s++) {
		HashMap hashMap = new HashMap();

     	hashMap.put(PremiumsWorkerDS.TABN, arraylistSort.get(s).tabn);
     	hashMap.put(PremiumsWorkerDS.PODR_NAZV, arraylistSort.get(s).podr_nazv);
     	hashMap.put(PremiumsWorkerDS.PODR_ID, arraylistSort.get(s).podr_id);
     	hashMap.put(PremiumsWorkerDS.FIO, arraylistSort.get(s).fio);
     	hashMap.put(PremiumsWorkerDS.POST_FIN, arraylistSort.get(s).post_fin);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_DAYS, arraylistSort.get(s).norma_vrem_days);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_HOURS, arraylistSort.get(s).norma_vrem_hours);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_HOURS_WITHOUT_HOURS, arraylistSort.get(s).norma_vrem_hours_without_hours);
		hashMap.put(PremiumsWorkerDS.SUMHOURSOTPUSK , arraylistSort.get(s).sumhoursotpusk);
		hashMap.put(PremiumsWorkerDS.MAIN_PODR_ID , arraylistSort.get(s).main_podr_id);
		hashMap.put(PremiumsWorkerDS.PODR_NAZV_MAIN , arraylistSort.get(s).podr_nazv_main);

        rows.add(hashMap);
	 }


    if (rows.isEmpty()) {
    	return null;
    }
    else {
        return new PremiumsWorkerDS(rows.toArray());
    }


    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (DatasourceConnectException e) {
    	throw new ReportSystemException(e);
	} catch (PersistenceException e) {
    	throw new ReportSystemException(e);
	} finally {
//        try {
//            if (resultSet != null) resultSet.close();
//            } catch (SQLException e) {
//        }
//        try {
//            if (statement != null) statement.close();
//        } catch (SQLException e) {
//        }
//
//        try {
//            if (resultSetNet != null) resultSetNet.close();
//            } catch (SQLException e) {
//        }
//        try {
//            if (statementNet != null) statementNet.close();
//        } catch (SQLException e) {
//        }


    }
}

public JRDataSource getWorkerForDodatok3(int rencode , String pdate1 , String pdate2 , int kindWorker  , int planKind )
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

PreparedStatement statement = null;
ResultSet  resultSet = null;

PreparedStatement statementNet = null;
ResultSet  resultSetNet = null;
boolean isNewConn = false;

System.out.print("Start getPremiumsWorker  ");

try {
	// по подразделению выберем бригады с нпз

	//FINWorkerShortList.

	ArrayList rows  = new ArrayList();

	String querySelWorker = "";

	String querySelWorkHours = "";

	String depCodes  = this.getDepartmentIdDownFromKadryIntString(rencode);

	if(depCodes.equals("")){
		  throw new EnergyproSystemException("Подразделение не определено!!!");
	}

	querySelWorker = "select /*distinct fw.tabnumber*/ string_agg(distinct ''''||fw.tabnumber||''''::varchar,',')  from finworker fw , enhumenitem hi , enplanwork p " +
					" where fw.code = hi.finworkercode  " +
					" and hi.planrefcode = p.code  " +
					" and p.kindcode = " + planKind +
					" and p.datestart between to_date('"+pdate1+"','dd.mm.yyyy') and to_date('"+pdate2+"','dd.mm.yyyy') " +
					" and fw.kindrefcode =  "  + kindWorker +
					" and coalesce(hi.countfact,0) > 0  " +
					" and fw.departmentcode::integer in ("+depCodes+") "
					//+ new String(kindWorker == 2 ? " and upper(fw.positionname) like upper('%монтер%лічильників%') " : " ")
					//+ /*tezzzt */ " and fw.tabnumber = 50861 "
					;

	if (finConnection == null || finConnection.isClosed()){

        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

	 if (netConnection == null || netConnection.isClosed() ){
     	netConnection =  getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
     }

	 statement = netConnection.prepareStatement(querySelWorker);
	 resultSet = statement.executeQuery();
	 String   tabnStr = "";
	 if (resultSet.next())
       tabnStr = resultSet.getString(1);


	 workerListFromKadryShortList workerList = this.getWorkerListFromKadry(pdate1, pdate2 , rencode ,tabnStr , kindWorker, false );

	 ArrayList<WorkerListFromKadryShortCompare> arraylistSort = new ArrayList<WorkerListFromKadryShortCompare>();

	 for (int f=0; f < workerList.totalCount; f++){

		 boolean ixistTabnFromSortList = false;
		 for (int i = 0; i < arraylistSort.size(); i++) {

			   int finWorkerList = workerList.get(f).tabn;
			   int finWorkerListSort =  arraylistSort.get(i).tabn;

			   if (finWorkerList == finWorkerListSort)  {
				   ixistTabnFromSortList = true;
				   arraylistSort.set(i, new WorkerListFromKadryShortCompare(
							 workerList.get(f).tabn ,
							 workerList.get(f).podr_nazv ,
							 workerList.get(f).podr_id ,
							 workerList.get(f).fio,
							 workerList.get(f).post_fin,
							 workerList.get(f).norma_vrem_days + arraylistSort.get(i).norma_vrem_days,
							 workerList.get(f).norma_vrem_hours.add(arraylistSort.get(i).norma_vrem_hours),
							 workerList.get(f).norma_vrem_hours_without_hours.add(arraylistSort.get(i).norma_vrem_hours_without_hours),
							 workerList.get(f).sumhoursotpusk.add(arraylistSort.get(i).sumhoursotpusk) ,
							 workerList.get(f).main_podr_id ,
							 workerList.get(f).podr_nazv_main,
							 workerList.get(f).shortname
							 ));
			   }

			}

		    if (!ixistTabnFromSortList) {
				 arraylistSort.add(new WorkerListFromKadryShortCompare(
				 workerList.get(f).tabn ,
				 workerList.get(f).podr_nazv ,
				 workerList.get(f).podr_id ,
				 workerList.get(f).fio,
				 workerList.get(f).post_fin,
				 workerList.get(f).norma_vrem_days,
				 workerList.get(f).norma_vrem_hours,
				 workerList.get(f).norma_vrem_hours_without_hours,
				 workerList.get(f).sumhoursotpusk ,
				 workerList.get(f).main_podr_id ,
				 workerList.get(f).podr_nazv_main,
				 workerList.get(f).shortname));
		    }
	 }

	 Collections.sort(arraylistSort);

	 for(WorkerListFromKadryShortCompare str: arraylistSort){
			System.out.println(str);
	   }

	 for (int s = 0; s < arraylistSort.size(); s++) {
		HashMap hashMap = new HashMap();

     	hashMap.put(PremiumsWorkerDS.TABN, arraylistSort.get(s).tabn);
     	hashMap.put(PremiumsWorkerDS.PODR_NAZV, arraylistSort.get(s).podr_nazv);
     	hashMap.put(PremiumsWorkerDS.PODR_ID, arraylistSort.get(s).podr_id);
     	hashMap.put(PremiumsWorkerDS.FIO, arraylistSort.get(s).fio);
     	hashMap.put(PremiumsWorkerDS.POST_FIN, arraylistSort.get(s).post_fin);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_DAYS, arraylistSort.get(s).norma_vrem_days);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_HOURS, arraylistSort.get(s).norma_vrem_hours);
		hashMap.put(PremiumsWorkerDS.NORMA_VREM_HOURS_WITHOUT_HOURS, arraylistSort.get(s).norma_vrem_hours_without_hours);
		hashMap.put(PremiumsWorkerDS.SUMHOURSOTPUSK , arraylistSort.get(s).sumhoursotpusk);
		hashMap.put(PremiumsWorkerDS.MAIN_PODR_ID , arraylistSort.get(s).main_podr_id);
		hashMap.put(PremiumsWorkerDS.PODR_NAZV_MAIN , arraylistSort.get(s).podr_nazv_main);
		hashMap.put(PremiumsWorkerDS.SHORTNAME , arraylistSort.get(s).shortname);

        rows.add(hashMap);
	 }


    if (rows.isEmpty()) {
    	return null;
    }
    else {
        return new PremiumsWorkerDS(rows.toArray());
    }


    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (DatasourceConnectException e) {
    	throw new ReportSystemException(e);
	} catch (PersistenceException e) {
    	throw new ReportSystemException(e);
	} finally {
//        try {
//            if (resultSet != null) resultSet.close();
//            } catch (SQLException e) {
//        }
//        try {
//            if (statement != null) statement.close();
//        } catch (SQLException e) {
//        }
//
//        try {
//            if (resultSetNet != null) resultSetNet.close();
//            } catch (SQLException e) {
//        }
//        try {
//            if (statementNet != null) statementNet.close();
//        } catch (SQLException e) {
//        }


    }
}



public String initialzeKadry() throws DatasourceConnectException {

	CallableStatement stmt = null;
	PreparedStatement statement = null;
    String selectStr = "";

    if (finConnection == null){
        //System.out.println("finConnection is null");
        //return new BigDecimal(Integer.MIN_VALUE);
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    }

    try {
    	String query =
    			" begin " +
    			" kadry.Pkg_Dostup.INITIALIZE(sysdate); " +
    			" kadry.Pkg_Podr_Utils.Init_Podr_Buf; " +
    			" kadry.Pkg_Doljnost_Utils.Init_Param_Calc_Okl_Fond; " +
    			" end; ";

    	stmt = finConnection.prepareCall(query);
    	stmt.execute();

    } catch (SQLException e) {
        System.out.println(e.getMessage() + "\nstatement - " + selectStr);
        try {
			EnergyproPersistenceExceptionAnalyzer.analyser
			        .throwPersistenceException(e);
		} catch (PersistenceException e1) {
			throw new SystemException(e.getMessage(), e);
		}
    } finally {
        try {
            if (statement != null)
                statement.close();
            if (stmt != null)
            	stmt.close();
        } catch (SQLException e) {
        }
        if (finConnection != null) {
            try {
            	finConnection.close();
            } catch (SQLException e) {
            }
        }
    }
	return "";


}


public class WorkerListFromKadryShortCompare implements Comparable  {

    public int tabn = Integer.MIN_VALUE;
    public String podr_nazv;
    public int podr_id = Integer.MIN_VALUE;
    public String fio;
    public String post_fin;
    public int norma_vrem_days = Integer.MIN_VALUE;
    public BigDecimal norma_vrem_hours;
    public BigDecimal norma_vrem_hours_without_hours;
    public BigDecimal sumhoursotpusk;
    public int main_podr_id = Integer.MIN_VALUE;
    public String podr_nazv_main;
    public String shortname;


    public WorkerListFromKadryShortCompare(int tabn, String podr_nazv, int podr_id , String fio ,
    		String post_fin , int norma_vrem_days , BigDecimal norma_vrem_hours , BigDecimal  norma_vrem_hours_without_hours ,
    		BigDecimal sumhoursotpusk  , int main_podr_id , String podr_nazv_main , String shortname) {
        this.tabn = tabn;
        this.podr_nazv = podr_nazv;
        this.podr_id = podr_id;
        this.fio= fio;
        this.post_fin = post_fin;
        this.norma_vrem_days = norma_vrem_days;
        this.norma_vrem_hours = norma_vrem_hours ;
        this.norma_vrem_hours_without_hours = norma_vrem_hours_without_hours ;
        this.sumhoursotpusk = sumhoursotpusk ;
        this.main_podr_id = main_podr_id;
        this.podr_nazv_main = podr_nazv_main ;
        this.shortname = shortname ;

    }

    public void setTabn(int aValue){
       tabn = aValue;
    }

    public int getTabn(){
       return tabn;
    }
    public void setPodr_nazv(String aValue){
       podr_nazv = aValue;
    }

    public String getPodr_nazv(){
       return podr_nazv;
    }
    public void setPodr_id(int aValue){
       podr_id = aValue;
    }

    public int getPodr_id(){
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
    public void setNorma_vrem_days(int aValue){
       norma_vrem_days = aValue;
    }

    public int getNorma_vrem_days(){
       return norma_vrem_days;
    }
    public void setNorma_vrem_hours(BigDecimal aValue){
       norma_vrem_hours = aValue;
    }

    public BigDecimal getNorma_vrem_hours(){
       return norma_vrem_hours;
    }
    public void setNorma_vrem_hours_without_hours(BigDecimal aValue){
       norma_vrem_hours_without_hours = aValue;
    }

    public BigDecimal getNorma_vrem_hours_without_hours(){
       return norma_vrem_hours_without_hours;
    }
    public void setSumhoursotpusk(BigDecimal aValue){
       sumhoursotpusk = aValue;
    }

    public BigDecimal getSumhoursotpusk(){
       return sumhoursotpusk;
    }
    public void setMain_podr_id(int aValue){
       main_podr_id = aValue;
    }

    public int getMain_podr_id(){
       return main_podr_id;
    }
    public void setPodr_nazv_main(String aValue){
       podr_nazv_main = aValue;
    }

    public String getPodr_nazv_main(){
       return podr_nazv_main;
    }

    public void setshortname(String aValue){
        shortname = aValue;
     }

     public String getshortname(){
        return shortname;
     }

    @Override
    public int compareTo(Object compareWork) {

		int comparepodr=((WorkerListFromKadryShortCompare)compareWork).getPodr_id();
        /* For Ascending order*/
        return this.podr_id-comparepodr;
	}

	@Override
	public String toString() {
        return "[ tabn=" + tabn+ ", podr_nazv =" + podr_nazv +
        ",podr_id =" +podr_id +
        ",fio=" + fio +
        ",post_fin =" + post_fin+
        ",norma_vrem_days =" + norma_vrem_days+
        ",norma_vrem_hours =" + norma_vrem_hours +
        ",norma_vrem_hours_without_hours =" + norma_vrem_hours_without_hours +
        ",sumhoursotpusk =" + sumhoursotpusk +
        ",main_podr_id =" + main_podr_id+
        ",podr_nazv_main =" + podr_nazv_main +
        ",shortname =" + shortname + "]";
    }





//	@Override
//	public int compareTo(Object o) {
//		// Auto-generated method stub
//		return 0;
//	}



}

/****
 * @param forPremija - если для премии то не показывать бригады по списку
 *
 * */
public workerListFromKadryShortList getWorkerListFromKadry(String pdate1 , String pdate2 , int rencode , String tabnStr , int kindWorker , boolean forPremija  ) throws PersistenceException
{

     Calendar calendar = Calendar.getInstance();
//      calendar.setTime(dateIn);
//      calendar.add(Calendar.DATE,-1);


      Date dateIn_1 = com.ksoe.energynet.util.Tools.clearTimeOfDate(calendar.getTime());


 workerListFromKadryShortList result = new workerListFromKadryShortList();
 workerListFromKadryShort anObject;
 result.list = new Vector();


 String     selectStr;

 if (finConnection == null){
     try {
		finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
	} catch (DatasourceConnectException e) {
    	throw new ReportSystemException(e);
	}
 }

 PreparedStatement statementInit = null;
 PreparedStatement statement = null;
 ResultSet  set = null;


  selectStr =
		  " select tabn, \n" +
				  " Case when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (93,98) then podr_nazv||'  РЗйА' \n" +
				  "      when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (97,103)  then podr_nazv||'  Ізоляція' \n" +
				  "      when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (102,104) then podr_nazv||' СДТУ' \n" +
				  "      else podr_nazv end podr_nazv , \n" +
				  "        podr_id, \n" +
				  "        fio , \n" +
				  "        post_fin , \n" +
				  "        norma_vrem_days, \n" +
				  "        norma_vrem_hours, \n" +
				  "        norma_vrem_hours_without_hours , \n" +
				  "        norma_vrem_hours - norma_vrem_hours_without_hours as  sumhoursotpusk , \n" +
				  "        Main_Podr_Id , \n" +
				  "        podr_nazv_main , " +
				  "        date_finish  , prof_kategory_id \n" +
				  " , Case when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (93,98) then 'RZA' \n" +
				  "        when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (97,103)  then 'IZ' \n" +
				  "        when podr_nazv = 'ТЕХНІЧНА ЧАСТИНА' and prof_kategory_id in (102,104) then 'DTU' \n" +
				  "       else  'SHTAT' end shortname  \n" + // 14
				  "  \n" +
				  "      from ( \n" +
				  " SELECT a.TabN as tabn, \n" +
				  "        a.podr_nazv , \n" +
				  "        a.podr_id, \n" +
				  "        a.fio , \n" +
				  "        a.post_nazv as post_fin , \n" +
				  //"        k.nazv, \n" +
				  "        nvl(trud.get_days_nvz(to_date('"+pdate1+"','dd.MM.yyyy'), to_date('"+pdate2+"','dd.MM.yyyy') , a.Date_Start, a.Date_Finish, 'D', tabn),0) norma_vrem_days, \n" +
				  "        nvl(trud.get_days_nvz(to_date('"+pdate1+"','dd.MM.yyyy'), to_date('"+pdate2+"','dd.MM.yyyy') , a.Date_Start, a.Date_Finish, 'H', tabn),0) norma_vrem_hours, \n" +
				  "        nvl(trud.get_days_nvz(to_date('"+pdate1+"','dd.MM.yyyy'), to_date('"+pdate2+"','dd.MM.yyyy') , a.Date_Start, a.Date_Finish, 'O', tabn),0) norma_vrem_hours_without_hours , \n" +
				  "        99999 as otpusk , " + // tezzzt
				  "        Main_Podr_Id , \n" +
				  "        podr_nazv_main , " +
				  "        date_finish , k.id as prof_kategory_id \n" +
				  "   from \n" +
				  " ( \n" +
				  " SELECT /*+ RULE */ \n" +
				  "       kr.TabN, \n" +
				  "       fio.F||' '||fio.I||' '||fio.O FIO, \n" +
				  "       -- назначение \n" +
				  "       n.Date_Start, \n" +
				  "       n.Date_Finish, \n" +
				  "       n.Doljnost_Id, \n" +
				  "       pd.Podr_Id, \n" +
				  "       pd.Podr_Nazv, \n" +
				  "       ps.Post_Id, \n" +
				  "       DECODE(nd.IO,1,'в.о. '||ps.Post_Nazv, ps.Post_Nazv) Post_Nazv, \n" +
				  "       -- \n" +
				  "       pd.Ceh_Id, \n" +
				  "       pd.Ceh_Nazv, \n" +
				  "       -- \n" +
				  "       ps.kategory_id, \n" +
				  "       -- \n" +
				  "       to_char(case when n.date_start >= to_date('"+ pdate1 +"','dd.MM.yyyy') then to_date(n.date_start,'dd.MM.yyyy') else to_date('"+ pdate1 +"','dd.MM.yyyy') end ,'dd.MM.yyyy') as DateS , \n" +
     			  "       to_char(case when n.date_finish <= to_date('"+ pdate2 +"','dd.MM.yyyy') then to_date(n.date_finish,'dd.MM.yyyy') else to_date('"+ pdate2 +"','dd.MM.yyyy') end ,'dd.MM.yyyy') as DatePo , \n" +
	"       pd.Main_Podr_Id , \n" +
				  "       /*(select p.nazv from kadry.podr_day p where p.podr_id = pd.Main_Podr_Id )*/ '' as podr_nazv_main \n" +
				  " FROM \n" +
				  "   kadry.Kategory        k, \n" +
				  "   kadry.Doljnost      d, \n" +
				  "   kadry.Doljnost_Day  dd, \n" +
				  "   (SELECT p.Id             Podr_Id, \n" +
				  "           p.Date_Start     Podr_Date_Start, \n" +
				  "           p.Date_Finish    Podr_Date_Finish, \n" +
				  "           pd.ROWID         Podr_Day_Row_Id, \n" +
				  "           pd.Podr_Id       Podr_Day_Podr_Id, \n" +
				  "           pd.Date_Start    Podr_Day_Date_Start, \n" +
				  "           pd.Main_Podr_Id  Main_Podr_Id, \n" +
				  "           pd.Podr_Vid_Id   Podr_Vid_Id, \n" +
				  "           pd.Nazv          Podr_Nazv, \n" +
				  "           pd.Sokr_Nazv     Podr_Sokr_Nazv, \n" +
				  "           pd.Kod           Podr_Kod, \n" +
				  "           pd.Prom_Id       Podr_Prom_Id, \n" +
				  "           pd.Pos           Podr_Pos, \n" +
				  "           pd.Vne_Shtata    Vne_Shtata, \n" +
				  "           ceh.ROWID        Ceh_Row_Id, \n" +
				  "           ceh.Podr_Id      Ceh_Id, \n" +
				  "           ceh.Date_Start   Ceh_Date_Start, \n" +
				  "           ceh.Main_Podr_Id Ceh_Main_Podr_Id, \n" +
				  "           ceh.Podr_Vid_Id  Ceh_Vid_Id, \n" +
				  "           ceh.Nazv         Ceh_Nazv, \n" +
				  "           ceh.Sokr_Nazv    Ceh_Sokr_Nazv, \n" +
				  "           ceh.Kod          Ceh_Kod, \n" +
				  "           ceh.Prom_Id      Ceh_Prom_Id, \n" +
				  "           ceh.Pos          Ceh_Pos \n" +
				  "      FROM kadry.Podr_Day  ceh, \n" +
				  "           kadry.Podr_Day  pd, \n" +
				  "           kadry.Podr      p \n" +
				  "     WHERE pd.Podr_Id(+) = p.Id \n" +
				  "       AND (pd.Podr_Id IS NULL \n" +
				  "         OR pd.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(pd1.Date_Start,'J')||ROWID),8) \n" +
				  "                           FROM kadry.Podr_Day pd1 \n" +
				  "                          WHERE pd1.Podr_Id=pd.Podr_Id \n" +
				  "                            AND pd1.Date_Start<=sysdate) \n" +
				  "           ) \n" +
				  "       AND ceh.Podr_Id = kadry.pkg_podr_utils.Get_Struct_Ed_Id_Buf(p.Id, sysdate) \n" +
				  "       AND ceh.ROWID = (SELECT SUBSTR(MAX(TO_CHAR(ceh1.date_start,'J')||ROWID),8) \n" +
				  "                           FROM kadry.Podr_Day ceh1 \n" +
				  "                          WHERE ceh1.Podr_Id=ceh.Podr_Id \n" +
				  "                            AND ceh1.Date_Start<=sysdate) \n" +
				  "  \n" +
				  "   ) pd, \n" +
				  "   kadry.v_Post       ps, \n" +
				  "   kadry.v_Post       ps1, \n" +
				  "  \n" +
				  " -- предыдущее назначение \n" +
				  "   kadry.Perevod_Vid  pv, \n" +
				  "   kadry.perevod_usl  pu, \n" +
				  "   kadry.Osnova       os, \n" +
				  "   kadry.Nazn         n2, \n" +
				  "   kadry.Doljnost     d2, \n" +
				  "   kadry.Post         ps2, \n" +
				  "   kadry.Razr         rz2, \n" +
				  "   kadry.Prof         pf2, \n" +
				  " --  Podr_Day     pd2, \n" +
				  " -- следующее назначение \n" +
				  "   kadry.Perevod_Vid  pv3, \n" +
				  "   kadry.perevod_usl  pu3, \n" +
				  "   kadry.Osnova       os3, \n" +
				  "   kadry.Nazn         n3, \n" +
				  "   kadry.Doljnost     d3, \n" +
				  "   kadry.Post         ps3, \n" +
				  "   kadry.Razr         rz3, \n" +
				  "   kadry.Prof         pf3, \n" +
				  "   kadry.FIO          fio, \n" +
				  "   kadry.Karta        kr, \n" +
				  "   kadry.Nazn_Vid     nv, \n" +
				  "   kadry.Nazn_Day     nd, \n" +
				  "   kadry.Nazn         n \n" +
				  " -- \n" +
				  " WHERE (nd.Nazn_Id = n.Id \n" +
				  "   -- Выбор параметров на дату среза \n" +
				  "   AND nd.Date_Start <= GREATEST(n.Date_Start, sysdate) \n" +
				  "   AND NOT EXISTS \n" +
				  "      (SELECT NULL FROM kadry.s_Nazn_Day nd2 \n" +
				  "        WHERE nd2.Nazn_Id = nd.Nazn_Id \n" +
				  "          AND nd2.Date_Start <= GREATEST(n.Date_Start, sysdate) \n" +
				  "          AND nd2.Date_Start > nd.Date_Start) \n" +
				  "   -- \n" +
				  "   AND n.Doljnost_Id = d.Id \n" +
				  "   AND pd.Podr_Id = d.Podr_Id \n" +
				  "  \n" +
				  "   AND ps.Post_Id = NVL(nd.Post_Id, d.Post_Id) \n" +
				  "   AND ps1.Post_Id = d.Post_Id \n" +
				  "   AND k.Id = d.Kategory_Id \n" +
				  "   -- \n" +
				  "   AND dd.Doljnost_Id(+) = d.Id \n" +
				  "   -- Выбор параметров должности на дату \n" +
				  "   AND (dd.Doljnost_Id IS NULL \n" +
				  "     OR (dd.Date_Start <= LEAST(n.Date_Finish, sysdate) \n" +
				  " --    OR (dd.Date_Start <= n.Date_Finish \n" +
				  "       AND NOT EXISTS (SELECT NULL FROM kadry.Doljnost_Day dd2 \n" +
				  "                       WHERE dd2.Doljnost_Id = dd.Doljnost_Id \n" +
				  "                         AND dd2.Date_Start <= LEAST(n.Date_Finish, sysdate) \n" +
				  " --                        AND dd2.Date_Start <= n.Date_Finish \n" +
				  "                         AND dd2.Date_Start > dd.Date_Start))) \n" +
				  " -- предыдущее назначение \n" +
				  "   AND pv.Id(+) = n.Perevod_Vid_Id \n" +
				  "   AND pu.id(+) = n.Perevod_Usl_id \n" +
				  "   AND os.Id(+) = n.Perevod_Osnova_Id \n" +
				  "   AND n.Perevod_Nazn_Id = n2.Id(+) \n" +
				  "   AND n2.Doljnost_Id = d2.Id(+) \n" +
				  "   AND ps2.Id(+) = d2.Post_Id \n" +
				  "   AND pf2.Id(+) = ps2.Prof_Id \n" +
				  "   AND rz2.Id(+) = ps2.Razr_Id \n" +
				  " -- сдедующее назначение \n" +
				  "   AND n.Id = n3.Perevod_Nazn_Id(+) \n" +
				  "   AND pu3.id(+) = n3.perevod_Usl_id \n" +
				  "   AND pv3.Id(+) = n3.Perevod_Vid_Id \n" +
				  "   AND os3.Id(+) = n3.Perevod_Osnova_Id \n" +
				  "   AND d3.Id(+)  = n3.Doljnost_Id \n" +
				  "   AND ps3.Id(+) = d3.Post_Id \n" +
				  "   AND pf3.Id(+) = ps3.Prof_Id \n" +
				  "   AND rz3.Id(+) = ps3.Razr_Id \n" +
				  " -- \n" +
				  "  \n" +
				  "   AND n.Karta_Id     = kr.Id \n" +
				  "   AND fio.Id         = kr.FIO_Id \n" +
				  "   AND nd.Nazn_Vid_Id = nv.Id(+) \n" +
				  " ) \n" +
				  "   AND kr.tabn in  ("+tabnStr+") \n" +
				  // для премии не показываем уволенных
				  new String( forPremija == true ? " and nvl(kr.po,TO_DATE('01.01.3000', 'DD.MM.YYYY') ) > to_date('"+pdate2+"','dd.MM.yyyy') " : " " ) +
				  " ------------------------------ \n" +
				  " ORDER BY kr.TabN, n.Date_Start desc \n" +
				  " ) a, kadry.kategory k \n" +
				  " where a.kategory_id = k.id \n" +
				  "   and a.date_start <= to_date('"+pdate2+"','dd.MM.yyyy') \n" +
				  "   and a.date_finish >= to_date('"+pdate1+"','dd.MM.yyyy') \n" +
				  " -- Статика условий по категориям НВЗ, НВЗ_Е \n" +
				  "   and ((k.id in (84, 99) and ("+kindWorker+" = 2)) or \n" +
				  "        (k.id in (89, 93, 97, 98,102,103,104) and ("+kindWorker+" = 1))) \n" +
				  "   and ceh_id = " + rencode + " \n" +
				  new String( forPremija == true ?
				  "   and podr_id not in (116 , 117 , 150 , 152 , 174 , 175 , 199 , 200 , 218 , 220 , 247 , 248 , 263 , 290 , 293 , 771 , 318 , 761 , 763 , 357 , 358 , 430 , 431 , 781 , 782 ,  456 , 457 , 896 , 507 ,  512 , 534 , 535 , 790 , 791 , 564 , 565 , 579 , 580 , 603 , 800 , 901) \n "
					: " "	  ) +
				  " order by date_finish \n"
				  +
				  " ) order by tabn , date_finish  \n" +
				  "  \n"
;
//selectStr = " select trud.get_days_nvz(to_date('27.05.2015','dd.mm.yyyy'), to_date('31.05.2015','dd.mm.yyyy'), 'H', '50861') norma_vrem_days from dual   ";

 try
  {
   statement = finConnection.prepareStatement(selectStr);

   String init =
   "   BEGIN " +
   " kadry.Pkg_Dostup.INITIALIZE(sysdate); " +
   " kadry.Pkg_Podr_Utils.Init_Podr_Buf; " +
   " kadry.Pkg_Doljnost_Utils.Init_Param_Calc_Okl_Fond; " +
 " END; ";
   statementInit = finConnection.prepareStatement(init);
   statementInit.executeQuery();

   set = statement.executeQuery();
   int i;
   for(i = 0;set.next();i++)    {


     anObject = new workerListFromKadryShort();

     //anObject.norma_vrem_hours = set.getBigDecimal(1); // tezzzt

     anObject.tabn = set.getInt(1);
     if ( set.wasNull() )
         anObject.tabn = Integer.MIN_VALUE;

     anObject.podr_nazv = set.getString(2);

     anObject.podr_id = set.getInt(3);
     if ( set.wasNull() )
         anObject.podr_id = Integer.MIN_VALUE;

     anObject.fio = set.getString(4);
     anObject.post_fin = set.getString(5);

     anObject.norma_vrem_days = set.getInt(6);
          if ( set.wasNull() )
         anObject.norma_vrem_days = Integer.MIN_VALUE;

     anObject.norma_vrem_hours = set.getBigDecimal(7);
          if(anObject.norma_vrem_hours != null)
         anObject.norma_vrem_hours = anObject.norma_vrem_hours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);


     anObject.norma_vrem_hours_without_hours = set.getBigDecimal(8);
     if(anObject.norma_vrem_hours_without_hours != null)
         anObject.norma_vrem_hours_without_hours = anObject.norma_vrem_hours_without_hours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

     anObject.sumhoursotpusk = set.getBigDecimal(9);
     if(anObject.sumhoursotpusk != null)
         anObject.sumhoursotpusk = anObject.sumhoursotpusk.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

     anObject.main_podr_id = set.getInt(10);
     if ( set.wasNull() )
         anObject.main_podr_id = Integer.MIN_VALUE;

     anObject.podr_nazv_main = set.getString(11);

     anObject.shortname = set.getString(14);


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
	try {
		if (set != null)
			set.close();
	} catch (SQLException e) {
	}
	try {
		if (statementInit != null)
			statementInit.close();
	} catch (SQLException e) {
	}
	try {
		if (statement != null)
			statement.close();
	} catch (SQLException e) {
	}

	try {
		if ((finConnection != null && !finConnection.isClosed())) {
			finConnection.close();
		}
	} catch (SQLException e) {
	}
 }
}


public int getDaysWorked (String pdate1 , String pdate2 , String tabnStr , boolean forPremija  ) throws PersistenceException {
	int days=0;

	Connection finConnection_ = null;
    Calendar calendar = Calendar.getInstance();
    Date dateIn_1 = com.ksoe.energynet.util.Tools.clearTimeOfDate(calendar.getTime());
	String selectStr;

	 try {
		 finConnection_ = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
	} catch (DatasourceConnectException e) {
	  throw new ReportSystemException(e);
	}

	 PreparedStatement statement = null;
	 ResultSet set = null;

	selectStr =" select trud.get_fakt_vrem("+tabnStr+",to_date('"+pdate1+"','dd.MM.yyyy')) from dual ";


	try	{

		statement = finConnection_.prepareStatement(selectStr);
		set = statement.executeQuery();

    if (set.next()){
    	days = set.getInt(1);
    	if (set.wasNull())
    		days=0;
    }
       return days;
     }
  catch(SQLException e)
   {
    System.out.println(e.getMessage()+"\nstatement - "+selectStr);
    EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
    return days;
   }
  finally
   {
    try {if (set != null) set.close();}                      catch (SQLException e) {}
    try {if (statement != null) statement.close();}          catch (SQLException e) {}

    try {
        if  ((finConnection_ != null && !finConnection_.isClosed())){
        	finConnection_.close();
        }
    } catch (SQLException e) {
    }
   }
 }

/***
 * SUPP-38148 - для ведомости вдачи спецодежды если на работника тогда запросик меняется
 * **/
public String getKodZatratByTabn(String tab_num ) throws PersistenceException
{


    boolean isDebug = false;

    if (isDebug)
        System.out.println("start getKodZatratByTabn:" + new Date());



    String sql = "select k.tabn, b.balans \n"+
				  " from zarpherson.t_kadry k, zarpherson.kadry_day kdv, zarpherson.balans b \n"+
				 " where k.idkadry = kdv.idkadry  \n"+
				  " and k.tabn = /*:p_tabn*/ "+ tab_num +" \n"+
				  " and kdv.balans_id = b.id  \n"+
				  " and kdv.rowid in  \n"+
				  " (select zarpherson.GET_KADRY_ROWID((select idkadry from zarpherson.t_kadry where tabn = /*:p_tabn*/"+tab_num+"), \n"+
				  "  sysdate, sysdate) from dual)  \n"
				    ;
    if (isDebug){
        System.out.println("sql : " + sql);
        System.out.println("param : " + tab_num);
    }


    PreparedStatement statement = null;
    ResultSet  resultSet = null;
    boolean isNewConn = false;

    String out = new String("");
    try {
        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }


        statement = finConnection.prepareStatement(sql);

        resultSet = statement.executeQuery();

        while(resultSet.next())
        {
        out =  resultSet.getString(2);
        }

        if (isDebug)
            System.out.println("return :" + out);

        if(out.equals(""))
            out="          ";

        return out;
    }

        catch (SQLException e) {
            System.out.println(    e.getMessage()+ " erorrr 101 - getKodZatratByTabn");
            throw new PersistenceException(e.getMessage());
    }
        catch (Exception e) {
            System.out.println(    e.getMessage()+ " error 101 - getKodZatratByTabn");
            throw new PersistenceException("Error in method getKodZatratByTabn()",e);
    }
        finally {

        try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {
            if ((finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {
        }
    }

}


/**
 * Возвращает остаток материалов по входящим МОЛам , на дату , по айдишнику материала

 *
 * @param molCode
 * @param date
 * @param ids
 * @param measCode
 * @param isAll
 * @param rowNum
 * @param colNum
 * @param rowCount
 * @return
 * @throws PersistenceException
 * @throws SQLException
 */
public BigDecimal getMaterialOperativeRestByIDs(String molCode , String date, String ids, Integer measCode, Integer isAll, Integer rowNum, Integer colNum, Integer rowCount) throws PersistenceException, SQLException
{
   boolean isDebug = true;

   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
  	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
  	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


   if (isDebug)
       System.out.println("start getMaterialOperativeRestByIDs: " + new Date());


   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }


       String sql = "";
		// не совсем корректный запрос для выбора остатков

		if (isAll.compareTo(1) == 0) {
			   sql = " SELECT    nvl(sum(nvl(" +
			   		 " ( case when " + measCode + " = m.mesure_unit_id then количество " +
					 " when " + measCode + " = 166 and m.mesure_unit_id = 168 then количество*1000 " +
					 " when " + measCode + " = 168 and m.mesure_unit_id = 166 then количество/1000 " +
					 " when " + measCode + " = 6 and m.mesure_unit_id = 8 then количество*1000 " +
					 " when " + measCode + " = 8 and m.mesure_unit_id = 6 then количество/1000 end" +
			   		  "   ),0)),0) as q_end \n" +
					 "    FROM umc_dba.v_revert_str, umc_dba.tmatherial m, umc_dba.rest_purpose_type t, umc_dba.rest_purpose r \n" +
					 "    WHERE дата_документа <= TO_DATE('31.12.2999', 'dd.mm.yyyy') and \n" + // остатки будем брать на текущий момент
					 // остатки для всех молов
				     " ( код_поставщика in (1823, 1888, 1889) or \n " +
					 "   substr(код_поставщика, 1, 2) = '76' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '70' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '38' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '39' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '72' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '75' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '80' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '53' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '54' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '78' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '41' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '52' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '51' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '43' or  \n" +
					 "   substr(код_поставщика, 1, 2) = '79' ) \n" +

					 "   and код_материала = m.id " +
					 "          and   код_материала in  (" + ids + ") \n" +
	                 " and umc_dba.v_revert_str.rest_purpose_id = r.id " +
	                 " and umc_dba.v_revert_str.\"БАЛ_СЧЕТ\" not in (2012,2010,2017,2030,2037,2035,2070,2073,2074,2077) " +
	                 " and r.rest_purpose_type_id = t.id "  +
					 " and t.id in (" + RQConsts.REST_PURPOSE_TYPE_ID_OPERATIVE + ", "
					                  + RQConsts.REST_PURPOSE_TYPE_ID_NET_OPERATIVE +  ") \n";
		} else
			   sql =
				 " SELECT    nvl(sum(nvl(( case when " + measCode + " = m.mesure_unit_id then количество " +
					 " when " + measCode + " = 166 and m.mesure_unit_id = 168 then количество*1000 " +
					 " when " + measCode + " = 168 and m.mesure_unit_id = 166 then количество/1000 " +
					 " when " + measCode + " = 6 and m.mesure_unit_id = 8 then количество*1000 " +
					 " when " + measCode + " = 8 and m.mesure_unit_id = 6 then количество/1000 end" +
			   		  "),0)),0) as q_end \n" +
				 "    FROM umc_dba.v_revert_str, umc_dba.tmatherial m, umc_dba.rest_purpose_type t, umc_dba.rest_purpose r \n" +
				 "    WHERE дата_документа <= TO_DATE('31.12.2999', 'dd.mm.yyyy') and \n" + // остатки будем брать на текущий момент

				( (molCode.equals("1823, 1888, 1889")) ? " код_поставщика in (" + molCode + ") and \n " :
				 "          substr(код_поставщика, 1, 2) = '" + molCode + "' and \n") +
				 "          код_материала in  (" + ids + ") \n" +
				 "          and код_материала = m.id " +
                 " and umc_dba.v_revert_str.rest_purpose_id = r.id " +
				 " and umc_dba.v_revert_str.\"БАЛ_СЧЕТ\" not in (2012,2010,2017,2030,2037,2035,2070,2073,2074,2077) " +
                 " and r.rest_purpose_type_id = t.id "  +
				 " and t.id in (" + RQConsts.REST_PURPOSE_TYPE_ID_OPERATIVE + ", "
				                  + RQConsts.REST_PURPOSE_TYPE_ID_NET_OPERATIVE +  ") \n";



       BigDecimal out = new BigDecimal(0);

       statement = finConnection.prepareStatement(sql);


       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       if (isDebug)
           System.out.println("progress : " + rowNum + " of " + rowCount);
       if (isDebug)
           System.out.println("progress : " + ((rowNum-1)*15+colNum) + " of " + rowCount*15);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getMaterialOperativeRestByIDs()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getMaterialOperativeRestByIDs: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}



/**
 * Возвращает остаток материалов по входящим МОЛам , по айдишнику материала на текущую дату

 *
 * @param molCode
 * @param ids
 * @param measCode
 * @return
 * @throws PersistenceException
 * @throws SQLException
 */
public BigDecimal getMaterialOperativeRestByIDsAndMols(String molCode, String ids, Integer measCode) throws PersistenceException, SQLException
{
   boolean isDebug = true;

   PreparedStatement statement = null;
   ResultSet  resultSet = null;
   boolean isNewConn = false;

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
  	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
  	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


   if (isDebug)
       System.out.println("start getMaterialOperativeRestByIDs: " + new Date());


   try {
       if (finConnection == null){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }

       if ( finConnection.isClosed() ){
           finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
           isNewConn = true;
       }


       String sql = "";
		// не совсем корректный запрос для выбора остатков

			   sql =  " SELECT    nvl(sum(nvl(( case when " + measCode + " = m.mesure_unit_id then d.quantity " +
				 " when " + measCode + " = 166 and m.mesure_unit_id = 168 then d.quantity*1000 " +
				 " when " + measCode + " = 168 and m.mesure_unit_id = 166 then d.quantity/1000 " +
				 " when " + measCode + " = 6 and m.mesure_unit_id = 8 then d.quantity*1000 " +
				 " when " + measCode + " = 8 and m.mesure_unit_id = 6 then d.quantity/1000 end" +
		   		  "),0)),0) as q_end \n" +
			     " FROM umc_dba.v_invert_string d, " +
			        "  umc_dba.rest_purpose t, " +
			        "  umc_dba.tmatherial m " +
			    " WHERE "  + molCode +
			         "  AND d.mat_id IN (" + ids + ")" +
			         " AND d.bal_sch IN (select sch||sub_sch from finansy.subschet where sch||sub_sch not in " +
			         " ('2012', '2010', '2017', '2030', '2037', '2035', '2070', '2073', '2074', '2077') and " +
			         "  sch||sub_sch  like ('20%')) " +
			         " AND t.rest_purpose_type_id in (0, 25) " +
			         " and d.rest_purpose_id = t.id " +
			         " and d.mat_id = m.id ";


       BigDecimal out = new BigDecimal(0);

       statement = finConnection.prepareStatement(sql);


       resultSet = statement.executeQuery();

       while(resultSet.next())
       {
           out = resultSet.getBigDecimal(1);
       }

       if (isDebug)
           System.out.println("return : " + out);

       return out;
   }

       catch (SQLException e) {
           throw new PersistenceException(e.getMessage());

   }
       catch (Exception e) {
           throw new PersistenceException("Error in method getMaterialOperativeRestByIDs()", e);

   }
       finally {


           if (isDebug)
               System.out.println("final getMaterialOperativeRestByIDs: " + new Date());

       try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
       try {if (statement != null) statement.close();} catch (SQLException e) {}

       try {
           if ((isNewConn)&&(finConnection != null && !finConnection.isClosed()))
               finConnection.close();
       } catch (SQLException e) {
       }
   }

}




// выборка количества рабочих часов в месяце
public BigDecimal getSummaOtpuskHoursByForThePeriod(String OtpDateStart , String OtpDateFinal , String SelectPeriodStart ,String SelectPeriodFinal ) throws PersistenceException
{
    boolean isDebug = false;
    if (isDebug)
        System.out.println("start getSummaOtpuskHoursByForThePeriod:" + new Date());

    // Date firstDay = getFirstDayInMonth(pdate_srez);
    BigDecimal out = new BigDecimal(0);
    BigDecimal sumHours = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;

    PreparedStatement statement2 = null;
    ResultSet resultSet2 = null;

    boolean isNewConn = false;

    try {
    String sql = " alter session "
            + " set   NLS_TERRITORY=AMERICA "
            + " NLS_LANGUAGE=AMERICAN "
            + " OPTIMIZER_MODE=RULE " ;

    if (finConnection == null){
        finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        isNewConn = true;
    }

    statement = finConnection.prepareStatement(sql);
    resultSet = statement.executeQuery();

    sql = "";
    sql = " select sum(otpr1+otpr2+otpr3+otpr4+otpr5+otpr6+otpr7+otpr8+otpr9+otpr10+otpr11+otpr12+otpr13+otpr14+otpr15+otpr16+otpr17+otpr18+otpr19+otpr20 \n" +
    		"        +otpr21+otpr22+otpr23+otpr24+otpr25+otpr26+otpr27+otpr28+otpr29+otpr30+otpr31) as sumhours from  \n" +
    		"        (  \n" +
    		"        select  \n" +
    		"        case when to_date('01.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday1 else 0 end as otpr1, \n" +
    		"        case when to_date('02.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday2 else 0 end as otpr2, \n" +
    		"        case when to_date('03.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday3 else 0 end as otpr3, \n" +
    		"        case when to_date('04.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday4 else 0 end as otpr4, \n" +
    		"        case when to_date('05.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday5 else 0 end as otpr5, \n" +
    		"        case when to_date('06.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday6 else 0 end as otpr6, \n" +
    		"        case when to_date('07.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday7 else 0 end as otpr7, \n" +
    		"        case when to_date('08.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday8 else 0 end as otpr8, \n" +
    		"        case when to_date('09.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday9 else 0 end as otpr9, \n" +
    		"        case when to_date('10.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday10 else 0 end as otpr10, \n" +
    		"        case when to_date('11.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday11 else 0 end as otpr11, \n" +
    		"        case when to_date('12.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday12 else 0 end as otpr12, \n" +
    		"        case when to_date('13.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday13 else 0 end as otpr13, \n" +
    		"        case when to_date('14.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday14 else 0 end as otpr14, \n" +
    		"        case when to_date('15.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday15 else 0 end as otpr15, \n" +
    		"        case when to_date('16.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday16 else 0 end as otpr16, \n" +
    		"        case when to_date('17.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday17 else 0 end as otpr17, \n" +
    		"        case when to_date('18.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday18 else 0 end as otpr18, \n" +
    		"        case when to_date('19.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday19 else 0 end as otpr19, \n" +
    		"        case when to_date('20.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday20 else 0 end as otpr20, \n" +
    		"        case when to_date('21.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday21 else 0 end as otpr21, \n" +
    		"        case when to_date('22.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday22 else 0 end as otpr22, \n" +
    		"        case when to_date('23.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday23 else 0 end as otpr23, \n" +
    		"        case when to_date('24.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday24 else 0 end as otpr24, \n" +
    		"        case when to_date('25.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday25 else 0 end as otpr25, \n" +
    		"        case when to_date('26.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday26 else 0 end as otpr26, \n" +
    		"        case when to_date('27.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday27 else 0 end as otpr27, \n" +
    		"        case when to_date('28.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday28 else 0 end as otpr28, \n" +
    		"        case when to_char(last_day(graf.date_rasch),'dd') > 28 then  \n" +
    		"            case when to_date('29.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday29 else 0 end  \n" +
    		"        else 0 end as otpr29,        \n" +
    		"        case when to_char(last_day(graf.date_rasch),'dd') > 29 then  \n" +
    		"            case when to_date('30.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday30 else 0 end  \n" +
    		"        else 0 end as otpr30, \n" +
    		"        case when to_char(last_day(graf.date_rasch),'dd') > 30 then  \n" +
    		"            case when to_date('31.'|| to_char(graf.date_rasch,'mm.yyyy'),'dd.mm.yyyy') between otp.s and otp.po then graf.rday31 else 0 end  \n" +
    		"        else 0 end as otpr31        \n" +
    		"              from   \n" +
    		"              (   \n" +
    		"              SELECT   \n" +
    		"               to_date('"+OtpDateStart+"','dd.mm.yyyy') as s, \n" +
    		"               to_date('"+OtpDateFinal+"','dd.mm.yyyy') as po               \n" +
    		"             FROM  \n" +
    		"                dual   \n" +
    		"              ) otp ,   \n" +
    		"              (  \n" +
    		"              SELECT  rday1 , rday2 , rday3 , rday4 , rday5 , rday6 , rday7 , rday8 , rday9 , rday10,  \n" +
    		"              rday11, rday12, rday13, rday14, rday15, rday16, rday17, rday18, rday19, rday20,  \n" +
    		"              rday21, rday22, rday23, rday24, rday25, rday26, rday27, rday28, rday29, rday30, rday31 , date_rasch \n" +
    		"              FROM   zarpherson.grafik_work_view  \n" +
    		"              WHERE  date_rasch between trunc(to_date('"+SelectPeriodStart+"' ,'dd.mm.yyyy'),'mm')  \n" +
    		"                                    and trunc(to_date('"+SelectPeriodFinal+"' ,'dd.mm.yyyy'),'mm') \n" +
    		"              And  work_time_id = 1  \n" +
    		"               \n" +
    		"              ) graf  \n" +
    		"           ) selsum   " ;

    if (finConnection == null)
        return new BigDecimal(Integer.MIN_VALUE);

        statement2 = finConnection.prepareStatement(sql);
        resultSet2 = statement2.executeQuery();



    if    (resultSet2.next())
    {
    	out = resultSet2.getBigDecimal(1);
    }

//    while(resultSet2.next())
//    {
//        out = resultSet2.getBigDecimal(1);
//    }

        return  out ;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + "getSummaOtpuskHoursByForThePeriod");
        return  out ;
    } finally {
        try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}

        try {if (resultSet2 != null) resultSet2.close();}             catch (SQLException e) {}
        try {if (statement2 != null) statement2.close();} catch (SQLException e) {}

            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                finConnection = null;}
            } catch (SQLException e) {
            }
    }

}

/**
 * функция выборки кол-ва персонала по штатному расписанию с учетом вакансий
 *  по кодам категорий (нвз_е нвз и тд)
 * @throws DatasourceConnectException
 * */
public BigDecimal getCountEltehpersonalFromKadryStaffing(String pdate_srez , Integer ppodrcod , String type_pers  ) throws PersistenceException
{

    boolean isDebug = false;

    BigDecimal out = new BigDecimal(0);

    PreparedStatement statement = null;
    ResultSet  resultSet = null;



    String sql = " SELECT  \n" +
    		"   count(distinct(d.Id)) \n" +
    		" FROM  \n" +
    		"   (SELECT g2.Id, g2.Nazv, gp2.Norma \n" +
    		"      FROM kadry.Grafik  g2,  kadry.Grafik_Period  gp2 \n" +
    		"      WHERE g2.Id = gp2.Grafik_Id(+) \n" +
    		"        AND " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN gp2.S AND gp2.Po) g, \n" +
    		"   kadry.Smennost       sm, \n" +
    		"   kadry.Vid_Deyatel    vd, \n" +
    		"   kadry.Grupa_Opl      go, \n" +
    		"   kadry.Personal_Vid   pv, \n" +
    		"   kadry.Post    ps1, \n" +
    		"   kadry.Prof    pf1, \n" +
    		"   kadry.Razr    rz1, \n" +
    		"   kadry.Post    ps2, \n" +
    		"   kadry.Prof    pf2, \n" +
    		"   kadry.Razr    rz2, \n" +
    		"   kadry.Oplata_Vid     ov, \n" +
    		"   kadry.Podr_Day       pd2, \n" +
    		"   kadry.Podr_Day   cehd, \n" +
    		"   kadry.Podr_Day   pd, \n" +
    		"   kadry.v_dostup     v, \n" +
    		"   kadry.Podr       p, \n" +
    		"   kadry.Nazn_Day  nd, \n" +
    		"   (SELECT * FROM kadry.s_Nazn \n" +
    		"      WHERE " + "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN Date_Start AND Date_Finish) n, \n" +
    		"   kadry.Karta  kr, \n" +
    		"   kadry.FIO    fio, \n" +
    		"   kadry.Usl_Truda ut, \n" +
    		"   kadry.Prom            prom, \n" +
    		"   kadry.Kategory        k, \n" +
    		"   kadry.Pricaz          pr1, \n" +
    		"   kadry.Pricaz          pr2, \n" +
    		"   kadry.Pricaz          pr3, \n" +
    		"   kadry.Doljnost_Day  dd, \n" +
    		"   kadry.Doljnost      d \n" +
    		" WHERE ((dd.Doljnost_Id = d.Id \n" +
    		"   -- ¬ыбор параметров на дату среза \n" +
    		"   AND dd.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"   AND NOT EXISTS \n" +
    		"      (SELECT NULL FROM kadry.s_Doljnost_Day dd2 \n" +
    		"        WHERE dd2.Doljnost_Id = dd.Doljnost_Id \n" +
    		"          AND dd2.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"          AND dd2.Date_Start > dd.Date_Start) \n" +
    		"   AND d.Start_Pricaz_Id = pr1.Id \n" +
    		"   AND d.Finish_Pricaz_Id = pr2.Id \n" +
    		"   AND d.Kategory_Id = k.Id \n" +
    		"   AND prom.Id = DECODE(k.Prom_Id, 3,pd.Prom_Id, k.Prom_Id) \n" +
    		"   AND dd.Pricaz_Id = pr3.Id \n" +
    		"   AND dd.usl_truda_id = ut.id(+) \n" +
    		"   AND d.Post_Id = ps1.Id \n" +
    		"   AND pf1.id = ps1.Prof_id \n" +
    		"   AND rz1.id = ps1.Razr_id \n" +
    		"   AND pv.Id(+) = pf1.Personal_Vid_Id \n" +
    		"   AND d.Nazn_Post_Id = ps2.Id(+) \n" +
    		"   AND pf2.id(+) = ps2.Prof_id \n" +
    		"   AND rz2.id(+) = ps2.Razr_id \n" +
    		"   AND dd.Oplata_Vid_Id = ov.Id(+) \n" +
    		"   AND vd.Id(+) = dd.Vid_Deyatel_Id \n" +
    		"   AND go.Id(+) = dd.Grupa_Opl_Id \n" +
    		"   AND sm.Id(+) = dd.Smennost_Id \n" +
    		"   AND p.Id = d.Podr_Id \n" +
    		//"   AND (v.p_dostup <> '1' OR p.id in (SELECT t.Column_Value FROM TABLE(CAST(Pkg_Dostup.get_table AS Tt_Number)) t)) \n" +
    		"   -- ¬ыбор параметров подразделени¤ на дату среза \n" +
    		"   AND pd.Podr_Id = p.Id \n" +
    		"   AND pd.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"   AND NOT EXISTS (SELECT NULL FROM kadry.s_Podr_Day pd2 \n" +
    		"                   WHERE pd2.Podr_Id = pd.Podr_Id \n" +
    		"                     AND pd2.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"                     AND pd2.Date_Start > pd.Date_Start) \n" +
    		"   -- ѕараметры ?еха на дату среза      \n" +
    		"   AND cehd.Podr_Id = kadry.pkg_Podr_Utils.Get_Struct_Ed_Id_Buf(p.Id, GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +")) \n" +
    		"   AND cehd.Date_Start <= "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +" \n" +
    		"   AND NOT EXISTS (SELECT NULL FROM kadry.s_Podr_Day cehd2 \n" +
    		"                   WHERE cehd2.Podr_Id = cehd.Podr_Id \n" +
    		"                     AND cehd2.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"                     AND cehd2.Date_Start > cehd.Date_Start) \n" +
    		"   AND d.Id = n.Doljnost_Id(+) \n" +
    		"   -- если нужно выбрать одно назначени¤ из нескольких текущих \n" +
    		"   AND (n.Id IS NULL \n" +
    		"     OR n.ID = (SELECT SUBSTR(MAX(TO_CHAR(n2.Date_Start,'J')||n2.ID),8) \n" +
    		"                     FROM kadry.Nazn n2 \n" +
    		"                     WHERE n2.Doljnost_Id = d.Id \n" +
    		"                       AND "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +" BETWEEN Date_Start AND Date_Finish \n" +
    		"       )) \n" +
    		"   AND nd.Nazn_Id(+) = n.Id \n" +
    		"   AND (nd.Nazn_Id IS NULL \n" +
    		"     OR (nd.Date_Start <= "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +" \n" +
    		"       AND NOT EXISTS (SELECT NULL FROM kadry.s_Nazn_Day nd2 \n" +
    		"                       WHERE nd2.Nazn_Id = nd.Nazn_Id \n" +
    		"                         AND nd2.Date_Start <= "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +" \n" +
    		"                         AND nd2.Date_Start > nd.Date_Start))) \n" +
    		"    AND n.Karta_Id = kr.Id(+) \n" +
    		"    AND kr.FIO_Id = fio.Id(+) \n" +
    		"   AND pd2.Podr_Id(+) = d.Podr2_Id \n" +
    		"   AND (pd2.Podr_Id IS NULL \n" +
    		"     OR (pd2.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"       AND NOT EXISTS (SELECT NULL FROM kadry.s_Podr_Day pd22 \n" +
    		"                       WHERE pd22.Podr_Id = pd2.Podr_Id \n" +
    		"                         AND pd22.Date_Start <= GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +") \n" +
    		"                         AND pd22.Date_Start > pd2.Date_Start))) \n" +
    		"   AND g.Id(+) = dd.Grafik_Id) AND  \n" +
    		"       "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" + " BETWEEN d.Date_Start AND d.Date_Finish /* открытые */ \n" +
    		" ) AND instr('"+type_pers+"','_'||ps1.kategory_Id||'_') > 0   \n" +
    		"   AND kadry.pkg_Podr_Utils.Chek_Child(d.Podr_Id, "+ ppodrcod +", GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +")) = 1 \n" +
    		" ORDER BY kadry.Pkg_Podr_Utils.Concat_Pos_Buf(pd.Podr_Id, GREATEST(d.Date_Start, "+ "to_date( '"+pdate_srez.toString()+"','dd.mm.yyyy')" +")), d.Pos " ;





    boolean isNewConn = false;

    try {

        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            isNewConn = true;
        }

        PreparedStatement statementInit = null;

        String init =
        "   BEGIN " +
        " kadry.Pkg_Dostup.INITIALIZE(sysdate); " +
        " kadry.Pkg_Podr_Utils.Init_Podr_Buf; " +
        " kadry.Pkg_Doljnost_Utils.Init_Param_Calc_Okl_Fond; " +
      " END; ";
        statementInit = finConnection.prepareStatement(init);
        statementInit.executeQuery();

        statement = finConnection.prepareStatement(sql);


        resultSet = statement.executeQuery();


        if (resultSet.next())
        out = resultSet.getBigDecimal(1);
        else
          System.out.println("resultSet clear getCountEltehpersonalFromKadryStaffing");

        return out;
    } catch (Exception e) {
        System.out.println(    e.getMessage() + " errorrr - getCountEltehpersonalFromKadryStaffing");
        return out;
    } finally {

        try {
            if (resultSet != null)
                resultSet.close();
            }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


            try {
                if  ((isNewConn ) && (finConnection != null && !finConnection.isClosed())){
                    finConnection.close();
                    finConnection = null;
                }
                    //System.out.println("Close connect");
            } catch (SQLException e) {
                System.out.println(e.getMessage() + "erorrr2 getCountEltehpersonalFromKadry");
            }
    }

}

public BigDecimal getTension_pointFromCNByCNPackCode(int cnpackcode , int cnsubsystemtyperefcode ) throws PersistenceException {

	Connection cnConnection = null;

    Calendar calendar = Calendar.getInstance();

	String selectStr = "";

	 BigDecimal tensionPoint = new BigDecimal(0);

	 try {
		 cnConnection = getCNConnection(AuthorizationJNDINames.CN_DATASOURCE);
	} catch (DatasourceConnectException e) {
	  throw new ReportSystemException(e);
	}

	 PreparedStatement statement = null;
	 ResultSet set = null;


	if (cnpackcode != Integer.MIN_VALUE && cnsubsystemtyperefcode != Integer.MIN_VALUE)
    {

        switch (cnsubsystemtyperefcode) {
        case CNSubsystemType.SS_CONNECTION: //Присоединение
            {
            	selectStr =" select tension_point from cn.cn_techterms where id_pack = " + cnpackcode ;
            	break;
            }
        case CNSubsystemType.SS_NEWCONNECTION: //Присоединение с 01.08.2010 г.
            {
            	selectStr =" select tension_point from cn.ncn_techterms where id_pack = " + cnpackcode ;
            	break;
            }
        case CNSubsystemType.SS_CONNECTION_20110314: //Присоединение с 14.03.2011 г.
            {
            	selectStr =" select tension_point from cn.cn_20110314_techterms where id_pack = " + cnpackcode ;
            	break;
            }
        case CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER: //Присоединение с 01.03.2013 г.
            {
            	selectStr =" select tension_point from cn.eap_techterms where id_pack = " + cnpackcode ;
            	break;
            }
		case CNSubsystemType.SS_ACCESSION_DISTRIBUTION_SYSTEMS_ORDER: // Присоединение с 19.04.2018 г.
		   {
			    selectStr =" select tension_point from cn.adso_techterms where id_pack = " + cnpackcode ;
			    break;
		   }

        }

    	try	{

    		statement = cnConnection.prepareStatement(selectStr);
    		set = statement.executeQuery();

        if (set.next()){
        	tensionPoint = set.getBigDecimal(1).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP) ;

        }
           return tensionPoint;
         }
      catch(SQLException e)
       {
        System.out.println(e.getMessage()+"\nstatement - "+selectStr);
        EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
        return tensionPoint;
       }
      finally
       {
        try {if (set != null) set.close();}                      catch (SQLException e) {}
        try {if (statement != null) statement.close();}          catch (SQLException e) {}

        try {
            if  ((cnConnection != null && !cnConnection.isClosed())){
            	cnConnection.close();
            }
        } catch (SQLException e) {
        }
       }


    }
	return tensionPoint;
 }


public String getSubsNameByCNPackCode(int cnpackcode , int cnsubsystemtyperefcode,int subsType) throws PersistenceException {

	Connection cnConnection = null;
	Connection netConnection = null;

	String selectStr = "";

	System.out.print(" getSubsNameByCNPackCode(int cnpackcode = " + cnpackcode);

	int subscode = Integer.MIN_VALUE;
	String subsname = "";

	 try {
		 cnConnection = getCNConnection(AuthorizationJNDINames.CN_DATASOURCE);
	} catch (DatasourceConnectException e) {
	  throw new ReportSystemException(e);
	}

	 try {
		 netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
	} catch (DatasourceConnectException e) {
	  throw new ReportSystemException(e);
	}

	 PreparedStatement cnstatement = null;
	 ResultSet cnset = null;
	 PreparedStatement netstatement = null;
	 ResultSet netset = null;


	if (cnpackcode != Integer.MIN_VALUE && cnsubsystemtyperefcode != Integer.MIN_VALUE)
    {

        switch (cnsubsystemtyperefcode) {
        case CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER: //Присоединение с 01.03.2013 г.
            {
            	selectStr = " select  " + ((subsType==1) ? "l.code_ss150" : "code_substation150") + " from eap_enlines l where l.id_pack =  " + cnpackcode ;
            	break;
            }
		case CNSubsystemType.SS_ACCESSION_DISTRIBUTION_SYSTEMS_ORDER: // Присоединение с 19.04.2018 г.
		   {
			    selectStr = " select  " + ((subsType==1) ? "l.code_ss150" : "code_substation150") + " from adso_enlines l where l.id_pack =  " + cnpackcode ;
			    break;
		   }
		case CNSubsystemType.SS_CONNECTION_20110314: //Присоединение с 01.03.2013 г.
		   {
			    selectStr = " select  " + ((subsType==1) ? "l.code_ss150" : "code_substation150") + " from cn_20110314_enlines l where l.id_pack =  " + cnpackcode ;
			    break;
		   }
		  default : return "";

        }

    	try	{

    		cnstatement = cnConnection.prepareStatement(selectStr);
    		cnset = cnstatement.executeQuery();

        if (cnset.next()){
        	subscode = cnset.getInt(1);
          }

         }

      catch(SQLException e)
       {
        System.out.println(e.getMessage()+"\nstatement - "+selectStr);
        EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
        return subsname;
       }
      finally
       {
        try {if (cnset != null) cnset.close();}                      catch (SQLException e) {}
        try {if (cnstatement != null) cnstatement.close();}          catch (SQLException e) {}

        try {
            if  ((cnConnection != null && !cnConnection.isClosed())){
            	cnConnection.close();
            }
        } catch (SQLException e) {
        }
       }

    	if (subscode != Integer.MIN_VALUE) {
    	try	{

    		selectStr = "select s150.name from ensubstation150 s150 where s150.code = " + subscode;
    		netstatement = netConnection.prepareStatement(selectStr);
    		netset = netstatement.executeQuery();

        if (netset.next()){
        	subsname = netset.getString(1);
          }

        return subsname;
         }

      catch(SQLException e)
       {
        System.out.println(e.getMessage()+"\nstatement - "+selectStr);
        EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
        return subsname;
       }
      finally
       {
        try {if (netset != null) netset.close();}                      catch (SQLException e) {}
        try {if (netstatement != null) netstatement.close();}          catch (SQLException e) {}

        try {
            if  ((netConnection != null && !netConnection.isClosed())){
            	netConnection.close();
            }
        } catch (SQLException e) {
        }
       }
    }
    }
	return subsname;
 }

public Boolean isDriver(String tabNumber) {
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
		if (netConnection == null || netConnection.isClosed()) {
			netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
		}
		statement = netConnection.prepareStatement("SELECT EXISTS(SELECT FROM entravelsheet INNER JOIN finworker ON finworkercode = finworker.code WHERE finworker.tabnumber = ?)");
		statement.setString(1, tabNumber);
		resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return resultSet.getBoolean(1);
		} else {
			return false;
		}
	} catch (Exception e) {
		System.out.println("Error :" + e.getMessage());
		throw new SystemException(e);
	} finally {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {}
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {}
		try {
			if (netConnection != null && !netConnection.isClosed())
				netConnection.close();
		} catch (SQLException e) {}
	}
}

/**
 *
 * @param finDocID
 * @return
 * @throws PersistenceException
 */
public FINContractsShort getFinContractShort(int finDocID) throws PersistenceException{
	FINContractsShort finContractsShort = new FINContractsShort();

	PreparedStatement orgStatement = null;
    ResultSet  orgResultSet = null;

    PreparedStatement finStatement = null;
    ResultSet  finResultSet = null;

    try {


        if (finConnection == null){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        }

        if ( finConnection.isClosed() ){
            finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        }

    	//finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

        String rqOrgAgreePartnerLink =
    		"  SELECT " +
			"    partner_id, " +
			"    partner_code, " +
			"    partner_name, " +
			"    partner_ident_code " +
			"  FROM sprav.v_agree_partner_link ds_main " +
			"  WHERE AGREE_ID = " + finDocID +
			"  ORDER BY PARTNER_NAME ";


        orgStatement = finConnection.prepareStatement(rqOrgAgreePartnerLink);
        orgResultSet = orgStatement.executeQuery();

        for(int i=0; orgResultSet.next(); i++){
        	if(i==0){
	        	finContractsShort.orgId = orgResultSet.getInt(1);
	        	finContractsShort.orgName = orgResultSet.getString(3);
        	}else
        		break;
        }

 /*   String finSql = "SELECT "
			+ "FINCONTRACTS.CODE"
			+ ",FINCONTRACTS.CONTRACTNUMBER"
			+ ",FINCONTRACTS.CONTRACTDATE"
			+ ",FINCONTRACTS.FINDOCCODE"
			+ ",FINCONTRACTS.FINDOCID"
			+
			", FINCONTRACTS.ORGCODE "
			+ ", (SELECT RQORG.NAME FROM RQORG WHERE RQORG.CODE = FINCONTRACTS.ORGCODE) AS ORGNAME "
			+ ", FINCONTRACTS.GENERALCONTRACTREFCODE " +

			" FROM FINCONTRACTS " +
                        " FINCONTRACTS.FINDOCID = " + finDocID;
    */

    String finSql 	= "  SELECT " +
			"    ID, CODE, " +
			"    IN_NUM, IN_DATE, " +
			"    DIVISION_ID, DIVISION_NAME, " +
			"    AGREE_GROUP_ID, AGREE_GROUP_NAME, " +
			"    REG_NUM, LPAD(RTRIM(REG_NUM),12) ALIGNREGNUM, REG_DATE, " +
			"    START_DATE, END_DATE, " +
			"    curr_end_date, prolong_month, prolong_no, " +
			"    STATUS, STATUS_NAME, " +
			"    SUMMA, curr_summ, csumm_start_date, " +
			"    SUMM_NOTE, " +
			"    NUMS, " +
			"    EDIZM_ID, EDIZM_NAME, " +
			"    DESCRIPTION, CLOSE_DATE, NOTES, " +
			//"    PARENT_ID, PARENT_CODE, PARENT_IN_NUM, PARENT_IN_DATE, " +
			//"    RC_ID,  " +
			"    PAY_AFTER_EVENT, " +
			"    PAY_PERIOD,  " +
			"    PAY_TYPE, PAY_TYPE_NAME, " +
			"    NOTLIMITED, " +
			"    IO_FLAG, DEAL_FLAG, " +
			"    GK_KATEGORY, GK_KATEGORY_NAME, " +
			"    ACT_EXISTS, " +
			"    SERVICE_ID, SERVICE_NAME, " +
			"    id_BuyConds,  " +
			"    name_BuyConds, " +
			"    id_PayForm, " +
			"    name_PayForm, " +
			"    Summ_ChangeMode, " +
			"    id_OtvLico, " +
			"    TabN_OtvLico, " +
			"    FIO_OtvLico, " +
			"    currency_code, " +
			"    tender_date, " +
			"    tender_no, " +
			"    note1, " +
			"    note2, " +
			"    PARENT_ID " +
			"  FROM sprav.v_agree " +
			"  WHERE ID = " + finDocID ;


    finStatement = finConnection.prepareStatement(finSql);
    finResultSet = finStatement.executeQuery();

    for(int i=0; finResultSet.next(); i++){
    	finContractsShort.code = finResultSet.getInt(2);
    	finContractsShort.contractNumber = finResultSet.getString(3);
    	finContractsShort.contractDate = finResultSet.getDate(4);
    	finContractsShort.summa = finResultSet.getBigDecimal(19);
		if(finContractsShort.summa != null) {
			finContractsShort.summa = finContractsShort.summa.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
		}

		  finContractsShort.curr_summ = finResultSet.getBigDecimal(20);
			if(finContractsShort.curr_summ != null) {
				finContractsShort.curr_summ = finContractsShort.curr_summ.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
			}
    }



        return  finContractsShort ;
    } catch (Exception e) {
        System.out.println("Error in getFinContractShort: " +    e.getMessage());
        return finContractsShort ;
    } finally {

    	try {if (orgResultSet != null) orgResultSet.close();} catch (SQLException e) {}
        try {if (orgStatement != null) orgStatement.close();} catch (SQLException e) {}

    	try {if (finResultSet != null) finResultSet.close();} catch (SQLException e) {}
        try {if (finStatement != null) finStatement.close();} catch (SQLException e) {}

        try {
            if ((finConnection != null && !finConnection.isClosed()))
                finConnection.close();
        } catch (SQLException e) {}
    }

}


}




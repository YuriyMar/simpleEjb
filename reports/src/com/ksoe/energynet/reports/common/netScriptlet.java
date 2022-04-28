package com.ksoe.energynet.reports.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ksoe.authorization.dataminer.ConfigDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.authorization.valueobject.Config;
import com.ksoe.energynet.dataminer.ENGiveCounterDAO;
import com.ksoe.energynet.dataminer.ENIPItemDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENServicesObjectDAO;
import com.ksoe.energynet.logic.CNLogic;
import com.ksoe.energynet.logic.ManningTableLogic;
import com.ksoe.energynet.logic.PlanWorkLogic;
import com.ksoe.energynet.logic.SCLogic;
import com.ksoe.energynet.logic.mDaxLogic;
import com.ksoe.energynet.reports.Invest.StageBuyAndExecution2.dsFinansirOsvoenieBalans;
import com.ksoe.energynet.reports.RepOrder.chartPayments.ChartPaymentDS;
import com.ksoe.energynet.reports.RepOrder.reestrPaymentDS.ReestrPaymentDS;
import com.ksoe.energynet.reports.RepOrder.reestrPaymentDSService.reestrPaymentDSService;
import com.ksoe.energynet.reports.Services.ExecPriconectionContracts.dsExecPriconectionContract;
import com.ksoe.energynet.reports.Services.servicesRegistryPrint2DS.ServicesDS;
import com.ksoe.energynet.reports.Zbyt.PlanFact.DodatokZbytDS;
import com.ksoe.energynet.reports.repSummaryListGSM.inventory_list.sumrestbak;
import com.ksoe.energynet.util.LoggableStatement;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.ENIPItem;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.ENServicesContractKind;
import com.ksoe.energynet.valueobject.ENServicesContractType;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.filter.ENGiveCounterFilter;
import com.ksoe.energynet.valueobject.filter.ENIPItemFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENGiveCounterShortList;
import com.ksoe.energynet.valueobject.lists.ENIPItemShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectShortList;
import com.ksoe.energynet.valueobject.lists.SCCounterShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.mdax.valueobject.lists.AXPodrWithEmplidStrShortList;
import com.ksoe.report.exception.ReportSystemException;
import com.ksoe.report.scriptlets.DSField;
import com.ksoe.rqorder.logic.RQConsts;
import com.ksoe.techcard.dataminer.TKMaterialsDAO;
import com.ksoe.techcard.dataminer.TKTechCardDAO;
import com.ksoe.techcard.valueobject.filter.TKMaterialsFilter;
import com.ksoe.techcard.valueobject.filter.TKTechCardFilter;
import com.ksoe.techcard.valueobject.lists.TKMaterialsShortList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.fill.JRFillParameter;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;



public class netScriptlet extends JRDefaultScriptlet{


	private transient java.sql.Connection finConnection = null;
	private java.sql.Connection EWFConnection = null;

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

    @Override
	public void beforeReportInit() throws JRScriptletException {
        //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
        //connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
        //userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
        //logic = new TransportLogic(connection, userProfile);
    }

    public String getObject(/*String pdate2*/Integer YearVvod , Integer MonthVvod ,  Integer renCode , Integer budgCode ,
                                             BigDecimal materialCode , String molcode , String Condition )

    throws PersistenceException
        {

        boolean isDebug = false;

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        //System.out.println("start getWorkTimeInMonth:" + new Date());

        //    Date firstDay = getFirstDayInMonth(d);

            String sql = " select "
     + "  distinct(( select ename||'  Код Инв.'||'('||invnumber||')' from enelementdata where ecode = e.code))  as objname  "
     + " from enplanwork p  left join net.enmol2planwork en2 on (p.code = en2.planrefcode) , "
     + " enelement e ,  "
     + " tkmeasurement ei, "
     + " enestimateitem es, "
     + " tkmaterials sm, "
     + " endepartment d , "
     + " endepartment db "
 + " where p.code = es.planrefcode  "
+ " and p.elementrefcode = e.code "
+ " and p.departmentrefcode = d.code "
+ " and p.budgetrefcode = db.code "
+ " and es.materialrefcode = sm.code "
+ " and ei.code = sm.measurementcode "
+ " and p.kindcode = 2 "
//+ " and to_date( "+"'"+pdate2+"'"+"  ,'dd.mm.yyyy') between  p.datestart and p.datefinal "
+ " and p.yeargen = " + YearVvod
+ " and p.monthgen = " +  MonthVvod
+ " and (p.departmentrefcode = " + renCode + " or " + renCode + " = 0) "
+ " And ((p.budgetrefcode = " + budgCode + "  ) or " + budgCode + " = 0 ) "
+ " and p.statuscode not in (2,6) "
+ " and es.countfact <> 0 "
+ " and es.kindrefcode = 1 /* материалы*/ "
+ " and en2.molcode = " +"'"+ molcode + "'"
+ " and sm.code = " + materialCode
 // + Condition
;

        if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param YearVvod : " + YearVvod.toString());
            System.out.println("param MonthVvod : " + MonthVvod.toString());
            System.out.println("param rencode : " + renCode.toString());
            System.out.println("param budgcode  : " + budgCode.toString());
            System.out.println("param materialocode : " + materialCode.toString());
            System.out.println("param molcode : " + molcode.toString());
        }

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            String out = new String("");
            try {



                statement = connection.prepareStatement(sql);
                // statement.setDate(1, new java.sql.Date(firstDay.getTime()));

                resultSet = statement.executeQuery();
                //resultSet.next();
            //    if (!resultSet.next()) {
            //        throw new EnergyproSystemException("Count work time not found for date " + d);
            //    }

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

                //System.out.println("finally getObject:" + new Date());


                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                try {
                    if  (connection != null && connection.isClosed())
                        connection.close();
                } catch (SQLException e) {
                }
            }
        }

    public String getObjectRqOrder(/*String pdate2*/Integer YearVvod , Integer MonthVvod ,  Integer renCode , Integer budgCode , BigDecimal materialCode , String molcode , Integer MonthRaznar  , Integer YearRaznar , Integer zayakind )

    throws PersistenceException
        {
        boolean isDebug = false;

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        //System.out.println("start getWorkTimeInMonth:" + new Date());

        //    Date firstDay = getFirstDayInMonth(d);

            String sql = " select "
    + "  distinct(( select ename||'  Код Инв.'||'('||invnumber||')' from enelementdata where ecode = e.code))  as objname  "
    + " from enplanwork p  left join net.enmol2planwork en2 on (p.code = en2.planrefcode) , "
    + " enelement e ,  "
    + " tkmeasurement ei, "
    + " enestimateitem es, "
    + " tkmaterials sm, "
    + " endepartment d , "
    + " endepartment db , "
    + " RQORDERITEM2ENESTIMTTM rrr , "
    + " rqorder r , "
    + " rqorderitem ri "
+ " where p.code = es.planrefcode  "
+ " and p.elementrefcode = e.code "
+ " and p.departmentrefcode = d.code "
+ " and p.budgetrefcode = db.code "
+ " and es.materialrefcode = sm.code "
+ " and ei.code = sm.measurementcode "
+ " and p.kindcode = 2 "



+ " and (p.departmentrefcode = " + renCode + " or " + renCode + " = 0) "
+ " And ((p.budgetrefcode = " + budgCode + "  ) or " + budgCode + " = 0 ) "
+ " and p.statuscode not in (2,6) "
+ " and es.countfact <> 0 "
+ " and es.kindrefcode = 1 /* материалы*/ "
+ " and en2.molcode = " +"'"+ molcode + "'"
+ " and sm.code = " + materialCode
+ " AND e.typerefcode not in (select code from enelementtype where coalesce(managementrefcode, 0) = 3  ) "
+ " and ( p.yeargen = " + "'"+YearVvod +"'" + " or " +  "'"+ YearVvod + "'"+ " = 0 ) "
+ " and ( p.monthgen = " + "'"+MonthVvod +"'" + " or " +  "'"+ MonthVvod + "'"+ " = 0 ) "

+ " /*rq*/ "

+ " and  r.orderperiod = to_date('01.'||" + MonthRaznar+ "||'.'||"+ YearRaznar+ ",'dd.mm.yyyy') "

//+ " and  r.rqorderformcode = 1 /* выбиирается  из плановых заявок */ "
+ " and  r.kindrefcode = " + zayakind
+ " and  r.statusrefcode = 2 /* только утвержденные заявки */ "
+ " and  r.code = ri.orderrefcode "
+ " and  ri.code = rrr.orderitemcode "
+ " and  rrr.estimateitemcode = es.code "
+ " and  es.statusrefcode = 2 /* замовлений */ " ;

            if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param YearVvod : " + YearVvod.toString());
            System.out.println("param MonthVvod : " + MonthVvod.toString());
            System.out.println("param rencode : " + renCode.toString());
            System.out.println("param budgcode  : " + budgCode.toString());
            System.out.println("param materialocode : " + materialCode.toString());
            System.out.println("param molcode : " + molcode.toString());
            System.out.println("param zayakind : " + zayakind.toString());
            }

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            String out = new String("");
            try {



                statement = connection.prepareStatement(sql);
                // statement.setDate(1, new java.sql.Date(firstDay.getTime()));

                resultSet = statement.executeQuery();
                //resultSet.next();
            //    if (!resultSet.next()) {
            //        throw new EnergyproSystemException("Count work time not found for date " + d);
            //    }

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

                //System.out.println("finally getObject:" + new Date());


                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                try {
                    if  (connection != null && connection.isClosed())
                        connection.close();
                } catch (SQLException e) {
                }
            }
        }

    public String getObjectDecodingZayavka(/*String pdate2*/Integer YearVvod , Integer MonthVvod ,  BigDecimal renCode , BigDecimal budgCode ,
                            BigDecimal materialCode ,  Integer MonthRaznar  , Integer YearRaznar , Integer zayakind ,
                            Integer ordertype , Integer orderstatus , Integer orderform , Integer PcodeOrder  )

    throws PersistenceException
        {
        boolean isDebug = false;

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        //System.out.println("start getWorkTimeInMonth:" + new Date());

        //    Date firstDay = getFirstDayInMonth(d);

            String sql = "Select distinct(elementtypename ||' '||objname) as obj " +
            " From (select ent.name as elementtypename, (select e.ename || '  Код Инв.' || '(' " +
            " || e.invnumber || ')') as objname   From  enelementdata e, " +
            " enelementtype ent,    " +
            " endepartment d, endepartment db, rqorder r, " +
            "  rqorderitem ri left join rqddscodes rdds on ri.ddscodescode = rdds.code ,   " +
            "  enplanwork p left join  net.enmol2planwork en2 on (p.code = en2.planrefcode) " +
            "   left join  " +
            "  ( select es.planrefcode as planrefcode, es.code as code , sm.measurementcode as measurementcode " +
            "      from  enestimateitem es, tkmaterials sm  " +
            "     where es.materialrefcode = sm.code " +
            "       and es.countfact <> 0 " +
            "       And es.kindrefcode = 1 " +
            "       and es.statusrefcode = 2 " +
            "       and sm.code = " + materialCode +
            "   ) ww on ( ww.planrefcode = p.code  )  left join " +
            "    RQORDERITEM2ENESTIMTTM rrr on (rrr.estimateitemcode = ww.code) left join " +
            "    tkmeasurement ei on ( ei.code = ww.measurementcode) " +
            "   where " +
            "    p.elementrefcode = e.ecode " +
            "    and p.departmentrefcode = d.code " +
            "    and p.budgetrefcode = db.code " +
            "    and e.etype = ent.code " +
            "    and ( r.code = " + PcodeOrder + " or " +  PcodeOrder + " = 0 ) " +
            "    and (p.yeargen = " + "'"+ YearVvod + "'" + " or " + "'" + YearVvod + "'" + " = 0) " +
            "    and (p.monthgen = " + "'" + MonthVvod + "'" + " or " + "'" +  MonthVvod + "'" + " = 0) " +
            "    and (p.departmentrefcode = " + renCode +  " or " +  renCode + " = 0) " +
            "    And ((p.budgetrefcode = " + budgCode + ")" + " or " +  budgCode + " = 0) " +
            "    and  p.statuscode not in (2, 6) " +
            "    and ( r.orderperiod = to_date('01.' || " + MonthRaznar + " || '.' || " +  YearRaznar + " ,'dd.mm.yyyy') " +
            "        or " +
            "          '01.01.3000' = to_date('01.' || " + MonthRaznar + " || '.' || " +  YearRaznar + " ,'dd.mm.yyyy')  ) " +
            "    and (r.rqorderformcode = " + orderform + " or " +  orderform + " = 0) " +
            "    and ( r.kindrefcode = " + zayakind + " or " +  zayakind + "  = 0) " +
            "    and (r.statusrefcode = " + orderstatus + " or " +  orderstatus + "  = 0) " +
            "    and (r.rqordertypecode = " + ordertype + " or " +  ordertype + " = 0) " +
            "    and r.code = ri.orderrefcode " +
            "    and ri.code = rrr.orderitemcode " +
            "     ) dat "
            ;

            if (isDebug){
            System.out.println("sql : " + sql);
            System.out.println("param YearVvod : " + YearVvod.toString());
            System.out.println("param MonthVvod : " + MonthVvod.toString());
            System.out.println("param rencode : " + renCode.toString());
            System.out.println("param budgcode  : " + budgCode.toString());
            System.out.println("param materialocode : " + materialCode.toString());
        //    System.out.println("param molcode : " + molcode.toString());
            System.out.println("param zayakind : " + zayakind.toString());
            }

            PreparedStatement statement = null;
            ResultSet  resultSet = null;

            String out = new String("");
            try {



                statement = connection.prepareStatement(sql);
                // statement.setDate(1, new java.sql.Date(firstDay.getTime()));

                resultSet = statement.executeQuery();
                //resultSet.next();
            //    if (!resultSet.next()) {
            //        throw new EnergyproSystemException("Count work time not found for date " + d);
            //    }

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

                //System.out.println("finally getObject:" + new Date());


                try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
                try {if (statement != null) statement.close();} catch (SQLException e) {}

                try {
                    if  (connection != null && connection.isClosed())
                        connection.close();
                } catch (SQLException e) {
                }
            }
        }
public String getAktsFromObject( Integer pelementcode , String yearGen , String monthGen , Integer ptypework ) throws PersistenceException
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

//System.out.println("start getAktsFromObject:" + new Date());

//    Date firstDay = getFirstDayInMonth(d);

String sql = " select a.numbergen || ' от ' || to_char(a.dategen,'dd.MM.yyyy') "
 + " from enact a where a.elementcode = " + pelementcode
 + " and a.statusrefcode = 3 "
 + " and a.acttyperefcode = " + ptypework
 + " and a.dategen between first_day(to_date(" + "'01."+ monthGen + "." + yearGen +  " '," + "'dd.mm.yyyy')" +  ") and last_day(to_date(" + "'01."+ monthGen + "." + yearGen + "'," + "'dd.mm.yyyy') )"  ;

//System.out.println("sql : " + sql);
//System.out.println("param YearVvod : " + YearVvod.toString());
//System.out.println("param MonthVvod : " + MonthVvod.toString());
//System.out.println("param rencode : " + renCode.toString());
//System.out.println("param budgcode  : " + budgCode.toString());
//System.out.println("param materialocode : " + materialCode.toString());
//    System.out.println("param molcode : " + molcode.toString());
//System.out.println("param zayakind : " + zayakind.toString());

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
// statement.setDate(1, new java.sql.Date(firstDay.getTime()));

resultSet = statement.executeQuery();
//resultSet.next();
//    if (!resultSet.next()) {
//        throw new EnergyproSystemException("Count work time not found for date " + d);
//    }

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

System.out.println("finally getObject:" + new Date());


try {if (resultSet != null) resultSet.close();}             catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
    if  (connection != null && connection.isClosed())
        connection.close();
} catch (SQLException e) {
}
}
}


public String getDataPostavkaByStrOrder( Integer oicode , BigDecimal price )

throws PersistenceException
{
boolean isDebug = false;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getDataPostavkaByStrOrder:" + new Date());
}


String sql =
    " select distinct to_char(dategen,'dd.mm.yyyy') as dategen , pricewithnds from ( "
    + "        select "
    + "         pricewithnds , "
    + "         dategen "
    + "        from ( "
    + " /*поставка приход с выбором партий */ "
    + " select "
    + " rqfkorderitem2enstmttm.estimateitemcode , "
    + " iord.code as prihordercode , "
    + " iord.numberdoc, "
    + " iord.dategen, "
    + " rqfkorderitem2enstmttm.countgen as countfact, "
    + " ii.measurementnametxt, "
//     + " round(cast(ii.pricewithnds as numeric),2) as pricewithnds , "
    + " round(cast (ii.pricewithoutnds + coalesce((ii.pricewithoutnds * iord.ndspercent/100)) as    numeric), 3) as pricewithnds , "
 // NET-4284    + " round(cast(ii.pricewithoutnds*1..2 as numeric),3) as pricewithnds , "
    + " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, "
    + " 0 as findoccode, "
    + " /*выбор кол-во излишки */ "
    + " COALESCE((select sum(coalesce(rqfkorderitemremainder.countgen,0)) from rqfkorderitem , rqfkorderitemremainder "
    + "  where rqfkorderitem.code = rqfkorderitemremainder.fkorderitemrefcode "
    + "    and rqfkorderitem.code = ii.code  "
    + "    and rqfkorderitemremainder.typerefcode = 1 /*надлишок */ "
    + " ),0) as countremander, "
    + " iord.statuscode,  "
    + " rqfkorderstatus.name as orderstatusname "
    + " from rqfkorderitem ii "
    + "      left join rqfkorderitem2enstmttm "
    + "         on ( rqfkorderitem2enstmttm.fkorderitemrefcode = ii.code "
    + "              and coalesce(rqfkorderitem2enstmttm.countgen,0) <> 0 ) "
    + " , rqfkorder iord left join  findoc2fkorder ffkord on (iord.code = ffkord.fkorderrefcode)  , rqfkorderstatus "
    + " Where iord.code = ii.fkorderrefcode "
    + " and iord.statuscode = rqfkorderstatus.code "
    + " and rqfkorderstatus.code = 3 /*приходы по факту - значит проведенные */ "
    + " and iord.kindcode in (1,14,15) "
    + " and rqfkorderitem2enstmttm.estimateitemcode in ( select estimateitemcode from rqorderitem2enestimttm rrr where "
    + "                                                        rrr.orderitemcode =  " + oicode
    + "                                                ) "
    + " group by iord.code , iord.numberdoc, iord.dategen, "
    + " ii.measurementnametxt, "
    + " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, ii.pricewithoutnds , "
    + " iord.kindcode , iord.statuscode "
    + " , ii.countgen , ii.sumwithoutnds , ii.code , rqfkorderstatus.name "
    + " , rqfkorderitem2enstmttm.countgen "
    + " , rqfkorderitem2enstmttm.estimateitemcode "
    + " UNION /*выборка из remainder не излишков а приходов под планы */ "
    + " select "
    + "      rqfkorderitemremainder.estimateitemrefcode as estimateitemcode "
    + "    , rqfkorder.code as prihordercode "
    + "    , rqfkorder.numberdoc "
    + "    , rqfkorder.dategen "
    + "    , rqfkorderitemremainder.countgen as countfact "
    + "    , rqfkorderitem.measurementnametxt "
    + "    , round(cast(rqfkorderitemremainder.pricewithoutnds*(1::integer + coalesce(rqfkorder.ndspercent,0) / 100 ) as numeric),3) as pricewithnds  "
    + "    , rqfkorder.molincode "
    + "    , rqfkorder.molinname "
    + "    , rqfkorder.moloutcode "
    + "    , rqfkorder.moloutname "
    + "    , 0 as findoccode "
    + "    , 0 as countremander "
    + "    , rqfkorder.statuscode "
    + "    , rqfkorderstatus.name as orderstatusname "
    + "    from rqfkorderitemremainder , rqfkorderitem , rqfkorder , rqfkorderstatus "
    + "    where rqfkorderitemremainder.estimateitemrefcode in "
    + "                                              ( select    estimateitemcode from rqorderitem2enestimttm rrr where "
    + "                                                         rrr.orderitemcode =  " + oicode
    + "                                              ) "
    + "      and rqfkorderitemremainder.typerefcode = 2 "
    + "      and rqfkorderitemremainder.fkorderitemrefcode = rqfkorderitem.code "
    + "      and rqfkorderitem.fkorderrefcode = rqfkorder.code "
    + "      and rqfkorder.kindcode in (1,14,15) "
    + "      and rqfkorder.statuscode <> 1 /*НЕ равно черновым сказано Салыгиным 23,11,2010*/ "
    + "      and rqfkorder.statuscode = rqfkorderstatus.code "
    + "    ) w "

    + " ) ww "
    + " Where pricewithnds = " + price + "    /* количество прихода из строки отчета */  "
    ;

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


public String getObjectStateFromMotion( String year , String monthgen ,  Integer budgcode , Integer rencode ,
        String elementtypecode ,  String plancode , Integer codematerials , Integer kindcode , String moloutcode  , String nomenclaturenum , Integer pr_sel_obj  )

throws PersistenceException
{
boolean isDebug = true;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
if (isDebug){
 System.out.println("start getObjectStateFromMotion:" + new Date() + "kindcode = " + kindcode);
}
//    Date firstDay = getFirstDayInMonth(d);

String sql = new String("");

if (kindcode.intValue() == 10 ) {
                            sql = " /*выбор обьектов если было списание */ "
                            + " select distinct endata.ename ||' Інв.№ '|| endata.invnumber   as object  from enestimateitem enit , enplanwork enpw , enelementdata endata "
                            + " where enit.planrefcode = enpw.code "
                            + " and   endata.ecode = enpw.elementrefcode "
                            + " and  enit.code in ( "
                            + " Select ene.code as estimateitemcode "
                            + "    from enestimateitem2nstmttm enit2enit2 /*наличие материала на факте*/ "
                            + " , finmaterials fin , enestimateitem ene , enplanwork enp , enact2enplanwork act2wor , "
                            + "  enact act   "
                            + " Where enit2enit2.estimateiteminrefcode /*=*/ in  ( "
                            + "   Select enit2enit1.estimateitemoutrefcode from enestimateitem2nstmttm enit2enit1 /*наличие материала на плане*/ "
                            + "    Where enit2enit1.estimateiteminrefcode in (select  eni.code as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                            + "                               , enelement el "
                            + "                                     where enp.code = eni.planrefcode "
                            + "                                       and eni.countfact <> 0  "
                            + "                                       and enp.kindcode = 2  "
                            + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                            + "                                       and enp.code = eni.planrefcode "
                            + "                                       and el.code = enp.elementrefcode "
                            + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                            + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                            + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                            + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                            + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                            + "                                       and (enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                            + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                            + "                                       and eni.statusrefcode in (1,2,3) "
                            + "                                     UNION  /*для перенесенных материалов из других планов */  "
                            + "                                     select  en2en.estimateiteminrefcode as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                            + "                               , enelement el , enestimateitem2nstmttm en2en "
                            + "                                     where enp.code = eni.planrefcode "
                            + "                                       and eni.countfact <> 0  "
                            + "                                       and enp.kindcode = 2  "
                            + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                            + "                                       and enp.code = eni.planrefcode "
                            + "                                       and el.code = enp.elementrefcode "
                            + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                            + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                            + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                            + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                            + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                            + "                                       and (enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                            + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                            + "                                       and eni.statusrefcode in (1,2,3) "
                            + "                                       and eni.code = en2en.estimateitemoutrefcode "
                            + "                                       and en2en.typerefcode in (2, 3 ) "
                            + "                                     ) ) "
                            + "  and fin.estimateitemrefcode = enit2enit2.estimateitemoutrefcode "
                            + "  /*удалили привязку к партии прихода*/ "
                            + "  and  fin.estimateitemrefcode = ene.code "
                            + "  and  ene.planrefcode = enp.code  "
                            + "  and  act2wor.plancode  =  enp.code "
                            + "  and  act2wor.actrefcode = act.code "
                            + "  and  fin.statusrefcode = 1 /*действительный*/ "
                            // + "  and  act.statusrefcode = 3 /*проведенный в финколлекции*/  " убрал признак потомучто в развернутом отчете выводятся также черновые акты или составленые
                            + "  and  upper(trim(fin.nn)) = upper(trim( " + nomenclaturenum +  "))"
                            +  " and act.numbergen = " + moloutcode + " ) "

                            ;
}

if ( (kindcode.intValue() == 2  ) || kindcode.intValue() == 3 ) {
    sql =   " /*выбор обьектов если видатковий ордер был с Склада на РЕС*/  "
            + " select distinct endata.ename ||' Інв.№ '|| endata.invnumber   as object  , prizn "
            + "  from enestimateitem enit , enplanwork enpw , enelementdata endata  "
            + "  ,  "
            + "  --- "
            + "  (  "
            + "   Select fi2ei.estimateitemcode  , "
            + "     f_analyse_motion(fi2ei.estimateitemcode  , 0 , "
            + "                 '' , '' ,  "
            + "                  fin.party_id , 0 , max(fi2ei.modify_time) ) as prizn  "
            + "  From    "
            + "         rqfkorderitem2enstmttm fi2ei, rqfkorderitem rqfkit ,  rqfkorder rqfkor  ,  finmaterials fin "
            + "  Where fi2ei.estimateitemcode  IN (select  eni.code as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                        + "                               , enelement el "
                        + "                                     where enp.code = eni.planrefcode "
                        + "                                       and eni.countfact <> 0  "
                        + "                                       and enp.kindcode = 2  "
                        + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                        + "                                       and enp.code = eni.planrefcode "
                        + "                                       and el.code = enp.elementrefcode "
                        + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                        + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                        + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                        + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                        + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                        + "                                       and ( enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                        + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                        + "                                       and eni.statusrefcode in (1,2,3) "
                        + "                                     UNION  /*для перенесенных материалов из других планов */  "
                        + "                                     select  en2en.estimateiteminrefcode as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                        + "                               , enelement el , enestimateitem2nstmttm en2en "
                        + "                                     where enp.code = eni.planrefcode "
                        + "                                       and eni.countfact <> 0  "
                        + "                                       and enp.kindcode = 2  "
                        + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                        + "                                       and enp.code = eni.planrefcode "
                        + "                                       and el.code = enp.elementrefcode "
                        + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                        + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                        + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                        + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                        + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                        + "                                       and (enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                        + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                        + "                                       and eni.statusrefcode in (1,2,3) "
                        + "                                       and eni.code = en2en.estimateitemoutrefcode "
                        + "                                       and en2en.typerefcode in (2, 3 ) "
                        + "                                    ) "
                        + " and rqfkit.code = fi2ei.fkorderitemrefcode  "
                        + " and rqfkor.code = rqfkit.fkorderrefcode  "
                        + " and rqfkor.kindcode = " + kindcode.intValue() + " /*Видатковий  ордер*/ "
                        + " and fin.estimateitemrefcode = fi2ei.estimateitemcode  "
                        + " and fin.code = fi2ei.finmaterialsrefcode "
                        + " and rqfkor.moloutcode = " + moloutcode
                        + " and upper(trim(rqfkit.nomenclaturenum)) = upper(trim( " + nomenclaturenum +  ")) "
            + " group by fi2ei.estimateitemcode ,  fin.party_id   "
            + "  ) selit "
            + " where enit.planrefcode = enpw.code  "
            + " and   endata.ecode = enpw.elementrefcode  "
            + " and  enit.code = selit.estimateitemcode   "
            + " and prizn = 0 " ;
}


if ( kindcode.intValue() == 1  )  {
    sql =  " /*выбор обьектов если видатковий ордер был с Склада на РЕС kind = 1 */ "
        + " select distinct endata.ename ||' Інв.№ '|| endata.invnumber   as object  from enestimateitem enit , enplanwork enpw , enelementdata endata "
        + " where enit.planrefcode = enpw.code "
        + " and   endata.ecode = enpw.elementrefcode "
        + " and  enit.code in ( "
        + " Select fi2ei.estimateitemcode "
        + " from  rqfkorderitem2enstmttm fi2ei, rqfkorderitem rqfkit ,  rqfkorder rqfkor "
        // + "    , rqfkorderdata2fkparty rqfk2part " убрал т.к если приход не проведен то партии на нем нету
        + " where fi2ei.estimateitemcode in (select  eni.code as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                + "                               , enelement el "
                + "                                     where enp.code = eni.planrefcode "
                + "                                       and eni.countfact <> 0  "
                + "                                       and enp.kindcode = 2  "
                + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                + "                                       and enp.code = eni.planrefcode "
                + "                                       and el.code = enp.elementrefcode "
                + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                + "                                       and (enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                + "                                       and eni.statusrefcode in (1,2,3) "
                + "                                     UNION  /*для перенесенных материалов из других планов */  "
                + "                                     select  en2en.estimateiteminrefcode as enestimateitemcode  from enplanwork enp , enestimateitem eni "
                + "                               , enelement el , enestimateitem2nstmttm en2en "
                + "                                     where enp.code = eni.planrefcode "
                + "                                       and eni.countfact <> 0  "
                + "                                       and enp.kindcode = 2  "
                + "                                       and eni.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
                + "                                       and enp.code = eni.planrefcode "
                + "                                       and el.code = enp.elementrefcode "
                + "                                       and ( enp.yeargen = " + year + "  or 0 = " + year + " ) "
                + "                                       and ( enp.monthgen = " + monthgen +  " or 0  = " + monthgen + " ) "
                + "                                       and ( enp.budgetrefcode = " + budgcode + " or 0 = " + budgcode + " ) "
                + "                                       and ( enp.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
                + "                                       and ( el.typerefcode = " + elementtypecode + " or 0 = " +  elementtypecode + " ) "
                + "                                       and (enp.code = " + plancode + " or  0 = " + plancode +  " ) "
                + "                                       and eni.materialrefcode = " + codematerials /*проставляем код материала  tkmaterials*/
                + "                                       and eni.statusrefcode in (1,2,3) "
                + "                                       and eni.code = en2en.estimateitemoutrefcode "
                + "                                       and en2en.typerefcode in (2, 3 ) "
                + "                                     ) "
                + " and rqfkit.code = fi2ei.fkorderitemrefcode "
                + " and rqfkor.code = rqfkit.fkorderrefcode "
                + " and rqfkor.kindcode = 1 /*Приходный ордер*/ "
            //    + " and rqfk2part.fkorderitemrefcode = rqfkit.code " убрал т.к если приход не проведен то партии на нем нету
            //    + " and rqfk2part.estimateitemrefcode = fi2ei.estimateitemcode " убрал т.к если приход не проведен то партии на нем нету
                // + " and rqfkor.statuscode = 3 /*Проведеные ордера берем */  "  " убрал признак потомучто в развернутом отчете выводятся также черновые ордера  или составленые
                + " and rqfkor.moloutcode = " + moloutcode
                + " and upper(trim(rqfkit.nomenclaturenum)) = upper(trim( " + nomenclaturenum +  ")) ) " ;
}

if ( pr_sel_obj.intValue() == 0  )  {  // если признак выбора обьекта = 0 тогда сбрасываем запрос и не вибирается ниче .
    sql = " select ' ' ";
                                  }


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
//resultSet.next();
//    if (!resultSet.next()) {
//        throw new EnergyproSystemException("Count work time not found for date " + d);
//    }

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

//System.out.println("finally getObject:" + new Date());


try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}

public String getObjectStateFromPlan( String year , String monthgen ,  Integer budgcode , Integer rencode ,
        String elementtypecode ,  String plancode , Integer codematerials  )

throws PersistenceException
{
boolean isDebug = false;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

//System.out.println("start getWorkTimeInMonth:" + new Date());

//    Date firstDay = getFirstDayInMonth(d);

String sql =
    " Select distinct enelementdata.ename ||' Інв.№ '|| enelementdata.invnumber   as object from "
    + " enplanwork , enelementdata , enestimateitem "
    + " where ( enplanwork.yeargen = "  + year + " or "  + " 0  = " + year + " ) "
    + " and ( enplanwork.monthgen = " + monthgen + " or " + " 0 = " + monthgen + " ) "
    + " and enplanwork.kindcode = 2 "
    + " and enelementdata.ecode = enplanwork.elementrefcode "
    + " and enestimateitem.countfact <> 0 "
    + " and ( enplanwork.budgetrefcode = " +  budgcode + " or  0 = "  + budgcode +  " ) "
    + " and ( enplanwork.departmentrefcode = " + rencode + " or 0 = " + rencode + " ) "
    + " and (enelementdata.etype = " + elementtypecode + " or 0 = " + elementtypecode + " ) "
    + " and (enplanwork.code = " + plancode +  " or  0 = " + plancode + " ) "
    + " and enestimateitem.kindrefcode <> 3 /*не равно демонтованим материалам*/ "
    + " and enestimateitem.planrefcode = enplanwork.code "
    + " and enestimateitem.materialrefcode = " + codematerials  ;

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
//resultSet.next();
//    if (!resultSet.next()) {
//        throw new EnergyproSystemException("Count work time not found for date " + d);
//    }

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

//System.out.println("finally getObject:" + new Date());


try {if (resultSet != null) resultSet.close();} catch (SQLException e) {}
try {if (statement != null) statement.close();} catch (SQLException e) {}

try {
if  (connection != null && connection.isClosed())
    connection.close();
} catch (SQLException e) {
}
}
}


public String getDataNumberBillByStrOrder( Integer oicode , Integer pr_date_number , BigDecimal pprice )

throws PersistenceException
{
boolean isDebug = false;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getDataNumberBillByStrOrder:" + new Date());
}


String sql =
    " select distinct  to_char(billdate,'dd.mm.yyyy') as billdate  , billnumber  "
    + " from ( "
    + " select p2b.billitemcode as billitemrefcode, p.numbergen,  "
    + " p.dategen, pi.summagen, coalesce(lpay.lsum,0) as lsum , "
    + " bi.countfact , bi.pricewithoutnds  , rb.dategen as billdate , rb.numberdoc as billnumber  "
    + " from  rqbill rb  ,rqbillitem bi , rqpaydoc p, rqpaydocitem pi, rqpaydocitem2billitem p2b  "
    + " left join ( "
    + " select pi2bi2oi.billitemcode as billitemrefcode, coalesce(pi2bi2oi.summagen,0) as lsum "
    + " from  rqpaydoc p , rqpaydocitem pi, rqpitem2bitem2oitem pi2bi2oi, rqbillitem bi "
    + " where pi.paydocrefcode = p.code "
    + " and bi.code = pi2bi2oi.billitemcode "
    + " and pi2bi2oi.paydocitemcode = pi.code "
    + " and pi2bi2oi.orderitemcode = "  + oicode
    + " and pi2bi2oi.billitemcode in (select distinct bi.code as billitem "
    + "                               from rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, "
    + "                                    rqbill b, rqbillitem bi, rqbillitem2orderitem bo "
    + "                               where be.estimateitemcode = oe.estimateitemcode "
    + "                                and be.billitemcode = bo.billitemrefcode "
    + "                                and bo.orderitemrefcode = oe.orderitemcode "
    + "                                and bi.code = bo.billitemrefcode "
    + "                                and b.code = bi.billrefcode "
    + "                                and bo.orderitemrefcode = " + oicode
    + "                               ) "
    + " ) lpay on lpay.billitemrefcode = p2b.billitemcode "

    + "  where bi.billrefcode = rb.code  "
    + " and  bi.code  = p2b.billitemcode  "
    + " and  pi.paydocrefcode = p.code "
    + " and p2b.paydocitemcode = pi.code "
    + " and p2b.billitemcode in (select  distinct bi.code as billitem "
    + "                         from rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, "
    + "                              rqbill b, rqbillitem bi, rqbillitem2orderitem bo "
    + "                         where be.estimateitemcode = oe.estimateitemcode "
    + "                          and be.billitemcode = bo.billitemrefcode "
    + "                          and bo.orderitemrefcode = oe.orderitemcode "
    + "                          and bi.code = bo.billitemrefcode "
    + "                          and b.code = bi.billrefcode "
    + "                          and bo.orderitemrefcode = " +   oicode
    + "                         ) "
    + " and bi.pricewithoutnds =  " + pprice
    + " ) w "
    + " group by billdate , billnumber " ;

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

    if (isDebug){
        System.out.println("pr_date_number =  " + pr_date_number.intValue());
    }
if ( out.length() > 0)
{
    if (pr_date_number.intValue() == 1 ) {

    out = out + " , " +  resultSet.getString(1); // выбор дат для счетов через запятую
        }
    if (pr_date_number.intValue() == 2 ) {

    out = out + " , " +  resultSet.getString(2); // выбор номеров счетов через запятую
        }

}
else
{
    if (pr_date_number.intValue() == 1 ) {
        out = resultSet.getString(1);}
    if (pr_date_number.intValue() == 2 ) {
        out = resultSet.getString(2);}
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


public String getFactDataPostavkaByEstimateitem( String pstrcodeestimate ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = true ;

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
try {if (statement != null) statement.close();} catch (SQLException e) {}

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
    + "  distinct bi.pricewithoutnds "
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

        " select sum(distinct pi.summagen)    from rqbillitem2enestimattm r2en , rqpaydocitem2billitem rq2bill , "
        + " rqpaydocitem pi , rqpaydoc p "
        + " where r2en.estimateitemcode  in ( " + pstrcodeestimate + ") "
    + " and r2en.billitemcode = rq2bill.billitemcode "
    + " and rq2bill.paydocitemcode = pi.code "
    + " and p.code = pi.paydocrefcode "   ;

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


public String getNumberBillByEstimateitem( String pstrcodeestimate , Integer pr_period ) // для отчета виконання заявок

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getNumberBillByEstimateitem:" + new Date());
}


String sql =
    // " select distinct ' '||numberbill "
    " Select Distinct ' '|| "
    + " /*если кол-во в счете меньше кол-ва в заявке то нада счет не показывать (для строк заявок предыдущих периодов!!!! ) */ "
    + " case when ( "
    + "              ( select sum(bbb.countgen) from  rqbillitem2enestimattm bbb where bbb.estimateitemcode in ( " + pstrcodeestimate + "))"
    + "           < "
    + "           ( select sum(rrr.countgen) from  rqorderitem2enestimttm rrr where rrr.estimateitemcode in ( " + pstrcodeestimate + "))"
    + "            ) and ( -1 = " + pr_period + ")"
    + "     then  "
    + " '' "
    + "     else "
    + "    numberbill "
    + "     end "
    + " from ( "
    + " select  "
    + "  b.numberdoc as numberbill  "
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


public String getSumAsText( BigDecimal sum )

throws PersistenceException
{
boolean isDebug = true ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getSumAsText:" + new Date());
}


String sql =
    "Select textgrivna("+sum+")";

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

String out = new String("");
try {



statement = connection.prepareStatement(sql);
//statement.setBigDecimal(1, count);
//statement.setBigDecimal(2, count);
//statement.setBigDecimal(3, count);


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



System.out.println("sql : " + out);
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


public BigDecimal getKmTrafficGPS( Integer transportrealcode , String datestart  ) // вытаскиваем пробег по машинке на указанный день

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getKmTrafficGPS:" + new Date());
}


String sql = /*23.10.2012 NET-3367 Запрос был изменен*/
    " select kmbyglobus + kmbybenish from  \n " +
    " (select  \n " +
    " (select  \n " +
    " coalesce(sum(speedometerfinal - speedometerstart),0) from  \n " +
    " (select distinct  \n " +
    " coalesce(tv.speedometerfinal,0) as speedometerfinal, \n " +
    " coalesce(tv.speedometerstart,0) as speedometerstart, \n " +
    " tv.code as travelsheetitemcode \n " +
    " from entravelsheet as ts \n " +
    " inner join tktransportreal as re on ts.transportrealcode = re.code \n " +
    " inner join entravelsheetitem as tv on tv.travelsheetrefcode = ts.code \n " +
    " inner join entravlshttm2trnsprttm as tvti on tvti.travelsheetitemrefcode = tv.code \n " +
    " inner join entransportitem as ti on ti.code = tvti.transportitemrefcode \n " +
    " inner join enplanwork as pw on pw.code = ti.planrefcode \n " +
    " inner join entransport2enestimate as ties on ti.code = ties.transportrefcode \n " +
    " inner join enestimateitem as es on (ties.estimaterefcode = es.code) \n " +
    " inner join tktransportrealhistory as hi on re.code = hi.transportrealrefcode \n " +
    " where  \n " +
    " hi.datestart <= to_date('" + datestart + "','dd.mm.yyyy')  \n " +

    //" and hi.datefinal >= to_date('" + datestart + "','dd.mm.yyyy')  \n " +
	" and coalesce(hi.datefinal,to_date('31.12.9999','dd.mm.yyyy')) >= to_date('" + datestart + "','dd.mm.yyyy')  \n " +

    " and hi.reg_id is not null \n " +
    " and ts.datestart between hi.datestart and coalesce(hi.datefinal, to_date('31.12.9999','dd.mm.yyyy')) \n " +
    " and pw.datestart = to_date('" + datestart + "','dd.mm.yyyy') \n " +
    " and es.kindrefcode = 2 /*пмм*/ \n " +
    " and tv.kindrefcode = 2 /*фактические*/ \n " +
    " and re.code = " + transportrealcode + ") as travels) as kmbyglobus, \n " +
    " (select coalesce(sum(g.sumkm),0) as sumkm   \n " +
    " from entrafficgps g  \n " +
    " where  \n " +
    " g.dategen = to_date('" + datestart + "','dd.mm.yyyy') \n " +
    " and g.realtransportcode = " + transportrealcode + ") as kmbybenish) as kmquery \n ";

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

BigDecimal out = new BigDecimal(0);
try {



statement = connection.prepareStatement(sql);

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

public BigDecimal getFuelTrafficGPS( Integer transportrealcode , String datestart  ) // вытаскиваем пробег по машинке на указанный день

throws PersistenceException
{
boolean isDebug = false ;

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

if (isDebug){
System.out.println("start getFuelTrafficGPS:" + new Date());
}


String sql = /*23.10.2012 NET-3367 Запрос был изменен*/
    " select (fuelbyglobus + fuelbybenish) as fuelsum from  \n " +
    " (select  \n " +
    " (select coalesce(sum(es.countfact),0)  \n " +
    " from entravelsheet as ts \n " +
    " inner join tktransportreal as re on ts.transportrealcode = re.code \n " +
    " inner join entravelsheetitem as tv on tv.travelsheetrefcode = ts.code \n " +
    " inner join entravlshttm2trnsprttm as tvti on tvti.travelsheetitemrefcode = tv.code \n " +
    " inner join entransportitem as ti on ti.code = tvti.transportitemrefcode \n " +
    " inner join enplanwork as pw on pw.code = ti.planrefcode \n " +
    " inner join entransport2enestimate as ties on ti.code = ties.transportrefcode \n " +
    " inner join enestimateitem as es on (ties.estimaterefcode = es.code) \n " +
    " inner join tktransportrealhistory as hi on re.code = hi.transportrealrefcode \n " +
    " where  \n " +
    " hi.datestart <= to_date('" + datestart + "','dd.mm.yyyy')  \n " +

    //" and hi.datefinal >= to_date('" + datestart + "','dd.mm.yyyy')  \n " +
	" and coalesce(hi.datefinal,to_date('31.12.9999','dd.mm.yyyy')) >= to_date('" + datestart + "','dd.mm.yyyy')  \n " +

    " and hi.reg_id is not null \n " +
    " and ts.datestart between hi.datestart and coalesce(hi.datefinal, to_date('31.12.9999','dd.mm.yyyy')) \n " +
    " and pw.datestart = to_date('" + datestart + "','dd.mm.yyyy') \n " +
    " and es.kindrefcode = 2 /*пмм*/ \n " +
    " and tv.kindrefcode = 2 /*фактические*/ \n " +
    " and re.code = " + transportrealcode + ") as fuelbyglobus, \n " +
    " (select coalesce(sum(g.sumfuel),0)   \n " +
    " from entrafficgps g  \n " +
    " where  \n " +
    " g.dategen = to_date('" + datestart + "','dd.mm.yyyy') \n " +
    " and g.realtransportcode = " + transportrealcode + ") as fuelbybenish) as fuelquery \n ";

if (isDebug){
System.out.println("sql : " + sql);

}

PreparedStatement statement = null;
ResultSet  resultSet = null;

BigDecimal out = new BigDecimal(0);
try {



statement = connection.prepareStatement(sql);

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

public String getDepartmentCodesDown( int depCode ) throws PersistenceException
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
out = new ManningTableLogic(connection, userProfile).getDepartmentCodesDown(depCode);



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


    public String getTKclassificationtypeCodesDown(int classificationtypeCode)
            throws PersistenceException {
        boolean isDebug = true;

        JRFillParameter jrParameterMap = this.parametersMap
                .get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap
                .get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap
                .getValue()).get("userProfile");

        if (isDebug) {
            System.out.println("start getTKclassificationtypeCodesDown:"
                    + new Date());
        }

        String out = "";
        try {
            out = new ManningTableLogic(connection, userProfile)
                    .getTKclassificationtypeCodesDown(classificationtypeCode);

            return out;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return out;
        } finally {

            try {
                if (connection != null && connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }

public String getTKMaterialsCodesDown(int materialCode)    throws PersistenceException {
boolean isDebug = false;

JRFillParameter jrParameterMap = this.parametersMap
        .get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap
        .get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap
        .getValue()).get("userProfile");

if (isDebug) {
    System.out.println("start getTKMaterialsCodesDown:"
            + new Date());
}

String out = "";
try {
    out = new ManningTableLogic(connection, userProfile)
            .getTKMaterialsCodesDown(materialCode);

    // System.out.println("length = " + out.length() ) ;
    return out;
} catch (Exception e) {
    System.out.println(e.getMessage());
    return out;
} finally {

    try {
        if (connection != null && connection.isClosed())
            connection.close();
    } catch (SQLException e) {
    }
}
}

public JRDataSource getReestrPaymentDATA(int oicode ,
        String orderdate ,
        String budgetrefname ,
        String statussymbol,
        java.util.Date planneddatepays,
        String paymenttypename,
        int paymentvalue,
        BigDecimal plansumpay,
        String reestr_date,
        String billitemcode,
        String paymenttypename_initial,
        int paymentvalue_initial ,
        java.util.Date min_postavka_date,
        String startDate ,
        BigDecimal summa )
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

Statement tempSt = null;
ResultSet rs = null;

try {


    System.out.println(" start getReestrPaymentDATA ( oicode = " + oicode +" , billitemcode = " + billitemcode + ")");
    ArrayList rows  = new ArrayList();
    String query = "";
    boolean isPrihodFromBillItem = false;






    if (min_postavka_date != null ){ // выберем приходы

    // проверим можно ли приходы выбирать со связи строки прихода со строкой счета
    query =" select count(fi2bi.code) from rqfkorderitem2billitem fi2bi  , rqfkorderitem fi , rqfkorder f  \n" +
    " where  fi2bi.billitemrefcode in("+billitemcode+") \n" +
    " and ( select count(fi2bisub.code) from rqfkorderitem2billitem fi2bisub  \n" +
    "        where fi2bisub.fkorderitemrefcode = fi2bi.fkorderitemrefcode ) = 1 \n" +
   // " and (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode in("+billitemcode+") ) = 1      \n" +
   // " and (select count(bi2ei.code) from rqbillitem2enestimattm bi2ei where bi2ei.billitemcode in("+billitemcode+") ) = 1   \n" +
    " and fi.code = fi2bi.fkorderitemrefcode \n" +
    " and fi.fkorderrefcode = f.code \n" +
    " and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy') \n" ;


    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);

    if(rs.next()){

        if (rs.getInt(1) > 0 ) {
        isPrihodFromBillItem = true;
        }
    }


    if (isPrihodFromBillItem ){
        query = " select    \n" +
        "   countfact   \n" +
        "   ,pricewithnds   \n" +
        "   ,sumwithnds   \n" +
        "   ,dategen   \n" +
        "    ,( select     \n" +
        "         sum( (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then    \n" +
        "       ( ((select sum(q.countgen) from rqbillitem2enestimattm q  ,rqorderitem2enestimttm q2    \n" +
        "               where q.billitemcode =bi.code    \n" +
        "               and q.estimateitemcode = q2.estimateitemcode    \n" +
        "               and q2.orderitemcode  = "+oicode+"  )    \n" +
        "              /    \n" +
        "             (select sum(q.countgen) from rqbillitem2enestimattm q  ,rqorderitem2enestimttm q2    \n" +
        "               where q.billitemcode =bi.code    \n" +
        "               and q.estimateitemcode = q2.estimateitemcode    \n" +
        "             ))    \n" +
        "                     * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p    \n" +
        "                                           where pi2bi.paydocitemcode = pi.code    \n" +
        "                                             and pi2bi.billitemcode = bi.code    \n" +
        "                                             and pi.paydocrefcode = p.code    \n" +
        "                                             and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ),0) )::numeric(15,3)    \n" +
        "                      else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p    \n" +
        "                                           where pi2bi.paydocitemcode = pi.code    \n" +
        "                                             and pi2bi.billitemcode = bi.code    \n" +
        "                                             and pi.paydocrefcode = p.code    \n" +
        "                                             and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ),0)    \n" +
        "                      end  ) )  as summagen   \n" +
        "          from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode )    \n" +
        "                                          left join  rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code )    \n" +
        "                                          left join  rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy'))    \n" +
        "        where bi.billrefcode = b.code    \n" +
        "        and bi.code in ("+ billitemcode +") )  as p_sum      /*5*/   \n" +
        "      , (select oi.planneddatedelivery from rqorderitem oi where oi.code = "+oicode+"  ) as planneddatedelivery  /*6*/   \n" +
        "        , (select oi.planneddatepays from rqorderitem oi where oi.code = "+oicode+"  ) as planneddatepays /*7*/   \n" +
        "        , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+oicode+"  ) as typepayrefcode /*8*/   \n" +
        "        , (select tp.name from rqorderitem oi  , rqorderitemtypepay tp where oi.code = "+oicode+" and oi.typepayrefcode = tp.code ) as rqorderitemtypepay_name  \n" +
        " /*9*/   \n" +
        "        , (select oi.paymentvalue from rqorderitem oi where oi.code = "+oicode+"  ) as paymentvalue /*10*/   \n" +
        "        , (select ' '||b.numberdoc from rqbill b , rqbillitem bi where b.code = bi.billrefcode and bi.code in ("+ billitemcode +") limit 1 ) as billnumber /*11*/   \n" +
        "        , case when first_day(to_date('"+startDate+"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =     \n" +
        "        ri.orderrefcode and ri.code = "+oicode+" limit 1 ) then 1 else 0 end as order_is_cur_period     /*12*/   \n" +
        "        /*сумма оплат до даты первого прихода*/     \n" +
        "        , coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p     \n" +
        "                                      where pi2bi.paydocitemcode = pi.code     \n" +
        "                                        and pi2bi.billitemcode in ("+ billitemcode +")    \n" +
        "                                        and pi.paydocrefcode = p.code    \n" +
        "                                        and p.dategen <= (select  get_min_date_fkorder_by_orderitem("+oicode+" , 1/*статусы ордеров все*/, '"+startDate+"'  ) ) ),0) as    \n" +
        "   sum_predoplat  /*13 сумма предоплат*/    \n" +
        "        , (select max(p.dategen) as dategen_pay from rqbillitem bi , rqbill b  , rqpaydocitem pi , rqpaydoc p , rqpaydocitem2billitem pi2bi    \n" +
        "    where bi.billrefcode = b.code    \n" +
        "    and pi2bi.billitemcode = bi.code    \n" +
        "    and pi2bi.paydocitemcode = pi.code    \n" +
        "    and pi.paydocrefcode = p.code    \n" +
        "    and bi.code in ("+ billitemcode +")   \n" +
        "    and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ) as dategen_pay  /*14 максимальн дата оплаты*/   \n" +
        "    from    \n" +
        "    (   \n" +
        "   select   \n" +
        "   sum(countfact) as countfact  \n" +
        "   ,pricewithnds  \n" +
        "   ,sum(countfact * pricewithnds)::numeric(15,3) as sumwithnds   \n" +
        "   ,dategen  \n" +
        "    from    \n" +
        "    (  \n" +
        "   /*количество из развязки по естиметам  с приходными строками и счетами  */     \n" +
        "      select  ( select sum(fi2eni.countgen)        \n" +
        "       from rqfkorderitem2enstmttm fi2eni , rqorderitem2enestimttm oi2eni  , rqfkorderitem fi , rqfkorder f   \n" +
        "          where oi2eni.orderitemcode = "+oicode+"   \n" +
        "            and fi2eni.estimateitemcode = oi2eni.estimateitemcode    \n" +
        "            and fi2eni.fkorderitemrefcode = fi.code    \n" +
        "            and fi.fkorderrefcode = f.code    \n" +
        "            and f.kindcode in (1,15)    \n" +
        "            and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')   \n" +
        "            and fi.code in (select distinct fi2bia.fkorderitemrefcode from rqfkorderitem2billitem fi2bia    \n" +
        "                                             where  fi2bia.billitemrefcode in("+ billitemcode +")   \n" +
        "                                           )   \n" +
        "            and fi.code = fi_par.code    \n" +
        "          ) + /*к количеству по естимейтам прибавим излишки на приходной строке под бюджетодержателя без естимейта   */   \n" +
        "              /* !!!излишки будем искать только если количество в приходе по естимейтам меньше чем количество в счете ИЗ ЗАЯВКИ  */   \n" +
        "            case  when  coalesce((select sum(bi2oi.countfact) from rqbillitem2orderitem bi2oi  \n" +
        "                                   where bi2oi.billitemrefcode in ("+ billitemcode +") \n" +
        "                                   and bi2oi.orderitemrefcode = "+oicode+"  \n" +
        "                                   ),0) > \n" +
        "            (   coalesce(( select sum(q.countgen) from rqfkorderitem2enstmttm q ,  rqorderitem2enestimttm oi2ei2 \n" +
        "                    where q.finmaterialsrefcode is null \n" +
        "                      and   q.estimateitemcode in (select qq.estimateitemcode from rqbillitem2enestimattm qq \n" +
        "                                                    where qq.billitemcode  IN ("+ billitemcode +") )  \n" +
        "                      and q.estimateitemcode = oi2ei2.estimateitemcode  \n" +
        "                      and oi2ei2.orderitemcode = "+oicode+"  \n" +
        "                  ),0) \n" +
        "                                  ---- \n" +
        "         +  /*к количеству по естимейтам прибавим излишки на приходной строке под бюджетодержателя с естимейтом  */ \n" +
        "    coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem    \n" +
        "           where fkrem.estimateitemrefcode in     \n" +
        "                  (select  distinct oi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es \n" +
        "                              , rqfkorderitem  fi ,rqfkorder f      \n" +
        "                            where oi2ei.orderitemcode =  "+oicode+" \n" +
        "                            and bi2ei.billitemcode IN ("+ billitemcode +")     \n" +
        "                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode \n" +
        "                            and fi2es.estimateitemcode = oi2ei.estimateitemcode      \n" +
        "                            and fi.code = fi2es.fkorderitemrefcode      \n" +
        "                            and fi.fkorderrefcode = f.code      \n" +
        "                            and f.kindcode in (1,15)     \n" +
        "                            and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy') \n" +
        "                   )    \n" +
        "            and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+oicode+"  ) \n" +
        "            and fkrem.estimateitemrefcode is not null        \n" +
        "            and fkrem.fkorderitemrefcode = fi_par.code \n" +
        "         ),0) \n" +
        "         ) \n" +
        "         ---- \n" +
        "                  then   \n" +
        "      \n" +
        "                 coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem    \n" +
        "                        where fkrem.fkorderitemrefcode in     \n" +
        "                               (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es     \n" +
        "                                           , rqfkorderitem  fi ,rqfkorder f      \n" +
        "                                         where oi2ei.orderitemcode =  "+oicode+"       \n" +
        "                                         and bi2ei.billitemcode IN ("+ billitemcode +")     \n" +
        "                                         and bi2ei.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                         and fi2es.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                         and fi.code = fi2es.fkorderitemrefcode      \n" +
        "                                         and fi.fkorderrefcode = f.code      \n" +
        "                                         and f.kindcode in (1,15)     \n" +
        "                                         and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')     \n" +
        "                                )    \n" +
        "                         and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+oicode+"   )      \n" +
        "                         and fkrem.estimateitemrefcode is null     \n" +
        "                         and fkrem.fkorderitemrefcode = fi_par.code       \n" +
        "                      ),0) \n" +
        "             else 0  \n" +
        "             end        \n" +
        "         + /*к количеству по естимейтам прибавим излишки на приходной строке под бюджетодержателя с естимейтом  */     \n" +
        "           coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem    \n" +
        "                  where fkrem.estimateitemrefcode in     \n" +
        "                         (select  distinct oi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es     \n" +
        "                                     , rqfkorderitem  fi ,rqfkorder f      \n" +
        "                                   where oi2ei.orderitemcode =  "+oicode+"       \n" +
        "                                   and bi2ei.billitemcode IN ("+ billitemcode +")     \n" +
        "                                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                   and fi2es.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                   and fi.code = fi2es.fkorderitemrefcode      \n" +
        "                                   and fi.fkorderrefcode = f.code      \n" +
        "                                   and f.kindcode in (1,15)     \n" +
        "                                   and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')     \n" +
        "                          )    \n" +
        "                   and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+oicode+"  )    \n" +
        "                   and fkrem.estimateitemrefcode is not null        \n" +
        "                   and fkrem.fkorderitemrefcode = fi_par.code       \n" +
        "                ),0)           \n" +
        "         as countfact    \n" +
        "        ,(fi_par.pricewithoutnds*(1+ coalesce(f.ndspercent,0)/100))::numeric(15,3) as pricewithnds               \n" +
        "        ,f.dategen     \n" +
        "      from rqfkorderitem2billitem fi2bi  , rqfkorderitem fi_par , rqfkorder f    \n" +
        "        where  fi2bi.fkorderitemrefcode in (select distinct fi2bia.fkorderitemrefcode from rqfkorderitem2billitem fi2bia    \n" +
        "                                             where  fi2bia.billitemrefcode in("+ billitemcode +")   \n" +
        "                                           )   \n" +
        "        and fi2bi.fkorderitemrefcode = fi_par.code     \n" +
        "        and fi_par.fkorderrefcode = f.code     \n" +
        "        and f.kindcode in (1,15)    \n" +
        "        and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')   \n" +
        "        /*add*/ and fi2bi.billitemrefcode in ("+ billitemcode +")  \n" +
        "        group by f.dategen , fi_par.pricewithoutnds , ndspercent , fi_par.code    \n" +
        "    ) as sel1    \n" +
        "    group by pricewithnds , dategen   \n" +
        "    order by dategen  \n" +
        "    ) as sel2   \n" +
        "  \n" ;








    }
    else {



     query = " select countfact_postavka::numeric(15,6) +  \n" + // плюсуем поидее его излишки без естимейта
     " coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem               \n" +
     "  where fkrem.fkorderitemrefcode in  \n" +
     "         (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es  \n" +
     "                     , rqfkorderitem  fi ,rqfkorder f   \n" +
     "                   where oi2ei.orderitemcode =  "+ oicode + "    \n" +
     "                   and bi2ei.billitemcode IN ("+ billitemcode +")  \n" +
     "                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode    \n" +
     "                   and fi2es.estimateitemcode = oi2ei.estimateitemcode    \n" +
     "                   and fi.code = fi2es.fkorderitemrefcode   \n" +
     "                   and fi.fkorderrefcode = f.code   \n" +
     "                   and f.kindcode in (1,15)  \n" +
     "                   and f.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')  \n" +
     "        ) \n" +
     "  and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+ oicode + "  )    and fkrem.estimateitemrefcode is null   ),0) \n" +
     "       as countfact, \n" +
     "  \n" +

    "        pricewithnds::numeric(15,3) as pricewithnds , \n" +
    "        (  ( countfact_postavka::numeric(15,6) +  \n" + // плюсуем поидее его излишки без естимейта
    " coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem               \n" +
    "  where fkrem.fkorderitemrefcode in  \n" +
    "         (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es  \n" +
    "                     , rqfkorderitem  fi ,rqfkorder f   \n" +
    "                   where oi2ei.orderitemcode =  "+ oicode + "    \n" +
    "                   and bi2ei.billitemcode IN ("+ billitemcode +")  \n" +
    "                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode    \n" +
    "                   and fi2es.estimateitemcode = oi2ei.estimateitemcode    \n" +
    "                   and fi.code = fi2es.fkorderitemrefcode   \n" +
    "                   and fi.fkorderrefcode = f.code   \n" +
    "                   and f.kindcode in (1,15)  \n" +
    "                   and f.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')  \n" +
    "        ) \n" +
    "  and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+ oicode + "  )     and fkrem.estimateitemrefcode is null  ),0) \n" +
    " ) \n" +
    " * pricewithnds)::numeric(15,3) as sumwithnds \n" +
    "         ,dategen \n" +
    ",( select  \n" +
    "  sum( (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then \n" +
   " ( ((select sum(q.countgen) from rqbillitem2enestimattm q  ,rqorderitem2enestimttm q2 \n" +
   "         where q.billitemcode =bi.code \n" +
   "         and q.estimateitemcode = q2.estimateitemcode \n" +
   "         and q2.orderitemcode  = "+ oicode + "  ) \n" +
   "         / \n" +
   "       (select sum(q.countgen) from rqbillitem2enestimattm q  ,rqorderitem2enestimttm q2 \n" +
   "         where q.billitemcode =bi.code \n" +
   "         and q.estimateitemcode = q2.estimateitemcode \n" +
   "        )) \n" +
    "              * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
    "                                    where pi2bi.paydocitemcode = pi.code \n" +
    "                                      and pi2bi.billitemcode = bi.code \n" +
    "                                      and pi.paydocrefcode = p.code \n" +
    "                                      and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') ),0) )::numeric(15,3) \n" +
    "               else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
    "                                    where pi2bi.paydocitemcode = pi.code \n" +
    "                                      and pi2bi.billitemcode = bi.code \n" +
    "                                      and pi.paydocrefcode = p.code \n" +
    "                                      and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
    "               end  ) )  as summagen \n" +
    "   from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
    "                                   left join  rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code ) \n" +
    "                                   left join  rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')) \n" +
    "  \n" +
    " where bi.billrefcode = b.code \n" +
    " and bi.code in ("+ billitemcode +") )  as p_sum \n" +



    " , (select oi.planneddatedelivery from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatedelivery \n" + // 6
    " , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatepays \n" + // 7
    " , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode + "  ) as typepayrefcode \n" + // 8
    " , (select tp.name from rqorderitem oi  , rqorderitemtypepay tp where oi.code = "+ oicode + " and oi.typepayrefcode = tp.code ) as rqorderitemtypepay_name \n" + // 9
    " , (select oi.paymentvalue from rqorderitem oi where oi.code = "+ oicode + "  ) as paymentvalue \n" + // 10
    " , (select ' '||b.numberdoc from rqbill b , rqbillitem bi where b.code = bi.billrefcode and bi.code in ("+ billitemcode +") limit 1 ) as billnumber \n" + //11
    " , case when first_day(to_date('"+ startDate +"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =  \n" +
    " ri.orderrefcode and ri.code = "+ oicode + " limit 1 ) then 1 else 0 end as order_is_cur_period \n" +    // 12
    " /*сумма оплат до даты первого прихода*/  " +
    " , coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p  " +
    "                               where pi2bi.paydocitemcode = pi.code " +
    "                                 and pi2bi.billitemcode in ("+ billitemcode +")" +
    "                                 and pi.paydocrefcode = p.code " +
    "                                 and p.dategen <= (select  get_min_date_fkorder_by_orderitem("+ oicode + " , 1/*статусы ордеров все*/, '"+ startDate+ "'  ) ) ),0) as sum_predoplat " +  // 13 сумма предоплат
    " , (select max(p.dategen) as dategen_pay from rqbillitem bi , rqbill b  , rqpaydocitem pi , rqpaydoc p , rqpaydocitem2billitem pi2bi " +
" where bi.billrefcode = b.code " +
" and pi2bi.billitemcode = bi.code " +
" and pi2bi.paydocitemcode = pi.code " +
" and pi.paydocrefcode = p.code " +
    " and bi.code in ("+ billitemcode +")" + " ) as dategen_pay  \n" + // 14 максимальн дата оплаты
    " from \n" +
    " ( \n" +
    " select sum(countfact) + sum(countremander) as countfact_postavka , pricewithnds , dategen \n" +
    " from ( \n" +
    " /*поставка приход с выбором партий */ \n" +
    " select \n" +
    " rqfkorderitem2enstmttm.estimateitemcode , \n" +
    " iord.code as prihordercode , \n" +
    " iord.numberdoc, \n" +
    " iord.dategen, \n" +
    " rqfkorderitem2enstmttm.countgen as countfact, \n" +
    " ii.measurementnametxt \n" +
    "  , case when (select coalesce(b.vat,0)  from rqbillitem2enestimattm bi2e , rqbillitem bi , rqbill b \n" +
    "                      where bi2e.billitemcode = bi.code \n" +
    "                         and bi.billrefcode = b.code \n" +
    "                       and bi2e.estimateitemcode in ( select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei   \n" +
    " ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode + " \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode) \n" +
    "                       limit 1 \n" +
    "                 ) > 0 then \n" +
    "             round(cast(ii.pricewithoutnds* (select 1+coalesce(b.vat,0)/100::numeric from rqbillitem bi , rqbill b \n" + // NET-4284 ндс
    "                                              where bi.billrefcode = b.code  \n" +
    "                                                and bi.code in (  " +  billitemcode + " ) \n" +
    "                                             limit 1 ) as numeric),3) \n" +
    "                                   else \n" +
    "             round(cast(ii.pricewithoutnds as numeric),3) end \n" +
    "   as pricewithnds , \n" +
    " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, \n" +
    "  0 as findoccode, \n" +
    " /*выбор кол-во излишки */ \n" +
    " 0 as countremander, \n" +
    "  iord.statuscode, \n" +
    "  rqfkorderstatus.name as orderstatusname \n" +
    " from rqfkorderitem ii \n" +
    "       left join rqfkorderitem2enstmttm \n" +
    "          on ( rqfkorderitem2enstmttm.fkorderitemrefcode = ii.code \n" +
    "               and coalesce(rqfkorderitem2enstmttm.countgen,0) <> 0 ) \n" +
    " , rqfkorder iord left join  findoc2fkorder ffkord on (iord.code = ffkord.fkorderrefcode)  , rqfkorderstatus \n" +
    " where iord.code = ii.fkorderrefcode \n" +
    " and iord.statuscode = rqfkorderstatus.code \n" +
    " and iord.kindcode in (1,15) \n" +
    " and iord.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    " and rqfkorderitem2enstmttm.estimateitemcode in ( select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode  ) \n" +
    " and ii.code not in ( select rqfkorderitem2billitem.fkorderitemrefcode from rqfkorderitem2billitem   , rqbillitem \n" +
    ",     rqpaydocitem2billitem p2b ,  rqpaydocitem pi , rqpaydoc p \n" +
    " where rqbillitem.code = rqfkorderitem2billitem.billitemrefcode   \n" +
    " and coalesce(rqbillitem.staterefcode, 0) = 1 \n" +
    " and p2b.billitemcode = rqbillitem.code  \n" +
    " and p2b.paydocitemcode = pi.code  \n" +
    " and p.code = pi.paydocrefcode \n" +
    " and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "          ) \n" +
    "  \n" +
    " group by iord.code , iord.numberdoc, iord.dategen, ii.measurementnametxt, \n" +
    " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, ii.pricewithoutnds , \n" +
    " iord.kindcode , iord.statuscode  , ii.countgen , ii.sumwithoutnds , ii.code , rqfkorderstatus.name \n" +
    " , rqfkorderitem2enstmttm.countgen , rqfkorderitem2enstmttm.estimateitemcode \n" +
    "  \n" +
    " UNION /*выборка из remainder не излишков а приходов под планы */ \n" +
    "  \n" +
    "  select \n" +
    "       rqfkorderitemremainder.estimateitemrefcode as estimateitemcode \n" +
    "     , rqfkorder.code as prihordercode \n" +
    "     , rqfkorder.numberdoc \n" +
    "     , rqfkorder.dategen \n" +
    "     , rqfkorderitemremainder.countgen as countfact \n" +
    "     , rqfkorderitem.measurementnametxt \n" +
    "     , case when (select coalesce(b.vat,0)  from rqbillitem2enestimattm bi2e , rqbillitem bi , rqbill b \n" +
    "                      where bi2e.billitemcode = bi.code \n" +
    "                         and bi.billrefcode = b.code \n" +
    "                       and bi2e.estimateitemcode in (  select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei   \n" +
    " ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode ) \n" +
    "                       limit 1 \n" +
    "                 ) > 0 then \n" +
    "               round(cast(rqfkorderitemremainder.pricewithoutnds* (select 1+coalesce(b.vat,0)/100::numeric from rqbillitem bi , rqbill b \n" + // NET-4284 ндс
    "                                              where bi.billrefcode = b.code  \n" +
    "                                                and bi.code in (  " +  billitemcode + " ) \n" +
    "                                             limit 1) as numeric),3) \n" +
    "                       else \n" +
    "               round(cast(rqfkorderitemremainder.pricewithoutnds as numeric),3) \n" +
    "                       end \n" +
    "      as pricewithnds \n" +
    "     , rqfkorder.molincode \n" +
    "     , rqfkorder.molinname \n" +
    "     , rqfkorder.moloutcode \n" +
    "     , rqfkorder.moloutname \n" +
    "     , 0 as findoccode \n" +
    "     , 0 as countremander \n" +
    "     , rqfkorder.statuscode \n" +
    "     , rqfkorderstatus.name as orderstatusname \n" +
    "     from rqfkorderitemremainder , rqfkorderitem , rqfkorder , rqfkorderstatus \n" +
    "     where rqfkorderitemremainder.estimateitemrefcode in \n" +
    "                                             (  select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode ) \n" +
    "       and rqfkorderitemremainder.typerefcode = 2 \n" +
    "       and rqfkorderitemremainder.fkorderitemrefcode = rqfkorderitem.code \n" +
    "       and rqfkorderitem.fkorderrefcode = rqfkorder.code \n" +
    "       and rqfkorder.kindcode in (1,15) \n" +
    "       and rqfkorder.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "       and rqfkorder.statuscode = rqfkorderstatus.code \n" +
    " and rqfkorderitem.code not in ( select rqfkorderitem2billitem.fkorderitemrefcode from rqfkorderitem2billitem   , rqbillitem \n" +
    ",     rqpaydocitem2billitem p2b ,  rqpaydocitem pi , rqpaydoc p \n" +
    " where rqbillitem.code = rqfkorderitem2billitem.billitemrefcode   \n" +
    " and coalesce(rqbillitem.staterefcode, 0) = 1 \n" +
    " and p2b.billitemcode = rqbillitem.code  \n" +
    " and p2b.paydocitemcode = pi.code  \n" +
    " and p.code = pi.paydocrefcode \n" +
    " and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "          )  \n" +
    " ) w \n" +
    "  group by pricewithnds , dategen \n" +
    "  ) as q order by dategen \n" ;
    }


    // DEBUG!!1
    // System.out.println(" query = " + query);


    //Statement tempSt;
    tempSt.clearBatch();
    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);



        BigDecimal tmpSum = new BigDecimal(0);
         BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
         BigDecimal PAY_FACT_SUMMA = new BigDecimal(0);
         Date PAY_PLAN_DATE = null;
         String PAY_TYPE_NAME = "";
         int PAY_TYPE_VALUE = 0;
         Date PAY_DATE = null;
         int PAY_SIGN = 0;
         Date PAY_FACT_DATE = null;
         int order_is_cur_period = 0;
         int typepayrefcode = 0;
         Date planneddatepays_initial = null;
         Date fact_postavka_date = null;
         BigDecimal sum_predoplat = new BigDecimal(0);
         String billnumber = "";
         DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
         Date startDT = formatter.parse(startDate);
         BigDecimal pricewithnds = new BigDecimal(0);
         BigDecimal countfact_postavka = new BigDecimal(0);
         BigDecimal sumByFkOrderAll = new BigDecimal(0);
         Date planneddatedelivery = null;
         BigDecimal paymentsByBillItems = new BigDecimal(0); // общ сумма оплат по билл итемам
         while(rs.next())  /// нашли приходы
            {
                order_is_cur_period = rs.getInt(12);
                typepayrefcode = rs.getInt(8);
                planneddatepays_initial = rs.getDate(7);
                billnumber = rs.getString(11);
                fact_postavka_date = rs.getDate(4);
                pricewithnds =rs.getBigDecimal(2);
                countfact_postavka = rs.getBigDecimal(1);
                planneddatedelivery = rs.getDate(6);

                HashMap hashMap = new HashMap();
                hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, rs.getBigDecimal(1));
                hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, rs.getBigDecimal(2));
                hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, rs.getBigDecimal(3));
                hashMap.put(ReestrPaymentDS.PRIHOD_DATE,rs.getDate(4));

                BigDecimal sumByFkOrder = rs.getBigDecimal(3); // сумма в приходном
                sumByFkOrderAll = new BigDecimal(sumByFkOrderAll.doubleValue() + sumByFkOrder.doubleValue());
                paymentsByBillItems = rs.getBigDecimal(5); // общ сумма оплат по билл итемам
                if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue()  >= sumByFkOrder.doubleValue()  ){
                    PAY_FACT_SUMMA = sumByFkOrder; // если оплаты больше или равны сумме в приходном то сумму факт оплаты кидаем такую как в ордере
                    PAY_PLAN_SUMMA = new BigDecimal(0);// все оплатили все харашо платить больше не буим
                    PAY_TYPE_NAME = "Кінцевий розрахунок";
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(sumByFkOrder);
                } else
                {     if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue() > 0 ){
                    PAY_FACT_SUMMA = new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()); // если разница меньше
                    PAY_TYPE_NAME = "Кінцевий розрахунок"; //
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()));
                    } else{
                        PAY_FACT_SUMMA = new BigDecimal(0);
                        PAY_TYPE_NAME = "Кінцевий розрахунок";
                        PAY_TYPE_VALUE = 100;
                    }
                // перем = сумма по счету - сумма всех оплат
                BigDecimal restSumma = new BigDecimal( summa.doubleValue()-paymentsByBillItems.doubleValue() ).setScale(3, BigDecimal.ROUND_HALF_UP);


                PAY_PLAN_SUMMA = new BigDecimal(sumByFkOrder.doubleValue() - PAY_FACT_SUMMA.doubleValue()).setScale(3, BigDecimal.ROUND_HALF_UP);
                if (restSumma.doubleValue() < PAY_PLAN_SUMMA.doubleValue()  ){
                    PAY_PLAN_SUMMA = restSumma;
                }

                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
                }


                if (typepayrefcode == 1){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, 7); // если строка заявки по предоплате то факт поставки + 7 дней
                } else if (typepayrefcode == 2){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, paymentvalue_initial ); // если по факту то факт поставки + значение на строке заявки
                }

            // если плановая дата поставки со строки заявки больше чем PAY_PLAN_DATE которую высчитали то нужно принимать плановую дату поставки как дата оплаты

                if (planneddatedelivery.after(PAY_PLAN_DATE) ){
                    PAY_PLAN_DATE = planneddatedelivery;

                }



                if (PAY_PLAN_DATE.getDate() > 25){
                        PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                    } else {
                        PAY_DATE = PAY_PLAN_DATE;
                    }

                    if (PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() > 0 && (PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)) ){
                        PAY_SIGN = 1;
                    }
                    else {
                        PAY_SIGN = 0;
                    }

                    if (PAY_DATE.after(startDT)) {
                        PAY_PLAN_SUMMA = new BigDecimal(0);
                    }

                    try{
                        PAY_FACT_DATE = rs.getDate(14);
                        } catch (Exception e) {
                            // PAY_FACT_DATE =null;
                            throw new ReportSystemException(e);
                            }
                    PAY_FACT_SUMMA = PAY_FACT_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP);

                    if (PAY_FACT_SUMMA.doubleValue()<= 0){
                        PAY_FACT_DATE = null;
                    }

                hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA, PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP));
                hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
                hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,PAY_FACT_DATE);
                hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,pricewithnds);
                hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,PAY_FACT_SUMMA);
                hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,countfact_postavka);
                hashMap.put(ReestrPaymentDS.BILL_NUM,billnumber);
                hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
                hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
                hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
                hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
                hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
                hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
                hashMap.put(ReestrPaymentDS.BILLITEMCODES,billitemcode);


                rows.add(hashMap);
            }

         sumByFkOrderAll = sumByFkOrderAll.setScale(3, BigDecimal.ROUND_HALF_UP);
            // после того как известны суммы из приходов сравним с суммой по счету если остаток больше 0 тогда создать строку суммы на разницу (сумма счета минус сумма по приходам )
            if (
                ((summa.doubleValue() - sumByFkOrderAll.doubleValue() > 0 ) && sumByFkOrderAll.doubleValue() > 0
                && typepayrefcode == 1  // 13.08.2013 генерить пустую строку только если тип оплаты по заявке стоит предоплата
                ) ||
            // 14.08
                ( // если тип предоплата и был приход и дата плановой оплаты (к дате прихода + 7 дней ) больше чем дата формирования отчета
                // и по выбраной строке небыло фактич оплаты или фактические оплаты по приходу меньше чем должна была быть предоплата
                        // то плановую сумму оплаты необходимо расчитать так что бы отображалась предоплата
                        ( typepayrefcode == 1 && PAY_PLAN_DATE.after(startDT)
                                && paymentsByBillItems.doubleValue() == 0 // оплат небыло
                                )


                )
                )
            //\\ 14.08
            {
                // определим PAY_PLAN_SUMMA и PAY_PLAN_DATE (проверим делали ли предоплату (предоплатой считаем те оплаты которые были раньше даты первого прихода по строке заявки  ))
                // если что то не доплатили то ничего не платим и дату оплаты со строки заявки
                // если тип строки предоплата - сравним сумму которую должны были заплатить по предоплате
                if (typepayrefcode == 1){
                    // 14.08
                    if ( typepayrefcode == 1 // вид оплаты предоплата
                            && PAY_PLAN_DATE.after(startDT) // плановая дата оплаты по последнему приходу получилась больше чем дата формирования отчета
                                && paymentsByBillItems.doubleValue() == 0 // оплат небыло
                        ){
                            PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - paymentsByBillItems.doubleValue());

                            if (order_is_cur_period != 1 ){ // если предыдущий месяц то на первый день месяца

                                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                                PAY_TYPE_NAME = paymenttypename_initial;
                                PAY_TYPE_VALUE = paymentvalue_initial;
                            } else // иначе с поля в заявке
                            {

                                PAY_PLAN_DATE =  planneddatepays_initial;
                                PAY_TYPE_NAME = paymenttypename_initial;
                                PAY_TYPE_VALUE = paymentvalue_initial;
                            }
                        }
                    //\\ 14.08
                    // разница за вычетом приходов
                else if( ( (summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) > sum_predoplat.doubleValue() )
                    { // предоплата была меньше или вообще не было
                        PAY_PLAN_SUMMA = new BigDecimal( ((summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) -  sum_predoplat.doubleValue()
                        // отнимем сумму оплат которые не разнесены по приходам (т.е сумма по оплатам больше чем приходы)
                                -
                                (paymentsByBillItems.doubleValue()- tmpSum.doubleValue())
                        );
                        if (order_is_cur_period != 1 ){ // если предыдущий месяц то на первый день месяца

                                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                                PAY_TYPE_NAME = paymenttypename_initial;
                                PAY_TYPE_VALUE = paymentvalue_initial;
                        } else // иначе с поля в заявке
                        {

                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                            PAY_TYPE_VALUE = paymentvalue_initial;
                        }
                    }

                    else
                    {
                        PAY_PLAN_SUMMA = new BigDecimal(0); // заплатили в полном объеме
                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                        PAY_TYPE_VALUE = paymentvalue_initial;
                    }
                    }
                else if (typepayrefcode == 2) { // если тип строки по факту то ждем факта и на него потом разнесется сумма оплат
                    // PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue()-sumByFkOrderAll.doubleValue()
                            // отнимем сумму оплат которые не разнесены по приходам (т.е сумма по оплатам больше чем приходы)
                            -
                            (paymentsByBillItems.doubleValue()- tmpSum.doubleValue())
                            );
                    PAY_PLAN_DATE =  planneddatepays_initial;
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                    }

                PAY_PLAN_SUMMA = PAY_PLAN_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP);

                if (PAY_PLAN_DATE.getDate() > 25){
                    PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                } else {
                    PAY_DATE = PAY_PLAN_DATE;
                }

                if (PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() > 0 && (PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)) && typepayrefcode != 2  ){
                    PAY_SIGN = 1;
                }
                else {
                    PAY_SIGN = 0;
                }

                if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                }


                if (PAY_PLAN_SUMMA.doubleValue() > 0){
                        HashMap hashMap = new HashMap();
                        hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_DATE,null);
                        hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP));
                        hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,null);
                        hashMap.put(ReestrPaymentDS.BILL_NUM,billnumber);
                        hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
                        hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                        hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
                        hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
                        hashMap.put(ReestrPaymentDS.BILLITEMCODES,billitemcode);
                        rows.add(hashMap);
                }
            }



    rs.close();
    tempSt.close();

    } else {
  //!!!!!!!!!!!!!!!!!!!!!!1 если не нашли приходы значит вставить строку из параметров + определить оплаты

        query = " select datepay , pricewithoutnds , sum(summagen) as summagen , sum(countfact) as countfact , ' '||numberdocbill as numberdocbill \n" +
        " , case when first_day(to_date('"+ startDate +"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =  \n" +
        " ri.orderrefcode and ri.code = "+ oicode + " limit 1 ) then 1 else 0 end as order_is_cur_period \n" +    // 6
        " , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode + "  ) as typepayrefcode \n" + // 7
        " , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatepays \n" + // 8
        "  \n" +
        "  from  \n" +
        // " (select to_char(p.dategen,'dd.mm.yyyy') as datepay \n" +
        " (select p.dategen as datepay \n" +
        "  , bi.pricewithoutnds \n" +
        "  , (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then \n" +
        "             ( (select countfact from rqorderitem where code = "+ oicode + " ) / (select sum(oi.countfact) from rqbillitem2orderitem bi2oi , rqorderitem oi  \n" +
        " where bi2oi.billitemrefcode = bi.code  and  bi2oi.orderitemrefcode = oi.code ) \n" +
        "              * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) )::numeric(15,3) \n" +
        "               else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
        "               end  ) as summagen \n" +
        "  , sum(bi.countfact) as  countfact \n" +
        "  , ''||b.numberdoc as numberdocbill \n" +
        "   from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
        "                                   left join rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code ) \n" +
        "                                   left join rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ) \n" +
        "  \n" +
        "  \n" +
        " where bi.billrefcode = b.code \n" +
        " and bi.code IN ("+ billitemcode +") \n" +
        " group by bi.pricewithoutnds , p.dategen , b.numberdoc , bi.code )  as sel_bill_pay \n" +
        "  \n" +
        " group by datepay , pricewithoutnds , numberdocbill \n";


        tempSt = connection.createStatement();
        rs = tempSt.executeQuery(query);

        BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
        Date PAY_PLAN_DATE = null;
        String PAY_TYPE_NAME = "";
        int PAY_TYPE_VALUE = 0;
        Date PAY_DATE = null;
        int PAY_SIGN = 0;
        Date PAY_FACT_DATE = null;
        BigDecimal PAY_FACT_PRICE = null;
        //
        BigDecimal sumpay = new BigDecimal(0);
        int order_is_cur_period = 0 ;
        int typepayrefcode = 0;
        Date planneddatepays_initial = null;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date startDT = formatter.parse(startDate);
        while(rs.next())  ///
        {
            order_is_cur_period = rs.getInt(6);
            typepayrefcode = rs.getInt(7);
            sumpay = rs.getBigDecimal(3);
            planneddatepays_initial = rs.getDate(8);

            // определим PAY_PLAN_SUMMA
            if (order_is_cur_period == 1 && typepayrefcode == 1 ) {
                PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            if (order_is_cur_period == 1 && typepayrefcode == 2 ) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            // пред период предоплата значение предопл = 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial == 100){
                PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue() - sumpay.doubleValue());
                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
            }
            // пред период предоплата значение предопл <> 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial != 100){

                if ( summa.doubleValue()*paymentvalue_initial/100 <=  sumpay.doubleValue() ) { // предоплату заплатили
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_DATE = Tools.getLastDayOfMonth(startDT); // на ПОСЛЕДНЕЕ число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
                else // предоплату не оплотили доплатить остаток по предоплате
                {
                    PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                    PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
            }
            // пред заявки и тип по факту поставки (поставки не было значит берем 0 )
            if (order_is_cur_period == 0 && typepayrefcode == 2) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }

            PAY_PLAN_SUMMA = PAY_PLAN_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP);

            if (PAY_PLAN_DATE.getDate() > 25){
                PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
            } else {
                PAY_DATE = PAY_PLAN_DATE;
            }

            if (PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() > 0 && (PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)) ){
                PAY_SIGN = 1;
            }
            else {
                PAY_SIGN = 0;
            }

            if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                PAY_PLAN_SUMMA = new BigDecimal(0);
            }

            PAY_FACT_PRICE = rs.getBigDecimal(2);

            try{
            PAY_FACT_DATE = rs.getDate(1);
            } catch (Exception e) {
                // PAY_FACT_DATE =null;
                throw new ReportSystemException(e);
                }


            if (PAY_DATE.after(startDT)) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
            }

            HashMap hashMap = new HashMap();
            hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_DATE,null);
            hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP));
            hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
            hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,PAY_FACT_DATE); // факт дата оплаты
            hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,PAY_FACT_PRICE);
            hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,rs.getBigDecimal(3));
            hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,rs.getBigDecimal(4));
            hashMap.put(ReestrPaymentDS.BILL_NUM,rs.getString(5));
            hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
            hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
            hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
            hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
            hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
            hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
            hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
            hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
            hashMap.put(ReestrPaymentDS.BILLITEMCODES,billitemcode);
            rows.add(hashMap);
        }

        rs.close();
        tempSt.close();

    }

    return new ReestrPaymentDS(rows.toArray());

    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (ParseException e) {
        throw new ReportSystemException(e);
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


public JRDataSource getReestrPaymentDATAForServices(int oicode ,
        String budgetrefname ,
        String billitemcode,
        String paymenttypename_initial,
        int paymentvalue_initial ,
        java.util.Date min_postavka_date,
        String startDate ,
        BigDecimal summa )
{

        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
        Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
        UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        Statement tempSt = null;

try {

    // проверим есть ли в темповой таблице строки прихода под строки счета
    query = "select count(*) from tmpbilltem2rqkorderitem q   where q.billitemcode IN ("+ billitemcode +") ;";
    stmt = connection.createStatement();
    rs = stmt.executeQuery(query);
    boolean postavkaByBill = false;
    if(rs.next()){
        if (rs.getInt(1) > 0 ){
        postavkaByBill = true;
        }
    }


     System.out.println(" start getReestrPaymentDATAForServices");
    ArrayList rows  = new ArrayList();

    if (postavkaByBill  ){ // выберем приходы

     query = " select countfact_postavka  \n" +
     " ,pricewithnds  \n" +
     " ,sum(sumwithnds) as sumwithnds \n" +
     " ,fkorderdate  \n" +
     " ,(select coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
     "        where pi2bi.paydocitemcode = pi.code  \n" +
     "          and pi2bi.billitemcode = bi.code  \n" +
     "          and pi.paydocrefcode = p.code  \n" +
     "          and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
     " from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
     "      left join  rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code )   \n" +
     "      left join  rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')) \n" +
     " where bi.billrefcode = b.code  \n" +
     " and bi.code  IN ("+ billitemcode +") )  as p_sum \n" +
     " ,planneddatedelivery \n" +
     " ,planneddatepays \n" +
     " ,typepayrefcode \n" +
     " ,rqorderitemtypepay_name \n" +
     " ,paymentvalue \n" +
     " ,billnumber \n" +
     " ,order_is_cur_period \n" +
     " ,sum(sum_predoplat) as sum_predoplat \n" +
     " ,datemax_pay \n" +
     " ,rqfkordercode \n" +
     " from ( \n" +
     " select  \n" +
     " 1 as countfact_postavka  \n" +
     " , q.pricewithnds  \n" +
     " , q.sumwithnds \n" +
     " , q.fkorderdate  \n" +
     "     , (select oi.planneddatedelivery from rqorderitem oi where oi.code = "+ oicode +"   ) as planneddatedelivery  /*6*/ \n" +
     "      , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode +"   ) as planneddatepays  /*7*/ \n" +
     "      , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode +"   ) as typepayrefcode   /*8*/ \n" +
     "      , (select tp.name from rqorderitem oi  , rqorderitemtypepay tp where oi.code = "+ oicode +"  and oi.typepayrefcode = tp.code ) as rqorderitemtypepay_name   \n" +
     " /*9*/ \n" +
     "      , (select oi.paymentvalue from rqorderitem oi where oi.code = "+ oicode +"   ) as paymentvalue  /*10*/ \n" +
     "      , (select ' '||b.numberdoc from rqbill b , rqbillitem bi where b.code = bi.billrefcode and bi.code = q.billitemcode limit 1 ) as billnumber  /*11*/ \n" +
     "      , case when first_day(to_date('"+startDate+"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =   \n" +
     "      ri.orderrefcode and ri.code = "+ oicode +"  limit 1 ) then 1 else 0 end as order_is_cur_period     /*12*/ \n" +
     "      /*сумма оплат до даты первого прихода*/     \n" +
     "      , coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p     \n" +
     "                                    where pi2bi.paydocitemcode = pi.code   \n" +
     "                                      and pi2bi.billitemcode = q.billitemcode \n" +
     "                                      and pi.paydocrefcode = p.code   \n" +
     "                                      and p.dategen <= (select  get_min_date_fkorder_service_by_orderitem("+ oicode +"  , 1/*статусы ордеров все*/ , '"+startDate+"') ) ),0) as  \n" +
     " sum_predoplat  /*13*/ \n" +
     " ,(select max(p.dategen) as dategen_pay \n" +
     "    from rqbillitem bi , rqbill b  , rqpaydocitem pi , rqpaydoc p , rqpaydocitem2billitem pi2bi  \n" +
     "  where bi.billrefcode = b.code  \n" +
     "  and pi2bi.billitemcode = bi.code  \n" +
     "  and pi2bi.paydocitemcode = pi.code  \n" +
     "  and pi.paydocrefcode = p.code  \n" +
     "  and bi.code = q.billitemcode  ) as datemax_pay  /*14*/  \n" +
     " ,( select distinct f.code from rqfkorderitem fi , rqfkorder f \n" +
     "     where fi.code  =  q.rqfkorderitemcode \n" +
     "         and fi.fkorderrefcode = f.code ) as rqfkordercode \n" +
     " from  \n" +
     " tmpbilltem2rqkorderitem q  \n" +
     " where q.billitemcode IN ("+ billitemcode +") \n" +
     " ) as sel1  \n" +
     " group by countfact_postavka , pricewithnds ,fkorderdate ,planneddatedelivery ,planneddatepays ,typepayrefcode \n" +
     " ,rqorderitemtypepay_name ,paymentvalue,billnumber,order_is_cur_period,datemax_pay , rqfkordercode \n" +
     " Order by fkorderdate  \n" +
     "  \n" ;


    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);


        BigDecimal tmpSum = new BigDecimal(0);
         BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
         BigDecimal PAY_FACT_SUMMA = new BigDecimal(0);
         Date PAY_PLAN_DATE = null;
         String PAY_TYPE_NAME = "";
         int PAY_TYPE_VALUE = 0;
         Date PAY_DATE = null;
         int PAY_SIGN = 0;
         int order_is_cur_period = 0;
         int typepayrefcode = 0;
         Date planneddatepays_initial = null;
         Date fact_postavka_date = null;
         BigDecimal sum_predoplat = new BigDecimal(0);
         String billnumber = "";
         DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
         Date startDT = formatter.parse(startDate);
         BigDecimal pricewithnds = new BigDecimal(0);
         BigDecimal countfact_postavka = new BigDecimal(0);
         BigDecimal sumByFkOrderAll = new BigDecimal(0);
         Date planneddatedelivery = null;
         Date PAY_FACT_DATE = null;
         BigDecimal restSummaInBillExceptPAY_PLAN_SUMMA = new BigDecimal(0);

         restSummaInBillExceptPAY_PLAN_SUMMA = summa;


         while(rs.next())  /// нашли приходы
            {
                order_is_cur_period = rs.getInt(12);
                typepayrefcode = rs.getInt(8);
                planneddatepays_initial = rs.getDate(7);
                billnumber = rs.getString(11);
                fact_postavka_date = rs.getDate(4);
                pricewithnds =rs.getBigDecimal(2);
                countfact_postavka = rs.getBigDecimal(1);
                planneddatedelivery = rs.getDate(6);

                HashMap hashMap = new HashMap();
                hashMap.put(reestrPaymentDSService.PRIHOD_COUNT, rs.getBigDecimal(1));
                hashMap.put(reestrPaymentDSService.PRIHOD_PRICE, rs.getBigDecimal(2));
                hashMap.put(reestrPaymentDSService.PRIHOD_SUMMA, rs.getBigDecimal(3));
                hashMap.put(reestrPaymentDSService.PRIHOD_DATE,rs.getDate(4));

                BigDecimal sumByFkOrder = rs.getBigDecimal(3); // сумма в приходном
                sumByFkOrderAll = new BigDecimal(sumByFkOrderAll.doubleValue() + sumByFkOrder.doubleValue());
                BigDecimal paymentsByBillItems = rs.getBigDecimal(5); // общ сумма оплат по билл итемам
                if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue()  >= sumByFkOrder.doubleValue()  ){
                    PAY_FACT_SUMMA = sumByFkOrder; // если оплаты больше или равны сумме в приходном то сумму факт оплаты кидаем такую как в ордере
                    PAY_PLAN_SUMMA = new BigDecimal(0);// все оплатили все харашо платить больше не буим
                    PAY_TYPE_NAME = "Кінцевий розрахунок";
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(sumByFkOrder);
                } else
                {     if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue() > 0 ){
                    PAY_FACT_SUMMA = new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()); // если разница меньше
                    PAY_TYPE_NAME = "Кінцевий розрахунок"; //
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()));
                    } else{
                        PAY_FACT_SUMMA = new BigDecimal(0);
                        PAY_TYPE_NAME = "Кінцевий розрахунок";
                        PAY_TYPE_VALUE = 100;
                    }
                // перем = сумма по счету - сумма всех оплат
                BigDecimal restSumma = new BigDecimal( summa.doubleValue()-paymentsByBillItems.doubleValue() );
                // перем = сумма по счету - сумма по приходным




                PAY_PLAN_SUMMA = new BigDecimal(sumByFkOrder.doubleValue() - PAY_FACT_SUMMA.doubleValue());
                if (restSumma.doubleValue() < PAY_PLAN_SUMMA.doubleValue()  ){
                    PAY_PLAN_SUMMA = restSumma;
                }

                if (restSummaInBillExceptPAY_PLAN_SUMMA.doubleValue() < sumByFkOrderAll.doubleValue() ) {
                    PAY_PLAN_SUMMA = new BigDecimal(restSummaInBillExceptPAY_PLAN_SUMMA.doubleValue() - PAY_FACT_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()).setScale(3, BigDecimal.ROUND_HALF_UP);
                    if (PAY_PLAN_SUMMA.doubleValue() < 0) {
                        PAY_PLAN_SUMMA = new BigDecimal(0);
                    }

                }

                restSummaInBillExceptPAY_PLAN_SUMMA = new BigDecimal( restSummaInBillExceptPAY_PLAN_SUMMA.doubleValue() - PAY_PLAN_SUMMA.doubleValue() - PAY_FACT_SUMMA.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);


                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
                }

                /*if (PAY_PLAN_SUMMA.doubleValue() == 0 || PAY_PLAN_DATE == null){
                    PAY_SIGN = 0;
                } else {

                    if (typepayrefcode == 1){
                        PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, 7); // если строка заявки по предоплате то факт поставки + 7 дней
                    } else if (typepayrefcode == 2){
                        PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, paymentvalue_initial ); // если по факту то факт поставки + значение на строке заявки
                    }

                    if (PAY_PLAN_DATE.getDate() > 25){
                            PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                        } else {
                            PAY_DATE = PAY_PLAN_DATE;
                        }

                        if (PAY_PLAN_SUMMA.doubleValue() > 0 && PAY_DATE.before(startDT) ){
                            PAY_SIGN = 1;
                        }
                        else {
                            PAY_SIGN = 0;
                        }
                }
                if (PAY_SIGN == Integer.MIN_VALUE ){
                    PAY_SIGN = 0;
                }
                if (PAY_TYPE_VALUE == Integer.MIN_VALUE ){
                    PAY_TYPE_VALUE = 0;
                }
                if (paymentvalue_initial == Integer.MIN_VALUE ){
                    paymentvalue_initial = 0;
                }*/
                if (typepayrefcode == 1){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, 7); // если строка заявки по предоплате то факт поставки + 7 дней
                } else if (typepayrefcode == 2){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, paymentvalue_initial ); // если по факту то факт поставки + значение на строке заявки
                }

            // если плановая дата поставки со строки заявки больше чем PAY_PLAN_DATE которую высчитали то нужно принимать плановую дату поставки как дата оплаты

                if (planneddatedelivery.after(PAY_PLAN_DATE) ){
                    PAY_PLAN_DATE = planneddatedelivery;

                }

                if (PAY_PLAN_DATE.getDate() > 25){
                        PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                    } else {
                        PAY_DATE = PAY_PLAN_DATE;
                    }

                    if ((PAY_PLAN_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP)).doubleValue() > 0 && ( PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)  ) ){
                        PAY_SIGN = 1;
                    }
                    else {
                        PAY_SIGN = 0;
                    }

                    if (PAY_DATE.after(startDT)) {
                        PAY_PLAN_SUMMA = new BigDecimal(0);
                    }

                    try{
                        PAY_FACT_DATE = rs.getDate(14);
                        } catch (Exception e) {
                            // PAY_FACT_DATE =null;
                            throw new ReportSystemException(e);
                            }
                    PAY_FACT_SUMMA = PAY_FACT_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP);

                    if (PAY_FACT_SUMMA.doubleValue()<= 0){
                        PAY_FACT_DATE = null;
                    }


                hashMap.put(reestrPaymentDSService.PAY_PLAN_SUMMA, PAY_PLAN_SUMMA);
                hashMap.put(reestrPaymentDSService.PAY_PLAN_DATE,PAY_PLAN_DATE);
                hashMap.put(reestrPaymentDSService.PAY_FACT_DATE,null);
                hashMap.put(reestrPaymentDSService.PAY_FACT_PRICE,pricewithnds);
                hashMap.put(reestrPaymentDSService.PAY_FACT_SUMMA,PAY_FACT_SUMMA);
                hashMap.put(reestrPaymentDSService.PAY_FACT_COUNT,countfact_postavka);
                hashMap.put(reestrPaymentDSService.BILL_NUM,billnumber);
                hashMap.put(reestrPaymentDSService.BUDGET_NAME,budgetrefname);
                hashMap.put(reestrPaymentDSService.RESP_CENTER,"");
                hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME,PAY_TYPE_NAME);
                hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                hashMap.put(reestrPaymentDSService.PAY_DATE,PAY_DATE);
                hashMap.put(reestrPaymentDSService.PAY_SIGN,new Integer(PAY_SIGN));
                hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));

                rows.add(hashMap);
            }

         sumByFkOrderAll = sumByFkOrderAll.setScale(3, BigDecimal.ROUND_HALF_UP);
            // после того как известны суммы из приходов сравним с суммой по счету если остаток больше 0 тогда создать строку суммы на разницу (сумма счета минус сумма по приходам )
            if ((summa.doubleValue() - sumByFkOrderAll.doubleValue() > 0 ) && sumByFkOrderAll.doubleValue() > 0  ){
                // определим PAY_PLAN_SUMMA и PAY_PLAN_DATE (проверим делали ли предоплату (предоплатой считаем те оплаты которые были раньше даты первого прихода по строке заявки  ))
                // если что то не доплатили то ничего не платим и дату оплаты со строки заявки
                // если тип строки предоплата - сравним сумму которую должны были заплатить по предоплате
                if (typepayrefcode == 1){
                            // разница за вычетом приходов
                    if( ( (summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) > sum_predoplat.doubleValue() ) { // предоплата была меньше или вообще не было
                        PAY_PLAN_SUMMA = new BigDecimal( ((summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) -  sum_predoplat.doubleValue() );
                        if (order_is_cur_period != 1 ){ // если предыдущий месяц то на первый день месяца

                                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                                PAY_TYPE_NAME = paymenttypename_initial;
                                PAY_TYPE_VALUE = paymentvalue_initial;
                        } else // иначе с поля в заявке
                        {

                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                            PAY_TYPE_VALUE = paymentvalue_initial;
                        }
                    } else
                    {
                        PAY_PLAN_SUMMA = new BigDecimal(0); // заплатили в полном объеме
                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                        PAY_TYPE_VALUE = paymentvalue_initial;
                    }
                    }
                else if (typepayrefcode == 2) { // если тип строки по факту то ждем факта и на него потом разнесется сумма оплат
                    // PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue()-sumByFkOrderAll.doubleValue());
                    PAY_PLAN_DATE =  planneddatepays_initial;
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                    }

                PAY_PLAN_SUMMA = PAY_PLAN_SUMMA.setScale(3, BigDecimal.ROUND_HALF_UP);

                if (PAY_PLAN_SUMMA.doubleValue() == 0 || PAY_PLAN_DATE == null){
                    PAY_SIGN = 0;
                } else {

                        if (PAY_PLAN_DATE.getDate() > 25){
                            PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                        } else {
                            PAY_DATE = PAY_PLAN_DATE;
                        }

                        if (PAY_PLAN_SUMMA.doubleValue() > 0 && ( PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)  ) && typepayrefcode != 2  ){
                            PAY_SIGN = 1;
                        }
                        else {
                            PAY_SIGN = 0;
                        }
                }

                if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                }

                if (PAY_SIGN == Integer.MIN_VALUE ){
                    PAY_SIGN = 0;
                }
                if (PAY_TYPE_VALUE == Integer.MIN_VALUE ){
                    PAY_TYPE_VALUE = 0;
                }
                if (paymentvalue_initial == Integer.MIN_VALUE ){
                    paymentvalue_initial = 0;
                }

                if (PAY_PLAN_SUMMA.doubleValue() > 0){
                        HashMap hashMap = new HashMap();
                        hashMap.put(reestrPaymentDSService.PRIHOD_COUNT, new BigDecimal(0));
                        hashMap.put(reestrPaymentDSService.PRIHOD_PRICE, new BigDecimal(0));
                        hashMap.put(reestrPaymentDSService.PRIHOD_SUMMA, new BigDecimal(0));
                        hashMap.put(reestrPaymentDSService.PRIHOD_DATE,null);
                        hashMap.put(reestrPaymentDSService.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA);
                        hashMap.put(reestrPaymentDSService.PAY_PLAN_DATE,PAY_PLAN_DATE);
                        hashMap.put(reestrPaymentDSService.PAY_FACT_DATE,null);
                        hashMap.put(reestrPaymentDSService.PAY_FACT_PRICE,null);
                        hashMap.put(reestrPaymentDSService.PAY_FACT_SUMMA,null);
                        hashMap.put(reestrPaymentDSService.PAY_FACT_COUNT,null);
                        hashMap.put(reestrPaymentDSService.BILL_NUM,billnumber);
                        hashMap.put(reestrPaymentDSService.BUDGET_NAME,budgetrefname);
                        hashMap.put(reestrPaymentDSService.RESP_CENTER,"");
                        hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME,PAY_TYPE_NAME);
                        hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                        hashMap.put(reestrPaymentDSService.PAY_DATE,PAY_DATE);
                        hashMap.put(reestrPaymentDSService.PAY_SIGN,new Integer(PAY_SIGN));
                        hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                        hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
                        rows.add(hashMap);
                }
            }


     rs.close();
    tempSt.close();

    } else {
  //!!!!!!!!!!!!!!!!!!!!!!1 если не нашли приходы значит вставить строку из параметров + определить оплаты

        query = " select datepay , pricewithoutnds , sum(summagen) as summagen , sum(countfact) as countfact , ' '||numberdocbill as numberdocbill \n" +
        " , case when first_day(to_date('"+ startDate +"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =  \n" +
        " ri.orderrefcode and ri.code = "+ oicode + " limit 1 ) then 1 else 0 end as order_is_cur_period \n" +    // 6
        " , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode + "  ) as typepayrefcode \n" + // 7
        " , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatepays \n" + // 8
        "  \n" +
        "  from  \n" +
        " (select to_char(p.dategen,'dd.mm.yyyy') as datepay \n" +
        "  , bi.pricewithoutnds \n" +
        "  , (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then \n" +
        "             ( (select countfact from rqorderitem where code = "+ oicode + " ) / (select sum(oi.countfact) from rqbillitem2orderitem bi2oi , rqorderitem oi  \n" +
        " where bi2oi.billitemrefcode = bi.code  and  bi2oi.orderitemrefcode = oi.code ) \n" +
        "              * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) )::numeric(15,3) \n" +
        "               else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
        "               end  ) as summagen \n" +
        "  , sum(bi.countfact) as  countfact \n" +
        "  , ''||b.numberdoc as numberdocbill \n" +
        "   from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
        "                                   left join rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code ) \n" +
        "                                   left join rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ) \n" +
        "  \n" +
        "  \n" +
        " where bi.billrefcode = b.code \n" +
        " and bi.code IN ("+ billitemcode +") \n" +
        " group by bi.pricewithoutnds , p.dategen , b.numberdoc , bi.code )  as sel_bill_pay \n" +
        "  \n" +
        " group by datepay , pricewithoutnds , numberdocbill \n";

        tempSt = connection.createStatement();
        rs = tempSt.executeQuery(query);

        BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
        Date PAY_PLAN_DATE = null;
        String PAY_TYPE_NAME = "";
        int PAY_TYPE_VALUE = 0;
        Date PAY_DATE = null;
        int PAY_SIGN = 0;
        Date PAY_FACT_DATE = null;
        BigDecimal PAY_FACT_PRICE = null;
        //
        BigDecimal sumpay = new BigDecimal(0);
        int order_is_cur_period = 0 ;
        int typepayrefcode = 0;
        Date planneddatepays_initial = null;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date startDT = formatter.parse(startDate);
        while(rs.next())  ///
        {
            order_is_cur_period = rs.getInt(6);
            typepayrefcode = rs.getInt(7);
            sumpay = rs.getBigDecimal(3);
            planneddatepays_initial = rs.getDate(8);

            // определим PAY_PLAN_SUMMA
            if (order_is_cur_period == 1 && typepayrefcode == 1 ) {
                PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            if (order_is_cur_period == 1 && typepayrefcode == 2 ) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            // пред период предоплата значение предопл = 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial == 100){
                PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue() - sumpay.doubleValue());
                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
            }
            // пред период предоплата значение предопл <> 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial != 100){

                if ( summa.doubleValue()*paymentvalue_initial/100 <=  sumpay.doubleValue() ) { // предоплату заплатили
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_DATE = Tools.getLastDayOfMonth(startDT); // на ПОСЛЕДНЕЕ число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
                else // предоплату не оплотили доплатить остаток по предоплате
                {
                    PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                    PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
            }
            // пред заявки и тип по факту поставки (поставки не было значит берем 0 )
            if (order_is_cur_period == 0 && typepayrefcode == 2) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }


            if (PAY_PLAN_SUMMA.doubleValue() == 0 || PAY_PLAN_DATE == null){
                PAY_SIGN = 0;
            } else {

                    if (PAY_PLAN_DATE.getDate() > 25){
                        PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                    } else {
                        PAY_DATE = PAY_PLAN_DATE;
                    }

                    if (PAY_PLAN_SUMMA.doubleValue() > 0 && ( PAY_DATE.before(startDT) || PAY_DATE.equals(startDT)  ) ){
                        PAY_SIGN = 1;
                    }
                    else {
                        PAY_SIGN = 0;
                    }

                    if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                        PAY_PLAN_SUMMA = new BigDecimal(0);
                    }

                    PAY_FACT_PRICE = rs.getBigDecimal(2);

                    try{
                    PAY_FACT_DATE = rs.getDate(1);
                    } catch (Exception e) {
                        PAY_FACT_DATE =null;
                        }

                }

            if (PAY_SIGN == Integer.MIN_VALUE ){
                PAY_SIGN = 0;
            }
            if (PAY_TYPE_VALUE == Integer.MIN_VALUE ){
                PAY_TYPE_VALUE = 0;
            }
            if (paymentvalue_initial == Integer.MIN_VALUE ){
                paymentvalue_initial = 0;
            }



            HashMap hashMap = new HashMap();
            hashMap.put(reestrPaymentDSService.PRIHOD_COUNT, new BigDecimal(0));
            hashMap.put(reestrPaymentDSService.PRIHOD_PRICE, new BigDecimal(0));
            hashMap.put(reestrPaymentDSService.PRIHOD_SUMMA, new BigDecimal(0));
            hashMap.put(reestrPaymentDSService.PRIHOD_DATE,null);
            hashMap.put(reestrPaymentDSService.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA);
            hashMap.put(reestrPaymentDSService.PAY_PLAN_DATE,PAY_PLAN_DATE);
            hashMap.put(reestrPaymentDSService.PAY_FACT_DATE,PAY_FACT_DATE); // факт дата оплаты
            hashMap.put(reestrPaymentDSService.PAY_FACT_PRICE,PAY_FACT_PRICE);
            hashMap.put(reestrPaymentDSService.PAY_FACT_SUMMA,rs.getBigDecimal(3));
            hashMap.put(reestrPaymentDSService.PAY_FACT_COUNT,rs.getBigDecimal(4));
            hashMap.put(reestrPaymentDSService.BILL_NUM,rs.getString(5));
            hashMap.put(reestrPaymentDSService.BUDGET_NAME,budgetrefname);
            hashMap.put(reestrPaymentDSService.RESP_CENTER,"");
            hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME,PAY_TYPE_NAME);
            hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
            hashMap.put(reestrPaymentDSService.PAY_DATE,PAY_DATE);
            hashMap.put(reestrPaymentDSService.PAY_SIGN,new Integer(PAY_SIGN));
            hashMap.put(reestrPaymentDSService.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
            hashMap.put(reestrPaymentDSService.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
            rows.add(hashMap);
        }

        rs.close();
        tempSt.close();

    }

    return new reestrPaymentDSService(rows.toArray());

    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (ParseException e) {
        throw new ReportSystemException(e);
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
        }
        try {
            if (tempSt != null) tempSt.close();
        } catch (SQLException e) {
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
        }
    }
}


class FkorderItemData
{
    int fkOrderItemCode;
    double sumwithnds;
    double sumwithndsRest;
    double priceWithNds;
    Date dateGen;


    public FkorderItemData(int vFkOrderItemCode,double vSumwithnds,double vSumwithndsRest , double vPriceWithNds , Date vDateGen )
    {
        fkOrderItemCode = vFkOrderItemCode;
        sumwithnds = vSumwithnds;
        sumwithndsRest = vSumwithndsRest;
        priceWithNds = vPriceWithNds;
        dateGen = vDateGen;
    }

    @Override
	public int hashCode()
    {
        return ("" + fkOrderItemCode ).hashCode();
    }

    @Override
	public boolean equals(Object obj)
    {
        if (obj instanceof FkorderItemData)
        {
            FkorderItemData other = (FkorderItemData)obj;
            return this.fkOrderItemCode == other.fkOrderItemCode;

        } else
        {
            return false;
        }
    }

}


public String createTemporaryTableBillItem2FkOrder(int oicode , String startDate) throws SQLException{
    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    Statement stmt = null;
    ResultSet rs = null;
    Statement stmtBill = null;
    ResultSet rsBill = null;
    String sql = "";
    String sqlBill = "";
    boolean isExistPostavka = false ;
    try{
        // заполним HASHMAP по строкам прихода
        //LinkedHashMap<FkorderItemData, FkorderItemData> fkorderitemData = new LinkedHashMap<FkorderItemData, FkorderItemData>();
        LinkedHashMap fkorderitemData = new LinkedHashMap();
        FkorderItemData fKey;
        // по строке заявки выбрать все строки приходов
        sql = " select  \n" +
        "  fi.code  \n" +
        " ,fi.sumwithoutnds + ( fi.sumwithoutnds * coalesce(f.ndspercent,0)/100 )::numeric(15,3) as sumwithnds \n" +
        " ,fi.pricewithoutnds + ( fi.pricewithoutnds * coalesce(f.ndspercent,0)/100 )::numeric(15,3) as pricewithnds \n" +
        " ,f.dategen \n" +
        " from rqfkorder2planfact fo2plf , rqfkorderitem fi , rqfkorder f \n" +
        " where fo2plf.fkorderitemcode in (  select distinct(f2e.fkorderitemrefcode) from rqfkorderitem2enstmttm f2e , rqorderitem2enestimttm o2e \n" +
        "                                    where f2e.estimateitemcode = o2e.estimateitemcode \n" +
        "                                    and o2e.orderitemcode = "+ oicode +" )   \n" +
        " and fo2plf.fkorderitemcode = fi.code  \n" +
        " and fi.fkorderrefcode = f.code  \n" +
        " and f.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
        " order by f.dategen  , f.code \n" +
        "  \n";

        stmt = connection.createStatement();
        rs = stmt.executeQuery(sql);
        int billitemcode_tmp = 0 ;
        while(rs.next())
        {
            fKey = new FkorderItemData(rs.getInt(1),rs.getDouble(2), rs.getDouble(2), rs.getDouble(3) , rs.getDate(4));
            fkorderitemData.put(fKey, fKey);
            isExistPostavka = true;
        }

        int resultRsSize = 0;
        sql = "select count(*) as countt from information_schema.tables where table_name = 'tmpbilltem2rqkorderitem';";
        stmt.clearBatch();
        rs = stmt.executeQuery(sql);
        if(rs.next()){
            resultRsSize = rs.getInt(1);
        }

        if (resultRsSize == 0 ){
            sql = "CREATE TABLE tmpbilltem2rqkorderitem (billitemcode DOUBLE PRECISION,   rqfkorderitemcode DOUBLE PRECISION,   sumwithnds DOUBLE PRECISION,  pricewithnds DOUBLE PRECISION,  fkorderdate DATE)";
        } else {
            sql = "delete from tmpbilltem2rqkorderitem";
        }
        stmt.execute(sql);

        if (isExistPostavka){ // раскидать суммы по приходам на строки счетов



            double rqfkorderitemcode_tmp ;
            double sumwithnds_tmp ;
            double pricewithnds_tmp;

            double sumwithnds_billitem;
            Date fkorderdate_tmp = null;

            String DATE_FORMAT = "dd.MM.yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);



        //    stmt.execute("INSERT INTO tmpbilltem2rqkorderitem (billitemcode, rqfkorderitemcode, sumwithnds, pricewithnds, fkorderdate) " +
        //                 " VALUES (500087187, 500190212, 20000, 20000, '01.08.2013');");

        // цикл по строкам счета которые относятся к строке заявки - соритируем по дате счета
            sqlBill = " select bi.code , q.sumwithoutnds + (q.sumwithoutnds * coalesce(b.vat,0)/100 )::numeric(15,3)  as sumwithnds , b.dategen \n" +
            " from rqbillitem2orderitem q  , rqbillitem bi , rqbill b \n" +
            " where q.orderitemrefcode = "+ oicode +" \n" +
            " and q.billitemrefcode = bi.code  \n" +
            " and bi.billrefcode = b.code  \n" +
            " order by b.dategen , b.code , bi.code \n" ;

            stmtBill = connection.createStatement();
            rsBill = stmtBill.executeQuery(sqlBill);
            while(rsBill.next())
            {
                double sumwithnds_billitem_tmp = 0;

                billitemcode_tmp = rsBill.getInt(1);
                sumwithnds_billitem =  rsBill.getDouble(2); // сумма с ндс в строке счета
                double sumwithnds_billitem_rest = rsBill.getDouble(2); // переменная суммі по строке счета с которой будем отнимать

                /////////////////////////////////////////////////////
                //Iterator<FkorderItemData> fIterator = fkorderitemData.keySet().iterator();
                Iterator fIterator = fkorderitemData.keySet().iterator();

                while (fIterator.hasNext()){
                    fKey = (FkorderItemData)fIterator.next();
                    FkorderItemData fValue = (FkorderItemData)fkorderitemData.get(fKey);

                    if (sumwithnds_billitem_rest > 0 ){
                        if (fValue.sumwithndsRest > 0 ){
                            if (sumwithnds_billitem_rest >= fValue.sumwithndsRest ){
                                sumwithnds_billitem_tmp = sumwithnds_billitem_tmp+fValue.sumwithndsRest;

                                sumwithnds_billitem_rest = sumwithnds_billitem_rest - sumwithnds_billitem_tmp;
                                // вставка в темп таблицу
                                stmt.clearBatch();
                                String sqll = "INSERT INTO tmpbilltem2rqkorderitem (billitemcode, rqfkorderitemcode, sumwithnds, pricewithnds, fkorderdate) " +
                                " VALUES ("+billitemcode_tmp+", "+ fValue.fkOrderItemCode +", "+fValue.sumwithndsRest+", "+ fValue.priceWithNds+", '"+ sdf.format(fValue.dateGen) +"');";
                                stmt.execute(sqll);
                                fValue.sumwithndsRest = 0;
                            } else {
                                sumwithnds_billitem_tmp =  sumwithnds_billitem_tmp + sumwithnds_billitem_rest;
                                fValue.sumwithndsRest = fValue.sumwithndsRest - sumwithnds_billitem_rest;

                                stmt.clearBatch();
                                    stmt.execute("INSERT INTO tmpbilltem2rqkorderitem (billitemcode, rqfkorderitemcode, sumwithnds, pricewithnds, fkorderdate) " +
                                                    " VALUES ("+billitemcode_tmp+", "+ fValue.fkOrderItemCode +", "+sumwithnds_billitem_rest+", "+ fValue.priceWithNds+", '"+ sdf.format(fValue.dateGen) +"');");
                                sumwithnds_billitem_rest = sumwithnds_billitem_rest - sumwithnds_billitem_rest;
                            }
                            fkorderitemData.put(fValue, fValue);

                        }
                    }
                    }



                }
            // проверим остались ли суммы по приходам которые не разнесли по счетам
            // если есть то кидаем все приходы на последнюю строчку счета (из цикла по строкам счета )
            Iterator fIterator = fkorderitemData.keySet().iterator();
            while (fIterator.hasNext()){
                fKey = (FkorderItemData)fIterator.next();
                FkorderItemData fValue = (FkorderItemData)fkorderitemData.get(fKey);
                    if (fValue.sumwithndsRest > 0 ){
                        stmt.clearBatch();
                        String sqll = "INSERT INTO tmpbilltem2rqkorderitem (billitemcode, rqfkorderitemcode, sumwithnds, pricewithnds, fkorderdate) " +
                         " VALUES ("+billitemcode_tmp+", "+ fValue.fkOrderItemCode +", "+fValue.sumwithndsRest+", "+ fValue.priceWithNds+", '"+ sdf.format(fValue.dateGen) +"');";
                        stmt.execute(sqll);
                    }

                 }

                }
    /*try {


        stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TEMPORARY TABLE mytable (COL_A VARCHAR(100), COL_B INTEGER)");
        stmt.executeUpdate("insert into mytable values ('первый нах', 111)");
        stmt.executeUpdate("insert into mytable values ('второй нах', 222)");
        } catch (SQLException e) {
            throw new ReportSystemException(e);
        }
        String testQuery = " select * from mytable ";

        testSt = connection.createStatement();
        rsTest = testSt.executeQuery(testQuery);
        while(rsTest.next())
            {
                System.out.println(" COL_A = " + rsTest.getString(1) );
                System.out.println(" COL_B = " + rsTest.getInt(2) );
            }*/


  } finally    {
            rs.close();
            stmt.close();
            rsBill.close();
            stmtBill.close();
            }

    return "1";
}


public JRDataSource getReestrPaymentDATA2(int oicode ,
        String orderdate ,
        String budgetrefname ,
        String statussymbol,
        java.util.Date planneddatepays,
        String paymenttypename,
        int paymentvalue,
        BigDecimal plansumpay,
        String reestr_date,
        String billitemcode,
        String paymenttypename_initial,
        int paymentvalue_initial ,
        java.util.Date min_postavka_date,
        String startDate ,
        BigDecimal summa )
{

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    Statement tempSt = null;
    ResultSet rs = null;

try {


    System.out.println(" start getReestrPaymentDATA");
    ArrayList rows  = new ArrayList();
    String query = "";
    boolean isPrihodFromBillItem = false;

    if (min_postavka_date != null ){ // выберем приходы

    // проверим можно ли приходы выбирать со связи строки прихода со строкой счета
    query =" select count(fi2bi.code) from rqfkorderitem2billitem fi2bi  , rqfkorderitem fi , rqfkorder f  \n" +
    " where  fi2bi.billitemrefcode in("+billitemcode+") \n" +
    " and ( select count(fi2bisub.code) from rqfkorderitem2billitem fi2bisub  \n" +
    "        where fi2bisub.fkorderitemrefcode = fi2bi.fkorderitemrefcode ) = 1 \n" +
   // " and (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode in("+billitemcode+") ) = 1      \n" +
   // " and (select count(bi2ei.code) from rqbillitem2enestimattm bi2ei where bi2ei.billitemcode in("+billitemcode+") ) = 1   \n" +
    " and fi.code = fi2bi.fkorderitemrefcode \n" +
    " and fi.fkorderrefcode = f.code \n" +
    " and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy') \n" ;

    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);

    if(rs.next()){
        isPrihodFromBillItem = true;
    }


    if (isPrihodFromBillItem ){
        query = " select    \n" +
        "   countfact   \n" +
        "   ,pricewithnds   \n" +
        "   ,sumwithnds   \n" +
        "   ,dategen   \n" +
        "    ,( select     \n" +
        "         sum( (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then    \n" +
        "       ( ((" +
        "               select sum(bi2oioi.countfact) from rqbillitem2orderitem bi2oioi  \n" +
        " where bi2oioi.billitemrefcode = bi.code  \n" +
          "     and bi2oioi.orderitemrefcode =  " + oicode +
                "   )    \n" +
        "              /    \n" +
        "             (select sum(bi2oioi.countfact) from rqbillitem2orderitem bi2oioi \n" +
             " where bi2oioi.billitemrefcode = bi.code  \n" +
        "             ))    \n" +
        "                     * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p    \n" +
        "                                           where pi2bi.paydocitemcode = pi.code    \n" +
        "                                             and pi2bi.billitemcode = bi.code    \n" +
        "                                             and pi.paydocrefcode = p.code    \n" +
        "                                             and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ),0) )::numeric(15,3)    \n" +
        "                      else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p    \n" +
        "                                           where pi2bi.paydocitemcode = pi.code    \n" +
        "                                             and pi2bi.billitemcode = bi.code    \n" +
        "                                             and pi.paydocrefcode = p.code    \n" +
        "                                             and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ),0)    \n" +
        "                      end  ) )  as summagen   \n" +
        "          from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode )    \n" +
        "                                          left join  rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code )    \n" +
        "                                          left join  rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy'))    \n" +
        "        where bi.billrefcode = b.code    \n" +
        "        and bi.code in ("+ billitemcode +") )  as p_sum      /*5*/   \n" +
        "      , (select oi.planneddatedelivery from rqorderitem oi where oi.code = "+oicode+"  ) as planneddatedelivery  /*6*/   \n" +
        "        , (select oi.planneddatepays from rqorderitem oi where oi.code = "+oicode+"  ) as planneddatepays /*7*/   \n" +
        "        , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+oicode+"  ) as typepayrefcode /*8*/   \n" +
        "        , (select tp.name from rqorderitem oi  , rqorderitemtypepay tp where oi.code = "+oicode+" and oi.typepayrefcode = tp.code ) as rqorderitemtypepay_name  \n" +
        " /*9*/   \n" +
        "        , (select oi.paymentvalue from rqorderitem oi where oi.code = "+oicode+"  ) as paymentvalue /*10*/   \n" +
        "        , (select ' '||b.numberdoc from rqbill b , rqbillitem bi where b.code = bi.billrefcode and bi.code in ("+ billitemcode +") limit 1 ) as billnumber /*11*/   \n" +
        "        , case when first_day(to_date('"+startDate+"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =     \n" +
        "        ri.orderrefcode and ri.code = "+oicode+" limit 1 ) then 1 else 0 end as order_is_cur_period     /*12*/   \n" +
        "        /*сумма оплат до даты первого прихода*/     \n" +
        "        , coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p     \n" +
        "                                      where pi2bi.paydocitemcode = pi.code     \n" +
        "                                        and pi2bi.billitemcode in ("+ billitemcode +")    \n" +
        "                                        and pi.paydocrefcode = p.code    \n" +
        "                                        and p.dategen <= (select  get_min_date_fkorder_by_orderitem("+oicode+" , 1/*статусы ордеров все*/, '"+startDate+"'  ) ) ),0) as    \n" +
        "   sum_predoplat  /*13 сумма предоплат*/    \n" +
        "        , (select max(p.dategen) as dategen_pay from rqbillitem bi , rqbill b  , rqpaydocitem pi , rqpaydoc p , rqpaydocitem2billitem pi2bi    \n" +
        "    where bi.billrefcode = b.code    \n" +
        "    and pi2bi.billitemcode = bi.code    \n" +
        "    and pi2bi.paydocitemcode = pi.code    \n" +
        "    and pi.paydocrefcode = p.code    \n" +
        "    and bi.code in ("+ billitemcode +")   \n" +
        "    and p.dategen <= to_date('"+startDate+"','dd.mm.yyyy') ) as dategen_pay  /*14 максимальн дата оплаты*/   \n" +
        "    from    \n" +
        "    (   \n" +
        "   select   \n" +
        "   sum(countfact) as countfact  \n" +
        "   ,pricewithnds  \n" +
        "   ,sum(countfact * pricewithnds)::numeric(15,3) as sumwithnds   \n" +
        "   ,dategen  \n" +
        "    from    \n" +
        "    (  \n" +
        "   /*количество из развязки по естиметам  с приходными строками и счетами  */     \n" +
        "      select  ( select sum(fi2eni.countgen)        \n" +
        "       from rqfkorderitem2enstmttm fi2eni , rqorderitem2enestimttm oi2eni  , rqfkorderitem fi , rqfkorder f   \n" +
        "          where oi2eni.orderitemcode = "+oicode+"   \n" +
        "            and fi2eni.estimateitemcode = oi2eni.estimateitemcode    \n" +
        "            and fi2eni.fkorderitemrefcode = fi.code    \n" +
        "            and fi.fkorderrefcode = f.code    \n" +
        "            and f.kindcode in (1,15)    \n" +
        "            and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')   \n" +
        "            and fi.code in (select distinct fi2bia.fkorderitemrefcode from rqfkorderitem2billitem fi2bia    \n" +
        "                                             where  fi2bia.billitemrefcode in("+ billitemcode +")   \n" +
        "                                           )   \n" +
        "            and fi.code = fi_par.code    \n" +
        "          ) + /*к количеству по естимейтам прибавим излишки на приходной строке под бюджетодержателя без естимейта   */   \n" +
        "              /* !!!излишки будем искать только если количество в приходе по естимейтам меньше чем количество в счете ИЗ ЗАЯВКИ  */   \n" +
        "            case  when  coalesce((select sum(bi2oi.countfact) from rqbillitem2orderitem bi2oi  \n" +
        "                                   where bi2oi.billitemrefcode in ("+ billitemcode +") \n" +
        "                                   and bi2oi.orderitemrefcode = "+oicode+"  \n" +
        "                                   ),0) > \n" +
        "                         coalesce(( select sum(q.countgen) from rqfkorderitem2enstmttm q ,  rqorderitem2enestimttm oi2ei2 \n" +
        "                           where q.finmaterialsrefcode is null \n" +
        "                           and   q.estimateitemcode in (select qq.estimateitemcode from rqbillitem2enestimattm qq  \n" +
        "                                                         where qq.billitemcode  IN ("+ billitemcode +") ) \n" +
        "                           and q.estimateitemcode = oi2ei2.estimateitemcode \n" +
        "                           and oi2ei2.orderitemcode = "+oicode+" \n" +
        "                         ),0) \n" +
        "                  then   \n" +
        "      \n" +
        "                 coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem    \n" +
        "                        where fkrem.fkorderitemrefcode in     \n" +
        "                               (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es     \n" +
        "                                           , rqfkorderitem  fi ,rqfkorder f      \n" +
        "                                         where oi2ei.orderitemcode =  "+oicode+"       \n" +
        "                                         and bi2ei.billitemcode IN ("+ billitemcode +")     \n" +
        "                                         and bi2ei.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                         and fi2es.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                         and fi.code = fi2es.fkorderitemrefcode      \n" +
        "                                         and fi.fkorderrefcode = f.code      \n" +
        "                                         and f.kindcode in (1,15)     \n" +
        "                                         and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')     \n" +
        "                                )    \n" +
        "                         and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+oicode+"   )      \n" +
        "                         and fkrem.estimateitemrefcode is null     \n" +
        "                         and fkrem.fkorderitemrefcode = fi_par.code       \n" +
        "                      ),0) \n" +
        "             else 0  \n" +
        "             end        \n" +
        "         + /*к количеству по естимейтам прибавим излишки на приходной строке под бюджетодержателя с естимейтом  */     \n" +
        "           coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem    \n" +
        "                  where fkrem.estimateitemrefcode in     \n" +
        "                         (select  distinct oi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es     \n" +
        "                                     , rqfkorderitem  fi ,rqfkorder f      \n" +
        "                                   where oi2ei.orderitemcode =  "+oicode+"       \n" +
        "                                   and bi2ei.billitemcode IN ("+ billitemcode +")     \n" +
        "                                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                   and fi2es.estimateitemcode = oi2ei.estimateitemcode       \n" +
        "                                   and fi.code = fi2es.fkorderitemrefcode      \n" +
        "                                   and fi.fkorderrefcode = f.code      \n" +
        "                                   and f.kindcode in (1,15)     \n" +
        "                                   and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')     \n" +
        "                          )    \n" +
        "                   and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+oicode+"  )    \n" +
        "                   and fkrem.estimateitemrefcode is not null        \n" +
        "                   and fkrem.fkorderitemrefcode = fi_par.code       \n" +
        "                ),0)           \n" +
        "         as countfact    \n" +
        "        ,(fi_par.pricewithoutnds*(1+ coalesce(f.ndspercent,0)/100))::numeric(15,3) as pricewithnds               \n" +
        "        ,f.dategen     \n" +
        "      from rqfkorderitem2billitem fi2bi  , rqfkorderitem fi_par , rqfkorder f    \n" +
        "        where  fi2bi.fkorderitemrefcode in (select distinct fi2bia.fkorderitemrefcode from rqfkorderitem2billitem fi2bia    \n" +
        "                                             where  fi2bia.billitemrefcode in("+ billitemcode +")   \n" +
        "                                           )   \n" +
        "        and fi2bi.fkorderitemrefcode = fi_par.code     \n" +
        "        and fi_par.fkorderrefcode = f.code     \n" +
        "        and f.kindcode in (1,15)    \n" +
        "        and f.dategen <= to_date('"+startDate+"','dd.mm.yyyy')   \n" +
        "        /*add*/ and fi2bi.billitemrefcode in ("+ billitemcode +")  \n" +
        "        group by f.dategen , fi_par.pricewithoutnds , ndspercent , fi_par.code    \n" +
        "    ) as sel1    \n" +
        "    group by pricewithnds , dategen   \n" +
        "    order by dategen  \n" +
        "    ) as sel2   \n" +
        "  \n" ;


    }
    else {


     query = " select countfact_postavka::numeric(15,6) +  \n" + // плюсуем поидее его излишки без естимейта
     " coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem               \n" +
     "  where fkrem.fkorderitemrefcode in  \n" +
     "         (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es  \n" +
     "                     , rqfkorderitem  fi ,rqfkorder f   \n" +
     "                   where oi2ei.orderitemcode =  "+ oicode + "    \n" +
     "                   and bi2ei.billitemcode IN ("+ billitemcode +")  \n" +
     "                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode    \n" +
     "                   and fi2es.estimateitemcode = oi2ei.estimateitemcode    \n" +
     "                   and fi.code = fi2es.fkorderitemrefcode   \n" +
     "                   and fi.fkorderrefcode = f.code   \n" +
     "                   and f.kindcode in (1,15)  \n" +
     "                   and f.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')  \n" +
     "        ) \n" +
     "  and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+ oicode + "  )    and fkrem.estimateitemrefcode is null   ),0) \n" +
     "       as countfact, \n" +
     "  \n" +

    "        pricewithnds::numeric(15,3) as pricewithnds , \n" +
    "        (  ( countfact_postavka::numeric(15,6) +  \n" + // плюсуем поидее его излишки без естимейта
    " coalesce((  select sum(fkrem.countgen) from rqfkorderitemremainder fkrem               \n" +
    "  where fkrem.fkorderitemrefcode in  \n" +
    "         (select  distinct fi.code from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei  , rqfkorderitem2enstmttm fi2es  \n" +
    "                     , rqfkorderitem  fi ,rqfkorder f   \n" +
    "                   where oi2ei.orderitemcode =  "+ oicode + "    \n" +
    "                   and bi2ei.billitemcode IN ("+ billitemcode +")  \n" +
    "                   and bi2ei.estimateitemcode = oi2ei.estimateitemcode    \n" +
    "                   and fi2es.estimateitemcode = oi2ei.estimateitemcode    \n" +
    "                   and fi.code = fi2es.fkorderitemrefcode   \n" +
    "                   and fi.fkorderrefcode = f.code   \n" +
    "                   and f.kindcode in (1,15)  \n" +
    "                   and f.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')  \n" +
    "        ) \n" +
    "  and fkrem.budgetcode in (select oi.budgetrefcode from rqorderitem oi where oi.code = "+ oicode + "  )     and fkrem.estimateitemrefcode is null  ),0) \n" +
    " ) \n" +
    " * pricewithnds)::numeric(15,3) as sumwithnds \n" +
    "         ,dategen \n" +
    ",( select  \n" +
    "  sum( (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then \n" +
   " ( ((select sum(bi2oioi.countfact) from rqbillitem2orderitem bi2oioi \n" +
    "         where bi2oioi.billitemrefcode = bi.code \n" +
    "           and bi2oioi.orderitemrefcode = "+ oicode +" ) \n" +
   "         / \n" +
   "       (select sum(bi2oioi.countfact) from rqbillitem2orderitem bi2oioi \n" +
       "      where bi2oioi.billitemrefcode = bi.code \n" +
   "        )) \n" +
    "              * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
    "                                    where pi2bi.paydocitemcode = pi.code \n" +
    "                                      and pi2bi.billitemcode = bi.code \n" +
    "                                      and pi.paydocrefcode = p.code \n" +
    "                                      and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') ),0) )::numeric(15,3) \n" +
    "               else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
    "                                    where pi2bi.paydocitemcode = pi.code \n" +
    "                                      and pi2bi.billitemcode = bi.code \n" +
    "                                      and pi.paydocrefcode = p.code \n" +
    "                                      and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
    "               end  ) )  as summagen \n" +
    "   from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
    "                                   left join  rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code ) \n" +
    "                                   left join  rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy')) \n" +
    "  \n" +
    " where bi.billrefcode = b.code \n" +
    " and bi.code in ("+ billitemcode +") )  as p_sum \n" +



    " , (select oi.planneddatedelivery from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatedelivery \n" + // 6
    " , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatepays \n" + // 7
    " , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode + "  ) as typepayrefcode \n" + // 8
    " , (select tp.name from rqorderitem oi  , rqorderitemtypepay tp where oi.code = "+ oicode + " and oi.typepayrefcode = tp.code ) as rqorderitemtypepay_name \n" + // 9
    " , (select oi.paymentvalue from rqorderitem oi where oi.code = "+ oicode + "  ) as paymentvalue \n" + // 10
    " , (select ' '||b.numberdoc from rqbill b , rqbillitem bi where b.code = bi.billrefcode and bi.code in ("+ billitemcode +") limit 1 ) as billnumber \n" + //11
    " , case when first_day(to_date('"+ startDate +"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =  \n" +
    " ri.orderrefcode and ri.code = "+ oicode + " limit 1 ) then 1 else 0 end as order_is_cur_period \n" +    // 12
    " /*сумма оплат до даты первого прихода*/  " +
    " , coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p  " +
    "                               where pi2bi.paydocitemcode = pi.code " +
    "                                 and pi2bi.billitemcode in ("+ billitemcode +")" +
    "                                 and pi.paydocrefcode = p.code " +
    "                                 and p.dategen <= (select  get_min_date_fkorder_by_orderitem("+ oicode + " , 1/*статусы ордеров все*/, '"+ startDate+ "'  ) ) ),0) as sum_predoplat " +  // 13 сумма предоплат
    " , (select max(p.dategen) as dategen_pay from rqbillitem bi , rqbill b  , rqpaydocitem pi , rqpaydoc p , rqpaydocitem2billitem pi2bi " +
" where bi.billrefcode = b.code " +
" and pi2bi.billitemcode = bi.code " +
" and pi2bi.paydocitemcode = pi.code " +
" and pi.paydocrefcode = p.code " +
    " and bi.code in ("+ billitemcode +")" + " ) as dategen_pay  \n" + // 14 максимальн дата оплаты
    " from \n" +
    " ( \n" +
    " select sum(countfact) + sum(countremander) as countfact_postavka , pricewithnds , dategen \n" +
    " from ( \n" +
    " /*поставка приход с выбором партий */ \n" +
    " select \n" +
    " rqfkorderitem2enstmttm.estimateitemcode , \n" +
    " iord.code as prihordercode , \n" +
    " iord.numberdoc, \n" +
    " iord.dategen, \n" +
    " rqfkorderitem2enstmttm.countgen as countfact, \n" +
    " ii.measurementnametxt \n" +
    "  , case when (select coalesce(b.vat,0)  from rqbillitem2enestimattm bi2e , rqbillitem bi , rqbill b \n" +
    "                      where bi2e.billitemcode = bi.code \n" +
    "                         and bi.billrefcode = b.code \n" +
    "                       and bi2e.estimateitemcode in ( select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei   \n" +
    " ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode + " \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode) \n" +
    "                       limit 1 \n" +
    "                 ) > 0 then \n" +
    "             round(cast(ii.pricewithoutnds*(select 1+coalesce(b.vat,0)/100::numeric from rqbillitem bi , rqbill b \n" + // NET-4284 ндс
    "                                              where bi.billrefcode = b.code  \n" +
    "                                                and bi.code in (  " +  billitemcode + " ) \n" +
    "                                             limit 1) as numeric),3) \n" +
    "                                   else \n" +
    "             round(cast(ii.pricewithoutnds as numeric),3) end \n" +
    "   as pricewithnds , \n" +
    " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, \n" +
    "  0 as findoccode, \n" +
    " /*выбор кол-во излишки */ \n" +
    " 0 as countremander, \n" +
    "  iord.statuscode, \n" +
    "  rqfkorderstatus.name as orderstatusname \n" +
    " from rqfkorderitem ii \n" +
    "       left join rqfkorderitem2enstmttm \n" +
    "          on ( rqfkorderitem2enstmttm.fkorderitemrefcode = ii.code \n" +
    "               and coalesce(rqfkorderitem2enstmttm.countgen,0) <> 0 ) \n" +
    " , rqfkorder iord left join  findoc2fkorder ffkord on (iord.code = ffkord.fkorderrefcode)  , rqfkorderstatus \n" +
    " where iord.code = ii.fkorderrefcode \n" +
    " and iord.statuscode = rqfkorderstatus.code \n" +
    " and iord.kindcode in (1,15) \n" +
    " and iord.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    " and rqfkorderitem2enstmttm.estimateitemcode in ( select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode  ) \n" +
    " and ii.code not in ( select rqfkorderitem2billitem.fkorderitemrefcode from rqfkorderitem2billitem   , rqbillitem \n" +
    ",     rqpaydocitem2billitem p2b ,  rqpaydocitem pi , rqpaydoc p \n" +
    " where rqbillitem.code = rqfkorderitem2billitem.billitemrefcode   \n" +
    " and coalesce(rqbillitem.staterefcode, 0) = 1 \n" +
    " and p2b.billitemcode = rqbillitem.code  \n" +
    " and p2b.paydocitemcode = pi.code  \n" +
    " and p.code = pi.paydocrefcode \n" +
    " and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "          ) \n" +
    "  \n" +
    " group by iord.code , iord.numberdoc, iord.dategen, ii.measurementnametxt, \n" +
    " iord.molincode, iord.molinname, iord.moloutcode, iord.moloutname, ii.pricewithoutnds , \n" +
    " iord.kindcode , iord.statuscode  , ii.countgen , ii.sumwithoutnds , ii.code , rqfkorderstatus.name \n" +
    " , rqfkorderitem2enstmttm.countgen , rqfkorderitem2enstmttm.estimateitemcode \n" +
    "  \n" +
    " UNION /*выборка из remainder не излишков а приходов под планы */ \n" +
    "  \n" +
    "  select \n" +
    "       rqfkorderitemremainder.estimateitemrefcode as estimateitemcode \n" +
    "     , rqfkorder.code as prihordercode \n" +
    "     , rqfkorder.numberdoc \n" +
    "     , rqfkorder.dategen \n" +
    "     , rqfkorderitemremainder.countgen as countfact \n" +
    "     , rqfkorderitem.measurementnametxt \n" +
    "     , case when (select coalesce(b.vat,0)  from rqbillitem2enestimattm bi2e , rqbillitem bi , rqbill b \n" +
    "                      where bi2e.billitemcode = bi.code \n" +
    "                         and bi.billrefcode = b.code \n" +
    "                       and bi2e.estimateitemcode in (  select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei   \n" +
    " ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode ) \n" +
    "                       limit 1 \n" +
    "                 ) > 0 then \n" +
    "               round(cast(rqfkorderitemremainder.pricewithoutnds*(select 1+coalesce(b.vat,0)/100::numeric from rqbillitem bi , rqbill b \n" + // NET-4284 ндс
    "                                              where bi.billrefcode = b.code  \n" +
    "                                                and bi.code in (  " +  billitemcode + " ) \n" +
    "                                             limit 1) as numeric),3) \n" +
    "                       else \n" +
    "               round(cast(rqfkorderitemremainder.pricewithoutnds as numeric),3) \n" +
    "                       end \n" +
    "      as pricewithnds \n" +
    "     , rqfkorder.molincode \n" +
    "     , rqfkorder.molinname \n" +
    "     , rqfkorder.moloutcode \n" +
    "     , rqfkorder.moloutname \n" +
    "     , 0 as findoccode \n" +
    "     , 0 as countremander \n" +
    "     , rqfkorder.statuscode \n" +
    "     , rqfkorderstatus.name as orderstatusname \n" +
    "     from rqfkorderitemremainder , rqfkorderitem , rqfkorder , rqfkorderstatus \n" +
    "     where rqfkorderitemremainder.estimateitemrefcode in \n" +
    "                                             (  select bi2ei.estimateitemcode from rqorderitem2enestimttm oi2ei  ,rqbillitem2enestimattm bi2ei \n" +
    "                                                                            where oi2ei.orderitemcode = "+ oicode +" \n" +
    "                                                                            and bi2ei.billitemcode IN ("+ billitemcode +") \n" +
    "                                                                            and bi2ei.estimateitemcode = oi2ei.estimateitemcode ) \n" +
    "       and rqfkorderitemremainder.typerefcode = 2 \n" +
    "       and rqfkorderitemremainder.fkorderitemrefcode = rqfkorderitem.code \n" +
    "       and rqfkorderitem.fkorderrefcode = rqfkorder.code \n" +
    "       and rqfkorder.kindcode in (1,15) \n" +
    "       and rqfkorder.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "       and rqfkorder.statuscode = rqfkorderstatus.code \n" +
    " and rqfkorderitem.code not in ( select rqfkorderitem2billitem.fkorderitemrefcode from rqfkorderitem2billitem   , rqbillitem \n" +
    ",     rqpaydocitem2billitem p2b ,  rqpaydocitem pi , rqpaydoc p \n" +
    " where rqbillitem.code = rqfkorderitem2billitem.billitemrefcode   \n" +
    " and coalesce(rqbillitem.staterefcode, 0) = 1 \n" +
    " and p2b.billitemcode = rqbillitem.code  \n" +
    " and p2b.paydocitemcode = pi.code  \n" +
    " and p.code = pi.paydocrefcode \n" +
    " and p.dategen <= to_date('"+ startDate +"','dd.mm.yyyy') \n" +
    "          )  \n" +
    " ) w \n" +
    "  group by pricewithnds , dategen \n" +
    "  ) as q order by dategen \n" ;
    }


    //System.out.println(" query = " + query);


    //Statement tempSt;
    tempSt.clearBatch();
    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);


        BigDecimal tmpSum = new BigDecimal(0);
         BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
         BigDecimal PAY_FACT_SUMMA = new BigDecimal(0);
         Date PAY_PLAN_DATE = null;
         String PAY_TYPE_NAME = "";
         int PAY_TYPE_VALUE = 0;
         Date PAY_DATE = null;
         int PAY_SIGN = 0;
         Date PAY_FACT_DATE = null;
         int order_is_cur_period = 0;
         int typepayrefcode = 0;
         Date planneddatepays_initial = null;
         Date fact_postavka_date = null;
         BigDecimal sum_predoplat = new BigDecimal(0);
         String billnumber = "";
         DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
         Date startDT = formatter.parse(startDate);
         BigDecimal pricewithnds = new BigDecimal(0);
         BigDecimal countfact_postavka = new BigDecimal(0);
         BigDecimal sumByFkOrderAll = new BigDecimal(0);
         Date planneddatedelivery = null;
         while(rs.next())  /// нашли приходы
            {
                order_is_cur_period = rs.getInt(12);
                typepayrefcode = rs.getInt(8);
                planneddatepays_initial = rs.getDate(7);
                billnumber = rs.getString(11);
                fact_postavka_date = rs.getDate(4);
                pricewithnds =rs.getBigDecimal(2);
                countfact_postavka = rs.getBigDecimal(1);
                planneddatedelivery = rs.getDate(6);

                HashMap hashMap = new HashMap();
                hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, rs.getBigDecimal(1));
                hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, rs.getBigDecimal(2));
                hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, rs.getBigDecimal(3));
                hashMap.put(ReestrPaymentDS.PRIHOD_DATE,rs.getDate(4));

                BigDecimal sumByFkOrder = rs.getBigDecimal(3); // сумма в приходном
                sumByFkOrderAll = new BigDecimal(sumByFkOrderAll.doubleValue() + sumByFkOrder.doubleValue());
                BigDecimal paymentsByBillItems = rs.getBigDecimal(5); // общ сумма оплат по билл итемам
                if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue()  >= sumByFkOrder.doubleValue()  ){
                    PAY_FACT_SUMMA = sumByFkOrder; // если оплаты больше или равны сумме в приходном то сумму факт оплаты кидаем такую как в ордере
                    PAY_PLAN_SUMMA = new BigDecimal(0);// все оплатили все харашо платить больше не буим
                    PAY_TYPE_NAME = "Кінцевий розрахунок";
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(sumByFkOrder);
                } else
                {     if (paymentsByBillItems.doubleValue()- tmpSum.doubleValue() > 0 ){
                    PAY_FACT_SUMMA = new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()); // если разница меньше
                    PAY_TYPE_NAME = "Кінцевий розрахунок"; //
                    PAY_TYPE_VALUE = 100;
                    tmpSum = tmpSum.add(new BigDecimal(paymentsByBillItems.doubleValue()- tmpSum.doubleValue()));
                    } else{
                        PAY_FACT_SUMMA = new BigDecimal(0);
                        PAY_TYPE_NAME = "Кінцевий розрахунок";
                        PAY_TYPE_VALUE = 100;
                    }
                // перем = сумма по счету - сумма всех оплат
                BigDecimal restSumma = new BigDecimal( summa.doubleValue()-paymentsByBillItems.doubleValue() );


                PAY_PLAN_SUMMA = new BigDecimal(sumByFkOrder.doubleValue() - PAY_FACT_SUMMA.doubleValue());
                if (restSumma.doubleValue() < PAY_PLAN_SUMMA.doubleValue()  ){
                    PAY_PLAN_SUMMA = restSumma;
                }

                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
                }

                if (typepayrefcode == 1){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, 7); // если строка заявки по предоплате то факт поставки + 7 дней
                } else if (typepayrefcode == 2){
                    PAY_PLAN_DATE = Tools.addDays(fact_postavka_date, paymentvalue_initial ); // если по факту то факт поставки + значение на строке заявки
                }

            // если плановая дата поставки со строки заявки больше чем PAY_PLAN_DATE которую высчитали то нужно принимать плановую дату поставки как дата оплаты

                if (planneddatedelivery.after(PAY_PLAN_DATE) ){
                    PAY_PLAN_DATE = planneddatedelivery;

                }

                if (PAY_PLAN_DATE.getDate() > 25){
                        PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                    } else {
                        PAY_DATE = PAY_PLAN_DATE;
                    }

                    if (PAY_PLAN_SUMMA.doubleValue() > 0 && PAY_DATE.before(startDT) ){
                        PAY_SIGN = 1;
                    }
                    else {
                        PAY_SIGN = 0;
                    }

                    if (PAY_DATE.after(startDT)) {
                        PAY_PLAN_SUMMA = new BigDecimal(0);
                    }

                    try{
                        PAY_FACT_DATE = rs.getDate(14);
                        } catch (Exception e) {
                            // PAY_FACT_DATE =null;
                            throw new ReportSystemException(e);
                            }
                    PAY_FACT_SUMMA = PAY_FACT_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP);

                    if (PAY_FACT_SUMMA.doubleValue()<= 0){
                        PAY_FACT_DATE = null;
                    }

                hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA, PAY_PLAN_SUMMA);
                hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
                hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,PAY_FACT_DATE);
                hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,pricewithnds);
                hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,PAY_FACT_SUMMA);
                hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,countfact_postavka);
                hashMap.put(ReestrPaymentDS.BILL_NUM,billnumber);
                hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
                hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
                hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
                hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
                hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
                hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));

                rows.add(hashMap);
            }

         sumByFkOrderAll = sumByFkOrderAll.setScale(2, BigDecimal.ROUND_HALF_UP);
            // после того как известны суммы из приходов сравним с суммой по счету если остаток больше 0 тогда создать строку суммы на разницу (сумма счета минус сумма по приходам )
            if ((summa.doubleValue() - sumByFkOrderAll.doubleValue() > 0 ) && sumByFkOrderAll.doubleValue() > 0  ){
                // определим PAY_PLAN_SUMMA и PAY_PLAN_DATE (проверим делали ли предоплату (предоплатой считаем те оплаты которые были раньше даты первого прихода по строке заявки  ))
                // если что то не доплатили то ничего не платим и дату оплаты со строки заявки
                // если тип строки предоплата - сравним сумму которую должны были заплатить по предоплате
                if (typepayrefcode == 1){
                            // разница за вычетом приходов
                    if( ( (summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) > sum_predoplat.doubleValue() ) { // предоплата была меньше или вообще не было
                        PAY_PLAN_SUMMA = new BigDecimal( ((summa.doubleValue()-sumByFkOrderAll.doubleValue())*paymentvalue_initial/100) -  sum_predoplat.doubleValue() );
                        if (order_is_cur_period != 1 ){ // если предыдущий месяц то на первый день месяца

                                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                                PAY_TYPE_NAME = paymenttypename_initial;
                                PAY_TYPE_VALUE = paymentvalue_initial;
                        } else // иначе с поля в заявке
                        {

                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                            PAY_TYPE_VALUE = paymentvalue_initial;
                        }
                    } else
                    {
                        PAY_PLAN_SUMMA = new BigDecimal(0); // заплатили в полном объеме
                        PAY_PLAN_DATE =  planneddatepays_initial;
                        PAY_TYPE_NAME = paymenttypename_initial;
                        PAY_TYPE_VALUE = paymentvalue_initial;
                    }
                    }
                else if (typepayrefcode == 2) { // если тип строки по факту то ждем факта и на него потом разнесется сумма оплат
                    // PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue()-sumByFkOrderAll.doubleValue());
                    PAY_PLAN_DATE =  planneddatepays_initial;
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                    }

                PAY_PLAN_SUMMA = PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP);

                if (PAY_PLAN_DATE.getDate() > 25){
                    PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
                } else {
                    PAY_DATE = PAY_PLAN_DATE;
                }

                if (PAY_PLAN_SUMMA.doubleValue() > 0 && PAY_DATE.before(startDT) && typepayrefcode != 2  ){
                    PAY_SIGN = 1;
                }
                else {
                    PAY_SIGN = 0;
                }

                if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                }


                if (PAY_PLAN_SUMMA.doubleValue() > 0){
                        HashMap hashMap = new HashMap();
                        hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, new BigDecimal(0));
                        hashMap.put(ReestrPaymentDS.PRIHOD_DATE,null);
                        hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA);
                        hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,null);
                        hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,null);
                        hashMap.put(ReestrPaymentDS.BILL_NUM,billnumber);
                        hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
                        hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
                        hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
                        hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
                        hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
                        rows.add(hashMap);
                }
            }



    rs.close();
    tempSt.close();

    } else {
  //!!!!!!!!!!!!!!!!!!!!!!1 если не нашли приходы значит вставить строку из параметров + определить оплаты

        query = " select datepay , pricewithoutnds , sum(summagen) as summagen , sum(countfact) as countfact , ' '||numberdocbill as numberdocbill \n" +
        " , case when first_day(to_date('"+ startDate +"','dd.mm.yyyy') ) =  ( select  first_day(r.orderperiod) from rqorder r , rqorderitem ri where r.code =  \n" +
        " ri.orderrefcode and ri.code = "+ oicode + " limit 1 ) then 1 else 0 end as order_is_cur_period \n" +    // 6
        " , (select oi.typepayrefcode from rqorderitem oi where oi.code = "+ oicode + "  ) as typepayrefcode \n" + // 7
        " , (select oi.planneddatepays from rqorderitem oi where oi.code = "+ oicode + "  ) as planneddatepays \n" + // 8
        "  \n" +
        "  from  \n" +
        // " (select to_char(p.dategen,'dd.mm.yyyy') as datepay \n" +
        " (select p.dategen as datepay \n" +
        "  , bi.pricewithoutnds \n" +
        "  , (case when (select count(bi2oi.code) from rqbillitem2orderitem bi2oi where bi2oi.billitemrefcode = bi.code ) > 1 then \n" +
        "             ( (select countfact from rqorderitem where code = "+ oicode + " ) " +
                " / " +
                "(select sum(oi.countfact) from rqbillitem2orderitem bi2oi , rqorderitem oi  \n" +
        " where bi2oi.billitemrefcode = bi.code  and  bi2oi.orderitemrefcode = oi.code ) \n" +
        "              * coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) )::numeric(15,3) \n" +
        "               else coalesce((select sum(pi.summagen) from rqpaydocitem2billitem pi2bi, rqpaydocitem pi , rqpaydoc p \n" +
        "                                    where pi2bi.paydocitemcode = pi.code \n" +
        "                                      and pi2bi.billitemcode = bi.code \n" +
        "                                      and pi.paydocrefcode = p.code \n" +
        "                                      and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ),0) \n" +
        "               end  ) as summagen \n" +
        "  , sum(bi.countfact) as  countfact \n" +
        "  , ''||b.numberdoc as numberdocbill \n" +
        "   from   rqbill b , rqbillitem bi left join rqpaydocitem2billitem pi2bi on (bi.code = pi2bi.billitemcode ) \n" +
        "                                   left join rqpaydocitem pi on (pi2bi.paydocitemcode = pi.code ) \n" +
        "                                   left join rqpaydoc p on ( pi.paydocrefcode = p.code and p.dategen <=to_date('"+ startDate +"','dd.mm.yyyy') ) \n" +
        "  \n" +
        "  \n" +
        " where bi.billrefcode = b.code \n" +
        " and bi.code IN ("+ billitemcode +") \n" +
        " group by bi.pricewithoutnds , p.dategen , b.numberdoc , bi.code )  as sel_bill_pay \n" +
        "  \n" +
        " group by datepay , pricewithoutnds , numberdocbill \n";

        tempSt = connection.createStatement();
        rs = tempSt.executeQuery(query);

        BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
        Date PAY_PLAN_DATE = null;
        String PAY_TYPE_NAME = "";
        int PAY_TYPE_VALUE = 0;
        Date PAY_DATE = null;
        int PAY_SIGN = 0;
        Date PAY_FACT_DATE = null;
        BigDecimal PAY_FACT_PRICE = null;
        //
        BigDecimal sumpay = new BigDecimal(0);
        int order_is_cur_period = 0 ;
        int typepayrefcode = 0;
        Date planneddatepays_initial = null;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date startDT = formatter.parse(startDate);
        while(rs.next())  ///
        {
            order_is_cur_period = rs.getInt(6);
            typepayrefcode = rs.getInt(7);
            sumpay = rs.getBigDecimal(3);
            planneddatepays_initial = rs.getDate(8);

            // определим PAY_PLAN_SUMMA
            if (order_is_cur_period == 1 && typepayrefcode == 1 ) {
                PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            if (order_is_cur_period == 1 && typepayrefcode == 2 ) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }
            // пред период предоплата значение предопл = 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial == 100){
                PAY_PLAN_SUMMA = new BigDecimal(summa.doubleValue() - sumpay.doubleValue());
                PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                PAY_TYPE_NAME = "Кінцевий розрахунок";
                PAY_TYPE_VALUE = 100;
            }
            // пред период предоплата значение предопл <> 100
            if (order_is_cur_period == 0 && typepayrefcode == 1 && paymentvalue_initial != 100){

                if ( summa.doubleValue()*paymentvalue_initial/100 <=  sumpay.doubleValue() ) { // предоплату заплатили
                    PAY_PLAN_SUMMA = new BigDecimal(0);
                    PAY_PLAN_DATE = Tools.getLastDayOfMonth(startDT); // на ПОСЛЕДНЕЕ число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
                else // предоплату не оплотили доплатить остаток по предоплате
                {
                    PAY_PLAN_SUMMA = new BigDecimal((summa.doubleValue()*paymentvalue_initial/100)  - sumpay.doubleValue());
                    PAY_PLAN_DATE = Tools.getFirstDayOfMonth(startDT); // на первое число месяца среза
                    PAY_TYPE_NAME = paymenttypename_initial;
                    PAY_TYPE_VALUE = paymentvalue_initial;
                }
            }
            // пред заявки и тип по факту поставки (поставки не было значит берем 0 )
            if (order_is_cur_period == 0 && typepayrefcode == 2) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
                PAY_PLAN_DATE = planneddatepays_initial;
                PAY_TYPE_NAME = paymenttypename_initial;
                PAY_TYPE_VALUE = paymentvalue_initial;
            }

            PAY_PLAN_SUMMA = PAY_PLAN_SUMMA.setScale(2, BigDecimal.ROUND_HALF_UP);

            if (PAY_PLAN_DATE.getDate() > 25){
                PAY_DATE = Tools.changeDay(PAY_PLAN_DATE, 25);
            } else {
                PAY_DATE = PAY_PLAN_DATE;
            }

            if (PAY_PLAN_SUMMA.doubleValue() > 0 && PAY_DATE.before(startDT) ){
                PAY_SIGN = 1;
            }
            else {
                PAY_SIGN = 0;
            }

            if (PAY_PLAN_SUMMA.doubleValue() < 0 ){
                PAY_PLAN_SUMMA = new BigDecimal(0);
            }

            PAY_FACT_PRICE = rs.getBigDecimal(2);

            try{
            PAY_FACT_DATE = rs.getDate(1);
            } catch (Exception e) {
                // PAY_FACT_DATE =null;
                throw new ReportSystemException(e);
                }


            if (PAY_DATE.after(startDT)) {
                PAY_PLAN_SUMMA = new BigDecimal(0);
            }

            HashMap hashMap = new HashMap();
            hashMap.put(ReestrPaymentDS.PRIHOD_COUNT, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_PRICE, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_SUMMA, new BigDecimal(0));
            hashMap.put(ReestrPaymentDS.PRIHOD_DATE,null);
            hashMap.put(ReestrPaymentDS.PAY_PLAN_SUMMA,PAY_PLAN_SUMMA);
            hashMap.put(ReestrPaymentDS.PAY_PLAN_DATE,PAY_PLAN_DATE);
            hashMap.put(ReestrPaymentDS.PAY_FACT_DATE,PAY_FACT_DATE); // факт дата оплаты
            hashMap.put(ReestrPaymentDS.PAY_FACT_PRICE,PAY_FACT_PRICE);
            hashMap.put(ReestrPaymentDS.PAY_FACT_SUMMA,rs.getBigDecimal(3));
            hashMap.put(ReestrPaymentDS.PAY_FACT_COUNT,rs.getBigDecimal(4));
            hashMap.put(ReestrPaymentDS.BILL_NUM,rs.getString(5));
            hashMap.put(ReestrPaymentDS.BUDGET_NAME,budgetrefname);
            hashMap.put(ReestrPaymentDS.RESP_CENTER,"");
            hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME,PAY_TYPE_NAME);
            hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE,new Integer(PAY_TYPE_VALUE));
            hashMap.put(ReestrPaymentDS.PAY_DATE,PAY_DATE);
            hashMap.put(ReestrPaymentDS.PAY_SIGN,new Integer(PAY_SIGN));
            hashMap.put(ReestrPaymentDS.PAY_TYPE_NAME_INITIAL,paymenttypename_initial);
            hashMap.put(ReestrPaymentDS.PAY_TYPE_VALUE_INITIAL,new Integer(paymentvalue_initial));
            rows.add(hashMap);
        }

        rs.close();
        tempSt.close();

    }

    return new ReestrPaymentDS(rows.toArray());

    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (ParseException e) {
        throw new ReportSystemException(e);
    } finally {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
        }
        try {
            if (tempSt != null)
                tempSt.close();
        } catch (SQLException e) {
        }
    }
}


public JRDataSource getSumCountFuelBak(String molcode , String DateStart , int actstatus  , String DateEnd  , int typefuel)
{

JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
try {

    ArrayList rows  = new ArrayList();
    String query = "";

    Statement tempSt = null;
    ResultSet rs = null;


     query = "SELECT \n" +
"     codefueltypenet, namefueltypenet, \n" +
"     sum(countgenstart) as countgenstart, \n" +
"     sum(countin) as countin \n" +
"  \n" +
" FROM \n" +
" ( \n" +
" select    ftype as codefueltypenet, \n" +
"         fueltypename as namefueltypenet, \n" +
"         /*если в этом месяце машина не выезжала, берем остатки предыдущего месяца*/ \n" +
"         coalesce(first_count_start, prev_count_final, 0) as countgenstart, \n" +
"         coalesce(last_count_final, prev_count_final, 0) as countgenfinal_from_trvchet, \n" +
"         coalesce(first_sum_start, prev_sum_final, 0) as sumgenstart, \n" +
"         coalesce(count_in, 0) as countin, \n" +
"         coalesce(sum_in, 0) as sumgenin \n" +
"  \n" +
" from \n" +
"  \n" +
" (select ftype, fueltypename, \n" +
"  \n" +
" -- первый остаток в этом месяце \n" +
" (select fr.countgenstart \n" +
"     from entravelsheet ts inner join entravelsheetfuelremns fr \n" +
"     on fr.travelsheetrefcode = ts.code \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"         and net.get_transport_real_mol(transp.tcode,fr.dategen) = transport_history.finmolcode \n" +
"     and fr.fueltyperefcode = ftype \n" +
"     order by ts.DateStart, ts.timestart limit 1) \n" +
"     as first_count_start, \n" +
" (select fr.sumgenstart \n" +
"     from entravelsheet ts inner join entravelsheetfuelremns fr \n" +
"     on fr.travelsheetrefcode = ts.code \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"         and net.get_transport_real_mol(transp.tcode,fr.dategen) = transport_history.finmolcode \n" +
"     and fr.fueltyperefcode = ftype \n" +
"     order by ts.DateStart, ts.timestart limit 1) \n" +
"     as first_sum_start, \n" +
"  \n" +
" -- последний остаток в этом месяце \n" +
" (select fr.countgenfinal \n" +
"     from entravelsheet ts inner join entravelsheetfuelremns fr \n" +
"     on fr.travelsheetrefcode = ts.code \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"     and fr.fueltyperefcode = ftype \n" +
"     order by ts.datefinal DESC, ts.timefinal DESC limit 1) \n" +
"     as last_count_final, \n" +
"  \n" +
" -- последний остаток из прошлых месяцев \n" +
" -- (будет использован, если в этом месяце не было ПЛ для этой машины) \n" +
" (select fr.countgenfinal \n" +
"     from entravelsheet ts inner join entravelsheetfuelremns fr \n" +
"     on fr.travelsheetrefcode = ts.code \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen < transport_history.DateStart \n" +
"     and fr.fueltyperefcode = ftype \n" +
"     order by ts.datefinal DESC, ts.timefinal DESC limit 1) \n" +
"     as prev_count_final, \n" +
" (select fr.sumgenfinal \n" +
"     from entravelsheet ts inner join entravelsheetfuelremns fr \n" +
"     on fr.travelsheetrefcode = ts.code \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen < transport_history.DateStart \n" +
"     and fr.fueltyperefcode = ftype \n" +
"     order by ts.datefinal DESC, ts.timefinal DESC limit 1) \n" +
"     as prev_sum_final, \n" +
"  \n" +
" -- приходы и расходы \n" +
" (select sum(fr.countgenin) \n" +
"     from entravelsheetfuelremns fr \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"     and net.get_transport_real_mol(transp.tcode,fr.dategen) = transport_history.finmolcode \n" +
"     and fr.fueltyperefcode = ftype) \n" +
"     as count_in, \n" +
" (select sum(fr.sumgenin) \n" +
"     from entravelsheetfuelremns as fr \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"     and net.get_transport_real_mol(transp.tcode,fr.dategen) = transport_history.finmolcode \n" +
"     and fr.fueltyperefcode = ftype) \n" +
"     as sum_in, \n" +
" (select sum(fr.countgenout) \n" +
"     from entravelsheetfuelremns fr \n" +
"     where fr.realtransportcode = tcode \n" +
"     and fr.dategen between transport_history.DateStart and transport_history.DateEnd \n" +
"     and net.get_transport_real_mol(transp.tcode,fr.dategen) = transport_history.finmolcode \n" +
"     and fr.fueltyperefcode = ftype) \n" +
"     as count_out \n" +
"  \n" +
" from \n" +
" (select trr.code as tcode, trr.buhname, trr.gosnumber \n" +
" from tktransportreal trr inner join tktransportmark trrmark \n" +
" on trr.transportmarkcode = trrmark.code \n" +
"      where \n" +
"           (  /*бензин 1 */ \n" +
"                   ( trr.fueltypecode not in (75000003) and "+typefuel+" = 1 ) \n" +
"                or \n" +
"              /*дизтопливо */ \n" +
"                   ( trr.fueltypecode =  75000003  and "+typefuel+" = 2 ) \n" +
"     ) \n" +
"     /*SUPP-2581 Если была выдача топлива тип кот. не совпадает с типом топлива на машине, то \n" +
"         такая машина тоже попадает в отчет*/ \n" +
"     or exists (select code from entravelsheetfuelremns as tvs where tvs.realtransportcode = trr.code \n" +
"     and tvs.dategen between to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
"     and last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
"     and ((tvs.fueltyperefcode not in (75000003) and "+typefuel+" = 1) or \n" +
"         (tvs.fueltyperefcode in (75000003) and "+typefuel+" = 2)) ) \n" +
" ) as transp, \n" +
" (select tkf.code as ftype, tkf.name as fueltypename from tkfueltype tkf \n" +
" where \n" +
"           (  /*бензин 1 */ \n" +
"                   ( tkf.code not in (75000003) and "+typefuel+" = 1 ) \n" +
"                or \n" +
"              /*дизтопливо */ \n" +
"                   ( tkf.code =  75000003  and "+typefuel+" = 2 ) \n" +
"     ) \n" +
" ) as fuel, \n" +
" (select \n" +
"     /*Код автотранспорта*/ \n" +
"      tktransportrealhistory.transportrealrefcode, \n" +
"      /*Начало срока владения этого автотранспорта этим МОЛом в этом месяце*/ \n" +
"     min(case \n" +
"         when DateStart >= to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
"             and (select trh1.finmolcode from tktransportrealhistory as trh1 where trh1.code =  tktransportrealhistory.parentrefcode) != tktransportrealhistory.finmolcode \n" +
"         then DateStart \n" +
"          else to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
"     end) as DateStart, \n" +
"     /*Конец срока владения этого автотранспорта этим МОЛом в этом месяце*/ \n" +
"     max(case \n" +
"         when coalesce(datefinal, last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy'))) <= last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
"         then coalesce(datefinal, last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy'))) \n" +
"         else last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
"     end) as DateEnd, \n" +
"     tktransportrealhistory.finMolCode \n" +
"      from \n" +
"      tktransportrealhistory \n" +
"      where \n" +
"      tktransportrealhistory.finmolcode = '"+molcode+"' \n" +
"      and ( \n" +
"              (tktransportrealhistory.DateStart >= to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
"              and coalesce(tktransportrealhistory.datefinal,tktransportrealhistory.DateStart) <= last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
"              ) \n" +
"          or (to_date('"+DateStart+"', 'dd.mm.yyyy') >= tktransportrealhistory.DateStart \n" +
"             and to_date('"+DateStart+"', 'dd.mm.yyyy') <= coalesce(tktransportrealhistory.datefinal, last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy'))) \n" +
"             ) \n" +
"            /*23.05.2012 - не попадали в отчет машины которые передавались с мола на мол в течении отчетного периода (исправлено)*/ \n" +
"         or ( \n" +
"             tktransportrealhistory.datestart between to_date('"+DateStart+"', 'dd.mm.yyyy')  and last_day(to_date('"+DateStart+"', 'dd.mm.yyyy')) \n" +
"            ) \n" +
"         or ( \n" +
"            coalesce(tktransportrealhistory.datefinal, tktransportrealhistory.datestart) between to_date('"+DateStart+"', 'dd.mm.yyyy')  and last_day(to_date(\n" +
" '"+DateStart+"', 'dd.mm.yyyy')) \n" +
"             ) \n" +
"          ) \n" +
"      group by tktransportrealhistory.transportrealrefcode, tktransportrealhistory.finmolcode) as transport_history \n" +
"      where transport_history.transportrealrefcode = transp.tcode \n" +
" ) AS OTBOR \n" +
" -- отбираем строки, в которых есть хоть какое-то движение \n" +
" WHERE \n" +
"     coalesce(first_count_start, 0) <> 0 \n" +
"     or coalesce(last_count_final, 0) <> 0 \n" +
"     or coalesce(prev_count_final, 0) <> 0 \n" +
"     or coalesce(count_in, 0) <> 0 \n" +
"     or coalesce(count_out, 0) <> 0 \n" +
"  \n" +
" ) AS tmp_tbl \n" +
" GROUP BY codefueltypenet, namefueltypenet \n" +
"  \n"
 ;



    System.out.println(" query = " + query);

    tempSt = connection.createStatement();
    rs = tempSt.executeQuery(query);

        int codefueltypenet = Integer.MIN_VALUE;
        String namefueltypenet = "";
        BigDecimal countgenstart = new BigDecimal(0);
        BigDecimal countin = new BigDecimal(0);

         while(rs.next())
            {
            codefueltypenet = rs.getInt(1);
            namefueltypenet = rs.getString(2);
            countgenstart = rs.getBigDecimal(3);
            countin = rs.getBigDecimal(4);



                HashMap hashMap = new HashMap();


                BigDecimal countSpisFuel = getSumSpisFuel(DateStart  , DateEnd , molcode  , typefuel , actstatus , codefueltypenet);
                BigDecimal countRestBak = new BigDecimal(0);
                countRestBak =   countgenstart.add(countin).subtract(countSpisFuel).setScale(2, BigDecimal.ROUND_HALF_UP);

                hashMap.put(sumrestbak.FUEL_NAME, namefueltypenet);
                hashMap.put(sumrestbak.COUNTGEN, countRestBak);

                rows.add(hashMap);
            }

    rs.close();
    tempSt.close();


    return new ReestrPaymentDS(rows.toArray());

} catch (SQLException e) {
    throw new ReportSystemException(e);
} finally {

}
}


/**
 * Возвращает сумму количества списаний топлива по условиям
 *
 * @return  сумму количества спис топл
 */
 public BigDecimal getSumSpisFuel(String DateStart  , String DateEnd , String molcode  , int typefuel , int actstatus , int codefueltype)
 {

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    BigDecimal sumPayFact = new BigDecimal(0);
     String sql =
        " select  \n" +
        " sum(quantity) as sumquantityrash     \n" +
        " from \n" +
        " (select nn_general, matname_general, quantity, cost, \n" +
        " case when acttyperefcode not in (4,2,1,5,3,7,9,19) /*расход типу акта для прочих типов актов  */ \n" +
        "       and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_other, \n" +
        " case when acttyperefcode not in (7,9) /*не услуги на сторону  */ \n" +
        "     and budgetrefcode = 240000001 \n" +
        "     then 1 else null end as is_energosbyt, \n" +
        " case when acttyperefcode = 4 /*расход типу акта по кап строительству */ \n" +
        "     and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_kapstr, \n" +
        " case when acttyperefcode = 2 /*расход типу акта реконструкция и модернизация */ \n" +
        "     and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_rm, \n" +
        " case when acttyperefcode in (1,5) /*расход типу акта капитальный и текущий ремонты */ \n" +
        "     and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_ktrem, \n" +
        " case when acttyperefcode = 3 /*расход типу акта : техническое обслуживание */ \n" +
        "     and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_tho, \n" +
        " case when (acttyperefcode in (7,9))  /*расход типу акта : работы на сторону и ТУ */ \n" +
        "     or  (budgetrefcode in (500000000,75000011)) /*или не равно энергосбыту или ВПТУ и ВРТУ */ \n" +
        "     then 1 else null end as is_usl, \n" +
        " case when acttyperefcode = 19 /*расход типу акта : по демонтажу */ \n" +
        "     and budgetrefcode not in (500000000,75000011,240000001) \n" +
        "     then 1 else null end as is_dem \n" +
        "  \n" +
        " from \n" +
        "  \n" +
        " (Select finm.nn as nn_general, finm.mat_name as matname_general, \n" +
        " act.acttyperefcode as acttyperefcode, pw.budgetrefcode as budgetrefcode, \n" +
        " sum(finm.quantity) as quantity, sum(finm.cost) as cost \n" +
        " From entravelsheet trsh \n" +
        "     inner join entravelsheetitem trshi on trsh.code = trshi.travelsheetrefcode \n" +
        "     inner join entravlshttm2trnsprttm trshi2entri on trshi.code = trshi2entri.travelsheetitemrefcode \n" +
        "     inner join entransport2enestimate entri2eni on trshi2entri.transportitemrefcode = entri2eni.transportrefcode \n" +
        "     inner join enestimateitem eni on eni.code = entri2eni.estimaterefcode \n" +
        "     inner join finmaterials finm on finm.estimateitemrefcode = entri2eni.estimaterefcode \n" +
        "     inner join enact2enplanwork act2plan on trshi.planrefcode = act2plan.plancode \n" +
        "     inner join enact act on act.code = act2plan.actrefcode \n" +
        "     inner join tkfueltype ft on ft.materialrefcode = eni.materialrefcode \n" +
        "     inner join enplanwork pw on trshi.planrefcode = pw.code \n" +
        "     inner join (tktransportreal trr inner join \n" +
        "     /*Подзапрос, который возвращает список кодов транспорта которым владел МОЛ с кодом molcode \n" +
        "     в период между DateStart, DateEnd*/ \n" +
        "                 (select \n" +
        "                     /*Код автотранспорта*/ \n" +
        "                      tktransportrealhistory.transportrealrefcode, \n" +
        "                      /*Начало срока владения этого автотранспорта этим МОЛом в этом месяце*/ \n" +
        "                     min(case \n" +
        "                         when DateStart >= to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
        "                             and (select trh1.finmolcode from tktransportrealhistory as trh1 where trh1.code =   \n" +
        " tktransportrealhistory.parentrefcode) != tktransportrealhistory.finmolcode \n" +
        "                         then DateStart \n" +
        "                          else to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
        "                         end) as DateStart, \n" +
        "                     /*Конец срока владения этого автотранспорта этим МОЛом в этом месяце*/ \n" +
        "                     max(case \n" +
        "                         when coalesce(datefinal, last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy'))) <= last_day(to_date('"+DateEnd+"',  \n" +
        " 'dd.mm.yyyy')) \n" +
        "                         then coalesce(datefinal, last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy'))) \n" +
        "                         else last_day(to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
        "                         end) as DateEnd \n" +
        "                  from \n" +
        "                      tktransportrealhistory \n" +
        "                  where \n" +
        "                      tktransportrealhistory.finmolcode = '"+molcode+"' \n" +
        "                  and ( \n" +
        "                          (tktransportrealhistory.DateStart >= to_date('"+DateStart+"', 'dd.mm.yyyy') \n" +
        "                          and coalesce(tktransportrealhistory.datefinal,tktransportrealhistory.DateStart) <= last_day(to_date('"+DateEnd+"',  \n" +
        " 'dd.mm.yyyy')) \n" +
        "                          ) \n" +
        "                          or (to_date('"+DateStart+"', 'dd.mm.yyyy') >= tktransportrealhistory.DateStart \n" +
        "                         and to_date('"+DateStart+"', 'dd.mm.yyyy') <= coalesce(tktransportrealhistory.datefinal, to_date('"+DateEnd+"',  \n" +
        " 'dd.mm.yyyy')) \n" +
        "                         ) \n" +
        "                         or (tktransportrealhistory.datestart between to_date('"+DateStart+"', 'dd.mm.yyyy') and to_date('"+DateEnd+"', 'dd.mm.yyyy')) \n" +
        "                         or (coalesce(tktransportrealhistory.datefinal, tktransportrealhistory.datestart) between to_date('"+DateStart+"', 'dd.mm.yyyy') and to_date(" +
        " '"+DateEnd+"', 'dd.mm.yyyy')) \n" +
        "  \n" +
        "                     ) \n" +
        "                  group by tktransportrealhistory.transportrealrefcode) as transport_history \n" +
        "     on transport_history.transportrealrefcode = trr.code) on trsh.transportrealcode = trr.code \n" +
        " Where \n" +
        "      (/*бензин 1*/ (ft.code not in (75000003) and "+typefuel+" = 1) \n" +
        "         or /*дизтопливо*/ (ft.code = 75000003 and "+typefuel+" = 2) ) \n" +
        "      and trshi.kindrefcode = 2 \n" +
        "      and finm.statusrefcode = 1 \n" +
        "      and finm.div_code = '"+molcode+"' \n" +
        "      and ((act.statusrefcode = 3 /*проведеные*/ and 1 = "+actstatus+" ) or \n" +
        "            (act.statusrefcode in (1,3,4) /*проведеные и черновые и на подписании*/ and 2 = "+actstatus+" )) \n" +
        "      and ft.code = "+codefueltype+" /*тип топлива*/ \n" +
        "      and pw.datestart between to_date( '"+DateStart+"', 'dd.mm.yyyy') and  to_date('"+DateEnd+"', 'dd.mm.yyyy') \n" +
        " Group by finm.nn  , finm.mat_name,  act.acttyperefcode, pw.budgetrefcode \n" +
        " ) T \n" +
        " ) TT \n" +
        "  \n" ;



     PreparedStatement stmt = null ;
     ResultSet set = null ;

     try
     {
         stmt = connection.prepareStatement(sql);

         set = stmt.executeQuery();

         if ( set.next() )
         {
            sumPayFact = set.getBigDecimal(1);
            return sumPayFact;
         }
         else
         {
             throw new EnergyproSystemException("\n\n Помилка при визначенні фактичних оплат!!!");
         }
     }
     catch(SQLException e)
     {
         System.out.println(e.getMessage()+"\nstatement - " + sql);
         throw new EnergyproSystemException(e);
     }
     finally
     {
     try {if (set != null) set.close();}             catch (SQLException e) {}
     try {if (stmt != null) stmt.close();} catch (SQLException e) {}
     stmt = null;
     }

 }

 /**
  * возврат суммы материалов и зарплаты с начислениями по кодам планов
  * для фактического отчета по расчистке трассы
 * @throws PersistenceException **/
 public BigDecimal getSumByPlanFact(String codesPlans) throws PersistenceException
 {
    BigDecimal out = new BigDecimal(0);
    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    out = new PlanWorkLogic(connection, userProfile).getSumByPlanFact(codesPlans);
    return  out;

 }


 public JRDataSource getChartPaymentDATA_old(
        String startDate, /*string*/
        int contractNumber, /*int*/
        int pkindrefcode, /*int*/
        String strGroupmaterials,/*string*/
        int porgcode, /*int*/
        int budgCode, /*int*/
        int prizn_period,  /*int*/
        int ddscode, /*int*/
        int orderstatus, /*int*/
        int showcurrentofforder,/*int*/
        int pkindrefcode_service, /*int*/
        int rqorderitemcode, /*int*/
        int onlyNotPayItem,
        int ShowPayOnlyCurPeriod
        ) throws PersistenceException

// (
//         java.lang.String,
//         int,
//         int,
//         java.lang.String,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int) in com.ksoe.energynet.reports.common.netScriptlet cannot be applied to (java.lang.String,java.lang.Integer,java.lang.Integer,java.lang.String,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer)
 {
        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
    try {


        System.out.println(" start getChartPaymentDATA ");
        ArrayList rows  = new ArrayList();
        String query = "";

        String test_ri_code = ""; // "1000000367";

    query =
            /*1*/    " select ddscode ,  \n" +
            /*2*/"  'тмц' as project,  \n" +
            /*3*/"  numberdoc ,  \n" +
            /*4*/"  orderdate ,   \n" +
            /*5*/"  mname ,   \n" +
            /*6*/"  meas ,   \n" +
            /*7*/"  contract ,  \n" +
            /*8*/"  (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo ,  \n" +
            /*9*/"  orgname , \n" +
            /*10*/"  (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact , \n" +
            /*11*/"  countgen ,  \n" +
            /*12*/"  p::numeric(15,3) , \n" +
            /*13*/"  pricewithnds::numeric(15,3) as pricewithnds, \n" +
            /*14*/" (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa , \n" +
            /*15*/"  oplatata_date ,  \n" +
            /*16*/"  to_char(postavka_date,'dd.mm.yyyy') as postavka_date , \n" +
            /*17*/"  estimateitemcode , \n" +
            /*18*/"  case when estimateitemcode_for_bill = '0' then estimateitemcode else estimateitemcode_for_bill end  as estimateitemcode_for_bill, \n" +
            /*19*/"  oicode , \n" +
            /*20*/"  budgetrefname , \n" +
            /*21*/"  deliverytime , \n" +
            /*22*/"  statussymbol , \n" +
            "              /*если на строке предоплата и 100 % тогда на 01 число ТД сумма по строке заявки минус сумма предоплат*/ \n" +
            "             case When typepayrefcode = 1 \n" +
            "                  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) = 100 \n" +
            "                      then   first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "              /*если на строке предоплата и не 100 % тогда на 01 число ТД*/ \n" +
            "                  When typepayrefcode = 1  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) <> 100 \n" +
            "                      then case when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
            " p_sum::numeric(15,2) \n" +
            "                             then case when postavka_date is not null then /*была поставка*/ \n" +
            "                                    case when postavka_date+7 <= to_date('" + startDate +"','dd.mm.yyyy') then  /*дата поставки +7 меньше или равна дате среза(выборки) */ \n" +
            "                                     postavka_date+7 /* дата аплаты приравниваем план дата поставка +7*/ \n" +
            "                                    else  first_day(to_date('" + startDate +"' ,'dd.mm.yyyy') )  /*если факт дата поставки + 7 дней больше чем дата формирования тд то дата*/  \n" +
            " /*на первое число ТД*/ \n" +
            "                                  end \n" +
            "                                  else  /*поставки не было*/ \n" +
            "                                  first_day(to_date('" + startDate +"' ,'dd.mm.yyyy') ) \n" +
            "                                  end \n" +
            "                             else \n" +
            "                                  last_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                        end \n" +
            "  \n" +
            "              /*если по факту и план дата оплаты = месяцу формирования ТД*/ \n" +
            "                 When typepayrefcode = 2 \n" +
            "                    and first_day(planneddatepays) >= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                     then planneddatepays \n" +
            "                 When typepayrefcode = 2 \n" +
            "                    and first_day(planneddatepays) < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                     then first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "              /*иначе на последний день формирование ТД*/ \n" +
            "                 Else  last_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
    /*23*/  " end as planneddatepays , \n" +
            "  \n" +
    /*24*/  "  -1 as pr_period ,  \n" +
            "  case when first_day(postavka_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
            "            'Кінцевий розрахунок' \n" +
            "            else   (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
    /*25*/  "  end as paymenttypename , \n" +
            "  \n" +
            "  case when first_day(postavka_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
            "                100 \n" +
            "                else \n" +
            "  (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
    /*26*/  "             end as paymentvalue \n" +
            " , coalesce( case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) < first_day(planneddatepays) then 0 \n" +
            "                    /*если на строке предоплата и не 100 % тогда на 01 число ТД*/ \n" +
            "                  When typepayrefcode = 1 \n" +
            "                  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) <> 100 \n" +
            "                      then case when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
            " p_sum::numeric(15,2) \n" +
            "                                 /*и дата поставки = дата формирования ТД то сумма = сумма по заявке минус оплаты*/ \n" +
            "                                and first_day(postavka_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                                then coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p_sum )::numeric(15,2) \n" +
            "  \n" +
            "                                when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric \n" +
            " (15,2) \n" +
            "                                 /*и дата поставки = дата формирования ТД то сумма = сумма по заявке минус оплаты*/ \n" +
            "                                and first_day(postavka_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                                then coalesce((select oii.sumgen*oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2) - p_sum \n" +
            "                                else \n" +
            "                                coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p_sum )::numeric(15,2) \n" +
            "                        end \n" +
            "                  else \n" +
            "                  coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p_sum )::numeric(15,2) \n" +
            "  \n" +
            "             end \n" +
    /*27*/    "             , 0) as plansumpay \n" +
            "  \n" +
            " from ( \n" +
            " /* все заявки */ \n" +
            " select ddscode ,  'тмц' as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
            "  sum(countgen) as countgen , sum(p)::numeric(15,3) as p  ,  pricewithnds::numeric(15,3) as pricewithnds, \n" +
            "  oplatata_date , postavka_date , \n" +
            "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
            "    group_concat( distinct estimateitemcode_for_bill::varchar ,',') as estimateitemcode_for_bill , \n" +
            "  oicode , \n" +
            "  budgetrefname , \n" +
            "  deliverytime , \n" +
            "  statussymbol , \n" +
            "  planneddatepays , \n" +
            "  sum(payincurperiod) as payincurperiod , \n" +
            "       /*сумма оплаты в пред периоде */ \n" +
            "  (select * from get_pay_prior_period2(oicode , group_concat( estimateitemcode::varchar ,',') , '" + startDate +"')) as paysumoldperiod , \n" +
            "  (select * from get_bill_sum_by_estimate(group_concat( estimateitemcode::text ,',') )) as billsum \n" +
            "  , typepayrefcode \n" +
            "  ,  coalesce(( Select sum(rqpaydocitem.summagen)   From rqpaydocitem2billitem , rqbillitem2orderitem  , \n" +
            "                                                                       rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                      where \n" +
            "  \n" +
            "                       rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "  \n" +
            "  \n" +
            "                       and rqpaydoc.dategen <= case when " + onlyNotPayItem + "  = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
            " оплаты до даты формирования тд*/ \n" +
            "                                                   then to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "                                                   else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
            "  \n" +
            "  \n" +
            "                       and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
            "                       and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
            "  \n" +
            "  \n" +
            "          ),0) as p_sum \n" +
            "  From ( \n" +
            " Select r.numberdoc , \n" +
            "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
            "        ri.materialnamegen , \n" +
            "        ri2e.countgen , \n" +
            "        ri2e.estimateitemcode , \n" +
            "        /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
            "         coalesce(( Select sum(rqbillitem2enestimattm.countgen)   From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
            "                                                                       rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and rqpaydoc.dategen < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "  \n" +
            "  \n" +
            "          ),0) as p \n" +
            "          /*естимейты из заявок пред периода отнимем естимейты по которым были оплаты \n" +
            "           останутся только естимейты по которым небыло оплаты */ \n" +
            "        , coalesce((select ri2e.estimateitemcode \n" +
            "         EXCEPT \n" +
            "         Select rqbillitem2enestimattm.estimateitemcode     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and rqpaydoc.dategen < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "         ),0) as  estimateitemcode_for_bill \n" +
            "          , \n" +
            "          (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode \n" +
            "  \n" +
            "           ) as mname \n" +
            "          , \n" +
            "          (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode  \n" +
            " and mm.measurementcode = ms.code \n" +
            "  \n" +
            "           ) as meas \n" +
            "           , \n" +
            "           rdds.txtcode as ddscode, \n" +
            "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
            "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
            "           ri.pricewithnds , \n" +
            "           ri.plannedDatePays as oplatata_date, \n" +
            "           ri.plannedDateDelivery as postavka_date , \n" +
            "           ri.code as oicode , \n" +
            "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
            "            COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
            "           case when r.statusrefcode = 2 then 'З' \n" +
            "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol \n" +
            "           , ri.planneddatepays \n" +
            "           ,  /*признак = или больше 1 если оплаты были в периоде формирования отчета т.е с первого числа месяца по указаное число при  \n" +
            " формировании отчета */ \n" +
            "         coalesce(( Select  count(rqpaydoc.code)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
            "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "  \n" +
            "  \n" +
            "          ),0) as payincurperiod \n" +
            "  \n" +
            " , ri.typepayrefcode \n" +
            "  \n" +
            "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
            "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
            "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
            "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i , tkmaterials sm \n" +
            " Where r.code = ri.orderrefcode \n" +
            "   and ri.code = ri2e.orderitemcode \n" +
            "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
            "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
            "   and i.code = ri2e.estimateitemcode \n" +
            "   and i.planrefcode = p.code \n" +

            (test_ri_code.equals("") ? "" : " and ri.code = " + test_ri_code )  +

            "   and p.budgetrefcode = db.code \n" +
            "   and p.kindcode = 2 \n" +
            "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
            "         /*или план завершений но была оплата в текущ периоде */ \n" +
            "        or ( p.statuscode = 8 \n" +
            "            and \n" +
            "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
            "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and enplanwork.code = enestimateitem.planrefcode \n" +
            "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
            "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
            "                       and enplanwork.statuscode = 8 \n" +
            "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "                       limit 1 \n" +
            "             ) = 8 ) \n" +
            "        ) \n" +
            "   and r.orderperiod < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "   /*ддс*/ \n" +
            "   and ( rdds.code = " + ddscode + " or " + ddscode + "   = 0 ) \n" +
            "   /* бюджетодержатель */ \n" +
            "   and (db.code = " + budgCode + " or " + budgCode + " = 0) \n" +
            "   /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */ \n" +
            "   and  (r.kindrefcode = "+pkindrefcode+" or ( "+pkindrefcode+" = 0 and r.kindrefcode in (4,5) )   ) \n" +
            "   and i.materialrefcode = sm.code \n" +
            "   /* номер договора */ \n" +
            "  and (ri.findocid = "+contractNumber+" or "+contractNumber+" = 0) \n" +
            // " and ri.code = 500209419 \n" + // test
            "   /*постачальник */ \n" +
            "   and (rqorg.id = " + porgcode + "  or  " + porgcode + " = 0 ) \n" +
            "   /*статус заЯвки*/ \n" +
            "   and  (r.statusrefcode = " + orderstatus + " or ( " + orderstatus + "   = 0 and r.statusrefcode in (2,3) )   ) \n" +
            "       /* группа материалов */ \n" +
            "   /*  and sm.code in \n" +
            "   (select tm.code from tkmaterials tm \n" +
            "     where {strGroupmaterials})*/ \n" +
            "      /* группа материалов */ \n" +
            " and " +  strGroupmaterials + " \n" +
            "  \n" +
            "  \n" +
            "                  /* отсекаем планы 2010 2011 */ \n" +
            "   and p.code not in (select code  from aaa_plan_code ) \n" +
            "  \n" +
            "  \n" +
            "  ) s1 \n" +
            "  \n" +
            "    Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
            "           , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname , deliverytime , statussymbol , planneddatepays \n" +
            "           , typepayrefcode \n" +
            "  ) s2 \n" +
            "    Where  (  /*показать строки заявки которые не оплачены в предыдущ периоде */ \n" +
            "             /* если план кол-во больше оплоченых и сумма счета больше оплаты допустим на 2 грн*/ \n" +
            "             ((s2.countgen > s2.p) and ((billsum - paysumoldperiod) > 2 )  ) \n" +
            "              /*если кол-во план <=0 и разница оплаты и счета больше 2 )*/ \n" +
            "             or ( \n" +
            "                  ( (s2.countgen - s2.p ) <= 0 ) \n" +
            "                  and \n" +
            "                   ( (billsum - paysumoldperiod) > 2  ) \n" +
            "                ) \n" +
            "              /*или были оплаты в текущ периоде*/ \n" +
            "             or \n" +
            "              payincurperiod > 0 \n" +
            "              /*если небыло счета*/ \n" +
            "             or \n" +
            "              COALESCE(billsum,0) = 0 \n" +
            "             /*если есть счет  но нет оплат ни в текущ периоде ни в прошлых*/ \n" +
            "             or \n" +
            "              ( COALESCE(billsum,0) > 0 and  COALESCE(paysumoldperiod,0) = 0 and s2.p = 0 ) \n" +
            "             /* временно для тд октября - не попадал  бензин 80 и дт*/ \n" +
            "             or oicode in (500139368 , 500139371) \n" +
            "           ) \n" +
            "    /* уберем строки с нулевыми плановыми суммами*/ \n" +
            "    and case when s2.countgen > s2.p then  (countgen - p) * pricewithnds ::numeric(15,3) \n" +
            "       when ( ( (s2.countgen - s2.p ) <= 0 )  and (billsum > paysumoldperiod ) )  then billsum-paysumoldperiod::numeric(15,3) \n" +
            "       /*NET-4066 Если была оплата в текущем периоде, то необходимо показывать эту строку завки*/ \n" +
            "       when payincurperiod > 0 then payincurperiod \n" +
            "       end \n" +
            "       is not null \n" +
            "  \n" +
            // " order by ddscode , budgetrefname \n" +





            " UNION ALL \n" +
            " /*КЛЕИМ ЗАЯВКИ ТЕКУЩЕГО ПЕРИОДА */ \n" +
            " select ddscode ,   \n" +
            " 'тмц' as project,  \n" +
            " numberdoc  ,  \n" +
            " orderdate ,   \n" +
            " mname ,   \n" +
            " meas ,   \n" +
            " contract ,  \n" +
            " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo ,   \n" +
            " orgname , \n" +
            " (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact , \n" +
            " 0 as countgen ,  \n" +
            " 0 as p , \n" +
            " pricewithnds , \n" +
            " (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa  , \n" +
            " oplatata_date ,  \n" +
            "  to_char(postavka_date,'dd.mm.yyyy') as postavka_date , \n" +
            " group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
            " group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
            " oicode , \n" +
            " budgetrefname , \n" +
            " deliverytime , \n" +
            " statussymbol  , \n" +
            " planneddatepays , \n" +
            " 1 as pr_period , \n" +
            "    (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) as paymenttypename , \n" +
            "    (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) as paymentvalue \n" +
            "  \n" +
            "  , coalesce( case when typepayrefcode = 1 then case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) >= first_day(planneddatepays) then coalesce((select  \n" +
            " oii.sumgen*oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode ))::numeric(15,2) \n" +
            "                                           else  0 \n" +
            "                                           end \n" +
            "                   when typepayrefcode = 2 then case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) >= first_day(planneddatepays) then  (select oii.sumgen  \n" +
            " from rqorderitem oii where oii.code = oicode ) \n" +
            "                                           else 0 \n" +
            "                                           end \n" +
            "                   when typepayrefcode is null then    (select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
            "         end  , 0) \n" +
            "         - \n" +
            "         /*otnimem summu oplat do tekushey dati esli nada pokazatb to 4to eshe ostalos oplatitb*/ \n" +
            "         case when " + onlyNotPayItem + "  = 1 then \n" +
            "               coalesce(( Select sum(rqpaydocitem.summagen)   From rqpaydocitem2billitem , rqbillitem2orderitem  , \n" +
            "                                                                           rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                         where \n" +
            "  \n" +
            "                           rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                           and rqpaydoc.dategen <= to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "                           and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
            "                           and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
            "              ),0) else 0 end \n" +
            "           as plansumpay \n" +
            "  from ( \n" +
            " Select r.numberdoc , \n" +
            "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
            "        ri.materialnamegen , \n" +
            "        ri2e.countgen , \n" +
            "        ri2e.estimateitemcode , \n" +
            "          (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode \n" +
            "  \n" +
            "           ) as mname \n" +
            "          , \n" +
            "          (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode  \n" +
            " and mm.measurementcode = ms.code \n" +
            "  \n" +
            "           ) as meas \n" +
            "           , \n" +
            "           rdds.txtcode as ddscode, \n" +
            "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
            "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
            "           ri.pricewithnds , \n" +
            "           ri.plannedDatePays as oplatata_date, \n" +
            "           ri.plannedDateDelivery as postavka_date , \n" +
            "           ri.code as oicode , \n" +
            "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
            "           COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
            "           case when r.statusrefcode = 2 then 'З' \n" +
            "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
            "           ri.planneddatepays \n" +
            "           , ri.typepayrefcode \n" +
            "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
            "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
            "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
            "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
            " Where r.code = ri.orderrefcode \n" +
            "   and ri.code = ri2e.orderitemcode \n" +
            "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
            "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
            "   and i.code = ri2e.estimateitemcode \n" +
            "   and i.planrefcode = p.code \n" +
            "   and p.kindcode = 2 \n" +

            (test_ri_code.equals("") ? "" : " and ri.code = " + test_ri_code )  +

            "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
            "         /*или план завершений но была оплата в текущ периоде */ \n" +
            "        or ( p.statuscode = 8 \n" +
            "            and \n" +
            "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
            "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and enplanwork.code = enestimateitem.planrefcode \n" +
            "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
            "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
            "                       and enplanwork.statuscode = 8 \n" +
            "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "             limit 1 \n" +
            "              ) = 8 ) \n" +
            "        ) \n" +
            "   and p.budgetrefcode = db.code \n" +
            "   and ( r.orderperiod between first_day( to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                           and to_date('" + startDate +"','dd.mm.yyyy')   and " + prizn_period + "   = 1 ) \n" +
            "   /*ддс*/ \n" +
            "   and ( rdds.code = " + ddscode + "   or " + ddscode + "   = 0 ) \n" +
            "   /* бюджетодержатель */ \n" +
            "   and (db.code = " + budgCode + "  or " + budgCode + "  = 0) \n" +
            "   /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */ \n" +
            "   and  (r.kindrefcode = " + pkindrefcode + "  or ( " + pkindrefcode + "  = 0 and r.kindrefcode in (4,5) )   ) \n" +
            "   and i.materialrefcode = sm.code \n" +
            "   /* номер договора */ \n" +
            "   and (ri.findocid = " + contractNumber + "  or " + contractNumber + "  = 0) \n" +
            "   /*постачальник */ \n" +
            "   and (rqorg.id = " + porgcode + "or  " + porgcode + "= 0 ) \n" +
            "   /*статус заЯвки*/ \n" +
            "   and  (r.statusrefcode = " + orderstatus + "  or ( " + orderstatus + "  = 0 and r.statusrefcode in (2,3) )   ) \n" +
            "    /* группа материалов */ \n" +
            "   and " + strGroupmaterials + "  \n" +
            "   /*отображать внеплановые заявки текущего месяца или нет 0 - нет 1 да*/ \n" +
            "   and (( r.kindrefcode = 4  and " + showcurrentofforder + "  = 0 ) or (r.kindrefcode in (4,5)  and " + showcurrentofforder + "  = 1  )) \n" +
            "  \n" +
            "   /* отсекаем планы 2010 2011 */ \n" +
            "   and p.code not in (select code  from aaa_plan_code ) \n" +
            "  \n" +
            "  \n" +
            "  \n" +
            "   ) s1 \n" +
            "  \n" +
            " Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
            " , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime  , \n" +
            " statussymbol , planneddatepays , typepayrefcode \n" +
            "  \n" +
            "  \n" +

            " UNION ALL \n" +
            " /*КЛЕИМ заявки по услугам ТЕКУЩЕГО ПЕРИОДА */ \n" +
            " select ddscode , \n" +
            " 'послуга' as project, \n" +
            "  numberdoc  , \n" +
            "  orderdate , \n" +
            "  mname , \n" +
            "  meas , \n" +
            "  contract ,  \n" +
            " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
            " orgname , \n" +
            "  sum(countgen) as countfact , \n" +
            "  0 as countgen , \n" +
            "  sum(p) as p , \n" +
            " pricewithnds ,  \n" +
            " sum(countgen)  * pricewithnds as summa , \n" +
            " oplatata_date , \n" +
            "  to_char(postavka_date,'dd.mm.yyyy') as postavka_date , \n" +
            " group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
            " group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
            "   oicode , \n" +
            "   budgetrefname , \n" +
            "   deliverytime , \n" +
            "   statussymbol  , \n" +
            "   planneddatepays , \n" +
            "   1 as pr_period , \n" +
            "  (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) as paymenttypename , \n" +
            "  (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) as paymentvalue , \n" +
            "   coalesce( case when typepayrefcode = 1 then case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) >= first_day(planneddatepays) then coalesce((select  \n" +
            " oii.sumgen*oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode ))::numeric(15,2) \n" +
            "                                           else  0 \n" +
            "                                           end \n" +
            "                   when typepayrefcode = 2 then case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) >= first_day(planneddatepays) then  (select oii.sumgen  \n" +
            " from rqorderitem oii where oii.code = oicode ) \n" +
            "                                           else 0 \n" +
            "                                           end \n" +
            "                   when typepayrefcode is null then    (select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
            "         end  , 0) \n" +
            "         - \n" +
            "         /*otnimem summu oplat do tekushey dati esli nada pokazatb to 4to eshe ostalos oplatitb*/ \n" +
            "         case when " + onlyNotPayItem + "  = 1 then \n" +
            "               coalesce(( Select sum(rqpaydocitem.summagen)   From rqpaydocitem2billitem , rqbillitem2orderitem  , \n" +
            "                                                                           rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                         where \n" +
            "  \n" +
            "                           rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "  \n" +
            "  \n" +
            "                           and rqpaydoc.dategen <= to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "  \n" +
            "  \n" +
            "                           and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
            "                           and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
            "  \n" +
            "  \n" +
            "              ),0) else 0 end - sum(p) \n" +
            "           as plansumpay \n" +
            "   From ( \n" +
            " Select r.numberdoc , \n" +
            "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
            "        ri.materialnamegen , \n" +
            "        ri2e.countgen , \n" +
            "        ri2e.estimateitemcode , \n" +
            "       /*название услуги вытягиваем такое как пропишут в коментах на плане */ \n" +
            "       (select '('|| tii.techkartnumber || ') ' || coalesce(pii.commentgen,'Невизначено') \n" +
            "                                    from enestimateitem iii , enplanworkitem pii , tktechcard tii \n" +
            "                                   Where iii.planitemrefcode = pii.code \n" +
            "                                     and iii.code = i.code \n" +
            "                                     and pii.kartarefcode = tii.code \n" +
            "                                     limit 1   ) as mname \n" +
            "          , \n" +
            "          (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode  \n" +
            " and mm.measurementcode = ms.code \n" +
            "  \n" +
            "           ) as meas \n" +
            "           , \n" +
            "  \n" +
            "            /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
            "         coalesce(( Select sum(rqpaydocitem2billitem.summagen)     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "  \n" +
            "                       and rqpaydoc.dategen <= case when " + onlyNotPayItem + "   = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
            " оплаты до даты формирования тд*/ \n" +
            "                                                   then to_date('" + startDate +"' ,'dd.mm.yyyy') \n" +
            "                                                   else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
            "  \n" +
            "  \n" +
            "          ),0) as p , \n" +
            "           rdds.txtcode as ddscode, \n" +
            "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
            "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
            "           ri.pricewithnds , \n" +
            "           ri.plannedDatePays as oplatata_date, \n" +
            "           ri.plannedDateDelivery as postavka_date , \n" +
            "           ri.code as oicode , \n" +
            "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
            "           COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
            "           case when r.statusrefcode = 2 then 'З' \n" +
            "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
            "           ri.planneddatepays \n" +
            "          ,ri.typepayrefcode \n" +
            "  \n" +
            "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
            "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
            "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
            "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
            " Where r.code = ri.orderrefcode \n" +
            "   and ri.code = ri2e.orderitemcode \n" +
            "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
            "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
            "   and i.code = ri2e.estimateitemcode \n" +
            "   and i.planrefcode = p.code \n" +

            (test_ri_code.equals("") ? "" : " and ri.code = " + test_ri_code )  +


            "   and p.kindcode = 2 \n" +
            "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
            "         /*или план завершений но была оплата в текущ периоде */ \n" +
            "        or ( p.statuscode = 8 \n" +
            "            and \n" +
            "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
            "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
            "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
            "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
            "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
            "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
            "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
            "                       and enplanwork.code = enestimateitem.planrefcode \n" +
            "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
            "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
            "                       and enplanwork.statuscode = 8 \n" +
            "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
            "                    limit 1 \n" +
            "                    ) = 8 ) \n" +
            "        ) \n" +
            "   and p.budgetrefcode = db.code \n" +
            "   and i.materialrefcode = sm.code \n" +
            "   and ( r.orderperiod between  add_months(first_day( to_date('" + startDate +"','dd.mm.yyyy') ),-1) \n" +
            "                           and last_day(add_months(to_date('" + startDate +"','dd.mm.yyyy'),-1)::date)   and " + prizn_period + "   = 1 \n" +
            "  \n" +
            "                            or r.orderperiod between first_day( to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
            "                           and last_day(to_date('" + startDate +"','dd.mm.yyyy')::date)   and " + prizn_period + "   = 1 \n" +
            "                       ) \n" +
            "   /*ддс*/ \n" +
            "   and ( rdds.code = " + ddscode + "  or " + ddscode + "   = 0 ) \n" +
            "   /* бюджетодержатель */ \n" +
            "   and (db.code = " + budgCode + "  or " + budgCode + "  = 0) \n" +
            "   /* ХОЕ планова услуги = 7 , ХОЕ позапланова услуги = 8 */ \n" +
            "   and  (r.kindrefcode = " + pkindrefcode_service + "  or ( " + pkindrefcode_service + "  = 0 and r.kindrefcode in (7,8) )   ) \n" +
            "   /* номер договора */ \n" +
            "  and (ri.findocid = " + contractNumber + "  or " + contractNumber + "  = 0) \n" +
            "   /*постачальник */ \n" +
            " and (rqorg.id = " + porgcode + "or  " + porgcode + "= 0 ) \n" +
            "   /*статус заЯвки*/ \n" +
            "  and  (r.statusrefcode = " + orderstatus + "  or ( " + orderstatus + "  = 0 and r.statusrefcode in (2,3) )   ) \n" +
            " /* группа материалов */ \n" +
            " and " + strGroupmaterials + " \n" +
            "   /*отображать внеплановые заявки по услугам текущего месяца или нет 0 - нет 1 да*/ \n" +
            "  /*and (( r.kindrefcode = 7   and {showcurrentofforder} = 0 ) or (r.kindrefcode in (7,8)  and {showcurrentofforder} = 1  ))*/ \n" +
            "  \n" +
            "  \n" +
            "                  /* отсекаем планы 2010 2011 */ \n" +
            "   and p.code not in (select code  from aaa_plan_code ) \n" +
            "  \n" +
            "  \n" +
            "  \n" +
            "   ) s_usl \n" +
            "  \n" +
            "  \n" +
            "  \n" +
            "   Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
            "           , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime  , statussymbol , planneddatepays , s_usl.typepayrefcode \n" +
            "  \n" +
            "  \n";

            // Разобьем страшный запрос на 2 (а то происходит СтекОверфлоу у некоторых при билде ;))
            String query1 =

                "   UNION ALL \n" +
                " /*КЛЕИМ неоплаченые заявки по услугам предыдущих ПЕРИОДов */ \n" +
                "   select ddscode ,  project, numberdoc  , orderdate ,  mname ,  meas ,  contract ,  \n" +
                "   (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
                "   orgname , \n" +
                "   countfact , \n" +
                "   countgen ,  p , \n" +
                "   pricewithnds , \n" +
                "   countgen  * pricewithnds as summa , \n" +
                "   oplatata_date , \n" +
                "  to_char(postavka_date,'dd.mm.yyyy') as postavka_date , \n" +
                "   estimateitemcode , \n" +
                "   estimateitemcode_for_bill , \n" +
                "   oicode , \n" +
                "   budgetrefname , \n" +
                "   deliverytime , \n" +
                "   statussymbol  , \n" +
                " case When typepayrefcode = 1 \n" +
                "                  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) = 100 \n" +
                "                      then   first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "             /*если на строке предоплата и не 100 % тогда на 01 число ТД*/ \n" +
                "                  When typepayrefcode = 1  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) <> 100 \n" +
                "                      then case when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p::numeric \n" +
                " (15,2) \n" +
                "                             then case when postavka_date is not null then  /*была поставка*/ \n" +
                "                                    case when postavka_date+7 <= to_date('" + startDate +"','dd.mm.yyyy') then  /* дата поставки +7 меньше или равна дате среза \n" +
                " (выборки)*/ \n" +
                "                                     postavka_date+7 /*дата аплаты приравниваем план дата поставка +7*/ \n" +
                "                                    else  first_day(to_date('" + startDate +"' ,'dd.mm.yyyy') ) /*если факт дата поставки + 7 дней больше чем дата формирования тд то  \n" +
                " дата на первое число ТД*/ \n" +
                "                                  end \n" +
                "                                  else /* поставки не было*/ \n" +
                "                                  first_day(to_date('" + startDate +"' ,'dd.mm.yyyy') ) \n" +
                "                                  end \n" +
                "                             else \n" +
                "                                  last_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "                        end \n" +
                "  \n" +
                "             /*если по факту и план дата оплаты = месяцу формирования ТД*/ \n" +
                "                 When typepayrefcode = 2 \n" +
                "                    and first_day(planneddatepays) >= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "                     then planneddatepays \n" +
                "                 When typepayrefcode = 2 \n" +
                "                    and first_day(planneddatepays) < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "                     then first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "             /* иначе на последний день формирование ТД*/ \n" +
                "                 Else  last_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "             end as planneddatepays  , \n" +
                "   pr_period /*, \n" +
                "   payCurPeriod*/ \n" +
                " , case when first_day(postavka_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                "            'Кінцевий розрахунок' \n" +
                "            else   (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                "   end as paymenttypename \n" +
                " , case when first_day(postavka_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then 100 \n" +
                "                else \n" +
                "  (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                "   end as paymentvalue \n" +
                " /*, case when  (summa - p) = 0 then null \n" +
                "        when  (summa - p) < 0 then null else summa - p end as plansumpay_old */ \n" +
                "  \n" +
                " , coalesce( case when first_day(to_date('" + startDate +"','dd.mm.yyyy') ) < first_day(planneddatepays) then 0 \n" +
                "                   /*если на строке предоплата и не 100 % тогда на 01 число ТД*/ \n" +
                "                  When typepayrefcode = 1 \n" +
                "                  and (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) <> 100 \n" +
                "                      then case when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p::numeric \n" +
                " (15,2) \n" +
                "                                /*и дата поставки = дата формирования ТД то сумма = сумма по заявке минус оплаты*/ \n" +
                "                                and first_day(postavka_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "                                then coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p )::numeric(15,2) \n" +
                "  \n" +
                "                                when coalesce((select oii.sumgen* oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p::numeric(15,2) \n" +
                "                                /*и дата поставки = дата формирования ТД то сумма = сумма по заявке минус оплаты*/ \n" +
                "                                and first_day(postavka_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                "                                then coalesce((select oii.sumgen*oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2) - p \n" +
                "                                else \n" +
                "                                coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p )::numeric(15,2) \n" +
                "                        end \n" +
                "                  else \n" +
                "                  coalesce((select oii.sumgen from rqorderitem oii where oii.code = oicode ) - p )::numeric(15,2) \n" +
                "  \n" +
                "             end \n" +
                "             , 0) \n" +
                "            as plansumpay \n" +
                " From \n" +
                " ( \n" +
                " select ddscode ,  'послуга'::varchar as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                "     sum(countgen) as  countfact , \n" +
                "    sum(countgen) as countgen ,  sum(p) as p , \n" +
                "    pricewithnds , sum(countgen)  * pricewithnds as summa , \n" +
                "   oplatata_date , postavka_date , \n" +
                "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
                "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
                "   oicode , \n" +
                "   budgetrefname , \n" +
                "   deliverytime , \n" +
                "   statussymbol  , \n" +
                "   planneddatepays , \n" +
                "   1 as pr_period , \n" +
                "   payCurPeriod , \n" +
                "   typepayrefcode \n" +
                "   , ispaid \n" +
                "   From ( \n" +
                " Select r.numberdoc , \n" +
                "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
                "        ri.materialnamegen , \n" +
                "        ri2e.countgen , \n" +
                "        ri2e.estimateitemcode , \n" +
                "        /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
                "         coalesce(( Select sum(rqpaydocitem2billitem.summagen)     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                "  \n" +
                "                       and rqpaydoc.dategen <= case when " + onlyNotPayItem + "  = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                " оплаты до даты формирования тд*/ \n" +
                "                                                   then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                "                                                   else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                "  \n" +
                "  \n" +
                "          ),0) as p  , \n" +
                "       /*название услуги вытягиваем такое как пропишут в коментах на плане */ \n" +
                "       (select '('|| tii.techkartnumber || ') ' || coalesce(pii.commentgen,'Невизначено') \n" +
                "                                    from enestimateitem iii , enplanworkitem pii , tktechcard tii \n" +
                "                                   Where iii.planitemrefcode = pii.code \n" +
                "                                     and iii.code = i.code \n" +
                "                                     and pii.kartarefcode = tii.code \n" +
                "                                     limit 1   ) as mname \n" +
                "          , \n" +
                " (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms \n" +
                " where mm.code = ee.materialrefcode \n" +
                " and ee.code = ri2e.estimateitemcode \n" +
                " and mm.measurementcode = ms.code \n" +
                " ) as meas \n" +
                "           , \n" +
                "           rdds.txtcode as ddscode, \n" +
                "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
                "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
                "           ri.pricewithnds , \n" +
                "           ri.plannedDatePays as oplatata_date, \n" +
                "           ri.plannedDateDelivery as postavka_date , \n" +
                "           ri.code as oicode , \n" +
                "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
                "           COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
                "           case when r.statusrefcode = 2 then 'З' \n" +
                "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
                "           ri.planneddatepays \n" +
                " , \n" +
                " /* Оплаты в периоде выборки */ \n" +
                " coalesce(( \n" +
                " Select coalesce(sum(p2b.summagen),0) as summagen \n" +
                " From rqbillitem2enestimattm be , rqbillitem bii , rqpaydocitem2billitem p2b , rqbill r, rqpaydocitem pdi, rqpaydoc pd , \n" +
                "      rqorderitem2enestimttm o2e \n" +
                "  where  be.estimateitemcode  in (ri2e.estimateitemcode) \n" +
                "    and be.billitemcode = bii.code \n" +
                "    and p2b.billitemcode = bii.code \n" +
                "    and r.code = bii.billrefcode \n" +
                "    and o2e.estimateitemcode = be.estimateitemcode \n" +
                "    and o2e.orderitemcode = ri.code \n" +
                "    and be.billitemcode in ( Select distinct bi.code as billitem \n" +
                "                         From rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, \n" +
                "                              rqbill b, rqbillitem bi, rqbillitem2orderitem bo \n" +
                "                         where be.estimateitemcode = oe.estimateitemcode \n" +
                "                          and be.billitemcode = bo.billitemrefcode \n" +
                "                          and bo.orderitemrefcode = oe.orderitemcode \n" +
                "                          and bi.code = bo.billitemrefcode \n" +
                "                          and b.code = bi.billrefcode \n" +
                "                          and bo.orderitemrefcode = ri.code \n" +
                "                          and be.estimateitemcode in  (ri2e.estimateitemcode) \n" +
                "                         ) \n" +
                "    and pdi.code = p2b.paydocitemcode \n" +
                "    and pdi.paydocrefcode = pd.code \n" +
                "    and pd.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy')) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                "    ),0) as  payCurPeriod \n" +
                "    , ri.typepayrefcode \n" +
                "    , ri.ispaid \n" +
                "  \n" +
                "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
                "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
                "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
                "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
                " Where r.code = ri.orderrefcode \n" +
                "   and ri.code = ri2e.orderitemcode \n" +
                "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
                "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
                "   and i.code = ri2e.estimateitemcode \n" +
                "   and i.planrefcode = p.code \n" +

                (test_ri_code.equals("") ? "" : " and ri.code = " + test_ri_code )  +


                "   and p.kindcode = 2 \n" +
                "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
                "         /*или план завершений но была оплата в текущ периоде */ \n" +
                "        or ( p.statuscode = 8 \n" +
                "            and \n" +
                "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
                "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                "                       and enplanwork.code = enestimateitem.planrefcode \n" +
                "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
                "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
                "                       and enplanwork.statuscode = 8 \n" +
                "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                "            limit 1 \n" +
                "             ) = 8 ) \n" +
                "        ) \n" +
                "   and p.budgetrefcode = db.code \n" +
                "   and i.materialrefcode = sm.code \n" +
                "   and r.orderperiod < add_months( first_day(to_date('" + startDate +"','dd.mm.yyyy') ),-1) \n" +
                "   /*ддс*/ \n" +
                "   and ( rdds.code = " + ddscode + "  or " + ddscode + "   = 0 ) \n" +
                "   /* бюджетодержатель */ \n" +
                "   and (db.code = " + budgCode + "  or " + budgCode + "  = 0) \n" +
                "   /* ХОЕ планова услуги = 7 , ХОЕ позапланова услуги = 8 */ \n" +
                "   and  (r.kindrefcode = " + pkindrefcode_service + "  or ( " + pkindrefcode_service + "  = 0 and r.kindrefcode in (7,8) )   ) \n" +
                "   /* номер договора */ \n" +
                "   and (ri.findocid = " + contractNumber + "  or " + contractNumber + "  = 0) \n" +
                "   /*постачальник */ \n" +
                "   and (rqorg.id = " + porgcode + "or  " + porgcode + "= 0 ) \n" +
                "   /* статус заЯвки */ \n" +
                "   and  (r.statusrefcode = " + orderstatus + "  or ( " + orderstatus + "  = 0 and r.statusrefcode in (2,3) )   ) \n" +
                "   /* отображать внеплановые заявки по услугам текущего месяца или нет 0 - нет 1 да */ \n" +
                "   and (( r.kindrefcode = 7   and " + showcurrentofforder + "  = 0 ) or (r.kindrefcode in (7,8)  and " + showcurrentofforder + "  = 1  )) \n" +
                "  \n" +
                "               /* отсекаем планы 2010 2011 */ \n" +
                "               and p.code not in (select code  from aaa_plan_code ) \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "   ) s_usl \n" +
                "  \n" +
                "   Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
                "           , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime \n" +
                "           , statussymbol , planneddatepays , payCurPeriod ,typepayrefcode ,  ispaid \n" +
                "   ) s_usl2 \n" +
                "    Where  ((s_usl2.summa > s_usl2.p) or (payCurPeriod > 0) ) \n" +
                "    and ( coalesce(ispaid,0) <> 1 or (payCurPeriod > 0) ) \n" +

                "  Order by ddscode , budgetrefname , mname , project \n";


            query = query + query1;



            Statement tempSt;

            tempSt = connection.createStatement();
            System.out.println(query);
            ResultSet rs = tempSt.executeQuery(query);

            BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
            Date PAY_PLAN_DATE = null;

            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date startDT = formatter.parse(startDate);

            BigDecimal sd1= new BigDecimal(0),sd2= new BigDecimal(0),sd3= new BigDecimal(0),sd4= new BigDecimal(0),sd5= new BigDecimal(0),sd6= new BigDecimal(0),sd7= new BigDecimal(0),sd8= new BigDecimal(0),sd9= new BigDecimal(0),sd10= new BigDecimal(0),sd11= new BigDecimal(0),sd12= new BigDecimal(0),sd13= new BigDecimal(0),sd14= new BigDecimal(0),sd15= new BigDecimal(0),sd16= new BigDecimal(0),sd17= new BigDecimal(0),sd18= new BigDecimal(0),sd19= new BigDecimal(0),sd20= new BigDecimal(0),sd21= new BigDecimal(0),sd22= new BigDecimal(0),sd23= new BigDecimal(0),sd24= new BigDecimal(0),sd25= new BigDecimal(0),sd26= new BigDecimal(0),sd27= new BigDecimal(0),sd28= new BigDecimal(0),sd29= new BigDecimal(0),sd30= new BigDecimal(0),sd31 = new BigDecimal(0);

            BigDecimal[] dayArr = new BigDecimal[31];

            // сбросим массив в 0
            for(int i = 0; i < 31; i++){
                dayArr[i] = new BigDecimal(0);
            }

            while(rs.next())
            {

                HashMap hashMap = new HashMap();

                sd1= new BigDecimal(0);sd2= new BigDecimal(0);sd3= new BigDecimal(0);sd4= new BigDecimal(0);sd5= new BigDecimal(0);sd6= new BigDecimal(0);sd7= new BigDecimal(0);sd8= new BigDecimal(0);sd9= new BigDecimal(0);sd10= new BigDecimal(0);sd11= new BigDecimal(0);sd12= new BigDecimal(0);sd13= new BigDecimal(0);sd14= new BigDecimal(0);sd15= new BigDecimal(0);sd16= new BigDecimal(0);sd17= new BigDecimal(0);sd18= new BigDecimal(0);sd19= new BigDecimal(0);sd20= new BigDecimal(0);sd21= new BigDecimal(0);sd22= new BigDecimal(0);sd23= new BigDecimal(0);sd24= new BigDecimal(0);sd25= new BigDecimal(0);sd26= new BigDecimal(0);sd27= new BigDecimal(0);sd28= new BigDecimal(0);sd29= new BigDecimal(0);sd30= new BigDecimal(0);sd31 = new BigDecimal(0);
                PAY_PLAN_DATE = rs.getDate(23);

                if (PAY_PLAN_DATE.before(startDT) || PAY_PLAN_DATE.equals(startDT)){
                    sd1 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 1) )) {
                    sd2 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 2) )) {
                    sd3 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 3) )) {
                    sd4 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 4) )) {
                    sd5 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 5) )) {
                    sd6 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 6) )) {
                    sd7 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 7) )) {
                    sd8 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 8) )) {
                    sd9 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 9) )) {
                    sd10 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 10) )) {
                    sd11 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 11) )) {
                    sd12 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 12) )) {
                    sd13 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 13) )) {
                    sd14 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 14) )) {
                    sd15 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 15) )) {
                    sd16 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 16) )) {
                    sd17 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 17) )) {
                    sd18 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 18) )) {
                    sd19 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 19) )) {
                    sd20 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 20) )) {
                    sd21 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 21) )) {
                    sd22 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 22) )) {
                    sd23 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 23) )) {
                    sd24 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 24) )) {
                    sd25 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 25) )) {
                    sd26 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 26) )) {
                    sd27 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 27) )) {
                    sd28 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 28)) ) {
                    sd29 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 29))  ) {
                    sd30 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 30))  ) {
                    sd31 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.after(Tools.getLastDayOfMonth(startDT))) {
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 28 ) {
                        sd28 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 29 ) {
                        sd29 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 30 ) {
                        sd30 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 31 ) {
                        sd31 = rs.getBigDecimal(27);
                    }

                }

                // отнимем оплаты с по строке заявки которые не попали в селект
                // поидее (выбиралось по дате платежа <= dateStart  ) значит оплаты смотрим с 02 числа месяца по последнее число месяца
                BigDecimal sumpayCurPeriod = new BigDecimal(0);
                sumpayCurPeriod =  this.getSumPayByOrderItemCode(rs.getInt(19),Tools.addDays(startDT, 1),Tools.getLastDayOfMonth(startDT));

                sd1 = sd1.subtract(sumpayCurPeriod);
                sd2 = sd2.subtract(sumpayCurPeriod);
                sd3 = sd3.subtract(sumpayCurPeriod);
                sd4 = sd4.subtract(sumpayCurPeriod);
                sd5 = sd5.subtract(sumpayCurPeriod);
                sd6 = sd6.subtract(sumpayCurPeriod);
                sd7 = sd7.subtract(sumpayCurPeriod);
                sd8 = sd8.subtract(sumpayCurPeriod);
                sd9 = sd9.subtract(sumpayCurPeriod);
                sd10 = sd10.subtract(sumpayCurPeriod);
                sd11 = sd11.subtract(sumpayCurPeriod);
                sd12 = sd12.subtract(sumpayCurPeriod);
                sd13 = sd13.subtract(sumpayCurPeriod);
                sd14 = sd14.subtract(sumpayCurPeriod);
                sd15 = sd15.subtract(sumpayCurPeriod);
                sd16 = sd16.subtract(sumpayCurPeriod);
                sd17 = sd17.subtract(sumpayCurPeriod);
                sd18 = sd18.subtract(sumpayCurPeriod);
                sd19 = sd19.subtract(sumpayCurPeriod);
                sd20 = sd20.subtract(sumpayCurPeriod);
                sd21 = sd21.subtract(sumpayCurPeriod);
                sd22 = sd22.subtract(sumpayCurPeriod);
                sd23 = sd23.subtract(sumpayCurPeriod);
                sd24 = sd24.subtract(sumpayCurPeriod);
                sd25 = sd25.subtract(sumpayCurPeriod);
                sd26 = sd26.subtract(sumpayCurPeriod);
                sd27 = sd27.subtract(sumpayCurPeriod);
                sd28 = sd28.subtract(sumpayCurPeriod);
                sd29 = sd29.subtract(sumpayCurPeriod);
                sd30 = sd30.subtract(sumpayCurPeriod);
                sd31 = sd31.subtract(sumpayCurPeriod);

                hashMap.put(ChartPaymentDS.DDSCODE, rs.getString(1));
                hashMap.put(ChartPaymentDS.PROJECT, rs.getString(2));
                hashMap.put(ChartPaymentDS.NUMBERDOC, rs.getString(3));
                hashMap.put(ChartPaymentDS.ORDERDATE, rs.getString(4));
                hashMap.put(ChartPaymentDS.STATUSSYMBOL, rs.getString(22));
                hashMap.put(ChartPaymentDS.OKPO, rs.getString(8));
                hashMap.put(ChartPaymentDS.ORGNAME, rs.getString(9));
                hashMap.put(ChartPaymentDS.CONTRACT, rs.getString(7));
                hashMap.put(ChartPaymentDS.MNAME, rs.getString(5));
                hashMap.put(ChartPaymentDS.MEAS, rs.getString(6));
                hashMap.put(ChartPaymentDS.COUNTFACT, rs.getBigDecimal(10));
                hashMap.put(ChartPaymentDS.PRICEWITHNDS , rs.getBigDecimal(13));
                hashMap.put(ChartPaymentDS.SUMMA, rs.getBigDecimal(14));
                hashMap.put(ChartPaymentDS.POSTAVKA_DATE, rs.getString(16));
                hashMap.put(ChartPaymentDS.DELIVERYTIME, rs.getInt(21));
                hashMap.put(ChartPaymentDS.BUDGETREFNAME, rs.getString(20));
                hashMap.put(ChartPaymentDS.PAYMENTTYPENAME, rs.getString(25));
                hashMap.put(ChartPaymentDS.PAYMENTVALUE, rs.getInt(26));
                hashMap.put(ChartPaymentDS.OICODE, rs.getInt(19));

                if (sd1.doubleValue() < 0 ){sd1 = new BigDecimal(0);}
                if (sd2.doubleValue() < 0 ){sd2 = new BigDecimal(0);}
                if (sd3.doubleValue() < 0 ){sd3 = new BigDecimal(0);}
                if (sd4.doubleValue() < 0 ){sd4 = new BigDecimal(0);}
                if (sd5.doubleValue() < 0 ){sd5 = new BigDecimal(0);}
                if (sd6.doubleValue() < 0 ){sd6 = new BigDecimal(0);}
                if (sd7.doubleValue() < 0 ){sd7 = new BigDecimal(0);}
                if (sd8.doubleValue() < 0 ){sd8 = new BigDecimal(0);}
                if (sd9.doubleValue() < 0 ){sd9 = new BigDecimal(0);}
                if (sd10.doubleValue() < 0 ){sd10 = new BigDecimal(0);}
                if (sd11.doubleValue() < 0 ){sd11 = new BigDecimal(0);}
                if (sd12.doubleValue() < 0 ){sd12 = new BigDecimal(0);}
                if (sd13.doubleValue() < 0 ){sd13 = new BigDecimal(0);}
                if (sd14.doubleValue() < 0 ){sd14 = new BigDecimal(0);}
                if (sd15.doubleValue() < 0 ){sd15 = new BigDecimal(0);}
                if (sd16.doubleValue() < 0 ){sd16 = new BigDecimal(0);}
                if (sd17.doubleValue() < 0 ){sd17 = new BigDecimal(0);}
                if (sd18.doubleValue() < 0 ){sd18 = new BigDecimal(0);}
                if (sd19.doubleValue() < 0 ){sd19 = new BigDecimal(0);}
                if (sd20.doubleValue() < 0 ){sd20 = new BigDecimal(0);}
                if (sd21.doubleValue() < 0 ){sd21 = new BigDecimal(0);}
                if (sd22.doubleValue() < 0 ){sd22 = new BigDecimal(0);}
                if (sd23.doubleValue() < 0 ){sd23 = new BigDecimal(0);}
                if (sd24.doubleValue() < 0 ){sd24 = new BigDecimal(0);}
                if (sd25.doubleValue() < 0 ){sd25 = new BigDecimal(0);}
                if (sd26.doubleValue() < 0 ){sd26 = new BigDecimal(0);}
                if (sd27.doubleValue() < 0 ){sd27 = new BigDecimal(0);}
                if (sd28.doubleValue() < 0 ){sd28 = new BigDecimal(0);}
                if (sd29.doubleValue() < 0 ){sd29 = new BigDecimal(0);}
                if (sd30.doubleValue() < 0 ){sd30 = new BigDecimal(0);}
                if (sd31.doubleValue() < 0 ){sd31 = new BigDecimal(0);}

                hashMap.put(ChartPaymentDS.SD1, sd1);
                hashMap.put(ChartPaymentDS.SD2, sd2);
                hashMap.put(ChartPaymentDS.SD3, sd3);
                hashMap.put(ChartPaymentDS.SD4, sd4);
                hashMap.put(ChartPaymentDS.SD5, sd5);
                hashMap.put(ChartPaymentDS.SD6, sd6);
                hashMap.put(ChartPaymentDS.SD7, sd7);
                hashMap.put(ChartPaymentDS.SD8, sd8);
                hashMap.put(ChartPaymentDS.SD9, sd9);
                hashMap.put(ChartPaymentDS.SD10, sd10);
                hashMap.put(ChartPaymentDS.SD11, sd11);
                hashMap.put(ChartPaymentDS.SD12, sd12);
                hashMap.put(ChartPaymentDS.SD13, sd13);
                hashMap.put(ChartPaymentDS.SD14, sd14);
                hashMap.put(ChartPaymentDS.SD15, sd15);
                hashMap.put(ChartPaymentDS.SD16, sd16);
                hashMap.put(ChartPaymentDS.SD17, sd17);
                hashMap.put(ChartPaymentDS.SD18, sd18);
                hashMap.put(ChartPaymentDS.SD19, sd19);
                hashMap.put(ChartPaymentDS.SD20, sd20);
                hashMap.put(ChartPaymentDS.SD21, sd21);
                hashMap.put(ChartPaymentDS.SD22, sd22);
                hashMap.put(ChartPaymentDS.SD23, sd23);
                hashMap.put(ChartPaymentDS.SD24, sd24);
                hashMap.put(ChartPaymentDS.SD25, sd25);
                hashMap.put(ChartPaymentDS.SD26, sd26);
                hashMap.put(ChartPaymentDS.SD27, sd27);
                hashMap.put(ChartPaymentDS.SD28, sd28);
                hashMap.put(ChartPaymentDS.SD29, sd29);
                hashMap.put(ChartPaymentDS.SD30, sd30);
                hashMap.put(ChartPaymentDS.SD31, sd31);

                rows.add(hashMap);
            }

            rs.close();
            tempSt.close();



        return new ChartPaymentDS(rows.toArray());

    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (ParseException e) {
        throw new ReportSystemException(e);
    } finally {

    }
    }


/*
 * Сумма оплат по строке заявки за период
 * */
 public BigDecimal getSumPayByOrderItemCode( int oicode , Date startDate , Date finalDate )

 throws PersistenceException
 {
 boolean isDebug = false ;

 JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
 Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
 UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

 if (isDebug){
 System.out.println("start getSumPayByOrderItemCode:" + new Date());
 }


 String sql =
    "  select   coalesce(( Select sum(rqpaydocitem.summagen)    \n" +
    "           From rqpaydocitem2billitem , rqbillitem2orderitem  , rqbillitem  , rqpaydocitem , rqpaydoc  \n" +
    " where  \n" +
    "                \n" +
    " rqpaydocitem2billitem.billitemcode = rqbillitem.code  \n" +
    " and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode  \n" +
    " and rqpaydoc.code = rqpaydocitem.paydocrefcode           \n" +
    " and rqpaydoc.dategen between ? and ? \n" +
    " and rqbillitem2orderitem.orderitemrefcode = "+ oicode + "   \n" +
    " and rqbillitem2orderitem.billitemrefcode = rqbillitem.code  \n" +
    "                \n" +
    "                \n" +
    " ),0) as p_sum  \n" +
    "  \n" ;


 if (isDebug){
 System.out.println("sql : " + sql);

 }

 PreparedStatement statement = null;
 ResultSet  resultSet = null;

 BigDecimal out = new BigDecimal(0);
 try {

 statement = connection.prepareStatement(sql);
 statement.setDate(1,new java.sql.Date(startDate.getTime()));
 statement.setDate(2,new java.sql.Date(finalDate.getTime()));

 resultSet = statement.executeQuery();


 while(resultSet.next())
 {
 out =  resultSet.getBigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP);
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





 public JRDataSource getChartPaymentDATA(
        String startDate, /*string*/
        int contractNumber, /*int*/
        int pkindrefcode, /*int*/
        String strGroupmaterials,/*string*/
        int porgcode, /*int*/
        int budgCode, /*int*/
        int prizn_period,  /*int*/
        int ddscode, /*int*/
        int orderstatus, /*int*/
        int showcurrentofforder,/*int*/
        int pkindrefcode_service, /*int*/
        int rqorderitemcode, /*int*/
        int onlyNotPayItem,
        int ShowPayOnlyCurPeriod
        ) throws PersistenceException

// (
//         java.lang.String,
//         int,
//         int,
//         java.lang.String,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int,
//         int) in com.ksoe.energynet.reports.common.netScriptlet cannot be applied to (java.lang.String,java.lang.Integer,java.lang.Integer,java.lang.String,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer,java.lang.Integer)
 {
        JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
    try {


        System.out.println(" start getChartPaymentDATA ");
        ArrayList rows  = new ArrayList();
        String query = "";

        String test_ri_code = ""; // "1000000367";

    query =


            " select ddscode, \n" +
                    " project::text as project, \n" +
                    " numberdoc, \n" +
                    " orderdate, \n" +
                    " mname, \n" +
                    " meas, \n" +
                    " contract, \n" +
                    " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
                    " orgname, \n" +
                    " countfact, \n" +
                    " countgen, \n" +
                    " p, \n" +
                    " pricewithnds, \n" +
                    " summa, \n" +
                    " oplatata_date, \n" +   // 15
                    " to_char(postavka_date,'dd.mm.yyyy') as postavka_date, \n" +
                    " estimateitemcode, \n" +
                    " estimateitemcode_for_bill, \n" +
                    " oicode, \n" +
                    " budgetrefname, \n" +
                    " deliverytime, \n" +
                    " statussymbol, \n" +
                    " planneddatepays, \n" + // 23
                    " pr_period, \n" +       // 24
                    " paymenttypename, \n" + // 25
                    " paymentvalue, \n" +    // 26
                    " case when first_day(planneddatepays) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "   then  plansumpay else 0 end as plansumpay \n" +
                    "  \n" +
                    " from ( \n" +
                    " select ddscode ,  'тмц' as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                    " /* расчет кол-во плановое \n" +
                    "  - если кол-во план больше кол-ва факт тогда разницу между план и фактом выводим \n" +
                    "  - если  разница нормативное кол-во минус оплачено в пред периоде равно или меньше 0 \n" +
                    "      но сумма по счету больше чем оплачено  (т.е не все оплатили ) тогда нормативное кол -во \n" +
                    "  */ \n" +
                    "  (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact , \n" +
                    "  countgen , p::numeric(15,3) , \n" +
                    "  pricewithnds::numeric(15,3) as pricewithnds, \n" +
                    " /* расчет сумма плановая \n" +
                    "  - если кол-во план больше кол-ва факт тогда разницу между план и фактом умноженное на цену выводим \n" +
                    "  - если  разница нормативное кол-во минус оплачено в пред периоде равно или меньше 0 \n" +
                    "      но сумма по счету больше чем оплачено  (т.е не все оплатили ) тогда нормативное кол -во \n" +
                    "  */ \n" +
                    "    (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa , \n" +
                    "  oplatata_date , postavka_date  , \n" +
                    "  estimateitemcode , \n" +
                    "  case when estimateitemcode_for_bill = '0' then estimateitemcode else estimateitemcode_for_bill end  as estimateitemcode_for_bill, \n" +
                    "  oicode , \n" +
                    "  budgetrefname , \n" +
                    "  deliverytime  , \n" +
                    "  statussymbol , \n" +
                    "             --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             oplatata_date \n" +
                    "                             else \n" +
                    "                               case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                  oplatata_date \n" +
                    "                               else \n" +
                    "                                  first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                               end \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (case when  first_day(postavka_date+7) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                   postavka_date+7 \n" +
                    "                                 else \n" +
                    "                                 first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                            end) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end  *  \n" +
                    " oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                           oplatata_date \n" +
                    "                                           else \n" +
                    "                                           first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                                      end \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      postavka_date+7 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              oplatata_date \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as planneddatepays , \n" +
                    "             -----============================ \n" +
                    "  -1 as pr_period , \n" +
                    "  --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              'Кінцевий розрахунок' \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            ('Кінцевий розрахунок') \n" +
                    "                           else ( \n" +
                    "                           (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymenttypename , \n" +
                    "             -----============================ \n" +
                    "   --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              100 \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (100) \n" +
                    "                           else ( \n" +
                    "                           (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymentvalue , \n" +
                    "             -----============================ \n" +
                    "   /* SUPP-9591 для заявок предыдущего периода если есть счет то сумму с ндс по заявке тянем уже относительно счета а не заявки иначе сумма  \n" +
                    " берется со строки заявки */ \n" +
                    "   --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             0 \n" +
                    "                             else \n" +
                    "                                ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end from rqorderitem oii where oii.code  \n" +
                    " = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                           ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end from rqorderitem oii where oii.code =  \n" +
                    " oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end  *  \n" +
                    " oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >  p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      ((select case when SUMMMA_S_NDS_IN_BILL = 0 then oii.sumgen else SUMMMA_S_NDS_IN_BILL end *oii.paymentvalue/100 from  \n" +
                    " rqorderitem oii where oii.code = oicode ) \n" +
                    "                                       - \n" +
                    "                                       p_sum)::numeric(15,2) \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      0 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              0 \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as plansumpay \n" +
                    "             -----============================ \n" +
                    " from ( \n" +
                    " /* все заявки */ \n" +
                    " select ddscode ,  'тмц' as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                    "  sum(countgen) as countgen , sum(p)::numeric(15,3) as p  ,  pricewithnds::numeric(15,3) as pricewithnds, \n" +
                    "  oplatata_date , postavka_date , \n" +
                    "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
                    "    group_concat( distinct estimateitemcode_for_bill::varchar ,',') as estimateitemcode_for_bill , \n" +
                    "  oicode , \n" +
                    "  budgetrefname , \n" +
                    "  deliverytime , \n" +
                    "  statussymbol , \n" +
                    "  planneddatepays , \n" +
                    "  sum(payincurperiod) as payincurperiod \n" +
                    "       /*сумма оплаты в пред периоде */ \n" +
                    " -- (select * from get_pay_02prior_period2(oicode , group_concat( estimateitemcode::varchar ,',') , /*startDate*/ '01.12.2013')) as paysumoldperiod , \n" +
                    " -- \n" +
                    " , coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/ \n" +
                    "     / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "          where oi.orderitemrefcode = oicode \n" +
                    "          and oi.billitemrefcode = bi.code ),0) \n" +
                    "        * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "          where oi.orderitemrefcode = oicode \n" +
                    "          and oi.billitemrefcode = bi.code ),0) \n" +
                    "          From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen <= to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                       and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
                    "                       and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
                    "          ),0) as paysumoldperiod \n" +
                    "  -- sum(billsum) as billsum \n" +
                    " -- , (select * from get_bill_sum_by_estimate(group_concat( estimateitemcode::text ,',') )) as billsum \n" +
                    " -------- \n" +
                    " , coalesce((select sum((bi.sumgen / bi.countfact) * bi2oi.countfact) ::numeric(15, 2) \n" +
                    "  from rqbillitem bi, \n" +
                    "       rqbillitem2orderitem bi2oi \n" +
                    "  where bi.code = bi2oi.billitemrefcode and \n" +
                    "        bi2oi.orderitemrefcode = oicode ),0) as billsum \n" +
                    " -------- \n" +
                    "  , typepayrefcode \n" +
                    "  , coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/ \n" +
                    "     / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "          where oi.orderitemrefcode = oicode \n" +
                    "          and oi.billitemrefcode = bi.code ),0) \n" +
                    "        * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "          where oi.orderitemrefcode = oicode \n" +
                    "          and oi.billitemrefcode = bi.code ),0) \n" +
                    "          From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen <= case when " + onlyNotPayItem + " = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                   then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                                                   else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "                       and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
                    "                       and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
                    "          ),0) as p_sum \n" +
                    "   , ( select coalesce(sum(summma_s_nds_in_bill),0) from ( \n" +
                    " select b.countfact *  (bi.sumgen/bi.countfact) as summma_s_nds_in_bill \n" +
                    " from rqbillitem2orderitem b , rqbillitem bi \n" +
                    " where b.orderitemrefcode = oicode \n" +
                    " and b.billitemrefcode = bi.code ) as aa ) as SUMMMA_S_NDS_IN_BILL \n" +
                    "  , paymentvalue \n" +
                    "  From ( \n" +
                    " Select r.numberdoc , \n" +
                    "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
                    "        ri.materialnamegen , \n" +
                    "        ri2e.countgen , \n" +
                    "        ri2e.estimateitemcode , \n" +
                    "        /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
                    "         coalesce(( Select sum(rqbillitem2enestimattm.countgen)   From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                       rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "          ),0) as p \n" +
                    "          /*естимейты из заявок пред периода отнимем естимейты по которым были оплаты \n" +
                    "           останутся только естимейты по которым небыло оплаты */ \n" +
                    "        , coalesce((select ri2e.estimateitemcode \n" +
                    "         EXCEPT \n" +
                    "         Select rqbillitem2enestimattm.estimateitemcode     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "         ),0) as  estimateitemcode_for_bill \n" +
                    "          , \n" +
                    "          (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode \n" +
                    "           ) as mname \n" +
                    "          , \n" +
                    "          (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode  \n" +
                    " and mm.measurementcode = ms.code \n" +
                    "           ) as meas \n" +
                    "           , \n" +
                    "           rdds.txtcode as ddscode, \n" +
                    "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
                    "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
                    "           ri.pricewithnds , \n" +
                    "           ri.plannedDatePays as oplatata_date, \n" +
                    "           ri.plannedDateDelivery as postavka_date , \n" +
                    "           ri.code as oicode , \n" +
                    "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
                    "            COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
                    "           case when r.statusrefcode = 2 then 'З' \n" +
                    "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol \n" +
                    "           , ri.planneddatepays \n" +
                    "           ,  /*признак = или больше 1 если оплаты были в периоде формирования отчета т.е с первого числа месяца по указаное число при  \n" +
                    " формировании отчета */ \n" +
                    "         coalesce(( Select  count(rqpaydoc.code)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "          ),0) as payincurperiod \n" +
                    " , ri.typepayrefcode \n" +
                    " , ri.paymentvalue \n" +
                    "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
                    "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
                    "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
                    "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i , tkmaterials sm \n" +
                    " Where r.code = ri.orderrefcode \n" +
                    "   and ri.code = ri2e.orderitemcode \n" +
                    "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
                    "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
                    "   and i.code = ri2e.estimateitemcode \n" +
                    "   and i.planrefcode = p.code \n" +
                    "   and p.budgetrefcode = db.code \n" +
                    "   and p.kindcode = 2 \n" +
                    "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
                    "         /*или план завершений но была оплата в текущ периоде */ \n" +
                    "        or ( p.statuscode = 8 \n" +
                    "            and \n" +
                    "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and enplanwork.code = enestimateitem.planrefcode \n" +
                    "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
                    "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
                    "                       and enplanwork.statuscode = 8 \n" +
                    "                     -- 18.02.2014 строка ниже оплаты нада учитывать за весь месяц что  бы  показывать строку в ТД \n" +
                   // "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and last_day( to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                       limit 1 \n" +
                    "             ) = 8 ) \n" +
                    " /*add yura 17.02.2014 или есть строка счета по строке заявки которая неоплачена еще */ \n" +
                    " or ( \n" +
                    "   (Select  coalesce(bi.staterefcode,0) from rqbillitem2orderitem b2o, rqbillitem bi  \n" +
                    "      where b2o.orderitemrefcode = ri.code \n" +
                    "      and b2o.billitemrefcode = bi.code  \n" +
                    "      order by bi.staterefcode \n" +
                    "      limit 1 \n" +
                    "     ) = 0 \n" +
                    "    ) \n" +
                    "        ) \n" +
                    "   and r.orderperiod < first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "   and r.orderperiod >= '01.01.2013' /* SUPP-9591 не берем старые заявки так как в связке rqbillitem2orderitem нету количества*/ \n" +
                    "   /*ддс*/ \n" +
                    "   and ( rdds.code = " + ddscode + " or " + ddscode + "  = 0 ) \n" +
                    "   /* бюджетодержатель */ \n" +
                    "   and (db.code = " + budgCode + " or " + budgCode + " = 0) \n" +
                    "   /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */ \n" +
                    "   and  (r.kindrefcode = " + pkindrefcode + " or ( " + pkindrefcode + " = 0 and r.kindrefcode in (4,5) )   ) \n" +
                    "   and i.materialrefcode = sm.code \n" +
                    "   /* номер договора */ \n" +
                    "  and (ri.findocid = " + contractNumber + " or " + contractNumber + " = 0) \n" +
                    "   /*постачальник */ \n" +
                    "   and (rqorg.id = " + porgcode + "  or  " + porgcode + " = 0 ) \n" +
                    "   /*статус заЯвки*/ \n" +
                    "   and  (r.statusrefcode = " + orderstatus + " or ( " + orderstatus + "  = 0 and r.statusrefcode in (2,3) )   ) \n" +
                    "       /* группа материалов */ \n" +
                    "   /*  and sm.code in \n" +
                    "   (select tm.code from tkmaterials tm \n" +
                    "     where {strGroupmaterials})*/ \n" +
                    "      /* группа материалов */ \n" +
                    " and " +  strGroupmaterials + " \n" +
                    "                  /* отсекаем планы 2010 2011 */ \n" +
                    "   and p.code not in (select code  from aaa_plan_code ) \n" +
                    "  -- and ri.code in (500199792 ,  500199793) \n" +
                    "  ) s1 \n" +
                    "    Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
                    "           , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname , deliverytime , statussymbol , planneddatepays \n" +
                    "           , typepayrefcode , paymentvalue \n" +
                    "  ) s2 \n" +
                    "    Where  (  /*показать строки заявки которые не оплачены в предыдущ периоде */ \n" +
                    "             -- если план кол-во больше оплоченых и сумма счета больше оплаты допустим на 2 грн \n" +
                    "             ((s2.countgen > s2.p) and ((billsum - paysumoldperiod) > 2 )  ) \n" +
                    "             -- если кол-во план <=0 и разница оплаты и счета больше 2 ) \n" +
                    "             or ( \n" +
                    "                  ( (s2.countgen - s2.p ) <= 0 ) \n" +
                    "                  and \n" +
                    "                   ( (billsum - paysumoldperiod) > 2  ) \n" +
                    "                ) \n" +
                    "             -- или были оплаты в текущ периоде \n" +
                    "             or \n" +
                    "              payincurperiod > 0 \n" +
                    "             -- если небыло счета \n" +
                    "             or \n" +
                    "              COALESCE(billsum,0) = 0 \n" +
                    "             -- если есть счет  но нет оплат ни в текущ периоде ни в прошлых \n" +
                    "             or \n" +
                    "              ( COALESCE(billsum,0) > 0 and  COALESCE(paysumoldperiod,0) = 0 and s2.p = 0 ) \n" +
                    " or /* add yura 18.02.2014 если неоплаченый счет (не попадали копейки недоплаченые по счету )*/ \n" +
                    " ( COALESCE(billsum,0) >  COALESCE(paysumoldperiod,0) ) \n" +
                    "             -- временно для тд октября - не попадал  бензин 80 и дт \n" +
                    "             or oicode in (500139368 , 500139371) \n" +
                    "           ) \n" +
                    "    /* уберем строки с нулевыми плановыми суммами*/ \n" +
                    "    and case when s2.countgen > s2.p then  (countgen - p) * pricewithnds ::numeric(15,3) \n" +
                    "       when ( ( (s2.countgen - s2.p ) <= 0 )  and (billsum > paysumoldperiod ) )  then billsum-paysumoldperiod::numeric(15,3) \n" +
                    "       /*NET-4066 Если была оплата в текущем периоде, то необходимо показывать эту строку завки*/ \n" +
                    "       when payincurperiod > 0 then payincurperiod \n" +
                    "       end \n" +
                    "       is not null \n" +
                    "  ) as sel \n" +
                    " UNION ALL \n" +
                    " /*КЛЕИМ ЗАЯВКИ ТЕКУЩЕГО ПЕРИОДА */ \n" +
                    " /* все заявки */ \n" +
                    " select ddscode , \n" +
                    " project, \n" +
                    " numberdoc  , \n" +
                    " orderdate , \n" +
                    " mname , \n" +
                    " meas , \n" +
                    " contract , \n" +
                    " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
                    " orgname , \n" +
                    " countfact , \n" +
                    " countgen , \n" +
                    " p , \n" +
                    " pricewithnds , \n" +
                    " summa  , \n" +
                    " oplatata_date , \n" +
                    " to_char(postavka_date,'dd.mm.yyyy') as postavka_date, \n" +
                    " estimateitemcode , \n" +
                    " estimateitemcode_for_bill , \n" +
                    " oicode , \n" +
                    " budgetrefname , \n" +
                    " deliverytime , \n" +
                    " statussymbol  , \n" +
                    " --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             oplatata_date \n" +
                    "                             else \n" +
                    "                               case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                  oplatata_date \n" +
                    "                               else \n" +
                    "                                  first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                               end \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (case when  first_day(postavka_date+7) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                   postavka_date+7 \n" +
                    "                                 else \n" +
                    "                                 first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                            end) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                           oplatata_date \n" +
                    "                                           else \n" +
                    "                                           first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                                      end \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      postavka_date+7 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              oplatata_date \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as planneddatepays , \n" +
                    " -----============================ \n" +
                    "  1 as pr_period , \n" +
                    " --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              'Кінцевий розрахунок' \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            ('Кінцевий розрахунок') \n" +
                    "                           else ( \n" +
                    "                           (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymenttypename , \n" +
                    "             -----============================ \n" +
                    "   --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              100 \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (100) \n" +
                    "                           else ( \n" +
                    "                           (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymentvalue , \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             0 \n" +
                    "                             else \n" +
                    "                                ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                           ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      ((select oii.sumgen  *oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                       - \n" +
                    "                                       p_sum)::numeric(15,2) \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      0 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              0 \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as plansumpay  \n" +
                    "             -----============================ \n" +
                    "  \n" +
                    " From \n" +
                    " ( \n" +
                    "     select ddscode ,  'тмц'::text as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                    "        (select oii.countfact from rqorderitem oii where oii.code = oicode ) as countfact , \n" +
                    "       0 as countgen , 0 as p , \n" +
                    "      pricewithnds , \n" +
                    "      (select oii.sumgen from rqorderitem oii where oii.code = oicode ) as summa  , \n" +
                    "      oplatata_date , postavka_date , \n" +
                    "       group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
                    "       group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
                    "      oicode , \n" +
                    "      budgetrefname , \n" +
                    "      deliverytime , \n" +
                    "      statussymbol  , \n" +
                    "      1 as pr_period \n" +
                    "         , coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/ \n" +
                    "         / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "            * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "              From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                         where rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                           and rqpaydoc.dategen <= case when " + onlyNotPayItem + " = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                       then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                                                       else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "                           and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
                    "                           and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
                    "              ),0) as p_sum   , \n" +
                    "           typepayrefcode , \n" +
                    "           paymentvalue \n" +
                    "      from ( \n" +
                    "     Select r.numberdoc , \n" +
                    "            to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
                    "            ri.materialnamegen , \n" +
                    "            ri2e.countgen , \n" +
                    "            ri2e.estimateitemcode , \n" +
                    "              (select m.name  from tkmaterials m , enestimateitem e where m.code = e.materialrefcode and e.code = ri2e.estimateitemcode \n" +
                    "               ) as mname \n" +
                    "              , \n" +
                    "              (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code = ri2e.estimateitemcode  \n" +
                    " and mm.measurementcode = ms.code \n" +
                    "               ) as meas \n" +
                    "               , \n" +
                    "               rdds.txtcode as ddscode, \n" +
                    "               ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
                    "               (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
                    "               ri.pricewithnds , \n" +
                    "               ri.plannedDatePays as oplatata_date, \n" +
                    "               ri.plannedDateDelivery as postavka_date , \n" +
                    "               ri.code as oicode , \n" +
                    "               case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
                    "               COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
                    "               case when r.statusrefcode = 2 then 'З' \n" +
                    "                    when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
                    "               ri.planneddatepays \n" +
                    "               , ri.typepayrefcode \n" +
                    "               , ri.paymentvalue \n" +
                    "       From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
                    "                                        left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
                    "                                        left join rqorg on (ri.orgcode = rqorg.code ) \n" +
                    "           , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
                    "     Where r.code = ri.orderrefcode \n" +
                    "       and ri.code = ri2e.orderitemcode \n" +
                    "       and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
                    "       and ri2e.statusrefcode = 1 /*действительный*/ \n" +
                    "       and i.code = ri2e.estimateitemcode \n" +
                    "       and i.planrefcode = p.code \n" +
                    "       and p.kindcode = 2 \n" +
                    "       and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
                    "             /*или план завершений но была оплата в текущ периоде */ \n" +
                    "            or ( p.statuscode = 8 \n" +
                    "                and \n" +
                    "                (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                      rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
                    "                         where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                           and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                           and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                           and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                           and enplanwork.code = enestimateitem.planrefcode \n" +
                    "                           and enestimateitem.code = ri2e.estimateitemcode \n" +
                    "                           and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
                    "                           and enplanwork.statuscode = 8 \n" +
                    "                           and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                 limit 1 \n" +
                    "                  ) = 8 ) \n" +
                    "            ) \n" +
                    "       and p.budgetrefcode = db.code \n" +
                    "       and ( r.orderperiod between first_day( to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                               and to_date('" + startDate +"','dd.mm.yyyy')   and " + prizn_period + "  = 1 ) \n" +
                    "       /*ддс*/ \n" +
                    "       and ( rdds.code = " + ddscode + "  or " + ddscode + "  = 0 ) \n" +
                    "       /* бюджетодержатель */ \n" +
                    "       and (db.code = " + budgCode + " or " + budgCode + " = 0) \n" +
                    "       /* ХОЕ планова = 4 , ХОЕ позапланова = 5 */ \n" +
                    "       and  (r.kindrefcode = " + pkindrefcode + " or ( " + pkindrefcode + " = 0 and r.kindrefcode in (4,5) )   ) \n" +
                    "       and i.materialrefcode = sm.code \n" +
                    "       /* номер договора */ \n" +
                    "       and (ri.findocid = " + contractNumber + " or " + contractNumber + " = 0) \n" +
                    "       /*постачальник */ \n" +
                    "       and (rqorg.id = " + porgcode + " or  " + porgcode + " = 0 ) \n" +
                    "       /*статус заЯвки*/ \n" +
                    "       and  (r.statusrefcode = " + orderstatus + " or ( " + orderstatus + " = 0 and r.statusrefcode in (2,3) )   ) \n" +
                    "         /* группа материалов */ \n" +
                    "       /*  and sm.code in \n" +
                    "       (select tm.code from tkmaterials tm \n" +
                    "         where {strGroupmaterials})*/ \n" +
                    "          /* группа материалов */ \n" +
                    "       and " +  strGroupmaterials + " \n" +
                    "       /*отображать внеплановые заявки текущего месяца или нет 0 - нет 1 да*/ \n" +
                    "       and (( r.kindrefcode = 4  and " + showcurrentofforder + " = 0 ) or (r.kindrefcode in (4,5)  and " + showcurrentofforder + " = 1  )) \n" +
                    "                     /* отсекаем планы 2010 2011 */ \n" +
                    "       and p.code not in (select code  from aaa_plan_code ) \n" +
                    "       ) s1 \n" +
                    "     Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
                    "     , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime  , \n" +
                    "     statussymbol , planneddatepays , typepayrefcode  , paymentvalue \n" +
                    " ) s2 \n";

                    //Разобьем страшный запрос на 2 (а то происходит СтекОверфлоу у некоторых при билде ;))
                    String query1 =

                    " UNION ALL \n" +
                    " /*КЛЕИМ заявки по услугам ТЕКУЩЕГО ПЕРИОДА */ \n" +
                    " select ddscode , \n" +
                    " project, \n" +
                    " numberdoc  , \n" +
                    " orderdate , \n" +
                    " mname , \n" +
                    " meas , \n" +
                    " contract , \n" +
                    " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
                    " orgname , \n" +
                    " countfact , \n" +
                    " countgen , \n" +
                    " p , \n" +
                    " pricewithnds , \n" +
                    " summa , \n" +
                    " oplatata_date , \n" +
                    " to_char(postavka_date,'dd.mm.yyyy') as postavka_date ,  \n" +
                    " estimateitemcode , \n" +
                    " estimateitemcode_for_bill , \n" +
                    " oicode , \n" +
                    " budgetrefname , \n" +
                    " deliverytime , \n" +
                    " statussymbol  , \n" +
                    " --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             oplatata_date \n" +
                    "                             else \n" +
                    "                               case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                  oplatata_date \n" +
                    "                               else \n" +
                    "                                  first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                               end \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (case when  first_day(postavka_date+7) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                   postavka_date+7 \n" +
                    "                                 else \n" +
                    "                                 first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                            end) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                           oplatata_date \n" +
                    "                                           else \n" +
                    "                                           first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                                      end \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      postavka_date+7 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              oplatata_date \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as planneddatepays , \n" +
                    " -----============================ \n" +
                    "   1 as pr_period , \n" +
                    "  --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              'Кінцевий розрахунок' \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            ('Кінцевий розрахунок') \n" +
                    "                           else ( \n" +
                    "                           (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymenttypename , \n" +
                    "             -----============================ \n" +
                    "   --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              100 \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (100) \n" +
                    "                           else ( \n" +
                    "                           (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymentvalue , \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             0 \n" +
                    "                             else \n" +
                    "                                ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                           ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      ((select oii.sumgen  *oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                       - \n" +
                    "                                       p_sum)::numeric(15,2) \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      0 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              0 \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as plansumpay  \n" +
                    "             -----============================ \n" +
                    " From ( \n" +
                    "       select ddscode ,  'послуга'::text as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                    "         sum(countgen) as countfact , \n" +
                    "         0 as countgen ,  sum(p) as p , \n" +
                    "         pricewithnds , sum(countgen)  * pricewithnds as summa , \n" +
                    "         oplatata_date , postavka_date , \n" +
                    "         group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
                    "         group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
                    "         oicode , \n" +
                    "         budgetrefname , \n" +
                    "         deliverytime , \n" +
                    "         statussymbol  , \n" +
                    "         1 as pr_period , \n" +
                    "         typepayrefcode, \n" +
                    "         paymentvalue , \n" +
                    "           coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/ \n" +
                    "         / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "            * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "              From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                         where rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                           and rqpaydoc.dategen <= case when " + onlyNotPayItem + " = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                       then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                                                       else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "                           and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
                    "                           and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
                    "              ),0) as p_sum \n" +
                    "         From ( \n" +
                    "       Select r.numberdoc , \n" +
                    "              to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
                    "              ri.materialnamegen , \n" +
                    "              ri2e.countgen , \n" +
                    "              ri2e.estimateitemcode , \n" +
                    "             /*название услуги вытягиваем такое как пропишут в коментах на плане */ \n" +
                    "             (select '('|| tii.techkartnumber || ') ' || coalesce(pii.commentgen,'Невизначено') \n" +
                    "                                          from enestimateitem iii , enplanworkitem pii , tktechcard tii \n" +
                    "                                         Where iii.planitemrefcode = pii.code \n" +
                    "                                           and iii.code = i.code \n" +
                    "                                           and pii.kartarefcode = tii.code \n" +
                    "                                           limit 1   ) as mname \n" +
                    "                , \n" +
                    "                (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms where mm.code = ee.materialrefcode and ee.code =  \n" +
                    " ri2e.estimateitemcode and mm.measurementcode = ms.code \n" +
                    "                 ) as meas \n" +
                    "                 , \n" +
                    "                  /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
                    "               coalesce(( Select sum(rqpaydocitem2billitem.summagen)     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                           where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                             and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                             and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                             and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                             and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                             and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                             and rqpaydoc.dategen <= case when " + onlyNotPayItem + "  = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                         then to_date('" + startDate +"' ,'dd.mm.yyyy') \n" +
                    "                                                         else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "                ),0) as p , \n" +
                    "                 rdds.txtcode as ddscode, \n" +
                    "                 ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
                    "                 (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
                    "                 ri.pricewithnds , \n" +
                    "                 ri.plannedDatePays as oplatata_date, \n" +
                    "                 ri.plannedDateDelivery as postavka_date , \n" +
                    "                 ri.code as oicode , \n" +
                    "                 case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
                    "                 COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
                    "                 case when r.statusrefcode = 2 then 'З' \n" +
                    "                      when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
                    "                 ri.planneddatepays \n" +
                    "                ,ri.typepayrefcode \n" +
                    "                ,ri.paymentvalue \n" +
                    "         From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
                    "                                          left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
                    "                                          left join rqorg on (ri.orgcode = rqorg.code ) \n" +
                    "             , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
                    "       Where r.code = ri.orderrefcode \n" +
                    "         and ri.code = ri2e.orderitemcode \n" +
                    "         and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
                    "         and ri2e.statusrefcode = 1 /*действительный*/ \n" +
                    "         and i.code = ri2e.estimateitemcode \n" +
                    "         and i.planrefcode = p.code \n" +
                    "         and p.kindcode = 2 \n" +
                    "         and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
                    "               /*или план завершений но была оплата в текущ периоде */ \n" +
                    "              or ( p.statuscode = 8 \n" +
                    "                  and \n" +
                    "                  (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                        rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
                    "                           where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                             and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                             and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                             and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                             and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                             and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                             and enplanwork.code = enestimateitem.planrefcode \n" +
                    "                             and enestimateitem.code = ri2e.estimateitemcode \n" +
                    "                             and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
                    "                             and enplanwork.statuscode = 8 \n" +
                    "                             and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                          limit 1 \n" +
                    "                          ) = 8 \n" +
                    "                          /*только если передали признак - показывать оплаты текущего периода*/ \n" +
                    "                   and " + ShowPayOnlyCurPeriod + " = 1 \n" +
                    "                   ) \n" +
                    "              ) \n" +
                    "         and p.budgetrefcode = db.code \n" +
                    "         and i.materialrefcode = sm.code \n" +
                    "         and ( r.orderperiod between  add_months(first_day( to_date('" + startDate +"','dd.mm.yyyy') ),-1) \n" +
                    "                                 and last_day(add_months(to_date('" + startDate +"','dd.mm.yyyy'),-1)::date)   and " + prizn_period + "  = 1 \n" +
                    "                                  or r.orderperiod between first_day( to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                                 and last_day(to_date('" + startDate +"','dd.mm.yyyy')::date)   and " + prizn_period + "  = 1 \n" +
                    "                             ) \n" +
                    "         /*ддс*/ \n" +
                    "         and ( rdds.code = " + ddscode + " or " + ddscode + "  = 0 ) \n" +
                    "         /* бюджетодержатель */ \n" +
                    "         and (db.code = " + budgCode + " or " + budgCode + " = 0) \n" +
                    "         /* ХОЕ планова услуги = 7 , ХОЕ позапланова услуги = 8 */ \n" +
                    "         and  (r.kindrefcode = " + pkindrefcode_service + " or ( " + pkindrefcode_service + " = 0 and r.kindrefcode in (7,8) )   ) \n" +
                    "         /* номер договора   */ \n" +
                    "        and (ri.findocid = " + contractNumber + " or " + contractNumber + " = 0) \n" +
                    "         /*постачальник  */ \n" +
                    "       and (rqorg.id = " + porgcode + " or  " + porgcode + " = 0 ) \n" +
                    "         /*статус заЯвки*/ \n" +
                    "        and  (r.statusrefcode = " + orderstatus + " or ( " + orderstatus + " = 0 and r.statusrefcode in (2,3) )   ) \n" +
                    "         /*отображать внеплановые заявки по услугам текущего месяца или нет 0 - нет 1 да*/ \n" +
                    "        -- and (( r.kindrefcode = 7   and {showcurrentofforder} = 0 ) or (r.kindrefcode in (7,8)  and {showcurrentofforder} = 1  )) \n" +
                    "                        /* отсекаем планы 2010 2011 */ \n" +
                    "         and p.code not in (select code  from aaa_plan_code ) \n" +
                    "         ) s_usl \n" +
                    "         Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , pricewithnds , oplatata_date , \n" +
                    "         postavka_date  , oicode , budgetrefname  , deliverytime  , statussymbol , planneddatepays , s_usl.typepayrefcode , paymentvalue \n" +
                    "   ) s2_usl \n" +
                    "   Where summa > p_sum --- показываем только те строки которые не оплотились в предыдущих периодах \n" +
                    " UNION ALL \n" +
                    " /*КЛЕИМ неоплаченые заявки по услугам предыдущих ПЕРИОДов */ \n" +
                    " select ddscode , \n" +
                    " project, \n" +
                    " numberdoc  , \n" +
                    " orderdate , \n" +
                    " mname , \n" +
                    " meas , \n" +
                    " contract , \n" +
                    " (select o.okpo from rqorderitem oi , rqorg o where oi.orgcode = o.code and oi.code = oicode ) as okpo , \n" +
                    " orgname , \n" +
                    "   countfact , \n" +
                    "   countgen ,  p , \n" +
                    "   pricewithnds , \n" +
                    "   countgen  * pricewithnds as summa , \n" +
                    "   oplatata_date , \n" +
                    "   to_char(postavka_date,'dd.mm.yyyy') as postavka_date , \n" +
                    "   estimateitemcode , \n" +
                    "   estimateitemcode_for_bill , \n" +
                    "   oicode , \n" +
                    "   budgetrefname , \n" +
                    "   deliverytime , \n" +
                    "   statussymbol  , \n" +
                    " --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             oplatata_date \n" +
                    "                             else \n" +
                    "                               case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                  oplatata_date \n" +
                    "                               else \n" +
                    "                                  first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                               end \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (case when  first_day(postavka_date+7) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                   postavka_date+7 \n" +
                    "                                 else \n" +
                    "                                 first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                            end) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      case when first_day(oplatata_date) = first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                                           oplatata_date \n" +
                    "                                           else \n" +
                    "                                           first_day(to_date('" + startDate +"','dd.mm.yyyy') ) \n" +
                    "                                      end \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      postavka_date+7 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              oplatata_date \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as planneddatepays , \n" +
                    " -----============================ \n" +
                    "   pr_period , \n" +
                    " --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              'Кінцевий розрахунок' \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            ('Кінцевий розрахунок') \n" +
                    "                           else ( \n" +
                    "                           (select oitp.name  from rqorderitem oi , rqorderitemtypepay oitp where oi.typepayrefcode = oitp.code and  oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymenttypename , \n" +
                    "             -----============================ \n" +
                    "   --========================== \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                             else \n" +
                    "                              100 \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (100) \n" +
                    "                           else ( \n" +
                    "                           (select oi.paymentvalue  from rqorderitem oi  where oi.code = oicode) \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as paymentvalue , \n" +
                    "              case when (    typepayrefcode = 2 \n" +
                    "                         or (typepayrefcode = 1 and paymentvalue = 100 ) \n" +
                    "                         or  typepayrefcode is null ) then ( \n" +
                    "                             case when first_day(oplatata_date) > first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                             0 \n" +
                    "                             else \n" +
                    "                                ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                             end \n" +
                    "                         ) \n" +
                    "                    when (typepayrefcode = 1 and paymentvalue <> 100 ) then \n" +
                    "                    ( case when first_day(postavka_date+7) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                           ((select oii.sumgen from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                  - \n" +
                    "                                  p_sum)::numeric(15,2) \n" +
                    "                           else ( \n" +
                    "                           case when  first_day(oplatata_date) <= first_day(to_date('" + startDate +"','dd.mm.yyyy') ) then \n" +
                    "                            (  case when coalesce((select oii.sumgen * oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode )  )::numeric(15,2)  >   \n" +
                    " p_sum::numeric(15,2) then  -- не оплотили предоплату \n" +
                    "                                      ((select oii.sumgen  *oii.paymentvalue/100 from rqorderitem oii where oii.code = oicode ) \n" +
                    "                                       - \n" +
                    "                                       p_sum)::numeric(15,2) \n" +
                    "                                   else -- предоплату оплотили , окончательную дату расчета примем как дата поставки + 7 дней \n" +
                    "                                      0 \n" +
                    "                              end \n" +
                    "                            ) else --план дата оплаты больше чем дата формирования ТД \n" +
                    "                            ( \n" +
                    "                              0 \n" +
                    "                            ) \n" +
                    "                           end \n" +
                    "                           ) \n" +
                    "                      end \n" +
                    "                    ) \n" +
                    "              end  as plansumpay \n" +
                    "             -----============================ \n" +
                    "  \n" +
                    " From \n" +
                    " ( \n" +
                    " select ddscode ,  'послуга'::text as project, numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname , \n" +
                    "     sum(countgen) as  countfact , \n" +
                    "    sum(countgen) as countgen ,  sum(p) as p , \n" +
                    "    pricewithnds , sum(countgen)  * pricewithnds as summa , \n" +
                    "   oplatata_date , postavka_date , \n" +
                    "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode , \n" +
                    "    group_concat( estimateitemcode::varchar ,',') as estimateitemcode_for_bill , \n" +
                    "   oicode , \n" +
                    "   budgetrefname , \n" +
                    "   deliverytime , \n" +
                    "   statussymbol  , \n" +
                    "   planneddatepays , \n" +
                    "   1 as pr_period , \n" +
                    "   payCurPeriod , \n" +
                    "   typepayrefcode \n" +
                    "   , ispaid \n" +
                    "   , paymentvalue \n" +
                    "   ,  coalesce(( Select sum(rqpaydocitem.summagen) /*пропорционально вычисляем оплаты строки заявки*/ \n" +
                    "         / coalesce((select sum(bi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "            * coalesce((select sum(oi.countfact) from rqbillitem2orderitem oi , rqbillitem bi \n" +
                    "              where oi.orderitemrefcode = oicode \n" +
                    "              and oi.billitemrefcode = bi.code ),0) \n" +
                    "              From rqpaydocitem2billitem , rqbillitem2orderitem  ,rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                         where rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                           and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                           and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                           and rqpaydoc.dategen <= case when " + onlyNotPayItem + " = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                       then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                                                       else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "                           and rqbillitem2orderitem.orderitemrefcode = oicode \n" +
                    "                           and rqbillitem2orderitem.billitemrefcode = rqbillitem.code \n" +
                    "              ),0) as p_sum \n" +
                    "   From ( \n" +
                    " Select r.numberdoc , \n" +
                    "        to_char(r.orderperiod,'MM.yyyy') as orderdate , \n" +
                    "        ri.materialnamegen , \n" +
                    "        ri2e.countgen , \n" +
                    "        ri2e.estimateitemcode , \n" +
                    "        /*проверить есть ли естимейт в оплатах и дата оплаты что бы была меньше указанной даты в выборке */ \n" +
                    "         coalesce(( Select sum(rqpaydocitem2billitem.summagen)     From rqpaydocitem2billitem , rqbillitem2enestimattm , rqbillitem  , rqpaydocitem , rqpaydoc \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and rqpaydoc.dategen <= case when " + onlyNotPayItem + " = 1 /*если нада показать то что еще осталось платить то нужно учесть все  \n" +
                    " оплаты до даты формирования тд*/ \n" +
                    "                                                   then to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "                                                   else first_day(to_date('" + startDate +"','dd.mm.yyyy') ) end \n" +
                    "          ),0) as p  , \n" +
                    "       /*название услуги вытягиваем такое как пропишут в коментах на плане */ \n" +
                    "       (select '('|| tii.techkartnumber || ') ' || coalesce(pii.commentgen,'Невизначено') \n" +
                    "                                    from enestimateitem iii , enplanworkitem pii , tktechcard tii \n" +
                    "                                   Where iii.planitemrefcode = pii.code \n" +
                    "                                     and iii.code = i.code \n" +
                    "                                     and pii.kartarefcode = tii.code \n" +
                    "                                     limit 1   ) as mname \n" +
                    "          , \n" +
                    " (select ms.name  from tkmaterials mm , enestimateitem ee , tkmeasurement ms \n" +
                    " where mm.code = ee.materialrefcode \n" +
                    " and ee.code = ri2e.estimateitemcode \n" +
                    " and mm.measurementcode = ms.code \n" +
                    " ) as meas \n" +
                    "           , \n" +
                    "           rdds.txtcode as ddscode, \n" +
                    "           ri.contractnumber || 'от ' || ri.contractdate as contract , \n" +
                    "           (select ro.name from rqorg ro where  ro.code = ri.orgcode  ) as orgname , \n" +
                    "           ri.pricewithnds , \n" +
                    "           ri.plannedDatePays as oplatata_date, \n" +
                    "           ri.plannedDateDelivery as postavka_date , \n" +
                    "           ri.code as oicode , \n" +
                    "           case when dbb.name is null then db.name else dbb.name end as budgetrefname , \n" +
                    "           COALESCE(ri.deliverytime , sm.deliverydate , 0 ) as deliverytime , \n" +
                    "           case when r.statusrefcode = 2 then 'З' \n" +
                    "                when r.statusrefcode = 3 then 'П' else '' end as statussymbol , \n" +
                    "           ri.planneddatepays \n" +
                    " , \n" +
                    " /* Оплаты в периоде выборки */ \n" +
                    " coalesce(( \n" +
                    " Select coalesce(sum(p2b.summagen),0) as summagen \n" +
                    " From rqbillitem2enestimattm be , rqbillitem bii , rqpaydocitem2billitem p2b , rqbill r, rqpaydocitem pdi, rqpaydoc pd , \n" +
                    "      rqorderitem2enestimttm o2e \n" +
                    "  where  be.estimateitemcode  in (ri2e.estimateitemcode) \n" +
                    "    and be.billitemcode = bii.code \n" +
                    "    and p2b.billitemcode = bii.code \n" +
                    "    and r.code = bii.billrefcode \n" +
                    "    and o2e.estimateitemcode = be.estimateitemcode \n" +
                    "    and o2e.orderitemcode = ri.code \n" +
                    "    and be.billitemcode in ( Select distinct bi.code as billitem \n" +
                    "                         From rqorderitem2enestimttm oe,  rqbillitem2enestimattm be, \n" +
                    "                              rqbill b, rqbillitem bi, rqbillitem2orderitem bo \n" +
                    "                         where be.estimateitemcode = oe.estimateitemcode \n" +
                    "                          and be.billitemcode = bo.billitemrefcode \n" +
                    "                          and bo.orderitemrefcode = oe.orderitemcode \n" +
                    "                          and bi.code = bo.billitemrefcode \n" +
                    "                          and b.code = bi.billrefcode \n" +
                    "                          and bo.orderitemrefcode = ri.code \n" +
                    "                          and be.estimateitemcode in  (ri2e.estimateitemcode) \n" +
                    "                         ) \n" +
                    "    and pdi.code = p2b.paydocitemcode \n" +
                    "    and pdi.paydocrefcode = pd.code \n" +
                    "    and pd.dategen >= first_day(to_date('" + startDate +"','dd.mm.yyyy')) \n" +
                    "    ),0) as  payCurPeriod \n" +
                    "    , ri.typepayrefcode \n" +
                    "    , ri.ispaid \n" +
                    "    , ri.paymentvalue \n" +
                    "   From  rqorder r , rqorderitem ri left join endepartment dbb on ( ri.budgetrefcode = dbb.code) \n" +
                    "                                    left join rqddscodes rdds on (ri.ddscodescode = rdds.code) \n" +
                    "                                    left join rqorg on (ri.orgcode = rqorg.code ) \n" +
                    "       , rqorderitem2enestimttm ri2e , endepartment db ,  enplanwork p , enestimateitem i  , tkmaterials sm \n" +
                    " Where r.code = ri.orderrefcode \n" +
                    "   and ri.code = ri2e.orderitemcode \n" +
                    "   and ri.statusrefcode <> 2 /*которые не утратили необходимость*/ \n" +
                    "   and ri2e.statusrefcode = 1 /*действительный*/ \n" +
                    "   and i.code = ri2e.estimateitemcode \n" +
                    "   and i.planrefcode = p.code \n" +
                    "   and p.kindcode = 2 \n" +
                    "   and ( p.statuscode not in (2,6,8) /*планы не устаревшие и не недействительные и не завершенные*/ \n" +
                    "         /*или план завершений но была оплата в текущ периоде */ \n" +
                    "        or ( p.statuscode = 8 \n" +
                    "            and \n" +
                    "            (Select  coalesce(enplanwork.statuscode,0)    From rqpaydocitem2billitem , rqbillitem2enestimattm , \n" +
                    "                                                                  rqbillitem  , rqpaydocitem , rqpaydoc , enplanwork  , enestimateitem \n" +
                    "                      where rqpaydocitem2billitem.billitemcode = rqbillitem2enestimattm.billitemcode \n" +
                    "                       and rqbillitem2enestimattm.estimateitemcode = ri2e.estimateitemcode \n" +
                    "                       and rqbillitem2enestimattm.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem2billitem.billitemcode = rqbillitem.code \n" +
                    "                       and rqpaydocitem.code = rqpaydocitem2billitem.paydocitemcode \n" +
                    "                       and rqpaydoc.code = rqpaydocitem.paydocrefcode \n" +
                    "                       and enplanwork.code = enestimateitem.planrefcode \n" +
                    "                       and enestimateitem.code = ri2e.estimateitemcode \n" +
                    "                       and enestimateitem.code = rqbillitem2enestimattm.estimateitemcode \n" +
                    "                       and enplanwork.statuscode = 8 \n" +
                    "                       and rqpaydoc.dategen between first_day(to_date('" + startDate +"','dd.mm.yyyy') ) and to_date('" + startDate +"','dd.mm.yyyy') \n" +
                    "            limit 1 \n" +
                    "             ) = 8 ) \n" +
                    "        ) \n" +
                    "   and p.budgetrefcode = db.code \n" +
                    "   and i.materialrefcode = sm.code \n" +
                    "   and r.orderperiod < add_months( first_day(to_date('" + startDate +"','dd.mm.yyyy') ),-1) \n" +
                    "   /*ддс*/ \n" +
                    "   and ( rdds.code = " + ddscode + " or " + ddscode + "  = 0 ) \n" +
                    "   /* бюджетодержатель */ \n" +
                    "   and (db.code = " + budgCode + " or " + budgCode + " = 0) \n" +
                    "   /* ХОЕ планова услуги = 7 , ХОЕ позапланова услуги = 8 */ \n" +
                    "   and  (r.kindrefcode = " + pkindrefcode_service + " or ( " + pkindrefcode_service + " = 0 and r.kindrefcode in (7,8) )   ) \n" +
                    "   /* номер договора */ \n" +
                    "   and (ri.findocid = " + contractNumber + " or " + contractNumber + " = 0) \n" +
                    "   /*постачальник */ \n" +
                    "   and (rqorg.id = " + porgcode + " or  " + porgcode + " = 0 ) \n" +
                    "   /* статус заЯвки */ \n" +
                    "   and  (r.statusrefcode = " + orderstatus + " or ( " + orderstatus + " = 0 and r.statusrefcode in (2,3) )   ) \n" +
                    "   /* отображать внеплановые заявки по услугам текущего месяца или нет 0 - нет 1 да */ \n" +
                    "   and (( r.kindrefcode = 7   and " + showcurrentofforder + " = 0 ) or (r.kindrefcode in (7,8)  and " + showcurrentofforder + " = 1  )) \n" +
                    "               /* отсекаем планы 2010 2011 */ \n" +
                    "               and p.code not in (select code  from aaa_plan_code ) \n" +
                    "   ) s_usl \n" +
                    "   Group by  ddscode ,  numberdoc  , orderdate ,  mname ,  meas ,  contract , orgname \n" +
                    "           , pricewithnds , oplatata_date , postavka_date  , oicode , budgetrefname  , deliverytime \n" +
                    "           , statussymbol , planneddatepays , payCurPeriod ,typepayrefcode ,  ispaid , paymentvalue \n" +
                    "   ) s_usl2 \n" +
                    "    Where  ((s_usl2.summa > s_usl2.p) or (payCurPeriod > 0) ) \n" +
                    "    and ( coalesce(ispaid,0) <> 1 or (payCurPeriod > 0) ) \n" +
                    "  Order by project desc , ddscode , budgetrefname , mname \n";


            query = query + query1;
            Statement tempSt;

            tempSt = connection.createStatement();
            System.out.println(query);
            ResultSet rs = tempSt.executeQuery(query);

            BigDecimal PAY_PLAN_SUMMA = new BigDecimal(0);
            Date PAY_PLAN_DATE = null;

            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date startDT = formatter.parse(startDate);

            BigDecimal sd1= new BigDecimal(0),sd2= new BigDecimal(0),sd3= new BigDecimal(0),sd4= new BigDecimal(0),sd5= new BigDecimal(0),sd6= new BigDecimal(0),sd7= new BigDecimal(0),sd8= new BigDecimal(0),sd9= new BigDecimal(0),sd10= new BigDecimal(0),sd11= new BigDecimal(0),sd12= new BigDecimal(0),sd13= new BigDecimal(0),sd14= new BigDecimal(0),sd15= new BigDecimal(0),sd16= new BigDecimal(0),sd17= new BigDecimal(0),sd18= new BigDecimal(0),sd19= new BigDecimal(0),sd20= new BigDecimal(0),sd21= new BigDecimal(0),sd22= new BigDecimal(0),sd23= new BigDecimal(0),sd24= new BigDecimal(0),sd25= new BigDecimal(0),sd26= new BigDecimal(0),sd27= new BigDecimal(0),sd28= new BigDecimal(0),sd29= new BigDecimal(0),sd30= new BigDecimal(0),sd31 = new BigDecimal(0);

            BigDecimal[] dayArr = new BigDecimal[31];

            // сбросим массив в 0
            for(int i = 0; i < 31; i++){
                dayArr[i] = new BigDecimal(0);
            }

            while(rs.next())
            {

                HashMap hashMap = new HashMap();

                sd1= new BigDecimal(0);sd2= new BigDecimal(0);sd3= new BigDecimal(0);sd4= new BigDecimal(0);sd5= new BigDecimal(0);sd6= new BigDecimal(0);sd7= new BigDecimal(0);sd8= new BigDecimal(0);sd9= new BigDecimal(0);sd10= new BigDecimal(0);sd11= new BigDecimal(0);sd12= new BigDecimal(0);sd13= new BigDecimal(0);sd14= new BigDecimal(0);sd15= new BigDecimal(0);sd16= new BigDecimal(0);sd17= new BigDecimal(0);sd18= new BigDecimal(0);sd19= new BigDecimal(0);sd20= new BigDecimal(0);sd21= new BigDecimal(0);sd22= new BigDecimal(0);sd23= new BigDecimal(0);sd24= new BigDecimal(0);sd25= new BigDecimal(0);sd26= new BigDecimal(0);sd27= new BigDecimal(0);sd28= new BigDecimal(0);sd29= new BigDecimal(0);sd30= new BigDecimal(0);sd31 = new BigDecimal(0);
                PAY_PLAN_DATE = rs.getDate(23);

                if (PAY_PLAN_DATE.before(startDT) || PAY_PLAN_DATE.equals(startDT)){
                    sd1 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 1) )) {
                    sd2 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 2) )) {
                    sd3 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 3) )) {
                    sd4 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 4) )) {
                    sd5 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 5) )) {
                    sd6 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 6) )) {
                    sd7 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 7) )) {
                    sd8 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 8) )) {
                    sd9 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 9) )) {
                    sd10 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 10) )) {
                    sd11 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 11) )) {
                    sd12 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 12) )) {
                    sd13 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 13) )) {
                    sd14 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 14) )) {
                    sd15 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 15) )) {
                    sd16 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 16) )) {
                    sd17 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 17) )) {
                    sd18 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 18) )) {
                    sd19 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 19) )) {
                    sd20 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 20) )) {
                    sd21 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 21) )) {
                    sd22 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 22) )) {
                    sd23 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 23) )) {
                    sd24 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 24) )) {
                    sd25 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 25) )) {
                    sd26 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 26) )) {
                    sd27 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 27) )) {
                    sd28 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 28)) ) {
                    sd29 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 29))  ) {
                    sd30 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.equals(Tools.addDays(startDT, 30))  ) {
                    sd31 = rs.getBigDecimal(27);
                }
                if ( PAY_PLAN_DATE.after(Tools.getLastDayOfMonth(startDT))) {
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 28 ) {
                        sd28 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 29 ) {
                        sd29 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 30 ) {
                        sd30 = rs.getBigDecimal(27);
                    }
                    if ( Tools.getLastDayOfMonth(startDT).getDay() == 31 ) {
                        sd31 = rs.getBigDecimal(27);
                    }

                }

                // отнимем оплаты с по строке заявки которые не попали в селект
                // поидее (выбиралось по дате платежа <= dateStart  ) значит оплаты смотрим с 02 числа месяца по последнее число месяца
                BigDecimal sumpayCurPeriod = new BigDecimal(0);
                sumpayCurPeriod =  this.getSumPayByOrderItemCode(rs.getInt(19),Tools.addDays(startDT, 1),Tools.getLastDayOfMonth(startDT));

                sd1 = sd1.subtract(sumpayCurPeriod);
                sd2 = sd2.subtract(sumpayCurPeriod);
                sd3 = sd3.subtract(sumpayCurPeriod);
                sd4 = sd4.subtract(sumpayCurPeriod);
                sd5 = sd5.subtract(sumpayCurPeriod);
                sd6 = sd6.subtract(sumpayCurPeriod);
                sd7 = sd7.subtract(sumpayCurPeriod);
                sd8 = sd8.subtract(sumpayCurPeriod);
                sd9 = sd9.subtract(sumpayCurPeriod);
                sd10 = sd10.subtract(sumpayCurPeriod);
                sd11 = sd11.subtract(sumpayCurPeriod);
                sd12 = sd12.subtract(sumpayCurPeriod);
                sd13 = sd13.subtract(sumpayCurPeriod);
                sd14 = sd14.subtract(sumpayCurPeriod);
                sd15 = sd15.subtract(sumpayCurPeriod);
                sd16 = sd16.subtract(sumpayCurPeriod);
                sd17 = sd17.subtract(sumpayCurPeriod);
                sd18 = sd18.subtract(sumpayCurPeriod);
                sd19 = sd19.subtract(sumpayCurPeriod);
                sd20 = sd20.subtract(sumpayCurPeriod);
                sd21 = sd21.subtract(sumpayCurPeriod);
                sd22 = sd22.subtract(sumpayCurPeriod);
                sd23 = sd23.subtract(sumpayCurPeriod);
                sd24 = sd24.subtract(sumpayCurPeriod);
                sd25 = sd25.subtract(sumpayCurPeriod);
                sd26 = sd26.subtract(sumpayCurPeriod);
                sd27 = sd27.subtract(sumpayCurPeriod);
                sd28 = sd28.subtract(sumpayCurPeriod);
                sd29 = sd29.subtract(sumpayCurPeriod);
                sd30 = sd30.subtract(sumpayCurPeriod);
                sd31 = sd31.subtract(sumpayCurPeriod);

                hashMap.put(ChartPaymentDS.DDSCODE, rs.getString(1));
                hashMap.put(ChartPaymentDS.PROJECT, rs.getString(2));
                hashMap.put(ChartPaymentDS.NUMBERDOC, rs.getString(3));
                hashMap.put(ChartPaymentDS.ORDERDATE, rs.getString(4));
                hashMap.put(ChartPaymentDS.STATUSSYMBOL, rs.getString(22));
                hashMap.put(ChartPaymentDS.OKPO, rs.getString(8));
                hashMap.put(ChartPaymentDS.ORGNAME, rs.getString(9));
                hashMap.put(ChartPaymentDS.CONTRACT, rs.getString(7));
                hashMap.put(ChartPaymentDS.MNAME, rs.getString(5));
                hashMap.put(ChartPaymentDS.MEAS, rs.getString(6));
                hashMap.put(ChartPaymentDS.COUNTFACT, rs.getBigDecimal(10));
                hashMap.put(ChartPaymentDS.PRICEWITHNDS , rs.getBigDecimal(13));
                hashMap.put(ChartPaymentDS.SUMMA, rs.getBigDecimal(14));
                hashMap.put(ChartPaymentDS.POSTAVKA_DATE, rs.getString(16));
                hashMap.put(ChartPaymentDS.DELIVERYTIME, rs.getInt(21));
                hashMap.put(ChartPaymentDS.BUDGETREFNAME, rs.getString(20));
                hashMap.put(ChartPaymentDS.PAYMENTTYPENAME, rs.getString(25));
                hashMap.put(ChartPaymentDS.PAYMENTVALUE, rs.getInt(26));
                hashMap.put(ChartPaymentDS.OICODE, rs.getInt(19));

                if (sd1.doubleValue() < 0 ){sd1 = new BigDecimal(0);}
                if (sd2.doubleValue() < 0 ){sd2 = new BigDecimal(0);}
                if (sd3.doubleValue() < 0 ){sd3 = new BigDecimal(0);}
                if (sd4.doubleValue() < 0 ){sd4 = new BigDecimal(0);}
                if (sd5.doubleValue() < 0 ){sd5 = new BigDecimal(0);}
                if (sd6.doubleValue() < 0 ){sd6 = new BigDecimal(0);}
                if (sd7.doubleValue() < 0 ){sd7 = new BigDecimal(0);}
                if (sd8.doubleValue() < 0 ){sd8 = new BigDecimal(0);}
                if (sd9.doubleValue() < 0 ){sd9 = new BigDecimal(0);}
                if (sd10.doubleValue() < 0 ){sd10 = new BigDecimal(0);}
                if (sd11.doubleValue() < 0 ){sd11 = new BigDecimal(0);}
                if (sd12.doubleValue() < 0 ){sd12 = new BigDecimal(0);}
                if (sd13.doubleValue() < 0 ){sd13 = new BigDecimal(0);}
                if (sd14.doubleValue() < 0 ){sd14 = new BigDecimal(0);}
                if (sd15.doubleValue() < 0 ){sd15 = new BigDecimal(0);}
                if (sd16.doubleValue() < 0 ){sd16 = new BigDecimal(0);}
                if (sd17.doubleValue() < 0 ){sd17 = new BigDecimal(0);}
                if (sd18.doubleValue() < 0 ){sd18 = new BigDecimal(0);}
                if (sd19.doubleValue() < 0 ){sd19 = new BigDecimal(0);}
                if (sd20.doubleValue() < 0 ){sd20 = new BigDecimal(0);}
                if (sd21.doubleValue() < 0 ){sd21 = new BigDecimal(0);}
                if (sd22.doubleValue() < 0 ){sd22 = new BigDecimal(0);}
                if (sd23.doubleValue() < 0 ){sd23 = new BigDecimal(0);}
                if (sd24.doubleValue() < 0 ){sd24 = new BigDecimal(0);}
                if (sd25.doubleValue() < 0 ){sd25 = new BigDecimal(0);}
                if (sd26.doubleValue() < 0 ){sd26 = new BigDecimal(0);}
                if (sd27.doubleValue() < 0 ){sd27 = new BigDecimal(0);}
                if (sd28.doubleValue() < 0 ){sd28 = new BigDecimal(0);}
                if (sd29.doubleValue() < 0 ){sd29 = new BigDecimal(0);}
                if (sd30.doubleValue() < 0 ){sd30 = new BigDecimal(0);}
                if (sd31.doubleValue() < 0 ){sd31 = new BigDecimal(0);}

                hashMap.put(ChartPaymentDS.SD1, sd1);
                hashMap.put(ChartPaymentDS.SD2, sd2);
                hashMap.put(ChartPaymentDS.SD3, sd3);
                hashMap.put(ChartPaymentDS.SD4, sd4);
                hashMap.put(ChartPaymentDS.SD5, sd5);
                hashMap.put(ChartPaymentDS.SD6, sd6);
                hashMap.put(ChartPaymentDS.SD7, sd7);
                hashMap.put(ChartPaymentDS.SD8, sd8);
                hashMap.put(ChartPaymentDS.SD9, sd9);
                hashMap.put(ChartPaymentDS.SD10, sd10);
                hashMap.put(ChartPaymentDS.SD11, sd11);
                hashMap.put(ChartPaymentDS.SD12, sd12);
                hashMap.put(ChartPaymentDS.SD13, sd13);
                hashMap.put(ChartPaymentDS.SD14, sd14);
                hashMap.put(ChartPaymentDS.SD15, sd15);
                hashMap.put(ChartPaymentDS.SD16, sd16);
                hashMap.put(ChartPaymentDS.SD17, sd17);
                hashMap.put(ChartPaymentDS.SD18, sd18);
                hashMap.put(ChartPaymentDS.SD19, sd19);
                hashMap.put(ChartPaymentDS.SD20, sd20);
                hashMap.put(ChartPaymentDS.SD21, sd21);
                hashMap.put(ChartPaymentDS.SD22, sd22);
                hashMap.put(ChartPaymentDS.SD23, sd23);
                hashMap.put(ChartPaymentDS.SD24, sd24);
                hashMap.put(ChartPaymentDS.SD25, sd25);
                hashMap.put(ChartPaymentDS.SD26, sd26);
                hashMap.put(ChartPaymentDS.SD27, sd27);
                hashMap.put(ChartPaymentDS.SD28, sd28);
                hashMap.put(ChartPaymentDS.SD29, sd29);
                hashMap.put(ChartPaymentDS.SD30, sd30);
                hashMap.put(ChartPaymentDS.SD31, sd31);

                rows.add(hashMap);
            }

            rs.close();
            tempSt.close();



        return new ChartPaymentDS(rows.toArray());

    } catch (SQLException e) {
        throw new ReportSystemException(e);
    } catch (ParseException e) {
        throw new ReportSystemException(e);
    } finally {

    }
    }


 /*
  * параметры для postgres
  * */
  public String setParamPostgresql( String paramStr  )

  throws PersistenceException
  {
  boolean isDebug = true ;

  JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
  Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
  UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

  if (isDebug){
  System.out.println("start setParamPostgresql:" + new Date());
  }


  String sql = paramStr;


  if (isDebug){
  System.out.println("sql : " + sql);

  }

  PreparedStatement statement = null;
  ResultSet  resultSet = null;

  String out = "";
  try {

  statement = connection.prepareStatement(sql);


  resultSet = statement.executeQuery();


  out = "";
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

  public JRDataSource getDsFinansirOsvoenieBalans(int enipitemcode , String itcodesandchildcodes , String datereport , int kvartal
		  , String infotenders ,

          // --- FIRST DATA COLUMN
          String itemnumber,
          String enippurposeprogram,
          String name_product,
          String measurementname,
          String financing,
          BigDecimal price,
          BigDecimal countgen,
          BigDecimal sumgen,
          BigDecimal period_count,
          BigDecimal period_sum ,
          int inform_nkre , // по форме нкре(1) колонки осталось не профинансировано считаются относительно накопительного по кварталам , иначе относительно года
          String datefrompay
		  )
	{


	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	LoggableStatement tempSt = null;
	ResultSet rs = null;

	try {

		ENIPItemDAO ipiDAO = new ENIPItemDAO(connection, userProfile);
		ENPlanWorkDAO pDAO = new ENPlanWorkDAO(connection, userProfile);

		ENIPItemFilter ipiFilter = new ENIPItemFilter();
		ipiFilter.conditionSQL = " enipitem.code in ( " + itcodesandchildcodes + ")";
		ENIPItemShortList ipiList = ipiDAO.getScrollableFilteredList(ipiFilter, 0, -1);

		/*for(int i = 0; i < ipiList.totalCount; i++){

			ENPlanWorkFilter pFilter = new ENPlanWorkFilter();
			pFilter.conditionSQL = " enplanwork.code in ( select ip2p.planrefcode from enipitem2plan ip2p where ip2p.ipitemrefcode = " + ipiList.get(i).code + " )";
			pFilter.kind.code = ENPlanWorkKind.CURRENT; // возбмем только месячные планы

			int[] pArr = pDAO.getFilteredCodeArray(pFilter, 0, -1);

			for(int p = 0; p < pArr.length; p++){

				ENPlanWork pObj = pDAO.getObject(pArr[p]);



				System.out.print("ipicode = " + ipiList.get(i).code + " ||||| plan code = " + pArr[p] );
			}

	    }*/

		String selStr1 = " select  \n" +
		" enipitemcode, /*параметр парент пункта ип*/ \n" +  // 1
		" datereport,  \n" +  // 2
		" sum(profinans_count)::numeric(15,6) as profinans_count ,  \n" + // 3
		" sum(profinans_sum)/1000::numeric(15,2) as profinans_sum ,  \n" + // 4
		" string_agg(distinct(osvoeno_docs) , ',') as osvoeno_docs ,  \n" + // 5
		" sum(osvoeno_count_by_plan) + case when enipitemcode = 4065 then 2 else 0 end as osvoeno_count_by_plan,  \n" + // 6
		" sum(osvoeno_summa_by_plan)/1000::numeric(15,2) + case when enipitemcode = 4065 then 30 else 0 end as osvoeno_summa_by_plan, \n" + // 7
	//	" ( countgen  - sum(profinans_count) )::numeric(15,6) as not_financed_count ,  \n" + // 8
	//	" ( sumgen - (sum(profinans_sum)/1000))::numeric(15,2) as not_financed_sum ,  \n" + // 9
		" ( "+ ( inform_nkre == 1  ? period_count : countgen  ) +"  - sum(profinans_count) )::numeric(15,6) as not_financed_count ,  \n" + // 8
		" ( "+ ( inform_nkre == 1  ? period_sum : sumgen )  +" - (sum(profinans_sum)/1000))::numeric(15,2) as not_financed_sum ,  \n" + // 9
		" case when sum(profinans_count) > 0 and sum(profinans_sum) > 0 \n" +
		"           then ((((sum(profinans_sum)/1000/sum(profinans_count)) / price ) *100 )-100)::numeric(15,2) \n" +
		"        when ( sum(profinans_count) <= 0 or sum(profinans_sum) <= 0 ) and  (sum(osvoeno_count_by_plan) > 0 and sum(osvoeno_summa_by_plan) > 0 ) \n" +
		"           then ((((sum(osvoeno_summa_by_plan)/1000/sum(osvoeno_count_by_plan)) / price ) *100 )-100)::numeric(15,2) \n" +
		"  \n" +
		" else null end as persent ,  \n" + // 10
		" infotenders ,  \n" +  // 11
		" /*postavchiki*/ \n" +
		" ( select \n" +
		"  \n" +
		"         (/* перечень поставщиков */ \n" +
		"         select string_agg(distinct(o.name) ,',') \n" +
		"         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , \n" +
		"          rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay , enipitem2plan ipit2pl \n" +
		"          , rqbillitem bi , rqbill b , rqorg o \n" +
		"          , (select it.code from enipitem it where it.code in ("+itcodesandchildcodes+")) dataenipitem \n" +
		"         where   ipit2pl.ipitemrefcode = dataenipitem.code \n" +
		"         and p.kindcode = 2 \n" +
		"         and ei.kindrefcode in (1,6) \n" +
		"         and bi.ddscodestxt = '312 100'  /*выполнено по ддс ип*/ \n" +
		"         and p.code = ei.planrefcode \n" +
		"         and ei.code = bi2ei.estimateitemcode \n" +
		"         and pi2bi.billitemcode = bi2ei.billitemcode \n" +
		"         and pi2bi.paydocitemcode = pi.code \n" +
		"         and pi.paydocrefcode = pay.code \n" +
		"         and ipit2pl.planrefcode = p.code \n" +
		"         and bi2ei.billitemcode = bi.code \n" +
		"         and bi.billrefcode = b.code \n" +
		"         and b.orgcode = o.code \n" +
		"  \n" +
		"         /* если на пункте указаны материалы то документы  только по этим материалам из плана \n" +
		"    иначе берется сумма по материалам и услугам скопом */ \n" +
		"      and case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials \n" +
		"              where enipitem2tkmaterials.ipitemrefcode = dataenipitem.code \n" +
		"                                        ) > 0 then \n" +
		"         ei.materialrefcode in  (select enipitem2tkmaterials.materialrefcode from enipitem2tkmaterials \n" +
		"              where enipitem2tkmaterials.ipitemrefcode = dataenipitem.code \n" +
		"                                        ) \n" +
		"      else 1=1 \n" +
		"      end \n" +
		"           ) \n" +
		"  \n" +
		"  ) as contragent \n" +  // 12
		"  \n" ;

       String selStr2 =" from ( \n" +
		" select  \n" +
		" enipitemcode , /*параметр парент пункта ип*/ \n" +
		" (select COALESCE(enipitem.countgen,0) from enipitem where enipitem.code = enipitemcode) as countgen ,  \n" +
		" (select COALESCE(enipitem.sumgen,0) from enipitem where enipitem.code = enipitemcode) as sumgen , \n" +
		" (select COALESCE(enipitem.price,0) from enipitem where enipitem.code = enipitemcode) as price , \n" +
		" (select enipitem.infotenders from enipitem where enipitem.code = enipitemcode) as infotenders , \n" +
		" planworkcode,  \n" +
		" iscounters,  \n" +
		" typerefcode,  \n" +
		" ipimplementtyperefcode,  \n" +
		" isusematerialonipitem,  \n" +
		" rep_kvartal,  \n" +
		" datereport,  \n" +
		" case when isusematerialonipitem > 0 then profinans_count else osvoeno_count_by_plan end as profinans_count ,  \n" +
		// SUPP-52255 - возвращали деньги , отнимем
					" case  when planworkcode = 1020604843 then profinans_sum - 833333 " +   // c ндс 1000000
					"         when planworkcode = 1020752615 then profinans_sum - 1980055 " +   // c ндс 2376066
				    " else profinans_sum   end as profinans_sum ,  \n" +
		" osvoeno_docs,  \n" +
		" osvoeno_count_by_plan,  \n" +
		" /* 22.07.2014 задание щербина что если по второму разделу суммма по внедрению больше чем сумма по финансированию тогда сумма по внедрению \n" +
		"   =  сумме по финансированию */ \n" +
		"   /*23.09.2021 доработка 2 раздела по новому тех  заданию . case when ( select enipitem.invgrouprefcode from enipitem where enipitem.code = enipitemcode ) = 2 \n" +
		"         and osvoeno_summa_by_plan > profinans_sum then profinans_sum else  osvoeno_summa_by_plan   end as */ osvoeno_summa_by_plan \n" +

		" from ( \n" +
		"  \n" +
		" select  \n" +
		" enipitemcode \n" +
		" ,ipitemcode   \n" +
		" ,planworkcode \n" +
		" ,isCounters \n" +
		" ,typerefcode \n" +
		" ,ipimplementtyperefcode  \n" +
		" ,isUseMaterialOnIpItem \n" +
		" ,rep_kvartal  \n" +
		" ,datereport \n" +
		" /* профинансировано кол-во */  \n" +
		" , COALESCE( \n" +
		" case when isUseMaterialOnIpItem > 0 then \n" +
		"         (/* кол-во профинансировано если на пункте ИП проставлен материал */ \n" +
		"         select sum(bi2ei.countgen) \n" +
		"         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi , \n" +
		"           enipitem2plan ipit2pl /*, enipitem2tkmaterials iti2tm*/  \n" +
		"         where p.kindcode = 2 \n" +
		"         and bi2ei.billitemcode = bi.code \n" +
		"         and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
		"         and ei.kindrefcode in (1,6) \n" +
		"         and p.code = ei.planrefcode \n" +
		"         and ei.code = bi2ei.estimateitemcode \n" +
		"         and ipit2pl.planrefcode = p.code \n" +
		"         and p.code = sel_in1.planworkcode  \n" +
		//"         and ipit2pl.ipitemrefcode = iti2tm.ipitemrefcode \n" +
		//"         and ei.materialrefcode = iti2tm.materialrefcode \n" +
		// "         and iti2tm.ipitemrefcode = sel_in1.ipitemcode \n" +
		"         and ei.materialrefcode in ( select i2m.materialrefcode from enipitem2tkmaterials i2m where i2m.ipitemrefcode= sel_in1.ipitemcode and i2m.materialrefcode=ei.materialrefcode) \n" +
		"         and ipit2pl.ipitemrefcode = ipitemcode \n" +
		"         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
		"                                where pi2bi.billitemcode = bi.code \n" +
		"                                   and pi2bi.paydocitemcode = pi.code \n" +
		"                                   and pi.paydocrefcode = pay.code \n" +
		//"                                   and pay.dategen <= datereport"
		"                                   and pay.dategen between to_date('" + datefrompay +"' ,'dd.mm.yyyy')  and datereport \n" +
		"                                ) \n" +
		"          ) \n" +
		"  else /*если на пункте материал не проставлен то кол- во подкинем выше (возмется из освоено - кол-во )*/ \n" +
		"    0 \n" +
		"  \n" +
		"  end \n" +
		"  ,0) as profinans_count \n" +
		"  /*--------------------*/ \n" +
		"  /*сумма профинансировано без НДС */ \n" +
		"  , coalesce( ( select sum(summagen)::numeric(15,2) from \n" +
		" ( \n" +
		" select sum( \n" +
		"   ( \n" +
		"   bi2ei.countgen \n" +
		"   * \n" +
		"   (bi.pricewithoutnds*pi.summagen/bi.sumwithoutnds  ) \n" +
		"   ) \n" +
		"  \n" +
		"    )  / (1::numeric+(b.vat::numeric/100::numeric) )  as summagen \n" +
		"  \n" +
		"   from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei ,  rqpaydocitem2billitem pi2bi , \n" +
		"   rqpaydocitem pi , rqpaydoc pay , rqbillitem bi , rqbill b    \n" +
		" where p.kindcode = 2 \n" +
		" and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
		" and ei.kindrefcode in (1,6) \n" +
		" and p.code = ei.planrefcode \n" +
		" and ei.code = bi2ei.estimateitemcode \n" +
		" and pi2bi.billitemcode = bi2ei.billitemcode \n" +
		" and pi2bi.paydocitemcode = pi.code \n" +
		" and pi.paydocrefcode = pay.code \n" +
		" and bi2ei.billitemcode = bi.code \n" +
		//" and pay.dategen <= datereport \n" +
		" and pay.dategen between to_date('"+datefrompay+"' ,'dd.mm.yyyy')  and datereport \n" +
		" and bi.billrefcode = b.code \n" +
		" and p.code = sel_in1.planworkcode \n" +
		"  \n" +
		" /* если на пункте указаны материалы то сумму оплат только по этим материалам из плана \n" +
		"    иначе берется сумма оплат по материалам и услугам скопом */ \n" +
		" and case when isUseMaterialOnIpItem > 0 then \n" +
		"               ei.materialrefcode in  (select enipitem2tkmaterials.materialrefcode from enipitem2tkmaterials \n" +
		"                                       where enipitem2tkmaterials.ipitemrefcode = sel_in1.ipitemcode \n" +
		"                                      ) \n" +
		"     else 1=1 \n" +
		"     end \n" +
		"  \n" +
		" group by b.vat \n" +
		" ) s1 ),0) as profinans_sum \n" ;


       String selStr3 = " /* osvoeno_docs  */ \n" +
		" , \n" +
		"    /* освоение ОЗ , ХОЗ способ */ \n" +
		"  case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then  \n" +
		"                case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */  \n" +
		"                        (select string_agg(distinct( case when f.kindcode = 8  then 'Введ. в експл. спец. одягу (МБП)' \n" + 
		"                        	 when f.kindcode = 9  then 'Введ. в експл. спец. одягу (МНМА)'\n" + 
		"                        	 when f.kindcode = 11 then 'Введ. в експл. МБП'\n" + 
		"                        	 when f.kindcode = 12 then 'Введ. в експл. МНМА'\n" + 
		"                        	 when f.kindcode = 21 then 'Введ. в експл. ОЗ'\n" + 
		"                             else '' end || f.numberdoc) ,',') \n" +
		"                         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi              \n" +
		"                        ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f           \n" +
		"                         where p.kindcode = 2 \n" +
		"                         and bi2ei.billitemcode = bi.code \n" +
		"                         and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/ \n" +
		"                         and ei.kindrefcode in (1,6) \n" +
		"                         and p.code = ei.planrefcode \n" +
		"                         and ei.code = bi2ei.estimateitemcode             \n" +
		"                         and fi2ei.estimateitemcode = ei.code \n" +
		"                         and fi2ei.fkorderitemrefcode = fi.code \n" +
		"                         and fi.fkorderrefcode = f.code \n" +
		"                         and f.kindcode in (8,9,11,12,21) \n" +
		"                         and f.statuscode in (3,4,5) \n" +
		"                         and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                         and p.code = sel_in1.planworkcode \n" +
		"                         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
		"                                                where pi2bi.billitemcode = bi.code \n" +
		"                                                   and pi2bi.paydocitemcode = pi.code \n" +
		"                                                   and pi.paydocrefcode = pay.code \n" +
		"                                         ) \n" +
		"  \n" +
		"                         and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode ) \n" +
		"                                     \n" +
		"                          ) \n" +
		"                      when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, кол-во из фактов которые сидят в актах а акты в ОЗ-2 */ \n" +
		"                           (select string_agg(distinct(rm.numbergen) ,',') \n" +
		"                             from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
		"                             , enplanwork p , enreconstrmodernoz rm \n" +
		"                             where  hp.planoldrefcode = ipp.planrefcode \n" +
		"                             and hp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                             and hp.reasoncode = 4 \n" +
		"                             and hp.plannewrefcode = hf.planoldrefcode \n" +
		"                             and hf.reasoncode = 5 \n" +
		"                             and hf.plannewrefcode = a2p.plancode \n" +
		"                             and a2p.actrefcode = r2a.actrefcode \n" +
		"                             and hf.plannewrefcode = p.code  \n" +
		"                             and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                             and rm.statusrefcode = 3  \n" +
		"                             and p.statuscode  = 3 \n" +
		"                             and ipp.ipitemrefcode = ipitemcode \n" +
		"                             and rm.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                          ) \n" +
		"                end \n" +
		"             /* освоение ОЗ , услуги со стороны */ \n" +
		"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
		"                (select string_agg(distinct(rm.numbergen) ,',') \n" +
		"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
		"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
		"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
		"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                   and hp.reasoncode = 4 \n" +
		"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
		"                   and hf.reasoncode = 5 \n" +
		"                   and hf.plannewrefcode = a2p.plancode \n" +
		"                   and a2p.actrefcode = r2a.actrefcode \n" +
		"                   and hf.plannewrefcode = p.code  \n" +
		"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                   and rm.statusrefcode = 3  \n" +
		"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
		"                   and rpf.factcode = p.code  \n" +
		"                   and rpf.fkorderitemcode = fi.code  \n" +
		"                   and fi.fkorderrefcode = f.code \n" +
		"                   and f.kindcode = 14 \n" +
		"                   and f.statuscode in (3,4,5) \n" +
		"                   and p.statuscode  = 3  \n" +
		"                   and ipp.ipitemrefcode = ipitemcode \n" +
		"                  ) \n" +
		"            /* освоение Акт вып работ , услуги со стороны */ \n" +
		"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
		"                (select string_agg(distinct(f.numberdoc) ,',') \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
		"                      , rqfkorder f  \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and rpf.factcode = p.code  \n" +
		"                 and rpf.fkorderitemcode = fi.code  \n" +
		"                 and fi.fkorderrefcode = f.code \n" +
		"                 and f.kindcode = 14 \n" +
		"                 and f.statuscode in (3,4,5)  \n" +
		"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                )  \n" +
		"            /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */ \n" +
		"        /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
		"                 ''  пока непонятно как по счетчикам считать использование*/  \n" +
		"                 /*( \n" +
		"                  select string_agg(distinct(scusageinputitemoz.numberdoc) ,',') \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
		"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and p.code = ei.planrefcode \n" +
		"                 and ei.accountingtyperefcode = 2  \n" +
		"                 and scc.estimateitemrefcode = ei.code  \n" +
		"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
		"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
		"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
		"                 and scusageinputitem.sccode is not null \n" +
		"                 and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію \n" +
		"                                             ,4  -- Введення у експлуатацію ЗКУ \n" +
		"                                             ,5  -- Введення у експлуатацію  у складі ЗКУ \n" +
		"                                             )   \n" +
		"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
		"                 and scusageinput.statusrefcode = 3 --ОЗ-1 проведений  \n" +
		"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
		"                  \n" +
		"                  \n" +
		"                )*/ \n" +
		"           \n" +
		"           \n" +
		"           \n" +
		"    /* освоение по приходным ордерам */ \n" +
		   "        when  ipimplementtyperefcode = 3 then   \n" +
		   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в Приходах */   \n" +
		   "                         (select string_agg(distinct(f.numberdoc) ,',')  \n" +
		   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
		   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
		   "                          where p.kindcode = 2  \n" +
		   "                          and bi2ei.billitemcode = bi.code  \n" +
		   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
		   "                          and ei.kindrefcode in (1,6)  \n" +
		   "                          and p.code = ei.planrefcode  \n" +
		   "                          and ei.code = bi2ei.estimateitemcode              \n" +
		   "                          and fi2ei.estimateitemcode = ei.code  \n" +
		   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
		   "                          and fi.fkorderrefcode = f.code  \n" +
		   "                          and f.kindcode in (1)  \n" +
		   "                          and f.statuscode in (3,4,5)  \n" +
		   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
		   "                          and p.code = sel_in1.planworkcode  \n" +
		   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
		   "                                                 where pi2bi.billitemcode = bi.code  \n" +
		   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
		   "                                                    and pi.paydocrefcode = pay.code  \n" +
		   "                                          )  \n" +
		   "    \n" +
		   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
		   "                                       \n" +
		   "                           )  \n" +
		   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, документы по всем приходным ордерам материалов с плана */  \n" +
		   "                         (select string_agg(distinct(f.numberdoc) ,',')  \n" +
		   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
		   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
		   "                          where p.kindcode = 2  \n" +
		   "                          and bi2ei.billitemcode = bi.code  \n" +
		   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
		   "                          and ei.kindrefcode in (1,6)  \n" +
		   "                          and p.code = ei.planrefcode  \n" +
		   "                          and ei.code = bi2ei.estimateitemcode              \n" +
		   "                          and fi2ei.estimateitemcode = ei.code  \n" +
		   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
		   "                          and fi.fkorderrefcode = f.code  \n" +
		   "                          and f.kindcode in (1)  \n" +
		   "                          and f.statuscode in (3,4,5)  \n" +
		   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
		   "                          and p.code = sel_in1.planworkcode  \n" +
		   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
		   "                                                 where pi2bi.billitemcode = bi.code  \n" +
		   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
		   "                                                    and pi.paydocrefcode = pay.code  \n" +
		   "                                          )  \n" +
		   "                           )  \n" +
		   "                 end   \n" +
		   "  \n" +
		   " /*освоение документы  zku */  \n" +
		   "        when typerefcode in ( 106, 111, 112) then  \n" +
		   "         (select string_agg(distinct( scusageinputitemoz.numberdoc),',')  \n" +
		   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
		   "                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput                               \n" +
		   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
		   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
		   "                              and hp.reasoncode = 4  \n" +
		   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
		   "                              and hf.reasoncode = 5  \n" +
		   "                              and hf.plannewrefcode = a2p.plancode  \n" +
		   "                              and hf.plannewrefcode = p.code   \n" +
		   "                              and p.statuscode  = 3  \n" +
		   "                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
		   "                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
		   "                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
		   "                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
		   "                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		   " /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
		   " /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
		   "   scusageinput.dategen >= sel_in1.minDateOrderInvest      \n" + 
		   "  else 1=1  \n" +
		   "  end /*new*/  \n" + 
		   "                              and scusageinput.statusrefcode = 3                                \n" +
		   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
		   "                           ) \n" +
		   "  \n"  +
		   "   /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" + 
		   "   when ipimplementtyperefcode = 4 then  \n" +
		   "     /*выберем документы  */  \n" + 
		   "  (select string_agg(distinct actnum ,',') from ( select  \n" +
		   "    distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
		   "                         from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
		   "                         , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
		   "                         where  hp.planoldrefcode = ipp.planrefcode   \n" +
		   "                         and hp.planoldrefcode = sel_in1.planworkcode    \n" +
		   "                         and hp.reasoncode = 4   \n" +
		   "                         and hp.plannewrefcode = hf.planoldrefcode   \n" +
		   "                         and hf.reasoncode = 5   \n" +
		   "                         and hf.plannewrefcode = a2p.plancode  \n" + 
		   "                         and hf.plannewrefcode = p.code   \n" +
		   "                         and p.statuscode  = 3  \n" +
		   "                         and enestimateitem.planrefcode = p.code  \n" +
		   "                         and enestimateitem.code = sccounter.estimateitemrefcode \n" +
		   "                         and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
		   "                         and a2p.actrefcode = act.code \n" +
		   "                         and enestimateitem.kindrefcode in (1,2) \n" +
		   "                         and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
		   "                         /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
		   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
		   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +    
		   "                        else 1=1 \n" +
		   "                        end \n" +
		   "                         and ipp.ipitemrefcode = ipitemcode   \n" +
		   "                                union  \n" +
		   "              select distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
		   "              from enplancorrecthistory phf,  \n" +
		   "                   enestimateitem eifact,  \n" +
		   "                  finmaterials fin, \n" +
		   "                 enact2enplanwork a2p,        \n" +                  
		   "                 enplanwork pfact , \n" +
		   "                 enact act  \n" +
		   "            where phf.plannewrefcode = pfact.code and  \n" +
		   "                  phf.plannewrefcode = eifact.planrefcode and  \n" +
		   "                  eifact.kindrefcode in (1, 2) and \n" +
		   "                  eifact.countfact > 0 and \n" +
		   "                  eifact.code = fin.estimateitemrefcode and \n" +
		   "                  fin.statusrefcode = 1 and \n" +
		   "                  pfact.code = eifact.planrefcode and  \n" +
		   "                  pfact.code = a2p.plancode and    \n" +                      
		   "                  pfact.statuscode = 3 and \n" +
		   "                   act.code =a2p.actrefcode and \n" +
		   "                    act.dateact <= sel_in1.datereport  /*дата освоения материалов */ \n" +
		   "                    /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
		   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
		   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +   
		   "                        else 1=1 \n" +
		   "                        end \n" +
		   "                    and phf.reasoncode = 5  \n" +
		   "                    and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
		   "                    and phf.planoldrefcode in ( \n" +
		   "                                            select ph.plannewrefcode \n" +
		   "                                            from enplancorrecthistory ph \n" +
		   "                                            where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
		   "                                                  ph.reasoncode = 4 \n" +
		   "                    ) \n" +
	       "  ) as q1 ) \n" +
	       "      /*zzzend*/     \n" +
		"       else ''  \n" +
		"  end          \n" +
		"     \n" +
		"  as osvoeno_docs  \n" +
		"  \n" +
		" /* osvoenie count */ \n" ;
		String selStr4 =" ,coalesce( \n" +
		"    /* освоение ОЗ , ХОЗ способ */ \n" +
		"  case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then  \n" +
		"                case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */ \n" +
		"                        coalesce((select sum(fi2ei.countgen) \n" +
		"                         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi              \n" +
		"                        ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f           \n" +
		"                         where p.kindcode = 2 \n" +
		"                         and bi2ei.billitemcode = bi.code \n" +
		"                         and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */ \n" +
		"                         and ei.kindrefcode in (1,6) \n" +
		"                         and p.code = ei.planrefcode \n" +
		"                         and ei.code = bi2ei.estimateitemcode             \n" +
		"                         and fi2ei.estimateitemcode = ei.code \n" +
		"                         and fi2ei.fkorderitemrefcode = fi.code \n" +
		"                         and fi.fkorderrefcode = f.code \n" +
		"                         and f.kindcode in (8,9,11,12,21) \n" +
		"                         and f.statuscode in (3,4,5) \n" +
		"                         and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                         and p.code = sel_in1.planworkcode \n" +
		"                         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
		"                                                where pi2bi.billitemcode = bi.code \n" +
		"                                                   and pi2bi.paydocitemcode = pi.code \n" +
		"                                                   and pi.paydocrefcode = pay.code \n" +
		"                                         ) \n" +
		"  \n" +
		"                         and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode ) \n" +
		"                                     \n" +
		"                          ),0) \n" +

        /// SUPP-107705 ЦУДОУУ, отчет о выполнении ИП-2021, ЗКУ 1 фазные (убрать нужно промежуточные , так как дублируются данные  )
		/*"  --кол-во из промежут документов 1   \n" +
		"                          +  \n" +
		"                          coalesce((select sum(fi2ei.countgen)  \n" +
		"                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
		"                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
		"                          where p.kindcode = 2  \n" +
		"                          and bi2ei.billitemcode = bi.code  \n" +
		"                          and bi.ddscodestxt = '312 100'  --выполнено по ддс ип    \n" +
		"                          and ei.kindrefcode in (1,6)  \n" +
		"                          and p.code = ei.planrefcode  \n" +
		"                          and ei.code = bi2ei.estimateitemcode              \n" +
		"                          and fi2ei.estimateitemcode = ei.code  \n" +
		"                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
		"                          and fi.fkorderrefcode = f.code  \n" +
		"                          and f.kindcode = 1 \n" +
		"                          and f.statuscode in (3,4,5)  \n" +
		"                          and f.dategen <= sel_in1.datereport --дата освоения материалов    \n" +
		"                          and p.code = sel_in1.planworkcode  \n" +
		"                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
		"                                                 where pi2bi.billitemcode = bi.code  \n" +
		"                                                    and pi2bi.paydocitemcode = pi.code  \n" +
		"                                                    and pi.paydocrefcode = pay.code  \n" +
		"                                          )  \n" +
		"    \n" +
		"                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
		"                          -- что бы естимейт был не введен в эксплуатацию     \n" +
		" 				         and not exists ( select rqfkorderitem2enstmttm.estimateitemcode from  rqfkorderitem2enstmttm  , rqfkorderitem  ,  \n" +
		" rqfkorder   \n" +
		" 				                                               where rqfkorderitem2enstmttm.estimateitemcode = ei.code   \n" +
		" 				                                               and rqfkorderitem2enstmttm.fkorderitemrefcode = rqfkorderitem.code   \n" +
		" 				                                               and rqfkorderitem.fkorderrefcode = rqfkorder.code  \n" +
		" 				                                               and rqfkorder.kindcode in (8,9,11,12,21)  \n" +
		" 				                                               and rqfkorder.statuscode in (3,4,5)  \n" +
		" 				                                               and rqfkorder.dategen <= sel_in1.datereport -- дата освоения материалов      \n" +
		" 				                                            )  \n" +
		"                          ),0) \n" +
		"  \n" +*/



		"                      when isUseMaterialOnIpItem = 0 then /*материала на пункте ип нету, кол-во из фактов которые сидят в актах а акты в ОЗ-2 */  \n" +
		"                          coalesce( (select sum(p.investWorksAmount) \n" +
		"                             from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
		"                             , enplanwork p , enreconstrmodernoz rm \n" +
		"                             where  hp.planoldrefcode = ipp.planrefcode \n" +
		"                             and hp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                             and hp.reasoncode = 4 \n" +
		"                             and hp.plannewrefcode = hf.planoldrefcode \n" +
		"                             and hf.reasoncode = 5 \n" +
		"                             and hf.plannewrefcode = a2p.plancode \n" +
		"                             and a2p.actrefcode = r2a.actrefcode \n" +
		"                             and hf.plannewrefcode = p.code  \n" +
		"                             and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                             and rm.statusrefcode = 3  \n" +
		"                             and p.statuscode  = 3 \n" +
		"                             and rm.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                             and ipp.ipitemrefcode = ipitemcode \n" +
		"                          ),0)  \n" +

		/*"  -- кол-во из промежут документов 2   \n" +
		"                          +  \n" +
		"                          coalesce( (select sum(p.investWorksAmount)  \n" +
		"                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p   , enplanwork p , enact a  \n" +
		"                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
		"                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
		"                              and hp.reasoncode = 4  \n" +
		"                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
		"                              and hf.reasoncode = 5  \n" +
		"                              and hf.plannewrefcode = a2p.plancode                               \n" +
		"                              and hf.plannewrefcode = p.code   \n" +
		"                              and p.statuscode  = 3  \n" +
		"                              and ipp.ipitemrefcode = ipitemcode  \n" +
		"                              and a2p.actrefcode = a.code   \n" +
		" 				             and a.dategen <= sel_in1.datereport -- дата освоения материалов    \n" +
		" 				             and a.statusrefcode = 3  \n" +
		"                              and not exists ( select enreconstrmodernoz2nct.code from enreconstrmodernoz2nct ,  enreconstrmodernoz \n" +
		"                                                                     where enreconstrmodernoz2nct.actrefcode = a2p.actrefcode \n" +
		"                                                                       and enreconstrmodernoz2nct.enreconstrmodernozrfcd =  enreconstrmodernoz.code \n" +
		" 				                                                      and enreconstrmodernoz.dategen <= sel_in1.datereport -- дата освоения материалов   \n" +
		" 				                                                      and enreconstrmodernoz.statusrefcode = 3  \n" +
		" 				                                                  )  \n" +
		"                           ),0) \n" +*/
		"  \n" +


		"                end \n" +
		"             /* освоение ОЗ , услуги со стороны */ \n" +
		"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
		"                coalesce( (select sum(p.investWorksAmount) \n" +
		"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
		"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
		"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
		"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                   and hp.reasoncode = 4 \n" +
		"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
		"                   and hf.reasoncode = 5 \n" +
		"                   and hf.plannewrefcode = a2p.plancode \n" +
		"                   and a2p.actrefcode = r2a.actrefcode \n" +
		"                   and hf.plannewrefcode = p.code  \n" +
		"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                   and rm.statusrefcode = 3  \n" +
		"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
		"                   and rpf.factcode = p.code  \n" +
		"                   and rpf.fkorderitemcode = fi.code  \n" +
		"                   and fi.fkorderrefcode = f.code \n" +
		"                   and f.kindcode = 14 \n" +
		"                   and f.statuscode in (3,4,5) \n" +
		"                   and p.statuscode  = 3  \n" +
		"                   and ipp.ipitemrefcode = ipitemcode \n" +
		"                  ),0) \n" +

		/*"   -- кол-во из промежут документов 3  \n" +
		"                          + \n" +
		"                  coalesce( (select sum(p.investWorksAmount)  \n" +
		"                    from  enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
		" 				             , enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f   \n" +
		"                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
		"                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
		"                    and hp.reasoncode = 4  \n" +
		"                    and hp.plannewrefcode = hf.planoldrefcode  \n" +
		"                    and hf.reasoncode = 5  \n" +
		"                    and hf.plannewrefcode = a2p.plancode  \n" +
		"                    and hf.plannewrefcode = p.code                      \n" +
		"                    and rpf.factcode = p.code   \n" +
		"                    and rpf.fkorderitemcode = fi.code   \n" +
		"                    and fi.fkorderrefcode = f.code  \n" +
		"                    and f.kindcode = 14  \n" +
		"                    and f.statuscode in (3,4,5)  \n" +
		"                    and f.dategen <= sel_in1.datereport -- дата освоения материалов/ услуг    \n" +
		"                    and p.statuscode  = 3   \n" +
		"                    and ipp.ipitemrefcode = ipitemcode  \n" +
		"                    and not exists ( select r2a.actrefcode from enreconstrmodernoz2nct r2a , enreconstrmodernoz oz   \n" +
		" 				                     where r2a.actrefcode = a2p.actrefcode  \n" +
		" 				                       and r2a.enreconstrmodernozrfcd = oz.code  \n" +
		" 				                       and oz.statusrefcode = 3 \n" +
		" 				                       and oz.dategen <= sel_in1.datereport -- дата освоения материалов/ услуг    \n" +
		" 				                    ) \n" +
		"                     \n" +
		"                   )   ,0) \n" +*/
		"  \n" +


		"            /* освоение Акт вып работ , услуги со стороны */ \n" +
		"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
		"               coalesce( (select sum(p.investWorksAmount) \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
		"                      , rqfkorder f  \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and rpf.factcode = p.code  \n" +
		"                 and rpf.fkorderitemcode = fi.code  \n" +
		"                 and fi.fkorderrefcode = f.code \n" +
		"                 and f.kindcode = 14 \n" +
		"                 and f.statuscode in (3,4,5)  \n" +
		"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                ),0)  \n" +
		"            /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */ \n" +
		"        /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
		"                0  пока непонятно как по счетчикам считать использование */ \n" +
		"                /*coalesce( ( \n" +
		"                 select  count(scc.code)   \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
		"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and p.code = ei.planrefcode \n" +
		"                 and ei.accountingtyperefcode = 2 --счетчики \n" +
		"                 and scc.estimateitemrefcode = ei.code  \n" +
		"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
		"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
		"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
		"                 and scusageinputitem.sccode is not null \n" +
		"                 and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію \n" +
		"                                             ,4 -- Введення у експлуатацію ЗКУ \n" +
		"                                             ,5 -- Введення у експлуатацію  у складі ЗКУ \n" +
		"                                             )   \n" +
		"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
		"                 and scusageinput.statusrefcode = 3 -- ОЗ-1 проведений  \n" +
		"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
		"                  \n" +
		"                  \n" +
		"                ),0)*/ \n" +
		"           \n" +
		"           \n" +
		"            \n" +
		 // 21.07.2014 - добавил условия для типа освоения "приход" и по типу плана (зку 106 , 111 , 112)
			" /*освоение приходны ордер */    \n" +
			"            when  ipimplementtyperefcode = 3  then                     \n" +
			"                      case when isUseMaterialOnIpItem > 0 then     \n" +
			"                        coalesce((select sum(coalesce(fi2ei.countgen,0))    \n" +
			"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
			"                          , rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and fi2ei.estimateitemcode = ei.code   \n" +
			"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"      \n" +
			"                           \n" +
			"                          and fi.materialcode in  ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )   \n" +
			"                                          \n" +
			"                           \n" +
			"                            ) ,0)  \n" +
			"                             \n" +
			"                            + /* излишки */ \n" +
			"                            coalesce((select sum(coalesce(rqfkorderitemremainder.countgen,0))    \n" +
			"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
			"                          , rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
			"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"      \n" +
			"                          and fi.materialcode in  ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
			"                                         \n" +
			"                            ) ,0) \n" +
			"                       else \n" +
			"                          coalesce((select sum(coalesce(fi2ei.countgen,0))    \n" +
			"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
			"                          , rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and fi2ei.estimateitemcode = ei.code   \n" +
			"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"                            ) ,0)  \n" +
			"                             \n" +
			"                            + /* излишки */ \n" +
			"                            coalesce((select sum(coalesce(rqfkorderitemremainder.countgen,0))    \n" +
			"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
			"                          , rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
			"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"                            ) ,0 ) \n" +
			"                         \n" +
			"                       end \n" +
			"  \n" +
			"  /*освоение кол-во zku */  \n" +
			"        when typerefcode in ( 106, 111, 112) then  \n" +
			"        coalesce( (select count( p.code)  \n" +
			"                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
			"                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput   \n" +
			"                              \n" +
			"                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
			"                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
			"                              and hp.reasoncode = 4  \n" +
			"                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
			"                              and hf.reasoncode = 5  \n" +
			"                              and hf.plannewrefcode = a2p.plancode  \n" +
			"                              and hf.plannewrefcode = p.code   \n" +
			"                              and p.statuscode  = 3  \n" +
			"                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
			"                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
			"                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
			"                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
			"                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                                                  /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
			"                                scusageinput.dategen >= sel_in1.minDateOrderInvest \n" +
			"                               else 1=1 \n" +
			"                               end /*new*/ \n" +
			"                              and scusageinput.statusrefcode = 3                                \n" +
			"                              and ipp.ipitemrefcode = ipitemcode  \n" +
			"                           ),0)  \n" +
			"  \n" +
			"  /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" +
			"            when ipimplementtyperefcode = 4 then  \n" +
			"            /*выберем кол-во счетчика который подвязан под пункт */  \n" +
			"          coalesce( (select count(distinct sccounter.code)  \n" +
			"                                from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
			"                                , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
			"                                where  hp.planoldrefcode = ipp.planrefcode   \n" +
			"                                and hp.planoldrefcode = sel_in1.planworkcode    \n" +
			"                                and hp.reasoncode = 4   \n" +
			"                                and hp.plannewrefcode = hf.planoldrefcode   \n" +
			"                                and hf.reasoncode = 5   \n" +
			"                                and hf.plannewrefcode = a2p.plancode   \n" +
			"                                and hf.plannewrefcode = p.code    \n" +
			"                                and p.statuscode  = 3   \n" +
			"                                and enestimateitem.planrefcode = p.code   \n" +
			"                                and enestimateitem.code = sccounter.estimateitemrefcode  \n" +
			"                                and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode \n" +
			"                                                                                                                               and coalesce(et.ismaterialforcount,0) > 0 ) \n" +
			"                                and a2p.actrefcode = act.code  \n" +
			"                                and enestimateitem.kindrefcode in (1,2) \n" +
			"                                and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                                /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
			"                                   and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
			"                                    act.dateact >= sel_in1.minDateOrderInvest      \n" +
			"                                   else 1=1  \n" +
			"                                   end \n" +
			"                                and ipp.ipitemrefcode = ipitemcode   \n" +
			"                             ),0)   \n" +
			"                             + \n" +
			"             coalesce((  \n" +
			"                     select sum(coalesce( fin.quantity, 0)) as count  \n" +
			"                     from enplancorrecthistory phf,  \n" +
			"                          enestimateitem eifact,  \n" +
			"                          finmaterials fin,  \n" +
			"                          enact2enplanwork a2p,                          \n" +
			"                          enplanwork pfact ,  \n" +
			"                          enact aa  \n" +
			",tempnomenclatures tn \n" +  
			"                     where phf.plannewrefcode = pfact.code and  \n" +
			"                           tn.mat_id = fin.mat_id  and  \n" +
            "                           phf.plannewrefcode = eifact.planrefcode and  \n" +
			"                           eifact.kindrefcode in (1, 2) and  \n" +
			"                           eifact.countfact > 0 and  \n" +
			"                           eifact.code = fin.estimateitemrefcode and  \n" +
			"                           fin.statusrefcode = 1 and  \n" +
			"                           pfact.code = eifact.planrefcode and  \n" +
			"                           pfact.code = a2p.plancode and                           \n" +
			"                           pfact.statuscode = 3 and  \n" +
			"                           aa.code =a2p.actrefcode and  \n" +
			"                           aa.dateact <= sel_in1.datereport  /*дата освоения материалов */  \n" +
			"                           /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
			"                           and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
			"                            aa.dateact >= sel_in1.minDateOrderInvest      \n" +
			"                           else 1=1  \n" +
			"                           end \n" +
			"                           and phf.reasoncode = 5   \n" +
			"                           and tn.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode  \n" +
			"                                                                                                                   and coalesce(et.ismaterialforcount,0) > 0 ) \n" +
			"                           and phf.planoldrefcode in (  \n" +
			"                                                   select ph.plannewrefcode  \n" +
			"                                                   from enplancorrecthistory ph  \n" +
			"                                                   where ph.planoldrefcode =  sel_in1.planworkcode and  \n" +
			"                                                         ph.reasoncode = 4  \n" +
			"                           )  \n" +
			"          ), 0)   \n" +
			"             /*zzzend*/  \n" +
		"       else 0  \n" +
		"  end          \n" +
		"     \n" +
		" ,0) as osvoeno_count_by_plan  \n" +
		"  \n" +
		" /* osvoenie summa */   \n" ;

		String selStr5 = " ,coalesce( \n" +
		"   case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then /* сумма выберается с финматериалс на фактах связанными с актами вып работ и  \n" +
		" оз-2  */ \n" +
		"               case when isUseMaterialOnIpItem > 0 then  \n" +
		"                   coalesce((select sum(fi2ei.countgen * fi.pricewithoutnds) \n" +
		"                         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi              \n" +
		"                        ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f           \n" +
		"                         where p.kindcode = 2 \n" +
		"                         and bi2ei.billitemcode = bi.code \n" +
		"                         and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
		"                         and ei.kindrefcode in (1,6) \n" +
		"                         and p.code = ei.planrefcode \n" +
		"                         and ei.code = bi2ei.estimateitemcode             \n" +
		"                         and fi2ei.estimateitemcode = ei.code \n" +
		"                         and fi2ei.fkorderitemrefcode = fi.code \n" +
		"                         and fi.fkorderrefcode = f.code \n" +
		"                         and f.kindcode in (8,9,11,12,21) \n" +
		"                         and f.statuscode in (3,4,5) \n" +
		"                         and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                         and p.code = sel_in1.planworkcode \n" +
		"                         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
		"                                                where pi2bi.billitemcode = bi.code \n" +
		"                                                   and pi2bi.paydocitemcode = pi.code \n" +
		"                                                   and pi.paydocrefcode = pay.code \n" +
		"                                         ) \n" +
		"  \n" +
		"                         and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode ) \n" +
		"                                     \n" +
		"                          ),0) \n" +
		/// SUPP-107705 ЦУДОУУ, отчет о выполнении ИП-2021, ЗКУ 1 фазные (убрать нужно промежуточные , так как дублируются данные  )
		/*"  -- сумма из промежут документов 1   \n" +
		"                           +  \n" +
		"                            \n" +
		"                         coalesce((select sum(fi2ei.countgen * fi.pricewithoutnds)  \n" +
		"                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
		"                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
		"                          where p.kindcode = 2  \n" +
		"                          and bi2ei.billitemcode = bi.code  \n" +
		"                          and bi.ddscodestxt = '312 100' -- выполнено по ддс ип   \n" +
		"                          and ei.kindrefcode in (1,6)  \n" +
		"                          and p.code = ei.planrefcode  \n" +
		"                          and ei.code = bi2ei.estimateitemcode              \n" +
		"                          and fi2ei.estimateitemcode = ei.code  \n" +
		"                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
		"                          and fi.fkorderrefcode = f.code  \n" +
		"                          and f.kindcode = 1  \n" +
		"                          and f.statuscode in (3,4,5)  \n" +
		"                          and f.dategen <= sel_in1.datereport -- дата освоения материалов    \n" +
		"                          and p.code = sel_in1.planworkcode  \n" +
		"                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
		"                                                 where pi2bi.billitemcode = bi.code  \n" +
		"                                                    and pi2bi.paydocitemcode = pi.code  \n" +
		"                                                    and pi.paydocrefcode = pay.code  \n" +
		"                                          )  \n" +
		"    \n" +
		"                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
		"                          -- что бы естимейт был не введен в эксплуатацию     \n" +
		" 				         and not exists ( select rqfkorderitem2enstmttm.estimateitemcode from  rqfkorderitem2enstmttm  , rqfkorderitem  ,  \n" +
		" rqfkorder   \n" +
		" 				                           where rqfkorderitem2enstmttm.estimateitemcode = ei.code   \n" +
		" 				                             and rqfkorderitem2enstmttm.fkorderitemrefcode = rqfkorderitem.code   \n" +
		" 				                             and rqfkorderitem.fkorderrefcode = rqfkorder.code  \n" +
		" 				                             and rqfkorder.kindcode in (8,9,11,12,21)  \n" +
		" 				                             and rqfkorder.statuscode in (3,4,5)  \n" +
		" 				                             and rqfkorder.dategen <= sel_in1.datereport  -- дата освоения материалов      \n" +
		" 				                            )              \n" +
		"                           ),0) \n " + */
		"  \n" +

		"                     else  /* на пункте ИП материала нет - выберем сумму материалов из фактов*/   \n" +
		"                    \n" +

		/*	"    SUPP-40428  если хоз способ  то - освоение сумма учитывать материалы + бензин на всех фактах сделанных из месячного плана и сидящих в реконстр модернизации
		 *                  coalesce(  (select sum(coalesce(fin.cost,0)) as cost  \n" +
		"                     from enestimateitem eiplan , enestimateitem2nstmttm e2e  , enestimateitem2nstmttm e2e2  ,  enestimateitem eifact  \n" +
		"                     , rqbillitem2enestimattm bi2eiplan , rqbillitem bi ,  finmaterials fin , enplanwork pfact , enact2enplanwork a2p  \n" +
		"                     , enreconstrmodernoz2nct r2a , enreconstrmodernoz rm \n" +
		"                     where eiplan.planrefcode = sel_in1.planworkcode \n" +
		"                     and eiplan.code = e2e.estimateiteminrefcode  \n" +
		"                     and e2e.typerefcode = 1  \n" +
		"                     and e2e.estimateitemoutrefcode = e2e2.estimateiteminrefcode   \n" +
		"                     and e2e2.typerefcode = 1 \n" +
		"                     and e2e2.estimateitemoutrefcode = eifact.code \n" +
		"                     and eifact.countfact > 0 \n" +
		"                     and eiplan.code = bi2eiplan.estimateitemcode \n" +
		"                     and bi2eiplan.billitemcode = bi.code  \n" +
		"                     and bi.ddscodestxt = '312 100' \n" +
		"                     and eifact.code = fin.estimateitemrefcode \n" +
		"                     and fin.statusrefcode = 1 \n" +
		"                     and pfact.code = eifact.planrefcode  \n" +
		"                     and pfact.code = a2p.plancode  \n" +
		"                     and a2p.actrefcode = r2a.actrefcode \n" +
		"                     and pfact.statuscode = 3  \n" +
		"                     and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                     and rm.statusrefcode = 3 \n" +
		" and rm.dategen <= sel_in1.datereport  \n" + // 02.03.2015 не учитывалась дата среза репорта
		"                      \n" +
		"                     ) ,0) \n" +

		" -- сумма из промежут документов 2  \n" +
		"                           +  \n" +
		"                            \n" +
		"                     coalesce(  (select sum(coalesce(fin.cost,0)) as cost   \n" +
		"                      from enestimateitem eiplan , enestimateitem2nstmttm e2e  , enestimateitem2nstmttm e2e2  ,  enestimateitem eifact   \n" +
		"                      , rqbillitem2enestimattm bi2eiplan , rqbillitem bi ,  finmaterials fin , enplanwork pfact , enact2enplanwork a2p   \n" +
		"                      , enact a \n" +
		"                      where eiplan.planrefcode = sel_in1.planworkcode  \n" +
		"                      and eiplan.code = e2e.estimateiteminrefcode   \n" +
		"                      and e2e.typerefcode = 1   \n" +
		"                      and e2e.estimateitemoutrefcode = e2e2.estimateiteminrefcode    \n" +
		"                      and e2e2.typerefcode = 1  \n" +
		"                      and e2e2.estimateitemoutrefcode = eifact.code  \n" +
		"                      and eifact.countfact > 0  \n" +
		"                      and eiplan.code = bi2eiplan.estimateitemcode  \n" +
		"                      and bi2eiplan.billitemcode = bi.code   \n" +
		"                      and bi.ddscodestxt = '312 100'  \n" +
		"                      and eifact.code = fin.estimateitemrefcode  \n" +
		"                      and fin.statusrefcode = 1  \n" +
		"                      and pfact.code = eifact.planrefcode   \n" +
		"                      and pfact.code = a2p.plancode   \n" +
		"                      and pfact.statuscode = 3   \n" +
		"                      and a2p.actrefcode = a.code  \n" +
		" 				     and a.dategen <= sel_in1.datereport  \n" +
		" 				     and a.statusrefcode = 3  \n" +
		" 				     and not exists ( select enreconstrmodernoz2nct.code from enreconstrmodernoz2nct ,  enreconstrmodernoz   \n" +
		" 				                       where enreconstrmodernoz2nct.actrefcode = a2p.actrefcode   \n" +
		"                                          and enreconstrmodernoz2nct.enreconstrmodernozrfcd =  enreconstrmodernoz.code  \n" +
		" 				                         and enreconstrmodernoz.dategen <= sel_in1.datereport   \n" +
		" 				                         and enreconstrmodernoz.statusrefcode = 3  \n" +
		" 				                    )  \n" +
		"                        \n" +
		"                      ) ,0)  \n" +
		"  \n" + */

						" coalesce(( \n" +
						"                   select sum(coalesce(fin.cost, 0)) as cost \n" +
						"                   from enplancorrecthistory phf, \n" +
						"                        enestimateitem eifact, \n" +
						"                        finmaterials fin, \n" +
						"                        enact2enplanwork a2p, \n" +
						"                        enreconstrmodernoz2nct r2a, \n" +
						"                        enreconstrmodernoz rm, \n" +
						"                        enplanwork pfact \n" +
						"                   where phf.plannewrefcode = pfact.code and \n" +
						"                         phf.plannewrefcode = eifact.planrefcode and \n" +
						"                         eifact.kindrefcode in (1, 2) and \n" +
						"                         eifact.countfact > 0 and \n" +
						"                         eifact.code = fin.estimateitemrefcode and \n" +
						"                         fin.statusrefcode = 1 and \n" +
						"                         pfact.code = eifact.planrefcode and \n" +
						"                         pfact.code = a2p.plancode and \n" +
						"                         a2p.actrefcode = r2a.actrefcode and \n" +
						"                         pfact.statuscode = 3 and \n" +
						"                         r2a.enreconstrmodernozrfcd = rm.code and \n" +
						"                         rm.statusrefcode = 3 and \n" +
						"                         rm.dategen <= sel_in1.datereport \n" +
						"                         and \n" +
						"                         phf.reasoncode = 5 and \n" +
						"                         phf.planoldrefcode in ( \n" +
						"                                                 select ph.plannewrefcode \n" +
						"                                                 from enplancorrecthistory ph \n" +
						"                                                 where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
						"                                                       ph.reasoncode = 4 \n" +
						"                         ) \n" +
						"        ), 0)  \n" +
						"  \n" +
						"  +  \n" +
						" coalesce(( \n" +
						"                   select sum(coalesce(fin.cost, 0)) as cost \n" +
						"                   from enplancorrecthistory phf, \n" +
						"                        enestimateitem eifact, \n" +
						"                        finmaterials fin, \n" +
						"                        enact2enplanwork a2p, \n" +
						"                        enreconstrmodernoz2nct r2a, \n" +
						"                        enreconstrmodernoz rm, \n" +
						"                        enplanwork pfact ,  \n" +
						"                        enact a  \n" +
						"                   where phf.plannewrefcode = pfact.code and \n" +
						"                         phf.plannewrefcode = eifact.planrefcode and \n" +
						"                         eifact.kindrefcode in (1, 2) and \n" +
						"                         eifact.countfact > 0 and \n" +
						"                         eifact.code = fin.estimateitemrefcode and \n" +
						"                         fin.statusrefcode = 1 and \n" +
						"                         pfact.code = eifact.planrefcode and \n" +
						"                         pfact.code = a2p.plancode and \n" +
						"                         a2p.actrefcode = r2a.actrefcode and \n" +
						"                         pfact.statuscode = 3 and \n" +
						"                         a.dategen <= sel_in1.datereport  and  \n" +
						"                         a.statusrefcode = 3 and  \n" +
						"                         a.code = a2p.actrefcode and \n" +
						"                          not exists ( select enreconstrmodernoz2nct.code from enreconstrmodernoz2nct ,  enreconstrmodernoz  \n" +
						" 		 				                       where enreconstrmodernoz2nct.actrefcode = a2p.actrefcode   \n" +
						" 		                                          and enreconstrmodernoz2nct.enreconstrmodernozrfcd =  enreconstrmodernoz.code  \n" +
						" 		 				                         and enreconstrmodernoz.dategen <= sel_in1.datereport \n" +
						" 		 				                         and enreconstrmodernoz.statusrefcode = 3   \n" +
						" 		 				                    )      \n" +
						"                         and phf.reasoncode = 5 and \n" +
						"                         phf.planoldrefcode in ( \n" +
						"                                                 select ph.plannewrefcode \n" +
						"                                                 from enplancorrecthistory ph \n" +
						"                                                 where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
						"                                                       ph.reasoncode = 4 \n" +
						"                         ) \n" +
						"        ), 0)  \n" +
						"  \n" +



		"                end  \n" +
		"              /* освоение ОЗ , услуги со стороны сумма */ \n" +
		"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
		"                coalesce( (select sum(fi.sumwithoutnds) \n" +
		"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
		"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
		"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
		"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                   and hp.reasoncode = 4 \n" +
		"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
		"                   and hf.reasoncode = 5 \n" +
		"                   and hf.plannewrefcode = a2p.plancode \n" +
		"                   and a2p.actrefcode = r2a.actrefcode \n" +
		"                   and hf.plannewrefcode = p.code  \n" +
		"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
		"                   and rm.statusrefcode = 3  \n" +
		"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
		"                   and rpf.factcode = p.code  \n" +
		"                   and rpf.fkorderitemcode = fi.code  \n" +
		"                   and fi.fkorderrefcode = f.code \n" +
		"                   and f.kindcode = 14 \n" +
		"                   and f.statuscode in (3,4,5) \n" +
		"                   and p.statuscode  = 3  \n" +
		"                   and ipp.ipitemrefcode = ipitemcode \n" +
		"                  ),0) \n" +

//SUPP-107705 ЦУДОУУ, отчет о выполнении ИП-2021, ЗКУ 1 фазные (убрать нужно промежуточные , так как дублируются данные  )
		/*" -- сумма из промежут документов 3    \n" +
		"                +  \n" +
		"                  coalesce( (select sum(fi.sumwithoutnds)  \n" +
		"                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
		"                    , enplanwork p ,  rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f  \n" +
		"                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
		"                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
		"                    and hp.reasoncode = 4  \n" +
		"                    and hp.plannewrefcode = hf.planoldrefcode  \n" +
		"                    and hf.reasoncode = 5  \n" +
		"                    and hf.plannewrefcode = a2p.plancode  \n" +
		"                    and hf.plannewrefcode = p.code   \n" +
		"                    and rpf.factcode = p.code   \n" +
		"                    and rpf.fkorderitemcode = fi.code   \n" +
		"                    and fi.fkorderrefcode = f.code  \n" +
		"                    and f.kindcode = 14  \n" +
		"                    and f.dategen <= sel_in1.datereport  -- дата освоения материалов/ услуг    \n" +
		"                    and f.statuscode in (3,4,5)  \n" +
		"                    and p.statuscode  = 3   \n" +
		"                    and ipp.ipitemrefcode = ipitemcode \n" +
		"                    and not exists ( select r2a.actrefcode from enreconstrmodernoz2nct r2a , enreconstrmodernoz oz   \n" +
		" 				                                         where r2a.actrefcode = a2p.actrefcode \n" +
		" 				                                           and r2a.enreconstrmodernozrfcd = oz.code  \n" +
		" 				                                           and oz.statusrefcode = 3  \n" +
		" 				                                           and oz.dategen <= sel_in1.datereport -- дата освоения материалов/ услуг    \n" +
		" 				                                        )    \n" +
		"                   ),0)   \n" +*/
		"  \n" +


		"                /* освоение Акт вып работ , услуги со стороны сумма  */ \n" +
		"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
		"               coalesce( (select sum(fi.sumwithoutnds) \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
		"                      , rqfkorder f  \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and rpf.factcode = p.code  \n" +
		"                 and rpf.fkorderitemcode = fi.code  \n" +
		"                 and fi.fkorderrefcode = f.code \n" +
		"                 and f.kindcode = 14 \n" +
		"                 and f.statuscode in (3,4,5)  \n" +
		"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
		"                ),0)  \n" +
		"             /* освоение счетчики , сумма из sccounter - через план факт  */ \n" +
		"        /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
		"                0  пока непонятно как по счетчикам считать использование */ \n" +
		"                /*coalesce( ( \n" +
		"                 select  sum(scc.cost)   \n" +
		"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
		"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
		"                 where chp.reasoncode = 4 \n" +
		"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
		"                 and chpfact.reasoncode = 5 \n" +
		"                 and p.code = chpfact.plannewrefcode \n" +
		"                 and p.statuscode = 3 \n" +
		"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
		"                 and p.code = ei.planrefcode \n" +
		"                 and ei.accountingtyperefcode = 2 -- счетчики \n" +
		"                 and scc.estimateitemrefcode = ei.code  \n" +
		"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
		"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
		"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
		"                 and scusageinputitem.sccode is not null \n" +
		"                 and scusageinputitem.kindrefcode in (1 -- Введення у Експлуатацію \n" +
		"                                             ,4 -- Введення у експлуатацію ЗКУ \n" +
		"                                             ,5 -- Введення у експлуатацію  у складі ЗКУ \n" +
		"                                             )   \n" +
		"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
		"                 and scusageinput.statusrefcode = 3 -- ОЗ-1 проведений  \n" +
		"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
		"                  \n" +
		"                  \n" +
		"                ),0)  */        \n" +
		 // 21.07.2014 - добавил условия для типа освоения "приход" и по типу плана (зку 106 , 111 , 112)
			" when  ipimplementtyperefcode = 3 then /* сумма с прих ордера   */  \n" +
			"                    case when isUseMaterialOnIpItem > 0 then \n" +
			"                        coalesce( (select sum((coalesce(fi2ei.countgen,0) ) * fi.pricewithoutnds)  \n" +
			"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
			"                          ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and fi2ei.estimateitemcode = ei.code   \n" +
			"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"      \n" +
			"                          and fi.materialcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )                         \n" +
			"    \n" +
			"                           \n" +
			"                                         \n" +
			"                            ) ,0) \n" +
			"                           + /*излишки */  \n" +
			"                            \n" +
			"                           coalesce( (select sum((coalesce(rqfkorderitemremainder.countgen,0) ) * fi.pricewithoutnds)  \n" +
			"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
			"                          ,rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
			"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"                          and fi.materialcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )                         \n" +
			"    \n" +
			"                         ) ,0)   \n" +
			"                       else \n" +
			"                          coalesce( (select sum((coalesce(fi2ei.countgen,0) ) * fi.pricewithoutnds)  \n" +
			"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
			"                          ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and fi2ei.estimateitemcode = ei.code   \n" +
			"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"                            ) ,0) \n" +
			"                           + /*излишки */  \n" +
			"                            \n" +
			"                           coalesce( (select sum((coalesce(rqfkorderitemremainder.countgen,0) ) * fi.pricewithoutnds)  \n" +
			"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
			"                          ,rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
			"                           where p.kindcode = 2   \n" +
			"                           and bi2ei.billitemcode = bi.code   \n" +
			"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
			"                           and ei.kindrefcode in (1,6)   \n" +
			"                           and p.code = ei.planrefcode   \n" +
			"                           and ei.code = bi2ei.estimateitemcode               \n" +
			"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
			"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
			"                           and fi.fkorderrefcode = f.code   \n" +
			"                           and f.kindcode in (1)   \n" +
			"                           and f.statuscode in (3,4,5)   \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
			"                           and p.code = sel_in1.planworkcode   \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
			"                                                  where pi2bi.billitemcode = bi.code   \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
			"                                                     and pi.paydocrefcode = pay.code   \n" +
			"                                           )   \n" +
			"                         ) ,0) \n" +
			"                       end  \n" +
			"  \n" +
			
			"  /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" +
			"            when ipimplementtyperefcode = 4 then  \n" +
			"            /*выберем первоначальную стоимость счетчика который подвязан под пункт */  \n" +
			"          coalesce( (select ( select  co.cost From rqfkorder as fo \n" +
			"	inner join rqfkorderitem as fi on fi.fkorderrefcode = fo.code \n" +
			"	inner join scinvoice as inv on inv.fkorderitemrefcode = fi.code  \n" +
			"	inner join scorder as o on inv.code = o.invoicerefcode  \n" +
			"	inner join scorder2counter as oco on o.code = oco.scorderrefcode \n" +
			"	inner join sccounter as co on oco.counterrefcode = co.code \n" +
			"	where co.invnumber = sccounter.invnumber  \n" +
			"	order by fo.dategen asc \n" +
			"	limit 1 ) as cost_piobr  \n" +
			"                                from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
			"                                , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
			"                                where  hp.planoldrefcode = ipp.planrefcode   \n" +
			"                                and hp.planoldrefcode = sel_in1.planworkcode    \n" +
			"                                and hp.reasoncode = 4   \n" +
			"                                and hp.plannewrefcode = hf.planoldrefcode   \n" +
			"                                and hf.reasoncode = 5   \n" +
			"                                and hf.plannewrefcode = a2p.plancode   \n" +
			"                                and hf.plannewrefcode = p.code    \n" +
			"                                and enestimateitem.kindrefcode = 1 \n" + 
			"                                and p.statuscode  = 3   \n" +
			"                                and enestimateitem.planrefcode = p.code   \n" +
			"                                and enestimateitem.code = sccounter.estimateitemrefcode  \n" +
			"                                and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
			"                                and a2p.actrefcode = act.code  \n" +
			"                                and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                                /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
			"                               and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
			"                                act.dateact >= sel_in1.minDateOrderInvest      \n" +
			"                               else 1=1  \n" +
			"                               end \n" +
			"                                and ipp.ipitemrefcode = ipitemcode   \n" +
			"                             limit 1 ),0)   \n" +
			"                             + \n" +
			"             coalesce((  \n" +
			"                     select sum(coalesce(fin.cost, 0)) as cost  \n" +
			"                     from enplancorrecthistory phf,  \n" +
			"                          enestimateitem eifact,  \n" +
			"                          finmaterials fin,  \n" +
			"                          enact2enplanwork a2p,                          \n" +
			"                          enplanwork pfact ,  \n" +
			"                          enact aa  \n" +
			"                     where phf.plannewrefcode = pfact.code and  \n" +
			"                           phf.plannewrefcode = eifact.planrefcode and  \n" +
			"                           eifact.kindrefcode in (1, 2) and  \n" +
			"                           eifact.countfact > 0 and  \n" +
			"                           eifact.code = fin.estimateitemrefcode and  \n" +
			"                           fin.statusrefcode = 1 and  \n" +
			"                           pfact.code = eifact.planrefcode and  \n" +
			"                           pfact.code = a2p.plancode and                           \n" +
			"                           pfact.statuscode = 3 and  \n" +
			"                           aa.code =a2p.actrefcode and  \n" +
			"                           aa.dateact <= sel_in1.datereport  /*дата освоения материалов */  \n" +
			"                          /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
			"                           and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
			"                            aa.dateact >= sel_in1.minDateOrderInvest      \n" +
			"                           else 1=1  \n" +
			"                           end  \n" +
			"                           and phf.reasoncode = 5   \n" +
			"                           and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
			"                           and phf.planoldrefcode in (  \n" +
			"                                                   select ph.plannewrefcode  \n" +
			"                                                   from enplancorrecthistory ph  \n" +
			"                                                   where ph.planoldrefcode =  sel_in1.planworkcode and  \n" +
			"                                                         ph.reasoncode = 4  \n" +
			"                           )  \n" +
			"          ), 0)   \n" +
			"             /*zzzend*/ \n" +
			"                             /*освоение сумма для ipimplementtyperefcode и тип не инвестпрограмма и не услуги со стороны по ИП*/ \n" +
			"           /*zzzstart 100 */  \n" +
			"              when ipimplementtyperefcode = 1 and typerefcode not in (5,20) then   \n" +
			"              /*выберем первоначальную стоимость счетчика который подвязан под пункт */   \n" +
			"            coalesce( (select ( select  co.cost From rqfkorder as fo  \n" +
			"                  inner join rqfkorderitem as fi on fi.fkorderrefcode = fo.code  \n" +
			"                  inner join scinvoice as inv on inv.fkorderitemrefcode = fi.code   \n" +
			"                  inner join scorder as o on inv.code = o.invoicerefcode   \n" +
			"                  inner join scorder2counter as oco on o.code = oco.scorderrefcode  \n" +
			"                  inner join sccounter as co on oco.counterrefcode = co.code  \n" +
			"                  where co.invnumber = sccounter.invnumber   \n" +
			"                  order by fo.dategen asc  \n" +
			"                  limit 1 ) as cost_piobr   \n" +
			"                                  from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p   \n" +
			"                                  , enplanwork p ,  sccounter , enestimateitem , enact act  \n" +
			"                                  where  hp.planoldrefcode = ipp.planrefcode    \n" +
			"                                  and hp.planoldrefcode = sel_in1.planworkcode     \n" +
			"                                  and hp.reasoncode = 4    \n" +
			"                                  and hp.plannewrefcode = hf.planoldrefcode    \n" +
			"                                  and hf.reasoncode = 5    \n" +
			"                                  and hf.plannewrefcode = a2p.plancode    \n" +
			"                                  and hf.plannewrefcode = p.code     \n" +
			"                                  and enestimateitem.kindrefcode = 1  \n" +
			"                                  and p.statuscode  = 3    \n" +
			"                                  and enestimateitem.planrefcode = p.code    \n" +
			"                                  and enestimateitem.code = sccounter.estimateitemrefcode   \n" +
			"                                  and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode )  \n" +
			"                                  and a2p.actrefcode = act.code   \n" +
			"                                  and act.dateact <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                                  /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */  \n" +
			"                                 and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then   \n" +
			"                                  act.dateact >= sel_in1.minDateOrderInvest       \n" +
			"                                 else 1=1   \n" +
			"                                 end  \n" +
			"                                  and ipp.ipitemrefcode = ipitemcode    \n" +
			"                               limit 1 ),0)    \n" +
			"                               +  \n" +
			"               coalesce((   \n" +
			"                       select sum(coalesce(fin.cost, 0)) as cost   \n" +
			"                       from enplancorrecthistory phf,   \n" +
			"                            enestimateitem eifact,   \n" +
			"                            finmaterials fin,   \n" +
			"                            enact2enplanwork a2p,                           \n" +
			"                            enplanwork pfact ,   \n" +
			"                            enact aa   \n" +
			"                       where phf.plannewrefcode = pfact.code and   \n" +
			"                             phf.plannewrefcode = eifact.planrefcode and   \n" +
			"                             eifact.kindrefcode in (1, 2) and   \n" +
			"                             eifact.countfact > 0 and   \n" +
			"                             eifact.code = fin.estimateitemrefcode and   \n" +
			"                             fin.statusrefcode = 1 and   \n" +
			"                             pfact.code = eifact.planrefcode and   \n" +
			"                             pfact.code = a2p.plancode and                            \n" +
			"                             pfact.statuscode = 3 and   \n" +
			"                             aa.code =a2p.actrefcode and   \n" +
			"                             aa.dateact <= sel_in1.datereport  /*дата освоения материалов */   \n" +
			"                            /*условие только для раздела 2 пуункта 2 •    в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */  \n" +
			"                             and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then   \n" +
			"                              aa.dateact >= sel_in1.minDateOrderInvest       \n" +
			"                             else 1=1   \n" +
			"                             end   \n" +
			"                             and phf.reasoncode = 5    \n" +
			"                             and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode )  \n" +
			"                             and phf.planoldrefcode in (   \n" +
			"                                                     select ph.plannewrefcode   \n" +
			"                                                     from enplancorrecthistory ph   \n" +
			"                                                     where ph.planoldrefcode =  sel_in1.planworkcode and   \n" +
			"                                                           ph.reasoncode = 4   \n" +
			"                             )   \n" +
			"            ), 0)    \n" +
			"               /*zzzend 100 */ " + 
    		"   else 0                   \n" +
	    	"      \n" +
		    "   end     \n" +
		    " ,0) as osvoeno_summa_by_plan  \n" +
 		"  \n" +
		", yeargenip " +
		" from (  \n" +
		" select \n" +
		"   ipi.code as ipitemcode  \n" +
		"  ,p.code as planworkcode  \n" +
		"  ,case when p.typerefcode in (100, 106, 111, 112) then 1 else 0 end as isCounters  \n" +
		"  ,p.typerefcode \n" +
		"  ,coalesce(p.ipimplementtyperefcode,ipi.ipimplementtyperefcode) as ipimplementtyperefcode   \n" +
		"  ,case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials \n" +
		"              where enipitem2tkmaterials.ipitemrefcode = ipi.code  ) > 0    then 1 else 0 end as isUseMaterialOnIpItem \n" +
		"  , kvartal.rep_kvartal  \n" +
		"  ,datereport.datereport  \n" +
		"  , " + enipitemcode +" as enipitemcode             \n" +
		"  , ip.yeargen as yeargenip \n" +
		"    , ipi.invgrouprefcode \n" +
		"    , ipi.itemnumber \n" +
		"    , qOrder.minDateOrderInvest \n" +
		" from  \n" +
		"  enipitem2plan ipi2p \n" +
		" ,enplanwork p   \n" +
		" ,enipitem ipi \n" +
		" ,( select "+ kvartal +" as rep_kvartal ) as kvartal  \n" +
		" ,( select to_date('"+datereport+"','dd.mm.yyyy') as datereport ) as datereport \n" +
		" , enip ip  \n" +
		"  , ( select min(r.dategen) as minDateOrderInvest , " + enipitemcode +" as ipItemCode   from \n" +
		"                                 enestimateitem q1 , rqfkorderitem2enstmttm re , enplanwork e , rqfkorderitem ri , rqfkorder r  \n" +
		"                                 , rqorderitem2enestimttm re2 , rqorderitem r2  \n" +
		"                                 where q1.code = re.estimateitemcode  \n" +
		"                                 and q1.materialrefcode in (select t.code from tkmaterials t where t.name like '%ШАФА%ВВІДНО-ОБЛІКОВА%' or t.name like '%ШАФА%ВВІДНО-ОБДЛІКОВА%') \n" +
		"                                 and e.code = q1.planrefcode  \n" +
		"                                 and e.code in ( select ep.planrefcode from enipitem2plan ep where ep.ipitemrefcode=  " + enipitemcode +"    )  \n" +
		"                                 and re.fkorderitemrefcode =ri.code  \n" +
		"                                 and ri.fkorderrefcode = r.code  \n" +
		"                                 and re.estimateitemcode = re2.estimateitemcode  \n" +
		"                                 and re2.orderitemcode = r2.code  \n" +
		"                                 and r.kindcode in (1,15) \n" +
		"                                 and r2.ddscodescode = 180    /*ИНВЕСТПРОГРАММА*/ \n" +
		"                                 and to_char(r.dategen,'yyyy') =  ( select i.yeargen::text from  enipitem ii , enip i  where ii.iprefcode = i.code    and  ii.code in (" + itcodesandchildcodes + " )  limit 1  )  \n" +
		"                                  \n" +
		"        ) as qOrder  " + 
		" where ipi2p.planrefcode = p.code  \n" +
		" and p.kindcode = 2 \n" +
		" and ipi2p.ipitemrefcode = ipi.code \n" +
		" and ipi.iprefcode = ip.code  \n" +		 
		" and ipi.code in (" + itcodesandchildcodes + " ) \n" +
		" and qOrder.ipItemCode = ipi.code \n" + 
		" and case when ipi.code = 4051 then p.code not in (select planworkcode  from _invprogr_except_plan where itemcode =  4051) else 1 = 1 end \n" +
		" and case when ipi.code = 4052 then p.code not in (select planworkcode  from _invprogr_except_plan where itemcode =  4052) else 1 = 1 end \n" +
		"  \n" +
		"  \n" +
		" ) as sel_in1 \n" +
		"  \n" +
		" ) as sel_in2 \n" +
		"  \n" +
		" ) as sel_in3 \n" +
		"  \n" +
		" group by enipitemcode, /* параметр парент пункта ип */ \n" +
		" datereport , countgen , sumgen , price , infotenders \n" +
		"  \n" ;

		
		tempSt = new LoggableStatement(connection, selStr1 + selStr2+ selStr3+ selStr4+ selStr5);
        System.out.println(selStr1 + selStr2 + selStr3 + selStr4 + selStr5);
        
        System.out.println("############### ip_sql "
     	        + "Executing query: " + ((LoggableStatement)tempSt).getQueryString());
    	        
        rs = tempSt.executeQuery();
        
        
         

	    ArrayList rows  = new ArrayList();
	    int count = 0;

	    while(rs.next())
        {
	    	count = count+1;

	    	BigDecimal profinans_count = new BigDecimal(0);
	    	BigDecimal profinans_sum = new BigDecimal(0);
            BigDecimal profinans_price = new BigDecimal(0);

            BigDecimal osvoeno_count = new BigDecimal(0);
            BigDecimal osvoeno_sum = new BigDecimal(0);

            profinans_count = rs.getBigDecimal(3);
            profinans_sum = rs.getBigDecimal(4);

            osvoeno_count = rs.getBigDecimal(6);
            osvoeno_sum = rs.getBigDecimal(7);




            String osvoeno_docs = "";
            // SUPP-107705 дублируются документы ((( osvoeno_docs = getDocsOsvoenie( enipitemcode , itcodesandchildcodes , datereport , kvartal );
            osvoeno_docs = getDocsOsvoenieWithoutIntervalDoc( enipitemcode , itcodesandchildcodes , datereport , kvartal ); 
            if(enipitemcode==4065) {
            	osvoeno_docs = osvoeno_docs + " Акт вып.работ Sm21-067 16.09.2021, Акт вып.работ Sm21-104 02.12.2021";
            }


            /*if (profinans_count != null && profinans_count.doubleValue() > 0 && profinans_sum != null && profinans_sum.doubleValue() > 0 ){
            	profinans_price = profinans_sum.divide(profinans_count).setScale(2, BigDecimal.ROUND_HALF_UP) ;
            }*/

            if (osvoeno_count != null && osvoeno_count.doubleValue() > 0 && osvoeno_sum != null && osvoeno_sum.doubleValue() > 0 ){
            	profinans_price = osvoeno_sum.divide(osvoeno_count,2);
            	profinans_price = profinans_price.setScale(2, BigDecimal.ROUND_HALF_UP) ;
            }





	                HashMap hashMap = new HashMap();
	                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_PRICE, profinans_price);
	                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_COUNT, profinans_count);
	                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_SUM, profinans_sum);
	                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_COUNT, rs.getBigDecimal(6));

	                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_SUM, rs.getBigDecimal(7));
	               // hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_DOCS, rs.getString(5));
	                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_DOCS, osvoeno_docs);
	                hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_COUNT, rs.getBigDecimal(8));
	                hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_SUM, rs.getBigDecimal(9));
	                hashMap.put(dsFinansirOsvoenieBalans.PERCENT, rs.getBigDecimal(10));
	                hashMap.put(dsFinansirOsvoenieBalans.CONTRAGENT, rs.getString(12));
	                hashMap.put(dsFinansirOsvoenieBalans.INFOTENDERS, rs.getString(11));


	             // --- FIRST DATA COLUMN
	                hashMap.put(dsFinansirOsvoenieBalans.ITEMNUMBER , itemnumber);
	                hashMap.put(dsFinansirOsvoenieBalans.ENIPPURPOSEPROGRAM ,enippurposeprogram);
	                hashMap.put(dsFinansirOsvoenieBalans.NAME_PRODUCT ,name_product);
	                hashMap.put(dsFinansirOsvoenieBalans.MEASUREMENTNAME ,measurementname);
	                hashMap.put(dsFinansirOsvoenieBalans.FINANCING ,financing);
	                hashMap.put(dsFinansirOsvoenieBalans.PRICE , price);
	                hashMap.put(dsFinansirOsvoenieBalans.COUNTGEN ,countgen);
	                hashMap.put(dsFinansirOsvoenieBalans.SUMGEN ,sumgen);
	                hashMap.put(dsFinansirOsvoenieBalans.PERIOD_COUNT ,period_count);
	                hashMap.put(dsFinansirOsvoenieBalans.PERIOD_SUM , period_sum);


	                rows.add(hashMap);

        }


	    if (count == 0 ) {


	    	ENIPItem ipiObj = ipiDAO.getObject(enipitemcode);

	    	HashMap hashMap = new HashMap();
            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_PRICE, new BigDecimal(0));
            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_COUNT, new BigDecimal(0));
            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_SUM, new BigDecimal(0));
            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_COUNT, new BigDecimal(0));

            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_SUM, new BigDecimal(0));
            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_DOCS, "")  ;
            hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_COUNT, ipiObj.countGen);
            hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_SUM, ipiObj.sumGen);
            hashMap.put(dsFinansirOsvoenieBalans.PERCENT, null);
            hashMap.put(dsFinansirOsvoenieBalans.CONTRAGENT, "");
            hashMap.put(dsFinansirOsvoenieBalans.INFOTENDERS, infotenders);

            // --- FIRST DATA COLUMN
            hashMap.put(dsFinansirOsvoenieBalans.ITEMNUMBER , itemnumber);
            hashMap.put(dsFinansirOsvoenieBalans.ENIPPURPOSEPROGRAM ,enippurposeprogram);
            hashMap.put(dsFinansirOsvoenieBalans.NAME_PRODUCT ,name_product);
            hashMap.put(dsFinansirOsvoenieBalans.MEASUREMENTNAME ,measurementname);
            hashMap.put(dsFinansirOsvoenieBalans.FINANCING ,financing);
            hashMap.put(dsFinansirOsvoenieBalans.PRICE , price);
            hashMap.put(dsFinansirOsvoenieBalans.COUNTGEN ,countgen);
            hashMap.put(dsFinansirOsvoenieBalans.SUMGEN ,sumgen);
            hashMap.put(dsFinansirOsvoenieBalans.PERIOD_COUNT ,period_count);
            hashMap.put(dsFinansirOsvoenieBalans.PERIOD_SUM , period_sum);


            rows.add(hashMap);
	    }



	    return new dsFinansirOsvoenieBalans(rows.toArray());

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



  /*
   * Документы по освоению по пунктам ИП
   * */
   public String getDocsOsvoenie( int enipitemcode , String itcodesandchildcodes , String datereport , int kvartal )   throws PersistenceException
   {
   boolean isDebug = false ;

   JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
   Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
   UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


   String sql1 =
		   " select string_agg(distinct( osvoeno_docs )  , ',' )  \n" +
				   " from  \n" +
				   " ( \n" +
				   " select       /* освоение ОЗ , ХОЗ способ */  \n" +
				   "   case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then   \n" +
				   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */   \n" +
				   "                         (select string_agg(distinct('ОЗ-1№ '||f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
				   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
				   "                          where p.kindcode = 2  \n" +
				   "                          and bi2ei.billitemcode = bi.code  \n" +
				   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
				   "                          and ei.kindrefcode in (1,6)  \n" +
				   "                          and p.code = ei.planrefcode  \n" +
				   "                          and ei.code = bi2ei.estimateitemcode              \n" +
				   "                          and fi2ei.estimateitemcode = ei.code  \n" +
				   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
				   "                          and fi.fkorderrefcode = f.code  \n" +
				   "                          and f.kindcode in (8,9,11,12,21)  \n" +
				   "                          and f.statuscode in (3,4,5)  \n" +
				   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
				   "                          and p.code = sel_in1.planworkcode  \n" +
				   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
				   "                                                 where pi2bi.billitemcode = bi.code  \n" +
				   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
				   "                                                    and pi.paydocrefcode = pay.code  \n" +
				   "                                          )  \n" +
				   "    \n" +
				   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
				   "                                       \n" +
				   "                           )  \n" +
				   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, значит акты  ОЗ-2 */  \n" +
				   "                            (select string_agg(distinct('ОЗ-2 '||rm.numbergen||' від '||to_char(rm.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a   \n" +
				   "                              , enplanwork p , enreconstrmodernoz rm  \n" +
				   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
				   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                              and hp.reasoncode = 4  \n" +
				   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
				   "                              and hf.reasoncode = 5  \n" +
				   "                              and hf.plannewrefcode = a2p.plancode  \n" +
				   "                              and a2p.actrefcode = r2a.actrefcode  \n" +
				   "                              and hf.plannewrefcode = p.code   \n" +
				   "                              and r2a.enreconstrmodernozrfcd = rm.code   \n" +
				   "                              and rm.statusrefcode = 3   \n" +
				   "                              and p.statuscode  = 3  \n" +
				   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
				   "                              and rm.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
				   "                           )  \n" +
				   "                 end  \n" +
				   "              /* освоение ОЗ , услуги со стороны */  \n" +
				   "           when  ipimplementtyperefcode = 1 and  typerefcode = 20 then    \n" +
				   "                 (select 'ОЗ-2№ '|| string_agg(distinct(rm.numbergen||' від '||to_char(rm.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a   \n" +
				   "                    , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f  \n" +
				   "                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
				   "                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                    and hp.reasoncode = 4  \n" +
				   "                    and hp.plannewrefcode = hf.planoldrefcode  \n" +
				   "                    and hf.reasoncode = 5  \n" +
				   "                    and hf.plannewrefcode = a2p.plancode  \n" +
				   "                    and a2p.actrefcode = r2a.actrefcode  \n" +
				   "                    and hf.plannewrefcode = p.code   \n" +
				   "                    and r2a.enreconstrmodernozrfcd = rm.code   \n" +
				   "                    and rm.statusrefcode = 3   \n" +
				   "                    and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */  \n" +
				   "                    and rpf.factcode = p.code   \n" +
				   "                    and rpf.fkorderitemcode = fi.code   \n" +
				   "                    and fi.fkorderrefcode = f.code  \n" +
				   "                    and f.kindcode = 14  \n" +
				   "                    and f.statuscode in (3,4,5)  \n" +
				   "                    and p.statuscode  = 3   \n" +
				   "                    and ipp.ipitemrefcode = ipitemcode  \n" +
				   "                     \n" +
				   "                     \n" +
				   "                   )  \n" +
				   "             /* освоение Акт вып работ , услуги со стороны */  \n" +
				   "           when  ipimplementtyperefcode = 2 and  typerefcode = 20 then  \n" +
				   "                 (select string_agg(distinct(f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                  from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi  \n" +
				   "                       , rqfkorder f   \n" +
				   "                  where chp.reasoncode = 4  \n" +
				   "                  and chp.plannewrefcode = chpfact.planoldrefcode  \n" +
				   "                  and chpfact.reasoncode = 5  \n" +
				   "                  and p.code = chpfact.plannewrefcode  \n" +
				   "                  and p.statuscode = 3  \n" +
				   "                  and chp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                  and rpf.factcode = p.code   \n" +
				   "                  and rpf.fkorderitemcode = fi.code   \n" +
				   "                  and fi.fkorderrefcode = f.code  \n" +
				   "                  and f.kindcode = 14  \n" +
				   "                  and f.statuscode in (3,4,5)   \n" +
				   "                  and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
				   "                 )   \n" +
				   "             /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */  \n" +
				   "         /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then  \n" +
				   "                  ''  пока непонятно как по счетчикам считать использование*/   \n" +
				   "                  /*(  \n" +
				   "                   select string_agg(distinct(scusageinputitemoz.numberdoc) ,',')  \n" +
				   "                  from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei   \n" +
				   "                      , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput  \n" +
				   "                  where chp.reasoncode = 4  \n" +
				   "                  and chp.plannewrefcode = chpfact.planoldrefcode  \n" +
				   "                  and chpfact.reasoncode = 5  \n" +
				   "                  and p.code = chpfact.plannewrefcode  \n" +
				   "                  and p.statuscode = 3  \n" +
				   "                  and chp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                  and p.code = ei.planrefcode  \n" +
				   "                  and ei.accountingtyperefcode = 2   \n" +
				   "                  and scc.estimateitemrefcode = ei.code   \n" +
				   "                  and scusageinputtmz2sccntr.sccounterrefcode = scc.code  \n" +
				   "                  and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode  \n" +
				   "                  and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code   \n" +
				   "                  and scusageinputitem.sccode is not null  \n" +
				   "                  and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію  \n" +
				   "                                              ,4  -- Введення у експлуатацію ЗКУ  \n" +
				   "                                              ,5  -- Введення у експлуатацію  у складі ЗКУ  \n" +
				   "                                              )    \n" +
				   "                  and scusageinputitem.usageinputrefcode = scusageinput.code   \n" +
				   "                  and scusageinput.statusrefcode = 3 --ОЗ-1 проведений   \n" +
				   "                  and scusageinput.dategen <= sel_in1.datereport   \n" +
				   "                    \n" +
				   "                    \n" +
				   "                 )*/  \n" +
				   "             \n" +
				   "             \n" +
				   "              \n" +
					  " /* освоение по приходным ордерам */ \n" +
					   "        when  ipimplementtyperefcode = 3 then   \n" +
					   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в Приходах */   \n" +
					   "                         (select 'Орд№' || string_agg(distinct(f.numberdoc) ,',')  \n" +
					   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
					   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
					   "                          where p.kindcode = 2  \n" +
					   "                          and bi2ei.billitemcode = bi.code  \n" +
					   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
					   "                          and ei.kindrefcode in (1,6)  \n" +
					   "                          and p.code = ei.planrefcode  \n" +
					   "                          and ei.code = bi2ei.estimateitemcode              \n" +
					   "                          and fi2ei.estimateitemcode = ei.code  \n" +
					   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
					   "                          and fi.fkorderrefcode = f.code  \n" +
					   "                          and f.kindcode in (1)  \n" +
					   "                          and f.statuscode in (3,4,5)  \n" +
					   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
					   "                          and p.code = sel_in1.planworkcode  \n" +
					   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
					   "                                                 where pi2bi.billitemcode = bi.code  \n" +
					   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
					   "                                                    and pi.paydocrefcode = pay.code  \n" +
					   "                                          )  \n" +
					   "    \n" +
					   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
					   "                                       \n" +
					   "                           )  \n" +
					   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, документы по всем приходным ордерам материалов с плана */  \n" +
					   "                         (select 'Орд№' || string_agg(distinct(f.numberdoc) ,',')  \n" +
					   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
					   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
					   "                          where p.kindcode = 2  \n" +
					   "                          and bi2ei.billitemcode = bi.code  \n" +
					   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
					   "                          and ei.kindrefcode in (1,6)  \n" +
					   "                          and p.code = ei.planrefcode  \n" +
					   "                          and ei.code = bi2ei.estimateitemcode              \n" +
					   "                          and fi2ei.estimateitemcode = ei.code  \n" +
					   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
					   "                          and fi.fkorderrefcode = f.code  \n" +
					   "                          and f.kindcode in (1)  \n" +
					   "                          and f.statuscode in (3,4,5)  \n" +
					   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
					   "                          and p.code = sel_in1.planworkcode  \n" +
					   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
					   "                                                 where pi2bi.billitemcode = bi.code  \n" +
					   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
					   "                                                    and pi.paydocrefcode = pay.code  \n" +
					   "                                          )  \n" +
					   "                           )  \n" +
					   "                 end   \n" +
					   "  \n" +
					   " /*освоение документы  zku */  \n" +
					   "        when typerefcode in ( 106, 111, 112) then  \n" +
					   "         (select 'ОЗ №' ||string_agg(distinct( scusageinputitemoz.numberdoc),',')  \n" +
					   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
					   "                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput                               \n" +
					   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
					   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
					   "                              and hp.reasoncode = 4  \n" +
					   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
					   "                              and hf.reasoncode = 5  \n" +
					   "                              and hf.plannewrefcode = a2p.plancode  \n" +
					   "                              and hf.plannewrefcode = p.code   \n" +
					   "                              and p.statuscode  = 3  \n" +
					   "                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
					   "                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
					   "                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
					   "                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
					   "                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
					   " /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" + 
	                   " /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
	                   "  scusageinput.dategen >= sel_in1.minDateOrderInvest    \n" +   
	                   " else 1=1  \n" + 
	                   " end /*new*/  \n" +
					   "                              and scusageinput.statusrefcode = 3                                \n" +
					   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
					   "                           ) \n" +
					   "  \n"  +
					   "   /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" + 
					   "   when ipimplementtyperefcode = 4 then  \n" +
					   "     /*выберем документы  */  \n" + 
					   "  (select string_agg(distinct actnum ,',') from ( select  \n" +
					   "    distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
					   "                         from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
					   "                         , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
					   "                         where  hp.planoldrefcode = ipp.planrefcode   \n" +
					   "                         and hp.planoldrefcode = sel_in1.planworkcode    \n" +
					   "                         and hp.reasoncode = 4   \n" +
					   "                         and hp.plannewrefcode = hf.planoldrefcode   \n" +
					   "                         and hf.reasoncode = 5   \n" +
					   "                         and hf.plannewrefcode = a2p.plancode  \n" + 
					   "                         and hf.plannewrefcode = p.code   \n" +
					   "                         and p.statuscode  = 3  \n" +
					   "                         and enestimateitem.planrefcode = p.code  \n" +
					   "                         and enestimateitem.code = sccounter.estimateitemrefcode \n" +
					   "                         and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
					   "                         and a2p.actrefcode = act.code \n" +
					   "                         and enestimateitem.kindrefcode in (1,2) \n" +
					   "                         and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
					   "                         /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
					   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
					   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +    
					   "                        else 1=1 \n" +
					   "                        end \n" +
					   "                         and ipp.ipitemrefcode = ipitemcode   \n" +
					   "                                union  \n" +
					   "              select distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
					   "              from enplancorrecthistory phf,  \n" +
					   "                   enestimateitem eifact,  \n" +
					   "                  finmaterials fin, \n" +
					   "                 enact2enplanwork a2p,        \n" +                  
					   "                 enplanwork pfact , \n" +
					   "                 enact act  \n" +
					   "            where phf.plannewrefcode = pfact.code and  \n" +
					   "                  phf.plannewrefcode = eifact.planrefcode and  \n" +
					   "                  eifact.kindrefcode in (1, 2) and \n" +
					   "                  eifact.countfact > 0 and \n" +
					   "                  eifact.code = fin.estimateitemrefcode and \n" +
					   "                  fin.statusrefcode = 1 and \n" +
					   "                  pfact.code = eifact.planrefcode and  \n" +
					   "                  pfact.code = a2p.plancode and    \n" +                      
					   "                  pfact.statuscode = 3 and \n" +
					   "                   act.code =a2p.actrefcode and \n" +
					   "                    act.dateact <= sel_in1.datereport  /*дата освоения материалов */ \n" +
					   "                    /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
					   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
					   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +   
					   "                        else 1=1 \n" +
					   "                        end \n" +
					   "                    and phf.reasoncode = 5  \n" +
					   "                    and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
					   "                    and phf.planoldrefcode in ( \n" +
					   "                                            select ph.plannewrefcode \n" +
					   "                                            from enplancorrecthistory ph \n" +
					   "                                            where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
					   "                                                  ph.reasoncode = 4 \n" +
					   "                    ) \n" +
				       "  ) as q1 ) \n" +
				       "      /*zzzend*/     \n" +
				   "        else ''   \n" +
				   "   end           \n" +
				   "       \n" +
				   "   as osvoeno_docs    \n" +
				   "             \n" +
				   " from (   \n" +
				   "  select  \n" +
				   "    ipi.code as ipitemcode   \n" +
				   "   ,p.code as planworkcode   \n" +
				   "   ,case when p.typerefcode in (100, 106, 111, 112) then 1 else 0 end as isCounters   \n" +
				   "   ,p.typerefcode  \n" +
				   "   ,coalesce(p.ipimplementtyperefcode,ipi.ipimplementtyperefcode) as ipimplementtyperefcode    \n" +
				   "   ,case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials  \n" +
				   "               where enipitem2tkmaterials.ipitemrefcode = ipi.code  ) > 0    then 1 else 0 end as isUseMaterialOnIpItem  \n" +
				   "   , kvartal.rep_kvartal   \n" +
				   "   ,datereport.datereport   \n" +
				   "   , " + enipitemcode +" as enipitemcode              \n" +
				   "   , ip.yeargen as yeargenip  \n" + 
				   "    , ipi.itemnumber \n" +
					"    , qOrder.minDateOrderInvest \n" +
				   "  from   \n" +
				   "   enipitem2plan ipi2p  \n" +
				   "  ,enplanwork p    \n" +
				   "  ,enipitem ipi  \n" +
				   "  ,( select "+ kvartal +" as rep_kvartal ) as kvartal   \n" +
				   "  ,( select to_date('"+datereport+"','dd.mm.yyyy') as datereport ) as datereport  \n" +
				   "  , enip ip   \n" +
				   "  , ( select min(r.dategen) as minDateOrderInvest , " + enipitemcode +" as ipItemCode   from  \n" + 
					"         enestimateitem q1 , rqfkorderitem2enstmttm re , enplanwork e , rqfkorderitem ri , rqfkorder r  \n" + 
					"         , rqorderitem2enestimttm re2 , rqorderitem r2  \n" + 
					"         where q1.code = re.estimateitemcode  \n" + 
					"         and q1.materialrefcode in (select t.code from tkmaterials t where t.name like '%ШАФА%ВВІДНО-ОБЛІКОВА%' or t.name like '%ШАФА%ВВІДНО-ОБДЛІКОВА%') \n" +  
					"         and e.code = q1.planrefcode \n" +  
					"         and e.code in ( select ep.planrefcode from enipitem2plan ep where ep.ipitemrefcode=  " + enipitemcode +"    ) \n" +
					"         and re.fkorderitemrefcode =ri.code \n" +
					"         and ri.fkorderrefcode = r.code \n" +
					"         and re.estimateitemcode = re2.estimateitemcode \n" +
					"         and re2.orderitemcode = r2.code \n" +
					"          and r.kindcode in (1,15) \n" +
					"         and r2.ddscodescode = 180	/*ИНВЕСТПРОГРАММА*/ \n" + 
					"         and to_char(r.dategen,'yyyy') =  ( select i.yeargen::text from  enipitem ii , enip i  where ii.iprefcode = i.code    and  ii.code in (" + itcodesandchildcodes + " )  limit 1  ) \n" + 
					"  ) as qOrder \n" + 
				   "  where ipi2p.planrefcode = p.code   \n" +
				   "  and p.kindcode = 2  \n" +
				   "  and ipi2p.ipitemrefcode = ipi.code  \n" +
				   "  and ipi.iprefcode = ip.code   \n" +
				   "  and ipi.code in (" + itcodesandchildcodes + " )  \n" +
				   "  and qOrder.ipItemCode = ipi.code  \n" +
				   "    \n" +
				   "    \n" +
				   "  ) as sel_in1 \n" +
				   "   \n" +
				   "   \n" ;
				   String sql2 =  "  /* КЛЕИМ ПРОМЕЖУТОЧНЫЕ АКТЫ , НАКЛАДНЫЕ*/ \n" +
				   "  UNION ALL  \n" +
				   "   \n" +
				   "  select       /* освоение ОЗ , ХОЗ способ */  \n" +
				   "   case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then   \n" +
				   " /* на пункте ип указаны материалы которые искать в приходе накладных , материалы не введены  ОЗ-шками  */   \n" +
				   "                 case when isUseMaterialOnIpItem > 0 then  \n" +
				   "                         (select string_agg(distinct('Накл.№ '||f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
				   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
				   "                          where p.kindcode = 2  \n" +
				   "                          and bi2ei.billitemcode = bi.code  \n" +
				   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
				   "                          and ei.kindrefcode in (1,6)  \n" +
				   "                          and p.code = ei.planrefcode  \n" +
				   "                          and ei.code = bi2ei.estimateitemcode              \n" +
				   "                          and fi2ei.estimateitemcode = ei.code  \n" +
				   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
				   "                          and fi.fkorderrefcode = f.code  \n" +
				   "                          and f.kindcode = 1  \n" +
				   "                          and f.statuscode in (3,4,5)  \n" +
				   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
				   "                          and p.code = sel_in1.planworkcode  \n" +
				   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
				   "                                                 where pi2bi.billitemcode = bi.code  \n" +
				   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
				   "                                                    and pi.paydocrefcode = pay.code  \n" +
				   "                                          )  \n" +
				   "    \n" +
				   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
				   "                           /*что бы естимейт был не введен в эксплуатацию */  \n" +
				   "                          and not exists ( select rqfkorderitem2enstmttm.estimateitemcode from  rqfkorderitem2enstmttm  , rqfkorderitem  , rqfkorder  \n" +
				   "                                            where rqfkorderitem2enstmttm.estimateitemcode = ei.code  \n" +
				   "                                            and rqfkorderitem2enstmttm.fkorderitemrefcode = rqfkorderitem.code  \n" +
				   "                                            and rqfkorderitem.fkorderrefcode = rqfkorder.code \n" +
				   "                                            and rqfkorder.kindcode in (8,9,11,12,21) \n" +
				   "                                            and rqfkorder.statuscode in (3,4,5) \n" +
				   "                                            and rqfkorder.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
				   "                                         )            \n" +
				   "                           ) \n" +
				   "                           \n" +
				   "                             \n" +
				   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, кол-во из фактов которые сидят в актах а акты БЕЗ ОЗ-2 */  \n" +
				   "                            (select string_agg(distinct('Акт№ '|| a.numbergen||' від '||to_char(a.dategen ,'dd.mm.yyyy')) ,',')  \n" +
				   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p                                  \n" +
				   "                              , enplanwork p , enact a \n" +
				   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
				   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                              and hp.reasoncode = 4  \n" +
				   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
				   "                              and hf.reasoncode = 5  \n" +
				   "                              and hf.plannewrefcode = a2p.plancode  \n" +
				   "                              and hf.plannewrefcode = p.code   \n" +
				   "                              and p.statuscode  = 3  \n" +
				   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
				   "                              and a2p.actrefcode = a.code  \n" +
				   "                              and a.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
				   "                              and a.statusrefcode = 3 \n" +
				   "                               \n" +
				   "                              and not exists ( select enreconstrmodernoz2nct.code from enreconstrmodernoz2nct ,  enreconstrmodernoz  \n" +
				   "                                                where enreconstrmodernoz2nct.actrefcode = a2p.actrefcode  \n" +
				   "                                                  and enreconstrmodernoz2nct.enreconstrmodernozrfcd =  enreconstrmodernoz.code \n" +
				   "                                                  and enreconstrmodernoz.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
				   "                                                  and enreconstrmodernoz.statusrefcode = 3 \n" +
				   "                                               ) \n" +
				   "                           )  \n" +
				   "                 end  \n" +
				   "              /* если  освоение ОЗ , услуги со стороны */  \n" +
				   "           when  ipimplementtyperefcode = 1 and  typerefcode = 20 then    \n" +
				   "                 ( select string_agg(distinct('Акт№ '||f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')),',') as numbergen \n" +
				   "                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
				   "                    , enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f  \n" +
				   "                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
				   "                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                    and hp.reasoncode = 4  \n" +
				   "                    and hp.plannewrefcode = hf.planoldrefcode  \n" +
				   "                    and hf.reasoncode = 5  \n" +
				   "                    and hf.plannewrefcode = a2p.plancode  \n" +
				   "                    and hf.plannewrefcode = p.code   \n" +
				   "                    and f.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */  \n" +
				   "                    and rpf.factcode = p.code   \n" +
				   "                    and rpf.fkorderitemcode = fi.code   \n" +
				   "                    and fi.fkorderrefcode = f.code  \n" +
				   "                    and f.kindcode = 14  \n" +
				   "                    and f.statuscode in (3,4,5)  \n" +
				   "                    and p.statuscode  = 3   \n" +
				   "                    and ipp.ipitemrefcode = sel_in1.ipitemcode  \n" +
				   "                    and not exists ( select r2a.actrefcode from enreconstrmodernoz2nct r2a , enreconstrmodernoz oz  \n" +
				   "                                      where r2a.actrefcode = a2p.actrefcode \n" +
				   "                                        and r2a.enreconstrmodernozrfcd = oz.code \n" +
				   "                                        and oz.statusrefcode = 3 \n" +
				   "                                        and oz.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */  \n" +
				   "                                     )   \n" +
				   "                     \n" +
				   "                     \n" +
				   "                   )  \n" +
				   "          \n" ;
				 String sql3 =  "             /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */  \n" +
				   "           /* when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then  \n" +
				   "                  '' пока непонятно как по счетчикам считать использование*/   \n" +
				   "                  /*(  \n" +
				   "                   select string_agg(distinct(scusageinputitemoz.numberdoc) ,',')  \n" +
				   "                  from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei   \n" +
				   "                      , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput  \n" +
				   "                  where chp.reasoncode = 4  \n" +
				   "                  and chp.plannewrefcode = chpfact.planoldrefcode  \n" +
				   "                  and chpfact.reasoncode = 5  \n" +
				   "                  and p.code = chpfact.plannewrefcode  \n" +
				   "                  and p.statuscode = 3  \n" +
				   "                  and chp.planoldrefcode = sel_in1.planworkcode   \n" +
				   "                  and p.code = ei.planrefcode  \n" +
				   "                  and ei.accountingtyperefcode = 2   \n" +
				   "                  and scc.estimateitemrefcode = ei.code   \n" +
				   "                  and scusageinputtmz2sccntr.sccounterrefcode = scc.code  \n" +
				   "                  and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode  \n" +
				   "                  and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code   \n" +
				   "                  and scusageinputitem.sccode is not null  \n" +
				   "                  and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію  \n" +
				   "                                              ,4  -- Введення у експлуатацію ЗКУ  \n" +
				   "                                              ,5  -- Введення у експлуатацію  у складі ЗКУ  \n" +
				   "                                              )    \n" +
				   "                  and scusageinputitem.usageinputrefcode = scusageinput.code   \n" +
				   "                  and scusageinput.statusrefcode = 3 --ОЗ-1 проведений   \n" +
				   "                  and scusageinput.dategen <= sel_in1.datereport   \n" +
				   "                    \n" +
				   "                    \n" +
				   "                 )*/  \n" +

				   "        else ''   \n" +
				   "   end           \n" +
				   "       \n" +
				   "   as osvoeno_docs    \n" +
				   "             \n" +
				   " from (   \n" +
				   "  select  \n" +
				   "    ipi.code as ipitemcode   \n" +
				   "   ,p.code as planworkcode   \n" +
				   "   ,case when p.typerefcode in (100, 106, 111, 112) then 1 else 0 end as isCounters   \n" +
				   "   ,p.typerefcode  \n" +
				   "   ,coalesce(p.ipimplementtyperefcode,ipi.ipimplementtyperefcode) as ipimplementtyperefcode    \n" +
				   "   ,case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials  \n" +
				   "               where enipitem2tkmaterials.ipitemrefcode = ipi.code  ) > 0    then 1 else 0 end as isUseMaterialOnIpItem  \n" +
				   "   , kvartal.rep_kvartal   \n" +
				   "   ,datereport.datereport   \n" +
				   "   , " + enipitemcode +" as enipitemcode              \n" +
				   "   , ip.yeargen as yeargenip  \n" +
				   "    , ipi.itemnumber \n" +
					"    , qOrder.minDateOrderInvest \n" +
				   "  from   \n" +
				   "   enipitem2plan ipi2p  \n" +
				   "  ,enplanwork p    \n" +
				   "  ,enipitem ipi  \n" +
				   "  ,( select "+ kvartal +" as rep_kvartal ) as kvartal   \n" +
				   "  ,( select to_date('"+datereport+"','dd.mm.yyyy') as datereport ) as datereport  \n" +
				   "  , enip ip   \n" +
					"  , ( select min(r.dategen) as minDateOrderInvest , " + enipitemcode +" as ipItemCode   from  \n" + 
					"         enestimateitem q1 , rqfkorderitem2enstmttm re , enplanwork e , rqfkorderitem ri , rqfkorder r  \n" + 
					"         , rqorderitem2enestimttm re2 , rqorderitem r2  \n" + 
					"         where q1.code = re.estimateitemcode  \n" + 
					"         and q1.materialrefcode in (select t.code from tkmaterials t where t.name like '%ШАФА%ВВІДНО-ОБЛІКОВА%' or t.name like '%ШАФА%ВВІДНО-ОБДЛІКОВА%') \n" +  
					"         and e.code = q1.planrefcode \n" +  
					"         and e.code in ( select ep.planrefcode from enipitem2plan ep where ep.ipitemrefcode=  " + enipitemcode +"    ) \n" +
					"         and re.fkorderitemrefcode =ri.code \n" +
					"         and ri.fkorderrefcode = r.code \n" +
					"         and re.estimateitemcode = re2.estimateitemcode \n" +
					"         and re2.orderitemcode = r2.code \n" +
					"          and r.kindcode in (1,15) \n" +
					"         and r2.ddscodescode = 180	/*ИНВЕСТПРОГРАММА*/ \n" + 
					"         and to_char(r.dategen,'yyyy') =  ( select i.yeargen::text from  enipitem ii , enip i  where ii.iprefcode = i.code    and  ii.code in (" + itcodesandchildcodes + " )  limit 1  ) \n" + 
					"  ) as qOrder \n" + 
				   "  where ipi2p.planrefcode = p.code   \n" +
				   "  and p.kindcode = 2  \n" +
				   "  and ipi2p.ipitemrefcode = ipi.code  \n" +
				   "  and ipi.iprefcode = ip.code   \n" +
				   "  and ipi.code in (" + itcodesandchildcodes + " )  \n" +
				   "  and qOrder.ipItemCode = ipi.code  \n" +
				   "    \n" +
				   "    \n" +
				   "  ) as sel_in1 \n" +
				   "   \n" +
				   "  ) as sel2 \n" +

				   "  \n" ;



   if (isDebug){
   System.out.println("sql : " + sql1 + sql2+ sql3);

   }

   LoggableStatement statement = null;
   ResultSet  resultSet = null;

   String out = "";
   try {

   
   statement = new LoggableStatement(connection, sql1 + sql2+ sql3);
   System.out.println("############### getDocsOsvoenie "
	        + "Executing query: " + ((LoggableStatement)statement).getQueryString());

   resultSet = statement.executeQuery();


   while(resultSet.next())
   {
      out =  resultSet.getString(1);
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


   /*
    * Документы по освоению по пунктам ИП Без промежуточных документов 
    * */
    public String getDocsOsvoenieWithoutIntervalDoc( int enipitemcode , String itcodesandchildcodes , String datereport , int kvartal )   throws PersistenceException
    {
    boolean isDebug = false ;

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


    String sql1 =
 		   " select string_agg(distinct( osvoeno_docs )  , ',' )  \n" +
 				   " from  \n" +
 				   " ( \n" +
 				   " select       /* освоение ОЗ , ХОЗ способ */  \n" +
 				   "   case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then   \n" +
 				   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */   \n" +
 				   "                         (select string_agg(distinct('ОЗ-1№ '||f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')) ,',')  \n" +
 				   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
 				   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
 				   "                          where p.kindcode = 2  \n" +
 				   "                          and bi2ei.billitemcode = bi.code  \n" +
 				   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
 				   "                          and ei.kindrefcode in (1,6)  \n" +
 				   "                          and p.code = ei.planrefcode  \n" +
 				   "                          and ei.code = bi2ei.estimateitemcode              \n" +
 				   "                          and fi2ei.estimateitemcode = ei.code  \n" +
 				   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
 				   "                          and fi.fkorderrefcode = f.code  \n" +
 				   "                          and f.kindcode in (8,9,11,12,21)  \n" +
 				   "                          and f.statuscode in (3,4,5)  \n" +
 				   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
 				   "                          and p.code = sel_in1.planworkcode  \n" +
 				   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
 				   "                                                 where pi2bi.billitemcode = bi.code  \n" +
 				   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
 				   "                                                    and pi.paydocrefcode = pay.code  \n" +
 				   "                                          )  \n" +
 				   "    \n" +
 				   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
 				   "                                       \n" +
 				   "                           )  \n" +
 				   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, значит акты  ОЗ-2 */  \n" +
 				   "                            (select string_agg(distinct('ОЗ-2 '||rm.numbergen||' від '||to_char(rm.dategen ,'dd.mm.yyyy')) ,',')  \n" +
 				   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a   \n" +
 				   "                              , enplanwork p , enreconstrmodernoz rm  \n" +
 				   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
 				   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
 				   "                              and hp.reasoncode = 4  \n" +
 				   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
 				   "                              and hf.reasoncode = 5  \n" +
 				   "                              and hf.plannewrefcode = a2p.plancode  \n" +
 				   "                              and a2p.actrefcode = r2a.actrefcode  \n" +
 				   "                              and hf.plannewrefcode = p.code   \n" +
 				   "                              and r2a.enreconstrmodernozrfcd = rm.code   \n" +
 				   "                              and rm.statusrefcode = 3   \n" +
 				   "                              and p.statuscode  = 3  \n" +
 				   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
 				   "                              and rm.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
 				   "                           )  \n" +
 				   "                 end  \n" +
 				   "              /* освоение ОЗ , услуги со стороны */  \n" +
 				   "           when  ipimplementtyperefcode = 1 and  typerefcode = 20 then    \n" +
 				   "                 (select 'ОЗ-2№ '|| string_agg(distinct(rm.numbergen||' від '||to_char(rm.dategen ,'dd.mm.yyyy')) ,',')  \n" +
 				   "                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a   \n" +
 				   "                    , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f  \n" +
 				   "                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
 				   "                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
 				   "                    and hp.reasoncode = 4  \n" +
 				   "                    and hp.plannewrefcode = hf.planoldrefcode  \n" +
 				   "                    and hf.reasoncode = 5  \n" +
 				   "                    and hf.plannewrefcode = a2p.plancode  \n" +
 				   "                    and a2p.actrefcode = r2a.actrefcode  \n" +
 				   "                    and hf.plannewrefcode = p.code   \n" +
 				   "                    and r2a.enreconstrmodernozrfcd = rm.code   \n" +
 				   "                    and rm.statusrefcode = 3   \n" +
 				   "                    and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */  \n" +
 				   "                    and rpf.factcode = p.code   \n" +
 				   "                    and rpf.fkorderitemcode = fi.code   \n" +
 				   "                    and fi.fkorderrefcode = f.code  \n" +
 				   "                    and f.kindcode = 14  \n" +
 				   "                    and f.statuscode in (3,4,5)  \n" +
 				   "                    and p.statuscode  = 3   \n" +
 				   "                    and ipp.ipitemrefcode = ipitemcode  \n" +
 				   "                     \n" +
 				   "                     \n" +
 				   "                   )  \n" +
 				   "             /* освоение Акт вып работ , услуги со стороны */  \n" +
 				   "           when  ipimplementtyperefcode = 2 and  typerefcode = 20 then  \n" +
 				   "                 (select string_agg(distinct(f.numberdoc||' від '||to_char(f.dategen ,'dd.mm.yyyy')) ,',')  \n" +
 				   "                  from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi  \n" +
 				   "                       , rqfkorder f   \n" +
 				   "                  where chp.reasoncode = 4  \n" +
 				   "                  and chp.plannewrefcode = chpfact.planoldrefcode  \n" +
 				   "                  and chpfact.reasoncode = 5  \n" +
 				   "                  and p.code = chpfact.plannewrefcode  \n" +
 				   "                  and p.statuscode = 3  \n" +
 				   "                  and chp.planoldrefcode = sel_in1.planworkcode   \n" +
 				   "                  and rpf.factcode = p.code   \n" +
 				   "                  and rpf.fkorderitemcode = fi.code   \n" +
 				   "                  and fi.fkorderrefcode = f.code  \n" +
 				   "                  and f.kindcode = 14  \n" +
 				   "                  and f.statuscode in (3,4,5)   \n" +
 				   "                  and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
 				   "                 )   \n" +
 				   "             /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */  \n" +
 				   "         /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then  \n" +
 				   "                  ''  пока непонятно как по счетчикам считать использование*/   \n" +
 				   "                  /*(  \n" +
 				   "                   select string_agg(distinct(scusageinputitemoz.numberdoc) ,',')  \n" +
 				   "                  from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei   \n" +
 				   "                      , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput  \n" +
 				   "                  where chp.reasoncode = 4  \n" +
 				   "                  and chp.plannewrefcode = chpfact.planoldrefcode  \n" +
 				   "                  and chpfact.reasoncode = 5  \n" +
 				   "                  and p.code = chpfact.plannewrefcode  \n" +
 				   "                  and p.statuscode = 3  \n" +
 				   "                  and chp.planoldrefcode = sel_in1.planworkcode   \n" +
 				   "                  and p.code = ei.planrefcode  \n" +
 				   "                  and ei.accountingtyperefcode = 2   \n" +
 				   "                  and scc.estimateitemrefcode = ei.code   \n" +
 				   "                  and scusageinputtmz2sccntr.sccounterrefcode = scc.code  \n" +
 				   "                  and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode  \n" +
 				   "                  and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code   \n" +
 				   "                  and scusageinputitem.sccode is not null  \n" +
 				   "                  and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію  \n" +
 				   "                                              ,4  -- Введення у експлуатацію ЗКУ  \n" +
 				   "                                              ,5  -- Введення у експлуатацію  у складі ЗКУ  \n" +
 				   "                                              )    \n" +
 				   "                  and scusageinputitem.usageinputrefcode = scusageinput.code   \n" +
 				   "                  and scusageinput.statusrefcode = 3 --ОЗ-1 проведений   \n" +
 				   "                  and scusageinput.dategen <= sel_in1.datereport   \n" +
 				   "                    \n" +
 				   "                    \n" +
 				   "                 )*/  \n" +
 				   "             \n" +
 				   "             \n" +
 				   "              \n" +
 					  " /* освоение по приходным ордерам */ \n" +
 					   "        when  ipimplementtyperefcode = 3 then   \n" +
 					   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в Приходах */   \n" +
 					   "                         (select 'Орд№' || string_agg(distinct(f.numberdoc) ,',')  \n" +
 					   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
 					   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
 					   "                          where p.kindcode = 2  \n" +
 					   "                          and bi2ei.billitemcode = bi.code  \n" +
 					   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
 					   "                          and ei.kindrefcode in (1,6)  \n" +
 					   "                          and p.code = ei.planrefcode  \n" +
 					   "                          and ei.code = bi2ei.estimateitemcode              \n" +
 					   "                          and fi2ei.estimateitemcode = ei.code  \n" +
 					   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
 					   "                          and fi.fkorderrefcode = f.code  \n" +
 					   "                          and f.kindcode in (1)  \n" +
 					   "                          and f.statuscode in (3,4,5)  \n" +
 					   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
 					   "                          and p.code = sel_in1.planworkcode  \n" +
 					   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
 					   "                                                 where pi2bi.billitemcode = bi.code  \n" +
 					   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
 					   "                                                    and pi.paydocrefcode = pay.code  \n" +
 					   "                                          )  \n" +
 					   "    \n" +
 					   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
 					   "                                       \n" +
 					   "                           )  \n" +
 					   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, документы по всем приходным ордерам материалов с плана */  \n" +
 					   "                         (select 'Орд№' || string_agg(distinct(f.numberdoc) ,',')  \n" +
 					   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
 					   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
 					   "                          where p.kindcode = 2  \n" +
 					   "                          and bi2ei.billitemcode = bi.code  \n" +
 					   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
 					   "                          and ei.kindrefcode in (1,6)  \n" +
 					   "                          and p.code = ei.planrefcode  \n" +
 					   "                          and ei.code = bi2ei.estimateitemcode              \n" +
 					   "                          and fi2ei.estimateitemcode = ei.code  \n" +
 					   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
 					   "                          and fi.fkorderrefcode = f.code  \n" +
 					   "                          and f.kindcode in (1)  \n" +
 					   "                          and f.statuscode in (3,4,5)  \n" +
 					   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
 					   "                          and p.code = sel_in1.planworkcode  \n" +
 					   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
 					   "                                                 where pi2bi.billitemcode = bi.code  \n" +
 					   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
 					   "                                                    and pi.paydocrefcode = pay.code  \n" +
 					   "                                          )  \n" +
 					   "                           )  \n" +
 					   "                 end   \n" +
 					   "  \n" +
 					   " /*освоение документы  zku */  \n" +
 					   "        when typerefcode in ( 106, 111, 112) then  \n" +
 					   "         (select 'ОЗ №' ||string_agg(distinct( scusageinputitemoz.numberdoc),',')  \n" +
 					   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
 					   "                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput                               \n" +
 					   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
 					   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
 					   "                              and hp.reasoncode = 4  \n" +
 					   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
 					   "                              and hf.reasoncode = 5  \n" +
 					   "                              and hf.plannewrefcode = a2p.plancode  \n" +
 					   "                              and hf.plannewrefcode = p.code   \n" +
 					   "                              and p.statuscode  = 3  \n" +
 					   "                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
 					   "                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
 					   "                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
 					   "                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
 					   "                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
 					  " /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" + 
 	                   " /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
 	                   "  scusageinput.dategen >= sel_in1.minDateOrderInvest    \n" +   
 	                   " else 1=1  \n" + 
 	                   " end /*new*/  \n" +
 					   "                              and scusageinput.statusrefcode = 3                                \n" +
 					   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
 					   "                           ) \n" +
 					   "  \n"  +
 					   "   /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" + 
 					   "   when ipimplementtyperefcode = 4 then  \n" +
 					   "     /*выберем документы  */  \n" + 
 					   "  (select string_agg(distinct actnum ,',') from ( select  \n" +
 					   "    distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
 					   "                         from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
 					   "                         , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
 					   "                         where  hp.planoldrefcode = ipp.planrefcode   \n" +
 					   "                         and hp.planoldrefcode = sel_in1.planworkcode    \n" +
 					   "                         and hp.reasoncode = 4   \n" +
 					   "                         and hp.plannewrefcode = hf.planoldrefcode   \n" +
 					   "                         and hf.reasoncode = 5   \n" +
 					   "                         and hf.plannewrefcode = a2p.plancode  \n" + 
 					   "                         and hf.plannewrefcode = p.code   \n" +
 					   "                         and p.statuscode  = 3  \n" +
 					   "                         and enestimateitem.planrefcode = p.code  \n" +
 					   "                         and enestimateitem.code = sccounter.estimateitemrefcode \n" +
 					   "                         and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
 					   "                         and a2p.actrefcode = act.code \n" +
 					   "                         and enestimateitem.kindrefcode in (1,2) \n" +
 					   "                         and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
 					   "                         /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
 					   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
 					   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +    
 					   "                        else 1=1 \n" +
 					   "                        end \n" +
 					   "                         and ipp.ipitemrefcode = ipitemcode   \n" +
 					   "                                union  \n" +
 					   "              select distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" +
 					   "              from enplancorrecthistory phf,  \n" +
 					   "                   enestimateitem eifact,  \n" +
 					   "                  finmaterials fin, \n" +
 					   "                 enact2enplanwork a2p,        \n" +                  
 					   "                 enplanwork pfact , \n" +
 					   "                 enact act  \n" +
 					   "            where phf.plannewrefcode = pfact.code and  \n" +
 					   "                  phf.plannewrefcode = eifact.planrefcode and  \n" +
 					   "                  eifact.kindrefcode in (1, 2) and \n" +
 					   "                  eifact.countfact > 0 and \n" +
 					   "                  eifact.code = fin.estimateitemrefcode and \n" +
 					   "                  fin.statusrefcode = 1 and \n" +
 					   "                  pfact.code = eifact.planrefcode and  \n" +
 					   "                  pfact.code = a2p.plancode and    \n" +                      
 					   "                  pfact.statuscode = 3 and \n" +
 					   "                   act.code =a2p.actrefcode and \n" +
 					   "                    act.dateact <= sel_in1.datereport  /*дата освоения материалов */ \n" +
 					   "                    /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
 					   "                        and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
 					   "                         act.dateact >= sel_in1.minDateOrderInvest  \n" +   
 					   "                        else 1=1 \n" +
 					   "                        end \n" +
 					   "                    and phf.reasoncode = 5  \n" +
 					   "                    and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
 					   "                    and phf.planoldrefcode in ( \n" +
 					   "                                            select ph.plannewrefcode \n" +
 					   "                                            from enplancorrecthistory ph \n" +
 					   "                                            where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
 					   "                                                  ph.reasoncode = 4 \n" +
 					   "                    ) \n" +
 				       "  ) as q1 ) \n" +
 				       "      /*zzzend*/     \n" +
 				   "        else ''   \n" +
 				   "   end           \n" +
 				   "       \n" +
 				   "   as osvoeno_docs    \n" +
 				   "             \n" +
 				   " from (   \n" +
 				   "  select  \n" +
 				   "    ipi.code as ipitemcode   \n" +
 				   "   ,p.code as planworkcode   \n" +
 				   "   ,case when p.typerefcode in (100, 106, 111, 112) then 1 else 0 end as isCounters   \n" +
 				   "   ,p.typerefcode  \n" +
 				   "   ,coalesce(p.ipimplementtyperefcode,ipi.ipimplementtyperefcode) as ipimplementtyperefcode    \n" +
 				   "   ,case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials  \n" +
 				   "               where enipitem2tkmaterials.ipitemrefcode = ipi.code  ) > 0    then 1 else 0 end as isUseMaterialOnIpItem  \n" +
 				   "   , kvartal.rep_kvartal   \n" +
 				   "   ,datereport.datereport   \n" +
 				   "   , " + enipitemcode +" as enipitemcode              \n" +
 				   "   , ip.yeargen as yeargenip  \n" + 
 				   "    , ipi.itemnumber \n" +
 					"    , qOrder.minDateOrderInvest \n" +
 				   "  from   \n" +
 				   "   enipitem2plan ipi2p  \n" +
 				   "  ,enplanwork p    \n" +
 				   "  ,enipitem ipi  \n" +
 				   "  ,( select "+ kvartal +" as rep_kvartal ) as kvartal   \n" +
 				   "  ,( select to_date('"+datereport+"','dd.mm.yyyy') as datereport ) as datereport  \n" +
 				   "  , enip ip   \n" +
 				   "  , ( select min(r.dategen) as minDateOrderInvest , " + enipitemcode +" as ipItemCode   from  \n" + 
 					"         enestimateitem q1 , rqfkorderitem2enstmttm re , enplanwork e , rqfkorderitem ri , rqfkorder r  \n" + 
 					"         , rqorderitem2enestimttm re2 , rqorderitem r2  \n" + 
 					"         where q1.code = re.estimateitemcode  \n" + 
 					"         and q1.materialrefcode in (select t.code from tkmaterials t where t.name like '%ШАФА%ВВІДНО-ОБЛІКОВА%' or t.name like '%ШАФА%ВВІДНО-ОБДЛІКОВА%') \n" +  
 					"         and e.code = q1.planrefcode \n" +  
 					"         and e.code in ( select ep.planrefcode from enipitem2plan ep where ep.ipitemrefcode=  " + enipitemcode +"    ) \n" +
 					"         and re.fkorderitemrefcode =ri.code \n" +
 					"         and ri.fkorderrefcode = r.code \n" +
 					"         and re.estimateitemcode = re2.estimateitemcode \n" +
 					"         and re2.orderitemcode = r2.code \n" +
 					"          and r.kindcode in (1,15) \n" +
 					"         and r2.ddscodescode = 180	/*ИНВЕСТПРОГРАММА*/ \n" + 
 					"         and to_char(r.dategen,'yyyy') =  ( select i.yeargen::text from  enipitem ii , enip i  where ii.iprefcode = i.code    and  ii.code in (" + itcodesandchildcodes + " )  limit 1  ) \n" + 
 					"  ) as qOrder \n" + 
 				   "  where ipi2p.planrefcode = p.code   \n" +
 				   "  and p.kindcode = 2  \n" +
 				   "  and ipi2p.ipitemrefcode = ipi.code  \n" +
 				   "  and ipi.iprefcode = ip.code   \n" +
 				   "  and ipi.code in (" + itcodesandchildcodes + " )  \n" +
 				   "  and qOrder.ipItemCode = ipi.code  \n" +
 				   "    \n" +
 				   "    \n" +
 				   "  ) as sel_in1 \n" +
 				   "   \n" +
 				   
 				   "  ) as sel2 \n" +

 				   "  \n" ;



    if (isDebug){
    System.out.println("sql : " + sql1  );

    }

    LoggableStatement statement = null;
    ResultSet  resultSet = null;

    String out = "";
    try {

    
    statement = new LoggableStatement(connection, sql1  );
    System.out.println("############### getDocsOsvoenie "
 	        + "Executing query: " + ((LoggableStatement)statement).getQueryString());

    resultSet = statement.executeQuery();


    while(resultSet.next())
    {
       out =  resultSet.getString(1);
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



    
   public JRDataSource getDsFinansirOsvoenieBalansNKRE(int enipitemcode , String itcodesandchildcodes ,
		   String datereport , int kvartal
			  , String infotenders ,

	          // --- FIRST DATA COLUMN
	          String itemnumber,
	          String enippurposeprogram,
	          String name_product,
	          String measurementname,
	          String financing,
	          BigDecimal price,
	          BigDecimal countgen,
	          BigDecimal sumgen,
	          BigDecimal period_count,
	          BigDecimal period_sum ,
	          int inform_nkre , // по форме нкре(1) колонки осталось не профинансировано считаются относительно накопительного по кварталам , иначе относительно года
              String datefrompay
			  )
		{


		 JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

		LoggableStatement tempSt = null;
		ResultSet rs = null;

		try {

			ENIPItemDAO ipiDAO = new ENIPItemDAO(connection, userProfile);
			ENPlanWorkDAO pDAO = new ENPlanWorkDAO(connection, userProfile);

			ENIPItemFilter ipiFilter = new ENIPItemFilter();
			ipiFilter.conditionSQL = " enipitem.code in ( " + itcodesandchildcodes + ")";
			ENIPItemShortList ipiList = ipiDAO.getScrollableFilteredList(ipiFilter, 0, -1);

			/*for(int i = 0; i < ipiList.totalCount; i++){

				ENPlanWorkFilter pFilter = new ENPlanWorkFilter();
				pFilter.conditionSQL = " enplanwork.code in ( select ip2p.planrefcode from enipitem2plan ip2p where ip2p.ipitemrefcode = " + ipiList.get(i).code + " )";
				pFilter.kind.code = ENPlanWorkKind.CURRENT; // возбмем только месячные планы

				int[] pArr = pDAO.getFilteredCodeArray(pFilter, 0, -1);

				for(int p = 0; p < pArr.length; p++){

					ENPlanWork pObj = pDAO.getObject(pArr[p]);



					System.out.print("ipicode = " + ipiList.get(i).code + " ||||| plan code = " + pArr[p] );
				}

		    }*/

			String selStr1 = " select  \n" +
			" enipitemcode, /*параметр парент пункта ип*/ \n" +  // 1
			" datereport,  \n" +  // 2
			" sum(profinans_count)::numeric(15,6) as profinans_count ,  \n" + // 3
			" sum(profinans_sum)/1000::numeric(15,2) as profinans_sum ,  \n" + // 4
			" substring(string_agg(distinct(osvoeno_docs) , ','),1,32500) as osvoeno_docs ,  \n" + // 5
			" sum(osvoeno_count_by_plan) as osvoeno_count_by_plan,  \n" + // 6
			" sum(osvoeno_summa_by_plan)/1000::numeric(15,2) as osvoeno_summa_by_plan, \n" + // 7
		//	" ( countgen  - sum(profinans_count) )::numeric(15,6) as not_financed_count ,  \n" + // 8
		//	" ( sumgen - (sum(profinans_sum)/1000))::numeric(15,2) as not_financed_sum ,  \n" + // 9
			" ( "+ ( inform_nkre == 1  ? period_count : countgen  ) +"  - sum(profinans_count) )::numeric(15,6) as not_financed_count ,  \n" + // 8
			" ( "+ ( inform_nkre == 1  ? period_sum : sumgen )  +" - (sum(profinans_sum)/1000))::numeric(15,2) as not_financed_sum ,  \n" + // 9
			" case when sum(profinans_count) > 0 and sum(profinans_sum) > 0 \n" +
			"           then ((((sum(profinans_sum)/1000/sum(profinans_count)) / price ) *100 )-100)::numeric(15,2) \n" +
			"        when ( sum(profinans_count) <= 0 or sum(profinans_sum) <= 0 ) and  (sum(osvoeno_count_by_plan) > 0 and sum(osvoeno_summa_by_plan) > 0 ) \n" +
			"           then ((((sum(osvoeno_summa_by_plan)/1000/sum(osvoeno_count_by_plan)) / price ) *100 )-100)::numeric(15,2) \n" +
			"  \n" +
			" else null end as persent ,  \n" + // 10
			"  substring(infotenders,1,32500) as infotenders ,  \n" +  // 11
			" /*postavchiki*/ \n" +
			" substring(( select \n" +
			"  \n" +
			"         (/* перечень поставщиков */ \n" +
			"         select string_agg(distinct(o.name) ,',') \n" +
			"         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , \n" +
			"          rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay , enipitem2plan ipit2pl \n" +
			"          , rqbillitem bi , rqbill b , rqorg o \n" +
			"          , (select it.code from enipitem it where it.code in ("+itcodesandchildcodes+")) dataenipitem \n" +
			"         where   ipit2pl.ipitemrefcode = dataenipitem.code \n" +
			"         and p.kindcode = 2 \n" +
			"         and ei.kindrefcode in (1,6) \n" +
			"         and bi.ddscodestxt = '312 100'  /*выполнено по ддс ип*/ \n" +
			"         and p.code = ei.planrefcode \n" +
			"         and ei.code = bi2ei.estimateitemcode \n" +
			"         and pi2bi.billitemcode = bi2ei.billitemcode \n" +
			"         and pi2bi.paydocitemcode = pi.code \n" +
			"         and pi.paydocrefcode = pay.code \n" +
			"         and ipit2pl.planrefcode = p.code \n" +
			"         and bi2ei.billitemcode = bi.code \n" +
			"         and bi.billrefcode = b.code \n" +
			"         and b.orgcode = o.code \n" +
			"  \n" +
			"         /* если на пункте указаны материалы то документы  только по этим материалам из плана \n" +
			"    иначе берется сумма по материалам и услугам скопом */ \n" +
			"      and case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials \n" +
			"              where enipitem2tkmaterials.ipitemrefcode = dataenipitem.code \n" +
			"                                        ) > 0 then \n" +
			"         ei.materialrefcode in  (select enipitem2tkmaterials.materialrefcode from enipitem2tkmaterials \n" +
			"              where enipitem2tkmaterials.ipitemrefcode = dataenipitem.code \n" +
			"                                        ) \n" +
			"      else 1=1 \n" +
			"      end \n" +
			"           ) \n" +
			"  \n" +
			"  ),1,32500) as contragent \n" +  // 12
			"  \n" ;

	       String selStr2 =" from ( \n" +
			" select  \n" +
			" enipitemcode , /*параметр парент пункта ип*/ \n" +
			" (select COALESCE(enipitem.countgen,0) from enipitem where enipitem.code = enipitemcode) as countgen ,  \n" +
			" (select COALESCE(enipitem.sumgen,0) from enipitem where enipitem.code = enipitemcode) as sumgen , \n" +
			" (select COALESCE(enipitem.price,0) from enipitem where enipitem.code = enipitemcode) as price , \n" +
			" (select enipitem.infotenders from enipitem where enipitem.code = enipitemcode) as infotenders , \n" +
			" planworkcode,  \n" +
			" iscounters,  \n" +
			" typerefcode,  \n" +
			" ipimplementtyperefcode,  \n" +
			" isusematerialonipitem,  \n" +
			" rep_kvartal,  \n" +
			" datereport,  \n" +
			" case when isusematerialonipitem > 0 then profinans_count else osvoeno_count_by_plan end as profinans_count ,  \n" +
			// SUPP-52255 - возвращали деньги , отнимем
			" case when planworkcode = 1020604843 then profinans_sum - 833333 " +   // c ндс 1000000
			"         when planworkcode = 1020752615 then profinans_sum - 1980055 " +   // c ндс 2376066
		    " else profinans_sum   end as profinans_sum ,  \n" +
			" osvoeno_docs,  \n" +
			" osvoeno_count_by_plan,  \n" +
			" /* 22.07.2014 задание щербина что если по второму разделу суммма по внедрению больше чем сумма по финансированию тогда сумма по внедрению \n" +
			"   =  сумме по финансированию */ \n" +
			"  /*23.09.2021 доработка 2 раздела по новому тех  заданию . case when ( select enipitem.invgrouprefcode from enipitem where enipitem.code = enipitemcode ) = 2 \n" +
			"         and osvoeno_summa_by_plan > profinans_sum then profinans_sum else  osvoeno_summa_by_plan   end as */ osvoeno_summa_by_plan \n" +

			" from ( \n" +
			"  \n" +
			" select  \n" +
			" enipitemcode \n" +
			" ,ipitemcode   \n" +
			" ,planworkcode \n" +
			" ,isCounters \n" +
			" ,typerefcode \n" +
			" ,ipimplementtyperefcode  \n" +
			" ,isUseMaterialOnIpItem \n" +
			" ,rep_kvartal  \n" +
			" ,datereport \n" +
			" /* профинансировано кол-во */  \n" +
			" , COALESCE( \n" +
			" case when isUseMaterialOnIpItem > 0 then \n" +
			"         (/* кол-во профинансировано если на пункте ИП проставлен материал */ \n" +
			"         select sum(bi2ei.countgen) \n" +
			"         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi , \n" +
			"           enipitem2plan ipit2pl /*, enipitem2tkmaterials iti2tm  */ \n" +
			"         where p.kindcode = 2 \n" +
			"         and bi2ei.billitemcode = bi.code \n" +
			"         and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
			"         and ei.kindrefcode in (1,6) \n" +
			"         and p.code = ei.planrefcode \n" +
			"         and ei.code = bi2ei.estimateitemcode \n" +
			"         and ipit2pl.planrefcode = p.code \n" +
			"         and p.code = sel_in1.planworkcode  \n" +
			//"         and ipit2pl.ipitemrefcode = iti2tm.ipitemrefcode \n" +
			//"         and ei.materialrefcode = iti2tm.materialrefcode \n" +
			//"         and iti2tm.ipitemrefcode = sel_in1.ipitemcode \n" +
			" and ei.materialrefcode in ( select i2m.materialrefcode from enipitem2tkmaterials i2m where i2m.ipitemrefcode= sel_in1.ipitemcode  and i2m.materialrefcode=ei.materialrefcode) \n" +
			"         and ipit2pl.ipitemrefcode = ipitemcode \n" +
			"         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
			"                                where pi2bi.billitemcode = bi.code \n" +
			"                                   and pi2bi.paydocitemcode = pi.code \n" +
			"                                   and pi.paydocrefcode = pay.code \n" +
			//"                                   and pay.dategen <= datereport"
			"                                   and pay.dategen between to_date('"+ datefrompay +"' ,'dd.mm.yyyy')  and datereport \n" +
			"                                ) \n" +
			"          ) \n" +
			"  else /*если на пункте материал не проставлен то кол- во подкинем выше (возмется из освоено - кол-во )*/ \n" +
			"    0 \n" +
			"  \n" +
			"  end \n" +
			"  ,0) as profinans_count \n" +
			"  /*--------------------*/ \n" +
			"  /*сумма профинансировано без НДС */ \n" +
			"  , coalesce( ( select sum(summagen)::numeric(15,2) from \n" +
			" ( \n" +
			" select sum( \n" +
			"   ( \n" +
			"   bi2ei.countgen \n" +
			"   * \n" +
			"   (bi.pricewithoutnds*pi.summagen/bi.sumwithoutnds  ) \n" +
			"   ) \n" +
			"  \n" +
			"    )  / (1::numeric+(b.vat::numeric/100::numeric) )  as summagen \n" +
			"  \n" +
			"   from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei ,  rqpaydocitem2billitem pi2bi , \n" +
			"   rqpaydocitem pi , rqpaydoc pay , rqbillitem bi , rqbill b    \n" +
			" where p.kindcode = 2 \n" +
			" and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
			" and ei.kindrefcode in (1,6) \n" +
			" and p.code = ei.planrefcode \n" +
			" and ei.code = bi2ei.estimateitemcode \n" +
			" and pi2bi.billitemcode = bi2ei.billitemcode \n" +
			" and pi2bi.paydocitemcode = pi.code \n" +
			" and pi.paydocrefcode = pay.code \n" +
			" and bi2ei.billitemcode = bi.code \n" +
			//" and pay.dategen <= datereport \n" +
			" and pay.dategen between to_date('"+datefrompay+"' ,'dd.mm.yyyy')  and datereport \n" +
			" and bi.billrefcode = b.code \n" +
			" and p.code = sel_in1.planworkcode \n" +
			"  \n" +
			" /* если на пункте указаны материалы то сумму оплат только по этим материалам из плана \n" +
			"    иначе берется сумма оплат по материалам и услугам скопом */ \n" +
			" and case when isUseMaterialOnIpItem > 0 then \n" +
			"               ei.materialrefcode in  (select enipitem2tkmaterials.materialrefcode from enipitem2tkmaterials \n" +
			"                                       where enipitem2tkmaterials.ipitemrefcode = sel_in1.ipitemcode \n" +
			"                                      ) \n" +
			"     else 1=1 \n" +
			"     end \n" +
			"  \n" +
			" group by b.vat \n" +
			" ) s1 ),0) as profinans_sum \n" ;


	       String selStr3 = " /* osvoeno_docs  */ \n" +
			" , \n" +
			"    /* освоение ОЗ , ХОЗ способ */ \n" +
			"  case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then  \n" +
			"                case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */  \n" +
			"                     ( select string_agg(qoz_doc.oz_docs,',') from \n" +
			"                        (/*!!!!!!ZZZZZZ*/ select distinct(case when f.kindcode = 8  then 'Введ. в експл. спец. одягу (МБП)'  \n" +
			"                               when f.kindcode = 9  then 'Введ. в експл. спец. одягу (МНМА)' \n" +
			"                               when f.kindcode = 11 then 'Введ. в експл. МБП' \n" +
			"                               when f.kindcode = 12 then 'Введ. в експл. МНМА' \n" +
			"                               when f.kindcode = 21 then 'Введ. в експл. ОЗ' \n" +
			"                               else '' end || f.numberdoc || ' '|| to_char(f.dategen,'dd.mm.yyyy') )   as oz_docs \n" +
			"                           from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
			"                          ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
			"                           where p.kindcode = 2  \n" +
			"                           and bi2ei.billitemcode = bi.code  \n" +
			"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
			"                           and ei.kindrefcode in (1,6)  \n" +
			"                           and p.code = ei.planrefcode  \n" +
			"                           and ei.code = bi2ei.estimateitemcode              \n" +
			"                           and fi2ei.estimateitemcode = ei.code  \n" +
			"                           and fi2ei.fkorderitemrefcode = fi.code  \n" +
			"                           and fi.fkorderrefcode = f.code  \n" +
			"                           and f.kindcode in (8,9,11,12,21)  \n" +
			"                           and f.statuscode in (3,4,5)  \n" +
			"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                           and p.code = sel_in1.planworkcode  \n" +
			"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
			"                                                  where pi2bi.billitemcode = bi.code  \n" +
			"                                                     and pi2bi.paydocitemcode = pi.code  \n" +
			"                                                     and pi.paydocrefcode = pay.code  \n" +
			"                                           )  \n" +
			"     \n" +
			"                           and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
			"                            \n" +
			"                           UNION  \n" +
			"                           select  doc_oz1_2  as oz_docs from ( \n" +
			"                             select distinct('ОЗ-2 '|| rm.numbergen || ' '|| to_char(rm.dategen ,'dd.mm.yyyy') ) as doc_oz1_2  \n" +
			"                               from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a   \n" +
			"                               , enplanwork p , enreconstrmodernoz rm  \n" +
			"                               where  hp.planoldrefcode = ipp.planrefcode  \n" +
			"                               and hp.planoldrefcode = sel_in1.planworkcode   \n" +
			"                               and hp.reasoncode = 4  \n" +
			"                               and hp.plannewrefcode = hf.planoldrefcode  \n" +
			"                               and hf.reasoncode = 5  \n" +
			"                               and hf.plannewrefcode = a2p.plancode  \n" +
			"                               and a2p.actrefcode = r2a.actrefcode  \n" +
			"                               and hf.plannewrefcode = p.code   \n" +
			"                               and r2a.enreconstrmodernozrfcd = rm.code   \n" +
			"                               and rm.statusrefcode = 3   \n" +
			"                               and p.statuscode  = 3  \n" +
			"                               and ipp.ipitemrefcode = ipitemcode  \n" +
			"                               and rm.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                               /*выборка из ОЗ если материал не вводился ордерами (kindcode in (8,9,11,12,21)) */ \n" +
			"                               and (select count(p.code) from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
			"                                      ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
			"                                       where p.kindcode = 2  \n" +
			"                                       and bi2ei.billitemcode = bi.code  \n" +
			"                                       and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
			"                                       and ei.kindrefcode in (1,6)  \n" +
			"                                       and p.code = ei.planrefcode  \n" +
			"                                       and ei.code = bi2ei.estimateitemcode              \n" +
			"                                       and fi2ei.estimateitemcode = ei.code  \n" +
			"                                       and fi2ei.fkorderitemrefcode = fi.code  \n" +
			"                                       and fi.fkorderrefcode = f.code  \n" +
			"                                       and f.kindcode in (8,9,11,12,21)  \n" +
			"                                       and f.statuscode in (3,4,5)  \n" +
			"                                       and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                                       and p.code = sel_in1.planworkcode  \n" +
			"                                       and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
			"                                                              where pi2bi.billitemcode = bi.code  \n" +
			"                                                                 and pi2bi.paydocitemcode = pi.code  \n" +
			"                                                                 and pi.paydocrefcode = pay.code  \n" +
			"                                                       )  \n" +
			"                 \n" +
			"                                       and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
			"                                     ) = 0  \n" +
			"                                \n" +
			"                      UNION  \n" +
			"                      select distinct('ОЗ-1 '|| b.numbergen || ' '|| to_char(b.dategen ,'dd.mm.yyyy') )  as doc_oz1_2  \n" +
			"                      from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enbuilding2enact b2a   \n" +
			"                      , enplanwork p , enbuilding b   \n" +
			"                      where  hp.planoldrefcode = ipp.planrefcode  \n" +
			"                      and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                      and hp.reasoncode = 4  \n" +
			"                      and hp.plannewrefcode = hf.planoldrefcode  \n" +
			"                      and hf.reasoncode = 5  \n" +
			"                      and hf.plannewrefcode = a2p.plancode  \n" +
			"                      and a2p.actrefcode = b2a.actrefcode  \n" +
			"                      and hf.plannewrefcode = p.code   \n" +
			"                      and b2a.enbuildingrefcode = b.code  \n" +
			"                      and p.statuscode  = 3   \n" +
			"                      and ipp.ipitemrefcode = ipitemcode  \n" +
			"                      and b.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                      /*выборка из ОЗ если материал не вводился ордерами (kindcode in (8,9,11,12,21)) */ \n" +
			"                      and (select count(p.code) from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
			"                              ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
			"                               where p.kindcode = 2  \n" +
			"                               and bi2ei.billitemcode = bi.code  \n" +
			"                               and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
			"                               and ei.kindrefcode in (1,6)  \n" +
			"                               and p.code = ei.planrefcode  \n" +
			"                               and ei.code = bi2ei.estimateitemcode              \n" +
			"                               and fi2ei.estimateitemcode = ei.code  \n" +
			"                               and fi2ei.fkorderitemrefcode = fi.code  \n" +
			"                               and fi.fkorderrefcode = f.code  \n" +
			"                               and f.kindcode in (8,9,11,12,21)  \n" +
			"                               and f.statuscode in (3,4,5)  \n" +
			"                               and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
			"                               and p.code = sel_in1.planworkcode  \n" +
			"                               and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
			"                                                      where pi2bi.billitemcode = bi.code  \n" +
			"                                                         and pi2bi.paydocitemcode = pi.code \n" +
			"                                                         and pi.paydocrefcode = pay.code  \n" +
			"                                               )  \n" +
			"         \n" +
			"                               and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
			"                             ) = 0  \n" +
			"                      ) qoz1_2 \n" +
			"                            \n" +
			"                            ) qoz_doc ) \n" +
			"                      when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, кол-во из фактов которые сидят в актах а акты в ОЗ-2 */ \n" +
			"                           (select string_agg(doc_oz1_2,' ,') from (select distinct('ОЗ-2 '|| rm.numbergen || ' '|| to_char(rm.dategen ,'dd.mm.yyyy') ) as doc_oz1_2 \n" +
			"                             from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
			"                             , enplanwork p , enreconstrmodernoz rm \n" +
			"                             where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                             and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                             and hp.reasoncode = 4 \n" +
			"                             and hp.plannewrefcode = hf.planoldrefcode \n" +
			"                             and hf.reasoncode = 5 \n" +
			"                             and hf.plannewrefcode = a2p.plancode \n" +
			"                             and a2p.actrefcode = r2a.actrefcode \n" +
			"                             and hf.plannewrefcode = p.code  \n" +
			"                             and r2a.enreconstrmodernozrfcd = rm.code  \n" +
			"                             and rm.statusrefcode = 3  \n" +
			"                             and p.statuscode  = 3 \n" +
			"                             and ipp.ipitemrefcode = ipitemcode \n" +
			"                             and rm.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                    UNION \n" +
			"                    select distinct('ОЗ-1 '|| b.numbergen || ' '|| to_char(b.dategen ,'dd.mm.yyyy') )  as doc_oz1_2 \n"+
			"                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enbuilding2enact b2a  \n"+
			"                    , enplanwork p , enbuilding b  \n"+
			"                    where  hp.planoldrefcode = ipp.planrefcode \n"+
			"                    and hp.planoldrefcode = sel_in1.planworkcode \n"+  
			"                    and hp.reasoncode = 4 \n"+
			"                    and hp.plannewrefcode = hf.planoldrefcode \n"+ 
			"                    and hf.reasoncode = 5 \n"+
			"                    and hf.plannewrefcode = a2p.plancode \n"+ 
			"                    and a2p.actrefcode = b2a.actrefcode \n"+
			"                    and hf.plannewrefcode = p.code  \n"+
			"                    and b2a.enbuildingrefcode = b.code \n"+
			"                    and p.statuscode  = 3  \n"+
			"                    and ipp.ipitemrefcode = ipitemcode \n"+ 
			"                    and b.dategen <= sel_in1.datereport /*дата освоения материалов */ \n"+
			"                    ) qoz1_2 \n"+ 
			"                          ) \n" +
			"                end \n" +
			"             /* освоение ОЗ , услуги со стороны */ \n" +
			"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
			"                ( select string_agg(qoz1_2.oz_1_2,',') from ( select distinct('ОЗ-2 '|| rm.numbergen || ' '|| to_char(rm.dategen ,'dd.mm.yyyy') ) as oz_1_2 \n" +
			"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
			"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
			"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                   and hp.reasoncode = 4 \n" +
			"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
			"                   and hf.reasoncode = 5 \n" +
			"                   and hf.plannewrefcode = a2p.plancode \n" +
			"                   and a2p.actrefcode = r2a.actrefcode \n" +
			"                   and hf.plannewrefcode = p.code  \n" +
			"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
			"                   and rm.statusrefcode = 3  \n" +
			"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
			"                   and rpf.factcode = p.code  \n" +
			"                   and rpf.fkorderitemcode = fi.code  \n" +
			"                   and fi.fkorderrefcode = f.code \n" +
			"                   and f.kindcode = 14 \n" +
			"                   and f.statuscode in (3,4,5) \n" +
			"                   and p.statuscode  = 3  \n" +
			"                   and ipp.ipitemrefcode = ipitemcode \n" +
			"                   UNION \n" +
			"                   select  distinct('ОЗ-1 '|| b.numbergen || ' '|| to_char(b.dategen ,'dd.mm.yyyy') )   as oz_1_2 \n" + 
			"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enbuilding2enact b2a \n" +  
			"                   , enplanwork p , enbuilding b , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
			"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                   and hp.planoldrefcode = sel_in1.planworkcode \n" +
			"                   and hp.reasoncode = 4  \n" +
			"                   and hp.plannewrefcode = hf.planoldrefcode  \n" +
			"                   and hf.reasoncode = 5  \n" +
			"                   and hf.plannewrefcode = a2p.plancode  \n" +
			"                   and a2p.actrefcode = b2a.actrefcode  \n" +
			"                   and hf.plannewrefcode = p.code   \n" +
			"                   and b2a.enbuildingrefcode = b.code \n" +
			"                   and b.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" + 
			"                   and rpf.factcode = p.code  \n" +
			"                   and rpf.fkorderitemcode = fi.code  \n" +
			"                   and fi.fkorderrefcode = f.code \n" + 
			"                   and f.kindcode = 14 \n" +
			"                   and f.statuscode in (3,4,5) \n" +
			"                   and p.statuscode  = 3  \n" +
			"                   and ipp.ipitemrefcode = ipitemcode \n" +
			"                    ) as qoz1_2 \n" +
			"                  ) \n" +
			"            /* освоение Акт вып работ , услуги со стороны */ \n" +
			"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
			"                (select string_agg(distinct('Акт вик. робіт послуги ' || f.numberdoc || ' '|| to_char(f.dategen,'dd.mm.yyyy') ) ,',') \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
			"                      , rqfkorder f  \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and rpf.factcode = p.code  \n" +
			"                 and rpf.fkorderitemcode = fi.code  \n" +
			"                 and fi.fkorderrefcode = f.code \n" +
			"                 and f.kindcode = 14 \n" +
			"                 and f.statuscode in (3,4,5)  \n" +
			"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                )  \n" +
			"            /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */ \n" +
			"       /*   when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
			"                 '' /* пока непонятно как по счетчикам считать использование*/  \n" +
			"                 ( \n" +
			"                  select string_agg(distinct(case when scusageinputitem.kindrefcode = 1  then 'Введення експл. лічільн. ОЗ'\r\n" + 
			"                       when scusageinputitem.kindrefcode = 4  then 'Введення експл. ЗКУ'\r\n" + 
			"                       when scusageinputitem.kindrefcode = 5  then 'Введення експл. у складі ЗКУ'\r\n" + 
			"						 else '' end || scusageinputitemoz.numberdoc || ' '|| to_char(scusageinput.dategen,'dd.mm.yyyy') ) ,',') \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
			"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and p.code = ei.planrefcode \n" +
			"                 and ei.accountingtyperefcode = 2  \n" +
			"                 and scc.estimateitemrefcode = ei.code  \n" +
			"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
			"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
			"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
			"                 and scusageinputitem.sccode is not null \n" +
			"                 and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію \n" +
			"                                             ,4  -- Введення у експлуатацію ЗКУ \n" +
			"                                             ,5  -- Введення у експлуатацію  у складі ЗКУ \n" +
			"                                             )   \n" +
			"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
			"                 and scusageinput.statusrefcode = 3 --ОЗ-1 проведений  \n" +
			"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
			"                  \n" +
			"                  \n" +
			"                )*/ \n" +
			"           \n" ;

	       String selStr3_1 = "    /* освоение по приходным ордерам */ \n" +
	    		   "        when  ipimplementtyperefcode = 3 then   \n" +
	    		   "                 case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в Приходах */   \n" +
	    		   "                         (select string_agg(distinct('Прибутковий ордер ' || f.numberdoc || ' '|| to_char(f.dategen,'dd.mm.yyyy') ) ,',')  \n" +
	    		   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
	    		   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
	    		   "                          where p.kindcode = 2  \n" +
	    		   "                          and bi2ei.billitemcode = bi.code  \n" +
	    		   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
	    		   "                          and ei.kindrefcode in (1,6)  \n" +
	    		   "                          and p.code = ei.planrefcode  \n" +
	    		   "                          and ei.code = bi2ei.estimateitemcode              \n" +
	    		   "                          and fi2ei.estimateitemcode = ei.code  \n" +
	    		   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
	    		   "                          and fi.fkorderrefcode = f.code  \n" +
	    		   "                          and f.kindcode in (1)  \n" +
	    		   "                          and f.statuscode in (3,4,5)  \n" +
	    		   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
	    		   "                          and p.code = sel_in1.planworkcode  \n" +
	    		   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
	    		   "                                                 where pi2bi.billitemcode = bi.code  \n" +
	    		   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
	    		   "                                                    and pi.paydocrefcode = pay.code  \n" +
	    		   "                                          )  \n" +
	    		   "    \n" +
	    		   "                          and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
	    		   "                                       \n" +
	    		   "                           )  \n" +
	    		   "                       when isUseMaterialOnIpItem = 0 then /* материала на пункте ип нету, документы по всем приходным ордерам материалов с плана */  \n" +
	    		   "                         (select string_agg(distinct('Прибутковий ордер ' || f.numberdoc || ' '|| to_char(f.dategen,'dd.mm.yyyy') ) ,',')  \n" +
	    		   "                          from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi               \n" +
	    		   "                         ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f            \n" +
	    		   "                          where p.kindcode = 2  \n" +
	    		   "                          and bi2ei.billitemcode = bi.code  \n" +
	    		   "                          and bi.ddscodestxt = '312 100' /* выполнено по ддс ип*/  \n" +
	    		   "                          and ei.kindrefcode in (1,6)  \n" +
	    		   "                          and p.code = ei.planrefcode  \n" +
	    		   "                          and ei.code = bi2ei.estimateitemcode              \n" +
	    		   "                          and fi2ei.estimateitemcode = ei.code  \n" +
	    		   "                          and fi2ei.fkorderitemrefcode = fi.code  \n" +
	    		   "                          and fi.fkorderrefcode = f.code  \n" +
	    		   "                          and f.kindcode in (1)  \n" +
	    		   "                          and f.statuscode in (3,4,5)  \n" +
	    		   "                          and f.dategen <= sel_in1.datereport /*дата освоения материалов */  \n" +
	    		   "                          and p.code = sel_in1.planworkcode  \n" +
	    		   "                          and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay  \n" +
	    		   "                                                 where pi2bi.billitemcode = bi.code  \n" +
	    		   "                                                    and pi2bi.paydocitemcode = pi.code  \n" +
	    		   "                                                    and pi.paydocrefcode = pay.code  \n" +
	    		   "                                          )  \n" +
	    		   "                           )  \n" +
	    		   "                 end   \n" +
	    		   "  \n" +
	    		   " /*освоение документы  zku */  \n" +
	    		   "        when typerefcode in ( 106, 111, 112) then  \n" +
	    		   "         (select string_agg(distinct( 'Введення експл. лічільн ' || scusageinputitemoz.numberdoc || ' '|| to_char(scusageinput.dategen,'dd.mm.yyyy') ),',')  \n" +
	    		   "                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
	    		   "                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput                               \n" +
	    		   "                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
	    		   "                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
	    		   "                              and hp.reasoncode = 4  \n" +
	    		   "                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
	    		   "                              and hf.reasoncode = 5  \n" +
	    		   "                              and hf.plannewrefcode = a2p.plancode  \n" +
	    		   "                              and hf.plannewrefcode = p.code   \n" +
	    		   "                              and p.statuscode  = 3  \n" +
	    		   "                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
	    		   "                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
	    		   "                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
	    		   "                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
	    		   "                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
	    		   " /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" + 
                   " /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
                   "  scusageinput.dategen >= sel_in1.minDateOrderInvest    \n" +   
                   " else 1=1  \n" + 
                   " end /*new*/  \n" +
	    		   "                              and scusageinput.statusrefcode = 3                                \n" +
	    		   "                              and ipp.ipitemrefcode = ipitemcode  \n" +
	    		   "                           ) \n" +
	    		   " /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" + 
	    	       " when ipimplementtyperefcode = 4 then \n" + 
	    	       "   /*выберем документы  */  \n" +
	    	       " (select string_agg(distinct actnum ,',') from ( select  \n" +
	    	       "  distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" + 
	    	       "                       from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p  \n" +
	    	       "                       , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
	    	       "                       where  hp.planoldrefcode = ipp.planrefcode  \n" +
	    	       "                       and hp.planoldrefcode = sel_in1.planworkcode    \n" +
	    	       "                       and hp.reasoncode = 4   \n" +
	    	       "                       and hp.plannewrefcode = hf.planoldrefcode   \n" +
	    	       "                       and hf.reasoncode = 5  \n" +
	    	       "                       and hf.plannewrefcode = a2p.plancode  \n" +
	    	       "                       and hf.plannewrefcode = p.code   \n" +
	    	       "                       and p.statuscode  = 3  \n" +
	    	       "                       and enestimateitem.planrefcode = p.code  \n" +
	    	       "                       and enestimateitem.code = sccounter.estimateitemrefcode \n" +
	    	       "                       and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
	    	       "                       and a2p.actrefcode = act.code  \n" +
	    	       "                       and enestimateitem.kindrefcode in (1,2) \n" +
	    	       "                       and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" + 
	    	       "                       /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" + 
	    		   "                      and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +   
	    		   "                       act.dateact >= sel_in1.minDateOrderInvest  \n" +      
	    		   "                      else 1=1  \n" + 
	    		   "                      end \n" + 
	    	       "                       and ipp.ipitemrefcode = ipitemcode   \n" + 
	    	       "                       union  \n" + 
	    	       "    select distinct( 'Акт вып.работ ' || act.numbergen || ' '|| to_char(act.dateact ,'dd.mm.yyyy') )  as actnum \n" + 
	    	       "            from enplancorrecthistory phf, \n" + 
	    	       "                 enestimateitem eifact, \n" +
	    	       "                 finmaterials fin,  \n" +
	    	       "                 enact2enplanwork a2p,    \n" +                      
	    	       "                 enplanwork pfact , \n" +
	    	       "                 enact act \n" +
	    	       "            where phf.plannewrefcode = pfact.code and \n" +
	    	       "                  phf.plannewrefcode = eifact.planrefcode and \n" +
	    	       "                  eifact.kindrefcode in (1, 2) and \n" +
	    	       "                  eifact.countfact > 0 and \n" +
	    	       "                  eifact.code = fin.estimateitemrefcode and \n" +
	    	       "                  fin.statusrefcode = 1 and \n" +
	    	       "                  pfact.code = eifact.planrefcode and \n" +
	    	       "                  pfact.code = a2p.plancode and     \n" +                     
	    	       "                  pfact.statuscode = 3 and \n" +
	    	       "                  act.code =a2p.actrefcode and \n" +
	    	       "                  act.dateact <= sel_in1.datereport  /*дата освоения материалов */ \n" +
	    	       "                  /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
	    		   "                      and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
	    		   "                       act.dateact >= sel_in1.minDateOrderInvest  \n" +   
	    		   "                      else 1=1 \n" +
	    		   "                      end  \n" +
	    	       "                  and phf.reasoncode = 5   \n" +
	    	       "                  and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
	    	       "                  and phf.planoldrefcode in (  \n" +
	    	       "                                          select ph.plannewrefcode  \n" +
	    	       "                                          from enplancorrecthistory ph  \n" +
	    	       "                                          where ph.planoldrefcode =  sel_in1.planworkcode and  \n" + 
	    	       "                                                ph.reasoncode = 4 \n" +
	    	       "                  )  \n" +
	    	       "  ) as q1 )  \n" +
	    	       "    /*zzzend*/   \n" ; 



	       String selStr4 = "       else ''  \n" +
			"  end          \n" +
			"     \n" +
			"  as osvoeno_docs  \n" +
			"  \n" +
			" /* osvoenie count */ \n" +
			" ,coalesce( \n" +
			"    /* освоение ОЗ , ХОЗ способ */ \n" +
			"  case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then  \n" +
			"                case when isUseMaterialOnIpItem > 0 then /* на пункте ип указаны материалы которые искать в ОЗ */ \n" +
			"                        (select sum(fi2ei.countgen) \n" +
			"                         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi              \n" +
			"                        ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f           \n" +
			"                         where p.kindcode = 2 \n" +
			"                         and bi2ei.billitemcode = bi.code \n" +
			"                         and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */ \n" +
			"                         and ei.kindrefcode in (1,6) \n" +
			"                         and p.code = ei.planrefcode \n" +
			"                         and ei.code = bi2ei.estimateitemcode             \n" +
			"                         and fi2ei.estimateitemcode = ei.code \n" +
			"                         and fi2ei.fkorderitemrefcode = fi.code \n" +
			"                         and fi.fkorderrefcode = f.code \n" +
			"                         and f.kindcode in (8,9,11,12,21) \n" +
			"                         and f.statuscode in (3,4,5) \n" +
			"                         and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                         and p.code = sel_in1.planworkcode \n" +
			"                         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
			"                                                where pi2bi.billitemcode = bi.code \n" +
			"                                                   and pi2bi.paydocitemcode = pi.code \n" +
			"                                                   and pi.paydocrefcode = pay.code \n" +
			"                                         ) \n" +
			"  \n" +
			"                         and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode ) \n" +
			"                                     \n" +
			"                          ) \n" +
			"                      when isUseMaterialOnIpItem = 0 then /*материала на пункте ип нету, кол-во из фактов которые сидят в актах а акты в ОЗ-2 */  \n" +
			"                          coalesce( (select sum(p.investWorksAmount) \n" +
			"                             from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
			"                             , enplanwork p , enreconstrmodernoz rm \n" +
			"                             where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                             and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                             and hp.reasoncode = 4 \n" +
			"                             and hp.plannewrefcode = hf.planoldrefcode \n" +
			"                             and hf.reasoncode = 5 \n" +
			"                             and hf.plannewrefcode = a2p.plancode \n" +
			"                             and a2p.actrefcode = r2a.actrefcode \n" +
			"                             and hf.plannewrefcode = p.code  \n" +
			"                             and r2a.enreconstrmodernozrfcd = rm.code  \n" +
			"                             and rm.statusrefcode = 3  \n" +
			"                             and p.statuscode  = 3 \n" +
			"                             and rm.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                             and ipp.ipitemrefcode = ipitemcode \n" +
			"                          ),0)  \n" +
			"                end \n" +
			"             /* освоение ОЗ , услуги со стороны */ \n" +
			"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
			"                coalesce( (select sum(p.investWorksAmount) \n" +
			"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
			"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
			"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                   and hp.reasoncode = 4 \n" +
			"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
			"                   and hf.reasoncode = 5 \n" +
			"                   and hf.plannewrefcode = a2p.plancode \n" +
			"                   and a2p.actrefcode = r2a.actrefcode \n" +
			"                   and hf.plannewrefcode = p.code  \n" +
			"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
			"                   and rm.statusrefcode = 3  \n" +
			"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
			"                   and rpf.factcode = p.code  \n" +
			"                   and rpf.fkorderitemcode = fi.code  \n" +
			"                   and fi.fkorderrefcode = f.code \n" +
			"                   and f.kindcode = 14 \n" +
			"                   and f.statuscode in (3,4,5) \n" +
			"                   and p.statuscode  = 3  \n" +
			"                   and ipp.ipitemrefcode = ipitemcode \n" +
			"                  ),0) \n" +
			"            /* освоение Акт вып работ , услуги со стороны */ \n" +
			"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
			"               coalesce( (select sum(p.investWorksAmount) \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
			"                      , rqfkorder f  \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and rpf.factcode = p.code  \n" +
			"                 and rpf.fkorderitemcode = fi.code  \n" +
			"                 and fi.fkorderrefcode = f.code \n" +
			"                 and f.kindcode = 14 \n" +
			"                 and f.statuscode in (3,4,5)  \n" +
			"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                ),0)  \n" +
			"            /* освоение счетчики , кол-во сидит в план фактах sccounter под месячные планы на пункте ИП  */ \n" +
			"        /*  when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
			"                0  пока непонятно как по счетчикам считать использование */ \n" +
			"                /*coalesce( ( \n" +
			"                 select  count(scc.code)   \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
			"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and p.code = ei.planrefcode \n" +
			"                 and ei.accountingtyperefcode = 2 --счетчики \n" +
			"                 and scc.estimateitemrefcode = ei.code  \n" +
			"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
			"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
			"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
			"                 and scusageinputitem.sccode is not null \n" +
			"                 and scusageinputitem.kindrefcode in (1 --Введення у Експлуатацію \n" +
			"                                             ,4 -- Введення у експлуатацію ЗКУ \n" +
			"                                             ,5 -- Введення у експлуатацію  у складі ЗКУ \n" +
			"                                             )   \n" +
			"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
			"                 and scusageinput.statusrefcode = 3 -- ОЗ-1 проведений  \n" +
			"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
			"                  \n" +
			"                  \n" +
			"                ),0)*/ \n" ;

	        String selStr4_1 = " /*освоение приходны ордер */    \n" +
	        		"            when  ipimplementtyperefcode = 3  then                     \n" +
	        		"                      case when isUseMaterialOnIpItem > 0 then     \n" +
	        		"                        coalesce((select sum(coalesce(fi2ei.countgen,0))    \n" +
	        		"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
	        		"                          , rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
	        		"                           where p.kindcode = 2   \n" +
	        		"                           and bi2ei.billitemcode = bi.code   \n" +
	        		"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
	        		"                           and ei.kindrefcode in (1,6)   \n" +
	        		"                           and p.code = ei.planrefcode   \n" +
	        		"                           and ei.code = bi2ei.estimateitemcode               \n" +
	        		"                           and fi2ei.estimateitemcode = ei.code   \n" +
	        		"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
	        		"                           and fi.fkorderrefcode = f.code   \n" +
	        		"                           and f.kindcode in (1)   \n" +
	        		"                           and f.statuscode in (3,4,5)   \n" +
	        		"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
	        		"                           and p.code = sel_in1.planworkcode   \n" +
	        		"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
	        		"                                                  where pi2bi.billitemcode = bi.code   \n" +
	        		"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
	        		"                                                     and pi.paydocrefcode = pay.code   \n" +
	        		"                                           )   \n" +
	        		"      \n" +
	        		"                           \n" +
	        		"                          and fi.materialcode in  ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )   \n" +
	        		"                                          \n" +
	        		"                           \n" +
	        		"                            ) ,0)  \n" +
	        		"                             \n" +
	        		"                            + /* излишки */ \n" +
	        		"                            coalesce((select sum(coalesce(rqfkorderitemremainder.countgen,0))    \n" +
	        		"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
	        		"                          , rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
	        		"                           where p.kindcode = 2   \n" +
	        		"                           and bi2ei.billitemcode = bi.code   \n" +
	        		"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
	        		"                           and ei.kindrefcode in (1,6)   \n" +
	        		"                           and p.code = ei.planrefcode   \n" +
	        		"                           and ei.code = bi2ei.estimateitemcode               \n" +
	        		"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
	        		"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
	        		"                           and fi.fkorderrefcode = f.code   \n" +
	        		"                           and f.kindcode in (1)   \n" +
	        		"                           and f.statuscode in (3,4,5)   \n" +
	        		"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
	        		"                           and p.code = sel_in1.planworkcode   \n" +
	        		"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
	        		"                                                  where pi2bi.billitemcode = bi.code   \n" +
	        		"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
	        		"                                                     and pi.paydocrefcode = pay.code   \n" +
	        		"                                           )   \n" +
	        		"      \n" +
	        		"                          and fi.materialcode in  ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )  \n" +
	        		"                                         \n" +
	        		"                            ) ,0) \n" +
	        		"                       else \n" +
	        		"                          coalesce((select sum(coalesce(fi2ei.countgen,0))    \n" +
	        		"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
	        		"                          , rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
	        		"                           where p.kindcode = 2   \n" +
	        		"                           and bi2ei.billitemcode = bi.code   \n" +
	        		"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
	        		"                           and ei.kindrefcode in (1,6)   \n" +
	        		"                           and p.code = ei.planrefcode   \n" +
	        		"                           and ei.code = bi2ei.estimateitemcode               \n" +
	        		"                           and fi2ei.estimateitemcode = ei.code   \n" +
	        		"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
	        		"                           and fi.fkorderrefcode = f.code   \n" +
	        		"                           and f.kindcode in (1)   \n" +
	        		"                           and f.statuscode in (3,4,5)   \n" +
	        		"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
	        		"                           and p.code = sel_in1.planworkcode   \n" +
	        		"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
	        		"                                                  where pi2bi.billitemcode = bi.code   \n" +
	        		"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
	        		"                                                     and pi.paydocrefcode = pay.code   \n" +
	        		"                                           )   \n" +
	        		"                            ) ,0)  \n" +
	        		"                             \n" +
	        		"                            + /* излишки */ \n" +
	        		"                            coalesce((select sum(coalesce(rqfkorderitemremainder.countgen,0))    \n" +
	        		"                           from enplanwork p , enestimateitem ei  , rqbillitem2enestimattm bi2ei , rqbillitem bi     \n" +
	        		"                          , rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
	        		"                           where p.kindcode = 2   \n" +
	        		"                           and bi2ei.billitemcode = bi.code   \n" +
	        		"                           and bi.ddscodestxt = '312 100' /*выполнено по ддс ип */   \n" +
	        		"                           and ei.kindrefcode in (1,6)   \n" +
	        		"                           and p.code = ei.planrefcode   \n" +
	        		"                           and ei.code = bi2ei.estimateitemcode               \n" +
	        		"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
	        		"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
	        		"                           and fi.fkorderrefcode = f.code   \n" +
	        		"                           and f.kindcode in (1)   \n" +
	        		"                           and f.statuscode in (3,4,5)   \n" +
	        		"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
	        		"                           and p.code = sel_in1.planworkcode   \n" +
	        		"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
	        		"                                                  where pi2bi.billitemcode = bi.code   \n" +
	        		"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
	        		"                                                     and pi.paydocrefcode = pay.code   \n" +
	        		"                                           )   \n" +
	        		"                            ) ,0 ) \n" +
	        		"                         \n" +
	        		"                       end \n" +
	        		"  \n" +
	        		"  /*освоение кол-во zku */  \n" +
	        		"        when typerefcode in ( 106, 111, 112) then  \n" +
	        		"        coalesce( (select count( p.code)  \n" +
	        		"                              from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" +
	        		"                              , enplanwork p , scusageinputitemoz2nct , scusageinputitemoz , scusageinputitem , scusageinput   \n" +
	        		"                              \n" +
	        		"                              where  hp.planoldrefcode = ipp.planrefcode  \n" +
	        		"                              and hp.planoldrefcode = sel_in1.planworkcode   \n" +
	        		"                              and hp.reasoncode = 4  \n" +
	        		"                              and hp.plannewrefcode = hf.planoldrefcode  \n" +
	        		"                              and hf.reasoncode = 5  \n" +
	        		"                              and hf.plannewrefcode = a2p.plancode  \n" +
	        		"                              and hf.plannewrefcode = p.code   \n" +
	        		"                              and p.statuscode  = 3  \n" +
	        		"                              and a2p.actrefcode = scusageinputitemoz2nct.enactrefcode \n" +
	        		"                              and scusageinputitemoz.code = scusageinputitemoz2nct.usageinputitemozrefcod \n" +
	        		"                              and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code \n" +
	        		"                              and scusageinput.code = scusageinputitem.usageinputrefcode \n" +
	        		"                              and scusageinput.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
	        		" /*new*/ and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" +
                    " scusageinput.dategen >= sel_in1.minDateOrderInvest \n" +    
                    " else 1=1  \n" +
                    " end /*new*/ \n" +
	        		"                              and scusageinput.statusrefcode = 3                                \n" +
	        		"                              and ipp.ipitemrefcode = ipitemcode  \n" +
	        		"                           ),0)  \n" +
					"	/*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" +
					"	when ipimplementtyperefcode = 4 then \n" +
					"	/*выберем кол-во счетчика который подвязан под пункт */  \n" +
					"	coalesce( (select count(distinct sccounter.code)  \n" +
					"	                    from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" + 
					"	                    , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
					"	                    where  hp.planoldrefcode = ipp.planrefcode  \n" +
					"	                    and hp.planoldrefcode = sel_in1.planworkcode   \n" +
					"	                    and hp.reasoncode = 4  \n" +
					"	                    and hp.plannewrefcode = hf.planoldrefcode \n" +  
					"	                    and hf.reasoncode = 5  \n" +
					"	                    and hf.plannewrefcode = a2p.plancode   \n" +
					"	                    and hf.plannewrefcode = p.code    \n" +
					"	                    and p.statuscode  = 3  \n" +
					"	                    and enestimateitem.planrefcode = p.code  \n" + 
					"	                    and enestimateitem.code = sccounter.estimateitemrefcode  \n" + 
					"	                    and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode \n" +
					"	                      																							 and coalesce(et.ismaterialforcount,0) > 0 ) \n" + 
					"	                    and a2p.actrefcode = act.code \n" + 
					"	                    and enestimateitem.kindrefcode in (1,2) \n" +
					"	                    and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
					"	                    /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" + 
					"	                       and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
					"	                        act.dateact >= sel_in1.minDateOrderInvest      \n" +
					"	                       else 1=1  \n" +
					"	                       end \n" +
					"	                    and ipp.ipitemrefcode = ipitemcode \n" +  
					"	                 ),0)   \n" +
					"	                 + \n" +
					"	 coalesce((  \n" +
					"	         select count( distinct coalesce( fin.code, 0)) as count \n" + 
					"	         from enplancorrecthistory phf,  \n" +
					"	              enestimateitem eifact, \n" +
					"	              finmaterials fin, \n" +
					"	              enact2enplanwork a2p, \n" +                        
					"	              enplanwork pfact , \n" +
					"	              enact aa \n" +
					" , tempnomenclatures tn \n" + 
					"	         where phf.plannewrefcode = pfact.code and \n" + 
					"                  tn.mat_id = fin.mat_id  and \n" +
					"	               phf.plannewrefcode = eifact.planrefcode and \n" +
					"	               eifact.kindrefcode in (1, 2) and \n" +
					"	               eifact.countfact > 0 and \n" +
					"	               eifact.code = fin.estimateitemrefcode and \n" +
					"	               fin.statusrefcode = 1 and \n" +
					"	               pfact.code = eifact.planrefcode and \n" +
					"	               pfact.code = a2p.plancode and      \n" +                    
					"	               pfact.statuscode = 3 and \n" +
					"	               aa.code =a2p.actrefcode and \n" +
					"	               aa.dateact <= sel_in1.datereport  /*дата освоения материалов */ \n" +
					"	               /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
					"	               and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then  \n" +
					"	                aa.dateact >= sel_in1.minDateOrderInvest      \n" +
					"	               else 1=1 \n" +
					"	               end \n" +
					"	               and phf.reasoncode = 5 \n" +  
					"	               and tn.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode \n" + 
					"	               																						and coalesce(et.ismaterialforcount,0) > 0 ) \n" +
					"	               and phf.planoldrefcode in ( \n" + 
					"	                                       select ph.plannewrefcode \n" + 
					"	                                       from enplancorrecthistory ph  \n" +
					"	                                       where ph.planoldrefcode =  sel_in1.planworkcode and \n" +  
					"	                                             ph.reasoncode = 4 \n" +
					"	               )  \n" +
					"	), 0)   \n" +
					"	 /*zzzend*/ \n" +
			"                         \n" +
			"       else 0  \n" +
			"  end          \n" +
			"     \n" +
			" ,0) as osvoeno_count_by_plan  \n" +
			"  \n" +
			" /* osvoenie summa */   \n" ;

			String selStr5 = " ,coalesce( \n" +
			"   case when  ipimplementtyperefcode = 1 and  typerefcode = 5 then /* сумма выберается с финматериалс на фактах связанными с актами вып работ и  \n" +
			" оз-2  */ \n" +
			"               case when isUseMaterialOnIpItem > 0 then  \n" +
			"                   (select sum(fi2ei.countgen * fi.pricewithoutnds) \n" +
			"                         from enplanwork p , enestimateitem ei , rqbillitem2enestimattm bi2ei , rqbillitem bi              \n" +
			"                        ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f           \n" +
			"                         where p.kindcode = 2 \n" +
			"                         and bi2ei.billitemcode = bi.code \n" +
			"                         and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */ \n" +
			"                         and ei.kindrefcode in (1,6) \n" +
			"                         and p.code = ei.planrefcode \n" +
			"                         and ei.code = bi2ei.estimateitemcode             \n" +
			"                         and fi2ei.estimateitemcode = ei.code \n" +
			"                         and fi2ei.fkorderitemrefcode = fi.code \n" +
			"                         and fi.fkorderrefcode = f.code \n" +
			"                         and f.kindcode in (8,9,11,12,21) \n" +
			"                         and f.statuscode in (3,4,5) \n" +
			"                         and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                         and p.code = sel_in1.planworkcode \n" +
			"                         and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay \n" +
			"                                                where pi2bi.billitemcode = bi.code \n" +
			"                                                   and pi2bi.paydocitemcode = pi.code \n" +
			"                                                   and pi.paydocrefcode = pay.code \n" +
			"                                         ) \n" +
			"  \n" +
			"                         and ei.materialrefcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode ) \n" +
			"                                     \n" +
			"                          ) \n" +
			"                     else  /* на пункте ИП материала нет - выберем сумму материалов из фактов*/   \n" +
			"                    \n" +

						/* SUPP-40428  если хоз способ  то - освоение сумма учитывать материалы + бензин на всех фактах сделанных из месячного плана и сидящих в реконстр модернизации
						   "                      coalesce(  (select sum(coalesce(fin.cost,0)) as cost  \n" +
						"                     from enestimateitem eiplan , enestimateitem2nstmttm e2e  , enestimateitem2nstmttm e2e2  ,  enestimateitem eifact  \n" +
						"                     , rqbillitem2enestimattm bi2eiplan , rqbillitem bi ,  finmaterials fin , enplanwork pfact , enact2enplanwork a2p  \n" +
						"                     , enreconstrmodernoz2nct r2a , enreconstrmodernoz rm \n" +
						"                     where eiplan.planrefcode = sel_in1.planworkcode \n" +
						"                     and eiplan.code = e2e.estimateiteminrefcode  \n" +
						"                     and e2e.typerefcode = 1  \n" +
						"                     and e2e.estimateitemoutrefcode = e2e2.estimateiteminrefcode   \n" +
						"                     and e2e2.typerefcode = 1 \n" +
						"                     and e2e2.estimateitemoutrefcode = eifact.code \n" +
						"                     and eifact.countfact > 0 \n" +
						"                     and eiplan.code = bi2eiplan.estimateitemcode \n" +
						"                     and bi2eiplan.billitemcode = bi.code  \n" +
						"                     and bi.ddscodestxt = '312 100' \n" +
						"                     and eifact.code = fin.estimateitemrefcode \n" +
						"                     and fin.statusrefcode = 1 \n" +
						"                     and pfact.code = eifact.planrefcode  \n" +
						"                     and pfact.code = a2p.plancode  \n" +
						"                     and a2p.actrefcode = r2a.actrefcode \n" +
						"                     and pfact.statuscode = 3  \n" +
						"                     and r2a.enreconstrmodernozrfcd = rm.code  \n" +
						"                     and rm.statusrefcode = 3 \n" +
						"                     and rm.dategen <= sel_in1.datereport --дата освоения материалов  \n" + // 02.03.2015 не учитывалась дата среза репорта
						"                      \n" +
						"                     ) ,0) \n" +*/

						"  coalesce(( \n" +
						"                   select sum(coalesce(fin.cost, 0)) as cost \n" +
						"                   from enplancorrecthistory phf, \n" +
						"                        enestimateitem eifact, \n" +
						"                        finmaterials fin, \n" +
						"                        enact2enplanwork a2p, \n" +
						"                        enreconstrmodernoz2nct r2a, \n" +
						"                        enreconstrmodernoz rm, \n" +
						"                        enplanwork pfact \n" +
						"                   where phf.plannewrefcode = pfact.code and \n" +
						"                         phf.plannewrefcode = eifact.planrefcode and \n" +
						"                         eifact.kindrefcode in (1 /*, 2 SUPP-107862 */ ) and \n" +
						"                         eifact.countfact > 0 and \n" +
						"                         eifact.code = fin.estimateitemrefcode and \n" +
						"                         fin.statusrefcode = 1 and \n" +
						"                         pfact.code = eifact.planrefcode and \n" +
						"                         pfact.code = a2p.plancode and \n" +
						"                         a2p.actrefcode = r2a.actrefcode and \n" +
						"                         pfact.statuscode = 3 and \n" +
						"                         r2a.enreconstrmodernozrfcd = rm.code and \n" +
						"                         rm.statusrefcode = 3 and \n" +
						"                         rm.dategen <= sel_in1.datereport  /*дата освоения материалов */ \n" +
						"                         and \n" +
						"                         phf.reasoncode = 5 and \n" +
						"                         phf.planoldrefcode in ( \n" +
						"                                                 select ph.plannewrefcode \n" +
						"                                                 from enplancorrecthistory ph \n" +
						"                                                 where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
						"                                                       ph.reasoncode = 4 \n" +
						"                         ) \n" +
						"        ), 0) \n" +
						"  \n" +


			"                      \n" +
			"                end  \n" +
			"              /* освоение ОЗ , услуги со стороны сумма */ \n" +
			"          when  ipimplementtyperefcode = 1 and  typerefcode = 20 then   \n" +
			"                coalesce( (select sum(fi.sumwithoutnds) \n" +
			"                   from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p , enreconstrmodernoz2nct r2a  \n" +
			"                   , enplanwork p , enreconstrmodernoz rm , rqfkorder2planfact rpf , rqfkorderitem fi , rqfkorder f \n" +
			"                   where  hp.planoldrefcode = ipp.planrefcode \n" +
			"                   and hp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                   and hp.reasoncode = 4 \n" +
			"                   and hp.plannewrefcode = hf.planoldrefcode \n" +
			"                   and hf.reasoncode = 5 \n" +
			"                   and hf.plannewrefcode = a2p.plancode \n" +
			"                   and a2p.actrefcode = r2a.actrefcode \n" +
			"                   and hf.plannewrefcode = p.code  \n" +
			"                   and r2a.enreconstrmodernozrfcd = rm.code  \n" +
			"                   and rm.statusrefcode = 3  \n" +
			"                   and rm.dategen <= sel_in1.datereport /*дата освоения материалов/ услуг */ \n" +
			"                   and rpf.factcode = p.code  \n" +
			"                   and rpf.fkorderitemcode = fi.code  \n" +
			"                   and fi.fkorderrefcode = f.code \n" +
			"                   and f.kindcode = 14 \n" +
			"                   and f.statuscode in (3,4,5) \n" +
			"                   and p.statuscode  = 3  \n" +
			"                   and ipp.ipitemrefcode = ipitemcode \n" +
			"                  ),0) \n" +
			"                /* освоение Акт вып работ , услуги со стороны сумма  */ \n" +
			"          when  ipimplementtyperefcode = 2 and  typerefcode = 20 then \n" +
			"               coalesce( (select sum(fi.sumwithoutnds) \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , rqfkorder2planfact rpf , rqfkorderitem fi \n" +
			"                      , rqfkorder f  \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and rpf.factcode = p.code  \n" +
			"                 and rpf.fkorderitemcode = fi.code  \n" +
			"                 and fi.fkorderrefcode = f.code \n" +
			"                 and f.kindcode = 14 \n" +
			"                 and f.statuscode in (3,4,5)  \n" +
			"                 and f.dategen <= sel_in1.datereport /*дата освоения материалов */ \n" +
			"                ),0)  \n" +
			"             /* освоение счетчики , сумма из sccounter - через план факт  */ \n" +
			"       /*   when  ipimplementtyperefcode = 1 and  typerefcode in (100, 106, 111, 112) then \n" +
			"                0  пока непонятно как по счетчикам считать использование */ \n" +
			"                /*coalesce( ( \n" +
			"                 select  sum(scc.cost)   \n" +
			"                 from enplancorrecthistory chp ,  enplancorrecthistory chpfact ,enplanwork p , enestimateitem ei  \n" +
			"                     , sccounter scc , scusageinputtmz2sccntr , scusageinputitemoz , scusageinputitem , scusageinput \n" +
			"                 where chp.reasoncode = 4 \n" +
			"                 and chp.plannewrefcode = chpfact.planoldrefcode \n" +
			"                 and chpfact.reasoncode = 5 \n" +
			"                 and p.code = chpfact.plannewrefcode \n" +
			"                 and p.statuscode = 3 \n" +
			"                 and chp.planoldrefcode = sel_in1.planworkcode  \n" +
			"                 and p.code = ei.planrefcode \n" +
			"                 and ei.accountingtyperefcode = 2 -- счетчики \n" +
			"                 and scc.estimateitemrefcode = ei.code  \n" +
			"                 and scusageinputtmz2sccntr.sccounterrefcode = scc.code \n" +
			"                 and scusageinputitemoz.code = scusageinputtmz2sccntr.ozrefcode \n" +
			"                 and scusageinputitemoz.usageinputitemrefcode = scusageinputitem.code  \n" +
			"                 and scusageinputitem.sccode is not null \n" +
			"                 and scusageinputitem.kindrefcode in (1 -- Введення у Експлуатацію \n" +
			"                                             ,4 -- Введення у експлуатацію ЗКУ \n" +
			"                                             ,5 -- Введення у експлуатацію  у складі ЗКУ \n" +
			"                                             )   \n" +
			"                 and scusageinputitem.usageinputrefcode = scusageinput.code  \n" +
			"                 and scusageinput.statusrefcode = 3 -- ОЗ-1 проведений  \n" +
			"                 and scusageinput.dategen <= sel_in1.datereport  \n" +
			"                  \n" +
			"                  \n" +
			"                ),0)  */        \n" ;

			String selStr5_1 = " when  ipimplementtyperefcode = 3 then /* сумма с прих ордера   */  \n" +
					"                    case when isUseMaterialOnIpItem > 0 then \n" +
					"                        coalesce( (select sum((coalesce(fi2ei.countgen,0) ) * fi.pricewithoutnds)  \n" +
					"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
					"                          ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
					"                           where p.kindcode = 2   \n" +
					"                           and bi2ei.billitemcode = bi.code   \n" +
					"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
					"                           and ei.kindrefcode in (1,6)   \n" +
					"                           and p.code = ei.planrefcode   \n" +
					"                           and ei.code = bi2ei.estimateitemcode               \n" +
					"                           and fi2ei.estimateitemcode = ei.code   \n" +
					"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
					"                           and fi.fkorderrefcode = f.code   \n" +
					"                           and f.kindcode in (1)   \n" +
					"                           and f.statuscode in (3,4,5)   \n" +
					"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
					"                           and p.code = sel_in1.planworkcode   \n" +
					"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
					"                                                  where pi2bi.billitemcode = bi.code   \n" +
					"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
					"                                                     and pi.paydocrefcode = pay.code   \n" +
					"                                           )   \n" +
					"    \n" +
					"                          and fi.materialcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )                         \n" +
					"    \n" +
					"                           \n" +
					"                                         \n" +
					"                            ) ,0) \n" +
					"                           + /*излишки */  \n" +
					"                            \n" +
					"                           coalesce( (select sum((coalesce(rqfkorderitemremainder.countgen,0) ) * fi.pricewithoutnds)  \n" +
					"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
					"                          ,rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
					"                           where p.kindcode = 2   \n" +
					"                           and bi2ei.billitemcode = bi.code   \n" +
					"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
					"                           and ei.kindrefcode in (1,6)   \n" +
					"                           and p.code = ei.planrefcode   \n" +
					"                           and ei.code = bi2ei.estimateitemcode               \n" +
					"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
					"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
					"                           and fi.fkorderrefcode = f.code   \n" +
					"                           and f.kindcode in (1)   \n" +
					"                           and f.statuscode in (3,4,5)   \n" +
					"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
					"                           and p.code = sel_in1.planworkcode   \n" +
					"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
					"                                                  where pi2bi.billitemcode = bi.code   \n" +
					"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
					"                                                     and pi.paydocrefcode = pay.code   \n" +
					"                                           )   \n" +
					"                          and fi.materialcode in ( select ipi2m.materialrefcode from enipitem2tkmaterials ipi2m where ipi2m.ipitemrefcode = sel_in1.ipitemcode )                         \n" +
					"    \n" +
					"                         ) ,0)   \n" +
					"                       else \n" +
					"                          coalesce( (select sum((coalesce(fi2ei.countgen,0) ) * fi.pricewithoutnds)  \n" +
					"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
					"                          ,rqfkorderitem2enstmttm fi2ei , rqfkorderitem fi , rqfkorder f             \n" +
					"                           where p.kindcode = 2   \n" +
					"                           and bi2ei.billitemcode = bi.code   \n" +
					"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
					"                           and ei.kindrefcode in (1,6)   \n" +
					"                           and p.code = ei.planrefcode   \n" +
					"                           and ei.code = bi2ei.estimateitemcode               \n" +
					"                           and fi2ei.estimateitemcode = ei.code   \n" +
					"                           and fi2ei.fkorderitemrefcode = fi.code   \n" +
					"                           and fi.fkorderrefcode = f.code   \n" +
					"                           and f.kindcode in (1)   \n" +
					"                           and f.statuscode in (3,4,5)   \n" +
					"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
					"                           and p.code = sel_in1.planworkcode   \n" +
					"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
					"                                                  where pi2bi.billitemcode = bi.code   \n" +
					"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
					"                                                     and pi.paydocrefcode = pay.code   \n" +
					"                                           )   \n" +
					"                            ) ,0) \n" +
					"                           + /*излишки */  \n" +
					"                            \n" +
					"                           coalesce( (select sum((coalesce(rqfkorderitemremainder.countgen,0) ) * fi.pricewithoutnds)  \n" +
					"                           from enplanwork p , enestimateitem ei  ,rqbillitem2enestimattm bi2ei , rqbillitem bi                \n" +
					"                          ,rqfkorderitemremainder , rqfkorderitem fi , rqfkorder f             \n" +
					"                           where p.kindcode = 2   \n" +
					"                           and bi2ei.billitemcode = bi.code   \n" +
					"                           and bi.ddscodestxt = '312 100' /* выполнено по ддс ип */   \n" +
					"                           and ei.kindrefcode in (1,6)   \n" +
					"                           and p.code = ei.planrefcode   \n" +
					"                           and ei.code = bi2ei.estimateitemcode               \n" +
					"                           and rqfkorderitemremainder.estimateitemrefcode = ei.code   \n" +
					"                           and rqfkorderitemremainder.fkorderitemrefcode = fi.code   \n" +
					"                           and fi.fkorderrefcode = f.code   \n" +
					"                           and f.kindcode in (1)   \n" +
					"                           and f.statuscode in (3,4,5)   \n" +
					"                           and f.dategen <= sel_in1.datereport /*дата освоения материалов */   \n" +
					"                           and p.code = sel_in1.planworkcode   \n" +
					"                           and bi.code in ( select pi2bi.billitemcode from rqpaydocitem2billitem pi2bi , rqpaydocitem pi , rqpaydoc pay   \n" +
					"                                                  where pi2bi.billitemcode = bi.code   \n" +
					"                                                     and pi2bi.paydocitemcode = pi.code   \n" +
					"                                                     and pi.paydocrefcode = pay.code   \n" +
					"                                           )   \n" +
					"                         ) ,0) \n" +
					"                       end  \n" +
					"  \n" +
					 
					" /*zzzstart*/  /*Освоение Акт вып.работ(хоз.способ) */ \n" +
					"   when ipimplementtyperefcode = 4 then  \n" +
					"   /*выберем сумму счетчика который подвязан под пункт */ \n" + 
					" coalesce( (select ( select  co.cost From rqfkorder as fo \n" + 
					"  inner join rqfkorderitem as fi on fi.fkorderrefcode = fo.code  \n" +
					" inner join scinvoice as inv on inv.fkorderitemrefcode = fi.code  \n" +
					" inner join scorder as o on inv.code = o.invoicerefcode  \n" +
					" inner join scorder2counter as oco on o.code = oco.scorderrefcode  \n" +
					" inner join sccounter as co on oco.counterrefcode = co.code  \n" +
					" where co.invnumber = sccounter.invnumber   \n" +
					" order by fo.dategen asc  \n" +
					" limit 1 ) as cost_piobr \n" +
					"                       from enplancorrecthistory hp ,  enplancorrecthistory hf , enipitem2plan ipp , enact2enplanwork a2p \n" + 
					"                       , enplanwork p ,  sccounter , enestimateitem , enact act \n" +
					"                       where  hp.planoldrefcode = ipp.planrefcode  \n" +
					"                       and hp.planoldrefcode = sel_in1.planworkcode   \n" +
					"                       and hp.reasoncode = 4  \n" +
					"                       and hp.plannewrefcode = hf.planoldrefcode \n" +  
					"                       and hf.reasoncode = 5  \n" +
					"                       and hf.plannewrefcode = a2p.plancode \n" +  
					"                       and hf.plannewrefcode = p.code    \n" +
					"                       and p.statuscode  = 3  \n" +
					"                       and enestimateitem.kindrefcode = 1 \n" +
					"                       and enestimateitem.planrefcode = p.code \n" +  
					"                       and enestimateitem.code = sccounter.estimateitemrefcode \n" + 
					"                       and enestimateitem.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
					"                       and a2p.actrefcode = act.code \n" + 
					"                       and act.dateact <= sel_in1.datereport /*дата освоения материалов */ \n" +
					"                       /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
					"                      and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
					"                       act.dateact >= sel_in1.minDateOrderInvest     \n" + 
					"                      else 1=1 \n" +
					"                      end \n" +
					"                       and ipp.ipitemrefcode = ipitemcode \n" +  
					"                    limit 1 ),0)  \n" +
					"                    + \n" +
					"    coalesce(( \n" + 
					"            select sum(coalesce(fin.cost, 0)) as cost \n" +
					"            from enplancorrecthistory phf,  \n" +
					"                 enestimateitem eifact,  \n" +
					"                 finmaterials fin,  \n" +
					"                 enact2enplanwork a2p,   \n" +                       
					"                 enplanwork pfact , \n" +
					"                 enact aa \n" +
					"            where phf.plannewrefcode = pfact.code and \n" +
					"                  phf.plannewrefcode = eifact.planrefcode and \n" +
					"                  eifact.kindrefcode in (1/*, 2 SUPP-107862 */) and \n" +
					"                  eifact.countfact > 0 and \n" +
					"                  eifact.code = fin.estimateitemrefcode and \n" +
					"                  fin.statusrefcode = 1 and \n" +
					"                  pfact.code = eifact.planrefcode and \n" +
					"                  pfact.code = a2p.plancode and  \n" +                        
					"                  pfact.statuscode = 3 and \n" +
					"                  aa.code =a2p.actrefcode and  \n" +
					"                  aa.dateact <= sel_in1.datereport  /*дата освоения материалов */  \n" +
					"                 /*условие только для раздела 2 пуункта 2 •	в стовбець R «Вартість тис. грн. без ПДВ» заносилися дані тільки по вартості матеріалів, що вказані у вкладці «Матеріали», з дати оприбуткування першого ордеру по закупці матеріалу «ШАФА ВВІДНО-ОБЛІКОВА 1Ф З КОМП. МЕТІЗІВ ТА АВТОМАТИЧНИМ ВИМИКАЧЕМ 25А» по коду ДДС 312 100 (інвестпрограма); */ \n" +
					"                  and case when (invgrouprefcode = 2 and (itemnumber = '2' or  itemnumber = '3' ) ) then \n" + 
					"                   aa.dateact >= sel_in1.minDateOrderInvest     \n" +
					"                  else 1=1 \n" +
					"                  end \n" +
					"                  and phf.reasoncode = 5 \n" +  
					"                  and eifact.materialrefcode in ( select et.materialrefcode from enipitem2tkmaterials et where et.ipitemrefcode = ipitemcode ) \n" +
					"                  and phf.planoldrefcode in ( \n" + 
					"                                          select ph.plannewrefcode \n" + 
					"                                          from enplancorrecthistory ph  \n" +
					"                                          where ph.planoldrefcode =  sel_in1.planworkcode and \n" +
					"                                                ph.reasoncode = 4 \n" +
					"                  ) \n" +
					"  ), 0)  \n" +
					"    /*zzzend*/ \n" +
			"   else 0                   \n" +
			"      \n" +
			"   end     \n" +
			" ,0) as osvoeno_summa_by_plan  \n" +
			"  \n" +
			", yeargenip " +
			" from (  \n" +
			" select \n" +
			"   ipi.code as ipitemcode  \n" +
			"  ,p.code as planworkcode  \n" +
			"  ,case when p.typerefcode in (100, 106, 111, 112) then 1 else 0 end as isCounters  \n" +
			"  ,p.typerefcode \n" +
			"  ,coalesce(p.ipimplementtyperefcode,ipi.ipimplementtyperefcode) as ipimplementtyperefcode   \n" +
			"  ,case when (select count(enipitem2tkmaterials.code) from enipitem2tkmaterials \n" +
			"              where enipitem2tkmaterials.ipitemrefcode = ipi.code  ) > 0    then 1 else 0 end as isUseMaterialOnIpItem \n" +
			"  , kvartal.rep_kvartal  \n" +
			"  ,datereport.datereport  \n" +
			"  , " + enipitemcode +" as enipitemcode             \n" +
			"  , ip.yeargen as yeargenip \n" +
			"  , ipi.invgrouprefcode \n" +
			"    , ipi.itemnumber \n" +
			"    , qOrder.minDateOrderInvest \n" + 
			" from  \n" +
			"  enipitem2plan ipi2p \n" +
			" ,enplanwork p   \n" +
			" ,enipitem ipi \n" +
			" ,( select "+ kvartal +" as rep_kvartal ) as kvartal  \n" +
			" ,( select to_date('"+datereport+"','dd.mm.yyyy') as datereport ) as datereport \n" +
			" , enip ip  \n" + 
			"  , ( select min(r.dategen) as minDateOrderInvest , " + enipitemcode +" as ipItemCode   from  \n" + 
			"         enestimateitem q1 , rqfkorderitem2enstmttm re , enplanwork e , rqfkorderitem ri , rqfkorder r  \n" + 
			"         , rqorderitem2enestimttm re2 , rqorderitem r2  \n" + 
			"         where q1.code = re.estimateitemcode  \n" + 
			"         and q1.materialrefcode in (select t.code from tkmaterials t where t.name like '%ШАФА%ВВІДНО-ОБЛІКОВА%' or t.name like '%ШАФА%ВВІДНО-ОБДЛІКОВА%' ) \n" +  
			"         and e.code = q1.planrefcode \n" +  
			"         and e.code in ( select ep.planrefcode from enipitem2plan ep where ep.ipitemrefcode=  " + enipitemcode +"    ) \n" +
			"         and re.fkorderitemrefcode =ri.code \n" +
			"         and ri.fkorderrefcode = r.code \n" +
			"         and re.estimateitemcode = re2.estimateitemcode \n" +
			"         and re2.orderitemcode = r2.code \n" +
			"          and r.kindcode in (1,15) \n" +
			"         and r2.ddscodescode = 180	/*ИНВЕСТПРОГРАММА*/ \n" + 
			"         and to_char(r.dategen,'yyyy') =  ( select i.yeargen::text from  enipitem ii , enip i  where ii.iprefcode = i.code    and  ii.code in (" + itcodesandchildcodes + " )  limit 1  )  \n" + 
			"  ) as qOrder \n" + 
			" where ipi2p.planrefcode = p.code  \n" +
			" and p.kindcode = 2 \n" +
			" and ipi2p.ipitemrefcode = ipi.code \n" +
			" and ipi.iprefcode = ip.code  \n" +
			//" and ipi.iprefcode =1 \n" +
			" and ipi.code in (" + itcodesandchildcodes + " ) \n" +
			" and qOrder.ipItemCode = ipi.code  \n" +
			"  \n" +
			"  \n" +
			" ) as sel_in1 \n" +
			"  \n" +
			" ) as sel_in2 \n" +
			"  \n" +
			" ) as sel_in3 \n" +
			"  \n" +
			" group by enipitemcode, /* параметр парент пункта ип */ \n" +
			" datereport , countgen , sumgen , price , infotenders \n" +
			"  \n" ;

			//tempSt = connection.prepareStatement(selStr1 + selStr2+ selStr3+selStr3_1+ selStr4+selStr4_1 + selStr5 + selStr5_1);
			tempSt = new LoggableStatement(connection, selStr1 + selStr2+ selStr3+selStr3_1+ selStr4+selStr4_1 + selStr5 + selStr5_1);
			
			String content =  connection.prepareStatement(selStr1 + selStr2+ selStr3+selStr3_1+ 
					selStr4+selStr4_1 + selStr5 + selStr5_1).toString(); //selStr1 + selStr2+ selStr3+selStr3_1+ selStr4+selStr4_1 + selStr5 + selStr5_1;
			String path = "C:/tezzzt.txt";
			try {
				Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

			
	        /*String reportPath = "C:/Users/yura/test.txt";
	        String fileName =  selStr1 + selStr2 + selStr3 + selStr3_1 + selStr4 + selStr4_1 +  selStr5 + selStr5_1;



	        FileOutputStream fop;
	        try {
	            fop = new FileOutputStream(reportPath);

	            // get the content in bytes
	            byte[] contentInBytes = fileName.getBytes();

	            fop.write(contentInBytes);
	            fop.flush();
	            fop.close();

	            System.out.println("Done sql save  C:/Users/yura/test.txt ");

	        } catch (FileNotFoundException e) {

	            e.printStackTrace();
	        } catch (IOException e) {

	            e.printStackTrace();
	        }*/
			  System.out.println("############### ip_sql "
 	        + "Executing query: " + ((LoggableStatement)tempSt).getQueryString());
	        rs = tempSt.executeQuery();

		    ArrayList rows   = new ArrayList();
		    int count = 0;

		    while(rs.next())
	        {
		    	count = count+1;

		    	BigDecimal profinans_count = new BigDecimal(0);
		    	BigDecimal profinans_sum = new BigDecimal(0);
	            BigDecimal profinans_price = new BigDecimal(0);

	            BigDecimal osvoeno_count = new BigDecimal(0);
	            BigDecimal osvoeno_sum = new BigDecimal(0);

	            profinans_count = rs.getBigDecimal(3);
	            profinans_sum = rs.getBigDecimal(4);

	            osvoeno_count = rs.getBigDecimal(6);
	            osvoeno_sum = rs.getBigDecimal(7);



	            /*if (profinans_count != null && profinans_count.doubleValue() > 0 && profinans_sum != null && profinans_sum.doubleValue() > 0 ){
	            	profinans_price = profinans_sum.divide(profinans_count).setScale(2, BigDecimal.ROUND_HALF_UP) ;
	            }*/

	            if (osvoeno_count != null && osvoeno_count.doubleValue() > 0 && osvoeno_sum != null && osvoeno_sum.doubleValue() > 0 ){
	            	profinans_price = osvoeno_sum.divide(osvoeno_count,2);
	            	profinans_price = profinans_price.setScale(2, BigDecimal.ROUND_HALF_UP) ;
	            }





		                HashMap hashMap = new HashMap();
		                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_PRICE, profinans_price);
		                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_COUNT, profinans_count);
		                hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_SUM, profinans_sum);
		                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_COUNT, rs.getBigDecimal(6));

		                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_SUM, rs.getBigDecimal(7));
		                hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_DOCS, rs.getString(5));
		                hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_COUNT, rs.getBigDecimal(8));
		                hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_SUM, rs.getBigDecimal(9));
		                hashMap.put(dsFinansirOsvoenieBalans.PERCENT, rs.getBigDecimal(10));
		                hashMap.put(dsFinansirOsvoenieBalans.CONTRAGENT, rs.getString(12));
		                hashMap.put(dsFinansirOsvoenieBalans.INFOTENDERS, rs.getString(11));


		             // --- FIRST DATA COLUMN
		                hashMap.put(dsFinansirOsvoenieBalans.ITEMNUMBER , itemnumber);
		                hashMap.put(dsFinansirOsvoenieBalans.ENIPPURPOSEPROGRAM ,enippurposeprogram);
		                hashMap.put(dsFinansirOsvoenieBalans.NAME_PRODUCT ,name_product);
		                hashMap.put(dsFinansirOsvoenieBalans.MEASUREMENTNAME ,measurementname);
		                hashMap.put(dsFinansirOsvoenieBalans.FINANCING ,financing);
		                hashMap.put(dsFinansirOsvoenieBalans.PRICE , price);
		                hashMap.put(dsFinansirOsvoenieBalans.COUNTGEN ,countgen);
		                hashMap.put(dsFinansirOsvoenieBalans.SUMGEN ,sumgen);
		                hashMap.put(dsFinansirOsvoenieBalans.PERIOD_COUNT ,period_count);
		                hashMap.put(dsFinansirOsvoenieBalans.PERIOD_SUM , period_sum);


		                rows.add(hashMap);

	        }


		    if (count == 0 ) {


		    	ENIPItem ipiObj = ipiDAO.getObject(enipitemcode);

		    	HashMap hashMap = new HashMap();
	            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_PRICE, new BigDecimal(0));
	            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_COUNT, new BigDecimal(0));
	            hashMap.put(dsFinansirOsvoenieBalans.PROFINANS_SUM, new BigDecimal(0));
	            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_COUNT, new BigDecimal(0));

	            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_SUM, new BigDecimal(0));
	            hashMap.put(dsFinansirOsvoenieBalans.OSVOENO_DOCS, "")  ;
	            hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_COUNT, /*ipiObj.countGen*/ period_count);
	            hashMap.put(dsFinansirOsvoenieBalans.NOT_FINANCED_SUM, /*ipiObj.sumGen*/ period_sum);
	            hashMap.put(dsFinansirOsvoenieBalans.PERCENT, null);
	            hashMap.put(dsFinansirOsvoenieBalans.CONTRAGENT, "");
	            hashMap.put(dsFinansirOsvoenieBalans.INFOTENDERS, infotenders);

	            // --- FIRST DATA COLUMN
	            hashMap.put(dsFinansirOsvoenieBalans.ITEMNUMBER , itemnumber);
	            hashMap.put(dsFinansirOsvoenieBalans.ENIPPURPOSEPROGRAM ,enippurposeprogram);
	            hashMap.put(dsFinansirOsvoenieBalans.NAME_PRODUCT ,name_product);
	            hashMap.put(dsFinansirOsvoenieBalans.MEASUREMENTNAME ,measurementname);
	            hashMap.put(dsFinansirOsvoenieBalans.FINANCING ,financing);
	            hashMap.put(dsFinansirOsvoenieBalans.PRICE , price);
	            hashMap.put(dsFinansirOsvoenieBalans.COUNTGEN ,countgen);
	            hashMap.put(dsFinansirOsvoenieBalans.SUMGEN ,sumgen);
	            hashMap.put(dsFinansirOsvoenieBalans.PERIOD_COUNT ,period_count);
	            hashMap.put(dsFinansirOsvoenieBalans.PERIOD_SUM , period_sum);


	            rows.add(hashMap);
		    }



		    return new dsFinansirOsvoenieBalans(rows.toArray());

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


   /**
    * возврат инфы по счетчику для договора по установке многотарифного учета
   * @throws PersistenceException **/
   public String getCounterForServicesObject(int soObjCode) throws PersistenceException
   {
        String infoCounter = "";
        try {

		JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
		ENGiveCounterDAO gcDao = new ENGiveCounterDAO(connection,userProfile);

		SCLogic scLogic = new SCLogic(connection, getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE), userProfile);

		SCCounterShortList scList = null;
		try {
			scList = scLogic.getCounterForServicesObject(soObjCode);
		} catch(Exception e) {
			/*SUPP-54646*/ return e.getMessage();
		}

		if (scList.totalCount > 0 ){
			infoCounter = scList.get(0).name +  "  Інв.№ =" + scList.get(0).invNumber
					                 + " Зав.№ = " + scList.get(0).buildNumber
					                 + " МОЛ = " + scList.get(0).molCode
					                 + " Рахунок = " + scList.get(0).account;
			     if (!scList.get(0).kindRefName.equals("")){
			    	 infoCounter = infoCounter + " (" + scList.get(0).kindRefName + ")";
			     }

			}
		else {
		// не нашли если, пошукаем в engivecounter SUPP-86175 счетчики что передаются заказчиком
		ENGiveCounterFilter gcFilter = new ENGiveCounterFilter();
		gcFilter.servicesObjectRef.code = soObjCode;
		ENGiveCounterShortList gcList =gcDao.getScrollableFilteredList(gcFilter,0,-1);
		for (int i = 0; i < gcList.totalCount; i++) {
			infoCounter = gcList.get(0).counterType
	                 + " Зав.№ = " + gcList.get(0).serialNumber
	                 + " МОЛ = " + gcList.get(0).molCode
	                 + " Лічильник замовника" ;
		}
		}

			System.out.println("getCounterForServicesObject report , services object code = " + soObjCode);

			return infoCounter;

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}


   /**
    * Функция возвращает строки для отчета "Звіт по послугам на сторону Стан виконання договорів"
 * @throws JRException
    *
    *
    * ***/
   public JRDataSource servicesRegistryPrint(
		     String datestart
		   , String datefinal
		   , String renCode
		   , int showStatusProveden
		   , int show_enservicescontractstts_terminated
		   , int isOnlyReplaсeCounter
		   , int OnlyOverdue
		   , String tkclassificationtypecodes
		   , JRDataSource jds
		   , int ServicesWithoutFact
		   ) throws JRException
	{

    if (jds != null ) {
    	ArrayList rows  = new ArrayList();

    	((JRMapArrayDataSource) jds).moveFirst();

    	 while(jds.next() )  ///
         {
    		JRDesignField field = new JRDesignField();


 	    	HashMap hashMap = new HashMap();
 	    	field.setName("contractnumberservices");
 	    	hashMap.put(ServicesDS.CONTRACTNUMBERSERVICES, jds.getFieldValue(field));
 	    	field.setName("contractdateservices");
            hashMap.put(ServicesDS.CONTRACTDATESERVICES, jds.getFieldValue(field));
            field.setName("servicesname");
            hashMap.put(ServicesDS.SERVICESNAME, jds.getFieldValue(field));
            field.setName("contragentname");
            hashMap.put(ServicesDS.CONTRAGENTNAME, jds.getFieldValue(field));
            field.setName("contragentaddress");
     		hashMap.put(ServicesDS.CONTRAGENTADDRESS, jds.getFieldValue(field));
     		field.setName("depname");
     		hashMap.put(ServicesDS.DEPNAME, jds.getFieldValue(field));
     		field.setName("statusname");
     		hashMap.put(ServicesDS.STATUSNAME, jds.getFieldValue(field));
     		field.setName("buh_status");
     		hashMap.put(ServicesDS.BUH_STATUS, jds.getFieldValue(field));
     		field.setName("typename");
     		hashMap.put(ServicesDS.TYPENAME, jds.getFieldValue(field));
     		field.setName("contragenttype");
     		hashMap.put(ServicesDS.CONTRAGENTTYPE, jds.getFieldValue(field));
     		field.setName("act");
     		hashMap.put(ServicesDS.ACT, jds.getFieldValue(field));
     		field.setName("executor_fact");
     		hashMap.put(ServicesDS.EXECUTOR_FACT, jds.getFieldValue(field));
     		field.setName("is_red");
     		hashMap.put(ServicesDS.IS_RED, jds.getFieldValue(field));
     		field.setName("is_yellow");
     		hashMap.put(ServicesDS.IS_YELLOW, jds.getFieldValue(field) );
     		field.setName("executeworkdate");
     		hashMap.put(ServicesDS.EXECUTEWORKDATE, jds.getFieldValue(field));
     		field.setName("dateinpay");
     		hashMap.put(ServicesDS.DATEINPAY, jds.getFieldValue(field));
     		field.setName("limit_date");
     		hashMap.put(ServicesDS.LIMIT_DATE, jds.getFieldValue(field));
     		field.setName("nameclassif_in_dogovor");
     		hashMap.put(ServicesDS.NAMECLASSIF_IN_DOGOVOR, jds.getFieldValue(field));
     		field.setName("sumtotal");
     		hashMap.put(ServicesDS.SUMTOTAL, jds.getFieldValue(field));
     		field.setName("calctotalcost");
     		hashMap.put(ServicesDS.CALCTOTALCOST, jds.getFieldValue(field));
     		field.setName("enservicescontractstts_code");
     		hashMap.put(ServicesDS.ENSERVICESCONTRACTSTTS_CODE, jds.getFieldValue(field));
     		field.setName("contractcode");
     		hashMap.put(ServicesDS.CONTRACTCODE, jds.getFieldValue(field));
     		field.setName("str_materials_code_counters");
     		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS, jds.getFieldValue(field));
     		field.setName("str_materials_code_counters_1f");
     		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS_1F, jds.getFieldValue(field));
     		field.setName("str_materials_code_counters_3f");
     		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS_3F, jds.getFieldValue(field));
     		field.setName("counter_for_service");
     		hashMap.put(ServicesDS.COUNTER_FOR_SERVICE, jds.getFieldValue(field));
     		field.setName("counter_phasity_on_service");
     		hashMap.put(ServicesDS.COUNTER_PHASITY_ON_SERVICE, jds.getFieldValue(field));
     		field.setName("counters_codes_for_contract");
     		hashMap.put(ServicesDS.COUNTERS_CODES_FOR_CONTRACT, jds.getFieldValue(field));
     		field.setName("personalaccountnumber");
     		hashMap.put(ServicesDS.PERSONALACCOUNTNUMBER, jds.getFieldValue(field));
     		field.setName("numdateordercounter");
     		hashMap.put(ServicesDS.NUMDATEORDERCOUNTER, jds.getFieldValue(field));
     		field.setName("termtxt");
     		hashMap.put(ServicesDS.TERMTXT, jds.getFieldValue(field));

            rows.add(hashMap);
         }

	//	return jds;
    	 return new ServicesDS(rows.toArray());
	}

	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");


	Statement tempSt = null;
	ResultSet rs = null;

	//net.sf.jasperreports.engine.ex
	//net.sf.jasperreports.engine.JRResultSetDataSource
	try {


	    ArrayList rows  = new ArrayList();
	    String query = "";

	    query = " select contractnumberservices	, \n" +
	    		" contractdateservices	, \n" +
	    		" servicesname	, \n" +
	    		" contragentname	, \n" +
	    		" contragentaddress, \n" + // 5
	    		" depname	, \n" +
	    		" case when statusname = 'Роботи не виконані'  and failure_work is not null then 'Роботи не виконані по причині:' ||failure_work \n" +
	    		"      when statusname = 'Роботи виконані' then statusname \n" +
	    		"     else statusname || '( '|| (select name_plan ||' статус ' || name_status_plan \n" +
	    		"           from ( \n" +
	    		"           select 'Завдання Факт' as name_plan , \n" +
	    		"           ps.name as name_status_plan  from \n" +
	    		"           enplanwork p ,  enplanworkstatus ps \n" +
	    		"           where p.elementrefcode = elementcode \n" +
	    		"           and p.kindcode =4 \n" +
	    		"           and p.statuscode = ps.code \n" +
	    		"           union all \n" +
	    		"           select 'Завдання План' as name_plan , \n" +
	    		"           ps.name as name_status_plan  from \n" +
	    		"           enplanwork p ,  enplanworkstatus ps \n" +
	    		"           where p.elementrefcode = elementcode \n" +
	    		"           and p.kindcode = 3 \n" +
	    		"           and p.statuscode = ps.code \n" +
	    		"           union all \n" +
	    		"           select 'Місячний План' as name_plan , \n" +
	    		"           ps.name as name_status_plan  from \n" +
	    		"           enplanwork p ,  enplanworkstatus ps \n" +
	    		"           where p.elementrefcode = elementcode \n" +
	    		"           and p.kindcode = 2 \n" +
	    		"           and p.statuscode = ps.code \n" +
	    		"           union all \n" +
	    		"           select 'Кошторис' as name_plan , \n" +
	    		"           ps.name as name_status_plan  from \n" +
	    		"           enplanwork p ,  enplanworkstatus ps \n" +
	    		"           where p.elementrefcode = elementcode \n" +
	    		"           and p.kindcode = 5 \n" +
	    		"           and p.statuscode = ps.code \n" +
	    		"           ) as selpl limit 1 ) \n" +
	    		"  \n" +
	    		"   ||')'  end \n" +
	    		"             as statusname , \n" +   // 7
	    		" failure_work, \n" +
	    		" buh_status	, \n" +
	    		" typename	, \n" +
	    		" contragenttype, \n" +
	    		" act	, \n" +
	    		" executor_fact, \n" +
	    		" is_red	, \n" +
	    		" 0 as is_yellow, \n" + // пока в желтый не красим
	    		" to_char(executeworkdate,'dd.mm.yyyy') as executeworkdate, \n" +
	    		" case when dateinpay is null and contragenttyperefcode = 2 then 'Бюджет' else dateinpay 	end  as dateinpay , \n" +
	    		" /*SUPP-32920 - если дог. с рез. по времени и дата выполнения по дог больше даты граничной то граничную дату примем как дата вып договора */ \n" +
	    		" /*to_char(case when isjobsbytime = 1 and limit_date < executeworkdate \n" +
	    		"    then executeworkdate else limit_date end ,'dd.mm.yyyy') as */ \n" +
	    		"    to_char( boundarydatework ,'dd.mm.yyyy') as limit_date  , \n" +    //  18
	    		"  \n" +
	    		" contragenttyperefcode, /*тип контрагента*/ \n" +
	    		" nameclassif_in_dogovor , \n" +
	    		" /*сума оплати */ \n" +
	    		" coalesce((select sum(p2s.sumtotal) from enpayment2so p2s where p2s.servicesobjectrefcode = contractcode ),0) as sumtotal \n" +
	    		" /*калькуляционная стоимость */ \n" +
	    		" , (select cct.totalcost from encalccontracttotal cct  where cct.planrefcode = (select p.code from enplanwork p , enservicesobject so \n" +
	    		"           																 \n" +
	    		" 		where p.elementrefcode = so.elementcode \n" +
	    		"                                                                                 and so.code=contractcode \n" +
	    		"                                                                                 and p.kindcode = 5 )  ) as calctotalcost \n" +
	    		" , /*статус договора если 8 т.е скасований то красить в серый */ \n" +
	    		" enservicescontractstts_code \n" +
	    		" , elementcode \n" +
	    		" , contractcode \n" +
	    		" , ( select string_agg( m2m.tkmaterialsoutrefcode::text ,',')  from tkmaterials2tkmaterils m2m \n" +
	    		" where m2m.tkmaterialsinrefcode in (2000001969,2000001970)  ) as str_materials_code_counters \n" +
	    		" , ( select string_agg( m2m.tkmaterialsoutrefcode::text ,',')  from tkmaterials2tkmaterils m2m \n" +
	    		" where m2m.tkmaterialsinrefcode in (2000001969)  ) as str_materials_code_counters_1f \n" +
	    		" , ( select string_agg( m2m.tkmaterialsoutrefcode::text ,',')  from tkmaterials2tkmaterils m2m \n" +
	    		" where m2m.tkmaterialsinrefcode in (2000001970)  ) as str_materials_code_counters_3f \n" +

//	    		", coalesce((select case when ei.materialrefcode = 2000001969 then 1" +
//	    		"                        when ei.materialrefcode = 2000001970 then 3 else 0 end from \n" +
//	    		"		enservicesobject so , enplanwork p , enestimateitem ei \n" +
//	    		"		where so.code = contractcode \n" +
//	    		"		and so.elementcode = p.elementrefcode \n" +
//	    		"		and p.code = ei.planrefcode  \n" +
//	    		"		and p.kindcode = 5 \n" +
//	    		"		and ei.kindrefcode = 1  \n" +
//	    		"		and ei.accountingtyperefcode = 2 limit 1  ),0) as counter_phasity_on_service \n" +
				/// фазность через привязку берем
                ", coalesce((select  case when m2m.tkmaterialsinrefcode = 2000001969 then 1    \n" +
				"when m2m.tkmaterialsinrefcode = 2000001970 then 3 else 0 end \n" +
				"from tkclassificationtype clt , tkmaterls2tkclssfctntp m2cl , tkmaterials2tkmaterils m2m  \n" +
				", enplanwork2classfctntp p2c , enplanwork p , enservicesobject so   \n" +
				"where clt.code = m2cl.classificationtyperfcd  \n" +
				"and m2cl.materialrefcode = m2m.tkmaterialsoutrefcode  \n" +
				"and p2c.classificationtyperfcd = clt.code  \n" +
				"and p2c.planrefcode = p.code  \n" +
				"and p.kindcode = 5 \n" +
				"and p.elementrefcode = so.elementcode  \n" +
				"and so.code = contractcode \n" +
				"group by m2m.tkmaterialsinrefcode limit  1 ),0) as counter_phasity_on_service\n" +

	    		//

	    		" ,(select string_agg(m2cl.materialrefcode::varchar,',') \n" +
	    		"   from tkclassificationtype clt , tkmaterls2tkclssfctntp m2cl , tkmaterials2tkmaterils m2m  \n" +
	    		"      , enplanwork2classfctntp p2c , enplanwork p , enservicesobject so   \n" +
	    		" where clt.code = m2cl.classificationtyperfcd  \n" +
	    		"   and m2cl.materialrefcode = m2m.tkmaterialsoutrefcode  \n" +
	    		"   and p2c.classificationtyperfcd = clt.code  \n" +
	    		"   and p2c.planrefcode = p.code  \n" +
	    		"   and p.kindcode = 5 \n" +
	    		"   and p.elementrefcode = so.elementcode  \n" +
	    		"   and so.code = contractcode \n" +
	    		"   group by so.code , so.contractnumberservices limit  1 ) as counters_for_contract \n" +
	    		"    , personalaccountnumber \n" +
	    		// номер дата ордера на перемещение счетчика
	    		",( select   f.numberdoc || ' / ' || to_char(f.dategen,'dd.mm.yyyy') as num_date_ord  from enplanwork p , enestimateitem ei , \n" +
	    		"		rqfkorderitem2enstmttm f2e , rqfkorderitem fi , \n" +
	    		"		rqfkorder f   \n" +
	    		"		where p.elementrefcode = elementcode \n" +
	    		"		and p.kindcode = 2  \n" +
	    		"		and ei.planrefcode = p.code \n" +
	    		"		and ei.accountingtyperefcode = 2 \n" +
	    		"		and ei.countfact > 0  \n" +
	    		"		and ei.code = f2e.estimateitemcode  \n" +
	    		"		and f2e.fkorderitemrefcode = fi.code  \n" +
	    		"		and fi.fkorderrefcode = f.code  \n" +
	    		"		and f.statuscode in (3,4,5) \n" +
	    		"		order by f.dategen desc limit 1 ) \n" +   // 31
	    		" , case when coalesce(regulation,0) = 0 and coalesce(term,0) >0 then term::text || '(робоч.)' \n" +
	            " when coalesce(regulation,0) = 1 and coalesce(term,0) >0 then term::text || '(календ.)'   \n" +
	    		" else ''  \n" +
	    		" end as termtxt, \n" +
	    		" contragentaddresswork \n" +

	    		"  from \n" +
	    		"  \n" +
	    		" ( \n" +
	    		"  \n" +
	    		" select so.boundarydatework  , so.personalaccountnumber ,  so.code as contractcode , \n" +
	    		"         ( select  string_agg(distinct ( tct.kod || ' ' || tct.name ),' , ') from enplanwork2classfctntp p2c, enplanwork p , tkclassificationtype tct \n" +
	    		"           where p2c.planrefcode = p.code \n" +
	    		"           and p2c.classificationtyperfcd = tct.code \n" +
	    		"           and p.kindcode = 5 \n" +
	    		"           and p.elementrefcode = so.elementcode \n" +
	    		"             ) as nameclassif_in_dogovor , \n" +
	    		"        so.contragenttyperefcode ,/*тип контрагента*/ \n" +
	    		"        so.contractnumberservices , \n" +
	    		"        to_char(so.contractdateservices,'dd.MM.yyyy') as contractdateservices, \n" +
	    		"        --so.contractnumber, \n" +
	    		"        --to_char(so.contractdate,'dd.MM.yyyy') as contractdate, \n" +
	    		"        so.name as servicesname, \n" +
	    		"        so.contragentname, \n" +
	    		"        so.contragentaddress, \n" +
	    		"        dep.name as depname, \n" +
	    		"  \n" ;
	    		String query2 =  "        /* робота виконана если по услуге есть акт вып работ и в хоть одном задание план из этого акта нету акта невыполнения   */ \n" +
	    		"        case when coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p \n" +
	    		"                              where enact.elementcode = so.elementcode \n" +
	    		"                                and enact.elementcode = p.elementrefcode \n" +
	    		"                                and p.kindcode = 3 \n" +
	    		"                                and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null \n" +
	    		"                                and enact.code = a2p.actrefcode \n" +
	    		"                           ),0) > 0 then \n" +
	    		"        'Роботи виконані' \n" +
	    		"            else 'Роботи не виконані' end as statusname, \n" +
	    		"        -- причины невыполнения работы \n" +
	    		"        ( select string_agg( distinct afr.name || '( акт№'||coalesce(af.numbergen,'') || case when af.dateact is not null then ' від '||to_char(af.dateact,'dd.mm.yyyy') else ''  \n" +
	    		" end ||')',',' ) \n" +
	    		"           from enplanwork2actfailure p2af , enplanwork p ,  enactfailure af , enactfailurereason afr \n" +
	    		"           where p2af.planrefcode = p.code \n" +
	    		"             and p.kindcode = 3 \n" +
	    		"             and p.elementrefcode = so.elementcode \n" +
	    		"             and p2af.actfailurerefcode = af.code \n" +
	    		"             and af.reasonrefcode = afr.code ) as failure_work  , \n" +
	    		"  \n" +
	    		"        sobjs.name as buh_status, \n" +
	    		"        ct.name as typename, \n" +
	    		"        ctype.name as contragenttype, \n" +
	    		"  \n" +
	    		"  \n" +
	    		"        ( select string_agg(distinct enact.numbergen || ' от ' || to_char(enact.dateact,'dd.MM.yyyy')||'('||actst.name||')',',') \n" +
	    		"           from enact , enactstatus actst , enact2enplanwork a2p \n" +
	    		"          where enact.statusrefcode = actst.code \n" +
	    		"            and enact.code = a2p.actrefcode \n" +
	    		"            and enact.elementcode = so.elementcode limit 1 ) as act, \n" +
	    		"  \n" +
	    		"        -- исполнитель факт \n" +
	    		"         ( select string_agg(distinct fex.name,',') \n" +
	    		"         from finexecutor fex , enplanwork p \n" +
	    		"         where p.elementrefcode = so.elementcode \n" +
	    		"         and fex.code = p.finexecutorcode \n" +
	    		"         and p.kindcode = 4 \n" +
	    		"         limit 1   ) as executor_fact \n" +
	    		"  \n" +
	    		/*" ,  -- IS_RED \n" +
	    		"     case when so.statusrefcode in (2,4) " +
	    		"      or so.contractstatusrefcode = 11 -- SUPP-34177 - проведенным считаются договора также со енерджинетовским статусом закритий " +
	    		" then 0 else  -- SUPP-33046 если провед в фин кол то не просроч \n" +
	    		"        case when so.contragenttyperefcode <> 2 then \n" +
	    		"          case when \n" +
	    		"  \n" +
	    		"             ( table_isjobsbytime.limit_date_by_pay < current_date \n" +
	    		"               and table_isjobsbytime.limit_date_by_pay >= so.executeworkdate \n" +
	    		"               and table_isjobsbytime.isjobsbytime =1) \n" +
	    		"               and datepay is not null \n" +
	    		"             or \n" +
	    		"             ( so.executeworkdate < current_date \n" +
	    		"               and table_isjobsbytime.limit_date_by_pay < so.executeworkdate \n" +
	    		"               and table_isjobsbytime.isjobsbytime =1 \n" +
	    		"               and datepay is not null ) \n" +
	    		"             or ( \n" +
	    		"               table_isjobsbytime.limit_date_by_pay < current_date \n" +
	    		"               and table_isjobsbytime.isjobsbytime =0 \n" +
	    		"               and datepay is not null \n" +
	    		"             ) \n" +
	    		"  \n" +
	    		"             then \n" +
	    		"               case when coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p \n" +
	    		"                                  where enact.elementcode = so.elementcode \n" +
	    		"                                    and enact.elementcode = p.elementrefcode \n" +
	    		"                                    and p.kindcode = 3 \n" +
	    		"                                    and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null \n" +
	    		"                                    and enact.code = a2p.actrefcode \n" +
	    		"                               ),0) > 0 \n" +
	    		"               then 0 else  -- кол-во дне просрочки от оплаты(красный цвет ) \n" +
	    		"  \n" +
	    		"                        case when table_isjobsbytime.isjobsbytime = 1 and limit_date_by_pay < so.executeworkdate then \n" +
	    		"                          current_date -  so.executeworkdate \n" +
	    		"                        else \n" +
	    		"                          current_date - limit_date_by_pay \n" +
	    		"  \n" +
	    		"                        end \n" +
	    		"              end \n" +
	    		"             else 0 end \n" +
	    		"          else \n" +
	    		"            case when \n" +
	    		"             coalesce((case coalesce(so.regionaltype,0) when 2 \n" +
	    		"             then 9 + so.executeworkdate \n" +
	    		"             else 5 + so.executeworkdate \n" +
	    		"             end),current_date) < current_date \n" +
	    		"             then \n" +
	    		"               case when coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p \n" +
	    		"                                  where enact.elementcode = so.elementcode \n" +
	    		"                                    and enact.elementcode = p.elementrefcode \n" +
	    		"                                    and p.kindcode = 3 \n" +
	    		"                                    and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null \n" +
	    		"                                    and enact.code = a2p.actrefcode \n" +
	    		"                               ),0) >  0 \n" +
	    		"               then 0 else -- кол-во дней просрочки от намеченой даты выполнения для юр бюджет \n" +
	    		"                         (case coalesce(so.regionaltype,0) when 2 \n" +
	    		"                       then current_date - (9 + so.executeworkdate) \n" +
	    		"                       else current_date - (5 + so.executeworkdate) \n" +
	    		"                       end) \n" +
	    		"              end \n" +
	    		"             else 0 end \n" +
	    		"             END \n" +
//	    		"          end -- SUPP-33046 если провед в фин кол то не просроч  \n" +
	    		"         as is_red \n" +*/
	    		"  , (get_number_day_delay_services_redterm(so.code)) as is_red  \n" +
	    		"  \n" +
	    		"       , \n" +
	    		"  /*is_yellow*/ \n" ;
	    	String query3 =	"  case when so.statusrefcode in (2,4) " +
	    		" or so.contractstatusrefcode = 11 /*SUPP-34177 - проведенным считаются договора также со енерджинетовским статусом закритий*/  " +
	    		" then 0 else  /* SUPP-33046 если провед в фин кол то не просроч*/ \n" +
	    		"       case when \n" +
	    		"       coalesce((case coalesce(so.regionaltype,0) when 2 \n" +
	    		"         then 9 + so.executeworkdate \n" +
	    		"         else 5 + so.executeworkdate \n" +
	    		"         end),current_date) < current_date \n" +
	    		"         then \n" +
	    		"           case when coalesce(( select count(enact.code) from enact , enplanwork p , enact2enplanwork a2p \n" +
	    		"                              where enact.elementcode = so.elementcode \n" +
	    		"                                and enact.elementcode = p.elementrefcode \n" +
	    		"                                and p.kindcode = 3 \n" +
	    		"                                and ( select p2af.planrefcode from enplanwork2actfailure p2af where p2af.planrefcode = p.code ) is null \n" +
	    		"                                and enact.code = a2p.actrefcode \n" +
	    		"                           ),0) > 0 \n" +
	    		"           then 0 else -- кол-во дней просрочки от намеченой даты выполнения для \n" +
	    		"                     (case coalesce(so.regionaltype,0) when 2 \n" +
	    		"                   then current_date - (9 + so.executeworkdate) \n" +
	    		"                   else current_date - (5 + so.executeworkdate) \n" +
	    		"                   end) \n" +
	    		"          end \n" +
	    		"         else 0 end \n" +
	    		"        end /* SUPP-33046 если провед в фин кол то не просроч*/ \n" +
	    		"       as is_yellow \n" +
	    		"  \n" +
	    		"   , so.executeworkdate as executeworkdate  -- согласовання дата вып работ \n" +
	    		"   , (select to_char(min(dategen),'dd.mm.yyyy') from enpayment2so \n" +
	    		"         where enpayment2so.servicesobjectrefcode = so.code) as dateinpay -- дата надходження коштыв \n" +
	    		"  \n" +
	    		"    -- предельни дата выполнения \n" +
	    		"    ,  case when so.contragenttyperefcode <> 2 then \n" +
	    		"  \n" +
	    		"          case when isjobsbytime = 1 and so.executeworkdate >= table_isjobsbytime.limit_date_by_pay \n" +
	    		"               then so.executeworkdate \n" +
	    		"          else \n" +
	    		"               table_isjobsbytime.limit_date_by_pay \n" +
	    		"          end \n" +
	    		"       else \n" +
	    		"        case when coalesce(so.regionaltype,0) = 2 \n" +
	    		"           then 9 + so.executeworkdate \n" +
	    		"           else 5 + so.executeworkdate \n" +
	    		"           end \n" +
	    		"       end  as limit_date \n" +
	    		"  \n" +
	    		"    -- предельни дата выполнения is_yellow \n" +
	    		"    ,  case when so.contragenttyperefcode <> 2 then \n" +
	    		"          case when coalesce(so.regionaltype,0) = 2 \n" +
	    		"           then 9 + so.executeworkdate \n" +
	    		"           else 5 + so.executeworkdate \n" +
	    		"           end \n" +
	    		"       else \n" +
	    		"        case when coalesce(so.regionaltype,0) = 2 \n" +
	    		"           then 9 + so.executeworkdate \n" +
	    		"           else 5 + so.executeworkdate \n" +
	    		"           end \n" +
	    		"       end  as limit_date_yellow \n" +
	    		"  \n" +
	    		"    , sc.code as enservicescontractstts_code \n" +
	    		"    , so.elementcode \n" +
	    		"    , table_isjobsbytime.isjobsbytime \n" +
	    		"    , so.regulation  \n" +
	    		"    , so.term  \n" +
	    		"    , so.contragentaddresswork  \n" +
	    		" from enservicesobject so left join enwarrant war on (war.code = so.warrantrefcode) ,  \n" +
	    		"      enservicescontractstts sc, \n" +
	    		"      endepartment dep, \n" +
	    		"      enservicescontracttype ct, \n" +
	    		"      enservicescontragenttp ctype, \n" +
	    		"      enservicesobjectstatus sobjs, \n" +
	    		"      (select so.code as socode , so.contractnumberservices \n" +
	    		"     , (select coalesce(p2c.isjobsbytime,0) \n" +
	    		"          from enplanwork pw , enplanwork2classfctntp p2c \n" +
	    		"         where pw.elementrefcode = so.elementcode \n" +
	    		"         and pw.kindcode = 5 \n" +
	    		"         and pw.code = p2c.planrefcode \n" +
	    		"         order by coalesce(p2c.isjobsbytime,0) desc \n" +
	    		"         limit 1  ) as isjobsbytime \n" +
	    		"         , (select min(dategen) from enpayment2so \n" +
	    		"                     where enpayment2so.servicesobjectrefcode = so.code) as datepay \n" +
	    		"         , coalesce((case coalesce(so.regionaltype,0) when 2 \n" +
	    		"             then 9 + (select min(dategen) from enpayment2so \n" +
	    		"                     where enpayment2so.servicesobjectrefcode = so.code) \n" +
	    		"             else 5 + (select min(dategen) from enpayment2so \n" +
	    		"                     where enpayment2so.servicesobjectrefcode = so.code) \n" +
	    		"             end),so.executeworkdate) as limit_date_by_pay \n" +
	    		"             -- add \n" +
	    		"         , coalesce((case coalesce(so.regionaltype,0) when 2 \n" +
	    		"             then 9 +  so.executeworkdate \n" +
	    		"             else 5 +  so.executeworkdate \n" +
	    		"             end),current_date) as limit_date_by_executeworkdate \n" +
	    		"         , so.executeworkdate \n" +
	    		"      from enservicesobject so \n" +
	    		"     --  where  so.contractnumberservices  = '96766' \n" +
	    		"      ) as table_isjobsbytime \n" +
	    		"  \n" +
	    		" where so.contractstatusrefcode =  sc.code \n" +
	    		"   and dep.code = so.departmentcode \n" +
	    		"   and ct.code = so.contracttyperefcode \n" +
	    		"   and ctype.code = so.contragenttyperefcode \n" +
	    		"   and sobjs.code = so.statusrefcode \n" +
	    		"   and so.code = table_isjobsbytime.socode \n" +
	    		"  \n" +
	    		"   and so.contractdateservices between to_date('"+datestart+"','dd.MM.yyyy') and to_date('"+datefinal+"','dd.MM.yyyy') \n" +
	    		"   and (  "+renCode+"::integer <> 0 and so.departmentcode = "+renCode+"::integer or "+renCode+"::integer = 0 ) \n" +
	    		"   and ( (so.statusrefcode not in (2,4) " +
	    		"          and  so.contractstatusrefcode <> 11 /*SUPP-34177 - проведенным считаются договора также со енерджинетовским статусом закритий*/" +
	    		"          and "+showStatusProveden+" = 0 ) or ("+showStatusProveden+" = 1) ) \n" +
	    		"   -- and so.code = 1017010030 \n" +
	    		"   and so.contracttyperefcode in (1,2) -- что б не брались приэднання всякие и продажи . \n" +
	    		"   and so.contractstatusrefcode not in (2,10) -- не берем договора которые в статусе отказ от услуги , отмененные и скасованые \n" +
	    		" -- только лиценнз работы \n" +
	    		"    and so.elementcode in ( \n" +
	    		"                          select p.elementrefcode from enplanwork p , enplanwork2classfctntp pc, tkclassificationtype cl , enelement el \n" +
	    		"                          where p.code = pc.planrefcode \n" +
	    		"                          and pc.classificationtyperfcd = cl.code \n" +
	    		"                          and cl.isnotlicensedactivity =0 \n" +
	    		"                          and p.elementrefcode = el.code \n" +
	    		"                          and el.typerefcode = 23 \n" +
	    		"                          ) \n" +
	    		"    /*показывать или нет скасованые договора */ \n" +
	    		"    and case when "+show_enservicescontractstts_terminated+" = 1 then 1=1 else sc.code <> 8 end \n" +
	    		"  \n" +
	    		"    and case when "+isOnlyReplaсeCounter+" = 1 then  so.code in (select s.code \n" +
	    		"             from enservicesobject s where s.elementcode in ( \n" +
	    		"             select distinct pl.elementrefcode from enplanwork pl where pl.code in ( \n" +
	    		"             select tcl.planrefcode \n" +
	    		"             from enplanwork2classfctntp tcl, tkclassificationtype t \n" +
	    		"             where tcl.classificationtyperfcd = t.code  and t.replacecounterkindcode = 1 ) )) \n" +
	    		"       else 1 = 1 end \n" +
	    		"  \n" +
	    		"  /*фильтр по калькуляциям в договоре */ \n" +
	    		"  and case when '"+tkclassificationtypecodes+"' <> '' then  so.code in \n" +
	    		"     (select distinct so.code from tkclassificationtype tcl  , enplanwork2classfctntp p2clt , enplanwork p           , enservicesobject so \n" +
	    		"    where tcl.kod like replace('"+tkclassificationtypecodes+"','*','%') \n" +
	    		"         and p2clt.classificationtyperfcd = tcl.code \n" +
	    		"         and p2clt.planrefcode = p.code \n" +
	    		"         and p.elementrefcode = so.elementcode \n" +
	    		"         and p.kindcode = 5 \n" +
	    		"   ) else 1 = 1 end \n" +
				" and case when " + ServicesWithoutFact + " = 1 then so.elementcode not in ( select pp.elementrefcode from enplanwork pp \n" +
				"         where pp.kindcode = " + ENPlanWorkKind.FACT +
				"         and pp.elementrefcode=so.elementcode ) \n" +
				" else 1=1 end   \n" +
	    		"  \n" +
	    		"  GROUP BY so.contractnumberservices , \n" +
	    		"        so.contractdateservices, \n" +
	    		"        so.name , \n" +
	    		"        so.contragentname, \n" +
	    		"        so.contragentaddress, \n" +
	    		"        so.contragentaddresswork, \n" +
	    		"        dep.name, \n" +
	    		"        sobjs.name , \n" +
	    		"        ct.name, \n" +
	    		"        ctype.name , \n" +
	    		"        so.elementcode, \n" +
	    		"        so.contragenttyperefcode , \n" +
	    		"        so.regionaltype, \n" +
	    		"        so.code , \n" +
	    		"        sc.code , \n" +
	    		"        table_isjobsbytime.datepay , \n" +
	    		"        table_isjobsbytime.isjobsbytime , \n" +
	    		"        table_isjobsbytime.limit_date_by_pay , \n" +
	    		"        -- add \n" +
	    		"        table_isjobsbytime.limit_date_by_executeworkdate , \n" +
	    		"  so.personalaccountnumber \n" +
	    		"  ) as qqq \n" +
	    		"  where case when "+OnlyOverdue+" = 1 then is_red > 0 /*or is_yellow > 0*/ else 1=1 end \n" +
	    		"  ORDER BY depname , contractdateservices,  contractnumberservices \n" +
	    		"  \n" ;


	    tempSt = connection.createStatement();
	    rs = tempSt.executeQuery(query + query2 + query3);

	    while(rs.next())  ///
        {
	    	HashMap hashMap = new HashMap();
            hashMap.put(ServicesDS.CONTRACTNUMBERSERVICES, rs.getString(1));
    		hashMap.put(ServicesDS.CONTRACTDATESERVICES, rs.getString(2));
    		hashMap.put(ServicesDS.SERVICESNAME, rs.getString(3));
    		hashMap.put(ServicesDS.CONTRAGENTNAME, rs.getString(4));
    		hashMap.put(ServicesDS.CONTRAGENTADDRESS, rs.getString(5));
    		hashMap.put(ServicesDS.DEPNAME, rs.getString(6));
    		hashMap.put(ServicesDS.STATUSNAME, rs.getString(7));
    		hashMap.put(ServicesDS.BUH_STATUS, rs.getString(9));
    		hashMap.put(ServicesDS.TYPENAME, rs.getString(10));
    		hashMap.put(ServicesDS.CONTRAGENTTYPE, rs.getString(11));
    		hashMap.put(ServicesDS.ACT, rs.getString(12));
    		hashMap.put(ServicesDS.EXECUTOR_FACT, rs.getString(13));
    		hashMap.put(ServicesDS.IS_RED, rs.getInt(14));
    		hashMap.put(ServicesDS.IS_YELLOW, rs.getInt(15));
    		hashMap.put(ServicesDS.EXECUTEWORKDATE, rs.getString(16));
    		hashMap.put(ServicesDS.DATEINPAY, rs.getString(17));
    		hashMap.put(ServicesDS.LIMIT_DATE, rs.getString(18));
    		hashMap.put(ServicesDS.NAMECLASSIF_IN_DOGOVOR, rs.getString(20));
    		hashMap.put(ServicesDS.SUMTOTAL, rs.getBigDecimal(21));
    		hashMap.put(ServicesDS.CALCTOTALCOST, rs.getBigDecimal(22));
    		hashMap.put(ServicesDS.ENSERVICESCONTRACTSTTS_CODE, rs.getInt(23));
    		hashMap.put(ServicesDS.CONTRACTCODE, rs.getInt(25));
    		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS, rs.getString(26));
    		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS_1F, rs.getString(27));
    		hashMap.put(ServicesDS.STR_MATERIALS_CODE_COUNTERS_3F, rs.getString(28));

    		String counter_for_service = this.getCounterForServicesObject(rs.getInt(25));

    		hashMap.put(ServicesDS.COUNTER_FOR_SERVICE, counter_for_service);

    		hashMap.put(ServicesDS.COUNTER_PHASITY_ON_SERVICE, rs.getInt(29));

    		hashMap.put(ServicesDS.COUNTERS_CODES_FOR_CONTRACT, rs.getString(30));

    		hashMap.put(ServicesDS.PERSONALACCOUNTNUMBER, rs.getString(31));

    		hashMap.put(ServicesDS.NUMDATEORDERCOUNTER, rs.getString(32));

    		hashMap.put(ServicesDS.TERMTXT, rs.getString(33));
    		hashMap.put(ServicesDS.CONTRAGENTADDRESSWORK, rs.getString(34));

            rows.add(hashMap);
        }


	    rs.close();
	    tempSt.close();


	    return new ServicesDS(rows.toArray());

	    } catch (SQLException e) {
	        throw new ReportSystemException(e);
	    } catch (PersistenceException e) {
	    	throw new ReportSystemException(e);
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


   /**
    * Функция возвращает строки для отчета "Звіт по послугам на сторону Стан виконання договорів"
 * @throws JRException
    *
    *
    * ***/
   public JRDataSource getMissingCounter(
		     JRDataSource jds
		   ) throws JRException
	{

	// колличество договоров без счетчиков. сумарно. разбитое по доступным материалам
	   Hashtable<String, Integer> missingCounter = new Hashtable<>();
	// Наличие  счетчиков - то что в countersread и то что в заявке - вместе  <код материала , кол-во в наличии или то что заказано >
	   Hashtable<Integer, Integer> availabilityCounter = new Hashtable<>();

	   Statement tempSt = null;
	   ResultSet rs = null;
	   ArrayList rows  = new ArrayList();

	    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	   TKMaterialsDAO tmDAO = new TKMaterialsDAO(connection, userProfile);
	try{
    if (jds != null ) {


    	((JRMapArrayDataSource) jds).moveFirst();
    	int countContract = 0;


    	String str_materials_code_counters = "";
    	 while(jds.next() )  ///
         {



    		 JRDesignField field = new JRDesignField();


 	    	field.setName("counters_codes_for_contract");
 	    	String counters_codes_for_contract = jds.getFieldValue(field).toString();
 	    	field.setName("counter_for_service");
 	    	String counter_for_service = jds.getFieldValue(field).toString();
 	    	field.setName("enservicescontractstts_code");
 	    	int enservicescontractstts_code = (Integer) jds.getFieldValue(field);
 	    	field.setName("str_materials_code_counters");
		    str_materials_code_counters = jds.getFieldValue(field).toString();


 	    	// если без счетчика договор то созраним в хеш
 	    	 if (counter_for_service.equals("")
 	    			&& enservicescontractstts_code != 2
 	    			&& enservicescontractstts_code != 8
 	    			&& enservicescontractstts_code != 10
 	    			&& enservicescontractstts_code != 11
 	    		 ) {
		 	    	if(missingCounter.containsKey(counters_codes_for_contract)) {
			    		countContract = missingCounter.get(counters_codes_for_contract);
			    		countContract = countContract+1;
					  } else
					  {
						countContract = 1;
					  }
		 	    	System.out.println (" for put  "  + counters_codes_for_contract );
		 	    	missingCounter.put(counters_codes_for_contract, countContract);
 	    	    }


         }

    	 // посчитаем счетчики в наличии (остатки + заявлено) в разрезе кода материала(счетчика)
    	 // счетчики из остатка
    	 String sqlCountersread = "";
    	 sqlCountersread = " select count(a.num_un) as rest_count , a.materialcode  \n" +
					       "   FROM countersread.ostable a \n" +
					       " where a.materialcode in("+ str_materials_code_counters +") \n" +
					       " and a.show_ = 'Y' \n" +
					       " and a.kod_podr in (18,27) \n" +
					       " and nvl(a.energy_lock,0) <= 0 \n" +
					       " and substr(a.kod_subsch_b,1,2) = '15' \n" +
					       " group by a.materialcode ";

    	 PreparedStatement statement = null;
    	 ResultSet  resultSet = null;
    	 if (finConnection == null || finConnection.isClosed()) {
             finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
         }

    	 statement = finConnection.prepareStatement(sqlCountersread);

         resultSet = statement.executeQuery();

         while(resultSet.next())
         {
        	 availabilityCounter.put(resultSet.getInt(2), resultSet.getInt(1));
         }

         // счетчики в заявке
         String sqlCountersOrdered = "  Select \n" +
						"    materialcode \n" +
						"  , countername \n" +
						" , (sum(order_count_is_not_pay) - sum(count_prih_is_not_pay) ) + (sum(order_count_is_pay) - sum(count_prih_is_pay)) as ordered \n" +
						" from ( \n" +
						"  \n" +
						"       Select m.name as countername , oi.countfact , o.numberdoc , oi.code as oicode , oi.materialcode \n" +
						"       , /*кол-во оприходовано всего*/ \n" +
						"       coalesce((select sum(q.countfact) \n" +
						"           from rqfkorderitem2billitem q , rqbillitem2orderitem bi2oi, rqfkorderitem fi , rqfkorder f \n" +
						"           where q.billitemrefcode = bi2oi.billitemrefcode \n" +
						"           and q.fkorderitemrefcode = fi.code \n" +
						"           and fi.fkorderrefcode = f.code \n" +
						"           and f.statuscode = 5 \n" +
						"           and fi.materialcode = oi.materialcode \n" +
						"           and bi2oi.orderitemrefcode = oi.code \n" +
						"       ),0) as count_prih \n" +
						"  \n" +
						"       , /*кол-во оприходовано с тех строк счета по которым была оплата */ \n" +
						"       coalesce((select sum(q.countfact) \n" +
						"           from rqfkorderitem2billitem q , rqbillitem2orderitem bi2oi, rqfkorderitem fi , rqfkorder f , rqbillitem bi , rqpaydocitem2billitem pi2bi \n" +
						"           where q.billitemrefcode = bi2oi.billitemrefcode \n" +
						"           and q.fkorderitemrefcode = fi.code \n" +
						"           and fi.fkorderrefcode = f.code \n" +
						"           and f.statuscode = 5 \n" +
						"           and fi.materialcode = oi.materialcode \n" +
						"           and q.billitemrefcode = bi.code \n" +
						"           and pi2bi.billitemcode = bi.code \n" +
						"  \n" +
						"           and bi2oi.orderitemrefcode = oi.code \n" +
						"       ),0) as count_prih_is_pay \n" +
						"  \n" +
						"        , /*кол-во оприходовано с тех строк счета по которым НЕБЫЛО оплаты */ \n" +
						"       coalesce((select sum(q.countfact) \n" +
						"           from rqfkorderitem2billitem q , rqbillitem2orderitem bi2oi, rqfkorderitem fi , rqfkorder f , rqbillitem bi \n" +
						"           where q.billitemrefcode = bi2oi.billitemrefcode \n" +
						"           and q.fkorderitemrefcode = fi.code \n" +
						"           and fi.fkorderrefcode = f.code \n" +
						"           and f.statuscode = 5 \n" +
						"           and fi.materialcode = oi.materialcode \n" +
						"           and q.billitemrefcode = bi.code \n" +
						"           and not EXISTS (select pi2bi.code from rqpaydocitem2billitem pi2bi where pi2bi.billitemcode = bi.code ) \n" +
						"           and bi2oi.orderitemrefcode = oi.code \n" +
						"       ),0) as count_prih_is_not_pay \n" +
						"       /*кол-во заявлено с оплатами  */ \n" +
						"       ,COALESCE( ( \n" +
						"                select sum(bi2oi.countfact) from rqbillitem2orderitem bi2oi , rqbillitem bi, rqpaydocitem2billitem pi2bi \n" +
						"                  where bi2oi.orderitemrefcode = oi.code \n" +
						"                    and bi2oi.billitemrefcode = bi.code \n" +
						"                    and pi2bi.billitemcode = bi.code \n" +
						"       ),0) as order_count_is_pay \n" +
						"  \n" +
						"        /*кол-во заявлено без оплат або без рахунків */ \n" +
						"       , oi.countfact - \n" +
						"         COALESCE( ( \n" +
						"                 select sum(bi2oi.countfact) from rqbillitem2orderitem bi2oi , rqbillitem bi, rqpaydocitem2billitem pi2bi \n" +
						"                  where bi2oi.orderitemrefcode = oi.code \n" +
						"                    and bi2oi.billitemrefcode = bi.code \n" +
						"                    and pi2bi.billitemcode = bi.code \n" +
						"       ),0) as order_count_is_not_pay \n" +
						"  \n" +
						"       from rqapprovedcost acost , rqorderitem oi , tkmaterials m , rqorder o \n" +
						"       where acost.rqorderitemrefcode = oi.code \n" +
						"       and oi.materialcode = m.code \n" +
						"       and oi.orderrefcode = o.code \n" +
						"       and o.statusrefcode = 2 \n" +
						"       and acost.approvedcoststatusrfcd = 2 \n" +
						" and o.creationmethodrefcode=3 \n" +
					    "  and o.kindrefcode=5 \n" +
						" ) as oiapp \n" +
						" group by countername , materialcode \n" +
						" order by countername \n" +
						"  \n"  ;



	statement = connection.prepareStatement(sqlCountersOrdered);

    resultSet = statement.executeQuery();

    int countOrdered = 0 ;
    while(resultSet.next())
    {

    if (resultSet.getInt(3) > 0 && 1 == 2 /*tezzzt*/ ) {
    	if(availabilityCounter.containsKey(resultSet.getInt(1))) {
    		countOrdered = availabilityCounter.get(resultSet.getInt(1));
    		countOrdered = countOrdered+resultSet.getInt(3);
		  } else
		  {
			  countOrdered = resultSet.getInt(3);
		  }

    	availabilityCounter.put(resultSet.getInt(1), countOrdered);
    }

    }



    for (Enumeration e = missingCounter.keys(); e.hasMoreElements();)
		   {
			   String codes_counter_missingCounter = (String) e.nextElement();
		       int countmissingCounter = missingCounter.get(codes_counter_missingCounter);
		       System.out.println (" codes_counter_missingCounter   " +  codes_counter_missingCounter  + " count = " +countmissingCounter);
		   }

    for (Enumeration e = availabilityCounter.keys(); e.hasMoreElements();)
		   {
			   int codeCounter = (Integer) e.nextElement();
		       int countAvailabilityCounter = availabilityCounter.get(codeCounter);
		       System.out.println (" availability Counter  " +  codeCounter  + " count = " +countAvailabilityCounter);
		   }

        for (Enumeration e = missingCounter.keys(); e.hasMoreElements();)
		   {
			   String counters_codes_for_contract = (String) e.nextElement();
		       int countMissingContract = missingCounter.get(counters_codes_for_contract);
		       System.out.println (" missing counter " +  counters_codes_for_contract + " count = " +countMissingContract);

		       TKMaterialsFilter tmFilter = new TKMaterialsFilter();
		       tmFilter.conditionSQL = " TKMATERIALS.code in ("+counters_codes_for_contract+") ";
		       int[] tmArr = tmDAO.getFilteredCodeArray(tmFilter, 0, -1);

		       for (int i = 0; i < tmArr.length; i++){
		    	// если нашли в наличии такой счетчик то не считаем то что на этот договор нет счетчика в наличии где то
		    	   if(availabilityCounter.containsKey(tmArr[i])) {

		    		 int  substrCountAvailabilityCounter = availabilityCounter.get(tmArr[i]);

		    		 countMissingContract = missingCounter.get(counters_codes_for_contract);
		    		 countMissingContract = countMissingContract-substrCountAvailabilityCounter;
		    		 missingCounter.put(counters_codes_for_contract, countMissingContract);

		    		 int countAvailabilityCounter = availabilityCounter.get(tmArr[i]);
		    		 countAvailabilityCounter = countAvailabilityCounter-substrCountAvailabilityCounter;
		    		 availabilityCounter.put(tmArr[i], countAvailabilityCounter);

			       }
		       }

		   }

       // выводим на отчет недостающее количество счетчиков

        for (Enumeration e = missingCounter.keys(); e.hasMoreElements();)
		   {
			   String codes_counter_missingCounter = (String) e.nextElement();
		       int countmissingCounter = missingCounter.get(codes_counter_missingCounter);

		       if (countmissingCounter > 0 || 1==1 /*tezzzt*/ ){
		    	   HashMap hashMap = new HashMap();

		    	   TKMaterialsFilter tmFilter = new TKMaterialsFilter();
			       tmFilter.conditionSQL = " tk1.code in ("+codes_counter_missingCounter+") ";
			       TKMaterialsShortList tmList = tmDAO.getScrollableFilteredList(tmFilter, 0, -1);
			       String strMissingCountersName = "";
			       for (int i = 0; i < tmList.totalCount; i++){
			    	   if (strMissingCountersName.equals("")) {
			    		   strMissingCountersName = tmList.get(i).name;
			    	   } else
			    	   {
			    		   strMissingCountersName = strMissingCountersName + "\n або " + tmList.get(i).name;
			    	   }

			       }

		 	       hashMap.put(ServicesDS.NAMECLASSIF_IN_DOGOVOR, strMissingCountersName); // наименования счетчиков подходящих под договора
		 	       hashMap.put(ServicesDS.IS_RED, countmissingCounter); // кол -во недостающее
		 	       rows.add(hashMap);
		       }

		   }

    }



	    } catch (SQLException e) {
	        throw new ReportSystemException(e);
	    } catch (DatasourceConnectException e) {
    	    throw new ReportSystemException(e);
    	} catch (PersistenceException e) {
    		throw new ReportSystemException(e);
		}finally {
	        try {
	            if (rs != null) rs.close();
	            } catch (SQLException e) {
	        }
	        try {
	            if (tempSt != null) tempSt.close();
	        } catch (SQLException e) {
	        }
	        try {
                if ((finConnection != null && !finConnection.isClosed()))
                     finConnection.close();
            } catch (SQLException e) {
            }
	    }

	return new ServicesDS(rows.toArray());

	}



	public  boolean checkUsesMDAXDataForReport() {
			Connection authConn = null;
			boolean usesMDAXData = false;
			try {

				authConn = getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE);

				JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
				UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

				ConfigDAO configDAO = new ConfigDAO(userProfile, authConn);
				Config conf = configDAO.getObject(Config.CONFIG_USES_MDAX_DATA_REPORT);

				if (conf.value.equals(Config.USES_MDAX_DATA_YES)) {
					usesMDAXData = true;
				}

				return usesMDAXData;


			} catch (DatasourceConnectException e) {
				throw new SystemException(e.getMessage(), e);
			} catch (PersistenceException e) {
				throw new SystemException(e.getMessage(), e);
			} finally {
				try {
					authConn.close();
				} catch (SQLException e) {
					throw new SystemException(e.getMessage(), e);
				}
			}
		}


public static class expiredServicesCountersDS extends JRMapArrayDataSource implements
	    JRDataSourceProvider {

		public static final String WITHOUTCOUNTSER = "withoutcountser"; // кол -во договоров без счетчика
		public static final String COUNTERFROMSTOCK = "counterfromstock"; // кол-во договоров со счечиками на складе
		public static final String COUNTERFROMREM = "counterfromrem"; // кол-во договоров со счетчиками в РЕСе

		private static final JRField[] fields = new JRField[] {
			new DSField(WITHOUTCOUNTSER, Integer.class),
			new DSField(COUNTERFROMSTOCK, Integer.class),
			new DSField(COUNTERFROMREM, Integer.class)


		};

		private expiredServicesCountersDS() {
			super(null);
		}

		public expiredServicesCountersDS(Object[] arg0) {
			super(arg0);
		}

		@Override
		public JRDataSource create(JasperReport arg0) throws JRException {
			return this;
		}

		@Override
		public void dispose(JRDataSource arg0) throws JRException {

		}

		@Override
		public JRField[] getFields(JasperReport arg0) throws JRException,
				UnsupportedOperationException {
			return fields;
		}

		@Override
		public boolean supportsGetFieldsOperation() {
			return true;
		}

}


	public JRDataSource getExpiredServicesCounters(String codeServices)
			throws JRException {

		JRFillParameter jrParameterMap = this.parametersMap
				.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) this.parametersMap.get(
				"REPORT_CONNECTION").getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap
				.getValue()).get("userProfile");

		ENServicesObjectDAO soDAO = new ENServicesObjectDAO(connection, userProfile);
		ENServicesObjectFilter soFilter = new ENServicesObjectFilter();
		soFilter.conditionSQL = " enservicesobject.code in ( " + codeServices + ")";

		int withoutcountser = 0;
		int counterfromstock = 0;
		int counterfromrem = 0;

try {

	SCLogic scLogic = new SCLogic(connection,
			getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE), userProfile);

	ENServicesObjectShortList soList = soDAO.getScrollableFilteredList(soFilter, 0, -1);

	for (int i=0; i < soList.totalCount; i++) {
	SCCounterShortList scList = scLogic.getCounterForServicesObject(soList.get(i).code, false);

	if (scList.totalCount == 0){
		withoutcountser = withoutcountser +1;
		continue;
	}

	if (scList.get(0).molCode.equals(RQConsts.SCCOUNTER_PARAMETERIZED_MULTIFUNCTIONAL_MOL_OUT ) ){
		counterfromstock = counterfromstock+1;
	} else if (!scList.get(0).molCode.equals(RQConsts.SCCOUNTER_PARAMETERIZED_MULTIFUNCTIONAL_MOL_OUT) ){
		counterfromrem = counterfromrem+1;
	}

    }


   ArrayList rows  = new ArrayList();

     	HashMap hashMap = new HashMap();
        hashMap.put(expiredServicesCountersDS.WITHOUTCOUNTSER, withoutcountser);
        hashMap.put(expiredServicesCountersDS.COUNTERFROMSTOCK, counterfromstock);
        hashMap.put(expiredServicesCountersDS.COUNTERFROMREM, counterfromrem);
	    rows.add(hashMap);


   return new expiredServicesCountersDS(rows.toArray());

		} catch (PersistenceException e) {
			throw new ReportSystemException(e);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {

		}
	}

/**
 * формирование додатков енергосбыт
 *
 * */

public JRDataSource getDodatokZbytDS(String dateStart ,
		String dateFinal ,
		String hrmorganizationid ,
//		String finexecutorcode ,
//		String finexecutorname ,
//		int obj ,
//		int worktypecode ,
//		int formplan ,
//		int statusplanfact ,
//		int pwkindcode ,
		String enReportAdditionCodesString /* список ун кодов таблицы с додатками */
		)
{

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

    TKTechCardDAO tkDAO = new TKTechCardDAO(connection, userProfile);

    Statement tempSt = null;
    ResultSet rs = null;

try {

	ArrayList rows  = new ArrayList();
	mDaxLogic mdLogic = new mDaxLogic(connection, userProfile );
	AXPodrWithEmplidStrShortList podrList = null;
	 // tezzzt podrList = mdLogic.getAXPodrWithEmplidStrList(hrmorganizationid, dateStart, dateFinal, "НВЗ_Е");

    TKTechCardFilter tkFilter =new TKTechCardFilter();
  //  tkFilter.conditionSQL = "TKTECHCARD>CODE IN ( )   "

	for (int i=0; i < podrList.totalCount; i++)
    {
		HashMap hashMap = new HashMap();
	    hashMap.put(DodatokZbytDS.FACTCOUNTGEN, new BigDecimal(0));
	    hashMap.put(DodatokZbytDS.FINEXECUTORNAME, podrList.get(i).cehName +" " + podrList.get(i).podrName );
	    rows.add(hashMap);
    }




    return new DodatokZbytDS(rows.toArray());

    }
     catch (SystemException e) {
        throw new ReportSystemException(e);
    } finally {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
        }
        try {
            if (tempSt != null)
                tempSt.close();
        } catch (SQLException e) {
        }
    }
}

public BufferedImage getBarcode(Integer serviceCode, BigDecimal sumToPay)
{
	return getBarcode(serviceCode, sumToPay, new Integer(Integer.MIN_VALUE), null, false);
}

public BufferedImage getBarcode(Integer serviceCode, BigDecimal sumToPay, Integer departmentCode, String contractNumberServices, boolean isTU)
{

    JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
    Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
    UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	   try
  {
			Barcode bc;


	          //BigDecimal toPay = so.t
	          //int toPay = new BigDecimal(new BigDecimal(1)
	          //        .doubleValue()*100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

	          int toPay = 0;

	          int billTypeCode = 1;

	          String soContractNumberServices = "";
	          int soDepartmentCode = Integer.MIN_VALUE;

	          if (isTU)
	          {
	        	  billTypeCode = 2;
	        	  soContractNumberServices = contractNumberServices;
	        	  soDepartmentCode = departmentCode.intValue();
	          }
	          else
	          {
		          ENServicesObjectDAO soDAO = new ENServicesObjectDAO(connection, userProfile);
		          ENServicesObject so = soDAO.getObject(serviceCode);

		          /*
		          ENServicesFactCalcDAO fcDAO = new ENServicesFactCalcDAO(connection, userProfile);
		          ENServicesFactCalcFilter fcFilter = new ENServicesFactCalcFilter();
		          fcFilter.servicesObjectRef.code = so.code;
		          ENServicesFactCalcShortList fcList = fcDAO.getScrollableFilteredList(fcFilter, 0, -1);

		          if (fcList.totalCount > 0)
		          {
		        	  if (isPrepay)
		        	  {
		        		  toPay = new BigDecimal(fcList.get(0).totalSumPrepay.doubleValue() * 100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		        	  }
		        	  else
		        	  {
		        		  toPay = new BigDecimal(fcList.get(0).totalSumRest.doubleValue() * 100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		        	  }
		          }
		          */
		          if (so.contractKindRef.code == ENServicesContractKind.SERVICES)
		          {
		        	  if (so.contractTypeRef.code == ENServicesContractType.CONNECTION)
		        	  {
		        		  billTypeCode = 2;
		        	  }
		          }
		          else if (so.contractKindRef.code == ENServicesContractKind.SALE)
		          {
		        	  billTypeCode = 6;
		          }

	        	  soContractNumberServices = so.contractNumberServices;
	        	  soDepartmentCode = so.department.code;
	          }

	        toPay = new BigDecimal(sumToPay.doubleValue() * 100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

	        if (toPay > 0)
			{

//				Код услуги(в 36 ричн.)
				//Номер договора:
				//Код подразделения(в 36 ричн.)
				//Код типа услуги (в 36 ричн.)
				//Сумма(в копейках с НДС в 36 ричн.)

				String line =Integer.toString(Integer.parseInt(serviceCode.toString(), 10), 36)
						+ "+"
						+ soContractNumberServices
						+ "+"
				        + Integer.toString(Integer.parseInt(new Integer(soDepartmentCode).toString(), 10), 36)
				        + "+"
						+ Integer.toString(Integer.parseInt(new Integer(billTypeCode).toString(), 10), 36)
						+ "+"
						+ Integer.toString(Integer.parseInt(new Integer(toPay).toString(), 10), 36);


				bc = BarcodeFactory.create3of9(line.substring(0).toUpperCase(), false);
			}
			else
			{
				bc = BarcodeFactory.create3of9("0", false);
			}
			return BarcodeImageHandler.getImage(bc);
		}

 catch (PersistenceException e) {
		throw new ReportSystemException(e);
	}

catch (BarcodeException e)
  {
  throw new ReportSystemException(e);
  }
catch (OutputException e)
  {
  throw new ReportSystemException(e);
  }
}

/**
 * -- заполнение датасерса для репорта "Звіт про виконання договорів про приєднання"
 * @throws DatasourceConnectException
 *
 * */
public JRDataSource getDataForReportExecPriconectionContract(
		String contractDateStart , String contractDateFinal   , String endepartmentCode , String contractnumberservices
		)
	{


	JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

//	CNLogic cLogic = new CNLogic(
//			getRemoteConnection(AuthorizationJNDINames.CN_IMMEDIATE_DATASOURCE),
//			getUserProfile());
	//EWFConnection =   getRemoteConnection(AuthorizationJNDINames.CN_IMMEDIATE_DATASOURCE);







	PreparedStatement tempSt = null;
	ResultSet rs = null;

	try {

		EWFConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
		CNLogic cLogic = new CNLogic(EWFConnection,userProfile);

		ENIPItemDAO ipiDAO = new ENIPItemDAO(connection, userProfile);



	 String selStr = " ";
	 selStr = " 		select \n" +
			 " 			ENSERVICESOBJECT.CODE as socode, \n" +
			 " 			ENSERVICESOBJECT.CONTRACTNUMBERSERVICES, \n" +
			 " 			ENSERVICESOBJECT.CONTRACTDATESERVICES, \n" +
			 " 			ENSERVICESOBJECT.CONTRACTNUMBER, \n" +
			 " 			ENSERVICESOBJECT.CONTRACTDATE, \n" +
			 " 			ENSERVICESOBJECT.FINDOCID, \n" +
			 " 			ENSERVICESOBJECT.CONTRAGENTNAME, \n" +
			 " 			ENSERVICESOBJECT.CONTRAGENTOKPO, \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					ENSERVICESCONTRAGENTTP.NAME \n" +
			 " 				from \n" +
			 " 					ENSERVICESCONTRAGENTTP \n" +
			 " 				where \n" +
			 " 					ENSERVICESCONTRAGENTTP.CODE = ENSERVICESOBJECT.CONTRAGENTTYPEREFCODE \n" +
			 " 			) as ENSERVICESCONTRAGENTTPNAME , \n" +
			 " 			ENDEPARTMENT.SHORTNAME, \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					case \n" +
			 " 						when enservicescontracttype.code = 5 then enservicescontracttype.name || ' - ' ||( \n" +
			 " 							select \n" +
			 " 								k.name \n" +
			 " 							from \n" +
			 " 								enservicesobject2techcondtnsservices s2t, \n" +
			 " 								entechconditionsservcs ts, \n" +
			 " 								enconnectionkind k \n" +
			 " 							where \n" +
			 " 								ts.code = s2t.techcondservrefcode \n" +
			 " 								and ts.connectionkindrefcode = k.code \n" +
			 " 								and s2t.servicesobjectrefcode = enservicesobject.code \n" +
			 " 						) \n" +
			 " 						else enservicescontracttype.name \n" +
			 " 					end \n" +
			 " 				from \n" +
			 " 					enservicescontracttype \n" +
			 " 				where \n" +
			 " 					enservicescontracttype.code = enservicesobject.contracttyperefcode \n" +
			 " 			) as enservicescontracttypename , \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					ENSERVICESCONTRACTSTTS.CODE \n" +
			 " 				from \n" +
			 " 					ENSERVICESCONTRACTSTTS \n" +
			 " 				where \n" +
			 " 					ENSERVICESCONTRACTSTTS.CODE = ENSERVICESOBJECT.CONTRACTSTATUSREFCODE \n" +
			 " 			) ENSERVICESCONTRACTSTTSCODE , \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					case \n" +
			 " 						when( \n" +
			 " 							enservicescontractstts.code = 3 \n" +
			 " 							and enservicesobject.contractkindrefcode = 2 \n" +
			 " 						) then 'Специфікацію затверджено' \n" +
			 " 						else ENSERVICESCONTRACTSTTS.NAME \n" +
			 " 					end \n" +
			 " 				from \n" +
			 " 					ENSERVICESCONTRACTSTTS \n" +
			 " 				where \n" +
			 " 					ENSERVICESCONTRACTSTTS.CODE = ENSERVICESOBJECT.CONTRACTSTATUSREFCODE \n" +
			 " 			) as ENSERVICESCONTRACTSTTSNAME , \n" +
			 " 			ENSERVICESOBJECTSTATUS.NAME as buhstatus , \n" +
			 " 			ENSERVICESOBJECT.COMMENTGEN, \n" +
			 " 			ENSERVICESOBJECT.CONTRAGENTADDRESS, \n" +
			 " 			ENSERVICESCONTRACTKIND.CODE, \n" +
			 " 			ENSERVICESCONTRACTKIND.NAME, \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					coalesce( \n" +
			 " 						sum( selSumPay.sumtotal ) \n" +
			 " 					) \n" +
			 " 				from \n" +
			 " 					( \n" +
			 " 						select \n" +
			 " 							coalesce( \n" +
			 " 								sum( enpayment2so.sumtotal ) \n" +
			 " 							) as sumtotal \n" +
			 " 						from \n" +
			 " 							enpayment2so \n" +
			 " 						where \n" +
			 " 							enpayment2so.servicesobjectrefcode = ENSERVICESOBJECT.CODE \n" +
			 " 							and enpayment2so.paymenttyperefcode in( \n" +
			 " 								1, \n" +
			 " 								2 \n" +
			 " 							) \n" +
			 " 					union all select \n" +
			 " 							- coalesce( \n" +
			 " 								sum( enpayment2so.sumtotal ) \n" +
			 " 							) as sumtotal \n" +
			 " 						from \n" +
			 " 							enpayment2so \n" +
			 " 						where \n" +
			 " 							enpayment2so.servicesobjectrefcode = ENSERVICESOBJECT.CODE \n" +
			 " 							and enpayment2so.paymenttyperefcode in(3) \n" +
			 " 					) as selSumPay \n" +
			 " 			) as selSumPay, \n" +
			 " 			( \n" +
			 " 				select \n" +
			 " 					sum( case when enservicesobject.calctyperefcode = 1 then case when enservicesobject.contracttyperefcode  \n" +
			 " = 5 then case when enservicesobject.code in( select servicesobjectrefcode from enservicesobject2techcondtnsservices where techcondservrefcode in( select  \n" +
			 " code from entechconditionsservcs where coalesce( buildersarea, 0 ) = 0 and coalesce( basestation, 0 ) = 0 and coalesce( smallarchfrm, 0 ) = 0 ) ) then cast(  \n" +
			 " coalesce( cct.totalcost, 0 ) as numeric( 10, 2 ) ) else cast( 0 as numeric( 10, 2 ) ) end else cast( coalesce( cct.totalcost, 0 ) as numeric( 10, 2 ) ) end else  \n" +
			 " sofc.totalsum end ) as summa \n" +
			 " 				from \n" +
			 " 					encalccontracttotal cct, \n" +
			 " 					enplanwork pw left join enservicesfactcalc sofc on \n" +
			 " 					sofc.servicesobjectrefcode = enservicesobject.code \n" +
			 " 				where \n" +
			 " 					enservicesobject.elementcode = pw.elementrefcode \n" +
			 " 					and cct.planrefcode = pw.code \n" +
			 " 					and pw.kindcode = 5 \n" +
			 " 			) as seldohodsum, \n" +
			 " 			ENSERVICESOBJECT.ISNOPAY \n" +

"  ,  (select max(p.datestart)  from enplanwork p \n" +
" 			   where p.staterefcode in (2,3,4) \n" +
" 			     and p.kindcode = 2 \n" +
" 			     and p.code in ( \n" +
" 			    select pw.code from enplanwork pw 			     \n" +
" 			     where pw.elementrefcode = ENSERVICESOBJECT.elementcode /*1017496320*/   \n" +
" 			     union  \n" +
" 			     select tc2pw.planrefcode  \n" +
" 					from ENTechCond2PlanWork tc2pw  \n" +
" 					where tc2pw.techconservicesrefcode in (select so2tc.techcondservrefcode from  \n" +
" enservicesobject2techcondtnsservices so2tc   \n" +
" 					                  where so2tc.servicesobjectrefcode = /*1017044521*/ ENSERVICESOBJECT.code ) \n" +
" 				)) as maxdatestartmonthplan /*макс дата старта месячного плана РМ (реконструкція модернізація), КБ  \n" +
" (капітального будівництва) або ТО (технічне обслуговування */ \n" +
"  , coalesce(( \n" +
" 						select  string_agg(to_char(enpayment2so.dategen,'dd.mm.yyyy') ,',') as datePay \n" +
" 						from enpayment2so \n" +
" 						where enpayment2so.servicesobjectrefcode = ENSERVICESOBJECT.CODE \n" +
" 						  and enpayment2so.paymenttyperefcode in(	1,2)	 \n" +
" 		),'') as datepay /*Дата надходження коштів якщо декылька то через запятую */ \n" +
"  , cnpackcode \n" +
"  , cnsubsystemtyperefcode \n" +

" , (Select replace(trim(string_agg(contragentaddresswork,'')),chr(10),'') from ENContragent enc  \n" +
" 		Where enc.contragentaddresswork is not null   \n" +
" 		And enc.techcondservicesrefcod = 	 \n" +
"         (select so2tc.techcondservrefcode from enservicesobject2techcondtnsservices so2tc  \n" +
"           where so2tc.servicesobjectrefcode = ENSERVICESOBJECT.CODE )) as contragentaddresswork \n" +

" , ( /* если нету вообще акта под задания факты с месячного плана  \n" +
"               * или для какого то одного факта нет акта то ставим статус Роботи не виконані  */ \n" +
" 		select case when  coalesce(sum(cntfact)) > (sum(cntact)) \n" +
" 		                   or  sum(cntfact) is null    then 'Роботи не виконані' else 'Роботи виконані' end as statuswork   \n" +
" 		from ( \n" +
" 				select chf.plannewrefcode , 1 as cntfact , case when a2p.code is not null then 1 else 0 end as cntact \n" +
" 				from   \n" +
" 				  enplancorrecthistory ch \n" +
" 				, enplancorrecthistory chf left join enact2enplanwork a2p on (chf.plannewrefcode = a2p.plancode) \n" +
" 				, ( \n" +
" 				    select p.code as plcode , max(p.datestart)   \n" +
" 				                from enplanwork p  \n" +
" 				 			   where p.staterefcode in (2,3,4)  \n" +
" 				 			     and p.kindcode = 2  \n" +
" 				 			     and p.code in (  \n" +
" 				 			     select pw.code from enplanwork pw 			      \n" +
" 				 			      where pw.elementrefcode = ENSERVICESOBJECT.elementcode     \n" +
" 				 			      union   \n" +
" 				 			     select tc2pw.planrefcode   \n" +
" 				 				   from ENTechCond2PlanWork tc2pw   \n" +
" 				 				  where tc2pw.techconservicesrefcode in (select so2tc.techcondservrefcode  \n" +
" from enservicesobject2techcondtnsservices so2tc    \n" +
" 				 					                                       where so2tc.servicesobjectrefcode =  \n" +
" ENSERVICESOBJECT.code )  \n" +
" 				 				) \n" +
" 				 	group by  p.code  \n" +
" 				 	order by p.datestart desc \n" +
" 				 	limit 1  \n" +
" 				) as monthplanwithmaxdatestart /* план с максимальной датой Рм , кб , то*/ \n" +
" 				where ch.reasoncode = 4 \n" +
" 				and ch.planoldrefcode = monthplanwithmaxdatestart.plcode \n" +
" 				and chf.planoldrefcode = ch.plannewrefcode \n" +
" 				and chf.reasoncode = 5 \n" +
" 		      ) as sel_fact_act \n" +
"             ) as statuswork \n" +
"  \n" +
" , coalesce(( /*выборка актов */         \n" +
" 				select string_agg(NUMBERGEN||' від ' || to_char(DATEGEN,'dd.mm.yyyy') ||  \n" +
" 				' ,статус ' || ENACTSTATUSNAME || ' ,тип акту ' || ENPLANWORKSTATENAME ,' ;' ) as actsinfo  \n" +
" 				from ( \n" +
" 				           \n" +
" 				SELECT ENACT.NUMBERGEN, \n" +
" 				       ENACT.DATEGEN, \n" +
" 				       ENACTSTATUS.NAME as ENACTSTATUSNAME , \n" +
" 				       ENPLANWORKSTATE.NAME as ENPLANWORKSTATENAME \n" +
" 				FROM ENACTSTATUS, \n" +
" 				     ENELEMENT, \n" +
" 				     ENPLANWORKSTATE, \n" +
" 				     ENACT \n" +
" 				WHERE ENACTSTATUS.CODE = ENACT.STATUSREFCODE \n" +
" 				  AND ENELEMENT.CODE = ENACT.ELEMENTCODE \n" +
" 				  AND ENPLANWORKSTATE.CODE = ENACT.ACTTYPEREFCODE \n" +
" 				  AND (ENACT.CODE IN \n" +
" 				         (SELECT a.code \n" +
" 				          FROM enact a \n" +
" 				          WHERE a.code IN \n" +
" 				              (SELECT a2pl.actrefcode \n" +
" 				               FROM enact2enplanwork a2pl \n" +
" 				               WHERE a2pl.plancode IN \n" +
" 				                   (SELECT ct2pl.planrefcode \n" +
" 				                    FROM entechcond2planwork ct2pl \n" +
" 				                    WHERE ct2pl.techconservicesrefcode = ( select qq.techcondservrefcode from  \n" +
" enservicesobject2techcondtnsservices qq  \n" +
" 				                                                            where qq.servicesobjectrefcode =  ENSERVICESOBJECT.CODE ) )) \n" +
" 				            AND a.code NOT IN \n" +
" 				              (SELECT ff.actcode \n" +
" 				               FROM rqfkorder2planfact ff))) \n" +
" 				 \n" +
" 				union ALL           \n" +
" 				 \n" +
" 				SELECT ENACT.NUMBERGEN, \n" +
" 				       ENACT.DATEGEN, \n" +
" 				       ENACTSTATUS.NAME as ENACTSTATUSNAME , \n" +
" 				       ENPLANWORKSTATE.NAME as ENPLANWORKSTATENAME \n" +
" 				FROM ENACTSTATUS, \n" +
" 				     ENELEMENT, \n" +
" 				     ENPLANWORKSTATE, \n" +
" 				     ENACT \n" +
" 				WHERE ENACTSTATUS.CODE = ENACT.STATUSREFCODE \n" +
" 				  AND ENELEMENT.CODE = ENACT.ELEMENTCODE \n" +
" 				  AND ENPLANWORKSTATE.CODE = ENACT.ACTTYPEREFCODE \n" +
" 				  AND ENACT.ELEMENTCODE = ENSERVICESOBJECT.elementcode \n" +
" 				 \n" +
" 				  union all  \n" +
" 				   \n" +
" 				SELECT fo.numberdoc AS numbergen, \n" +
" 				       fo.dategen, \n" +
" 				       fos.name AS ENACTSTATUSNAME, \n" +
" 				       'Підрядний спосіб' AS ENPLANWORKSTATENAME \n" +
" 				FROM rqfkorder fo, \n" +
" 				         rqfkorderstatus fos \n" +
" 				WHERE fo.statuscode = fos.code \n" +
" 				  AND fo.code IN \n" +
" 				    (SELECT rf2pl.fkordercode \n" +
" 				     FROM rqfkorder2planfact rf2pl \n" +
" 				     WHERE rf2pl.plancode IN \n" +
" 				         (SELECT ct2pl.planrefcode \n" +
" 				          FROM entechcond2planwork ct2pl \n" +
" 				          WHERE ct2pl.techconservicesrefcode = ( select qq.techcondservrefcode from  \n" +
"                                                                                   enservicesobject2techcondtnsservices qq  \n" +
" 				                                                            where qq.servicesobjectrefcode =  ENSERVICESOBJECT.CODE ))) \n" +
" 				  ) as selacts \n" +
"            ),'') as actsinfo \n" +
" ------------ \n" +
"            ,coalesce( \n" +
"             (SELECT string_agg(DISTINCT fx.name, ',') AS finexecutor \n" +
"              FROM enplanwork p, finexecutor fx \n" +
"              WHERE p.finexecutorcode = fx.code \n" +
"                AND p.staterefcode IN (2,3,4) \n" +
"                AND p.kindcode IN(4,5) \n" +
"                AND p.code IN \n" +
"                  (SELECT pw.code \n" +
"                   FROM enplanwork pw \n" +
"                   WHERE pw.elementrefcode = ENSERVICESOBJECT.elementcode \n" +
"                   UNION SELECT tc2pw.planrefcode \n" +
"                   FROM ENTechCond2PlanWork tc2pw \n" +
"                   WHERE tc2pw.techconservicesrefcode IN \n" +
"                       (SELECT so2tc.techcondservrefcode \n" +
"                        FROM enservicesobject2techcondtnsservices so2tc \n" +
"                        WHERE so2tc.servicesobjectrefcode = ENSERVICESOBJECT.code))),'') AS finexecutor  \n" +
"            ,(select dateval from ensovalues \n" +
 "                  where\n" +
 "                  servicesobjectcode = ENSERVICESOBJECT.CODE \n" +
 "                  and \n" +
 "                  sovaluestypecode = 4 /*Гранична дата виконання робіт по договору (попередня)*/) as datelimitexecute   \n" +
 "               \n" +
 "           ,(select d.shortname  from entechconditionsobjcts tco, endepartment d \n" +
 "                  where \n" +
 "                  tco.code = ENSERVICESOBJECT.techconobjectscode \n" +
 "                  and tco.departmentcode = d.code \n" +
 "              ) as ENDEPARTMENTSHORTNAME      \n"+
    "  \n" +


" 		from \n" +
			 " 			ENSERVICESOBJECT, \n" +
			 " 			ENDEPARTMENT, \n" +
			 " 			ENSERVICESOBJECTSTATUS, \n" +
			 " 			ENSERVICESCONTRACTKIND, \n" +
			 " 			ENSERVICESOBJECTCALCTP \n" +
			 " 		where \n" +
			 " 			ENDEPARTMENT.CODE = ENSERVICESOBJECT.DEPARTMENTCODE \n" +
			 " 			and ENSERVICESOBJECTSTATUS.CODE = ENSERVICESOBJECT.STATUSREFCODE \n" +
			 " 			and ENSERVICESCONTRACTKIND.CODE = ENSERVICESOBJECT.CONTRACTKINDREFCODE \n" +
			 " 			and ENSERVICESOBJECTCALCTP.CODE = ENSERVICESOBJECT.CALCTYPEREFCODE \n" +
			 " 			and ENSERVICESOBJECT.CONTRACTTYPEREFCODE = '5' \n" +
			 " 			and ENSERVICESOBJECT.CONTRACTKINDREFCODE = '1' \n" +
			 "          and ENSERVICESOBJECT.CONTRACTDATESERVICES between '" + contractDateStart + "' and '" + contractDateFinal + "'" +
			 //"  and ENSERVICESOBJECT.code = 1017048725  " + // tezzzt


			new String(!contractnumberservices.equals("") ?  " and ENSERVICESOBJECT.CONTRACTNUMBERSERVICES like '%"+contractnumberservices+"%'" : " ") +
			 "  \n" +
			 " 		order by ENSERVICESOBJECT.DATEEDIT desc, ENSERVICESOBJECT.CODE desc  \n"
			// +  " 	limit 250 "
			 ;



	  tempSt = connection.prepareStatement(selStr);
      System.out.println(selStr);
      rs = tempSt.executeQuery();

	    ArrayList rows  = new ArrayList();
	    int count = 0;

	    while(rs.next())
      {

	    	System.out.print(" getDataForReportExecPriconectionContract dogovor num start  "+ rs.getString("CONTRACTNUMBERSERVICES"));
	    	count = count+1;


	    	//System.out.print(" CONTRACTNUMBERSERVICES = " + rs.getString("CONTRACTNUMBERSERVICES") );

                    HashMap hashMap = new HashMap();
	                hashMap.put(dsExecPriconectionContract.CONTRACTNUMBERSERVICES , rs.getString("CONTRACTNUMBERSERVICES"));
	                hashMap.put(dsExecPriconectionContract.CONTRACTDATESERVICES , rs.getDate("CONTRACTDATESERVICES"));
	                hashMap.put(dsExecPriconectionContract.ENSERVICESCONTRACTTYPENAME , rs.getString("ENSERVICESCONTRACTTYPENAME"));
	                hashMap.put(dsExecPriconectionContract.MAXDATESTARTMONTHPLAN , rs.getDate("MAXDATESTARTMONTHPLAN"));
	                hashMap.put(dsExecPriconectionContract.DATEPAY, rs.getString("DATEPAY"));
	                hashMap.put(dsExecPriconectionContract.SELSUMPAY, rs.getBigDecimal("SELSUMPAY"));
	                hashMap.put(dsExecPriconectionContract.DATELIMITEXECUTE, rs.getDate("DATELIMITEXECUTE") );

	                // кол-во дней просрочки
	                int countdaydelay = 0;
	                if (rs.getDate("MAXDATESTARTMONTHPLAN") != null && rs.getDate("DATELIMITEXECUTE") != null ){
	                  if (rs.getDate("MAXDATESTARTMONTHPLAN").compareTo(rs.getDate("DATELIMITEXECUTE")) == 1 ){
	                	  countdaydelay =  daysBetween(rs.getDate("MAXDATESTARTMONTHPLAN") , rs.getDate("DATELIMITEXECUTE") );
	                	  countdaydelay = Math.abs(countdaydelay);
	                	  hashMap.put(dsExecPriconectionContract.COUNTDAYDELAY, countdaydelay );
	                  }
	                    else
		                	hashMap.put(dsExecPriconectionContract.COUNTDAYDELAY, 0 );
	                } else
	                	hashMap.put(dsExecPriconectionContract.COUNTDAYDELAY, 0 );


	                hashMap.put(dsExecPriconectionContract.CONTRAGENTNAME, rs.getString("CONTRAGENTNAME"));

	                hashMap.put(dsExecPriconectionContract.CONTRAGENTADDRESSWORK, rs.getString("CONTRAGENTADDRESSWORK"));

	                hashMap.put(dsExecPriconectionContract.STATUSWORK, rs.getString("STATUSWORK"));

	                hashMap.put(dsExecPriconectionContract.BUHSTATUS, rs.getString("BUHSTATUS"));

	                hashMap.put(dsExecPriconectionContract.ACTSINFO, rs.getString("ACTSINFO"));

	                hashMap.put(dsExecPriconectionContract.FINEXECUTOR, rs.getString("FINEXECUTOR"));

	                hashMap.put(dsExecPriconectionContract.CNDEPARTMENT, rs.getString("ENDEPARTMENTSHORTNAME"));

	                rows.add(hashMap);

	                System.out.print(" getDataForReportExecPriconectionContract dogovor num finish  "+ rs.getString("CONTRACTNUMBERSERVICES"));

      }



	    return new dsFinansirOsvoenieBalans(rows.toArray());

	    } catch (SQLException e) {
			throw new EnergyproSystemException(e.getMessage());
		}
	      catch (DatasourceConnectException e) {
	       throw new EnergyproSystemException(e.getMessage());
	    }

	     finally {
	        try {
	            if (rs != null) rs.close();
	            } catch (SQLException e) {
	        }
	        try {
	            if (tempSt != null) tempSt.close();
	        } catch (SQLException e) {
	        }

//	        try {
//                if  (connection != null && connection.isClosed())
//                    connection.close();
//            } catch (SQLException e) {
//            }
	    }
	}



private int daysBetween(Date d1, Date d2){
    return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
   }

/**
* @throws PersistenceException **/

public int getWorkDaysBetweenDates(int day , int month , int year) throws PersistenceException
{
   int out = 0;
   JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
   Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
   UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

   mDaxLogic mdLogic = new mDaxLogic(connection, userProfile);

   out = mdLogic.getWorkDaysBetweenDates(Tools.encodeDate(year, month, day) , Tools.encodeDate(year, month, day));
   return  out;

}






	/**
	 * формирование QR-code
	 *
	 * @param iban
	 * @param okpo
	 * @param toPay
	 * @param contractNumberServices
	 * @param contragentName
	 *
	 * @return BufferedImage
	 */
	public BufferedImage getQrCode(String iban, String okpo, BigDecimal toPay, String contractNumberServices,
			String contragentName) {

		toPay = toPay.setScale(2, BigDecimal.ROUND_HALF_UP);

		String str = "BCD\n"
				+ "001\n"
				+ "1\n"
				+ "UCT\n"
				+ "\n"
				+ "АТ \"Херсонобленерго\"  \n";

		str = str + iban + "\n";
		str = str + okpo + "\n";
		str = str + "UAH" + toPay + "\n\n\n";

		/** назначение платежа ???? */
		str = str + "Оплата послуг. Рах.№ " + contractNumberServices + " від "
				+ new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ". Платник " + contragentName;

		return createQRcode(str);
	}



	/**
	 * формирование QR-code
	 *
	 * @param str
	 * @return BufferedImage
	 */
	public BufferedImage createQRcode(String str) {
		try {

			int width = 200;
			int height = 200;

			Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
			String charset = "UTF-8";
			hintMap.put(EncodeHintType.CHARACTER_SET, charset);

			hintMap.put(EncodeHintType.MARGIN, 0); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			String imageFormat = "png"; // could be "gif", "tiff", "jpeg"

			BitMatrix bitMatrix = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, width, height, hintMap);

			return MatrixToImageWriter.toBufferedImage(bitMatrix);

		} catch (WriterException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public String getSettings(String key) throws PersistenceException
	{
	   String out = ""; 
	   JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
	   Connection connection = (Connection) this.parametersMap.get("REPORT_CONNECTION").getValue();
	   UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

	   FINScriptlet finScr = new FINScriptlet();

	   out = finScr.getSettings( key); 
	   return  out;

	}
	
	

}

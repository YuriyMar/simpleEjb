package com.ksoe.energynet.reports.common;

    import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.reports.RepOrder.RQFKOrder.OSTableDS;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.report.exception.ReportSystemException;

    public class FINOSScriptlet extends JRDefaultScriptlet{

        private transient java.sql.Connection finConnection = null;

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
            String s = "01" + "." + calendar.get(Calendar.MONTH)+1 + "." + calendar.get(Calendar.YEAR);
            //  System.out.println(s);
            return out;
        }

        protected java.sql.Connection getConnection(String dataSourceName)
                throws DatasourceConnectException {
            try {
                if (finConnection != null && !finConnection.isClosed()) {
                    return finConnection;
                }

                InitialContext initialContext = new InitialContext();
                DataSource dataSource = (DataSource) initialContext
                        .lookup(dataSourceName);
                finConnection = dataSource.getConnection();
                return finConnection;
            } catch (NamingException e) {
                throw new DatasourceConnectException(dataSourceName, e);
            } catch (SQLException e) {
                throw new DatasourceConnectException(dataSourceName, e);
            }
        }

        @Override
		public void beforeReportInit() throws JRScriptletException {
            //JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
            //connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
            //userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
            //logic = new TransportLogic(connection, userProfile);
            try {
                finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            } catch (DatasourceConnectException e) {
                throw new ReportSystemException(e);
            }
        }

        public BigDecimal getDepreciation(String inv_num , Date dd) throws PersistenceException
        {
            Date firstDay = getFirstDayInMonth(dd);

            String sql =  //"    /* селект амортизации из данных за последний квартал*/ " +
        " Select cast(sum_izn_b as numeric(15,2)) as qq From( " +
                      " SELECT max(a.num_un) as num_un, a.sum_izn_b " +
                      "  FROM os_t.izn_nachisl a , os_t.ostable oo " +
                      " Where a.num_un = oo.num_un " +
                      "   And oo.kod_inv = ? " +
                      "   And a.dt_nachisl = ? " +
                      "   Group by  a.sum_izn_b ) " +
            " UNION " +

            " Select sum_izn_b From ( " +
                      " SELECT max(rb.num_un) as num_un, rb.sum_izn_b " +
                      "  FROM os_t.izn_nachisl_rb rb , os_t.ostable oo " +
                      " Where rb.num_un = oo.num_un " +
                      "   And oo.kod_inv = ? " +
                      "    And rb.dt_nachisl = ? " +
                      "   Group by  rb.sum_izn_b ) " +
            " UNION " +
                  //  " /* селект амортизации (если нет амортизации за тек квартал и за предыдущие ) если выбирается амортизац за дату большую чем в бух учете  */ " +
            " Select sum_izn_b From ( " +
                      " SELECT max(inc.num_un) as num_un, inc.sum_izn_b " +
                      "  FROM os_t.izn_nachisl inc , os_t.ostable oo " +
                      " Where inc.num_un = oo.num_un " +
                      "   And oo.kod_inv = ? " +
                      "   And inc.dt_nachisl = Trunc((Select dt_current from os_t.osdate),'mm') " +
                      "   and Trunc((Select dt_current from os_t.osdate),'mm') < ? " +
                      "   Group by  inc.sum_izn_b ) " ;

            // sql = "select 1 from dual " ;


            System.out.println("sql : " + sql);
            System.out.println("param : " + firstDay.toString());
            System.out.println("param : " + inv_num);

            BigDecimal out = new BigDecimal(0);
            try {

                PreparedStatement statement = finConnection.prepareStatement(sql);

                statement.setString(1, inv_num);
                statement.setDate(2, new java.sql.Date(firstDay.getTime()));
                statement.setString(3, inv_num);
                statement.setDate(4,  new java.sql.Date(firstDay.getTime()));
                statement.setString(5, inv_num);
                statement.setDate(6,  new java.sql.Date(firstDay.getTime()));

            //    System.out.println(dd);
            //    System.out.println(new java.sql.Date(firstDay.getTime()));
            //    System.out.println(inv_num);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next())
                out = resultSet.getBigDecimal(1);

            //    out = new BigDecimal(1);


                return out;
            }

                catch (SQLException e) {
                    //out = new BigDecimal(2);
                    System.out.println(    e.getMessage());
                    //finConnection.
                    return out;


            } finally {
//                try {
                    //if (connection != null && !connection.isClosed())
//                        connection.close();
                //} catch (SQLException e) {
                //}
            }

        }
        public String ebana(Integer  PcodeAkt)
        //throws PersistenceException
        {
            String out = new String("" + "---");
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
            //        throw new EnergyproSystemException("Count work time not found for date " + d);
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
                //                try {
                //if (connection != null && !connection.isClosed())
                //                        connection.close();
                //} catch (SQLException e) {
                //}

                return out;
            }

            */
        }



        public JRDataSource getOSTableData(int num_un)
        {
            try {

                ArrayList rows  = new ArrayList();
                String query =
                    " SELECT ' ' || a.kod_inv, a.str_name, ' ' || nvl(a.kod_oborud, ''), " +
                    "        a.kod_subsch_b, a.sum_st_perv, a.dt_vypusk, a.dt_vvod_eksp, " +
                    "        (select dt_current from os_t.osdate) as dt_current, " +
                    "        (select ag.in_num from sprav.agree ag where ag.code = a.code_dogovor) as agree_in_num, " +
                    "        (select ag.in_date from sprav.agree ag where ag.code = a.code_dogovor) as agree_in_date, " +
                    "        (select o.st_current_b from os_t.os_dinam o where o.num_un = " + num_un + ") as st_current_b " +
                    " FROM os_t.ostable a " +
                    " WHERE a.num_un = " + num_un;


                if (finConnection == null || finConnection.isClosed()){
                    finConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                }

                Statement tempSt;
                tempSt = finConnection.createStatement();
                ResultSet rs = tempSt.executeQuery(query);

                while(rs.next())
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put(OSTableDS.KOD_INV, rs.getString(1));
                    hashMap.put(OSTableDS.STR_NAME, rs.getString(2));
                    hashMap.put(OSTableDS.KOD_OBORUD, rs.getString(3));
                    hashMap.put(OSTableDS.KOD_SUBSCH_B, rs.getString(4));
                    hashMap.put(OSTableDS.SUM_ST_PERV, rs.getBigDecimal(5));
                    hashMap.put(OSTableDS.DT_VYPUSK, rs.getDate(6));
                    hashMap.put(OSTableDS.DT_VVOD_EKSP, rs.getDate(7));
                    hashMap.put(OSTableDS.DT_CURRENT, rs.getDate(8));
                    hashMap.put(OSTableDS.AGREE_IN_NUM, rs.getString(9));
                    hashMap.put(OSTableDS.AGREE_IN_DATE, rs.getDate(10));
                    hashMap.put(OSTableDS.ST_CURRENT_B, rs.getBigDecimal(11));
                    rows.add(hashMap);
                }

                rs.close();
                tempSt.close();

                return new OSTableDS(rows.toArray());

            } catch (SQLException e) {
                throw new ReportSystemException(e);
            } catch (DatasourceConnectException e) {
                throw new ReportSystemException(e);
            } finally {
                try {
                    if (finConnection != null && !finConnection.isClosed()) {
                        finConnection.close();
                    }
                } catch (SQLException e) {
                }
            }
        }





    }



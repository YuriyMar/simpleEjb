package com.ksoe.energynet.util.AnswerFileDodatokOk;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.ksoe.energynet.reports.common.FINScriptlet;
import com.ksoe.energynet.valueobject.ENServicesContractStatus;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.rqorder.valueobject.RQFKOrder;
import com.ksoe.rqorder.valueobject.RQFKOrderKind;

public class AnswerFileDodatokOkGenerator {
    public AnswerFileDodatokOkList getAnswerFileDodatokOk(
            AnswerFileDodatokOkList inputList, UserProfile userProfile,
            Connection connection) {
        Statement statement = null;
        ResultSet set = null;
        String selectStr = "";
        int services_FromSide = 1;
        int services_OnSide_Materials = 2;

        String CodeFormNalog = "" ;
        String NameContragent;
        String OKPOContragent;
        String Type_pr;
        String Type_sm = "";
        try {
            /* pr :
             *  1- Услуги на сторону
             *  2- Услуги со стороны
             *  3- Материалы*/

            selectStr =
                       // " Select so.partnercode , so.name ,  sum(s.costwithoutvat) as costwithoutvat , 1 as pr " +
                    // " From encalctotalcost s , enplanwork p   , enservicesobject so " +
                    //    " where s.planrefcode = p.code " +
                    //    " and p.kindcode = 5 " +
                    //    " and p.elementrefcode = so.elementcode " +
                    //    " and to_char(so.contractdateservices,'yyyy')::numeric = " +  inputList.year +
                    //    " and to_char(so.contractdateservices,'mm')::numeric between " +  inputList.monthStart + " and " + inputList.monthFinal +
                        //"   and upper(so.partnercode) like '%0ЛПТ%' " +
                    //    " and so.contractstatusrefcode = " + ENServicesContractStatus.PAID  +  /*Сплачений*/
                    //    " group by so.partnercode , so.name" +

                        " Select so.partnercode , so.name ,  sum(s.costwithoutvat) as costwithoutvat, 1 as pr  " +
                        " from enplancorrecthistory pc3 , encalctotalcost s , enplanwork p   , enservicesobject so   " +
                        " where pc3.plannewrefcode in ( " +
                        " select pc2.planoldrefcode from enplancorrecthistory pc2   " +
                        " where pc2.plannewrefcode in ( " +
                        " select pc.planoldrefcode from enplancorrecthistory pc , enact2enplanwork a2p , enact a " +
                        " where pc.reasoncode =5 /*перенос в мес план*/ " +
                        " and pc.plannewrefcode = a2p.plancode  " +
                        " and a2p.actrefcode = a.code  " +
                        " and to_char(a.dategen,'yyyy')::numeric = " +  inputList.year +
                        " and to_char(a.dategen,'mm')::numeric between  " +  inputList.monthStart + " and " + inputList.monthFinal +
                        " and a.statusrefcode = 3 " +
                        " ) " +
                        " and pc2.reasoncode = 4   " +
                        " ) " +
                        " and pc3.reasoncode = 13 " +
                        " and s.planrefcode = p.code  " +
                        " and p.kindcode = 5 " +
                        " and p.elementrefcode = so.elementcode " +
                        " and so.contractstatusrefcode = " + ENServicesContractStatus.PAID  +  /*Сплачений*/
                        " and pc3.planoldrefcode = p.code " +
                        //"   and upper(so.partnercode) like '%ФОП Таран Олена Іванівна%' " +
                        " group by so.partnercode , so.name" +

                        " UNION ALL " +
                        // услуги со стороны
                        " Select  ro.id::varchar as idorg ,  ro.name ,  sum(rf.sumwithoutnds) as costwithoutvat , 2 as pr " +
                        "    From " +
                        " rqfkorder rf    , rqorg ro " +
                        " where rf.kindcode = " + RQFKOrderKind.SERVICES_FROM_SIDE +
                        " and rf.orgcode = ro.code " +
                        " and rf.statuscode = 3 " +
                        " and to_char(rf.dateposting,'yyyy')::numeric = " +  inputList.year +
                        " and to_char(rf.dateposting,'mm')::numeric between  " +  inputList.monthStart + " and " + inputList.monthFinal +
                        " group by ro.id , ro.name " +
                        " UNION ALL " +
                        // материалы
                        " Select  ro.id::varchar as idorg ,  ro.name ,  sum(rf.sumwithoutnds) as costwithoutvat , 2 as pr " +
                        "    From " +
                        " rqfkorder rf    , rqorg ro " +
                        " where rf.kindcode = " + RQFKOrderKind.PRIHOD_POSTAVKA +
                        " and rf.orgcode = ro.code " +
                        " and rf.statuscode not in (1,2) " +
                        " and to_char(rf.dateposting,'yyyy')::numeric = " +  inputList.year +
                        " and to_char(rf.dateposting,'mm')::numeric between  " +  inputList.monthStart + " and " + inputList.monthFinal +
                        " group by ro.id , ro.name " +
                        " order by pr "

                        ;

            statement = connection.createStatement();
            set = statement.executeQuery(selectStr);

            AnswerFileDodatokOk expList;
            inputList.list = new Vector();

            FINScriptlet fs = new FINScriptlet();
            while(set.next())
            {
                CodeFormNalog = "999";
                NameContragent = "";
                OKPOContragent = "";
                Type_pr = "";
                if (set.getInt(4) == services_FromSide ) {
                    Type_sm = "D";
                    CodeFormNalog =    fs.getCodeFormNalogByContragentCodeString(set.getString(1));
                if (CodeFormNalog.equals("1") || CodeFormNalog.equals("4") ) {
                    NameContragent = fs.getNameContragentByContragentCodeString(set.getString(1));
                    OKPOContragent = fs.getCodeOKPOByContragentCodeStringPDF(set.getString(1));
                    if (CodeFormNalog.equals("1")){
                        Type_pr = "F";
                    }
                    else
                        Type_pr = "Y";
                }
                }

                if (set.getInt(4) == services_OnSide_Materials ) {
                    Type_sm = "R";
                    CodeFormNalog =    fs.getCodeFormNalogByContragentCode( set.getInt(1) );
                if (CodeFormNalog.equals("1") || CodeFormNalog.equals("4") ) {
                    NameContragent = fs.getNameContragentByContragentCode(set.getInt(1));
                    OKPOContragent = fs.getCodeOKPOByContragentCode(set.getInt(1));
                    if (CodeFormNalog.equals("1")){
                        Type_pr = "F";
                    }
                    else
                        Type_pr = "Y";
                }
                }

                if (CodeFormNalog.equals("1") || CodeFormNalog.equals("4")) {


                expList = new AnswerFileDodatokOk();

                expList.name_pr = NameContragent;
                expList.inn = OKPOContragent;
                expList.sm = set.getBigDecimal(3);
                expList.type_pr = Type_pr;
                expList.type_sm = Type_sm;

                inputList.list.add(expList);
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nstatement - " + selectStr);
            throw new EnergyproSystemException("Select SQLException", e);
        }

        catch (PersistenceException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (set != null)
                    set.close();
            } catch (SQLException e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
            }
        }
        return inputList;
    }

    public class ExpList {

        public String name_pr;
        public String inn;
        public BigDecimal sm;
        public String type_pr;
        public String type_sm;
    }
}
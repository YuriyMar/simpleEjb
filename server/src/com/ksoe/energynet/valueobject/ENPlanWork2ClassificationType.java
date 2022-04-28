
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork2ClassificationType;  	
  */

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

    import  com.ksoe.energynet.valueobject.references.ENPlanWorkRef;
    import  com.ksoe.techcard.valueobject.references.TKClassificationTypeRef;
    import  com.ksoe.energynet.valueobject.references.ENConnectionWorkTypeRef;
    import  com.ksoe.techcard.valueobject.references.TKCalcKindRef;

public class ENPlanWork2ClassificationType implements Serializable {

private static final long serialVersionUID = 1L;

	public static final int NOT_JOBSBYTIME = 0;
	public static final int NOT_VISITCLIENT = 0;
	
	public static final int JOBSBYTIME = 1;
	public static final int VISITCLIENT = 1;

    public int  code = Integer.MIN_VALUE;
    public BigDecimal  countGen; 
    public String  commentGen; 
    public String  userGen; 
    public Date dateEdit ;
    public BigDecimal  machineHours; 
    public BigDecimal  relocationKm; 
    public BigDecimal  transportationLoad; 
    public int  isPrintOnKmOrMH = Integer.MIN_VALUE;
    public BigDecimal  costWorksContractor; 
    public Date dateGen ;
    public Date timeStart ;
    public Date timeFinal ;
    public int  codeVirtualBrigade = Integer.MIN_VALUE;
    public int  isJobsByTime = Integer.MIN_VALUE;
    public int  isVisitClient = Integer.MIN_VALUE;
    public BigDecimal  productionExpensesPercent; 
    public BigDecimal  administrativeExpensesPercent; 
    public long  modify_time = Long.MIN_VALUE;
    public int  servicePaymentKind = Integer.MIN_VALUE;
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public TKClassificationTypeRef classificationTypeRef = new TKClassificationTypeRef();
    public ENConnectionWorkTypeRef connectionWorkTypeRef = new ENConnectionWorkTypeRef();
    public TKCalcKindRef calcKindRef = new TKCalcKindRef();
    public static final String tableName = "ENPLANWORK2CLASSFCTNTP";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2CLASSFCTNTP.CODE";
    public static final String countGen_Attr = "countGen";
    public static final String countGen_Field = "COUNTGEN";
    public static final String countGen_QFielld = "ENPLANWORK2CLASSFCTNTP.COUNTGEN";
    public static final String commentGen_Attr = "commentGen";
    public static final String commentGen_Field = "COMMENTGEN";
    public static final String commentGen_QFielld = "ENPLANWORK2CLASSFCTNTP.COMMENTGEN";
    public static final String userGen_Attr = "userGen";
    public static final String userGen_Field = "USERGEN";
    public static final String userGen_QFielld = "ENPLANWORK2CLASSFCTNTP.USERGEN";
    public static final String dateEdit_Attr = "dateEdit";
    public static final String dateEdit_Field = "DATEEDIT";
    public static final String dateEdit_QFielld = "ENPLANWORK2CLASSFCTNTP.DATEEDIT";
    public static final String machineHours_Attr = "machineHours";
    public static final String machineHours_Field = "MACHINEHOURS";
    public static final String machineHours_QFielld = "ENPLANWORK2CLASSFCTNTP.MACHINEHOURS";
    public static final String relocationKm_Attr = "relocationKm";
    public static final String relocationKm_Field = "RELOCATIONKM";
    public static final String relocationKm_QFielld = "ENPLANWORK2CLASSFCTNTP.RELOCATIONKM";
    public static final String transportationLoad_Attr = "transportationLoad";
    public static final String transportationLoad_Field = "TRANSPORTATIONLOAD";
    public static final String transportationLoad_QFielld = "ENPLANWORK2CLASSFCTNTP.TRANSPORTATIONLOAD";
    public static final String isPrintOnKmOrMH_Attr = "isPrintOnKmOrMH";
    public static final String isPrintOnKmOrMH_Field = "ISPRINTONKMORMH";
    public static final String isPrintOnKmOrMH_QFielld = "ENPLANWORK2CLASSFCTNTP.ISPRINTONKMORMH";
    public static final String costWorksContractor_Attr = "costWorksContractor";
    public static final String costWorksContractor_Field = "COSTWORKSCONTRACTOR";
    public static final String costWorksContractor_QFielld = "ENPLANWORK2CLASSFCTNTP.COSTWORKSCONTRACTOR";
    public static final String dateGen_Attr = "dateGen";
    public static final String dateGen_Field = "DATEGEN";
    public static final String dateGen_QFielld = "ENPLANWORK2CLASSFCTNTP.DATEGEN";
    public static final String timeStart_Attr = "timeStart";
    public static final String timeStart_Field = "TIMESTART";
    public static final String timeStart_QFielld = "ENPLANWORK2CLASSFCTNTP.TIMESTART";
    public static final String timeFinal_Attr = "timeFinal";
    public static final String timeFinal_Field = "TIMEFINAL";
    public static final String timeFinal_QFielld = "ENPLANWORK2CLASSFCTNTP.TIMEFINAL";
    public static final String codeVirtualBrigade_Attr = "codeVirtualBrigade";
    public static final String codeVirtualBrigade_Field = "CODEVIRTUALBRIGADE";
    public static final String codeVirtualBrigade_QFielld = "ENPLANWORK2CLASSFCTNTP.CODEVIRTUALBRIGADE";
    public static final String isJobsByTime_Attr = "isJobsByTime";
    public static final String isJobsByTime_Field = "ISJOBSBYTIME";
    public static final String isJobsByTime_QFielld = "ENPLANWORK2CLASSFCTNTP.ISJOBSBYTIME";
    public static final String isVisitClient_Attr = "isVisitClient";
    public static final String isVisitClient_Field = "ISVISITCLIENT";
    public static final String isVisitClient_QFielld = "ENPLANWORK2CLASSFCTNTP.ISVISITCLIENT";
    public static final String productionExpensesPercent_Attr = "productionExpensesPercent";
    public static final String productionExpensesPercent_Field = "PRODUCTIONEXPENSESPERCENT";
    public static final String productionExpensesPercent_QFielld = "ENPLANWORK2CLASSFCTNTP.PRODUCTIONEXPENSSPRCNT";
    public static final String administrativeExpensesPercent_Attr = "administrativeExpensesPercent";
    public static final String administrativeExpensesPercent_Field = "ADMINISTRATIVEEXPENSESPERCENT";
    public static final String administrativeExpensesPercent_QFielld = "ENPLANWORK2CLASSFCTNTP.ADMINISTRATVXPNSSPRCNT";
    public static final String modify_time_Attr = "modify_time";
    public static final String modify_time_Field = "MODIFY_TIME";
    public static final String modify_time_QFielld = "ENPLANWORK2CLASSFCTNTP.MODIFY_TIME";
 public static final String servicePaymentKind_Attr = "servicePaymentKind";
    public static final String servicePaymentKind_Field = "SERVICEPAYMENTKIND";
    public static final String servicePaymentKind_QFielld = "ENPLANWORK2CLASSFCTNTP.SERVICEPAYMENTKIND";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2CLASSFCTNTP.PLANREFCODE";
    public static final String classificationTypeRef_Attr = "classificationTypeRef";
    public static final String classificationTypeRef_Field = "CLASSIFICATIONTYPERFCD";
    public static final String  classificationTypeRef_QFielld = "ENPLANWORK2CLASSFCTNTP.CLASSIFICATIONTYPERFCD";
    public static final String connectionWorkTypeRef_Attr = "connectionWorkTypeRef";
    public static final String connectionWorkTypeRef_Field = "CONNECTIONWORKTYPERFCD";
    public static final String  connectionWorkTypeRef_QFielld = "ENPLANWORK2CLASSFCTNTP.CONNECTIONWORKTYPERFCD";
    public static final String calcKindRef_Attr = "calcKindRef";
    public static final String calcKindRef_Field = "CALCKINDREFCODE";
    public static final String  calcKindRef_QFielld = "ENPLANWORK2CLASSFCTNTP.CALCKINDREFCODE";



    public void setCode(int aValue){
       code = aValue;
    }

    public int getCode(){
       return code;
    }


    public void setCountGen(BigDecimal aValue){
       countGen = aValue;
    }

    public BigDecimal getCountGen(){
       return countGen;
    }


    public void setCommentGen(String aValue){
       commentGen = aValue;
    }

    public String getCommentGen(){
       return commentGen;
    }


    public void setUserGen(String aValue){
       userGen = aValue;
    }

    public String getUserGen(){
       return userGen;
    }


    public void setDateEdit(Date aValue){
       dateEdit = aValue;
    }

    public Date getDateEdit(){
       return dateEdit;
    }


    public void setMachineHours(BigDecimal aValue){
       machineHours = aValue;
    }

    public BigDecimal getMachineHours(){
       return machineHours;
    }


    public void setRelocationKm(BigDecimal aValue){
       relocationKm = aValue;
    }

    public BigDecimal getRelocationKm(){
       return relocationKm;
    }


    public void setTransportationLoad(BigDecimal aValue){
       transportationLoad = aValue;
    }

    public BigDecimal getTransportationLoad(){
       return transportationLoad;
    }


    public void setIsPrintOnKmOrMH(int aValue){
       isPrintOnKmOrMH = aValue;
    }

    public int getIsPrintOnKmOrMH(){
       return isPrintOnKmOrMH;
    }


    public void setCostWorksContractor(BigDecimal aValue){
       costWorksContractor = aValue;
    }

    public BigDecimal getCostWorksContractor(){
       return costWorksContractor;
    }


    public void setDateGen(Date aValue){
       dateGen = aValue;
    }

    public Date getDateGen(){
       return dateGen;
    }


    public void setTimeStart(Date aValue){
       timeStart = aValue;
    }

    public Date getTimeStart(){
       return timeStart;
    }


    public void setTimeFinal(Date aValue){
       timeFinal = aValue;
    }

    public Date getTimeFinal(){
       return timeFinal;
    }


    public void setCodeVirtualBrigade(int aValue){
       codeVirtualBrigade = aValue;
    }

    public int getCodeVirtualBrigade(){
       return codeVirtualBrigade;
    }


    public void setIsJobsByTime(int aValue){
       isJobsByTime = aValue;
    }

    public int getIsJobsByTime(){
       return isJobsByTime;
    }


    public void setIsVisitClient(int aValue){
       isVisitClient = aValue;
    }

    public int getIsVisitClient(){
       return isVisitClient;
    }


    public void setProductionExpensesPercent(BigDecimal aValue){
       productionExpensesPercent = aValue;
    }

    public BigDecimal getProductionExpensesPercent(){
       return productionExpensesPercent;
    }


    public void setAdministrativeExpensesPercent(BigDecimal aValue){
       administrativeExpensesPercent = aValue;
    }

    public BigDecimal getAdministrativeExpensesPercent(){
       return administrativeExpensesPercent;
    }


    public void setModify_time(long aValue){
       modify_time = aValue;
    }

    public long getModify_time(){
       return modify_time;
    }

    public void setServicePaymentKind(int aValue){
       servicePaymentKind = aValue;
    }

    public int getServicePaymentKind(){
       return servicePaymentKind;
    }

    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }
    public void setClassificationTypeRef(TKClassificationTypeRef aValue){
       classificationTypeRef = aValue;
    }

    public TKClassificationTypeRef getClassificationTypeRef(){
       return classificationTypeRef;
    }
    public void setConnectionWorkTypeRef(ENConnectionWorkTypeRef aValue){
       connectionWorkTypeRef = aValue;
    }

    public ENConnectionWorkTypeRef getConnectionWorkTypeRef(){
       return connectionWorkTypeRef;
    }
    public void setCalcKindRef(TKCalcKindRef aValue){
       calcKindRef = aValue;
    }

    public TKCalcKindRef getCalcKindRef(){
       return calcKindRef;
    }

} // end of ENPlanWork2ClassificationTypeValueObject


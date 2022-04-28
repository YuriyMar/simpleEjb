
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork;
  */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ksoe.energynet.valueobject.references.ENDepartmentRef;
import com.ksoe.energynet.valueobject.references.ENElementRef;
import com.ksoe.energynet.valueobject.references.ENIPImplementationTypeRef;
import com.ksoe.energynet.valueobject.references.ENInvestProgramGroupsRef;
import com.ksoe.energynet.valueobject.references.ENPlanWorkFormRef;
import com.ksoe.energynet.valueobject.references.ENPlanWorkSourceRef;
import com.ksoe.energynet.valueobject.references.ENPlanWorkStateRef;
import com.ksoe.energynet.valueobject.references.ENPlanWorkTypeRef;
import com.ksoe.energypro.valueobject.references.EPRenRef;
import com.ksoe.rqorder.valueobject.RQDDSCodes;

public class ENPlanWork implements Serializable {

    public int  code = Integer.MIN_VALUE;
    public Date dateGen ;
    public Date dateStart ;
    public Date dateFinal ;
    public int  yearGen = Integer.MIN_VALUE;
    public int  monthGen = Integer.MIN_VALUE;
    public int  yearOriginal = Integer.MIN_VALUE;
    public int  monthOriginal = Integer.MIN_VALUE;
    public String  commentGen;
    public String  userGen;
    public Date dateEdit ;
    public BigDecimal  distanceAlong;
    public BigDecimal  distanceTo;
    public String  workOrderNumber;
    public Date dateWorkOrder ;
    public String  priConnectionNumber;
    public Date dateEndPriConnection ;
    public BigDecimal  investWorksAmount;
    public String  investWorksDescription;
    public Date investDateStartWork;
    public int  investWorkMeasCode = Integer.MIN_VALUE;
    public int  servicesFSideFinId = Integer.MIN_VALUE;
    public String  servicesFSideCnNum;
    public BigDecimal  totalTimeHours;
    public BigDecimal  totalTimeDays;
    public String  investItemNumber;
    public int  causeDisconnection = Integer.MIN_VALUE;
    public String  domain_info;
    public long  modify_time = Long.MIN_VALUE;
    public ENPlanWorkStatus status = new ENPlanWorkStatus();
    public ENElementRef elementRef = new ENElementRef();
    public ENPlanWorkTypeRef typeRef = new ENPlanWorkTypeRef();
    public ENPlanWorkKind kind = new ENPlanWorkKind();
    public EPRenRef renRef = new EPRenRef();
    public FINExecutor finExecutor = new FINExecutor();
    public ENPlanWorkStateRef stateRef = new ENPlanWorkStateRef();
    public ENPlanWorkFormRef formRef = new ENPlanWorkFormRef();
    public ENPlanWorkSourceRef sourceRef = new ENPlanWorkSourceRef();
    public RQDDSCodes ddsCodes = new RQDDSCodes();
    public ENDepartmentRef budgetRef = new ENDepartmentRef();
    public ENDepartmentRef responsibilityRef = new ENDepartmentRef();
    public ENDepartmentRef departmentRef = new ENDepartmentRef();
    public ENInvestProgramGroupsRef invgroupRef = new ENInvestProgramGroupsRef();
    public ENIPImplementationTypeRef ipImplementTypeRef = new ENIPImplementationTypeRef();
    public static final String tableName = "ENPLANWORK";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK.CODE";
    public static final String dateGen_Attr = "dateGen";
    public static final String dateGen_Field = "DATEGEN";
    public static final String dateGen_QFielld = "ENPLANWORK.DATEGEN";
    public static final String dateStart_Attr = "dateStart";
    public static final String dateStart_Field = "DATESTART";
    public static final String dateStart_QFielld = "ENPLANWORK.DATESTART";
    public static final String dateFinal_Attr = "dateFinal";
    public static final String dateFinal_Field = "DATEFINAL";
    public static final String dateFinal_QFielld = "ENPLANWORK.DATEFINAL";
    public static final String yearGen_Attr = "yearGen";
    public static final String yearGen_Field = "YEARGEN";
    public static final String yearGen_QFielld = "ENPLANWORK.YEARGEN";
    public static final String monthGen_Attr = "monthGen";
    public static final String monthGen_Field = "MONTHGEN";
    public static final String monthGen_QFielld = "ENPLANWORK.MONTHGEN";
    public static final String yearOriginal_Attr = "yearOriginal";
    public static final String yearOriginal_Field = "YEARORIGINAL";
    public static final String yearOriginal_QFielld = "ENPLANWORK.YEARORIGINAL";
    public static final String monthOriginal_Attr = "monthOriginal";
    public static final String monthOriginal_Field = "MONTHORIGINAL";
    public static final String monthOriginal_QFielld = "ENPLANWORK.MONTHORIGINAL";
    public static final String commentGen_Attr = "commentGen";
    public static final String commentGen_Field = "COMMENTGEN";
    public static final String commentGen_QFielld = "ENPLANWORK.COMMENTGEN";
    public static final String userGen_Attr = "userGen";
    public static final String userGen_Field = "USERGEN";
    public static final String userGen_QFielld = "ENPLANWORK.USERGEN";
    public static final String dateEdit_Attr = "dateEdit";
    public static final String dateEdit_Field = "DATEEDIT";
    public static final String dateEdit_QFielld = "ENPLANWORK.DATEEDIT";
    public static final String distanceAlong_Attr = "distanceAlong";
    public static final String distanceAlong_Field = "DISTANCEALONG";
    public static final String distanceAlong_QFielld = "ENPLANWORK.DISTANCEALONG";
    public static final String distanceTo_Attr = "distanceTo";
    public static final String distanceTo_Field = "DISTANCETO";
    public static final String distanceTo_QFielld = "ENPLANWORK.DISTANCETO";
    public static final String workOrderNumber_Attr = "workOrderNumber";
    public static final String workOrderNumber_Field = "WORKORDERNUMBER";
    public static final String workOrderNumber_QFielld = "ENPLANWORK.WORKORDERNUMBER";
    public static final String dateWorkOrder_Attr = "dateWorkOrder";
    public static final String dateWorkOrder_Field = "DATEWORKORDER";
    public static final String dateWorkOrder_QFielld = "ENPLANWORK.DATEWORKORDER";
    public static final String priConnectionNumber_Attr = "priConnectionNumber";
    public static final String priConnectionNumber_Field = "PRICONNECTIONNUMBER";
    public static final String priConnectionNumber_QFielld = "ENPLANWORK.PRICONNECTIONNUMBER";
    public static final String dateEndPriConnection_Attr = "dateEndPriConnection";
    public static final String dateEndPriConnection_Field = "DATEENDPRICONNECTION";
    public static final String dateEndPriConnection_QFielld = "ENPLANWORK.DATEENDPRICONNECTION";
    public static final String investWorksAmount_Attr = "investWorksAmount";
    public static final String investWorksAmount_Field = "INVESTWORKSAMOUNT";
    public static final String investWorksAmount_QFielld = "ENPLANWORK.INVESTWORKSAMOUNT";
    public static final String investWorksDescription_Attr = "investWorksDescription";
    public static final String investWorksDescription_Field = "INVESTWORKSDESCRIPTION";
    public static final String investWorksDescription_QFielld = "ENPLANWORK.INVESTWORKSDESCRIPTION";
    public static final String investDateStartWork_Attr = "investDateStartWork";
    public static final String investDateStartWork_Field = "INVESTDATESTARTWORK";
    public static final String investDateStartWork_QFielld = "ENPLANWORK.INVESTDATESTARTWORK";
    public static final String investWorkMeasCode_Attr = "investWorkMeasCode";
    public static final String investWorkMeasCode_Field = "INVESTWORKMEASCODE";
    public static final String investWorkMeasCode_QFielld = "ENPLANWORK.INVESTWORKMEASCODE";
    public static final String servicesFSideFinId_Attr = "servicesFSideFinId";
    public static final String servicesFSideFinId_Field = "SERVICESFSIDEFINID";
    public static final String servicesFSideFinId_QFielld = "ENPLANWORK.SERVICESFSIDEFINID";
    public static final String servicesFSideCnNum_Attr = "servicesFSideCnNum";
    public static final String servicesFSideCnNum_Field = "SERVICESFSIDECNNUM";
    public static final String servicesFSideCnNum_QFielld = "ENPLANWORK.SERVICESFSIDECNNUM";
    public static final String totalTimeHours_Attr = "totalTimeHours";
    public static final String totalTimeHours_Field = "TOTALTIMEHOURS";
    public static final String totalTimeHours_QFielld = "ENPLANWORK.TOTALTIMEHOURS";
    public static final String totalTimeDays_Attr = "totalTimeDays";
    public static final String totalTimeDays_Field = "TOTALTIMEDAYS";
    public static final String totalTimeDays_QFielld = "ENPLANWORK.TOTALTIMEDAYS";
    public static final String investItemNumber_Attr = "investItemNumber";
    public static final String investItemNumber_Field = "INVESTITEMNUMBER";
    public static final String investItemNumber_QFielld = "ENPLANWORK.INVESTITEMNUMBER";
    public static final String causeDisconnection_Attr = "causeDisconnection";
    public static final String causeDisconnection_Field = "CAUSEDISCONNECTION";
    public static final String causeDisconnection_QFielld = "ENPLANWORK.CAUSEDISCONNECTION";
    public static final String domain_info_Attr = "domain_info";
    public static final String domain_info_Field = "DOMAIN_INFO";
    public static final String domain_info_QFielld = "ENPLANWORK.DOMAIN_INFO";
    public static final String modify_time_Attr = "modify_time";
    public static final String modify_time_Field = "MODIFY_TIME";
    public static final String modify_time_QFielld = "ENPLANWORK.MODIFY_TIME";
    public static final String status_Attr = "status";
    public static final String status_Field = "STATUSCODE";
    public static final String  status_QFielld = "ENPLANWORK.STATUSCODE";
    public static final String elementRef_Attr = "elementRef";
    public static final String elementRef_Field = "ELEMENTREFCODE";
    public static final String  elementRef_QFielld = "ENPLANWORK.ELEMENTREFCODE";
    public static final String typeRef_Attr = "typeRef";
    public static final String typeRef_Field = "TYPEREFCODE";
    public static final String  typeRef_QFielld = "ENPLANWORK.TYPEREFCODE";
    public static final String kind_Attr = "kind";
    public static final String kind_Field = "KINDCODE";
    public static final String  kind_QFielld = "ENPLANWORK.KINDCODE";
    public static final String renRef_Attr = "renRef";
    public static final String renRef_Field = "RENREFCODE";
    public static final String  renRef_QFielld = "ENPLANWORK.RENREFCODE";
    public static final String finExecutor_Attr = "finExecutor";
    public static final String finExecutor_Field = "FINEXECUTORCODE";
    public static final String  finExecutor_QFielld = "ENPLANWORK.FINEXECUTORCODE";
    public static final String stateRef_Attr = "stateRef";
    public static final String stateRef_Field = "STATEREFCODE";
    public static final String  stateRef_QFielld = "ENPLANWORK.STATEREFCODE";
    public static final String formRef_Attr = "formRef";
    public static final String formRef_Field = "FORMREFCODE";
    public static final String  formRef_QFielld = "ENPLANWORK.FORMREFCODE";
    public static final String sourceRef_Attr = "sourceRef";
    public static final String sourceRef_Field = "SOURCEREFCODE";
    public static final String  sourceRef_QFielld = "ENPLANWORK.SOURCEREFCODE";
    public static final String ddsCodes_Attr = "ddsCodes";
    public static final String ddsCodes_Field = "DDSCODESCODE";
    public static final String  ddsCodes_QFielld = "ENPLANWORK.DDSCODESCODE";
    public static final String budgetRef_Attr = "budgetRef";
    public static final String budgetRef_Field = "BUDGETREFCODE";
    public static final String  budgetRef_QFielld = "ENPLANWORK.BUDGETREFCODE";
    public static final String responsibilityRef_Attr = "responsibilityRef";
    public static final String responsibilityRef_Field = "RESPONSIBILITYREFCODE";
    public static final String  responsibilityRef_QFielld = "ENPLANWORK.RESPONSIBILITYREFCODE";
    public static final String departmentRef_Attr = "departmentRef";
    public static final String departmentRef_Field = "DEPARTMENTREFCODE";
    public static final String  departmentRef_QFielld = "ENPLANWORK.DEPARTMENTREFCODE";
    public static final String invgroupRef_Attr = "invgroupRef";
    public static final String invgroupRef_Field = "INVGROUPREFCODE";
    public static final String  invgroupRef_QFielld = "ENPLANWORK.INVGROUPREFCODE";
    public static final String ipImplementTypeRef_Attr = "ipImplementTypeRef";
    public static final String ipImplementTypeRef_Field = "IPIMPLEMENTTYPEREFCODE";
    public static final String  ipImplementTypeRef_QFielld = "ENPLANWORK.IPIMPLEMENTTYPEREFCODE";


    public String  partnerCode;
    
    public String  finDocCode;
    
    public String getFinDocCode() {
		return finDocCode;
	}

	public void setFinDocCode(String finDocCode) {
		this.finDocCode = finDocCode;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	
    
    public void setCode(int aValue){
       code = aValue;
    }

    public int getCode(){
       return code;
    }


    public void setDateGen(Date aValue){
       dateGen = aValue;
    }

    public Date getDateGen(){
       return dateGen;
    }


    public void setDateStart(Date aValue){
       dateStart = aValue;
    }

    public Date getDateStart(){
       return dateStart;
    }


    public void setDateFinal(Date aValue){
       dateFinal = aValue;
    }

    public Date getDateFinal(){
       return dateFinal;
    }

    public void setYearGen(int aValue){
       yearGen = aValue;
    }

    public int getYearGen(){
       return yearGen;
    }

    public void setMonthGen(int aValue){
       monthGen = aValue;
    }

    public int getMonthGen(){
       return monthGen;
    }

    public void setYearOriginal(int aValue){
       yearOriginal = aValue;
    }

    public int getYearOriginal(){
       return yearOriginal;
    }

    public void setMonthOriginal(int aValue){
       monthOriginal = aValue;
    }

    public int getMonthOriginal(){
       return monthOriginal;
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

    public void setDistanceAlong(BigDecimal aValue){
       distanceAlong = aValue;
    }

    public BigDecimal getDistanceAlong(){
       return distanceAlong;
    }

    public void setDistanceTo(BigDecimal aValue){
       distanceTo = aValue;
    }

    public BigDecimal getDistanceTo(){
       return distanceTo;
    }

    public void setWorkOrderNumber(String aValue){
       workOrderNumber = aValue;
    }

    public String getWorkOrderNumber(){
       return workOrderNumber;
    }


    public void setDateWorkOrder(Date aValue){
       dateWorkOrder = aValue;
    }

    public Date getDateWorkOrder(){
       return dateWorkOrder;
    }

    public void setPriConnectionNumber(String aValue){
       priConnectionNumber = aValue;
    }

    public String getPriConnectionNumber(){
       return priConnectionNumber;
    }


    public void setDateEndPriConnection(Date aValue){
       dateEndPriConnection = aValue;
    }

    public Date getDateEndPriConnection(){
       return dateEndPriConnection;
    }

    public void setInvestWorksAmount(BigDecimal aValue){
       investWorksAmount = aValue;
    }

    public BigDecimal getInvestWorksAmount(){
       return investWorksAmount;
    }

    public void setInvestWorksDescription(String aValue){
       investWorksDescription = aValue;
    }

    public String getInvestWorksDescription(){
       return investWorksDescription;
    }

    public void setServicesFSideFinId(int aValue){
       servicesFSideFinId = aValue;
    }
    public Date getInvestDateStartWork(){
       return investDateStartWork;
    }

    public void setInvestDateStartWork(Date investDateStartWork){
       this.investDateStartWork = investDateStartWork;
    }

    public int getInvestWorkMeasCode(){
       return investWorkMeasCode;
    }
    
    public void setInvestWorkMeasCode(int investWorkMeasCode){
       this.investWorkMeasCode = investWorkMeasCode;
    }

    public int getServicesFSideFinId(){
       return servicesFSideFinId;
    }

    public void setServicesFSideCnNum(String aValue){
       servicesFSideCnNum = aValue;
    }

    public String getServicesFSideCnNum(){
       return servicesFSideCnNum;
    }

    public void setTotalTimeHours(BigDecimal aValue){
       totalTimeHours = aValue;
    }

    public BigDecimal getTotalTimeHours(){
       return totalTimeHours;
    }

    public void setTotalTimeDays(BigDecimal aValue){
       totalTimeDays = aValue;
    }

    public BigDecimal getTotalTimeDays(){
       return totalTimeDays;
    }

    public void setInvestItemNumber(String aValue){
       investItemNumber = aValue;
    }

    public String getInvestItemNumber(){
       return investItemNumber;
    }

    public void setCauseDisconnection(int aValue){
       causeDisconnection = aValue;
    }

    public int getCauseDisconnection(){
       return causeDisconnection;
    }

    public void setDomain_info(String aValue){
       domain_info = aValue;
    }

    public String getDomain_info(){
       return domain_info;
    }

    public void setModify_time(long aValue){
       modify_time = aValue;
    }

    public long getModify_time(){
       return modify_time;
    }

    public void setStatus(ENPlanWorkStatus aValue){
       status = aValue;
    }

    public ENPlanWorkStatus getStatus(){
       return status;
    }
    public void setElementRef(ENElementRef aValue){
       elementRef = aValue;
    }

    public ENElementRef getElementRef(){
       return elementRef;
    }
    public void setTypeRef(ENPlanWorkTypeRef aValue){
       typeRef = aValue;
    }

    public ENPlanWorkTypeRef getTypeRef(){
       return typeRef;
    }
    public void setKind(ENPlanWorkKind aValue){
       kind = aValue;
    }

    public ENPlanWorkKind getKind(){
       return kind;
    }
    public void setRenRef(EPRenRef aValue){
       renRef = aValue;
    }

    public EPRenRef getRenRef(){
       return renRef;
    }
    public void setFinExecutor(FINExecutor aValue){
       finExecutor = aValue;
    }

    public FINExecutor getFinExecutor(){
       return finExecutor;
    }
    public void setStateRef(ENPlanWorkStateRef aValue){
       stateRef = aValue;
    }

    public ENPlanWorkStateRef getStateRef(){
       return stateRef;
    }
    public void setFormRef(ENPlanWorkFormRef aValue){
       formRef = aValue;
    }

    public ENPlanWorkFormRef getFormRef(){
       return formRef;
    }
    public void setSourceRef(ENPlanWorkSourceRef aValue){
       sourceRef = aValue;
    }

    public ENPlanWorkSourceRef getSourceRef(){
       return sourceRef;
    }
    public void setDdsCodes(RQDDSCodes aValue){
       ddsCodes = aValue;
    }

    public RQDDSCodes getDdsCodes(){
       return ddsCodes;
    }
    public void setBudgetRef(ENDepartmentRef aValue){
       budgetRef = aValue;
    }

    public ENDepartmentRef getBudgetRef(){
       return budgetRef;
    }
    public void setResponsibilityRef(ENDepartmentRef aValue){
       responsibilityRef = aValue;
    }

    public ENDepartmentRef getResponsibilityRef(){
       return responsibilityRef;
    }
    public void setDepartmentRef(ENDepartmentRef aValue){
       departmentRef = aValue;
    }

    public ENDepartmentRef getDepartmentRef(){
       return departmentRef;
    }
    public void setInvgroupRef(ENInvestProgramGroupsRef aValue){
       invgroupRef = aValue;
    }

    public ENInvestProgramGroupsRef getInvgroupRef(){
       return invgroupRef;
    }
    public void setIpImplementTypeRef(ENIPImplementationTypeRef aValue){
       ipImplementTypeRef = aValue;
    }

    public ENIPImplementationTypeRef getIpImplementTypeRef(){
       return ipImplementTypeRef;
    }

} // end of ENPlanWorkValueObject


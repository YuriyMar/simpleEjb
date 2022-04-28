
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject.brief;



  import java.io.Serializable;

/**
  * Short Object for ENPlanWork;
  */

import java.math.BigDecimal;
import java.util.Date;


public class ENPlanWorkShort implements Serializable {

    public int code = Integer.MIN_VALUE;
    public Date dateGen ;
    public Date dateStart ;
    public Date dateFinal ;
    public int yearGen = Integer.MIN_VALUE;
    public int monthGen = Integer.MIN_VALUE;
    public int yearOriginal = Integer.MIN_VALUE;
    public int monthOriginal = Integer.MIN_VALUE;
    public String userGen;
    public Date dateEdit ;
    public String workOrderNumber;
    public Date dateWorkOrder ;
    public String priConnectionNumber;
    public Date dateEndPriConnection ;
    public String investWorksDescription;
	public Date investDateStartWork ;
	public int investWorkMeasCode = Integer.MIN_VALUE;
    public int servicesFSideFinId = Integer.MIN_VALUE;
    public String servicesFSideCnNum;
    public BigDecimal totalTimeHours;
    public BigDecimal totalTimeDays;
    public String investItemNumber;
    public int statusCode = Integer.MIN_VALUE;
    public String statusName;
    public int elementRefCode = Integer.MIN_VALUE;
    public int typeRefCode = Integer.MIN_VALUE;
    public String typeRefName;
    public String typeRefShortName;
    public int kindCode = Integer.MIN_VALUE;
    public String kindName;
    public int renRefCode = Integer.MIN_VALUE;
    public String renRefName;
    public int finExecutorCode = Integer.MIN_VALUE;
    public String finExecutorName;
    public int finExecutorFinCode = Integer.MIN_VALUE;
    public String finExecutorFinTypeName;
    public int finExecutorFinTypeCode = Integer.MIN_VALUE;
    public String finExecutorFinKindName;
    public int finExecutorFinKindCode = Integer.MIN_VALUE;
    public String finExecutorFinCehName;
    public int finExecutorFinCehCode = Integer.MIN_VALUE;
    public int stateRefCode = Integer.MIN_VALUE;
    public String stateRefName;
    public String stateRefShortName;
    public int formRefCode = Integer.MIN_VALUE;
    public String formRefName;
    public int sourceRefCode = Integer.MIN_VALUE;
    public String sourceRefName;
    public int ddsCodesCode = Integer.MIN_VALUE;
    public int budgetRefCode = Integer.MIN_VALUE;
    public String budgetRefShortName;
    public Date budgetRefDateStart;
    public Date budgetRefDateFinal;
    public int budgetRefRenCode = Integer.MIN_VALUE;
    public String budgetRefShpzBalans;
    public int budgetRefKau_table_id_1884 = Integer.MIN_VALUE;
    public String budgetRefKau_1884;
    public String budgetRefName_1884;
    public int responsibilityRefCode = Integer.MIN_VALUE;
    public String responsibilityRefShortName;
    public Date responsibilityRefDateStart;
    public Date responsibilityRefDateFinal;
    public int responsibilityRefRenCode = Integer.MIN_VALUE;
    public String responsibilityRefShpzBalans;
    public int responsibilityRefKau_table_id_1884 = Integer.MIN_VALUE;
    public String responsibilityRefKau_1884;
    public String responsibilityRefName_1884;
    public int departmentRefCode = Integer.MIN_VALUE;
    public String departmentRefShortName;
    public Date departmentRefDateStart;
    public Date departmentRefDateFinal;
    public int departmentRefRenCode = Integer.MIN_VALUE;
    public String departmentRefShpzBalans;
    public int departmentRefKau_table_id_1884 = Integer.MIN_VALUE;
    public String departmentRefKau_1884;
    public String departmentRefName_1884;
    public int invgroupRefCode = Integer.MIN_VALUE;
    public String invgroupRefName;
    public String invgroupRefCommentgen;


    /** ********************************************** */
    public int workOrderCode = Integer.MIN_VALUE;

    public String objectName;
    public String invNumber;

    public String actInfo;

    public int ipImplementTypeRefCode = Integer.MIN_VALUE;
    public String ipImplementTypeRefName;

    public int actRefCode = Integer.MIN_VALUE;
    public String actRefNumber;

    public BigDecimal planedSum;
    public BigDecimal humanHours;
    
    public String percentManningWithMaterials;
    
    public String percentManningWithTransport;
    /** ********************************************** */
    
    public String info_oz2;
    public String info_oz1;
    public String info_dfdocdecree;



    public String getInfo_dfdocdecree() {
		return info_dfdocdecree;
	}

	public void setInfo_dfdocdecree(String info_dfdocdecree) {
		this.info_dfdocdecree = info_dfdocdecree;
	}

	public String getInfo_oz1() {
		return info_oz1;
	}

	public void setInfo_oz1(String info_oz1) {
		this.info_oz1 = info_oz1;
	}

	public String getInfo_oz2() {
		return info_oz2;
	}

	public void setInfo_oz2(String info_oz2) {
		this.info_oz2 = info_oz2;
	}

	public BigDecimal getHumanHours() {
		return humanHours;
	}

	public void setHumanHours(BigDecimal humanHours) {
		this.humanHours = humanHours;
	}

	public int causeDisconnection = Integer.MIN_VALUE;

    public int getCauseDisconnection() {
		return causeDisconnection;
	}

	public void setCauseDisconnection(int causeDisconnection) {
		this.causeDisconnection = causeDisconnection;
	}

	public int getActRefCode() {
		return actRefCode;
	}

	public void setActRefCode(int actRefCode) {
		this.actRefCode = actRefCode;
	}

	public String getActRefNumber() {
		return actRefNumber;
	}

	public void setActRefNumber(String actRefNumber) {
		this.actRefNumber = actRefNumber;
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
    public void setInvestWorksDescription(String aValue){
       investWorksDescription = aValue;
    }

    public String getInvestWorksDescription(){
       return investWorksDescription;
    }

	public Date getInvestDateStartWork() {
		return investDateStartWork;
	}

	public void setInvestDateStartWork(Date investDateStartWork) {
		this.investDateStartWork = investDateStartWork;
	}

	public int getInvestWorkMeasCode() {
		return investWorkMeasCode;
	}

	public void setInvestWorkMeasCode(int investWorkMeasCode) {
		this.investWorkMeasCode = investWorkMeasCode;
	}

    public void setServicesFSideFinId(int aValue){
       servicesFSideFinId = aValue;
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


    public void setStatusCode(int aValue){
       statusCode = aValue;
    }
    public int getStatusCode(){
       return statusCode;
    }

    public void setStatusName(String aValue){
       statusName = aValue;
    }
    public String getStatusName(){
       return statusName;
    }

    public void setElementRefCode(int aValue){
       elementRefCode = aValue;
    }
    public int getElementRefCode(){
       return elementRefCode;
    }

    public void setTypeRefCode(int aValue){
       typeRefCode = aValue;
    }
    public int getTypeRefCode(){
       return typeRefCode;
    }

    public void setTypeRefName(String aValue){
       typeRefName = aValue;
    }
    public String getTypeRefName(){
       return typeRefName;
    }

    public void setTypeRefShortName(String aValue){
       typeRefShortName = aValue;
    }
    public String getTypeRefShortName(){
       return typeRefShortName;
    }

    public void setKindCode(int aValue){
       kindCode = aValue;
    }
    public int getKindCode(){
       return kindCode;
    }

    public void setKindName(String aValue){
       kindName = aValue;
    }
    public String getKindName(){
       return kindName;
    }

    public void setRenRefCode(int aValue){
       renRefCode = aValue;
    }
    public int getRenRefCode(){
       return renRefCode;
    }

    public void setRenRefName(String aValue){
       renRefName = aValue;
    }
    public String getRenRefName(){
       return renRefName;
    }

    public void setFinExecutorCode(int aValue){
       finExecutorCode = aValue;
    }
    public int getFinExecutorCode(){
       return finExecutorCode;
    }

    public void setFinExecutorName(String aValue){
       finExecutorName = aValue;
    }
    public String getFinExecutorName(){
       return finExecutorName;
    }

    public void setFinExecutorFinCode(int aValue){
       finExecutorFinCode = aValue;
    }
    public int getFinExecutorFinCode(){
       return finExecutorFinCode;
    }

    public void setFinExecutorFinTypeName(String aValue){
       finExecutorFinTypeName = aValue;
    }
    public String getFinExecutorFinTypeName(){
       return finExecutorFinTypeName;
    }

    public void setFinExecutorFinTypeCode(int aValue){
       finExecutorFinTypeCode = aValue;
    }
    public int getFinExecutorFinTypeCode(){
       return finExecutorFinTypeCode;
    }

    public void setFinExecutorFinKindName(String aValue){
       finExecutorFinKindName = aValue;
    }
    public String getFinExecutorFinKindName(){
       return finExecutorFinKindName;
    }

    public void setFinExecutorFinKindCode(int aValue){
       finExecutorFinKindCode = aValue;
    }
    public int getFinExecutorFinKindCode(){
       return finExecutorFinKindCode;
    }

    public void setFinExecutorFinCehName(String aValue){
       finExecutorFinCehName = aValue;
    }
    public String getFinExecutorFinCehName(){
       return finExecutorFinCehName;
    }

    public void setFinExecutorFinCehCode(int aValue){
       finExecutorFinCehCode = aValue;
    }
    public int getFinExecutorFinCehCode(){
       return finExecutorFinCehCode;
    }

    public void setStateRefCode(int aValue){
       stateRefCode = aValue;
    }
    public int getStateRefCode(){
       return stateRefCode;
    }

    public void setStateRefName(String aValue){
       stateRefName = aValue;
    }
    public String getStateRefName(){
       return stateRefName;
    }

    public void setStateRefShortName(String aValue){
       stateRefShortName = aValue;
    }
    public String getStateRefShortName(){
       return stateRefShortName;
    }

    public void setFormRefCode(int aValue){
       formRefCode = aValue;
    }
    public int getFormRefCode(){
       return formRefCode;
    }

    public void setFormRefName(String aValue){
       formRefName = aValue;
    }
    public String getFormRefName(){
       return formRefName;
    }

    public void setSourceRefCode(int aValue){
       sourceRefCode = aValue;
    }
    public int getSourceRefCode(){
       return sourceRefCode;
    }

    public void setSourceRefName(String aValue){
       sourceRefName = aValue;
    }
    public String getSourceRefName(){
       return sourceRefName;
    }

    public void setDdsCodesCode(int aValue){
       ddsCodesCode = aValue;
    }
    public int getDdsCodesCode(){
       return ddsCodesCode;
    }

    public void setBudgetRefCode(int aValue){
       budgetRefCode = aValue;
    }
    public int getBudgetRefCode(){
       return budgetRefCode;
    }

    public void setBudgetRefShortName(String aValue){
       budgetRefShortName = aValue;
    }
    public String getBudgetRefShortName(){
       return budgetRefShortName;
    }


    public void setBudgetRefDateStart(Date aValue){
       budgetRefDateStart = aValue;
    }
    public Date getBudgetRefDateStart(){
       return budgetRefDateStart;
    }


    public void setBudgetRefDateFinal(Date aValue){
       budgetRefDateFinal = aValue;
    }
    public Date getBudgetRefDateFinal(){
       return budgetRefDateFinal;
    }

    public void setBudgetRefRenCode(int aValue){
       budgetRefRenCode = aValue;
    }
    public int getBudgetRefRenCode(){
       return budgetRefRenCode;
    }

    public void setBudgetRefShpzBalans(String aValue){
       budgetRefShpzBalans = aValue;
    }
    public String getBudgetRefShpzBalans(){
       return budgetRefShpzBalans;
    }

    public void setBudgetRefKau_table_id_1884(int aValue){
       budgetRefKau_table_id_1884 = aValue;
    }
    public int getBudgetRefKau_table_id_1884(){
       return budgetRefKau_table_id_1884;
    }

    public void setBudgetRefKau_1884(String aValue){
       budgetRefKau_1884 = aValue;
    }
    public String getBudgetRefKau_1884(){
       return budgetRefKau_1884;
    }

    public void setBudgetRefName_1884(String aValue){
       budgetRefName_1884 = aValue;
    }
    public String getBudgetRefName_1884(){
       return budgetRefName_1884;
    }

    public void setResponsibilityRefCode(int aValue){
       responsibilityRefCode = aValue;
    }
    public int getResponsibilityRefCode(){
       return responsibilityRefCode;
    }

    public void setResponsibilityRefShortName(String aValue){
       responsibilityRefShortName = aValue;
    }
    public String getResponsibilityRefShortName(){
       return responsibilityRefShortName;
    }


    public void setResponsibilityRefDateStart(Date aValue){
       responsibilityRefDateStart = aValue;
    }
    public Date getResponsibilityRefDateStart(){
       return responsibilityRefDateStart;
    }


    public void setResponsibilityRefDateFinal(Date aValue){
       responsibilityRefDateFinal = aValue;
    }
    public Date getResponsibilityRefDateFinal(){
       return responsibilityRefDateFinal;
    }

    public void setResponsibilityRefRenCode(int aValue){
       responsibilityRefRenCode = aValue;
    }
    public int getResponsibilityRefRenCode(){
       return responsibilityRefRenCode;
    }

    public void setResponsibilityRefShpzBalans(String aValue){
       responsibilityRefShpzBalans = aValue;
    }
    public String getResponsibilityRefShpzBalans(){
       return responsibilityRefShpzBalans;
    }

    public void setResponsibilityRefKau_table_id_1884(int aValue){
       responsibilityRefKau_table_id_1884 = aValue;
    }
    public int getResponsibilityRefKau_table_id_1884(){
       return responsibilityRefKau_table_id_1884;
    }

    public void setResponsibilityRefKau_1884(String aValue){
       responsibilityRefKau_1884 = aValue;
    }
    public String getResponsibilityRefKau_1884(){
       return responsibilityRefKau_1884;
    }

    public void setResponsibilityRefName_1884(String aValue){
       responsibilityRefName_1884 = aValue;
    }
    public String getResponsibilityRefName_1884(){
       return responsibilityRefName_1884;
    }

    public void setDepartmentRefCode(int aValue){
       departmentRefCode = aValue;
    }
    public int getDepartmentRefCode(){
       return departmentRefCode;
    }

    public void setDepartmentRefShortName(String aValue){
       departmentRefShortName = aValue;
    }
    public String getDepartmentRefShortName(){
       return departmentRefShortName;
    }


    public void setDepartmentRefDateStart(Date aValue){
       departmentRefDateStart = aValue;
    }
    public Date getDepartmentRefDateStart(){
       return departmentRefDateStart;
    }


    public void setDepartmentRefDateFinal(Date aValue){
       departmentRefDateFinal = aValue;
    }
    public Date getDepartmentRefDateFinal(){
       return departmentRefDateFinal;
    }

    public void setDepartmentRefRenCode(int aValue){
       departmentRefRenCode = aValue;
    }
    public int getDepartmentRefRenCode(){
       return departmentRefRenCode;
    }

    public void setDepartmentRefShpzBalans(String aValue){
       departmentRefShpzBalans = aValue;
    }
    public String getDepartmentRefShpzBalans(){
       return departmentRefShpzBalans;
    }

    public void setDepartmentRefKau_table_id_1884(int aValue){
       departmentRefKau_table_id_1884 = aValue;
    }
    public int getDepartmentRefKau_table_id_1884(){
       return departmentRefKau_table_id_1884;
    }

    public void setDepartmentRefKau_1884(String aValue){
       departmentRefKau_1884 = aValue;
    }
    public String getDepartmentRefKau_1884(){
       return departmentRefKau_1884;
    }

    public void setDepartmentRefName_1884(String aValue){
       departmentRefName_1884 = aValue;
    }
    public String getDepartmentRefName_1884(){
       return departmentRefName_1884;
    }

    public void setInvgroupRefCode(int aValue){
       invgroupRefCode = aValue;
    }
    public int getInvgroupRefCode(){
       return invgroupRefCode;
    }

    public void setInvgroupRefName(String aValue){
       invgroupRefName = aValue;
    }
    public String getInvgroupRefName(){
       return invgroupRefName;
    }

    public void setInvgroupRefCommentgen(String aValue){
       invgroupRefCommentgen = aValue;
    }
    public String getInvgroupRefCommentgen(){
       return invgroupRefCommentgen;
    }

	public int getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(int workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getInvNumber() {
		return invNumber;
	}

	public void setInvNumber(String invNumber) {
		this.invNumber = invNumber;
	}

	public String getActInfo() {
		return actInfo;
	}

	public void setActInfo(String actInfo) {
		this.actInfo = actInfo;
	}

    public void setIpImplementTypeRefCode(int aValue){
       ipImplementTypeRefCode = aValue;
    }
    public int getIpImplementTypeRefCode(){
       return ipImplementTypeRefCode;
    }

    public void setIpImplementTypeRefName(String aValue){
       ipImplementTypeRefName = aValue;
    }
    public String getIpImplementTypeRefName(){
       return ipImplementTypeRefName;
    }

	public BigDecimal getPlanedSum() {
		return planedSum;
	}

	public void setPlanedSum(BigDecimal planedSum) {
		this.planedSum = planedSum;
	}

	public String getPercentManningWithMaterials() {
		return percentManningWithMaterials;
	}

	public void setPercentManningWithMaterials(String percentManningWithMaterials) {
		this.percentManningWithMaterials = percentManningWithMaterials;
	}

	public String getPercentManningWithTransport() {
		return percentManningWithTransport;
	}

	public void setPercentManningWithTransport(String percentManningWithTransport) {
		this.percentManningWithTransport = percentManningWithTransport;
	}



}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject.brief;



  /**
  * Short Object for ENPlanXqtnHistory;  	
  */

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;


public class ENPlanXqtnHistoryShort implements Serializable {

	private static final long serialVersionUID = 1L;

	public int code = Integer.MIN_VALUE;
	public Date dateGen ;
	public int executionPercent = Integer.MIN_VALUE;
	public String userGen;
	public Date dateEdit ;
	public int planRefCode = Integer.MIN_VALUE;
	public Date planRefDateGen;
	public Date planRefDateStart;
	public Date planRefDateFinal;
	public int planRefYearGen = Integer.MIN_VALUE;
	public int planRefMonthGen = Integer.MIN_VALUE;
	public int planRefYearOriginal = Integer.MIN_VALUE;
	public int planRefMonthOriginal = Integer.MIN_VALUE;
	public String planRefUserGen;
	public Date planRefDateEdit;
	public String planRefWorkOrderNumber;
	public Date planRefDateWorkOrder;
	public String planRefPriConnectionNumber;
	public Date planRefDateEndPriConnection;
	public String planRefInvestWorksDescription;
	public Date planRefInvestDateStartWork;
	public int planRefServicesFSideFinId = Integer.MIN_VALUE;
	public String planRefServicesFSideCnNum;
	public BigDecimal planRefTotalTimeHours;
	public BigDecimal planRefTotalTimeDays;
	public String planRefInvestItemNumber;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Date getDateGen() {
		return dateGen;
	}

	public void setDateGen(Date dateGen) {
		this.dateGen = dateGen;
	}

	public int getExecutionPercent() {
		return executionPercent;
	}

	public void setExecutionPercent(int executionPercent) {
		this.executionPercent = executionPercent;
	}

	public String getUserGen() {
		return userGen;
	}

	public void setUserGen(String userGen) {
		this.userGen = userGen;
	}

	public Date getDateEdit() {
		return dateEdit;
	}

	public void setDateEdit(Date dateEdit) {
		this.dateEdit = dateEdit;
	}



	public int getPlanRefCode(){
		return planRefCode;
	}

	public void setPlanRefCode(int planRefCode) {
		this.planRefCode = planRefCode;
	}

	public Date getPlanRefDateGen(){
		return planRefDateGen;
	}

	public void setPlanRefDateGen(Date planRefDateGen) {
		this.planRefDateGen = planRefDateGen;
	}

	public Date getPlanRefDateStart(){
		return planRefDateStart;
	}

	public void setPlanRefDateStart(Date planRefDateStart) {
		this.planRefDateStart = planRefDateStart;
	}

	public Date getPlanRefDateFinal(){
		return planRefDateFinal;
	}

	public void setPlanRefDateFinal(Date planRefDateFinal) {
		this.planRefDateFinal = planRefDateFinal;
	}

	public int getPlanRefYearGen(){
		return planRefYearGen;
	}

	public void setPlanRefYearGen(int planRefYearGen) {
		this.planRefYearGen = planRefYearGen;
	}

	public int getPlanRefMonthGen(){
		return planRefMonthGen;
	}

	public void setPlanRefMonthGen(int planRefMonthGen) {
		this.planRefMonthGen = planRefMonthGen;
	}

	public int getPlanRefYearOriginal(){
		return planRefYearOriginal;
	}

	public void setPlanRefYearOriginal(int planRefYearOriginal) {
		this.planRefYearOriginal = planRefYearOriginal;
	}

	public int getPlanRefMonthOriginal(){
		return planRefMonthOriginal;
	}

	public void setPlanRefMonthOriginal(int planRefMonthOriginal) {
		this.planRefMonthOriginal = planRefMonthOriginal;
	}

	public String getPlanRefUserGen(){
		return planRefUserGen;
	}

	public void setPlanRefUserGen(String planRefUserGen) {
		this.planRefUserGen = planRefUserGen;
	}

	public Date getPlanRefDateEdit(){
		return planRefDateEdit;
	}

	public void setPlanRefDateEdit(Date planRefDateEdit) {
		this.planRefDateEdit = planRefDateEdit;
	}

	public String getPlanRefWorkOrderNumber(){
		return planRefWorkOrderNumber;
	}

	public void setPlanRefWorkOrderNumber(String planRefWorkOrderNumber) {
		this.planRefWorkOrderNumber = planRefWorkOrderNumber;
	}

	public Date getPlanRefDateWorkOrder(){
		return planRefDateWorkOrder;
	}

	public void setPlanRefDateWorkOrder(Date planRefDateWorkOrder) {
		this.planRefDateWorkOrder = planRefDateWorkOrder;
	}

	public String getPlanRefPriConnectionNumber(){
		return planRefPriConnectionNumber;
	}

	public void setPlanRefPriConnectionNumber(String planRefPriConnectionNumber) {
		this.planRefPriConnectionNumber = planRefPriConnectionNumber;
	}

	public Date getPlanRefDateEndPriConnection(){
		return planRefDateEndPriConnection;
	}

	public void setPlanRefDateEndPriConnection(Date planRefDateEndPriConnection) {
		this.planRefDateEndPriConnection = planRefDateEndPriConnection;
	}

	public String getPlanRefInvestWorksDescription(){
		return planRefInvestWorksDescription;
	}

	public void setPlanRefInvestWorksDescription(String planRefInvestWorksDescription) {
		this.planRefInvestWorksDescription = planRefInvestWorksDescription;
	}

	public Date getPlanRefInvestDateStartWork(){
		return planRefInvestDateStartWork;
	}

	public void setPlanRefInvestDateStartWork(Date planRefInvestDateStartWork) {
		this.planRefInvestDateStartWork = planRefInvestDateStartWork;
	}

	public int getPlanRefServicesFSideFinId(){
		return planRefServicesFSideFinId;
	}

	public void setPlanRefServicesFSideFinId(int planRefServicesFSideFinId) {
		this.planRefServicesFSideFinId = planRefServicesFSideFinId;
	}

	public String getPlanRefServicesFSideCnNum(){
		return planRefServicesFSideCnNum;
	}

	public void setPlanRefServicesFSideCnNum(String planRefServicesFSideCnNum) {
		this.planRefServicesFSideCnNum = planRefServicesFSideCnNum;
	}

	public BigDecimal getPlanRefTotalTimeHours(){
		return planRefTotalTimeHours;
	}

	public void setPlanRefTotalTimeHours(BigDecimal planRefTotalTimeHours) {
		this.planRefTotalTimeHours = planRefTotalTimeHours;
	}

	public BigDecimal getPlanRefTotalTimeDays(){
		return planRefTotalTimeDays;
	}

	public void setPlanRefTotalTimeDays(BigDecimal planRefTotalTimeDays) {
		this.planRefTotalTimeDays = planRefTotalTimeDays;
	}

	public String getPlanRefInvestItemNumber(){
		return planRefInvestItemNumber;
	}

	public void setPlanRefInvestItemNumber(String planRefInvestItemNumber) {
		this.planRefInvestItemNumber = planRefInvestItemNumber;
	}



}

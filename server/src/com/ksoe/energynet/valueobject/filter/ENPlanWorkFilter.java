
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWork;

  /**
  * Filter for ENPlanWork;  
  * 	
  */

public class ENPlanWorkFilter extends  ENPlanWork {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkFilter()
   {
    code = Integer.MIN_VALUE; 
    dateGen = null; 
    dateStart = null; 
    dateFinal = null; 
    yearGen = Integer.MIN_VALUE; 
    monthGen = Integer.MIN_VALUE; 
    yearOriginal = Integer.MIN_VALUE; 
    monthOriginal = Integer.MIN_VALUE; 
    commentGen = null; 
    userGen = null; 
    dateEdit = null; 
    distanceAlong = null; 
    distanceTo = null; 
    workOrderNumber = null; 
    dateWorkOrder = null; 
    priConnectionNumber = null; 
    dateEndPriConnection = null; 
    investWorksAmount = null; 
    investWorksDescription = null; 
    investDateStartWork = null; 
    investWorkMeasCode = Integer.MIN_VALUE; 
    servicesFSideFinId = Integer.MIN_VALUE; 
    servicesFSideCnNum = null; 
    totalTimeHours = null; 
    totalTimeDays = null; 
    investItemNumber = null; 
    causeDisconnection = Integer.MIN_VALUE; 
    domain_info = null; 
    modify_time = Long.MIN_VALUE;
    status.code = Integer.MIN_VALUE;
    elementRef.code = Integer.MIN_VALUE;
    typeRef.code = Integer.MIN_VALUE;
    kind.code = Integer.MIN_VALUE;
    renRef.code = Integer.MIN_VALUE;
    finExecutor.code = Integer.MIN_VALUE;
    stateRef.code = Integer.MIN_VALUE;
    formRef.code = Integer.MIN_VALUE;
    sourceRef.code = Integer.MIN_VALUE;
    ddsCodes.code = Integer.MIN_VALUE;
    budgetRef.code = Integer.MIN_VALUE;
    responsibilityRef.code = Integer.MIN_VALUE;
    departmentRef.code = Integer.MIN_VALUE;
    invgroupRef.code = Integer.MIN_VALUE;
    ipImplementTypeRef.code = Integer.MIN_VALUE;
   }

} // end of Filter for ENPlanWork


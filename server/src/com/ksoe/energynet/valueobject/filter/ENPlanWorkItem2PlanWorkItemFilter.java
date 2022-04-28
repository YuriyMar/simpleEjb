
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem;

  /**
  * Filter for ENPlanWorkItem2PlanWorkItem;  
  * 	
  */

public class ENPlanWorkItem2PlanWorkItemFilter extends  ENPlanWorkItem2PlanWorkItem {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkItem2PlanWorkItemFilter()
   {
    code = Integer.MIN_VALUE; 
    countGen = null; 
    modify_time = Long.MIN_VALUE;
    typeRef.code = Integer.MIN_VALUE;;
    planItemInRef.code = Integer.MIN_VALUE;;
    planItemOutRef.code = Integer.MIN_VALUE;;
   }

} // end of Filter for ENPlanWorkItem2PlanWorkItem


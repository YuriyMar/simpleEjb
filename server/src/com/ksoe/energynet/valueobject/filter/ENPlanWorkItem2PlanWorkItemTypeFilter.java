
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType;

  /**
  * Filter for ENPlanWorkItem2PlanWorkItemType;  
  * 	
  */

public class ENPlanWorkItem2PlanWorkItemTypeFilter extends  ENPlanWorkItem2PlanWorkItemType {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkItem2PlanWorkItemTypeFilter()
   {
    code = Integer.MIN_VALUE; 
    name = null; 
   }

} // end of Filter for ENPlanWorkItem2PlanWorkItemType


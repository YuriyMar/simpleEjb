
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph;

  /**
  * Filter for ENPlanWorkItem2Graph;  
  * 	
  */

public class ENPlanWorkItem2GraphFilter extends  ENPlanWorkItem2Graph {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkItem2GraphFilter()
   {
    code = Integer.MIN_VALUE; 
    dayWork = null; 
    modify_time = Long.MIN_VALUE;
    userGen = null; 
    dateEdit = null; 
    countgen = null; 
    planWorkItemRef.code = Integer.MIN_VALUE;
    planWorkRef.code = Integer.MIN_VALUE;
   }

} // end of Filter for ENPlanWorkItem2Graph


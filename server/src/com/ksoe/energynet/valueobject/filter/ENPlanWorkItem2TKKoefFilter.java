
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef;

  /**
  * Filter for ENPlanWorkItem2TKKoef;  
  * 	
  */

public class ENPlanWorkItem2TKKoefFilter extends  ENPlanWorkItem2TKKoef {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkItem2TKKoefFilter()
   {
    code = Integer.MIN_VALUE; 
    commentGen = null; 
    domain_info = null; 
    modify_time = Long.MIN_VALUE;
    sourceKoef.code = Integer.MIN_VALUE;;
    planWorkItemRef.code = Integer.MIN_VALUE;;
   }

} // end of Filter for ENPlanWorkItem2TKKoef


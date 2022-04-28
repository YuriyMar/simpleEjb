
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory;

  /**
  * Filter for ENPlanWorkFuelHistory;  
  * 	
  */

public class ENPlanWorkFuelHistoryFilter extends  ENPlanWorkFuelHistory {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkFuelHistoryFilter()
   {
    code = Integer.MIN_VALUE; 
    dateGen = null; 
    countGen = null; 
    version = Integer.MIN_VALUE; 
    modify_time = Long.MIN_VALUE;
    userAdd = null; 
    dateAdd = null; 
    userGen = null; 
    dateEdit = null; 
    planRef.code = Integer.MIN_VALUE;
    fuelTypeRef.code = Integer.MIN_VALUE;
   }

} // end of Filter for ENPlanWorkFuelHistory


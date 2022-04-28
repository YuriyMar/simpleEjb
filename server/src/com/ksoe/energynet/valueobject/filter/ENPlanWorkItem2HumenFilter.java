
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject.filter;

import com.ksoe.energynet.valueobject.ENPlanWorkItem2Humen;

  /**
  * Filter for ENPlanWorkItem2Humen;  
  * 	
  */

public class ENPlanWorkItem2HumenFilter extends  ENPlanWorkItem2Humen {

  public String conditionSQL;
  public String orderBySQL;

  public final String getConditionSQL() {return conditionSQL;}
  public final void setConditionSQL(String aValue) {conditionSQL = aValue;}

  public final String getOrderBySQL() {return orderBySQL;}
  public final void setOrderBySQL(String aValue) {orderBySQL = aValue;}

  public ENPlanWorkItem2HumenFilter()
   {
    code = Integer.MIN_VALUE; 
    orederNum = Integer.MIN_VALUE; 
    tabNumber = null; 
    fio = null; 
    salary = null; 
    timeMonth = null; 
    daysMonth = null; 
    salaryHours = null; 
    timeWork = null; 
    timeObjectWork = null; 
    timeWorkFact = null; 
    timeDelivery = null; 
    paysWork = null; 
    bonus = null; 
    salaryHoursBonus = null; 
    paysWorkBonus = null; 
    chargePercent = null; 
    chargeSum = null; 
    balans = null; 
    podrcod = null; 
    costDelivery = null; 
    generalExpenses = null; 
    administrativeExpenses = null; 
    cehId = null; 
    percentSurcharge = null; 
    salaryHoursSurcharge = null; 
    paysWorkSurcharge = null; 
    percentPremium = null; 
    salaryHoursPremium = null; 
    paysWorkPremium = null; 
    percentAdditional = null; 
    salaryHoursAdditional = null; 
    paysWorkAdditional = null; 
    paysWorkWithAllSurcharge = null; 
    paysWorkWithAllSurchargeWithoutDeliv = null; 
    paysWorkBonusWithoutDeliv = null; 
    paysWorkSurchargeWithoutDeliv = null; 
    paysWorkPremiumWithoutDeliv = null; 
    paysWorkAdditionalWithoutDeliv = null; 
    chargeSumWithoutDeliv = null; 
    modify_time = Long.MIN_VALUE;
    humenKindRef.code = Integer.MIN_VALUE;
    plaItemRef.code = Integer.MIN_VALUE;
    classificationTypeRef.code = Integer.MIN_VALUE;
    chargeRef.code = Integer.MIN_VALUE;
    positionRef.code = Integer.MIN_VALUE;
    transport.code = Integer.MIN_VALUE;
   }

} // end of Filter for ENPlanWorkItem2Humen


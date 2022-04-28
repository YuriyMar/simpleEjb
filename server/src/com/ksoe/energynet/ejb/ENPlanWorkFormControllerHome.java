
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------

package com.ksoe.energynet.ejb;


  /**
  * EJB Home interface for ENPlanWorkForm;  
  * 	
  */
  

public interface ENPlanWorkFormControllerHome extends javax.ejb.EJBHome
{
  public com.ksoe.energynet.ejb.ENPlanWorkFormController create() 
    throws java.rmi.RemoteException, javax.ejb.CreateException;
}


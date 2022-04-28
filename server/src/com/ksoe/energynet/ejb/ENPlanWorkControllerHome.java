
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Sat Sep 26 14:38:30 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------

package com.ksoe.energynet.ejb;


  /**
  * EJB Home interface for ENPlanWork;  
  * 	
  */
  

public interface ENPlanWorkControllerHome extends javax.ejb.EJBHome
{
  public com.ksoe.energynet.ejb.ENPlanWorkController create() 
    throws java.rmi.RemoteException, javax.ejb.CreateException;
}


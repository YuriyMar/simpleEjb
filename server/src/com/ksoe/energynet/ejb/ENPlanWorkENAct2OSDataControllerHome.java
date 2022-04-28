
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------


package com.ksoe.energynet.ejb;

/**
 * EJB Home interface for ENPlanWorkENAct2OSData;
 *
 */

public interface ENPlanWorkENAct2OSDataControllerHome extends javax.ejb.EJBHome {
	public com.ksoe.energynet.ejb.ENPlanWorkENAct2OSDataController create()
			throws java.rmi.RemoteException, javax.ejb.CreateException;
}
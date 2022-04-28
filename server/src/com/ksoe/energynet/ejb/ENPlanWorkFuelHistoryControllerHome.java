
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------


package com.ksoe.energynet.ejb;

/**
 * EJB Home interface for ENPlanWorkFuelHistory;
 *
 */

public interface ENPlanWorkFuelHistoryControllerHome extends javax.ejb.EJBHome {
	public com.ksoe.energynet.ejb.ENPlanWorkFuelHistoryController create()
			throws java.rmi.RemoteException, javax.ejb.CreateException;
}
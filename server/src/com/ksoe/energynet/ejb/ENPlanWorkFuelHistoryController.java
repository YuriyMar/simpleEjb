
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb;

/**
 * EJB Controller interface for ENPlanWorkFuelHistory;
 *
 */

import com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkFuelHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFuelHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFuelHistoryShortList;


public interface ENPlanWorkFuelHistoryController extends javax.ejb.EJBObject {
	public static final String JNDI_NAME = "ksoe/energynet/ENPlanWorkFuelHistoryController";

	/* ENPlanWorkFuelHistory. �������� */
	public int add(ENPlanWorkFuelHistory aENPlanWorkFuelHistory)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. ������� */
	public void remove(int anObjectCode) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� */
	public void save(ENPlanWorkFuelHistory aENPlanWorkFuelHistory)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ */
	public ENPlanWorkFuelHistory getObject(int anObjectCode)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ ������ */
	public ENPlanWorkFuelHistoryShortList getList()
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ �� ������� */
	public ENPlanWorkFuelHistoryShortList getFilteredList(
			ENPlanWorkFuelHistoryFilter aENPlanWorkFuelHistoryFilter)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ ��� ��������� */
	public ENPlanWorkFuelHistoryShortList getScrollableList(int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ ��� ��������� �� ������� */
	public ENPlanWorkFuelHistoryShortList getScrollableFilteredList(
			ENPlanWorkFuelHistoryFilter aENPlanWorkFuelHistoryFilter, int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ ��� ��������� �� ������� */
	public ENPlanWorkFuelHistoryShortList getScrollableListByCondition(
			String aCondition, int aFromPosition, int aQuantity)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkFuelHistoryFilter filterObject, int fromPosition,
			int quantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. �������� ������ �� ������ */
	public ENPlanWorkFuelHistoryShort getShortObject(int code)
			throws java.rmi.RemoteException;

}
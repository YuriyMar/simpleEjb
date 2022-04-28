
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb;

/**
 * EJB Controller interface for ENPlanWorkENAct2OSData;
 *
 */

import com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkENAct2OSDataShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkENAct2OSDataFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkENAct2OSDataShortList;


public interface ENPlanWorkENAct2OSDataController extends javax.ejb.EJBObject {
	public static final String JNDI_NAME = "ksoe/energynet/ENPlanWorkENAct2OSDataController";

	/* ENPlanWorkENAct2OSData. �������� */
	public int add(ENPlanWorkENAct2OSData aENPlanWorkENAct2OSData)
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. ������� */
	public void remove(int anObjectCode) throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� */
	public void save(ENPlanWorkENAct2OSData aENPlanWorkENAct2OSData)
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ */
	public ENPlanWorkENAct2OSData getObject(int anObjectCode)
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ ������ */
	public ENPlanWorkENAct2OSDataShortList getList()
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ �� ������� */
	public ENPlanWorkENAct2OSDataShortList getFilteredList(
			ENPlanWorkENAct2OSDataFilter aENPlanWorkENAct2OSDataFilter)
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ ��� ��������� */
	public ENPlanWorkENAct2OSDataShortList getScrollableList(int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ ��� ��������� �� ������� */
	public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(
			ENPlanWorkENAct2OSDataFilter aENPlanWorkENAct2OSDataFilter, int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ ��� ��������� �� ������� */
	public ENPlanWorkENAct2OSDataShortList getScrollableListByCondition(
			String aCondition, int aFromPosition, int aQuantity)
			throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkENAct2OSDataFilter filterObject, int fromPosition,
			int quantity) throws java.rmi.RemoteException;

	/* ENPlanWorkENAct2OSData. �������� ������ �� ������ */
	public ENPlanWorkENAct2OSDataShort getShortObject(int code)
			throws java.rmi.RemoteException;
	
	/* ENPlanWorkENAct2OSData. �������� �������� ��� �������� � ��� ���� */
	public void saveIstOS(ENPlanWorkENAct2OSData aENPlanWorkENAct2OSData)
			throws java.rmi.RemoteException;

}
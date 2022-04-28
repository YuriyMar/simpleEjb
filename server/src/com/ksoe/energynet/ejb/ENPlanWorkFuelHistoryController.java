
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
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

	/* ENPlanWorkFuelHistory. Добавить */
	public int add(ENPlanWorkFuelHistory aENPlanWorkFuelHistory)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Удалить */
	public void remove(int anObjectCode) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Изменить */
	public void save(ENPlanWorkFuelHistory aENPlanWorkFuelHistory)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить объект */
	public ENPlanWorkFuelHistory getObject(int anObjectCode)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить полный список */
	public ENPlanWorkFuelHistoryShortList getList()
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить список по фильтру */
	public ENPlanWorkFuelHistoryShortList getFilteredList(
			ENPlanWorkFuelHistoryFilter aENPlanWorkFuelHistoryFilter)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить список для просмотра */
	public ENPlanWorkFuelHistoryShortList getScrollableList(int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить список для просмотра по фильтру */
	public ENPlanWorkFuelHistoryShortList getScrollableFilteredList(
			ENPlanWorkFuelHistoryFilter aENPlanWorkFuelHistoryFilter, int aFromPosition,
			int aQuantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить список для просмотра по условию */
	public ENPlanWorkFuelHistoryShortList getScrollableListByCondition(
			String aCondition, int aFromPosition, int aQuantity)
			throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkFuelHistoryFilter filterObject, int fromPosition,
			int quantity) throws java.rmi.RemoteException;

	/* ENPlanWorkFuelHistory. Получить объект из списка */
	public ENPlanWorkFuelHistoryShort getShortObject(int code)
			throws java.rmi.RemoteException;

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWorkFuelHistoryDAO;
import com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFuelHistoryShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkFuelHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFuelHistoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWorkFuelHistory;
 *
 */


public abstract class ENPlanWorkFuelHistoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWorkFuelHistoryControllerEJBGen() {
	}

	/* ENPlanWorkFuelHistory. Добавить */
	public int add(ENPlanWorkFuelHistory object) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkFuelHistory. Удалить */
	public void remove(int code) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWorkFuelHistory. Изменить */
	public void save(ENPlanWorkFuelHistory object) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkFuelHistory. Получить объект */
	public ENPlanWorkFuelHistory getObject(int code) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkFuelHistory. Получить полный список */
	public ENPlanWorkFuelHistoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWorkFuelHistory. Получить список по фильтру */
	public ENPlanWorkFuelHistoryShortList getFilteredList(
			ENPlanWorkFuelHistoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWorkFuelHistory. Получить список для просмотра */
	public ENPlanWorkFuelHistoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWorkFuelHistory. Получить список для просмотра по фильтру */
	public ENPlanWorkFuelHistoryShortList getScrollableFilteredList(
			ENPlanWorkFuelHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkFuelHistory. Получить список для просмотра по условию */
	public ENPlanWorkFuelHistoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWorkFuelHistoryFilter filterObject = new ENPlanWorkFuelHistoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWorkFuelHistory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkFuelHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkFuelHistory. Получить объект из списка */
	public ENPlanWorkFuelHistoryShort getShortObject(int code) {
		try {
			ENPlanWorkFuelHistoryDAO objectDAO = new ENPlanWorkFuelHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
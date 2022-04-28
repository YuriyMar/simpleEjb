
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENGPSTrackerHistoryDAO;
import com.ksoe.energynet.valueobject.ENGPSTrackerHistory;
import com.ksoe.energynet.valueobject.lists.ENGPSTrackerHistoryShortList;
import com.ksoe.energynet.valueobject.brief.ENGPSTrackerHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENGPSTrackerHistoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENGPSTrackerHistory;
 *
 */


public abstract class ENGPSTrackerHistoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENGPSTrackerHistoryControllerEJBGen() {
	}

	/* ENGPSTrackerHistory. Добавить */
	public int add(ENGPSTrackerHistory object) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTrackerHistory. Удалить */
	public void remove(int code) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENGPSTrackerHistory. Изменить */
	public void save(ENGPSTrackerHistory object) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTrackerHistory. Получить объект */
	public ENGPSTrackerHistory getObject(int code) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTrackerHistory. Получить полный список */
	public ENGPSTrackerHistoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENGPSTrackerHistory. Получить список по фильтру */
	public ENGPSTrackerHistoryShortList getFilteredList(
			ENGPSTrackerHistoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENGPSTrackerHistory. Получить список для просмотра */
	public ENGPSTrackerHistoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENGPSTrackerHistory. Получить список для просмотра по фильтру */
	public ENGPSTrackerHistoryShortList getScrollableFilteredList(
			ENGPSTrackerHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTrackerHistory. Получить список для просмотра по условию */
	public ENGPSTrackerHistoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENGPSTrackerHistoryFilter filterObject = new ENGPSTrackerHistoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENGPSTrackerHistory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENGPSTrackerHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTrackerHistory. Получить объект из списка */
	public ENGPSTrackerHistoryShort getShortObject(int code) {
		try {
			ENGPSTrackerHistoryDAO objectDAO = new ENGPSTrackerHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGPSTrackerHistory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
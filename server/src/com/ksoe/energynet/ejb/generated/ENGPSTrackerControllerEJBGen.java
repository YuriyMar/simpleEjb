
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENGPSTrackerDAO;
import com.ksoe.energynet.valueobject.ENGPSTracker;
import com.ksoe.energynet.valueobject.lists.ENGPSTrackerShortList;
import com.ksoe.energynet.valueobject.brief.ENGPSTrackerShort;
import com.ksoe.energynet.valueobject.filter.ENGPSTrackerFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENGPSTracker;
 *
 */


public abstract class ENGPSTrackerControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENGPSTrackerControllerEJBGen() {
	}

	/* ENGPSTracker. Добавить */
	public int add(ENGPSTracker object) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENGPSTracker%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTracker. Удалить */
	public void remove(int code) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENGPSTracker%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENGPSTracker. Изменить */
	public void save(ENGPSTracker object) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENGPSTracker%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTracker. Получить объект */
	public ENGPSTracker getObject(int code) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGPSTracker%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTracker. Получить полный список */
	public ENGPSTrackerShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENGPSTracker. Получить список по фильтру */
	public ENGPSTrackerShortList getFilteredList(
			ENGPSTrackerFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENGPSTracker. Получить список для просмотра */
	public ENGPSTrackerShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENGPSTracker. Получить список для просмотра по фильтру */
	public ENGPSTrackerShortList getScrollableFilteredList(
			ENGPSTrackerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENGPSTracker%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTracker. Получить список для просмотра по условию */
	public ENGPSTrackerShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENGPSTrackerFilter filterObject = new ENGPSTrackerFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENGPSTracker. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENGPSTrackerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENGPSTracker%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGPSTracker. Получить объект из списка */
	public ENGPSTrackerShort getShortObject(int code) {
		try {
			ENGPSTrackerDAO objectDAO = new ENGPSTrackerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGPSTracker%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
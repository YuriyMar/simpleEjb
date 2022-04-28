
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanXqtnHistoryDAO;
import com.ksoe.energynet.valueobject.ENPlanXqtnHistory;
import com.ksoe.energynet.valueobject.lists.ENPlanXqtnHistoryShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanXqtnHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENPlanXqtnHistoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanXqtnHistory;
 *
 */


public abstract class ENPlanXqtnHistoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanXqtnHistoryControllerEJBGen() {
	}

	/* ENPlanXqtnHistory. Добавить */
	public int add(ENPlanXqtnHistory object) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateGen = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanXqtnHistory. Удалить */
	public void remove(int code) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanXqtnHistory. Изменить */
	public void save(ENPlanXqtnHistory object) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanXqtnHistory. Получить объект */
	public ENPlanXqtnHistory getObject(int code) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanXqtnHistory. Получить полный список */
	public ENPlanXqtnHistoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanXqtnHistory. Получить список по фильтру */
	public ENPlanXqtnHistoryShortList getFilteredList(
			ENPlanXqtnHistoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanXqtnHistory. Получить список для просмотра */
	public ENPlanXqtnHistoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanXqtnHistory. Получить список для просмотра по фильтру */
	public ENPlanXqtnHistoryShortList getScrollableFilteredList(
			ENPlanXqtnHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanXqtnHistory. Получить список для просмотра по условию */
	public ENPlanXqtnHistoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanXqtnHistoryFilter filterObject = new ENPlanXqtnHistoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanXqtnHistory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanXqtnHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanXqtnHistory. Получить объект из списка */
	public ENPlanXqtnHistoryShort getShortObject(int code) {
		try {
			ENPlanXqtnHistoryDAO objectDAO = new ENPlanXqtnHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanXqtnHistory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
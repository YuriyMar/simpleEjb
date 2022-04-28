
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2022 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelCardHistoryDAO;
import com.ksoe.energynet.valueobject.ENFuelCardHistory;
import com.ksoe.energynet.valueobject.lists.ENFuelCardHistoryShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelCardHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENFuelCardHistoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelCardHistory;
 *
 */


public abstract class ENFuelCardHistoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelCardHistoryControllerEJBGen() {
	}

	/* ENFuelCardHistory. Добавить */
	public int add(ENFuelCardHistory object) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCardHistory. Удалить */
	public void remove(int code) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelCardHistory. Изменить */
	public void save(ENFuelCardHistory object) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCardHistory. Получить объект */
	public ENFuelCardHistory getObject(int code) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCardHistory. Получить полный список */
	public ENFuelCardHistoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelCardHistory. Получить список по фильтру */
	public ENFuelCardHistoryShortList getFilteredList(
			ENFuelCardHistoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelCardHistory. Получить список для просмотра */
	public ENFuelCardHistoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelCardHistory. Получить список для просмотра по фильтру */
	public ENFuelCardHistoryShortList getScrollableFilteredList(
			ENFuelCardHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCardHistory. Получить список для просмотра по условию */
	public ENFuelCardHistoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelCardHistoryFilter filterObject = new ENFuelCardHistoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelCardHistory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelCardHistoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCardHistory. Получить объект из списка */
	public ENFuelCardHistoryShort getShortObject(int code) {
		try {
			ENFuelCardHistoryDAO objectDAO = new ENFuelCardHistoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelCardHistory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
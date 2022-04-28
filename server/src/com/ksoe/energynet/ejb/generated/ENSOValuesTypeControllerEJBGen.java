
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOValuesTypeDAO;
import com.ksoe.energynet.valueobject.ENSOValuesType;
import com.ksoe.energynet.valueobject.lists.ENSOValuesTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENSOValuesTypeShort;
import com.ksoe.energynet.valueobject.filter.ENSOValuesTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOValuesType;
 *
 */


public abstract class ENSOValuesTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOValuesTypeControllerEJBGen() {
	}

	/* ENSOValuesType. Добавить */
	public int add(ENSOValuesType object) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesType. Удалить */
	public void remove(int code) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOValuesType. Изменить */
	public void save(ENSOValuesType object) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesType. Получить объект */
	public ENSOValuesType getObject(int code) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesType. Получить полный список */
	public ENSOValuesTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOValuesType. Получить список по фильтру */
	public ENSOValuesTypeShortList getFilteredList(
			ENSOValuesTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOValuesType. Получить список для просмотра */
	public ENSOValuesTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOValuesType. Получить список для просмотра по фильтру */
	public ENSOValuesTypeShortList getScrollableFilteredList(
			ENSOValuesTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOValuesType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesType. Получить список для просмотра по условию */
	public ENSOValuesTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOValuesTypeFilter filterObject = new ENSOValuesTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOValuesType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOValuesTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOValuesType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesType. Получить объект из списка */
	public ENSOValuesTypeShort getShortObject(int code) {
		try {
			ENSOValuesTypeDAO objectDAO = new ENSOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValuesType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
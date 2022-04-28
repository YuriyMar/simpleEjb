
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSO2SecObjectDAO;
import com.ksoe.energynet.valueobject.ENSO2SecObject;
import com.ksoe.energynet.valueobject.lists.ENSO2SecObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENSO2SecObjectShort;
import com.ksoe.energynet.valueobject.filter.ENSO2SecObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSO2SecObject;
 *
 */


public abstract class ENSO2SecObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSO2SecObjectControllerEJBGen() {
	}

	/* ENSO2SecObject. Добавить */
	public int add(ENSO2SecObject object) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSO2SecObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObject. Удалить */
	public void remove(int code) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSO2SecObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSO2SecObject. Изменить */
	public void save(ENSO2SecObject object) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSO2SecObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObject. Получить объект */
	public ENSO2SecObject getObject(int code) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2SecObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObject. Получить полный список */
	public ENSO2SecObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSO2SecObject. Получить список по фильтру */
	public ENSO2SecObjectShortList getFilteredList(
			ENSO2SecObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSO2SecObject. Получить список для просмотра */
	public ENSO2SecObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSO2SecObject. Получить список для просмотра по фильтру */
	public ENSO2SecObjectShortList getScrollableFilteredList(
			ENSO2SecObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSO2SecObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObject. Получить список для просмотра по условию */
	public ENSO2SecObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSO2SecObjectFilter filterObject = new ENSO2SecObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSO2SecObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSO2SecObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSO2SecObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObject. Получить объект из списка */
	public ENSO2SecObjectShort getShortObject(int code) {
		try {
			ENSO2SecObjectDAO objectDAO = new ENSO2SecObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2SecObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
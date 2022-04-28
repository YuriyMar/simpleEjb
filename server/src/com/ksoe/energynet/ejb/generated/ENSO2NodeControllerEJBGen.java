
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSO2NodeDAO;
import com.ksoe.energynet.valueobject.ENSO2Node;
import com.ksoe.energynet.valueobject.lists.ENSO2NodeShortList;
import com.ksoe.energynet.valueobject.brief.ENSO2NodeShort;
import com.ksoe.energynet.valueobject.filter.ENSO2NodeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSO2Node;
 *
 */


public abstract class ENSO2NodeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSO2NodeControllerEJBGen() {
	}

	/* ENSO2Node. Добавить */
	public int add(ENSO2Node object) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSO2Node%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2Node. Удалить */
	public void remove(int code) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSO2Node%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSO2Node. Изменить */
	public void save(ENSO2Node object) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSO2Node%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2Node. Получить объект */
	public ENSO2Node getObject(int code) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2Node%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2Node. Получить полный список */
	public ENSO2NodeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSO2Node. Получить список по фильтру */
	public ENSO2NodeShortList getFilteredList(
			ENSO2NodeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSO2Node. Получить список для просмотра */
	public ENSO2NodeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSO2Node. Получить список для просмотра по фильтру */
	public ENSO2NodeShortList getScrollableFilteredList(
			ENSO2NodeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSO2Node%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2Node. Получить список для просмотра по условию */
	public ENSO2NodeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSO2NodeFilter filterObject = new ENSO2NodeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSO2Node. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSO2NodeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSO2Node%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2Node. Получить объект из списка */
	public ENSO2NodeShort getShortObject(int code) {
		try {
			ENSO2NodeDAO objectDAO = new ENSO2NodeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2Node%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
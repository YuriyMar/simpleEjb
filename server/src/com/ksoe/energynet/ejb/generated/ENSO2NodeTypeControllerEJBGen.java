
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSO2NodeTypeDAO;
import com.ksoe.energynet.valueobject.ENSO2NodeType;
import com.ksoe.energynet.valueobject.lists.ENSO2NodeTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENSO2NodeTypeShort;
import com.ksoe.energynet.valueobject.filter.ENSO2NodeTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSO2NodeType;
 *
 */


public abstract class ENSO2NodeTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSO2NodeTypeControllerEJBGen() {
	}

	/* ENSO2NodeType. Добавить */
	public int add(ENSO2NodeType object) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSO2NodeType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2NodeType. Удалить */
	public void remove(int code) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSO2NodeType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSO2NodeType. Изменить */
	public void save(ENSO2NodeType object) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSO2NodeType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2NodeType. Получить объект */
	public ENSO2NodeType getObject(int code) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2NodeType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2NodeType. Получить полный список */
	public ENSO2NodeTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSO2NodeType. Получить список по фильтру */
	public ENSO2NodeTypeShortList getFilteredList(
			ENSO2NodeTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSO2NodeType. Получить список для просмотра */
	public ENSO2NodeTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSO2NodeType. Получить список для просмотра по фильтру */
	public ENSO2NodeTypeShortList getScrollableFilteredList(
			ENSO2NodeTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSO2NodeType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2NodeType. Получить список для просмотра по условию */
	public ENSO2NodeTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSO2NodeTypeFilter filterObject = new ENSO2NodeTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSO2NodeType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSO2NodeTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSO2NodeType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2NodeType. Получить объект из списка */
	public ENSO2NodeTypeShort getShortObject(int code) {
		try {
			ENSO2NodeTypeDAO objectDAO = new ENSO2NodeTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2NodeType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
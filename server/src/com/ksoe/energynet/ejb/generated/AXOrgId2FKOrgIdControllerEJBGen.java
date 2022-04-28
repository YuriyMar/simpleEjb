
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.AXOrgId2FKOrgIdDAO;
import com.ksoe.energynet.valueobject.AXOrgId2FKOrgId;
import com.ksoe.energynet.valueobject.lists.AXOrgId2FKOrgIdShortList;
import com.ksoe.energynet.valueobject.brief.AXOrgId2FKOrgIdShort;
import com.ksoe.energynet.valueobject.filter.AXOrgId2FKOrgIdFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for AXOrgId2FKOrgId;
 *
 */


public abstract class AXOrgId2FKOrgIdControllerEJBGen extends
		GenericSessionStatelessBean {
	public AXOrgId2FKOrgIdControllerEJBGen() {
	}

	/* AXOrgId2FKOrgId. Добавить */
	public int add(AXOrgId2FKOrgId object) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgId2FKOrgId. Удалить */
	public void remove(int code) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* AXOrgId2FKOrgId. Изменить */
	public void save(AXOrgId2FKOrgId object) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgId2FKOrgId. Получить объект */
	public AXOrgId2FKOrgId getObject(int code) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgId2FKOrgId. Получить полный список */
	public AXOrgId2FKOrgIdShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* AXOrgId2FKOrgId. Получить список по фильтру */
	public AXOrgId2FKOrgIdShortList getFilteredList(
			AXOrgId2FKOrgIdFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* AXOrgId2FKOrgId. Получить список для просмотра */
	public AXOrgId2FKOrgIdShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* AXOrgId2FKOrgId. Получить список для просмотра по фильтру */
	public AXOrgId2FKOrgIdShortList getScrollableFilteredList(
			AXOrgId2FKOrgIdFilter filterObject, int fromPosition,
			int quantity) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgId2FKOrgId. Получить список для просмотра по условию */
	public AXOrgId2FKOrgIdShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		AXOrgId2FKOrgIdFilter filterObject = new AXOrgId2FKOrgIdFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* AXOrgId2FKOrgId. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			AXOrgId2FKOrgIdFilter filterObject, int fromPosition,
			int quantity) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgId2FKOrgId. Получить объект из списка */
	public AXOrgId2FKOrgIdShort getShortObject(int code) {
		try {
			AXOrgId2FKOrgIdDAO objectDAO = new AXOrgId2FKOrgIdDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.AXOrgId2FKOrgId%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
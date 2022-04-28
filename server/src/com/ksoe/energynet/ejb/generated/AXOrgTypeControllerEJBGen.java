
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.AXOrgTypeDAO;
import com.ksoe.energynet.valueobject.AXOrgType;
import com.ksoe.energynet.valueobject.lists.AXOrgTypeShortList;
import com.ksoe.energynet.valueobject.brief.AXOrgTypeShort;
import com.ksoe.energynet.valueobject.filter.AXOrgTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for AXOrgType;
 *
 */


public abstract class AXOrgTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public AXOrgTypeControllerEJBGen() {
	}

	/* AXOrgType. Добавить */
	public int add(AXOrgType object) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.AXOrgType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgType. Удалить */
	public void remove(int code) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.AXOrgType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* AXOrgType. Изменить */
	public void save(AXOrgType object) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.AXOrgType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgType. Получить объект */
	public AXOrgType getObject(int code) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.AXOrgType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgType. Получить полный список */
	public AXOrgTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* AXOrgType. Получить список по фильтру */
	public AXOrgTypeShortList getFilteredList(
			AXOrgTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* AXOrgType. Получить список для просмотра */
	public AXOrgTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* AXOrgType. Получить список для просмотра по фильтру */
	public AXOrgTypeShortList getScrollableFilteredList(
			AXOrgTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.AXOrgType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgType. Получить список для просмотра по условию */
	public AXOrgTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		AXOrgTypeFilter filterObject = new AXOrgTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* AXOrgType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			AXOrgTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.AXOrgType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* AXOrgType. Получить объект из списка */
	public AXOrgTypeShort getShortObject(int code) {
		try {
			AXOrgTypeDAO objectDAO = new AXOrgTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.AXOrgType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
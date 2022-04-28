
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDepartment2CCExecutorDAO;
import com.ksoe.energynet.valueobject.ENDepartment2CCExecutor;
import com.ksoe.energynet.valueobject.lists.ENDepartment2CCExecutorShortList;
import com.ksoe.energynet.valueobject.brief.ENDepartment2CCExecutorShort;
import com.ksoe.energynet.valueobject.filter.ENDepartment2CCExecutorFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDepartment2CCExecutor;
 *
 */


public abstract class ENDepartment2CCExecutorControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDepartment2CCExecutorControllerEJBGen() {
	}

	/* ENDepartment2CCExecutor. Добавить */
	public int add(ENDepartment2CCExecutor object) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2CCExecutor. Удалить */
	public void remove(int code) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDepartment2CCExecutor. Изменить */
	public void save(ENDepartment2CCExecutor object) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2CCExecutor. Получить объект */
	public ENDepartment2CCExecutor getObject(int code) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2CCExecutor. Получить полный список */
	public ENDepartment2CCExecutorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDepartment2CCExecutor. Получить список по фильтру */
	public ENDepartment2CCExecutorShortList getFilteredList(
			ENDepartment2CCExecutorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDepartment2CCExecutor. Получить список для просмотра */
	public ENDepartment2CCExecutorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDepartment2CCExecutor. Получить список для просмотра по фильтру */
	public ENDepartment2CCExecutorShortList getScrollableFilteredList(
			ENDepartment2CCExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2CCExecutor. Получить список для просмотра по условию */
	public ENDepartment2CCExecutorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDepartment2CCExecutorFilter filterObject = new ENDepartment2CCExecutorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDepartment2CCExecutor. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDepartment2CCExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2CCExecutor. Получить объект из списка */
	public ENDepartment2CCExecutorShort getShortObject(int code) {
		try {
			ENDepartment2CCExecutorDAO objectDAO = new ENDepartment2CCExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDepartment2CCExecutor%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FINExecutorDAO;
import com.ksoe.energynet.valueobject.FINExecutor;
import com.ksoe.energynet.valueobject.lists.FINExecutorShortList;
import com.ksoe.energynet.valueobject.brief.FINExecutorShort;
import com.ksoe.energynet.valueobject.filter.FINExecutorFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FINExecutor;
 *
 */


public abstract class FINExecutorControllerEJBGen extends
		GenericSessionStatelessBean {
	public FINExecutorControllerEJBGen() {
	}

	/* FINExecutor. Добавить */
	public int add(FINExecutor object) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINExecutor. Удалить */
	public void remove(int code) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FINExecutor. Изменить */
	public void save(FINExecutor object) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINExecutor. Получить объект */
	public FINExecutor getObject(int code) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINExecutor. Получить полный список */
	public FINExecutorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FINExecutor. Получить список по фильтру */
	public FINExecutorShortList getFilteredList(
			FINExecutorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FINExecutor. Получить список для просмотра */
	public FINExecutorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FINExecutor. Получить список для просмотра по фильтру */
	public FINExecutorShortList getScrollableFilteredList(
			FINExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FINExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINExecutor. Получить список для просмотра по условию */
	public FINExecutorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FINExecutorFilter filterObject = new FINExecutorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FINExecutor. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FINExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FINExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINExecutor. Получить объект из списка */
	public FINExecutorShort getShortObject(int code) {
		try {
			FINExecutorDAO objectDAO = new FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINExecutor%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
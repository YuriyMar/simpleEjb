
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.EPRen2FINExecutorDAO;
import com.ksoe.energynet.valueobject.EPRen2FINExecutor;
import com.ksoe.energynet.valueobject.brief.EPRen2FINExecutorShort;
import com.ksoe.energynet.valueobject.filter.EPRen2FINExecutorFilter;
import com.ksoe.energynet.valueobject.lists.EPRen2FINExecutorShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for EPRen2FINExecutor;
 *
 */


public abstract class EPRen2FINExecutorControllerEJBGen extends
		GenericSessionStatelessBean {
	public EPRen2FINExecutorControllerEJBGen() {
	}

	/* EPRen2FINExecutor. Добавить */
	public int add(EPRen2FINExecutor object) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* EPRen2FINExecutor. Удалить */
	public void remove(int code) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* EPRen2FINExecutor. Изменить */
	public void save(EPRen2FINExecutor object) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* EPRen2FINExecutor. Получить объект */
	public EPRen2FINExecutor getObject(int code) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* EPRen2FINExecutor. Получить полный список */
	public EPRen2FINExecutorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* EPRen2FINExecutor. Получить список по фильтру */
	public EPRen2FINExecutorShortList getFilteredList(
			EPRen2FINExecutorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* EPRen2FINExecutor. Получить список для просмотра */
	public EPRen2FINExecutorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* EPRen2FINExecutor. Получить список для просмотра по фильтру */
	public EPRen2FINExecutorShortList getScrollableFilteredList(
			EPRen2FINExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* EPRen2FINExecutor. Получить список для просмотра по условию */
	public EPRen2FINExecutorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		EPRen2FINExecutorFilter filterObject = new EPRen2FINExecutorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* EPRen2FINExecutor. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			EPRen2FINExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* EPRen2FINExecutor. Получить объект из списка */
	public EPRen2FINExecutorShort getShortObject(int code) {
		try {
			EPRen2FINExecutorDAO objectDAO = new EPRen2FINExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.EPRen2FINExecutor%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
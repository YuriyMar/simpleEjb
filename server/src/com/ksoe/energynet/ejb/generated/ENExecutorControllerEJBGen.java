
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENExecutorDAO;
import com.ksoe.energynet.valueobject.ENExecutor;
import com.ksoe.energynet.valueobject.lists.ENExecutorShortList;
import com.ksoe.energynet.valueobject.brief.ENExecutorShort;
import com.ksoe.energynet.valueobject.filter.ENExecutorFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENExecutor;
 *
 */


public abstract class ENExecutorControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENExecutorControllerEJBGen() {
	}

	/* ENExecutor. Добавить */
	public int add(ENExecutor object) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENExecutor. Удалить */
	public void remove(int code) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENExecutor. Изменить */
	public void save(ENExecutor object) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENExecutor. Получить объект */
	public ENExecutor getObject(int code) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENExecutor%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENExecutor. Получить полный список */
	public ENExecutorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENExecutor. Получить список по фильтру */
	public ENExecutorShortList getFilteredList(
			ENExecutorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENExecutor. Получить список для просмотра */
	public ENExecutorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENExecutor. Получить список для просмотра по фильтру */
	public ENExecutorShortList getScrollableFilteredList(
			ENExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENExecutor. Получить список для просмотра по условию */
	public ENExecutorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENExecutorFilter filterObject = new ENExecutorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENExecutor. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENExecutorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENExecutor%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENExecutor. Получить объект из списка */
	public ENExecutorShort getShortObject(int code) {
		try {
			ENExecutorDAO objectDAO = new ENExecutorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENExecutor%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
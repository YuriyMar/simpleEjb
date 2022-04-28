
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCheckpointDAO;
import com.ksoe.energynet.valueobject.ENCheckpoint;
import com.ksoe.energynet.valueobject.lists.ENCheckpointShortList;
import com.ksoe.energynet.valueobject.brief.ENCheckpointShort;
import com.ksoe.energynet.valueobject.filter.ENCheckpointFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCheckpoint;
 *
 */


public abstract class ENCheckpointControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCheckpointControllerEJBGen() {
	}

	/* ENCheckpoint. Добавить */
	public int add(ENCheckpoint object) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCheckpoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpoint. Удалить */
	public void remove(int code) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCheckpoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCheckpoint. Изменить */
	public void save(ENCheckpoint object) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCheckpoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpoint. Получить объект */
	public ENCheckpoint getObject(int code) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpoint. Получить полный список */
	public ENCheckpointShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCheckpoint. Получить список по фильтру */
	public ENCheckpointShortList getFilteredList(
			ENCheckpointFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCheckpoint. Получить список для просмотра */
	public ENCheckpointShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCheckpoint. Получить список для просмотра по фильтру */
	public ENCheckpointShortList getScrollableFilteredList(
			ENCheckpointFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCheckpoint%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpoint. Получить список для просмотра по условию */
	public ENCheckpointShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCheckpointFilter filterObject = new ENCheckpointFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCheckpoint. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCheckpointFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCheckpoint%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpoint. Получить объект из списка */
	public ENCheckpointShort getShortObject(int code) {
		try {
			ENCheckpointDAO objectDAO = new ENCheckpointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpoint%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
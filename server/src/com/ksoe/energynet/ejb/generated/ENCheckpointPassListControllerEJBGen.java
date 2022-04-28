
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCheckpointPassListDAO;
import com.ksoe.energynet.valueobject.ENCheckpointPassList;
import com.ksoe.energynet.valueobject.lists.ENCheckpointPassListShortList;
import com.ksoe.energynet.valueobject.brief.ENCheckpointPassListShort;
import com.ksoe.energynet.valueobject.filter.ENCheckpointPassListFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCheckpointPassList;
 *
 */


public abstract class ENCheckpointPassListControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCheckpointPassListControllerEJBGen() {
	}

	/* ENCheckpointPassList. Добавить */
	public int add(ENCheckpointPassList object) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassList. Удалить */
	public void remove(int code) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCheckpointPassList. Изменить */
	public void save(ENCheckpointPassList object) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassList. Получить объект */
	public ENCheckpointPassList getObject(int code) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassList. Получить полный список */
	public ENCheckpointPassListShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCheckpointPassList. Получить список по фильтру */
	public ENCheckpointPassListShortList getFilteredList(
			ENCheckpointPassListFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCheckpointPassList. Получить список для просмотра */
	public ENCheckpointPassListShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCheckpointPassList. Получить список для просмотра по фильтру */
	public ENCheckpointPassListShortList getScrollableFilteredList(
			ENCheckpointPassListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassList. Получить список для просмотра по условию */
	public ENCheckpointPassListShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCheckpointPassListFilter filterObject = new ENCheckpointPassListFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCheckpointPassList. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCheckpointPassListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassList. Получить объект из списка */
	public ENCheckpointPassListShort getShortObject(int code) {
		try {
			ENCheckpointPassListDAO objectDAO = new ENCheckpointPassListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointPassList%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCheckpointPassListItemDAO;
import com.ksoe.energynet.valueobject.ENCheckpointPassListItem;
import com.ksoe.energynet.valueobject.lists.ENCheckpointPassListItemShortList;
import com.ksoe.energynet.valueobject.brief.ENCheckpointPassListItemShort;
import com.ksoe.energynet.valueobject.filter.ENCheckpointPassListItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCheckpointPassListItem;
 *
 */


public abstract class ENCheckpointPassListItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCheckpointPassListItemControllerEJBGen() {
	}

	/* ENCheckpointPassListItem. Добавить */
	public int add(ENCheckpointPassListItem object) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassListItem. Удалить */
	public void remove(int code) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCheckpointPassListItem. Изменить */
	public void save(ENCheckpointPassListItem object) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassListItem. Получить объект */
	public ENCheckpointPassListItem getObject(int code) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassListItem. Получить полный список */
	public ENCheckpointPassListItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCheckpointPassListItem. Получить список по фильтру */
	public ENCheckpointPassListItemShortList getFilteredList(
			ENCheckpointPassListItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCheckpointPassListItem. Получить список для просмотра */
	public ENCheckpointPassListItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCheckpointPassListItem. Получить список для просмотра по фильтру */
	public ENCheckpointPassListItemShortList getScrollableFilteredList(
			ENCheckpointPassListItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassListItem. Получить список для просмотра по условию */
	public ENCheckpointPassListItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCheckpointPassListItemFilter filterObject = new ENCheckpointPassListItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCheckpointPassListItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCheckpointPassListItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointPassListItem. Получить объект из списка */
	public ENCheckpointPassListItemShort getShortObject(int code) {
		try {
			ENCheckpointPassListItemDAO objectDAO = new ENCheckpointPassListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointPassListItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
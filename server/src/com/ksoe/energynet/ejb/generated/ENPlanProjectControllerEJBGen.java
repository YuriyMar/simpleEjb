
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanProjectDAO;
import com.ksoe.energynet.valueobject.ENPlanProject;
import com.ksoe.energynet.valueobject.lists.ENPlanProjectShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanProjectShort;
import com.ksoe.energynet.valueobject.filter.ENPlanProjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanProject;
 *
 */


public abstract class ENPlanProjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanProjectControllerEJBGen() {
	}

	/* ENPlanProject. Добавить */
	public int add(ENPlanProject object) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanProject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProject. Удалить */
	public void remove(int code) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanProject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanProject. Изменить */
	public void save(ENPlanProject object) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanProject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProject. Получить объект */
	public ENPlanProject getObject(int code) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProject. Получить полный список */
	public ENPlanProjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanProject. Получить список по фильтру */
	public ENPlanProjectShortList getFilteredList(
			ENPlanProjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanProject. Получить список для просмотра */
	public ENPlanProjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanProject. Получить список для просмотра по фильтру */
	public ENPlanProjectShortList getScrollableFilteredList(
			ENPlanProjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanProject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProject. Получить список для просмотра по условию */
	public ENPlanProjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanProjectFilter filterObject = new ENPlanProjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanProject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanProjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanProject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProject. Получить объект из списка */
	public ENPlanProjectShort getShortObject(int code) {
		try {
			ENPlanProjectDAO objectDAO = new ENPlanProjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
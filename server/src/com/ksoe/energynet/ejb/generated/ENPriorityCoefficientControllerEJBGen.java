
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPriorityCoefficientDAO;
import com.ksoe.energynet.valueobject.ENPriorityCoefficient;
import com.ksoe.energynet.valueobject.lists.ENPriorityCoefficientShortList;
import com.ksoe.energynet.valueobject.brief.ENPriorityCoefficientShort;
import com.ksoe.energynet.valueobject.filter.ENPriorityCoefficientFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPriorityCoefficient;
 *
 */


public abstract class ENPriorityCoefficientControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPriorityCoefficientControllerEJBGen() {
	}

	/* ENPriorityCoefficient. Добавить */
	public int add(ENPriorityCoefficient object) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPriorityCoefficient. Удалить */
	public void remove(int code) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPriorityCoefficient. Изменить */
	public void save(ENPriorityCoefficient object) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPriorityCoefficient. Получить объект */
	public ENPriorityCoefficient getObject(int code) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPriorityCoefficient. Получить полный список */
	public ENPriorityCoefficientShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPriorityCoefficient. Получить список по фильтру */
	public ENPriorityCoefficientShortList getFilteredList(
			ENPriorityCoefficientFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPriorityCoefficient. Получить список для просмотра */
	public ENPriorityCoefficientShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPriorityCoefficient. Получить список для просмотра по фильтру */
	public ENPriorityCoefficientShortList getScrollableFilteredList(
			ENPriorityCoefficientFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPriorityCoefficient. Получить список для просмотра по условию */
	public ENPriorityCoefficientShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPriorityCoefficientFilter filterObject = new ENPriorityCoefficientFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPriorityCoefficient. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPriorityCoefficientFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPriorityCoefficient. Получить объект из списка */
	public ENPriorityCoefficientShort getShortObject(int code) {
		try {
			ENPriorityCoefficientDAO objectDAO = new ENPriorityCoefficientDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPriorityCoefficient%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENEstimateItem2PlanDAO;
import com.ksoe.energynet.valueobject.ENEstimateItem2Plan;
import com.ksoe.energynet.valueobject.lists.ENEstimateItem2PlanShortList;
import com.ksoe.energynet.valueobject.brief.ENEstimateItem2PlanShort;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2PlanFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENEstimateItem2Plan;
 *
 */


public abstract class ENEstimateItem2PlanControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENEstimateItem2PlanControllerEJBGen() {
	}

	/* ENEstimateItem2Plan. Добавить */
	public int add(ENEstimateItem2Plan object) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2Plan. Удалить */
	public void remove(int code) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENEstimateItem2Plan. Изменить */
	public void save(ENEstimateItem2Plan object) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2Plan. Получить объект */
	public ENEstimateItem2Plan getObject(int code) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2Plan. Получить полный список */
	public ENEstimateItem2PlanShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENEstimateItem2Plan. Получить список по фильтру */
	public ENEstimateItem2PlanShortList getFilteredList(
			ENEstimateItem2PlanFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENEstimateItem2Plan. Получить список для просмотра */
	public ENEstimateItem2PlanShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENEstimateItem2Plan. Получить список для просмотра по фильтру */
	public ENEstimateItem2PlanShortList getScrollableFilteredList(
			ENEstimateItem2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2Plan. Получить список для просмотра по условию */
	public ENEstimateItem2PlanShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENEstimateItem2PlanFilter filterObject = new ENEstimateItem2PlanFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENEstimateItem2Plan. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENEstimateItem2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2Plan. Получить объект из списка */
	public ENEstimateItem2PlanShort getShortObject(int code) {
		try {
			ENEstimateItem2PlanDAO objectDAO = new ENEstimateItem2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2Plan%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWork2RQAllocationListDAO;
import com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2RQAllocationListShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWork2RQAllocationListShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2RQAllocationListFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWork2RQAllocationList;
 *
 */


public abstract class ENPlanWork2RQAllocationListControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWork2RQAllocationListControllerEJBGen() {
	}

	/* ENPlanWork2RQAllocationList. Добавить */
	public int add(ENPlanWork2RQAllocationList object) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2RQAllocationList. Удалить */
	public void remove(int code) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWork2RQAllocationList. Изменить */
	public void save(ENPlanWork2RQAllocationList object) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2RQAllocationList. Получить объект */
	public ENPlanWork2RQAllocationList getObject(int code) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2RQAllocationList. Получить полный список */
	public ENPlanWork2RQAllocationListShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWork2RQAllocationList. Получить список по фильтру */
	public ENPlanWork2RQAllocationListShortList getFilteredList(
			ENPlanWork2RQAllocationListFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWork2RQAllocationList. Получить список для просмотра */
	public ENPlanWork2RQAllocationListShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWork2RQAllocationList. Получить список для просмотра по фильтру */
	public ENPlanWork2RQAllocationListShortList getScrollableFilteredList(
			ENPlanWork2RQAllocationListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2RQAllocationList. Получить список для просмотра по условию */
	public ENPlanWork2RQAllocationListShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWork2RQAllocationListFilter filterObject = new ENPlanWork2RQAllocationListFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWork2RQAllocationList. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWork2RQAllocationListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2RQAllocationList. Получить объект из списка */
	public ENPlanWork2RQAllocationListShort getShortObject(int code) {
		try {
			ENPlanWork2RQAllocationListDAO objectDAO = new ENPlanWork2RQAllocationListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2RQAllocationList%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
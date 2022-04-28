
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanGraphFinancingFuelItemDAO;
import com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem;
import com.ksoe.energynet.valueobject.lists.ENPlanGraphFinancingFuelItemShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanGraphFinancingFuelItemShort;
import com.ksoe.energynet.valueobject.filter.ENPlanGraphFinancingFuelItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanGraphFinancingFuelItem;
 *
 */


public abstract class ENPlanGraphFinancingFuelItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanGraphFinancingFuelItemControllerEJBGen() {
	}

	/* ENPlanGraphFinancingFuelItem. Добавить */
	public int add(ENPlanGraphFinancingFuelItem object) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuelItem. Удалить */
	public void remove(int code) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanGraphFinancingFuelItem. Изменить */
	public void save(ENPlanGraphFinancingFuelItem object) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuelItem. Получить объект */
	public ENPlanGraphFinancingFuelItem getObject(int code) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuelItem. Получить полный список */
	public ENPlanGraphFinancingFuelItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanGraphFinancingFuelItem. Получить список по фильтру */
	public ENPlanGraphFinancingFuelItemShortList getFilteredList(
			ENPlanGraphFinancingFuelItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanGraphFinancingFuelItem. Получить список для просмотра */
	public ENPlanGraphFinancingFuelItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanGraphFinancingFuelItem. Получить список для просмотра по фильтру */
	public ENPlanGraphFinancingFuelItemShortList getScrollableFilteredList(
			ENPlanGraphFinancingFuelItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuelItem. Получить список для просмотра по условию */
	public ENPlanGraphFinancingFuelItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanGraphFinancingFuelItemFilter filterObject = new ENPlanGraphFinancingFuelItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanGraphFinancingFuelItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanGraphFinancingFuelItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuelItem. Получить объект из списка */
	public ENPlanGraphFinancingFuelItemShort getShortObject(int code) {
		try {
			ENPlanGraphFinancingFuelItemDAO objectDAO = new ENPlanGraphFinancingFuelItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuelItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
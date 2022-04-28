
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanGraphFinancingFuelDAO;
import com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel;
import com.ksoe.energynet.valueobject.lists.ENPlanGraphFinancingFuelShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanGraphFinancingFuelShort;
import com.ksoe.energynet.valueobject.filter.ENPlanGraphFinancingFuelFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanGraphFinancingFuel;
 *
 */


public abstract class ENPlanGraphFinancingFuelControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanGraphFinancingFuelControllerEJBGen() {
	}

	/* ENPlanGraphFinancingFuel. Добавить */
	public int add(ENPlanGraphFinancingFuel object) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuel. Удалить */
	public void remove(int code) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanGraphFinancingFuel. Изменить */
	public void save(ENPlanGraphFinancingFuel object) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuel. Получить объект */
	public ENPlanGraphFinancingFuel getObject(int code) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuel. Получить полный список */
	public ENPlanGraphFinancingFuelShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanGraphFinancingFuel. Получить список по фильтру */
	public ENPlanGraphFinancingFuelShortList getFilteredList(
			ENPlanGraphFinancingFuelFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanGraphFinancingFuel. Получить список для просмотра */
	public ENPlanGraphFinancingFuelShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanGraphFinancingFuel. Получить список для просмотра по фильтру */
	public ENPlanGraphFinancingFuelShortList getScrollableFilteredList(
			ENPlanGraphFinancingFuelFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuel. Получить список для просмотра по условию */
	public ENPlanGraphFinancingFuelShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanGraphFinancingFuelFilter filterObject = new ENPlanGraphFinancingFuelFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanGraphFinancingFuel. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanGraphFinancingFuelFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanGraphFinancingFuel. Получить объект из списка */
	public ENPlanGraphFinancingFuelShort getShortObject(int code) {
		try {
			ENPlanGraphFinancingFuelDAO objectDAO = new ENPlanGraphFinancingFuelDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanGraphFinancingFuel%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
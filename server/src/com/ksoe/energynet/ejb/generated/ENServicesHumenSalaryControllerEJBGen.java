
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesHumenSalaryDAO;
import com.ksoe.energynet.valueobject.ENServicesHumenSalary;
import com.ksoe.energynet.valueobject.lists.ENServicesHumenSalaryShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesHumenSalaryShort;
import com.ksoe.energynet.valueobject.filter.ENServicesHumenSalaryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesHumenSalary;
 *
 */


public abstract class ENServicesHumenSalaryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesHumenSalaryControllerEJBGen() {
	}

	/* ENServicesHumenSalary. Добавить */
	public int add(ENServicesHumenSalary object) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesHumenSalary. Удалить */
	public void remove(int code) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesHumenSalary. Изменить */
	public void save(ENServicesHumenSalary object) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesHumenSalary. Получить объект */
	public ENServicesHumenSalary getObject(int code) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesHumenSalary. Получить полный список */
	public ENServicesHumenSalaryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesHumenSalary. Получить список по фильтру */
	public ENServicesHumenSalaryShortList getFilteredList(
			ENServicesHumenSalaryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesHumenSalary. Получить список для просмотра */
	public ENServicesHumenSalaryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesHumenSalary. Получить список для просмотра по фильтру */
	public ENServicesHumenSalaryShortList getScrollableFilteredList(
			ENServicesHumenSalaryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesHumenSalary. Получить список для просмотра по условию */
	public ENServicesHumenSalaryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesHumenSalaryFilter filterObject = new ENServicesHumenSalaryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesHumenSalary. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesHumenSalaryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesHumenSalary. Получить объект из списка */
	public ENServicesHumenSalaryShort getShortObject(int code) {
		try {
			ENServicesHumenSalaryDAO objectDAO = new ENServicesHumenSalaryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesHumenSalary%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
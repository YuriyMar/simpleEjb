
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanInformCustomerDAO;
import com.ksoe.energynet.valueobject.ENPlanInformCustomer;
import com.ksoe.energynet.valueobject.lists.ENPlanInformCustomerShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanInformCustomerShort;
import com.ksoe.energynet.valueobject.filter.ENPlanInformCustomerFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanInformCustomer;
 *
 */


public abstract class ENPlanInformCustomerControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanInformCustomerControllerEJBGen() {
	}

	/* ENPlanInformCustomer. Добавить */
	public int add(ENPlanInformCustomer object) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanInformCustomer. Удалить */
	public void remove(int code) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanInformCustomer. Изменить */
	public void save(ENPlanInformCustomer object) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanInformCustomer. Получить объект */
	public ENPlanInformCustomer getObject(int code) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanInformCustomer. Получить полный список */
	public ENPlanInformCustomerShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanInformCustomer. Получить список по фильтру */
	public ENPlanInformCustomerShortList getFilteredList(
			ENPlanInformCustomerFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanInformCustomer. Получить список для просмотра */
	public ENPlanInformCustomerShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanInformCustomer. Получить список для просмотра по фильтру */
	public ENPlanInformCustomerShortList getScrollableFilteredList(
			ENPlanInformCustomerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanInformCustomer. Получить список для просмотра по условию */
	public ENPlanInformCustomerShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanInformCustomerFilter filterObject = new ENPlanInformCustomerFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanInformCustomer. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanInformCustomerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanInformCustomer. Получить объект из списка */
	public ENPlanInformCustomerShort getShortObject(int code) {
		try {
			ENPlanInformCustomerDAO objectDAO = new ENPlanInformCustomerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanInformCustomer%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
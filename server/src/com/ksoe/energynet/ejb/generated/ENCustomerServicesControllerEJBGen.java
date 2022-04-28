
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCustomerServicesDAO;
import com.ksoe.energynet.valueobject.filter.ENCustomerServicesFilter;
import com.ksoe.energynet.valueobject.lists.ENCustomerServicesShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCustomerServices;
 *
 */


public abstract class ENCustomerServicesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCustomerServicesControllerEJBGen() {
	}



	/* ENCustomerServices. Получить полный список */
	public ENCustomerServicesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCustomerServices. Получить список по фильтру */
	public ENCustomerServicesShortList getFilteredList(
			ENCustomerServicesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCustomerServices. Получить список для просмотра */
	public ENCustomerServicesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCustomerServices. Получить список для просмотра по фильтру */
	public ENCustomerServicesShortList getScrollableFilteredList(
			ENCustomerServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCustomerServicesDAO objectDAO = new ENCustomerServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCustomerServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerServices. Получить список для просмотра по условию */
	public ENCustomerServicesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCustomerServicesFilter filterObject = new ENCustomerServicesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCustomerServices. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCustomerServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCustomerServicesDAO objectDAO = new ENCustomerServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCustomerServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}



}
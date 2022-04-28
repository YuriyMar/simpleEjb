
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCustomerWarrantDAO;
import com.ksoe.energynet.valueobject.ENCustomerWarrant;
import com.ksoe.energynet.valueobject.lists.ENCustomerWarrantShortList;
import com.ksoe.energynet.valueobject.brief.ENCustomerWarrantShort;
import com.ksoe.energynet.valueobject.filter.ENCustomerWarrantFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCustomerWarrant;
 *
 */


public abstract class ENCustomerWarrantControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCustomerWarrantControllerEJBGen() {
	}

	/* ENCustomerWarrant. Добавить */
	public int add(ENCustomerWarrant object) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerWarrant. Удалить */
	public void remove(int code) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCustomerWarrant. Изменить */
	public void save(ENCustomerWarrant object) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerWarrant. Получить объект */
	public ENCustomerWarrant getObject(int code) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerWarrant. Получить полный список */
	public ENCustomerWarrantShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCustomerWarrant. Получить список по фильтру */
	public ENCustomerWarrantShortList getFilteredList(
			ENCustomerWarrantFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCustomerWarrant. Получить список для просмотра */
	public ENCustomerWarrantShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCustomerWarrant. Получить список для просмотра по фильтру */
	public ENCustomerWarrantShortList getScrollableFilteredList(
			ENCustomerWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerWarrant. Получить список для просмотра по условию */
	public ENCustomerWarrantShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCustomerWarrantFilter filterObject = new ENCustomerWarrantFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCustomerWarrant. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCustomerWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCustomerWarrant. Получить объект из списка */
	public ENCustomerWarrantShort getShortObject(int code) {
		try {
			ENCustomerWarrantDAO objectDAO = new ENCustomerWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCustomerWarrant%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRegForSupplierItemDAO;
import com.ksoe.energynet.valueobject.ENRegForSupplierItem;
import com.ksoe.energynet.valueobject.lists.ENRegForSupplierItemShortList;
import com.ksoe.energynet.valueobject.brief.ENRegForSupplierItemShort;
import com.ksoe.energynet.valueobject.filter.ENRegForSupplierItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRegForSupplierItem;
 *
 */


public abstract class ENRegForSupplierItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRegForSupplierItemControllerEJBGen() {
	}

	/* ENRegForSupplierItem. Добавить */
	public int add(ENRegForSupplierItem object) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierItem. Удалить */
	public void remove(int code) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRegForSupplierItem. Изменить */
	public void save(ENRegForSupplierItem object) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierItem. Получить объект */
	public ENRegForSupplierItem getObject(int code) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierItem. Получить полный список */
	public ENRegForSupplierItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRegForSupplierItem. Получить список по фильтру */
	public ENRegForSupplierItemShortList getFilteredList(
			ENRegForSupplierItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRegForSupplierItem. Получить список для просмотра */
	public ENRegForSupplierItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRegForSupplierItem. Получить список для просмотра по фильтру */
	public ENRegForSupplierItemShortList getScrollableFilteredList(
			ENRegForSupplierItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierItem. Получить список для просмотра по условию */
	public ENRegForSupplierItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRegForSupplierItemFilter filterObject = new ENRegForSupplierItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRegForSupplierItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENRegForSupplierItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierItem. Получить объект из списка */
	public ENRegForSupplierItemShort getShortObject(int code) {
		try {
			ENRegForSupplierItemDAO objectDAO = new ENRegForSupplierItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
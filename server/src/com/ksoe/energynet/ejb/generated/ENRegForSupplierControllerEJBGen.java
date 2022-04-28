
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRegForSupplierDAO;
import com.ksoe.energynet.valueobject.ENRegForSupplier;
import com.ksoe.energynet.valueobject.lists.ENRegForSupplierShortList;
import com.ksoe.energynet.valueobject.brief.ENRegForSupplierShort;
import com.ksoe.energynet.valueobject.filter.ENRegForSupplierFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRegForSupplier;
 *
 */


public abstract class ENRegForSupplierControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRegForSupplierControllerEJBGen() {
	}

	/* ENRegForSupplier. Добавить */
	public int add(ENRegForSupplier object) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRegForSupplier%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplier. Удалить */
	public void remove(int code) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRegForSupplier%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRegForSupplier. Изменить */
	public void save(ENRegForSupplier object) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRegForSupplier%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplier. Получить объект */
	public ENRegForSupplier getObject(int code) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplier%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplier. Получить полный список */
	public ENRegForSupplierShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRegForSupplier. Получить список по фильтру */
	public ENRegForSupplierShortList getFilteredList(
			ENRegForSupplierFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRegForSupplier. Получить список для просмотра */
	public ENRegForSupplierShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRegForSupplier. Получить список для просмотра по фильтру */
	public ENRegForSupplierShortList getScrollableFilteredList(
			ENRegForSupplierFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRegForSupplier%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplier. Получить список для просмотра по условию */
	public ENRegForSupplierShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRegForSupplierFilter filterObject = new ENRegForSupplierFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRegForSupplier. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENRegForSupplierFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRegForSupplier%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplier. Получить объект из списка */
	public ENRegForSupplierShort getShortObject(int code) {
		try {
			ENRegForSupplierDAO objectDAO = new ENRegForSupplierDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplier%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
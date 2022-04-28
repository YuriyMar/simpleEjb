
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRegForSupplierStatusDAO;
import com.ksoe.energynet.valueobject.ENRegForSupplierStatus;
import com.ksoe.energynet.valueobject.lists.ENRegForSupplierStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENRegForSupplierStatusShort;
import com.ksoe.energynet.valueobject.filter.ENRegForSupplierStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRegForSupplierStatus;
 *
 */


public abstract class ENRegForSupplierStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRegForSupplierStatusControllerEJBGen() {
	}

	/* ENRegForSupplierStatus. Добавить */
	public int add(ENRegForSupplierStatus object) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierStatus. Удалить */
	public void remove(int code) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRegForSupplierStatus. Изменить */
	public void save(ENRegForSupplierStatus object) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierStatus. Получить объект */
	public ENRegForSupplierStatus getObject(int code) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierStatus. Получить полный список */
	public ENRegForSupplierStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRegForSupplierStatus. Получить список по фильтру */
	public ENRegForSupplierStatusShortList getFilteredList(
			ENRegForSupplierStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRegForSupplierStatus. Получить список для просмотра */
	public ENRegForSupplierStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRegForSupplierStatus. Получить список для просмотра по фильтру */
	public ENRegForSupplierStatusShortList getScrollableFilteredList(
			ENRegForSupplierStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierStatus. Получить список для просмотра по условию */
	public ENRegForSupplierStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRegForSupplierStatusFilter filterObject = new ENRegForSupplierStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRegForSupplierStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENRegForSupplierStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierStatus. Получить объект из списка */
	public ENRegForSupplierStatusShort getShortObject(int code) {
		try {
			ENRegForSupplierStatusDAO objectDAO = new ENRegForSupplierStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
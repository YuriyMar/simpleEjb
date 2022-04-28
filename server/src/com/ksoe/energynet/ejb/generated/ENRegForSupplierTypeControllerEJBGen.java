
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRegForSupplierTypeDAO;
import com.ksoe.energynet.valueobject.ENRegForSupplierType;
import com.ksoe.energynet.valueobject.lists.ENRegForSupplierTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENRegForSupplierTypeShort;
import com.ksoe.energynet.valueobject.filter.ENRegForSupplierTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRegForSupplierType;
 *
 */


public abstract class ENRegForSupplierTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRegForSupplierTypeControllerEJBGen() {
	}

	/* ENRegForSupplierType. Добавить */
	public int add(ENRegForSupplierType object) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. Удалить */
	public void remove(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRegForSupplierType. Изменить */
	public void save(ENRegForSupplierType object) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. Получить объект */
	public ENRegForSupplierType getObject(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. Получить полный список */
	public ENRegForSupplierTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRegForSupplierType. Получить список по фильтру */
	public ENRegForSupplierTypeShortList getFilteredList(
			ENRegForSupplierTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRegForSupplierType. Получить список для просмотра */
	public ENRegForSupplierTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRegForSupplierType. Получить список для просмотра по фильтру */
	public ENRegForSupplierTypeShortList getScrollableFilteredList(
			ENRegForSupplierTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. Получить список для просмотра по условию */
	public ENRegForSupplierTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRegForSupplierTypeFilter filterObject = new ENRegForSupplierTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRegForSupplierType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENRegForSupplierTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. Получить объект из списка */
	public ENRegForSupplierTypeShort getShortObject(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
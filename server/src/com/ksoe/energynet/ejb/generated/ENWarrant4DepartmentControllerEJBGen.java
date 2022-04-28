
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWarrant4DepartmentDAO;
import com.ksoe.energynet.valueobject.ENWarrant4Department;
import com.ksoe.energynet.valueobject.lists.ENWarrant4DepartmentShortList;
import com.ksoe.energynet.valueobject.brief.ENWarrant4DepartmentShort;
import com.ksoe.energynet.valueobject.filter.ENWarrant4DepartmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWarrant4Department;
 *
 */


public abstract class ENWarrant4DepartmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWarrant4DepartmentControllerEJBGen() {
	}

	/* ENWarrant4Department. Добавить */
	public int add(ENWarrant4Department object) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWarrant4Department%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant4Department. Удалить */
	public void remove(int code) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWarrant4Department%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWarrant4Department. Изменить */
	public void save(ENWarrant4Department object) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWarrant4Department%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant4Department. Получить объект */
	public ENWarrant4Department getObject(int code) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrant4Department%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant4Department. Получить полный список */
	public ENWarrant4DepartmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWarrant4Department. Получить список по фильтру */
	public ENWarrant4DepartmentShortList getFilteredList(
			ENWarrant4DepartmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWarrant4Department. Получить список для просмотра */
	public ENWarrant4DepartmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWarrant4Department. Получить список для просмотра по фильтру */
	public ENWarrant4DepartmentShortList getScrollableFilteredList(
			ENWarrant4DepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWarrant4Department%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant4Department. Получить список для просмотра по условию */
	public ENWarrant4DepartmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWarrant4DepartmentFilter filterObject = new ENWarrant4DepartmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWarrant4Department. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWarrant4DepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWarrant4Department%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant4Department. Получить объект из списка */
	public ENWarrant4DepartmentShort getShortObject(int code) {
		try {
			ENWarrant4DepartmentDAO objectDAO = new ENWarrant4DepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrant4Department%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
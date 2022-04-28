
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENGeographicDepartmentDAO;
import com.ksoe.energynet.valueobject.ENGeographicDepartment;
import com.ksoe.energynet.valueobject.lists.ENGeographicDepartmentShortList;
import com.ksoe.energynet.valueobject.brief.ENGeographicDepartmentShort;
import com.ksoe.energynet.valueobject.filter.ENGeographicDepartmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENGeographicDepartment;
 *
 */


public abstract class ENGeographicDepartmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENGeographicDepartmentControllerEJBGen() {
	}

	/* ENGeographicDepartment. Добавить */
	public int add(ENGeographicDepartment object) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeographicDepartment. Удалить */
	public void remove(int code) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENGeographicDepartment. Изменить */
	public void save(ENGeographicDepartment object) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeographicDepartment. Получить объект */
	public ENGeographicDepartment getObject(int code) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeographicDepartment. Получить полный список */
	public ENGeographicDepartmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENGeographicDepartment. Получить список по фильтру */
	public ENGeographicDepartmentShortList getFilteredList(
			ENGeographicDepartmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENGeographicDepartment. Получить список для просмотра */
	public ENGeographicDepartmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENGeographicDepartment. Получить список для просмотра по фильтру */
	public ENGeographicDepartmentShortList getScrollableFilteredList(
			ENGeographicDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeographicDepartment. Получить список для просмотра по условию */
	public ENGeographicDepartmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENGeographicDepartmentFilter filterObject = new ENGeographicDepartmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENGeographicDepartment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENGeographicDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeographicDepartment. Получить объект из списка */
	public ENGeographicDepartmentShort getShortObject(int code) {
		try {
			ENGeographicDepartmentDAO objectDAO = new ENGeographicDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeographicDepartment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
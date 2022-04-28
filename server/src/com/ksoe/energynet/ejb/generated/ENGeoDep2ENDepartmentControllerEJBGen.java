
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENGeoDep2ENDepartmentDAO;
import com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment;
import com.ksoe.energynet.valueobject.lists.ENGeoDep2ENDepartmentShortList;
import com.ksoe.energynet.valueobject.brief.ENGeoDep2ENDepartmentShort;
import com.ksoe.energynet.valueobject.filter.ENGeoDep2ENDepartmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENGeoDep2ENDepartment;
 *
 */


public abstract class ENGeoDep2ENDepartmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENGeoDep2ENDepartmentControllerEJBGen() {
	}

	/* ENGeoDep2ENDepartment. Добавить */
	public int add(ENGeoDep2ENDepartment object) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeoDep2ENDepartment. Удалить */
	public void remove(int code) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENGeoDep2ENDepartment. Изменить */
	public void save(ENGeoDep2ENDepartment object) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeoDep2ENDepartment. Получить объект */
	public ENGeoDep2ENDepartment getObject(int code) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeoDep2ENDepartment. Получить полный список */
	public ENGeoDep2ENDepartmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENGeoDep2ENDepartment. Получить список по фильтру */
	public ENGeoDep2ENDepartmentShortList getFilteredList(
			ENGeoDep2ENDepartmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENGeoDep2ENDepartment. Получить список для просмотра */
	public ENGeoDep2ENDepartmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENGeoDep2ENDepartment. Получить список для просмотра по фильтру */
	public ENGeoDep2ENDepartmentShortList getScrollableFilteredList(
			ENGeoDep2ENDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeoDep2ENDepartment. Получить список для просмотра по условию */
	public ENGeoDep2ENDepartmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENGeoDep2ENDepartmentFilter filterObject = new ENGeoDep2ENDepartmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENGeoDep2ENDepartment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENGeoDep2ENDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeoDep2ENDepartment. Получить объект из списка */
	public ENGeoDep2ENDepartmentShort getShortObject(int code) {
		try {
			ENGeoDep2ENDepartmentDAO objectDAO = new ENGeoDep2ENDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeoDep2ENDepartment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
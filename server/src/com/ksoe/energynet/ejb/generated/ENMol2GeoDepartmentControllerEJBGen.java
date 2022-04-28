
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENMol2GeoDepartmentDAO;
import com.ksoe.energynet.valueobject.ENMol2GeoDepartment;
import com.ksoe.energynet.valueobject.lists.ENMol2GeoDepartmentShortList;
import com.ksoe.energynet.valueobject.brief.ENMol2GeoDepartmentShort;
import com.ksoe.energynet.valueobject.filter.ENMol2GeoDepartmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENMol2GeoDepartment;
 *
 */


public abstract class ENMol2GeoDepartmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENMol2GeoDepartmentControllerEJBGen() {
	}

	/* ENMol2GeoDepartment. Добавить */
	public int add(ENMol2GeoDepartment object) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMol2GeoDepartment. Удалить */
	public void remove(int code) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENMol2GeoDepartment. Изменить */
	public void save(ENMol2GeoDepartment object) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMol2GeoDepartment. Получить объект */
	public ENMol2GeoDepartment getObject(int code) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMol2GeoDepartment. Получить полный список */
	public ENMol2GeoDepartmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENMol2GeoDepartment. Получить список по фильтру */
	public ENMol2GeoDepartmentShortList getFilteredList(
			ENMol2GeoDepartmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENMol2GeoDepartment. Получить список для просмотра */
	public ENMol2GeoDepartmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENMol2GeoDepartment. Получить список для просмотра по фильтру */
	public ENMol2GeoDepartmentShortList getScrollableFilteredList(
			ENMol2GeoDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMol2GeoDepartment. Получить список для просмотра по условию */
	public ENMol2GeoDepartmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENMol2GeoDepartmentFilter filterObject = new ENMol2GeoDepartmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENMol2GeoDepartment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENMol2GeoDepartmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMol2GeoDepartment. Получить объект из списка */
	public ENMol2GeoDepartmentShort getShortObject(int code) {
		try {
			ENMol2GeoDepartmentDAO objectDAO = new ENMol2GeoDepartmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMol2GeoDepartment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDepartment2OrgDAO;
import com.ksoe.energynet.valueobject.ENDepartment2Org;
import com.ksoe.energynet.valueobject.lists.ENDepartment2OrgShortList;
import com.ksoe.energynet.valueobject.brief.ENDepartment2OrgShort;
import com.ksoe.energynet.valueobject.filter.ENDepartment2OrgFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDepartment2Org;
 *
 */


public abstract class ENDepartment2OrgControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDepartment2OrgControllerEJBGen() {
	}

	/* ENDepartment2Org. Добавить */
	public int add(ENDepartment2Org object) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDepartment2Org%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2Org. Удалить */
	public void remove(int code) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDepartment2Org%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDepartment2Org. Изменить */
	public void save(ENDepartment2Org object) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDepartment2Org%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2Org. Получить объект */
	public ENDepartment2Org getObject(int code) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDepartment2Org%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2Org. Получить полный список */
	public ENDepartment2OrgShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDepartment2Org. Получить список по фильтру */
	public ENDepartment2OrgShortList getFilteredList(
			ENDepartment2OrgFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDepartment2Org. Получить список для просмотра */
	public ENDepartment2OrgShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDepartment2Org. Получить список для просмотра по фильтру */
	public ENDepartment2OrgShortList getScrollableFilteredList(
			ENDepartment2OrgFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDepartment2Org%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2Org. Получить список для просмотра по условию */
	public ENDepartment2OrgShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDepartment2OrgFilter filterObject = new ENDepartment2OrgFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDepartment2Org. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDepartment2OrgFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDepartment2Org%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDepartment2Org. Получить объект из списка */
	public ENDepartment2OrgShort getShortObject(int code) {
		try {
			ENDepartment2OrgDAO objectDAO = new ENDepartment2OrgDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDepartment2Org%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
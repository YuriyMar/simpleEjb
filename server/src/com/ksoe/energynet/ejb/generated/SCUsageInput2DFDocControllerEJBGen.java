
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCUsageInput2DFDocDAO;
import com.ksoe.energynet.valueobject.SCUsageInput2DFDoc;
import com.ksoe.energynet.valueobject.lists.SCUsageInput2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.SCUsageInput2DFDocShort;
import com.ksoe.energynet.valueobject.filter.SCUsageInput2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCUsageInput2DFDoc;
 *
 */


public abstract class SCUsageInput2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCUsageInput2DFDocControllerEJBGen() {
	}

	/* SCUsageInput2DFDoc. Добавить */
	public int add(SCUsageInput2DFDoc object) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInput2DFDoc. Удалить */
	public void remove(int code) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCUsageInput2DFDoc. Изменить */
	public void save(SCUsageInput2DFDoc object) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInput2DFDoc. Получить объект */
	public SCUsageInput2DFDoc getObject(int code) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInput2DFDoc. Получить полный список */
	public SCUsageInput2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCUsageInput2DFDoc. Получить список по фильтру */
	public SCUsageInput2DFDocShortList getFilteredList(
			SCUsageInput2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCUsageInput2DFDoc. Получить список для просмотра */
	public SCUsageInput2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCUsageInput2DFDoc. Получить список для просмотра по фильтру */
	public SCUsageInput2DFDocShortList getScrollableFilteredList(
			SCUsageInput2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInput2DFDoc. Получить список для просмотра по условию */
	public SCUsageInput2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCUsageInput2DFDocFilter filterObject = new SCUsageInput2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCUsageInput2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCUsageInput2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInput2DFDoc. Получить объект из списка */
	public SCUsageInput2DFDocShort getShortObject(int code) {
		try {
			SCUsageInput2DFDocDAO objectDAO = new SCUsageInput2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCUsageInput2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
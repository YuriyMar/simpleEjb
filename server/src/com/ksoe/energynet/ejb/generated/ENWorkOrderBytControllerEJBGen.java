
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWorkOrderBytDAO;
import com.ksoe.energynet.valueobject.ENWorkOrderByt;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderBytShortList;
import com.ksoe.energynet.valueobject.brief.ENWorkOrderBytShort;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWorkOrderByt;
 *
 */


public abstract class ENWorkOrderBytControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWorkOrderBytControllerEJBGen() {
	}

	/* ENWorkOrderByt. Добавить */
	public int add(ENWorkOrderByt object) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderByt. Удалить */
	public void remove(int code) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWorkOrderByt. Изменить */
	public void save(ENWorkOrderByt object) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderByt. Получить объект */
	public ENWorkOrderByt getObject(int code) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderByt. Получить полный список */
	public ENWorkOrderBytShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWorkOrderByt. Получить список по фильтру */
	public ENWorkOrderBytShortList getFilteredList(
			ENWorkOrderBytFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWorkOrderByt. Получить список для просмотра */
	public ENWorkOrderBytShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWorkOrderByt. Получить список для просмотра по фильтру */
	public ENWorkOrderBytShortList getScrollableFilteredList(
			ENWorkOrderBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderByt. Получить список для просмотра по условию */
	public ENWorkOrderBytShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWorkOrderBytFilter filterObject = new ENWorkOrderBytFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWorkOrderByt. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWorkOrderBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderByt. Получить объект из списка */
	public ENWorkOrderBytShort getShortObject(int code) {
		try {
			ENWorkOrderBytDAO objectDAO = new ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderByt%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
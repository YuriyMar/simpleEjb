
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSeal2ENWorkOrderBytDAO;
import com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt;
import com.ksoe.energynet.valueobject.lists.SCSeal2ENWorkOrderBytShortList;
import com.ksoe.energynet.valueobject.brief.SCSeal2ENWorkOrderBytShort;
import com.ksoe.energynet.valueobject.filter.SCSeal2ENWorkOrderBytFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSeal2ENWorkOrderByt;
 *
 */


public abstract class SCSeal2ENWorkOrderBytControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSeal2ENWorkOrderBytControllerEJBGen() {
	}

	/* SCSeal2ENWorkOrderByt. Добавить */
	public int add(SCSeal2ENWorkOrderByt object) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2ENWorkOrderByt. Удалить */
	public void remove(int code) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSeal2ENWorkOrderByt. Изменить */
	public void save(SCSeal2ENWorkOrderByt object) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2ENWorkOrderByt. Получить объект */
	public SCSeal2ENWorkOrderByt getObject(int code) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2ENWorkOrderByt. Получить полный список */
	public SCSeal2ENWorkOrderBytShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSeal2ENWorkOrderByt. Получить список по фильтру */
	public SCSeal2ENWorkOrderBytShortList getFilteredList(
			SCSeal2ENWorkOrderBytFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSeal2ENWorkOrderByt. Получить список для просмотра */
	public SCSeal2ENWorkOrderBytShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSeal2ENWorkOrderByt. Получить список для просмотра по фильтру */
	public SCSeal2ENWorkOrderBytShortList getScrollableFilteredList(
			SCSeal2ENWorkOrderBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2ENWorkOrderByt. Получить список для просмотра по условию */
	public SCSeal2ENWorkOrderBytShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSeal2ENWorkOrderBytFilter filterObject = new SCSeal2ENWorkOrderBytFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSeal2ENWorkOrderByt. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSeal2ENWorkOrderBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2ENWorkOrderByt. Получить объект из списка */
	public SCSeal2ENWorkOrderBytShort getShortObject(int code) {
		try {
			SCSeal2ENWorkOrderBytDAO objectDAO = new SCSeal2ENWorkOrderBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal2ENWorkOrderByt%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
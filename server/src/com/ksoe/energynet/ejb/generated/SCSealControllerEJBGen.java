
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSealDAO;
import com.ksoe.energynet.valueobject.SCSeal;
import com.ksoe.energynet.valueobject.lists.SCSealShortList;
import com.ksoe.energynet.valueobject.brief.SCSealShort;
import com.ksoe.energynet.valueobject.filter.SCSealFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSeal;
 *
 */


public abstract class SCSealControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSealControllerEJBGen() {
	}

	/* SCSeal. Добавить */
	public int add(SCSeal object) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSeal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal. Удалить */
	public void remove(int code) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSeal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSeal. Изменить */
	public void save(SCSeal object) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSeal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal. Получить объект */
	public SCSeal getObject(int code) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal. Получить полный список */
	public SCSealShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSeal. Получить список по фильтру */
	public SCSealShortList getFilteredList(
			SCSealFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSeal. Получить список для просмотра */
	public SCSealShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSeal. Получить список для просмотра по фильтру */
	public SCSealShortList getScrollableFilteredList(
			SCSealFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSeal%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal. Получить список для просмотра по условию */
	public SCSealShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSealFilter filterObject = new SCSealFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSeal. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSealFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSeal%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal. Получить объект из списка */
	public SCSealShort getShortObject(int code) {
		try {
			SCSealDAO objectDAO = new SCSealDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
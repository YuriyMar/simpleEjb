
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSO2DistrAgreeDAO;
import com.ksoe.energynet.valueobject.ENSO2DistrAgree;
import com.ksoe.energynet.valueobject.lists.ENSO2DistrAgreeShortList;
import com.ksoe.energynet.valueobject.brief.ENSO2DistrAgreeShort;
import com.ksoe.energynet.valueobject.filter.ENSO2DistrAgreeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSO2DistrAgree;
 *
 */


public abstract class ENSO2DistrAgreeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSO2DistrAgreeControllerEJBGen() {
	}

	/* ENSO2DistrAgree. Добавить */
	public int add(ENSO2DistrAgree object) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2DistrAgree. Удалить */
	public void remove(int code) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSO2DistrAgree. Изменить */
	public void save(ENSO2DistrAgree object) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2DistrAgree. Получить объект */
	public ENSO2DistrAgree getObject(int code) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2DistrAgree. Получить полный список */
	public ENSO2DistrAgreeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSO2DistrAgree. Получить список по фильтру */
	public ENSO2DistrAgreeShortList getFilteredList(
			ENSO2DistrAgreeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSO2DistrAgree. Получить список для просмотра */
	public ENSO2DistrAgreeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSO2DistrAgree. Получить список для просмотра по фильтру */
	public ENSO2DistrAgreeShortList getScrollableFilteredList(
			ENSO2DistrAgreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2DistrAgree. Получить список для просмотра по условию */
	public ENSO2DistrAgreeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSO2DistrAgreeFilter filterObject = new ENSO2DistrAgreeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSO2DistrAgree. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSO2DistrAgreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2DistrAgree. Получить объект из списка */
	public ENSO2DistrAgreeShort getShortObject(int code) {
		try {
			ENSO2DistrAgreeDAO objectDAO = new ENSO2DistrAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2DistrAgree%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
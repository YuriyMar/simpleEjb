
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSO2SecObjectRespDAO;
import com.ksoe.energynet.valueobject.ENSO2SecObjectResp;
import com.ksoe.energynet.valueobject.lists.ENSO2SecObjectRespShortList;
import com.ksoe.energynet.valueobject.brief.ENSO2SecObjectRespShort;
import com.ksoe.energynet.valueobject.filter.ENSO2SecObjectRespFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSO2SecObjectResp;
 *
 */


public abstract class ENSO2SecObjectRespControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSO2SecObjectRespControllerEJBGen() {
	}

	/* ENSO2SecObjectResp. Добавить */
	public int add(ENSO2SecObjectResp object) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObjectResp. Удалить */
	public void remove(int code) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSO2SecObjectResp. Изменить */
	public void save(ENSO2SecObjectResp object) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObjectResp. Получить объект */
	public ENSO2SecObjectResp getObject(int code) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObjectResp. Получить полный список */
	public ENSO2SecObjectRespShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSO2SecObjectResp. Получить список по фильтру */
	public ENSO2SecObjectRespShortList getFilteredList(
			ENSO2SecObjectRespFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSO2SecObjectResp. Получить список для просмотра */
	public ENSO2SecObjectRespShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSO2SecObjectResp. Получить список для просмотра по фильтру */
	public ENSO2SecObjectRespShortList getScrollableFilteredList(
			ENSO2SecObjectRespFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObjectResp. Получить список для просмотра по условию */
	public ENSO2SecObjectRespShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSO2SecObjectRespFilter filterObject = new ENSO2SecObjectRespFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSO2SecObjectResp. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSO2SecObjectRespFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSO2SecObjectResp. Получить объект из списка */
	public ENSO2SecObjectRespShort getShortObject(int code) {
		try {
			ENSO2SecObjectRespDAO objectDAO = new ENSO2SecObjectRespDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSO2SecObjectResp%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
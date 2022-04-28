
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActFailureDAO;
import com.ksoe.energynet.valueobject.ENActFailure;
import com.ksoe.energynet.valueobject.lists.ENActFailureShortList;
import com.ksoe.energynet.valueobject.brief.ENActFailureShort;
import com.ksoe.energynet.valueobject.filter.ENActFailureFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActFailure;
 *
 */


public abstract class ENActFailureControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActFailureControllerEJBGen() {
	}

	/* ENActFailure. Добавить */
	public int add(ENActFailure object) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailure. Удалить */
	public void remove(int code) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActFailure. Изменить */
	public void save(ENActFailure object) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailure. Получить объект */
	public ENActFailure getObject(int code) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailure. Получить полный список */
	public ENActFailureShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActFailure. Получить список по фильтру */
	public ENActFailureShortList getFilteredList(
			ENActFailureFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActFailure. Получить список для просмотра */
	public ENActFailureShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActFailure. Получить список для просмотра по фильтру */
	public ENActFailureShortList getScrollableFilteredList(
			ENActFailureFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActFailure%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailure. Получить список для просмотра по условию */
	public ENActFailureShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActFailureFilter filterObject = new ENActFailureFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActFailure. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActFailureFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActFailure%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailure. Получить объект из списка */
	public ENActFailureShort getShortObject(int code) {
		try {
			ENActFailureDAO objectDAO = new ENActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActFailure%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
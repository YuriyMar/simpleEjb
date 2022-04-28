
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCountersStateVerificationDAO;
import com.ksoe.energynet.valueobject.ENCountersStateVerification;
import com.ksoe.energynet.valueobject.lists.ENCountersStateVerificationShortList;
import com.ksoe.energynet.valueobject.brief.ENCountersStateVerificationShort;
import com.ksoe.energynet.valueobject.filter.ENCountersStateVerificationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCountersStateVerification;
 *
 */


public abstract class ENCountersStateVerificationControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCountersStateVerificationControllerEJBGen() {
	}

	/* ENCountersStateVerification. Добавить */
	public int add(ENCountersStateVerification object) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCountersStateVerification. Удалить */
	public void remove(int code) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCountersStateVerification. Изменить */
	public void save(ENCountersStateVerification object) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCountersStateVerification. Получить объект */
	public ENCountersStateVerification getObject(int code) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCountersStateVerification. Получить полный список */
	public ENCountersStateVerificationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCountersStateVerification. Получить список по фильтру */
	public ENCountersStateVerificationShortList getFilteredList(
			ENCountersStateVerificationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCountersStateVerification. Получить список для просмотра */
	public ENCountersStateVerificationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCountersStateVerification. Получить список для просмотра по фильтру */
	public ENCountersStateVerificationShortList getScrollableFilteredList(
			ENCountersStateVerificationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCountersStateVerification. Получить список для просмотра по условию */
	public ENCountersStateVerificationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCountersStateVerificationFilter filterObject = new ENCountersStateVerificationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCountersStateVerification. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCountersStateVerificationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCountersStateVerification. Получить объект из списка */
	public ENCountersStateVerificationShort getShortObject(int code) {
		try {
			ENCountersStateVerificationDAO objectDAO = new ENCountersStateVerificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCountersStateVerification%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
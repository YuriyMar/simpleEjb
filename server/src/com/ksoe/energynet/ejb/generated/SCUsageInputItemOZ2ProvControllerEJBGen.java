
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCUsageInputItemOZ2ProvDAO;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZ2ProvShortList;
import com.ksoe.energynet.valueobject.brief.SCUsageInputItemOZ2ProvShort;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZ2ProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCUsageInputItemOZ2Prov;
 *
 */


public abstract class SCUsageInputItemOZ2ProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCUsageInputItemOZ2ProvControllerEJBGen() {
	}

	/* SCUsageInputItemOZ2Prov. �������� */
	public int add(SCUsageInputItemOZ2Prov object) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInputItemOZ2Prov. ������� */
	public void remove(int code) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCUsageInputItemOZ2Prov. �������� */
	public void save(SCUsageInputItemOZ2Prov object) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ */
	public SCUsageInputItemOZ2Prov getObject(int code) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ ������ */
	public SCUsageInputItemOZ2ProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ �� ������� */
	public SCUsageInputItemOZ2ProvShortList getFilteredList(
			SCUsageInputItemOZ2ProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ ��� ��������� */
	public SCUsageInputItemOZ2ProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ ��� ��������� �� ������� */
	public SCUsageInputItemOZ2ProvShortList getScrollableFilteredList(
			SCUsageInputItemOZ2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ ��� ��������� �� ������� */
	public SCUsageInputItemOZ2ProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCUsageInputItemOZ2ProvFilter filterObject = new SCUsageInputItemOZ2ProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			SCUsageInputItemOZ2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCUsageInputItemOZ2Prov. �������� ������ �� ������ */
	public SCUsageInputItemOZ2ProvShort getShortObject(int code) {
		try {
			SCUsageInputItemOZ2ProvDAO objectDAO = new SCUsageInputItemOZ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2Prov%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
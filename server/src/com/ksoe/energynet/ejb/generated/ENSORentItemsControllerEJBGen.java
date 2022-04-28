
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSORentItemsDAO;
import com.ksoe.energynet.valueobject.ENSORentItems;
import com.ksoe.energynet.valueobject.lists.ENSORentItemsShortList;
import com.ksoe.energynet.valueobject.brief.ENSORentItemsShort;
import com.ksoe.energynet.valueobject.filter.ENSORentItemsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSORentItems;
 *
 */


public abstract class ENSORentItemsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSORentItemsControllerEJBGen() {
	}

	/* ENSORentItems. �������� */
	public int add(ENSORentItems object) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSORentItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORentItems. ������� */
	public void remove(int code) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSORentItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSORentItems. �������� */
	public void save(ENSORentItems object) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSORentItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORentItems. �������� ������ */
	public ENSORentItems getObject(int code) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORentItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORentItems. �������� ������ ������ */
	public ENSORentItemsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSORentItems. �������� ������ �� ������� */
	public ENSORentItemsShortList getFilteredList(
			ENSORentItemsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSORentItems. �������� ������ ��� ��������� */
	public ENSORentItemsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSORentItems. �������� ������ ��� ��������� �� ������� */
	public ENSORentItemsShortList getScrollableFilteredList(
			ENSORentItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSORentItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORentItems. �������� ������ ��� ��������� �� ������� */
	public ENSORentItemsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSORentItemsFilter filterObject = new ENSORentItemsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSORentItems. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSORentItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSORentItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORentItems. �������� ������ �� ������ */
	public ENSORentItemsShort getShortObject(int code) {
		try {
			ENSORentItemsDAO objectDAO = new ENSORentItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORentItems%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
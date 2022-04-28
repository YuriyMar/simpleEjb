
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSizObjectDAO;
import com.ksoe.energynet.valueobject.ENSizObject;
import com.ksoe.energynet.valueobject.lists.ENSizObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENSizObjectShort;
import com.ksoe.energynet.valueobject.filter.ENSizObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSizObject;
 *
 */


public abstract class ENSizObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSizObjectControllerEJBGen() {
	}

	/* ENSizObject. �������� */
	public int add(ENSizObject object) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSizObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizObject. ������� */
	public void remove(int code) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSizObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSizObject. �������� */
	public void save(ENSizObject object) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSizObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizObject. �������� ������ */
	public ENSizObject getObject(int code) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSizObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizObject. �������� ������ ������ */
	public ENSizObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSizObject. �������� ������ �� ������� */
	public ENSizObjectShortList getFilteredList(
			ENSizObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSizObject. �������� ������ ��� ��������� */
	public ENSizObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSizObject. �������� ������ ��� ��������� �� ������� */
	public ENSizObjectShortList getScrollableFilteredList(
			ENSizObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSizObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizObject. �������� ������ ��� ��������� �� ������� */
	public ENSizObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSizObjectFilter filterObject = new ENSizObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSizObject. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSizObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSizObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizObject. �������� ������ �� ������ */
	public ENSizObjectShort getShortObject(int code) {
		try {
			ENSizObjectDAO objectDAO = new ENSizObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSizObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
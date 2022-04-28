
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWarrantDAO;
import com.ksoe.energynet.valueobject.ENWarrant;
import com.ksoe.energynet.valueobject.lists.ENWarrantShortList;
import com.ksoe.energynet.valueobject.brief.ENWarrantShort;
import com.ksoe.energynet.valueobject.filter.ENWarrantFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWarrant;
 *
 */


public abstract class ENWarrantControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWarrantControllerEJBGen() {
	}

	/* ENWarrant. �������� */
	public int add(ENWarrant object) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant. ������� */
	public void remove(int code) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWarrant. �������� */
	public void save(ENWarrant object) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant. �������� ������ */
	public ENWarrant getObject(int code) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant. �������� ������ ������ */
	public ENWarrantShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWarrant. �������� ������ �� ������� */
	public ENWarrantShortList getFilteredList(
			ENWarrantFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWarrant. �������� ������ ��� ��������� */
	public ENWarrantShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWarrant. �������� ������ ��� ��������� �� ������� */
	public ENWarrantShortList getScrollableFilteredList(
			ENWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant. �������� ������ ��� ��������� �� ������� */
	public ENWarrantShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWarrantFilter filterObject = new ENWarrantFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWarrant. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrant. �������� ������ �� ������ */
	public ENWarrantShort getShortObject(int code) {
		try {
			ENWarrantDAO objectDAO = new ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrant%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRentPeriodStatusDAO;
import com.ksoe.energynet.valueobject.ENRentPeriodStatus;
import com.ksoe.energynet.valueobject.lists.ENRentPeriodStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENRentPeriodStatusShort;
import com.ksoe.energynet.valueobject.filter.ENRentPeriodStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRentPeriodStatus;
 *
 */


public abstract class ENRentPeriodStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRentPeriodStatusControllerEJBGen() {
	}

	/* ENRentPeriodStatus. �������� */
	public int add(ENRentPeriodStatus object) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriodStatus. ������� */
	public void remove(int code) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRentPeriodStatus. �������� */
	public void save(ENRentPeriodStatus object) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriodStatus. �������� ������ */
	public ENRentPeriodStatus getObject(int code) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriodStatus. �������� ������ ������ */
	public ENRentPeriodStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRentPeriodStatus. �������� ������ �� ������� */
	public ENRentPeriodStatusShortList getFilteredList(
			ENRentPeriodStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRentPeriodStatus. �������� ������ ��� ��������� */
	public ENRentPeriodStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRentPeriodStatus. �������� ������ ��� ��������� �� ������� */
	public ENRentPeriodStatusShortList getScrollableFilteredList(
			ENRentPeriodStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriodStatus. �������� ������ ��� ��������� �� ������� */
	public ENRentPeriodStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRentPeriodStatusFilter filterObject = new ENRentPeriodStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRentPeriodStatus. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENRentPeriodStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriodStatus. �������� ������ �� ������ */
	public ENRentPeriodStatusShort getShortObject(int code) {
		try {
			ENRentPeriodStatusDAO objectDAO = new ENRentPeriodStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRentPeriodStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
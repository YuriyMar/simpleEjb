
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENEstimateItemPlanPayDAO;
import com.ksoe.energynet.valueobject.ENEstimateItemPlanPay;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemPlanPayShortList;
import com.ksoe.energynet.valueobject.brief.ENEstimateItemPlanPayShort;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemPlanPayFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENEstimateItemPlanPay;
 *
 */


public abstract class ENEstimateItemPlanPayControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENEstimateItemPlanPayControllerEJBGen() {
	}

	/* ENEstimateItemPlanPay. �������� */
	public int add(ENEstimateItemPlanPay object) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItemPlanPay. ������� */
	public void remove(int code) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENEstimateItemPlanPay. �������� */
	public void save(ENEstimateItemPlanPay object) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItemPlanPay. �������� ������ */
	public ENEstimateItemPlanPay getObject(int code) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItemPlanPay. �������� ������ ������ */
	public ENEstimateItemPlanPayShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENEstimateItemPlanPay. �������� ������ �� ������� */
	public ENEstimateItemPlanPayShortList getFilteredList(
			ENEstimateItemPlanPayFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENEstimateItemPlanPay. �������� ������ ��� ��������� */
	public ENEstimateItemPlanPayShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENEstimateItemPlanPay. �������� ������ ��� ��������� �� ������� */
	public ENEstimateItemPlanPayShortList getScrollableFilteredList(
			ENEstimateItemPlanPayFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItemPlanPay. �������� ������ ��� ��������� �� ������� */
	public ENEstimateItemPlanPayShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENEstimateItemPlanPayFilter filterObject = new ENEstimateItemPlanPayFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENEstimateItemPlanPay. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENEstimateItemPlanPayFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItemPlanPay. �������� ������ �� ������ */
	public ENEstimateItemPlanPayShort getShortObject(int code) {
		try {
			ENEstimateItemPlanPayDAO objectDAO = new ENEstimateItemPlanPayDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItemPlanPay%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
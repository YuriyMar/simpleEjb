
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCoefficientExecPlanDAO;
import com.ksoe.energynet.valueobject.ENCoefficientExecPlan;
import com.ksoe.energynet.valueobject.lists.ENCoefficientExecPlanShortList;
import com.ksoe.energynet.valueobject.brief.ENCoefficientExecPlanShort;
import com.ksoe.energynet.valueobject.filter.ENCoefficientExecPlanFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCoefficientExecPlan;
 *
 */


public abstract class ENCoefficientExecPlanControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCoefficientExecPlanControllerEJBGen() {
	}

	/* ENCoefficientExecPlan. �������� */
	public int add(ENCoefficientExecPlan object) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientExecPlan. ������� */
	public void remove(int code) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCoefficientExecPlan. �������� */
	public void save(ENCoefficientExecPlan object) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientExecPlan. �������� ������ */
	public ENCoefficientExecPlan getObject(int code) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientExecPlan. �������� ������ ������ */
	public ENCoefficientExecPlanShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCoefficientExecPlan. �������� ������ �� ������� */
	public ENCoefficientExecPlanShortList getFilteredList(
			ENCoefficientExecPlanFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCoefficientExecPlan. �������� ������ ��� ��������� */
	public ENCoefficientExecPlanShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCoefficientExecPlan. �������� ������ ��� ��������� �� ������� */
	public ENCoefficientExecPlanShortList getScrollableFilteredList(
			ENCoefficientExecPlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientExecPlan. �������� ������ ��� ��������� �� ������� */
	public ENCoefficientExecPlanShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCoefficientExecPlanFilter filterObject = new ENCoefficientExecPlanFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCoefficientExecPlan. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENCoefficientExecPlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientExecPlan. �������� ������ �� ������ */
	public ENCoefficientExecPlanShort getShortObject(int code) {
		try {
			ENCoefficientExecPlanDAO objectDAO = new ENCoefficientExecPlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCoefficientExecPlan%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
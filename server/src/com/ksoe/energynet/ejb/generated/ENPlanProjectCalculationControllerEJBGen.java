
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanProjectCalculationDAO;
import com.ksoe.energynet.valueobject.ENPlanProjectCalculation;
import com.ksoe.energynet.valueobject.lists.ENPlanProjectCalculationShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanProjectCalculationShort;
import com.ksoe.energynet.valueobject.filter.ENPlanProjectCalculationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanProjectCalculation;
 *
 */


public abstract class ENPlanProjectCalculationControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanProjectCalculationControllerEJBGen() {
	}

	/* ENPlanProjectCalculation. �������� */
	public int add(ENPlanProjectCalculation object) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectCalculation. ������� */
	public void remove(int code) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanProjectCalculation. �������� */
	public void save(ENPlanProjectCalculation object) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectCalculation. �������� ������ */
	public ENPlanProjectCalculation getObject(int code) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectCalculation. �������� ������ ������ */
	public ENPlanProjectCalculationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanProjectCalculation. �������� ������ �� ������� */
	public ENPlanProjectCalculationShortList getFilteredList(
			ENPlanProjectCalculationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanProjectCalculation. �������� ������ ��� ��������� */
	public ENPlanProjectCalculationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanProjectCalculation. �������� ������ ��� ��������� �� ������� */
	public ENPlanProjectCalculationShortList getScrollableFilteredList(
			ENPlanProjectCalculationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectCalculation. �������� ������ ��� ��������� �� ������� */
	public ENPlanProjectCalculationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanProjectCalculationFilter filterObject = new ENPlanProjectCalculationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanProjectCalculation. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENPlanProjectCalculationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectCalculation. �������� ������ �� ������ */
	public ENPlanProjectCalculationShort getShortObject(int code) {
		try {
			ENPlanProjectCalculationDAO objectDAO = new ENPlanProjectCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProjectCalculation%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
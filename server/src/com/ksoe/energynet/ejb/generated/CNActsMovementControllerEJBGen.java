
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.CNActsMovementDAO;
import com.ksoe.energynet.valueobject.CNActsMovement;
import com.ksoe.energynet.valueobject.lists.CNActsMovementShortList;
import com.ksoe.energynet.valueobject.brief.CNActsMovementShort;
import com.ksoe.energynet.valueobject.filter.CNActsMovementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for CNActsMovement;
 *
 */


public abstract class CNActsMovementControllerEJBGen extends
		GenericSessionStatelessBean {
	public CNActsMovementControllerEJBGen() {
	}

	/* CNActsMovement. �������� */
	public int add(CNActsMovement object) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.CNActsMovement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsMovement. ������� */
	public void remove(int code) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.CNActsMovement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* CNActsMovement. �������� */
	public void save(CNActsMovement object) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.CNActsMovement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsMovement. �������� ������ */
	public CNActsMovement getObject(int code) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNActsMovement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsMovement. �������� ������ ������ */
	public CNActsMovementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* CNActsMovement. �������� ������ �� ������� */
	public CNActsMovementShortList getFilteredList(
			CNActsMovementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* CNActsMovement. �������� ������ ��� ��������� */
	public CNActsMovementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* CNActsMovement. �������� ������ ��� ��������� �� ������� */
	public CNActsMovementShortList getScrollableFilteredList(
			CNActsMovementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.CNActsMovement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsMovement. �������� ������ ��� ��������� �� ������� */
	public CNActsMovementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		CNActsMovementFilter filterObject = new CNActsMovementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* CNActsMovement. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			CNActsMovementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.CNActsMovement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsMovement. �������� ������ �� ������ */
	public CNActsMovementShort getShortObject(int code) {
		try {
			CNActsMovementDAO objectDAO = new CNActsMovementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNActsMovement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
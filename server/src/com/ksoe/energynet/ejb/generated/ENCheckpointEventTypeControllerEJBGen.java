
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCheckpointEventTypeDAO;
import com.ksoe.energynet.valueobject.ENCheckpointEventType;
import com.ksoe.energynet.valueobject.lists.ENCheckpointEventTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENCheckpointEventTypeShort;
import com.ksoe.energynet.valueobject.filter.ENCheckpointEventTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCheckpointEventType;
 *
 */


public abstract class ENCheckpointEventTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCheckpointEventTypeControllerEJBGen() {
	}

	/* ENCheckpointEventType. �������� */
	public int add(ENCheckpointEventType object) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointEventType. ������� */
	public void remove(int code) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCheckpointEventType. �������� */
	public void save(ENCheckpointEventType object) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointEventType. �������� ������ */
	public ENCheckpointEventType getObject(int code) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointEventType. �������� ������ ������ */
	public ENCheckpointEventTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCheckpointEventType. �������� ������ �� ������� */
	public ENCheckpointEventTypeShortList getFilteredList(
			ENCheckpointEventTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCheckpointEventType. �������� ������ ��� ��������� */
	public ENCheckpointEventTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCheckpointEventType. �������� ������ ��� ��������� �� ������� */
	public ENCheckpointEventTypeShortList getScrollableFilteredList(
			ENCheckpointEventTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointEventType. �������� ������ ��� ��������� �� ������� */
	public ENCheckpointEventTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCheckpointEventTypeFilter filterObject = new ENCheckpointEventTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCheckpointEventType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENCheckpointEventTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCheckpointEventType. �������� ������ �� ������ */
	public ENCheckpointEventTypeShort getShortObject(int code) {
		try {
			ENCheckpointEventTypeDAO objectDAO = new ENCheckpointEventTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCheckpointEventType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
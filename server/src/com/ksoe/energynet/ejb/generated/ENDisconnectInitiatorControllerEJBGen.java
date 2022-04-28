
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDisconnectInitiatorDAO;
import com.ksoe.energynet.valueobject.ENDisconnectInitiator;
import com.ksoe.energynet.valueobject.lists.ENDisconnectInitiatorShortList;
import com.ksoe.energynet.valueobject.brief.ENDisconnectInitiatorShort;
import com.ksoe.energynet.valueobject.filter.ENDisconnectInitiatorFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDisconnectInitiator;
 *
 */


public abstract class ENDisconnectInitiatorControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDisconnectInitiatorControllerEJBGen() {
	}

	/* ENDisconnectInitiator. �������� */
	public int add(ENDisconnectInitiator object) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. ������� */
	public void remove(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDisconnectInitiator. �������� */
	public void save(ENDisconnectInitiator object) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. �������� ������ */
	public ENDisconnectInitiator getObject(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. �������� ������ ������ */
	public ENDisconnectInitiatorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDisconnectInitiator. �������� ������ �� ������� */
	public ENDisconnectInitiatorShortList getFilteredList(
			ENDisconnectInitiatorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDisconnectInitiator. �������� ������ ��� ��������� */
	public ENDisconnectInitiatorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDisconnectInitiator. �������� ������ ��� ��������� �� ������� */
	public ENDisconnectInitiatorShortList getScrollableFilteredList(
			ENDisconnectInitiatorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. �������� ������ ��� ��������� �� ������� */
	public ENDisconnectInitiatorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDisconnectInitiatorFilter filterObject = new ENDisconnectInitiatorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDisconnectInitiator. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENDisconnectInitiatorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. �������� ������ �� ������ */
	public ENDisconnectInitiatorShort getShortObject(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
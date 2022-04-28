
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelInventarizationStatusDAO;
import com.ksoe.energynet.valueobject.ENFuelInventarizationStatus;
import com.ksoe.energynet.valueobject.lists.ENFuelInventarizationStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelInventarizationStatusShort;
import com.ksoe.energynet.valueobject.filter.ENFuelInventarizationStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelInventarizationStatus;
 *
 */


public abstract class ENFuelInventarizationStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelInventarizationStatusControllerEJBGen() {
	}

	/* ENFuelInventarizationStatus. �������� */
	public int add(ENFuelInventarizationStatus object) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationStatus. ������� */
	public void remove(int code) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelInventarizationStatus. �������� */
	public void save(ENFuelInventarizationStatus object) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationStatus. �������� ������ */
	public ENFuelInventarizationStatus getObject(int code) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationStatus. �������� ������ ������ */
	public ENFuelInventarizationStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelInventarizationStatus. �������� ������ �� ������� */
	public ENFuelInventarizationStatusShortList getFilteredList(
			ENFuelInventarizationStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelInventarizationStatus. �������� ������ ��� ��������� */
	public ENFuelInventarizationStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelInventarizationStatus. �������� ������ ��� ��������� �� ������� */
	public ENFuelInventarizationStatusShortList getScrollableFilteredList(
			ENFuelInventarizationStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationStatus. �������� ������ ��� ��������� �� ������� */
	public ENFuelInventarizationStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelInventarizationStatusFilter filterObject = new ENFuelInventarizationStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelInventarizationStatus. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENFuelInventarizationStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationStatus. �������� ������ �� ������ */
	public ENFuelInventarizationStatusShort getShortObject(int code) {
		try {
			ENFuelInventarizationStatusDAO objectDAO = new ENFuelInventarizationStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarizationStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
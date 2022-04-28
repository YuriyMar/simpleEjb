
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENFuelSheetVolumesStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus;
import com.ksoe.energynet.valueobject.brief.ENFuelSheetVolumesStatusShort;
import com.ksoe.energynet.valueobject.filter.ENFuelSheetVolumesStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENFuelSheetVolumesStatusShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelSheetVolumesStatus;
 *
 */


public abstract class ENFuelSheetVolumesStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelSheetVolumesStatusControllerEJBGen() {
	}

	/* ENFuelSheetVolumesStatus. �������� */
	public int add(ENFuelSheetVolumesStatus object) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesStatus. ������� */
	public void remove(int code) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelSheetVolumesStatus. �������� */
	public void save(ENFuelSheetVolumesStatus object) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesStatus. �������� ������ */
	public ENFuelSheetVolumesStatus getObject(int code) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesStatus. �������� ������ ������ */
	public ENFuelSheetVolumesStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelSheetVolumesStatus. �������� ������ �� ������� */
	public ENFuelSheetVolumesStatusShortList getFilteredList(
			ENFuelSheetVolumesStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelSheetVolumesStatus. �������� ������ ��� ��������� */
	public ENFuelSheetVolumesStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelSheetVolumesStatus. �������� ������ ��� ��������� �� ������� */
	public ENFuelSheetVolumesStatusShortList getScrollableFilteredList(
			ENFuelSheetVolumesStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesStatus. �������� ������ ��� ��������� �� ������� */
	public ENFuelSheetVolumesStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelSheetVolumesStatusFilter filterObject = new ENFuelSheetVolumesStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelSheetVolumesStatus. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENFuelSheetVolumesStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesStatus. �������� ������ �� ������ */
	public ENFuelSheetVolumesStatusShort getShortObject(int code) {
		try {
			ENFuelSheetVolumesStatusDAO objectDAO = new ENFuelSheetVolumesStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
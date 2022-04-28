
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDelayServicesDAO;
import com.ksoe.energynet.valueobject.ENDelayServices;
import com.ksoe.energynet.valueobject.lists.ENDelayServicesShortList;
import com.ksoe.energynet.valueobject.brief.ENDelayServicesShort;
import com.ksoe.energynet.valueobject.filter.ENDelayServicesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDelayServices;
 *
 */


public abstract class ENDelayServicesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDelayServicesControllerEJBGen() {
	}

	/* ENDelayServices. �������� */
	public int add(ENDelayServices object) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDelayServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDelayServices. ������� */
	public void remove(int code) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDelayServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDelayServices. �������� */
	public void save(ENDelayServices object) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDelayServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDelayServices. �������� ������ */
	public ENDelayServices getObject(int code) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDelayServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDelayServices. �������� ������ ������ */
	public ENDelayServicesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDelayServices. �������� ������ �� ������� */
	public ENDelayServicesShortList getFilteredList(
			ENDelayServicesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDelayServices. �������� ������ ��� ��������� */
	public ENDelayServicesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDelayServices. �������� ������ ��� ��������� �� ������� */
	public ENDelayServicesShortList getScrollableFilteredList(
			ENDelayServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDelayServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDelayServices. �������� ������ ��� ��������� �� ������� */
	public ENDelayServicesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDelayServicesFilter filterObject = new ENDelayServicesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDelayServices. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENDelayServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDelayServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDelayServices. �������� ������ �� ������ */
	public ENDelayServicesShort getShortObject(int code) {
		try {
			ENDelayServicesDAO objectDAO = new ENDelayServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDelayServices%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
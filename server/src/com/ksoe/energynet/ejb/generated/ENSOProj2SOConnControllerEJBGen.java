
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOProj2SOConnDAO;
import com.ksoe.energynet.valueobject.ENSOProj2SOConn;
import com.ksoe.energynet.valueobject.lists.ENSOProj2SOConnShortList;
import com.ksoe.energynet.valueobject.brief.ENSOProj2SOConnShort;
import com.ksoe.energynet.valueobject.filter.ENSOProj2SOConnFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOProj2SOConn;
 *
 */


public abstract class ENSOProj2SOConnControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOProj2SOConnControllerEJBGen() {
	}

	/* ENSOProj2SOConn. �������� */
	public int add(ENSOProj2SOConn object) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOProj2SOConn. ������� */
	public void remove(int code) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOProj2SOConn. �������� */
	public void save(ENSOProj2SOConn object) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOProj2SOConn. �������� ������ */
	public ENSOProj2SOConn getObject(int code) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOProj2SOConn. �������� ������ ������ */
	public ENSOProj2SOConnShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOProj2SOConn. �������� ������ �� ������� */
	public ENSOProj2SOConnShortList getFilteredList(
			ENSOProj2SOConnFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOProj2SOConn. �������� ������ ��� ��������� */
	public ENSOProj2SOConnShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOProj2SOConn. �������� ������ ��� ��������� �� ������� */
	public ENSOProj2SOConnShortList getScrollableFilteredList(
			ENSOProj2SOConnFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOProj2SOConn. �������� ������ ��� ��������� �� ������� */
	public ENSOProj2SOConnShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOProj2SOConnFilter filterObject = new ENSOProj2SOConnFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOProj2SOConn. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSOProj2SOConnFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOProj2SOConn. �������� ������ �� ������ */
	public ENSOProj2SOConnShort getShortObject(int code) {
		try {
			ENSOProj2SOConnDAO objectDAO = new ENSOProj2SOConnDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOProj2SOConn%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
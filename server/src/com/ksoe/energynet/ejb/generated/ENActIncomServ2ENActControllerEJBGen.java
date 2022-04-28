
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncomServ2ENActDAO;
import com.ksoe.energynet.valueobject.ENActIncomServ2ENAct;
import com.ksoe.energynet.valueobject.lists.ENActIncomServ2ENActShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncomServ2ENActShort;
import com.ksoe.energynet.valueobject.filter.ENActIncomServ2ENActFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncomServ2ENAct;
 *
 */


public abstract class ENActIncomServ2ENActControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncomServ2ENActControllerEJBGen() {
	}

	/* ENActIncomServ2ENAct. �������� */
	public int add(ENActIncomServ2ENAct object) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2ENAct. ������� */
	public void remove(int code) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncomServ2ENAct. �������� */
	public void save(ENActIncomServ2ENAct object) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2ENAct. �������� ������ */
	public ENActIncomServ2ENAct getObject(int code) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2ENAct. �������� ������ ������ */
	public ENActIncomServ2ENActShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncomServ2ENAct. �������� ������ �� ������� */
	public ENActIncomServ2ENActShortList getFilteredList(
			ENActIncomServ2ENActFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncomServ2ENAct. �������� ������ ��� ��������� */
	public ENActIncomServ2ENActShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncomServ2ENAct. �������� ������ ��� ��������� �� ������� */
	public ENActIncomServ2ENActShortList getScrollableFilteredList(
			ENActIncomServ2ENActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2ENAct. �������� ������ ��� ��������� �� ������� */
	public ENActIncomServ2ENActShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncomServ2ENActFilter filterObject = new ENActIncomServ2ENActFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncomServ2ENAct. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENActIncomServ2ENActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2ENAct. �������� ������ �� ������ */
	public ENActIncomServ2ENActShort getShortObject(int code) {
		try {
			ENActIncomServ2ENActDAO objectDAO = new ENActIncomServ2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomServ2ENAct%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
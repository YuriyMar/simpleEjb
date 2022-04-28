
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInvestObjectTypeDAO;
import com.ksoe.energynet.valueobject.ENInvestObjectType;
import com.ksoe.energynet.valueobject.lists.ENInvestObjectTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENInvestObjectTypeShort;
import com.ksoe.energynet.valueobject.filter.ENInvestObjectTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInvestObjectType;
 *
 */


public abstract class ENInvestObjectTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInvestObjectTypeControllerEJBGen() {
	}

	/* ENInvestObjectType. �������� */
	public int add(ENInvestObjectType object) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInvestObjectType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestObjectType. ������� */
	public void remove(int code) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInvestObjectType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInvestObjectType. �������� */
	public void save(ENInvestObjectType object) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInvestObjectType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestObjectType. �������� ������ */
	public ENInvestObjectType getObject(int code) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestObjectType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestObjectType. �������� ������ ������ */
	public ENInvestObjectTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInvestObjectType. �������� ������ �� ������� */
	public ENInvestObjectTypeShortList getFilteredList(
			ENInvestObjectTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInvestObjectType. �������� ������ ��� ��������� */
	public ENInvestObjectTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInvestObjectType. �������� ������ ��� ��������� �� ������� */
	public ENInvestObjectTypeShortList getScrollableFilteredList(
			ENInvestObjectTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInvestObjectType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestObjectType. �������� ������ ��� ��������� �� ������� */
	public ENInvestObjectTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInvestObjectTypeFilter filterObject = new ENInvestObjectTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInvestObjectType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENInvestObjectTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestObjectType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestObjectType. �������� ������ �� ������ */
	public ENInvestObjectTypeShort getShortObject(int code) {
		try {
			ENInvestObjectTypeDAO objectDAO = new ENInvestObjectTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestObjectType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
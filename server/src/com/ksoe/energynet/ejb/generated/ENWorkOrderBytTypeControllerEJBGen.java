
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWorkOrderBytTypeDAO;
import com.ksoe.energynet.valueobject.ENWorkOrderBytType;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderBytTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENWorkOrderBytTypeShort;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWorkOrderBytType;
 *
 */


public abstract class ENWorkOrderBytTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWorkOrderBytTypeControllerEJBGen() {
	}

	/* ENWorkOrderBytType. �������� */
	public int add(ENWorkOrderBytType object) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytType. ������� */
	public void remove(int code) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWorkOrderBytType. �������� */
	public void save(ENWorkOrderBytType object) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytType. �������� ������ */
	public ENWorkOrderBytType getObject(int code) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytType. �������� ������ ������ */
	public ENWorkOrderBytTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWorkOrderBytType. �������� ������ �� ������� */
	public ENWorkOrderBytTypeShortList getFilteredList(
			ENWorkOrderBytTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWorkOrderBytType. �������� ������ ��� ��������� */
	public ENWorkOrderBytTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWorkOrderBytType. �������� ������ ��� ��������� �� ������� */
	public ENWorkOrderBytTypeShortList getScrollableFilteredList(
			ENWorkOrderBytTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytType. �������� ������ ��� ��������� �� ������� */
	public ENWorkOrderBytTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWorkOrderBytTypeFilter filterObject = new ENWorkOrderBytTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWorkOrderBytType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENWorkOrderBytTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytType. �������� ������ �� ������ */
	public ENWorkOrderBytTypeShort getShortObject(int code) {
		try {
			ENWorkOrderBytTypeDAO objectDAO = new ENWorkOrderBytTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
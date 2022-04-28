
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRegForSupplierTypeDAO;
import com.ksoe.energynet.valueobject.ENRegForSupplierType;
import com.ksoe.energynet.valueobject.lists.ENRegForSupplierTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENRegForSupplierTypeShort;
import com.ksoe.energynet.valueobject.filter.ENRegForSupplierTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRegForSupplierType;
 *
 */


public abstract class ENRegForSupplierTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRegForSupplierTypeControllerEJBGen() {
	}

	/* ENRegForSupplierType. �������� */
	public int add(ENRegForSupplierType object) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. ������� */
	public void remove(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRegForSupplierType. �������� */
	public void save(ENRegForSupplierType object) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. �������� ������ */
	public ENRegForSupplierType getObject(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. �������� ������ ������ */
	public ENRegForSupplierTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRegForSupplierType. �������� ������ �� ������� */
	public ENRegForSupplierTypeShortList getFilteredList(
			ENRegForSupplierTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRegForSupplierType. �������� ������ ��� ��������� */
	public ENRegForSupplierTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRegForSupplierType. �������� ������ ��� ��������� �� ������� */
	public ENRegForSupplierTypeShortList getScrollableFilteredList(
			ENRegForSupplierTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. �������� ������ ��� ��������� �� ������� */
	public ENRegForSupplierTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRegForSupplierTypeFilter filterObject = new ENRegForSupplierTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRegForSupplierType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENRegForSupplierTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRegForSupplierType. �������� ������ �� ������ */
	public ENRegForSupplierTypeShort getShortObject(int code) {
		try {
			ENRegForSupplierTypeDAO objectDAO = new ENRegForSupplierTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRegForSupplierType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
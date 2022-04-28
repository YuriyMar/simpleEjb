
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSignerDAO;
import com.ksoe.energynet.valueobject.ENSigner;
import com.ksoe.energynet.valueobject.lists.ENSignerShortList;
import com.ksoe.energynet.valueobject.brief.ENSignerShort;
import com.ksoe.energynet.valueobject.filter.ENSignerFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSigner;
 *
 */


public abstract class ENSignerControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSignerControllerEJBGen() {
	}

	/* ENSigner. �������� */
	public int add(ENSigner object) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSigner%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSigner. ������� */
	public void remove(int code) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSigner%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSigner. �������� */
	public void save(ENSigner object) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSigner%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSigner. �������� ������ */
	public ENSigner getObject(int code) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSigner%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSigner. �������� ������ ������ */
	public ENSignerShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSigner. �������� ������ �� ������� */
	public ENSignerShortList getFilteredList(
			ENSignerFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSigner. �������� ������ ��� ��������� */
	public ENSignerShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSigner. �������� ������ ��� ��������� �� ������� */
	public ENSignerShortList getScrollableFilteredList(
			ENSignerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSigner%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSigner. �������� ������ ��� ��������� �� ������� */
	public ENSignerShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSignerFilter filterObject = new ENSignerFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSigner. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSignerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSigner%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSigner. �������� ������ �� ������ */
	public ENSignerShort getShortObject(int code) {
		try {
			ENSignerDAO objectDAO = new ENSignerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSigner%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
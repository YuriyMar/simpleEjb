
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSealNamesDAO;
import com.ksoe.energynet.valueobject.ENSealNames;
import com.ksoe.energynet.valueobject.lists.ENSealNamesShortList;
import com.ksoe.energynet.valueobject.brief.ENSealNamesShort;
import com.ksoe.energynet.valueobject.filter.ENSealNamesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSealNames;
 *
 */


public abstract class ENSealNamesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSealNamesControllerEJBGen() {
	}

	/* ENSealNames. �������� */
	public int add(ENSealNames object) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSealNames%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealNames. ������� */
	public void remove(int code) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSealNames%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSealNames. �������� */
	public void save(ENSealNames object) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSealNames%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealNames. �������� ������ */
	public ENSealNames getObject(int code) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSealNames%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealNames. �������� ������ ������ */
	public ENSealNamesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSealNames. �������� ������ �� ������� */
	public ENSealNamesShortList getFilteredList(
			ENSealNamesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSealNames. �������� ������ ��� ��������� */
	public ENSealNamesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSealNames. �������� ������ ��� ��������� �� ������� */
	public ENSealNamesShortList getScrollableFilteredList(
			ENSealNamesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSealNames%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealNames. �������� ������ ��� ��������� �� ������� */
	public ENSealNamesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSealNamesFilter filterObject = new ENSealNamesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSealNames. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSealNamesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSealNames%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealNames. �������� ������ �� ������ */
	public ENSealNamesShort getShortObject(int code) {
		try {
			ENSealNamesDAO objectDAO = new ENSealNamesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSealNames%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
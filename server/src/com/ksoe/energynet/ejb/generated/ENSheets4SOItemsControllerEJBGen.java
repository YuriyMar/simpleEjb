
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSheets4SOItemsDAO;
import com.ksoe.energynet.valueobject.ENSheets4SOItems;
import com.ksoe.energynet.valueobject.lists.ENSheets4SOItemsShortList;
import com.ksoe.energynet.valueobject.brief.ENSheets4SOItemsShort;
import com.ksoe.energynet.valueobject.filter.ENSheets4SOItemsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSheets4SOItems;
 *
 */


public abstract class ENSheets4SOItemsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSheets4SOItemsControllerEJBGen() {
	}

	/* ENSheets4SOItems. �������� */
	public int add(ENSheets4SOItems object) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItems. ������� */
	public void remove(int code) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSheets4SOItems. �������� */
	public void save(ENSheets4SOItems object) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItems. �������� ������ */
	public ENSheets4SOItems getObject(int code) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItems. �������� ������ ������ */
	public ENSheets4SOItemsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSheets4SOItems. �������� ������ �� ������� */
	public ENSheets4SOItemsShortList getFilteredList(
			ENSheets4SOItemsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSheets4SOItems. �������� ������ ��� ��������� */
	public ENSheets4SOItemsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSheets4SOItems. �������� ������ ��� ��������� �� ������� */
	public ENSheets4SOItemsShortList getScrollableFilteredList(
			ENSheets4SOItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItems. �������� ������ ��� ��������� �� ������� */
	public ENSheets4SOItemsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSheets4SOItemsFilter filterObject = new ENSheets4SOItemsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSheets4SOItems. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENSheets4SOItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItems. �������� ������ �� ������ */
	public ENSheets4SOItemsShort getShortObject(int code) {
		try {
			ENSheets4SOItemsDAO objectDAO = new ENSheets4SOItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOItems%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
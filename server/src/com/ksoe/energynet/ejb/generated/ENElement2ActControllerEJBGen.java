
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2ActDAO;
import com.ksoe.energynet.valueobject.ENElement2Act;
import com.ksoe.energynet.valueobject.lists.ENElement2ActShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2ActShort;
import com.ksoe.energynet.valueobject.filter.ENElement2ActFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2Act;
 *
 */


public abstract class ENElement2ActControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2ActControllerEJBGen() {
	}

	/* ENElement2Act. �������� */
	public int add(ENElement2Act object) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2Act%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Act. ������� */
	public void remove(int code) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2Act%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2Act. �������� */
	public void save(ENElement2Act object) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2Act%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Act. �������� ������ */
	public ENElement2Act getObject(int code) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2Act%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Act. �������� ������ ������ */
	public ENElement2ActShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2Act. �������� ������ �� ������� */
	public ENElement2ActShortList getFilteredList(
			ENElement2ActFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2Act. �������� ������ ��� ��������� */
	public ENElement2ActShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2Act. �������� ������ ��� ��������� �� ������� */
	public ENElement2ActShortList getScrollableFilteredList(
			ENElement2ActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2Act%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Act. �������� ������ ��� ��������� �� ������� */
	public ENElement2ActShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2ActFilter filterObject = new ENElement2ActFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2Act. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENElement2ActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2Act%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Act. �������� ������ �� ������ */
	public ENElement2ActShort getShortObject(int code) {
		try {
			ENElement2ActDAO objectDAO = new ENElement2ActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2Act%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
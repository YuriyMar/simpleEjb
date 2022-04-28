
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionTariffTypeDAO;
import com.ksoe.energynet.valueobject.ENConnectionTariffType;
import com.ksoe.energynet.valueobject.lists.ENConnectionTariffTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionTariffTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionTariffTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionTariffType;
 *
 */


public abstract class ENConnectionTariffTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionTariffTypeControllerEJBGen() {
	}

	/* ENConnectionTariffType. �������� */
	public int add(ENConnectionTariffType object) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariffType. ������� */
	public void remove(int code) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionTariffType. �������� */
	public void save(ENConnectionTariffType object) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariffType. �������� ������ */
	public ENConnectionTariffType getObject(int code) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariffType. �������� ������ ������ */
	public ENConnectionTariffTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionTariffType. �������� ������ �� ������� */
	public ENConnectionTariffTypeShortList getFilteredList(
			ENConnectionTariffTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionTariffType. �������� ������ ��� ��������� */
	public ENConnectionTariffTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionTariffType. �������� ������ ��� ��������� �� ������� */
	public ENConnectionTariffTypeShortList getScrollableFilteredList(
			ENConnectionTariffTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariffType. �������� ������ ��� ��������� �� ������� */
	public ENConnectionTariffTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionTariffTypeFilter filterObject = new ENConnectionTariffTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionTariffType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionTariffTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariffType. �������� ������ �� ������ */
	public ENConnectionTariffTypeShort getShortObject(int code) {
		try {
			ENConnectionTariffTypeDAO objectDAO = new ENConnectionTariffTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionTariffType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
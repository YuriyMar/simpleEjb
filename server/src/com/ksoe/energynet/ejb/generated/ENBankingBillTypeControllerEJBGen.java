
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBankingBillTypeDAO;
import com.ksoe.energynet.valueobject.ENBankingBillType;
import com.ksoe.energynet.valueobject.lists.ENBankingBillTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENBankingBillTypeShort;
import com.ksoe.energynet.valueobject.filter.ENBankingBillTypeFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENBankingBillType;
 *
 */


public abstract class ENBankingBillTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBankingBillTypeControllerEJBGen() {
	}

	/* ENBankingBillType. �������� */
	public int add(ENBankingBillType object) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBankingBillType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingBillType. ������� */
	public void remove(int code) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBankingBillType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBankingBillType. �������� */
	public void save(ENBankingBillType object) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBankingBillType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingBillType. �������� ������ */
	public ENBankingBillType getObject(int code) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBankingBillType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingBillType. �������� ������ ������ */
	public ENBankingBillTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBankingBillType. �������� ������ �� ������� */
	public ENBankingBillTypeShortList getFilteredList(
			ENBankingBillTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBankingBillType. �������� ������ ��� ��������� */
	public ENBankingBillTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBankingBillType. �������� ������ ��� ��������� �� ������� */
	public ENBankingBillTypeShortList getScrollableFilteredList(
			ENBankingBillTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBankingBillType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingBillType. �������� ������ ��� ��������� �� ������� */
	public ENBankingBillTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBankingBillTypeFilter filterObject = new ENBankingBillTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBankingBillType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENBankingBillTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBankingBillType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingBillType. �������� ������ �� ������ */
	public ENBankingBillTypeShort getShortObject(int code) {
		try {
			ENBankingBillTypeDAO objectDAO = new ENBankingBillTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBankingBillType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
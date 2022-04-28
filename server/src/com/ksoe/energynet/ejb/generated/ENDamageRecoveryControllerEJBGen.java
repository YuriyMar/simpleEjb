
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDamageRecoveryDAO;
import com.ksoe.energynet.valueobject.ENDamageRecovery;
import com.ksoe.energynet.valueobject.lists.ENDamageRecoveryShortList;
import com.ksoe.energynet.valueobject.brief.ENDamageRecoveryShort;
import com.ksoe.energynet.valueobject.filter.ENDamageRecoveryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDamageRecovery;
 *
 */


public abstract class ENDamageRecoveryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDamageRecoveryControllerEJBGen() {
	}

	/* ENDamageRecovery. �������� */
	public int add(ENDamageRecovery object) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDamageRecovery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery. ������� */
	public void remove(int code) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDamageRecovery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDamageRecovery. �������� */
	public void save(ENDamageRecovery object) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDamageRecovery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery. �������� ������ */
	public ENDamageRecovery getObject(int code) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecovery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery. �������� ������ ������ */
	public ENDamageRecoveryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDamageRecovery. �������� ������ �� ������� */
	public ENDamageRecoveryShortList getFilteredList(
			ENDamageRecoveryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDamageRecovery. �������� ������ ��� ��������� */
	public ENDamageRecoveryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDamageRecovery. �������� ������ ��� ��������� �� ������� */
	public ENDamageRecoveryShortList getScrollableFilteredList(
			ENDamageRecoveryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDamageRecovery%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery. �������� ������ ��� ��������� �� ������� */
	public ENDamageRecoveryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDamageRecoveryFilter filterObject = new ENDamageRecoveryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDamageRecovery. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENDamageRecoveryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDamageRecovery%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery. �������� ������ �� ������ */
	public ENDamageRecoveryShort getShortObject(int code) {
		try {
			ENDamageRecoveryDAO objectDAO = new ENDamageRecoveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecovery%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
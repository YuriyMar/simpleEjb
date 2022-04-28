
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAgreementKindDAO;
import com.ksoe.energynet.valueobject.ENAgreementKind;
import com.ksoe.energynet.valueobject.lists.ENAgreementKindShortList;
import com.ksoe.energynet.valueobject.brief.ENAgreementKindShort;
import com.ksoe.energynet.valueobject.filter.ENAgreementKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAgreementKind;
 *
 */


public abstract class ENAgreementKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAgreementKindControllerEJBGen() {
	}

	/* ENAgreementKind. �������� */
	public int add(ENAgreementKind object) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAgreementKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreementKind. ������� */
	public void remove(int code) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAgreementKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAgreementKind. �������� */
	public void save(ENAgreementKind object) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAgreementKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreementKind. �������� ������ */
	public ENAgreementKind getObject(int code) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAgreementKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreementKind. �������� ������ ������ */
	public ENAgreementKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAgreementKind. �������� ������ �� ������� */
	public ENAgreementKindShortList getFilteredList(
			ENAgreementKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAgreementKind. �������� ������ ��� ��������� */
	public ENAgreementKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAgreementKind. �������� ������ ��� ��������� �� ������� */
	public ENAgreementKindShortList getScrollableFilteredList(
			ENAgreementKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAgreementKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreementKind. �������� ������ ��� ��������� �� ������� */
	public ENAgreementKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAgreementKindFilter filterObject = new ENAgreementKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAgreementKind. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENAgreementKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAgreementKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreementKind. �������� ������ �� ������ */
	public ENAgreementKindShort getShortObject(int code) {
		try {
			ENAgreementKindDAO objectDAO = new ENAgreementKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAgreementKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
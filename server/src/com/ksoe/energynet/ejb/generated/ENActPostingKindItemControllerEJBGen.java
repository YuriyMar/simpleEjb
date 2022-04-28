
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActPostingKindItemDAO;
import com.ksoe.energynet.valueobject.ENActPostingKindItem;
import com.ksoe.energynet.valueobject.lists.ENActPostingKindItemShortList;
import com.ksoe.energynet.valueobject.brief.ENActPostingKindItemShort;
import com.ksoe.energynet.valueobject.filter.ENActPostingKindItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActPostingKindItem;
 *
 */


public abstract class ENActPostingKindItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActPostingKindItemControllerEJBGen() {
	}

	/* ENActPostingKindItem. �������� */
	public int add(ENActPostingKindItem object) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			
			if(object.ENActPostingTypeSum.code == Integer.MIN_VALUE) {

				throw new SystemException(
						"�� ������� ��� ���� ��� ��������(��,���,������� ��� ����)");
			}
			
		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKindItem. ������� */
	public void remove(int code) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActPostingKindItem. �������� */
	public void save(ENActPostingKindItem object) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKindItem. �������� ������ */
	public ENActPostingKindItem getObject(int code) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKindItem. �������� ������ ������ */
	public ENActPostingKindItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActPostingKindItem. �������� ������ �� ������� */
	public ENActPostingKindItemShortList getFilteredList(
			ENActPostingKindItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActPostingKindItem. �������� ������ ��� ��������� */
	public ENActPostingKindItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActPostingKindItem. �������� ������ ��� ��������� �� ������� */
	public ENActPostingKindItemShortList getScrollableFilteredList(
			ENActPostingKindItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKindItem. �������� ������ ��� ��������� �� ������� */
	public ENActPostingKindItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActPostingKindItemFilter filterObject = new ENActPostingKindItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActPostingKindItem. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENActPostingKindItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKindItem. �������� ������ �� ������ */
	public ENActPostingKindItemShort getShortObject(int code) {
		try {
			ENActPostingKindItemDAO objectDAO = new ENActPostingKindItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingKindItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
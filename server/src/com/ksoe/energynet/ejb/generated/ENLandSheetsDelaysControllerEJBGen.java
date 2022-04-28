
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENLandSheetsDelaysDAO;
import com.ksoe.energynet.valueobject.ENLandSheetsDelays;
import com.ksoe.energynet.valueobject.lists.ENLandSheetsDelaysShortList;
import com.ksoe.energynet.valueobject.brief.ENLandSheetsDelaysShort;
import com.ksoe.energynet.valueobject.filter.ENLandSheetsDelaysFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENLandSheetsDelays;
 *
 */


public abstract class ENLandSheetsDelaysControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENLandSheetsDelaysControllerEJBGen() {
	}

	/* ENLandSheetsDelays. �������� */
	public int add(ENLandSheetsDelays object) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENLandSheetsDelays. ������� */
	public void remove(int code) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENLandSheetsDelays. �������� */
	public void save(ENLandSheetsDelays object) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENLandSheetsDelays. �������� ������ */
	public ENLandSheetsDelays getObject(int code) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENLandSheetsDelays. �������� ������ ������ */
	public ENLandSheetsDelaysShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENLandSheetsDelays. �������� ������ �� ������� */
	public ENLandSheetsDelaysShortList getFilteredList(
			ENLandSheetsDelaysFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENLandSheetsDelays. �������� ������ ��� ��������� */
	public ENLandSheetsDelaysShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENLandSheetsDelays. �������� ������ ��� ��������� �� ������� */
	public ENLandSheetsDelaysShortList getScrollableFilteredList(
			ENLandSheetsDelaysFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENLandSheetsDelays. �������� ������ ��� ��������� �� ������� */
	public ENLandSheetsDelaysShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENLandSheetsDelaysFilter filterObject = new ENLandSheetsDelaysFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENLandSheetsDelays. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENLandSheetsDelaysFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENLandSheetsDelays. �������� ������ �� ������ */
	public ENLandSheetsDelaysShort getShortObject(int code) {
		try {
			ENLandSheetsDelaysDAO objectDAO = new ENLandSheetsDelaysDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENLandSheetsDelays%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPPurposeProgramDAO;
import com.ksoe.energynet.valueobject.ENIPPurposeProgram;
import com.ksoe.energynet.valueobject.lists.ENIPPurposeProgramShortList;
import com.ksoe.energynet.valueobject.brief.ENIPPurposeProgramShort;
import com.ksoe.energynet.valueobject.filter.ENIPPurposeProgramFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPPurposeProgram;
 *
 */


public abstract class ENIPPurposeProgramControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPPurposeProgramControllerEJBGen() {
	}

	/* ENIPPurposeProgram. �������� */
	public int add(ENIPPurposeProgram object) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPPurposeProgram. ������� */
	public void remove(int code) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPPurposeProgram. �������� */
	public void save(ENIPPurposeProgram object) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPPurposeProgram. �������� ������ */
	public ENIPPurposeProgram getObject(int code) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPPurposeProgram. �������� ������ ������ */
	public ENIPPurposeProgramShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPPurposeProgram. �������� ������ �� ������� */
	public ENIPPurposeProgramShortList getFilteredList(
			ENIPPurposeProgramFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPPurposeProgram. �������� ������ ��� ��������� */
	public ENIPPurposeProgramShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPPurposeProgram. �������� ������ ��� ��������� �� ������� */
	public ENIPPurposeProgramShortList getScrollableFilteredList(
			ENIPPurposeProgramFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPPurposeProgram. �������� ������ ��� ��������� �� ������� */
	public ENIPPurposeProgramShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPPurposeProgramFilter filterObject = new ENIPPurposeProgramFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPPurposeProgram. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENIPPurposeProgramFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPPurposeProgram. �������� ������ �� ������ */
	public ENIPPurposeProgramShort getShortObject(int code) {
		try {
			ENIPPurposeProgramDAO objectDAO = new ENIPPurposeProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPPurposeProgram%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENMolFuelMotionTypeDAO;
import com.ksoe.energynet.valueobject.ENMolFuelMotionType;
import com.ksoe.energynet.valueobject.lists.ENMolFuelMotionTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENMolFuelMotionTypeShort;
import com.ksoe.energynet.valueobject.filter.ENMolFuelMotionTypeFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENMolFuelMotionType;
 *
 */


public abstract class ENMolFuelMotionTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENMolFuelMotionTypeControllerEJBGen() {
	}

	/* ENMolFuelMotionType. �������� */
	public int add(ENMolFuelMotionType object) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotionType. ������� */
	public void remove(int code) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENMolFuelMotionType. �������� */
	public void save(ENMolFuelMotionType object) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotionType. �������� ������ */
	public ENMolFuelMotionType getObject(int code) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotionType. �������� ������ ������ */
	public ENMolFuelMotionTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENMolFuelMotionType. �������� ������ �� ������� */
	public ENMolFuelMotionTypeShortList getFilteredList(
			ENMolFuelMotionTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENMolFuelMotionType. �������� ������ ��� ��������� */
	public ENMolFuelMotionTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENMolFuelMotionType. �������� ������ ��� ��������� �� ������� */
	public ENMolFuelMotionTypeShortList getScrollableFilteredList(
			ENMolFuelMotionTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotionType. �������� ������ ��� ��������� �� ������� */
	public ENMolFuelMotionTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENMolFuelMotionTypeFilter filterObject = new ENMolFuelMotionTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENMolFuelMotionType. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENMolFuelMotionTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotionType. �������� ������ �� ������ */
	public ENMolFuelMotionTypeShort getShortObject(int code) {
		try {
			ENMolFuelMotionTypeDAO objectDAO = new ENMolFuelMotionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMolFuelMotionType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
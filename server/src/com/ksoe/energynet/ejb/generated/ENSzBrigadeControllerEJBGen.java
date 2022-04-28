//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSzBrigadeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSzBrigade;
import com.ksoe.energynet.valueobject.filter.ENSzBrigadeFilter;
import com.ksoe.energynet.valueobject.lists.ENSzBrigadeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENSzBrigade;
 *
 */

public abstract class ENSzBrigadeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSzBrigadeControllerEJBGen() {
	}

	/* ENSzBrigade. Добавить */
	public int add(ENSzBrigade object) {
		try {

			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSzBrigade%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSzBrigade. Удалить */
	public void remove(int code) {
		try {
			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSzBrigade%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSzBrigade. Изменить */
	public void save(ENSzBrigade object) {
		try {
			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSzBrigade%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSzBrigade. Получить объект */
	public ENSzBrigade getObject(int code) {
		try {
			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSzBrigade%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSzBrigade. Получить полный список */
	public ENSzBrigadeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSzBrigade. Получить список по фильтру */
	public ENSzBrigadeShortList getFilteredList(ENSzBrigadeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSzBrigade. Получить список для просмотра */
	public ENSzBrigadeShortList getScrollableList(int fromPosition, int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* EENSzBrigade. Получить список для просмотра по фильтру */
	public ENSzBrigadeShortList getScrollableFilteredList(
			ENSzBrigadeFilter filterObject, int fromPosition, int quantity) {
		try {
			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSzBrigade%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSzBrigade. Получить список для просмотра по условию */
	public ENSzBrigadeShortList getScrollableListByCondition(String aCondition,
			int fromPosition, int quantity) {
		ENSzBrigadeFilter filterObject = new ENSzBrigadeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* EENSzBrigade. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(ENSzBrigadeFilter filterObject,
			int fromPosition, int quantity) {
		try {
			ENSzBrigadeDAO objectDAO = new ENSzBrigadeDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSzBrigade%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
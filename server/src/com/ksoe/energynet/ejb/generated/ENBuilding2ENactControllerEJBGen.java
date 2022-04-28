
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuilding2ENactDAO;
import com.ksoe.energynet.valueobject.ENBuilding2ENact;
import com.ksoe.energynet.valueobject.lists.ENBuilding2ENactShortList;
import com.ksoe.energynet.valueobject.brief.ENBuilding2ENactShort;
import com.ksoe.energynet.valueobject.filter.ENBuilding2ENactFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuilding2ENact;
 *
 */


public abstract class ENBuilding2ENactControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuilding2ENactControllerEJBGen() {
	}

	/* ENBuilding2ENact. Добавить */
	public int add(ENBuilding2ENact object) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ENact. Удалить */
	public void remove(int code) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuilding2ENact. Изменить */
	public void save(ENBuilding2ENact object) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ENact. Получить объект */
	public ENBuilding2ENact getObject(int code) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ENact. Получить полный список */
	public ENBuilding2ENactShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuilding2ENact. Получить список по фильтру */
	public ENBuilding2ENactShortList getFilteredList(
			ENBuilding2ENactFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuilding2ENact. Получить список для просмотра */
	public ENBuilding2ENactShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuilding2ENact. Получить список для просмотра по фильтру */
	public ENBuilding2ENactShortList getScrollableFilteredList(
			ENBuilding2ENactFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ENact. Получить список для просмотра по условию */
	public ENBuilding2ENactShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuilding2ENactFilter filterObject = new ENBuilding2ENactFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuilding2ENact. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuilding2ENactFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ENact. Получить объект из списка */
	public ENBuilding2ENactShort getShortObject(int code) {
		try {
			ENBuilding2ENactDAO objectDAO = new ENBuilding2ENactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ENact%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
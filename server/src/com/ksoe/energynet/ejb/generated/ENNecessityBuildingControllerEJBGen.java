
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENNecessityBuildingDAO;
import com.ksoe.energynet.valueobject.ENNecessityBuilding;
import com.ksoe.energynet.valueobject.lists.ENNecessityBuildingShortList;
import com.ksoe.energynet.valueobject.brief.ENNecessityBuildingShort;
import com.ksoe.energynet.valueobject.filter.ENNecessityBuildingFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENNecessityBuilding;
 *
 */


public abstract class ENNecessityBuildingControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENNecessityBuildingControllerEJBGen() {
	}

	/* ENNecessityBuilding. Добавить */
	public int add(ENNecessityBuilding object) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNecessityBuilding. Удалить */
	public void remove(int code) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENNecessityBuilding. Изменить */
	public void save(ENNecessityBuilding object) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNecessityBuilding. Получить объект */
	public ENNecessityBuilding getObject(int code) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNecessityBuilding. Получить полный список */
	public ENNecessityBuildingShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENNecessityBuilding. Получить список по фильтру */
	public ENNecessityBuildingShortList getFilteredList(
			ENNecessityBuildingFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENNecessityBuilding. Получить список для просмотра */
	public ENNecessityBuildingShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENNecessityBuilding. Получить список для просмотра по фильтру */
	public ENNecessityBuildingShortList getScrollableFilteredList(
			ENNecessityBuildingFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNecessityBuilding. Получить список для просмотра по условию */
	public ENNecessityBuildingShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENNecessityBuildingFilter filterObject = new ENNecessityBuildingFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENNecessityBuilding. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENNecessityBuildingFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNecessityBuilding. Получить объект из списка */
	public ENNecessityBuildingShort getShortObject(int code) {
		try {
			ENNecessityBuildingDAO objectDAO = new ENNecessityBuildingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNecessityBuilding%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCalcPowerReserveItemDAO;
import com.ksoe.energynet.valueobject.ENCalcPowerReserveItem;
import com.ksoe.energynet.valueobject.lists.ENCalcPowerReserveItemShortList;
import com.ksoe.energynet.valueobject.brief.ENCalcPowerReserveItemShort;
import com.ksoe.energynet.valueobject.filter.ENCalcPowerReserveItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCalcPowerReserveItem;
 *
 */


public abstract class ENCalcPowerReserveItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCalcPowerReserveItemControllerEJBGen() {
	}

	/* ENCalcPowerReserveItem. Добавить */
	public int add(ENCalcPowerReserveItem object) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserveItem. Удалить */
	public void remove(int code) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCalcPowerReserveItem. Изменить */
	public void save(ENCalcPowerReserveItem object) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserveItem. Получить объект */
	public ENCalcPowerReserveItem getObject(int code) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserveItem. Получить полный список */
	public ENCalcPowerReserveItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCalcPowerReserveItem. Получить список по фильтру */
	public ENCalcPowerReserveItemShortList getFilteredList(
			ENCalcPowerReserveItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCalcPowerReserveItem. Получить список для просмотра */
	public ENCalcPowerReserveItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCalcPowerReserveItem. Получить список для просмотра по фильтру */
	public ENCalcPowerReserveItemShortList getScrollableFilteredList(
			ENCalcPowerReserveItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserveItem. Получить список для просмотра по условию */
	public ENCalcPowerReserveItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCalcPowerReserveItemFilter filterObject = new ENCalcPowerReserveItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCalcPowerReserveItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCalcPowerReserveItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserveItem. Получить объект из списка */
	public ENCalcPowerReserveItemShort getShortObject(int code) {
		try {
			ENCalcPowerReserveItemDAO objectDAO = new ENCalcPowerReserveItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcPowerReserveItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelDistributionSheetItemDAO;
import com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem;
import com.ksoe.energynet.valueobject.lists.ENFuelDistributionSheetItemShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelDistributionSheetItemShort;
import com.ksoe.energynet.valueobject.filter.ENFuelDistributionSheetItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelDistributionSheetItem;
 *
 */


public abstract class ENFuelDistributionSheetItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelDistributionSheetItemControllerEJBGen() {
	}

	/* ENFuelDistributionSheetItem. Добавить */
	public int add(ENFuelDistributionSheetItem object) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheetItem. Удалить */
	public void remove(int code) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelDistributionSheetItem. Изменить */
	public void save(ENFuelDistributionSheetItem object) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheetItem. Получить объект */
	public ENFuelDistributionSheetItem getObject(int code) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheetItem. Получить полный список */
	public ENFuelDistributionSheetItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelDistributionSheetItem. Получить список по фильтру */
	public ENFuelDistributionSheetItemShortList getFilteredList(
			ENFuelDistributionSheetItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelDistributionSheetItem. Получить список для просмотра */
	public ENFuelDistributionSheetItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelDistributionSheetItem. Получить список для просмотра по фильтру */
	public ENFuelDistributionSheetItemShortList getScrollableFilteredList(
			ENFuelDistributionSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheetItem. Получить список для просмотра по условию */
	public ENFuelDistributionSheetItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelDistributionSheetItemFilter filterObject = new ENFuelDistributionSheetItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelDistributionSheetItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelDistributionSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheetItem. Получить объект из списка */
	public ENFuelDistributionSheetItemShort getShortObject(int code) {
		try {
			ENFuelDistributionSheetItemDAO objectDAO = new ENFuelDistributionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelDistributionSheetItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
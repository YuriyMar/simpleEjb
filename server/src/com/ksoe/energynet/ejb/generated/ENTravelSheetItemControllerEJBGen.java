
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTravelSheetItemDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetItem;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItemShortList;
import com.ksoe.energynet.valueobject.brief.ENTravelSheetItemShort;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTravelSheetItem;
 *
 */


public abstract class ENTravelSheetItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTravelSheetItemControllerEJBGen() {
	}

	/* ENTravelSheetItem. Добавить */
	public int add(ENTravelSheetItem object) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetItem. Удалить */
	public void remove(int code) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTravelSheetItem. Изменить */
	public void save(ENTravelSheetItem object) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetItem. Получить объект */
	public ENTravelSheetItem getObject(int code) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetItem. Получить полный список */
	public ENTravelSheetItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTravelSheetItem. Получить список по фильтру */
	public ENTravelSheetItemShortList getFilteredList(
			ENTravelSheetItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTravelSheetItem. Получить список для просмотра */
	public ENTravelSheetItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTravelSheetItem. Получить список для просмотра по фильтру */
	public ENTravelSheetItemShortList getScrollableFilteredList(
			ENTravelSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetItem. Получить список для просмотра по условию */
	public ENTravelSheetItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTravelSheetItemFilter filterObject = new ENTravelSheetItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTravelSheetItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTravelSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetItem. Получить объект из списка */
	public ENTravelSheetItemShort getShortObject(int code) {
		try {
			ENTravelSheetItemDAO objectDAO = new ENTravelSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
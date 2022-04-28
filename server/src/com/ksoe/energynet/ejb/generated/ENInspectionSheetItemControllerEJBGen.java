
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInspectionSheetItemDAO;
import com.ksoe.energynet.valueobject.ENInspectionSheetItem;
import com.ksoe.energynet.valueobject.lists.ENInspectionSheetItemShortList;
import com.ksoe.energynet.valueobject.brief.ENInspectionSheetItemShort;
import com.ksoe.energynet.valueobject.filter.ENInspectionSheetItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInspectionSheetItem;
 *
 */


public abstract class ENInspectionSheetItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInspectionSheetItemControllerEJBGen() {
	}

	/* ENInspectionSheetItem. Добавить */
	public int add(ENInspectionSheetItem object) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetItem. Удалить */
	public void remove(int code) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInspectionSheetItem. Изменить */
	public void save(ENInspectionSheetItem object) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetItem. Получить объект */
	public ENInspectionSheetItem getObject(int code) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetItem. Получить полный список */
	public ENInspectionSheetItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInspectionSheetItem. Получить список по фильтру */
	public ENInspectionSheetItemShortList getFilteredList(
			ENInspectionSheetItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInspectionSheetItem. Получить список для просмотра */
	public ENInspectionSheetItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInspectionSheetItem. Получить список для просмотра по фильтру */
	public ENInspectionSheetItemShortList getScrollableFilteredList(
			ENInspectionSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetItem. Получить список для просмотра по условию */
	public ENInspectionSheetItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInspectionSheetItemFilter filterObject = new ENInspectionSheetItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInspectionSheetItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInspectionSheetItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetItem. Получить объект из списка */
	public ENInspectionSheetItemShort getShortObject(int code) {
		try {
			ENInspectionSheetItemDAO objectDAO = new ENInspectionSheetItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheetItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
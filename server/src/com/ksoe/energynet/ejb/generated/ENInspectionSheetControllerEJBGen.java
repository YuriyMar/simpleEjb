
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInspectionSheetDAO;
import com.ksoe.energynet.valueobject.ENInspectionSheet;
import com.ksoe.energynet.valueobject.lists.ENInspectionSheetShortList;
import com.ksoe.energynet.valueobject.brief.ENInspectionSheetShort;
import com.ksoe.energynet.valueobject.filter.ENInspectionSheetFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInspectionSheet;
 *
 */


public abstract class ENInspectionSheetControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInspectionSheetControllerEJBGen() {
	}

	/* ENInspectionSheet. Добавить */
	public int add(ENInspectionSheet object) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInspectionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheet. Удалить */
	public void remove(int code) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInspectionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInspectionSheet. Изменить */
	public void save(ENInspectionSheet object) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInspectionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheet. Получить объект */
	public ENInspectionSheet getObject(int code) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheet. Получить полный список */
	public ENInspectionSheetShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInspectionSheet. Получить список по фильтру */
	public ENInspectionSheetShortList getFilteredList(
			ENInspectionSheetFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInspectionSheet. Получить список для просмотра */
	public ENInspectionSheetShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInspectionSheet. Получить список для просмотра по фильтру */
	public ENInspectionSheetShortList getScrollableFilteredList(
			ENInspectionSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInspectionSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheet. Получить список для просмотра по условию */
	public ENInspectionSheetShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInspectionSheetFilter filterObject = new ENInspectionSheetFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInspectionSheet. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInspectionSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInspectionSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheet. Получить объект из списка */
	public ENInspectionSheetShort getShortObject(int code) {
		try {
			ENInspectionSheetDAO objectDAO = new ENInspectionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheet%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
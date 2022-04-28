
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTravelSheetDAO;
import com.ksoe.energynet.valueobject.ENTravelSheet;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetShortList;
import com.ksoe.energynet.valueobject.brief.ENTravelSheetShort;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTravelSheet;
 *
 */


public abstract class ENTravelSheetControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTravelSheetControllerEJBGen() {
	}

	/* ENTravelSheet. Добавить */
	public int add(ENTravelSheet object) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTravelSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheet. Удалить */
	public void remove(int code) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTravelSheet. Изменить */
	public void save(ENTravelSheet object) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.setDomain_info(null);
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTravelSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheet. Получить объект */
	public ENTravelSheet getObject(int code) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheet. Получить полный список */
	public ENTravelSheetShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTravelSheet. Получить список по фильтру */
	public ENTravelSheetShortList getFilteredList(
			ENTravelSheetFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTravelSheet. Получить список для просмотра */
	public ENTravelSheetShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTravelSheet. Получить список для просмотра по фильтру */
	public ENTravelSheetShortList getScrollableFilteredList(
			ENTravelSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheet. Получить список для просмотра по условию */
	public ENTravelSheetShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTravelSheetFilter filterObject = new ENTravelSheetFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTravelSheet. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTravelSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheet. Получить объект из списка */
	public ENTravelSheetShort getShortObject(int code) {
		try {
			ENTravelSheetDAO objectDAO = new ENTravelSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheet%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
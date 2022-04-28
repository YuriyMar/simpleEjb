
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncomeServicesDAO;
import com.ksoe.energynet.valueobject.ENActIncomeServices;
import com.ksoe.energynet.valueobject.lists.ENActIncomeServicesShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncomeServicesShort;
import com.ksoe.energynet.valueobject.filter.ENActIncomeServicesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncomeServices;
 *
 */


public abstract class ENActIncomeServicesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncomeServicesControllerEJBGen() {
	}

	/* ENActIncomeServices. Добавить */
	public int add(ENActIncomeServices object) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncomeServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeServices. Удалить */
	public void remove(int code) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncomeServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncomeServices. Изменить */
	public void save(ENActIncomeServices object) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncomeServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeServices. Получить объект */
	public ENActIncomeServices getObject(int code) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeServices%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeServices. Получить полный список */
	public ENActIncomeServicesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncomeServices. Получить список по фильтру */
	public ENActIncomeServicesShortList getFilteredList(
			ENActIncomeServicesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncomeServices. Получить список для просмотра */
	public ENActIncomeServicesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncomeServices. Получить список для просмотра по фильтру */
	public ENActIncomeServicesShortList getScrollableFilteredList(
			ENActIncomeServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomeServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeServices. Получить список для просмотра по условию */
	public ENActIncomeServicesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncomeServicesFilter filterObject = new ENActIncomeServicesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncomeServices. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActIncomeServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomeServices%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeServices. Получить объект из списка */
	public ENActIncomeServicesShort getShortObject(int code) {
		try {
			ENActIncomeServicesDAO objectDAO = new ENActIncomeServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeServices%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWork2CCDamageLogDAO;
import com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2CCDamageLogShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWork2CCDamageLogShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2CCDamageLogFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWork2CCDamageLog;
 *
 */


public abstract class ENPlanWork2CCDamageLogControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWork2CCDamageLogControllerEJBGen() {
	}

	/* ENPlanWork2CCDamageLog. Добавить */
	public int add(ENPlanWork2CCDamageLog object) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2CCDamageLog. Удалить */
	public void remove(int code) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWork2CCDamageLog. Изменить */
	public void save(ENPlanWork2CCDamageLog object) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2CCDamageLog. Получить объект */
	public ENPlanWork2CCDamageLog getObject(int code) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2CCDamageLog. Получить полный список */
	public ENPlanWork2CCDamageLogShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWork2CCDamageLog. Получить список по фильтру */
	public ENPlanWork2CCDamageLogShortList getFilteredList(
			ENPlanWork2CCDamageLogFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWork2CCDamageLog. Получить список для просмотра */
	public ENPlanWork2CCDamageLogShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWork2CCDamageLog. Получить список для просмотра по фильтру */
	public ENPlanWork2CCDamageLogShortList getScrollableFilteredList(
			ENPlanWork2CCDamageLogFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2CCDamageLog. Получить список для просмотра по условию */
	public ENPlanWork2CCDamageLogShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWork2CCDamageLogFilter filterObject = new ENPlanWork2CCDamageLogFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWork2CCDamageLog. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWork2CCDamageLogFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2CCDamageLog. Получить объект из списка */
	public ENPlanWork2CCDamageLogShort getShortObject(int code) {
		try {
			ENPlanWork2CCDamageLogDAO objectDAO = new ENPlanWork2CCDamageLogDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
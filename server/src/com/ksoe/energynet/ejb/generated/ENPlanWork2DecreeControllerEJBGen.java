
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWork2DecreeDAO;
import com.ksoe.energynet.valueobject.ENPlanWork2Decree;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2DecreeShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWork2DecreeShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2DecreeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWork2Decree;
 *
 */


public abstract class ENPlanWork2DecreeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWork2DecreeControllerEJBGen() {
	}

	/* ENPlanWork2Decree. Добавить */
	public int add(ENPlanWork2Decree object) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2Decree. Удалить */
	public void remove(int code) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWork2Decree. Изменить */
	public void save(ENPlanWork2Decree object) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2Decree. Получить объект */
	public ENPlanWork2Decree getObject(int code) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2Decree. Получить полный список */
	public ENPlanWork2DecreeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWork2Decree. Получить список по фильтру */
	public ENPlanWork2DecreeShortList getFilteredList(
			ENPlanWork2DecreeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWork2Decree. Получить список для просмотра */
	public ENPlanWork2DecreeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWork2Decree. Получить список для просмотра по фильтру */
	public ENPlanWork2DecreeShortList getScrollableFilteredList(
			ENPlanWork2DecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2Decree. Получить список для просмотра по условию */
	public ENPlanWork2DecreeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWork2DecreeFilter filterObject = new ENPlanWork2DecreeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWork2Decree. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWork2DecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2Decree. Получить объект из списка */
	public ENPlanWork2DecreeShort getShortObject(int code) {
		try {
			ENPlanWork2DecreeDAO objectDAO = new ENPlanWork2DecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2Decree%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
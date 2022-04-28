
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWorkItem2GraphDAO;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2GraphShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2GraphShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2GraphFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWorkItem2Graph;
 *
 */


public abstract class ENPlanWorkItem2GraphControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWorkItem2GraphControllerEJBGen() {
	}

	/* ENPlanWorkItem2Graph. Добавить */
	public int add(ENPlanWorkItem2Graph object) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkItem2Graph. Удалить */
	public void remove(int code) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWorkItem2Graph. Изменить */
	public void save(ENPlanWorkItem2Graph object) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkItem2Graph. Получить объект */
	public ENPlanWorkItem2Graph getObject(int code) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkItem2Graph. Получить полный список */
	public ENPlanWorkItem2GraphShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWorkItem2Graph. Получить список по фильтру */
	public ENPlanWorkItem2GraphShortList getFilteredList(
			ENPlanWorkItem2GraphFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWorkItem2Graph. Получить список для просмотра */
	public ENPlanWorkItem2GraphShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWorkItem2Graph. Получить список для просмотра по фильтру */
	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(
			ENPlanWorkItem2GraphFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkItem2Graph. Получить список для просмотра по условию */
	public ENPlanWorkItem2GraphShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWorkItem2GraphFilter filterObject = new ENPlanWorkItem2GraphFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWorkItem2Graph. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkItem2GraphFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkItem2Graph. Получить объект из списка */
	public ENPlanWorkItem2GraphShort getShortObject(int code) {
		try {
			ENPlanWorkItem2GraphDAO objectDAO = new ENPlanWorkItem2GraphDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
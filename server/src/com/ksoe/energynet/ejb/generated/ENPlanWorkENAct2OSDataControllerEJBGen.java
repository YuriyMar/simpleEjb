
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWorkENAct2OSDataDAO;
import com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkENAct2OSDataShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkENAct2OSDataShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkENAct2OSDataFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWorkENAct2OSData;
 *
 */


public abstract class ENPlanWorkENAct2OSDataControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWorkENAct2OSDataControllerEJBGen() {
	}

	/* ENPlanWorkENAct2OSData. Добавить */
	public int add(ENPlanWorkENAct2OSData object) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Удалить */
	public void remove(int code) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWorkENAct2OSData. Изменить */
	public void save(ENPlanWorkENAct2OSData object) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Получить объект */
	public ENPlanWorkENAct2OSData getObject(int code) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Получить полный список */
	public ENPlanWorkENAct2OSDataShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWorkENAct2OSData. Получить список по фильтру */
	public ENPlanWorkENAct2OSDataShortList getFilteredList(
			ENPlanWorkENAct2OSDataFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWorkENAct2OSData. Получить список для просмотра */
	public ENPlanWorkENAct2OSDataShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWorkENAct2OSData. Получить список для просмотра по фильтру */
	public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(
			ENPlanWorkENAct2OSDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Получить список для просмотра по условию */
	public ENPlanWorkENAct2OSDataShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWorkENAct2OSDataFilter filterObject = new ENPlanWorkENAct2OSDataFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWorkENAct2OSData. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWorkENAct2OSDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Получить объект из списка */
	public ENPlanWorkENAct2OSDataShort getShortObject(int code) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
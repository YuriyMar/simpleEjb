
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInvestProgram2PlanDAO;
import com.ksoe.energynet.valueobject.ENInvestProgram2Plan;
import com.ksoe.energynet.valueobject.lists.ENInvestProgram2PlanShortList;
import com.ksoe.energynet.valueobject.brief.ENInvestProgram2PlanShort;
import com.ksoe.energynet.valueobject.filter.ENInvestProgram2PlanFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInvestProgram2Plan;
 *
 */


public abstract class ENInvestProgram2PlanControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInvestProgram2PlanControllerEJBGen() {
	}

	/* ENInvestProgram2Plan. Добавить */
	public int add(ENInvestProgram2Plan object) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram2Plan. Удалить */
	public void remove(int code) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInvestProgram2Plan. Изменить */
	public void save(ENInvestProgram2Plan object) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram2Plan. Получить объект */
	public ENInvestProgram2Plan getObject(int code) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram2Plan. Получить полный список */
	public ENInvestProgram2PlanShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInvestProgram2Plan. Получить список по фильтру */
	public ENInvestProgram2PlanShortList getFilteredList(
			ENInvestProgram2PlanFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInvestProgram2Plan. Получить список для просмотра */
	public ENInvestProgram2PlanShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInvestProgram2Plan. Получить список для просмотра по фильтру */
	public ENInvestProgram2PlanShortList getScrollableFilteredList(
			ENInvestProgram2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram2Plan. Получить список для просмотра по условию */
	public ENInvestProgram2PlanShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInvestProgram2PlanFilter filterObject = new ENInvestProgram2PlanFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInvestProgram2Plan. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInvestProgram2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram2Plan. Получить объект из списка */
	public ENInvestProgram2PlanShort getShortObject(int code) {
		try {
			ENInvestProgram2PlanDAO objectDAO = new ENInvestProgram2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgram2Plan%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
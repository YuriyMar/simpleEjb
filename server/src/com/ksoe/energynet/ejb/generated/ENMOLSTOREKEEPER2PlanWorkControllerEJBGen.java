
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENMOLSTOREKEEPER2PlanWorkDAO;
import com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork;
import com.ksoe.energynet.valueobject.lists.ENMOLSTOREKEEPER2PlanWorkShortList;
import com.ksoe.energynet.valueobject.brief.ENMOLSTOREKEEPER2PlanWorkShort;
import com.ksoe.energynet.valueobject.filter.ENMOLSTOREKEEPER2PlanWorkFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENMOLSTOREKEEPER2PlanWork;
 *
 */


public abstract class ENMOLSTOREKEEPER2PlanWorkControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENMOLSTOREKEEPER2PlanWorkControllerEJBGen() {
	}

	/* ENMOLSTOREKEEPER2PlanWork. Добавить */
	public int add(ENMOLSTOREKEEPER2PlanWork object) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMOLSTOREKEEPER2PlanWork. Удалить */
	public void remove(int code) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENMOLSTOREKEEPER2PlanWork. Изменить */
	public void save(ENMOLSTOREKEEPER2PlanWork object) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить объект */
	public ENMOLSTOREKEEPER2PlanWork getObject(int code) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить полный список */
	public ENMOLSTOREKEEPER2PlanWorkShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить список по фильтру */
	public ENMOLSTOREKEEPER2PlanWorkShortList getFilteredList(
			ENMOLSTOREKEEPER2PlanWorkFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить список для просмотра */
	public ENMOLSTOREKEEPER2PlanWorkShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить список для просмотра по фильтру */
	public ENMOLSTOREKEEPER2PlanWorkShortList getScrollableFilteredList(
			ENMOLSTOREKEEPER2PlanWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить список для просмотра по условию */
	public ENMOLSTOREKEEPER2PlanWorkShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENMOLSTOREKEEPER2PlanWorkFilter filterObject = new ENMOLSTOREKEEPER2PlanWorkFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENMOLSTOREKEEPER2PlanWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMOLSTOREKEEPER2PlanWork. Получить объект из списка */
	public ENMOLSTOREKEEPER2PlanWorkShort getShortObject(int code) {
		try {
			ENMOLSTOREKEEPER2PlanWorkDAO objectDAO = new ENMOLSTOREKEEPER2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMOLSTOREKEEPER2PlanWork%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServices2PlanDAO;
import com.ksoe.energynet.valueobject.ENServices2Plan;
import com.ksoe.energynet.valueobject.lists.ENServices2PlanShortList;
import com.ksoe.energynet.valueobject.brief.ENServices2PlanShort;
import com.ksoe.energynet.valueobject.filter.ENServices2PlanFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServices2Plan;
 *
 */


public abstract class ENServices2PlanControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServices2PlanControllerEJBGen() {
	}

	/* ENServices2Plan. Добавить */
	public int add(ENServices2Plan object) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServices2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2Plan. Удалить */
	public void remove(int code) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServices2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServices2Plan. Изменить */
	public void save(ENServices2Plan object) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServices2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2Plan. Получить объект */
	public ENServices2Plan getObject(int code) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServices2Plan%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2Plan. Получить полный список */
	public ENServices2PlanShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServices2Plan. Получить список по фильтру */
	public ENServices2PlanShortList getFilteredList(
			ENServices2PlanFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServices2Plan. Получить список для просмотра */
	public ENServices2PlanShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServices2Plan. Получить список для просмотра по фильтру */
	public ENServices2PlanShortList getScrollableFilteredList(
			ENServices2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServices2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2Plan. Получить список для просмотра по условию */
	public ENServices2PlanShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServices2PlanFilter filterObject = new ENServices2PlanFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServices2Plan. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServices2PlanFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServices2Plan%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2Plan. Получить объект из списка */
	public ENServices2PlanShort getShortObject(int code) {
		try {
			ENServices2PlanDAO objectDAO = new ENServices2PlanDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServices2Plan%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
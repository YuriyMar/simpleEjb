
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENEstimateItem2PlanTypeDAO;
import com.ksoe.energynet.valueobject.ENEstimateItem2PlanType;
import com.ksoe.energynet.valueobject.lists.ENEstimateItem2PlanTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENEstimateItem2PlanTypeShort;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2PlanTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENEstimateItem2PlanType;
 *
 */


public abstract class ENEstimateItem2PlanTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENEstimateItem2PlanTypeControllerEJBGen() {
	}

	/* ENEstimateItem2PlanType. Добавить */
	public int add(ENEstimateItem2PlanType object) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2PlanType. Удалить */
	public void remove(int code) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENEstimateItem2PlanType. Изменить */
	public void save(ENEstimateItem2PlanType object) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2PlanType. Получить объект */
	public ENEstimateItem2PlanType getObject(int code) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2PlanType. Получить полный список */
	public ENEstimateItem2PlanTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENEstimateItem2PlanType. Получить список по фильтру */
	public ENEstimateItem2PlanTypeShortList getFilteredList(
			ENEstimateItem2PlanTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENEstimateItem2PlanType. Получить список для просмотра */
	public ENEstimateItem2PlanTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENEstimateItem2PlanType. Получить список для просмотра по фильтру */
	public ENEstimateItem2PlanTypeShortList getScrollableFilteredList(
			ENEstimateItem2PlanTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2PlanType. Получить список для просмотра по условию */
	public ENEstimateItem2PlanTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENEstimateItem2PlanTypeFilter filterObject = new ENEstimateItem2PlanTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENEstimateItem2PlanType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENEstimateItem2PlanTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimateItem2PlanType. Получить объект из списка */
	public ENEstimateItem2PlanTypeShort getShortObject(int code) {
		try {
			ENEstimateItem2PlanTypeDAO objectDAO = new ENEstimateItem2PlanTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2PlanType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
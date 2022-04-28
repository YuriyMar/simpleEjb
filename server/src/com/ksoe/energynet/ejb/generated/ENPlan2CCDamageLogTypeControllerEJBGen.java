
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlan2CCDamageLogTypeDAO;
import com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType;
import com.ksoe.energynet.valueobject.lists.ENPlan2CCDamageLogTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENPlan2CCDamageLogTypeShort;
import com.ksoe.energynet.valueobject.filter.ENPlan2CCDamageLogTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlan2CCDamageLogType;
 *
 */


public abstract class ENPlan2CCDamageLogTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlan2CCDamageLogTypeControllerEJBGen() {
	}

	/* ENPlan2CCDamageLogType. Добавить */
	public int add(ENPlan2CCDamageLogType object) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2CCDamageLogType. Удалить */
	public void remove(int code) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlan2CCDamageLogType. Изменить */
	public void save(ENPlan2CCDamageLogType object) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2CCDamageLogType. Получить объект */
	public ENPlan2CCDamageLogType getObject(int code) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2CCDamageLogType. Получить полный список */
	public ENPlan2CCDamageLogTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlan2CCDamageLogType. Получить список по фильтру */
	public ENPlan2CCDamageLogTypeShortList getFilteredList(
			ENPlan2CCDamageLogTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlan2CCDamageLogType. Получить список для просмотра */
	public ENPlan2CCDamageLogTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlan2CCDamageLogType. Получить список для просмотра по фильтру */
	public ENPlan2CCDamageLogTypeShortList getScrollableFilteredList(
			ENPlan2CCDamageLogTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2CCDamageLogType. Получить список для просмотра по условию */
	public ENPlan2CCDamageLogTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlan2CCDamageLogTypeFilter filterObject = new ENPlan2CCDamageLogTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlan2CCDamageLogType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlan2CCDamageLogTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2CCDamageLogType. Получить объект из списка */
	public ENPlan2CCDamageLogTypeShort getShortObject(int code) {
		try {
			ENPlan2CCDamageLogTypeDAO objectDAO = new ENPlan2CCDamageLogTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
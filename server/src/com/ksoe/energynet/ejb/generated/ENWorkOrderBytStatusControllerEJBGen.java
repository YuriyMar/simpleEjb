
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWorkOrderBytStatusDAO;
import com.ksoe.energynet.valueobject.ENWorkOrderBytStatus;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderBytStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENWorkOrderBytStatusShort;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWorkOrderBytStatus;
 *
 */


public abstract class ENWorkOrderBytStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWorkOrderBytStatusControllerEJBGen() {
	}

	/* ENWorkOrderBytStatus. Добавить */
	public int add(ENWorkOrderBytStatus object) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytStatus. Удалить */
	public void remove(int code) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWorkOrderBytStatus. Изменить */
	public void save(ENWorkOrderBytStatus object) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytStatus. Получить объект */
	public ENWorkOrderBytStatus getObject(int code) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytStatus. Получить полный список */
	public ENWorkOrderBytStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWorkOrderBytStatus. Получить список по фильтру */
	public ENWorkOrderBytStatusShortList getFilteredList(
			ENWorkOrderBytStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWorkOrderBytStatus. Получить список для просмотра */
	public ENWorkOrderBytStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWorkOrderBytStatus. Получить список для просмотра по фильтру */
	public ENWorkOrderBytStatusShortList getScrollableFilteredList(
			ENWorkOrderBytStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytStatus. Получить список для просмотра по условию */
	public ENWorkOrderBytStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWorkOrderBytStatusFilter filterObject = new ENWorkOrderBytStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWorkOrderBytStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWorkOrderBytStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytStatus. Получить объект из списка */
	public ENWorkOrderBytStatusShort getShortObject(int code) {
		try {
			ENWorkOrderBytStatusDAO objectDAO = new ENWorkOrderBytStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
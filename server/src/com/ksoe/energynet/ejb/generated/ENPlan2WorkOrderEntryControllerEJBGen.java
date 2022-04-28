
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlan2WorkOrderEntryDAO;
import com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry;
import com.ksoe.energynet.valueobject.lists.ENPlan2WorkOrderEntryShortList;
import com.ksoe.energynet.valueobject.brief.ENPlan2WorkOrderEntryShort;
import com.ksoe.energynet.valueobject.filter.ENPlan2WorkOrderEntryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlan2WorkOrderEntry;
 *
 */


public abstract class ENPlan2WorkOrderEntryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlan2WorkOrderEntryControllerEJBGen() {
	}

	/* ENPlan2WorkOrderEntry. Добавить */
	public int add(ENPlan2WorkOrderEntry object) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2WorkOrderEntry. Удалить */
	public void remove(int code) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlan2WorkOrderEntry. Изменить */
	public void save(ENPlan2WorkOrderEntry object) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2WorkOrderEntry. Получить объект */
	public ENPlan2WorkOrderEntry getObject(int code) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2WorkOrderEntry. Получить полный список */
	public ENPlan2WorkOrderEntryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlan2WorkOrderEntry. Получить список по фильтру */
	public ENPlan2WorkOrderEntryShortList getFilteredList(
			ENPlan2WorkOrderEntryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlan2WorkOrderEntry. Получить список для просмотра */
	public ENPlan2WorkOrderEntryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlan2WorkOrderEntry. Получить список для просмотра по фильтру */
	public ENPlan2WorkOrderEntryShortList getScrollableFilteredList(
			ENPlan2WorkOrderEntryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2WorkOrderEntry. Получить список для просмотра по условию */
	public ENPlan2WorkOrderEntryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlan2WorkOrderEntryFilter filterObject = new ENPlan2WorkOrderEntryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlan2WorkOrderEntry. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlan2WorkOrderEntryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlan2WorkOrderEntry. Получить объект из списка */
	public ENPlan2WorkOrderEntryShort getShortObject(int code) {
		try {
			ENPlan2WorkOrderEntryDAO objectDAO = new ENPlan2WorkOrderEntryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlan2WorkOrderEntry%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
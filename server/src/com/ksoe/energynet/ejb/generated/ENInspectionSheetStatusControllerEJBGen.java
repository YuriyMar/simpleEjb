
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInspectionSheetStatusDAO;
import com.ksoe.energynet.valueobject.ENInspectionSheetStatus;
import com.ksoe.energynet.valueobject.lists.ENInspectionSheetStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENInspectionSheetStatusShort;
import com.ksoe.energynet.valueobject.filter.ENInspectionSheetStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInspectionSheetStatus;
 *
 */


public abstract class ENInspectionSheetStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInspectionSheetStatusControllerEJBGen() {
	}

	/* ENInspectionSheetStatus. Добавить */
	public int add(ENInspectionSheetStatus object) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetStatus. Удалить */
	public void remove(int code) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInspectionSheetStatus. Изменить */
	public void save(ENInspectionSheetStatus object) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetStatus. Получить объект */
	public ENInspectionSheetStatus getObject(int code) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetStatus. Получить полный список */
	public ENInspectionSheetStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInspectionSheetStatus. Получить список по фильтру */
	public ENInspectionSheetStatusShortList getFilteredList(
			ENInspectionSheetStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInspectionSheetStatus. Получить список для просмотра */
	public ENInspectionSheetStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInspectionSheetStatus. Получить список для просмотра по фильтру */
	public ENInspectionSheetStatusShortList getScrollableFilteredList(
			ENInspectionSheetStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetStatus. Получить список для просмотра по условию */
	public ENInspectionSheetStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInspectionSheetStatusFilter filterObject = new ENInspectionSheetStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInspectionSheetStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInspectionSheetStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInspectionSheetStatus. Получить объект из списка */
	public ENInspectionSheetStatusShort getShortObject(int code) {
		try {
			ENInspectionSheetStatusDAO objectDAO = new ENInspectionSheetStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInspectionSheetStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
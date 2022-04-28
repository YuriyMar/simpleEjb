
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanwork2GeneralContractsDAO;
import com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts;
import com.ksoe.energynet.valueobject.lists.ENPlanwork2GeneralContractsShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanwork2GeneralContractsShort;
import com.ksoe.energynet.valueobject.filter.ENPlanwork2GeneralContractsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanwork2GeneralContracts;
 *
 */


public abstract class ENPlanwork2GeneralContractsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanwork2GeneralContractsControllerEJBGen() {
	}

	/* ENPlanwork2GeneralContracts. Добавить */
	public int add(ENPlanwork2GeneralContracts object) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanwork2GeneralContracts. Удалить */
	public void remove(int code) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanwork2GeneralContracts. Изменить */
	public void save(ENPlanwork2GeneralContracts object) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanwork2GeneralContracts. Получить объект */
	public ENPlanwork2GeneralContracts getObject(int code) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanwork2GeneralContracts. Получить полный список */
	public ENPlanwork2GeneralContractsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanwork2GeneralContracts. Получить список по фильтру */
	public ENPlanwork2GeneralContractsShortList getFilteredList(
			ENPlanwork2GeneralContractsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanwork2GeneralContracts. Получить список для просмотра */
	public ENPlanwork2GeneralContractsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanwork2GeneralContracts. Получить список для просмотра по фильтру */
	public ENPlanwork2GeneralContractsShortList getScrollableFilteredList(
			ENPlanwork2GeneralContractsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanwork2GeneralContracts. Получить список для просмотра по условию */
	public ENPlanwork2GeneralContractsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanwork2GeneralContractsFilter filterObject = new ENPlanwork2GeneralContractsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanwork2GeneralContracts. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanwork2GeneralContractsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanwork2GeneralContracts. Получить объект из списка */
	public ENPlanwork2GeneralContractsShort getShortObject(int code) {
		try {
			ENPlanwork2GeneralContractsDAO objectDAO = new ENPlanwork2GeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
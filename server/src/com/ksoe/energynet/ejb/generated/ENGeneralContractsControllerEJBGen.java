
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENGeneralContractsDAO;
import com.ksoe.energynet.valueobject.ENGeneralContracts;
import com.ksoe.energynet.valueobject.lists.ENGeneralContractsShortList;
import com.ksoe.energynet.valueobject.brief.ENGeneralContractsShort;
import com.ksoe.energynet.valueobject.filter.ENGeneralContractsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENGeneralContracts;
 *
 */


public abstract class ENGeneralContractsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENGeneralContractsControllerEJBGen() {
	}

	/* ENGeneralContracts. Добавить */
	public int add(ENGeneralContracts object) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENGeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeneralContracts. Удалить */
	public void remove(int code) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENGeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENGeneralContracts. Изменить */
	public void save(ENGeneralContracts object) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENGeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeneralContracts. Получить объект */
	public ENGeneralContracts getObject(int code) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeneralContracts%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeneralContracts. Получить полный список */
	public ENGeneralContractsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENGeneralContracts. Получить список по фильтру */
	public ENGeneralContractsShortList getFilteredList(
			ENGeneralContractsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENGeneralContracts. Получить список для просмотра */
	public ENGeneralContractsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENGeneralContracts. Получить список для просмотра по фильтру */
	public ENGeneralContractsShortList getScrollableFilteredList(
			ENGeneralContractsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENGeneralContracts%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeneralContracts. Получить список для просмотра по условию */
	public ENGeneralContractsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENGeneralContractsFilter filterObject = new ENGeneralContractsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENGeneralContracts. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENGeneralContractsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENGeneralContracts%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENGeneralContracts. Получить объект из списка */
	public ENGeneralContractsShort getShortObject(int code) {
		try {
			ENGeneralContractsDAO objectDAO = new ENGeneralContractsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENGeneralContracts%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
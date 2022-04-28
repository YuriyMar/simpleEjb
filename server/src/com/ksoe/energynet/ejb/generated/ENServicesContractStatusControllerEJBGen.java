
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesContractStatusDAO;
import com.ksoe.energynet.valueobject.ENServicesContractStatus;
import com.ksoe.energynet.valueobject.lists.ENServicesContractStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesContractStatusShort;
import com.ksoe.energynet.valueobject.filter.ENServicesContractStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesContractStatus;
 *
 */


public abstract class ENServicesContractStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesContractStatusControllerEJBGen() {
	}

	/* ENServicesContractStatus. Добавить */
	public int add(ENServicesContractStatus object) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContractStatus. Удалить */
	public void remove(int code) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesContractStatus. Изменить */
	public void save(ENServicesContractStatus object) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContractStatus. Получить объект */
	public ENServicesContractStatus getObject(int code) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContractStatus. Получить полный список */
	public ENServicesContractStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesContractStatus. Получить список по фильтру */
	public ENServicesContractStatusShortList getFilteredList(
			ENServicesContractStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesContractStatus. Получить список для просмотра */
	public ENServicesContractStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesContractStatus. Получить список для просмотра по фильтру */
	public ENServicesContractStatusShortList getScrollableFilteredList(
			ENServicesContractStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContractStatus. Получить список для просмотра по условию */
	public ENServicesContractStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesContractStatusFilter filterObject = new ENServicesContractStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesContractStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesContractStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContractStatus. Получить объект из списка */
	public ENServicesContractStatusShort getShortObject(int code) {
		try {
			ENServicesContractStatusDAO objectDAO = new ENServicesContractStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesContractStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
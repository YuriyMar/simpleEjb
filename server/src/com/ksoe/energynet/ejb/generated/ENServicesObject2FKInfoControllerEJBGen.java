
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesObject2FKInfoDAO;
import com.ksoe.energynet.valueobject.ENServicesObject2FKInfo;
import com.ksoe.energynet.valueobject.lists.ENServicesObject2FKInfoShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesObject2FKInfoShort;
import com.ksoe.energynet.valueobject.filter.ENServicesObject2FKInfoFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesObject2FKInfo;
 *
 */


public abstract class ENServicesObject2FKInfoControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesObject2FKInfoControllerEJBGen() {
	}

	/* ENServicesObject2FKInfo. Добавить */
	public int add(ENServicesObject2FKInfo object) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2FKInfo. Удалить */
	public void remove(int code) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesObject2FKInfo. Изменить */
	public void save(ENServicesObject2FKInfo object) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2FKInfo. Получить объект */
	public ENServicesObject2FKInfo getObject(int code) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2FKInfo. Получить полный список */
	public ENServicesObject2FKInfoShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesObject2FKInfo. Получить список по фильтру */
	public ENServicesObject2FKInfoShortList getFilteredList(
			ENServicesObject2FKInfoFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesObject2FKInfo. Получить список для просмотра */
	public ENServicesObject2FKInfoShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesObject2FKInfo. Получить список для просмотра по фильтру */
	public ENServicesObject2FKInfoShortList getScrollableFilteredList(
			ENServicesObject2FKInfoFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2FKInfo. Получить список для просмотра по условию */
	public ENServicesObject2FKInfoShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesObject2FKInfoFilter filterObject = new ENServicesObject2FKInfoFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesObject2FKInfo. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesObject2FKInfoFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2FKInfo. Получить объект из списка */
	public ENServicesObject2FKInfoShort getShortObject(int code) {
		try {
			ENServicesObject2FKInfoDAO objectDAO = new ENServicesObject2FKInfoDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObject2FKInfo%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
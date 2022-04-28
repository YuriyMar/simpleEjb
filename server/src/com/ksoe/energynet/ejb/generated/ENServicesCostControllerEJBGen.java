
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesCostDAO;
import com.ksoe.energynet.valueobject.ENServicesCost;
import com.ksoe.energynet.valueobject.lists.ENServicesCostShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesCostShort;
import com.ksoe.energynet.valueobject.filter.ENServicesCostFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesCost;
 *
 */


public abstract class ENServicesCostControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesCostControllerEJBGen() {
	}

	/* ENServicesCost. Добавить */
	public int add(ENServicesCost object) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesCost%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesCost. Удалить */
	public void remove(int code) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesCost%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesCost. Изменить */
	public void save(ENServicesCost object) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesCost%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesCost. Получить объект */
	public ENServicesCost getObject(int code) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesCost%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesCost. Получить полный список */
	public ENServicesCostShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesCost. Получить список по фильтру */
	public ENServicesCostShortList getFilteredList(
			ENServicesCostFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesCost. Получить список для просмотра */
	public ENServicesCostShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesCost. Получить список для просмотра по фильтру */
	public ENServicesCostShortList getScrollableFilteredList(
			ENServicesCostFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesCost%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesCost. Получить список для просмотра по условию */
	public ENServicesCostShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesCostFilter filterObject = new ENServicesCostFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesCost. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesCostFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesCost%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesCost. Получить объект из списка */
	public ENServicesCostShort getShortObject(int code) {
		try {
			ENServicesCostDAO objectDAO = new ENServicesCostDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesCost%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
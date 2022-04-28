
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWarrantTypeDAO;
import com.ksoe.energynet.valueobject.ENWarrantType;
import com.ksoe.energynet.valueobject.lists.ENWarrantTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENWarrantTypeShort;
import com.ksoe.energynet.valueobject.filter.ENWarrantTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWarrantType;
 *
 */


public abstract class ENWarrantTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWarrantTypeControllerEJBGen() {
	}

	/* ENWarrantType. Добавить */
	public int add(ENWarrantType object) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWarrantType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrantType. Удалить */
	public void remove(int code) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWarrantType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWarrantType. Изменить */
	public void save(ENWarrantType object) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWarrantType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrantType. Получить объект */
	public ENWarrantType getObject(int code) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrantType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrantType. Получить полный список */
	public ENWarrantTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWarrantType. Получить список по фильтру */
	public ENWarrantTypeShortList getFilteredList(
			ENWarrantTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWarrantType. Получить список для просмотра */
	public ENWarrantTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWarrantType. Получить список для просмотра по фильтру */
	public ENWarrantTypeShortList getScrollableFilteredList(
			ENWarrantTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWarrantType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrantType. Получить список для просмотра по условию */
	public ENWarrantTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWarrantTypeFilter filterObject = new ENWarrantTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWarrantType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWarrantTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWarrantType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWarrantType. Получить объект из списка */
	public ENWarrantTypeShort getShortObject(int code) {
		try {
			ENWarrantTypeDAO objectDAO = new ENWarrantTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWarrantType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
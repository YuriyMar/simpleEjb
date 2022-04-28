
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FINCurrencyDAO;
import com.ksoe.energynet.valueobject.FINCurrency;
import com.ksoe.energynet.valueobject.lists.FINCurrencyShortList;
import com.ksoe.energynet.valueobject.brief.FINCurrencyShort;
import com.ksoe.energynet.valueobject.filter.FINCurrencyFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FINCurrency;
 *
 */


public abstract class FINCurrencyControllerEJBGen extends
		GenericSessionStatelessBean {
	public FINCurrencyControllerEJBGen() {
	}

	/* FINCurrency. Добавить */
	public int add(FINCurrency object) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FINCurrency%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINCurrency. Удалить */
	public void remove(int code) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FINCurrency%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FINCurrency. Изменить */
	public void save(FINCurrency object) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FINCurrency%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINCurrency. Получить объект */
	public FINCurrency getObject(int code) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINCurrency%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINCurrency. Получить полный список */
	public FINCurrencyShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FINCurrency. Получить список по фильтру */
	public FINCurrencyShortList getFilteredList(
			FINCurrencyFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FINCurrency. Получить список для просмотра */
	public FINCurrencyShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FINCurrency. Получить список для просмотра по фильтру */
	public FINCurrencyShortList getScrollableFilteredList(
			FINCurrencyFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FINCurrency%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINCurrency. Получить список для просмотра по условию */
	public FINCurrencyShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FINCurrencyFilter filterObject = new FINCurrencyFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FINCurrency. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FINCurrencyFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FINCurrency%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINCurrency. Получить объект из списка */
	public FINCurrencyShort getShortObject(int code) {
		try {
			FINCurrencyDAO objectDAO = new FINCurrencyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINCurrency%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
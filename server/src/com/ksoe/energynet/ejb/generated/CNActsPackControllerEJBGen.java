
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.CNActsPackDAO;
import com.ksoe.energynet.valueobject.CNActsPack;
import com.ksoe.energynet.valueobject.lists.CNActsPackShortList;
import com.ksoe.energynet.valueobject.brief.CNActsPackShort;
import com.ksoe.energynet.valueobject.filter.CNActsPackFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for CNActsPack;
 *
 */


public abstract class CNActsPackControllerEJBGen extends
		GenericSessionStatelessBean {
	public CNActsPackControllerEJBGen() {
	}

	/* CNActsPack. Добавить */
	public int add(CNActsPack object) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.CNActsPack%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsPack. Удалить */
	public void remove(int code) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.CNActsPack%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* CNActsPack. Изменить */
	public void save(CNActsPack object) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.CNActsPack%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsPack. Получить объект */
	public CNActsPack getObject(int code) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNActsPack%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsPack. Получить полный список */
	public CNActsPackShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* CNActsPack. Получить список по фильтру */
	public CNActsPackShortList getFilteredList(
			CNActsPackFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* CNActsPack. Получить список для просмотра */
	public CNActsPackShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* CNActsPack. Получить список для просмотра по фильтру */
	public CNActsPackShortList getScrollableFilteredList(
			CNActsPackFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.CNActsPack%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsPack. Получить список для просмотра по условию */
	public CNActsPackShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		CNActsPackFilter filterObject = new CNActsPackFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* CNActsPack. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			CNActsPackFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.CNActsPack%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNActsPack. Получить объект из списка */
	public CNActsPackShort getShortObject(int code) {
		try {
			CNActsPackDAO objectDAO = new CNActsPackDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNActsPack%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
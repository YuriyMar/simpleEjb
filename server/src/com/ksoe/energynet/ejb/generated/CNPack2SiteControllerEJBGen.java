
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.CNPack2SiteDAO;
import com.ksoe.energynet.valueobject.CNPack2Site;
import com.ksoe.energynet.valueobject.lists.CNPack2SiteShortList;
import com.ksoe.energynet.valueobject.brief.CNPack2SiteShort;
import com.ksoe.energynet.valueobject.filter.CNPack2SiteFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for CNPack2Site;
 *
 */


public abstract class CNPack2SiteControllerEJBGen extends
		GenericSessionStatelessBean {
	public CNPack2SiteControllerEJBGen() {
	}

	/* CNPack2Site. Добавить */
	public int add(CNPack2Site object) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.CNPack2Site%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNPack2Site. Удалить */
	public void remove(int code) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.CNPack2Site%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* CNPack2Site. Изменить */
	public void save(CNPack2Site object) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.CNPack2Site%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNPack2Site. Получить объект */
	public CNPack2Site getObject(int code) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNPack2Site%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNPack2Site. Получить полный список */
	public CNPack2SiteShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* CNPack2Site. Получить список по фильтру */
	public CNPack2SiteShortList getFilteredList(
			CNPack2SiteFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* CNPack2Site. Получить список для просмотра */
	public CNPack2SiteShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* CNPack2Site. Получить список для просмотра по фильтру */
	public CNPack2SiteShortList getScrollableFilteredList(
			CNPack2SiteFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.CNPack2Site%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNPack2Site. Получить список для просмотра по условию */
	public CNPack2SiteShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		CNPack2SiteFilter filterObject = new CNPack2SiteFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* CNPack2Site. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			CNPack2SiteFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.CNPack2Site%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNPack2Site. Получить объект из списка */
	public CNPack2SiteShort getShortObject(int code) {
		try {
			CNPack2SiteDAO objectDAO = new CNPack2SiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNPack2Site%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
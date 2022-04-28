
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSiteDAO;
import com.ksoe.energynet.valueobject.ENSite;
import com.ksoe.energynet.valueobject.lists.ENSiteShortList;
import com.ksoe.energynet.valueobject.brief.ENSiteShort;
import com.ksoe.energynet.valueobject.filter.ENSiteFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSite;
 *
 */


public abstract class ENSiteControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSiteControllerEJBGen() {
	}

	/* ENSite. Добавить */
	public int add(ENSite object) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSite%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSite. Удалить */
	public void remove(int code) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSite%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSite. Изменить */
	public void save(ENSite object) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSite%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSite. Получить объект */
	public ENSite getObject(int code) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSite%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSite. Получить полный список */
	public ENSiteShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSite. Получить список по фильтру */
	public ENSiteShortList getFilteredList(
			ENSiteFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSite. Получить список для просмотра */
	public ENSiteShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSite. Получить список для просмотра по фильтру */
	public ENSiteShortList getScrollableFilteredList(
			ENSiteFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSite%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSite. Получить список для просмотра по условию */
	public ENSiteShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSiteFilter filterObject = new ENSiteFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSite. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSiteFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSite%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSite. Получить объект из списка */
	public ENSiteShort getShortObject(int code) {
		try {
			ENSiteDAO objectDAO = new ENSiteDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSite%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
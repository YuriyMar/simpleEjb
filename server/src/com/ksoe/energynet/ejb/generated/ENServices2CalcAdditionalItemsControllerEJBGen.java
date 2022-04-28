
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServices2CalcAdditionalItemsDAO;
import com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems;
import com.ksoe.energynet.valueobject.lists.ENServices2CalcAdditionalItemsShortList;
import com.ksoe.energynet.valueobject.brief.ENServices2CalcAdditionalItemsShort;
import com.ksoe.energynet.valueobject.filter.ENServices2CalcAdditionalItemsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServices2CalcAdditionalItems;
 *
 */


public abstract class ENServices2CalcAdditionalItemsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServices2CalcAdditionalItemsControllerEJBGen() {
	}

	/* ENServices2CalcAdditionalItems. Добавить */
	public int add(ENServices2CalcAdditionalItems object) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2CalcAdditionalItems. Удалить */
	public void remove(int code) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServices2CalcAdditionalItems. Изменить */
	public void save(ENServices2CalcAdditionalItems object) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2CalcAdditionalItems. Получить объект */
	public ENServices2CalcAdditionalItems getObject(int code) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2CalcAdditionalItems. Получить полный список */
	public ENServices2CalcAdditionalItemsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServices2CalcAdditionalItems. Получить список по фильтру */
	public ENServices2CalcAdditionalItemsShortList getFilteredList(
			ENServices2CalcAdditionalItemsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServices2CalcAdditionalItems. Получить список для просмотра */
	public ENServices2CalcAdditionalItemsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServices2CalcAdditionalItems. Получить список для просмотра по фильтру */
	public ENServices2CalcAdditionalItemsShortList getScrollableFilteredList(
			ENServices2CalcAdditionalItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2CalcAdditionalItems. Получить список для просмотра по условию */
	public ENServices2CalcAdditionalItemsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServices2CalcAdditionalItemsFilter filterObject = new ENServices2CalcAdditionalItemsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServices2CalcAdditionalItems. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServices2CalcAdditionalItemsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServices2CalcAdditionalItems. Получить объект из списка */
	public ENServices2CalcAdditionalItemsShort getShortObject(int code) {
		try {
			ENServices2CalcAdditionalItemsDAO objectDAO = new ENServices2CalcAdditionalItemsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServices2CalcAdditionalItems%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
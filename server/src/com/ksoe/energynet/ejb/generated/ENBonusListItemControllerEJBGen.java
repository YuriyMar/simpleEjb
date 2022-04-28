
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBonusListItemDAO;
import com.ksoe.energynet.valueobject.ENBonusListItem;
import com.ksoe.energynet.valueobject.lists.ENBonusListItemShortList;
import com.ksoe.energynet.valueobject.brief.ENBonusListItemShort;
import com.ksoe.energynet.valueobject.filter.ENBonusListItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBonusListItem;
 *
 */


public abstract class ENBonusListItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBonusListItemControllerEJBGen() {
	}

	/* ENBonusListItem. Добавить */
	public int add(ENBonusListItem object) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBonusListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItem. Удалить */
	public void remove(int code) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBonusListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBonusListItem. Изменить */
	public void save(ENBonusListItem object) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBonusListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItem. Получить объект */
	public ENBonusListItem getObject(int code) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItem. Получить полный список */
	public ENBonusListItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBonusListItem. Получить список по фильтру */
	public ENBonusListItemShortList getFilteredList(
			ENBonusListItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBonusListItem. Получить список для просмотра */
	public ENBonusListItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBonusListItem. Получить список для просмотра по фильтру */
	public ENBonusListItemShortList getScrollableFilteredList(
			ENBonusListItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBonusListItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItem. Получить список для просмотра по условию */
	public ENBonusListItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBonusListItemFilter filterObject = new ENBonusListItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBonusListItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBonusListItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBonusListItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItem. Получить объект из списка */
	public ENBonusListItemShort getShortObject(int code) {
		try {
			ENBonusListItemDAO objectDAO = new ENBonusListItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
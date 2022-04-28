
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBonusListItemStatusDAO;
import com.ksoe.energynet.valueobject.ENBonusListItemStatus;
import com.ksoe.energynet.valueobject.lists.ENBonusListItemStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENBonusListItemStatusShort;
import com.ksoe.energynet.valueobject.filter.ENBonusListItemStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBonusListItemStatus;
 *
 */


public abstract class ENBonusListItemStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBonusListItemStatusControllerEJBGen() {
	}

	/* ENBonusListItemStatus. Добавить */
	public int add(ENBonusListItemStatus object) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItemStatus. Удалить */
	public void remove(int code) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBonusListItemStatus. Изменить */
	public void save(ENBonusListItemStatus object) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItemStatus. Получить объект */
	public ENBonusListItemStatus getObject(int code) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItemStatus. Получить полный список */
	public ENBonusListItemStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBonusListItemStatus. Получить список по фильтру */
	public ENBonusListItemStatusShortList getFilteredList(
			ENBonusListItemStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBonusListItemStatus. Получить список для просмотра */
	public ENBonusListItemStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBonusListItemStatus. Получить список для просмотра по фильтру */
	public ENBonusListItemStatusShortList getScrollableFilteredList(
			ENBonusListItemStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItemStatus. Получить список для просмотра по условию */
	public ENBonusListItemStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBonusListItemStatusFilter filterObject = new ENBonusListItemStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBonusListItemStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBonusListItemStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListItemStatus. Получить объект из списка */
	public ENBonusListItemStatusShort getShortObject(int code) {
		try {
			ENBonusListItemStatusDAO objectDAO = new ENBonusListItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListItemStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
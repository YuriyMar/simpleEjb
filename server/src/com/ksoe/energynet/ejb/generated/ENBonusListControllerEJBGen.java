
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBonusListDAO;
import com.ksoe.energynet.valueobject.ENBonusList;
import com.ksoe.energynet.valueobject.lists.ENBonusListShortList;
import com.ksoe.energynet.valueobject.brief.ENBonusListShort;
import com.ksoe.energynet.valueobject.filter.ENBonusListFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBonusList;
 *
 */


public abstract class ENBonusListControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBonusListControllerEJBGen() {
	}

	/* ENBonusList. Добавить */
	public int add(ENBonusList object) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBonusList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusList. Удалить */
	public void remove(int code) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBonusList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBonusList. Изменить */
	public void save(ENBonusList object) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBonusList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusList. Получить объект */
	public ENBonusList getObject(int code) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusList%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusList. Получить полный список */
	public ENBonusListShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBonusList. Получить список по фильтру */
	public ENBonusListShortList getFilteredList(
			ENBonusListFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBonusList. Получить список для просмотра */
	public ENBonusListShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBonusList. Получить список для просмотра по фильтру */
	public ENBonusListShortList getScrollableFilteredList(
			ENBonusListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBonusList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusList. Получить список для просмотра по условию */
	public ENBonusListShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBonusListFilter filterObject = new ENBonusListFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBonusList. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBonusListFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBonusList%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusList. Получить объект из списка */
	public ENBonusListShort getShortObject(int code) {
		try {
			ENBonusListDAO objectDAO = new ENBonusListDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusList%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
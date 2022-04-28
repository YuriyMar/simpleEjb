
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBonusListStatusDAO;
import com.ksoe.energynet.valueobject.ENBonusListStatus;
import com.ksoe.energynet.valueobject.lists.ENBonusListStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENBonusListStatusShort;
import com.ksoe.energynet.valueobject.filter.ENBonusListStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBonusListStatus;
 *
 */


public abstract class ENBonusListStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBonusListStatusControllerEJBGen() {
	}

	/* ENBonusListStatus. Добавить */
	public int add(ENBonusListStatus object) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBonusListStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListStatus. Удалить */
	public void remove(int code) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBonusListStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBonusListStatus. Изменить */
	public void save(ENBonusListStatus object) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBonusListStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListStatus. Получить объект */
	public ENBonusListStatus getObject(int code) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListStatus. Получить полный список */
	public ENBonusListStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBonusListStatus. Получить список по фильтру */
	public ENBonusListStatusShortList getFilteredList(
			ENBonusListStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBonusListStatus. Получить список для просмотра */
	public ENBonusListStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBonusListStatus. Получить список для просмотра по фильтру */
	public ENBonusListStatusShortList getScrollableFilteredList(
			ENBonusListStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBonusListStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListStatus. Получить список для просмотра по условию */
	public ENBonusListStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBonusListStatusFilter filterObject = new ENBonusListStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBonusListStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBonusListStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBonusListStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBonusListStatus. Получить объект из списка */
	public ENBonusListStatusShort getShortObject(int code) {
		try {
			ENBonusListStatusDAO objectDAO = new ENBonusListStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBonusListStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
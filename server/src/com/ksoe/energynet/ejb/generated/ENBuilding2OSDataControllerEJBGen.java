
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuilding2OSDataDAO;
import com.ksoe.energynet.valueobject.ENBuilding2OSData;
import com.ksoe.energynet.valueobject.lists.ENBuilding2OSDataShortList;
import com.ksoe.energynet.valueobject.brief.ENBuilding2OSDataShort;
import com.ksoe.energynet.valueobject.filter.ENBuilding2OSDataFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuilding2OSData;
 *
 */


public abstract class ENBuilding2OSDataControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuilding2OSDataControllerEJBGen() {
	}

	/* ENBuilding2OSData. Добавить */
	public int add(ENBuilding2OSData object) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2OSData. Удалить */
	public void remove(int code) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuilding2OSData. Изменить */
	public void save(ENBuilding2OSData object) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2OSData. Получить объект */
	public ENBuilding2OSData getObject(int code) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2OSData. Получить полный список */
	public ENBuilding2OSDataShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuilding2OSData. Получить список по фильтру */
	public ENBuilding2OSDataShortList getFilteredList(
			ENBuilding2OSDataFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuilding2OSData. Получить список для просмотра */
	public ENBuilding2OSDataShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuilding2OSData. Получить список для просмотра по фильтру */
	public ENBuilding2OSDataShortList getScrollableFilteredList(
			ENBuilding2OSDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2OSData. Получить список для просмотра по условию */
	public ENBuilding2OSDataShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuilding2OSDataFilter filterObject = new ENBuilding2OSDataFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuilding2OSData. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuilding2OSDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2OSData. Получить объект из списка */
	public ENBuilding2OSDataShort getShortObject(int code) {
		try {
			ENBuilding2OSDataDAO objectDAO = new ENBuilding2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2OSData%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
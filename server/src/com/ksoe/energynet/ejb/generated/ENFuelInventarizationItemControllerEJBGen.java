
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelInventarizationItemDAO;
import com.ksoe.energynet.valueobject.ENFuelInventarizationItem;
import com.ksoe.energynet.valueobject.lists.ENFuelInventarizationItemShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelInventarizationItemShort;
import com.ksoe.energynet.valueobject.filter.ENFuelInventarizationItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelInventarizationItem;
 *
 */


public abstract class ENFuelInventarizationItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelInventarizationItemControllerEJBGen() {
	}

	/* ENFuelInventarizationItem. Добавить */
	public int add(ENFuelInventarizationItem object) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationItem. Удалить */
	public void remove(int code) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelInventarizationItem. Изменить */
	public void save(ENFuelInventarizationItem object) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationItem. Получить объект */
	public ENFuelInventarizationItem getObject(int code) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationItem. Получить полный список */
	public ENFuelInventarizationItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelInventarizationItem. Получить список по фильтру */
	public ENFuelInventarizationItemShortList getFilteredList(
			ENFuelInventarizationItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelInventarizationItem. Получить список для просмотра */
	public ENFuelInventarizationItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelInventarizationItem. Получить список для просмотра по фильтру */
	public ENFuelInventarizationItemShortList getScrollableFilteredList(
			ENFuelInventarizationItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationItem. Получить список для просмотра по условию */
	public ENFuelInventarizationItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelInventarizationItemFilter filterObject = new ENFuelInventarizationItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelInventarizationItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelInventarizationItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarizationItem. Получить объект из списка */
	public ENFuelInventarizationItemShort getShortObject(int code) {
		try {
			ENFuelInventarizationItemDAO objectDAO = new ENFuelInventarizationItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarizationItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
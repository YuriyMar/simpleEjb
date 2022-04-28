
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2022 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelCardDAO;
import com.ksoe.energynet.valueobject.ENFuelCard;
import com.ksoe.energynet.valueobject.lists.ENFuelCardShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelCardShort;
import com.ksoe.energynet.valueobject.filter.ENFuelCardFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelCard;
 *
 */


public abstract class ENFuelCardControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelCardControllerEJBGen() {
	}

	/* ENFuelCard. Добавить */
	public int add(ENFuelCard object) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCard. Удалить */
	public void remove(int code) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelCard. Изменить */
	public void save(ENFuelCard object) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCard. Получить объект */
	public ENFuelCard getObject(int code) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCard. Получить полный список */
	public ENFuelCardShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelCard. Получить список по фильтру */
	public ENFuelCardShortList getFilteredList(
			ENFuelCardFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelCard. Получить список для просмотра */
	public ENFuelCardShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelCard. Получить список для просмотра по фильтру */
	public ENFuelCardShortList getScrollableFilteredList(
			ENFuelCardFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelCard%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCard. Получить список для просмотра по условию */
	public ENFuelCardShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelCardFilter filterObject = new ENFuelCardFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelCard. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelCardFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelCard%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelCard. Получить объект из списка */
	public ENFuelCardShort getShortObject(int code) {
		try {
			ENFuelCardDAO objectDAO = new ENFuelCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelCard%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
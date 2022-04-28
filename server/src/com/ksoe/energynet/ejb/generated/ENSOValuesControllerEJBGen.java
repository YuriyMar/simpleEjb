
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOValuesDAO;
import com.ksoe.energynet.valueobject.ENSOValues;
import com.ksoe.energynet.valueobject.lists.ENSOValuesShortList;
import com.ksoe.energynet.valueobject.brief.ENSOValuesShort;
import com.ksoe.energynet.valueobject.filter.ENSOValuesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOValues;
 *
 */


public abstract class ENSOValuesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOValuesControllerEJBGen() {
	}

	/* ENSOValues. Добавить */
	public int add(ENSOValues object) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValues. Удалить */
	public void remove(int code) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOValues. Изменить */
	public void save(ENSOValues object) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValues. Получить объект */
	public ENSOValues getObject(int code) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValues. Получить полный список */
	public ENSOValuesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOValues. Получить список по фильтру */
	public ENSOValuesShortList getFilteredList(
			ENSOValuesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOValues. Получить список для просмотра */
	public ENSOValuesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOValues. Получить список для просмотра по фильтру */
	public ENSOValuesShortList getScrollableFilteredList(
			ENSOValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValues. Получить список для просмотра по условию */
	public ENSOValuesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOValuesFilter filterObject = new ENSOValuesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOValues. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValues. Получить объект из списка */
	public ENSOValuesShort getShortObject(int code) {
		try {
			ENSOValuesDAO objectDAO = new ENSOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValues%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
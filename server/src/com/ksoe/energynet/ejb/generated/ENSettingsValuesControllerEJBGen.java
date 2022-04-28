
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSettingsValuesDAO;
import com.ksoe.energynet.valueobject.ENSettingsValues;
import com.ksoe.energynet.valueobject.lists.ENSettingsValuesShortList;
import com.ksoe.energynet.valueobject.brief.ENSettingsValuesShort;
import com.ksoe.energynet.valueobject.filter.ENSettingsValuesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSettingsValues;
 *
 */


public abstract class ENSettingsValuesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSettingsValuesControllerEJBGen() {
	}

	/* ENSettingsValues. Добавить */
	public int add(ENSettingsValues object) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSettingsValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingsValues. Удалить */
	public void remove(int code) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSettingsValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSettingsValues. Изменить */
	public void save(ENSettingsValues object) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSettingsValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingsValues. Получить объект */
	public ENSettingsValues getObject(int code) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettingsValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingsValues. Получить полный список */
	public ENSettingsValuesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSettingsValues. Получить список по фильтру */
	public ENSettingsValuesShortList getFilteredList(
			ENSettingsValuesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSettingsValues. Получить список для просмотра */
	public ENSettingsValuesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSettingsValues. Получить список для просмотра по фильтру */
	public ENSettingsValuesShortList getScrollableFilteredList(
			ENSettingsValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSettingsValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingsValues. Получить список для просмотра по условию */
	public ENSettingsValuesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSettingsValuesFilter filterObject = new ENSettingsValuesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSettingsValues. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSettingsValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSettingsValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingsValues. Получить объект из списка */
	public ENSettingsValuesShort getShortObject(int code) {
		try {
			ENSettingsValuesDAO objectDAO = new ENSettingsValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettingsValues%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
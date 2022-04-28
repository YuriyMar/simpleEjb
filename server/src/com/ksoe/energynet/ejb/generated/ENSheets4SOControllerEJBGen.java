
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSheets4SODAO;
import com.ksoe.energynet.valueobject.ENSheets4SO;
import com.ksoe.energynet.valueobject.lists.ENSheets4SOShortList;
import com.ksoe.energynet.valueobject.brief.ENSheets4SOShort;
import com.ksoe.energynet.valueobject.filter.ENSheets4SOFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSheets4SO;
 *
 */


public abstract class ENSheets4SOControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSheets4SOControllerEJBGen() {
	}

	/* ENSheets4SO. Добавить */
	public int add(ENSheets4SO object) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSheets4SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SO. Удалить */
	public void remove(int code) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSheets4SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSheets4SO. Изменить */
	public void save(ENSheets4SO object) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSheets4SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SO. Получить объект */
	public ENSheets4SO getObject(int code) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SO. Получить полный список */
	public ENSheets4SOShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSheets4SO. Получить список по фильтру */
	public ENSheets4SOShortList getFilteredList(
			ENSheets4SOFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSheets4SO. Получить список для просмотра */
	public ENSheets4SOShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSheets4SO. Получить список для просмотра по фильтру */
	public ENSheets4SOShortList getScrollableFilteredList(
			ENSheets4SOFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSheets4SO%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SO. Получить список для просмотра по условию */
	public ENSheets4SOShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSheets4SOFilter filterObject = new ENSheets4SOFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSheets4SO. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSheets4SOFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSheets4SO%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SO. Получить объект из списка */
	public ENSheets4SOShort getShortObject(int code) {
		try {
			ENSheets4SODAO objectDAO = new ENSheets4SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SO%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
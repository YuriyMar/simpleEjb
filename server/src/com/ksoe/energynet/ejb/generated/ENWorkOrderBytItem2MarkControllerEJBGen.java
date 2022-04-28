
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWorkOrderBytItem2MarkDAO;
import com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderBytItem2MarkShortList;
import com.ksoe.energynet.valueobject.brief.ENWorkOrderBytItem2MarkShort;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytItem2MarkFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWorkOrderBytItem2Mark;
 *
 */


public abstract class ENWorkOrderBytItem2MarkControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWorkOrderBytItem2MarkControllerEJBGen() {
	}

	/* ENWorkOrderBytItem2Mark. Добавить */
	public int add(ENWorkOrderBytItem2Mark object) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem2Mark. Удалить */
	public void remove(int code) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWorkOrderBytItem2Mark. Изменить */
	public void save(ENWorkOrderBytItem2Mark object) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem2Mark. Получить объект */
	public ENWorkOrderBytItem2Mark getObject(int code) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem2Mark. Получить полный список */
	public ENWorkOrderBytItem2MarkShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWorkOrderBytItem2Mark. Получить список по фильтру */
	public ENWorkOrderBytItem2MarkShortList getFilteredList(
			ENWorkOrderBytItem2MarkFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWorkOrderBytItem2Mark. Получить список для просмотра */
	public ENWorkOrderBytItem2MarkShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWorkOrderBytItem2Mark. Получить список для просмотра по фильтру */
	public ENWorkOrderBytItem2MarkShortList getScrollableFilteredList(
			ENWorkOrderBytItem2MarkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem2Mark. Получить список для просмотра по условию */
	public ENWorkOrderBytItem2MarkShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWorkOrderBytItem2MarkFilter filterObject = new ENWorkOrderBytItem2MarkFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWorkOrderBytItem2Mark. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWorkOrderBytItem2MarkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem2Mark. Получить объект из списка */
	public ENWorkOrderBytItem2MarkShort getShortObject(int code) {
		try {
			ENWorkOrderBytItem2MarkDAO objectDAO = new ENWorkOrderBytItem2MarkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem2Mark%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
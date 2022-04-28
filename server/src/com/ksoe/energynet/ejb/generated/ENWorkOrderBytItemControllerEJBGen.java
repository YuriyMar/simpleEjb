
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENWorkOrderBytItemDAO;
import com.ksoe.energynet.valueobject.ENWorkOrderBytItem;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderBytItemShortList;
import com.ksoe.energynet.valueobject.brief.ENWorkOrderBytItemShort;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENWorkOrderBytItem;
 *
 */


public abstract class ENWorkOrderBytItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENWorkOrderBytItemControllerEJBGen() {
	}

	/* ENWorkOrderBytItem. Добавить */
	public int add(ENWorkOrderBytItem object) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem. Удалить */
	public void remove(int code) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENWorkOrderBytItem. Изменить */
	public void save(ENWorkOrderBytItem object) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem. Получить объект */
	public ENWorkOrderBytItem getObject(int code) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem. Получить полный список */
	public ENWorkOrderBytItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENWorkOrderBytItem. Получить список по фильтру */
	public ENWorkOrderBytItemShortList getFilteredList(
			ENWorkOrderBytItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENWorkOrderBytItem. Получить список для просмотра */
	public ENWorkOrderBytItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENWorkOrderBytItem. Получить список для просмотра по фильтру */
	public ENWorkOrderBytItemShortList getScrollableFilteredList(
			ENWorkOrderBytItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem. Получить список для просмотра по условию */
	public ENWorkOrderBytItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENWorkOrderBytItemFilter filterObject = new ENWorkOrderBytItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENWorkOrderBytItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENWorkOrderBytItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENWorkOrderBytItem. Получить объект из списка */
	public ENWorkOrderBytItemShort getShortObject(int code) {
		try {
			ENWorkOrderBytItemDAO objectDAO = new ENWorkOrderBytItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderBytItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
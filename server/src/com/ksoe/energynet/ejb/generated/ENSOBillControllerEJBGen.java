
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOBillDAO;
import com.ksoe.energynet.valueobject.ENSOBill;
import com.ksoe.energynet.valueobject.lists.ENSOBillShortList;
import com.ksoe.energynet.valueobject.brief.ENSOBillShort;
import com.ksoe.energynet.valueobject.filter.ENSOBillFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOBill;
 *
 */


public abstract class ENSOBillControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOBillControllerEJBGen() {
	}

	/* ENSOBill. Добавить */
	public int add(ENSOBill object) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOBill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOBill. Удалить */
	public void remove(int code) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOBill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOBill. Изменить */
	public void save(ENSOBill object) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOBill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOBill. Получить объект */
	public ENSOBill getObject(int code) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOBill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOBill. Получить полный список */
	public ENSOBillShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOBill. Получить список по фильтру */
	public ENSOBillShortList getFilteredList(
			ENSOBillFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOBill. Получить список для просмотра */
	public ENSOBillShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOBill. Получить список для просмотра по фильтру */
	public ENSOBillShortList getScrollableFilteredList(
			ENSOBillFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOBill%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOBill. Получить список для просмотра по условию */
	public ENSOBillShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOBillFilter filterObject = new ENSOBillFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOBill. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOBillFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOBill%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOBill. Получить объект из списка */
	public ENSOBillShort getShortObject(int code) {
		try {
			ENSOBillDAO objectDAO = new ENSOBillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOBill%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
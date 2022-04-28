
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOPayBillProvDAO;
import com.ksoe.energynet.valueobject.ENSOPayBillProv;
import com.ksoe.energynet.valueobject.lists.ENSOPayBillProvShortList;
import com.ksoe.energynet.valueobject.brief.ENSOPayBillProvShort;
import com.ksoe.energynet.valueobject.filter.ENSOPayBillProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOPayBillProv;
 *
 */


public abstract class ENSOPayBillProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOPayBillProvControllerEJBGen() {
	}

	/* ENSOPayBillProv. Добавить */
	public int add(ENSOPayBillProv object) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOPayBillProv. Удалить */
	public void remove(int code) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOPayBillProv. Изменить */
	public void save(ENSOPayBillProv object) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOPayBillProv. Получить объект */
	public ENSOPayBillProv getObject(int code) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOPayBillProv. Получить полный список */
	public ENSOPayBillProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOPayBillProv. Получить список по фильтру */
	public ENSOPayBillProvShortList getFilteredList(
			ENSOPayBillProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOPayBillProv. Получить список для просмотра */
	public ENSOPayBillProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOPayBillProv. Получить список для просмотра по фильтру */
	public ENSOPayBillProvShortList getScrollableFilteredList(
			ENSOPayBillProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOPayBillProv. Получить список для просмотра по условию */
	public ENSOPayBillProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOPayBillProvFilter filterObject = new ENSOPayBillProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOPayBillProv. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOPayBillProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOPayBillProv. Получить объект из списка */
	public ENSOPayBillProvShort getShortObject(int code) {
		try {
			ENSOPayBillProvDAO objectDAO = new ENSOPayBillProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOPayBillProv%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
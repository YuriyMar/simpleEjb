
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActInvest2DFDocDAO;
import com.ksoe.energynet.valueobject.ENActInvest2DFDoc;
import com.ksoe.energynet.valueobject.lists.ENActInvest2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.ENActInvest2DFDocShort;
import com.ksoe.energynet.valueobject.filter.ENActInvest2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActInvest2DFDoc;
 *
 */


public abstract class ENActInvest2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActInvest2DFDocControllerEJBGen() {
	}

	/* ENActInvest2DFDoc. Добавить */
	public int add(ENActInvest2DFDoc object) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvest2DFDoc. Удалить */
	public void remove(int code) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActInvest2DFDoc. Изменить */
	public void save(ENActInvest2DFDoc object) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvest2DFDoc. Получить объект */
	public ENActInvest2DFDoc getObject(int code) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvest2DFDoc. Получить полный список */
	public ENActInvest2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActInvest2DFDoc. Получить список по фильтру */
	public ENActInvest2DFDocShortList getFilteredList(
			ENActInvest2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActInvest2DFDoc. Получить список для просмотра */
	public ENActInvest2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActInvest2DFDoc. Получить список для просмотра по фильтру */
	public ENActInvest2DFDocShortList getScrollableFilteredList(
			ENActInvest2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvest2DFDoc. Получить список для просмотра по условию */
	public ENActInvest2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActInvest2DFDocFilter filterObject = new ENActInvest2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActInvest2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActInvest2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvest2DFDoc. Получить объект из списка */
	public ENActInvest2DFDocShort getShortObject(int code) {
		try {
			ENActInvest2DFDocDAO objectDAO = new ENActInvest2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActInvest2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
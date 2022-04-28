
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2DFDocDAO;
import com.ksoe.energynet.valueobject.ENAct2DFDoc;
import com.ksoe.energynet.valueobject.lists.ENAct2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2DFDocShort;
import com.ksoe.energynet.valueobject.filter.ENAct2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2DFDoc;
 *
 */


public abstract class ENAct2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2DFDocControllerEJBGen() {
	}

	/* ENAct2DFDoc. Добавить */
	public int add(ENAct2DFDoc object) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDoc. Удалить */
	public void remove(int code) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2DFDoc. Изменить */
	public void save(ENAct2DFDoc object) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDoc. Получить объект */
	public ENAct2DFDoc getObject(int code) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDoc. Получить полный список */
	public ENAct2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2DFDoc. Получить список по фильтру */
	public ENAct2DFDocShortList getFilteredList(
			ENAct2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2DFDoc. Получить список для просмотра */
	public ENAct2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2DFDoc. Получить список для просмотра по фильтру */
	public ENAct2DFDocShortList getScrollableFilteredList(
			ENAct2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDoc. Получить список для просмотра по условию */
	public ENAct2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2DFDocFilter filterObject = new ENAct2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDoc. Получить объект из списка */
	public ENAct2DFDocShort getShortObject(int code) {
		try {
			ENAct2DFDocDAO objectDAO = new ENAct2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
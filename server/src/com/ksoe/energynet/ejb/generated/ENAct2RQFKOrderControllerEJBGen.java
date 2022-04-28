
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2RQFKOrderDAO;
import com.ksoe.energynet.valueobject.ENAct2RQFKOrder;
import com.ksoe.energynet.valueobject.lists.ENAct2RQFKOrderShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2RQFKOrderShort;
import com.ksoe.energynet.valueobject.filter.ENAct2RQFKOrderFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2RQFKOrder;
 *
 */


public abstract class ENAct2RQFKOrderControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2RQFKOrderControllerEJBGen() {
	}

	/* ENAct2RQFKOrder. Добавить */
	public int add(ENAct2RQFKOrder object) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrder. Удалить */
	public void remove(int code) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2RQFKOrder. Изменить */
	public void save(ENAct2RQFKOrder object) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrder. Получить объект */
	public ENAct2RQFKOrder getObject(int code) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrder. Получить полный список */
	public ENAct2RQFKOrderShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2RQFKOrder. Получить список по фильтру */
	public ENAct2RQFKOrderShortList getFilteredList(
			ENAct2RQFKOrderFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2RQFKOrder. Получить список для просмотра */
	public ENAct2RQFKOrderShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2RQFKOrder. Получить список для просмотра по фильтру */
	public ENAct2RQFKOrderShortList getScrollableFilteredList(
			ENAct2RQFKOrderFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrder. Получить список для просмотра по условию */
	public ENAct2RQFKOrderShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2RQFKOrderFilter filterObject = new ENAct2RQFKOrderFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2RQFKOrder. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2RQFKOrderFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrder. Получить объект из списка */
	public ENAct2RQFKOrderShort getShortObject(int code) {
		try {
			ENAct2RQFKOrderDAO objectDAO = new ENAct2RQFKOrderDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2RQFKOrder%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
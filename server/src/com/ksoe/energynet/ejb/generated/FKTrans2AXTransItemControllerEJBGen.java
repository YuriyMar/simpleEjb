
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FKTrans2AXTransItemDAO;
import com.ksoe.energynet.valueobject.FKTrans2AXTransItem;
import com.ksoe.energynet.valueobject.lists.FKTrans2AXTransItemShortList;
import com.ksoe.energynet.valueobject.brief.FKTrans2AXTransItemShort;
import com.ksoe.energynet.valueobject.filter.FKTrans2AXTransItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FKTrans2AXTransItem;
 *
 */


public abstract class FKTrans2AXTransItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public FKTrans2AXTransItemControllerEJBGen() {
	}

	/* FKTrans2AXTransItem. Добавить */
	public int add(FKTrans2AXTransItem object) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTransItem. Удалить */
	public void remove(int code) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FKTrans2AXTransItem. Изменить */
	public void save(FKTrans2AXTransItem object) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTransItem. Получить объект */
	public FKTrans2AXTransItem getObject(int code) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTransItem. Получить полный список */
	public FKTrans2AXTransItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FKTrans2AXTransItem. Получить список по фильтру */
	public FKTrans2AXTransItemShortList getFilteredList(
			FKTrans2AXTransItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FKTrans2AXTransItem. Получить список для просмотра */
	public FKTrans2AXTransItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FKTrans2AXTransItem. Получить список для просмотра по фильтру */
	public FKTrans2AXTransItemShortList getScrollableFilteredList(
			FKTrans2AXTransItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTransItem. Получить список для просмотра по условию */
	public FKTrans2AXTransItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FKTrans2AXTransItemFilter filterObject = new FKTrans2AXTransItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FKTrans2AXTransItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FKTrans2AXTransItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTransItem. Получить объект из списка */
	public FKTrans2AXTransItemShort getShortObject(int code) {
		try {
			FKTrans2AXTransItemDAO objectDAO = new FKTrans2AXTransItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FKTrans2AXTransItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
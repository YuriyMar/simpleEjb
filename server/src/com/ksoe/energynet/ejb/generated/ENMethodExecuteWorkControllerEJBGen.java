
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENMethodExecuteWorkDAO;
import com.ksoe.energynet.valueobject.ENMethodExecuteWork;
import com.ksoe.energynet.valueobject.lists.ENMethodExecuteWorkShortList;
import com.ksoe.energynet.valueobject.brief.ENMethodExecuteWorkShort;
import com.ksoe.energynet.valueobject.filter.ENMethodExecuteWorkFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENMethodExecuteWork;
 *
 */


public abstract class ENMethodExecuteWorkControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENMethodExecuteWorkControllerEJBGen() {
	}

	/* ENMethodExecuteWork. Добавить */
	public int add(ENMethodExecuteWork object) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMethodExecuteWork. Удалить */
	public void remove(int code) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENMethodExecuteWork. Изменить */
	public void save(ENMethodExecuteWork object) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMethodExecuteWork. Получить объект */
	public ENMethodExecuteWork getObject(int code) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMethodExecuteWork. Получить полный список */
	public ENMethodExecuteWorkShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENMethodExecuteWork. Получить список по фильтру */
	public ENMethodExecuteWorkShortList getFilteredList(
			ENMethodExecuteWorkFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENMethodExecuteWork. Получить список для просмотра */
	public ENMethodExecuteWorkShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENMethodExecuteWork. Получить список для просмотра по фильтру */
	public ENMethodExecuteWorkShortList getScrollableFilteredList(
			ENMethodExecuteWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMethodExecuteWork. Получить список для просмотра по условию */
	public ENMethodExecuteWorkShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENMethodExecuteWorkFilter filterObject = new ENMethodExecuteWorkFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENMethodExecuteWork. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENMethodExecuteWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMethodExecuteWork. Получить объект из списка */
	public ENMethodExecuteWorkShort getShortObject(int code) {
		try {
			ENMethodExecuteWorkDAO objectDAO = new ENMethodExecuteWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMethodExecuteWork%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
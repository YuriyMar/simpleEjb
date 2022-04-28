
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FinKodIstDAO;
import com.ksoe.energynet.valueobject.FinKodIst;
import com.ksoe.energynet.valueobject.lists.FinKodIstShortList;
import com.ksoe.energynet.valueobject.brief.FinKodIstShort;
import com.ksoe.energynet.valueobject.filter.FinKodIstFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FinKodIst;
 *
 */


public abstract class FinKodIstControllerEJBGen extends
		GenericSessionStatelessBean {
	public FinKodIstControllerEJBGen() {
	}

	/* FinKodIst. Добавить */
	public int add(FinKodIst object) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinKodIst. Удалить */
	public void remove(int code) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FinKodIst. Изменить */
	public void save(FinKodIst object) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinKodIst. Получить объект */
	public FinKodIst getObject(int code) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinKodIst. Получить полный список */
	public FinKodIstShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FinKodIst. Получить список по фильтру */
	public FinKodIstShortList getFilteredList(
			FinKodIstFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FinKodIst. Получить список для просмотра */
	public FinKodIstShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FinKodIst. Получить список для просмотра по фильтру */
	public FinKodIstShortList getScrollableFilteredList(
			FinKodIstFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FinKodIst%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinKodIst. Получить список для просмотра по условию */
	public FinKodIstShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FinKodIstFilter filterObject = new FinKodIstFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FinKodIst. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FinKodIstFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FinKodIst%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinKodIst. Получить объект из списка */
	public FinKodIstShort getShortObject(int code) {
		try {
			FinKodIstDAO objectDAO = new FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FinKodIst%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
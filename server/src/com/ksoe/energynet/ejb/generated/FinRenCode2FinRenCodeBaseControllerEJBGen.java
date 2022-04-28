
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FinRenCode2FinRenCodeBaseDAO;
import com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase;
import com.ksoe.energynet.valueobject.lists.FinRenCode2FinRenCodeBaseShortList;
import com.ksoe.energynet.valueobject.brief.FinRenCode2FinRenCodeBaseShort;
import com.ksoe.energynet.valueobject.filter.FinRenCode2FinRenCodeBaseFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FinRenCode2FinRenCodeBase;
 *
 */


public abstract class FinRenCode2FinRenCodeBaseControllerEJBGen extends
		GenericSessionStatelessBean {
	public FinRenCode2FinRenCodeBaseControllerEJBGen() {
	}

	/* FinRenCode2FinRenCodeBase. Добавить */
	public int add(FinRenCode2FinRenCodeBase object) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinRenCode2FinRenCodeBase. Удалить */
	public void remove(int code) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FinRenCode2FinRenCodeBase. Изменить */
	public void save(FinRenCode2FinRenCodeBase object) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinRenCode2FinRenCodeBase. Получить объект */
	public FinRenCode2FinRenCodeBase getObject(int code) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinRenCode2FinRenCodeBase. Получить полный список */
	public FinRenCode2FinRenCodeBaseShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FinRenCode2FinRenCodeBase. Получить список по фильтру */
	public FinRenCode2FinRenCodeBaseShortList getFilteredList(
			FinRenCode2FinRenCodeBaseFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FinRenCode2FinRenCodeBase. Получить список для просмотра */
	public FinRenCode2FinRenCodeBaseShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FinRenCode2FinRenCodeBase. Получить список для просмотра по фильтру */
	public FinRenCode2FinRenCodeBaseShortList getScrollableFilteredList(
			FinRenCode2FinRenCodeBaseFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinRenCode2FinRenCodeBase. Получить список для просмотра по условию */
	public FinRenCode2FinRenCodeBaseShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FinRenCode2FinRenCodeBaseFilter filterObject = new FinRenCode2FinRenCodeBaseFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FinRenCode2FinRenCodeBase. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FinRenCode2FinRenCodeBaseFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FinRenCode2FinRenCodeBase. Получить объект из списка */
	public FinRenCode2FinRenCodeBaseShort getShortObject(int code) {
		try {
			FinRenCode2FinRenCodeBaseDAO objectDAO = new FinRenCode2FinRenCodeBaseDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FinRenCode2FinRenCodeBase%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
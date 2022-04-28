
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2RQFKOrderTypeDAO;
import com.ksoe.energynet.valueobject.ENAct2RQFKOrderType;
import com.ksoe.energynet.valueobject.lists.ENAct2RQFKOrderTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2RQFKOrderTypeShort;
import com.ksoe.energynet.valueobject.filter.ENAct2RQFKOrderTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2RQFKOrderType;
 *
 */


public abstract class ENAct2RQFKOrderTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2RQFKOrderTypeControllerEJBGen() {
	}

	/* ENAct2RQFKOrderType. Добавить */
	public int add(ENAct2RQFKOrderType object) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrderType. Удалить */
	public void remove(int code) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2RQFKOrderType. Изменить */
	public void save(ENAct2RQFKOrderType object) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrderType. Получить объект */
	public ENAct2RQFKOrderType getObject(int code) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrderType. Получить полный список */
	public ENAct2RQFKOrderTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2RQFKOrderType. Получить список по фильтру */
	public ENAct2RQFKOrderTypeShortList getFilteredList(
			ENAct2RQFKOrderTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2RQFKOrderType. Получить список для просмотра */
	public ENAct2RQFKOrderTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2RQFKOrderType. Получить список для просмотра по фильтру */
	public ENAct2RQFKOrderTypeShortList getScrollableFilteredList(
			ENAct2RQFKOrderTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrderType. Получить список для просмотра по условию */
	public ENAct2RQFKOrderTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2RQFKOrderTypeFilter filterObject = new ENAct2RQFKOrderTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2RQFKOrderType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2RQFKOrderTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2RQFKOrderType. Получить объект из списка */
	public ENAct2RQFKOrderTypeShort getShortObject(int code) {
		try {
			ENAct2RQFKOrderTypeDAO objectDAO = new ENAct2RQFKOrderTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2RQFKOrderType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
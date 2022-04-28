
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuilding2CommissionDAO;
import com.ksoe.energynet.valueobject.ENBuilding2Commission;
import com.ksoe.energynet.valueobject.lists.ENBuilding2CommissionShortList;
import com.ksoe.energynet.valueobject.brief.ENBuilding2CommissionShort;
import com.ksoe.energynet.valueobject.filter.ENBuilding2CommissionFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuilding2Commission;
 *
 */


public abstract class ENBuilding2CommissionControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuilding2CommissionControllerEJBGen() {
	}

	/* ENBuilding2Commission. Добавить */
	public int add(ENBuilding2Commission object) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2Commission. Удалить */
	public void remove(int code) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuilding2Commission. Изменить */
	public void save(ENBuilding2Commission object) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2Commission. Получить объект */
	public ENBuilding2Commission getObject(int code) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2Commission. Получить полный список */
	public ENBuilding2CommissionShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuilding2Commission. Получить список по фильтру */
	public ENBuilding2CommissionShortList getFilteredList(
			ENBuilding2CommissionFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuilding2Commission. Получить список для просмотра */
	public ENBuilding2CommissionShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuilding2Commission. Получить список для просмотра по фильтру */
	public ENBuilding2CommissionShortList getScrollableFilteredList(
			ENBuilding2CommissionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2Commission. Получить список для просмотра по условию */
	public ENBuilding2CommissionShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuilding2CommissionFilter filterObject = new ENBuilding2CommissionFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuilding2Commission. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuilding2CommissionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2Commission. Получить объект из списка */
	public ENBuilding2CommissionShort getShortObject(int code) {
		try {
			ENBuilding2CommissionDAO objectDAO = new ENBuilding2CommissionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2Commission%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
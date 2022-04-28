
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuilding2ServicesObjectDAO;
import com.ksoe.energynet.valueobject.ENBuilding2ServicesObject;
import com.ksoe.energynet.valueobject.lists.ENBuilding2ServicesObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENBuilding2ServicesObjectShort;
import com.ksoe.energynet.valueobject.filter.ENBuilding2ServicesObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuilding2ServicesObject;
 *
 */


public abstract class ENBuilding2ServicesObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuilding2ServicesObjectControllerEJBGen() {
	}

	/* ENBuilding2ServicesObject. Добавить */
	public int add(ENBuilding2ServicesObject object) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ServicesObject. Удалить */
	public void remove(int code) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuilding2ServicesObject. Изменить */
	public void save(ENBuilding2ServicesObject object) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ServicesObject. Получить объект */
	public ENBuilding2ServicesObject getObject(int code) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ServicesObject. Получить полный список */
	public ENBuilding2ServicesObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuilding2ServicesObject. Получить список по фильтру */
	public ENBuilding2ServicesObjectShortList getFilteredList(
			ENBuilding2ServicesObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuilding2ServicesObject. Получить список для просмотра */
	public ENBuilding2ServicesObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuilding2ServicesObject. Получить список для просмотра по фильтру */
	public ENBuilding2ServicesObjectShortList getScrollableFilteredList(
			ENBuilding2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ServicesObject. Получить список для просмотра по условию */
	public ENBuilding2ServicesObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuilding2ServicesObjectFilter filterObject = new ENBuilding2ServicesObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuilding2ServicesObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuilding2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ServicesObject. Получить объект из списка */
	public ENBuilding2ServicesObjectShort getShortObject(int code) {
		try {
			ENBuilding2ServicesObjectDAO objectDAO = new ENBuilding2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ServicesObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuilding2ActTypeWorkDAO;
import com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork;
import com.ksoe.energynet.valueobject.lists.ENBuilding2ActTypeWorkShortList;
import com.ksoe.energynet.valueobject.brief.ENBuilding2ActTypeWorkShort;
import com.ksoe.energynet.valueobject.filter.ENBuilding2ActTypeWorkFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuilding2ActTypeWork;
 *
 */


public abstract class ENBuilding2ActTypeWorkControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuilding2ActTypeWorkControllerEJBGen() {
	}

	/* ENBuilding2ActTypeWork. Добавить */
	public int add(ENBuilding2ActTypeWork object) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ActTypeWork. Удалить */
	public void remove(int code) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuilding2ActTypeWork. Изменить */
	public void save(ENBuilding2ActTypeWork object) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ActTypeWork. Получить объект */
	public ENBuilding2ActTypeWork getObject(int code) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ActTypeWork. Получить полный список */
	public ENBuilding2ActTypeWorkShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuilding2ActTypeWork. Получить список по фильтру */
	public ENBuilding2ActTypeWorkShortList getFilteredList(
			ENBuilding2ActTypeWorkFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuilding2ActTypeWork. Получить список для просмотра */
	public ENBuilding2ActTypeWorkShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuilding2ActTypeWork. Получить список для просмотра по фильтру */
	public ENBuilding2ActTypeWorkShortList getScrollableFilteredList(
			ENBuilding2ActTypeWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ActTypeWork. Получить список для просмотра по условию */
	public ENBuilding2ActTypeWorkShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuilding2ActTypeWorkFilter filterObject = new ENBuilding2ActTypeWorkFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuilding2ActTypeWork. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuilding2ActTypeWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuilding2ActTypeWork. Получить объект из списка */
	public ENBuilding2ActTypeWorkShort getShortObject(int code) {
		try {
			ENBuilding2ActTypeWorkDAO objectDAO = new ENBuilding2ActTypeWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuilding2ActTypeWork%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
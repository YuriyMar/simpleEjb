
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2ActTypeDAO;
import com.ksoe.energynet.valueobject.ENElement2ActType;
import com.ksoe.energynet.valueobject.lists.ENElement2ActTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2ActTypeShort;
import com.ksoe.energynet.valueobject.filter.ENElement2ActTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2ActType;
 *
 */


public abstract class ENElement2ActTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2ActTypeControllerEJBGen() {
	}

	/* ENElement2ActType. Добавить */
	public int add(ENElement2ActType object) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2ActType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ActType. Удалить */
	public void remove(int code) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2ActType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2ActType. Изменить */
	public void save(ENElement2ActType object) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2ActType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ActType. Получить объект */
	public ENElement2ActType getObject(int code) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ActType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ActType. Получить полный список */
	public ENElement2ActTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2ActType. Получить список по фильтру */
	public ENElement2ActTypeShortList getFilteredList(
			ENElement2ActTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2ActType. Получить список для просмотра */
	public ENElement2ActTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2ActType. Получить список для просмотра по фильтру */
	public ENElement2ActTypeShortList getScrollableFilteredList(
			ENElement2ActTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2ActType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ActType. Получить список для просмотра по условию */
	public ENElement2ActTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2ActTypeFilter filterObject = new ENElement2ActTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2ActType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENElement2ActTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2ActType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ActType. Получить объект из списка */
	public ENElement2ActTypeShort getShortObject(int code) {
		try {
			ENElement2ActTypeDAO objectDAO = new ENElement2ActTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ActType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
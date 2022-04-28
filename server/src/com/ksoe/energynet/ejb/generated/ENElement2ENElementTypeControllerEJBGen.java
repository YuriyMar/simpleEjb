
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2ENElementTypeDAO;
import com.ksoe.energynet.valueobject.ENElement2ENElementType;
import com.ksoe.energynet.valueobject.lists.ENElement2ENElementTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2ENElementTypeShort;
import com.ksoe.energynet.valueobject.filter.ENElement2ENElementTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2ENElementType;
 *
 */


public abstract class ENElement2ENElementTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2ENElementTypeControllerEJBGen() {
	}

	/* ENElement2ENElementType. Добавить */
	public int add(ENElement2ENElementType object) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElementType. Удалить */
	public void remove(int code) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2ENElementType. Изменить */
	public void save(ENElement2ENElementType object) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElementType. Получить объект */
	public ENElement2ENElementType getObject(int code) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElementType. Получить полный список */
	public ENElement2ENElementTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2ENElementType. Получить список по фильтру */
	public ENElement2ENElementTypeShortList getFilteredList(
			ENElement2ENElementTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2ENElementType. Получить список для просмотра */
	public ENElement2ENElementTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2ENElementType. Получить список для просмотра по фильтру */
	public ENElement2ENElementTypeShortList getScrollableFilteredList(
			ENElement2ENElementTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElementType. Получить список для просмотра по условию */
	public ENElement2ENElementTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2ENElementTypeFilter filterObject = new ENElement2ENElementTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2ENElementType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENElement2ENElementTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElementType. Получить объект из списка */
	public ENElement2ENElementTypeShort getShortObject(int code) {
		try {
			ENElement2ENElementTypeDAO objectDAO = new ENElement2ENElementTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ENElementType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2ENElementDAO;
import com.ksoe.energynet.valueobject.ENElement2ENElement;
import com.ksoe.energynet.valueobject.lists.ENElement2ENElementShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2ENElementShort;
import com.ksoe.energynet.valueobject.filter.ENElement2ENElementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2ENElement;
 *
 */


public abstract class ENElement2ENElementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2ENElementControllerEJBGen() {
	}

	/* ENElement2ENElement. Добавить */
	public int add(ENElement2ENElement object) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElement. Удалить */
	public void remove(int code) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2ENElement. Изменить */
	public void save(ENElement2ENElement object) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElement. Получить объект */
	public ENElement2ENElement getObject(int code) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElement. Получить полный список */
	public ENElement2ENElementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2ENElement. Получить список по фильтру */
	public ENElement2ENElementShortList getFilteredList(
			ENElement2ENElementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2ENElement. Получить список для просмотра */
	public ENElement2ENElementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2ENElement. Получить список для просмотра по фильтру */
	public ENElement2ENElementShortList getScrollableFilteredList(
			ENElement2ENElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2ENElement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElement. Получить список для просмотра по условию */
	public ENElement2ENElementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2ENElementFilter filterObject = new ENElement2ENElementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2ENElement. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENElement2ENElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2ENElement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2ENElement. Получить объект из списка */
	public ENElement2ENElementShort getShortObject(int code) {
		try {
			ENElement2ENElementDAO objectDAO = new ENElement2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2ENElement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
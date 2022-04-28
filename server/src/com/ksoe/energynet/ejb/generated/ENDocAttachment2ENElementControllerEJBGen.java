
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachment2ENElementDAO;
import com.ksoe.energynet.valueobject.ENDocAttachment2ENElement;
import com.ksoe.energynet.valueobject.lists.ENDocAttachment2ENElementShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachment2ENElementShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachment2ENElementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment2ENElement;
 *
 */


public abstract class ENDocAttachment2ENElementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachment2ENElementControllerEJBGen() {
	}

	/* ENDocAttachment2ENElement. Добавить */
	public int add(ENDocAttachment2ENElement object) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENElement. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment2ENElement. Изменить */
	public void save(ENDocAttachment2ENElement object) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENElement. Получить объект */
	public ENDocAttachment2ENElement getObject(int code) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENElement. Получить полный список */
	public ENDocAttachment2ENElementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment2ENElement. Получить список по фильтру */
	public ENDocAttachment2ENElementShortList getFilteredList(
			ENDocAttachment2ENElementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment2ENElement. Получить список для просмотра */
	public ENDocAttachment2ENElementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment2ENElement. Получить список для просмотра по фильтру */
	public ENDocAttachment2ENElementShortList getScrollableFilteredList(
			ENDocAttachment2ENElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENElement. Получить список для просмотра по условию */
	public ENDocAttachment2ENElementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachment2ENElementFilter filterObject = new ENDocAttachment2ENElementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment2ENElement. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachment2ENElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENElement. Получить объект из списка */
	public ENDocAttachment2ENElementShort getShortObject(int code) {
		try {
			ENDocAttachment2ENElementDAO objectDAO = new ENDocAttachment2ENElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENElement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCottageDAO;
import com.ksoe.energynet.valueobject.ENCottage;
import com.ksoe.energynet.valueobject.lists.ENCottageShortList;
import com.ksoe.energynet.valueobject.brief.ENCottageShort;
import com.ksoe.energynet.valueobject.filter.ENCottageFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCottage;
 *
 */


public abstract class ENCottageControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCottageControllerEJBGen() {
	}

	/* ENCottage. Добавить */
	public int add(ENCottage object) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCottage%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage. Удалить */
	public void remove(int code) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCottage%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCottage. Изменить */
	public void save(ENCottage object) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCottage%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage. Получить объект */
	public ENCottage getObject(int code) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCottage%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage. Получить полный список */
	public ENCottageShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCottage. Получить список по фильтру */
	public ENCottageShortList getFilteredList(
			ENCottageFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCottage. Получить список для просмотра */
	public ENCottageShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCottage. Получить список для просмотра по фильтру */
	public ENCottageShortList getScrollableFilteredList(
			ENCottageFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCottage%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage. Получить список для просмотра по условию */
	public ENCottageShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCottageFilter filterObject = new ENCottageFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCottage. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCottageFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCottage%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage. Получить объект из списка */
	public ENCottageShort getShortObject(int code) {
		try {
			ENCottageDAO objectDAO = new ENCottageDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCottage%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
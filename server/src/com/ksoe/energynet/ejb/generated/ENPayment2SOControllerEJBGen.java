
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPayment2SODAO;
import com.ksoe.energynet.valueobject.ENPayment2SO;
import com.ksoe.energynet.valueobject.lists.ENPayment2SOShortList;
import com.ksoe.energynet.valueobject.brief.ENPayment2SOShort;
import com.ksoe.energynet.valueobject.filter.ENPayment2SOFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPayment2SO;
 *
 */


public abstract class ENPayment2SOControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPayment2SOControllerEJBGen() {
	}

	/* ENPayment2SO. Добавить */
	public int add(ENPayment2SO object) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPayment2SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SO. Удалить */
	public void remove(int code) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPayment2SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPayment2SO. Изменить */
	public void save(ENPayment2SO object) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPayment2SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SO. Получить объект */
	public ENPayment2SO getObject(int code) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPayment2SO%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SO. Получить полный список */
	public ENPayment2SOShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPayment2SO. Получить список по фильтру */
	public ENPayment2SOShortList getFilteredList(
			ENPayment2SOFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPayment2SO. Получить список для просмотра */
	public ENPayment2SOShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPayment2SO. Получить список для просмотра по фильтру */
	public ENPayment2SOShortList getScrollableFilteredList(
			ENPayment2SOFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPayment2SO%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SO. Получить список для просмотра по условию */
	public ENPayment2SOShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPayment2SOFilter filterObject = new ENPayment2SOFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPayment2SO. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPayment2SOFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPayment2SO%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SO. Получить объект из списка */
	public ENPayment2SOShort getShortObject(int code) {
		try {
			ENPayment2SODAO objectDAO = new ENPayment2SODAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPayment2SO%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
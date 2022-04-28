
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelInvResultDAO;
import com.ksoe.energynet.valueobject.ENFuelInvResult;
import com.ksoe.energynet.valueobject.lists.ENFuelInvResultShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelInvResultShort;
import com.ksoe.energynet.valueobject.filter.ENFuelInvResultFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelInvResult;
 *
 */


public abstract class ENFuelInvResultControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelInvResultControllerEJBGen() {
	}

	/* ENFuelInvResult. Добавить */
	public int add(ENFuelInvResult object) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelInvResult%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResult. Удалить */
	public void remove(int code) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelInvResult%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelInvResult. Изменить */
	public void save(ENFuelInvResult object) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelInvResult%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResult. Получить объект */
	public ENFuelInvResult getObject(int code) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInvResult%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResult. Получить полный список */
	public ENFuelInvResultShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelInvResult. Получить список по фильтру */
	public ENFuelInvResultShortList getFilteredList(
			ENFuelInvResultFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelInvResult. Получить список для просмотра */
	public ENFuelInvResultShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelInvResult. Получить список для просмотра по фильтру */
	public ENFuelInvResultShortList getScrollableFilteredList(
			ENFuelInvResultFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelInvResult%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResult. Получить список для просмотра по условию */
	public ENFuelInvResultShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelInvResultFilter filterObject = new ENFuelInvResultFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelInvResult. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelInvResultFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelInvResult%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResult. Получить объект из списка */
	public ENFuelInvResultShort getShortObject(int code) {
		try {
			ENFuelInvResultDAO objectDAO = new ENFuelInvResultDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInvResult%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
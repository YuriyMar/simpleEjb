
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPItem2ENIPItemDAO;
import com.ksoe.energynet.valueobject.ENIPItem2ENIPItem;
import com.ksoe.energynet.valueobject.lists.ENIPItem2ENIPItemShortList;
import com.ksoe.energynet.valueobject.brief.ENIPItem2ENIPItemShort;
import com.ksoe.energynet.valueobject.filter.ENIPItem2ENIPItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPItem2ENIPItem;
 *
 */


public abstract class ENIPItem2ENIPItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPItem2ENIPItemControllerEJBGen() {
	}

	/* ENIPItem2ENIPItem. Добавить */
	public int add(ENIPItem2ENIPItem object) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2ENIPItem. Удалить */
	public void remove(int code) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPItem2ENIPItem. Изменить */
	public void save(ENIPItem2ENIPItem object) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2ENIPItem. Получить объект */
	public ENIPItem2ENIPItem getObject(int code) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2ENIPItem. Получить полный список */
	public ENIPItem2ENIPItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPItem2ENIPItem. Получить список по фильтру */
	public ENIPItem2ENIPItemShortList getFilteredList(
			ENIPItem2ENIPItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPItem2ENIPItem. Получить список для просмотра */
	public ENIPItem2ENIPItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPItem2ENIPItem. Получить список для просмотра по фильтру */
	public ENIPItem2ENIPItemShortList getScrollableFilteredList(
			ENIPItem2ENIPItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2ENIPItem. Получить список для просмотра по условию */
	public ENIPItem2ENIPItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPItem2ENIPItemFilter filterObject = new ENIPItem2ENIPItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPItem2ENIPItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPItem2ENIPItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2ENIPItem. Получить объект из списка */
	public ENIPItem2ENIPItemShort getShortObject(int code) {
		try {
			ENIPItem2ENIPItemDAO objectDAO = new ENIPItem2ENIPItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2ENIPItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
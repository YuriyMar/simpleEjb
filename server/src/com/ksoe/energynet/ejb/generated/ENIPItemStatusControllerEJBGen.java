
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPItemStatusDAO;
import com.ksoe.energynet.valueobject.ENIPItemStatus;
import com.ksoe.energynet.valueobject.lists.ENIPItemStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENIPItemStatusShort;
import com.ksoe.energynet.valueobject.filter.ENIPItemStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPItemStatus;
 *
 */


public abstract class ENIPItemStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPItemStatusControllerEJBGen() {
	}

	/* ENIPItemStatus. Добавить */
	public int add(ENIPItemStatus object) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItemStatus. Удалить */
	public void remove(int code) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPItemStatus. Изменить */
	public void save(ENIPItemStatus object) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItemStatus. Получить объект */
	public ENIPItemStatus getObject(int code) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItemStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItemStatus. Получить полный список */
	public ENIPItemStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPItemStatus. Получить список по фильтру */
	public ENIPItemStatusShortList getFilteredList(
			ENIPItemStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPItemStatus. Получить список для просмотра */
	public ENIPItemStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPItemStatus. Получить список для просмотра по фильтру */
	public ENIPItemStatusShortList getScrollableFilteredList(
			ENIPItemStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPItemStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItemStatus. Получить список для просмотра по условию */
	public ENIPItemStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPItemStatusFilter filterObject = new ENIPItemStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPItemStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPItemStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPItemStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItemStatus. Получить объект из списка */
	public ENIPItemStatusShort getShortObject(int code) {
		try {
			ENIPItemStatusDAO objectDAO = new ENIPItemStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItemStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
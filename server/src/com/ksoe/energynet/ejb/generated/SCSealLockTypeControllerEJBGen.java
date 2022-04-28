
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSealLockTypeDAO;
import com.ksoe.energynet.valueobject.SCSealLockType;
import com.ksoe.energynet.valueobject.lists.SCSealLockTypeShortList;
import com.ksoe.energynet.valueobject.brief.SCSealLockTypeShort;
import com.ksoe.energynet.valueobject.filter.SCSealLockTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSealLockType;
 *
 */


public abstract class SCSealLockTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSealLockTypeControllerEJBGen() {
	}

	/* SCSealLockType. Добавить */
	public int add(SCSealLockType object) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSealLockType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealLockType. Удалить */
	public void remove(int code) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSealLockType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSealLockType. Изменить */
	public void save(SCSealLockType object) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSealLockType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealLockType. Получить объект */
	public SCSealLockType getObject(int code) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealLockType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealLockType. Получить полный список */
	public SCSealLockTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSealLockType. Получить список по фильтру */
	public SCSealLockTypeShortList getFilteredList(
			SCSealLockTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSealLockType. Получить список для просмотра */
	public SCSealLockTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSealLockType. Получить список для просмотра по фильтру */
	public SCSealLockTypeShortList getScrollableFilteredList(
			SCSealLockTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSealLockType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealLockType. Получить список для просмотра по условию */
	public SCSealLockTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSealLockTypeFilter filterObject = new SCSealLockTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSealLockType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSealLockTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSealLockType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealLockType. Получить объект из списка */
	public SCSealLockTypeShort getShortObject(int code) {
		try {
			SCSealLockTypeDAO objectDAO = new SCSealLockTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealLockType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
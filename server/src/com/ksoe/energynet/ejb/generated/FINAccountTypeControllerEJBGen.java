
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FINAccountTypeDAO;
import com.ksoe.energynet.valueobject.FINAccountType;
import com.ksoe.energynet.valueobject.lists.FINAccountTypeShortList;
import com.ksoe.energynet.valueobject.brief.FINAccountTypeShort;
import com.ksoe.energynet.valueobject.filter.FINAccountTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FINAccountType;
 *
 */


public abstract class FINAccountTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public FINAccountTypeControllerEJBGen() {
	}

	/* FINAccountType. Добавить */
	public int add(FINAccountType object) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FINAccountType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINAccountType. Удалить */
	public void remove(int code) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FINAccountType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FINAccountType. Изменить */
	public void save(FINAccountType object) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FINAccountType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINAccountType. Получить объект */
	public FINAccountType getObject(int code) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINAccountType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINAccountType. Получить полный список */
	public FINAccountTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FINAccountType. Получить список по фильтру */
	public FINAccountTypeShortList getFilteredList(
			FINAccountTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FINAccountType. Получить список для просмотра */
	public FINAccountTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FINAccountType. Получить список для просмотра по фильтру */
	public FINAccountTypeShortList getScrollableFilteredList(
			FINAccountTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FINAccountType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINAccountType. Получить список для просмотра по условию */
	public FINAccountTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FINAccountTypeFilter filterObject = new FINAccountTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FINAccountType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FINAccountTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FINAccountType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FINAccountType. Получить объект из списка */
	public FINAccountTypeShort getShortObject(int code) {
		try {
			FINAccountTypeDAO objectDAO = new FINAccountTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FINAccountType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
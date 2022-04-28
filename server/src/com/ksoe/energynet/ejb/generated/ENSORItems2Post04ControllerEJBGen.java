
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSORItems2Post04DAO;
import com.ksoe.energynet.valueobject.ENSORItems2Post04;
import com.ksoe.energynet.valueobject.lists.ENSORItems2Post04ShortList;
import com.ksoe.energynet.valueobject.brief.ENSORItems2Post04Short;
import com.ksoe.energynet.valueobject.filter.ENSORItems2Post04Filter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSORItems2Post04;
 *
 */


public abstract class ENSORItems2Post04ControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSORItems2Post04ControllerEJBGen() {
	}

	/* ENSORItems2Post04. Добавить */
	public int add(ENSORItems2Post04 object) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post04. Удалить */
	public void remove(int code) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSORItems2Post04. Изменить */
	public void save(ENSORItems2Post04 object) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post04. Получить объект */
	public ENSORItems2Post04 getObject(int code) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post04. Получить полный список */
	public ENSORItems2Post04ShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSORItems2Post04. Получить список по фильтру */
	public ENSORItems2Post04ShortList getFilteredList(
			ENSORItems2Post04Filter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSORItems2Post04. Получить список для просмотра */
	public ENSORItems2Post04ShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSORItems2Post04. Получить список для просмотра по фильтру */
	public ENSORItems2Post04ShortList getScrollableFilteredList(
			ENSORItems2Post04Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post04. Получить список для просмотра по условию */
	public ENSORItems2Post04ShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSORItems2Post04Filter filterObject = new ENSORItems2Post04Filter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSORItems2Post04. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSORItems2Post04Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post04. Получить объект из списка */
	public ENSORItems2Post04Short getShortObject(int code) {
		try {
			ENSORItems2Post04DAO objectDAO = new ENSORItems2Post04DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORItems2Post04%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
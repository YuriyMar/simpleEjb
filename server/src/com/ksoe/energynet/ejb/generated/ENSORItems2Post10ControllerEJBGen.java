
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSORItems2Post10DAO;
import com.ksoe.energynet.valueobject.ENSORItems2Post10;
import com.ksoe.energynet.valueobject.lists.ENSORItems2Post10ShortList;
import com.ksoe.energynet.valueobject.brief.ENSORItems2Post10Short;
import com.ksoe.energynet.valueobject.filter.ENSORItems2Post10Filter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSORItems2Post10;
 *
 */


public abstract class ENSORItems2Post10ControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSORItems2Post10ControllerEJBGen() {
	}

	/* ENSORItems2Post10. Добавить */
	public int add(ENSORItems2Post10 object) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post10. Удалить */
	public void remove(int code) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSORItems2Post10. Изменить */
	public void save(ENSORItems2Post10 object) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post10. Получить объект */
	public ENSORItems2Post10 getObject(int code) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post10. Получить полный список */
	public ENSORItems2Post10ShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSORItems2Post10. Получить список по фильтру */
	public ENSORItems2Post10ShortList getFilteredList(
			ENSORItems2Post10Filter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSORItems2Post10. Получить список для просмотра */
	public ENSORItems2Post10ShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSORItems2Post10. Получить список для просмотра по фильтру */
	public ENSORItems2Post10ShortList getScrollableFilteredList(
			ENSORItems2Post10Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post10. Получить список для просмотра по условию */
	public ENSORItems2Post10ShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSORItems2Post10Filter filterObject = new ENSORItems2Post10Filter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSORItems2Post10. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSORItems2Post10Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSORItems2Post10. Получить объект из списка */
	public ENSORItems2Post10Short getShortObject(int code) {
		try {
			ENSORItems2Post10DAO objectDAO = new ENSORItems2Post10DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSORItems2Post10%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
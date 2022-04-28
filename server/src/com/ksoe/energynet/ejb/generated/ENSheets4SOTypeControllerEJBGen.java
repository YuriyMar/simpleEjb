
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSheets4SOTypeDAO;
import com.ksoe.energynet.valueobject.ENSheets4SOType;
import com.ksoe.energynet.valueobject.lists.ENSheets4SOTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENSheets4SOTypeShort;
import com.ksoe.energynet.valueobject.filter.ENSheets4SOTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSheets4SOType;
 *
 */


public abstract class ENSheets4SOTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSheets4SOTypeControllerEJBGen() {
	}

	/* ENSheets4SOType. Добавить */
	public int add(ENSheets4SOType object) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSheets4SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOType. Удалить */
	public void remove(int code) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSheets4SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSheets4SOType. Изменить */
	public void save(ENSheets4SOType object) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSheets4SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOType. Получить объект */
	public ENSheets4SOType getObject(int code) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOType. Получить полный список */
	public ENSheets4SOTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSheets4SOType. Получить список по фильтру */
	public ENSheets4SOTypeShortList getFilteredList(
			ENSheets4SOTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSheets4SOType. Получить список для просмотра */
	public ENSheets4SOTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSheets4SOType. Получить список для просмотра по фильтру */
	public ENSheets4SOTypeShortList getScrollableFilteredList(
			ENSheets4SOTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSheets4SOType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOType. Получить список для просмотра по условию */
	public ENSheets4SOTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSheets4SOTypeFilter filterObject = new ENSheets4SOTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSheets4SOType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSheets4SOTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSheets4SOType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOType. Получить объект из списка */
	public ENSheets4SOTypeShort getShortObject(int code) {
		try {
			ENSheets4SOTypeDAO objectDAO = new ENSheets4SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
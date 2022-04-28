
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTCOValuesTypeDAO;
import com.ksoe.energynet.valueobject.ENTCOValuesType;
import com.ksoe.energynet.valueobject.lists.ENTCOValuesTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENTCOValuesTypeShort;
import com.ksoe.energynet.valueobject.filter.ENTCOValuesTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTCOValuesType;
 *
 */


public abstract class ENTCOValuesTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTCOValuesTypeControllerEJBGen() {
	}

	/* ENTCOValuesType. Добавить */
	public int add(ENTCOValuesType object) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTCOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValuesType. Удалить */
	public void remove(int code) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTCOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTCOValuesType. Изменить */
	public void save(ENTCOValuesType object) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTCOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValuesType. Получить объект */
	public ENTCOValuesType getObject(int code) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTCOValuesType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValuesType. Получить полный список */
	public ENTCOValuesTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTCOValuesType. Получить список по фильтру */
	public ENTCOValuesTypeShortList getFilteredList(
			ENTCOValuesTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTCOValuesType. Получить список для просмотра */
	public ENTCOValuesTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTCOValuesType. Получить список для просмотра по фильтру */
	public ENTCOValuesTypeShortList getScrollableFilteredList(
			ENTCOValuesTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTCOValuesType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValuesType. Получить список для просмотра по условию */
	public ENTCOValuesTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTCOValuesTypeFilter filterObject = new ENTCOValuesTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTCOValuesType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTCOValuesTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTCOValuesType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValuesType. Получить объект из списка */
	public ENTCOValuesTypeShort getShortObject(int code) {
		try {
			ENTCOValuesTypeDAO objectDAO = new ENTCOValuesTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTCOValuesType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
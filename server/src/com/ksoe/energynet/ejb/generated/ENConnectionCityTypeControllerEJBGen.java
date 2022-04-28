
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionCityTypeDAO;
import com.ksoe.energynet.valueobject.ENConnectionCityType;
import com.ksoe.energynet.valueobject.lists.ENConnectionCityTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionCityTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionCityTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionCityType;
 *
 */


public abstract class ENConnectionCityTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionCityTypeControllerEJBGen() {
	}

	/* ENConnectionCityType. Добавить */
	public int add(ENConnectionCityType object) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionCityType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionCityType. Удалить */
	public void remove(int code) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionCityType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionCityType. Изменить */
	public void save(ENConnectionCityType object) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionCityType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionCityType. Получить объект */
	public ENConnectionCityType getObject(int code) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionCityType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionCityType. Получить полный список */
	public ENConnectionCityTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionCityType. Получить список по фильтру */
	public ENConnectionCityTypeShortList getFilteredList(
			ENConnectionCityTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionCityType. Получить список для просмотра */
	public ENConnectionCityTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionCityType. Получить список для просмотра по фильтру */
	public ENConnectionCityTypeShortList getScrollableFilteredList(
			ENConnectionCityTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionCityType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionCityType. Получить список для просмотра по условию */
	public ENConnectionCityTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionCityTypeFilter filterObject = new ENConnectionCityTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionCityType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionCityTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionCityType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionCityType. Получить объект из списка */
	public ENConnectionCityTypeShort getShortObject(int code) {
		try {
			ENConnectionCityTypeDAO objectDAO = new ENConnectionCityTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionCityType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENRentPeriod2ServicesDAO;
import com.ksoe.energynet.valueobject.ENRentPeriod2Services;
import com.ksoe.energynet.valueobject.lists.ENRentPeriod2ServicesShortList;
import com.ksoe.energynet.valueobject.brief.ENRentPeriod2ServicesShort;
import com.ksoe.energynet.valueobject.filter.ENRentPeriod2ServicesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENRentPeriod2Services;
 *
 */


public abstract class ENRentPeriod2ServicesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENRentPeriod2ServicesControllerEJBGen() {
	}

	/* ENRentPeriod2Services. Добавить */
	public int add(ENRentPeriod2Services object) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriod2Services. Удалить */
	public void remove(int code) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENRentPeriod2Services. Изменить */
	public void save(ENRentPeriod2Services object) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriod2Services. Получить объект */
	public ENRentPeriod2Services getObject(int code) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriod2Services. Получить полный список */
	public ENRentPeriod2ServicesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENRentPeriod2Services. Получить список по фильтру */
	public ENRentPeriod2ServicesShortList getFilteredList(
			ENRentPeriod2ServicesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENRentPeriod2Services. Получить список для просмотра */
	public ENRentPeriod2ServicesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENRentPeriod2Services. Получить список для просмотра по фильтру */
	public ENRentPeriod2ServicesShortList getScrollableFilteredList(
			ENRentPeriod2ServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriod2Services. Получить список для просмотра по условию */
	public ENRentPeriod2ServicesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENRentPeriod2ServicesFilter filterObject = new ENRentPeriod2ServicesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENRentPeriod2Services. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENRentPeriod2ServicesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENRentPeriod2Services. Получить объект из списка */
	public ENRentPeriod2ServicesShort getShortObject(int code) {
		try {
			ENRentPeriod2ServicesDAO objectDAO = new ENRentPeriod2ServicesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENRentPeriod2Services%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
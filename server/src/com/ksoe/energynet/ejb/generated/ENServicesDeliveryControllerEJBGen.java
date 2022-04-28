
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesDeliveryDAO;
import com.ksoe.energynet.valueobject.ENServicesDelivery;
import com.ksoe.energynet.valueobject.lists.ENServicesDeliveryShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesDeliveryShort;
import com.ksoe.energynet.valueobject.filter.ENServicesDeliveryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesDelivery;
 *
 */


public abstract class ENServicesDeliveryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesDeliveryControllerEJBGen() {
	}

	/* ENServicesDelivery. Добавить */
	public int add(ENServicesDelivery object) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesDelivery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesDelivery. Удалить */
	public void remove(int code) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesDelivery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesDelivery. Изменить */
	public void save(ENServicesDelivery object) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesDelivery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesDelivery. Получить объект */
	public ENServicesDelivery getObject(int code) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesDelivery%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesDelivery. Получить полный список */
	public ENServicesDeliveryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesDelivery. Получить список по фильтру */
	public ENServicesDeliveryShortList getFilteredList(
			ENServicesDeliveryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesDelivery. Получить список для просмотра */
	public ENServicesDeliveryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesDelivery. Получить список для просмотра по фильтру */
	public ENServicesDeliveryShortList getScrollableFilteredList(
			ENServicesDeliveryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesDelivery%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesDelivery. Получить список для просмотра по условию */
	public ENServicesDeliveryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesDeliveryFilter filterObject = new ENServicesDeliveryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesDelivery. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesDeliveryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesDelivery%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesDelivery. Получить объект из списка */
	public ENServicesDeliveryShort getShortObject(int code) {
		try {
			ENServicesDeliveryDAO objectDAO = new ENServicesDeliveryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesDelivery%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
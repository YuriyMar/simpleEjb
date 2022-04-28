
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesTransportDAO;
import com.ksoe.energynet.valueobject.ENServicesTransport;
import com.ksoe.energynet.valueobject.lists.ENServicesTransportShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesTransportShort;
import com.ksoe.energynet.valueobject.filter.ENServicesTransportFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesTransport;
 *
 */


public abstract class ENServicesTransportControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesTransportControllerEJBGen() {
	}

	/* ENServicesTransport. Добавить */
	public int add(ENServicesTransport object) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesTransport%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesTransport. Удалить */
	public void remove(int code) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesTransport%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesTransport. Изменить */
	public void save(ENServicesTransport object) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesTransport%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesTransport. Получить объект */
	public ENServicesTransport getObject(int code) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesTransport%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesTransport. Получить полный список */
	public ENServicesTransportShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesTransport. Получить список по фильтру */
	public ENServicesTransportShortList getFilteredList(
			ENServicesTransportFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesTransport. Получить список для просмотра */
	public ENServicesTransportShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesTransport. Получить список для просмотра по фильтру */
	public ENServicesTransportShortList getScrollableFilteredList(
			ENServicesTransportFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesTransport%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesTransport. Получить список для просмотра по условию */
	public ENServicesTransportShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesTransportFilter filterObject = new ENServicesTransportFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesTransport. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesTransportFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesTransport%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesTransport. Получить объект из списка */
	public ENServicesTransportShort getShortObject(int code) {
		try {
			ENServicesTransportDAO objectDAO = new ENServicesTransportDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesTransport%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
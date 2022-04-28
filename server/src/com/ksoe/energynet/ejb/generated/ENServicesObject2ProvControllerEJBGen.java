
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesObject2ProvDAO;
import com.ksoe.energynet.valueobject.ENServicesObject2Prov;
import com.ksoe.energynet.valueobject.lists.ENServicesObject2ProvShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesObject2ProvShort;
import com.ksoe.energynet.valueobject.filter.ENServicesObject2ProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesObject2Prov;
 *
 */


public abstract class ENServicesObject2ProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesObject2ProvControllerEJBGen() {
	}

	/* ENServicesObject2Prov. Добавить */
	public int add(ENServicesObject2Prov object) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2Prov. Удалить */
	public void remove(int code) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesObject2Prov. Изменить */
	public void save(ENServicesObject2Prov object) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2Prov. Получить объект */
	public ENServicesObject2Prov getObject(int code) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2Prov. Получить полный список */
	public ENServicesObject2ProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesObject2Prov. Получить список по фильтру */
	public ENServicesObject2ProvShortList getFilteredList(
			ENServicesObject2ProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesObject2Prov. Получить список для просмотра */
	public ENServicesObject2ProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesObject2Prov. Получить список для просмотра по фильтру */
	public ENServicesObject2ProvShortList getScrollableFilteredList(
			ENServicesObject2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2Prov. Получить список для просмотра по условию */
	public ENServicesObject2ProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesObject2ProvFilter filterObject = new ENServicesObject2ProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesObject2Prov. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesObject2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObject2Prov. Получить объект из списка */
	public ENServicesObject2ProvShort getShortObject(int code) {
		try {
			ENServicesObject2ProvDAO objectDAO = new ENServicesObject2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObject2Prov%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
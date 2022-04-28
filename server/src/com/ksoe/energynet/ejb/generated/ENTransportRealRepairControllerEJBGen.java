
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTransportRealRepairDAO;
import com.ksoe.energynet.valueobject.ENTransportRealRepair;
import com.ksoe.energynet.valueobject.lists.ENTransportRealRepairShortList;
import com.ksoe.energynet.valueobject.brief.ENTransportRealRepairShort;
import com.ksoe.energynet.valueobject.filter.ENTransportRealRepairFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTransportRealRepair;
 *
 */


public abstract class ENTransportRealRepairControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTransportRealRepairControllerEJBGen() {
	}

	/* ENTransportRealRepair. Добавить */
	public int add(ENTransportRealRepair object) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportRealRepair. Удалить */
	public void remove(int code) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTransportRealRepair. Изменить */
	public void save(ENTransportRealRepair object) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportRealRepair. Получить объект */
	public ENTransportRealRepair getObject(int code) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportRealRepair. Получить полный список */
	public ENTransportRealRepairShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTransportRealRepair. Получить список по фильтру */
	public ENTransportRealRepairShortList getFilteredList(
			ENTransportRealRepairFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTransportRealRepair. Получить список для просмотра */
	public ENTransportRealRepairShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTransportRealRepair. Получить список для просмотра по фильтру */
	public ENTransportRealRepairShortList getScrollableFilteredList(
			ENTransportRealRepairFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportRealRepair. Получить список для просмотра по условию */
	public ENTransportRealRepairShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTransportRealRepairFilter filterObject = new ENTransportRealRepairFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTransportRealRepair. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTransportRealRepairFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportRealRepair. Получить объект из списка */
	public ENTransportRealRepairShort getShortObject(int code) {
		try {
			ENTransportRealRepairDAO objectDAO = new ENTransportRealRepairDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransportRealRepair%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
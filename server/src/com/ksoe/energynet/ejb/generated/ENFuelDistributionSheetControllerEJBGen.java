
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelDistributionSheetDAO;
import com.ksoe.energynet.valueobject.ENFuelDistributionSheet;
import com.ksoe.energynet.valueobject.lists.ENFuelDistributionSheetShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelDistributionSheetShort;
import com.ksoe.energynet.valueobject.filter.ENFuelDistributionSheetFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelDistributionSheet;
 *
 */


public abstract class ENFuelDistributionSheetControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelDistributionSheetControllerEJBGen() {
	}

	/* ENFuelDistributionSheet. Добавить */
	public int add(ENFuelDistributionSheet object) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheet. Удалить */
	public void remove(int code) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelDistributionSheet. Изменить */
	public void save(ENFuelDistributionSheet object) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheet. Получить объект */
	public ENFuelDistributionSheet getObject(int code) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheet. Получить полный список */
	public ENFuelDistributionSheetShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelDistributionSheet. Получить список по фильтру */
	public ENFuelDistributionSheetShortList getFilteredList(
			ENFuelDistributionSheetFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelDistributionSheet. Получить список для просмотра */
	public ENFuelDistributionSheetShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelDistributionSheet. Получить список для просмотра по фильтру */
	public ENFuelDistributionSheetShortList getScrollableFilteredList(
			ENFuelDistributionSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheet. Получить список для просмотра по условию */
	public ENFuelDistributionSheetShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelDistributionSheetFilter filterObject = new ENFuelDistributionSheetFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelDistributionSheet. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelDistributionSheetFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelDistributionSheet. Получить объект из списка */
	public ENFuelDistributionSheetShort getShortObject(int code) {
		try {
			ENFuelDistributionSheetDAO objectDAO = new ENFuelDistributionSheetDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelDistributionSheet%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
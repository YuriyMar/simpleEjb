
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesFactCalcDAO;
import com.ksoe.energynet.valueobject.ENServicesFactCalc;
import com.ksoe.energynet.valueobject.lists.ENServicesFactCalcShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesFactCalcShort;
import com.ksoe.energynet.valueobject.filter.ENServicesFactCalcFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENServicesFactCalc;
 *
 */


public abstract class ENServicesFactCalcControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesFactCalcControllerEJBGen() {
	}

	/* ENServicesFactCalc. Добавить */
	public int add(ENServicesFactCalc object) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalc. Удалить */
	public void remove(int code) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesFactCalc. Изменить */
	public void save(ENServicesFactCalc object) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalc. Получить объект */
	public ENServicesFactCalc getObject(int code) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalc. Получить полный список */
	public ENServicesFactCalcShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesFactCalc. Получить список по фильтру */
	public ENServicesFactCalcShortList getFilteredList(
			ENServicesFactCalcFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesFactCalc. Получить список для просмотра */
	public ENServicesFactCalcShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesFactCalc. Получить список для просмотра по фильтру */
	public ENServicesFactCalcShortList getScrollableFilteredList(
			ENServicesFactCalcFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalc. Получить список для просмотра по условию */
	public ENServicesFactCalcShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesFactCalcFilter filterObject = new ENServicesFactCalcFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesFactCalc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesFactCalcFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalc. Получить объект из списка */
	public ENServicesFactCalcShort getShortObject(int code) {
		try {
			ENServicesFactCalcDAO objectDAO = new ENServicesFactCalcDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesFactCalc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
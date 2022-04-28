
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesFactCalcByActDAO;
import com.ksoe.energynet.valueobject.ENServicesFactCalcByAct;
import com.ksoe.energynet.valueobject.lists.ENServicesFactCalcByActShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesFactCalcByActShort;
import com.ksoe.energynet.valueobject.filter.ENServicesFactCalcByActFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENServicesFactCalcByAct;
 *
 */


public abstract class ENServicesFactCalcByActControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesFactCalcByActControllerEJBGen() {
	}

	/* ENServicesFactCalcByAct. Добавить */
	public int add(ENServicesFactCalcByAct object) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalcByAct. Удалить */
	public void remove(int code) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesFactCalcByAct. Изменить */
	public void save(ENServicesFactCalcByAct object) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalcByAct. Получить объект */
	public ENServicesFactCalcByAct getObject(int code) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalcByAct. Получить полный список */
	public ENServicesFactCalcByActShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesFactCalcByAct. Получить список по фильтру */
	public ENServicesFactCalcByActShortList getFilteredList(
			ENServicesFactCalcByActFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesFactCalcByAct. Получить список для просмотра */
	public ENServicesFactCalcByActShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesFactCalcByAct. Получить список для просмотра по фильтру */
	public ENServicesFactCalcByActShortList getScrollableFilteredList(
			ENServicesFactCalcByActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalcByAct. Получить список для просмотра по условию */
	public ENServicesFactCalcByActShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesFactCalcByActFilter filterObject = new ENServicesFactCalcByActFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesFactCalcByAct. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesFactCalcByActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesFactCalcByAct. Получить объект из списка */
	public ENServicesFactCalcByActShort getShortObject(int code) {
		try {
			ENServicesFactCalcByActDAO objectDAO = new ENServicesFactCalcByActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesFactCalcByAct%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
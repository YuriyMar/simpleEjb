
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActProj2OZ2DAO;
import com.ksoe.energynet.valueobject.ENActProj2OZ2;
import com.ksoe.energynet.valueobject.lists.ENActProj2OZ2ShortList;
import com.ksoe.energynet.valueobject.brief.ENActProj2OZ2Short;
import com.ksoe.energynet.valueobject.filter.ENActProj2OZ2Filter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENActProj2OZ2;
 *
 */


public abstract class ENActProj2OZ2ControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActProj2OZ2ControllerEJBGen() {
	}

	/* ENActProj2OZ2. Добавить */
	public int add(ENActProj2OZ2 object) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2. Удалить */
	public void remove(int code) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActProj2OZ2. Изменить */
	public void save(ENActProj2OZ2 object) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2. Получить объект */
	public ENActProj2OZ2 getObject(int code) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2. Получить полный список */
	public ENActProj2OZ2ShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActProj2OZ2. Получить список по фильтру */
	public ENActProj2OZ2ShortList getFilteredList(
			ENActProj2OZ2Filter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActProj2OZ2. Получить список для просмотра */
	public ENActProj2OZ2ShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActProj2OZ2. Получить список для просмотра по фильтру */
	public ENActProj2OZ2ShortList getScrollableFilteredList(
			ENActProj2OZ2Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2. Получить список для просмотра по условию */
	public ENActProj2OZ2ShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActProj2OZ2Filter filterObject = new ENActProj2OZ2Filter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActProj2OZ2. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActProj2OZ2Filter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2. Получить объект из списка */
	public ENActProj2OZ2Short getShortObject(int code) {
		try {
			ENActProj2OZ2DAO objectDAO = new ENActProj2OZ2DAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActProj2OZ2%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
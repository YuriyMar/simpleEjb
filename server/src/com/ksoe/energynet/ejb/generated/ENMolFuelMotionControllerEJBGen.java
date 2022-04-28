
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENMolFuelMotionDAO;
import com.ksoe.energynet.valueobject.ENMolFuelMotion;
import com.ksoe.energynet.valueobject.lists.ENMolFuelMotionShortList;
import com.ksoe.energynet.valueobject.brief.ENMolFuelMotionShort;
import com.ksoe.energynet.valueobject.filter.ENMolFuelMotionFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENMolFuelMotion;
 *
 */


public abstract class ENMolFuelMotionControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENMolFuelMotionControllerEJBGen() {
	}

	/* ENMolFuelMotion. Добавить */
	public int add(ENMolFuelMotion object) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotion. Удалить */
	public void remove(int code) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENMolFuelMotion. Изменить */
	public void save(ENMolFuelMotion object) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotion. Получить объект */
	public ENMolFuelMotion getObject(int code) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotion. Получить полный список */
	public ENMolFuelMotionShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENMolFuelMotion. Получить список по фильтру */
	public ENMolFuelMotionShortList getFilteredList(
			ENMolFuelMotionFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENMolFuelMotion. Получить список для просмотра */
	public ENMolFuelMotionShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENMolFuelMotion. Получить список для просмотра по фильтру */
	public ENMolFuelMotionShortList getScrollableFilteredList(
			ENMolFuelMotionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotion. Получить список для просмотра по условию */
	public ENMolFuelMotionShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENMolFuelMotionFilter filterObject = new ENMolFuelMotionFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENMolFuelMotion. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENMolFuelMotionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENMolFuelMotion. Получить объект из списка */
	public ENMolFuelMotionShort getShortObject(int code) {
		try {
			ENMolFuelMotionDAO objectDAO = new ENMolFuelMotionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENMolFuelMotion%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
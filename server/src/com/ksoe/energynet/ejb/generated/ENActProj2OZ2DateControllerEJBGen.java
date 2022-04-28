
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActProj2OZ2DateDAO;
import com.ksoe.energynet.valueobject.ENActProj2OZ2Date;
import com.ksoe.energynet.valueobject.lists.ENActProj2OZ2DateShortList;
import com.ksoe.energynet.valueobject.brief.ENActProj2OZ2DateShort;
import com.ksoe.energynet.valueobject.filter.ENActProj2OZ2DateFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENActProj2OZ2Date;
 *
 */


public abstract class ENActProj2OZ2DateControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActProj2OZ2DateControllerEJBGen() {
	}

	/* ENActProj2OZ2Date. Добавить */
	public int add(ENActProj2OZ2Date object) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2Date. Удалить */
	public void remove(int code) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActProj2OZ2Date. Изменить */
	public void save(ENActProj2OZ2Date object) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2Date. Получить объект */
	public ENActProj2OZ2Date getObject(int code) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2Date. Получить полный список */
	public ENActProj2OZ2DateShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActProj2OZ2Date. Получить список по фильтру */
	public ENActProj2OZ2DateShortList getFilteredList(
			ENActProj2OZ2DateFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActProj2OZ2Date. Получить список для просмотра */
	public ENActProj2OZ2DateShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActProj2OZ2Date. Получить список для просмотра по фильтру */
	public ENActProj2OZ2DateShortList getScrollableFilteredList(
			ENActProj2OZ2DateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2Date. Получить список для просмотра по условию */
	public ENActProj2OZ2DateShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActProj2OZ2DateFilter filterObject = new ENActProj2OZ2DateFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActProj2OZ2Date. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActProj2OZ2DateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActProj2OZ2Date. Получить объект из списка */
	public ENActProj2OZ2DateShort getShortObject(int code) {
		try {
			ENActProj2OZ2DateDAO objectDAO = new ENActProj2OZ2DateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActProj2OZ2Date%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
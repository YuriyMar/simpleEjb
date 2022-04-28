
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPayment2SOTypeDAO;
import com.ksoe.energynet.valueobject.ENPayment2SOType;
import com.ksoe.energynet.valueobject.lists.ENPayment2SOTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENPayment2SOTypeShort;
import com.ksoe.energynet.valueobject.filter.ENPayment2SOTypeFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENPayment2SOType;
 *
 */


public abstract class ENPayment2SOTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPayment2SOTypeControllerEJBGen() {
	}

	/* ENPayment2SOType. Добавить */
	public int add(ENPayment2SOType object) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPayment2SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SOType. Удалить */
	public void remove(int code) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPayment2SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPayment2SOType. Изменить */
	public void save(ENPayment2SOType object) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPayment2SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SOType. Получить объект */
	public ENPayment2SOType getObject(int code) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPayment2SOType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SOType. Получить полный список */
	public ENPayment2SOTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPayment2SOType. Получить список по фильтру */
	public ENPayment2SOTypeShortList getFilteredList(
			ENPayment2SOTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPayment2SOType. Получить список для просмотра */
	public ENPayment2SOTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPayment2SOType. Получить список для просмотра по фильтру */
	public ENPayment2SOTypeShortList getScrollableFilteredList(
			ENPayment2SOTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPayment2SOType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SOType. Получить список для просмотра по условию */
	public ENPayment2SOTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPayment2SOTypeFilter filterObject = new ENPayment2SOTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPayment2SOType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPayment2SOTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPayment2SOType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPayment2SOType. Получить объект из списка */
	public ENPayment2SOTypeShort getShortObject(int code) {
		try {
			ENPayment2SOTypeDAO objectDAO = new ENPayment2SOTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPayment2SOType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
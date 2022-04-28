
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesObjectCalcTypeDAO;
import com.ksoe.energynet.valueobject.ENServicesObjectCalcType;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectCalcTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesObjectCalcTypeShort;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectCalcTypeFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENServicesObjectCalcType;
 *
 */


public abstract class ENServicesObjectCalcTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesObjectCalcTypeControllerEJBGen() {
	}

	/* ENServicesObjectCalcType. Добавить */
	public int add(ENServicesObjectCalcType object) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectCalcType. Удалить */
	public void remove(int code) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesObjectCalcType. Изменить */
	public void save(ENServicesObjectCalcType object) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectCalcType. Получить объект */
	public ENServicesObjectCalcType getObject(int code) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectCalcType. Получить полный список */
	public ENServicesObjectCalcTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesObjectCalcType. Получить список по фильтру */
	public ENServicesObjectCalcTypeShortList getFilteredList(
			ENServicesObjectCalcTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesObjectCalcType. Получить список для просмотра */
	public ENServicesObjectCalcTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesObjectCalcType. Получить список для просмотра по фильтру */
	public ENServicesObjectCalcTypeShortList getScrollableFilteredList(
			ENServicesObjectCalcTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectCalcType. Получить список для просмотра по условию */
	public ENServicesObjectCalcTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesObjectCalcTypeFilter filterObject = new ENServicesObjectCalcTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesObjectCalcType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesObjectCalcTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectCalcType. Получить объект из списка */
	public ENServicesObjectCalcTypeShort getShortObject(int code) {
		try {
			ENServicesObjectCalcTypeDAO objectDAO = new ENServicesObjectCalcTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObjectCalcType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
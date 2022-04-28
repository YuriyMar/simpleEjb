
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTravelSheetFuelTypeDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetFuelType;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetFuelTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENTravelSheetFuelTypeShort;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetFuelTypeFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENTravelSheetFuelType;
 *
 */


public abstract class ENTravelSheetFuelTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTravelSheetFuelTypeControllerEJBGen() {
	}

	/* ENTravelSheetFuelType. Добавить */
	public int add(ENTravelSheetFuelType object) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelType. Удалить */
	public void remove(int code) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTravelSheetFuelType. Изменить */
	public void save(ENTravelSheetFuelType object) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelType. Получить объект */
	public ENTravelSheetFuelType getObject(int code) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelType. Получить полный список */
	public ENTravelSheetFuelTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTravelSheetFuelType. Получить список по фильтру */
	public ENTravelSheetFuelTypeShortList getFilteredList(
			ENTravelSheetFuelTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTravelSheetFuelType. Получить список для просмотра */
	public ENTravelSheetFuelTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTravelSheetFuelType. Получить список для просмотра по фильтру */
	public ENTravelSheetFuelTypeShortList getScrollableFilteredList(
			ENTravelSheetFuelTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelType. Получить список для просмотра по условию */
	public ENTravelSheetFuelTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTravelSheetFuelTypeFilter filterObject = new ENTravelSheetFuelTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTravelSheetFuelType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTravelSheetFuelTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelType. Получить объект из списка */
	public ENTravelSheetFuelTypeShort getShortObject(int code) {
		try {
			ENTravelSheetFuelTypeDAO objectDAO = new ENTravelSheetFuelTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuelType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
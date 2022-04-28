
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTravelSheetFuelFillDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetFuelFill;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetFuelFillShortList;
import com.ksoe.energynet.valueobject.brief.ENTravelSheetFuelFillShort;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetFuelFillFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTravelSheetFuelFill;
 *
 */


public abstract class ENTravelSheetFuelFillControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTravelSheetFuelFillControllerEJBGen() {
	}

	/* ENTravelSheetFuelFill. Добавить */
	public int add(ENTravelSheetFuelFill object) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelFill. Удалить */
	public void remove(int code) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTravelSheetFuelFill. Изменить */
	public void save(ENTravelSheetFuelFill object) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelFill. Получить объект */
	public ENTravelSheetFuelFill getObject(int code) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelFill. Получить полный список */
	public ENTravelSheetFuelFillShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTravelSheetFuelFill. Получить список по фильтру */
	public ENTravelSheetFuelFillShortList getFilteredList(
			ENTravelSheetFuelFillFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTravelSheetFuelFill. Получить список для просмотра */
	public ENTravelSheetFuelFillShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTravelSheetFuelFill. Получить список для просмотра по фильтру */
	public ENTravelSheetFuelFillShortList getScrollableFilteredList(
			ENTravelSheetFuelFillFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelFill. Получить список для просмотра по условию */
	public ENTravelSheetFuelFillShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTravelSheetFuelFillFilter filterObject = new ENTravelSheetFuelFillFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTravelSheetFuelFill. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTravelSheetFuelFillFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuelFill. Получить объект из списка */
	public ENTravelSheetFuelFillShort getShortObject(int code) {
		try {
			ENTravelSheetFuelFillDAO objectDAO = new ENTravelSheetFuelFillDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuelFill%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
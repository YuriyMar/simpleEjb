
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCalc2ConnectTariffDAO;
import com.ksoe.energynet.valueobject.ENCalc2ConnectTariff;
import com.ksoe.energynet.valueobject.lists.ENCalc2ConnectTariffShortList;
import com.ksoe.energynet.valueobject.brief.ENCalc2ConnectTariffShort;
import com.ksoe.energynet.valueobject.filter.ENCalc2ConnectTariffFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCalc2ConnectTariff;
 *
 */


public abstract class ENCalc2ConnectTariffControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCalc2ConnectTariffControllerEJBGen() {
	}

	/* ENCalc2ConnectTariff. Добавить */
	public int add(ENCalc2ConnectTariff object) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalc2ConnectTariff. Удалить */
	public void remove(int code) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCalc2ConnectTariff. Изменить */
	public void save(ENCalc2ConnectTariff object) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalc2ConnectTariff. Получить объект */
	public ENCalc2ConnectTariff getObject(int code) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalc2ConnectTariff. Получить полный список */
	public ENCalc2ConnectTariffShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCalc2ConnectTariff. Получить список по фильтру */
	public ENCalc2ConnectTariffShortList getFilteredList(
			ENCalc2ConnectTariffFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCalc2ConnectTariff. Получить список для просмотра */
	public ENCalc2ConnectTariffShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCalc2ConnectTariff. Получить список для просмотра по фильтру */
	public ENCalc2ConnectTariffShortList getScrollableFilteredList(
			ENCalc2ConnectTariffFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalc2ConnectTariff. Получить список для просмотра по условию */
	public ENCalc2ConnectTariffShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCalc2ConnectTariffFilter filterObject = new ENCalc2ConnectTariffFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCalc2ConnectTariff. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCalc2ConnectTariffFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalc2ConnectTariff. Получить объект из списка */
	public ENCalc2ConnectTariffShort getShortObject(int code) {
		try {
			ENCalc2ConnectTariffDAO objectDAO = new ENCalc2ConnectTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalc2ConnectTariff%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCalcPowerReserveDAO;
import com.ksoe.energynet.valueobject.ENCalcPowerReserve;
import com.ksoe.energynet.valueobject.lists.ENCalcPowerReserveShortList;
import com.ksoe.energynet.valueobject.brief.ENCalcPowerReserveShort;
import com.ksoe.energynet.valueobject.filter.ENCalcPowerReserveFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCalcPowerReserve;
 *
 */


public abstract class ENCalcPowerReserveControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCalcPowerReserveControllerEJBGen() {
	}

	/* ENCalcPowerReserve. Добавить */
	public int add(ENCalcPowerReserve object) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserve. Удалить */
	public void remove(int code) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCalcPowerReserve. Изменить */
	public void save(ENCalcPowerReserve object) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserve. Получить объект */
	public ENCalcPowerReserve getObject(int code) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserve. Получить полный список */
	public ENCalcPowerReserveShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCalcPowerReserve. Получить список по фильтру */
	public ENCalcPowerReserveShortList getFilteredList(
			ENCalcPowerReserveFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCalcPowerReserve. Получить список для просмотра */
	public ENCalcPowerReserveShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCalcPowerReserve. Получить список для просмотра по фильтру */
	public ENCalcPowerReserveShortList getScrollableFilteredList(
			ENCalcPowerReserveFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserve. Получить список для просмотра по условию */
	public ENCalcPowerReserveShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCalcPowerReserveFilter filterObject = new ENCalcPowerReserveFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCalcPowerReserve. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCalcPowerReserveFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcPowerReserve. Получить объект из списка */
	public ENCalcPowerReserveShort getShortObject(int code) {
		try {
			ENCalcPowerReserveDAO objectDAO = new ENCalcPowerReserveDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcPowerReserve%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
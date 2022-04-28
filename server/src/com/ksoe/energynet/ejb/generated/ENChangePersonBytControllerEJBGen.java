
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENChangePersonBytDAO;
import com.ksoe.energynet.valueobject.ENChangePersonByt;
import com.ksoe.energynet.valueobject.lists.ENChangePersonBytShortList;
import com.ksoe.energynet.valueobject.brief.ENChangePersonBytShort;
import com.ksoe.energynet.valueobject.filter.ENChangePersonBytFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENChangePersonByt;
 *
 */


public abstract class ENChangePersonBytControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENChangePersonBytControllerEJBGen() {
	}

	/* ENChangePersonByt. Добавить */
	public int add(ENChangePersonByt object) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENChangePersonByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENChangePersonByt. Удалить */
	public void remove(int code) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENChangePersonByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENChangePersonByt. Изменить */
	public void save(ENChangePersonByt object) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENChangePersonByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENChangePersonByt. Получить объект */
	public ENChangePersonByt getObject(int code) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENChangePersonByt%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENChangePersonByt. Получить полный список */
	public ENChangePersonBytShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENChangePersonByt. Получить список по фильтру */
	public ENChangePersonBytShortList getFilteredList(
			ENChangePersonBytFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENChangePersonByt. Получить список для просмотра */
	public ENChangePersonBytShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENChangePersonByt. Получить список для просмотра по фильтру */
	public ENChangePersonBytShortList getScrollableFilteredList(
			ENChangePersonBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENChangePersonByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENChangePersonByt. Получить список для просмотра по условию */
	public ENChangePersonBytShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENChangePersonBytFilter filterObject = new ENChangePersonBytFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENChangePersonByt. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENChangePersonBytFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENChangePersonByt%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENChangePersonByt. Получить объект из списка */
	public ENChangePersonBytShort getShortObject(int code) {
		try {
			ENChangePersonBytDAO objectDAO = new ENChangePersonBytDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENChangePersonByt%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
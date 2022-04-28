
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServFromSideStatusDAO;
import com.ksoe.energynet.valueobject.ENServFromSideStatus;
import com.ksoe.energynet.valueobject.lists.ENServFromSideStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENServFromSideStatusShort;
import com.ksoe.energynet.valueobject.filter.ENServFromSideStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServFromSideStatus;
 *
 */


public abstract class ENServFromSideStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServFromSideStatusControllerEJBGen() {
	}

	/* ENServFromSideStatus. Добавить */
	public int add(ENServFromSideStatus object) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSideStatus. Удалить */
	public void remove(int code) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServFromSideStatus. Изменить */
	public void save(ENServFromSideStatus object) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSideStatus. Получить объект */
	public ENServFromSideStatus getObject(int code) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSideStatus. Получить полный список */
	public ENServFromSideStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServFromSideStatus. Получить список по фильтру */
	public ENServFromSideStatusShortList getFilteredList(
			ENServFromSideStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServFromSideStatus. Получить список для просмотра */
	public ENServFromSideStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServFromSideStatus. Получить список для просмотра по фильтру */
	public ENServFromSideStatusShortList getScrollableFilteredList(
			ENServFromSideStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSideStatus. Получить список для просмотра по условию */
	public ENServFromSideStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServFromSideStatusFilter filterObject = new ENServFromSideStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServFromSideStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServFromSideStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSideStatus. Получить объект из списка */
	public ENServFromSideStatusShort getShortObject(int code) {
		try {
			ENServFromSideStatusDAO objectDAO = new ENServFromSideStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServFromSideStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
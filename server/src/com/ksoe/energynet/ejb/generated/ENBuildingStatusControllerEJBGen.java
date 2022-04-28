
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuildingStatusDAO;
import com.ksoe.energynet.valueobject.ENBuildingStatus;
import com.ksoe.energynet.valueobject.lists.ENBuildingStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENBuildingStatusShort;
import com.ksoe.energynet.valueobject.filter.ENBuildingStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuildingStatus;
 *
 */


public abstract class ENBuildingStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuildingStatusControllerEJBGen() {
	}

	/* ENBuildingStatus. Добавить */
	public int add(ENBuildingStatus object) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuildingStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingStatus. Удалить */
	public void remove(int code) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuildingStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuildingStatus. Изменить */
	public void save(ENBuildingStatus object) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuildingStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingStatus. Получить объект */
	public ENBuildingStatus getObject(int code) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuildingStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingStatus. Получить полный список */
	public ENBuildingStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuildingStatus. Получить список по фильтру */
	public ENBuildingStatusShortList getFilteredList(
			ENBuildingStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuildingStatus. Получить список для просмотра */
	public ENBuildingStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuildingStatus. Получить список для просмотра по фильтру */
	public ENBuildingStatusShortList getScrollableFilteredList(
			ENBuildingStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuildingStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingStatus. Получить список для просмотра по условию */
	public ENBuildingStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuildingStatusFilter filterObject = new ENBuildingStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuildingStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuildingStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuildingStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingStatus. Получить объект из списка */
	public ENBuildingStatusShort getShortObject(int code) {
		try {
			ENBuildingStatusDAO objectDAO = new ENBuildingStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuildingStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
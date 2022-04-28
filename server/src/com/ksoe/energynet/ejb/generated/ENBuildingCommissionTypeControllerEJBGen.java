
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBuildingCommissionTypeDAO;
import com.ksoe.energynet.valueobject.ENBuildingCommissionType;
import com.ksoe.energynet.valueobject.lists.ENBuildingCommissionTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENBuildingCommissionTypeShort;
import com.ksoe.energynet.valueobject.filter.ENBuildingCommissionTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBuildingCommissionType;
 *
 */


public abstract class ENBuildingCommissionTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBuildingCommissionTypeControllerEJBGen() {
	}

	/* ENBuildingCommissionType. Добавить */
	public int add(ENBuildingCommissionType object) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingCommissionType. Удалить */
	public void remove(int code) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBuildingCommissionType. Изменить */
	public void save(ENBuildingCommissionType object) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingCommissionType. Получить объект */
	public ENBuildingCommissionType getObject(int code) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingCommissionType. Получить полный список */
	public ENBuildingCommissionTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBuildingCommissionType. Получить список по фильтру */
	public ENBuildingCommissionTypeShortList getFilteredList(
			ENBuildingCommissionTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBuildingCommissionType. Получить список для просмотра */
	public ENBuildingCommissionTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBuildingCommissionType. Получить список для просмотра по фильтру */
	public ENBuildingCommissionTypeShortList getScrollableFilteredList(
			ENBuildingCommissionTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingCommissionType. Получить список для просмотра по условию */
	public ENBuildingCommissionTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBuildingCommissionTypeFilter filterObject = new ENBuildingCommissionTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBuildingCommissionType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBuildingCommissionTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBuildingCommissionType. Получить объект из списка */
	public ENBuildingCommissionTypeShort getShortObject(int code) {
		try {
			ENBuildingCommissionTypeDAO objectDAO = new ENBuildingCommissionTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBuildingCommissionType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
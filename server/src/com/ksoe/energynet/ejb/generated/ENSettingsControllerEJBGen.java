
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSettingsDAO;
import com.ksoe.energynet.valueobject.ENSettings;
import com.ksoe.energynet.valueobject.lists.ENSettingsShortList;
import com.ksoe.energynet.valueobject.brief.ENSettingsShort;
import com.ksoe.energynet.valueobject.filter.ENSettingsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSettings;
 *
 */


public abstract class ENSettingsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSettingsControllerEJBGen() {
	}

	/* ENSettings. Добавить */
	public int add(ENSettings object) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSettings%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettings. Удалить */
	public void remove(int code) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSettings%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSettings. Изменить */
	public void save(ENSettings object) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSettings%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettings. Получить объект */
	public ENSettings getObject(int code) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettings%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettings. Получить полный список */
	public ENSettingsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSettings. Получить список по фильтру */
	public ENSettingsShortList getFilteredList(
			ENSettingsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSettings. Получить список для просмотра */
	public ENSettingsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSettings. Получить список для просмотра по фильтру */
	public ENSettingsShortList getScrollableFilteredList(
			ENSettingsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSettings%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettings. Получить список для просмотра по условию */
	public ENSettingsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSettingsFilter filterObject = new ENSettingsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSettings. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSettingsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSettings%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettings. Получить объект из списка */
	public ENSettingsShort getShortObject(int code) {
		try {
			ENSettingsDAO objectDAO = new ENSettingsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettings%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPowerReliabilityCategoryDAO;
import com.ksoe.energynet.valueobject.ENPowerReliabilityCategory;
import com.ksoe.energynet.valueobject.lists.ENPowerReliabilityCategoryShortList;
import com.ksoe.energynet.valueobject.brief.ENPowerReliabilityCategoryShort;
import com.ksoe.energynet.valueobject.filter.ENPowerReliabilityCategoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPowerReliabilityCategory;
 *
 */


public abstract class ENPowerReliabilityCategoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPowerReliabilityCategoryControllerEJBGen() {
	}

	/* ENPowerReliabilityCategory. Добавить */
	public int add(ENPowerReliabilityCategory object) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPowerReliabilityCategory. Удалить */
	public void remove(int code) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPowerReliabilityCategory. Изменить */
	public void save(ENPowerReliabilityCategory object) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPowerReliabilityCategory. Получить объект */
	public ENPowerReliabilityCategory getObject(int code) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPowerReliabilityCategory. Получить полный список */
	public ENPowerReliabilityCategoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPowerReliabilityCategory. Получить список по фильтру */
	public ENPowerReliabilityCategoryShortList getFilteredList(
			ENPowerReliabilityCategoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPowerReliabilityCategory. Получить список для просмотра */
	public ENPowerReliabilityCategoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPowerReliabilityCategory. Получить список для просмотра по фильтру */
	public ENPowerReliabilityCategoryShortList getScrollableFilteredList(
			ENPowerReliabilityCategoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPowerReliabilityCategory. Получить список для просмотра по условию */
	public ENPowerReliabilityCategoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPowerReliabilityCategoryFilter filterObject = new ENPowerReliabilityCategoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPowerReliabilityCategory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPowerReliabilityCategoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPowerReliabilityCategory. Получить объект из списка */
	public ENPowerReliabilityCategoryShort getShortObject(int code) {
		try {
			ENPowerReliabilityCategoryDAO objectDAO = new ENPowerReliabilityCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPowerReliabilityCategory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
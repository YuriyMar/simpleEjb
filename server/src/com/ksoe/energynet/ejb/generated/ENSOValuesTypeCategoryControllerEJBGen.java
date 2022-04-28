
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOValuesTypeCategoryDAO;
import com.ksoe.energynet.valueobject.ENSOValuesTypeCategory;
import com.ksoe.energynet.valueobject.lists.ENSOValuesTypeCategoryShortList;
import com.ksoe.energynet.valueobject.brief.ENSOValuesTypeCategoryShort;
import com.ksoe.energynet.valueobject.filter.ENSOValuesTypeCategoryFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOValuesTypeCategory;
 *
 */


public abstract class ENSOValuesTypeCategoryControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOValuesTypeCategoryControllerEJBGen() {
	}

	/* ENSOValuesTypeCategory. Добавить */
	public int add(ENSOValuesTypeCategory object) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesTypeCategory. Удалить */
	public void remove(int code) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOValuesTypeCategory. Изменить */
	public void save(ENSOValuesTypeCategory object) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesTypeCategory. Получить объект */
	public ENSOValuesTypeCategory getObject(int code) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesTypeCategory. Получить полный список */
	public ENSOValuesTypeCategoryShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOValuesTypeCategory. Получить список по фильтру */
	public ENSOValuesTypeCategoryShortList getFilteredList(
			ENSOValuesTypeCategoryFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOValuesTypeCategory. Получить список для просмотра */
	public ENSOValuesTypeCategoryShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOValuesTypeCategory. Получить список для просмотра по фильтру */
	public ENSOValuesTypeCategoryShortList getScrollableFilteredList(
			ENSOValuesTypeCategoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesTypeCategory. Получить список для просмотра по условию */
	public ENSOValuesTypeCategoryShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOValuesTypeCategoryFilter filterObject = new ENSOValuesTypeCategoryFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOValuesTypeCategory. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOValuesTypeCategoryFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOValuesTypeCategory. Получить объект из списка */
	public ENSOValuesTypeCategoryShort getShortObject(int code) {
		try {
			ENSOValuesTypeCategoryDAO objectDAO = new ENSOValuesTypeCategoryDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOValuesTypeCategory%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
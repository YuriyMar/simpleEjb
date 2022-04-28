
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.RegulatoryAssetBaseCalculationDAO;
import com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation;
import com.ksoe.energynet.valueobject.lists.RegulatoryAssetBaseCalculationShortList;
import com.ksoe.energynet.valueobject.brief.RegulatoryAssetBaseCalculationShort;
import com.ksoe.energynet.valueobject.filter.RegulatoryAssetBaseCalculationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for RegulatoryAssetBaseCalculation;
 *
 */


public abstract class RegulatoryAssetBaseCalculationControllerEJBGen extends
		GenericSessionStatelessBean {
	public RegulatoryAssetBaseCalculationControllerEJBGen() {
	}

	/* RegulatoryAssetBaseCalculation. Добавить */
	public int add(RegulatoryAssetBaseCalculation object) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RegulatoryAssetBaseCalculation. Удалить */
	public void remove(int code) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* RegulatoryAssetBaseCalculation. Изменить */
	public void save(RegulatoryAssetBaseCalculation object) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RegulatoryAssetBaseCalculation. Получить объект */
	public RegulatoryAssetBaseCalculation getObject(int code) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RegulatoryAssetBaseCalculation. Получить полный список */
	public RegulatoryAssetBaseCalculationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* RegulatoryAssetBaseCalculation. Получить список по фильтру */
	public RegulatoryAssetBaseCalculationShortList getFilteredList(
			RegulatoryAssetBaseCalculationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* RegulatoryAssetBaseCalculation. Получить список для просмотра */
	public RegulatoryAssetBaseCalculationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* RegulatoryAssetBaseCalculation. Получить список для просмотра по фильтру */
	public RegulatoryAssetBaseCalculationShortList getScrollableFilteredList(
			RegulatoryAssetBaseCalculationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RegulatoryAssetBaseCalculation. Получить список для просмотра по условию */
	public RegulatoryAssetBaseCalculationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		RegulatoryAssetBaseCalculationFilter filterObject = new RegulatoryAssetBaseCalculationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* RegulatoryAssetBaseCalculation. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			RegulatoryAssetBaseCalculationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RegulatoryAssetBaseCalculation. Получить объект из списка */
	public RegulatoryAssetBaseCalculationShort getShortObject(int code) {
		try {
			RegulatoryAssetBaseCalculationDAO objectDAO = new RegulatoryAssetBaseCalculationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.RegulatoryAssetBaseCalculation%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
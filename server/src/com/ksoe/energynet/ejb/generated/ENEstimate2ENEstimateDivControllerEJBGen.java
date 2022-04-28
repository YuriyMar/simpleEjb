
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENEstimate2ENEstimateDivDAO;
import com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv;
import com.ksoe.energynet.valueobject.lists.ENEstimate2ENEstimateDivShortList;
import com.ksoe.energynet.valueobject.brief.ENEstimate2ENEstimateDivShort;
import com.ksoe.energynet.valueobject.filter.ENEstimate2ENEstimateDivFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENEstimate2ENEstimateDiv;
 *
 */


public abstract class ENEstimate2ENEstimateDivControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENEstimate2ENEstimateDivControllerEJBGen() {
	}

	/* ENEstimate2ENEstimateDiv. Добавить */
	public int add(ENEstimate2ENEstimateDiv object) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimate2ENEstimateDiv. Удалить */
	public void remove(int code) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENEstimate2ENEstimateDiv. Изменить */
	public void save(ENEstimate2ENEstimateDiv object) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimate2ENEstimateDiv. Получить объект */
	public ENEstimate2ENEstimateDiv getObject(int code) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimate2ENEstimateDiv. Получить полный список */
	public ENEstimate2ENEstimateDivShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENEstimate2ENEstimateDiv. Получить список по фильтру */
	public ENEstimate2ENEstimateDivShortList getFilteredList(
			ENEstimate2ENEstimateDivFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENEstimate2ENEstimateDiv. Получить список для просмотра */
	public ENEstimate2ENEstimateDivShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENEstimate2ENEstimateDiv. Получить список для просмотра по фильтру */
	public ENEstimate2ENEstimateDivShortList getScrollableFilteredList(
			ENEstimate2ENEstimateDivFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimate2ENEstimateDiv. Получить список для просмотра по условию */
	public ENEstimate2ENEstimateDivShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENEstimate2ENEstimateDivFilter filterObject = new ENEstimate2ENEstimateDivFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENEstimate2ENEstimateDiv. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENEstimate2ENEstimateDivFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENEstimate2ENEstimateDiv. Получить объект из списка */
	public ENEstimate2ENEstimateDivShort getShortObject(int code) {
		try {
			ENEstimate2ENEstimateDivDAO objectDAO = new ENEstimate2ENEstimateDivDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENEstimate2ENEstimateDiv%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
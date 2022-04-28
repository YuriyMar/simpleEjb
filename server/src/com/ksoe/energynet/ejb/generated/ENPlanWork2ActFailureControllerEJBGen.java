
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanWork2ActFailureDAO;
import com.ksoe.energynet.valueobject.ENPlanWork2ActFailure;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2ActFailureShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanWork2ActFailureShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2ActFailureFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanWork2ActFailure;
 *
 */


public abstract class ENPlanWork2ActFailureControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanWork2ActFailureControllerEJBGen() {
	}

	/* ENPlanWork2ActFailure. Добавить */
	public int add(ENPlanWork2ActFailure object) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2ActFailure. Удалить */
	public void remove(int code) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWork2ActFailure. Изменить */
	public void save(ENPlanWork2ActFailure object) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2ActFailure. Получить объект */
	public ENPlanWork2ActFailure getObject(int code) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2ActFailure. Получить полный список */
	public ENPlanWork2ActFailureShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanWork2ActFailure. Получить список по фильтру */
	public ENPlanWork2ActFailureShortList getFilteredList(
			ENPlanWork2ActFailureFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanWork2ActFailure. Получить список для просмотра */
	public ENPlanWork2ActFailureShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanWork2ActFailure. Получить список для просмотра по фильтру */
	public ENPlanWork2ActFailureShortList getScrollableFilteredList(
			ENPlanWork2ActFailureFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2ActFailure. Получить список для просмотра по условию */
	public ENPlanWork2ActFailureShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanWork2ActFailureFilter filterObject = new ENPlanWork2ActFailureFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanWork2ActFailure. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanWork2ActFailureFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWork2ActFailure. Получить объект из списка */
	public ENPlanWork2ActFailureShort getShortObject(int code) {
		try {
			ENPlanWork2ActFailureDAO objectDAO = new ENPlanWork2ActFailureDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2ActFailure%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
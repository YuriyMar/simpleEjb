
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENReportAdditionZbytMetrologyDAO;
import com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology;
import com.ksoe.energynet.valueobject.lists.ENReportAdditionZbytMetrologyShortList;
import com.ksoe.energynet.valueobject.brief.ENReportAdditionZbytMetrologyShort;
import com.ksoe.energynet.valueobject.filter.ENReportAdditionZbytMetrologyFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENReportAdditionZbytMetrology;
 *
 */


public abstract class ENReportAdditionZbytMetrologyControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENReportAdditionZbytMetrologyControllerEJBGen() {
	}

	/* ENReportAdditionZbytMetrology. Добавить */
	public int add(ENReportAdditionZbytMetrology object) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReportAdditionZbytMetrology. Удалить */
	public void remove(int code) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENReportAdditionZbytMetrology. Изменить */
	public void save(ENReportAdditionZbytMetrology object) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReportAdditionZbytMetrology. Получить объект */
	public ENReportAdditionZbytMetrology getObject(int code) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReportAdditionZbytMetrology. Получить полный список */
	public ENReportAdditionZbytMetrologyShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENReportAdditionZbytMetrology. Получить список по фильтру */
	public ENReportAdditionZbytMetrologyShortList getFilteredList(
			ENReportAdditionZbytMetrologyFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENReportAdditionZbytMetrology. Получить список для просмотра */
	public ENReportAdditionZbytMetrologyShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENReportAdditionZbytMetrology. Получить список для просмотра по фильтру */
	public ENReportAdditionZbytMetrologyShortList getScrollableFilteredList(
			ENReportAdditionZbytMetrologyFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReportAdditionZbytMetrology. Получить список для просмотра по условию */
	public ENReportAdditionZbytMetrologyShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENReportAdditionZbytMetrologyFilter filterObject = new ENReportAdditionZbytMetrologyFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENReportAdditionZbytMetrology. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENReportAdditionZbytMetrologyFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReportAdditionZbytMetrology. Получить объект из списка */
	public ENReportAdditionZbytMetrologyShort getShortObject(int code) {
		try {
			ENReportAdditionZbytMetrologyDAO objectDAO = new ENReportAdditionZbytMetrologyDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENReportAdditionZbytMetrology%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
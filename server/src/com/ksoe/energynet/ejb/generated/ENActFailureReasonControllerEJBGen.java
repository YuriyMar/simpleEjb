
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActFailureReasonDAO;
import com.ksoe.energynet.valueobject.ENActFailureReason;
import com.ksoe.energynet.valueobject.lists.ENActFailureReasonShortList;
import com.ksoe.energynet.valueobject.brief.ENActFailureReasonShort;
import com.ksoe.energynet.valueobject.filter.ENActFailureReasonFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActFailureReason;
 *
 */


public abstract class ENActFailureReasonControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActFailureReasonControllerEJBGen() {
	}

	/* ENActFailureReason. Добавить */
	public int add(ENActFailureReason object) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActFailureReason%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailureReason. Удалить */
	public void remove(int code) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActFailureReason%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActFailureReason. Изменить */
	public void save(ENActFailureReason object) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActFailureReason%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailureReason. Получить объект */
	public ENActFailureReason getObject(int code) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActFailureReason%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailureReason. Получить полный список */
	public ENActFailureReasonShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActFailureReason. Получить список по фильтру */
	public ENActFailureReasonShortList getFilteredList(
			ENActFailureReasonFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActFailureReason. Получить список для просмотра */
	public ENActFailureReasonShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActFailureReason. Получить список для просмотра по фильтру */
	public ENActFailureReasonShortList getScrollableFilteredList(
			ENActFailureReasonFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActFailureReason%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailureReason. Получить список для просмотра по условию */
	public ENActFailureReasonShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActFailureReasonFilter filterObject = new ENActFailureReasonFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActFailureReason. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActFailureReasonFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActFailureReason%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActFailureReason. Получить объект из списка */
	public ENActFailureReasonShort getShortObject(int code) {
		try {
			ENActFailureReasonDAO objectDAO = new ENActFailureReasonDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActFailureReason%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
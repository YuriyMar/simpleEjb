
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDamageRecoveryStatusDAO;
import com.ksoe.energynet.valueobject.ENDamageRecoveryStatus;
import com.ksoe.energynet.valueobject.lists.ENDamageRecoveryStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENDamageRecoveryStatusShort;
import com.ksoe.energynet.valueobject.filter.ENDamageRecoveryStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDamageRecoveryStatus;
 *
 */


public abstract class ENDamageRecoveryStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDamageRecoveryStatusControllerEJBGen() {
	}

	/* ENDamageRecoveryStatus. Добавить */
	public int add(ENDamageRecoveryStatus object) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecoveryStatus. Удалить */
	public void remove(int code) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDamageRecoveryStatus. Изменить */
	public void save(ENDamageRecoveryStatus object) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecoveryStatus. Получить объект */
	public ENDamageRecoveryStatus getObject(int code) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecoveryStatus. Получить полный список */
	public ENDamageRecoveryStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDamageRecoveryStatus. Получить список по фильтру */
	public ENDamageRecoveryStatusShortList getFilteredList(
			ENDamageRecoveryStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDamageRecoveryStatus. Получить список для просмотра */
	public ENDamageRecoveryStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDamageRecoveryStatus. Получить список для просмотра по фильтру */
	public ENDamageRecoveryStatusShortList getScrollableFilteredList(
			ENDamageRecoveryStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecoveryStatus. Получить список для просмотра по условию */
	public ENDamageRecoveryStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDamageRecoveryStatusFilter filterObject = new ENDamageRecoveryStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDamageRecoveryStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDamageRecoveryStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecoveryStatus. Получить объект из списка */
	public ENDamageRecoveryStatusShort getShortObject(int code) {
		try {
			ENDamageRecoveryStatusDAO objectDAO = new ENDamageRecoveryStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecoveryStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
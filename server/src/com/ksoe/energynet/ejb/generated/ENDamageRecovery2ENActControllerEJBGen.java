
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDamageRecovery2ENActDAO;
import com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct;
import com.ksoe.energynet.valueobject.lists.ENDamageRecovery2ENActShortList;
import com.ksoe.energynet.valueobject.brief.ENDamageRecovery2ENActShort;
import com.ksoe.energynet.valueobject.filter.ENDamageRecovery2ENActFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDamageRecovery2ENAct;
 *
 */


public abstract class ENDamageRecovery2ENActControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDamageRecovery2ENActControllerEJBGen() {
	}

	/* ENDamageRecovery2ENAct. Добавить */
	public int add(ENDamageRecovery2ENAct object) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery2ENAct. Удалить */
	public void remove(int code) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDamageRecovery2ENAct. Изменить */
	public void save(ENDamageRecovery2ENAct object) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery2ENAct. Получить объект */
	public ENDamageRecovery2ENAct getObject(int code) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery2ENAct. Получить полный список */
	public ENDamageRecovery2ENActShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDamageRecovery2ENAct. Получить список по фильтру */
	public ENDamageRecovery2ENActShortList getFilteredList(
			ENDamageRecovery2ENActFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDamageRecovery2ENAct. Получить список для просмотра */
	public ENDamageRecovery2ENActShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDamageRecovery2ENAct. Получить список для просмотра по фильтру */
	public ENDamageRecovery2ENActShortList getScrollableFilteredList(
			ENDamageRecovery2ENActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery2ENAct. Получить список для просмотра по условию */
	public ENDamageRecovery2ENActShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDamageRecovery2ENActFilter filterObject = new ENDamageRecovery2ENActFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDamageRecovery2ENAct. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDamageRecovery2ENActFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDamageRecovery2ENAct. Получить объект из списка */
	public ENDamageRecovery2ENActShort getShortObject(int code) {
		try {
			ENDamageRecovery2ENActDAO objectDAO = new ENDamageRecovery2ENActDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDamageRecovery2ENAct%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
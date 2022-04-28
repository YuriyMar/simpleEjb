
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncome2ProvDAO;
import com.ksoe.energynet.valueobject.ENActIncome2Prov;
import com.ksoe.energynet.valueobject.lists.ENActIncome2ProvShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncome2ProvShort;
import com.ksoe.energynet.valueobject.filter.ENActIncome2ProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncome2Prov;
 *
 */


public abstract class ENActIncome2ProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncome2ProvControllerEJBGen() {
	}

	/* ENActIncome2Prov. Добавить */
	public int add(ENActIncome2Prov object) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncome2Prov. Удалить */
	public void remove(int code) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncome2Prov. Изменить */
	public void save(ENActIncome2Prov object) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncome2Prov. Получить объект */
	public ENActIncome2Prov getObject(int code) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncome2Prov. Получить полный список */
	public ENActIncome2ProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncome2Prov. Получить список по фильтру */
	public ENActIncome2ProvShortList getFilteredList(
			ENActIncome2ProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncome2Prov. Получить список для просмотра */
	public ENActIncome2ProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncome2Prov. Получить список для просмотра по фильтру */
	public ENActIncome2ProvShortList getScrollableFilteredList(
			ENActIncome2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncome2Prov. Получить список для просмотра по условию */
	public ENActIncome2ProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncome2ProvFilter filterObject = new ENActIncome2ProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncome2Prov. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActIncome2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncome2Prov. Получить объект из списка */
	public ENActIncome2ProvShort getShortObject(int code) {
		try {
			ENActIncome2ProvDAO objectDAO = new ENActIncome2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncome2Prov%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
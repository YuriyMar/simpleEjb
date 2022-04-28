
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2ProvDAO;
import com.ksoe.energynet.valueobject.ENAct2Prov;
import com.ksoe.energynet.valueobject.lists.ENAct2ProvShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2ProvShort;
import com.ksoe.energynet.valueobject.filter.ENAct2ProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2Prov;
 *
 */


public abstract class ENAct2ProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2ProvControllerEJBGen() {
	}

	/* ENAct2Prov. Добавить */
	public int add(ENAct2Prov object) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Prov. Удалить */
	public void remove(int code) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2Prov. Изменить */
	public void save(ENAct2Prov object) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Prov. Получить объект */
	public ENAct2Prov getObject(int code) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Prov. Получить полный список */
	public ENAct2ProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2Prov. Получить список по фильтру */
	public ENAct2ProvShortList getFilteredList(
			ENAct2ProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2Prov. Получить список для просмотра */
	public ENAct2ProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2Prov. Получить список для просмотра по фильтру */
	public ENAct2ProvShortList getScrollableFilteredList(
			ENAct2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Prov. Получить список для просмотра по условию */
	public ENAct2ProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2ProvFilter filterObject = new ENAct2ProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2Prov. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Prov. Получить объект из списка */
	public ENAct2ProvShort getShortObject(int code) {
		try {
			ENAct2ProvDAO objectDAO = new ENAct2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2Prov%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
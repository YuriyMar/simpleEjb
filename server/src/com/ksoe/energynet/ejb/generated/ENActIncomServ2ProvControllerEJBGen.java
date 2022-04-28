
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncomServ2ProvDAO;
import com.ksoe.energynet.valueobject.ENActIncomServ2Prov;
import com.ksoe.energynet.valueobject.lists.ENActIncomServ2ProvShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncomServ2ProvShort;
import com.ksoe.energynet.valueobject.filter.ENActIncomServ2ProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncomServ2Prov;
 *
 */


public abstract class ENActIncomServ2ProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncomServ2ProvControllerEJBGen() {
	}

	/* ENActIncomServ2Prov. Добавить */
	public int add(ENActIncomServ2Prov object) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2Prov. Удалить */
	public void remove(int code) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncomServ2Prov. Изменить */
	public void save(ENActIncomServ2Prov object) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2Prov. Получить объект */
	public ENActIncomServ2Prov getObject(int code) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2Prov. Получить полный список */
	public ENActIncomServ2ProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncomServ2Prov. Получить список по фильтру */
	public ENActIncomServ2ProvShortList getFilteredList(
			ENActIncomServ2ProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncomServ2Prov. Получить список для просмотра */
	public ENActIncomServ2ProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncomServ2Prov. Получить список для просмотра по фильтру */
	public ENActIncomServ2ProvShortList getScrollableFilteredList(
			ENActIncomServ2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2Prov. Получить список для просмотра по условию */
	public ENActIncomServ2ProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncomServ2ProvFilter filterObject = new ENActIncomServ2ProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncomServ2Prov. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActIncomServ2ProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomServ2Prov. Получить объект из списка */
	public ENActIncomServ2ProvShort getShortObject(int code) {
		try {
			ENActIncomServ2ProvDAO objectDAO = new ENActIncomServ2ProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomServ2Prov%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
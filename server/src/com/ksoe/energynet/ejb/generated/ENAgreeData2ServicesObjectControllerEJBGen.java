
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAgreeData2ServicesObjectDAO;
import com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject;
import com.ksoe.energynet.valueobject.lists.ENAgreeData2ServicesObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENAgreeData2ServicesObjectShort;
import com.ksoe.energynet.valueobject.filter.ENAgreeData2ServicesObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAgreeData2ServicesObject;
 *
 */


public abstract class ENAgreeData2ServicesObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAgreeData2ServicesObjectControllerEJBGen() {
	}

	/* ENAgreeData2ServicesObject. Добавить */
	public int add(ENAgreeData2ServicesObject object) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreeData2ServicesObject. Удалить */
	public void remove(int code) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAgreeData2ServicesObject. Изменить */
	public void save(ENAgreeData2ServicesObject object) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreeData2ServicesObject. Получить объект */
	public ENAgreeData2ServicesObject getObject(int code) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreeData2ServicesObject. Получить полный список */
	public ENAgreeData2ServicesObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAgreeData2ServicesObject. Получить список по фильтру */
	public ENAgreeData2ServicesObjectShortList getFilteredList(
			ENAgreeData2ServicesObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAgreeData2ServicesObject. Получить список для просмотра */
	public ENAgreeData2ServicesObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAgreeData2ServicesObject. Получить список для просмотра по фильтру */
	public ENAgreeData2ServicesObjectShortList getScrollableFilteredList(
			ENAgreeData2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreeData2ServicesObject. Получить список для просмотра по условию */
	public ENAgreeData2ServicesObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAgreeData2ServicesObjectFilter filterObject = new ENAgreeData2ServicesObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAgreeData2ServicesObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAgreeData2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAgreeData2ServicesObject. Получить объект из списка */
	public ENAgreeData2ServicesObjectShort getShortObject(int code) {
		try {
			ENAgreeData2ServicesObjectDAO objectDAO = new ENAgreeData2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAgreeData2ServicesObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
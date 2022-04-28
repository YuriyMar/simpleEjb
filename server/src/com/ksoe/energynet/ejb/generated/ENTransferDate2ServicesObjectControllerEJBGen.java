
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTransferDate2ServicesObjectDAO;
import com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject;
import com.ksoe.energynet.valueobject.lists.ENTransferDate2ServicesObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENTransferDate2ServicesObjectShort;
import com.ksoe.energynet.valueobject.filter.ENTransferDate2ServicesObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTransferDate2ServicesObject;
 *
 */


public abstract class ENTransferDate2ServicesObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTransferDate2ServicesObjectControllerEJBGen() {
	}

	/* ENTransferDate2ServicesObject. Добавить */
	public int add(ENTransferDate2ServicesObject object) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransferDate2ServicesObject. Удалить */
	public void remove(int code) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTransferDate2ServicesObject. Изменить */
	public void save(ENTransferDate2ServicesObject object) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransferDate2ServicesObject. Получить объект */
	public ENTransferDate2ServicesObject getObject(int code) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransferDate2ServicesObject. Получить полный список */
	public ENTransferDate2ServicesObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTransferDate2ServicesObject. Получить список по фильтру */
	public ENTransferDate2ServicesObjectShortList getFilteredList(
			ENTransferDate2ServicesObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTransferDate2ServicesObject. Получить список для просмотра */
	public ENTransferDate2ServicesObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTransferDate2ServicesObject. Получить список для просмотра по фильтру */
	public ENTransferDate2ServicesObjectShortList getScrollableFilteredList(
			ENTransferDate2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransferDate2ServicesObject. Получить список для просмотра по условию */
	public ENTransferDate2ServicesObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTransferDate2ServicesObjectFilter filterObject = new ENTransferDate2ServicesObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTransferDate2ServicesObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTransferDate2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransferDate2ServicesObject. Получить объект из списка */
	public ENTransferDate2ServicesObjectShort getShortObject(int code) {
		try {
			ENTransferDate2ServicesObjectDAO objectDAO = new ENTransferDate2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransferDate2ServicesObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
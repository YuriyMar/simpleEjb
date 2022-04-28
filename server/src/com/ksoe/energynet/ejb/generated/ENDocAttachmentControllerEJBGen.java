
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.energynet.dataminer.ENDocAttachmentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDocAttachment;
import com.ksoe.energynet.valueobject.brief.ENDocAttachmentShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachmentFilter;
import com.ksoe.energynet.valueobject.lists.ENDocAttachmentShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment;
 *
 */


public abstract class ENDocAttachmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachmentControllerEJBGen() {
	}

	/* ENDocAttachment. Добавить */
	public int add(ENDocAttachment object) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment. Изменить */
	public void save(ENDocAttachment object) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment. Получить объект */
	public ENDocAttachment getObject(int code) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment. Получить полный список */
	public ENDocAttachmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment. Получить список по фильтру */
	public ENDocAttachmentShortList getFilteredList(
			ENDocAttachmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment. Получить список для просмотра */
	public ENDocAttachmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment. Получить список для просмотра по фильтру */
	public ENDocAttachmentShortList getScrollableFilteredList(
			ENDocAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment. Получить список для просмотра по условию */
	public ENDocAttachmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachmentFilter filterObject = new ENDocAttachmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment. Получить объект из списка */
	public ENDocAttachmentShort getShortObject(int code) {
		try {
			ENDocAttachmentDAO objectDAO = new ENDocAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
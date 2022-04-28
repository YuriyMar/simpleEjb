
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.CNAttachmentDAO;
import com.ksoe.energynet.valueobject.CNAttachment;
import com.ksoe.energynet.valueobject.lists.CNAttachmentShortList;
import com.ksoe.energynet.valueobject.brief.CNAttachmentShort;
import com.ksoe.energynet.valueobject.filter.CNAttachmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for CNAttachment;
 *
 */


public abstract class CNAttachmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public CNAttachmentControllerEJBGen() {
	}

	/* CNAttachment. Добавить */
	public int add(CNAttachment object) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.CNAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNAttachment. Удалить */
	public void remove(int code) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.CNAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* CNAttachment. Изменить */
	public void save(CNAttachment object) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.CNAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNAttachment. Получить объект */
	public CNAttachment getObject(int code) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNAttachment. Получить полный список */
	public CNAttachmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* CNAttachment. Получить список по фильтру */
	public CNAttachmentShortList getFilteredList(
			CNAttachmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* CNAttachment. Получить список для просмотра */
	public CNAttachmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* CNAttachment. Получить список для просмотра по фильтру */
	public CNAttachmentShortList getScrollableFilteredList(
			CNAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.CNAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNAttachment. Получить список для просмотра по условию */
	public CNAttachmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		CNAttachmentFilter filterObject = new CNAttachmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* CNAttachment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			CNAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.CNAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* CNAttachment. Получить объект из списка */
	public CNAttachmentShort getShortObject(int code) {
		try {
			CNAttachmentDAO objectDAO = new CNAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.CNAttachment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
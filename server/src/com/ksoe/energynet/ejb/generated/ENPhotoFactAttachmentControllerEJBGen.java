
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPhotoFactAttachmentDAO;
import com.ksoe.energynet.valueobject.ENPhotoFactAttachment;
import com.ksoe.energynet.valueobject.lists.ENPhotoFactAttachmentShortList;
import com.ksoe.energynet.valueobject.brief.ENPhotoFactAttachmentShort;
import com.ksoe.energynet.valueobject.filter.ENPhotoFactAttachmentFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPhotoFactAttachment;
 *
 */


public abstract class ENPhotoFactAttachmentControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPhotoFactAttachmentControllerEJBGen() {
	}

	/* ENPhotoFactAttachment. Добавить */
	public int add(ENPhotoFactAttachment object) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFactAttachment. Удалить */
	public void remove(int code) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPhotoFactAttachment. Изменить */
	public void save(ENPhotoFactAttachment object) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFactAttachment. Получить объект */
	public ENPhotoFactAttachment getObject(int code) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFactAttachment. Получить полный список */
	public ENPhotoFactAttachmentShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPhotoFactAttachment. Получить список по фильтру */
	public ENPhotoFactAttachmentShortList getFilteredList(
			ENPhotoFactAttachmentFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPhotoFactAttachment. Получить список для просмотра */
	public ENPhotoFactAttachmentShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPhotoFactAttachment. Получить список для просмотра по фильтру */
	public ENPhotoFactAttachmentShortList getScrollableFilteredList(
			ENPhotoFactAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFactAttachment. Получить список для просмотра по условию */
	public ENPhotoFactAttachmentShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPhotoFactAttachmentFilter filterObject = new ENPhotoFactAttachmentFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPhotoFactAttachment. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPhotoFactAttachmentFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFactAttachment. Получить объект из списка */
	public ENPhotoFactAttachmentShort getShortObject(int code) {
		try {
			ENPhotoFactAttachmentDAO objectDAO = new ENPhotoFactAttachmentDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPhotoFactAttachment%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENDocAttachmentStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDocAttachmentStatus;
import com.ksoe.energynet.valueobject.brief.ENDocAttachmentStatusShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachmentStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENDocAttachmentStatusShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachmentStatus;
 *
 */


public abstract class ENDocAttachmentStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachmentStatusControllerEJBGen() {
	}

	/* ENDocAttachmentStatus. Добавить */
	public int add(ENDocAttachmentStatus object) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentStatus. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachmentStatus. Изменить */
	public void save(ENDocAttachmentStatus object) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentStatus. Получить объект */
	public ENDocAttachmentStatus getObject(int code) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentStatus. Получить полный список */
	public ENDocAttachmentStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachmentStatus. Получить список по фильтру */
	public ENDocAttachmentStatusShortList getFilteredList(
			ENDocAttachmentStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachmentStatus. Получить список для просмотра */
	public ENDocAttachmentStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachmentStatus. Получить список для просмотра по фильтру */
	public ENDocAttachmentStatusShortList getScrollableFilteredList(
			ENDocAttachmentStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentStatus. Получить список для просмотра по условию */
	public ENDocAttachmentStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachmentStatusFilter filterObject = new ENDocAttachmentStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachmentStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachmentStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentStatus. Получить объект из списка */
	public ENDocAttachmentStatusShort getShortObject(int code) {
		try {
			ENDocAttachmentStatusDAO objectDAO = new ENDocAttachmentStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
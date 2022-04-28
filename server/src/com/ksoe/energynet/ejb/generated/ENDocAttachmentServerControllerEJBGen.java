
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachmentServerDAO;
import com.ksoe.energynet.valueobject.ENDocAttachmentServer;
import com.ksoe.energynet.valueobject.lists.ENDocAttachmentServerShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachmentServerShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachmentServerFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachmentServer;
 *
 */


public abstract class ENDocAttachmentServerControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachmentServerControllerEJBGen() {
	}

	/* ENDocAttachmentServer. Добавить */
	public int add(ENDocAttachmentServer object) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentServer. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachmentServer. Изменить */
	public void save(ENDocAttachmentServer object) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentServer. Получить объект */
	public ENDocAttachmentServer getObject(int code) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentServer. Получить полный список */
	public ENDocAttachmentServerShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachmentServer. Получить список по фильтру */
	public ENDocAttachmentServerShortList getFilteredList(
			ENDocAttachmentServerFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachmentServer. Получить список для просмотра */
	public ENDocAttachmentServerShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachmentServer. Получить список для просмотра по фильтру */
	public ENDocAttachmentServerShortList getScrollableFilteredList(
			ENDocAttachmentServerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentServer. Получить список для просмотра по условию */
	public ENDocAttachmentServerShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachmentServerFilter filterObject = new ENDocAttachmentServerFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachmentServer. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachmentServerFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentServer. Получить объект из списка */
	public ENDocAttachmentServerShort getShortObject(int code) {
		try {
			ENDocAttachmentServerDAO objectDAO = new ENDocAttachmentServerDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentServer%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
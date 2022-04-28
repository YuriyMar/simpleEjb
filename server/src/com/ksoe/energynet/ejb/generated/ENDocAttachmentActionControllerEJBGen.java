
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachmentActionDAO;
import com.ksoe.energynet.valueobject.ENDocAttachmentAction;
import com.ksoe.energynet.valueobject.lists.ENDocAttachmentActionShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachmentActionShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachmentActionFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachmentAction;
 *
 */


public abstract class ENDocAttachmentActionControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachmentActionControllerEJBGen() {
	}

	/* ENDocAttachmentAction. Добавить */
	public int add(ENDocAttachmentAction object) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentAction. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachmentAction. Изменить */
	public void save(ENDocAttachmentAction object) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentAction. Получить объект */
	public ENDocAttachmentAction getObject(int code) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentAction. Получить полный список */
	public ENDocAttachmentActionShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachmentAction. Получить список по фильтру */
	public ENDocAttachmentActionShortList getFilteredList(
			ENDocAttachmentActionFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachmentAction. Получить список для просмотра */
	public ENDocAttachmentActionShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachmentAction. Получить список для просмотра по фильтру */
	public ENDocAttachmentActionShortList getScrollableFilteredList(
			ENDocAttachmentActionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentAction. Получить список для просмотра по условию */
	public ENDocAttachmentActionShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachmentActionFilter filterObject = new ENDocAttachmentActionFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachmentAction. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachmentActionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentAction. Получить объект из списка */
	public ENDocAttachmentActionShort getShortObject(int code) {
		try {
			ENDocAttachmentActionDAO objectDAO = new ENDocAttachmentActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentAction%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
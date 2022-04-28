
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachmentTypeDAO;
import com.ksoe.energynet.valueobject.ENDocAttachmentType;
import com.ksoe.energynet.valueobject.lists.ENDocAttachmentTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachmentTypeShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachmentTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachmentType;
 *
 */


public abstract class ENDocAttachmentTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachmentTypeControllerEJBGen() {
	}

	/* ENDocAttachmentType. Добавить */
	public int add(ENDocAttachmentType object) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentType. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachmentType. Изменить */
	public void save(ENDocAttachmentType object) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentType. Получить объект */
	public ENDocAttachmentType getObject(int code) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentType. Получить полный список */
	public ENDocAttachmentTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachmentType. Получить список по фильтру */
	public ENDocAttachmentTypeShortList getFilteredList(
			ENDocAttachmentTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachmentType. Получить список для просмотра */
	public ENDocAttachmentTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachmentType. Получить список для просмотра по фильтру */
	public ENDocAttachmentTypeShortList getScrollableFilteredList(
			ENDocAttachmentTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentType. Получить список для просмотра по условию */
	public ENDocAttachmentTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachmentTypeFilter filterObject = new ENDocAttachmentTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachmentType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachmentTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachmentType. Получить объект из списка */
	public ENDocAttachmentTypeShort getShortObject(int code) {
		try {
			ENDocAttachmentTypeDAO objectDAO = new ENDocAttachmentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachmentType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
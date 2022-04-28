
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachment2ENWarrantDAO;
import com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant;
import com.ksoe.energynet.valueobject.lists.ENDocAttachment2ENWarrantShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachment2ENWarrantShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachment2ENWarrantFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment2ENWarrant;
 *
 */


public abstract class ENDocAttachment2ENWarrantControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachment2ENWarrantControllerEJBGen() {
	}

	/* ENDocAttachment2ENWarrant. Добавить */
	public int add(ENDocAttachment2ENWarrant object) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENWarrant. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment2ENWarrant. Изменить */
	public void save(ENDocAttachment2ENWarrant object) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENWarrant. Получить объект */
	public ENDocAttachment2ENWarrant getObject(int code) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENWarrant. Получить полный список */
	public ENDocAttachment2ENWarrantShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment2ENWarrant. Получить список по фильтру */
	public ENDocAttachment2ENWarrantShortList getFilteredList(
			ENDocAttachment2ENWarrantFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment2ENWarrant. Получить список для просмотра */
	public ENDocAttachment2ENWarrantShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment2ENWarrant. Получить список для просмотра по фильтру */
	public ENDocAttachment2ENWarrantShortList getScrollableFilteredList(
			ENDocAttachment2ENWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENWarrant. Получить список для просмотра по условию */
	public ENDocAttachment2ENWarrantShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachment2ENWarrantFilter filterObject = new ENDocAttachment2ENWarrantFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment2ENWarrant. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachment2ENWarrantFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENWarrant. Получить объект из списка */
	public ENDocAttachment2ENWarrantShort getShortObject(int code) {
		try {
			ENDocAttachment2ENWarrantDAO objectDAO = new ENDocAttachment2ENWarrantDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENWarrant%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
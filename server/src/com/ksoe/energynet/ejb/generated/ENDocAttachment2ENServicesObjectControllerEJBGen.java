
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENDocAttachment2ENServicesObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject;
import com.ksoe.energynet.valueobject.brief.ENDocAttachment2ENServicesObjectShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachment2ENServicesObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENDocAttachment2ENServicesObjectShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment2ENServicesObject;
 *
 */


public abstract class ENDocAttachment2ENServicesObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachment2ENServicesObjectControllerEJBGen() {
	}

	/* ENDocAttachment2ENServicesObject. Добавить */
	public int add(ENDocAttachment2ENServicesObject object) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENServicesObject. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment2ENServicesObject. Изменить */
	public void save(ENDocAttachment2ENServicesObject object) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENServicesObject. Получить объект */
	public ENDocAttachment2ENServicesObject getObject(int code) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENServicesObject. Получить полный список */
	public ENDocAttachment2ENServicesObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment2ENServicesObject. Получить список по фильтру */
	public ENDocAttachment2ENServicesObjectShortList getFilteredList(
			ENDocAttachment2ENServicesObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment2ENServicesObject. Получить список для просмотра */
	public ENDocAttachment2ENServicesObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment2ENServicesObject. Получить список для просмотра по фильтру */
	public ENDocAttachment2ENServicesObjectShortList getScrollableFilteredList(
			ENDocAttachment2ENServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENServicesObject. Получить список для просмотра по условию */
	public ENDocAttachment2ENServicesObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachment2ENServicesObjectFilter filterObject = new ENDocAttachment2ENServicesObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment2ENServicesObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachment2ENServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2ENServicesObject. Получить объект из списка */
	public ENDocAttachment2ENServicesObjectShort getShortObject(int code) {
		try {
			ENDocAttachment2ENServicesObjectDAO objectDAO = new ENDocAttachment2ENServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2ENServicesObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
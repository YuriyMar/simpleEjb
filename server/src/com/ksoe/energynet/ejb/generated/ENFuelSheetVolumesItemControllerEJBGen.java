
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.energynet.dataminer.ENFuelSheetVolumesItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem;
import com.ksoe.energynet.valueobject.brief.ENFuelSheetVolumesItemShort;
import com.ksoe.energynet.valueobject.filter.ENFuelSheetVolumesItemFilter;
import com.ksoe.energynet.valueobject.lists.ENFuelSheetVolumesItemShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelSheetVolumesItem;
 *
 */


public abstract class ENFuelSheetVolumesItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelSheetVolumesItemControllerEJBGen() {
	}

	/* ENFuelSheetVolumesItem. Добавить */
	public int add(ENFuelSheetVolumesItem object) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesItem. Удалить */
	public void remove(int code) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelSheetVolumesItem. Изменить */
	public void save(ENFuelSheetVolumesItem object) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesItem. Получить объект */
	public ENFuelSheetVolumesItem getObject(int code) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesItem. Получить полный список */
	public ENFuelSheetVolumesItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelSheetVolumesItem. Получить список по фильтру */
	public ENFuelSheetVolumesItemShortList getFilteredList(
			ENFuelSheetVolumesItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelSheetVolumesItem. Получить список для просмотра */
	public ENFuelSheetVolumesItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelSheetVolumesItem. Получить список для просмотра по фильтру */
	public ENFuelSheetVolumesItemShortList getScrollableFilteredList(
			ENFuelSheetVolumesItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesItem. Получить список для просмотра по условию */
	public ENFuelSheetVolumesItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelSheetVolumesItemFilter filterObject = new ENFuelSheetVolumesItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelSheetVolumesItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelSheetVolumesItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumesItem. Получить объект из списка */
	public ENFuelSheetVolumesItemShort getShortObject(int code) {
		try {
			ENFuelSheetVolumesItemDAO objectDAO = new ENFuelSheetVolumesItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumesItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
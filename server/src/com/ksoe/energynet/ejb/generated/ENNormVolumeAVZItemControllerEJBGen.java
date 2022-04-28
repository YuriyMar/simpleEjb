
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENNormVolumeAVZItemDAO;
import com.ksoe.energynet.valueobject.ENNormVolumeAVZItem;
import com.ksoe.energynet.valueobject.lists.ENNormVolumeAVZItemShortList;
import com.ksoe.energynet.valueobject.brief.ENNormVolumeAVZItemShort;
import com.ksoe.energynet.valueobject.filter.ENNormVolumeAVZItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENNormVolumeAVZItem;
 *
 */


public abstract class ENNormVolumeAVZItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENNormVolumeAVZItemControllerEJBGen() {
	}

	/* ENNormVolumeAVZItem. Добавить */
	public int add(ENNormVolumeAVZItem object) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormVolumeAVZItem. Удалить */
	public void remove(int code) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENNormVolumeAVZItem. Изменить */
	public void save(ENNormVolumeAVZItem object) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormVolumeAVZItem. Получить объект */
	public ENNormVolumeAVZItem getObject(int code) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormVolumeAVZItem. Получить полный список */
	public ENNormVolumeAVZItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENNormVolumeAVZItem. Получить список по фильтру */
	public ENNormVolumeAVZItemShortList getFilteredList(
			ENNormVolumeAVZItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENNormVolumeAVZItem. Получить список для просмотра */
	public ENNormVolumeAVZItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENNormVolumeAVZItem. Получить список для просмотра по фильтру */
	public ENNormVolumeAVZItemShortList getScrollableFilteredList(
			ENNormVolumeAVZItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormVolumeAVZItem. Получить список для просмотра по условию */
	public ENNormVolumeAVZItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENNormVolumeAVZItemFilter filterObject = new ENNormVolumeAVZItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENNormVolumeAVZItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENNormVolumeAVZItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormVolumeAVZItem. Получить объект из списка */
	public ENNormVolumeAVZItemShort getShortObject(int code) {
		try {
			ENNormVolumeAVZItemDAO objectDAO = new ENNormVolumeAVZItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNormVolumeAVZItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
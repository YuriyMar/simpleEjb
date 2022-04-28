
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENNormativeVolumeAVZDAO;
import com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ;
import com.ksoe.energynet.valueobject.lists.ENNormativeVolumeAVZShortList;
import com.ksoe.energynet.valueobject.brief.ENNormativeVolumeAVZShort;
import com.ksoe.energynet.valueobject.filter.ENNormativeVolumeAVZFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENNormativeVolumeAVZ;
 *
 */


public abstract class ENNormativeVolumeAVZControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENNormativeVolumeAVZControllerEJBGen() {
	}

	/* ENNormativeVolumeAVZ. Добавить */
	public int add(ENNormativeVolumeAVZ object) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormativeVolumeAVZ. Удалить */
	public void remove(int code) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENNormativeVolumeAVZ. Изменить */
	public void save(ENNormativeVolumeAVZ object) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormativeVolumeAVZ. Получить объект */
	public ENNormativeVolumeAVZ getObject(int code) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormativeVolumeAVZ. Получить полный список */
	public ENNormativeVolumeAVZShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENNormativeVolumeAVZ. Получить список по фильтру */
	public ENNormativeVolumeAVZShortList getFilteredList(
			ENNormativeVolumeAVZFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENNormativeVolumeAVZ. Получить список для просмотра */
	public ENNormativeVolumeAVZShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENNormativeVolumeAVZ. Получить список для просмотра по фильтру */
	public ENNormativeVolumeAVZShortList getScrollableFilteredList(
			ENNormativeVolumeAVZFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormativeVolumeAVZ. Получить список для просмотра по условию */
	public ENNormativeVolumeAVZShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENNormativeVolumeAVZFilter filterObject = new ENNormativeVolumeAVZFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENNormativeVolumeAVZ. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENNormativeVolumeAVZFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENNormativeVolumeAVZ. Получить объект из списка */
	public ENNormativeVolumeAVZShort getShortObject(int code) {
		try {
			ENNormativeVolumeAVZDAO objectDAO = new ENNormativeVolumeAVZDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENNormativeVolumeAVZ%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
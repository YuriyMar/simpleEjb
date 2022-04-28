
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.energynet.dataminer.ENFuelSheetVolumesDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENFuelSheetVolumes;
import com.ksoe.energynet.valueobject.brief.ENFuelSheetVolumesShort;
import com.ksoe.energynet.valueobject.filter.ENFuelSheetVolumesFilter;
import com.ksoe.energynet.valueobject.lists.ENFuelSheetVolumesShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelSheetVolumes;
 *
 */


public abstract class ENFuelSheetVolumesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelSheetVolumesControllerEJBGen() {
	}

	/* ENFuelSheetVolumes. Добавить */
	public int add(ENFuelSheetVolumes object) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumes. Удалить */
	public void remove(int code) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelSheetVolumes. Изменить */
	public void save(ENFuelSheetVolumes object) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumes. Получить объект */
	public ENFuelSheetVolumes getObject(int code) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumes. Получить полный список */
	public ENFuelSheetVolumesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelSheetVolumes. Получить список по фильтру */
	public ENFuelSheetVolumesShortList getFilteredList(
			ENFuelSheetVolumesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelSheetVolumes. Получить список для просмотра */
	public ENFuelSheetVolumesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelSheetVolumes. Получить список для просмотра по фильтру */
	public ENFuelSheetVolumesShortList getScrollableFilteredList(
			ENFuelSheetVolumesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumes. Получить список для просмотра по условию */
	public ENFuelSheetVolumesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelSheetVolumesFilter filterObject = new ENFuelSheetVolumesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelSheetVolumes. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelSheetVolumesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolumes. Получить объект из списка */
	public ENFuelSheetVolumesShort getShortObject(int code) {
		try {
			ENFuelSheetVolumesDAO objectDAO = new ENFuelSheetVolumesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolumes%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
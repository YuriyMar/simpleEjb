
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTravelSheetFuel2FINMaterialsDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetFuel2FINMaterialsShortList;
import com.ksoe.energynet.valueobject.brief.ENTravelSheetFuel2FINMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetFuel2FINMaterialsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTravelSheetFuel2FINMaterials;
 *
 */


public abstract class ENTravelSheetFuel2FINMaterialsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTravelSheetFuel2FINMaterialsControllerEJBGen() {
	}

	/* ENTravelSheetFuel2FINMaterials. Добавить */
	public int add(ENTravelSheetFuel2FINMaterials object) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuel2FINMaterials. Удалить */
	public void remove(int code) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTravelSheetFuel2FINMaterials. Изменить */
	public void save(ENTravelSheetFuel2FINMaterials object) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuel2FINMaterials. Получить объект */
	public ENTravelSheetFuel2FINMaterials getObject(int code) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuel2FINMaterials. Получить полный список */
	public ENTravelSheetFuel2FINMaterialsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTravelSheetFuel2FINMaterials. Получить список по фильтру */
	public ENTravelSheetFuel2FINMaterialsShortList getFilteredList(
			ENTravelSheetFuel2FINMaterialsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTravelSheetFuel2FINMaterials. Получить список для просмотра */
	public ENTravelSheetFuel2FINMaterialsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTravelSheetFuel2FINMaterials. Получить список для просмотра по фильтру */
	public ENTravelSheetFuel2FINMaterialsShortList getScrollableFilteredList(
			ENTravelSheetFuel2FINMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuel2FINMaterials. Получить список для просмотра по условию */
	public ENTravelSheetFuel2FINMaterialsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTravelSheetFuel2FINMaterialsFilter filterObject = new ENTravelSheetFuel2FINMaterialsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTravelSheetFuel2FINMaterials. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTravelSheetFuel2FINMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTravelSheetFuel2FINMaterials. Получить объект из списка */
	public ENTravelSheetFuel2FINMaterialsShort getShortObject(int code) {
		try {
			ENTravelSheetFuel2FINMaterialsDAO objectDAO = new ENTravelSheetFuel2FINMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuel2FINMaterials%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
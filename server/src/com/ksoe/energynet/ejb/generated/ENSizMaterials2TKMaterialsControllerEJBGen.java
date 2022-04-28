
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSizMaterials2TKMaterialsDAO;
import com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials;
import com.ksoe.energynet.valueobject.lists.ENSizMaterials2TKMaterialsShortList;
import com.ksoe.energynet.valueobject.brief.ENSizMaterials2TKMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENSizMaterials2TKMaterialsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSizMaterials2TKMaterials;
 *
 */


public abstract class ENSizMaterials2TKMaterialsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSizMaterials2TKMaterialsControllerEJBGen() {
	}

	/* ENSizMaterials2TKMaterials. Добавить */
	public int add(ENSizMaterials2TKMaterials object) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizMaterials2TKMaterials. Удалить */
	public void remove(int code) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSizMaterials2TKMaterials. Изменить */
	public void save(ENSizMaterials2TKMaterials object) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userEdit = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizMaterials2TKMaterials. Получить объект */
	public ENSizMaterials2TKMaterials getObject(int code) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizMaterials2TKMaterials. Получить полный список */
	public ENSizMaterials2TKMaterialsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSizMaterials2TKMaterials. Получить список по фильтру */
	public ENSizMaterials2TKMaterialsShortList getFilteredList(
			ENSizMaterials2TKMaterialsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSizMaterials2TKMaterials. Получить список для просмотра */
	public ENSizMaterials2TKMaterialsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSizMaterials2TKMaterials. Получить список для просмотра по фильтру */
	public ENSizMaterials2TKMaterialsShortList getScrollableFilteredList(
			ENSizMaterials2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizMaterials2TKMaterials. Получить список для просмотра по условию */
	public ENSizMaterials2TKMaterialsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSizMaterials2TKMaterialsFilter filterObject = new ENSizMaterials2TKMaterialsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSizMaterials2TKMaterials. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSizMaterials2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSizMaterials2TKMaterials. Получить объект из списка */
	public ENSizMaterials2TKMaterialsShort getShortObject(int code) {
		try {
			ENSizMaterials2TKMaterialsDAO objectDAO = new ENSizMaterials2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSizMaterials2TKMaterials%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
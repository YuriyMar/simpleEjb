
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPItem2TKMaterialsDAO;
import com.ksoe.energynet.valueobject.ENIPItem2TKMaterials;
import com.ksoe.energynet.valueobject.lists.ENIPItem2TKMaterialsShortList;
import com.ksoe.energynet.valueobject.brief.ENIPItem2TKMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENIPItem2TKMaterialsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPItem2TKMaterials;
 *
 */


public abstract class ENIPItem2TKMaterialsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPItem2TKMaterialsControllerEJBGen() {
	}

	/* ENIPItem2TKMaterials. Добавить */
	public int add(ENIPItem2TKMaterials object) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2TKMaterials. Удалить */
	public void remove(int code) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPItem2TKMaterials. Изменить */
	public void save(ENIPItem2TKMaterials object) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2TKMaterials. Получить объект */
	public ENIPItem2TKMaterials getObject(int code) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2TKMaterials. Получить полный список */
	public ENIPItem2TKMaterialsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPItem2TKMaterials. Получить список по фильтру */
	public ENIPItem2TKMaterialsShortList getFilteredList(
			ENIPItem2TKMaterialsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPItem2TKMaterials. Получить список для просмотра */
	public ENIPItem2TKMaterialsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPItem2TKMaterials. Получить список для просмотра по фильтру */
	public ENIPItem2TKMaterialsShortList getScrollableFilteredList(
			ENIPItem2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2TKMaterials. Получить список для просмотра по условию */
	public ENIPItem2TKMaterialsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPItem2TKMaterialsFilter filterObject = new ENIPItem2TKMaterialsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPItem2TKMaterials. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPItem2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2TKMaterials. Получить объект из списка */
	public ENIPItem2TKMaterialsShort getShortObject(int code) {
		try {
			ENIPItem2TKMaterialsDAO objectDAO = new ENIPItem2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2TKMaterials%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
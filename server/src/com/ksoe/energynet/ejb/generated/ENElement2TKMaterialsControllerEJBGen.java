
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2TKMaterialsDAO;
import com.ksoe.energynet.valueobject.ENElement2TKMaterials;
import com.ksoe.energynet.valueobject.lists.ENElement2TKMaterialsShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2TKMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENElement2TKMaterialsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2TKMaterials;
 *
 */


public abstract class ENElement2TKMaterialsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2TKMaterialsControllerEJBGen() {
	}

	/* ENElement2TKMaterials. Добавить */
	public int add(ENElement2TKMaterials object) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2TKMaterials. Удалить */
	public void remove(int code) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2TKMaterials. Изменить */
	public void save(ENElement2TKMaterials object) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2TKMaterials. Получить объект */
	public ENElement2TKMaterials getObject(int code) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2TKMaterials. Получить полный список */
	public ENElement2TKMaterialsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2TKMaterials. Получить список по фильтру */
	public ENElement2TKMaterialsShortList getFilteredList(
			ENElement2TKMaterialsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2TKMaterials. Получить список для просмотра */
	public ENElement2TKMaterialsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2TKMaterials. Получить список для просмотра по фильтру */
	public ENElement2TKMaterialsShortList getScrollableFilteredList(
			ENElement2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2TKMaterials. Получить список для просмотра по условию */
	public ENElement2TKMaterialsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2TKMaterialsFilter filterObject = new ENElement2TKMaterialsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2TKMaterials. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENElement2TKMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2TKMaterials. Получить объект из списка */
	public ENElement2TKMaterialsShort getShortObject(int code) {
		try {
			ENElement2TKMaterialsDAO objectDAO = new ENElement2TKMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2TKMaterials%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
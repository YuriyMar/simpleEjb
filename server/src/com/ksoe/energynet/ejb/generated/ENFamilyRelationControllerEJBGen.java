
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFamilyRelationDAO;
import com.ksoe.energynet.valueobject.ENFamilyRelation;
import com.ksoe.energynet.valueobject.lists.ENFamilyRelationShortList;
import com.ksoe.energynet.valueobject.brief.ENFamilyRelationShort;
import com.ksoe.energynet.valueobject.filter.ENFamilyRelationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFamilyRelation;
 *
 */


public abstract class ENFamilyRelationControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFamilyRelationControllerEJBGen() {
	}

	/* ENFamilyRelation. Добавить */
	public int add(ENFamilyRelation object) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFamilyRelation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilyRelation. Удалить */
	public void remove(int code) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFamilyRelation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFamilyRelation. Изменить */
	public void save(ENFamilyRelation object) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFamilyRelation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilyRelation. Получить объект */
	public ENFamilyRelation getObject(int code) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFamilyRelation%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilyRelation. Получить полный список */
	public ENFamilyRelationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFamilyRelation. Получить список по фильтру */
	public ENFamilyRelationShortList getFilteredList(
			ENFamilyRelationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFamilyRelation. Получить список для просмотра */
	public ENFamilyRelationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFamilyRelation. Получить список для просмотра по фильтру */
	public ENFamilyRelationShortList getScrollableFilteredList(
			ENFamilyRelationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFamilyRelation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilyRelation. Получить список для просмотра по условию */
	public ENFamilyRelationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFamilyRelationFilter filterObject = new ENFamilyRelationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFamilyRelation. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFamilyRelationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFamilyRelation%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilyRelation. Получить объект из списка */
	public ENFamilyRelationShort getShortObject(int code) {
		try {
			ENFamilyRelationDAO objectDAO = new ENFamilyRelationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFamilyRelation%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
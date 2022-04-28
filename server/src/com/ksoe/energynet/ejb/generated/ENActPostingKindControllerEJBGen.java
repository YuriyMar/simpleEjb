
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActPostingKindDAO;
import com.ksoe.energynet.valueobject.ENActPostingKind;
import com.ksoe.energynet.valueobject.lists.ENActPostingKindShortList;
import com.ksoe.energynet.valueobject.brief.ENActPostingKindShort;
import com.ksoe.energynet.valueobject.filter.ENActPostingKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActPostingKind;
 *
 */


public abstract class ENActPostingKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActPostingKindControllerEJBGen() {
	}

	/* ENActPostingKind. Добавить */
	public int add(ENActPostingKind object) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActPostingKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKind. Удалить */
	public void remove(int code) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActPostingKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActPostingKind. Изменить */
	public void save(ENActPostingKind object) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActPostingKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKind. Получить объект */
	public ENActPostingKind getObject(int code) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKind. Получить полный список */
	public ENActPostingKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActPostingKind. Получить список по фильтру */
	public ENActPostingKindShortList getFilteredList(
			ENActPostingKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActPostingKind. Получить список для просмотра */
	public ENActPostingKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActPostingKind. Получить список для просмотра по фильтру */
	public ENActPostingKindShortList getScrollableFilteredList(
			ENActPostingKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActPostingKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKind. Получить список для просмотра по условию */
	public ENActPostingKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActPostingKindFilter filterObject = new ENActPostingKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActPostingKind. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActPostingKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActPostingKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingKind. Получить объект из списка */
	public ENActPostingKindShort getShortObject(int code) {
		try {
			ENActPostingKindDAO objectDAO = new ENActPostingKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
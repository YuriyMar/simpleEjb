
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTenderPurchaseKindDAO;
import com.ksoe.energynet.valueobject.ENTenderPurchaseKind;
import com.ksoe.energynet.valueobject.lists.ENTenderPurchaseKindShortList;
import com.ksoe.energynet.valueobject.brief.ENTenderPurchaseKindShort;
import com.ksoe.energynet.valueobject.filter.ENTenderPurchaseKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTenderPurchaseKind;
 *
 */


public abstract class ENTenderPurchaseKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTenderPurchaseKindControllerEJBGen() {
	}

	/* ENTenderPurchaseKind. Добавить */
	public int add(ENTenderPurchaseKind object) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTenderPurchaseKind. Удалить */
	public void remove(int code) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTenderPurchaseKind. Изменить */
	public void save(ENTenderPurchaseKind object) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTenderPurchaseKind. Получить объект */
	public ENTenderPurchaseKind getObject(int code) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTenderPurchaseKind. Получить полный список */
	public ENTenderPurchaseKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTenderPurchaseKind. Получить список по фильтру */
	public ENTenderPurchaseKindShortList getFilteredList(
			ENTenderPurchaseKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTenderPurchaseKind. Получить список для просмотра */
	public ENTenderPurchaseKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTenderPurchaseKind. Получить список для просмотра по фильтру */
	public ENTenderPurchaseKindShortList getScrollableFilteredList(
			ENTenderPurchaseKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTenderPurchaseKind. Получить список для просмотра по условию */
	public ENTenderPurchaseKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTenderPurchaseKindFilter filterObject = new ENTenderPurchaseKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTenderPurchaseKind. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTenderPurchaseKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTenderPurchaseKind. Получить объект из списка */
	public ENTenderPurchaseKindShort getShortObject(int code) {
		try {
			ENTenderPurchaseKindDAO objectDAO = new ENTenderPurchaseKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTenderPurchaseKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBudgets2NomenclaturesDAO;
import com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures;
import com.ksoe.energynet.valueobject.lists.ENBudgets2NomenclaturesShortList;
import com.ksoe.energynet.valueobject.brief.ENBudgets2NomenclaturesShort;
import com.ksoe.energynet.valueobject.filter.ENBudgets2NomenclaturesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENBudgets2Nomenclatures;
 *
 */


public abstract class ENBudgets2NomenclaturesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBudgets2NomenclaturesControllerEJBGen() {
	}

	/* ENBudgets2Nomenclatures. Добавить */
	public int add(ENBudgets2Nomenclatures object) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBudgets2Nomenclatures. Удалить */
	public void remove(int code) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBudgets2Nomenclatures. Изменить */
	public void save(ENBudgets2Nomenclatures object) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBudgets2Nomenclatures. Получить объект */
	public ENBudgets2Nomenclatures getObject(int code) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBudgets2Nomenclatures. Получить полный список */
	public ENBudgets2NomenclaturesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBudgets2Nomenclatures. Получить список по фильтру */
	public ENBudgets2NomenclaturesShortList getFilteredList(
			ENBudgets2NomenclaturesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBudgets2Nomenclatures. Получить список для просмотра */
	public ENBudgets2NomenclaturesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBudgets2Nomenclatures. Получить список для просмотра по фильтру */
	public ENBudgets2NomenclaturesShortList getScrollableFilteredList(
			ENBudgets2NomenclaturesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBudgets2Nomenclatures. Получить список для просмотра по условию */
	public ENBudgets2NomenclaturesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBudgets2NomenclaturesFilter filterObject = new ENBudgets2NomenclaturesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBudgets2Nomenclatures. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBudgets2NomenclaturesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBudgets2Nomenclatures. Получить объект из списка */
	public ENBudgets2NomenclaturesShort getShortObject(int code) {
		try {
			ENBudgets2NomenclaturesDAO objectDAO = new ENBudgets2NomenclaturesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBudgets2Nomenclatures%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncomeCreatMetodDAO;
import com.ksoe.energynet.valueobject.ENActIncomeCreatMetod;
import com.ksoe.energynet.valueobject.lists.ENActIncomeCreatMetodShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncomeCreatMetodShort;
import com.ksoe.energynet.valueobject.filter.ENActIncomeCreatMetodFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncomeCreatMetod;
 *
 */


public abstract class ENActIncomeCreatMetodControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncomeCreatMetodControllerEJBGen() {
	}

	/* ENActIncomeCreatMetod. Добавить */
	public int add(ENActIncomeCreatMetod object) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeCreatMetod. Удалить */
	public void remove(int code) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncomeCreatMetod. Изменить */
	public void save(ENActIncomeCreatMetod object) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeCreatMetod. Получить объект */
	public ENActIncomeCreatMetod getObject(int code) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeCreatMetod. Получить полный список */
	public ENActIncomeCreatMetodShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncomeCreatMetod. Получить список по фильтру */
	public ENActIncomeCreatMetodShortList getFilteredList(
			ENActIncomeCreatMetodFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncomeCreatMetod. Получить список для просмотра */
	public ENActIncomeCreatMetodShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncomeCreatMetod. Получить список для просмотра по фильтру */
	public ENActIncomeCreatMetodShortList getScrollableFilteredList(
			ENActIncomeCreatMetodFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeCreatMetod. Получить список для просмотра по условию */
	public ENActIncomeCreatMetodShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncomeCreatMetodFilter filterObject = new ENActIncomeCreatMetodFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncomeCreatMetod. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActIncomeCreatMetodFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeCreatMetod. Получить объект из списка */
	public ENActIncomeCreatMetodShort getShortObject(int code) {
		try {
			ENActIncomeCreatMetodDAO objectDAO = new ENActIncomeCreatMetodDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeCreatMetod%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
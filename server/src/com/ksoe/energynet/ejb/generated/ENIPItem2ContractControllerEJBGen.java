
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPItem2ContractDAO;
import com.ksoe.energynet.valueobject.ENIPItem2Contract;
import com.ksoe.energynet.valueobject.lists.ENIPItem2ContractShortList;
import com.ksoe.energynet.valueobject.brief.ENIPItem2ContractShort;
import com.ksoe.energynet.valueobject.filter.ENIPItem2ContractFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPItem2Contract;
 *
 */


public abstract class ENIPItem2ContractControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPItem2ContractControllerEJBGen() {
	}

	/* ENIPItem2Contract. Добавить */
	public int add(ENIPItem2Contract object) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2Contract. Удалить */
	public void remove(int code) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPItem2Contract. Изменить */
	public void save(ENIPItem2Contract object) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2Contract. Получить объект */
	public ENIPItem2Contract getObject(int code) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2Contract. Получить полный список */
	public ENIPItem2ContractShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPItem2Contract. Получить список по фильтру */
	public ENIPItem2ContractShortList getFilteredList(
			ENIPItem2ContractFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPItem2Contract. Получить список для просмотра */
	public ENIPItem2ContractShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPItem2Contract. Получить список для просмотра по фильтру */
	public ENIPItem2ContractShortList getScrollableFilteredList(
			ENIPItem2ContractFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2Contract. Получить список для просмотра по условию */
	public ENIPItem2ContractShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPItem2ContractFilter filterObject = new ENIPItem2ContractFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPItem2Contract. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPItem2ContractFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPItem2Contract. Получить объект из списка */
	public ENIPItem2ContractShort getShortObject(int code) {
		try {
			ENIPItem2ContractDAO objectDAO = new ENIPItem2ContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPItem2Contract%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
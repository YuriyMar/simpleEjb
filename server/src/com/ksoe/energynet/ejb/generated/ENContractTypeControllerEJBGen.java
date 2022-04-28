
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENContractTypeDAO;
import com.ksoe.energynet.valueobject.ENContractType;
import com.ksoe.energynet.valueobject.lists.ENContractTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENContractTypeShort;
import com.ksoe.energynet.valueobject.filter.ENContractTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENContractType;
 *
 */


public abstract class ENContractTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENContractTypeControllerEJBGen() {
	}

	/* ENContractType. Добавить */
	public int add(ENContractType object) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENContractType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENContractType. Удалить */
	public void remove(int code) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENContractType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENContractType. Изменить */
	public void save(ENContractType object) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENContractType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENContractType. Получить объект */
	public ENContractType getObject(int code) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENContractType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENContractType. Получить полный список */
	public ENContractTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENContractType. Получить список по фильтру */
	public ENContractTypeShortList getFilteredList(
			ENContractTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENContractType. Получить список для просмотра */
	public ENContractTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENContractType. Получить список для просмотра по фильтру */
	public ENContractTypeShortList getScrollableFilteredList(
			ENContractTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENContractType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENContractType. Получить список для просмотра по условию */
	public ENContractTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENContractTypeFilter filterObject = new ENContractTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENContractType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENContractTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENContractType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENContractType. Получить объект из списка */
	public ENContractTypeShort getShortObject(int code) {
		try {
			ENContractTypeDAO objectDAO = new ENContractTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENContractType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
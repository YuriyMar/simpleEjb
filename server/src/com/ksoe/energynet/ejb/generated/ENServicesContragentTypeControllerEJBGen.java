
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesContragentTypeDAO;
import com.ksoe.energynet.valueobject.ENServicesContragentType;
import com.ksoe.energynet.valueobject.lists.ENServicesContragentTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesContragentTypeShort;
import com.ksoe.energynet.valueobject.filter.ENServicesContragentTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesContragentType;
 *
 */


public abstract class ENServicesContragentTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesContragentTypeControllerEJBGen() {
	}

	/* ENServicesContragentType. Добавить */
	public int add(ENServicesContragentType object) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesContragentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContragentType. Удалить */
	public void remove(int code) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesContragentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesContragentType. Изменить */
	public void save(ENServicesContragentType object) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesContragentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContragentType. Получить объект */
	public ENServicesContragentType getObject(int code) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesContragentType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContragentType. Получить полный список */
	public ENServicesContragentTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesContragentType. Получить список по фильтру */
	public ENServicesContragentTypeShortList getFilteredList(
			ENServicesContragentTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesContragentType. Получить список для просмотра */
	public ENServicesContragentTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesContragentType. Получить список для просмотра по фильтру */
	public ENServicesContragentTypeShortList getScrollableFilteredList(
			ENServicesContragentTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesContragentType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContragentType. Получить список для просмотра по условию */
	public ENServicesContragentTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesContragentTypeFilter filterObject = new ENServicesContragentTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesContragentType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesContragentTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesContragentType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesContragentType. Получить объект из списка */
	public ENServicesContragentTypeShort getShortObject(int code) {
		try {
			ENServicesContragentTypeDAO objectDAO = new ENServicesContragentTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesContragentType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesObjectKindFKDAO;
import com.ksoe.energynet.valueobject.ENServicesObjectKindFK;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectKindFKShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesObjectKindFKShort;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectKindFKFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesObjectKindFK;
 *
 */


public abstract class ENServicesObjectKindFKControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesObjectKindFKControllerEJBGen() {
	}

	/* ENServicesObjectKindFK. Добавить */
	public int add(ENServicesObjectKindFK object) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectKindFK. Удалить */
	public void remove(int code) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesObjectKindFK. Изменить */
	public void save(ENServicesObjectKindFK object) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectKindFK. Получить объект */
	public ENServicesObjectKindFK getObject(int code) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectKindFK. Получить полный список */
	public ENServicesObjectKindFKShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesObjectKindFK. Получить список по фильтру */
	public ENServicesObjectKindFKShortList getFilteredList(
			ENServicesObjectKindFKFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesObjectKindFK. Получить список для просмотра */
	public ENServicesObjectKindFKShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesObjectKindFK. Получить список для просмотра по фильтру */
	public ENServicesObjectKindFKShortList getScrollableFilteredList(
			ENServicesObjectKindFKFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectKindFK. Получить список для просмотра по условию */
	public ENServicesObjectKindFKShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesObjectKindFKFilter filterObject = new ENServicesObjectKindFKFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesObjectKindFK. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesObjectKindFKFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesObjectKindFK. Получить объект из списка */
	public ENServicesObjectKindFKShort getShortObject(int code) {
		try {
			ENServicesObjectKindFKDAO objectDAO = new ENServicesObjectKindFKDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesObjectKindFK%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDisconnectInitiatorDAO;
import com.ksoe.energynet.valueobject.ENDisconnectInitiator;
import com.ksoe.energynet.valueobject.lists.ENDisconnectInitiatorShortList;
import com.ksoe.energynet.valueobject.brief.ENDisconnectInitiatorShort;
import com.ksoe.energynet.valueobject.filter.ENDisconnectInitiatorFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDisconnectInitiator;
 *
 */


public abstract class ENDisconnectInitiatorControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDisconnectInitiatorControllerEJBGen() {
	}

	/* ENDisconnectInitiator. Добавить */
	public int add(ENDisconnectInitiator object) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. Удалить */
	public void remove(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDisconnectInitiator. Изменить */
	public void save(ENDisconnectInitiator object) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. Получить объект */
	public ENDisconnectInitiator getObject(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. Получить полный список */
	public ENDisconnectInitiatorShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDisconnectInitiator. Получить список по фильтру */
	public ENDisconnectInitiatorShortList getFilteredList(
			ENDisconnectInitiatorFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDisconnectInitiator. Получить список для просмотра */
	public ENDisconnectInitiatorShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDisconnectInitiator. Получить список для просмотра по фильтру */
	public ENDisconnectInitiatorShortList getScrollableFilteredList(
			ENDisconnectInitiatorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. Получить список для просмотра по условию */
	public ENDisconnectInitiatorShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDisconnectInitiatorFilter filterObject = new ENDisconnectInitiatorFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDisconnectInitiator. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDisconnectInitiatorFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDisconnectInitiator. Получить объект из списка */
	public ENDisconnectInitiatorShort getShortObject(int code) {
		try {
			ENDisconnectInitiatorDAO objectDAO = new ENDisconnectInitiatorDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDisconnectInitiator%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
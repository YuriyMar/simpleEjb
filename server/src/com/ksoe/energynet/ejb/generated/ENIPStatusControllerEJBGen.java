
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPStatusDAO;
import com.ksoe.energynet.valueobject.ENIPStatus;
import com.ksoe.energynet.valueobject.lists.ENIPStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENIPStatusShort;
import com.ksoe.energynet.valueobject.filter.ENIPStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPStatus;
 *
 */


public abstract class ENIPStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPStatusControllerEJBGen() {
	}

	/* ENIPStatus. Добавить */
	public int add(ENIPStatus object) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPStatus. Удалить */
	public void remove(int code) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPStatus. Изменить */
	public void save(ENIPStatus object) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPStatus. Получить объект */
	public ENIPStatus getObject(int code) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPStatus. Получить полный список */
	public ENIPStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPStatus. Получить список по фильтру */
	public ENIPStatusShortList getFilteredList(
			ENIPStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPStatus. Получить список для просмотра */
	public ENIPStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPStatus. Получить список для просмотра по фильтру */
	public ENIPStatusShortList getScrollableFilteredList(
			ENIPStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPStatus. Получить список для просмотра по условию */
	public ENIPStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPStatusFilter filterObject = new ENIPStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPStatus. Получить объект из списка */
	public ENIPStatusShort getShortObject(int code) {
		try {
			ENIPStatusDAO objectDAO = new ENIPStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
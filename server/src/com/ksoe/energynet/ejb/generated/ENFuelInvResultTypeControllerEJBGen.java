
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelInvResultTypeDAO;
import com.ksoe.energynet.valueobject.ENFuelInvResultType;
import com.ksoe.energynet.valueobject.lists.ENFuelInvResultTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelInvResultTypeShort;
import com.ksoe.energynet.valueobject.filter.ENFuelInvResultTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelInvResultType;
 *
 */


public abstract class ENFuelInvResultTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelInvResultTypeControllerEJBGen() {
	}

	/* ENFuelInvResultType. Добавить */
	public int add(ENFuelInvResultType object) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResultType. Удалить */
	public void remove(int code) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelInvResultType. Изменить */
	public void save(ENFuelInvResultType object) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResultType. Получить объект */
	public ENFuelInvResultType getObject(int code) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResultType. Получить полный список */
	public ENFuelInvResultTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelInvResultType. Получить список по фильтру */
	public ENFuelInvResultTypeShortList getFilteredList(
			ENFuelInvResultTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelInvResultType. Получить список для просмотра */
	public ENFuelInvResultTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelInvResultType. Получить список для просмотра по фильтру */
	public ENFuelInvResultTypeShortList getScrollableFilteredList(
			ENFuelInvResultTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResultType. Получить список для просмотра по условию */
	public ENFuelInvResultTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelInvResultTypeFilter filterObject = new ENFuelInvResultTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelInvResultType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelInvResultTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInvResultType. Получить объект из списка */
	public ENFuelInvResultTypeShort getShortObject(int code) {
		try {
			ENFuelInvResultTypeDAO objectDAO = new ENFuelInvResultTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInvResultType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionLocationTypeDAO;
import com.ksoe.energynet.valueobject.ENConnectionLocationType;
import com.ksoe.energynet.valueobject.lists.ENConnectionLocationTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionLocationTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionLocationTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionLocationType;
 *
 */


public abstract class ENConnectionLocationTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionLocationTypeControllerEJBGen() {
	}

	/* ENConnectionLocationType. Добавить */
	public int add(ENConnectionLocationType object) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLocationType. Удалить */
	public void remove(int code) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionLocationType. Изменить */
	public void save(ENConnectionLocationType object) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLocationType. Получить объект */
	public ENConnectionLocationType getObject(int code) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLocationType. Получить полный список */
	public ENConnectionLocationTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionLocationType. Получить список по фильтру */
	public ENConnectionLocationTypeShortList getFilteredList(
			ENConnectionLocationTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionLocationType. Получить список для просмотра */
	public ENConnectionLocationTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionLocationType. Получить список для просмотра по фильтру */
	public ENConnectionLocationTypeShortList getScrollableFilteredList(
			ENConnectionLocationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLocationType. Получить список для просмотра по условию */
	public ENConnectionLocationTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionLocationTypeFilter filterObject = new ENConnectionLocationTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionLocationType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionLocationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLocationType. Получить объект из списка */
	public ENConnectionLocationTypeShort getShortObject(int code) {
		try {
			ENConnectionLocationTypeDAO objectDAO = new ENConnectionLocationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionLocationType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
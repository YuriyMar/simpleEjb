
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPImplementationTypeDAO;
import com.ksoe.energynet.valueobject.ENIPImplementationType;
import com.ksoe.energynet.valueobject.lists.ENIPImplementationTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENIPImplementationTypeShort;
import com.ksoe.energynet.valueobject.filter.ENIPImplementationTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPImplementationType;
 *
 */


public abstract class ENIPImplementationTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPImplementationTypeControllerEJBGen() {
	}

	/* ENIPImplementationType. Добавить */
	public int add(ENIPImplementationType object) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPImplementationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPImplementationType. Удалить */
	public void remove(int code) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPImplementationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPImplementationType. Изменить */
	public void save(ENIPImplementationType object) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPImplementationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPImplementationType. Получить объект */
	public ENIPImplementationType getObject(int code) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPImplementationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPImplementationType. Получить полный список */
	public ENIPImplementationTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPImplementationType. Получить список по фильтру */
	public ENIPImplementationTypeShortList getFilteredList(
			ENIPImplementationTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPImplementationType. Получить список для просмотра */
	public ENIPImplementationTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPImplementationType. Получить список для просмотра по фильтру */
	public ENIPImplementationTypeShortList getScrollableFilteredList(
			ENIPImplementationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPImplementationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPImplementationType. Получить список для просмотра по условию */
	public ENIPImplementationTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPImplementationTypeFilter filterObject = new ENIPImplementationTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPImplementationType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPImplementationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPImplementationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPImplementationType. Получить объект из списка */
	public ENIPImplementationTypeShort getShortObject(int code) {
		try {
			ENIPImplementationTypeDAO objectDAO = new ENIPImplementationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPImplementationType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
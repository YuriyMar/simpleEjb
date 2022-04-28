
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionInstallationTypeDAO;
import com.ksoe.energynet.valueobject.ENConnectionInstallationType;
import com.ksoe.energynet.valueobject.lists.ENConnectionInstallationTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionInstallationTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionInstallationTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionInstallationType;
 *
 */


public abstract class ENConnectionInstallationTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionInstallationTypeControllerEJBGen() {
	}

	/* ENConnectionInstallationType. Добавить */
	public int add(ENConnectionInstallationType object) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionInstallationType. Удалить */
	public void remove(int code) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionInstallationType. Изменить */
	public void save(ENConnectionInstallationType object) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionInstallationType. Получить объект */
	public ENConnectionInstallationType getObject(int code) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionInstallationType. Получить полный список */
	public ENConnectionInstallationTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionInstallationType. Получить список по фильтру */
	public ENConnectionInstallationTypeShortList getFilteredList(
			ENConnectionInstallationTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionInstallationType. Получить список для просмотра */
	public ENConnectionInstallationTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionInstallationType. Получить список для просмотра по фильтру */
	public ENConnectionInstallationTypeShortList getScrollableFilteredList(
			ENConnectionInstallationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionInstallationType. Получить список для просмотра по условию */
	public ENConnectionInstallationTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionInstallationTypeFilter filterObject = new ENConnectionInstallationTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionInstallationType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionInstallationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionInstallationType. Получить объект из списка */
	public ENConnectionInstallationTypeShort getShortObject(int code) {
		try {
			ENConnectionInstallationTypeDAO objectDAO = new ENConnectionInstallationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionInstallationType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOTechParamsDAO;
import com.ksoe.energynet.valueobject.ENSOTechParams;
import com.ksoe.energynet.valueobject.lists.ENSOTechParamsShortList;
import com.ksoe.energynet.valueobject.brief.ENSOTechParamsShort;
import com.ksoe.energynet.valueobject.filter.ENSOTechParamsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOTechParams;
 *
 */


public abstract class ENSOTechParamsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOTechParamsControllerEJBGen() {
	}

	/* ENSOTechParams. Добавить */
	public int add(ENSOTechParams object) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOTechParams%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOTechParams. Удалить */
	public void remove(int code) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOTechParams%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOTechParams. Изменить */
	public void save(ENSOTechParams object) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOTechParams%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOTechParams. Получить объект */
	public ENSOTechParams getObject(int code) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOTechParams%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOTechParams. Получить полный список */
	public ENSOTechParamsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOTechParams. Получить список по фильтру */
	public ENSOTechParamsShortList getFilteredList(
			ENSOTechParamsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOTechParams. Получить список для просмотра */
	public ENSOTechParamsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOTechParams. Получить список для просмотра по фильтру */
	public ENSOTechParamsShortList getScrollableFilteredList(
			ENSOTechParamsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOTechParams%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOTechParams. Получить список для просмотра по условию */
	public ENSOTechParamsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOTechParamsFilter filterObject = new ENSOTechParamsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOTechParams. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOTechParamsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOTechParams%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOTechParams. Получить объект из списка */
	public ENSOTechParamsShort getShortObject(int code) {
		try {
			ENSOTechParamsDAO objectDAO = new ENSOTechParamsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOTechParams%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
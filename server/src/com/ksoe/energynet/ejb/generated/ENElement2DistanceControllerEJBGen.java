
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENElement2DistanceDAO;
import com.ksoe.energynet.valueobject.ENElement2Distance;
import com.ksoe.energynet.valueobject.lists.ENElement2DistanceShortList;
import com.ksoe.energynet.valueobject.brief.ENElement2DistanceShort;
import com.ksoe.energynet.valueobject.filter.ENElement2DistanceFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENElement2Distance;
 *
 */


public abstract class ENElement2DistanceControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENElement2DistanceControllerEJBGen() {
	}

	/* ENElement2Distance. Добавить */
	public int add(ENElement2Distance object) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENElement2Distance%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Distance. Удалить */
	public void remove(int code) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENElement2Distance%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENElement2Distance. Изменить */
	public void save(ENElement2Distance object) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENElement2Distance%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Distance. Получить объект */
	public ENElement2Distance getObject(int code) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2Distance%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Distance. Получить полный список */
	public ENElement2DistanceShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENElement2Distance. Получить список по фильтру */
	public ENElement2DistanceShortList getFilteredList(
			ENElement2DistanceFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENElement2Distance. Получить список для просмотра */
	public ENElement2DistanceShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENElement2Distance. Получить список для просмотра по фильтру */
	public ENElement2DistanceShortList getScrollableFilteredList(
			ENElement2DistanceFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENElement2Distance%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Distance. Получить список для просмотра по условию */
	public ENElement2DistanceShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENElement2DistanceFilter filterObject = new ENElement2DistanceFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENElement2Distance. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENElement2DistanceFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENElement2Distance%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENElement2Distance. Получить объект из списка */
	public ENElement2DistanceShort getShortObject(int code) {
		try {
			ENElement2DistanceDAO objectDAO = new ENElement2DistanceDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENElement2Distance%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
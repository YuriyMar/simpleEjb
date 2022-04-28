
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAverageCountPersonalDAO;
import com.ksoe.energynet.valueobject.ENAverageCountPersonal;
import com.ksoe.energynet.valueobject.lists.ENAverageCountPersonalShortList;
import com.ksoe.energynet.valueobject.brief.ENAverageCountPersonalShort;
import com.ksoe.energynet.valueobject.filter.ENAverageCountPersonalFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAverageCountPersonal;
 *
 */


public abstract class ENAverageCountPersonalControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAverageCountPersonalControllerEJBGen() {
	}

	/* ENAverageCountPersonal. Добавить */
	public int add(ENAverageCountPersonal object) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAverageCountPersonal. Удалить */
	public void remove(int code) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAverageCountPersonal. Изменить */
	public void save(ENAverageCountPersonal object) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAverageCountPersonal. Получить объект */
	public ENAverageCountPersonal getObject(int code) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAverageCountPersonal. Получить полный список */
	public ENAverageCountPersonalShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAverageCountPersonal. Получить список по фильтру */
	public ENAverageCountPersonalShortList getFilteredList(
			ENAverageCountPersonalFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAverageCountPersonal. Получить список для просмотра */
	public ENAverageCountPersonalShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAverageCountPersonal. Получить список для просмотра по фильтру */
	public ENAverageCountPersonalShortList getScrollableFilteredList(
			ENAverageCountPersonalFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAverageCountPersonal. Получить список для просмотра по условию */
	public ENAverageCountPersonalShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAverageCountPersonalFilter filterObject = new ENAverageCountPersonalFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAverageCountPersonal. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAverageCountPersonalFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAverageCountPersonal. Получить объект из списка */
	public ENAverageCountPersonalShort getShortObject(int code) {
		try {
			ENAverageCountPersonalDAO objectDAO = new ENAverageCountPersonalDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAverageCountPersonal%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
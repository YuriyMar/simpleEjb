
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttType2ActionDAO;
import com.ksoe.energynet.valueobject.ENDocAttType2Action;
import com.ksoe.energynet.valueobject.lists.ENDocAttType2ActionShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttType2ActionShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttType2ActionFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttType2Action;
 *
 */


public abstract class ENDocAttType2ActionControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttType2ActionControllerEJBGen() {
	}

	/* ENDocAttType2Action. Добавить */
	public int add(ENDocAttType2Action object) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttType2Action. Удалить */
	public void remove(int code) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttType2Action. Изменить */
	public void save(ENDocAttType2Action object) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttType2Action. Получить объект */
	public ENDocAttType2Action getObject(int code) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttType2Action. Получить полный список */
	public ENDocAttType2ActionShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttType2Action. Получить список по фильтру */
	public ENDocAttType2ActionShortList getFilteredList(
			ENDocAttType2ActionFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttType2Action. Получить список для просмотра */
	public ENDocAttType2ActionShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttType2Action. Получить список для просмотра по фильтру */
	public ENDocAttType2ActionShortList getScrollableFilteredList(
			ENDocAttType2ActionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttType2Action. Получить список для просмотра по условию */
	public ENDocAttType2ActionShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttType2ActionFilter filterObject = new ENDocAttType2ActionFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttType2Action. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttType2ActionFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttType2Action. Получить объект из списка */
	public ENDocAttType2ActionShort getShortObject(int code) {
		try {
			ENDocAttType2ActionDAO objectDAO = new ENDocAttType2ActionDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttType2Action%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
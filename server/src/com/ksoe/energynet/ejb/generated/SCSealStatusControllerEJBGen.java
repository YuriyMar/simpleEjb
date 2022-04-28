
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSealStatusDAO;
import com.ksoe.energynet.valueobject.SCSealStatus;
import com.ksoe.energynet.valueobject.lists.SCSealStatusShortList;
import com.ksoe.energynet.valueobject.brief.SCSealStatusShort;
import com.ksoe.energynet.valueobject.filter.SCSealStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSealStatus;
 *
 */


public abstract class SCSealStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSealStatusControllerEJBGen() {
	}

	/* SCSealStatus. Добавить */
	public int add(SCSealStatus object) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSealStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealStatus. Удалить */
	public void remove(int code) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSealStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSealStatus. Изменить */
	public void save(SCSealStatus object) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSealStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealStatus. Получить объект */
	public SCSealStatus getObject(int code) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealStatus. Получить полный список */
	public SCSealStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSealStatus. Получить список по фильтру */
	public SCSealStatusShortList getFilteredList(
			SCSealStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSealStatus. Получить список для просмотра */
	public SCSealStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSealStatus. Получить список для просмотра по фильтру */
	public SCSealStatusShortList getScrollableFilteredList(
			SCSealStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSealStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealStatus. Получить список для просмотра по условию */
	public SCSealStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSealStatusFilter filterObject = new SCSealStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSealStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSealStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSealStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealStatus. Получить объект из списка */
	public SCSealStatusShort getShortObject(int code) {
		try {
			SCSealStatusDAO objectDAO = new SCSealStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSealTypeDAO;
import com.ksoe.energynet.valueobject.SCSealType;
import com.ksoe.energynet.valueobject.lists.SCSealTypeShortList;
import com.ksoe.energynet.valueobject.brief.SCSealTypeShort;
import com.ksoe.energynet.valueobject.filter.SCSealTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSealType;
 *
 */


public abstract class SCSealTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSealTypeControllerEJBGen() {
	}

	/* SCSealType. Добавить */
	public int add(SCSealType object) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSealType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealType. Удалить */
	public void remove(int code) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSealType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSealType. Изменить */
	public void save(SCSealType object) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSealType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealType. Получить объект */
	public SCSealType getObject(int code) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealType. Получить полный список */
	public SCSealTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSealType. Получить список по фильтру */
	public SCSealTypeShortList getFilteredList(
			SCSealTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSealType. Получить список для просмотра */
	public SCSealTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSealType. Получить список для просмотра по фильтру */
	public SCSealTypeShortList getScrollableFilteredList(
			SCSealTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSealType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealType. Получить список для просмотра по условию */
	public SCSealTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSealTypeFilter filterObject = new SCSealTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSealType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSealTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSealType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSealType. Получить объект из списка */
	public SCSealTypeShort getShortObject(int code) {
		try {
			SCSealTypeDAO objectDAO = new SCSealTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSealType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
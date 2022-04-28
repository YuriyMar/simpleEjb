
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCottage2TKClassificationTypeDAO;
import com.ksoe.energynet.valueobject.ENCottage2TKClassificationType;
import com.ksoe.energynet.valueobject.lists.ENCottage2TKClassificationTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENCottage2TKClassificationTypeShort;
import com.ksoe.energynet.valueobject.filter.ENCottage2TKClassificationTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCottage2TKClassificationType;
 *
 */


public abstract class ENCottage2TKClassificationTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCottage2TKClassificationTypeControllerEJBGen() {
	}

	/* ENCottage2TKClassificationType. Добавить */
	public int add(ENCottage2TKClassificationType object) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage2TKClassificationType. Удалить */
	public void remove(int code) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCottage2TKClassificationType. Изменить */
	public void save(ENCottage2TKClassificationType object) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage2TKClassificationType. Получить объект */
	public ENCottage2TKClassificationType getObject(int code) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage2TKClassificationType. Получить полный список */
	public ENCottage2TKClassificationTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCottage2TKClassificationType. Получить список по фильтру */
	public ENCottage2TKClassificationTypeShortList getFilteredList(
			ENCottage2TKClassificationTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCottage2TKClassificationType. Получить список для просмотра */
	public ENCottage2TKClassificationTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCottage2TKClassificationType. Получить список для просмотра по фильтру */
	public ENCottage2TKClassificationTypeShortList getScrollableFilteredList(
			ENCottage2TKClassificationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage2TKClassificationType. Получить список для просмотра по условию */
	public ENCottage2TKClassificationTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCottage2TKClassificationTypeFilter filterObject = new ENCottage2TKClassificationTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCottage2TKClassificationType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCottage2TKClassificationTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCottage2TKClassificationType. Получить объект из списка */
	public ENCottage2TKClassificationTypeShort getShortObject(int code) {
		try {
			ENCottage2TKClassificationTypeDAO objectDAO = new ENCottage2TKClassificationTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCottage2TKClassificationType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
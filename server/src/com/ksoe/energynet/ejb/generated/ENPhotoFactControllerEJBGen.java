
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPhotoFactDAO;
import com.ksoe.energynet.valueobject.ENPhotoFact;
import com.ksoe.energynet.valueobject.lists.ENPhotoFactShortList;
import com.ksoe.energynet.valueobject.brief.ENPhotoFactShort;
import com.ksoe.energynet.valueobject.filter.ENPhotoFactFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPhotoFact;
 *
 */


public abstract class ENPhotoFactControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPhotoFactControllerEJBGen() {
	}

	/* ENPhotoFact. Добавить */
	public int add(ENPhotoFact object) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPhotoFact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFact. Удалить */
	public void remove(int code) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPhotoFact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPhotoFact. Изменить */
	public void save(ENPhotoFact object) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPhotoFact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFact. Получить объект */
	public ENPhotoFact getObject(int code) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPhotoFact%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFact. Получить полный список */
	public ENPhotoFactShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPhotoFact. Получить список по фильтру */
	public ENPhotoFactShortList getFilteredList(
			ENPhotoFactFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPhotoFact. Получить список для просмотра */
	public ENPhotoFactShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPhotoFact. Получить список для просмотра по фильтру */
	public ENPhotoFactShortList getScrollableFilteredList(
			ENPhotoFactFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPhotoFact%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFact. Получить список для просмотра по условию */
	public ENPhotoFactShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPhotoFactFilter filterObject = new ENPhotoFactFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPhotoFact. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPhotoFactFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPhotoFact%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPhotoFact. Получить объект из списка */
	public ENPhotoFactShort getShortObject(int code) {
		try {
			ENPhotoFactDAO objectDAO = new ENPhotoFactDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPhotoFact%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFamilySize2ServicesObjectDAO;
import com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject;
import com.ksoe.energynet.valueobject.lists.ENFamilySize2ServicesObjectShortList;
import com.ksoe.energynet.valueobject.brief.ENFamilySize2ServicesObjectShort;
import com.ksoe.energynet.valueobject.filter.ENFamilySize2ServicesObjectFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFamilySize2ServicesObject;
 *
 */


public abstract class ENFamilySize2ServicesObjectControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFamilySize2ServicesObjectControllerEJBGen() {
	}

	/* ENFamilySize2ServicesObject. Добавить */
	public int add(ENFamilySize2ServicesObject object) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilySize2ServicesObject. Удалить */
	public void remove(int code) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFamilySize2ServicesObject. Изменить */
	public void save(ENFamilySize2ServicesObject object) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilySize2ServicesObject. Получить объект */
	public ENFamilySize2ServicesObject getObject(int code) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilySize2ServicesObject. Получить полный список */
	public ENFamilySize2ServicesObjectShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFamilySize2ServicesObject. Получить список по фильтру */
	public ENFamilySize2ServicesObjectShortList getFilteredList(
			ENFamilySize2ServicesObjectFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFamilySize2ServicesObject. Получить список для просмотра */
	public ENFamilySize2ServicesObjectShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFamilySize2ServicesObject. Получить список для просмотра по фильтру */
	public ENFamilySize2ServicesObjectShortList getScrollableFilteredList(
			ENFamilySize2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilySize2ServicesObject. Получить список для просмотра по условию */
	public ENFamilySize2ServicesObjectShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFamilySize2ServicesObjectFilter filterObject = new ENFamilySize2ServicesObjectFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFamilySize2ServicesObject. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFamilySize2ServicesObjectFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFamilySize2ServicesObject. Получить объект из списка */
	public ENFamilySize2ServicesObjectShort getShortObject(int code) {
		try {
			ENFamilySize2ServicesObjectDAO objectDAO = new ENFamilySize2ServicesObjectDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFamilySize2ServicesObject%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
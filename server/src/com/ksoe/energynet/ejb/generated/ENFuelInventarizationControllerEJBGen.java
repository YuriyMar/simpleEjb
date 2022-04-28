
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENFuelInventarizationDAO;
import com.ksoe.energynet.valueobject.ENFuelInventarization;
import com.ksoe.energynet.valueobject.lists.ENFuelInventarizationShortList;
import com.ksoe.energynet.valueobject.brief.ENFuelInventarizationShort;
import com.ksoe.energynet.valueobject.filter.ENFuelInventarizationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelInventarization;
 *
 */


public abstract class ENFuelInventarizationControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelInventarizationControllerEJBGen() {
	}

	/* ENFuelInventarization. Добавить */
	public int add(ENFuelInventarization object) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelInventarization%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarization. Удалить */
	public void remove(int code) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelInventarization%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelInventarization. Изменить */
	public void save(ENFuelInventarization object) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelInventarization%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarization. Получить объект */
	public ENFuelInventarization getObject(int code) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarization%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarization. Получить полный список */
	public ENFuelInventarizationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelInventarization. Получить список по фильтру */
	public ENFuelInventarizationShortList getFilteredList(
			ENFuelInventarizationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelInventarization. Получить список для просмотра */
	public ENFuelInventarizationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelInventarization. Получить список для просмотра по фильтру */
	public ENFuelInventarizationShortList getScrollableFilteredList(
			ENFuelInventarizationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelInventarization%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarization. Получить список для просмотра по условию */
	public ENFuelInventarizationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelInventarizationFilter filterObject = new ENFuelInventarizationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelInventarization. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelInventarizationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelInventarization%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelInventarization. Получить объект из списка */
	public ENFuelInventarizationShort getShortObject(int code) {
		try {
			ENFuelInventarizationDAO objectDAO = new ENFuelInventarizationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelInventarization%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
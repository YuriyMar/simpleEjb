
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActIncomeTech2DFDocDAO;
import com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc;
import com.ksoe.energynet.valueobject.lists.ENActIncomeTech2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.ENActIncomeTech2DFDocShort;
import com.ksoe.energynet.valueobject.filter.ENActIncomeTech2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActIncomeTech2DFDoc;
 *
 */


public abstract class ENActIncomeTech2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActIncomeTech2DFDocControllerEJBGen() {
	}

	/* ENActIncomeTech2DFDoc. Добавить */
	public int add(ENActIncomeTech2DFDoc object) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeTech2DFDoc. Удалить */
	public void remove(int code) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActIncomeTech2DFDoc. Изменить */
	public void save(ENActIncomeTech2DFDoc object) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeTech2DFDoc. Получить объект */
	public ENActIncomeTech2DFDoc getObject(int code) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeTech2DFDoc. Получить полный список */
	public ENActIncomeTech2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActIncomeTech2DFDoc. Получить список по фильтру */
	public ENActIncomeTech2DFDocShortList getFilteredList(
			ENActIncomeTech2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActIncomeTech2DFDoc. Получить список для просмотра */
	public ENActIncomeTech2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActIncomeTech2DFDoc. Получить список для просмотра по фильтру */
	public ENActIncomeTech2DFDocShortList getScrollableFilteredList(
			ENActIncomeTech2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeTech2DFDoc. Получить список для просмотра по условию */
	public ENActIncomeTech2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActIncomeTech2DFDocFilter filterObject = new ENActIncomeTech2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActIncomeTech2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActIncomeTech2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActIncomeTech2DFDoc. Получить объект из списка */
	public ENActIncomeTech2DFDocShort getShortObject(int code) {
		try {
			ENActIncomeTech2DFDocDAO objectDAO = new ENActIncomeTech2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
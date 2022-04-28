
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENReconstrModernOZ2DFDocDAO;
import com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc;
import com.ksoe.energynet.valueobject.lists.ENReconstrModernOZ2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.ENReconstrModernOZ2DFDocShort;
import com.ksoe.energynet.valueobject.filter.ENReconstrModernOZ2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENReconstrModernOZ2DFDoc;
 *
 */


public abstract class ENReconstrModernOZ2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENReconstrModernOZ2DFDocControllerEJBGen() {
	}

	/* ENReconstrModernOZ2DFDoc. Добавить */
	public int add(ENReconstrModernOZ2DFDoc object) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReconstrModernOZ2DFDoc. Удалить */
	public void remove(int code) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENReconstrModernOZ2DFDoc. Изменить */
	public void save(ENReconstrModernOZ2DFDoc object) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReconstrModernOZ2DFDoc. Получить объект */
	public ENReconstrModernOZ2DFDoc getObject(int code) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReconstrModernOZ2DFDoc. Получить полный список */
	public ENReconstrModernOZ2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENReconstrModernOZ2DFDoc. Получить список по фильтру */
	public ENReconstrModernOZ2DFDocShortList getFilteredList(
			ENReconstrModernOZ2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENReconstrModernOZ2DFDoc. Получить список для просмотра */
	public ENReconstrModernOZ2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENReconstrModernOZ2DFDoc. Получить список для просмотра по фильтру */
	public ENReconstrModernOZ2DFDocShortList getScrollableFilteredList(
			ENReconstrModernOZ2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReconstrModernOZ2DFDoc. Получить список для просмотра по условию */
	public ENReconstrModernOZ2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENReconstrModernOZ2DFDocFilter filterObject = new ENReconstrModernOZ2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENReconstrModernOZ2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENReconstrModernOZ2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENReconstrModernOZ2DFDoc. Получить объект из списка */
	public ENReconstrModernOZ2DFDocShort getShortObject(int code) {
		try {
			ENReconstrModernOZ2DFDocDAO objectDAO = new ENReconstrModernOZ2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
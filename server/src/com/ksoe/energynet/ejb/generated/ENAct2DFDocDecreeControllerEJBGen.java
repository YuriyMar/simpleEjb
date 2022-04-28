
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2DFDocDecreeDAO;
import com.ksoe.energynet.valueobject.ENAct2DFDocDecree;
import com.ksoe.energynet.valueobject.lists.ENAct2DFDocDecreeShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2DFDocDecreeShort;
import com.ksoe.energynet.valueobject.filter.ENAct2DFDocDecreeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2DFDocDecree;
 *
 */


public abstract class ENAct2DFDocDecreeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2DFDocDecreeControllerEJBGen() {
	}

	/* ENAct2DFDocDecree. Добавить */
	public int add(ENAct2DFDocDecree object) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDocDecree. Удалить */
	public void remove(int code) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2DFDocDecree. Изменить */
	public void save(ENAct2DFDocDecree object) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDocDecree. Получить объект */
	public ENAct2DFDocDecree getObject(int code) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDocDecree. Получить полный список */
	public ENAct2DFDocDecreeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2DFDocDecree. Получить список по фильтру */
	public ENAct2DFDocDecreeShortList getFilteredList(
			ENAct2DFDocDecreeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2DFDocDecree. Получить список для просмотра */
	public ENAct2DFDocDecreeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2DFDocDecree. Получить список для просмотра по фильтру */
	public ENAct2DFDocDecreeShortList getScrollableFilteredList(
			ENAct2DFDocDecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDocDecree. Получить список для просмотра по условию */
	public ENAct2DFDocDecreeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2DFDocDecreeFilter filterObject = new ENAct2DFDocDecreeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2DFDocDecree. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2DFDocDecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2DFDocDecree. Получить объект из списка */
	public ENAct2DFDocDecreeShort getShortObject(int code) {
		try {
			ENAct2DFDocDecreeDAO objectDAO = new ENAct2DFDocDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2DFDocDecree%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
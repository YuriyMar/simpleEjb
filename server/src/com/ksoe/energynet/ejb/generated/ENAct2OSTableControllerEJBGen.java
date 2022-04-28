
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2OSTableDAO;
import com.ksoe.energynet.valueobject.ENAct2OSTable;
import com.ksoe.energynet.valueobject.lists.ENAct2OSTableShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2OSTableShort;
import com.ksoe.energynet.valueobject.filter.ENAct2OSTableFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2OSTable;
 *
 */


public abstract class ENAct2OSTableControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2OSTableControllerEJBGen() {
	}

	/* ENAct2OSTable. Добавить */
	public int add(ENAct2OSTable object) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2OSTable%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2OSTable. Удалить */
	public void remove(int code) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2OSTable%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2OSTable. Изменить */
	public void save(ENAct2OSTable object) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2OSTable%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2OSTable. Получить объект */
	public ENAct2OSTable getObject(int code) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2OSTable%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2OSTable. Получить полный список */
	public ENAct2OSTableShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2OSTable. Получить список по фильтру */
	public ENAct2OSTableShortList getFilteredList(
			ENAct2OSTableFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2OSTable. Получить список для просмотра */
	public ENAct2OSTableShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2OSTable. Получить список для просмотра по фильтру */
	public ENAct2OSTableShortList getScrollableFilteredList(
			ENAct2OSTableFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2OSTable%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2OSTable. Получить список для просмотра по условию */
	public ENAct2OSTableShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2OSTableFilter filterObject = new ENAct2OSTableFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2OSTable. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2OSTableFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2OSTable%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2OSTable. Получить объект из списка */
	public ENAct2OSTableShort getShortObject(int code) {
		try {
			ENAct2OSTableDAO objectDAO = new ENAct2OSTableDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2OSTable%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENIPFinancingDAO;
import com.ksoe.energynet.valueobject.ENIPFinancing;
import com.ksoe.energynet.valueobject.lists.ENIPFinancingShortList;
import com.ksoe.energynet.valueobject.brief.ENIPFinancingShort;
import com.ksoe.energynet.valueobject.filter.ENIPFinancingFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPFinancing;
 *
 */


public abstract class ENIPFinancingControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENIPFinancingControllerEJBGen() {
	}

	/* ENIPFinancing. Добавить */
	public int add(ENIPFinancing object) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENIPFinancing%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPFinancing. Удалить */
	public void remove(int code) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENIPFinancing%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENIPFinancing. Изменить */
	public void save(ENIPFinancing object) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENIPFinancing%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPFinancing. Получить объект */
	public ENIPFinancing getObject(int code) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPFinancing%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPFinancing. Получить полный список */
	public ENIPFinancingShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENIPFinancing. Получить список по фильтру */
	public ENIPFinancingShortList getFilteredList(
			ENIPFinancingFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENIPFinancing. Получить список для просмотра */
	public ENIPFinancingShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENIPFinancing. Получить список для просмотра по фильтру */
	public ENIPFinancingShortList getScrollableFilteredList(
			ENIPFinancingFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENIPFinancing%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPFinancing. Получить список для просмотра по условию */
	public ENIPFinancingShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENIPFinancingFilter filterObject = new ENIPFinancingFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENIPFinancing. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENIPFinancingFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENIPFinancing%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENIPFinancing. Получить объект из списка */
	public ENIPFinancingShort getShortObject(int code) {
		try {
			ENIPFinancingDAO objectDAO = new ENIPFinancingDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENIPFinancing%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInvestProgramItemDAO;
import com.ksoe.energynet.valueobject.ENInvestProgramItem;
import com.ksoe.energynet.valueobject.lists.ENInvestProgramItemShortList;
import com.ksoe.energynet.valueobject.brief.ENInvestProgramItemShort;
import com.ksoe.energynet.valueobject.filter.ENInvestProgramItemFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInvestProgramItem;
 *
 */


public abstract class ENInvestProgramItemControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInvestProgramItemControllerEJBGen() {
	}

	/* ENInvestProgramItem. Добавить */
	public int add(ENInvestProgramItem object) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramItem. Удалить */
	public void remove(int code) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInvestProgramItem. Изменить */
	public void save(ENInvestProgramItem object) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramItem. Получить объект */
	public ENInvestProgramItem getObject(int code) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramItem. Получить полный список */
	public ENInvestProgramItemShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInvestProgramItem. Получить список по фильтру */
	public ENInvestProgramItemShortList getFilteredList(
			ENInvestProgramItemFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInvestProgramItem. Получить список для просмотра */
	public ENInvestProgramItemShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInvestProgramItem. Получить список для просмотра по фильтру */
	public ENInvestProgramItemShortList getScrollableFilteredList(
			ENInvestProgramItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramItem. Получить список для просмотра по условию */
	public ENInvestProgramItemShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInvestProgramItemFilter filterObject = new ENInvestProgramItemFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInvestProgramItem. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInvestProgramItemFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramItem. Получить объект из списка */
	public ENInvestProgramItemShort getShortObject(int code) {
		try {
			ENInvestProgramItemDAO objectDAO = new ENInvestProgramItemDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgramItem%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
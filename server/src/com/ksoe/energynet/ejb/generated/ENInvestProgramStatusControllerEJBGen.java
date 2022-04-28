
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInvestProgramStatusDAO;
import com.ksoe.energynet.valueobject.ENInvestProgramStatus;
import com.ksoe.energynet.valueobject.lists.ENInvestProgramStatusShortList;
import com.ksoe.energynet.valueobject.brief.ENInvestProgramStatusShort;
import com.ksoe.energynet.valueobject.filter.ENInvestProgramStatusFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInvestProgramStatus;
 *
 */


public abstract class ENInvestProgramStatusControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInvestProgramStatusControllerEJBGen() {
	}

	/* ENInvestProgramStatus. Добавить */
	public int add(ENInvestProgramStatus object) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramStatus. Удалить */
	public void remove(int code) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInvestProgramStatus. Изменить */
	public void save(ENInvestProgramStatus object) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramStatus. Получить объект */
	public ENInvestProgramStatus getObject(int code) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramStatus. Получить полный список */
	public ENInvestProgramStatusShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInvestProgramStatus. Получить список по фильтру */
	public ENInvestProgramStatusShortList getFilteredList(
			ENInvestProgramStatusFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInvestProgramStatus. Получить список для просмотра */
	public ENInvestProgramStatusShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInvestProgramStatus. Получить список для просмотра по фильтру */
	public ENInvestProgramStatusShortList getScrollableFilteredList(
			ENInvestProgramStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramStatus. Получить список для просмотра по условию */
	public ENInvestProgramStatusShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInvestProgramStatusFilter filterObject = new ENInvestProgramStatusFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInvestProgramStatus. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInvestProgramStatusFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgramStatus. Получить объект из списка */
	public ENInvestProgramStatusShort getShortObject(int code) {
		try {
			ENInvestProgramStatusDAO objectDAO = new ENInvestProgramStatusDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgramStatus%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
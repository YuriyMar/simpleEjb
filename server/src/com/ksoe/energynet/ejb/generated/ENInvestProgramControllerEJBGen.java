
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENInvestProgramDAO;
import com.ksoe.energynet.valueobject.ENInvestProgram;
import com.ksoe.energynet.valueobject.lists.ENInvestProgramShortList;
import com.ksoe.energynet.valueobject.brief.ENInvestProgramShort;
import com.ksoe.energynet.valueobject.filter.ENInvestProgramFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENInvestProgram;
 *
 */


public abstract class ENInvestProgramControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENInvestProgramControllerEJBGen() {
	}

	/* ENInvestProgram. Добавить */
	public int add(ENInvestProgram object) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENInvestProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram. Удалить */
	public void remove(int code) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENInvestProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENInvestProgram. Изменить */
	public void save(ENInvestProgram object) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENInvestProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram. Получить объект */
	public ENInvestProgram getObject(int code) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgram%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram. Получить полный список */
	public ENInvestProgramShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENInvestProgram. Получить список по фильтру */
	public ENInvestProgramShortList getFilteredList(
			ENInvestProgramFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENInvestProgram. Получить список для просмотра */
	public ENInvestProgramShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENInvestProgram. Получить список для просмотра по фильтру */
	public ENInvestProgramShortList getScrollableFilteredList(
			ENInvestProgramFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENInvestProgram%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram. Получить список для просмотра по условию */
	public ENInvestProgramShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENInvestProgramFilter filterObject = new ENInvestProgramFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENInvestProgram. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENInvestProgramFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestProgram%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENInvestProgram. Получить объект из списка */
	public ENInvestProgramShort getShortObject(int code) {
		try {
			ENInvestProgramDAO objectDAO = new ENInvestProgramDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENInvestProgram%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
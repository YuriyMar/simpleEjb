
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServFromSide2PlanWorkDAO;
import com.ksoe.energynet.valueobject.ENServFromSide2PlanWork;
import com.ksoe.energynet.valueobject.lists.ENServFromSide2PlanWorkShortList;
import com.ksoe.energynet.valueobject.brief.ENServFromSide2PlanWorkShort;
import com.ksoe.energynet.valueobject.filter.ENServFromSide2PlanWorkFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServFromSide2PlanWork;
 *
 */


public abstract class ENServFromSide2PlanWorkControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServFromSide2PlanWorkControllerEJBGen() {
	}

	/* ENServFromSide2PlanWork. Добавить */
	public int add(ENServFromSide2PlanWork object) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSide2PlanWork. Удалить */
	public void remove(int code) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServFromSide2PlanWork. Изменить */
	public void save(ENServFromSide2PlanWork object) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSide2PlanWork. Получить объект */
	public ENServFromSide2PlanWork getObject(int code) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSide2PlanWork. Получить полный список */
	public ENServFromSide2PlanWorkShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServFromSide2PlanWork. Получить список по фильтру */
	public ENServFromSide2PlanWorkShortList getFilteredList(
			ENServFromSide2PlanWorkFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServFromSide2PlanWork. Получить список для просмотра */
	public ENServFromSide2PlanWorkShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServFromSide2PlanWork. Получить список для просмотра по фильтру */
	public ENServFromSide2PlanWorkShortList getScrollableFilteredList(
			ENServFromSide2PlanWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSide2PlanWork. Получить список для просмотра по условию */
	public ENServFromSide2PlanWorkShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServFromSide2PlanWorkFilter filterObject = new ENServFromSide2PlanWorkFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServFromSide2PlanWork. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServFromSide2PlanWorkFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServFromSide2PlanWork. Получить объект из списка */
	public ENServFromSide2PlanWorkShort getShortObject(int code) {
		try {
			ENServFromSide2PlanWorkDAO objectDAO = new ENServFromSide2PlanWorkDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServFromSide2PlanWork%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
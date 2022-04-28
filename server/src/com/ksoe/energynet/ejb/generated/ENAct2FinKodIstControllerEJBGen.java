
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2FinKodIstDAO;
import com.ksoe.energynet.valueobject.ENAct2FinKodIst;
import com.ksoe.energynet.valueobject.lists.ENAct2FinKodIstShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2FinKodIstShort;
import com.ksoe.energynet.valueobject.filter.ENAct2FinKodIstFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2FinKodIst;
 *
 */


public abstract class ENAct2FinKodIstControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2FinKodIstControllerEJBGen() {
	}

	/* ENAct2FinKodIst. Добавить */
	public int add(ENAct2FinKodIst object) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinKodIst. Удалить */
	public void remove(int code) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2FinKodIst. Изменить */
	public void save(ENAct2FinKodIst object) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinKodIst. Получить объект */
	public ENAct2FinKodIst getObject(int code) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinKodIst. Получить полный список */
	public ENAct2FinKodIstShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2FinKodIst. Получить список по фильтру */
	public ENAct2FinKodIstShortList getFilteredList(
			ENAct2FinKodIstFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2FinKodIst. Получить список для просмотра */
	public ENAct2FinKodIstShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2FinKodIst. Получить список для просмотра по фильтру */
	public ENAct2FinKodIstShortList getScrollableFilteredList(
			ENAct2FinKodIstFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinKodIst. Получить список для просмотра по условию */
	public ENAct2FinKodIstShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2FinKodIstFilter filterObject = new ENAct2FinKodIstFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2FinKodIst. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2FinKodIstFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinKodIst. Получить объект из списка */
	public ENAct2FinKodIstShort getShortObject(int code) {
		try {
			ENAct2FinKodIstDAO objectDAO = new ENAct2FinKodIstDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2FinKodIst%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
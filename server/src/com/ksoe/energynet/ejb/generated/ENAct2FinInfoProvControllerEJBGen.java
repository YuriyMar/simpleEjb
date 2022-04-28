
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2FinInfoProvDAO;
import com.ksoe.energynet.valueobject.ENAct2FinInfoProv;
import com.ksoe.energynet.valueobject.lists.ENAct2FinInfoProvShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2FinInfoProvShort;
import com.ksoe.energynet.valueobject.filter.ENAct2FinInfoProvFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2FinInfoProv;
 *
 */


public abstract class ENAct2FinInfoProvControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2FinInfoProvControllerEJBGen() {
	}

	/* ENAct2FinInfoProv. Добавить */
	public int add(ENAct2FinInfoProv object) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinInfoProv. Удалить */
	public void remove(int code) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2FinInfoProv. Изменить */
	public void save(ENAct2FinInfoProv object) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinInfoProv. Получить объект */
	public ENAct2FinInfoProv getObject(int code) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinInfoProv. Получить полный список */
	public ENAct2FinInfoProvShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2FinInfoProv. Получить список по фильтру */
	public ENAct2FinInfoProvShortList getFilteredList(
			ENAct2FinInfoProvFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2FinInfoProv. Получить список для просмотра */
	public ENAct2FinInfoProvShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2FinInfoProv. Получить список для просмотра по фильтру */
	public ENAct2FinInfoProvShortList getScrollableFilteredList(
			ENAct2FinInfoProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinInfoProv. Получить список для просмотра по условию */
	public ENAct2FinInfoProvShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2FinInfoProvFilter filterObject = new ENAct2FinInfoProvFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2FinInfoProv. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2FinInfoProvFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2FinInfoProv. Получить объект из списка */
	public ENAct2FinInfoProvShort getShortObject(int code) {
		try {
			ENAct2FinInfoProvDAO objectDAO = new ENAct2FinInfoProvDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2FinInfoProv%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
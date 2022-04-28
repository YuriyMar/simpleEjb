
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSettleTypeDAO;
import com.ksoe.energynet.valueobject.ENSettleType;
import com.ksoe.energynet.valueobject.lists.ENSettleTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENSettleTypeShort;
import com.ksoe.energynet.valueobject.filter.ENSettleTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSettleType;
 *
 */


public abstract class ENSettleTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSettleTypeControllerEJBGen() {
	}

	/* ENSettleType. Добавить */
	public int add(ENSettleType object) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSettleType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettleType. Удалить */
	public void remove(int code) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSettleType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSettleType. Изменить */
	public void save(ENSettleType object) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSettleType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettleType. Получить объект */
	public ENSettleType getObject(int code) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettleType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettleType. Получить полный список */
	public ENSettleTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSettleType. Получить список по фильтру */
	public ENSettleTypeShortList getFilteredList(
			ENSettleTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSettleType. Получить список для просмотра */
	public ENSettleTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSettleType. Получить список для просмотра по фильтру */
	public ENSettleTypeShortList getScrollableFilteredList(
			ENSettleTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSettleType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettleType. Получить список для просмотра по условию */
	public ENSettleTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSettleTypeFilter filterObject = new ENSettleTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSettleType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSettleTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSettleType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettleType. Получить объект из списка */
	public ENSettleTypeShort getShortObject(int code) {
		try {
			ENSettleTypeDAO objectDAO = new ENSettleTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettleType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
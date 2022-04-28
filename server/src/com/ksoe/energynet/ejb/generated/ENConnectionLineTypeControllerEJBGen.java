
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionLineTypeDAO;
import com.ksoe.energynet.valueobject.ENConnectionLineType;
import com.ksoe.energynet.valueobject.lists.ENConnectionLineTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionLineTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionLineTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionLineType;
 *
 */


public abstract class ENConnectionLineTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionLineTypeControllerEJBGen() {
	}

	/* ENConnectionLineType. Добавить */
	public int add(ENConnectionLineType object) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionLineType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLineType. Удалить */
	public void remove(int code) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionLineType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionLineType. Изменить */
	public void save(ENConnectionLineType object) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionLineType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLineType. Получить объект */
	public ENConnectionLineType getObject(int code) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionLineType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLineType. Получить полный список */
	public ENConnectionLineTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionLineType. Получить список по фильтру */
	public ENConnectionLineTypeShortList getFilteredList(
			ENConnectionLineTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionLineType. Получить список для просмотра */
	public ENConnectionLineTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionLineType. Получить список для просмотра по фильтру */
	public ENConnectionLineTypeShortList getScrollableFilteredList(
			ENConnectionLineTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionLineType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLineType. Получить список для просмотра по условию */
	public ENConnectionLineTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionLineTypeFilter filterObject = new ENConnectionLineTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionLineType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionLineTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionLineType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionLineType. Получить объект из списка */
	public ENConnectionLineTypeShort getShortObject(int code) {
		try {
			ENConnectionLineTypeDAO objectDAO = new ENConnectionLineTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionLineType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
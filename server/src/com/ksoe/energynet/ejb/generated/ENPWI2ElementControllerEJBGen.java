
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPWI2ElementDAO;
import com.ksoe.energynet.valueobject.ENPWI2Element;
import com.ksoe.energynet.valueobject.lists.ENPWI2ElementShortList;
import com.ksoe.energynet.valueobject.brief.ENPWI2ElementShort;
import com.ksoe.energynet.valueobject.filter.ENPWI2ElementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPWI2Element;
 *
 */


public abstract class ENPWI2ElementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPWI2ElementControllerEJBGen() {
	}

	/* ENPWI2Element. Добавить */
	public int add(ENPWI2Element object) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPWI2Element%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPWI2Element. Удалить */
	public void remove(int code) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPWI2Element%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPWI2Element. Изменить */
	public void save(ENPWI2Element object) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPWI2Element%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPWI2Element. Получить объект */
	public ENPWI2Element getObject(int code) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPWI2Element%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPWI2Element. Получить полный список */
	public ENPWI2ElementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPWI2Element. Получить список по фильтру */
	public ENPWI2ElementShortList getFilteredList(
			ENPWI2ElementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPWI2Element. Получить список для просмотра */
	public ENPWI2ElementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPWI2Element. Получить список для просмотра по фильтру */
	public ENPWI2ElementShortList getScrollableFilteredList(
			ENPWI2ElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPWI2Element%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPWI2Element. Получить список для просмотра по условию */
	public ENPWI2ElementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPWI2ElementFilter filterObject = new ENPWI2ElementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPWI2Element. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPWI2ElementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPWI2Element%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPWI2Element. Получить объект из списка */
	public ENPWI2ElementShort getShortObject(int code) {
		try {
			ENPWI2ElementDAO objectDAO = new ENPWI2ElementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPWI2Element%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionPowerPointDAO;
import com.ksoe.energynet.valueobject.ENConnectionPowerPoint;
import com.ksoe.energynet.valueobject.lists.ENConnectionPowerPointShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionPowerPointShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionPowerPointFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionPowerPoint;
 *
 */


public abstract class ENConnectionPowerPointControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionPowerPointControllerEJBGen() {
	}

	/* ENConnectionPowerPoint. Добавить */
	public int add(ENConnectionPowerPoint object) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionPowerPoint. Удалить */
	public void remove(int code) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionPowerPoint. Изменить */
	public void save(ENConnectionPowerPoint object) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionPowerPoint. Получить объект */
	public ENConnectionPowerPoint getObject(int code) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionPowerPoint. Получить полный список */
	public ENConnectionPowerPointShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionPowerPoint. Получить список по фильтру */
	public ENConnectionPowerPointShortList getFilteredList(
			ENConnectionPowerPointFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionPowerPoint. Получить список для просмотра */
	public ENConnectionPowerPointShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionPowerPoint. Получить список для просмотра по фильтру */
	public ENConnectionPowerPointShortList getScrollableFilteredList(
			ENConnectionPowerPointFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionPowerPoint. Получить список для просмотра по условию */
	public ENConnectionPowerPointShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionPowerPointFilter filterObject = new ENConnectionPowerPointFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionPowerPoint. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionPowerPointFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionPowerPoint. Получить объект из списка */
	public ENConnectionPowerPointShort getShortObject(int code) {
		try {
			ENConnectionPowerPointDAO objectDAO = new ENConnectionPowerPointDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionPowerPoint%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
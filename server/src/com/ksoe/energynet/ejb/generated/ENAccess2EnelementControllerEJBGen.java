
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAccess2EnelementDAO;
import com.ksoe.energynet.valueobject.ENAccess2Enelement;
import com.ksoe.energynet.valueobject.lists.ENAccess2EnelementShortList;
import com.ksoe.energynet.valueobject.brief.ENAccess2EnelementShort;
import com.ksoe.energynet.valueobject.filter.ENAccess2EnelementFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENAccess2Enelement;
 *
 */


public abstract class ENAccess2EnelementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAccess2EnelementControllerEJBGen() {
	}

	/* ENAccess2Enelement. Добавить */
	public int add(ENAccess2Enelement object) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAccess2Enelement. Удалить */
	public void remove(int code) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAccess2Enelement. Изменить */
	public void save(ENAccess2Enelement object) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAccess2Enelement. Получить объект */
	public ENAccess2Enelement getObject(int code) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAccess2Enelement. Получить полный список */
	public ENAccess2EnelementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAccess2Enelement. Получить список по фильтру */
	public ENAccess2EnelementShortList getFilteredList(
			ENAccess2EnelementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAccess2Enelement. Получить список для просмотра */
	public ENAccess2EnelementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAccess2Enelement. Получить список для просмотра по фильтру */
	public ENAccess2EnelementShortList getScrollableFilteredList(
			ENAccess2EnelementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAccess2Enelement. Получить список для просмотра по условию */
	public ENAccess2EnelementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAccess2EnelementFilter filterObject = new ENAccess2EnelementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAccess2Enelement. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAccess2EnelementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAccess2Enelement. Получить объект из списка */
	public ENAccess2EnelementShort getShortObject(int code) {
		try {
			ENAccess2EnelementDAO objectDAO = new ENAccess2EnelementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAccess2Enelement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
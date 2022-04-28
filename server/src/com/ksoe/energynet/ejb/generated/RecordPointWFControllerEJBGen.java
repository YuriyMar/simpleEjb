
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.RecordPointWFDAO;
import com.ksoe.energynet.valueobject.RecordPointWF;
import com.ksoe.energynet.valueobject.brief.RecordPointWFShort;
import com.ksoe.energynet.valueobject.filter.RecordPointWFFilter;
import com.ksoe.energynet.valueobject.lists.RecordPointWFShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for RecordPointWF;
 *
 */


public abstract class RecordPointWFControllerEJBGen extends
		GenericSessionStatelessBean {
	public RecordPointWFControllerEJBGen() {
	}

	/* RecordPointWF. Добавить */
	public int add(RecordPointWF object) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.RecordPointWF%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RecordPointWF. Удалить */
	public void remove(int code) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.RecordPointWF%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* RecordPointWF. Изменить */
	public void save(RecordPointWF object) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.RecordPointWF%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RecordPointWF. Получить объект */
	public RecordPointWF getObject(int code) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.RecordPointWF%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RecordPointWF. Получить полный список */
	public RecordPointWFShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* RecordPointWF. Получить список по фильтру */
	public RecordPointWFShortList getFilteredList(
			RecordPointWFFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* RecordPointWF. Получить список для просмотра */
	public RecordPointWFShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* RecordPointWF. Получить список для просмотра по фильтру */
	public RecordPointWFShortList getScrollableFilteredList(
			RecordPointWFFilter filterObject, int fromPosition,
			int quantity) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.RecordPointWF%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RecordPointWF. Получить список для просмотра по условию */
	public RecordPointWFShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		RecordPointWFFilter filterObject = new RecordPointWFFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* RecordPointWF. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			RecordPointWFFilter filterObject, int fromPosition,
			int quantity) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.RecordPointWF%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* RecordPointWF. Получить объект из списка */
	public RecordPointWFShort getShortObject(int code) {
		try {
			RecordPointWFDAO objectDAO = new RecordPointWFDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.RecordPointWF%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
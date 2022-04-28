
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENBankingDetailsDAO;
import com.ksoe.energynet.valueobject.ENBankingDetails;
import com.ksoe.energynet.valueobject.lists.ENBankingDetailsShortList;
import com.ksoe.energynet.valueobject.brief.ENBankingDetailsShort;
import com.ksoe.energynet.valueobject.filter.ENBankingDetailsFilter;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

/**
 * EJB controller for ENBankingDetails;
 *
 */


public abstract class ENBankingDetailsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENBankingDetailsControllerEJBGen() {
	}

	/* ENBankingDetails. Добавить */
	public int add(ENBankingDetails object) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENBankingDetails%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingDetails. Удалить */
	public void remove(int code) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENBankingDetails%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENBankingDetails. Изменить */
	public void save(ENBankingDetails object) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENBankingDetails%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingDetails. Получить объект */
	public ENBankingDetails getObject(int code) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBankingDetails%} object.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingDetails. Получить полный список */
	public ENBankingDetailsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENBankingDetails. Получить список по фильтру */
	public ENBankingDetailsShortList getFilteredList(
			ENBankingDetailsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENBankingDetails. Получить список для просмотра */
	public ENBankingDetailsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENBankingDetails. Получить список для просмотра по фильтру */
	public ENBankingDetailsShortList getScrollableFilteredList(
			ENBankingDetailsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENBankingDetails%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingDetails. Получить список для просмотра по условию */
	public ENBankingDetailsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENBankingDetailsFilter filterObject = new ENBankingDetailsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENBankingDetails. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENBankingDetailsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENBankingDetails%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENBankingDetails. Получить объект из списка */
	public ENBankingDetailsShort getShortObject(int code) {
		try {
			ENBankingDetailsDAO objectDAO = new ENBankingDetailsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENBankingDetails%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
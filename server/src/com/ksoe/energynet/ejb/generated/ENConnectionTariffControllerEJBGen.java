
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENConnectionTariffDAO;
import com.ksoe.energynet.valueobject.ENConnectionTariff;
import com.ksoe.energynet.valueobject.lists.ENConnectionTariffShortList;
import com.ksoe.energynet.valueobject.brief.ENConnectionTariffShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionTariffFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENConnectionTariff;
 *
 */


public abstract class ENConnectionTariffControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENConnectionTariffControllerEJBGen() {
	}

	/* ENConnectionTariff. Добавить */
	public int add(ENConnectionTariff object) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENConnectionTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariff. Удалить */
	public void remove(int code) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENConnectionTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENConnectionTariff. Изменить */
	public void save(ENConnectionTariff object) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENConnectionTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariff. Получить объект */
	public ENConnectionTariff getObject(int code) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionTariff%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariff. Получить полный список */
	public ENConnectionTariffShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENConnectionTariff. Получить список по фильтру */
	public ENConnectionTariffShortList getFilteredList(
			ENConnectionTariffFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENConnectionTariff. Получить список для просмотра */
	public ENConnectionTariffShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENConnectionTariff. Получить список для просмотра по фильтру */
	public ENConnectionTariffShortList getScrollableFilteredList(
			ENConnectionTariffFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionTariff%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariff. Получить список для просмотра по условию */
	public ENConnectionTariffShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENConnectionTariffFilter filterObject = new ENConnectionTariffFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENConnectionTariff. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENConnectionTariffFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionTariff%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENConnectionTariff. Получить объект из списка */
	public ENConnectionTariffShort getShortObject(int code) {
		try {
			ENConnectionTariffDAO objectDAO = new ENConnectionTariffDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENConnectionTariff%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
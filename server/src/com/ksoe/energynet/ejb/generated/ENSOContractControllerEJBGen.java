
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOContractDAO;
import com.ksoe.energynet.valueobject.ENSOContract;
import com.ksoe.energynet.valueobject.lists.ENSOContractShortList;
import com.ksoe.energynet.valueobject.brief.ENSOContractShort;
import com.ksoe.energynet.valueobject.filter.ENSOContractFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOContract;
 *
 */


public abstract class ENSOContractControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOContractControllerEJBGen() {
	}

	/* ENSOContract. Добавить */
	public int add(ENSOContract object) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOContract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOContract. Удалить */
	public void remove(int code) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOContract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOContract. Изменить */
	public void save(ENSOContract object) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOContract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOContract. Получить объект */
	public ENSOContract getObject(int code) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOContract%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOContract. Получить полный список */
	public ENSOContractShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOContract. Получить список по фильтру */
	public ENSOContractShortList getFilteredList(
			ENSOContractFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOContract. Получить список для просмотра */
	public ENSOContractShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOContract. Получить список для просмотра по фильтру */
	public ENSOContractShortList getScrollableFilteredList(
			ENSOContractFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOContract%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOContract. Получить список для просмотра по условию */
	public ENSOContractShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOContractFilter filterObject = new ENSOContractFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOContract. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOContractFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOContract%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOContract. Получить объект из списка */
	public ENSOContractShort getShortObject(int code) {
		try {
			ENSOContractDAO objectDAO = new ENSOContractDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOContract%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
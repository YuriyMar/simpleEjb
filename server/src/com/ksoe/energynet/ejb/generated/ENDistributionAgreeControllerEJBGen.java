
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDistributionAgreeDAO;
import com.ksoe.energynet.valueobject.ENDistributionAgree;
import com.ksoe.energynet.valueobject.lists.ENDistributionAgreeShortList;
import com.ksoe.energynet.valueobject.brief.ENDistributionAgreeShort;
import com.ksoe.energynet.valueobject.filter.ENDistributionAgreeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDistributionAgree;
 *
 */


public abstract class ENDistributionAgreeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDistributionAgreeControllerEJBGen() {
	}

	/* ENDistributionAgree. Добавить */
	public int add(ENDistributionAgree object) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDistributionAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDistributionAgree. Удалить */
	public void remove(int code) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDistributionAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDistributionAgree. Изменить */
	public void save(ENDistributionAgree object) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDistributionAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDistributionAgree. Получить объект */
	public ENDistributionAgree getObject(int code) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDistributionAgree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDistributionAgree. Получить полный список */
	public ENDistributionAgreeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDistributionAgree. Получить список по фильтру */
	public ENDistributionAgreeShortList getFilteredList(
			ENDistributionAgreeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDistributionAgree. Получить список для просмотра */
	public ENDistributionAgreeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDistributionAgree. Получить список для просмотра по фильтру */
	public ENDistributionAgreeShortList getScrollableFilteredList(
			ENDistributionAgreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDistributionAgree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDistributionAgree. Получить список для просмотра по условию */
	public ENDistributionAgreeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDistributionAgreeFilter filterObject = new ENDistributionAgreeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDistributionAgree. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDistributionAgreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDistributionAgree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDistributionAgree. Получить объект из списка */
	public ENDistributionAgreeShort getShortObject(int code) {
		try {
			ENDistributionAgreeDAO objectDAO = new ENDistributionAgreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDistributionAgree%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
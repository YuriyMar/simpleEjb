
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSOLeaseAgreementDAO;
import com.ksoe.energynet.valueobject.ENSOLeaseAgreement;
import com.ksoe.energynet.valueobject.lists.ENSOLeaseAgreementShortList;
import com.ksoe.energynet.valueobject.brief.ENSOLeaseAgreementShort;
import com.ksoe.energynet.valueobject.filter.ENSOLeaseAgreementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSOLeaseAgreement;
 *
 */


public abstract class ENSOLeaseAgreementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSOLeaseAgreementControllerEJBGen() {
	}

	/* ENSOLeaseAgreement. Добавить */
	public int add(ENSOLeaseAgreement object) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOLeaseAgreement. Удалить */
	public void remove(int code) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSOLeaseAgreement. Изменить */
	public void save(ENSOLeaseAgreement object) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOLeaseAgreement. Получить объект */
	public ENSOLeaseAgreement getObject(int code) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOLeaseAgreement. Получить полный список */
	public ENSOLeaseAgreementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSOLeaseAgreement. Получить список по фильтру */
	public ENSOLeaseAgreementShortList getFilteredList(
			ENSOLeaseAgreementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSOLeaseAgreement. Получить список для просмотра */
	public ENSOLeaseAgreementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSOLeaseAgreement. Получить список для просмотра по фильтру */
	public ENSOLeaseAgreementShortList getScrollableFilteredList(
			ENSOLeaseAgreementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOLeaseAgreement. Получить список для просмотра по условию */
	public ENSOLeaseAgreementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSOLeaseAgreementFilter filterObject = new ENSOLeaseAgreementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSOLeaseAgreement. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSOLeaseAgreementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSOLeaseAgreement. Получить объект из списка */
	public ENSOLeaseAgreementShort getShortObject(int code) {
		try {
			ENSOLeaseAgreementDAO objectDAO = new ENSOLeaseAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSOLeaseAgreement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
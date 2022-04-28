
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAdditionalAgreementDAO;
import com.ksoe.energynet.valueobject.ENAdditionalAgreement;
import com.ksoe.energynet.valueobject.lists.ENAdditionalAgreementShortList;
import com.ksoe.energynet.valueobject.brief.ENAdditionalAgreementShort;
import com.ksoe.energynet.valueobject.filter.ENAdditionalAgreementFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAdditionalAgreement;
 *
 */


public abstract class ENAdditionalAgreementControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAdditionalAgreementControllerEJBGen() {
	}

	/* ENAdditionalAgreement. Добавить */
	public int add(ENAdditionalAgreement object) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAdditionalAgreement. Удалить */
	public void remove(int code) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAdditionalAgreement. Изменить */
	public void save(ENAdditionalAgreement object) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAdditionalAgreement. Получить объект */
	public ENAdditionalAgreement getObject(int code) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAdditionalAgreement. Получить полный список */
	public ENAdditionalAgreementShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAdditionalAgreement. Получить список по фильтру */
	public ENAdditionalAgreementShortList getFilteredList(
			ENAdditionalAgreementFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAdditionalAgreement. Получить список для просмотра */
	public ENAdditionalAgreementShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAdditionalAgreement. Получить список для просмотра по фильтру */
	public ENAdditionalAgreementShortList getScrollableFilteredList(
			ENAdditionalAgreementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAdditionalAgreement. Получить список для просмотра по условию */
	public ENAdditionalAgreementShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAdditionalAgreementFilter filterObject = new ENAdditionalAgreementFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAdditionalAgreement. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAdditionalAgreementFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAdditionalAgreement. Получить объект из списка */
	public ENAdditionalAgreementShort getShortObject(int code) {
		try {
			ENAdditionalAgreementDAO objectDAO = new ENAdditionalAgreementDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAdditionalAgreement%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCoefficientQualityStandardsDAO;
import com.ksoe.energynet.valueobject.ENCoefficientQualityStandards;
import com.ksoe.energynet.valueobject.lists.ENCoefficientQualityStandardsShortList;
import com.ksoe.energynet.valueobject.brief.ENCoefficientQualityStandardsShort;
import com.ksoe.energynet.valueobject.filter.ENCoefficientQualityStandardsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCoefficientQualityStandards;
 *
 */


public abstract class ENCoefficientQualityStandardsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCoefficientQualityStandardsControllerEJBGen() {
	}

	/* ENCoefficientQualityStandards. Добавить */
	public int add(ENCoefficientQualityStandards object) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientQualityStandards. Удалить */
	public void remove(int code) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCoefficientQualityStandards. Изменить */
	public void save(ENCoefficientQualityStandards object) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientQualityStandards. Получить объект */
	public ENCoefficientQualityStandards getObject(int code) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientQualityStandards. Получить полный список */
	public ENCoefficientQualityStandardsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCoefficientQualityStandards. Получить список по фильтру */
	public ENCoefficientQualityStandardsShortList getFilteredList(
			ENCoefficientQualityStandardsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCoefficientQualityStandards. Получить список для просмотра */
	public ENCoefficientQualityStandardsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCoefficientQualityStandards. Получить список для просмотра по фильтру */
	public ENCoefficientQualityStandardsShortList getScrollableFilteredList(
			ENCoefficientQualityStandardsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientQualityStandards. Получить список для просмотра по условию */
	public ENCoefficientQualityStandardsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCoefficientQualityStandardsFilter filterObject = new ENCoefficientQualityStandardsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCoefficientQualityStandards. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCoefficientQualityStandardsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCoefficientQualityStandards. Получить объект из списка */
	public ENCoefficientQualityStandardsShort getShortObject(int code) {
		try {
			ENCoefficientQualityStandardsDAO objectDAO = new ENCoefficientQualityStandardsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCoefficientQualityStandards%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
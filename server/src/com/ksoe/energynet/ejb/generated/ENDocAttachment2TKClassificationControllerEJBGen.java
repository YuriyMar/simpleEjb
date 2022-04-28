
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachment2TKClassificationDAO;
import com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification;
import com.ksoe.energynet.valueobject.lists.ENDocAttachment2TKClassificationShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachment2TKClassificationShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachment2TKClassificationFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment2TKClassification;
 *
 */


public abstract class ENDocAttachment2TKClassificationControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachment2TKClassificationControllerEJBGen() {
	}

	/* ENDocAttachment2TKClassification. Добавить */
	public int add(ENDocAttachment2TKClassification object) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKClassification. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment2TKClassification. Изменить */
	public void save(ENDocAttachment2TKClassification object) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKClassification. Получить объект */
	public ENDocAttachment2TKClassification getObject(int code) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKClassification. Получить полный список */
	public ENDocAttachment2TKClassificationShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment2TKClassification. Получить список по фильтру */
	public ENDocAttachment2TKClassificationShortList getFilteredList(
			ENDocAttachment2TKClassificationFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment2TKClassification. Получить список для просмотра */
	public ENDocAttachment2TKClassificationShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment2TKClassification. Получить список для просмотра по фильтру */
	public ENDocAttachment2TKClassificationShortList getScrollableFilteredList(
			ENDocAttachment2TKClassificationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKClassification. Получить список для просмотра по условию */
	public ENDocAttachment2TKClassificationShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachment2TKClassificationFilter filterObject = new ENDocAttachment2TKClassificationFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment2TKClassification. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachment2TKClassificationFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKClassification. Получить объект из списка */
	public ENDocAttachment2TKClassificationShort getShortObject(int code) {
		try {
			ENDocAttachment2TKClassificationDAO objectDAO = new ENDocAttachment2TKClassificationDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2TKClassification%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAttachment2ServicesKindDAO;
import com.ksoe.energynet.valueobject.ENAttachment2ServicesKind;
import com.ksoe.energynet.valueobject.lists.ENAttachment2ServicesKindShortList;
import com.ksoe.energynet.valueobject.brief.ENAttachment2ServicesKindShort;
import com.ksoe.energynet.valueobject.filter.ENAttachment2ServicesKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAttachment2ServicesKind;
 *
 */


public abstract class ENAttachment2ServicesKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAttachment2ServicesKindControllerEJBGen() {
	}

	/* ENAttachment2ServicesKind. Добавить */
	public int add(ENAttachment2ServicesKind object) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAttachment2ServicesKind. Удалить */
	public void remove(int code) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAttachment2ServicesKind. Изменить */
	public void save(ENAttachment2ServicesKind object) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAttachment2ServicesKind. Получить объект */
	public ENAttachment2ServicesKind getObject(int code) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAttachment2ServicesKind. Получить полный список */
	public ENAttachment2ServicesKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAttachment2ServicesKind. Получить список по фильтру */
	public ENAttachment2ServicesKindShortList getFilteredList(
			ENAttachment2ServicesKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAttachment2ServicesKind. Получить список для просмотра */
	public ENAttachment2ServicesKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAttachment2ServicesKind. Получить список для просмотра по фильтру */
	public ENAttachment2ServicesKindShortList getScrollableFilteredList(
			ENAttachment2ServicesKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAttachment2ServicesKind. Получить список для просмотра по условию */
	public ENAttachment2ServicesKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAttachment2ServicesKindFilter filterObject = new ENAttachment2ServicesKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAttachment2ServicesKind. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAttachment2ServicesKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAttachment2ServicesKind. Получить объект из списка */
	public ENAttachment2ServicesKindShort getShortObject(int code) {
		try {
			ENAttachment2ServicesKindDAO objectDAO = new ENAttachment2ServicesKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAttachment2ServicesKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
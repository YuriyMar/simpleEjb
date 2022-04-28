
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.SCSeal2WorkOrderBytKindDAO;
import com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind;
import com.ksoe.energynet.valueobject.lists.SCSeal2WorkOrderBytKindShortList;
import com.ksoe.energynet.valueobject.brief.SCSeal2WorkOrderBytKindShort;
import com.ksoe.energynet.valueobject.filter.SCSeal2WorkOrderBytKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for SCSeal2WorkOrderBytKind;
 *
 */


public abstract class SCSeal2WorkOrderBytKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public SCSeal2WorkOrderBytKindControllerEJBGen() {
	}

	/* SCSeal2WorkOrderBytKind. Добавить */
	public int add(SCSeal2WorkOrderBytKind object) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2WorkOrderBytKind. Удалить */
	public void remove(int code) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* SCSeal2WorkOrderBytKind. Изменить */
	public void save(SCSeal2WorkOrderBytKind object) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2WorkOrderBytKind. Получить объект */
	public SCSeal2WorkOrderBytKind getObject(int code) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2WorkOrderBytKind. Получить полный список */
	public SCSeal2WorkOrderBytKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* SCSeal2WorkOrderBytKind. Получить список по фильтру */
	public SCSeal2WorkOrderBytKindShortList getFilteredList(
			SCSeal2WorkOrderBytKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* SCSeal2WorkOrderBytKind. Получить список для просмотра */
	public SCSeal2WorkOrderBytKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* SCSeal2WorkOrderBytKind. Получить список для просмотра по фильтру */
	public SCSeal2WorkOrderBytKindShortList getScrollableFilteredList(
			SCSeal2WorkOrderBytKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2WorkOrderBytKind. Получить список для просмотра по условию */
	public SCSeal2WorkOrderBytKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		SCSeal2WorkOrderBytKindFilter filterObject = new SCSeal2WorkOrderBytKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* SCSeal2WorkOrderBytKind. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			SCSeal2WorkOrderBytKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* SCSeal2WorkOrderBytKind. Получить объект из списка */
	public SCSeal2WorkOrderBytKindShort getShortObject(int code) {
		try {
			SCSeal2WorkOrderBytKindDAO objectDAO = new SCSeal2WorkOrderBytKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.SCSeal2WorkOrderBytKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
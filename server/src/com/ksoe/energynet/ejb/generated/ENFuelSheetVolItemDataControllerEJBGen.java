
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENFuelSheetVolItemDataDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENFuelSheetVolItemData;
import com.ksoe.energynet.valueobject.brief.ENFuelSheetVolItemDataShort;
import com.ksoe.energynet.valueobject.filter.ENFuelSheetVolItemDataFilter;
import com.ksoe.energynet.valueobject.lists.ENFuelSheetVolItemDataShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENFuelSheetVolItemData;
 *
 */


public abstract class ENFuelSheetVolItemDataControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENFuelSheetVolItemDataControllerEJBGen() {
	}

	/* ENFuelSheetVolItemData. Добавить */
	public int add(ENFuelSheetVolItemData object) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolItemData. Удалить */
	public void remove(int code) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENFuelSheetVolItemData. Изменить */
	public void save(ENFuelSheetVolItemData object) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolItemData. Получить объект */
	public ENFuelSheetVolItemData getObject(int code) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolItemData. Получить полный список */
	public ENFuelSheetVolItemDataShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENFuelSheetVolItemData. Получить список по фильтру */
	public ENFuelSheetVolItemDataShortList getFilteredList(
			ENFuelSheetVolItemDataFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENFuelSheetVolItemData. Получить список для просмотра */
	public ENFuelSheetVolItemDataShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENFuelSheetVolItemData. Получить список для просмотра по фильтру */
	public ENFuelSheetVolItemDataShortList getScrollableFilteredList(
			ENFuelSheetVolItemDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolItemData. Получить список для просмотра по условию */
	public ENFuelSheetVolItemDataShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENFuelSheetVolItemDataFilter filterObject = new ENFuelSheetVolItemDataFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENFuelSheetVolItemData. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENFuelSheetVolItemDataFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENFuelSheetVolItemData. Получить объект из списка */
	public ENFuelSheetVolItemDataShort getShortObject(int code) {
		try {
			ENFuelSheetVolItemDataDAO objectDAO = new ENFuelSheetVolItemDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENFuelSheetVolItemData%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
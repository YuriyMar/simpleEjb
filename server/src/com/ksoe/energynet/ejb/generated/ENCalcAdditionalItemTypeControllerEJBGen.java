
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENCalcAdditionalItemTypeDAO;
import com.ksoe.energynet.valueobject.ENCalcAdditionalItemType;
import com.ksoe.energynet.valueobject.lists.ENCalcAdditionalItemTypeShortList;
import com.ksoe.energynet.valueobject.brief.ENCalcAdditionalItemTypeShort;
import com.ksoe.energynet.valueobject.filter.ENCalcAdditionalItemTypeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENCalcAdditionalItemType;
 *
 */


public abstract class ENCalcAdditionalItemTypeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENCalcAdditionalItemTypeControllerEJBGen() {
	}

	/* ENCalcAdditionalItemType. Добавить */
	public int add(ENCalcAdditionalItemType object) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcAdditionalItemType. Удалить */
	public void remove(int code) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENCalcAdditionalItemType. Изменить */
	public void save(ENCalcAdditionalItemType object) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcAdditionalItemType. Получить объект */
	public ENCalcAdditionalItemType getObject(int code) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcAdditionalItemType. Получить полный список */
	public ENCalcAdditionalItemTypeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENCalcAdditionalItemType. Получить список по фильтру */
	public ENCalcAdditionalItemTypeShortList getFilteredList(
			ENCalcAdditionalItemTypeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENCalcAdditionalItemType. Получить список для просмотра */
	public ENCalcAdditionalItemTypeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENCalcAdditionalItemType. Получить список для просмотра по фильтру */
	public ENCalcAdditionalItemTypeShortList getScrollableFilteredList(
			ENCalcAdditionalItemTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcAdditionalItemType. Получить список для просмотра по условию */
	public ENCalcAdditionalItemTypeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENCalcAdditionalItemTypeFilter filterObject = new ENCalcAdditionalItemTypeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENCalcAdditionalItemType. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENCalcAdditionalItemTypeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENCalcAdditionalItemType. Получить объект из списка */
	public ENCalcAdditionalItemTypeShort getShortObject(int code) {
		try {
			ENCalcAdditionalItemTypeDAO objectDAO = new ENCalcAdditionalItemTypeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENCalcAdditionalItemType%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
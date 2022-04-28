
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActPostingTypeSumDAO;
import com.ksoe.energynet.valueobject.ENActPostingTypeSum;
import com.ksoe.energynet.valueobject.lists.ENActPostingTypeSumShortList;
import com.ksoe.energynet.valueobject.brief.ENActPostingTypeSumShort;
import com.ksoe.energynet.valueobject.filter.ENActPostingTypeSumFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActPostingTypeSum;
 *
 */


public abstract class ENActPostingTypeSumControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActPostingTypeSumControllerEJBGen() {
	}

	/* ENActPostingTypeSum. Добавить */
	public int add(ENActPostingTypeSum object) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingTypeSum. Удалить */
	public void remove(int code) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActPostingTypeSum. Изменить */
	public void save(ENActPostingTypeSum object) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingTypeSum. Получить объект */
	public ENActPostingTypeSum getObject(int code) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingTypeSum. Получить полный список */
	public ENActPostingTypeSumShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActPostingTypeSum. Получить список по фильтру */
	public ENActPostingTypeSumShortList getFilteredList(
			ENActPostingTypeSumFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActPostingTypeSum. Получить список для просмотра */
	public ENActPostingTypeSumShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActPostingTypeSum. Получить список для просмотра по фильтру */
	public ENActPostingTypeSumShortList getScrollableFilteredList(
			ENActPostingTypeSumFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingTypeSum. Получить список для просмотра по условию */
	public ENActPostingTypeSumShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActPostingTypeSumFilter filterObject = new ENActPostingTypeSumFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActPostingTypeSum. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActPostingTypeSumFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingTypeSum. Получить объект из списка */
	public ENActPostingTypeSumShort getShortObject(int code) {
		try {
			ENActPostingTypeSumDAO objectDAO = new ENActPostingTypeSumDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingTypeSum%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
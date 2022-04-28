
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActInvestType2DFDocDAO;
import com.ksoe.energynet.valueobject.ENActInvestType2DFDoc;
import com.ksoe.energynet.valueobject.lists.ENActInvestType2DFDocShortList;
import com.ksoe.energynet.valueobject.brief.ENActInvestType2DFDocShort;
import com.ksoe.energynet.valueobject.filter.ENActInvestType2DFDocFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActInvestType2DFDoc;
 *
 */


public abstract class ENActInvestType2DFDocControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActInvestType2DFDocControllerEJBGen() {
	}

	/* ENActInvestType2DFDoc. Добавить */
	public int add(ENActInvestType2DFDoc object) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvestType2DFDoc. Удалить */
	public void remove(int code) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActInvestType2DFDoc. Изменить */
	public void save(ENActInvestType2DFDoc object) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvestType2DFDoc. Получить объект */
	public ENActInvestType2DFDoc getObject(int code) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvestType2DFDoc. Получить полный список */
	public ENActInvestType2DFDocShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActInvestType2DFDoc. Получить список по фильтру */
	public ENActInvestType2DFDocShortList getFilteredList(
			ENActInvestType2DFDocFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActInvestType2DFDoc. Получить список для просмотра */
	public ENActInvestType2DFDocShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActInvestType2DFDoc. Получить список для просмотра по фильтру */
	public ENActInvestType2DFDocShortList getScrollableFilteredList(
			ENActInvestType2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvestType2DFDoc. Получить список для просмотра по условию */
	public ENActInvestType2DFDocShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActInvestType2DFDocFilter filterObject = new ENActInvestType2DFDocFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActInvestType2DFDoc. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActInvestType2DFDocFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActInvestType2DFDoc. Получить объект из списка */
	public ENActInvestType2DFDocShort getShortObject(int code) {
		try {
			ENActInvestType2DFDocDAO objectDAO = new ENActInvestType2DFDocDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActInvestType2DFDoc%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
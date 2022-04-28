
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActPostingFormDAO;
import com.ksoe.energynet.valueobject.ENActPostingForm;
import com.ksoe.energynet.valueobject.lists.ENActPostingFormShortList;
import com.ksoe.energynet.valueobject.brief.ENActPostingFormShort;
import com.ksoe.energynet.valueobject.filter.ENActPostingFormFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActPostingForm;
 *
 */


public abstract class ENActPostingFormControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActPostingFormControllerEJBGen() {
	}

	/* ENActPostingForm. Добавить */
	public int add(ENActPostingForm object) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActPostingForm%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingForm. Удалить */
	public void remove(int code) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActPostingForm%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActPostingForm. Изменить */
	public void save(ENActPostingForm object) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActPostingForm%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingForm. Получить объект */
	public ENActPostingForm getObject(int code) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingForm%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingForm. Получить полный список */
	public ENActPostingFormShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActPostingForm. Получить список по фильтру */
	public ENActPostingFormShortList getFilteredList(
			ENActPostingFormFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActPostingForm. Получить список для просмотра */
	public ENActPostingFormShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActPostingForm. Получить список для просмотра по фильтру */
	public ENActPostingFormShortList getScrollableFilteredList(
			ENActPostingFormFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActPostingForm%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingForm. Получить список для просмотра по условию */
	public ENActPostingFormShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActPostingFormFilter filterObject = new ENActPostingFormFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActPostingForm. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENActPostingFormFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActPostingForm%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingForm. Получить объект из списка */
	public ENActPostingFormShort getShortObject(int code) {
		try {
			ENActPostingFormDAO objectDAO = new ENActPostingFormDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingForm%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
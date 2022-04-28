
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENAct2SCUsageInputDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAct2SCUsageInput;
import com.ksoe.energynet.valueobject.brief.ENAct2SCUsageInputShort;
import com.ksoe.energynet.valueobject.filter.ENAct2SCUsageInputFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2SCUsageInputShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2SCUsageInput;
 *
 */


public abstract class ENAct2SCUsageInputControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2SCUsageInputControllerEJBGen() {
	}

	/* ENAct2SCUsageInput. Добавить */
	public int add(ENAct2SCUsageInput object) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2SCUsageInput. Удалить */
	public void remove(int code) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2SCUsageInput. Изменить */
	public void save(ENAct2SCUsageInput object) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2SCUsageInput. Получить объект */
	public ENAct2SCUsageInput getObject(int code) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2SCUsageInput. Получить полный список */
	public ENAct2SCUsageInputShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2SCUsageInput. Получить список по фильтру */
	public ENAct2SCUsageInputShortList getFilteredList(
			ENAct2SCUsageInputFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2SCUsageInput. Получить список для просмотра */
	public ENAct2SCUsageInputShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2SCUsageInput. Получить список для просмотра по фильтру */
	public ENAct2SCUsageInputShortList getScrollableFilteredList(
			ENAct2SCUsageInputFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2SCUsageInput. Получить список для просмотра по условию */
	public ENAct2SCUsageInputShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2SCUsageInputFilter filterObject = new ENAct2SCUsageInputFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2SCUsageInput. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2SCUsageInputFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2SCUsageInput. Получить объект из списка */
	public ENAct2SCUsageInputShort getShortObject(int code) {
		try {
			ENAct2SCUsageInputDAO objectDAO = new ENAct2SCUsageInputDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2SCUsageInput%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
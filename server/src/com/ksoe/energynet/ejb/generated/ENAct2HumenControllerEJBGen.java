
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2HumenDAO;
import com.ksoe.energynet.valueobject.ENAct2Humen;
import com.ksoe.energynet.valueobject.lists.ENAct2HumenShortList;
import com.ksoe.energynet.valueobject.brief.ENAct2HumenShort;
import com.ksoe.energynet.valueobject.filter.ENAct2HumenFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENAct2Humen;
 *
 */


public abstract class ENAct2HumenControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENAct2HumenControllerEJBGen() {
	}

	/* ENAct2Humen. Добавить */
	public int add(ENAct2Humen object) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENAct2Humen%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Humen. Удалить */
	public void remove(int code) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENAct2Humen%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENAct2Humen. Изменить */
	public void save(ENAct2Humen object) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENAct2Humen%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Humen. Получить объект */
	public ENAct2Humen getObject(int code) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2Humen%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Humen. Получить полный список */
	public ENAct2HumenShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENAct2Humen. Получить список по фильтру */
	public ENAct2HumenShortList getFilteredList(
			ENAct2HumenFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENAct2Humen. Получить список для просмотра */
	public ENAct2HumenShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENAct2Humen. Получить список для просмотра по фильтру */
	public ENAct2HumenShortList getScrollableFilteredList(
			ENAct2HumenFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENAct2Humen%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Humen. Получить список для просмотра по условию */
	public ENAct2HumenShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENAct2HumenFilter filterObject = new ENAct2HumenFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENAct2Humen. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENAct2HumenFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2Humen%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENAct2Humen. Получить объект из списка */
	public ENAct2HumenShort getShortObject(int code) {
		try {
			ENAct2HumenDAO objectDAO = new ENAct2HumenDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENAct2Humen%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
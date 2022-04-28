
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTCOValuesDAO;
import com.ksoe.energynet.valueobject.ENTCOValues;
import com.ksoe.energynet.valueobject.lists.ENTCOValuesShortList;
import com.ksoe.energynet.valueobject.brief.ENTCOValuesShort;
import com.ksoe.energynet.valueobject.filter.ENTCOValuesFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTCOValues;
 *
 */


public abstract class ENTCOValuesControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTCOValuesControllerEJBGen() {
	}

	/* ENTCOValues. Добавить */
	public int add(ENTCOValues object) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTCOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValues. Удалить */
	public void remove(int code) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTCOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTCOValues. Изменить */
	public void save(ENTCOValues object) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTCOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValues. Получить объект */
	public ENTCOValues getObject(int code) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTCOValues%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValues. Получить полный список */
	public ENTCOValuesShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTCOValues. Получить список по фильтру */
	public ENTCOValuesShortList getFilteredList(
			ENTCOValuesFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTCOValues. Получить список для просмотра */
	public ENTCOValuesShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTCOValues. Получить список для просмотра по фильтру */
	public ENTCOValuesShortList getScrollableFilteredList(
			ENTCOValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTCOValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValues. Получить список для просмотра по условию */
	public ENTCOValuesShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTCOValuesFilter filterObject = new ENTCOValuesFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTCOValues. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTCOValuesFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTCOValues%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTCOValues. Получить объект из списка */
	public ENTCOValuesShort getShortObject(int code) {
		try {
			ENTCOValuesDAO objectDAO = new ENTCOValuesDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTCOValues%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
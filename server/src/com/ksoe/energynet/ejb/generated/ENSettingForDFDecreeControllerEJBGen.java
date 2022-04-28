
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSettingForDFDecreeDAO;
import com.ksoe.energynet.valueobject.ENSettingForDFDecree;
import com.ksoe.energynet.valueobject.lists.ENSettingForDFDecreeShortList;
import com.ksoe.energynet.valueobject.brief.ENSettingForDFDecreeShort;
import com.ksoe.energynet.valueobject.filter.ENSettingForDFDecreeFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSettingForDFDecree;
 *
 */


public abstract class ENSettingForDFDecreeControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSettingForDFDecreeControllerEJBGen() {
	}

	/* ENSettingForDFDecree. Добавить */
	public int add(ENSettingForDFDecree object) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingForDFDecree. Удалить */
	public void remove(int code) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSettingForDFDecree. Изменить */
	public void save(ENSettingForDFDecree object) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingForDFDecree. Получить объект */
	public ENSettingForDFDecree getObject(int code) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingForDFDecree. Получить полный список */
	public ENSettingForDFDecreeShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSettingForDFDecree. Получить список по фильтру */
	public ENSettingForDFDecreeShortList getFilteredList(
			ENSettingForDFDecreeFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSettingForDFDecree. Получить список для просмотра */
	public ENSettingForDFDecreeShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSettingForDFDecree. Получить список для просмотра по фильтру */
	public ENSettingForDFDecreeShortList getScrollableFilteredList(
			ENSettingForDFDecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingForDFDecree. Получить список для просмотра по условию */
	public ENSettingForDFDecreeShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSettingForDFDecreeFilter filterObject = new ENSettingForDFDecreeFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSettingForDFDecree. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSettingForDFDecreeFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSettingForDFDecree. Получить объект из списка */
	public ENSettingForDFDecreeShort getShortObject(int code) {
		try {
			ENSettingForDFDecreeDAO objectDAO = new ENSettingForDFDecreeDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSettingForDFDecree%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
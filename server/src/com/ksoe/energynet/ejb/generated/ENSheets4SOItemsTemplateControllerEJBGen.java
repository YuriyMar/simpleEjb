
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSheets4SOItemsTemplateDAO;
import com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate;
import com.ksoe.energynet.valueobject.lists.ENSheets4SOItemsTemplateShortList;
import com.ksoe.energynet.valueobject.brief.ENSheets4SOItemsTemplateShort;
import com.ksoe.energynet.valueobject.filter.ENSheets4SOItemsTemplateFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSheets4SOItemsTemplate;
 *
 */


public abstract class ENSheets4SOItemsTemplateControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSheets4SOItemsTemplateControllerEJBGen() {
	}

	/* ENSheets4SOItemsTemplate. Добавить */
	public int add(ENSheets4SOItemsTemplate object) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItemsTemplate. Удалить */
	public void remove(int code) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSheets4SOItemsTemplate. Изменить */
	public void save(ENSheets4SOItemsTemplate object) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItemsTemplate. Получить объект */
	public ENSheets4SOItemsTemplate getObject(int code) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItemsTemplate. Получить полный список */
	public ENSheets4SOItemsTemplateShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSheets4SOItemsTemplate. Получить список по фильтру */
	public ENSheets4SOItemsTemplateShortList getFilteredList(
			ENSheets4SOItemsTemplateFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSheets4SOItemsTemplate. Получить список для просмотра */
	public ENSheets4SOItemsTemplateShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSheets4SOItemsTemplate. Получить список для просмотра по фильтру */
	public ENSheets4SOItemsTemplateShortList getScrollableFilteredList(
			ENSheets4SOItemsTemplateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItemsTemplate. Получить список для просмотра по условию */
	public ENSheets4SOItemsTemplateShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSheets4SOItemsTemplateFilter filterObject = new ENSheets4SOItemsTemplateFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSheets4SOItemsTemplate. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSheets4SOItemsTemplateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSheets4SOItemsTemplate. Получить объект из списка */
	public ENSheets4SOItemsTemplateShort getShortObject(int code) {
		try {
			ENSheets4SOItemsTemplateDAO objectDAO = new ENSheets4SOItemsTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSheets4SOItemsTemplate%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
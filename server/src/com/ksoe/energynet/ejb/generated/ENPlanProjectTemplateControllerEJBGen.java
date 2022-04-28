
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENPlanProjectTemplateDAO;
import com.ksoe.energynet.valueobject.ENPlanProjectTemplate;
import com.ksoe.energynet.valueobject.lists.ENPlanProjectTemplateShortList;
import com.ksoe.energynet.valueobject.brief.ENPlanProjectTemplateShort;
import com.ksoe.energynet.valueobject.filter.ENPlanProjectTemplateFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENPlanProjectTemplate;
 *
 */


public abstract class ENPlanProjectTemplateControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENPlanProjectTemplateControllerEJBGen() {
	}

	/* ENPlanProjectTemplate. Добавить */
	public int add(ENPlanProjectTemplate object) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectTemplate. Удалить */
	public void remove(int code) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanProjectTemplate. Изменить */
	public void save(ENPlanProjectTemplate object) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectTemplate. Получить объект */
	public ENPlanProjectTemplate getObject(int code) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectTemplate. Получить полный список */
	public ENPlanProjectTemplateShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENPlanProjectTemplate. Получить список по фильтру */
	public ENPlanProjectTemplateShortList getFilteredList(
			ENPlanProjectTemplateFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENPlanProjectTemplate. Получить список для просмотра */
	public ENPlanProjectTemplateShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENPlanProjectTemplate. Получить список для просмотра по фильтру */
	public ENPlanProjectTemplateShortList getScrollableFilteredList(
			ENPlanProjectTemplateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectTemplate. Получить список для просмотра по условию */
	public ENPlanProjectTemplateShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENPlanProjectTemplateFilter filterObject = new ENPlanProjectTemplateFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENPlanProjectTemplate. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENPlanProjectTemplateFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanProjectTemplate. Получить объект из списка */
	public ENPlanProjectTemplateShort getShortObject(int code) {
		try {
			ENPlanProjectTemplateDAO objectDAO = new ENPlanProjectTemplateDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanProjectTemplate%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
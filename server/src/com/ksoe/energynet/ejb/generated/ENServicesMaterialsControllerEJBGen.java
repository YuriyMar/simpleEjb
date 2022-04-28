
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENServicesMaterialsDAO;
import com.ksoe.energynet.valueobject.ENServicesMaterials;
import com.ksoe.energynet.valueobject.lists.ENServicesMaterialsShortList;
import com.ksoe.energynet.valueobject.brief.ENServicesMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENServicesMaterialsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENServicesMaterials;
 *
 */


public abstract class ENServicesMaterialsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENServicesMaterialsControllerEJBGen() {
	}

	/* ENServicesMaterials. Добавить */
	public int add(ENServicesMaterials object) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENServicesMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesMaterials. Удалить */
	public void remove(int code) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENServicesMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENServicesMaterials. Изменить */
	public void save(ENServicesMaterials object) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENServicesMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesMaterials. Получить объект */
	public ENServicesMaterials getObject(int code) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesMaterials%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesMaterials. Получить полный список */
	public ENServicesMaterialsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENServicesMaterials. Получить список по фильтру */
	public ENServicesMaterialsShortList getFilteredList(
			ENServicesMaterialsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENServicesMaterials. Получить список для просмотра */
	public ENServicesMaterialsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENServicesMaterials. Получить список для просмотра по фильтру */
	public ENServicesMaterialsShortList getScrollableFilteredList(
			ENServicesMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENServicesMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesMaterials. Получить список для просмотра по условию */
	public ENServicesMaterialsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENServicesMaterialsFilter filterObject = new ENServicesMaterialsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENServicesMaterials. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENServicesMaterialsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesMaterials%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENServicesMaterials. Получить объект из списка */
	public ENServicesMaterialsShort getShortObject(int code) {
		try {
			ENServicesMaterialsDAO objectDAO = new ENServicesMaterialsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENServicesMaterials%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
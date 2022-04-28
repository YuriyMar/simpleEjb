
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTechAgr2SOKindDAO;
import com.ksoe.energynet.valueobject.ENTechAgr2SOKind;
import com.ksoe.energynet.valueobject.lists.ENTechAgr2SOKindShortList;
import com.ksoe.energynet.valueobject.brief.ENTechAgr2SOKindShort;
import com.ksoe.energynet.valueobject.filter.ENTechAgr2SOKindFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTechAgr2SOKind;
 *
 */


public abstract class ENTechAgr2SOKindControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTechAgr2SOKindControllerEJBGen() {
	}

	/* ENTechAgr2SOKind. Добавить */
	public int add(ENTechAgr2SOKind object) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTechAgr2SOKind. Удалить */
	public void remove(int code) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTechAgr2SOKind. Изменить */
	public void save(ENTechAgr2SOKind object) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTechAgr2SOKind. Получить объект */
	public ENTechAgr2SOKind getObject(int code) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTechAgr2SOKind. Получить полный список */
	public ENTechAgr2SOKindShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTechAgr2SOKind. Получить список по фильтру */
	public ENTechAgr2SOKindShortList getFilteredList(
			ENTechAgr2SOKindFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTechAgr2SOKind. Получить список для просмотра */
	public ENTechAgr2SOKindShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTechAgr2SOKind. Получить список для просмотра по фильтру */
	public ENTechAgr2SOKindShortList getScrollableFilteredList(
			ENTechAgr2SOKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTechAgr2SOKind. Получить список для просмотра по условию */
	public ENTechAgr2SOKindShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTechAgr2SOKindFilter filterObject = new ENTechAgr2SOKindFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTechAgr2SOKind. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTechAgr2SOKindFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTechAgr2SOKind. Получить объект из списка */
	public ENTechAgr2SOKindShort getShortObject(int code) {
		try {
			ENTechAgr2SOKindDAO objectDAO = new ENTechAgr2SOKindDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTechAgr2SOKind%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
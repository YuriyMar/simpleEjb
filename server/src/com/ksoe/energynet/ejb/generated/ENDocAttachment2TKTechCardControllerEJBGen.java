
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENDocAttachment2TKTechCardDAO;
import com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard;
import com.ksoe.energynet.valueobject.lists.ENDocAttachment2TKTechCardShortList;
import com.ksoe.energynet.valueobject.brief.ENDocAttachment2TKTechCardShort;
import com.ksoe.energynet.valueobject.filter.ENDocAttachment2TKTechCardFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENDocAttachment2TKTechCard;
 *
 */


public abstract class ENDocAttachment2TKTechCardControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENDocAttachment2TKTechCardControllerEJBGen() {
	}

	/* ENDocAttachment2TKTechCard. Добавить */
	public int add(ENDocAttachment2TKTechCard object) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKTechCard. Удалить */
	public void remove(int code) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENDocAttachment2TKTechCard. Изменить */
	public void save(ENDocAttachment2TKTechCard object) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKTechCard. Получить объект */
	public ENDocAttachment2TKTechCard getObject(int code) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKTechCard. Получить полный список */
	public ENDocAttachment2TKTechCardShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENDocAttachment2TKTechCard. Получить список по фильтру */
	public ENDocAttachment2TKTechCardShortList getFilteredList(
			ENDocAttachment2TKTechCardFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENDocAttachment2TKTechCard. Получить список для просмотра */
	public ENDocAttachment2TKTechCardShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENDocAttachment2TKTechCard. Получить список для просмотра по фильтру */
	public ENDocAttachment2TKTechCardShortList getScrollableFilteredList(
			ENDocAttachment2TKTechCardFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKTechCard. Получить список для просмотра по условию */
	public ENDocAttachment2TKTechCardShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENDocAttachment2TKTechCardFilter filterObject = new ENDocAttachment2TKTechCardFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENDocAttachment2TKTechCard. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENDocAttachment2TKTechCardFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENDocAttachment2TKTechCard. Получить объект из списка */
	public ENDocAttachment2TKTechCardShort getShortObject(int code) {
		try {
			ENDocAttachment2TKTechCardDAO objectDAO = new ENDocAttachment2TKTechCardDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENDocAttachment2TKTechCard%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
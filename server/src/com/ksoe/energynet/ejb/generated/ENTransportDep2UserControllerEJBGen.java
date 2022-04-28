
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENTransportDep2UserDAO;
import com.ksoe.energynet.valueobject.ENTransportDep2User;
import com.ksoe.energynet.valueobject.lists.ENTransportDep2UserShortList;
import com.ksoe.energynet.valueobject.brief.ENTransportDep2UserShort;
import com.ksoe.energynet.valueobject.filter.ENTransportDep2UserFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENTransportDep2User;
 *
 */


public abstract class ENTransportDep2UserControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENTransportDep2UserControllerEJBGen() {
	}

	/* ENTransportDep2User. Добавить */
	public int add(ENTransportDep2User object) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENTransportDep2User%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportDep2User. Удалить */
	public void remove(int code) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENTransportDep2User%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENTransportDep2User. Изменить */
	public void save(ENTransportDep2User object) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENTransportDep2User%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportDep2User. Получить объект */
	public ENTransportDep2User getObject(int code) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransportDep2User%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportDep2User. Получить полный список */
	public ENTransportDep2UserShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENTransportDep2User. Получить список по фильтру */
	public ENTransportDep2UserShortList getFilteredList(
			ENTransportDep2UserFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENTransportDep2User. Получить список для просмотра */
	public ENTransportDep2UserShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENTransportDep2User. Получить список для просмотра по фильтру */
	public ENTransportDep2UserShortList getScrollableFilteredList(
			ENTransportDep2UserFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENTransportDep2User%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportDep2User. Получить список для просмотра по условию */
	public ENTransportDep2UserShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENTransportDep2UserFilter filterObject = new ENTransportDep2UserFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENTransportDep2User. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENTransportDep2UserFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportDep2User%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENTransportDep2User. Получить объект из списка */
	public ENTransportDep2UserShort getShortObject(int code) {
		try {
			ENTransportDep2UserDAO objectDAO = new ENTransportDep2UserDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENTransportDep2User%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2016 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENSealColorsDAO;
import com.ksoe.energynet.valueobject.ENSealColors;
import com.ksoe.energynet.valueobject.lists.ENSealColorsShortList;
import com.ksoe.energynet.valueobject.brief.ENSealColorsShort;
import com.ksoe.energynet.valueobject.filter.ENSealColorsFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENSealColors;
 *
 */


public abstract class ENSealColorsControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENSealColorsControllerEJBGen() {
	}

	/* ENSealColors. Добавить */
	public int add(ENSealColors object) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENSealColors%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealColors. Удалить */
	public void remove(int code) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENSealColors%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENSealColors. Изменить */
	public void save(ENSealColors object) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENSealColors%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealColors. Получить объект */
	public ENSealColors getObject(int code) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSealColors%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealColors. Получить полный список */
	public ENSealColorsShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENSealColors. Получить список по фильтру */
	public ENSealColorsShortList getFilteredList(
			ENSealColorsFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENSealColors. Получить список для просмотра */
	public ENSealColorsShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENSealColors. Получить список для просмотра по фильтру */
	public ENSealColorsShortList getScrollableFilteredList(
			ENSealColorsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENSealColors%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealColors. Получить список для просмотра по условию */
	public ENSealColorsShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENSealColorsFilter filterObject = new ENSealColorsFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENSealColors. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			ENSealColorsFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENSealColors%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENSealColors. Получить объект из списка */
	public ENSealColorsShort getShortObject(int code) {
		try {
			ENSealColorsDAO objectDAO = new ENSealColorsDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENSealColors%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
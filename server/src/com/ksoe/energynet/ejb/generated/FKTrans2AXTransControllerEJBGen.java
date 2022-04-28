
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2017 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FKTrans2AXTransDAO;
import com.ksoe.energynet.valueobject.FKTrans2AXTrans;
import com.ksoe.energynet.valueobject.lists.FKTrans2AXTransShortList;
import com.ksoe.energynet.valueobject.brief.FKTrans2AXTransShort;
import com.ksoe.energynet.valueobject.filter.FKTrans2AXTransFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for FKTrans2AXTrans;
 *
 */


public abstract class FKTrans2AXTransControllerEJBGen extends
		GenericSessionStatelessBean {
	public FKTrans2AXTransControllerEJBGen() {
	}

	/* FKTrans2AXTrans. Добавить */
	public int add(FKTrans2AXTrans object) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTrans. Удалить */
	public void remove(int code) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* FKTrans2AXTrans. Изменить */
	public void save(FKTrans2AXTrans object) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTrans. Получить объект */
	public FKTrans2AXTrans getObject(int code) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTrans. Получить полный список */
	public FKTrans2AXTransShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* FKTrans2AXTrans. Получить список по фильтру */
	public FKTrans2AXTransShortList getFilteredList(
			FKTrans2AXTransFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* FKTrans2AXTrans. Получить список для просмотра */
	public FKTrans2AXTransShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* FKTrans2AXTrans. Получить список для просмотра по фильтру */
	public FKTrans2AXTransShortList getScrollableFilteredList(
			FKTrans2AXTransFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTrans. Получить список для просмотра по условию */
	public FKTrans2AXTransShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		FKTrans2AXTransFilter filterObject = new FKTrans2AXTransFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* FKTrans2AXTrans. Получить список ПК-кодов по фильтру */
	public int[] getScrollableFilteredCodeArray(
			FKTrans2AXTransFilter filterObject, int fromPosition,
			int quantity) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* FKTrans2AXTrans. Получить объект из списка */
	public FKTrans2AXTransShort getShortObject(int code) {
		try {
			FKTrans2AXTransDAO objectDAO = new FKTrans2AXTransDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.FKTrans2AXTrans%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
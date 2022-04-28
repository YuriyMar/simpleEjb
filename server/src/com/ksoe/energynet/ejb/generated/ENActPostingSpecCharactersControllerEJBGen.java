
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2020 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENActPostingSpecCharactersDAO;
import com.ksoe.energynet.valueobject.ENActPostingSpecCharacters;
import com.ksoe.energynet.valueobject.lists.ENActPostingSpecCharactersShortList;
import com.ksoe.energynet.valueobject.brief.ENActPostingSpecCharactersShort;
import com.ksoe.energynet.valueobject.filter.ENActPostingSpecCharactersFilter;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENActPostingSpecCharacters;
 *
 */


public abstract class ENActPostingSpecCharactersControllerEJBGen extends
		GenericSessionStatelessBean {
	public ENActPostingSpecCharactersControllerEJBGen() {
	}

	/* ENActPostingSpecCharacters. �������� */
	public int add(ENActPostingSpecCharacters object) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingSpecCharacters. ������� */
	public void remove(int code) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENActPostingSpecCharacters. �������� */
	public void save(ENActPostingSpecCharacters object) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingSpecCharacters. �������� ������ */
	public ENActPostingSpecCharacters getObject(int code) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingSpecCharacters. �������� ������ ������ */
	public ENActPostingSpecCharactersShortList getList() {
		return getScrollableFilteredList(null, 0, -1);
	}

	/* ENActPostingSpecCharacters. �������� ������ �� ������� */
	public ENActPostingSpecCharactersShortList getFilteredList(
			ENActPostingSpecCharactersFilter filterObject) {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	/* ENActPostingSpecCharacters. �������� ������ ��� ��������� */
	public ENActPostingSpecCharactersShortList getScrollableList(int fromPosition,
			int quantity) {
		return getScrollableFilteredList(null, fromPosition, quantity);
	}

	/* ENActPostingSpecCharacters. �������� ������ ��� ��������� �� ������� */
	public ENActPostingSpecCharactersShortList getScrollableFilteredList(
			ENActPostingSpecCharactersFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingSpecCharacters. �������� ������ ��� ��������� �� ������� */
	public ENActPostingSpecCharactersShortList getScrollableListByCondition(
			String aCondition, int fromPosition, int quantity) {
		ENActPostingSpecCharactersFilter filterObject = new ENActPostingSpecCharactersFilter();
		filterObject.conditionSQL = aCondition;
		return getScrollableFilteredList(filterObject, fromPosition, quantity);
	}

	/* ENActPostingSpecCharacters. �������� ������ ��-����� �� ������� */
	public int[] getScrollableFilteredCodeArray(
			ENActPostingSpecCharactersFilter filterObject, int fromPosition,
			int quantity) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
					quantity);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get code array of {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENActPostingSpecCharacters. �������� ������ �� ������ */
	public ENActPostingSpecCharactersShort getShortObject(int code) {
		try {
			ENActPostingSpecCharactersDAO objectDAO = new ENActPostingSpecCharactersDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getShortObject(code);

		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENActPostingSpecCharacters%} ShortObject.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

}
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENUnitedGroupConnectionsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENUnitedGroupConnections;
import com.ksoe.energynet.valueobject.brief.ENUnitedGroupConnectionsShort;
import com.ksoe.energynet.valueobject.filter.ENUnitedGroupConnectionsFilter;
import com.ksoe.energynet.valueobject.lists.ENUnitedGroupConnectionsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENUnitedGroupConnections;
 *
 */

public abstract class ENUnitedGroupConnectionsControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENUnitedGroupConnectionsControllerEJBGen() {
    }

    /* ENUnitedGroupConnections. Добавить */
    public int add(ENUnitedGroupConnections object) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Удалить */
    public void remove(int code) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Изменить */
    public void save(ENUnitedGroupConnections object) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Получить объект */
    public ENUnitedGroupConnections getObject(int code) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Получить полный список */
    public ENUnitedGroupConnectionsShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENUnitedGroupConnections. Получить список по фильтру */
    public ENUnitedGroupConnectionsShortList getFilteredList(
            ENUnitedGroupConnectionsFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENUnitedGroupConnections. Получить список для просмотра */
    public ENUnitedGroupConnectionsShortList getScrollableList(
            int fromPosition, int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENUnitedGroupConnections. Получить список для просмотра по фильтру */
    public ENUnitedGroupConnectionsShortList getScrollableFilteredList(
            ENUnitedGroupConnectionsFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Получить список для просмотра по условию */
    public ENUnitedGroupConnectionsShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENUnitedGroupConnectionsFilter filterObject = new ENUnitedGroupConnectionsFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENUnitedGroupConnections. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENUnitedGroupConnectionsFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroupConnections. Получить объект из списка */
    public ENUnitedGroupConnectionsShort getShortObject(int code) {
        try {
            ENUnitedGroupConnectionsDAO objectDAO = new ENUnitedGroupConnectionsDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENUnitedGroupConnections%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
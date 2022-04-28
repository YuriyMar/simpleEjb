
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENEstimateHistoryTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateHistoryType;
import com.ksoe.energynet.valueobject.brief.ENEstimateHistoryTypeShort;
import com.ksoe.energynet.valueobject.filter.ENEstimateHistoryTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateHistoryTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENEstimateHistoryType;
 *
 */


public abstract class ENEstimateHistoryTypeControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENEstimateHistoryTypeControllerEJBGen() {
    }

    /* ENEstimateHistoryType. Добавить */
    public int add(ENEstimateHistoryType object) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Удалить */
    public void remove(int code) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Изменить */
    public void save(ENEstimateHistoryType object) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Получить объект */
    public ENEstimateHistoryType getObject(int code) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Получить полный список */
    public ENEstimateHistoryTypeShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENEstimateHistoryType. Получить список по фильтру */
    public ENEstimateHistoryTypeShortList getFilteredList(
            ENEstimateHistoryTypeFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENEstimateHistoryType. Получить список для просмотра */
    public ENEstimateHistoryTypeShortList getScrollableList(int fromPosition,
            int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENEstimateHistoryType. Получить список для просмотра по фильтру */
    public ENEstimateHistoryTypeShortList getScrollableFilteredList(
            ENEstimateHistoryTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Получить список для просмотра по условию */
    public ENEstimateHistoryTypeShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENEstimateHistoryTypeFilter filterObject = new ENEstimateHistoryTypeFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENEstimateHistoryType. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENEstimateHistoryTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistoryType. Получить объект из списка */
    public ENEstimateHistoryTypeShort getShortObject(int code) {
        try {
            ENEstimateHistoryTypeDAO objectDAO = new ENEstimateHistoryTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENEstimateHistoryType%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
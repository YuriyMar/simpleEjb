
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENEstimateHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateHistory;
import com.ksoe.energynet.valueobject.brief.ENEstimateHistoryShort;
import com.ksoe.energynet.valueobject.filter.ENEstimateHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENEstimateHistory;
 *
 */


public abstract class ENEstimateHistoryControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENEstimateHistoryControllerEJBGen() {
    }

    /* ENEstimateHistory. Добавить */
    public int add(ENEstimateHistory object) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENEstimateHistory%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Удалить */
    public void remove(int code) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENEstimateHistory%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Изменить */
    public void save(ENEstimateHistory object) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENEstimateHistory%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Получить объект */
    public ENEstimateHistory getObject(int code) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENEstimateHistory%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Получить полный список */
    public ENEstimateHistoryShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENEstimateHistory. Получить список по фильтру */
    public ENEstimateHistoryShortList getFilteredList(
            ENEstimateHistoryFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENEstimateHistory. Получить список для просмотра */
    public ENEstimateHistoryShortList getScrollableList(int fromPosition,
            int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENEstimateHistory. Получить список для просмотра по фильтру */
    public ENEstimateHistoryShortList getScrollableFilteredList(
            ENEstimateHistoryFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateHistory%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Получить список для просмотра по условию */
    public ENEstimateHistoryShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENEstimateHistoryFilter filterObject = new ENEstimateHistoryFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENEstimateHistory. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENEstimateHistoryFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENEstimateHistory%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENEstimateHistory. Получить объект из списка */
    public ENEstimateHistoryShort getShortObject(int code) {
        try {
            ENEstimateHistoryDAO objectDAO = new ENEstimateHistoryDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENEstimateHistory%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
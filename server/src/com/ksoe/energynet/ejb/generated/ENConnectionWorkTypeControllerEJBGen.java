//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENConnectionWorkTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENConnectionWorkType;
import com.ksoe.energynet.valueobject.brief.ENConnectionWorkTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionWorkTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENConnectionWorkTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENConnectionWorkType;
 *
 */

public abstract class ENConnectionWorkTypeControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENConnectionWorkTypeControllerEJBGen() {
    }

    /* ENConnectionWorkType. Добавить */
    public int add(ENConnectionWorkType object) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Удалить */
    public void remove(int code) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Изменить */
    public void save(ENConnectionWorkType object) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Получить объект */
    public ENConnectionWorkType getObject(int code) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Получить полный список */
    public ENConnectionWorkTypeShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENConnectionWorkType. Получить список по фильтру */
    public ENConnectionWorkTypeShortList getFilteredList(
            ENConnectionWorkTypeFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENConnectionWorkType. Получить список для просмотра */
    public ENConnectionWorkTypeShortList getScrollableList(int fromPosition,
            int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENConnectionWorkType. Получить список для просмотра по фильтру */
    public ENConnectionWorkTypeShortList getScrollableFilteredList(
            ENConnectionWorkTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Получить список для просмотра по условию */
    public ENConnectionWorkTypeShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENConnectionWorkTypeFilter filterObject = new ENConnectionWorkTypeFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENConnectionWorkType. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENConnectionWorkTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionWorkType. Получить объект из списка */
    public ENConnectionWorkTypeShort getShortObject(int code) {
        try {
            ENConnectionWorkTypeDAO objectDAO = new ENConnectionWorkTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENConnectionWorkType%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
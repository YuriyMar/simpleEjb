
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.energynet.dataminer.ENIPItem2PlanDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENIPItem2Plan;
import com.ksoe.energynet.valueobject.brief.ENIPItem2PlanShort;
import com.ksoe.energynet.valueobject.filter.ENIPItem2PlanFilter;
import com.ksoe.energynet.valueobject.lists.ENIPItem2PlanShortList;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;

/**
 * EJB controller for ENIPItem2Plan;
 *
 */


public abstract class ENIPItem2PlanControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENIPItem2PlanControllerEJBGen() {
    }

    /* ENIPItem2Plan. Добавить */
    public int add(ENIPItem2Plan object) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            object.userGen = getUserProfile().userName;
            object.dateEdit = new Date();

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Удалить */
    public void remove(int code) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Изменить */
    public void save(ENIPItem2Plan object) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            object.userGen = getUserProfile().userName;
            object.dateEdit = new Date();

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Получить объект */
    public ENIPItem2Plan getObject(int code) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Получить полный список */
    public ENIPItem2PlanShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENIPItem2Plan. Получить список по фильтру */
    public ENIPItem2PlanShortList getFilteredList(
            ENIPItem2PlanFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENIPItem2Plan. Получить список для просмотра */
    public ENIPItem2PlanShortList getScrollableList(int fromPosition,
            int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENIPItem2Plan. Получить список для просмотра по фильтру */
    public ENIPItem2PlanShortList getScrollableFilteredList(
            ENIPItem2PlanFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Получить список для просмотра по условию */
    public ENIPItem2PlanShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENIPItem2PlanFilter filterObject = new ENIPItem2PlanFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENIPItem2Plan. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENIPItem2PlanFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENIPItem2Plan. Получить объект из списка */
    public ENIPItem2PlanShort getShortObject(int code) {
        try {
            ENIPItem2PlanDAO objectDAO = new ENIPItem2PlanDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new SystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENIPItem2Plan%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
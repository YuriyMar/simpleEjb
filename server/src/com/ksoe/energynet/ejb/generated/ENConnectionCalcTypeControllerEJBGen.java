
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;


import com.ksoe.energynet.dataminer.ENConnectionCalcTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENConnectionCalcType;
import com.ksoe.energynet.valueobject.brief.ENConnectionCalcTypeShort;
import com.ksoe.energynet.valueobject.filter.ENConnectionCalcTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENConnectionCalcTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENConnectionCalcType;
 *
 */


public abstract class ENConnectionCalcTypeControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENConnectionCalcTypeControllerEJBGen() {
    }

    /* ENConnectionCalcType. Добавить */
    public int add(ENConnectionCalcType object) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Удалить */
    public void remove(int code) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Изменить */
    public void save(ENConnectionCalcType object) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Получить объект */
    public ENConnectionCalcType getObject(int code) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Получить полный список */
    public ENConnectionCalcTypeShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENConnectionCalcType. Получить список по фильтру */
    public ENConnectionCalcTypeShortList getFilteredList(
            ENConnectionCalcTypeFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENConnectionCalcType. Получить список для просмотра */
    public ENConnectionCalcTypeShortList getScrollableList(int fromPosition,
            int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENConnectionCalcType. Получить список для просмотра по фильтру */
    public ENConnectionCalcTypeShortList getScrollableFilteredList(
            ENConnectionCalcTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Получить список для просмотра по условию */
    public ENConnectionCalcTypeShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENConnectionCalcTypeFilter filterObject = new ENConnectionCalcTypeFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENConnectionCalcType. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENConnectionCalcTypeFilter filterObject, int fromPosition,
            int quantity) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENConnectionCalcType. Получить объект из списка */
    public ENConnectionCalcTypeShort getShortObject(int code) {
        try {
            ENConnectionCalcTypeDAO objectDAO = new ENConnectionCalcTypeDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENConnectionCalcType%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}
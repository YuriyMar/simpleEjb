//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENUnitedGroup2TechCondServicesDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices;
import com.ksoe.energynet.valueobject.brief.ENUnitedGroup2TechCondServicesShort;
import com.ksoe.energynet.valueobject.filter.ENUnitedGroup2TechCondServicesFilter;
import com.ksoe.energynet.valueobject.lists.ENUnitedGroup2TechCondServicesShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

/**
 * EJB controller for ENUnitedGroup2TechCondServices;
 *
 */

public abstract class ENUnitedGroup2TechCondServicesControllerEJBGen extends
        GenericSessionStatelessBean {
    public ENUnitedGroup2TechCondServicesControllerEJBGen() {
    }

    /* ENUnitedGroup2TechCondServices. Добавить */
    public int add(ENUnitedGroup2TechCondServices object) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.add(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't add {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Удалить */
    public void remove(int code) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.remove(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't remove {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Изменить */
    public void save(ENUnitedGroup2TechCondServices object) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            objectDAO.save(object);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Получить объект */
    public ENUnitedGroup2TechCondServices getObject(int code) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getObject(code);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Получить полный список */
    public ENUnitedGroup2TechCondServicesShortList getList() {
        return getScrollableFilteredList(null, 0, -1);
    }

    /* ENUnitedGroup2TechCondServices. Получить список по фильтру */
    public ENUnitedGroup2TechCondServicesShortList getFilteredList(
            ENUnitedGroup2TechCondServicesFilter filterObject) {
        return getScrollableFilteredList(filterObject, 0, -1);
    }

    /* ENUnitedGroup2TechCondServices. Получить список для просмотра */
    public ENUnitedGroup2TechCondServicesShortList getScrollableList(
            int fromPosition, int quantity) {
        return getScrollableFilteredList(null, fromPosition, quantity);
    }

    /* ENUnitedGroup2TechCondServices. Получить список для просмотра по фильтру */
    public ENUnitedGroup2TechCondServicesShortList getScrollableFilteredList(
            ENUnitedGroup2TechCondServicesFilter filterObject,
            int fromPosition, int quantity) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getScrollableFilteredList(filterObject,
                    fromPosition, quantity, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Получить список для просмотра по условию */
    public ENUnitedGroup2TechCondServicesShortList getScrollableListByCondition(
            String aCondition, int fromPosition, int quantity) {
        ENUnitedGroup2TechCondServicesFilter filterObject = new ENUnitedGroup2TechCondServicesFilter();
        filterObject.conditionSQL = aCondition;
        return getScrollableFilteredList(filterObject, fromPosition, quantity);
    }

    /* ENUnitedGroup2TechCondServices. Получить список ПК-кодов по фильтру */
    public int[] getScrollableFilteredCodeArray(
            ENUnitedGroup2TechCondServicesFilter filterObject,
            int fromPosition, int quantity) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getFilteredCodeArray(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get code array of {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /* ENUnitedGroup2TechCondServices. Получить объект из списка */
    public ENUnitedGroup2TechCondServicesShort getShortObject(int code) {
        try {
            ENUnitedGroup2TechCondServicesDAO objectDAO = new ENUnitedGroup2TechCondServicesDAO(
                    getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            return objectDAO.getShortObject(code);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get {%com.ksoe.energynet.valueobject.ENUnitedGroup2TechCondServices%} ShortObject.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENManagementDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENManagement;
import com.ksoe.energynet.valueobject.filter.ENManagementFilter;
import com.ksoe.energynet.valueobject.lists.ENManagementShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENManagement;  
  * 	
  */

public abstract class ENManagementControllerEJBGen extends GenericSessionStatelessBean
{
  public ENManagementControllerEJBGen() {}

  /*ENManagement. Добавить*/
  public int add(ENManagement object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManagementValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENManagement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManagement. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENManagement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENManagement. Изменить*/
  public void save(ENManagement object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManagementValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENManagement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManagement. Получить объект*/
  public ENManagement getObject(int code)
   {
    try
     {
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENManagement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManagement. Получить полный список*/
  public ENManagementShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENManagement. Получить список по фильтру*/
  public ENManagementShortList getFilteredList(ENManagementFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENManagement. Получить список для просмотра*/
  public ENManagementShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENManagement. Получить список для просмотра по фильтру*/
  public ENManagementShortList getScrollableFilteredList(ENManagementFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENManagement%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManagement. Получить список для просмотра по условию*/
  public ENManagementShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENManagementFilter filterObject = new ENManagementFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENManagement. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENManagementFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENManagementDAO objectDAO = new ENManagementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENManagement%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
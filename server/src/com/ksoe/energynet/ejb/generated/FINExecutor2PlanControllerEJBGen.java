
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.FINExecutor2PlanDAO;
import com.ksoe.energynet.valueobject.FINExecutor2Plan;
import com.ksoe.energynet.valueobject.lists.FINExecutor2PlanShortList;
import com.ksoe.energynet.valueobject.filter.FINExecutor2PlanFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for FINExecutor2Plan;  
  * 	
  */

public abstract class FINExecutor2PlanControllerEJBGen extends GenericSessionStatelessBean
{
  public FINExecutor2PlanControllerEJBGen() {}

  /*FINExecutor2Plan. Добавить*/
  public int add(FINExecutor2Plan object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINExecutor2PlanValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutor2Plan. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINExecutor2Plan. Изменить*/
  public void save(FINExecutor2Plan object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINExecutor2PlanValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutor2Plan. Получить объект*/
  public FINExecutor2Plan getObject(int code)
   {
    try
     {
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutor2Plan. Получить полный список*/
  public FINExecutor2PlanShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINExecutor2Plan. Получить список по фильтру*/
  public FINExecutor2PlanShortList getFilteredList(FINExecutor2PlanFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINExecutor2Plan. Получить список для просмотра*/
  public FINExecutor2PlanShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINExecutor2Plan. Получить список для просмотра по фильтру*/
  public FINExecutor2PlanShortList getScrollableFilteredList(FINExecutor2PlanFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutor2Plan. Получить список для просмотра по условию*/
  public FINExecutor2PlanShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINExecutor2PlanFilter filterObject = new FINExecutor2PlanFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINExecutor2Plan. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(FINExecutor2PlanFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINExecutor2PlanDAO objectDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINExecutor2Plan%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
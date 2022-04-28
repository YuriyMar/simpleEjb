
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.FINExecutorTypeDAO;
import com.ksoe.energynet.valueobject.FINExecutorType;
import com.ksoe.energynet.valueobject.lists.FINExecutorTypeShortList;
import com.ksoe.energynet.valueobject.filter.FINExecutorTypeFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for FINExecutorType;  
  * 	
  */

public abstract class FINExecutorTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public FINExecutorTypeControllerEJBGen() {}

  /*FINExecutorType. Добавить*/
  public int add(FINExecutorType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINExecutorTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINExecutorType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutorType. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINExecutorType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINExecutorType. Изменить*/
  public void save(FINExecutorType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINExecutorTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINExecutorType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutorType. Получить объект*/
  public FINExecutorType getObject(int code)
   {
    try
     {
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINExecutorType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutorType. Получить полный список*/
  public FINExecutorTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINExecutorType. Получить список по фильтру*/
  public FINExecutorTypeShortList getFilteredList(FINExecutorTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINExecutorType. Получить список для просмотра*/
  public FINExecutorTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINExecutorType. Получить список для просмотра по фильтру*/
  public FINExecutorTypeShortList getScrollableFilteredList(FINExecutorTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINExecutorType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINExecutorType. Получить список для просмотра по условию*/
  public FINExecutorTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINExecutorTypeFilter filterObject = new FINExecutorTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINExecutorType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(FINExecutorTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINExecutorTypeDAO objectDAO = new FINExecutorTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINExecutorType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
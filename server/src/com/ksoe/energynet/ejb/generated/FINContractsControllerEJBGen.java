
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.FINContractsDAO;
import com.ksoe.energynet.valueobject.FINContracts;
import com.ksoe.energynet.valueobject.lists.FINContractsShortList;
import com.ksoe.energynet.valueobject.filter.FINContractsFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for FINContracts;  
  * 	
  */

public abstract class FINContractsControllerEJBGen extends GenericSessionStatelessBean
{
  public FINContractsControllerEJBGen() {}

  /*FINContracts. Добавить*/
  public int add(FINContracts object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINContractsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINContracts. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINContracts. Изменить*/
  public void save(FINContracts object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINContractsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINContracts. Получить объект*/
  public FINContracts getObject(int code)
   {
    try
     {
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINContracts. Получить полный список*/
  public FINContractsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINContracts. Получить список по фильтру*/
  public FINContractsShortList getFilteredList(FINContractsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINContracts. Получить список для просмотра*/
  public FINContractsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINContracts. Получить список для просмотра по фильтру*/
  public FINContractsShortList getScrollableFilteredList(FINContractsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINContracts%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINContracts. Получить список для просмотра по условию*/
  public FINContractsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINContractsFilter filterObject = new FINContractsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINContracts. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(FINContractsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINContractsDAO objectDAO = new FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINContracts%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.FINDoc2ENServicesObjectDAO;
import com.ksoe.energynet.valueobject.FINDoc2ENServicesObject;
import com.ksoe.energynet.valueobject.lists.FINDoc2ENServicesObjectShortList;
import com.ksoe.energynet.valueobject.filter.FINDoc2ENServicesObjectFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for FINDoc2ENServicesObject;  
  * 	
  */

public abstract class FINDoc2ENServicesObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public FINDoc2ENServicesObjectControllerEJBGen() {}

  /*FINDoc2ENServicesObject. Добавить*/
  public int add(FINDoc2ENServicesObject object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2ENServicesObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2ENServicesObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINDoc2ENServicesObject. Изменить*/
  public void save(FINDoc2ENServicesObject object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2ENServicesObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2ENServicesObject. Получить объект*/
  public FINDoc2ENServicesObject getObject(int code)
   {
    try
     {
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2ENServicesObject. Получить полный список*/
  public FINDoc2ENServicesObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINDoc2ENServicesObject. Получить список по фильтру*/
  public FINDoc2ENServicesObjectShortList getFilteredList(FINDoc2ENServicesObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINDoc2ENServicesObject. Получить список для просмотра*/
  public FINDoc2ENServicesObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINDoc2ENServicesObject. Получить список для просмотра по фильтру*/
  public FINDoc2ENServicesObjectShortList getScrollableFilteredList(FINDoc2ENServicesObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2ENServicesObject. Получить список для просмотра по условию*/
  public FINDoc2ENServicesObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINDoc2ENServicesObjectFilter filterObject = new FINDoc2ENServicesObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINDoc2ENServicesObject. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(FINDoc2ENServicesObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDoc2ENServicesObjectDAO objectDAO = new FINDoc2ENServicesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINDoc2ENServicesObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
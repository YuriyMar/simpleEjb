
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.CNMovementDAO;
import com.ksoe.energynet.valueobject.CNMovement;
import com.ksoe.energynet.valueobject.lists.CNMovementShortList;
import com.ksoe.energynet.valueobject.filter.CNMovementFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for CNMovement;  
  * 	
  */

public abstract class CNMovementControllerEJBGen extends GenericSessionStatelessBean
{
  public CNMovementControllerEJBGen() {}

  /*CNMovement. Добавить*/
  public int add(CNMovement object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNMovementValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNMovement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNMovement. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNMovement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNMovement. Изменить*/
  public void save(CNMovement object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNMovementValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNMovement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNMovement. Получить объект*/
  public CNMovement getObject(int code)
   {
    try
     {
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNMovement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNMovement. Получить полный список*/
  public CNMovementShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNMovement. Получить список по фильтру*/
  public CNMovementShortList getFilteredList(CNMovementFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNMovement. Получить список для просмотра*/
  public CNMovementShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNMovement. Получить список для просмотра по фильтру*/
  public CNMovementShortList getScrollableFilteredList(CNMovementFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNMovement%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNMovement. Получить список для просмотра по условию*/
  public CNMovementShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNMovementFilter filterObject = new CNMovementFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ECNMovement. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(CNMovementFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNMovementDAO objectDAO = new CNMovementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.CNMovement%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
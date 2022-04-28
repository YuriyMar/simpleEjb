
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENReconstrModernOZ2ENactDAO;
import com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact;
import com.ksoe.energynet.valueobject.lists.ENReconstrModernOZ2ENactShortList;
import com.ksoe.energynet.valueobject.filter.ENReconstrModernOZ2ENactFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENReconstrModernOZ2ENact;  
  * 	
  */

public abstract class ENReconstrModernOZ2ENactControllerEJBGen extends GenericSessionStatelessBean
{
  public ENReconstrModernOZ2ENactControllerEJBGen() {}

  /*ENReconstrModernOZ2ENact. Добавить*/
  public int add(ENReconstrModernOZ2ENact object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZ2ENactValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ2ENact. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENReconstrModernOZ2ENact. Изменить*/
  public void save(ENReconstrModernOZ2ENact object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZ2ENactValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ2ENact. Получить объект*/
  public ENReconstrModernOZ2ENact getObject(int code)
   {
    try
     {
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ2ENact. Получить полный список*/
  public ENReconstrModernOZ2ENactShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENReconstrModernOZ2ENact. Получить список по фильтру*/
  public ENReconstrModernOZ2ENactShortList getFilteredList(ENReconstrModernOZ2ENactFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENReconstrModernOZ2ENact. Получить список для просмотра*/
  public ENReconstrModernOZ2ENactShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENReconstrModernOZ2ENact. Получить список для просмотра по фильтру*/
  public ENReconstrModernOZ2ENactShortList getScrollableFilteredList(ENReconstrModernOZ2ENactFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ2ENact. Получить список для просмотра по условию*/
  public ENReconstrModernOZ2ENactShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENReconstrModernOZ2ENactFilter filterObject = new ENReconstrModernOZ2ENactFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENReconstrModernOZ2ENact. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENReconstrModernOZ2ENactFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZ2ENactDAO objectDAO = new ENReconstrModernOZ2ENactDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
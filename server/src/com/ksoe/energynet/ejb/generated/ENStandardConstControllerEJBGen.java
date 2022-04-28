
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENStandardConstDAO;
import com.ksoe.energynet.valueobject.ENStandardConst;
import com.ksoe.energynet.valueobject.lists.ENStandardConstShortList;
import com.ksoe.energynet.valueobject.filter.ENStandardConstFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENStandardConst;  
  * 	
  */

public abstract class ENStandardConstControllerEJBGen extends GenericSessionStatelessBean
{
  public ENStandardConstControllerEJBGen() {}

  /*ENStandardConst. Добавить*/
  public int add(ENStandardConst object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENStandardConst%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConst. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENStandardConst%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENStandardConst. Изменить*/
  public void save(ENStandardConst object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENStandardConst%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConst. Получить объект*/
  public ENStandardConst getObject(int code)
   {
    try
     {
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENStandardConst%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConst. Получить полный список*/
  public ENStandardConstShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENStandardConst. Получить список по фильтру*/
  public ENStandardConstShortList getFilteredList(ENStandardConstFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENStandardConst. Получить список для просмотра*/
  public ENStandardConstShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENStandardConst. Получить список для просмотра по фильтру*/
  public ENStandardConstShortList getScrollableFilteredList(ENStandardConstFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENStandardConst%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConst. Получить список для просмотра по условию*/
  public ENStandardConstShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENStandardConstFilter filterObject = new ENStandardConstFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENStandardConst. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENStandardConstFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstDAO objectDAO = new ENStandardConstDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENStandardConst%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENServicesObject2PaymentScheduleDAO;
import com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule;
import com.ksoe.energynet.valueobject.lists.ENServicesObject2PaymentScheduleShortList;
import com.ksoe.energynet.valueobject.filter.ENServicesObject2PaymentScheduleFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENServicesObject2PaymentSchedule;  
  * 	
  */

public abstract class ENServicesObject2PaymentScheduleControllerEJBGen extends GenericSessionStatelessBean
{
  public ENServicesObject2PaymentScheduleControllerEJBGen() {}

  /*ENServicesObject2PaymentSchedule. Добавить*/
  public int add(ENServicesObject2PaymentSchedule object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesObject2PaymentScheduleValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObject2PaymentSchedule. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENServicesObject2PaymentSchedule. Изменить*/
  public void save(ENServicesObject2PaymentSchedule object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesObject2PaymentScheduleValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObject2PaymentSchedule. Получить объект*/
  public ENServicesObject2PaymentSchedule getObject(int code)
   {
    try
     {
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObject2PaymentSchedule. Получить полный список*/
  public ENServicesObject2PaymentScheduleShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENServicesObject2PaymentSchedule. Получить список по фильтру*/
  public ENServicesObject2PaymentScheduleShortList getFilteredList(ENServicesObject2PaymentScheduleFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENServicesObject2PaymentSchedule. Получить список для просмотра*/
  public ENServicesObject2PaymentScheduleShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENServicesObject2PaymentSchedule. Получить список для просмотра по фильтру*/
  public ENServicesObject2PaymentScheduleShortList getScrollableFilteredList(ENServicesObject2PaymentScheduleFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObject2PaymentSchedule. Получить список для просмотра по условию*/
  public ENServicesObject2PaymentScheduleShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENServicesObject2PaymentScheduleFilter filterObject = new ENServicesObject2PaymentScheduleFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENServicesObject2PaymentSchedule. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENServicesObject2PaymentScheduleFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesObject2PaymentScheduleDAO objectDAO = new ENServicesObject2PaymentScheduleDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObject2PaymentSchedule%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
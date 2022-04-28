
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkItem2PlanWorkItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2PlanWorkItemFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2PlanWorkItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkItem2PlanWorkItem;  
  * 	
  */

public abstract class ENPlanWorkItem2PlanWorkItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkItem2PlanWorkItemControllerEJBGen() {}

  /*ENPlanWorkItem2PlanWorkItem. Добавить*/
  public int add(ENPlanWorkItem2PlanWorkItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2PlanWorkItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkItem2PlanWorkItem. Изменить*/
  public void save(ENPlanWorkItem2PlanWorkItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2PlanWorkItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItem. Получить объект*/
  public ENPlanWorkItem2PlanWorkItem getObject(int code)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItem. Получить полный список*/
  public ENPlanWorkItem2PlanWorkItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkItem2PlanWorkItem. Получить список по фильтру*/
  public ENPlanWorkItem2PlanWorkItemShortList getFilteredList(ENPlanWorkItem2PlanWorkItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkItem2PlanWorkItem. Получить список для просмотра*/
  public ENPlanWorkItem2PlanWorkItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkItem2PlanWorkItem. Получить список для просмотра по фильтру*/
  public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItem. Получить список для просмотра по условию*/
  public ENPlanWorkItem2PlanWorkItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkItem2PlanWorkItemFilter filterObject = new ENPlanWorkItem2PlanWorkItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWorkItem2PlanWorkItem. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWorkItem2PlanWorkItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemDAO objectDAO = new ENPlanWorkItem2PlanWorkItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
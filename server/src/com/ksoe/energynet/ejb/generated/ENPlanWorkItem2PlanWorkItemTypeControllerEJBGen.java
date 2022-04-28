
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkItem2PlanWorkItemTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2PlanWorkItemTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2PlanWorkItemTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkItem2PlanWorkItemType;  
  * 	
  */

public abstract class ENPlanWorkItem2PlanWorkItemTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkItem2PlanWorkItemTypeControllerEJBGen() {}

  /*ENPlanWorkItem2PlanWorkItemType. Добавить*/
  public int add(ENPlanWorkItem2PlanWorkItemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2PlanWorkItemTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItemType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkItem2PlanWorkItemType. Изменить*/
  public void save(ENPlanWorkItem2PlanWorkItemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2PlanWorkItemTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItemType. Получить объект*/
  public ENPlanWorkItem2PlanWorkItemType getObject(int code)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItemType. Получить полный список*/
  public ENPlanWorkItem2PlanWorkItemTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkItem2PlanWorkItemType. Получить список по фильтру*/
  public ENPlanWorkItem2PlanWorkItemTypeShortList getFilteredList(ENPlanWorkItem2PlanWorkItemTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkItem2PlanWorkItemType. Получить список для просмотра*/
  public ENPlanWorkItem2PlanWorkItemTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkItem2PlanWorkItemType. Получить список для просмотра по фильтру*/
  public ENPlanWorkItem2PlanWorkItemTypeShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItemTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2PlanWorkItemType. Получить список для просмотра по условию*/
  public ENPlanWorkItem2PlanWorkItemTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkItem2PlanWorkItemTypeFilter filterObject = new ENPlanWorkItem2PlanWorkItemTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWorkItem2PlanWorkItemType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWorkItem2PlanWorkItemTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkItem2PlanWorkItemTypeDAO objectDAO = new ENPlanWorkItem2PlanWorkItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItemType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanType2PlanStateDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanType2PlanState;
import com.ksoe.energynet.valueobject.filter.ENPlanType2PlanStateFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanType2PlanStateShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanType2PlanState;  
  * 	
  */

public abstract class ENPlanType2PlanStateControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanType2PlanStateControllerEJBGen() {}

  /*ENPlanType2PlanState. Добавить*/
  public int add(ENPlanType2PlanState object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanType2PlanStateValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanType2PlanStateDAO objectDAO = new ENPlanType2PlanStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanType2PlanState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanType2PlanState. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanType2PlanStateDAO objectDAO = new ENPlanType2PlanStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanType2PlanState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanType2PlanState. Изменить*/
  public void save(ENPlanType2PlanState object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanType2PlanStateValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanType2PlanStateDAO objectDAO = new ENPlanType2PlanStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanType2PlanState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanType2PlanState. Получить объект*/
  public ENPlanType2PlanState getObject(int code)
   {
    try
     {
      ENPlanType2PlanStateDAO objectDAO = new ENPlanType2PlanStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanType2PlanState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanType2PlanState. Получить полный список*/
  public ENPlanType2PlanStateShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanType2PlanState. Получить список по фильтру*/
  public ENPlanType2PlanStateShortList getFilteredList(ENPlanType2PlanStateFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanType2PlanState. Получить список для просмотра*/
  public ENPlanType2PlanStateShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanType2PlanState. Получить список для просмотра по фильтру*/
  public ENPlanType2PlanStateShortList getScrollableFilteredList(ENPlanType2PlanStateFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanType2PlanStateDAO objectDAO = new ENPlanType2PlanStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanType2PlanState%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanType2PlanState. Получить список для просмотра по условию*/
  public ENPlanType2PlanStateShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanType2PlanStateFilter filterObject = new ENPlanType2PlanStateFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
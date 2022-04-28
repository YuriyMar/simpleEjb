
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWork2PlanWorkReasonDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2PlanWorkReasonFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2PlanWorkReasonShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWork2PlanWorkReason;  
  * 	
  */

public abstract class ENPlanWork2PlanWorkReasonControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWork2PlanWorkReasonControllerEJBGen() {}

  /*ENPlanWork2PlanWorkReason. Добавить*/
  public int add(ENPlanWork2PlanWorkReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2PlanWorkReasonValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2PlanWorkReason. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWork2PlanWorkReason. Изменить*/
  public void save(ENPlanWork2PlanWorkReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2PlanWorkReasonValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2PlanWorkReason. Получить объект*/
  public ENPlanWork2PlanWorkReason getObject(int code)
   {
    try
     {
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2PlanWorkReason. Получить полный список*/
  public ENPlanWork2PlanWorkReasonShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWork2PlanWorkReason. Получить список по фильтру*/
  public ENPlanWork2PlanWorkReasonShortList getFilteredList(ENPlanWork2PlanWorkReasonFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWork2PlanWorkReason. Получить список для просмотра*/
  public ENPlanWork2PlanWorkReasonShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWork2PlanWorkReason. Получить список для просмотра по фильтру*/
  public ENPlanWork2PlanWorkReasonShortList getScrollableFilteredList(ENPlanWork2PlanWorkReasonFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2PlanWorkReason. Получить список для просмотра по условию*/
  public ENPlanWork2PlanWorkReasonShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWork2PlanWorkReasonFilter filterObject = new ENPlanWork2PlanWorkReasonFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWork2PlanWorkReason. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWork2PlanWorkReasonFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2PlanWorkReasonDAO objectDAO = new ENPlanWork2PlanWorkReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2PlanWorkReason%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
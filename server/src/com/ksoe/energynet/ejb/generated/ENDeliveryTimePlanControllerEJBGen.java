
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDeliveryTimePlanDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDeliveryTimePlan;
import com.ksoe.energynet.valueobject.filter.ENDeliveryTimePlanFilter;
import com.ksoe.energynet.valueobject.lists.ENDeliveryTimePlanShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDeliveryTimePlan;  
  * 	
  */

public abstract class ENDeliveryTimePlanControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDeliveryTimePlanControllerEJBGen() {}

  /*ENDeliveryTimePlan. Добавить*/
  public int add(ENDeliveryTimePlan object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryTimePlanValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryTimePlanDAO objectDAO = new ENDeliveryTimePlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDeliveryTimePlan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTimePlan. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDeliveryTimePlanDAO objectDAO = new ENDeliveryTimePlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDeliveryTimePlan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDeliveryTimePlan. Изменить*/
  public void save(ENDeliveryTimePlan object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryTimePlanValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryTimePlanDAO objectDAO = new ENDeliveryTimePlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDeliveryTimePlan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTimePlan. Получить объект*/
  public ENDeliveryTimePlan getObject(int code)
   {
    try
     {
      ENDeliveryTimePlanDAO objectDAO = new ENDeliveryTimePlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDeliveryTimePlan%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTimePlan. Получить полный список*/
  public ENDeliveryTimePlanShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDeliveryTimePlan. Получить список по фильтру*/
  public ENDeliveryTimePlanShortList getFilteredList(ENDeliveryTimePlanFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDeliveryTimePlan. Получить список для просмотра*/
  public ENDeliveryTimePlanShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDeliveryTimePlan. Получить список для просмотра по фильтру*/
  public ENDeliveryTimePlanShortList getScrollableFilteredList(ENDeliveryTimePlanFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDeliveryTimePlanDAO objectDAO = new ENDeliveryTimePlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDeliveryTimePlan%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTimePlan. Получить список для просмотра по условию*/
  public ENDeliveryTimePlanShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDeliveryTimePlanFilter filterObject = new ENDeliveryTimePlanFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
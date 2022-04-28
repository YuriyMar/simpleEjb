
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAccumulatorStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAccumulatorStatus;
import com.ksoe.energynet.valueobject.filter.ENAccumulatorStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENAccumulatorStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAccumulatorStatus;  
  * 	
  */

public abstract class ENAccumulatorStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAccumulatorStatusControllerEJBGen() {}

  /*ENAccumulatorStatus. Добавить*/
  public int add(ENAccumulatorStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAccumulatorStatus. Изменить*/
  public void save(ENAccumulatorStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorStatus. Получить объект*/
  public ENAccumulatorStatus getObject(int code)
   {
    try
     {
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorStatus. Получить полный список*/
  public ENAccumulatorStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAccumulatorStatus. Получить список по фильтру*/
  public ENAccumulatorStatusShortList getFilteredList(ENAccumulatorStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAccumulatorStatus. Получить список для просмотра*/
  public ENAccumulatorStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAccumulatorStatus. Получить список для просмотра по фильтру*/
  public ENAccumulatorStatusShortList getScrollableFilteredList(ENAccumulatorStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorStatus. Получить список для просмотра по условию*/
  public ENAccumulatorStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAccumulatorStatusFilter filterObject = new ENAccumulatorStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAccumulatorStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAccumulatorStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorStatusDAO objectDAO = new ENAccumulatorStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAccumulatorStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
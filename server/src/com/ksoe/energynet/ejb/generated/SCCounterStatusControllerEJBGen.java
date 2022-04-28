
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCCounterStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCCounterStatus;
import com.ksoe.energynet.valueobject.filter.SCCounterStatusFilter;
import com.ksoe.energynet.valueobject.lists.SCCounterStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCCounterStatus;  
  * 	
  */

public abstract class SCCounterStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public SCCounterStatusControllerEJBGen() {}

  /*SCCounterStatus. Добавить*/
  public int add(SCCounterStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCCounterStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCCounterStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCCounterStatus. Изменить*/
  public void save(SCCounterStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCCounterStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterStatus. Получить объект*/
  public SCCounterStatus getObject(int code)
   {
    try
     {
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCCounterStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterStatus. Получить полный список*/
  public SCCounterStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCCounterStatus. Получить список по фильтру*/
  public SCCounterStatusShortList getFilteredList(SCCounterStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCCounterStatus. Получить список для просмотра*/
  public SCCounterStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCCounterStatus. Получить список для просмотра по фильтру*/
  public SCCounterStatusShortList getScrollableFilteredList(SCCounterStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCCounterStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterStatus. Получить список для просмотра по условию*/
  public SCCounterStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCCounterStatusFilter filterObject = new SCCounterStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCCounterStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCCounterStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterStatusDAO objectDAO = new SCCounterStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCCounterStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
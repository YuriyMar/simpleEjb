
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputStatus;
import com.ksoe.energynet.valueobject.filter.SCUsageInputStatusFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputStatus;  
  * 	
  */

public abstract class SCUsageInputStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputStatusControllerEJBGen() {}

  /*SCUsageInputStatus. Добавить*/
  public int add(SCUsageInputStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputStatus. Изменить*/
  public void save(SCUsageInputStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputStatus. Получить объект*/
  public SCUsageInputStatus getObject(int code)
   {
    try
     {
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputStatus. Получить полный список*/
  public SCUsageInputStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputStatus. Получить список по фильтру*/
  public SCUsageInputStatusShortList getFilteredList(SCUsageInputStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputStatus. Получить список для просмотра*/
  public SCUsageInputStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputStatus. Получить список для просмотра по фильтру*/
  public SCUsageInputStatusShortList getScrollableFilteredList(SCUsageInputStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputStatus. Получить список для просмотра по условию*/
  public SCUsageInputStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputStatusFilter filterObject = new SCUsageInputStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputStatusDAO objectDAO = new SCUsageInputStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
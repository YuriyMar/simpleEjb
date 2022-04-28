
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItem;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItem;  
  * 	
  */

public abstract class SCUsageInputItemControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemControllerEJBGen() {}

  /*SCUsageInputItem. Добавить*/
  public int add(SCUsageInputItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItem. Изменить*/
  public void save(SCUsageInputItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItem. Получить объект*/
  public SCUsageInputItem getObject(int code)
   {
    try
     {
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItem. Получить полный список*/
  public SCUsageInputItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItem. Получить список по фильтру*/
  public SCUsageInputItemShortList getFilteredList(SCUsageInputItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItem. Получить список для просмотра*/
  public SCUsageInputItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItem. Получить список для просмотра по фильтру*/
  public SCUsageInputItemShortList getScrollableFilteredList(SCUsageInputItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItem. Получить список для просмотра по условию*/
  public SCUsageInputItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemFilter filterObject = new SCUsageInputItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItem. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemDAO objectDAO = new SCUsageInputItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
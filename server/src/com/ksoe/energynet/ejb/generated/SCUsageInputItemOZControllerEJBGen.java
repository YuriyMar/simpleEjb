
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemOZDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZ;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItemOZ;  
  * 	
  */

public abstract class SCUsageInputItemOZControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemOZControllerEJBGen() {}

  /*SCUsageInputItemOZ. Добавить*/
  public int add(SCUsageInputItemOZ object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItemOZ. Изменить*/
  public void save(SCUsageInputItemOZ object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ. Получить объект*/
  public SCUsageInputItemOZ getObject(int code)
   {
    try
     {
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ. Получить полный список*/
  public SCUsageInputItemOZShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItemOZ. Получить список по фильтру*/
  public SCUsageInputItemOZShortList getFilteredList(SCUsageInputItemOZFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItemOZ. Получить список для просмотра*/
  public SCUsageInputItemOZShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ. Получить список для просмотра по фильтру*/
  public SCUsageInputItemOZShortList getScrollableFilteredList(SCUsageInputItemOZFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ. Получить список для просмотра по условию*/
  public SCUsageInputItemOZShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemOZFilter filterObject = new SCUsageInputItemOZFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemOZFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZDAO objectDAO = new SCUsageInputItemOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
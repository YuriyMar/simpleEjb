
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemOZ2SCCounterDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZ2SCCounterFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZ2SCCounterShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItemOZ2SCCounter;  
  * 	
  */

public abstract class SCUsageInputItemOZ2SCCounterControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemOZ2SCCounterControllerEJBGen() {}

  /*SCUsageInputItemOZ2SCCounter. Добавить*/
  public int add(SCUsageInputItemOZ2SCCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZ2SCCounterValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2SCCounter. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItemOZ2SCCounter. Изменить*/
  public void save(SCUsageInputItemOZ2SCCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZ2SCCounterValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2SCCounter. Получить объект*/
  public SCUsageInputItemOZ2SCCounter getObject(int code)
   {
    try
     {
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2SCCounter. Получить полный список*/
  public SCUsageInputItemOZ2SCCounterShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItemOZ2SCCounter. Получить список по фильтру*/
  public SCUsageInputItemOZ2SCCounterShortList getFilteredList(SCUsageInputItemOZ2SCCounterFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItemOZ2SCCounter. Получить список для просмотра*/
  public SCUsageInputItemOZ2SCCounterShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ2SCCounter. Получить список для просмотра по фильтру*/
  public SCUsageInputItemOZ2SCCounterShortList getScrollableFilteredList(SCUsageInputItemOZ2SCCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2SCCounter. Получить список для просмотра по условию*/
  public SCUsageInputItemOZ2SCCounterShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemOZ2SCCounterFilter filterObject = new SCUsageInputItemOZ2SCCounterFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ2SCCounter. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemOZ2SCCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZ2SCCounterDAO objectDAO = new SCUsageInputItemOZ2SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2SCCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
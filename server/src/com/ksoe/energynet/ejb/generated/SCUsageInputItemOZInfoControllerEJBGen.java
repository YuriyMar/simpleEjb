
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemOZInfoDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZInfoFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZInfoShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItemOZInfo;  
  * 	
  */

public abstract class SCUsageInputItemOZInfoControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemOZInfoControllerEJBGen() {}

  /*SCUsageInputItemOZInfo. Добавить*/
  public int add(SCUsageInputItemOZInfo object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZInfoValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZInfo. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItemOZInfo. Изменить*/
  public void save(SCUsageInputItemOZInfo object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZInfoValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZInfo. Получить объект*/
  public SCUsageInputItemOZInfo getObject(int code)
   {
    try
     {
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZInfo. Получить полный список*/
  public SCUsageInputItemOZInfoShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItemOZInfo. Получить список по фильтру*/
  public SCUsageInputItemOZInfoShortList getFilteredList(SCUsageInputItemOZInfoFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItemOZInfo. Получить список для просмотра*/
  public SCUsageInputItemOZInfoShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZInfo. Получить список для просмотра по фильтру*/
  public SCUsageInputItemOZInfoShortList getScrollableFilteredList(SCUsageInputItemOZInfoFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZInfo. Получить список для просмотра по условию*/
  public SCUsageInputItemOZInfoShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemOZInfoFilter filterObject = new SCUsageInputItemOZInfoFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZInfo. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemOZInfoFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZInfoDAO objectDAO = new SCUsageInputItemOZInfoDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZInfo%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
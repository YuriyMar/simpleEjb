
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemOZ2ENActDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZ2ENActFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZ2ENActShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItemOZ2ENAct;  
  * 	
  */

public abstract class SCUsageInputItemOZ2ENActControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemOZ2ENActControllerEJBGen() {}

  /*SCUsageInputItemOZ2ENAct. Добавить*/
  public int add(SCUsageInputItemOZ2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZ2ENActValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2ENAct. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItemOZ2ENAct. Изменить*/
  public void save(SCUsageInputItemOZ2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemOZ2ENActValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2ENAct. Получить объект*/
  public SCUsageInputItemOZ2ENAct getObject(int code)
   {
    try
     {
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2ENAct. Получить полный список*/
  public SCUsageInputItemOZ2ENActShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItemOZ2ENAct. Получить список по фильтру*/
  public SCUsageInputItemOZ2ENActShortList getFilteredList(SCUsageInputItemOZ2ENActFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItemOZ2ENAct. Получить список для просмотра*/
  public SCUsageInputItemOZ2ENActShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ2ENAct. Получить список для просмотра по фильтру*/
  public SCUsageInputItemOZ2ENActShortList getScrollableFilteredList(SCUsageInputItemOZ2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemOZ2ENAct. Получить список для просмотра по условию*/
  public SCUsageInputItemOZ2ENActShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemOZ2ENActFilter filterObject = new SCUsageInputItemOZ2ENActFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItemOZ2ENAct. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemOZ2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemOZ2ENActDAO objectDAO = new SCUsageInputItemOZ2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemOZ2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
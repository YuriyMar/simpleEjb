
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENOperativeObjectHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENOperativeObjectHistory;
import com.ksoe.energynet.valueobject.filter.ENOperativeObjectHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENOperativeObjectHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENOperativeObjectHistory;  
  * 	
  */

public abstract class ENOperativeObjectHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENOperativeObjectHistoryControllerEJBGen() {}

  /*ENOperativeObjectHistory. Добавить*/
  public int add(ENOperativeObjectHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOperativeObjectHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOperativeObjectHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENOperativeObjectHistory. Изменить*/
  public void save(ENOperativeObjectHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOperativeObjectHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOperativeObjectHistory. Получить объект*/
  public ENOperativeObjectHistory getObject(int code)
   {
    try
     {
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOperativeObjectHistory. Получить полный список*/
  public ENOperativeObjectHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENOperativeObjectHistory. Получить список по фильтру*/
  public ENOperativeObjectHistoryShortList getFilteredList(ENOperativeObjectHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENOperativeObjectHistory. Получить список для просмотра*/
  public ENOperativeObjectHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENOperativeObjectHistory. Получить список для просмотра по фильтру*/
  public ENOperativeObjectHistoryShortList getScrollableFilteredList(ENOperativeObjectHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOperativeObjectHistory. Получить список для просмотра по условию*/
  public ENOperativeObjectHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENOperativeObjectHistoryFilter filterObject = new ENOperativeObjectHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENOperativeObjectHistory. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENOperativeObjectHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENOperativeObjectHistoryDAO objectDAO = new ENOperativeObjectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENOperativeObjectHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
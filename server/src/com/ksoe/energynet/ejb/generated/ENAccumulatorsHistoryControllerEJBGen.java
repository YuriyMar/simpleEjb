
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAccumulatorsHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAccumulatorsHistory;
import com.ksoe.energynet.valueobject.filter.ENAccumulatorsHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENAccumulatorsHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAccumulatorsHistory;  
  * 	
  */

public abstract class ENAccumulatorsHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAccumulatorsHistoryControllerEJBGen() {}

  /*ENAccumulatorsHistory. Добавить*/
  public int add(ENAccumulatorsHistory object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAccumulatorsHistory. Изменить*/
  public void save(ENAccumulatorsHistory object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsHistory. Получить объект*/
  public ENAccumulatorsHistory getObject(int code)
   {
    try
     {
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsHistory. Получить полный список*/
  public ENAccumulatorsHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAccumulatorsHistory. Получить список по фильтру*/
  public ENAccumulatorsHistoryShortList getFilteredList(ENAccumulatorsHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAccumulatorsHistory. Получить список для просмотра*/
  public ENAccumulatorsHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAccumulatorsHistory. Получить список для просмотра по фильтру*/
  public ENAccumulatorsHistoryShortList getScrollableFilteredList(ENAccumulatorsHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsHistory. Получить список для просмотра по условию*/
  public ENAccumulatorsHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAccumulatorsHistoryFilter filterObject = new ENAccumulatorsHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAccumulatorsHistory. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAccumulatorsHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsHistoryDAO objectDAO = new ENAccumulatorsHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAccumulatorsHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
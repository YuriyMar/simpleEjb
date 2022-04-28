
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItemStatusHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemStatusHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemStatusHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItemStatusHistory;  
  * 	
  */

public abstract class ENEstimateItemStatusHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItemStatusHistoryControllerEJBGen() {}

  /*ENEstimateItemStatusHistory. Добавить*/
  public int add(ENEstimateItemStatusHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemStatusHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemStatusHistoryDAO objectDAO = new ENEstimateItemStatusHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemStatusHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItemStatusHistoryDAO objectDAO = new ENEstimateItemStatusHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItemStatusHistory. Изменить*/
  public void save(ENEstimateItemStatusHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemStatusHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemStatusHistoryDAO objectDAO = new ENEstimateItemStatusHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemStatusHistory. Получить объект*/
  public ENEstimateItemStatusHistory getObject(int code)
   {
    try
     {
      ENEstimateItemStatusHistoryDAO objectDAO = new ENEstimateItemStatusHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemStatusHistory. Получить полный список*/
  public ENEstimateItemStatusHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItemStatusHistory. Получить список по фильтру*/
  public ENEstimateItemStatusHistoryShortList getFilteredList(ENEstimateItemStatusHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItemStatusHistory. Получить список для просмотра*/
  public ENEstimateItemStatusHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItemStatusHistory. Получить список для просмотра по фильтру*/
  public ENEstimateItemStatusHistoryShortList getScrollableFilteredList(ENEstimateItemStatusHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItemStatusHistoryDAO objectDAO = new ENEstimateItemStatusHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItemStatusHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemStatusHistory. Получить список для просмотра по условию*/
  public ENEstimateItemStatusHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItemStatusHistoryFilter filterObject = new ENEstimateItemStatusHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
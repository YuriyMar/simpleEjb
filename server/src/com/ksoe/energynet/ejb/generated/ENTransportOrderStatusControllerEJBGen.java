
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportOrderStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportOrderStatus;
import com.ksoe.energynet.valueobject.filter.ENTransportOrderStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportOrderStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportOrderStatus;  
  * 	
  */

public abstract class ENTransportOrderStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportOrderStatusControllerEJBGen() {}

  /*ENTransportOrderStatus. Добавить*/
  public int add(ENTransportOrderStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrderStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrderStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportOrderStatus. Изменить*/
  public void save(ENTransportOrderStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrderStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrderStatus. Получить объект*/
  public ENTransportOrderStatus getObject(int code)
   {
    try
     {
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrderStatus. Получить полный список*/
  public ENTransportOrderStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportOrderStatus. Получить список по фильтру*/
  public ENTransportOrderStatusShortList getFilteredList(ENTransportOrderStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportOrderStatus. Получить список для просмотра*/
  public ENTransportOrderStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportOrderStatus. Получить список для просмотра по фильтру*/
  public ENTransportOrderStatusShortList getScrollableFilteredList(ENTransportOrderStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrderStatus. Получить список для просмотра по условию*/
  public ENTransportOrderStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportOrderStatusFilter filterObject = new ENTransportOrderStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportOrderStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportOrderStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrderStatusDAO objectDAO = new ENTransportOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportOrderStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
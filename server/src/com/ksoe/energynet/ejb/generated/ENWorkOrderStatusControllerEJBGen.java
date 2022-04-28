
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENWorkOrderStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENWorkOrderStatus;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENWorkOrderStatus;  
  * 	
  */

public abstract class ENWorkOrderStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENWorkOrderStatusControllerEJBGen() {}

  /*ENWorkOrderStatus. Добавить*/
  public int add(ENWorkOrderStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkOrderStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkOrderStatusDAO objectDAO = new ENWorkOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENWorkOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrderStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENWorkOrderStatusDAO objectDAO = new ENWorkOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENWorkOrderStatus. Изменить*/
  public void save(ENWorkOrderStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkOrderStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkOrderStatusDAO objectDAO = new ENWorkOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWorkOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrderStatus. Получить объект*/
  public ENWorkOrderStatus getObject(int code)
   {
    try
     {
      ENWorkOrderStatusDAO objectDAO = new ENWorkOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENWorkOrderStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrderStatus. Получить полный список*/
  public ENWorkOrderStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENWorkOrderStatus. Получить список по фильтру*/
  public ENWorkOrderStatusShortList getFilteredList(ENWorkOrderStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENWorkOrderStatus. Получить список для просмотра*/
  public ENWorkOrderStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENWorkOrderStatus. Получить список для просмотра по фильтру*/
  public ENWorkOrderStatusShortList getScrollableFilteredList(ENWorkOrderStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWorkOrderStatusDAO objectDAO = new ENWorkOrderStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrderStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrderStatus. Получить список для просмотра по условию*/
  public ENWorkOrderStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENWorkOrderStatusFilter filterObject = new ENWorkOrderStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
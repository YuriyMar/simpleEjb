
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDeliveryOrderDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDeliveryOrder;
import com.ksoe.energynet.valueobject.filter.ENDeliveryOrderFilter;
import com.ksoe.energynet.valueobject.lists.ENDeliveryOrderShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDeliveryOrder;  
  * 	
  */

public abstract class ENDeliveryOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDeliveryOrderControllerEJBGen() {}

  /*ENDeliveryOrder. Добавить*/
  public int add(ENDeliveryOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryOrderDAO objectDAO = new ENDeliveryOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDeliveryOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDeliveryOrderDAO objectDAO = new ENDeliveryOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDeliveryOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDeliveryOrder. Изменить*/
  public void save(ENDeliveryOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryOrderDAO objectDAO = new ENDeliveryOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDeliveryOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryOrder. Получить объект*/
  public ENDeliveryOrder getObject(int code)
   {
    try
     {
      ENDeliveryOrderDAO objectDAO = new ENDeliveryOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDeliveryOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryOrder. Получить полный список*/
  public ENDeliveryOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDeliveryOrder. Получить список по фильтру*/
  public ENDeliveryOrderShortList getFilteredList(ENDeliveryOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDeliveryOrder. Получить список для просмотра*/
  public ENDeliveryOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDeliveryOrder. Получить список для просмотра по фильтру*/
  public ENDeliveryOrderShortList getScrollableFilteredList(ENDeliveryOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDeliveryOrderDAO objectDAO = new ENDeliveryOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDeliveryOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryOrder. Получить список для просмотра по условию*/
  public ENDeliveryOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDeliveryOrderFilter filterObject = new ENDeliveryOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
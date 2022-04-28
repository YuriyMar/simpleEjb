
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportOrder2TransportItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem;
import com.ksoe.energynet.valueobject.filter.ENTransportOrder2TransportItemFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportOrder2TransportItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportOrder2TransportItem;  
  * 	
  */

public abstract class ENTransportOrder2TransportItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportOrder2TransportItemControllerEJBGen() {}

  /*ENTransportOrder2TransportItem. Добавить*/
  public int add(ENTransportOrder2TransportItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrder2TransportItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2TransportItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportOrder2TransportItem. Изменить*/
  public void save(ENTransportOrder2TransportItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrder2TransportItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2TransportItem. Получить объект*/
  public ENTransportOrder2TransportItem getObject(int code)
   {
    try
     {
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2TransportItem. Получить полный список*/
  public ENTransportOrder2TransportItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportOrder2TransportItem. Получить список по фильтру*/
  public ENTransportOrder2TransportItemShortList getFilteredList(ENTransportOrder2TransportItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportOrder2TransportItem. Получить список для просмотра*/
  public ENTransportOrder2TransportItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportOrder2TransportItem. Получить список для просмотра по фильтру*/
  public ENTransportOrder2TransportItemShortList getScrollableFilteredList(ENTransportOrder2TransportItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2TransportItem. Получить список для просмотра по условию*/
  public ENTransportOrder2TransportItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportOrder2TransportItemFilter filterObject = new ENTransportOrder2TransportItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportOrder2TransportItem. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportOrder2TransportItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrder2TransportItemDAO objectDAO = new ENTransportOrder2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportOrder2TransportItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTransportRoute2RQTransportInvoiceDAO;
import com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice;
import com.ksoe.energynet.valueobject.lists.ENTransportRoute2RQTransportInvoiceShortList;
import com.ksoe.energynet.valueobject.filter.ENTransportRoute2RQTransportInvoiceFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTransportRoute2RQTransportInvoice;  
  * 	
  */

public abstract class ENTransportRoute2RQTransportInvoiceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportRoute2RQTransportInvoiceControllerEJBGen() {}

  /*ENTransportRoute2RQTransportInvoice. Добавить*/
  public int add(ENTransportRoute2RQTransportInvoice object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRoute2RQTransportInvoiceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQTransportInvoice. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportRoute2RQTransportInvoice. Изменить*/
  public void save(ENTransportRoute2RQTransportInvoice object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRoute2RQTransportInvoiceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQTransportInvoice. Получить объект*/
  public ENTransportRoute2RQTransportInvoice getObject(int code)
   {
    try
     {
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQTransportInvoice. Получить полный список*/
  public ENTransportRoute2RQTransportInvoiceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportRoute2RQTransportInvoice. Получить список по фильтру*/
  public ENTransportRoute2RQTransportInvoiceShortList getFilteredList(ENTransportRoute2RQTransportInvoiceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportRoute2RQTransportInvoice. Получить список для просмотра*/
  public ENTransportRoute2RQTransportInvoiceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportRoute2RQTransportInvoice. Получить список для просмотра по фильтру*/
  public ENTransportRoute2RQTransportInvoiceShortList getScrollableFilteredList(ENTransportRoute2RQTransportInvoiceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQTransportInvoice. Получить список для просмотра по условию*/
  public ENTransportRoute2RQTransportInvoiceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportRoute2RQTransportInvoiceFilter filterObject = new ENTransportRoute2RQTransportInvoiceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportRoute2RQTransportInvoice. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportRoute2RQTransportInvoiceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRoute2RQTransportInvoiceDAO objectDAO = new ENTransportRoute2RQTransportInvoiceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRoute2RQTransportInvoice%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
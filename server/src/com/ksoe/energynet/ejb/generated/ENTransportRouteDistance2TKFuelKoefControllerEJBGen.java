
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTransportRouteDistance2TKFuelKoefDAO;
import com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef;
import com.ksoe.energynet.valueobject.lists.ENTransportRouteDistance2TKFuelKoefShortList;
import com.ksoe.energynet.valueobject.filter.ENTransportRouteDistance2TKFuelKoefFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTransportRouteDistance2TKFuelKoef;  
  * 	
  */

public abstract class ENTransportRouteDistance2TKFuelKoefControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportRouteDistance2TKFuelKoefControllerEJBGen() {}

  /*ENTransportRouteDistance2TKFuelKoef. Добавить*/
  public int add(ENTransportRouteDistance2TKFuelKoef object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRouteDistance2TKFuelKoefValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance2TKFuelKoef. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportRouteDistance2TKFuelKoef. Изменить*/
  public void save(ENTransportRouteDistance2TKFuelKoef object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRouteDistance2TKFuelKoefValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance2TKFuelKoef. Получить объект*/
  public ENTransportRouteDistance2TKFuelKoef getObject(int code)
   {
    try
     {
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance2TKFuelKoef. Получить полный список*/
  public ENTransportRouteDistance2TKFuelKoefShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportRouteDistance2TKFuelKoef. Получить список по фильтру*/
  public ENTransportRouteDistance2TKFuelKoefShortList getFilteredList(ENTransportRouteDistance2TKFuelKoefFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportRouteDistance2TKFuelKoef. Получить список для просмотра*/
  public ENTransportRouteDistance2TKFuelKoefShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportRouteDistance2TKFuelKoef. Получить список для просмотра по фильтру*/
  public ENTransportRouteDistance2TKFuelKoefShortList getScrollableFilteredList(ENTransportRouteDistance2TKFuelKoefFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance2TKFuelKoef. Получить список для просмотра по условию*/
  public ENTransportRouteDistance2TKFuelKoefShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportRouteDistance2TKFuelKoefFilter filterObject = new ENTransportRouteDistance2TKFuelKoefFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportRouteDistance2TKFuelKoef. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportRouteDistance2TKFuelKoefFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRouteDistance2TKFuelKoefDAO objectDAO = new ENTransportRouteDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRouteDistance2TKFuelKoef%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
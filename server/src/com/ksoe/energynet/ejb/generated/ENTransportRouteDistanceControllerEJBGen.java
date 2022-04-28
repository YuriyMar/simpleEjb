
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTransportRouteDistanceDAO;
import com.ksoe.energynet.valueobject.ENTransportRouteDistance;
import com.ksoe.energynet.valueobject.lists.ENTransportRouteDistanceShortList;
import com.ksoe.energynet.valueobject.filter.ENTransportRouteDistanceFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTransportRouteDistance;  
  * 	
  */

public abstract class ENTransportRouteDistanceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportRouteDistanceControllerEJBGen() {}

  /*ENTransportRouteDistance. Добавить*/
  public int add(ENTransportRouteDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRouteDistanceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportRouteDistance. Изменить*/
  public void save(ENTransportRouteDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRouteDistanceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance. Получить объект*/
  public ENTransportRouteDistance getObject(int code)
   {
    try
     {
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance. Получить полный список*/
  public ENTransportRouteDistanceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportRouteDistance. Получить список по фильтру*/
  public ENTransportRouteDistanceShortList getFilteredList(ENTransportRouteDistanceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportRouteDistance. Получить список для просмотра*/
  public ENTransportRouteDistanceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportRouteDistance. Получить список для просмотра по фильтру*/
  public ENTransportRouteDistanceShortList getScrollableFilteredList(ENTransportRouteDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRouteDistance. Получить список для просмотра по условию*/
  public ENTransportRouteDistanceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportRouteDistanceFilter filterObject = new ENTransportRouteDistanceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportRouteDistance. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportRouteDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRouteDistanceDAO objectDAO = new ENTransportRouteDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRouteDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
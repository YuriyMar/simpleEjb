
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTransportRoute2RQFKOrderDAO;
import com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder;
import com.ksoe.energynet.valueobject.lists.ENTransportRoute2RQFKOrderShortList;
import com.ksoe.energynet.valueobject.filter.ENTransportRoute2RQFKOrderFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTransportRoute2RQFKOrder;  
  * 	
  */

public abstract class ENTransportRoute2RQFKOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportRoute2RQFKOrderControllerEJBGen() {}

  /*ENTransportRoute2RQFKOrder. Добавить*/
  public int add(ENTransportRoute2RQFKOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRoute2RQFKOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQFKOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportRoute2RQFKOrder. Изменить*/
  public void save(ENTransportRoute2RQFKOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRoute2RQFKOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQFKOrder. Получить объект*/
  public ENTransportRoute2RQFKOrder getObject(int code)
   {
    try
     {
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQFKOrder. Получить полный список*/
  public ENTransportRoute2RQFKOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportRoute2RQFKOrder. Получить список по фильтру*/
  public ENTransportRoute2RQFKOrderShortList getFilteredList(ENTransportRoute2RQFKOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportRoute2RQFKOrder. Получить список для просмотра*/
  public ENTransportRoute2RQFKOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportRoute2RQFKOrder. Получить список для просмотра по фильтру*/
  public ENTransportRoute2RQFKOrderShortList getScrollableFilteredList(ENTransportRoute2RQFKOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRoute2RQFKOrder. Получить список для просмотра по условию*/
  public ENTransportRoute2RQFKOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportRoute2RQFKOrderFilter filterObject = new ENTransportRoute2RQFKOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportRoute2RQFKOrder. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportRoute2RQFKOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRoute2RQFKOrderDAO objectDAO = new ENTransportRoute2RQFKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRoute2RQFKOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
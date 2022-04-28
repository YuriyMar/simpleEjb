
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportOrder2TravelDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportOrder2Travel;
import com.ksoe.energynet.valueobject.filter.ENTransportOrder2TravelFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportOrder2TravelShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportOrder2Travel;  
  * 	
  */

public abstract class ENTransportOrder2TravelControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportOrder2TravelControllerEJBGen() {}

  /*ENTransportOrder2Travel. Добавить*/
  public int add(ENTransportOrder2Travel object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrder2TravelValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2Travel. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportOrder2Travel. Изменить*/
  public void save(ENTransportOrder2Travel object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrder2TravelValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2Travel. Получить объект*/
  public ENTransportOrder2Travel getObject(int code)
   {
    try
     {
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2Travel. Получить полный список*/
  public ENTransportOrder2TravelShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportOrder2Travel. Получить список по фильтру*/
  public ENTransportOrder2TravelShortList getFilteredList(ENTransportOrder2TravelFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportOrder2Travel. Получить список для просмотра*/
  public ENTransportOrder2TravelShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportOrder2Travel. Получить список для просмотра по фильтру*/
  public ENTransportOrder2TravelShortList getScrollableFilteredList(ENTransportOrder2TravelFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder2Travel. Получить список для просмотра по условию*/
  public ENTransportOrder2TravelShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportOrder2TravelFilter filterObject = new ENTransportOrder2TravelFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportOrder2Travel. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportOrder2TravelFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrder2TravelDAO objectDAO = new ENTransportOrder2TravelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportOrder2Travel%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
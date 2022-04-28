
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelSheetItem2TransportItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItem2TransportItemFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItem2TransportItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelSheetItem2TransportItem;  
  * 	
  */

public abstract class ENTravelSheetItem2TransportItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetItem2TransportItemControllerEJBGen() {}

  /*ENTravelSheetItem2TransportItem. Добавить*/
  public int add(ENTravelSheetItem2TransportItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItem2TransportItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItem2TransportItemDAO objectDAO = new ENTravelSheetItem2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItem2TransportItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetItem2TransportItemDAO objectDAO = new ENTravelSheetItem2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetItem2TransportItem. Изменить*/
  public void save(ENTravelSheetItem2TransportItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItem2TransportItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItem2TransportItemDAO objectDAO = new ENTravelSheetItem2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItem2TransportItem. Получить объект*/
  public ENTravelSheetItem2TransportItem getObject(int code)
   {
    try
     {
      ENTravelSheetItem2TransportItemDAO objectDAO = new ENTravelSheetItem2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItem2TransportItem. Получить полный список*/
  public ENTravelSheetItem2TransportItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetItem2TransportItem. Получить список по фильтру*/
  public ENTravelSheetItem2TransportItemShortList getFilteredList(ENTravelSheetItem2TransportItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetItem2TransportItem. Получить список для просмотра*/
  public ENTravelSheetItem2TransportItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetItem2TransportItem. Получить список для просмотра по фильтру*/
  public ENTravelSheetItem2TransportItemShortList getScrollableFilteredList(ENTravelSheetItem2TransportItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItem2TransportItemDAO objectDAO = new ENTravelSheetItem2TransportItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItem2TransportItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItem2TransportItem. Получить список для просмотра по условию*/
  public ENTravelSheetItem2TransportItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetItem2TransportItemFilter filterObject = new ENTravelSheetItem2TransportItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
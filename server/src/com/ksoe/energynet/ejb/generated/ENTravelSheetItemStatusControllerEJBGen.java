
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelSheetItemStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelSheetItemStatus;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItemStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItemStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelSheetItemStatus;  
  * 	
  */

public abstract class ENTravelSheetItemStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetItemStatusControllerEJBGen() {}

  /*ENTravelSheetItemStatus. Добавить*/
  public int add(ENTravelSheetItemStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemStatusDAO objectDAO = new ENTravelSheetItemStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItemStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetItemStatusDAO objectDAO = new ENTravelSheetItemStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItemStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetItemStatus. Изменить*/
  public void save(ENTravelSheetItemStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemStatusDAO objectDAO = new ENTravelSheetItemStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItemStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemStatus. Получить объект*/
  public ENTravelSheetItemStatus getObject(int code)
   {
    try
     {
      ENTravelSheetItemStatusDAO objectDAO = new ENTravelSheetItemStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItemStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemStatus. Получить полный список*/
  public ENTravelSheetItemStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetItemStatus. Получить список по фильтру*/
  public ENTravelSheetItemStatusShortList getFilteredList(ENTravelSheetItemStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetItemStatus. Получить список для просмотра*/
  public ENTravelSheetItemStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetItemStatus. Получить список для просмотра по фильтру*/
  public ENTravelSheetItemStatusShortList getScrollableFilteredList(ENTravelSheetItemStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemStatusDAO objectDAO = new ENTravelSheetItemStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItemStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemStatus. Получить список для просмотра по условию*/
  public ENTravelSheetItemStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetItemStatusFilter filterObject = new ENTravelSheetItemStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
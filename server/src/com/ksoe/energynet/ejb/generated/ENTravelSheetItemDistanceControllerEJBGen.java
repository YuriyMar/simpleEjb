
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTravelSheetItemDistanceDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetItemDistance;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItemDistanceShortList;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItemDistanceFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTravelSheetItemDistance;  
  * 	
  */

public abstract class ENTravelSheetItemDistanceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetItemDistanceControllerEJBGen() {}

  /*ENTravelSheetItemDistance. Добавить*/
  public int add(ENTravelSheetItemDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemDistanceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetItemDistance. Изменить*/
  public void save(ENTravelSheetItemDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemDistanceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance. Получить объект*/
  public ENTravelSheetItemDistance getObject(int code)
   {
    try
     {
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance. Получить полный список*/
  public ENTravelSheetItemDistanceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetItemDistance. Получить список по фильтру*/
  public ENTravelSheetItemDistanceShortList getFilteredList(ENTravelSheetItemDistanceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetItemDistance. Получить список для просмотра*/
  public ENTravelSheetItemDistanceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetItemDistance. Получить список для просмотра по фильтру*/
  public ENTravelSheetItemDistanceShortList getScrollableFilteredList(ENTravelSheetItemDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance. Получить список для просмотра по условию*/
  public ENTravelSheetItemDistanceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetItemDistanceFilter filterObject = new ENTravelSheetItemDistanceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTravelSheetItemDistance. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTravelSheetItemDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemDistanceDAO objectDAO = new ENTravelSheetItemDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
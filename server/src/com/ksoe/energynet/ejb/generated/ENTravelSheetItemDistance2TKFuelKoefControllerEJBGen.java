
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTravelSheetItemDistance2TKFuelKoefDAO;
import com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItemDistance2TKFuelKoefShortList;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItemDistance2TKFuelKoefFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTravelSheetItemDistance2TKFuelKoef;  
  * 	
  */

public abstract class ENTravelSheetItemDistance2TKFuelKoefControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetItemDistance2TKFuelKoefControllerEJBGen() {}

  /*ENTravelSheetItemDistance2TKFuelKoef. Добавить*/
  public int add(ENTravelSheetItemDistance2TKFuelKoef object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemDistance2TKFuelKoefValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetItemDistance2TKFuelKoef. Изменить*/
  public void save(ENTravelSheetItemDistance2TKFuelKoef object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemDistance2TKFuelKoefValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Получить объект*/
  public ENTravelSheetItemDistance2TKFuelKoef getObject(int code)
   {
    try
     {
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Получить полный список*/
  public ENTravelSheetItemDistance2TKFuelKoefShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Получить список по фильтру*/
  public ENTravelSheetItemDistance2TKFuelKoefShortList getFilteredList(ENTravelSheetItemDistance2TKFuelKoefFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Получить список для просмотра*/
  public ENTravelSheetItemDistance2TKFuelKoefShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetItemDistance2TKFuelKoef. Получить список для просмотра по фильтру*/
  public ENTravelSheetItemDistance2TKFuelKoefShortList getScrollableFilteredList(ENTravelSheetItemDistance2TKFuelKoefFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemDistance2TKFuelKoef. Получить список для просмотра по условию*/
  public ENTravelSheetItemDistance2TKFuelKoefShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetItemDistance2TKFuelKoefFilter filterObject = new ENTravelSheetItemDistance2TKFuelKoefFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTravelSheetItemDistance2TKFuelKoef. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTravelSheetItemDistance2TKFuelKoefFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemDistance2TKFuelKoefDAO objectDAO = new ENTravelSheetItemDistance2TKFuelKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetItemDistance2TKFuelKoef%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
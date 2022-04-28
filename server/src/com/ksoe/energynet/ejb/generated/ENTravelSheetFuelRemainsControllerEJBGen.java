
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelSheetFuelRemainsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetFuelRemainsFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetFuelRemainsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelSheetFuelRemains;  
  * 	
  */

public abstract class ENTravelSheetFuelRemainsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetFuelRemainsControllerEJBGen() {}

  /*ENTravelSheetFuelRemains. Добавить*/
  public int add(ENTravelSheetFuelRemains object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetFuelRemainsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetFuelRemains. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetFuelRemains. Изменить*/
  public void save(ENTravelSheetFuelRemains object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetFuelRemainsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetFuelRemains. Получить объект*/
  public ENTravelSheetFuelRemains getObject(int code)
   {
    try
     {
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetFuelRemains. Получить полный список*/
  public ENTravelSheetFuelRemainsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetFuelRemains. Получить список по фильтру*/
  public ENTravelSheetFuelRemainsShortList getFilteredList(ENTravelSheetFuelRemainsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetFuelRemains. Получить список для просмотра*/
  public ENTravelSheetFuelRemainsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetFuelRemains. Получить список для просмотра по фильтру*/
  public ENTravelSheetFuelRemainsShortList getScrollableFilteredList(ENTravelSheetFuelRemainsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetFuelRemains. Получить список для просмотра по условию*/
  public ENTravelSheetFuelRemainsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetFuelRemainsFilter filterObject = new ENTravelSheetFuelRemainsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTravelSheetFuelRemains. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTravelSheetFuelRemainsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetFuelRemainsDAO objectDAO = new ENTravelSheetFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTravelSheetFuelRemains%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
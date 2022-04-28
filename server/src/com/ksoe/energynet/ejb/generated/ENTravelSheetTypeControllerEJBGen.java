
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelSheetTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelSheetType;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelSheetType;  
  * 	
  */

public abstract class ENTravelSheetTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetTypeControllerEJBGen() {}

  /*ENTravelSheetType. Добавить*/
  public int add(ENTravelSheetType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetTypeDAO objectDAO = new ENTravelSheetTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetTypeDAO objectDAO = new ENTravelSheetTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetType. Изменить*/
  public void save(ENTravelSheetType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetTypeDAO objectDAO = new ENTravelSheetTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetType. Получить объект*/
  public ENTravelSheetType getObject(int code)
   {
    try
     {
      ENTravelSheetTypeDAO objectDAO = new ENTravelSheetTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetType. Получить полный список*/
  public ENTravelSheetTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetType. Получить список по фильтру*/
  public ENTravelSheetTypeShortList getFilteredList(ENTravelSheetTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetType. Получить список для просмотра*/
  public ENTravelSheetTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetType. Получить список для просмотра по фильтру*/
  public ENTravelSheetTypeShortList getScrollableFilteredList(ENTravelSheetTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetTypeDAO objectDAO = new ENTravelSheetTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetType. Получить список для просмотра по условию*/
  public ENTravelSheetTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetTypeFilter filterObject = new ENTravelSheetTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
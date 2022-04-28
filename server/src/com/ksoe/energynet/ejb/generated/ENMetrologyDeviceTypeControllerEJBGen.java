
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMetrologyDeviceTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMetrologyDeviceType;
import com.ksoe.energynet.valueobject.filter.ENMetrologyDeviceTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENMetrologyDeviceTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMetrologyDeviceType;  
  * 	
  */

public abstract class ENMetrologyDeviceTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMetrologyDeviceTypeControllerEJBGen() {}

  /*ENMetrologyDeviceType. Добавить*/
  public int add(ENMetrologyDeviceType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyDeviceTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyDeviceTypeDAO objectDAO = new ENMetrologyDeviceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMetrologyDeviceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDeviceType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMetrologyDeviceTypeDAO objectDAO = new ENMetrologyDeviceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMetrologyDeviceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMetrologyDeviceType. Изменить*/
  public void save(ENMetrologyDeviceType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyDeviceTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyDeviceTypeDAO objectDAO = new ENMetrologyDeviceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMetrologyDeviceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDeviceType. Получить объект*/
  public ENMetrologyDeviceType getObject(int code)
   {
    try
     {
      ENMetrologyDeviceTypeDAO objectDAO = new ENMetrologyDeviceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMetrologyDeviceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDeviceType. Получить полный список*/
  public ENMetrologyDeviceTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMetrologyDeviceType. Получить список по фильтру*/
  public ENMetrologyDeviceTypeShortList getFilteredList(ENMetrologyDeviceTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMetrologyDeviceType. Получить список для просмотра*/
  public ENMetrologyDeviceTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMetrologyDeviceType. Получить список для просмотра по фильтру*/
  public ENMetrologyDeviceTypeShortList getScrollableFilteredList(ENMetrologyDeviceTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMetrologyDeviceTypeDAO objectDAO = new ENMetrologyDeviceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMetrologyDeviceType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDeviceType. Получить список для просмотра по условию*/
  public ENMetrologyDeviceTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMetrologyDeviceTypeFilter filterObject = new ENMetrologyDeviceTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
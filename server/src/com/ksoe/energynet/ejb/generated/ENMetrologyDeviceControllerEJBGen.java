
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMetrologyDeviceDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMetrologyDevice;
import com.ksoe.energynet.valueobject.filter.ENMetrologyDeviceFilter;
import com.ksoe.energynet.valueobject.lists.ENMetrologyDeviceShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMetrologyDevice;  
  * 	
  */

public abstract class ENMetrologyDeviceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMetrologyDeviceControllerEJBGen() {}

  /*ENMetrologyDevice. Добавить*/
  public int add(ENMetrologyDevice object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyDeviceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyDeviceDAO objectDAO = new ENMetrologyDeviceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMetrologyDevice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDevice. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMetrologyDeviceDAO objectDAO = new ENMetrologyDeviceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMetrologyDevice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMetrologyDevice. Изменить*/
  public void save(ENMetrologyDevice object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyDeviceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyDeviceDAO objectDAO = new ENMetrologyDeviceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMetrologyDevice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDevice. Получить объект*/
  public ENMetrologyDevice getObject(int code)
   {
    try
     {
      ENMetrologyDeviceDAO objectDAO = new ENMetrologyDeviceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMetrologyDevice%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDevice. Получить полный список*/
  public ENMetrologyDeviceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMetrologyDevice. Получить список по фильтру*/
  public ENMetrologyDeviceShortList getFilteredList(ENMetrologyDeviceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMetrologyDevice. Получить список для просмотра*/
  public ENMetrologyDeviceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMetrologyDevice. Получить список для просмотра по фильтру*/
  public ENMetrologyDeviceShortList getScrollableFilteredList(ENMetrologyDeviceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMetrologyDeviceDAO objectDAO = new ENMetrologyDeviceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMetrologyDevice%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyDevice. Получить список для просмотра по условию*/
  public ENMetrologyDeviceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMetrologyDeviceFilter filterObject = new ENMetrologyDeviceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
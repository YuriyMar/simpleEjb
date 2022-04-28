
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENRecordPointBytDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENRecordPointByt;
import com.ksoe.energynet.valueobject.filter.ENRecordPointBytFilter;
import com.ksoe.energynet.valueobject.lists.ENRecordPointBytShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENRecordPointByt;  
  * 	
  */

public abstract class ENRecordPointBytControllerEJBGen extends GenericSessionStatelessBean
{
  public ENRecordPointBytControllerEJBGen() {}

  /*ENRecordPointByt. Добавить*/
  public int add(ENRecordPointByt object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRecordPointBytValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRecordPointBytDAO objectDAO = new ENRecordPointBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENRecordPointByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointByt. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENRecordPointBytDAO objectDAO = new ENRecordPointBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENRecordPointByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENRecordPointByt. Изменить*/
  public void save(ENRecordPointByt object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRecordPointBytValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRecordPointBytDAO objectDAO = new ENRecordPointBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENRecordPointByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointByt. Получить объект*/
  public ENRecordPointByt getObject(int code)
   {
    try
     {
      ENRecordPointBytDAO objectDAO = new ENRecordPointBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENRecordPointByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointByt. Получить полный список*/
  public ENRecordPointBytShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENRecordPointByt. Получить список по фильтру*/
  public ENRecordPointBytShortList getFilteredList(ENRecordPointBytFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENRecordPointByt. Получить список для просмотра*/
  public ENRecordPointBytShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENRecordPointByt. Получить список для просмотра по фильтру*/
  public ENRecordPointBytShortList getScrollableFilteredList(ENRecordPointBytFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRecordPointBytDAO objectDAO = new ENRecordPointBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENRecordPointByt%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointByt. Получить список для просмотра по условию*/
  public ENRecordPointBytShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENRecordPointBytFilter filterObject = new ENRecordPointBytFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
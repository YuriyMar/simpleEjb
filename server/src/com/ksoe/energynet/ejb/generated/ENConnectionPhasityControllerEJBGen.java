
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENConnectionPhasityDAO;
import com.ksoe.energynet.valueobject.ENConnectionPhasity;
import com.ksoe.energynet.valueobject.lists.ENConnectionPhasityShortList;
import com.ksoe.energynet.valueobject.filter.ENConnectionPhasityFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENConnectionPhasity;  
  * 	
  */

public abstract class ENConnectionPhasityControllerEJBGen extends GenericSessionStatelessBean
{
  public ENConnectionPhasityControllerEJBGen() {}

  /*ENConnectionPhasity. Добавить*/
  public int add(ENConnectionPhasity object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionPhasityValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionPhasity. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENConnectionPhasity. Изменить*/
  public void save(ENConnectionPhasity object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionPhasityValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionPhasity. Получить объект*/
  public ENConnectionPhasity getObject(int code)
   {
    try
     {
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionPhasity. Получить полный список*/
  public ENConnectionPhasityShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENConnectionPhasity. Получить список по фильтру*/
  public ENConnectionPhasityShortList getFilteredList(ENConnectionPhasityFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENConnectionPhasity. Получить список для просмотра*/
  public ENConnectionPhasityShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENConnectionPhasity. Получить список для просмотра по фильтру*/
  public ENConnectionPhasityShortList getScrollableFilteredList(ENConnectionPhasityFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionPhasity. Получить список для просмотра по условию*/
  public ENConnectionPhasityShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENConnectionPhasityFilter filterObject = new ENConnectionPhasityFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENConnectionPhasity. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENConnectionPhasityFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionPhasityDAO objectDAO = new ENConnectionPhasityDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionPhasity%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
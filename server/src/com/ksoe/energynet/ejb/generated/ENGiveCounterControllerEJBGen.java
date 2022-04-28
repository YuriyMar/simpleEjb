
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENGiveCounterDAO;
import com.ksoe.energynet.valueobject.ENGiveCounter;
import com.ksoe.energynet.valueobject.lists.ENGiveCounterShortList;
import com.ksoe.energynet.valueobject.filter.ENGiveCounterFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENGiveCounter;  
  * 	
  */

public abstract class ENGiveCounterControllerEJBGen extends GenericSessionStatelessBean
{
  public ENGiveCounterControllerEJBGen() {}

  /*ENGiveCounter. Добавить*/
  public int add(ENGiveCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENGiveCounterValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENGiveCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENGiveCounter. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENGiveCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENGiveCounter. Изменить*/
  public void save(ENGiveCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENGiveCounterValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENGiveCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENGiveCounter. Получить объект*/
  public ENGiveCounter getObject(int code)
   {
    try
     {
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENGiveCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENGiveCounter. Получить полный список*/
  public ENGiveCounterShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENGiveCounter. Получить список по фильтру*/
  public ENGiveCounterShortList getFilteredList(ENGiveCounterFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENGiveCounter. Получить список для просмотра*/
  public ENGiveCounterShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENGiveCounter. Получить список для просмотра по фильтру*/
  public ENGiveCounterShortList getScrollableFilteredList(ENGiveCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENGiveCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENGiveCounter. Получить список для просмотра по условию*/
  public ENGiveCounterShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENGiveCounterFilter filterObject = new ENGiveCounterFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENGiveCounter. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENGiveCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENGiveCounterDAO objectDAO = new ENGiveCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENGiveCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMetrologyCounterDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMetrologyCounter;
import com.ksoe.energynet.valueobject.filter.ENMetrologyCounterFilter;
import com.ksoe.energynet.valueobject.lists.ENMetrologyCounterShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMetrologyCounter;  
  * 	
  */

public abstract class ENMetrologyCounterControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMetrologyCounterControllerEJBGen() {}

  /*ENMetrologyCounter. Добавить*/
  public int add(ENMetrologyCounter object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyCounterValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyCounterDAO objectDAO = new ENMetrologyCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMetrologyCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyCounter. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMetrologyCounterDAO objectDAO = new ENMetrologyCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMetrologyCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMetrologyCounter. Изменить*/
  public void save(ENMetrologyCounter object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyCounterValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyCounterDAO objectDAO = new ENMetrologyCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMetrologyCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyCounter. Получить объект*/
  public ENMetrologyCounter getObject(int code)
   {
    try
     {
      ENMetrologyCounterDAO objectDAO = new ENMetrologyCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMetrologyCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyCounter. Получить полный список*/
  public ENMetrologyCounterShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMetrologyCounter. Получить список по фильтру*/
  public ENMetrologyCounterShortList getFilteredList(ENMetrologyCounterFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMetrologyCounter. Получить список для просмотра*/
  public ENMetrologyCounterShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMetrologyCounter. Получить список для просмотра по фильтру*/
  public ENMetrologyCounterShortList getScrollableFilteredList(ENMetrologyCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMetrologyCounterDAO objectDAO = new ENMetrologyCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMetrologyCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyCounter. Получить список для просмотра по условию*/
  public ENMetrologyCounterShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMetrologyCounterFilter filterObject = new ENMetrologyCounterFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
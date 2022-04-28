
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMetrologyObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMetrologyObject;
import com.ksoe.energynet.valueobject.filter.ENMetrologyObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENMetrologyObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMetrologyObject;  
  * 	
  */

public abstract class ENMetrologyObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMetrologyObjectControllerEJBGen() {}

  /*ENMetrologyObject. Добавить*/
  public int add(ENMetrologyObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyObjectDAO objectDAO = new ENMetrologyObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMetrologyObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMetrologyObjectDAO objectDAO = new ENMetrologyObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMetrologyObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMetrologyObject. Изменить*/
  public void save(ENMetrologyObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMetrologyObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMetrologyObjectDAO objectDAO = new ENMetrologyObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMetrologyObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyObject. Получить объект*/
  public ENMetrologyObject getObject(int code)
   {
    try
     {
      ENMetrologyObjectDAO objectDAO = new ENMetrologyObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMetrologyObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyObject. Получить полный список*/
  public ENMetrologyObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMetrologyObject. Получить список по фильтру*/
  public ENMetrologyObjectShortList getFilteredList(ENMetrologyObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMetrologyObject. Получить список для просмотра*/
  public ENMetrologyObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMetrologyObject. Получить список для просмотра по фильтру*/
  public ENMetrologyObjectShortList getScrollableFilteredList(ENMetrologyObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMetrologyObjectDAO objectDAO = new ENMetrologyObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMetrologyObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMetrologyObject. Получить список для просмотра по условию*/
  public ENMetrologyObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMetrologyObjectFilter filterObject = new ENMetrologyObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
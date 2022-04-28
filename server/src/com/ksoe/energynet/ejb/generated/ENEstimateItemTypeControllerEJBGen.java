
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Sat Sep 26 14:38:30 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItemTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItemType;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItemType;  
  * 	
  */

public abstract class ENEstimateItemTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItemTypeControllerEJBGen() {}

  /*ENEstimateItemType. Добавить*/
  public int add(ENEstimateItemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemTypeDAO objectDAO = new ENEstimateItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItemTypeDAO objectDAO = new ENEstimateItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItemType. Изменить*/
  public void save(ENEstimateItemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemTypeDAO objectDAO = new ENEstimateItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemType. Получить объект*/
  public ENEstimateItemType getObject(int code)
   {
    try
     {
      ENEstimateItemTypeDAO objectDAO = new ENEstimateItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemType. Получить полный список*/
  public ENEstimateItemTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItemType. Получить список по фильтру*/
  public ENEstimateItemTypeShortList getFilteredList(ENEstimateItemTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItemType. Получить список для просмотра*/
  public ENEstimateItemTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItemType. Получить список для просмотра по фильтру*/
  public ENEstimateItemTypeShortList getScrollableFilteredList(ENEstimateItemTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItemTypeDAO objectDAO = new ENEstimateItemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItemType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemType. Получить список для просмотра по условию*/
  public ENEstimateItemTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItemTypeFilter filterObject = new ENEstimateItemTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
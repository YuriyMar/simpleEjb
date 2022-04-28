
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Fri Sep 18 11:06:01 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENElementTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENElementType;
import com.ksoe.energynet.valueobject.filter.ENElementTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENElementTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENElementType;  
  * 	
  */

public abstract class ENElementTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENElementTypeControllerEJBGen() {}

  /*ENElementType. Добавить*/
  public int add(ENElementType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENElementTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENElementTypeDAO objectDAO = new ENElementTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENElementType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElementType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENElementTypeDAO objectDAO = new ENElementTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENElementType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENElementType. Изменить*/
  public void save(ENElementType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENElementTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENElementTypeDAO objectDAO = new ENElementTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENElementType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElementType. Получить объект*/
  public ENElementType getObject(int code)
   {
    try
     {
      ENElementTypeDAO objectDAO = new ENElementTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENElementType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElementType. Получить полный список*/
  public ENElementTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENElementType. Получить список по фильтру*/
  public ENElementTypeShortList getFilteredList(ENElementTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENElementType. Получить список для просмотра*/
  public ENElementTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENElementType. Получить список для просмотра по фильтру*/
  public ENElementTypeShortList getScrollableFilteredList(ENElementTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENElementTypeDAO objectDAO = new ENElementTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENElementType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElementType. Получить список для просмотра по условию*/
  public ENElementTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENElementTypeFilter filterObject = new ENElementTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
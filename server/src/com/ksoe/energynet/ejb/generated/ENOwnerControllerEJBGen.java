
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Wed Sep 30 10:10:53 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENOwnerDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENOwner;
import com.ksoe.energynet.valueobject.filter.ENOwnerFilter;
import com.ksoe.energynet.valueobject.lists.ENOwnerShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENOwner;  
  * 	
  */

public abstract class ENOwnerControllerEJBGen extends GenericSessionStatelessBean
{
  public ENOwnerControllerEJBGen() {}

  /*ENOwner. Добавить*/
  public int add(ENOwner object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOwnerValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOwnerDAO objectDAO = new ENOwnerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENOwner%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOwner. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENOwnerDAO objectDAO = new ENOwnerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENOwner%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENOwner. Изменить*/
  public void save(ENOwner object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOwnerValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOwnerDAO objectDAO = new ENOwnerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENOwner%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOwner. Получить объект*/
  public ENOwner getObject(int code)
   {
    try
     {
      ENOwnerDAO objectDAO = new ENOwnerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENOwner%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOwner. Получить полный список*/
  public ENOwnerShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENOwner. Получить список по фильтру*/
  public ENOwnerShortList getFilteredList(ENOwnerFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENOwner. Получить список для просмотра*/
  public ENOwnerShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENOwner. Получить список для просмотра по фильтру*/
  public ENOwnerShortList getScrollableFilteredList(ENOwnerFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENOwnerDAO objectDAO = new ENOwnerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENOwner%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOwner. Получить список для просмотра по условию*/
  public ENOwnerShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENOwnerFilter filterObject = new ENOwnerFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
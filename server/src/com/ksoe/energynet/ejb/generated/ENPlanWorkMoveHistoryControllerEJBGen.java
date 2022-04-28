
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Mon Oct 05 15:21:12 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkMoveHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkMoveHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkMoveHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkMoveHistory;  
  * 	
  */

public abstract class ENPlanWorkMoveHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkMoveHistoryControllerEJBGen() {}

  /*ENPlanWorkMoveHistory. Добавить*/
  public int add(ENPlanWorkMoveHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkMoveHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkMoveHistoryDAO objectDAO = new ENPlanWorkMoveHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkMoveHistoryDAO objectDAO = new ENPlanWorkMoveHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkMoveHistory. Изменить*/
  public void save(ENPlanWorkMoveHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkMoveHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkMoveHistoryDAO objectDAO = new ENPlanWorkMoveHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveHistory. Получить объект*/
  public ENPlanWorkMoveHistory getObject(int code)
   {
    try
     {
      ENPlanWorkMoveHistoryDAO objectDAO = new ENPlanWorkMoveHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveHistory. Получить полный список*/
  public ENPlanWorkMoveHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkMoveHistory. Получить список по фильтру*/
  public ENPlanWorkMoveHistoryShortList getFilteredList(ENPlanWorkMoveHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkMoveHistory. Получить список для просмотра*/
  public ENPlanWorkMoveHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkMoveHistory. Получить список для просмотра по фильтру*/
  public ENPlanWorkMoveHistoryShortList getScrollableFilteredList(ENPlanWorkMoveHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkMoveHistoryDAO objectDAO = new ENPlanWorkMoveHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkMoveHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveHistory. Получить список для просмотра по условию*/
  public ENPlanWorkMoveHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkMoveHistoryFilter filterObject = new ENPlanWorkMoveHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}

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

import com.ksoe.energynet.dataminer.ENPlanWorkMoveReasonDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkMoveReason;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkMoveReasonFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkMoveReasonShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkMoveReason;  
  * 	
  */

public abstract class ENPlanWorkMoveReasonControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkMoveReasonControllerEJBGen() {}

  /*ENPlanWorkMoveReason. Добавить*/
  public int add(ENPlanWorkMoveReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkMoveReasonValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkMoveReasonDAO objectDAO = new ENPlanWorkMoveReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkMoveReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveReason. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkMoveReasonDAO objectDAO = new ENPlanWorkMoveReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkMoveReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkMoveReason. Изменить*/
  public void save(ENPlanWorkMoveReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkMoveReasonValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkMoveReasonDAO objectDAO = new ENPlanWorkMoveReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkMoveReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveReason. Получить объект*/
  public ENPlanWorkMoveReason getObject(int code)
   {
    try
     {
      ENPlanWorkMoveReasonDAO objectDAO = new ENPlanWorkMoveReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkMoveReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveReason. Получить полный список*/
  public ENPlanWorkMoveReasonShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkMoveReason. Получить список по фильтру*/
  public ENPlanWorkMoveReasonShortList getFilteredList(ENPlanWorkMoveReasonFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkMoveReason. Получить список для просмотра*/
  public ENPlanWorkMoveReasonShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkMoveReason. Получить список для просмотра по фильтру*/
  public ENPlanWorkMoveReasonShortList getScrollableFilteredList(ENPlanWorkMoveReasonFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkMoveReasonDAO objectDAO = new ENPlanWorkMoveReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkMoveReason%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkMoveReason. Получить список для просмотра по условию*/
  public ENPlanWorkMoveReasonShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkMoveReasonFilter filterObject = new ENPlanWorkMoveReasonFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
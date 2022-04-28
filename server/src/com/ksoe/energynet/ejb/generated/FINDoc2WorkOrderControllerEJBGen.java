
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINDoc2WorkOrderDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINDoc2WorkOrder;
import com.ksoe.energynet.valueobject.filter.FINDoc2WorkOrderFilter;
import com.ksoe.energynet.valueobject.lists.FINDoc2WorkOrderShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINDoc2WorkOrder;  
  * 	
  */

public abstract class FINDoc2WorkOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public FINDoc2WorkOrderControllerEJBGen() {}

  /*FINDoc2WorkOrder. Добавить*/
  public int add(FINDoc2WorkOrder object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2WorkOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2WorkOrderDAO objectDAO = new FINDoc2WorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINDoc2WorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2WorkOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINDoc2WorkOrderDAO objectDAO = new FINDoc2WorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINDoc2WorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINDoc2WorkOrder. Изменить*/
  public void save(FINDoc2WorkOrder object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2WorkOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2WorkOrderDAO objectDAO = new FINDoc2WorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINDoc2WorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2WorkOrder. Получить объект*/
  public FINDoc2WorkOrder getObject(int code)
   {
    try
     {
      FINDoc2WorkOrderDAO objectDAO = new FINDoc2WorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINDoc2WorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2WorkOrder. Получить полный список*/
  public FINDoc2WorkOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINDoc2WorkOrder. Получить список по фильтру*/
  public FINDoc2WorkOrderShortList getFilteredList(FINDoc2WorkOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINDoc2WorkOrder. Получить список для просмотра*/
  public FINDoc2WorkOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINDoc2WorkOrder. Получить список для просмотра по фильтру*/
  public FINDoc2WorkOrderShortList getScrollableFilteredList(FINDoc2WorkOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDoc2WorkOrderDAO objectDAO = new FINDoc2WorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINDoc2WorkOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2WorkOrder. Получить список для просмотра по условию*/
  public FINDoc2WorkOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINDoc2WorkOrderFilter filterObject = new FINDoc2WorkOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
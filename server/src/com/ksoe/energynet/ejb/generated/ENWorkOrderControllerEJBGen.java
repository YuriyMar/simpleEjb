
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENWorkOrderDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENWorkOrder;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderFilter;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENWorkOrder;
  *
  */

public abstract class ENWorkOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENWorkOrderControllerEJBGen() {}

  /*ENWorkOrder. Добавить*/
  public int add(ENWorkOrder object)
   {
    try
     {
      ENWorkOrderDAO objectDAO = new ENWorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENWorkOrderDAO objectDAO = new ENWorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENWorkOrder. Изменить*/
  public void save(ENWorkOrder object)
   {
    try
     {
      ENWorkOrderDAO objectDAO = new ENWorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder. Получить объект*/
  public ENWorkOrder getObject(int code)
   {
    try
     {
      ENWorkOrderDAO objectDAO = new ENWorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder. Получить полный список*/
  public ENWorkOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENWorkOrder. Получить список по фильтру*/
  public ENWorkOrderShortList getFilteredList(ENWorkOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENWorkOrder. Получить список для просмотра*/
  public ENWorkOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENWorkOrder. Получить список для просмотра по фильтру*/
  public ENWorkOrderShortList getScrollableFilteredList(ENWorkOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWorkOrderDAO objectDAO = new ENWorkOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder. Получить список для просмотра по условию*/
  public ENWorkOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENWorkOrderFilter filterObject = new ENWorkOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}
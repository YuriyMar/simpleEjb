
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportOrderDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportOrder;
import com.ksoe.energynet.valueobject.filter.ENTransportOrderFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportOrderShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportOrder;  
  * 	
  */

public abstract class ENTransportOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportOrderControllerEJBGen() {}

  /*ENTransportOrder. Добавить*/
  public int add(ENTransportOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportOrder. Изменить*/
  public void save(ENTransportOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder. Получить объект*/
  public ENTransportOrder getObject(int code)
   {
    try
     {
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder. Получить полный список*/
  public ENTransportOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportOrder. Получить список по фильтру*/
  public ENTransportOrderShortList getFilteredList(ENTransportOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportOrder. Получить список для просмотра*/
  public ENTransportOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportOrder. Получить список для просмотра по фильтру*/
  public ENTransportOrderShortList getScrollableFilteredList(ENTransportOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportOrder. Получить список для просмотра по условию*/
  public ENTransportOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportOrderFilter filterObject = new ENTransportOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportOrder. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportOrderDAO objectDAO = new ENTransportOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
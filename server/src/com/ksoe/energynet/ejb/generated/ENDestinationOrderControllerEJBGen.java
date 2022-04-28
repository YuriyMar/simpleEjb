
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENDestinationOrderDAO;
import com.ksoe.energynet.valueobject.ENDestinationOrder;
import com.ksoe.energynet.valueobject.lists.ENDestinationOrderShortList;
import com.ksoe.energynet.valueobject.filter.ENDestinationOrderFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENDestinationOrder;  
  * 	
  */

public abstract class ENDestinationOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDestinationOrderControllerEJBGen() {}

  /*ENDestinationOrder. Добавить*/
  public int add(ENDestinationOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDestinationOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDestinationOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDestinationOrder. Изменить*/
  public void save(ENDestinationOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDestinationOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationOrder. Получить объект*/
  public ENDestinationOrder getObject(int code)
   {
    try
     {
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDestinationOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationOrder. Получить полный список*/
  public ENDestinationOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDestinationOrder. Получить список по фильтру*/
  public ENDestinationOrderShortList getFilteredList(ENDestinationOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDestinationOrder. Получить список для просмотра*/
  public ENDestinationOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDestinationOrder. Получить список для просмотра по фильтру*/
  public ENDestinationOrderShortList getScrollableFilteredList(ENDestinationOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDestinationOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationOrder. Получить список для просмотра по условию*/
  public ENDestinationOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDestinationOrderFilter filterObject = new ENDestinationOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENDestinationOrder. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENDestinationOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationOrderDAO objectDAO = new ENDestinationOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENDestinationOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
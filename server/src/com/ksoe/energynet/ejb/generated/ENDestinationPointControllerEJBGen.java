
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENDestinationPointDAO;
import com.ksoe.energynet.valueobject.ENDestinationPoint;
import com.ksoe.energynet.valueobject.lists.ENDestinationPointShortList;
import com.ksoe.energynet.valueobject.filter.ENDestinationPointFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENDestinationPoint;  
  * 	
  */

public abstract class ENDestinationPointControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDestinationPointControllerEJBGen() {}

  /*ENDestinationPoint. Добавить*/
  public int add(ENDestinationPoint object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationPointValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDestinationPoint%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDestinationPoint%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDestinationPoint. Изменить*/
  public void save(ENDestinationPoint object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationPointValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDestinationPoint%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint. Получить объект*/
  public ENDestinationPoint getObject(int code)
   {
    try
     {
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDestinationPoint%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint. Получить полный список*/
  public ENDestinationPointShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDestinationPoint. Получить список по фильтру*/
  public ENDestinationPointShortList getFilteredList(ENDestinationPointFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDestinationPoint. Получить список для просмотра*/
  public ENDestinationPointShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDestinationPoint. Получить список для просмотра по фильтру*/
  public ENDestinationPointShortList getScrollableFilteredList(ENDestinationPointFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDestinationPoint%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint. Получить список для просмотра по условию*/
  public ENDestinationPointShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDestinationPointFilter filterObject = new ENDestinationPointFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENDestinationPoint. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENDestinationPointFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationPointDAO objectDAO = new ENDestinationPointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENDestinationPoint%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
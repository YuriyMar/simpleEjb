
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItem2ENEstimateItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2ENEstimateItemFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItem2ENEstimateItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItem2ENEstimateItem;  
  * 	
  */

public abstract class ENEstimateItem2ENEstimateItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItem2ENEstimateItemControllerEJBGen() {}

  /*ENEstimateItem2ENEstimateItem. Добавить*/
  public int add(ENEstimateItem2ENEstimateItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2ENEstimateItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2ENEstimateItemDAO objectDAO = new ENEstimateItem2ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2ENEstimateItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItem2ENEstimateItemDAO objectDAO = new ENEstimateItem2ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItem2ENEstimateItem. Изменить*/
  public void save(ENEstimateItem2ENEstimateItem object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2ENEstimateItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2ENEstimateItemDAO objectDAO = new ENEstimateItem2ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2ENEstimateItem. Получить объект*/
  public ENEstimateItem2ENEstimateItem getObject(int code)
   {
    try
     {
      ENEstimateItem2ENEstimateItemDAO objectDAO = new ENEstimateItem2ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2ENEstimateItem. Получить полный список*/
  public ENEstimateItem2ENEstimateItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItem2ENEstimateItem. Получить список по фильтру*/
  public ENEstimateItem2ENEstimateItemShortList getFilteredList(ENEstimateItem2ENEstimateItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItem2ENEstimateItem. Получить список для просмотра*/
  public ENEstimateItem2ENEstimateItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItem2ENEstimateItem. Получить список для просмотра по фильтру*/
  public ENEstimateItem2ENEstimateItemShortList getScrollableFilteredList(ENEstimateItem2ENEstimateItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItem2ENEstimateItemDAO objectDAO = new ENEstimateItem2ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2ENEstimateItem. Получить список для просмотра по условию*/
  public ENEstimateItem2ENEstimateItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItem2ENEstimateItemFilter filterObject = new ENEstimateItem2ENEstimateItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENHumenItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENHumenItem;
import com.ksoe.energynet.valueobject.filter.ENHumenItemFilter;
import com.ksoe.energynet.valueobject.lists.ENHumenItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENHumenItem;  
  * 	
  */

public abstract class ENHumenItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENHumenItemControllerEJBGen() {}

  /*ENHumenItem. Добавить*/
  public int add(ENHumenItem object)
   {
    try
     {

/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENHumenItemValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENHumenItemDAO objectDAO = new ENHumenItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENHumenItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENHumenItemDAO objectDAO = new ENHumenItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENHumenItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENHumenItem. Изменить*/
  public void save(ENHumenItem object)
   {
    try
     {

/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENHumenItemValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENHumenItemDAO objectDAO = new ENHumenItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENHumenItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItem. Получить объект*/
  public ENHumenItem getObject(int code)
   {
    try
     {
      ENHumenItemDAO objectDAO = new ENHumenItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENHumenItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItem. Получить полный список*/
  public ENHumenItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENHumenItem. Получить список по фильтру*/
  public ENHumenItemShortList getFilteredList(ENHumenItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENHumenItem. Получить список для просмотра*/
  public ENHumenItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENHumenItem. Получить список для просмотра по фильтру*/
  public ENHumenItemShortList getScrollableFilteredList(ENHumenItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENHumenItemDAO objectDAO = new ENHumenItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENHumenItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItem. Получить список для просмотра по условию*/
  public ENHumenItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENHumenItemFilter filterObject = new ENHumenItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
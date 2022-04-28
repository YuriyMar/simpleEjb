
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENElementDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENElement;
import com.ksoe.energynet.valueobject.filter.ENElementFilter;
import com.ksoe.energynet.valueobject.lists.ENElementShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENElement;  
  * 	
  */

public abstract class ENElementControllerEJBGen extends GenericSessionStatelessBean
{
  public ENElementControllerEJBGen() {}

  /*ENElement. Добавить*/
  public int add(ENElement object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENElementValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENElementDAO objectDAO = new ENElementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENElement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElement. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENElementDAO objectDAO = new ENElementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENElement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENElement. Изменить*/
  public void save(ENElement object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENElementValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENElementDAO objectDAO = new ENElementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENElement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElement. Получить объект*/
  public ENElement getObject(int code)
   {
    try
     {
      ENElementDAO objectDAO = new ENElementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENElement%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElement. Получить полный список*/
  public ENElementShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENElement. Получить список по фильтру*/
  public ENElementShortList getFilteredList(ENElementFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENElement. Получить список для просмотра*/
  public ENElementShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENElement. Получить список для просмотра по фильтру*/
  public ENElementShortList getScrollableFilteredList(ENElementFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENElementDAO objectDAO = new ENElementDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENElement%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENElement. Получить список для просмотра по условию*/
  public ENElementShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENElementFilter filterObject = new ENElementFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
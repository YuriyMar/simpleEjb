
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENServicesFromSideObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENServicesFromSideObject;
import com.ksoe.energynet.valueobject.filter.ENServicesFromSideObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENServicesFromSideObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENServicesFromSideObject;  
  * 	
  */

public abstract class ENServicesFromSideObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENServicesFromSideObjectControllerEJBGen() {}

  /*ENServicesFromSideObject. Добавить*/
  public int add(ENServicesFromSideObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesFromSideObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesFromSideObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENServicesFromSideObject. Изменить*/
  public void save(ENServicesFromSideObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesFromSideObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesFromSideObject. Получить объект*/
  public ENServicesFromSideObject getObject(int code)
   {
    try
     {
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesFromSideObject. Получить полный список*/
  public ENServicesFromSideObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENServicesFromSideObject. Получить список по фильтру*/
  public ENServicesFromSideObjectShortList getFilteredList(ENServicesFromSideObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENServicesFromSideObject. Получить список для просмотра*/
  public ENServicesFromSideObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENServicesFromSideObject. Получить список для просмотра по фильтру*/
  public ENServicesFromSideObjectShortList getScrollableFilteredList(ENServicesFromSideObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesFromSideObject. Получить список для просмотра по условию*/
  public ENServicesFromSideObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENServicesFromSideObjectFilter filterObject = new ENServicesFromSideObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENServicesFromSideObject. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENServicesFromSideObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesFromSideObjectDAO objectDAO = new ENServicesFromSideObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesFromSideObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
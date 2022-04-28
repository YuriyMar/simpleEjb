
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPurchasesObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPurchasesObject;
import com.ksoe.energynet.valueobject.filter.ENPurchasesObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENPurchasesObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPurchasesObject;  
  * 	
  */

public abstract class ENPurchasesObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPurchasesObjectControllerEJBGen() {}

  /*ENPurchasesObject. Добавить*/
  public int add(ENPurchasesObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesObjectDAO objectDAO = new ENPurchasesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPurchasesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPurchasesObjectDAO objectDAO = new ENPurchasesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPurchasesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPurchasesObject. Изменить*/
  public void save(ENPurchasesObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesObjectDAO objectDAO = new ENPurchasesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPurchasesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObject. Получить объект*/
  public ENPurchasesObject getObject(int code)
   {
    try
     {
      ENPurchasesObjectDAO objectDAO = new ENPurchasesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPurchasesObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObject. Получить полный список*/
  public ENPurchasesObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPurchasesObject. Получить список по фильтру*/
  public ENPurchasesObjectShortList getFilteredList(ENPurchasesObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPurchasesObject. Получить список для просмотра*/
  public ENPurchasesObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPurchasesObject. Получить список для просмотра по фильтру*/
  public ENPurchasesObjectShortList getScrollableFilteredList(ENPurchasesObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPurchasesObjectDAO objectDAO = new ENPurchasesObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPurchasesObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObject. Получить список для просмотра по условию*/
  public ENPurchasesObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPurchasesObjectFilter filterObject = new ENPurchasesObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
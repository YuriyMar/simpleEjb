
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENResponsibles2FINContractsDAO;
import com.ksoe.energynet.valueobject.ENResponsibles2FINContracts;
import com.ksoe.energynet.valueobject.lists.ENResponsibles2FINContractsShortList;
import com.ksoe.energynet.valueobject.filter.ENResponsibles2FINContractsFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENResponsibles2FINContracts;  
  * 	
  */

public abstract class ENResponsibles2FINContractsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENResponsibles2FINContractsControllerEJBGen() {}

  /*ENResponsibles2FINContracts. Добавить*/
  public int add(ENResponsibles2FINContracts object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENResponsibles2FINContractsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsibles2FINContracts. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENResponsibles2FINContracts. Изменить*/
  public void save(ENResponsibles2FINContracts object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENResponsibles2FINContractsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsibles2FINContracts. Получить объект*/
  public ENResponsibles2FINContracts getObject(int code)
   {
    try
     {
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsibles2FINContracts. Получить полный список*/
  public ENResponsibles2FINContractsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENResponsibles2FINContracts. Получить список по фильтру*/
  public ENResponsibles2FINContractsShortList getFilteredList(ENResponsibles2FINContractsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENResponsibles2FINContracts. Получить список для просмотра*/
  public ENResponsibles2FINContractsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENResponsibles2FINContracts. Получить список для просмотра по фильтру*/
  public ENResponsibles2FINContractsShortList getScrollableFilteredList(ENResponsibles2FINContractsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsibles2FINContracts. Получить список для просмотра по условию*/
  public ENResponsibles2FINContractsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENResponsibles2FINContractsFilter filterObject = new ENResponsibles2FINContractsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENResponsibles2FINContracts. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENResponsibles2FINContractsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENResponsibles2FINContractsDAO objectDAO = new ENResponsibles2FINContractsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENResponsibles2FINContracts%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
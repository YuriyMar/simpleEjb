
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENConnectionTariffEntryDAO;
import com.ksoe.energynet.valueobject.ENConnectionTariffEntry;
import com.ksoe.energynet.valueobject.lists.ENConnectionTariffEntryShortList;
import com.ksoe.energynet.valueobject.filter.ENConnectionTariffEntryFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENConnectionTariffEntry;  
  * 	
  */

public abstract class ENConnectionTariffEntryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENConnectionTariffEntryControllerEJBGen() {}

  /*ENConnectionTariffEntry. Добавить*/
  public int add(ENConnectionTariffEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionTariffEntryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionTariffEntry. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENConnectionTariffEntry. Изменить*/
  public void save(ENConnectionTariffEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionTariffEntryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionTariffEntry. Получить объект*/
  public ENConnectionTariffEntry getObject(int code)
   {
    try
     {
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionTariffEntry. Получить полный список*/
  public ENConnectionTariffEntryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENConnectionTariffEntry. Получить список по фильтру*/
  public ENConnectionTariffEntryShortList getFilteredList(ENConnectionTariffEntryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENConnectionTariffEntry. Получить список для просмотра*/
  public ENConnectionTariffEntryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENConnectionTariffEntry. Получить список для просмотра по фильтру*/
  public ENConnectionTariffEntryShortList getScrollableFilteredList(ENConnectionTariffEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionTariffEntry. Получить список для просмотра по условию*/
  public ENConnectionTariffEntryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENConnectionTariffEntryFilter filterObject = new ENConnectionTariffEntryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENConnectionTariffEntry. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENConnectionTariffEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionTariffEntryDAO objectDAO = new ENConnectionTariffEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionTariffEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENStandardConstEntryDAO;
import com.ksoe.energynet.valueobject.ENStandardConstEntry;
import com.ksoe.energynet.valueobject.lists.ENStandardConstEntryShortList;
import com.ksoe.energynet.valueobject.filter.ENStandardConstEntryFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENStandardConstEntry;  
  * 	
  */

public abstract class ENStandardConstEntryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENStandardConstEntryControllerEJBGen() {}

  /*ENStandardConstEntry. Добавить*/
  public int add(ENStandardConstEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstEntryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENStandardConstEntry. Изменить*/
  public void save(ENStandardConstEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstEntryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. Получить объект*/
  public ENStandardConstEntry getObject(int code)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. Получить полный список*/
  public ENStandardConstEntryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENStandardConstEntry. Получить список по фильтру*/
  public ENStandardConstEntryShortList getFilteredList(ENStandardConstEntryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENStandardConstEntry. Получить список для просмотра*/
  public ENStandardConstEntryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENStandardConstEntry. Получить список для просмотра по фильтру*/
  public ENStandardConstEntryShortList getScrollableFilteredList(ENStandardConstEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. Получить список для просмотра по условию*/
  public ENStandardConstEntryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENStandardConstEntryFilter filterObject = new ENStandardConstEntryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENStandardConstEntry. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENStandardConstEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
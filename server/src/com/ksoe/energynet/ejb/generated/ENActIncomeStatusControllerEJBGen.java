
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActIncomeStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActIncomeStatus;
import com.ksoe.energynet.valueobject.filter.ENActIncomeStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENActIncomeStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActIncomeStatus;  
  * 	
  */

public abstract class ENActIncomeStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActIncomeStatusControllerEJBGen() {}

  /*ENActIncomeStatus. Добавить*/
  public int add(ENActIncomeStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActIncomeStatus. Изменить*/
  public void save(ENActIncomeStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeStatus. Получить объект*/
  public ENActIncomeStatus getObject(int code)
   {
    try
     {
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeStatus. Получить полный список*/
  public ENActIncomeStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActIncomeStatus. Получить список по фильтру*/
  public ENActIncomeStatusShortList getFilteredList(ENActIncomeStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActIncomeStatus. Получить список для просмотра*/
  public ENActIncomeStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActIncomeStatus. Получить список для просмотра по фильтру*/
  public ENActIncomeStatusShortList getScrollableFilteredList(ENActIncomeStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeStatus. Получить список для просмотра по условию*/
  public ENActIncomeStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActIncomeStatusFilter filterObject = new ENActIncomeStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENActIncomeStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENActIncomeStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeStatusDAO objectDAO = new ENActIncomeStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomeStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
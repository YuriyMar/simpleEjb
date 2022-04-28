
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENAct2CostRecoveryDAO;
import com.ksoe.energynet.valueobject.ENAct2CostRecovery;
import com.ksoe.energynet.valueobject.lists.ENAct2CostRecoveryShortList;
import com.ksoe.energynet.valueobject.filter.ENAct2CostRecoveryFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENAct2CostRecovery;  
  * 	
  */

public abstract class ENAct2CostRecoveryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAct2CostRecoveryControllerEJBGen() {}

  /*ENAct2CostRecovery. Добавить*/
  public int add(ENAct2CostRecovery object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2CostRecoveryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2CostRecovery. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAct2CostRecovery. Изменить*/
  public void save(ENAct2CostRecovery object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2CostRecoveryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2CostRecovery. Получить объект*/
  public ENAct2CostRecovery getObject(int code)
   {
    try
     {
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2CostRecovery. Получить полный список*/
  public ENAct2CostRecoveryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAct2CostRecovery. Получить список по фильтру*/
  public ENAct2CostRecoveryShortList getFilteredList(ENAct2CostRecoveryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAct2CostRecovery. Получить список для просмотра*/
  public ENAct2CostRecoveryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAct2CostRecovery. Получить список для просмотра по фильтру*/
  public ENAct2CostRecoveryShortList getScrollableFilteredList(ENAct2CostRecoveryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2CostRecovery. Получить список для просмотра по условию*/
  public ENAct2CostRecoveryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAct2CostRecoveryFilter filterObject = new ENAct2CostRecoveryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAct2CostRecovery. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAct2CostRecoveryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAct2CostRecoveryDAO objectDAO = new ENAct2CostRecoveryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAct2CostRecovery%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
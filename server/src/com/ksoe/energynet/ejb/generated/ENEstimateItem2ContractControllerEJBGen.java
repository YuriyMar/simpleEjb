
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItem2ContractDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItem2Contract;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2ContractFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItem2ContractShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItem2Contract;  
  * 	
  */

public abstract class ENEstimateItem2ContractControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItem2ContractControllerEJBGen() {}

  /*ENEstimateItem2Contract. Добавить*/
  public int add(ENEstimateItem2Contract object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2ContractValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2ContractDAO objectDAO = new ENEstimateItem2ContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem2Contract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Contract. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItem2ContractDAO objectDAO = new ENEstimateItem2ContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem2Contract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItem2Contract. Изменить*/
  public void save(ENEstimateItem2Contract object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2ContractValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2ContractDAO objectDAO = new ENEstimateItem2ContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem2Contract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Contract. Получить объект*/
  public ENEstimateItem2Contract getObject(int code)
   {
    try
     {
      ENEstimateItem2ContractDAO objectDAO = new ENEstimateItem2ContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2Contract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Contract. Получить полный список*/
  public ENEstimateItem2ContractShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItem2Contract. Получить список по фильтру*/
  public ENEstimateItem2ContractShortList getFilteredList(ENEstimateItem2ContractFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItem2Contract. Получить список для просмотра*/
  public ENEstimateItem2ContractShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItem2Contract. Получить список для просмотра по фильтру*/
  public ENEstimateItem2ContractShortList getScrollableFilteredList(ENEstimateItem2ContractFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItem2ContractDAO objectDAO = new ENEstimateItem2ContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem2Contract%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Contract. Получить список для просмотра по условию*/
  public ENEstimateItem2ContractShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItem2ContractFilter filterObject = new ENEstimateItem2ContractFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENContractDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENContract;
import com.ksoe.energynet.valueobject.filter.ENContractFilter;
import com.ksoe.energynet.valueobject.lists.ENContractShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENContract;  
  * 	
  */

public abstract class ENContractControllerEJBGen extends GenericSessionStatelessBean
{
  public ENContractControllerEJBGen() {}

  /*ENContract. Добавить*/
  public int add(ENContract object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENContractValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENContractDAO objectDAO = new ENContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENContract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContract. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENContractDAO objectDAO = new ENContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENContract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENContract. Изменить*/
  public void save(ENContract object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENContractValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENContractDAO objectDAO = new ENContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENContract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContract. Получить объект*/
  public ENContract getObject(int code)
   {
    try
     {
      ENContractDAO objectDAO = new ENContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENContract%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContract. Получить полный список*/
  public ENContractShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENContract. Получить список по фильтру*/
  public ENContractShortList getFilteredList(ENContractFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENContract. Получить список для просмотра*/
  public ENContractShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENContract. Получить список для просмотра по фильтру*/
  public ENContractShortList getScrollableFilteredList(ENContractFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENContractDAO objectDAO = new ENContractDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENContract%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContract. Получить список для просмотра по условию*/
  public ENContractShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENContractFilter filterObject = new ENContractFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
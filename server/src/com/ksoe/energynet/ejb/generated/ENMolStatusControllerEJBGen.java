
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMolStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMolStatus;
import com.ksoe.energynet.valueobject.filter.ENMolStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENMolStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMolStatus;  
  * 	
  */

public abstract class ENMolStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMolStatusControllerEJBGen() {}

  /*ENMolStatus. Добавить*/
  public int add(ENMolStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMolStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMolStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMolStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMolStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMolStatus. Изменить*/
  public void save(ENMolStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMolStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMolStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMolStatus. Получить объект*/
  public ENMolStatus getObject(int code)
   {
    try
     {
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMolStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMolStatus. Получить полный список*/
  public ENMolStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMolStatus. Получить список по фильтру*/
  public ENMolStatusShortList getFilteredList(ENMolStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMolStatus. Получить список для просмотра*/
  public ENMolStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMolStatus. Получить список для просмотра по фильтру*/
  public ENMolStatusShortList getScrollableFilteredList(ENMolStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMolStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMolStatus. Получить список для просмотра по условию*/
  public ENMolStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMolStatusFilter filterObject = new ENMolStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENMolStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENMolStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMolStatusDAO objectDAO = new ENMolStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENMolStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
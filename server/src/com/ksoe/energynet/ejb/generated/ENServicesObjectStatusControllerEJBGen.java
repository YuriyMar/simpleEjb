
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENServicesObjectStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENServicesObjectStatus;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENServicesObjectStatus;  
  * 	
  */

public abstract class ENServicesObjectStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENServicesObjectStatusControllerEJBGen() {}

  /*ENServicesObjectStatus. Добавить*/
  public int add(ENServicesObjectStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesObjectStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObjectStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENServicesObjectStatus. Изменить*/
  public void save(ENServicesObjectStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesObjectStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObjectStatus. Получить объект*/
  public ENServicesObjectStatus getObject(int code)
   {
    try
     {
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObjectStatus. Получить полный список*/
  public ENServicesObjectStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENServicesObjectStatus. Получить список по фильтру*/
  public ENServicesObjectStatusShortList getFilteredList(ENServicesObjectStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENServicesObjectStatus. Получить список для просмотра*/
  public ENServicesObjectStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENServicesObjectStatus. Получить список для просмотра по фильтру*/
  public ENServicesObjectStatusShortList getScrollableFilteredList(ENServicesObjectStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesObjectStatus. Получить список для просмотра по условию*/
  public ENServicesObjectStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENServicesObjectStatusFilter filterObject = new ENServicesObjectStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENServicesObjectStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENServicesObjectStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesObjectStatusDAO objectDAO = new ENServicesObjectStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesObjectStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
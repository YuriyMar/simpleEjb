
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTiresInstallStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTiresInstallStatus;
import com.ksoe.energynet.valueobject.filter.ENTiresInstallStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENTiresInstallStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTiresInstallStatus;  
  * 	
  */

public abstract class ENTiresInstallStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTiresInstallStatusControllerEJBGen() {}

  /*ENTiresInstallStatus. Добавить*/
  public int add(ENTiresInstallStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTiresInstallStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTiresInstallStatus. Изменить*/
  public void save(ENTiresInstallStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTiresInstallStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallStatus. Получить объект*/
  public ENTiresInstallStatus getObject(int code)
   {
    try
     {
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallStatus. Получить полный список*/
  public ENTiresInstallStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTiresInstallStatus. Получить список по фильтру*/
  public ENTiresInstallStatusShortList getFilteredList(ENTiresInstallStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTiresInstallStatus. Получить список для просмотра*/
  public ENTiresInstallStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTiresInstallStatus. Получить список для просмотра по фильтру*/
  public ENTiresInstallStatusShortList getScrollableFilteredList(ENTiresInstallStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallStatus. Получить список для просмотра по условию*/
  public ENTiresInstallStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTiresInstallStatusFilter filterObject = new ENTiresInstallStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTiresInstallStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTiresInstallStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTiresInstallStatusDAO objectDAO = new ENTiresInstallStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTiresInstallStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
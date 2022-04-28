
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTechConditionsServicesStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus;
import com.ksoe.energynet.valueobject.filter.ENTechConditionsServicesStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENTechConditionsServicesStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTechConditionsServicesStatus;  
  * 	
  */

public abstract class ENTechConditionsServicesStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechConditionsServicesStatusControllerEJBGen() {}

  /*ENTechConditionsServicesStatus. Добавить*/
  public int add(ENTechConditionsServicesStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechConditionsServicesStatus. Изменить*/
  public void save(ENTechConditionsServicesStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesStatus. Получить объект*/
  public ENTechConditionsServicesStatus getObject(int code)
   {
    try
     {
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesStatus. Получить полный список*/
  public ENTechConditionsServicesStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechConditionsServicesStatus. Получить список по фильтру*/
  public ENTechConditionsServicesStatusShortList getFilteredList(ENTechConditionsServicesStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechConditionsServicesStatus. Получить список для просмотра*/
  public ENTechConditionsServicesStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechConditionsServicesStatus. Получить список для просмотра по фильтру*/
  public ENTechConditionsServicesStatusShortList getScrollableFilteredList(ENTechConditionsServicesStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesStatus. Получить список для просмотра по условию*/
  public ENTechConditionsServicesStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechConditionsServicesStatusFilter filterObject = new ENTechConditionsServicesStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechConditionsServicesStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechConditionsServicesStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesStatusDAO objectDAO = new ENTechConditionsServicesStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechConditionsServicesStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
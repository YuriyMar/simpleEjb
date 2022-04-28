
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTechConditionsServicesTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTechConditionsServicesType;
import com.ksoe.energynet.valueobject.filter.ENTechConditionsServicesTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENTechConditionsServicesTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTechConditionsServicesType;  
  * 	
  */

public abstract class ENTechConditionsServicesTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechConditionsServicesTypeControllerEJBGen() {}

  /*ENTechConditionsServicesType. Добавить*/
  public int add(ENTechConditionsServicesType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechConditionsServicesType. Изменить*/
  public void save(ENTechConditionsServicesType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesType. Получить объект*/
  public ENTechConditionsServicesType getObject(int code)
   {
    try
     {
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesType. Получить полный список*/
  public ENTechConditionsServicesTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechConditionsServicesType. Получить список по фильтру*/
  public ENTechConditionsServicesTypeShortList getFilteredList(ENTechConditionsServicesTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechConditionsServicesType. Получить список для просмотра*/
  public ENTechConditionsServicesTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechConditionsServicesType. Получить список для просмотра по фильтру*/
  public ENTechConditionsServicesTypeShortList getScrollableFilteredList(ENTechConditionsServicesTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServicesType. Получить список для просмотра по условию*/
  public ENTechConditionsServicesTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechConditionsServicesTypeFilter filterObject = new ENTechConditionsServicesTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechConditionsServicesType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechConditionsServicesTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesTypeDAO objectDAO = new ENTechConditionsServicesTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechConditionsServicesType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
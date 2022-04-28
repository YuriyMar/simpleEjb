
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTechConditionsObjectsDAO;
import com.ksoe.energynet.valueobject.ENTechConditionsObjects;
import com.ksoe.energynet.valueobject.lists.ENTechConditionsObjectsShortList;
import com.ksoe.energynet.valueobject.filter.ENTechConditionsObjectsFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTechConditionsObjects;  
  * 	
  */

public abstract class ENTechConditionsObjectsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechConditionsObjectsControllerEJBGen() {}

  /*ENTechConditionsObjects. Добавить*/
  public int add(ENTechConditionsObjects object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsObjectsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsObjects. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechConditionsObjects. Изменить*/
  public void save(ENTechConditionsObjects object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsObjectsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsObjects. Получить объект*/
  public ENTechConditionsObjects getObject(int code)
   {
    try
     {
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsObjects. Получить полный список*/
  public ENTechConditionsObjectsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechConditionsObjects. Получить список по фильтру*/
  public ENTechConditionsObjectsShortList getFilteredList(ENTechConditionsObjectsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechConditionsObjects. Получить список для просмотра*/
  public ENTechConditionsObjectsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechConditionsObjects. Получить список для просмотра по фильтру*/
  public ENTechConditionsObjectsShortList getScrollableFilteredList(ENTechConditionsObjectsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsObjects. Получить список для просмотра по условию*/
  public ENTechConditionsObjectsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechConditionsObjectsFilter filterObject = new ENTechConditionsObjectsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechConditionsObjects. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechConditionsObjectsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsObjectsDAO objectDAO = new ENTechConditionsObjectsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechConditionsObjects%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
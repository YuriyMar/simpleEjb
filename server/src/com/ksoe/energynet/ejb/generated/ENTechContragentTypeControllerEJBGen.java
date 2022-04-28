
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTechContragentTypeDAO;
import com.ksoe.energynet.valueobject.ENTechContragentType;
import com.ksoe.energynet.valueobject.lists.ENTechContragentTypeShortList;
import com.ksoe.energynet.valueobject.filter.ENTechContragentTypeFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTechContragentType;  
  * 	
  */

public abstract class ENTechContragentTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechContragentTypeControllerEJBGen() {}

  /*ENTechContragentType. Добавить*/
  public int add(ENTechContragentType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechContragentTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechContragentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechContragentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechContragentType. Изменить*/
  public void save(ENTechContragentType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechContragentTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechContragentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentType. Получить объект*/
  public ENTechContragentType getObject(int code)
   {
    try
     {
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechContragentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentType. Получить полный список*/
  public ENTechContragentTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechContragentType. Получить список по фильтру*/
  public ENTechContragentTypeShortList getFilteredList(ENTechContragentTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechContragentType. Получить список для просмотра*/
  public ENTechContragentTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechContragentType. Получить список для просмотра по фильтру*/
  public ENTechContragentTypeShortList getScrollableFilteredList(ENTechContragentTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechContragentType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentType. Получить список для просмотра по условию*/
  public ENTechContragentTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechContragentTypeFilter filterObject = new ENTechContragentTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechContragentType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechContragentTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechContragentTypeDAO objectDAO = new ENTechContragentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechContragentType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
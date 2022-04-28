
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.CNRen2ENDepartmentDAO;
import com.ksoe.energynet.valueobject.CNRen2ENDepartment;
import com.ksoe.energynet.valueobject.lists.CNRen2ENDepartmentShortList;
import com.ksoe.energynet.valueobject.filter.CNRen2ENDepartmentFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for CNRen2ENDepartment;  
  * 	
  */

public abstract class CNRen2ENDepartmentControllerEJBGen extends GenericSessionStatelessBean
{
  public CNRen2ENDepartmentControllerEJBGen() {}

  /*CNRen2ENDepartment. Добавить*/
  public int add(CNRen2ENDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNRen2ENDepartmentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNRen2ENDepartment. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNRen2ENDepartment. Изменить*/
  public void save(CNRen2ENDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNRen2ENDepartmentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNRen2ENDepartment. Получить объект*/
  public CNRen2ENDepartment getObject(int code)
   {
    try
     {
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNRen2ENDepartment. Получить полный список*/
  public CNRen2ENDepartmentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNRen2ENDepartment. Получить список по фильтру*/
  public CNRen2ENDepartmentShortList getFilteredList(CNRen2ENDepartmentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNRen2ENDepartment. Получить список для просмотра*/
  public CNRen2ENDepartmentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNRen2ENDepartment. Получить список для просмотра по фильтру*/
  public CNRen2ENDepartmentShortList getScrollableFilteredList(CNRen2ENDepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNRen2ENDepartment. Получить список для просмотра по условию*/
  public CNRen2ENDepartmentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNRen2ENDepartmentFilter filterObject = new CNRen2ENDepartmentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ECNRen2ENDepartment. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(CNRen2ENDepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNRen2ENDepartmentDAO objectDAO = new CNRen2ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.CNRen2ENDepartment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
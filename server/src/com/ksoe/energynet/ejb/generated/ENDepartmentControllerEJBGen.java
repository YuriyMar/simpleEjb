
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Thu Oct 08 13:57:38 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDepartmentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDepartment;
import com.ksoe.energynet.valueobject.filter.ENDepartmentFilter;
import com.ksoe.energynet.valueobject.lists.ENDepartmentShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDepartment;  
  * 	
  */

public abstract class ENDepartmentControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDepartmentControllerEJBGen() {}

  /*ENDepartment. Добавить*/
  public int add(ENDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartmentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartmentDAO objectDAO = new ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDepartmentDAO objectDAO = new ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDepartment. Изменить*/
  public void save(ENDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartmentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartmentDAO objectDAO = new ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment. Получить объект*/
  public ENDepartment getObject(int code)
   {
    try
     {
      ENDepartmentDAO objectDAO = new ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      ENDepartment obj = objectDAO.getObject(code);
      //obj.name = new ManningTableLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile()).getDepartmentCodesDown(obj.code, "");
      return obj;
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment. Получить полный список*/
  public ENDepartmentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDepartment. Получить список по фильтру*/
  public ENDepartmentShortList getFilteredList(ENDepartmentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDepartment. Получить список для просмотра*/
  public ENDepartmentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDepartment. Получить список для просмотра по фильтру*/
  public ENDepartmentShortList getScrollableFilteredList(ENDepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDepartmentDAO objectDAO = new ENDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDepartment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment. Получить список для просмотра по условию*/
  public ENDepartmentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDepartmentFilter filterObject = new ENDepartmentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
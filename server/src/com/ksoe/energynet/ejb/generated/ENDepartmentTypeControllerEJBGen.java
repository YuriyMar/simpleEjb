
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

import com.ksoe.energynet.dataminer.ENDepartmentTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDepartmentType;
import com.ksoe.energynet.valueobject.filter.ENDepartmentTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENDepartmentTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDepartmentType;  
  * 	
  */

public abstract class ENDepartmentTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDepartmentTypeControllerEJBGen() {}

  /*ENDepartmentType. Добавить*/
  public int add(ENDepartmentType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartmentTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartmentTypeDAO objectDAO = new ENDepartmentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDepartmentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartmentType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDepartmentTypeDAO objectDAO = new ENDepartmentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDepartmentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDepartmentType. Изменить*/
  public void save(ENDepartmentType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartmentTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartmentTypeDAO objectDAO = new ENDepartmentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDepartmentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartmentType. Получить объект*/
  public ENDepartmentType getObject(int code)
   {
    try
     {
      ENDepartmentTypeDAO objectDAO = new ENDepartmentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDepartmentType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartmentType. Получить полный список*/
  public ENDepartmentTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDepartmentType. Получить список по фильтру*/
  public ENDepartmentTypeShortList getFilteredList(ENDepartmentTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDepartmentType. Получить список для просмотра*/
  public ENDepartmentTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDepartmentType. Получить список для просмотра по фильтру*/
  public ENDepartmentTypeShortList getScrollableFilteredList(ENDepartmentTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDepartmentTypeDAO objectDAO = new ENDepartmentTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDepartmentType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartmentType. Получить список для просмотра по условию*/
  public ENDepartmentTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDepartmentTypeFilter filterObject = new ENDepartmentTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
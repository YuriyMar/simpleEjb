
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMol2DepartmentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMol2Department;
import com.ksoe.energynet.valueobject.filter.ENMol2DepartmentFilter;
import com.ksoe.energynet.valueobject.lists.ENMol2DepartmentShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMol2Department;  
  * 	
  */

public abstract class ENMol2DepartmentControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMol2DepartmentControllerEJBGen() {}

  /*ENMol2Department. Добавить*/
  public int add(ENMol2Department object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMol2DepartmentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMol2Department%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMol2Department. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMol2Department%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMol2Department. Изменить*/
  public void save(ENMol2Department object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMol2DepartmentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMol2Department%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMol2Department. Получить объект*/
  public ENMol2Department getObject(int code)
   {
    try
     {
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMol2Department%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMol2Department. Получить полный список*/
  public ENMol2DepartmentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMol2Department. Получить список по фильтру*/
  public ENMol2DepartmentShortList getFilteredList(ENMol2DepartmentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMol2Department. Получить список для просмотра*/
  public ENMol2DepartmentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMol2Department. Получить список для просмотра по фильтру*/
  public ENMol2DepartmentShortList getScrollableFilteredList(ENMol2DepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMol2Department%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMol2Department. Получить список для просмотра по условию*/
  public ENMol2DepartmentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMol2DepartmentFilter filterObject = new ENMol2DepartmentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENMol2Department. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENMol2DepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMol2DepartmentDAO objectDAO = new ENMol2DepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENMol2Department%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
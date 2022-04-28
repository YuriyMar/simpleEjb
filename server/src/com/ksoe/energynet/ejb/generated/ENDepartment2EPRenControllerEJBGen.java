
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDepartment2EPRenDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDepartment2EPRen;
import com.ksoe.energynet.valueobject.filter.ENDepartment2EPRenFilter;
import com.ksoe.energynet.valueobject.lists.ENDepartment2EPRenShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDepartment2EPRen;  
  * 	
  */

public abstract class ENDepartment2EPRenControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDepartment2EPRenControllerEJBGen() {}

  /*ENDepartment2EPRen. Добавить*/
  public int add(ENDepartment2EPRen object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartment2EPRenValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment2EPRen. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDepartment2EPRen. Изменить*/
  public void save(ENDepartment2EPRen object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDepartment2EPRenValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment2EPRen. Получить объект*/
  public ENDepartment2EPRen getObject(int code)
   {
    try
     {
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment2EPRen. Получить полный список*/
  public ENDepartment2EPRenShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDepartment2EPRen. Получить список по фильтру*/
  public ENDepartment2EPRenShortList getFilteredList(ENDepartment2EPRenFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDepartment2EPRen. Получить список для просмотра*/
  public ENDepartment2EPRenShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDepartment2EPRen. Получить список для просмотра по фильтру*/
  public ENDepartment2EPRenShortList getScrollableFilteredList(ENDepartment2EPRenFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDepartment2EPRen. Получить список для просмотра по условию*/
  public ENDepartment2EPRenShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDepartment2EPRenFilter filterObject = new ENDepartment2EPRenFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENDepartment2EPRen. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENDepartment2EPRenFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDepartment2EPRenDAO objectDAO = new ENDepartment2EPRenDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENDepartment2EPRen%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
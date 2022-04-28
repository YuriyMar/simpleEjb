
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENManningTableDAO;
import com.ksoe.energynet.logic.ManningTableLogic;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENManningTable;
import com.ksoe.energynet.valueobject.filter.ENManningTableFilter;
import com.ksoe.energynet.valueobject.lists.ENManningTableShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENManningTable;  
  * 	
  */

public abstract class ENManningTableControllerEJBGen extends GenericSessionStatelessBean
{
  public ENManningTableControllerEJBGen() {}

  /*ENManningTable. Добавить*/
  public int add(ENManningTable object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManningTableValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManningTableDAO objectDAO = new ENManningTableDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENManningTable%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENManningTableDAO objectDAO = new ENManningTableDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENManningTable%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENManningTable. Изменить*/
  public void save(ENManningTable object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManningTableValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManningTableDAO objectDAO = new ENManningTableDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENManningTable%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable. Получить объект*/
  public ENManningTable getObject(int code)
   {
    try
     {
      ENManningTableDAO objectDAO = new ENManningTableDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      ENManningTable obj = objectDAO.getObject(code);
      
      obj.name = new ManningTableLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),getUserProfile()).getFullDepartmentName(obj.code); 
    	  
      return obj;
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENManningTable%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable. Получить полный список*/
  public ENManningTableShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENManningTable. Получить список по фильтру*/
  public ENManningTableShortList getFilteredList(ENManningTableFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENManningTable. Получить список для просмотра*/
  public ENManningTableShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENManningTable. Получить список для просмотра по фильтру*/
  public ENManningTableShortList getScrollableFilteredList(ENManningTableFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENManningTableDAO objectDAO = new ENManningTableDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENManningTable%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable. Получить список для просмотра по условию*/
  public ENManningTableShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENManningTableFilter filterObject = new ENManningTableFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
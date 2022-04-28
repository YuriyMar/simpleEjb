
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.CNSubsystemTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.CNSubsystemType;
import com.ksoe.energynet.valueobject.filter.CNSubsystemTypeFilter;
import com.ksoe.energynet.valueobject.lists.CNSubsystemTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for CNSubsystemType;  
  * 	
  */

public abstract class CNSubsystemTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public CNSubsystemTypeControllerEJBGen() {}

  /*CNSubsystemType. Добавить*/
  public int add(CNSubsystemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNSubsystemTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNSubsystemTypeDAO objectDAO = new CNSubsystemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNSubsystemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNSubsystemType. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNSubsystemTypeDAO objectDAO = new CNSubsystemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNSubsystemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNSubsystemType. Изменить*/
  public void save(CNSubsystemType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNSubsystemTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNSubsystemTypeDAO objectDAO = new CNSubsystemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNSubsystemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNSubsystemType. Получить объект*/
  public CNSubsystemType getObject(int code)
   {
    try
     {
      CNSubsystemTypeDAO objectDAO = new CNSubsystemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNSubsystemType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNSubsystemType. Получить полный список*/
  public CNSubsystemTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNSubsystemType. Получить список по фильтру*/
  public CNSubsystemTypeShortList getFilteredList(CNSubsystemTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNSubsystemType. Получить список для просмотра*/
  public CNSubsystemTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNSubsystemType. Получить список для просмотра по фильтру*/
  public CNSubsystemTypeShortList getScrollableFilteredList(CNSubsystemTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNSubsystemTypeDAO objectDAO = new CNSubsystemTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNSubsystemType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNSubsystemType. Получить список для просмотра по условию*/
  public CNSubsystemTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNSubsystemTypeFilter filterObject = new CNSubsystemTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
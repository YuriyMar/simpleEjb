
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINMolTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINMolType;
import com.ksoe.energynet.valueobject.filter.FINMolTypeFilter;
import com.ksoe.energynet.valueobject.lists.FINMolTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMolType;  
  * 	
  */

public abstract class FINMolTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMolTypeControllerEJBGen() {}

  /*FINMolType. Добавить*/
  public int add(FINMolType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolTypeDAO objectDAO = new FINMolTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMolType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolType. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINMolTypeDAO objectDAO = new FINMolTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMolType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINMolType. Изменить*/
  public void save(FINMolType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolTypeDAO objectDAO = new FINMolTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMolType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolType. Получить объект*/
  public FINMolType getObject(int code)
   {
    try
     {
      FINMolTypeDAO objectDAO = new FINMolTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMolType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolType. Получить полный список*/
  public FINMolTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMolType. Получить список по фильтру*/
  public FINMolTypeShortList getFilteredList(FINMolTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMolType. Получить список для просмотра*/
  public FINMolTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMolType. Получить список для просмотра по фильтру*/
  public FINMolTypeShortList getScrollableFilteredList(FINMolTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMolTypeDAO objectDAO = new FINMolTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINMolType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolType. Получить список для просмотра по условию*/
  public FINMolTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMolTypeFilter filterObject = new FINMolTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
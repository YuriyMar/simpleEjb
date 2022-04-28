
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINMolDataDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINMolData;
import com.ksoe.energynet.valueobject.filter.FINMolDataFilter;
import com.ksoe.energynet.valueobject.lists.FINMolDataShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMolData;  
  * 	
  */

public abstract class FINMolDataControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMolDataControllerEJBGen() {}

  /*FINMolData. Добавить*/
  public int add(FINMolData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolDataValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDataDAO objectDAO = new FINMolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolData. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINMolDataDAO objectDAO = new FINMolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINMolData. Изменить*/
  public void save(FINMolData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolDataValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDataDAO objectDAO = new FINMolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolData. Получить объект*/
  public FINMolData getObject(int code)
   {
    try
     {
      FINMolDataDAO objectDAO = new FINMolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolData. Получить полный список*/
  public FINMolDataShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMolData. Получить список по фильтру*/
  public FINMolDataShortList getFilteredList(FINMolDataFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMolData. Получить список для просмотра*/
  public FINMolDataShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMolData. Получить список для просмотра по фильтру*/
  public FINMolDataShortList getScrollableFilteredList(FINMolDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMolDataDAO objectDAO = new FINMolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINMolData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMolData. Получить список для просмотра по условию*/
  public FINMolDataShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMolDataFilter filterObject = new FINMolDataFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
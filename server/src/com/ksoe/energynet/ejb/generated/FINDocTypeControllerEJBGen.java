
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINDocTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINDocType;
import com.ksoe.energynet.valueobject.filter.FINDocTypeFilter;
import com.ksoe.energynet.valueobject.lists.FINDocTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINDocType;  
  * 	
  */

public abstract class FINDocTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public FINDocTypeControllerEJBGen() {}

  /*FINDocType. Добавить*/
  public int add(FINDocType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDocTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDocTypeDAO objectDAO = new FINDocTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINDocType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDocType. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINDocTypeDAO objectDAO = new FINDocTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINDocType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINDocType. Изменить*/
  public void save(FINDocType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDocTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDocTypeDAO objectDAO = new FINDocTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINDocType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDocType. Получить объект*/
  public FINDocType getObject(int code)
   {
    try
     {
      FINDocTypeDAO objectDAO = new FINDocTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINDocType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDocType. Получить полный список*/
  public FINDocTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINDocType. Получить список по фильтру*/
  public FINDocTypeShortList getFilteredList(FINDocTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINDocType. Получить список для просмотра*/
  public FINDocTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINDocType. Получить список для просмотра по фильтру*/
  public FINDocTypeShortList getScrollableFilteredList(FINDocTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDocTypeDAO objectDAO = new FINDocTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINDocType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDocType. Получить список для просмотра по условию*/
  public FINDocTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINDocTypeFilter filterObject = new FINDocTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
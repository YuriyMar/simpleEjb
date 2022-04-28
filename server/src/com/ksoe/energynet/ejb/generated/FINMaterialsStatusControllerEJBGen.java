
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINMaterialsStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINMaterialsStatus;
import com.ksoe.energynet.valueobject.filter.FINMaterialsStatusFilter;
import com.ksoe.energynet.valueobject.lists.FINMaterialsStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMaterialsStatus;  
  * 	
  */

public abstract class FINMaterialsStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMaterialsStatusControllerEJBGen() {}

  /*FINMaterialsStatus. Добавить*/
  public int add(FINMaterialsStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMaterialsStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMaterialsStatusDAO objectDAO = new FINMaterialsStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMaterialsStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterialsStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINMaterialsStatusDAO objectDAO = new FINMaterialsStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMaterialsStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINMaterialsStatus. Изменить*/
  public void save(FINMaterialsStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMaterialsStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMaterialsStatusDAO objectDAO = new FINMaterialsStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMaterialsStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterialsStatus. Получить объект*/
  public FINMaterialsStatus getObject(int code)
   {
    try
     {
      FINMaterialsStatusDAO objectDAO = new FINMaterialsStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMaterialsStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterialsStatus. Получить полный список*/
  public FINMaterialsStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMaterialsStatus. Получить список по фильтру*/
  public FINMaterialsStatusShortList getFilteredList(FINMaterialsStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMaterialsStatus. Получить список для просмотра*/
  public FINMaterialsStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMaterialsStatus. Получить список для просмотра по фильтру*/
  public FINMaterialsStatusShortList getScrollableFilteredList(FINMaterialsStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMaterialsStatusDAO objectDAO = new FINMaterialsStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINMaterialsStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterialsStatus. Получить список для просмотра по условию*/
  public FINMaterialsStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMaterialsStatusFilter filterObject = new FINMaterialsStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
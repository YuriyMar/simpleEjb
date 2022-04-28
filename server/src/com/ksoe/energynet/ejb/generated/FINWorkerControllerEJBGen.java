
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINWorkerDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINWorker;
import com.ksoe.energynet.valueobject.filter.FINWorkerFilter;
import com.ksoe.energynet.valueobject.lists.FINWorkerShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINWorker;  
  * 	
  */

public abstract class FINWorkerControllerEJBGen extends GenericSessionStatelessBean
{
  public FINWorkerControllerEJBGen() {}

  /*FINWorker. Добавить*/
  public int add(FINWorker object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINWorkerValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINWorkerDAO objectDAO = new FINWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINWorker. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINWorkerDAO objectDAO = new FINWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINWorker. Изменить*/
  public void save(FINWorker object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINWorkerValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINWorkerDAO objectDAO = new FINWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINWorker. Получить объект*/
  public FINWorker getObject(int code)
   {
    try
     {
      FINWorkerDAO objectDAO = new FINWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINWorker. Получить полный список*/
  public FINWorkerShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINWorker. Получить список по фильтру*/
  public FINWorkerShortList getFilteredList(FINWorkerFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINWorker. Получить список для просмотра*/
  public FINWorkerShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINWorker. Получить список для просмотра по фильтру*/
  public FINWorkerShortList getScrollableFilteredList(FINWorkerFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINWorkerDAO objectDAO = new FINWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINWorker%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINWorker. Получить список для просмотра по условию*/
  public FINWorkerShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINWorkerFilter filterObject = new FINWorkerFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
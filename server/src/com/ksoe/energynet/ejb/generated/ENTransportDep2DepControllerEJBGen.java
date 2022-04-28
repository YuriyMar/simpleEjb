
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportDep2DepDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportDep2Dep;
import com.ksoe.energynet.valueobject.filter.ENTransportDep2DepFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportDep2DepShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportDep2Dep;  
  * 	
  */

public abstract class ENTransportDep2DepControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportDep2DepControllerEJBGen() {}

  /*ENTransportDep2Dep. Добавить*/
  public int add(ENTransportDep2Dep object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportDep2DepValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDep2Dep. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportDep2Dep. Изменить*/
  public void save(ENTransportDep2Dep object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportDep2DepValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDep2Dep. Получить объект*/
  public ENTransportDep2Dep getObject(int code)
   {
    try
     {
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDep2Dep. Получить полный список*/
  public ENTransportDep2DepShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportDep2Dep. Получить список по фильтру*/
  public ENTransportDep2DepShortList getFilteredList(ENTransportDep2DepFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportDep2Dep. Получить список для просмотра*/
  public ENTransportDep2DepShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportDep2Dep. Получить список для просмотра по фильтру*/
  public ENTransportDep2DepShortList getScrollableFilteredList(ENTransportDep2DepFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDep2Dep. Получить список для просмотра по условию*/
  public ENTransportDep2DepShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportDep2DepFilter filterObject = new ENTransportDep2DepFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportDep2Dep. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportDep2DepFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportDep2DepDAO objectDAO = new ENTransportDep2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportDep2Dep%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
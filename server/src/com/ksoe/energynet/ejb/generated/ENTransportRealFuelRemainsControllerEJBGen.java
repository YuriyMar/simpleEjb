
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportRealFuelRemainsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportRealFuelRemains;
import com.ksoe.energynet.valueobject.filter.ENTransportRealFuelRemainsFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportRealFuelRemainsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportRealFuelRemains;  
  * 	
  */

public abstract class ENTransportRealFuelRemainsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportRealFuelRemainsControllerEJBGen() {}

  /*ENTransportRealFuelRemains. Добавить*/
  public int add(ENTransportRealFuelRemains object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRealFuelRemainsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRealFuelRemains. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportRealFuelRemains. Изменить*/
  public void save(ENTransportRealFuelRemains object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportRealFuelRemainsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRealFuelRemains. Получить объект*/
  public ENTransportRealFuelRemains getObject(int code)
   {
    try
     {
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRealFuelRemains. Получить полный список*/
  public ENTransportRealFuelRemainsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportRealFuelRemains. Получить список по фильтру*/
  public ENTransportRealFuelRemainsShortList getFilteredList(ENTransportRealFuelRemainsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportRealFuelRemains. Получить список для просмотра*/
  public ENTransportRealFuelRemainsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportRealFuelRemains. Получить список для просмотра по фильтру*/
  public ENTransportRealFuelRemainsShortList getScrollableFilteredList(ENTransportRealFuelRemainsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportRealFuelRemains. Получить список для просмотра по условию*/
  public ENTransportRealFuelRemainsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportRealFuelRemainsFilter filterObject = new ENTransportRealFuelRemainsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportRealFuelRemains. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTransportRealFuelRemainsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportRealFuelRemainsDAO objectDAO = new ENTransportRealFuelRemainsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportRealFuelRemains%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
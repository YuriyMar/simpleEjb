
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAutoTiresHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAutoTiresHistory;
import com.ksoe.energynet.valueobject.filter.ENAutoTiresHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENAutoTiresHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAutoTiresHistory;
  *
  */

public abstract class ENAutoTiresHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAutoTiresHistoryControllerEJBGen() {}

  /*ENAutoTiresHistory. Добавить*/
  public int add(ENAutoTiresHistory object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENAutoTiresHistory. Изменить*/
  public void save(ENAutoTiresHistory object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresHistory. Получить объект*/
  public ENAutoTiresHistory getObject(int code)
   {
    try
     {
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresHistory. Получить полный список*/
  public ENAutoTiresHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAutoTiresHistory. Получить список по фильтру*/
  public ENAutoTiresHistoryShortList getFilteredList(ENAutoTiresHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAutoTiresHistory. Получить список для просмотра*/
  public ENAutoTiresHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAutoTiresHistory. Получить список для просмотра по фильтру*/
  public ENAutoTiresHistoryShortList getScrollableFilteredList(ENAutoTiresHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresHistory. Получить список для просмотра по условию*/
  public ENAutoTiresHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAutoTiresHistoryFilter filterObject = new ENAutoTiresHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAutoTiresHistory. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAutoTiresHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresHistoryDAO objectDAO = new ENAutoTiresHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAutoTiresHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


}
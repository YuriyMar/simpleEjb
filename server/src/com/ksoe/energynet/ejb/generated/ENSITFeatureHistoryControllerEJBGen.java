
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITFeatureHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITFeatureHistory;
import com.ksoe.energynet.valueobject.filter.ENSITFeatureHistoryFilter;
import com.ksoe.energynet.valueobject.lists.ENSITFeatureHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITFeatureHistory;  
  * 	
  */

public abstract class ENSITFeatureHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITFeatureHistoryControllerEJBGen() {}

  /*ENSITFeatureHistory. Добавить*/
  public int add(ENSITFeatureHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureHistoryDAO objectDAO = new ENSITFeatureHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITFeatureHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureHistory. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITFeatureHistoryDAO objectDAO = new ENSITFeatureHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITFeatureHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITFeatureHistory. Изменить*/
  public void save(ENSITFeatureHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureHistoryDAO objectDAO = new ENSITFeatureHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITFeatureHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureHistory. Получить объект*/
  public ENSITFeatureHistory getObject(int code)
   {
    try
     {
      ENSITFeatureHistoryDAO objectDAO = new ENSITFeatureHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITFeatureHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureHistory. Получить полный список*/
  public ENSITFeatureHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITFeatureHistory. Получить список по фильтру*/
  public ENSITFeatureHistoryShortList getFilteredList(ENSITFeatureHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITFeatureHistory. Получить список для просмотра*/
  public ENSITFeatureHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITFeatureHistory. Получить список для просмотра по фильтру*/
  public ENSITFeatureHistoryShortList getScrollableFilteredList(ENSITFeatureHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITFeatureHistoryDAO objectDAO = new ENSITFeatureHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITFeatureHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureHistory. Получить список для просмотра по условию*/
  public ENSITFeatureHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITFeatureHistoryFilter filterObject = new ENSITFeatureHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
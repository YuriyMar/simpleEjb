
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENContragentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENContragent;
import com.ksoe.energynet.valueobject.filter.ENContragentFilter;
import com.ksoe.energynet.valueobject.lists.ENContragentShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENContragent;  
  * 	
  */

public abstract class ENContragentControllerEJBGen extends GenericSessionStatelessBean
{
  public ENContragentControllerEJBGen() {}

  /*ENContragent. Добавить*/
  public int add(ENContragent object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENContragentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENContragent%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContragent. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENContragent%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENContragent. Изменить*/
  public void save(ENContragent object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENContragentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENContragent%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContragent. Получить объект*/
  public ENContragent getObject(int code)
   {
    try
     {
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENContragent%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContragent. Получить полный список*/
  public ENContragentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENContragent. Получить список по фильтру*/
  public ENContragentShortList getFilteredList(ENContragentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENContragent. Получить список для просмотра*/
  public ENContragentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENContragent. Получить список для просмотра по фильтру*/
  public ENContragentShortList getScrollableFilteredList(ENContragentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENContragent%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENContragent. Получить список для просмотра по условию*/
  public ENContragentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENContragentFilter filterObject = new ENContragentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENContragent. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENContragentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENContragentDAO objectDAO = new ENContragentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENContragent%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
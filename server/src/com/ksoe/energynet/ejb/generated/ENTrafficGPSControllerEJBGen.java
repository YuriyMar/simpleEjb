
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTrafficGPSDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTrafficGPS;
import com.ksoe.energynet.valueobject.filter.ENTrafficGPSFilter;
import com.ksoe.energynet.valueobject.lists.ENTrafficGPSShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTrafficGPS;  
  * 	
  */

public abstract class ENTrafficGPSControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTrafficGPSControllerEJBGen() {}

  /*ENTrafficGPS. Добавить*/
  public int add(ENTrafficGPS object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTrafficGPSValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTrafficGPS%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrafficGPS. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTrafficGPS%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTrafficGPS. Изменить*/
  public void save(ENTrafficGPS object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTrafficGPSValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTrafficGPS%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrafficGPS. Получить объект*/
  public ENTrafficGPS getObject(int code)
   {
    try
     {
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTrafficGPS%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrafficGPS. Получить полный список*/
  public ENTrafficGPSShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTrafficGPS. Получить список по фильтру*/
  public ENTrafficGPSShortList getFilteredList(ENTrafficGPSFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTrafficGPS. Получить список для просмотра*/
  public ENTrafficGPSShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTrafficGPS. Получить список для просмотра по фильтру*/
  public ENTrafficGPSShortList getScrollableFilteredList(ENTrafficGPSFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTrafficGPS%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrafficGPS. Получить список для просмотра по условию*/
  public ENTrafficGPSShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTrafficGPSFilter filterObject = new ENTrafficGPSFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTrafficGPS. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTrafficGPSFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTrafficGPSDAO objectDAO = new ENTrafficGPSDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTrafficGPS%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
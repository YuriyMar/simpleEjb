
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENWarrantStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENWarrantStatus;
import com.ksoe.energynet.valueobject.filter.ENWarrantStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENWarrantStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENWarrantStatus;  
  * 	
  */

public abstract class ENWarrantStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENWarrantStatusControllerEJBGen() {}

  /*ENWarrantStatus. Добавить*/
  public int add(ENWarrantStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWarrantStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENWarrantStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWarrantStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENWarrantStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENWarrantStatus. Изменить*/
  public void save(ENWarrantStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWarrantStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWarrantStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWarrantStatus. Получить объект*/
  public ENWarrantStatus getObject(int code)
   {
    try
     {
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENWarrantStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWarrantStatus. Получить полный список*/
  public ENWarrantStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENWarrantStatus. Получить список по фильтру*/
  public ENWarrantStatusShortList getFilteredList(ENWarrantStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENWarrantStatus. Получить список для просмотра*/
  public ENWarrantStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENWarrantStatus. Получить список для просмотра по фильтру*/
  public ENWarrantStatusShortList getScrollableFilteredList(ENWarrantStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENWarrantStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWarrantStatus. Получить список для просмотра по условию*/
  public ENWarrantStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENWarrantStatusFilter filterObject = new ENWarrantStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENWarrantStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENWarrantStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWarrantStatusDAO objectDAO = new ENWarrantStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENWarrantStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
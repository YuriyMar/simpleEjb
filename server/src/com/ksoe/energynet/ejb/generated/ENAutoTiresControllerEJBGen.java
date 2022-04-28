
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAutoTiresDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAutoTires;
import com.ksoe.energynet.valueobject.filter.ENAutoTiresFilter;
import com.ksoe.energynet.valueobject.lists.ENAutoTiresShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAutoTires;  
  * 	
  */

public abstract class ENAutoTiresControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAutoTiresControllerEJBGen() {}

  /*ENAutoTires. Добавить*/
  public int add(ENAutoTires object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAutoTires%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTires. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAutoTires%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAutoTires. Изменить*/
  public void save(ENAutoTires object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAutoTires%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTires. Получить объект*/
  public ENAutoTires getObject(int code)
   {
    try
     {
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAutoTires%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTires. Получить полный список*/
  public ENAutoTiresShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAutoTires. Получить список по фильтру*/
  public ENAutoTiresShortList getFilteredList(ENAutoTiresFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAutoTires. Получить список для просмотра*/
  public ENAutoTiresShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAutoTires. Получить список для просмотра по фильтру*/
  public ENAutoTiresShortList getScrollableFilteredList(ENAutoTiresFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAutoTires%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTires. Получить список для просмотра по условию*/
  public ENAutoTiresShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAutoTiresFilter filterObject = new ENAutoTiresFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAutoTires. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAutoTiresFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresDAO objectDAO = new ENAutoTiresDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAutoTires%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
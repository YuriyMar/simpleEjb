
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENRouteBytDAO;
import com.ksoe.energynet.valueobject.ENRouteByt;
import com.ksoe.energynet.valueobject.lists.ENRouteBytShortList;
import com.ksoe.energynet.valueobject.filter.ENRouteBytFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENRouteByt;  
  * 	
  */

public abstract class ENRouteBytControllerEJBGen extends GenericSessionStatelessBean
{
  public ENRouteBytControllerEJBGen() {}

  /*ENRouteByt. Добавить*/
  public int add(ENRouteByt object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRouteBytValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENRouteByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteByt. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENRouteByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENRouteByt. Изменить*/
  public void save(ENRouteByt object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRouteBytValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENRouteByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteByt. Получить объект*/
  public ENRouteByt getObject(int code)
   {
    try
     {
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENRouteByt%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteByt. Получить полный список*/
  public ENRouteBytShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENRouteByt. Получить список по фильтру*/
  public ENRouteBytShortList getFilteredList(ENRouteBytFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENRouteByt. Получить список для просмотра*/
  public ENRouteBytShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENRouteByt. Получить список для просмотра по фильтру*/
  public ENRouteBytShortList getScrollableFilteredList(ENRouteBytFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENRouteByt%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteByt. Получить список для просмотра по условию*/
  public ENRouteBytShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENRouteBytFilter filterObject = new ENRouteBytFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENRouteByt. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENRouteBytFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRouteBytDAO objectDAO = new ENRouteBytDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENRouteByt%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
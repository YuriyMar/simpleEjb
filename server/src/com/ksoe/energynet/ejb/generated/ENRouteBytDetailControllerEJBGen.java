
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENRouteBytDetailDAO;
import com.ksoe.energynet.valueobject.ENRouteBytDetail;
import com.ksoe.energynet.valueobject.lists.ENRouteBytDetailShortList;
import com.ksoe.energynet.valueobject.filter.ENRouteBytDetailFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENRouteBytDetail;  
  * 	
  */

public abstract class ENRouteBytDetailControllerEJBGen extends GenericSessionStatelessBean
{
  public ENRouteBytDetailControllerEJBGen() {}

  /*ENRouteBytDetail. Добавить*/
  public int add(ENRouteBytDetail object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRouteBytDetailValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteBytDetail. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENRouteBytDetail. Изменить*/
  public void save(ENRouteBytDetail object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRouteBytDetailValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteBytDetail. Получить объект*/
  public ENRouteBytDetail getObject(int code)
   {
    try
     {
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteBytDetail. Получить полный список*/
  public ENRouteBytDetailShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENRouteBytDetail. Получить список по фильтру*/
  public ENRouteBytDetailShortList getFilteredList(ENRouteBytDetailFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENRouteBytDetail. Получить список для просмотра*/
  public ENRouteBytDetailShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENRouteBytDetail. Получить список для просмотра по фильтру*/
  public ENRouteBytDetailShortList getScrollableFilteredList(ENRouteBytDetailFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRouteBytDetail. Получить список для просмотра по условию*/
  public ENRouteBytDetailShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENRouteBytDetailFilter filterObject = new ENRouteBytDetailFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENRouteBytDetail. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENRouteBytDetailFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRouteBytDetailDAO objectDAO = new ENRouteBytDetailDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENRouteBytDetail%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
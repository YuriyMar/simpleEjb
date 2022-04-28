
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENReconstrModernOZDAO;
import com.ksoe.energynet.valueobject.ENReconstrModernOZ;
import com.ksoe.energynet.valueobject.ENReconstrModernOZStatus;
import com.ksoe.energynet.valueobject.lists.ENReconstrModernOZShortList;
import com.ksoe.energynet.valueobject.filter.ENReconstrModernOZFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENReconstrModernOZ;  
  * 	
  */

public abstract class ENReconstrModernOZControllerEJBGen extends GenericSessionStatelessBean
{
  public ENReconstrModernOZControllerEJBGen() {}

  /*ENReconstrModernOZ. Добавить*/
  public int add(ENReconstrModernOZ object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      object.statusRef.code = ENReconstrModernOZStatus.DRAFT;
      object.userGen = getUserProfile().userName;
      object.dateEdit = new Date();
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENReconstrModernOZ. Изменить*/
  public void save(ENReconstrModernOZ object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ. Получить объект*/
  public ENReconstrModernOZ getObject(int code)
   {
    try
     {
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ. Получить полный список*/
  public ENReconstrModernOZShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENReconstrModernOZ. Получить список по фильтру*/
  public ENReconstrModernOZShortList getFilteredList(ENReconstrModernOZFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENReconstrModernOZ. Получить список для просмотра*/
  public ENReconstrModernOZShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENReconstrModernOZ. Получить список для просмотра по фильтру*/
  public ENReconstrModernOZShortList getScrollableFilteredList(ENReconstrModernOZFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZ. Получить список для просмотра по условию*/
  public ENReconstrModernOZShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENReconstrModernOZFilter filterObject = new ENReconstrModernOZFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENReconstrModernOZ. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENReconstrModernOZFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZDAO objectDAO = new ENReconstrModernOZDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENReconstrModernOZ%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
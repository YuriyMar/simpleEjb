
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENReconstrModernOZStatusDAO;
import com.ksoe.energynet.valueobject.ENReconstrModernOZStatus;
import com.ksoe.energynet.valueobject.lists.ENReconstrModernOZStatusShortList;
import com.ksoe.energynet.valueobject.filter.ENReconstrModernOZStatusFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENReconstrModernOZStatus;  
  * 	
  */

public abstract class ENReconstrModernOZStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENReconstrModernOZStatusControllerEJBGen() {}

  /*ENReconstrModernOZStatus. Добавить*/
  public int add(ENReconstrModernOZStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENReconstrModernOZStatus. Изменить*/
  public void save(ENReconstrModernOZStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModernOZStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZStatus. Получить объект*/
  public ENReconstrModernOZStatus getObject(int code)
   {
    try
     {
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZStatus. Получить полный список*/
  public ENReconstrModernOZStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENReconstrModernOZStatus. Получить список по фильтру*/
  public ENReconstrModernOZStatusShortList getFilteredList(ENReconstrModernOZStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENReconstrModernOZStatus. Получить список для просмотра*/
  public ENReconstrModernOZStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENReconstrModernOZStatus. Получить список для просмотра по фильтру*/
  public ENReconstrModernOZStatusShortList getScrollableFilteredList(ENReconstrModernOZStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModernOZStatus. Получить список для просмотра по условию*/
  public ENReconstrModernOZStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENReconstrModernOZStatusFilter filterObject = new ENReconstrModernOZStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENReconstrModernOZStatus. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENReconstrModernOZStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModernOZStatusDAO objectDAO = new ENReconstrModernOZStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENReconstrModernOZStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
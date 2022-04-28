
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENReconstrModern2OSDataDAO;
import com.ksoe.energynet.valueobject.ENReconstrModern2OSData;
import com.ksoe.energynet.valueobject.lists.ENReconstrModern2OSDataShortList;
import com.ksoe.energynet.valueobject.filter.ENReconstrModern2OSDataFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENReconstrModern2OSData;  
  * 	
  */

public abstract class ENReconstrModern2OSDataControllerEJBGen extends GenericSessionStatelessBean
{
  public ENReconstrModern2OSDataControllerEJBGen() {}

  /*ENReconstrModern2OSData. Добавить*/
  public int add(ENReconstrModern2OSData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModern2OSDataValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModern2OSData. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENReconstrModern2OSData. Изменить*/
  public void save(ENReconstrModern2OSData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENReconstrModern2OSDataValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModern2OSData. Получить объект*/
  public ENReconstrModern2OSData getObject(int code)
   {
    try
     {
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModern2OSData. Получить полный список*/
  public ENReconstrModern2OSDataShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENReconstrModern2OSData. Получить список по фильтру*/
  public ENReconstrModern2OSDataShortList getFilteredList(ENReconstrModern2OSDataFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENReconstrModern2OSData. Получить список для просмотра*/
  public ENReconstrModern2OSDataShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENReconstrModern2OSData. Получить список для просмотра по фильтру*/
  public ENReconstrModern2OSDataShortList getScrollableFilteredList(ENReconstrModern2OSDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENReconstrModern2OSData. Получить список для просмотра по условию*/
  public ENReconstrModern2OSDataShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENReconstrModern2OSDataFilter filterObject = new ENReconstrModern2OSDataFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENReconstrModern2OSData. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENReconstrModern2OSDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENReconstrModern2OSDataDAO objectDAO = new ENReconstrModern2OSDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENReconstrModern2OSData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
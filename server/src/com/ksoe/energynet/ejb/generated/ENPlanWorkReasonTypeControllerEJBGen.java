
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkReasonTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkReasonType;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkReasonTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkReasonTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkReasonType;  
  * 	
  */

public abstract class ENPlanWorkReasonTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkReasonTypeControllerEJBGen() {}

  /*ENPlanWorkReasonType. Добавить*/
  public int add(ENPlanWorkReasonType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkReasonTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkReasonType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkReasonType. Изменить*/
  public void save(ENPlanWorkReasonType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkReasonTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkReasonType. Получить объект*/
  public ENPlanWorkReasonType getObject(int code)
   {
    try
     {
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkReasonType. Получить полный список*/
  public ENPlanWorkReasonTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkReasonType. Получить список по фильтру*/
  public ENPlanWorkReasonTypeShortList getFilteredList(ENPlanWorkReasonTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkReasonType. Получить список для просмотра*/
  public ENPlanWorkReasonTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkReasonType. Получить список для просмотра по фильтру*/
  public ENPlanWorkReasonTypeShortList getScrollableFilteredList(ENPlanWorkReasonTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkReasonType. Получить список для просмотра по условию*/
  public ENPlanWorkReasonTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkReasonTypeFilter filterObject = new ENPlanWorkReasonTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWorkReasonType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWorkReasonTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkReasonTypeDAO objectDAO = new ENPlanWorkReasonTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWorkReasonType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
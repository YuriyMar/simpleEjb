
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWork2FKOrderDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWork2FKOrder;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2FKOrderFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2FKOrderShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWork2FKOrder;  
  * 	
  */

public abstract class ENPlanWork2FKOrderControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWork2FKOrderControllerEJBGen() {}

  /*ENPlanWork2FKOrder. Добавить*/
  public int add(ENPlanWork2FKOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2FKOrderValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2FKOrder. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWork2FKOrder. Изменить*/
  public void save(ENPlanWork2FKOrder object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2FKOrderValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2FKOrder. Получить объект*/
  public ENPlanWork2FKOrder getObject(int code)
   {
    try
     {
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2FKOrder. Получить полный список*/
  public ENPlanWork2FKOrderShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWork2FKOrder. Получить список по фильтру*/
  public ENPlanWork2FKOrderShortList getFilteredList(ENPlanWork2FKOrderFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWork2FKOrder. Получить список для просмотра*/
  public ENPlanWork2FKOrderShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWork2FKOrder. Получить список для просмотра по фильтру*/
  public ENPlanWork2FKOrderShortList getScrollableFilteredList(ENPlanWork2FKOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2FKOrder. Получить список для просмотра по условию*/
  public ENPlanWork2FKOrderShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWork2FKOrderFilter filterObject = new ENPlanWork2FKOrderFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWork2FKOrder. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWork2FKOrderFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2FKOrderDAO objectDAO = new ENPlanWork2FKOrderDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2FKOrder%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
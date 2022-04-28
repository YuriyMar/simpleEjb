
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkItem2TKKoefDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2TKKoefFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2TKKoefShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkItem2TKKoef;  
  * 	
  */

public abstract class ENPlanWorkItem2TKKoefControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkItem2TKKoefControllerEJBGen() {}

  /*ENPlanWorkItem2TKKoef. Добавить*/
  public int add(ENPlanWorkItem2TKKoef object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2TKKoefValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2TKKoef. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkItem2TKKoef. Изменить*/
  public void save(ENPlanWorkItem2TKKoef object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkItem2TKKoefValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2TKKoef. Получить объект*/
  public ENPlanWorkItem2TKKoef getObject(int code)
   {
    try
     {
      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2TKKoef. Получить полный список*/
  public ENPlanWorkItem2TKKoefShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkItem2TKKoef. Получить список по фильтру*/
  public ENPlanWorkItem2TKKoefShortList getFilteredList(ENPlanWorkItem2TKKoefFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkItem2TKKoef. Получить список для просмотра*/
  public ENPlanWorkItem2TKKoefShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkItem2TKKoef. Получить список для просмотра по фильтру*/
  public ENPlanWorkItem2TKKoefShortList getScrollableFilteredList(ENPlanWorkItem2TKKoefFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkItem2TKKoef. Получить список для просмотра по условию*/
  public ENPlanWorkItem2TKKoefShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkItem2TKKoefFilter filterObject = new ENPlanWorkItem2TKKoefFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
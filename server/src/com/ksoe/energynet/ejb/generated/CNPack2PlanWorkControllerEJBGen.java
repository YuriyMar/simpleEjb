
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.CNPack2PlanWorkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.CNPack2PlanWork;
import com.ksoe.energynet.valueobject.filter.CNPack2PlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.CNPack2PlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for CNPack2PlanWork;  
  * 	
  */

public abstract class CNPack2PlanWorkControllerEJBGen extends GenericSessionStatelessBean
{
  public CNPack2PlanWorkControllerEJBGen() {}

  /*CNPack2PlanWork. Добавить*/
  public int add(CNPack2PlanWork object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPack2PlanWorkValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPack2PlanWorkDAO objectDAO = new CNPack2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNPack2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack2PlanWork. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNPack2PlanWorkDAO objectDAO = new CNPack2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNPack2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNPack2PlanWork. Изменить*/
  public void save(CNPack2PlanWork object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPack2PlanWorkValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPack2PlanWorkDAO objectDAO = new CNPack2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNPack2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack2PlanWork. Получить объект*/
  public CNPack2PlanWork getObject(int code)
   {
    try
     {
      CNPack2PlanWorkDAO objectDAO = new CNPack2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNPack2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack2PlanWork. Получить полный список*/
  public CNPack2PlanWorkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNPack2PlanWork. Получить список по фильтру*/
  public CNPack2PlanWorkShortList getFilteredList(CNPack2PlanWorkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNPack2PlanWork. Получить список для просмотра*/
  public CNPack2PlanWorkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNPack2PlanWork. Получить список для просмотра по фильтру*/
  public CNPack2PlanWorkShortList getScrollableFilteredList(CNPack2PlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNPack2PlanWorkDAO objectDAO = new CNPack2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNPack2PlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack2PlanWork. Получить список для просмотра по условию*/
  public CNPack2PlanWorkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNPack2PlanWorkFilter filterObject = new CNPack2PlanWorkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
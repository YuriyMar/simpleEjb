
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENWorkOrder2ENPlanWorkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork;
import com.ksoe.energynet.valueobject.filter.ENWorkOrder2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.ENWorkOrder2ENPlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENWorkOrder2ENPlanWork;  
  * 	
  */

public abstract class ENWorkOrder2ENPlanWorkControllerEJBGen extends GenericSessionStatelessBean
{
  public ENWorkOrder2ENPlanWorkControllerEJBGen() {}

  /*ENWorkOrder2ENPlanWork. Добавить*/
  public int add(ENWorkOrder2ENPlanWork object)
   {
    try
     {

/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkOrder2ENPlanWorkValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkOrder2ENPlanWorkDAO objectDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder2ENPlanWork. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENWorkOrder2ENPlanWorkDAO objectDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENWorkOrder2ENPlanWork. Изменить*/
  public void save(ENWorkOrder2ENPlanWork object)
   {
    try
     {

/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkOrder2ENPlanWorkValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkOrder2ENPlanWorkDAO objectDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder2ENPlanWork. Получить объект*/
  public ENWorkOrder2ENPlanWork getObject(int code)
   {
    try
     {
      ENWorkOrder2ENPlanWorkDAO objectDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder2ENPlanWork. Получить полный список*/
  public ENWorkOrder2ENPlanWorkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENWorkOrder2ENPlanWork. Получить список по фильтру*/
  public ENWorkOrder2ENPlanWorkShortList getFilteredList(ENWorkOrder2ENPlanWorkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENWorkOrder2ENPlanWork. Получить список для просмотра*/
  public ENWorkOrder2ENPlanWorkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENWorkOrder2ENPlanWork. Получить список для просмотра по фильтру*/
  public ENWorkOrder2ENPlanWorkShortList getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWorkOrder2ENPlanWorkDAO objectDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorkOrder2ENPlanWork. Получить список для просмотра по условию*/
  public ENWorkOrder2ENPlanWorkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENWorkOrder2ENPlanWorkFilter filterObject = new ENWorkOrder2ENPlanWorkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
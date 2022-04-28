
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAct2ENPlanWork;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2ENPlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAct2ENPlanWork;  
  * 	
  */

public abstract class ENAct2ENPlanWorkControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAct2ENPlanWorkControllerEJBGen() {}

  /*ENAct2ENPlanWork. Добавить*/
  public int add(ENAct2ENPlanWork object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2ENPlanWorkValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2ENPlanWorkDAO objectDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAct2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2ENPlanWork. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAct2ENPlanWorkDAO objectDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAct2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAct2ENPlanWork. Изменить*/
  public void save(ENAct2ENPlanWork object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2ENPlanWorkValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2ENPlanWorkDAO objectDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAct2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2ENPlanWork. Получить объект*/
  public ENAct2ENPlanWork getObject(int code)
   {
    try
     {
      ENAct2ENPlanWorkDAO objectDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAct2ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2ENPlanWork. Получить полный список*/
  public ENAct2ENPlanWorkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAct2ENPlanWork. Получить список по фильтру*/
  public ENAct2ENPlanWorkShortList getFilteredList(ENAct2ENPlanWorkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAct2ENPlanWork. Получить список для просмотра*/
  public ENAct2ENPlanWorkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAct2ENPlanWork. Получить список для просмотра по фильтру*/
  public ENAct2ENPlanWorkShortList getScrollableFilteredList(ENAct2ENPlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAct2ENPlanWorkDAO objectDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAct2ENPlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2ENPlanWork. Получить список для просмотра по условию*/
  public ENAct2ENPlanWorkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAct2ENPlanWorkFilter filterObject = new ENAct2ENPlanWorkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
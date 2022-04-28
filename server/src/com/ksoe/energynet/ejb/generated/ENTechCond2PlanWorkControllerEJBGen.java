
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTechCond2PlanWorkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTechCond2PlanWork;
import com.ksoe.energynet.valueobject.filter.ENTechCond2PlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.ENTechCond2PlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTechCond2PlanWork;  
  * 	
  */

public abstract class ENTechCond2PlanWorkControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechCond2PlanWorkControllerEJBGen() {}

  /*ENTechCond2PlanWork. Добавить*/
  public int add(ENTechCond2PlanWork object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCond2PlanWorkValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCond2PlanWork. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechCond2PlanWork. Изменить*/
  public void save(ENTechCond2PlanWork object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCond2PlanWorkValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCond2PlanWork. Получить объект*/
  public ENTechCond2PlanWork getObject(int code)
   {
    try
     {
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCond2PlanWork. Получить полный список*/
  public ENTechCond2PlanWorkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechCond2PlanWork. Получить список по фильтру*/
  public ENTechCond2PlanWorkShortList getFilteredList(ENTechCond2PlanWorkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechCond2PlanWork. Получить список для просмотра*/
  public ENTechCond2PlanWorkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechCond2PlanWork. Получить список для просмотра по фильтру*/
  public ENTechCond2PlanWorkShortList getScrollableFilteredList(ENTechCond2PlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCond2PlanWork. Получить список для просмотра по условию*/
  public ENTechCond2PlanWorkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechCond2PlanWorkFilter filterObject = new ENTechCond2PlanWorkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechCond2PlanWork. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechCond2PlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCond2PlanWorkDAO objectDAO = new ENTechCond2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechCond2PlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
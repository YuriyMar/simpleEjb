
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActIncome2ENActDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActIncome2ENAct;
import com.ksoe.energynet.valueobject.filter.ENActIncome2ENActFilter;
import com.ksoe.energynet.valueobject.lists.ENActIncome2ENActShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActIncome2ENAct;  
  * 	
  */

public abstract class ENActIncome2ENActControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActIncome2ENActControllerEJBGen() {}

  /*ENActIncome2ENAct. Добавить*/
  public int add(ENActIncome2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncome2ENActValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome2ENAct. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActIncome2ENAct. Изменить*/
  public void save(ENActIncome2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncome2ENActValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome2ENAct. Получить объект*/
  public ENActIncome2ENAct getObject(int code)
   {
    try
     {
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome2ENAct. Получить полный список*/
  public ENActIncome2ENActShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActIncome2ENAct. Получить список по фильтру*/
  public ENActIncome2ENActShortList getFilteredList(ENActIncome2ENActFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActIncome2ENAct. Получить список для просмотра*/
  public ENActIncome2ENActShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActIncome2ENAct. Получить список для просмотра по фильтру*/
  public ENActIncome2ENActShortList getScrollableFilteredList(ENActIncome2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome2ENAct. Получить список для просмотра по условию*/
  public ENActIncome2ENActShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActIncome2ENActFilter filterObject = new ENActIncome2ENActFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENActIncome2ENAct. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENActIncome2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncome2ENActDAO objectDAO = new ENActIncome2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncome2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
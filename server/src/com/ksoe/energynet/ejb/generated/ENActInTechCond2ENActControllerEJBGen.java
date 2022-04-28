
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActInTechCond2ENActDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActInTechCond2ENAct;
import com.ksoe.energynet.valueobject.filter.ENActInTechCond2ENActFilter;
import com.ksoe.energynet.valueobject.lists.ENActInTechCond2ENActShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActInTechCond2ENAct;  
  * 	
  */

public abstract class ENActInTechCond2ENActControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActInTechCond2ENActControllerEJBGen() {}

  /*ENActInTechCond2ENAct. Добавить*/
  public int add(ENActInTechCond2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActInTechCond2ENActValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActInTechCond2ENAct. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActInTechCond2ENAct. Изменить*/
  public void save(ENActInTechCond2ENAct object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActInTechCond2ENActValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActInTechCond2ENAct. Получить объект*/
  public ENActInTechCond2ENAct getObject(int code)
   {
    try
     {
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActInTechCond2ENAct. Получить полный список*/
  public ENActInTechCond2ENActShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActInTechCond2ENAct. Получить список по фильтру*/
  public ENActInTechCond2ENActShortList getFilteredList(ENActInTechCond2ENActFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActInTechCond2ENAct. Получить список для просмотра*/
  public ENActInTechCond2ENActShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActInTechCond2ENAct. Получить список для просмотра по фильтру*/
  public ENActInTechCond2ENActShortList getScrollableFilteredList(ENActInTechCond2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActInTechCond2ENAct. Получить список для просмотра по условию*/
  public ENActInTechCond2ENActShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActInTechCond2ENActFilter filterObject = new ENActInTechCond2ENActFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENActInTechCond2ENAct. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENActInTechCond2ENActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActInTechCond2ENActDAO objectDAO = new ENActInTechCond2ENActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENActInTechCond2ENAct%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
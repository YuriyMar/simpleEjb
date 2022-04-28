
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCCounterKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCCounterKind;
import com.ksoe.energynet.valueobject.filter.SCCounterKindFilter;
import com.ksoe.energynet.valueobject.lists.SCCounterKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCCounterKind;  
  * 	
  */

public abstract class SCCounterKindControllerEJBGen extends GenericSessionStatelessBean
{
  public SCCounterKindControllerEJBGen() {}

  /*SCCounterKind. Добавить*/
  public int add(SCCounterKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCCounterKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCCounterKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCCounterKind. Изменить*/
  public void save(SCCounterKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCCounterKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterKind. Получить объект*/
  public SCCounterKind getObject(int code)
   {
    try
     {
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCCounterKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterKind. Получить полный список*/
  public SCCounterKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCCounterKind. Получить список по фильтру*/
  public SCCounterKindShortList getFilteredList(SCCounterKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCCounterKind. Получить список для просмотра*/
  public SCCounterKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCCounterKind. Получить список для просмотра по фильтру*/
  public SCCounterKindShortList getScrollableFilteredList(SCCounterKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCCounterKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounterKind. Получить список для просмотра по условию*/
  public SCCounterKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCCounterKindFilter filterObject = new SCCounterKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCCounterKind. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCCounterKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterKindDAO objectDAO = new SCCounterKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCCounterKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
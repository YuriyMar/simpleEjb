
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCUsageInputItemKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCUsageInputItemKind;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemKindFilter;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCUsageInputItemKind;  
  * 	
  */

public abstract class SCUsageInputItemKindControllerEJBGen extends GenericSessionStatelessBean
{
  public SCUsageInputItemKindControllerEJBGen() {}

  /*SCUsageInputItemKind. Добавить*/
  public int add(SCUsageInputItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCUsageInputItemKind. Изменить*/
  public void save(SCUsageInputItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCUsageInputItemKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemKind. Получить объект*/
  public SCUsageInputItemKind getObject(int code)
   {
    try
     {
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemKind. Получить полный список*/
  public SCUsageInputItemKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCUsageInputItemKind. Получить список по фильтру*/
  public SCUsageInputItemKindShortList getFilteredList(SCUsageInputItemKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCUsageInputItemKind. Получить список для просмотра*/
  public SCUsageInputItemKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCUsageInputItemKind. Получить список для просмотра по фильтру*/
  public SCUsageInputItemKindShortList getScrollableFilteredList(SCUsageInputItemKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCUsageInputItemKind. Получить список для просмотра по условию*/
  public SCUsageInputItemKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCUsageInputItemKindFilter filterObject = new SCUsageInputItemKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCUsageInputItemKind. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCUsageInputItemKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCUsageInputItemKindDAO objectDAO = new SCUsageInputItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCUsageInputItemKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
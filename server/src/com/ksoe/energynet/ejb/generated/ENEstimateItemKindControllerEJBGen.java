
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItemKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemKindFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItemKind;  
  * 	
  */

public abstract class ENEstimateItemKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItemKindControllerEJBGen() {}

  /*ENEstimateItemKind. Добавить*/
  public int add(ENEstimateItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemKindDAO objectDAO = new ENEstimateItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItemKindDAO objectDAO = new ENEstimateItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItemKind. Изменить*/
  public void save(ENEstimateItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItemKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItemKindDAO objectDAO = new ENEstimateItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemKind. Получить объект*/
  public ENEstimateItemKind getObject(int code)
   {
    try
     {
      ENEstimateItemKindDAO objectDAO = new ENEstimateItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemKind. Получить полный список*/
  public ENEstimateItemKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItemKind. Получить список по фильтру*/
  public ENEstimateItemKindShortList getFilteredList(ENEstimateItemKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItemKind. Получить список для просмотра*/
  public ENEstimateItemKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItemKind. Получить список для просмотра по фильтру*/
  public ENEstimateItemKindShortList getScrollableFilteredList(ENEstimateItemKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItemKindDAO objectDAO = new ENEstimateItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItemKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItemKind. Получить список для просмотра по условию*/
  public ENEstimateItemKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItemKindFilter filterObject = new ENEstimateItemKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
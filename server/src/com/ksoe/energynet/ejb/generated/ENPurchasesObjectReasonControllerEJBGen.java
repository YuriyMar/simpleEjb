
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPurchasesObjectReasonDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPurchasesObjectReason;
import com.ksoe.energynet.valueobject.filter.ENPurchasesObjectReasonFilter;
import com.ksoe.energynet.valueobject.lists.ENPurchasesObjectReasonShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPurchasesObjectReason;  
  * 	
  */

public abstract class ENPurchasesObjectReasonControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPurchasesObjectReasonControllerEJBGen() {}

  /*ENPurchasesObjectReason. Добавить*/
  public int add(ENPurchasesObjectReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesObjectReasonValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesObjectReasonDAO objectDAO = new ENPurchasesObjectReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPurchasesObjectReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObjectReason. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPurchasesObjectReasonDAO objectDAO = new ENPurchasesObjectReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPurchasesObjectReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPurchasesObjectReason. Изменить*/
  public void save(ENPurchasesObjectReason object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesObjectReasonValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesObjectReasonDAO objectDAO = new ENPurchasesObjectReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPurchasesObjectReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObjectReason. Получить объект*/
  public ENPurchasesObjectReason getObject(int code)
   {
    try
     {
      ENPurchasesObjectReasonDAO objectDAO = new ENPurchasesObjectReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPurchasesObjectReason%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObjectReason. Получить полный список*/
  public ENPurchasesObjectReasonShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPurchasesObjectReason. Получить список по фильтру*/
  public ENPurchasesObjectReasonShortList getFilteredList(ENPurchasesObjectReasonFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPurchasesObjectReason. Получить список для просмотра*/
  public ENPurchasesObjectReasonShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPurchasesObjectReason. Получить список для просмотра по фильтру*/
  public ENPurchasesObjectReasonShortList getScrollableFilteredList(ENPurchasesObjectReasonFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPurchasesObjectReasonDAO objectDAO = new ENPurchasesObjectReasonDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPurchasesObjectReason%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesObjectReason. Получить список для просмотра по условию*/
  public ENPurchasesObjectReasonShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPurchasesObjectReasonFilter filterObject = new ENPurchasesObjectReasonFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
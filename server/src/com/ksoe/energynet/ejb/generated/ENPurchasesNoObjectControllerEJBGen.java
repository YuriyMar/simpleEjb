
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPurchasesNoObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPurchasesNoObject;
import com.ksoe.energynet.valueobject.filter.ENPurchasesNoObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENPurchasesNoObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPurchasesNoObject;  
  * 	
  */

public abstract class ENPurchasesNoObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPurchasesNoObjectControllerEJBGen() {}

  /*ENPurchasesNoObject. Добавить*/
  public int add(ENPurchasesNoObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesNoObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesNoObjectDAO objectDAO = new ENPurchasesNoObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPurchasesNoObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesNoObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPurchasesNoObjectDAO objectDAO = new ENPurchasesNoObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPurchasesNoObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPurchasesNoObject. Изменить*/
  public void save(ENPurchasesNoObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPurchasesNoObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPurchasesNoObjectDAO objectDAO = new ENPurchasesNoObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPurchasesNoObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesNoObject. Получить объект*/
  public ENPurchasesNoObject getObject(int code)
   {
    try
     {
      ENPurchasesNoObjectDAO objectDAO = new ENPurchasesNoObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPurchasesNoObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesNoObject. Получить полный список*/
  public ENPurchasesNoObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPurchasesNoObject. Получить список по фильтру*/
  public ENPurchasesNoObjectShortList getFilteredList(ENPurchasesNoObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPurchasesNoObject. Получить список для просмотра*/
  public ENPurchasesNoObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPurchasesNoObject. Получить список для просмотра по фильтру*/
  public ENPurchasesNoObjectShortList getScrollableFilteredList(ENPurchasesNoObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPurchasesNoObjectDAO objectDAO = new ENPurchasesNoObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPurchasesNoObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPurchasesNoObject. Получить список для просмотра по условию*/
  public ENPurchasesNoObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPurchasesNoObjectFilter filterObject = new ENPurchasesNoObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
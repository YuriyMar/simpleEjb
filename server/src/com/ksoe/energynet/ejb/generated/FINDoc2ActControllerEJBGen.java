
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINDoc2ActDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINDoc2Act;
import com.ksoe.energynet.valueobject.filter.FINDoc2ActFilter;
import com.ksoe.energynet.valueobject.lists.FINDoc2ActShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINDoc2Act;  
  * 	
  */

public abstract class FINDoc2ActControllerEJBGen extends GenericSessionStatelessBean
{
  public FINDoc2ActControllerEJBGen() {}

  /*FINDoc2Act. Добавить*/
  public int add(FINDoc2Act object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2ActValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2ActDAO objectDAO = new FINDoc2ActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINDoc2Act%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2Act. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINDoc2ActDAO objectDAO = new FINDoc2ActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINDoc2Act%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINDoc2Act. Изменить*/
  public void save(FINDoc2Act object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2ActValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2ActDAO objectDAO = new FINDoc2ActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINDoc2Act%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2Act. Получить объект*/
  public FINDoc2Act getObject(int code)
   {
    try
     {
      FINDoc2ActDAO objectDAO = new FINDoc2ActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINDoc2Act%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2Act. Получить полный список*/
  public FINDoc2ActShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINDoc2Act. Получить список по фильтру*/
  public FINDoc2ActShortList getFilteredList(FINDoc2ActFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINDoc2Act. Получить список для просмотра*/
  public FINDoc2ActShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINDoc2Act. Получить список для просмотра по фильтру*/
  public FINDoc2ActShortList getScrollableFilteredList(FINDoc2ActFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDoc2ActDAO objectDAO = new FINDoc2ActDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINDoc2Act%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2Act. Получить список для просмотра по условию*/
  public FINDoc2ActShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINDoc2ActFilter filterObject = new FINDoc2ActFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
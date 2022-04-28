
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransformerObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransformerObject;
import com.ksoe.energynet.valueobject.filter.ENTransformerObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENTransformerObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransformerObject;  
  * 	
  */

public abstract class ENTransformerObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransformerObjectControllerEJBGen() {}

  /*ENTransformerObject. Добавить*/
  public int add(ENTransformerObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransformerObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransformerObjectDAO objectDAO = new ENTransformerObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransformerObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransformerObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransformerObjectDAO objectDAO = new ENTransformerObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransformerObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransformerObject. Изменить*/
  public void save(ENTransformerObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransformerObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransformerObjectDAO objectDAO = new ENTransformerObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransformerObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransformerObject. Получить объект*/
  public ENTransformerObject getObject(int code)
   {
    try
     {
      ENTransformerObjectDAO objectDAO = new ENTransformerObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransformerObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransformerObject. Получить полный список*/
  public ENTransformerObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransformerObject. Получить список по фильтру*/
  public ENTransformerObjectShortList getFilteredList(ENTransformerObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransformerObject. Получить список для просмотра*/
  public ENTransformerObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransformerObject. Получить список для просмотра по фильтру*/
  public ENTransformerObjectShortList getScrollableFilteredList(ENTransformerObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransformerObjectDAO objectDAO = new ENTransformerObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransformerObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransformerObject. Получить список для просмотра по условию*/
  public ENTransformerObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransformerObjectFilter filterObject = new ENTransformerObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
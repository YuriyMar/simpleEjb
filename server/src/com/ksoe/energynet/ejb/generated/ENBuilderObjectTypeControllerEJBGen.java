
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENBuilderObjectTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENBuilderObjectType;
import com.ksoe.energynet.valueobject.filter.ENBuilderObjectTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENBuilderObjectTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENBuilderObjectType;  
  * 	
  */

public abstract class ENBuilderObjectTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENBuilderObjectTypeControllerEJBGen() {}

  /*ENBuilderObjectType. Добавить*/
  public int add(ENBuilderObjectType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBuilderObjectTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBuilderObjectTypeDAO objectDAO = new ENBuilderObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENBuilderObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObjectType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENBuilderObjectTypeDAO objectDAO = new ENBuilderObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENBuilderObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENBuilderObjectType. Изменить*/
  public void save(ENBuilderObjectType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBuilderObjectTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBuilderObjectTypeDAO objectDAO = new ENBuilderObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENBuilderObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObjectType. Получить объект*/
  public ENBuilderObjectType getObject(int code)
   {
    try
     {
      ENBuilderObjectTypeDAO objectDAO = new ENBuilderObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENBuilderObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObjectType. Получить полный список*/
  public ENBuilderObjectTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENBuilderObjectType. Получить список по фильтру*/
  public ENBuilderObjectTypeShortList getFilteredList(ENBuilderObjectTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENBuilderObjectType. Получить список для просмотра*/
  public ENBuilderObjectTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENBuilderObjectType. Получить список для просмотра по фильтру*/
  public ENBuilderObjectTypeShortList getScrollableFilteredList(ENBuilderObjectTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBuilderObjectTypeDAO objectDAO = new ENBuilderObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENBuilderObjectType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObjectType. Получить список для просмотра по условию*/
  public ENBuilderObjectTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENBuilderObjectTypeFilter filterObject = new ENBuilderObjectTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
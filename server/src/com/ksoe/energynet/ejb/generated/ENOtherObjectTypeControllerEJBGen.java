
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENOtherObjectTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENOtherObjectType;
import com.ksoe.energynet.valueobject.filter.ENOtherObjectTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENOtherObjectTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENOtherObjectType;  
  * 	
  */

public abstract class ENOtherObjectTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENOtherObjectTypeControllerEJBGen() {}

  /*ENOtherObjectType. Добавить*/
  public int add(ENOtherObjectType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOtherObjectTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOtherObjectTypeDAO objectDAO = new ENOtherObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENOtherObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOtherObjectType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENOtherObjectTypeDAO objectDAO = new ENOtherObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENOtherObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENOtherObjectType. Изменить*/
  public void save(ENOtherObjectType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENOtherObjectTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENOtherObjectTypeDAO objectDAO = new ENOtherObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENOtherObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOtherObjectType. Получить объект*/
  public ENOtherObjectType getObject(int code)
   {
    try
     {
      ENOtherObjectTypeDAO objectDAO = new ENOtherObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENOtherObjectType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOtherObjectType. Получить полный список*/
  public ENOtherObjectTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENOtherObjectType. Получить список по фильтру*/
  public ENOtherObjectTypeShortList getFilteredList(ENOtherObjectTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENOtherObjectType. Получить список для просмотра*/
  public ENOtherObjectTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENOtherObjectType. Получить список для просмотра по фильтру*/
  public ENOtherObjectTypeShortList getScrollableFilteredList(ENOtherObjectTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENOtherObjectTypeDAO objectDAO = new ENOtherObjectTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENOtherObjectType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENOtherObjectType. Получить список для просмотра по условию*/
  public ENOtherObjectTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENOtherObjectTypeFilter filterObject = new ENOtherObjectTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
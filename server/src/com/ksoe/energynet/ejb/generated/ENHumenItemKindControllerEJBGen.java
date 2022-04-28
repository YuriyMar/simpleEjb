
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENHumenItemKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENHumenItemKind;
import com.ksoe.energynet.valueobject.filter.ENHumenItemKindFilter;
import com.ksoe.energynet.valueobject.lists.ENHumenItemKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENHumenItemKind;  
  * 	
  */

public abstract class ENHumenItemKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENHumenItemKindControllerEJBGen() {}

  /*ENHumenItemKind. Добавить*/
  public int add(ENHumenItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENHumenItemKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENHumenItemKindDAO objectDAO = new ENHumenItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENHumenItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItemKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENHumenItemKindDAO objectDAO = new ENHumenItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENHumenItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENHumenItemKind. Изменить*/
  public void save(ENHumenItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENHumenItemKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENHumenItemKindDAO objectDAO = new ENHumenItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENHumenItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItemKind. Получить объект*/
  public ENHumenItemKind getObject(int code)
   {
    try
     {
      ENHumenItemKindDAO objectDAO = new ENHumenItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENHumenItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItemKind. Получить полный список*/
  public ENHumenItemKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENHumenItemKind. Получить список по фильтру*/
  public ENHumenItemKindShortList getFilteredList(ENHumenItemKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENHumenItemKind. Получить список для просмотра*/
  public ENHumenItemKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENHumenItemKind. Получить список для просмотра по фильтру*/
  public ENHumenItemKindShortList getScrollableFilteredList(ENHumenItemKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENHumenItemKindDAO objectDAO = new ENHumenItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENHumenItemKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENHumenItemKind. Получить список для просмотра по условию*/
  public ENHumenItemKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENHumenItemKindFilter filterObject = new ENHumenItemKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
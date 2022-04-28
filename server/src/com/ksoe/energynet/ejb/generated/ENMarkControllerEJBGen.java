
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMarkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMark;
import com.ksoe.energynet.valueobject.filter.ENMarkFilter;
import com.ksoe.energynet.valueobject.lists.ENMarkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMark;  
  * 	
  */

public abstract class ENMarkControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMarkControllerEJBGen() {}

  /*ENMark. Добавить*/
  public int add(ENMark object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMarkValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMarkDAO objectDAO = new ENMarkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMark%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMark. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMarkDAO objectDAO = new ENMarkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMark%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMark. Изменить*/
  public void save(ENMark object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMarkValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMarkDAO objectDAO = new ENMarkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMark%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMark. Получить объект*/
  public ENMark getObject(int code)
   {
    try
     {
      ENMarkDAO objectDAO = new ENMarkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMark%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMark. Получить полный список*/
  public ENMarkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMark. Получить список по фильтру*/
  public ENMarkShortList getFilteredList(ENMarkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMark. Получить список для просмотра*/
  public ENMarkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMark. Получить список для просмотра по фильтру*/
  public ENMarkShortList getScrollableFilteredList(ENMarkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMarkDAO objectDAO = new ENMarkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMark%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMark. Получить список для просмотра по условию*/
  public ENMarkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMarkFilter filterObject = new ENMarkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
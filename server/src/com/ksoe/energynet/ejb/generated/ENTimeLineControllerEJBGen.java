
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTimeLineDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTimeLine;
import com.ksoe.energynet.valueobject.filter.ENTimeLineFilter;
import com.ksoe.energynet.valueobject.lists.ENTimeLineShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTimeLine;  
  * 	
  */

public abstract class ENTimeLineControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTimeLineControllerEJBGen() {}

  /*ENTimeLine. Добавить*/
  public int add(ENTimeLine object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTimeLineValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTimeLine%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTimeLine. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTimeLine%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTimeLine. Изменить*/
  public void save(ENTimeLine object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTimeLineValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTimeLine%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTimeLine. Получить объект*/
  public ENTimeLine getObject(int code)
   {
    try
     {
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTimeLine%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTimeLine. Получить полный список*/
  public ENTimeLineShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTimeLine. Получить список по фильтру*/
  public ENTimeLineShortList getFilteredList(ENTimeLineFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTimeLine. Получить список для просмотра*/
  public ENTimeLineShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTimeLine. Получить список для просмотра по фильтру*/
  public ENTimeLineShortList getScrollableFilteredList(ENTimeLineFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTimeLine%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTimeLine. Получить список для просмотра по условию*/
  public ENTimeLineShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTimeLineFilter filterObject = new ENTimeLineFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTimeLine. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTimeLineFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTimeLineDAO objectDAO = new ENTimeLineDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTimeLine%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
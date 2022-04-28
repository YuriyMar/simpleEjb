
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENDestinationPoint2PointDAO;
import com.ksoe.energynet.valueobject.ENDestinationPoint2Point;
import com.ksoe.energynet.valueobject.lists.ENDestinationPoint2PointShortList;
import com.ksoe.energynet.valueobject.filter.ENDestinationPoint2PointFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENDestinationPoint2Point;  
  * 	
  */

public abstract class ENDestinationPoint2PointControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDestinationPoint2PointControllerEJBGen() {}

  /*ENDestinationPoint2Point. Добавить*/
  public int add(ENDestinationPoint2Point object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationPoint2PointValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint2Point. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDestinationPoint2Point. Изменить*/
  public void save(ENDestinationPoint2Point object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDestinationPoint2PointValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint2Point. Получить объект*/
  public ENDestinationPoint2Point getObject(int code)
   {
    try
     {
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint2Point. Получить полный список*/
  public ENDestinationPoint2PointShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDestinationPoint2Point. Получить список по фильтру*/
  public ENDestinationPoint2PointShortList getFilteredList(ENDestinationPoint2PointFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDestinationPoint2Point. Получить список для просмотра*/
  public ENDestinationPoint2PointShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDestinationPoint2Point. Получить список для просмотра по фильтру*/
  public ENDestinationPoint2PointShortList getScrollableFilteredList(ENDestinationPoint2PointFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDestinationPoint2Point. Получить список для просмотра по условию*/
  public ENDestinationPoint2PointShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDestinationPoint2PointFilter filterObject = new ENDestinationPoint2PointFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENDestinationPoint2Point. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENDestinationPoint2PointFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDestinationPoint2PointDAO objectDAO = new ENDestinationPoint2PointDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENDestinationPoint2Point%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAutoTiresDistanceDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAutoTiresDistance;
import com.ksoe.energynet.valueobject.filter.ENAutoTiresDistanceFilter;
import com.ksoe.energynet.valueobject.lists.ENAutoTiresDistanceShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAutoTiresDistance;  
  * 	
  */

public abstract class ENAutoTiresDistanceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAutoTiresDistanceControllerEJBGen() {}

  /*ENAutoTiresDistance. Добавить*/
  public int add(ENAutoTiresDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresDistanceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresDistance. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAutoTiresDistance. Изменить*/
  public void save(ENAutoTiresDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAutoTiresDistanceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresDistance. Получить объект*/
  public ENAutoTiresDistance getObject(int code)
   {
    try
     {
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresDistance. Получить полный список*/
  public ENAutoTiresDistanceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAutoTiresDistance. Получить список по фильтру*/
  public ENAutoTiresDistanceShortList getFilteredList(ENAutoTiresDistanceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAutoTiresDistance. Получить список для просмотра*/
  public ENAutoTiresDistanceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAutoTiresDistance. Получить список для просмотра по фильтру*/
  public ENAutoTiresDistanceShortList getScrollableFilteredList(ENAutoTiresDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAutoTiresDistance. Получить список для просмотра по условию*/
  public ENAutoTiresDistanceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAutoTiresDistanceFilter filterObject = new ENAutoTiresDistanceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAutoTiresDistance. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAutoTiresDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAutoTiresDistanceDAO objectDAO = new ENAutoTiresDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAutoTiresDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
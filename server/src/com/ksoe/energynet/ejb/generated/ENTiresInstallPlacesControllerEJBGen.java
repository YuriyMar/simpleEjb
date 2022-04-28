
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTiresInstallPlacesDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTiresInstallPlaces;
import com.ksoe.energynet.valueobject.filter.ENTiresInstallPlacesFilter;
import com.ksoe.energynet.valueobject.lists.ENTiresInstallPlacesShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTiresInstallPlaces;  
  * 	
  */

public abstract class ENTiresInstallPlacesControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTiresInstallPlacesControllerEJBGen() {}

  /*ENTiresInstallPlaces. Добавить*/
  public int add(ENTiresInstallPlaces object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTiresInstallPlacesValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallPlaces. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTiresInstallPlaces. Изменить*/
  public void save(ENTiresInstallPlaces object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTiresInstallPlacesValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallPlaces. Получить объект*/
  public ENTiresInstallPlaces getObject(int code)
   {
    try
     {
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallPlaces. Получить полный список*/
  public ENTiresInstallPlacesShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTiresInstallPlaces. Получить список по фильтру*/
  public ENTiresInstallPlacesShortList getFilteredList(ENTiresInstallPlacesFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTiresInstallPlaces. Получить список для просмотра*/
  public ENTiresInstallPlacesShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTiresInstallPlaces. Получить список для просмотра по фильтру*/
  public ENTiresInstallPlacesShortList getScrollableFilteredList(ENTiresInstallPlacesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTiresInstallPlaces. Получить список для просмотра по условию*/
  public ENTiresInstallPlacesShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTiresInstallPlacesFilter filterObject = new ENTiresInstallPlacesFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTiresInstallPlaces. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTiresInstallPlacesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTiresInstallPlacesDAO objectDAO = new ENTiresInstallPlacesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTiresInstallPlaces%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
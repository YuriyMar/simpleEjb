
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITFeatureTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITFeatureType;
import com.ksoe.energynet.valueobject.filter.ENSITFeatureTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENSITFeatureTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITFeatureType;  
  * 	
  */

public abstract class ENSITFeatureTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITFeatureTypeControllerEJBGen() {}

  /*ENSITFeatureType. Добавить*/
  public int add(ENSITFeatureType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureTypeDAO objectDAO = new ENSITFeatureTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITFeatureType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITFeatureTypeDAO objectDAO = new ENSITFeatureTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITFeatureType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITFeatureType. Изменить*/
  public void save(ENSITFeatureType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureTypeDAO objectDAO = new ENSITFeatureTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITFeatureType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureType. Получить объект*/
  public ENSITFeatureType getObject(int code)
   {
    try
     {
      ENSITFeatureTypeDAO objectDAO = new ENSITFeatureTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITFeatureType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureType. Получить полный список*/
  public ENSITFeatureTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITFeatureType. Получить список по фильтру*/
  public ENSITFeatureTypeShortList getFilteredList(ENSITFeatureTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITFeatureType. Получить список для просмотра*/
  public ENSITFeatureTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITFeatureType. Получить список для просмотра по фильтру*/
  public ENSITFeatureTypeShortList getScrollableFilteredList(ENSITFeatureTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITFeatureTypeDAO objectDAO = new ENSITFeatureTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITFeatureType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeatureType. Получить список для просмотра по условию*/
  public ENSITFeatureTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITFeatureTypeFilter filterObject = new ENSITFeatureTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
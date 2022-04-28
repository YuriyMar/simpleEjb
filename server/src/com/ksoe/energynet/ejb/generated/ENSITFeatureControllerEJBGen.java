
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITFeatureDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITFeature;
import com.ksoe.energynet.valueobject.filter.ENSITFeatureFilter;
import com.ksoe.energynet.valueobject.lists.ENSITFeatureShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITFeature;  
  * 	
  */

public abstract class ENSITFeatureControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITFeatureControllerEJBGen() {}

  /*ENSITFeature. Добавить*/
  public int add(ENSITFeature object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureDAO objectDAO = new ENSITFeatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITFeature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeature. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITFeatureDAO objectDAO = new ENSITFeatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITFeature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITFeature. Изменить*/
  public void save(ENSITFeature object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITFeatureValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITFeatureDAO objectDAO = new ENSITFeatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITFeature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeature. Получить объект*/
  public ENSITFeature getObject(int code)
   {
    try
     {
      ENSITFeatureDAO objectDAO = new ENSITFeatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITFeature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeature. Получить полный список*/
  public ENSITFeatureShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITFeature. Получить список по фильтру*/
  public ENSITFeatureShortList getFilteredList(ENSITFeatureFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITFeature. Получить список для просмотра*/
  public ENSITFeatureShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITFeature. Получить список для просмотра по фильтру*/
  public ENSITFeatureShortList getScrollableFilteredList(ENSITFeatureFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITFeatureDAO objectDAO = new ENSITFeatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITFeature%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITFeature. Получить список для просмотра по условию*/
  public ENSITFeatureShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITFeatureFilter filterObject = new ENSITFeatureFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
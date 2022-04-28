
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransport2ENEstimateDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransport2ENEstimate;
import com.ksoe.energynet.valueobject.filter.ENTransport2ENEstimateFilter;
import com.ksoe.energynet.valueobject.lists.ENTransport2ENEstimateShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransport2ENEstimate;  
  * 	
  */

public abstract class ENTransport2ENEstimateControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransport2ENEstimateControllerEJBGen() {}

  /*ENTransport2ENEstimate. Добавить*/
  public int add(ENTransport2ENEstimate object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransport2ENEstimateValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransport2ENEstimateDAO objectDAO = new ENTransport2ENEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransport2ENEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransport2ENEstimate. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTransport2ENEstimateDAO objectDAO = new ENTransport2ENEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransport2ENEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransport2ENEstimate. Изменить*/
  public void save(ENTransport2ENEstimate object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransport2ENEstimateValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransport2ENEstimateDAO objectDAO = new ENTransport2ENEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransport2ENEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransport2ENEstimate. Получить объект*/
  public ENTransport2ENEstimate getObject(int code)
   {
    try
     {
      ENTransport2ENEstimateDAO objectDAO = new ENTransport2ENEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransport2ENEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransport2ENEstimate. Получить полный список*/
  public ENTransport2ENEstimateShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransport2ENEstimate. Получить список по фильтру*/
  public ENTransport2ENEstimateShortList getFilteredList(ENTransport2ENEstimateFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransport2ENEstimate. Получить список для просмотра*/
  public ENTransport2ENEstimateShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransport2ENEstimate. Получить список для просмотра по фильтру*/
  public ENTransport2ENEstimateShortList getScrollableFilteredList(ENTransport2ENEstimateFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransport2ENEstimateDAO objectDAO = new ENTransport2ENEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransport2ENEstimate%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransport2ENEstimate. Получить список для просмотра по условию*/
  public ENTransport2ENEstimateShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransport2ENEstimateFilter filterObject = new ENTransport2ENEstimateFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
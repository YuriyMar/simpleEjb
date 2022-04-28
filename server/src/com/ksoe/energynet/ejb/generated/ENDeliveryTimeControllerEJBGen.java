
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDeliveryTimeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDeliveryTime;
import com.ksoe.energynet.valueobject.filter.ENDeliveryTimeFilter;
import com.ksoe.energynet.valueobject.lists.ENDeliveryTimeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDeliveryTime;  
  * 	
  */

public abstract class ENDeliveryTimeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDeliveryTimeControllerEJBGen() {}

  /*ENDeliveryTime. Добавить*/
  public int add(ENDeliveryTime object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryTimeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryTimeDAO objectDAO = new ENDeliveryTimeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDeliveryTime%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTime. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDeliveryTimeDAO objectDAO = new ENDeliveryTimeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDeliveryTime%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDeliveryTime. Изменить*/
  public void save(ENDeliveryTime object)
   {
    try
     {

/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryTimeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryTimeDAO objectDAO = new ENDeliveryTimeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDeliveryTime%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTime. Получить объект*/
  public ENDeliveryTime getObject(int code)
   {
    try
     {
      ENDeliveryTimeDAO objectDAO = new ENDeliveryTimeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDeliveryTime%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTime. Получить полный список*/
  public ENDeliveryTimeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDeliveryTime. Получить список по фильтру*/
  public ENDeliveryTimeShortList getFilteredList(ENDeliveryTimeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDeliveryTime. Получить список для просмотра*/
  public ENDeliveryTimeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDeliveryTime. Получить список для просмотра по фильтру*/
  public ENDeliveryTimeShortList getScrollableFilteredList(ENDeliveryTimeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDeliveryTimeDAO objectDAO = new ENDeliveryTimeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDeliveryTime%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryTime. Получить список для просмотра по условию*/
  public ENDeliveryTimeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDeliveryTimeFilter filterObject = new ENDeliveryTimeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
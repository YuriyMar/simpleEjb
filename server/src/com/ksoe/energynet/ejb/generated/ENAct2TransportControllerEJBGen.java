
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAct2TransportDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAct2Transport;
import com.ksoe.energynet.valueobject.filter.ENAct2TransportFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2TransportShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAct2Transport;  
  * 	
  */

public abstract class ENAct2TransportControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAct2TransportControllerEJBGen() {}

  /*ENAct2Transport. Добавить*/
  public int add(ENAct2Transport object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2TransportValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2TransportDAO objectDAO = new ENAct2TransportDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAct2Transport%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2Transport. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAct2TransportDAO objectDAO = new ENAct2TransportDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAct2Transport%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAct2Transport. Изменить*/
  public void save(ENAct2Transport object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAct2TransportValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAct2TransportDAO objectDAO = new ENAct2TransportDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAct2Transport%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2Transport. Получить объект*/
  public ENAct2Transport getObject(int code)
   {
    try
     {
      ENAct2TransportDAO objectDAO = new ENAct2TransportDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAct2Transport%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2Transport. Получить полный список*/
  public ENAct2TransportShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAct2Transport. Получить список по фильтру*/
  public ENAct2TransportShortList getFilteredList(ENAct2TransportFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAct2Transport. Получить список для просмотра*/
  public ENAct2TransportShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAct2Transport. Получить список для просмотра по фильтру*/
  public ENAct2TransportShortList getScrollableFilteredList(ENAct2TransportFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAct2TransportDAO objectDAO = new ENAct2TransportDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAct2Transport%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAct2Transport. Получить список для просмотра по условию*/
  public ENAct2TransportShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAct2TransportFilter filterObject = new ENAct2TransportFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
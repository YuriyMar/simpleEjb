
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActStatusDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActStatus;
import com.ksoe.energynet.valueobject.filter.ENActStatusFilter;
import com.ksoe.energynet.valueobject.lists.ENActStatusShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActStatus;  
  * 	
  */

public abstract class ENActStatusControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActStatusControllerEJBGen() {}

  /*ENActStatus. Добавить*/
  public int add(ENActStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActStatusValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActStatusDAO objectDAO = new ENActStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActStatus. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENActStatusDAO objectDAO = new ENActStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActStatus. Изменить*/
  public void save(ENActStatus object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActStatusValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActStatusDAO objectDAO = new ENActStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActStatus. Получить объект*/
  public ENActStatus getObject(int code)
   {
    try
     {
      ENActStatusDAO objectDAO = new ENActStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActStatus%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActStatus. Получить полный список*/
  public ENActStatusShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActStatus. Получить список по фильтру*/
  public ENActStatusShortList getFilteredList(ENActStatusFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActStatus. Получить список для просмотра*/
  public ENActStatusShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActStatus. Получить список для просмотра по фильтру*/
  public ENActStatusShortList getScrollableFilteredList(ENActStatusFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActStatusDAO objectDAO = new ENActStatusDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActStatus%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActStatus. Получить список для просмотра по условию*/
  public ENActStatusShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActStatusFilter filterObject = new ENActStatusFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
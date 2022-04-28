
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENCfoDataDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENCfoData;
import com.ksoe.energynet.valueobject.filter.ENCfoDataFilter;
import com.ksoe.energynet.valueobject.lists.ENCfoDataShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENCfoData;  
  * 	
  */

public abstract class ENCfoDataControllerEJBGen extends GenericSessionStatelessBean
{
  public ENCfoDataControllerEJBGen() {}

  /*ENCfoData. Добавить*/
  public int add(ENCfoData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENCfoDataValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENCfoDataDAO objectDAO = new ENCfoDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENCfoData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENCfoData. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENCfoDataDAO objectDAO = new ENCfoDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENCfoData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENCfoData. Изменить*/
  public void save(ENCfoData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENCfoDataValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENCfoDataDAO objectDAO = new ENCfoDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENCfoData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENCfoData. Получить объект*/
  public ENCfoData getObject(int code)
   {
    try
     {
      ENCfoDataDAO objectDAO = new ENCfoDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENCfoData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENCfoData. Получить полный список*/
  public ENCfoDataShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENCfoData. Получить список по фильтру*/
  public ENCfoDataShortList getFilteredList(ENCfoDataFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENCfoData. Получить список для просмотра*/
  public ENCfoDataShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENCfoData. Получить список для просмотра по фильтру*/
  public ENCfoDataShortList getScrollableFilteredList(ENCfoDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENCfoDataDAO objectDAO = new ENCfoDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENCfoData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENCfoData. Получить список для просмотра по условию*/
  public ENCfoDataShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENCfoDataFilter filterObject = new ENCfoDataFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
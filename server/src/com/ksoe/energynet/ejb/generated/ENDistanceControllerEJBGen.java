
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDistanceDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDistance;
import com.ksoe.energynet.valueobject.filter.ENDistanceFilter;
import com.ksoe.energynet.valueobject.lists.ENDistanceShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDistance;
  *
  */

public abstract class ENDistanceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDistanceControllerEJBGen() {}

  /*ENDistance. Добавить*/
  public int add(ENDistance object)
   {
    try
     {
      ENDistanceDAO objectDAO = new ENDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistance. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDistanceDAO objectDAO = new ENDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENDistance. Изменить*/
  public void save(ENDistance object)
   {
    try
     {
      ENDistanceDAO objectDAO = new ENDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistance. Получить объект*/
  public ENDistance getObject(int code)
   {
    try
     {
      ENDistanceDAO objectDAO = new ENDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistance. Получить полный список*/
  public ENDistanceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDistance. Получить список по фильтру*/
  public ENDistanceShortList getFilteredList(ENDistanceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDistance. Получить список для просмотра*/
  public ENDistanceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDistance. Получить список для просмотра по фильтру*/
  public ENDistanceShortList getScrollableFilteredList(ENDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDistanceDAO objectDAO = new ENDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistance. Получить список для просмотра по условию*/
  public ENDistanceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDistanceFilter filterObject = new ENDistanceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}
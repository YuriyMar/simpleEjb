
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMOL2PlanWorkDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMOL2PlanWork;
import com.ksoe.energynet.valueobject.filter.ENMOL2PlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.ENMOL2PlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMOL2PlanWork;
  *
  */

public abstract class ENMOL2PlanWorkControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMOL2PlanWorkControllerEJBGen() {}

  /*ENMOL2PlanWork. Добавить*/
  public int add(ENMOL2PlanWork object)
   {
    try
     {
      ENMOL2PlanWorkDAO objectDAO = new ENMOL2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMOL2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMOL2PlanWork. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENMOL2PlanWorkDAO objectDAO = new ENMOL2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMOL2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENMOL2PlanWork. Изменить*/
  public void save(ENMOL2PlanWork object)
   {
    try
     {
      ENMOL2PlanWorkDAO objectDAO = new ENMOL2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMOL2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMOL2PlanWork. Получить объект*/
  public ENMOL2PlanWork getObject(int code)
   {
    try
     {
      ENMOL2PlanWorkDAO objectDAO = new ENMOL2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMOL2PlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMOL2PlanWork. Получить полный список*/
  public ENMOL2PlanWorkShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMOL2PlanWork. Получить список по фильтру*/
  public ENMOL2PlanWorkShortList getFilteredList(ENMOL2PlanWorkFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMOL2PlanWork. Получить список для просмотра*/
  public ENMOL2PlanWorkShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMOL2PlanWork. Получить список для просмотра по фильтру*/
  public ENMOL2PlanWorkShortList getScrollableFilteredList(ENMOL2PlanWorkFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMOL2PlanWorkDAO objectDAO = new ENMOL2PlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMOL2PlanWork%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMOL2PlanWork. Получить список для просмотра по условию*/
  public ENMOL2PlanWorkShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMOL2PlanWorkFilter filterObject = new ENMOL2PlanWorkFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}
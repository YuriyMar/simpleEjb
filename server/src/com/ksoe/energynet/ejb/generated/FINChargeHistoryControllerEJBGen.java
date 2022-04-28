
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINChargeHistoryDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINChargeHistory;
import com.ksoe.energynet.valueobject.filter.FINChargeHistoryFilter;
import com.ksoe.energynet.valueobject.lists.FINChargeHistoryShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINChargeHistory;  
  * 	
  */

public abstract class FINChargeHistoryControllerEJBGen extends GenericSessionStatelessBean
{
  public FINChargeHistoryControllerEJBGen() {}

  /*FINChargeHistory. ��������*/
  public int add(FINChargeHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINChargeHistoryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINChargeHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeHistory. �������*/
  public void remove(int code)
   {
    try
     {
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINChargeHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINChargeHistory. ��������*/
  public void save(FINChargeHistory object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINChargeHistoryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINChargeHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeHistory. �������� ������*/
  public FINChargeHistory getObject(int code)
   {
    try
     {
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINChargeHistory%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeHistory. �������� ������ ������*/
  public FINChargeHistoryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINChargeHistory. �������� ������ �� �������*/
  public FINChargeHistoryShortList getFilteredList(FINChargeHistoryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINChargeHistory. �������� ������ ��� ���������*/
  public FINChargeHistoryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINChargeHistory. �������� ������ ��� ��������� �� �������*/
  public FINChargeHistoryShortList getScrollableFilteredList(FINChargeHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINChargeHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeHistory. �������� ������ ��� ��������� �� �������*/
  public FINChargeHistoryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINChargeHistoryFilter filterObject = new FINChargeHistoryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINChargeHistory. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(FINChargeHistoryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINChargeHistoryDAO objectDAO = new FINChargeHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINChargeHistory%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
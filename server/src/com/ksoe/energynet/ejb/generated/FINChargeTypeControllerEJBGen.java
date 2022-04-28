
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINChargeTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINChargeType;
import com.ksoe.energynet.valueobject.filter.FINChargeTypeFilter;
import com.ksoe.energynet.valueobject.lists.FINChargeTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINChargeType;  
  * 	
  */

public abstract class FINChargeTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public FINChargeTypeControllerEJBGen() {}

  /*FINChargeType. ��������*/
  public int add(FINChargeType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINChargeTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINChargeType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeType. �������*/
  public void remove(int code)
   {
    try
     {
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINChargeType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINChargeType. ��������*/
  public void save(FINChargeType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINChargeTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINChargeType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeType. �������� ������*/
  public FINChargeType getObject(int code)
   {
    try
     {
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINChargeType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeType. �������� ������ ������*/
  public FINChargeTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINChargeType. �������� ������ �� �������*/
  public FINChargeTypeShortList getFilteredList(FINChargeTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINChargeType. �������� ������ ��� ���������*/
  public FINChargeTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINChargeType. �������� ������ ��� ��������� �� �������*/
  public FINChargeTypeShortList getScrollableFilteredList(FINChargeTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINChargeType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINChargeType. �������� ������ ��� ��������� �� �������*/
  public FINChargeTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINChargeTypeFilter filterObject = new FINChargeTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EFINChargeType. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(FINChargeTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINChargeTypeDAO objectDAO = new FINChargeTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.FINChargeType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
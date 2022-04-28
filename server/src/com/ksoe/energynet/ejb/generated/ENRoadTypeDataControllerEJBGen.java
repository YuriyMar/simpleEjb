
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENRoadTypeDataDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENRoadTypeData;
import com.ksoe.energynet.valueobject.filter.ENRoadTypeDataFilter;
import com.ksoe.energynet.valueobject.lists.ENRoadTypeDataShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENRoadTypeData;  
  * 	
  */

public abstract class ENRoadTypeDataControllerEJBGen extends GenericSessionStatelessBean
{
  public ENRoadTypeDataControllerEJBGen() {}

  /*ENRoadTypeData. ��������*/
  public int add(ENRoadTypeData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRoadTypeDataValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRoadTypeDataDAO objectDAO = new ENRoadTypeDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENRoadTypeData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRoadTypeData. �������*/
  public void remove(int code)
   {
    try
     {
      ENRoadTypeDataDAO objectDAO = new ENRoadTypeDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENRoadTypeData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENRoadTypeData. ��������*/
  public void save(ENRoadTypeData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRoadTypeDataValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRoadTypeDataDAO objectDAO = new ENRoadTypeDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENRoadTypeData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRoadTypeData. �������� ������*/
  public ENRoadTypeData getObject(int code)
   {
    try
     {
      ENRoadTypeDataDAO objectDAO = new ENRoadTypeDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENRoadTypeData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRoadTypeData. �������� ������ ������*/
  public ENRoadTypeDataShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENRoadTypeData. �������� ������ �� �������*/
  public ENRoadTypeDataShortList getFilteredList(ENRoadTypeDataFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENRoadTypeData. �������� ������ ��� ���������*/
  public ENRoadTypeDataShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENRoadTypeData. �������� ������ ��� ��������� �� �������*/
  public ENRoadTypeDataShortList getScrollableFilteredList(ENRoadTypeDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRoadTypeDataDAO objectDAO = new ENRoadTypeDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENRoadTypeData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRoadTypeData. �������� ������ ��� ��������� �� �������*/
  public ENRoadTypeDataShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENRoadTypeDataFilter filterObject = new ENRoadTypeDataFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
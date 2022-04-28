
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENMarkEstimateDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENMarkEstimate;
import com.ksoe.energynet.valueobject.filter.ENMarkEstimateFilter;
import com.ksoe.energynet.valueobject.lists.ENMarkEstimateShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENMarkEstimate;  
  * 	
  */

public abstract class ENMarkEstimateControllerEJBGen extends GenericSessionStatelessBean
{
  public ENMarkEstimateControllerEJBGen() {}

  /*ENMarkEstimate. ��������*/
  public int add(ENMarkEstimate object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMarkEstimateValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMarkEstimateDAO objectDAO = new ENMarkEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENMarkEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMarkEstimate. �������*/
  public void remove(int code)
   {
    try
     {
      ENMarkEstimateDAO objectDAO = new ENMarkEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENMarkEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENMarkEstimate. ��������*/
  public void save(ENMarkEstimate object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENMarkEstimateValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENMarkEstimateDAO objectDAO = new ENMarkEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENMarkEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMarkEstimate. �������� ������*/
  public ENMarkEstimate getObject(int code)
   {
    try
     {
      ENMarkEstimateDAO objectDAO = new ENMarkEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENMarkEstimate%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMarkEstimate. �������� ������ ������*/
  public ENMarkEstimateShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENMarkEstimate. �������� ������ �� �������*/
  public ENMarkEstimateShortList getFilteredList(ENMarkEstimateFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENMarkEstimate. �������� ������ ��� ���������*/
  public ENMarkEstimateShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENMarkEstimate. �������� ������ ��� ��������� �� �������*/
  public ENMarkEstimateShortList getScrollableFilteredList(ENMarkEstimateFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENMarkEstimateDAO objectDAO = new ENMarkEstimateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENMarkEstimate%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENMarkEstimate. �������� ������ ��� ��������� �� �������*/
  public ENMarkEstimateShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENMarkEstimateFilter filterObject = new ENMarkEstimateFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
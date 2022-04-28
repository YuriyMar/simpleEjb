
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActIncomeTechConditionsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActIncomeTechConditions;
import com.ksoe.energynet.valueobject.filter.ENActIncomeTechConditionsFilter;
import com.ksoe.energynet.valueobject.lists.ENActIncomeTechConditionsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActIncomeTechConditions;  
  * 	
  */

public abstract class ENActIncomeTechConditionsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActIncomeTechConditionsControllerEJBGen() {}

  /*ENActIncomeTechConditions. ��������*/
  public int add(ENActIncomeTechConditions object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeTechConditionsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeTechConditions. �������*/
  public void remove(int code)
   {
    try
     {
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActIncomeTechConditions. ��������*/
  public void save(ENActIncomeTechConditions object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeTechConditionsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeTechConditions. �������� ������*/
  public ENActIncomeTechConditions getObject(int code)
   {
    try
     {
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeTechConditions. �������� ������ ������*/
  public ENActIncomeTechConditionsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActIncomeTechConditions. �������� ������ �� �������*/
  public ENActIncomeTechConditionsShortList getFilteredList(ENActIncomeTechConditionsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActIncomeTechConditions. �������� ������ ��� ���������*/
  public ENActIncomeTechConditionsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActIncomeTechConditions. �������� ������ ��� ��������� �� �������*/
  public ENActIncomeTechConditionsShortList getScrollableFilteredList(ENActIncomeTechConditionsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncomeTechConditions. �������� ������ ��� ��������� �� �������*/
  public ENActIncomeTechConditionsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActIncomeTechConditionsFilter filterObject = new ENActIncomeTechConditionsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENActIncomeTechConditions. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENActIncomeTechConditionsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeTechConditionsDAO objectDAO = new ENActIncomeTechConditionsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncomeTechConditions%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENInvestProgramGroupsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENInvestProgramGroups;
import com.ksoe.energynet.valueobject.filter.ENInvestProgramGroupsFilter;
import com.ksoe.energynet.valueobject.lists.ENInvestProgramGroupsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENInvestProgramGroups;  
  * 	
  */

public abstract class ENInvestProgramGroupsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENInvestProgramGroupsControllerEJBGen() {}

  /*ENInvestProgramGroups. ��������*/
  public int add(ENInvestProgramGroups object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENInvestProgramGroupsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENInvestProgramGroups. �������*/
  public void remove(int code)
   {
    try
     {
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENInvestProgramGroups. ��������*/
  public void save(ENInvestProgramGroups object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENInvestProgramGroupsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENInvestProgramGroups. �������� ������*/
  public ENInvestProgramGroups getObject(int code)
   {
    try
     {
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENInvestProgramGroups. �������� ������ ������*/
  public ENInvestProgramGroupsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENInvestProgramGroups. �������� ������ �� �������*/
  public ENInvestProgramGroupsShortList getFilteredList(ENInvestProgramGroupsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENInvestProgramGroups. �������� ������ ��� ���������*/
  public ENInvestProgramGroupsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENInvestProgramGroups. �������� ������ ��� ��������� �� �������*/
  public ENInvestProgramGroupsShortList getScrollableFilteredList(ENInvestProgramGroupsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENInvestProgramGroups. �������� ������ ��� ��������� �� �������*/
  public ENInvestProgramGroupsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENInvestProgramGroupsFilter filterObject = new ENInvestProgramGroupsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENInvestProgramGroups. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENInvestProgramGroupsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENInvestProgramGroupsDAO objectDAO = new ENInvestProgramGroupsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENInvestProgramGroups%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
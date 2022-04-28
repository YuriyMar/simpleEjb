
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENStandardConstEntryDAO;
import com.ksoe.energynet.valueobject.ENStandardConstEntry;
import com.ksoe.energynet.valueobject.lists.ENStandardConstEntryShortList;
import com.ksoe.energynet.valueobject.filter.ENStandardConstEntryFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//������ ��������� import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENStandardConstEntry;  
  * 	
  */

public abstract class ENStandardConstEntryControllerEJBGen extends GenericSessionStatelessBean
{
  public ENStandardConstEntryControllerEJBGen() {}

  /*ENStandardConstEntry. ��������*/
  public int add(ENStandardConstEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstEntryValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. �������*/
  public void remove(int code)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENStandardConstEntry. ��������*/
  public void save(ENStandardConstEntry object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENStandardConstEntryValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. �������� ������*/
  public ENStandardConstEntry getObject(int code)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. �������� ������ ������*/
  public ENStandardConstEntryShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENStandardConstEntry. �������� ������ �� �������*/
  public ENStandardConstEntryShortList getFilteredList(ENStandardConstEntryFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENStandardConstEntry. �������� ������ ��� ���������*/
  public ENStandardConstEntryShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENStandardConstEntry. �������� ������ ��� ��������� �� �������*/
  public ENStandardConstEntryShortList getScrollableFilteredList(ENStandardConstEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENStandardConstEntry. �������� ������ ��� ��������� �� �������*/
  public ENStandardConstEntryShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENStandardConstEntryFilter filterObject = new ENStandardConstEntryFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENStandardConstEntry. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENStandardConstEntryFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENStandardConstEntryDAO objectDAO = new ENStandardConstEntryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENStandardConstEntry%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
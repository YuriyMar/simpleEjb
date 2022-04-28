
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTransportTemperatureDAO;
import com.ksoe.energynet.valueobject.ENTransportTemperature;
import com.ksoe.energynet.valueobject.lists.ENTransportTemperatureShortList;
import com.ksoe.energynet.valueobject.filter.ENTransportTemperatureFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//������ ��������� import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTransportTemperature;  
  * 	
  */

public abstract class ENTransportTemperatureControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportTemperatureControllerEJBGen() {}

  /*ENTransportTemperature. ��������*/
  public int add(ENTransportTemperature object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportTemperatureValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportTemperature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportTemperature. �������*/
  public void remove(int code)
   {
    try
     {
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportTemperature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportTemperature. ��������*/
  public void save(ENTransportTemperature object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportTemperatureValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportTemperature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportTemperature. �������� ������*/
  public ENTransportTemperature getObject(int code)
   {
    try
     {
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportTemperature%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportTemperature. �������� ������ ������*/
  public ENTransportTemperatureShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportTemperature. �������� ������ �� �������*/
  public ENTransportTemperatureShortList getFilteredList(ENTransportTemperatureFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportTemperature. �������� ������ ��� ���������*/
  public ENTransportTemperatureShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportTemperature. �������� ������ ��� ��������� �� �������*/
  public ENTransportTemperatureShortList getScrollableFilteredList(ENTransportTemperatureFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportTemperature%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportTemperature. �������� ������ ��� ��������� �� �������*/
  public ENTransportTemperatureShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportTemperatureFilter filterObject = new ENTransportTemperatureFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportTemperature. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENTransportTemperatureFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportTemperatureDAO objectDAO = new ENTransportTemperatureDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportTemperature%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
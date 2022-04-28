
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTransportDepartmentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTransportDepartment;
import com.ksoe.energynet.valueobject.filter.ENTransportDepartmentFilter;
import com.ksoe.energynet.valueobject.lists.ENTransportDepartmentShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTransportDepartment;  
  * 	
  */

public abstract class ENTransportDepartmentControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTransportDepartmentControllerEJBGen() {}

  /*ENTransportDepartment. ��������*/
  public int add(ENTransportDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportDepartmentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTransportDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDepartment. �������*/
  public void remove(int code)
   {
    try
     {
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTransportDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTransportDepartment. ��������*/
  public void save(ENTransportDepartment object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTransportDepartmentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTransportDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDepartment. �������� ������*/
  public ENTransportDepartment getObject(int code)
   {
    try
     {
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTransportDepartment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDepartment. �������� ������ ������*/
  public ENTransportDepartmentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTransportDepartment. �������� ������ �� �������*/
  public ENTransportDepartmentShortList getFilteredList(ENTransportDepartmentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTransportDepartment. �������� ������ ��� ���������*/
  public ENTransportDepartmentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTransportDepartment. �������� ������ ��� ��������� �� �������*/
  public ENTransportDepartmentShortList getScrollableFilteredList(ENTransportDepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTransportDepartment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTransportDepartment. �������� ������ ��� ��������� �� �������*/
  public ENTransportDepartmentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTransportDepartmentFilter filterObject = new ENTransportDepartmentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTransportDepartment. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENTransportDepartmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTransportDepartmentDAO objectDAO = new ENTransportDepartmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTransportDepartment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
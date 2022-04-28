
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENBuilderObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENBuilderObject;
import com.ksoe.energynet.valueobject.filter.ENBuilderObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENBuilderObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENBuilderObject;  
  * 	
  */

public abstract class ENBuilderObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENBuilderObjectControllerEJBGen() {}

  /*ENBuilderObject. ��������*/
  public int add(ENBuilderObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBuilderObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBuilderObjectDAO objectDAO = new ENBuilderObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENBuilderObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObject. �������*/
  public void remove(int code)
   {
    try
     {
      ENBuilderObjectDAO objectDAO = new ENBuilderObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENBuilderObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENBuilderObject. ��������*/
  public void save(ENBuilderObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBuilderObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBuilderObjectDAO objectDAO = new ENBuilderObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENBuilderObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObject. �������� ������*/
  public ENBuilderObject getObject(int code)
   {
    try
     {
      ENBuilderObjectDAO objectDAO = new ENBuilderObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENBuilderObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObject. �������� ������ ������*/
  public ENBuilderObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENBuilderObject. �������� ������ �� �������*/
  public ENBuilderObjectShortList getFilteredList(ENBuilderObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENBuilderObject. �������� ������ ��� ���������*/
  public ENBuilderObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENBuilderObject. �������� ������ ��� ��������� �� �������*/
  public ENBuilderObjectShortList getScrollableFilteredList(ENBuilderObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBuilderObjectDAO objectDAO = new ENBuilderObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENBuilderObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBuilderObject. �������� ������ ��� ��������� �� �������*/
  public ENBuilderObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENBuilderObjectFilter filterObject = new ENBuilderObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
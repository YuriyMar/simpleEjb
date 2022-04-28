
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENRecordPointPromDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENRecordPointProm;
import com.ksoe.energynet.valueobject.filter.ENRecordPointPromFilter;
import com.ksoe.energynet.valueobject.lists.ENRecordPointPromShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENRecordPointProm;  
  * 	
  */

public abstract class ENRecordPointPromControllerEJBGen extends GenericSessionStatelessBean
{
  public ENRecordPointPromControllerEJBGen() {}

  /*ENRecordPointProm. ��������*/
  public int add(ENRecordPointProm object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRecordPointPromValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRecordPointPromDAO objectDAO = new ENRecordPointPromDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENRecordPointProm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointProm. �������*/
  public void remove(int code)
   {
    try
     {
      ENRecordPointPromDAO objectDAO = new ENRecordPointPromDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENRecordPointProm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENRecordPointProm. ��������*/
  public void save(ENRecordPointProm object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENRecordPointPromValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENRecordPointPromDAO objectDAO = new ENRecordPointPromDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENRecordPointProm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointProm. �������� ������*/
  public ENRecordPointProm getObject(int code)
   {
    try
     {
      ENRecordPointPromDAO objectDAO = new ENRecordPointPromDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENRecordPointProm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointProm. �������� ������ ������*/
  public ENRecordPointPromShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENRecordPointProm. �������� ������ �� �������*/
  public ENRecordPointPromShortList getFilteredList(ENRecordPointPromFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENRecordPointProm. �������� ������ ��� ���������*/
  public ENRecordPointPromShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENRecordPointProm. �������� ������ ��� ��������� �� �������*/
  public ENRecordPointPromShortList getScrollableFilteredList(ENRecordPointPromFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENRecordPointPromDAO objectDAO = new ENRecordPointPromDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENRecordPointProm%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENRecordPointProm. �������� ������ ��� ��������� �� �������*/
  public ENRecordPointPromShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENRecordPointPromFilter filterObject = new ENRecordPointPromFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENConnectionLevelDAO;
import com.ksoe.energynet.valueobject.ENConnectionLevel;
import com.ksoe.energynet.valueobject.lists.ENConnectionLevelShortList;
import com.ksoe.energynet.valueobject.filter.ENConnectionLevelFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//������ ��������� import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENConnectionLevel;  
  * 	
  */

public abstract class ENConnectionLevelControllerEJBGen extends GenericSessionStatelessBean
{
  public ENConnectionLevelControllerEJBGen() {}

  /*ENConnectionLevel. ��������*/
  public int add(ENConnectionLevel object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionLevelValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENConnectionLevel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionLevel. �������*/
  public void remove(int code)
   {
    try
     {
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENConnectionLevel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENConnectionLevel. ��������*/
  public void save(ENConnectionLevel object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionLevelValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENConnectionLevel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionLevel. �������� ������*/
  public ENConnectionLevel getObject(int code)
   {
    try
     {
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENConnectionLevel%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionLevel. �������� ������ ������*/
  public ENConnectionLevelShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENConnectionLevel. �������� ������ �� �������*/
  public ENConnectionLevelShortList getFilteredList(ENConnectionLevelFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENConnectionLevel. �������� ������ ��� ���������*/
  public ENConnectionLevelShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENConnectionLevel. �������� ������ ��� ��������� �� �������*/
  public ENConnectionLevelShortList getScrollableFilteredList(ENConnectionLevelFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionLevel%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionLevel. �������� ������ ��� ��������� �� �������*/
  public ENConnectionLevelShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENConnectionLevelFilter filterObject = new ENConnectionLevelFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENConnectionLevel. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENConnectionLevelFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionLevelDAO objectDAO = new ENConnectionLevelDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionLevel%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
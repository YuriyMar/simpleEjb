
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelWorkModeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelWorkMode;
import com.ksoe.energynet.valueobject.filter.ENTravelWorkModeFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelWorkModeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelWorkMode;  
  * 	
  */

public abstract class ENTravelWorkModeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelWorkModeControllerEJBGen() {}

  /*ENTravelWorkMode. ��������*/
  public int add(ENTravelWorkMode object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelWorkModeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelWorkModeDAO objectDAO = new ENTravelWorkModeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelWorkMode%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelWorkMode. �������*/
  public void remove(int code)
   {
    try
     {
      ENTravelWorkModeDAO objectDAO = new ENTravelWorkModeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelWorkMode%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelWorkMode. ��������*/
  public void save(ENTravelWorkMode object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelWorkModeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelWorkModeDAO objectDAO = new ENTravelWorkModeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelWorkMode%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelWorkMode. �������� ������*/
  public ENTravelWorkMode getObject(int code)
   {
    try
     {
      ENTravelWorkModeDAO objectDAO = new ENTravelWorkModeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelWorkMode%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelWorkMode. �������� ������ ������*/
  public ENTravelWorkModeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelWorkMode. �������� ������ �� �������*/
  public ENTravelWorkModeShortList getFilteredList(ENTravelWorkModeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelWorkMode. �������� ������ ��� ���������*/
  public ENTravelWorkModeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelWorkMode. �������� ������ ��� ��������� �� �������*/
  public ENTravelWorkModeShortList getScrollableFilteredList(ENTravelWorkModeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelWorkModeDAO objectDAO = new ENTravelWorkModeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelWorkMode%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelWorkMode. �������� ������ ��� ��������� �� �������*/
  public ENTravelWorkModeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelWorkModeFilter filterObject = new ENTravelWorkModeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
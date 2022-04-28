
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDeliveryKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDeliveryKind;
import com.ksoe.energynet.valueobject.filter.ENDeliveryKindFilter;
import com.ksoe.energynet.valueobject.lists.ENDeliveryKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDeliveryKind;  
  * 	
  */

public abstract class ENDeliveryKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDeliveryKindControllerEJBGen() {}

  /*ENDeliveryKind. ��������*/
  public int add(ENDeliveryKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryKindDAO objectDAO = new ENDeliveryKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDeliveryKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryKind. �������*/
  public void remove(int code)
   {
    try
     {
      ENDeliveryKindDAO objectDAO = new ENDeliveryKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDeliveryKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDeliveryKind. ��������*/
  public void save(ENDeliveryKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDeliveryKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDeliveryKindDAO objectDAO = new ENDeliveryKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDeliveryKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryKind. �������� ������*/
  public ENDeliveryKind getObject(int code)
   {
    try
     {
      ENDeliveryKindDAO objectDAO = new ENDeliveryKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDeliveryKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryKind. �������� ������ ������*/
  public ENDeliveryKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDeliveryKind. �������� ������ �� �������*/
  public ENDeliveryKindShortList getFilteredList(ENDeliveryKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDeliveryKind. �������� ������ ��� ���������*/
  public ENDeliveryKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDeliveryKind. �������� ������ ��� ��������� �� �������*/
  public ENDeliveryKindShortList getScrollableFilteredList(ENDeliveryKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDeliveryKindDAO objectDAO = new ENDeliveryKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDeliveryKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDeliveryKind. �������� ������ ��� ��������� �� �������*/
  public ENDeliveryKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDeliveryKindFilter filterObject = new ENDeliveryKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.CNPackDAO;
import com.ksoe.energynet.valueobject.CNPack;
import com.ksoe.energynet.valueobject.lists.CNPackShortList;
import com.ksoe.energynet.valueobject.filter.CNPackFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//������ ��������� import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for CNPack;  
  * 	
  */

public abstract class CNPackControllerEJBGen extends GenericSessionStatelessBean
{
  public CNPackControllerEJBGen() {}

  /*CNPack. ��������*/
  public int add(CNPack object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPackValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. �������*/
  public void remove(int code)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNPack. ��������*/
  public void save(CNPack object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPackValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. �������� ������*/
  public CNPack getObject(int code)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. �������� ������ ������*/
  public CNPackShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNPack. �������� ������ �� �������*/
  public CNPackShortList getFilteredList(CNPackFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNPack. �������� ������ ��� ���������*/
  public CNPackShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNPack. �������� ������ ��� ��������� �� �������*/
  public CNPackShortList getScrollableFilteredList(CNPackFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNPack%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. �������� ������ ��� ��������� �� �������*/
  public CNPackShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNPackFilter filterObject = new CNPackFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*CNPack. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(CNPackFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.CNPack%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
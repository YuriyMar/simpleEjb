
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSpravMolDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSpravMol;
import com.ksoe.energynet.valueobject.filter.ENSpravMolFilter;
import com.ksoe.energynet.valueobject.lists.ENSpravMolShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSpravMol;  
  * 	
  */

public abstract class ENSpravMolControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSpravMolControllerEJBGen() {}

  /*ENSpravMol. ��������*/
  public int add(ENSpravMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSpravMolValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSpravMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSpravMol. �������*/
  public void remove(int code)
   {
    try
     {
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSpravMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSpravMol. ��������*/
  public void save(ENSpravMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSpravMolValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSpravMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSpravMol. �������� ������*/
  public ENSpravMol getObject(int code)
   {
    try
     {
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSpravMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSpravMol. �������� ������ ������*/
  public ENSpravMolShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSpravMol. �������� ������ �� �������*/
  public ENSpravMolShortList getFilteredList(ENSpravMolFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSpravMol. �������� ������ ��� ���������*/
  public ENSpravMolShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSpravMol. �������� ������ ��� ��������� �� �������*/
  public ENSpravMolShortList getScrollableFilteredList(ENSpravMolFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSpravMol%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSpravMol. �������� ������ ��� ��������� �� �������*/
  public ENSpravMolShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSpravMolFilter filterObject = new ENSpravMolFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENSpravMol. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENSpravMolFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSpravMolDAO objectDAO = new ENSpravMolDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENSpravMol%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
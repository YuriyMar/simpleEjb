
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENActIncomeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENActIncome;
import com.ksoe.energynet.valueobject.filter.ENActIncomeFilter;
import com.ksoe.energynet.valueobject.lists.ENActIncomeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENActIncome;  
  * 	
  */

public abstract class ENActIncomeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENActIncomeControllerEJBGen() {}

  /*ENActIncome. ��������*/
  public int add(ENActIncome object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENActIncome%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome. �������*/
  public void remove(int code)
   {
    try
     {
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENActIncome%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENActIncome. ��������*/
  public void save(ENActIncome object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENActIncomeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENActIncome%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome. �������� ������*/
  public ENActIncome getObject(int code)
   {
    try
     {
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENActIncome%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome. �������� ������ ������*/
  public ENActIncomeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENActIncome. �������� ������ �� �������*/
  public ENActIncomeShortList getFilteredList(ENActIncomeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENActIncome. �������� ������ ��� ���������*/
  public ENActIncomeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENActIncome. �������� ������ ��� ��������� �� �������*/
  public ENActIncomeShortList getScrollableFilteredList(ENActIncomeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENActIncome%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENActIncome. �������� ������ ��� ��������� �� �������*/
  public ENActIncomeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENActIncomeFilter filterObject = new ENActIncomeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENActIncome. �������� ������ ��-����� �� �������*/
  public int[] getScrollableFilteredCodeArray(ENActIncomeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENActIncomeDAO objectDAO = new ENActIncomeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENActIncome%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
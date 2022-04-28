
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FINMolDAO;
import com.ksoe.energynet.valueobject.FINMol;
import com.ksoe.energynet.valueobject.filter.FINMolFilter;
import com.ksoe.energynet.valueobject.lists.FINMolShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMol;
  *
  */

public abstract class FINMolControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMolControllerEJBGen() {}

  /*FINMol. ��������*/
  public int add(FINMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return Integer.MIN_VALUE ; //objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. �������*/
  public void remove(int code)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      //objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*FINMol. ��������*/
  public void save(FINMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. �������� ������*/
  public FINMol getObject(int code)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return null; //objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. �������� ������ ������*/
  public FINMolShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMol. �������� ������ �� �������*/
  public FINMolShortList getFilteredList(FINMolFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMol. �������� ������ ��� ���������*/
  public FINMolShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMol. �������� ������ ��� ��������� �� �������*/
  public FINMolShortList getScrollableFilteredList(FINMolFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("��� ����� � ������������ ... ������������ ������� ������ ��� ��� ���-�� ...",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. �������� ������ ��� ��������� �� �������*/
  public FINMolShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMolFilter filterObject = new FINMolFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}

//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright � 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENWorkerDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENWorker;
import com.ksoe.energynet.valueobject.filter.ENWorkerFilter;
import com.ksoe.energynet.valueobject.lists.ENWorkerShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENWorker;  
  * 	
  */

public abstract class ENWorkerControllerEJBGen extends GenericSessionStatelessBean
{
  public ENWorkerControllerEJBGen() {}

  /*ENWorker. ��������*/
  public int add(ENWorker object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkerValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkerDAO objectDAO = new ENWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorker. �������*/
  public void remove(int code)
   {
    try
     {
      ENWorkerDAO objectDAO = new ENWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENWorker. ��������*/
  public void save(ENWorker object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENWorkerValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENWorkerDAO objectDAO = new ENWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorker. �������� ������*/
  public ENWorker getObject(int code)
   {
    try
     {
      ENWorkerDAO objectDAO = new ENWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENWorker%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorker. �������� ������ ������*/
  public ENWorkerShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENWorker. �������� ������ �� �������*/
  public ENWorkerShortList getFilteredList(ENWorkerFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENWorker. �������� ������ ��� ���������*/
  public ENWorkerShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENWorker. �������� ������ ��� ��������� �� �������*/
  public ENWorkerShortList getScrollableFilteredList(ENWorkerFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENWorkerDAO objectDAO = new ENWorkerDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENWorker%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENWorker. �������� ������ ��� ��������� �� �������*/
  public ENWorkerShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENWorkerFilter filterObject = new ENWorkerFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
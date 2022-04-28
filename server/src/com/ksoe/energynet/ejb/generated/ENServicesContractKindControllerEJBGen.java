
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENServicesContractKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENServicesContractKind;
import com.ksoe.energynet.valueobject.filter.ENServicesContractKindFilter;
import com.ksoe.energynet.valueobject.lists.ENServicesContractKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENServicesContractKind;
  *
  */

public abstract class ENServicesContractKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENServicesContractKindControllerEJBGen() {}

  /*ENServicesContractKind. Добавить*/
  public int add(ENServicesContractKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesContractKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENServicesContractKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesContractKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENServicesContractKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENServicesContractKind. Изменить*/
  public void save(ENServicesContractKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENServicesContractKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENServicesContractKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesContractKind. Получить объект*/
  public ENServicesContractKind getObject(int code)
   {
    try
     {
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENServicesContractKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesContractKind. Получить полный список*/
  public ENServicesContractKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENServicesContractKind. Получить список по фильтру*/
  public ENServicesContractKindShortList getFilteredList(ENServicesContractKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENServicesContractKind. Получить список для просмотра*/
  public ENServicesContractKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENServicesContractKind. Получить список для просмотра по фильтру*/
  public ENServicesContractKindShortList getScrollableFilteredList(ENServicesContractKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENServicesContractKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENServicesContractKind. Получить список для просмотра по условию*/
  public ENServicesContractKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENServicesContractKindFilter filterObject = new ENServicesContractKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENServicesContractKind. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENServicesContractKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENServicesContractKindDAO objectDAO = new ENServicesContractKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENServicesContractKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


}
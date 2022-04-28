
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINDoc2MolDataDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINDoc2MolData;
import com.ksoe.energynet.valueobject.filter.FINDoc2MolDataFilter;
import com.ksoe.energynet.valueobject.lists.FINDoc2MolDataShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINDoc2MolData;  
  * 	
  */

public abstract class FINDoc2MolDataControllerEJBGen extends GenericSessionStatelessBean
{
  public FINDoc2MolDataControllerEJBGen() {}

  /*FINDoc2MolData. Добавить*/
  public int add(FINDoc2MolData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2MolDataValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2MolDataDAO objectDAO = new FINDoc2MolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINDoc2MolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2MolData. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINDoc2MolDataDAO objectDAO = new FINDoc2MolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINDoc2MolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*FINDoc2MolData. Изменить*/
  public void save(FINDoc2MolData object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINDoc2MolDataValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINDoc2MolDataDAO objectDAO = new FINDoc2MolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINDoc2MolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2MolData. Получить объект*/
  public FINDoc2MolData getObject(int code)
   {
    try
     {
      FINDoc2MolDataDAO objectDAO = new FINDoc2MolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINDoc2MolData%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2MolData. Получить полный список*/
  public FINDoc2MolDataShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINDoc2MolData. Получить список по фильтру*/
  public FINDoc2MolDataShortList getFilteredList(FINDoc2MolDataFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINDoc2MolData. Получить список для просмотра*/
  public FINDoc2MolDataShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINDoc2MolData. Получить список для просмотра по фильтру*/
  public FINDoc2MolDataShortList getScrollableFilteredList(FINDoc2MolDataFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINDoc2MolDataDAO objectDAO = new FINDoc2MolDataDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINDoc2MolData%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINDoc2MolData. Получить список для просмотра по условию*/
  public FINDoc2MolDataShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINDoc2MolDataFilter filterObject = new FINDoc2MolDataFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
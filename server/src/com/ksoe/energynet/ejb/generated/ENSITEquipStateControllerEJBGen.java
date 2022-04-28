
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITEquipStateDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITEquipState;
import com.ksoe.energynet.valueobject.filter.ENSITEquipStateFilter;
import com.ksoe.energynet.valueobject.lists.ENSITEquipStateShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITEquipState;  
  * 	
  */

public abstract class ENSITEquipStateControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITEquipStateControllerEJBGen() {}

  /*ENSITEquipState. Добавить*/
  public int add(ENSITEquipState object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipStateValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipStateDAO objectDAO = new ENSITEquipStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITEquipState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipState. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITEquipStateDAO objectDAO = new ENSITEquipStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITEquipState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITEquipState. Изменить*/
  public void save(ENSITEquipState object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipStateValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipStateDAO objectDAO = new ENSITEquipStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITEquipState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipState. Получить объект*/
  public ENSITEquipState getObject(int code)
   {
    try
     {
      ENSITEquipStateDAO objectDAO = new ENSITEquipStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITEquipState%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipState. Получить полный список*/
  public ENSITEquipStateShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITEquipState. Получить список по фильтру*/
  public ENSITEquipStateShortList getFilteredList(ENSITEquipStateFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITEquipState. Получить список для просмотра*/
  public ENSITEquipStateShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITEquipState. Получить список для просмотра по фильтру*/
  public ENSITEquipStateShortList getScrollableFilteredList(ENSITEquipStateFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITEquipStateDAO objectDAO = new ENSITEquipStateDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITEquipState%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipState. Получить список для просмотра по условию*/
  public ENSITEquipStateShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITEquipStateFilter filterObject = new ENSITEquipStateFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
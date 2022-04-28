
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITEquipTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITEquipType;
import com.ksoe.energynet.valueobject.filter.ENSITEquipTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENSITEquipTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITEquipType;  
  * 	
  */

public abstract class ENSITEquipTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITEquipTypeControllerEJBGen() {}

  /*ENSITEquipType. Добавить*/
  public int add(ENSITEquipType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipTypeDAO objectDAO = new ENSITEquipTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITEquipType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITEquipTypeDAO objectDAO = new ENSITEquipTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITEquipType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITEquipType. Изменить*/
  public void save(ENSITEquipType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipTypeDAO objectDAO = new ENSITEquipTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITEquipType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipType. Получить объект*/
  public ENSITEquipType getObject(int code)
   {
    try
     {
      ENSITEquipTypeDAO objectDAO = new ENSITEquipTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITEquipType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipType. Получить полный список*/
  public ENSITEquipTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITEquipType. Получить список по фильтру*/
  public ENSITEquipTypeShortList getFilteredList(ENSITEquipTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITEquipType. Получить список для просмотра*/
  public ENSITEquipTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITEquipType. Получить список для просмотра по фильтру*/
  public ENSITEquipTypeShortList getScrollableFilteredList(ENSITEquipTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITEquipTypeDAO objectDAO = new ENSITEquipTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITEquipType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipType. Получить список для просмотра по условию*/
  public ENSITEquipTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITEquipTypeFilter filterObject = new ENSITEquipTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
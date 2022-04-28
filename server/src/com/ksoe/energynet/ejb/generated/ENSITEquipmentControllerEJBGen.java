
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENSITEquipmentDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENSITEquipment;
import com.ksoe.energynet.valueobject.filter.ENSITEquipmentFilter;
import com.ksoe.energynet.valueobject.lists.ENSITEquipmentShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENSITEquipment;  
  * 	
  */

public abstract class ENSITEquipmentControllerEJBGen extends GenericSessionStatelessBean
{
  public ENSITEquipmentControllerEJBGen() {}

  /*ENSITEquipment. Добавить*/
  public int add(ENSITEquipment object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipmentValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipmentDAO objectDAO = new ENSITEquipmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENSITEquipment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipment. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENSITEquipmentDAO objectDAO = new ENSITEquipmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENSITEquipment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENSITEquipment. Изменить*/
  public void save(ENSITEquipment object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENSITEquipmentValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENSITEquipmentDAO objectDAO = new ENSITEquipmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENSITEquipment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipment. Получить объект*/
  public ENSITEquipment getObject(int code)
   {
    try
     {
      ENSITEquipmentDAO objectDAO = new ENSITEquipmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENSITEquipment%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipment. Получить полный список*/
  public ENSITEquipmentShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENSITEquipment. Получить список по фильтру*/
  public ENSITEquipmentShortList getFilteredList(ENSITEquipmentFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENSITEquipment. Получить список для просмотра*/
  public ENSITEquipmentShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENSITEquipment. Получить список для просмотра по фильтру*/
  public ENSITEquipmentShortList getScrollableFilteredList(ENSITEquipmentFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENSITEquipmentDAO objectDAO = new ENSITEquipmentDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENSITEquipment%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENSITEquipment. Получить список для просмотра по условию*/
  public ENSITEquipmentShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENSITEquipmentFilter filterObject = new ENSITEquipmentFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
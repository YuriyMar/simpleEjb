
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENDistanceTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENDistanceType;
import com.ksoe.energynet.valueobject.filter.ENDistanceTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENDistanceTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENDistanceType;  
  * 	
  */

public abstract class ENDistanceTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENDistanceTypeControllerEJBGen() {}

  /*ENDistanceType. Добавить*/
  public int add(ENDistanceType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDistanceTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDistanceTypeDAO objectDAO = new ENDistanceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENDistanceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistanceType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENDistanceTypeDAO objectDAO = new ENDistanceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENDistanceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENDistanceType. Изменить*/
  public void save(ENDistanceType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENDistanceTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENDistanceTypeDAO objectDAO = new ENDistanceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENDistanceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistanceType. Получить объект*/
  public ENDistanceType getObject(int code)
   {
    try
     {
      ENDistanceTypeDAO objectDAO = new ENDistanceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENDistanceType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistanceType. Получить полный список*/
  public ENDistanceTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENDistanceType. Получить список по фильтру*/
  public ENDistanceTypeShortList getFilteredList(ENDistanceTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENDistanceType. Получить список для просмотра*/
  public ENDistanceTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENDistanceType. Получить список для просмотра по фильтру*/
  public ENDistanceTypeShortList getScrollableFilteredList(ENDistanceTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENDistanceTypeDAO objectDAO = new ENDistanceTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENDistanceType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENDistanceType. Получить список для просмотра по условию*/
  public ENDistanceTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENDistanceTypeFilter filterObject = new ENDistanceTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
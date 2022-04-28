
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENManningTable2TKPositionDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENManningTable2TKPosition;
import com.ksoe.energynet.valueobject.filter.ENManningTable2TKPositionFilter;
import com.ksoe.energynet.valueobject.lists.ENManningTable2TKPositionShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENManningTable2TKPosition;  
  * 	
  */

public abstract class ENManningTable2TKPositionControllerEJBGen extends GenericSessionStatelessBean
{
  public ENManningTable2TKPositionControllerEJBGen() {}

  /*ENManningTable2TKPosition. Добавить*/
  public int add(ENManningTable2TKPosition object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManningTable2TKPositionValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManningTable2TKPositionDAO objectDAO = new ENManningTable2TKPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENManningTable2TKPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable2TKPosition. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENManningTable2TKPositionDAO objectDAO = new ENManningTable2TKPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENManningTable2TKPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENManningTable2TKPosition. Изменить*/
  public void save(ENManningTable2TKPosition object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENManningTable2TKPositionValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENManningTable2TKPositionDAO objectDAO = new ENManningTable2TKPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENManningTable2TKPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable2TKPosition. Получить объект*/
  public ENManningTable2TKPosition getObject(int code)
   {
    try
     {
      ENManningTable2TKPositionDAO objectDAO = new ENManningTable2TKPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENManningTable2TKPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable2TKPosition. Получить полный список*/
  public ENManningTable2TKPositionShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENManningTable2TKPosition. Получить список по фильтру*/
  public ENManningTable2TKPositionShortList getFilteredList(ENManningTable2TKPositionFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENManningTable2TKPosition. Получить список для просмотра*/
  public ENManningTable2TKPositionShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENManningTable2TKPosition. Получить список для просмотра по фильтру*/
  public ENManningTable2TKPositionShortList getScrollableFilteredList(ENManningTable2TKPositionFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENManningTable2TKPositionDAO objectDAO = new ENManningTable2TKPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENManningTable2TKPosition%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENManningTable2TKPosition. Получить список для просмотра по условию*/
  public ENManningTable2TKPositionShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENManningTable2TKPositionFilter filterObject = new ENManningTable2TKPositionFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
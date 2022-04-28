
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPositionDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPosition;
import com.ksoe.energynet.valueobject.filter.ENPositionFilter;
import com.ksoe.energynet.valueobject.lists.ENPositionShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPosition;  
  * 	
  */

public abstract class ENPositionControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPositionControllerEJBGen() {}

  /*ENPosition. Добавить*/
  public int add(ENPosition object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPositionValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPositionDAO objectDAO = new ENPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPosition. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPositionDAO objectDAO = new ENPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPosition. Изменить*/
  public void save(ENPosition object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPositionValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPositionDAO objectDAO = new ENPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPosition. Получить объект*/
  public ENPosition getObject(int code)
   {
    try
     {
      ENPositionDAO objectDAO = new ENPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPosition%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPosition. Получить полный список*/
  public ENPositionShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPosition. Получить список по фильтру*/
  public ENPositionShortList getFilteredList(ENPositionFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPosition. Получить список для просмотра*/
  public ENPositionShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPosition. Получить список для просмотра по фильтру*/
  public ENPositionShortList getScrollableFilteredList(ENPositionFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPositionDAO objectDAO = new ENPositionDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPosition%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPosition. Получить список для просмотра по условию*/
  public ENPositionShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPositionFilter filterObject = new ENPositionFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
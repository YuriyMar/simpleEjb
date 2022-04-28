
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENNomenclaturesOperativeDAO;
import com.ksoe.energynet.valueobject.ENNomenclaturesOperative;
import com.ksoe.energynet.valueobject.lists.ENNomenclaturesOperativeShortList;
import com.ksoe.energynet.valueobject.filter.ENNomenclaturesOperativeFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENNomenclaturesOperative;  
  * 	
  */

public abstract class ENNomenclaturesOperativeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENNomenclaturesOperativeControllerEJBGen() {}

  /*ENNomenclaturesOperative. Добавить*/
  public int add(ENNomenclaturesOperative object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENNomenclaturesOperativeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENNomenclaturesOperative. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENNomenclaturesOperative. Изменить*/
  public void save(ENNomenclaturesOperative object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENNomenclaturesOperativeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENNomenclaturesOperative. Получить объект*/
  public ENNomenclaturesOperative getObject(int code)
   {
    try
     {
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENNomenclaturesOperative. Получить полный список*/
  public ENNomenclaturesOperativeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENNomenclaturesOperative. Получить список по фильтру*/
  public ENNomenclaturesOperativeShortList getFilteredList(ENNomenclaturesOperativeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENNomenclaturesOperative. Получить список для просмотра*/
  public ENNomenclaturesOperativeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENNomenclaturesOperative. Получить список для просмотра по фильтру*/
  public ENNomenclaturesOperativeShortList getScrollableFilteredList(ENNomenclaturesOperativeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENNomenclaturesOperative. Получить список для просмотра по условию*/
  public ENNomenclaturesOperativeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENNomenclaturesOperativeFilter filterObject = new ENNomenclaturesOperativeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENNomenclaturesOperative. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENNomenclaturesOperativeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENNomenclaturesOperativeDAO objectDAO = new ENNomenclaturesOperativeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENNomenclaturesOperative%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
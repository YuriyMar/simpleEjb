
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENConnectionKindDAO;
import com.ksoe.energynet.valueobject.ENConnectionKind;
import com.ksoe.energynet.valueobject.lists.ENConnectionKindShortList;
import com.ksoe.energynet.valueobject.filter.ENConnectionKindFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENConnectionKind;  
  * 	
  */

public abstract class ENConnectionKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENConnectionKindControllerEJBGen() {}

  /*ENConnectionKind. Добавить*/
  public int add(ENConnectionKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENConnectionKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENConnectionKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENConnectionKind. Изменить*/
  public void save(ENConnectionKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENConnectionKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENConnectionKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionKind. Получить объект*/
  public ENConnectionKind getObject(int code)
   {
    try
     {
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENConnectionKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionKind. Получить полный список*/
  public ENConnectionKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENConnectionKind. Получить список по фильтру*/
  public ENConnectionKindShortList getFilteredList(ENConnectionKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENConnectionKind. Получить список для просмотра*/
  public ENConnectionKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENConnectionKind. Получить список для просмотра по фильтру*/
  public ENConnectionKindShortList getScrollableFilteredList(ENConnectionKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENConnectionKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENConnectionKind. Получить список для просмотра по условию*/
  public ENConnectionKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENConnectionKindFilter filterObject = new ENConnectionKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENConnectionKind. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENConnectionKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENConnectionKindDAO objectDAO = new ENConnectionKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENConnectionKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENResponsiblesKindDAO;
import com.ksoe.energynet.valueobject.ENResponsiblesKind;
import com.ksoe.energynet.valueobject.lists.ENResponsiblesKindShortList;
import com.ksoe.energynet.valueobject.filter.ENResponsiblesKindFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENResponsiblesKind;  
  * 	
  */

public abstract class ENResponsiblesKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENResponsiblesKindControllerEJBGen() {}

  /*ENResponsiblesKind. Добавить*/
  public int add(ENResponsiblesKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENResponsiblesKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsiblesKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENResponsiblesKind. Изменить*/
  public void save(ENResponsiblesKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENResponsiblesKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsiblesKind. Получить объект*/
  public ENResponsiblesKind getObject(int code)
   {
    try
     {
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsiblesKind. Получить полный список*/
  public ENResponsiblesKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENResponsiblesKind. Получить список по фильтру*/
  public ENResponsiblesKindShortList getFilteredList(ENResponsiblesKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENResponsiblesKind. Получить список для просмотра*/
  public ENResponsiblesKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENResponsiblesKind. Получить список для просмотра по фильтру*/
  public ENResponsiblesKindShortList getScrollableFilteredList(ENResponsiblesKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENResponsiblesKind. Получить список для просмотра по условию*/
  public ENResponsiblesKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENResponsiblesKindFilter filterObject = new ENResponsiblesKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENResponsiblesKind. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENResponsiblesKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENResponsiblesKindDAO objectDAO = new ENResponsiblesKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENResponsiblesKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
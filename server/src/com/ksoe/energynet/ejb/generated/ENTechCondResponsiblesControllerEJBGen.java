
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTechCondResponsiblesDAO;
import com.ksoe.energynet.valueobject.ENTechCondResponsibles;
import com.ksoe.energynet.valueobject.lists.ENTechCondResponsiblesShortList;
import com.ksoe.energynet.valueobject.filter.ENTechCondResponsiblesFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTechCondResponsibles;  
  * 	
  */

public abstract class ENTechCondResponsiblesControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechCondResponsiblesControllerEJBGen() {}

  /*ENTechCondResponsibles. Добавить*/
  public int add(ENTechCondResponsibles object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCondResponsiblesValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechCondResponsibles. Изменить*/
  public void save(ENTechCondResponsibles object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCondResponsiblesValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles. Получить объект*/
  public ENTechCondResponsibles getObject(int code)
   {
    try
     {
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles. Получить полный список*/
  public ENTechCondResponsiblesShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechCondResponsibles. Получить список по фильтру*/
  public ENTechCondResponsiblesShortList getFilteredList(ENTechCondResponsiblesFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechCondResponsibles. Получить список для просмотра*/
  public ENTechCondResponsiblesShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechCondResponsibles. Получить список для просмотра по фильтру*/
  public ENTechCondResponsiblesShortList getScrollableFilteredList(ENTechCondResponsiblesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles. Получить список для просмотра по условию*/
  public ENTechCondResponsiblesShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechCondResponsiblesFilter filterObject = new ENTechCondResponsiblesFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechCondResponsibles. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechCondResponsiblesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCondResponsiblesDAO objectDAO = new ENTechCondResponsiblesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechCondResponsibles%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
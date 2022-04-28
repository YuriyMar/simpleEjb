
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.ENTechCondResponsibles2DepDAO;
import com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep;
import com.ksoe.energynet.valueobject.lists.ENTechCondResponsibles2DepShortList;
import com.ksoe.energynet.valueobject.filter.ENTechCondResponsibles2DepFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for ENTechCondResponsibles2Dep;  
  * 	
  */

public abstract class ENTechCondResponsibles2DepControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechCondResponsibles2DepControllerEJBGen() {}

  /*ENTechCondResponsibles2Dep. Добавить*/
  public int add(ENTechCondResponsibles2Dep object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCondResponsibles2DepValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles2Dep. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechCondResponsibles2Dep. Изменить*/
  public void save(ENTechCondResponsibles2Dep object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechCondResponsibles2DepValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles2Dep. Получить объект*/
  public ENTechCondResponsibles2Dep getObject(int code)
   {
    try
     {
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles2Dep. Получить полный список*/
  public ENTechCondResponsibles2DepShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechCondResponsibles2Dep. Получить список по фильтру*/
  public ENTechCondResponsibles2DepShortList getFilteredList(ENTechCondResponsibles2DepFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechCondResponsibles2Dep. Получить список для просмотра*/
  public ENTechCondResponsibles2DepShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechCondResponsibles2Dep. Получить список для просмотра по фильтру*/
  public ENTechCondResponsibles2DepShortList getScrollableFilteredList(ENTechCondResponsibles2DepFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechCondResponsibles2Dep. Получить список для просмотра по условию*/
  public ENTechCondResponsibles2DepShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechCondResponsibles2DepFilter filterObject = new ENTechCondResponsibles2DepFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechCondResponsibles2Dep. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechCondResponsibles2DepFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechCondResponsibles2DepDAO objectDAO = new ENTechCondResponsibles2DepDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechCondResponsibles2Dep%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTechContragentFormDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTechContragentForm;
import com.ksoe.energynet.valueobject.filter.ENTechContragentFormFilter;
import com.ksoe.energynet.valueobject.lists.ENTechContragentFormShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTechContragentForm;  
  * 	
  */

public abstract class ENTechContragentFormControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechContragentFormControllerEJBGen() {}

  /*ENTechContragentForm. Добавить*/
  public int add(ENTechContragentForm object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechContragentFormValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechContragentForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentForm. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechContragentForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTechContragentForm. Изменить*/
  public void save(ENTechContragentForm object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechContragentFormValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechContragentForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentForm. Получить объект*/
  public ENTechContragentForm getObject(int code)
   {
    try
     {
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechContragentForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentForm. Получить полный список*/
  public ENTechContragentFormShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechContragentForm. Получить список по фильтру*/
  public ENTechContragentFormShortList getFilteredList(ENTechContragentFormFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechContragentForm. Получить список для просмотра*/
  public ENTechContragentFormShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechContragentForm. Получить список для просмотра по фильтру*/
  public ENTechContragentFormShortList getScrollableFilteredList(ENTechContragentFormFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechContragentForm%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechContragentForm. Получить список для просмотра по условию*/
  public ENTechContragentFormShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechContragentFormFilter filterObject = new ENTechContragentFormFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechContragentForm. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechContragentFormFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechContragentFormDAO objectDAO = new ENTechContragentFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechContragentForm%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2013 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.CNTechTermsDAO;
import com.ksoe.energynet.valueobject.CNTechTerms;
import com.ksoe.energynet.valueobject.lists.CNTechTermsShortList;
import com.ksoe.energynet.valueobject.filter.CNTechTermsFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for CNTechTerms;  
  * 	
  */

public abstract class CNTechTermsControllerEJBGen extends GenericSessionStatelessBean
{
  public CNTechTermsControllerEJBGen() {}

  /*CNTechTerms. Добавить*/
  public int add(CNTechTerms object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNTechTermsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNTechTerms%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNTechTerms. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNTechTerms%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNTechTerms. Изменить*/
  public void save(CNTechTerms object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNTechTermsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNTechTerms%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNTechTerms. Получить объект*/
  public CNTechTerms getObject(int code)
   {
    try
     {
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNTechTerms%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNTechTerms. Получить полный список*/
  public CNTechTermsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNTechTerms. Получить список по фильтру*/
  public CNTechTermsShortList getFilteredList(CNTechTermsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNTechTerms. Получить список для просмотра*/
  public CNTechTermsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNTechTerms. Получить список для просмотра по фильтру*/
  public CNTechTermsShortList getScrollableFilteredList(CNTechTermsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNTechTerms%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNTechTerms. Получить список для просмотра по условию*/
  public CNTechTermsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNTechTermsFilter filterObject = new CNTechTermsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ECNTechTerms. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(CNTechTermsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNTechTermsDAO objectDAO = new CNTechTermsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.CNTechTerms%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
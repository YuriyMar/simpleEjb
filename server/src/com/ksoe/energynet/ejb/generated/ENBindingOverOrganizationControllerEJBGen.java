
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENBindingOverOrganizationDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENBindingOverOrganization;
import com.ksoe.energynet.valueobject.filter.ENBindingOverOrganizationFilter;
import com.ksoe.energynet.valueobject.lists.ENBindingOverOrganizationShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENBindingOverOrganization;  
  * 	
  */

public abstract class ENBindingOverOrganizationControllerEJBGen extends GenericSessionStatelessBean
{
  public ENBindingOverOrganizationControllerEJBGen() {}

  /*ENBindingOverOrganization. Добавить*/
  public int add(ENBindingOverOrganization object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBindingOverOrganizationValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBindingOverOrganizationDAO objectDAO = new ENBindingOverOrganizationDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENBindingOverOrganization%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOverOrganization. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENBindingOverOrganizationDAO objectDAO = new ENBindingOverOrganizationDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENBindingOverOrganization%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENBindingOverOrganization. Изменить*/
  public void save(ENBindingOverOrganization object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBindingOverOrganizationValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBindingOverOrganizationDAO objectDAO = new ENBindingOverOrganizationDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENBindingOverOrganization%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOverOrganization. Получить объект*/
  public ENBindingOverOrganization getObject(int code)
   {
    try
     {
      ENBindingOverOrganizationDAO objectDAO = new ENBindingOverOrganizationDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENBindingOverOrganization%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOverOrganization. Получить полный список*/
  public ENBindingOverOrganizationShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENBindingOverOrganization. Получить список по фильтру*/
  public ENBindingOverOrganizationShortList getFilteredList(ENBindingOverOrganizationFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENBindingOverOrganization. Получить список для просмотра*/
  public ENBindingOverOrganizationShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENBindingOverOrganization. Получить список для просмотра по фильтру*/
  public ENBindingOverOrganizationShortList getScrollableFilteredList(ENBindingOverOrganizationFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBindingOverOrganizationDAO objectDAO = new ENBindingOverOrganizationDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENBindingOverOrganization%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOverOrganization. Получить список для просмотра по условию*/
  public ENBindingOverOrganizationShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENBindingOverOrganizationFilter filterObject = new ENBindingOverOrganizationFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
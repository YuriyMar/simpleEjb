
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTechConditionsServicesDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTechConditionsServices;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENTechConditionsServicesFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENTechConditionsServicesShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTechConditionsServices;
  *
  */

public abstract class ENTechConditionsServicesControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTechConditionsServicesControllerEJBGen() {}

  /*ENTechConditionsServices. Добавить*/
  public int add(ENTechConditionsServices object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServices. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENTechConditionsServices. Изменить*/
  public void save(ENTechConditionsServices object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTechConditionsServicesValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServices. Получить объект*/
  public ENTechConditionsServices getObject(int code)
   {
    try
     {
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServices. Получить полный список*/
  public ENTechConditionsServicesShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTechConditionsServices. Получить список по фильтру*/
  public ENTechConditionsServicesShortList getFilteredList(ENTechConditionsServicesFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTechConditionsServices. Получить список для просмотра*/
  public ENTechConditionsServicesShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTechConditionsServices. Получить список для просмотра по фильтру*/
  public ENTechConditionsServicesShortList getScrollableFilteredList(ENTechConditionsServicesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTechConditionsServices. Получить список для просмотра по условию*/
  public ENTechConditionsServicesShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTechConditionsServicesFilter filterObject = new ENTechConditionsServicesFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTechConditionsServices. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTechConditionsServicesFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTechConditionsServicesDAO objectDAO = new ENTechConditionsServicesDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTechConditionsServices%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

}
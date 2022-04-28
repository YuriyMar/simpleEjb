
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWork2ClassificationTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2ClassificationTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2ClassificationTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWork2ClassificationType;  
  * 	
  */

public abstract class ENPlanWork2ClassificationTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWork2ClassificationTypeControllerEJBGen() {}

  /*ENPlanWork2ClassificationType. Добавить*/
  public int add(ENPlanWork2ClassificationType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2ClassificationTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2ClassificationType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWork2ClassificationType. Изменить*/
  public void save(ENPlanWork2ClassificationType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWork2ClassificationTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2ClassificationType. Получить объект*/
  public ENPlanWork2ClassificationType getObject(int code)
   {
    try
     {
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2ClassificationType. Получить полный список*/
  public ENPlanWork2ClassificationTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWork2ClassificationType. Получить список по фильтру*/
  public ENPlanWork2ClassificationTypeShortList getFilteredList(ENPlanWork2ClassificationTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWork2ClassificationType. Получить список для просмотра*/
  public ENPlanWork2ClassificationTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWork2ClassificationType. Получить список для просмотра по фильтру*/
  public ENPlanWork2ClassificationTypeShortList getScrollableFilteredList(ENPlanWork2ClassificationTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWork2ClassificationType. Получить список для просмотра по условию*/
  public ENPlanWork2ClassificationTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWork2ClassificationTypeFilter filterObject = new ENPlanWork2ClassificationTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENPlanWork2ClassificationType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENPlanWork2ClassificationTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWork2ClassificationTypeDAO objectDAO = new ENPlanWork2ClassificationTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
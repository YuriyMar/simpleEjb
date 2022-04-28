
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkFormDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkForm;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFormFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFormShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkForm;  
  * 	
  */

public abstract class ENPlanWorkFormControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkFormControllerEJBGen() {}

  /*ENPlanWorkForm. Добавить*/
  public int add(ENPlanWorkForm object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkFormValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkFormDAO objectDAO = new ENPlanWorkFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkForm. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkFormDAO objectDAO = new ENPlanWorkFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkForm. Изменить*/
  public void save(ENPlanWorkForm object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkFormValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkFormDAO objectDAO = new ENPlanWorkFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkForm. Получить объект*/
  public ENPlanWorkForm getObject(int code)
   {
    try
     {
      ENPlanWorkFormDAO objectDAO = new ENPlanWorkFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkForm%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkForm. Получить полный список*/
  public ENPlanWorkFormShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkForm. Получить список по фильтру*/
  public ENPlanWorkFormShortList getFilteredList(ENPlanWorkFormFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkForm. Получить список для просмотра*/
  public ENPlanWorkFormShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkForm. Получить список для просмотра по фильтру*/
  public ENPlanWorkFormShortList getScrollableFilteredList(ENPlanWorkFormFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkFormDAO objectDAO = new ENPlanWorkFormDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkForm%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkForm. Получить список для просмотра по условию*/
  public ENPlanWorkFormShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkFormFilter filterObject = new ENPlanWorkFormFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
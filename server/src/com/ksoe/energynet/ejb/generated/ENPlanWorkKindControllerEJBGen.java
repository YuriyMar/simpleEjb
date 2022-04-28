
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPlanWorkKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkKindFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPlanWorkKind;  
  * 	
  */

public abstract class ENPlanWorkKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPlanWorkKindControllerEJBGen() {}

  /*ENPlanWorkKind. Добавить*/
  public int add(ENPlanWorkKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkKindDAO objectDAO = new ENPlanWorkKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPlanWorkKindDAO objectDAO = new ENPlanWorkKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPlanWorkKind. Изменить*/
  public void save(ENPlanWorkKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPlanWorkKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPlanWorkKindDAO objectDAO = new ENPlanWorkKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkKind. Получить объект*/
  public ENPlanWorkKind getObject(int code)
   {
    try
     {
      ENPlanWorkKindDAO objectDAO = new ENPlanWorkKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPlanWorkKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkKind. Получить полный список*/
  public ENPlanWorkKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPlanWorkKind. Получить список по фильтру*/
  public ENPlanWorkKindShortList getFilteredList(ENPlanWorkKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPlanWorkKind. Получить список для просмотра*/
  public ENPlanWorkKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPlanWorkKind. Получить список для просмотра по фильтру*/
  public ENPlanWorkKindShortList getScrollableFilteredList(ENPlanWorkKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPlanWorkKindDAO objectDAO = new ENPlanWorkKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWorkKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPlanWorkKind. Получить список для просмотра по условию*/
  public ENPlanWorkKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPlanWorkKindFilter filterObject = new ENPlanWorkKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
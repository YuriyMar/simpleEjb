
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENBindingOverDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENBindingOver;
import com.ksoe.energynet.valueobject.filter.ENBindingOverFilter;
import com.ksoe.energynet.valueobject.lists.ENBindingOverShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENBindingOver;  
  * 	
  */

public abstract class ENBindingOverControllerEJBGen extends GenericSessionStatelessBean
{
  public ENBindingOverControllerEJBGen() {}

  /*ENBindingOver. Добавить*/
  public int add(ENBindingOver object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBindingOverValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBindingOverDAO objectDAO = new ENBindingOverDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENBindingOver%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOver. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENBindingOverDAO objectDAO = new ENBindingOverDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENBindingOver%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENBindingOver. Изменить*/
  public void save(ENBindingOver object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBindingOverValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBindingOverDAO objectDAO = new ENBindingOverDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENBindingOver%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOver. Получить объект*/
  public ENBindingOver getObject(int code)
   {
    try
     {
      ENBindingOverDAO objectDAO = new ENBindingOverDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENBindingOver%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOver. Получить полный список*/
  public ENBindingOverShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENBindingOver. Получить список по фильтру*/
  public ENBindingOverShortList getFilteredList(ENBindingOverFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENBindingOver. Получить список для просмотра*/
  public ENBindingOverShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENBindingOver. Получить список для просмотра по фильтру*/
  public ENBindingOverShortList getScrollableFilteredList(ENBindingOverFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBindingOverDAO objectDAO = new ENBindingOverDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENBindingOver%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBindingOver. Получить список для просмотра по условию*/
  public ENBindingOverShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENBindingOverFilter filterObject = new ENBindingOverFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
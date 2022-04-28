
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItem2TypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItem2Type;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2TypeFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItem2TypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItem2Type;  
  * 	
  */

public abstract class ENEstimateItem2TypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItem2TypeControllerEJBGen() {}

  /*ENEstimateItem2Type. Добавить*/
  public int add(ENEstimateItem2Type object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2TypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2TypeDAO objectDAO = new ENEstimateItem2TypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem2Type%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Type. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItem2TypeDAO objectDAO = new ENEstimateItem2TypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem2Type%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENEstimateItem2Type. Изменить*/
  public void save(ENEstimateItem2Type object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENEstimateItem2TypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENEstimateItem2TypeDAO objectDAO = new ENEstimateItem2TypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem2Type%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Type. Получить объект*/
  public ENEstimateItem2Type getObject(int code)
   {
    try
     {
      ENEstimateItem2TypeDAO objectDAO = new ENEstimateItem2TypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem2Type%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Type. Получить полный список*/
  public ENEstimateItem2TypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItem2Type. Получить список по фильтру*/
  public ENEstimateItem2TypeShortList getFilteredList(ENEstimateItem2TypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItem2Type. Получить список для просмотра*/
  public ENEstimateItem2TypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItem2Type. Получить список для просмотра по фильтру*/
  public ENEstimateItem2TypeShortList getScrollableFilteredList(ENEstimateItem2TypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItem2TypeDAO objectDAO = new ENEstimateItem2TypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem2Type%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem2Type. Получить список для просмотра по условию*/
  public ENEstimateItem2TypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItem2TypeFilter filterObject = new ENEstimateItem2TypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
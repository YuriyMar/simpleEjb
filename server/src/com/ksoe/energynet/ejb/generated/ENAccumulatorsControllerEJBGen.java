
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAccumulatorsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAccumulators;
import com.ksoe.energynet.valueobject.filter.ENAccumulatorsFilter;
import com.ksoe.energynet.valueobject.lists.ENAccumulatorsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAccumulators;  
  * 	
  */

public abstract class ENAccumulatorsControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAccumulatorsControllerEJBGen() {}

  /*ENAccumulators. Добавить*/
  public int add(ENAccumulators object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAccumulators%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulators. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAccumulators%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAccumulators. Изменить*/
  public void save(ENAccumulators object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAccumulators%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulators. Получить объект*/
  public ENAccumulators getObject(int code)
   {
    try
     {
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAccumulators%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulators. Получить полный список*/
  public ENAccumulatorsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAccumulators. Получить список по фильтру*/
  public ENAccumulatorsShortList getFilteredList(ENAccumulatorsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAccumulators. Получить список для просмотра*/
  public ENAccumulatorsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAccumulators. Получить список для просмотра по фильтру*/
  public ENAccumulatorsShortList getScrollableFilteredList(ENAccumulatorsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAccumulators%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulators. Получить список для просмотра по условию*/
  public ENAccumulatorsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAccumulatorsFilter filterObject = new ENAccumulatorsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAccumulators. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAccumulatorsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsDAO objectDAO = new ENAccumulatorsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAccumulators%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
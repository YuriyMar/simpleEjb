
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENAccumulatorsDistanceDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENAccumulatorsDistance;
import com.ksoe.energynet.valueobject.filter.ENAccumulatorsDistanceFilter;
import com.ksoe.energynet.valueobject.lists.ENAccumulatorsDistanceShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENAccumulatorsDistance;  
  * 	
  */

public abstract class ENAccumulatorsDistanceControllerEJBGen extends GenericSessionStatelessBean
{
  public ENAccumulatorsDistanceControllerEJBGen() {}

  /*ENAccumulatorsDistance. Добавить*/
  public int add(ENAccumulatorsDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsDistanceValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsDistance. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENAccumulatorsDistance. Изменить*/
  public void save(ENAccumulatorsDistance object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENAccumulatorsDistanceValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsDistance. Получить объект*/
  public ENAccumulatorsDistance getObject(int code)
   {
    try
     {
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsDistance. Получить полный список*/
  public ENAccumulatorsDistanceShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENAccumulatorsDistance. Получить список по фильтру*/
  public ENAccumulatorsDistanceShortList getFilteredList(ENAccumulatorsDistanceFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENAccumulatorsDistance. Получить список для просмотра*/
  public ENAccumulatorsDistanceShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENAccumulatorsDistance. Получить список для просмотра по фильтру*/
  public ENAccumulatorsDistanceShortList getScrollableFilteredList(ENAccumulatorsDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENAccumulatorsDistance. Получить список для просмотра по условию*/
  public ENAccumulatorsDistanceShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENAccumulatorsDistanceFilter filterObject = new ENAccumulatorsDistanceFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENAccumulatorsDistance. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENAccumulatorsDistanceFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENAccumulatorsDistanceDAO objectDAO = new ENAccumulatorsDistanceDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENAccumulatorsDistance%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}

//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;

import com.ksoe.energynet.dataminer.CNPackDAO;
import com.ksoe.energynet.valueobject.CNPack;
import com.ksoe.energynet.valueobject.lists.CNPackShortList;
import com.ksoe.energynet.valueobject.filter.CNPackFilter;

import com.ksoe.energypro.exception.EnergyproSystemException;
//другой валидатор import com.ksoe.energypro.validation.EnergyproValueObjectsValidator;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;

  /**
  * EJB controller for CNPack;  
  * 	
  */

public abstract class CNPackControllerEJBGen extends GenericSessionStatelessBean
{
  public CNPackControllerEJBGen() {}

  /*CNPack. Добавить*/
  public int add(CNPack object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPackValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. Удалить*/
  public void remove(int code)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*CNPack. Изменить*/
  public void save(CNPack object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isCNPackValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. Получить объект*/
  public CNPack getObject(int code)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.CNPack%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. Получить полный список*/
  public CNPackShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*CNPack. Получить список по фильтру*/
  public CNPackShortList getFilteredList(CNPackFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*CNPack. Получить список для просмотра*/
  public CNPackShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ECNPack. Получить список для просмотра по фильтру*/
  public CNPackShortList getScrollableFilteredList(CNPackFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.CNPack%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*CNPack. Получить список для просмотра по условию*/
  public CNPackShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    CNPackFilter filterObject = new CNPackFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*CNPack. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(CNPackFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      CNPackDAO objectDAO = new CNPackDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.CNPack%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
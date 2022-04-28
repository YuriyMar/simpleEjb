
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTrptGPS2TrptRealDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal;
import com.ksoe.energynet.valueobject.filter.ENTrptGPS2TrptRealFilter;
import com.ksoe.energynet.valueobject.lists.ENTrptGPS2TrptRealShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTrptGPS2TrptReal;  
  * 	
  */

public abstract class ENTrptGPS2TrptRealControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTrptGPS2TrptRealControllerEJBGen() {}

  /*ENTrptGPS2TrptReal. Добавить*/
  public int add(ENTrptGPS2TrptReal object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTrptGPS2TrptRealValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrptGPS2TrptReal. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTrptGPS2TrptReal. Изменить*/
  public void save(ENTrptGPS2TrptReal object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTrptGPS2TrptRealValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrptGPS2TrptReal. Получить объект*/
  public ENTrptGPS2TrptReal getObject(int code)
   {
    try
     {
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrptGPS2TrptReal. Получить полный список*/
  public ENTrptGPS2TrptRealShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTrptGPS2TrptReal. Получить список по фильтру*/
  public ENTrptGPS2TrptRealShortList getFilteredList(ENTrptGPS2TrptRealFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTrptGPS2TrptReal. Получить список для просмотра*/
  public ENTrptGPS2TrptRealShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTrptGPS2TrptReal. Получить список для просмотра по фильтру*/
  public ENTrptGPS2TrptRealShortList getScrollableFilteredList(ENTrptGPS2TrptRealFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTrptGPS2TrptReal. Получить список для просмотра по условию*/
  public ENTrptGPS2TrptRealShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTrptGPS2TrptRealFilter filterObject = new ENTrptGPS2TrptRealFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENTrptGPS2TrptReal. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENTrptGPS2TrptRealFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTrptGPS2TrptRealDAO objectDAO = new ENTrptGPS2TrptRealDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENTrptGPS2TrptReal%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
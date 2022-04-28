
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.SCCounterDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.SCCounter;
import com.ksoe.energynet.valueobject.SCCounterKind;
import com.ksoe.energynet.valueobject.filter.SCCounterFilter;
import com.ksoe.energynet.valueobject.lists.SCCounterShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for SCCounter;  
  * 	
  */

public abstract class SCCounterControllerEJBGen extends GenericSessionStatelessBean
{
  public SCCounterControllerEJBGen() {}

  /*SCCounter. Добавить*/
  public int add(SCCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
    	SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        SCCounterFilter scFilter=new SCCounterFilter();
        SCCounterShortList scList;
        
        if (object.kindRef.code==SCCounterKind.FOR_FACT)
        {
          scFilter.estimateItemRef.code=object.estimateItemRef.code;
          scFilter.invNumber=object.invNumber;
          scFilter.kindRef.code=SCCounterKind.FOR_FACT;
          scList=objectDAO.getScrollableFilteredList(scFilter,0,1);
          
          if (scList.list.size()>0)
        	  throw new EnergyproSystemException("Лічильник вже прив'язаний");
        }
        
    	
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounter. Удалить*/
  public void remove(int code)
   {
    try
     {
      SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*SCCounter. Изменить*/
  public void save(SCCounter object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isSCCounterValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounter. Получить объект*/
  public SCCounter getObject(int code)
   {
    try
     {
      SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.SCCounter%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounter. Получить полный список*/
  public SCCounterShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*SCCounter. Получить список по фильтру*/
  public SCCounterShortList getFilteredList(SCCounterFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*SCCounter. Получить список для просмотра*/
  public SCCounterShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*ESCCounter. Получить список для просмотра по фильтру*/
  public SCCounterShortList getScrollableFilteredList(SCCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.SCCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*SCCounter. Получить список для просмотра по условию*/
  public SCCounterShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    SCCounterFilter filterObject = new SCCounterFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*ESCCounter. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(SCCounterFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      SCCounterDAO objectDAO = new SCCounterDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.SCCounter%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

   
}
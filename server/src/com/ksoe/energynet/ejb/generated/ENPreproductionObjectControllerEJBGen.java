
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENPreproductionObjectDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPreproductionObject;
import com.ksoe.energynet.valueobject.filter.ENPreproductionObjectFilter;
import com.ksoe.energynet.valueobject.lists.ENPreproductionObjectShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENPreproductionObject;  
  * 	
  */

public abstract class ENPreproductionObjectControllerEJBGen extends GenericSessionStatelessBean
{
  public ENPreproductionObjectControllerEJBGen() {}

  /*ENPreproductionObject. Добавить*/
  public int add(ENPreproductionObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPreproductionObjectValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPreproductionObjectDAO objectDAO = new ENPreproductionObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPreproductionObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPreproductionObject. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENPreproductionObjectDAO objectDAO = new ENPreproductionObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPreproductionObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENPreproductionObject. Изменить*/
  public void save(ENPreproductionObject object)
   {
    try
     {
      object.setDomain_info(null);
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENPreproductionObjectValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENPreproductionObjectDAO objectDAO = new ENPreproductionObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPreproductionObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPreproductionObject. Получить объект*/
  public ENPreproductionObject getObject(int code)
   {
    try
     {
      ENPreproductionObjectDAO objectDAO = new ENPreproductionObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENPreproductionObject%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPreproductionObject. Получить полный список*/
  public ENPreproductionObjectShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENPreproductionObject. Получить список по фильтру*/
  public ENPreproductionObjectShortList getFilteredList(ENPreproductionObjectFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENPreproductionObject. Получить список для просмотра*/
  public ENPreproductionObjectShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENPreproductionObject. Получить список для просмотра по фильтру*/
  public ENPreproductionObjectShortList getScrollableFilteredList(ENPreproductionObjectFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENPreproductionObjectDAO objectDAO = new ENPreproductionObjectDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPreproductionObject%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENPreproductionObject. Получить список для просмотра по условию*/
  public ENPreproductionObjectShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENPreproductionObjectFilter filterObject = new ENPreproductionObjectFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
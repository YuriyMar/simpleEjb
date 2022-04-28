
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.FINMaterialsDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.FINMaterials;
import com.ksoe.energynet.valueobject.filter.FINMaterialsFilter;
import com.ksoe.energynet.valueobject.lists.FINMaterialsShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMaterials;
  *
  */

public abstract class FINMaterialsControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMaterialsControllerEJBGen() {}

  /*FINMaterials. Добавить*/
  public int add(FINMaterials object)
   {
    try
     {
      FINMaterialsDAO objectDAO = new FINMaterialsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMaterials%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterials. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINMaterialsDAO objectDAO = new FINMaterialsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMaterials%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*FINMaterials. Изменить*/
  public void save(FINMaterials object)
   {
    try
     {
      FINMaterialsDAO objectDAO = new FINMaterialsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMaterials%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterials. Получить объект*/
  public FINMaterials getObject(int code)
   {
    try
     {
      FINMaterialsDAO objectDAO = new FINMaterialsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMaterials%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterials. Получить полный список*/
  public FINMaterialsShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMaterials. Получить список по фильтру*/
  public FINMaterialsShortList getFilteredList(FINMaterialsFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMaterials. Получить список для просмотра*/
  public FINMaterialsShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMaterials. Получить список для просмотра по фильтру*/
  public FINMaterialsShortList getScrollableFilteredList(FINMaterialsFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMaterialsDAO objectDAO = new FINMaterialsDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.FINMaterials%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMaterials. Получить список для просмотра по условию*/
  public FINMaterialsShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMaterialsFilter filterObject = new FINMaterialsFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}
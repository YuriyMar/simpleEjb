
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2012 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENBasisTypeDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENBasisType;
import com.ksoe.energynet.valueobject.filter.ENBasisTypeFilter;
import com.ksoe.energynet.valueobject.lists.ENBasisTypeShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENBasisType;
  *
  */

public abstract class ENBasisTypeControllerEJBGen extends GenericSessionStatelessBean
{
  public ENBasisTypeControllerEJBGen() {}

  /*ENBasisType. Добавить*/
  public int add(ENBasisType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBasisTypeValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENBasisType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBasisType. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENBasisType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENBasisType. Изменить*/
  public void save(ENBasisType object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENBasisTypeValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENBasisType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBasisType. Получить объект*/
  public ENBasisType getObject(int code)
   {
    try
     {
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENBasisType%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBasisType. Получить полный список*/
  public ENBasisTypeShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENBasisType. Получить список по фильтру*/
  public ENBasisTypeShortList getFilteredList(ENBasisTypeFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENBasisType. Получить список для просмотра*/
  public ENBasisTypeShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENBasisType. Получить список для просмотра по фильтру*/
  public ENBasisTypeShortList getScrollableFilteredList(ENBasisTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENBasisType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENBasisType. Получить список для просмотра по условию*/
  public ENBasisTypeShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENBasisTypeFilter filterObject = new ENBasisTypeFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

  /*EENBasisType. Получить список ПК-кодов по фильтру*/
  public int[] getScrollableFilteredCodeArray(ENBasisTypeFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENBasisTypeDAO objectDAO = new ENBasisTypeDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getFilteredCodeArray(filterObject, fromPosition, quantity);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get code array of {%com.ksoe.energynet.valueobject.ENBasisType%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


}
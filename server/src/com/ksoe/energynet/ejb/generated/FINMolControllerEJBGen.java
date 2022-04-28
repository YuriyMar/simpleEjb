
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.FINMolDAO;
import com.ksoe.energynet.valueobject.FINMol;
import com.ksoe.energynet.valueobject.filter.FINMolFilter;
import com.ksoe.energynet.valueobject.lists.FINMolShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for FINMol;
  *
  */

public abstract class FINMolControllerEJBGen extends GenericSessionStatelessBean
{
  public FINMolControllerEJBGen() {}

  /*FINMol. Добавить*/
  public int add(FINMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return Integer.MIN_VALUE ; //objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. Удалить*/
  public void remove(int code)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      //objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*FINMol. Изменить*/
  public void save(FINMol object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isFINMolValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. Получить объект*/
  public FINMol getObject(int code)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return null; //objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.FINMol%} object.",e);}
    //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. Получить полный список*/
  public FINMolShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*FINMol. Получить список по фильтру*/
  public FINMolShortList getFilteredList(FINMolFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*FINMol. Получить список для просмотра*/
  public FINMolShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EFINMol. Получить список для просмотра по фильтру*/
  public FINMolShortList getScrollableFilteredList(FINMolFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      FINMolDAO objectDAO = new FINMolDAO(getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Нет связи с ФинКолекцией ... прозводиться перевод месяца или еще что-то ...",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*FINMol. Получить список для просмотра по условию*/
  public FINMolShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    FINMolFilter filterObject = new FINMolFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


}
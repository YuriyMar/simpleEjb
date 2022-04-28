
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENTravelSheetItemKindDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENTravelSheetItemKind;
import com.ksoe.energynet.valueobject.filter.ENTravelSheetItemKindFilter;
import com.ksoe.energynet.valueobject.lists.ENTravelSheetItemKindShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENTravelSheetItemKind;  
  * 	
  */

public abstract class ENTravelSheetItemKindControllerEJBGen extends GenericSessionStatelessBean
{
  public ENTravelSheetItemKindControllerEJBGen() {}

  /*ENTravelSheetItemKind. Добавить*/
  public int add(ENTravelSheetItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemKindValidForAdd(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemKindDAO objectDAO = new ENTravelSheetItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENTravelSheetItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemKind. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENTravelSheetItemKindDAO objectDAO = new ENTravelSheetItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENTravelSheetItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }   

   
  /*ENTravelSheetItemKind. Изменить*/
  public void save(ENTravelSheetItemKind object)
   {
    try
     {
/*
add validator here .....
      EnergyproValueObjectsValidator validator = new EnergyproValueObjectsValidator();
      if(!validator.isENTravelSheetItemKindValidForSave(object))
       throw new EnergyproSystemException(validator.getLastErrorMessage());
*/
      ENTravelSheetItemKindDAO objectDAO = new ENTravelSheetItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENTravelSheetItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemKind. Получить объект*/
  public ENTravelSheetItemKind getObject(int code)
   {
    try
     {
      ENTravelSheetItemKindDAO objectDAO = new ENTravelSheetItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENTravelSheetItemKind%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemKind. Получить полный список*/
  public ENTravelSheetItemKindShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENTravelSheetItemKind. Получить список по фильтру*/
  public ENTravelSheetItemKindShortList getFilteredList(ENTravelSheetItemKindFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENTravelSheetItemKind. Получить список для просмотра*/
  public ENTravelSheetItemKindShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENTravelSheetItemKind. Получить список для просмотра по фильтру*/
  public ENTravelSheetItemKindShortList getScrollableFilteredList(ENTravelSheetItemKindFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENTravelSheetItemKindDAO objectDAO = new ENTravelSheetItemKindDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENTravelSheetItemKind%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENTravelSheetItemKind. Получить список для просмотра по условию*/
  public ENTravelSheetItemKindShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENTravelSheetItemKindFilter filterObject = new ENTravelSheetItemKindFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }

   
}
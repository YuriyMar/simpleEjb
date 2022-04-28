
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb.generated;

import com.ksoe.energynet.dataminer.ENEstimateItemDAO;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENEstimateItem;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.GenericSessionStatelessBean;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;

  /**
  * EJB controller for ENEstimateItem;
  *
  */

public abstract class ENEstimateItemControllerEJBGen extends GenericSessionStatelessBean
{
  public ENEstimateItemControllerEJBGen() {}

  /*ENEstimateItem. Добавить*/
  public int add(ENEstimateItem object)
   {
    try
     {

      ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.add(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem. Удалить*/
  public void remove(int code)
   {
    try
     {
      ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.remove(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }


  /*ENEstimateItem. Изменить*/
  public void save(ENEstimateItem object)
   {
    try
     {

      ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      objectDAO.save(object);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem. Получить объект*/
  public ENEstimateItem getObject(int code)
   {
    try
     {
      ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getObject(code);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get {%com.ksoe.energynet.valueobject.ENEstimateItem%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem. Получить полный список*/
  public ENEstimateItemShortList getList()
   {
    return getScrollableFilteredList(null,0,-1);
   }

  /*ENEstimateItem. Получить список по фильтру*/
  public ENEstimateItemShortList getFilteredList(ENEstimateItemFilter filterObject)
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  /*ENEstimateItem. Получить список для просмотра*/
  public ENEstimateItemShortList getScrollableList(int fromPosition, int quantity)
   {
    return getScrollableFilteredList(null,fromPosition,quantity);
   }

  /*EENEstimateItem. Получить список для просмотра по фильтру*/
  public ENEstimateItemShortList getScrollableFilteredList(ENEstimateItemFilter filterObject, int fromPosition, int quantity)
   {
    try
     {
      ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
      return objectDAO.getScrollableFilteredList(filterObject, fromPosition, quantity, null);
     }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem%} objects.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
   }

  /*ENEstimateItem. Получить список для просмотра по условию*/
  public ENEstimateItemShortList getScrollableListByCondition(String aCondition, int fromPosition, int quantity)
   {
    ENEstimateItemFilter filterObject = new ENEstimateItemFilter();
    filterObject.conditionSQL = aCondition;
    return getScrollableFilteredList(filterObject,fromPosition,quantity);
   }


  /**
   *  16.06.2012 +++
   *  ENEstimateItem. Список материалов для передачи счетчиков Метрологии под плановые замены
   *  обычный лист (getDetailedEstimateList) страшно тормозит на PostgreSQL 9
   *
   */
	public ENEstimateItemShortList getDetailedEstimateBySCCountersList(
			ENEstimateItemFilter eFilter, ENPlanWorkFilter pFilter) {
		try {

			ENEstimateItemDAO objectDAO = new ENEstimateItemDAO(
					getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getDetailedEstimateBySCCountersList(eFilter, pFilter);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENEstimateItem%} objects.",
					e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


}